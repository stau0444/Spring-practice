package com.ugo.practice.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.cafe.dto.CafeCommentDto;
import com.ugo.practice.user.dto.UsersDto;

public interface UsersService {
	public void adduser(ModelAndView mView,UsersDto dto);
	public void login(ModelAndView mView,UsersDto dto,HttpSession session);
	public void getUserInfo(ModelAndView mView,HttpSession session);
	public void updateUserInfo(ModelAndView mView, HttpSession session,UsersDto dto);
	public void updatePwd(ModelAndView mView,HttpSession session,UsersDto dto);
	public void delete(ModelAndView mView,String pwd,HttpSession session);
}
