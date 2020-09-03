package com.ugo.practice.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ugo.practice.shop.dto.ShopDto;

@Repository
public class ShopDaoImpl implements ShopDao {

	@Autowired
	private SqlSession session;

	@Override
	public List<ShopDto> getList() {
		List<ShopDto> list=session.selectList("shop.getList");
		return list;
	}

	@Override
	public boolean minusCount(int num) {
		int isMinus=session.update("shop.minusCount",num);
		if(isMinus>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean minusMoney(ShopDto dto) {
		int isMinus=session.update("shop.minusMoney",dto);
		if(isMinus>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void plusPoint(ShopDto dto) {
		session.update("shop.plusPoint",dto);
		
	}

	@Override
	public int getPrice(int num) {
		int price=session.selectOne("shop.getPrice", num);
		return price;
	}

	@Override
	public boolean insert(ShopDto dto) {
		int isInserted=session.insert("shop.insert",dto);
		if(isInserted>0) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public int getMaxRowNum() {
		int MaxRowNum=session.selectOne("shop.getMRN");
		return MaxRowNum;
	}
}
