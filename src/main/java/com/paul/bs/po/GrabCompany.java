package com.paul.bs.po;

public class GrabCompany {

	private Integer id;
	private String name;
	private String url;
	private Integer grabCountryId;
	
	
	
	public GrabCompany() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GrabCompany(String name, String url, Integer grabCountryId) {
		super();
		this.name = name;
		this.url = url;
		this.grabCountryId = grabCountryId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getGrabCountryId() {
		return grabCountryId;
	}
	public void setGrabCountryId(Integer grabCountryId) {
		this.grabCountryId = grabCountryId;
	}
	
	
	
}
