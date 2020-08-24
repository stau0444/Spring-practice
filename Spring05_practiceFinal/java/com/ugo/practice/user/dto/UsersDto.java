package com.ugo.practice.user.dto;

public class UsersDto {
	private String id;
	private String pwd;
	private String email;
	private String profile;
	private String regdate;
	private String newpwd;
	private int warnnum;
	
	public UsersDto() {}

	public UsersDto(String id, String pwd, String email, String profile, String regdate, String newpwd, int warnnum) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.profile = profile;
		this.regdate = regdate;
		this.newpwd = newpwd;
		this.warnnum = warnnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getnewpwd() {
		return newpwd;
	}

	public void setnewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public int getwarnnum() {
		return warnnum;
	}

	public void setwarnnum(int warnnum) {
		this.warnnum = warnnum;
	}
}
