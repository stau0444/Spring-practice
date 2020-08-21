package com.ugo.practice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.user.dao.UsersDao;
import com.ugo.practice.user.dto.UsersDto;

@Service
public class UsersServiceImpl implements UsersService {
	@Autowired
	private UsersDao usersDao;

	@Override
	public void adduser(ModelAndView mView, UsersDto dto) {
		boolean isSuccess=usersDao.insert(dto);
		mView.addObject("isSuccess", isSuccess);
	}

	@Override
	public void login(ModelAndView mView, UsersDto dto) {
		UsersDto userInfo=usersDao.getUserInfo(dto.getId());
		boolean isValid=false;
		if(userInfo!=null){
			String userPwd=userInfo.getPwd();
			isValid=BCrypt.checkpw(dto.getPwd(), userPwd);
			System.out.println(dto.getPwd());
			System.out.println(userPwd);
		}
		if(isValid==true) {
			mView.addObject("isValid",true);
		}else {
			mView.addObject("isValid",false);
		}
	}
}
