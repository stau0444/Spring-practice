package com.ugo.practice.user.controller;

import javax.servlet.http.HttpSession;

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
	public ModelAndView login(ModelAndView mView,UsersDto dto,HttpSession session ) {
		service.login(mView, dto,session);
		mView.setViewName("user/login");
		return mView;
	}
	
	@RequestMapping("/logout")
	public ModelAndView logout(HttpSession session,ModelAndView mView) {
		session.invalidate();
		boolean isLogOut=true;
		mView.addObject("isLogOut",isLogOut);
		mView.setViewName("logout");
		return mView;
	}
	@RequestMapping("/private/userinfo")
		public ModelAndView userInfo(ModelAndView mView,HttpSession session) {
			service.getUserInfo(mView, session);
			mView.setViewName("user/private/userinfo");
			return mView;
		}
	@RequestMapping("/private/updateform")
	public ModelAndView updateform(ModelAndView mView,UsersDto dto,HttpSession session) {
		service.getUserInfo(mView, session);
		mView.setViewName("user/private/updateform");
		return mView;
	}
	@RequestMapping("/private/update")
	public ModelAndView UpdateUserInfo(ModelAndView mView,HttpSession session,UsersDto dto) {
		service.updateUserInfo(mView, session, dto);
		mView.setViewName("user/private/update"); 
		return mView;
	}
	
	@RequestMapping("/private/update_pwdform")
	public String update_pwdform() {
		return "user/private/update_pwdform";
	}
	@RequestMapping("/private/updatepwd")
	public ModelAndView updatePwd(ModelAndView mView,HttpSession session,UsersDto dto) {
		service.updatePwd(mView, session, dto);
		mView.setViewName("user/private/updatepwd");
		return mView;
	}
	@RequestMapping("/private/delete_form")
	public String delete_form() {
		
		return "user/private/delete_form";
	}
	@RequestMapping("/private/delete")
	public ModelAndView delete(ModelAndView mView,String pwd,HttpSession session) {
		service.delete(mView, pwd, session);
		mView.setViewName("user/private/delete");
		return mView;
	}
	
	
	
}
