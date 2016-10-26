package com.manefit.www.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);
	
	@ExceptionHandler(Exception.class)
	public ModelAndView errorModelAndView(Exception e){
		logger.info(e.toString());
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/error_page");
		mv.addObject("exception",e);
		return mv;
	}
}
