package com.paul.bs.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.socket.TextMessage;

import com.paul.bs.mapper.GrabCountryMapper;
import com.paul.bs.po.GrabCountry;
import com.paul.bs.pojo.GrabCompanyInfoPOJO;
import com.paul.bs.pojo.GrabCompanyPOJO;
import com.paul.bs.pojo.GrabCountryPOJO;
import com.paul.bs.socket.SocketHandler;

@Service
public class EmailServiceImpl implements EmailService{
	
	@Autowired
	private GrabCountryMapper grabCountryMapper;
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	@Autowired
	private SocketHandler socketHandler;
	
	private Logger logger = Logger.getLogger(EmailServiceImpl.class);
	
	@Override
	public List<GrabCountryPOJO> getEmailTreeList(Integer id) {
		List<GrabCountryPOJO> grabCountries = grabCountryMapper.selectRelation(id);
		return grabCountries;
	}

	@Override
	public boolean send(final List<GrabCompanyPOJO> companyPOJOs,final String subject,final String TomplateName,final Map<String,Object> model,final Map<String,File> files) {
		Thread t = new Thread(new EmailSendRunnable(companyPOJOs,subject,TomplateName,model,files));
		t.start();
		return true;
	}

	@Override
	public List<GrabCountry> getAllGrabCountry() {
		return grabCountryMapper.selectAll();
	}
	
	
	private class EmailSendRunnable implements Runnable{
		
		private List<GrabCompanyPOJO> companyPOJOs;
		private String subject;
		private String TomplateName;
		private Map<String,Object> model;
		private Map<String,File> files;
		
		public  EmailSendRunnable(final List<GrabCompanyPOJO> companyPOJOs,final String subject,final String TomplateName,final Map<String,Object> model,final Map<String,File> files) {
			// TODO Auto-generated constructor stub
			this.companyPOJOs = companyPOJOs;
			this.subject = subject;
			this.TomplateName = TomplateName;
			this.model = model;
			this.files = files;
		}
		
		@Override
		public void run() {
			int count = 0;
			int length = companyPOJOs.size();
			for (GrabCompanyPOJO grabCompanyPOJO : companyPOJOs) {
				count++;
				int i = grabCompanyPOJO.getId();
				final StringBuilder emails = new StringBuilder();
				final List<GrabCompanyInfoPOJO> companyInfoPOJOs = grabCompanyPOJO.getChildren();
				if(!companyInfoPOJOs.isEmpty()){
					try{
							MimeMessagePreparator mimeMessagePreparator = new MimeMessagePreparator() {
								@Override
								public void prepare(MimeMessage mimeMessage) throws Exception {
									MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true,"utf-8");
									
									//messageHelper.setTo("994282901<994282901@qq.com>");
									for (GrabCompanyInfoPOJO grabCompanyInfoPOJO : companyInfoPOJOs) {
										messageHelper.addTo(grabCompanyInfoPOJO.getName()+"<"+grabCompanyInfoPOJO.getEmail()+">");
										emails.append(grabCompanyInfoPOJO.getEmail());
										emails.append(";");
									}
									emails.deleteCharAt(emails.length()-1);
									
									
									messageHelper.setFrom("paulzanmei<paulzanmei@163.com>");
									messageHelper.setSubject(subject);
									
									mimeMessage.addHeader("X-Mailer", "Foxmail 7, 2, 7, 174[cn]");
					                mimeMessage.addHeader("X-Priority", "3");
									
									String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, TomplateName, "utf-8", model);
									
									//System.out.println(text);
									messageHelper.setText(text, true);
									
									Set<String> set = files.keySet();
					                for (String string : set) {
					                	messageHelper.addInline(string, files.get(string));
									}
								}
							};
							
							
							logger.info("开始发送:"+emails.toString());
							socketHandler.sendMessageToUsers(new TextMessage("开始发送:"+emails.toString()));
							
							//javaMailSender.send(mimeMessagePreparator);
							
							logger.info("发送成功!");
							socketHandler.sendMessageToUsers(new TextMessage("发送成功!"));
							logger.info("还有"+(length-count)+"家公司等待发送");
							socketHandler.sendMessageToUsers(new TextMessage("还有"+(length-count)+"家公司等待发送"));
					
					}catch (MailException e) {
						//错误日志
						emails.deleteCharAt(emails.length()-1);
						logger.error("发送失败："+emails.toString());
						socketHandler.sendMessageToUsers(new TextMessage("发送失败："+emails.toString()));
						logger.error(e.getMessage());
						socketHandler.sendMessageToUsers(new TextMessage(e.getMessage()));
					}
					
					if(count<length){
						int a = (int)(1+Math.random()*10);
						long b = a*(60*1000);
						logger.info("停顿时间:"+a+"分钟");
						socketHandler.sendMessageToUsers(new TextMessage("停顿时间:"+a+"分钟"));
						try {
							Thread.sleep(b);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
	}
	
}
