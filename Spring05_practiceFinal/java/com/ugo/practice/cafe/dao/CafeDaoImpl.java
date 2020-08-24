package com.ugo.practice.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ugo.practice.cafe.dto.CafeDto;

@Repository
public class CafeDaoImpl implements CafeDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public boolean insert(CafeDto dto) {
		int isInsert=session.insert("cafe.insert",dto);
		if(isInsert>0) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public boolean delete(int num) {
		int isDelete=session.delete("cafe.delete",num);
		if(isDelete>0) {
			return true;
		}else {
			return false;			
		}
	}

	@Override
	public boolean update(CafeDto dto) {
		int isUpdate=session.update("cafe.update",dto);
		if(isUpdate>0) {
			return true;
		}else {			
			return false;
		}
	}

	@Override
	public List<CafeDto> getList() {
		List<CafeDto> list=session.selectList("cafe.getList");
		return list;
	}

	@Override
	public CafeDto getContent(int num) {
		CafeDto dto=session.selectOne("cafe.getData",num);
		return dto;
	}
}
