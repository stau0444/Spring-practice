package com.ugo.practice.cafe.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.cafe.dto.CafeCommentDto;
import com.ugo.practice.cafe.dto.CafeDto;

public interface CafeService{
	public void getList(HttpServletRequest request);
	public void insertContent(ModelAndView mView,CafeDto dto);
	public void getUserInfo(ModelAndView mView,@RequestParam int num,HttpServletRequest request);
	public void updateUserInfo(ModelAndView mView,CafeDto dto);
	public void deleteUser(ModelAndView mView,int num,HttpSession session);
	public void insertComment(ModelAndView mView,HttpServletRequest request);
}
