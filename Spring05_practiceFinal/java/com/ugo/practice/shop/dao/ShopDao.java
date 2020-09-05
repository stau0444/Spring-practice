package com.ugo.practice.shop.dao;

import java.util.List;

import com.ugo.practice.cafe.dto.CafeDto;
import com.ugo.practice.shop.dto.ShopDto;

public interface ShopDao {
	public List<ShopDto> getList(ShopDto dto);
	public boolean minusCount(int num);//상품번호를 받아서 상품 갯수를 줄인다
	public boolean minusMoney(ShopDto dto);
	public void plusPoint(ShopDto dto);
	public int getPrice(int num);
	public boolean insert(ShopDto dto);
	public int getMaxRowNum(ShopDto dto);
}
