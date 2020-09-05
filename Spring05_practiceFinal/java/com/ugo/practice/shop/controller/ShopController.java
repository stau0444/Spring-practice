package com.ugo.practice.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.shop.dto.ShopDto;
import com.ugo.practice.shop.service.ShopService;

@Controller
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@RequestMapping("/shop/list")
	public ModelAndView list(HttpServletRequest request,ModelAndView mView) {
		shopService.getList(request);
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
		System.out.println("컨트롤러:"+dto.getImage());
		shopService.insertProduct(mView, dto);
		mView.setViewName("shop/insert");
		return mView;
	}
	@RequestMapping("/shop/image_upload")
	@ResponseBody
	public Map<String, Object> savaProductImage(HttpServletRequest request,@RequestParam MultipartFile image){
		Map<String,Object> map=shopService.saveProductImage(request,image);
		return map; 
	}
}
