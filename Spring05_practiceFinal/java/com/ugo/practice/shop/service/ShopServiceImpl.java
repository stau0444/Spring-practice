package com.ugo.practice.shop.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.exception.AccountEmptyException;
import com.ugo.practice.exception.OutOfStockException;
import com.ugo.practice.shop.dao.ShopDao;
import com.ugo.practice.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;

	@Override
	public void getList(ModelAndView mView) {
		List<ShopDto> list=shopDao.getList();
		int MaxRowNum=shopDao.getMaxRowNum();
		mView.addObject("list", list);
		mView.addObject("MaxRowNum", MaxRowNum);
	}
	
	@Transactional
	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		String id=(String)request.getSession().getAttribute("id");
		//1. 파라미터로 전달되는 구입할 상품 번호
		int num=Integer.parseInt(request.getParameter("num"));
		//2. 상품의 가격을 얻어온다.
		int price=shopDao.getPrice(num);
		//3. 상품의 가격만큼 계좌 잔액을 줄인다.
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		boolean isMinusMoney=shopDao.minusMoney(dto);
		if(isMinusMoney==false) {//만일 0 이 나오면 
			throw new AccountEmptyException("계좌 잔액이 부족합니다!");
		}
		System.out.println("money:"+isMinusMoney);
		//4. 가격의 10% 를 포인트로 적립한다.
		shopDao.plusPoint(dto);
		//5. 재고의 갯수를 1 줄인다.
		boolean isMinusCount=shopDao.minusCount(num);
		System.out.println("count:"+isMinusCount);
		//가격의 1/10만큼 포인트를 올린다.
		
	}

	@Override
	public void insertProduct(ModelAndView mView, ShopDto dto) {
		boolean isInserted=shopDao.insert(dto);
		mView.addObject("isInserted", isInserted);
	}
}
