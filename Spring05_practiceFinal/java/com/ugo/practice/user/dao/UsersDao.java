package com.ugo.practice.user.dao;

import java.util.List;

import com.ugo.practice.user.dto.UsersDto;

public interface UsersDao {
	public boolean insert(UsersDto dto);
	public boolean delete(String id);
	public boolean update(UsersDto dto);
	public List<UsersDto> getList();
	public UsersDto getUserInfo(String id);
	public boolean updatePwd(UsersDto dto);
	public void addwarnnum(String id);
	public boolean checkId(String id);
}
