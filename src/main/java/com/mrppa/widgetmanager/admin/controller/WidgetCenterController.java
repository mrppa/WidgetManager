package com.mrppa.widgetmanager.admin.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public WidgetServices getWidgetServices() {
		return widgetServices;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setWidgetServices(WidgetServices widgetServices) {
		this.widgetServices = widgetServices;
	}

	@RequestMapping(value = "/WidgetCenter", method = RequestMethod.GET)
	public ModelAndView viewWigetCenter(ModelAndView modelAndView, Locale locale) {
		LOG.debug("REQUEST TO VIEW WIDGET CENTER");
		modelAndView.addObject("pageHeader", messageSource.getMessage("widgetcenter.header", null, locale));
		modelAndView.setViewName("admin/widgetCenter");
		try {
			List<Widget> widgets = widgetServices.getAllWidgets();
			modelAndView.addObject("widgetList", widgets);
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.widgetloaderror", null, locale));
		}
		return modelAndView;
	}

	@RequestMapping(value = "/viewAddWidget", method = RequestMethod.GET)
	public ModelAndView viewAddWidget(ModelAndView modelAndView,Locale locale) {
		LOG.debug("REQUEST TO VIEW ADD WIDGET PAGE");
		modelAndView.setViewName("admin/addWidget");
		modelAndView.addObject("widget", new Widget());
		modelAndView.addObject("pageHeader", messageSource.getMessage("addwidget.header", null, locale));
		return modelAndView;
	}

	@RequestMapping(value = "/postAddWidget", method = RequestMethod.POST)
	public ModelAndView postAddWidget(@ModelAttribute @Valid Widget widget, BindingResult result, Locale locale) {
		LOG.info("ADD WIDGET REQUEST");
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (result.hasErrors()) {
				modelAndView.setViewName("admin/addWidget");
				modelAndView.addObject("widget", widget);
				modelAndView.addObject("pageHeader", messageSource.getMessage("addwidget.header", null, locale));
			} else if (this.widgetServices.getWidget(widget.getName()) != null) {
				modelAndView.setViewName("admin/addWidget");
				modelAndView.addObject("widget", widget);
				modelAndView.addObject("errorMsg", messageSource.getMessage("addwidget.alreadyexists", null, locale));
				modelAndView.addObject("pageHeader", messageSource.getMessage("addwidget.header", null, locale));
			} else {
				this.widgetServices.addWidget(widget);
				String[] arr = { widget.getName() };
				modelAndView.addObject("successMsg", messageSource.getMessage("addwidget.success", arr, locale));
				this.viewWigetCenter(modelAndView, locale);
			}
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("addwidget.failure", null, locale));
			this.viewWigetCenter(modelAndView, locale);
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
				modelAndView.addObject("pageHeader", messageSource.getMessage("modifywidget.header", null, locale));
			} else {
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
				this.viewWigetCenter(modelAndView, locale);
			}

		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
			this.viewWigetCenter(modelAndView, locale);
		}

		return modelAndView;
	}

	@RequestMapping(value = "/postModifyWidget", method = RequestMethod.POST)
	public ModelAndView postModifyWidget(@ModelAttribute @Valid Widget widget, BindingResult result, Locale locale) {
		LOG.info("MODIFY WIDGET REQUEST");
		ModelAndView modelAndView = new ModelAndView();
		try {
			if (result.hasErrors()) {
				modelAndView.setViewName("admin/modifyWidget");
				modelAndView.addObject("widget", widget);
				modelAndView.addObject("pageHeader", messageSource.getMessage("modifywidget.header", null, locale));
			} else {
				this.widgetServices.updateWidget(widget);
				String[] arr = { widget.getName() };
				modelAndView.addObject("successMsg", messageSource.getMessage("modifywidget.success", arr, locale));
				this.viewWigetCenter(modelAndView, locale);
			}

		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("modifywidget.failure", null, locale));
			this.viewWigetCenter(modelAndView, locale);
		}
		LOG.info("MODIFY WIDGET REQUEST COMPLETED");
		return modelAndView;
	}

	@RequestMapping(value = "/postDeleteWidget")
	public ModelAndView postDeleteWidget(@RequestParam("widgetName") String widgetName, Locale locale) {
		LOG.info("DELETE WIDGET REQUEST");
		ModelAndView modelAndView = new ModelAndView();
		Widget widget = null;
		try {
			widget = this.widgetServices.getWidget(widgetName);
			if (widget == null) {
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
				this.viewWigetCenter(modelAndView, locale);
			} else {
				this.widgetServices.deleteWidget(widget);
				String[] arr = { widget.getName() };
				modelAndView.addObject("successMsg", messageSource.getMessage("deletewidget.success", arr, locale));
				this.viewWigetCenter(modelAndView, locale);
			}

		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("deletewidget.failure", null, locale));
			this.viewWigetCenter(modelAndView, locale);
		}
		LOG.info("DELETE WIDGET REQUEST COMPLETED");
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
				modelAndView.addObject("pageHeader", messageSource.getMessage("uploadwidget.header", null, locale));
			} else {
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
				this.viewWigetCenter(modelAndView, locale);
			}
		} catch (WidgetException e) {
			LOG.error("ERROR\t" + e.getException());
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
			this.viewWigetCenter(modelAndView, locale);
		}

		return modelAndView;
	}

	@PostMapping("/postUploadWidget")
	public ModelAndView postUploadWidget(@RequestParam("file") MultipartFile file,
			@RequestParam("widgetName") String widgetName, Locale locale) {
		LOG.info("REQUEST TO UPLOAD WIDGET \twidgetName\t" + widgetName);
		ModelAndView modelAndView = new ModelAndView();
		Widget widget = null;
		try {
			widget = this.widgetServices.getWidget(widgetName);

			if (widget == null) {
				LOG.error("WIDGET NOT FOUND . WIDGET NAME\t" + widgetName);
				modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
				this.viewWigetCenter(modelAndView, locale);
			} else if (file.isEmpty()) {
				LOG.error("EMPTY FILE DETECTED");
				modelAndView.setViewName("admin/uploadWidget");
				modelAndView.addObject("errorMsg",messageSource.getMessage("uploadwidget.emptyfileerror", null, locale));
				modelAndView.addObject("widget", widget);
				modelAndView.addObject("pageHeader", messageSource.getMessage("uploadwidget.header", null, locale));
			} else {
				this.widgetServices.uploadWidget(widget, file.getInputStream());
				String[] arr = { widget.getName() };
				modelAndView.addObject("successMsg", messageSource.getMessage("modifywidget.success", arr, locale));
				this.viewWigetCenter(modelAndView, locale);
			}

		} catch (WidgetException e) {
			LOG.error("ERROR", e);
			modelAndView.addObject("errorMsg", messageSource.getMessage("widgetcenter.unknownError", null, locale));
			this.viewWigetCenter(modelAndView, locale);
		} catch (IOException e) {
			LOG.error("FILE READ EXCEPTION", e);
			modelAndView.setViewName("admin/uploadWidget");
			modelAndView.addObject("widget", widget);
		}

		return modelAndView;

	}

}
