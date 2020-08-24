package com.ugo.practice.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(IncorrectPathException.class)
	public ModelAndView incorrectPath(ModelAndView mView,IncorrectPathException ipe) {
		
		mView.addObject("exception",ipe);
		mView.setViewName("error/info");
		return mView;
	}
}
