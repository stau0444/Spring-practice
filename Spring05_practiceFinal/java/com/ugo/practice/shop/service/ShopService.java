package com.ugo.practice.shop.service;

 import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.shop.dto.ShopDto;

public interface ShopService {
	public void getList(ModelAndView mView);
	public void buy(HttpServletRequest request,ModelAndView mView);
	public void insertProduct(ModelAndView mView,ShopDto dto);
}
