package com.paul.bs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.paul.bs.po.GrabCompany;


/**
 * 公司
 * @author zhongbaoluo
 *
 */
public interface GrabCompanyMapper {
	
	public boolean insert(List<GrabCompany> list)throws Exception;
	
	@Select(value="SELECT GC.id AS id,GC.NAME AS name,GC.URL AS url,GC.`grab_country.id` AS grabCountryId FROM grab_company GC")
	public List<GrabCompany> selectAll();
	
	@Select(value="SELECT GC.id AS id,GC.NAME AS name,GC.URL AS url,GC.`grab_country.id` AS grabCountryId FROM grab_company GC WHERE GC.id >=#{id}")
	public List<GrabCompany> selectSection(int id);
	
	@Select(value="SELECT GC.id AS id,GC.NAME AS name,GC.URL AS url,GC.`grab_country.id` AS grabCountryId FROM grab_company GC WHERE GC.`grab_country.id` >=#{grabCountryId}")
	public List<GrabCompany> selectByGrabCountryId(int grabCountryId);
}
