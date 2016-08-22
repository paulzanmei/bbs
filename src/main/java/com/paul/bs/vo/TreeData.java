package com.paul.bs.vo;

/**
 * 控件树 数据bean
 * @author zhongbaoluo
 *
 */
public class TreeData {
	
	private int id;
	private String name;
	/*private int level;*/
	private int pid;
	private boolean isParent;
	
	public TreeData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TreeData(int id, String name, int pid, boolean isParent) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.isParent = isParent;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}*/
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
	
	
}
