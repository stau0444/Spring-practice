package com.ugo.practice.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.ugo.practice.user.dto.UsersDto;

@Repository
public class UsersDaoImpl implements UsersDao {
	
	@Autowired
	private SqlSession session;

	@Override
	public boolean insert(UsersDto dto) {
		//비밀번호 암호화를 위해 dto에서 비밀번호 뺴온다
		String inputPwd=dto.getPwd();
		//암호화 메소드 불러와서 암호화시킨다.
		BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
		//인코딩 시키고
		String encodedPwd=encoder.encode(inputPwd);
		System.out.println(encodedPwd);
		//다시 디티오에 넣어준다
		dto.setPwd(encodedPwd);
		//인서트
		int isSuccess=session.insert("users.insert",dto);
		if(isSuccess>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean delete(String id) {
		int isSuccess=session.delete("users.delete",id);
		if(isSuccess>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean update(UsersDto dto) {
		int isSuccess=session.update("users.update",dto);
		if(isSuccess>0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<UsersDto> getList() {
		List<UsersDto> list=session.selectList("users.getList");
		return list;
	}

	@Override
	public UsersDto getUserInfo(String id) {
		UsersDto dto=session.selectOne("users.getData", id);
		return dto;
	}
}
