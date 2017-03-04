package com.mrppa.widgetmanager.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mrppa.widgetmanager.WidgetException;
import com.mrppa.widgetmanager.admin.services.WidgetServices;
import com.mrppa.widgetmanager.modal.Widget;

@Controller
@RequestMapping("/admin")
public class WidgetCenterController {
	private static final Logger LOG = LoggerFactory.getLogger(WidgetCenterController.class);

	@Autowired
	private WidgetServices widgetServices;

	@Autowired
	private MessageSource messageSource;

	public WidgetServices getWidgetServices() {
		return widgetServices;
	}

	public void setWidgetServices(WidgetServices widgetServices) {
		this.widgetServices = widgetServices;
	}

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	private void populateWigdgetCenter(ModelAndView modelAndView, Locale locale) {
		modelAndView.setViewName("admin/widgetCenter");
		try {
			List<Widget> widgets = widgetServices.getAllWidgets();
			modelAndView.addObject("widgetList", widgets);
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.widgetloaderror", null, locale));
		}
	}

	@RequestMapping(value = "/WidgetCenter", method = RequestMethod.GET)
	public ModelAndView viewWigetCenter(Locale locale) {
		LOG.debug("REQUEST TO VIEW WIDGET CENTER");
		ModelAndView modelAndView = new ModelAndView();
		this.populateWigdgetCenter(modelAndView, locale);
		return modelAndView;
	}

	@RequestMapping(value = "/viewAddWidget", method = RequestMethod.GET)
	public ModelAndView viewAddWidget() {
		LOG.debug("REQUEST TO VIEW ADD WIDGET PAGE");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/addWidget");
		modelAndView.addObject("widget", new Widget());
		return modelAndView;
	}

	@RequestMapping(value = "/postAddWidget", method = RequestMethod.POST)
	public ModelAndView postAddWidget(@ModelAttribute Widget widget, Locale locale) {
		LOG.info("ADD WIDGET REQUEST");
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (this.widgetServices.getWidget(widget.getName()) != null) {
				modelAndView.setViewName("admin/addWidget");
				modelAndView.addObject("widget", widget);
				modelAndView.addObject("errorMsg", messageSource.getMessage("addwidget.alreadyexists", null, locale));
			} else {
				this.widgetServices.addWidget(widget);
				this.populateWigdgetCenter(modelAndView, locale);
				String[] arr = { widget.getName() };
				modelAndView.addObject("successMsg", messageSource.getMessage("addwidget.success", arr, locale));
			}
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			this.populateWigdgetCenter(modelAndView, locale);
			modelAndView.addObject("errorMsg", messageSource.getMessage("addwidget.failure", null, locale));
		}
		LOG.info("ADD WIDGET REQUEST COMPLETED");
		return modelAndView;
	}

	@RequestMapping(value = "/viewModifyWidget", method = RequestMethod.GET)
	public ModelAndView viewModifyWidget(@RequestParam("widgetName") String widgetName, Locale locale) {
		LOG.debug("REQUEST TO VIEW ADD WIDGET PAGE \twidgetName\t" + widgetName);
		ModelAndView modelAndView = new ModelAndView();

		try {
			Widget widget = this.widgetServices.getWidget(widgetName);
			if (widget != null) {
				modelAndView.setViewName("admin/modifyWidget");
				modelAndView.addObject("widget", widget);
			} else {
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				this.populateWigdgetCenter(modelAndView, locale);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
			}
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			this.populateWigdgetCenter(modelAndView, locale);
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
		}

		return modelAndView;
	}

	@RequestMapping(value = "/postModifyWidget", method = RequestMethod.POST)
	public ModelAndView postModifyWidget(@ModelAttribute Widget widget, Locale locale) {
		LOG.info("MODIFY WIDGET REQUEST");
		ModelAndView modelAndView = new ModelAndView();
		try {
			this.widgetServices.updateWidget(widget);
			this.populateWigdgetCenter(modelAndView, locale);
			String[] arr = { widget.getName() };
			modelAndView.addObject("successMsg", messageSource.getMessage("modifywidget.success", arr, locale));

		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			this.populateWigdgetCenter(modelAndView, locale);
			modelAndView.addObject("errorMsg", messageSource.getMessage("modifywidget.failure", null, locale));
		}
		LOG.info("MODIFY WIDGET REQUEST COMPLETED");
		return modelAndView;
	}

	@RequestMapping(value = "/viewUploadWidget", method = RequestMethod.GET)
	public ModelAndView viewUploadWidget(@RequestParam("widgetName") String widgetName, Locale locale) {
		LOG.debug("REQUEST TO VIEW ADD WIDGET PAGE \twidgetName\t" + widgetName);
		ModelAndView modelAndView = new ModelAndView();

		try {
			Widget widget = this.widgetServices.getWidget(widgetName);
			if (widget != null) {
				modelAndView.setViewName("admin/uploadWidget");
				modelAndView.addObject("widget", widget);
			} else {
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				this.populateWigdgetCenter(modelAndView, locale);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
			}
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			this.populateWigdgetCenter(modelAndView, locale);
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
		}

		return modelAndView;
	}

//	@RequestMapping(value = "/postUploadWidget", method = RequestMethod.POST)
	@PostMapping("/postUploadWidget") 
	public ModelAndView postUploadWidget(@RequestParam("file") MultipartFile file,
			@RequestParam("widgetName") String widgetName, Locale locale) {
		LOG.info("REQUEST TO UPLOAD WIDGET \twidgetName\t" + widgetName);
		ModelAndView modelAndView = new ModelAndView();
		Widget widget=null;
		try {
			widget = this.widgetServices.getWidget(widgetName);
			
			if(widget==null)
			{
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				this.populateWigdgetCenter(modelAndView, locale);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
			}
			else if(file.isEmpty())
			{
				LOG.error("EMPTY FILE DETECTED");
				modelAndView.setViewName("admin/uploadWidget");
				modelAndView.addObject("errorMsg", messageSource.getMessage("uploadwidget.emptyfileerror", null, locale));
				modelAndView.addObject("widget", widget);
			}else{
				this.widgetServices.uploadWidget(widget, file.getInputStream());
				this.populateWigdgetCenter(modelAndView, locale);
				String[] arr = { widget.getName() };
				modelAndView.addObject("successMsg", messageSource.getMessage("modifywidget.success", arr, locale));
			}
			
		} catch (WidgetException e) {
			LOG.error("ERROR" ,e);
			this.populateWigdgetCenter(modelAndView, locale);
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
		} catch (IOException e) {
			LOG.error("FILE READ EXCEPTION",e);
			modelAndView.setViewName("admin/uploadWidget");
			modelAndView.addObject("widget", widget);
		}

		return modelAndView;

	}

}
