package com.ugo.practice.cafe.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.cafe.dto.CafeDto;

public interface CafeService{
	public void getList(ModelAndView mView);
	public void insertContent(ModelAndView mView,CafeDto dto);
	public void getUserInfo(ModelAndView mView,@RequestParam int num);
	public void updateUserInfo(ModelAndView mView,CafeDto dto);
	public void deleteUser(ModelAndView mView,int num,HttpSession session);
}
