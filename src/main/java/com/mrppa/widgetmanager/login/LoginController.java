package com.mrppa.widgetmanager.login;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mrppa.widgetmanager.store.WidgetStore;

@Controller
public class LoginController {
	@Autowired
	private MessageSource messageSource;


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout , Locale locale) {
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("errorMsg",  messageSource.getMessage("login.error", null, locale));
		}

		if (logout != null) {
			model.addObject("successMsg",  messageSource.getMessage("login.logoutmsg", null, locale));
		}
		model.setViewName("login/login");
		return model;
	}

}
