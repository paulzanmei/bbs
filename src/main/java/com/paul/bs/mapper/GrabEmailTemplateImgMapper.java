package com.paul.bs.mapper;

import com.paul.bs.po.GrabEmailTemplateImg;

public interface GrabEmailTemplateImgMapper {
	
	public GrabEmailTemplateImg selectByid(Integer id);
	public Integer insert(GrabEmailTemplateImg emailTemplateImg);
}
