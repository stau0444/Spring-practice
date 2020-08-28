package com.ugo.practice.cafe.dao;

import java.util.List;

import com.ugo.practice.cafe.dto.CafeCommentDto;

public interface CafeCommentDao{
	
	public List<CafeCommentDto> getList(CafeCommentDto dto);
	public void insert(CafeCommentDto dto);
	public void update(CafeCommentDto dto);
	public void delete(int num);
	public CafeCommentDto getDist(int num);
	public int getSequence();
	public int getCount(int ref_group);
}
