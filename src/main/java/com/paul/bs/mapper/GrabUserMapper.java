package com.paul.bs.mapper;

import com.paul.bs.po.GrabUser;

public interface GrabUserMapper {
	
	public GrabUser selectByid(Integer id);
	public Integer insert(GrabUser grabUser);
}
