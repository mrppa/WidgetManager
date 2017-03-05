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

import com.mrppa.widgetmanager.admin.services.WidgetKeyServices;
import com.mrppa.widgetmanager.admin.services.WidgetServices;
import com.mrppa.widgetmanager.modal.Widget;
import com.mrppa.widgetmanager.modal.WidgetKey;

@Controller
public class WidgetFrontController {
	private static final Logger LOG = LoggerFactory.getLogger(WidgetFrontController.class);

	@Autowired
	private WidgetServices widgetServices;

	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private WidgetKeyServices widgetKeyServices;

	public MessageSource getMessageSource() {
		return messageSource;
	}

	public WidgetServices getWidgetServices() {
		return widgetServices;
	}
	
	public WidgetKeyServices getWidgetKeyServices() {
		return widgetKeyServices;
	}

	public void setWidgetKeyServices(WidgetKeyServices widgetKeyServices) {
		this.widgetKeyServices = widgetKeyServices;
	}


	@RequestMapping("/widget/{widgetName}/{key}/**")
	public ResponseEntity openWidgetFile(@PathVariable String widgetName,@PathVariable String key, HttpServletRequest request) throws Exception {
		ResponseEntity responseEntity=null;
		Widget widget = this.widgetServices.getWidget(widgetName);
		if(widget==null)
		{
			HttpHeaders headers = new HttpHeaders();
			responseEntity=new ResponseEntity<String>( "INVALID WIDGET",headers,HttpStatus.BAD_REQUEST);
		}
		else
		{
			WidgetKey widgetKey=this.widgetKeyServices.getWidgetKey(widgetName, key);
			if(widgetKey==null)
			{
				HttpHeaders headers = new HttpHeaders();
				responseEntity=new ResponseEntity<String>( "INVALID WIDGET KEY",headers,HttpStatus.BAD_REQUEST);
			}
			else
			{
				try
				{
					String requestFilePath = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
					requestFilePath = requestFilePath.replaceFirst("/widget/" + widgetName + "/" + key +"/" , "");
					InputStream inputStream = this.widgetServices.readWidgetContentFile(widget, requestFilePath);
					InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
					if(inputStreamResource==null || !inputStreamResource.isOpen())
					{
						throw new Exception("NULL STREAM");
					}
					HttpHeaders headers = new HttpHeaders();
					responseEntity= new ResponseEntity(inputStreamResource, headers, HttpStatus.OK);
				}
				catch(Exception e){
					HttpHeaders headers = new HttpHeaders();
					responseEntity=new ResponseEntity<String>( "INVALID RESOURCE",headers,HttpStatus.NOT_FOUND);
				}
				
			}
		}
		return responseEntity;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public void setWidgetServices(WidgetServices widgetServices) {
		this.widgetServices = widgetServices;
	}
}
