package com.ugo.practice.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.shop.dto.ShopDto;
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
	@RequestMapping("/shop/detail")
	public String detail() {
		return "shop/detail";
	}
	@RequestMapping("/shop/insert_form")
	public String insert_form() {
		return "shop/insert_form";
	}
	@RequestMapping("/shop/insert")
	public ModelAndView insert(ModelAndView mView,ShopDto dto) {
		shopService.insertProduct(mView, dto);
		mView.setViewName("shop/insert");
		return mView;
	}
}
