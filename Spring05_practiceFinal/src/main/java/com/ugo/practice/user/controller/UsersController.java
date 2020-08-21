package com.ugo.practice.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.user.dto.UsersDto;
import com.ugo.practice.user.service.UsersService;

@Controller
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@RequestMapping("/user/insertForm")
	public String insertForm(){
		return "user/insertForm";
	}
	@RequestMapping("/user/insert")
	public ModelAndView insertForm(UsersDto dto,ModelAndView mView){
		service.adduser(mView, dto);
		mView.setViewName("user/insert");
		return mView;
	}
	@RequestMapping("/user/loginForm")
	public String loginform() {
		
		return("user/loginForm");
	}
	@RequestMapping(value="/user/login",method = RequestMethod.POST)
	public ModelAndView login(ModelAndView mView,UsersDto dto) {
		service.login(mView, dto);
		mView.setViewName("user/login");
		return mView;
	}
}
