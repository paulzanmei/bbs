package com.paul.bs.pojo;

import java.util.List;

import com.paul.bs.po.GrabCountry;

public class GrabCountryPOJO extends GrabCountry{
	
	private List<GrabCompanyPOJO> grabCompanys;

	public List<GrabCompanyPOJO> getChildren() {
		return grabCompanys;
	}

	public void setGrabCompanys(List<GrabCompanyPOJO> grabCompanys) {
		this.grabCompanys = grabCompanys;
	}
	
	
}
