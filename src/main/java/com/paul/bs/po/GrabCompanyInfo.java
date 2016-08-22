package com.paul.bs.po;

public class GrabCompanyInfo {
	
	private Integer id;
	private Integer grabCompanyId;
	private String email;
	private String domainName;
	private String emailName;
	
	public GrabCompanyInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GrabCompanyInfo(Integer grabCompanyId, String email, String domainName, String emailName) {
		super();
		this.grabCompanyId = grabCompanyId;
		this.email = email;
		this.domainName = domainName;
		this.emailName = emailName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getGrabCompanyId() {
		return grabCompanyId;
	}
	public void setGrabCompanyId(Integer grabCompanyId) {
		this.grabCompanyId = grabCompanyId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getEmailName() {
		return emailName;
	}

	public void setEmailName(String emailName) {
		this.emailName = emailName;
	}
	
	
	
}
