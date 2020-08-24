package com.ugo.practice.user.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	public void login(ModelAndView mView, UsersDto dto,HttpSession session) {
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
			session.setAttribute("id", dto.getId());
		}else {
			mView.addObject("isValid",false);
		}
	}

	@Override
	public void getUserInfo(ModelAndView mView, HttpSession session) {
		String id=(String)session.getAttribute("id");
		UsersDto dto=usersDao.getUserInfo(id);
		mView.addObject("dto",dto);
	}

	@Override
	public void updateUserInfo(ModelAndView mView, HttpSession session,UsersDto dto) {
		String id=(String)session.getAttribute("id");
		boolean isUpdate=usersDao.update(dto);
		mView.addObject("isUpdate",isUpdate);
	}

	@Override
	public void updatePwd(ModelAndView mView, HttpSession session, UsersDto dto) {
		String id=(String)session.getAttribute("id");
		String savedPwd=usersDao.getUserInfo(id).getPwd();
		boolean isValid=BCrypt.checkpw(dto.getPwd(),savedPwd);
		if(isValid) {
			dto.setId(id);
			dto.setPwd(savedPwd);
			BCryptPasswordEncoder enc=new BCryptPasswordEncoder();
			String encodedPwd=enc.encode(dto.getnewpwd());
			System.out.println(encodedPwd);
			System.out.println(savedPwd);
			dto.setnewpwd(encodedPwd);
			boolean isSuccess=usersDao.updatePwd(dto);
			System.out.println(isSuccess);
			mView.addObject("isSuccess",isSuccess);
		}else {
			mView.addObject("isSuccess",false);
		}
		System.out.println(isValid);
	}

	@Override
	public void delete(ModelAndView mView, String pwd, HttpSession session) {
		String id=(String)session.getAttribute("id");
		UsersDto dto=usersDao.getUserInfo(id);
		boolean isValid=BCrypt.checkpw(pwd,dto.getPwd());
		if(isValid) {
				usersDao.delete(id);
				mView.addObject("isValid", true);
				session.invalidate();
		}else {
			mView.addObject("isValid", false);
		}	
	}

	
}
