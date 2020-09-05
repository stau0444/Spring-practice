package com.ugo.practice.shop.service;

 import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.shop.dto.ShopDto;

public interface ShopService {
	public void getList(HttpServletRequest request);
	public void buy(HttpServletRequest request,ModelAndView mView);
	public void insertProduct(ModelAndView mView,ShopDto dto);
	public Map<String,Object> saveProductImage(HttpServletRequest request,MultipartFile mFile);
}
