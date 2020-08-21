package com.ugo.practice.user.service;

import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.user.dto.UsersDto;

public interface UsersService {
	public void adduser(ModelAndView mView,UsersDto dto);
	public void login(ModelAndView mView,UsersDto dto);
}
