package com.ugo.practice.shop.dto;

public class ShopDto {
	private int num;
	private String name;
	private int price;
	private int remainCount;
	private String id;
	private int rownum;
	private int startRowNum;
	private int endRowNum;
	private String image;
	
	public ShopDto() {}

	public ShopDto(int num, String name, int price, int remainCount, String id, int rownum, int startRowNum,
			int endRowNum,String image) {
		super();
		this.num = num;
		this.name = name;
		this.price = price;
		this.remainCount = remainCount;
		this.id = id;
		this.rownum = rownum;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.image=image;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRownum() {
		return rownum;
	}

	public void setRownum(int rownum) {
		this.rownum = rownum;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
