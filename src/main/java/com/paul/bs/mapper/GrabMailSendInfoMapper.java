package com.paul.bs.mapper;

import com.paul.bs.po.GrabMailSendInfo;

public interface GrabMailSendInfoMapper {
	
	public GrabMailSendInfo selectByid(Integer id);
	public Integer insert(GrabMailSendInfo grabMailSendInfo);
}
