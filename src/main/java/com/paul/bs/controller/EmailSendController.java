package com.paul.bs.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.paul.bs.po.GrabCountry;
import com.paul.bs.pojo.GrabCompanyPOJO;
import com.paul.bs.pojo.GrabCountryPOJO;
import com.paul.bs.service.EmailService;
import com.paul.bs.vo.TreeData;
import com.sleepycat.je.tree.Tree;

/**
 * 后台邮件发送控制器
 * 
 * */
@Controller
@RequestMapping("/emailsend")
public class EmailSendController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping
	public String indext(ModelMap map){
		map.put("grabcountrys", emailService.getAllGrabCountry());
		return "email";
	}
	
	@RequestMapping("/emaildata")
	@ResponseBody
	public List<GrabCountryPOJO> getEmailData(Integer id){
		if(id==null)
			id = 0;
		List<GrabCountryPOJO> list =  emailService.getEmailTreeList(id);
		return list;
	}
	
	
	
	@RequestMapping(value="/send",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap send(@RequestBody List<GrabCompanyPOJO> grabCountryPOJO){
		ModelMap map = new ModelMap();
		Map<String, Object> model = new HashMap<String,Object>();
		Map<String,File> files = new HashMap<>();
		files.put("a", new File(map.getClass().getResource("/img/log").getFile()));
		boolean is = emailService.send(grabCountryPOJO, "zhuti", "template/register.vm", model, files);
		if(is){
			map.put("msg", "ok");
		}else{
			map.put("msg", "发送失败！");
		}
		return map;
	}
}