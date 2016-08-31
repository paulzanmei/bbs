package com.paul.bs.po;

import java.util.Date;

public class GrabMailSendInfo {
	
	private Integer id;
	private String mail;
	private String state;
	private Date createdTime;
	private Integer createdId;
	
	
	
	public GrabMailSendInfo(String mail, String state, Date createdTime, Integer createdId) {
		this.mail = mail;
		this.state = state;
		this.createdTime = createdTime;
		this.createdId = createdId;
	}
	public GrabMailSendInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Integer getCreatedId() {
		return createdId;
	}
	public void setCreatedId(Integer createdId) {
		this.createdId = createdId;
	}
	
	
}
