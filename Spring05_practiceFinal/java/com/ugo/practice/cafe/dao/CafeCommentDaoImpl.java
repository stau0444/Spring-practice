package com.ugo.practice.cafe.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ugo.practice.cafe.dto.CafeCommentDto;

@Repository
public class CafeCommentDaoImpl implements CafeCommentDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<CafeCommentDto> getList(CafeCommentDto dto) {
		List<CafeCommentDto> list=session.selectList("cafeComment.getList",dto);
		return list;
	}

	@Override
	public void insert(CafeCommentDto dto) {
		 session.insert("cafeComment.insert",dto);
	}

	@Override
	public void update(CafeCommentDto dto) {
		 session.update("cafeComment.update",dto);
	}

	@Override
	public void delete(int num) {
		 session.delete("cafeComment.delete",num);
	}

	@Override
	public CafeCommentDto getDist(int num) {
		 CafeCommentDto dto=session.selectOne("cafeComment.getData", num);
		return dto;
	}

	@Override
	public int getSequence() {
		 int num=session.selectOne("cafeComment.getSeq");
		return num;
	}

	@Override
	public int getCount(int ref_group) {
		 int num=session.selectOne("cafeComment.getCount",ref_group);
		return num;
	}
}
