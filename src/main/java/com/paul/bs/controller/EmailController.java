package com.paul.bs.controller;

import java.io.File;
import java.util.Date;
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
import com.paul.bs.po.GrabEmailTemplate;
import com.paul.bs.pojo.GrabCompanyPOJO;
import com.paul.bs.pojo.GrabCountryPOJO;
import com.paul.bs.service.EmailService;
import com.paul.bs.vo.TreeData;
import com.sleepycat.je.tree.Tree;
import com.sun.tools.internal.ws.processor.model.Model;

/**
 * 后台邮件发送控制器
 * 
 * */
@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value="/send",method=RequestMethod.GET)
	public String send(ModelMap map){
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
		if(grabCountryPOJO.isEmpty()){
			map.put("msg", "请选择要发送的公司");
			return map;
		}
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
	
	@RequestMapping(value="/template",method=RequestMethod.GET)
	public String template(ModelMap map){
		map.put("template", emailService.selectList());
		return "emailtemplate";
	}
	@RequestMapping(value="/template/add",method=RequestMethod.GET)
	public String templateAdd(){
		return "emailtemplate_add";
	}
	
	@RequestMapping(value="/template/edit",method=RequestMethod.GET)
	public String templateEdit(Integer id,ModelMap map){
		map.put("template", emailService.selectTemplateByid(id));
		return "emailtemplate_edit";
	}
	
	@RequestMapping(value="/template/save",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap templateSave(GrabEmailTemplate emailTemplate){
		ModelMap map = new ModelMap();
		map.put("msg", "ok");
		emailTemplate.setCreatedTime(new Date(System.currentTimeMillis()));
		emailTemplate.setCreatedId(1);
		if(!emailService.insertTemplate(emailTemplate))
			map.put("msg", "保存失败");
		return map;
	}
	
	@RequestMapping(value="/template/edit",method=RequestMethod.POST)
	@ResponseBody
	public ModelMap templateEdit(GrabEmailTemplate emailTemplate){
		ModelMap map = new ModelMap();
		map.put("msg", "ok");
		emailTemplate.setUpdateTime(new Date(System.currentTimeMillis()));
		emailTemplate.setUpdateId(1);
		if(!emailService.updateByid(emailTemplate))
			map.put("msg", "修改失败");
		return map;
	}
}