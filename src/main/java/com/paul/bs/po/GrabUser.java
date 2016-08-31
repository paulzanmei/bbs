package com.paul.bs.po;

import java.util.Date;

public class GrabUser {
	
	private Integer id;
	private String username;
	private String password;
	private String mail;
	private String mailpassword;
	private Date createdTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMailpassword() {
		return mailpassword;
	}
	public void setMailpassword(String mailpassword) {
		this.mailpassword = mailpassword;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
