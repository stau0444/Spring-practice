package com.ugo.practice.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.shop.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping("/shop/list")
	public ModelAndView list(ModelAndView mView) {
		shopService.getList(mView);
		mView.setViewName("shop/list");
		return mView;
	}
	@RequestMapping("/shop/buy")
	public ModelAndView buy(HttpServletRequest request , ModelAndView mView) {
		shopService.buy(request,mView);
		mView.setViewName("shop/buy");
		return mView;
	}
}
