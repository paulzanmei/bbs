package com.paul.bs.pojo;

import com.paul.bs.po.GrabCompanyInfo;

public class GrabCompanyInfoPOJO extends GrabCompanyInfo{
	
	private String name;

	public String getName() {
		return this.getEmail();
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
