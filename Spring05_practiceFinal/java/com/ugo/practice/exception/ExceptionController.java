package com.ugo.practice.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionController {
	@ExceptionHandler(IncorrectPathException.class)
	public ModelAndView incorrectPath(IncorrectPathException ipe) {
		
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception",ipe);
		mView.setViewName("error/info");
		return mView;
	}
	
//	@ExceptionHandler(DataAccessException.class)
//	public ModelAndView AccountEmpty(DataAccessException aee) {
//		ModelAndView mView=new ModelAndView();
//		mView.addObject("exception",aee);
//		mView.setViewName("error/accountempty");
//		return mView;
//	}
	@ExceptionHandler(OutOfStockException.class)
	public ModelAndView OutOfStock(OutOfStockException OOS) {
		ModelAndView mView=new ModelAndView();
		mView.addObject("exception",OOS);
		mView.setViewName("error/outofstock");
		return mView;
	}
}

