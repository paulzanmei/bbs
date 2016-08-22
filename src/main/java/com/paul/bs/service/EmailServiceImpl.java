package com.paul.bs.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.paul.bs.mapper.GrabCountryMapper;
import com.paul.bs.pojo.GrabCountryPOJO;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private GrabCountryMapper grabCountryMapper;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	
	
	@Override
	public List<GrabCountryPOJO> getEmailTreeList(Integer id) {
		List<GrabCountryPOJO> grabCountries = grabCountryMapper.selectRelation(id);
		return grabCountries;
	}

	@Override
	public void send(final GrabCountryPOJO grabCountryPOJO,final String subject,final String TomplateName,final Map<String,Object> model,final Map<String,File> files) {
		MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// TODO Auto-generated method stub
				MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,"utf-8");
				messageHelper.setTo("994282901<994282901@qq.com>");
				messageHelper.setFrom("paulzanmei<paulzanmei@163.com>");
				messageHelper.setSubject(subject);
				
				mimeMessage.addHeader("X-Mailer", "Foxmail 7, 2, 7, 174[cn]");
                mimeMessage.addHeader("X-Priority", "3");
				
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TomplateName, "utf-8", model);
				
				System.out.println(text);
				messageHelper.setText("ni hao a", true);
				
				Set<String> set = files.keySet();
                for (String string : set) {
                	//messageHelper.addInline(string, files.get(string));
				}
				
			}
		};
		
		this.javaMailSender.send(mimeMessagePreparator);
	}

}
