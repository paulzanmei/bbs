package com.paul.bs.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.paul.bs.pojo.GrabCountryPOJO;

public interface EmailService {
	
	/**
	 * 获取邮箱树列表
	 * @param pId 父id
	 * @param level 父层级
	 * @return
	 */
	public List<GrabCountryPOJO> getEmailTreeList(Integer pId);
	
	public void send(GrabCountryPOJO grabCountryPOJO,final String subject,final String TomplateName,final Map<String, Object> model,final Map<String,File> files);
}