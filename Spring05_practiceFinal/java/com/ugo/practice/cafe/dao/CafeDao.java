package com.ugo.practice.cafe.dao;

import java.util.List;

import com.ugo.practice.cafe.dto.CafeDto;

public interface CafeDao {
	public boolean insert(CafeDto dto);
	public boolean delete(int num);
	public boolean update(CafeDto dto);
	public List<CafeDto> getList();
	public CafeDto getContent(int num);
}
