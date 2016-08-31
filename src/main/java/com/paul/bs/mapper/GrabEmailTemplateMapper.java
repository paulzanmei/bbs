package com.paul.bs.mapper;

import java.util.List;

import com.paul.bs.po.GrabEmailTemplate;

public interface GrabEmailTemplateMapper {
	
	public GrabEmailTemplate selectByid(Integer id);
	public Integer insert(GrabEmailTemplate emailTemplate);
	public List<GrabEmailTemplate> selectList();
	public Integer updateByid(GrabEmailTemplate emailTemplate);
}
