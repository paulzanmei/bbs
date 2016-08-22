package com.paul.bs.pojo;

import java.util.List;

import com.paul.bs.po.GrabCompany;

public class GrabCompanyPOJO extends GrabCompany{
	
	private List<GrabCompanyInfoPOJO> grabCompanyInfos;

	public List<GrabCompanyInfoPOJO> getChildren() {
		return grabCompanyInfos;
	}

	public void setGrabCompanyInfos(List<GrabCompanyInfoPOJO> grabCompanyInfos) {
		this.grabCompanyInfos = grabCompanyInfos;
	}
	

}
