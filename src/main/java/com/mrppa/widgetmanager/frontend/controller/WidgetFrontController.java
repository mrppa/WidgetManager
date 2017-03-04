package com.mrppa.widgetmanager.frontend.controller;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;

import com.mrppa.widgetmanager.admin.services.WidgetServices;
import com.mrppa.widgetmanager.modal.Widget;

@Controller
public class WidgetFrontController {
	private static final Logger LOG = LoggerFactory.getLogger(WidgetFrontController.class);

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

	@RequestMapping("/widget/{widgetName}/**")
	public ResponseEntity openWidgetFile(@PathVariable String widgetName, HttpServletRequest request) throws Exception {
		Widget widget = this.widgetServices.getWidget(widgetName);
		String requestFilePath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		requestFilePath = requestFilePath.replaceFirst("/widget/" + widgetName + "/", "");
		InputStream inputStream = this.widgetServices.readWidgetContentFile(widget, requestFilePath);
		InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setWidgetServices(WidgetServices widgetServices) {
		this.widgetServices = widgetServices;
	}
}
