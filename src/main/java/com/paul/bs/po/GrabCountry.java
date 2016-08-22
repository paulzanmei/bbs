package com.paul.bs.po;

public class GrabCountry {

	private Integer id;
	private String name;
	private String key;
	
	
	
	public GrabCountry() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GrabCountry(String name, String key) {
		super();
		this.name = name;
		this.key = key;
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
