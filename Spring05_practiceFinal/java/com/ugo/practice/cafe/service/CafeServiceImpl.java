package com.ugo.practice.cafe.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.cafe.dao.CafeDao;
import com.ugo.practice.cafe.dto.CafeDto;
import com.ugo.practice.exception.IncorrectPathException;
import com.ugo.practice.user.dao.UsersDao;

@Service
public class CafeServiceImpl implements CafeService{

	@Autowired
	private CafeDao cafeDao;
	@Autowired
	private UsersDao userDao;

	@Override
	public void getList(ModelAndView mView) {
		List<CafeDto> dto=cafeDao.getList();
		mView.addObject("list",dto);
	}

	@Override
	public void insertContent(ModelAndView mView, CafeDto dto) {
		
		boolean isInsert=cafeDao.insert(dto);
		mView.addObject("isInsert", isInsert);
	}

	@Override
	public void getUserInfo(ModelAndView mView,@RequestParam int num) {
		CafeDto dto=cafeDao.getContent(num);
		mView.addObject("dto",dto);
	}

	@Override
	public void updateUserInfo(ModelAndView mView, CafeDto dto) {
		boolean isUpdate=cafeDao.update(dto);
		mView.addObject("isUpdate", isUpdate);
		mView.addObject("num", dto.getNum());
	}

	@Override
	public void deleteUser(ModelAndView mView, int num,HttpSession session) {
		String writer=cafeDao.getContent(num).getWriter();
		String id=(String)session.getAttribute("id");
		
		if(!id.equals(writer)) {
			userDao.addwarnnum(id);
			int warnnum=userDao.getUserInfo(id).getwarnnum();
			if(warnnum>3) {
				userDao.delete(id);
				System.out.println("회원 삭제됨");
				session.invalidate();
			}
			throw new IncorrectPathException("예외 경로로 요청하셨습니다");
		}
		boolean isDelete=cafeDao.delete(num);
		mView.addObject("isDelete", isDelete);
	}
}
