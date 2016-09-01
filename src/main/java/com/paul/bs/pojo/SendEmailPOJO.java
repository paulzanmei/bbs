package com.paul.bs.pojo;

import java.util.List;

public class SendEmailPOJO {

	private List<GrabCompanyPOJO> grabCountryPOJO;
	private Integer tomplateId;
	
	public List<GrabCompanyPOJO> getGrabCountryPOJO() {
		return grabCountryPOJO;
	}
	public void setGrabCountryPOJO(List<GrabCompanyPOJO> grabCountryPOJO) {
		this.grabCountryPOJO = grabCountryPOJO;
	}
	public Integer getTomplateId() {
		return tomplateId;
	}
	public void setTomplateId(Integer tomplateId) {
		this.tomplateId = tomplateId;
	}
	
	
}
