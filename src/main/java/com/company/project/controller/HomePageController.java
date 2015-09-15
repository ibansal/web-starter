package com.company.project.controller;

import com.company.project.service.HomePageService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class HomePageController {

	@Autowired
	HomePageService homePageService;

    private static final Logger LOGGER = Logger.getLogger(HomePageController.class);

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView hello(@PathVariable("name") String name) {
		ModelAndView model = new ModelAndView();
		Locale locale = LocaleContextHolder.getLocale();
		homePageService.getGoogleData(locale.toString());
		model.setViewName("hello");
		model.addObject("msg", name);
		model.addObject("lang", locale.toString());
		return model;

	}

	@RequestMapping(value = "/test/exception", method = RequestMethod.GET)
	public ModelAndView exception(@PathVariable("name") String name) {
		throw new IllegalArgumentException("Getting article problem.");
	}
}