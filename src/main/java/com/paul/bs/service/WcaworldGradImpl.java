package com.paul.bs.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paul.bs.mapper.GrabCompanyInfoMapper;
import com.paul.bs.mapper.GrabCompanyMapper;
import com.paul.bs.mapper.GrabCountryMapper;
import com.paul.bs.po.GrabCompany;
import com.paul.bs.po.GrabCompanyInfo;
import com.paul.bs.po.GrabCountry;

@Service
public class WcaworldGradImpl  implements WcaworldGrad{

	private static final String urlCountry = "http://www.wcaworld.com/common/xmlhttp/memsearch.asp?type=city";
	private static final String urlCompanyl = "http://www.wcaworld.com/common/scripts/advance_search_result.asp?searchby=city&orderby=city&statecity=&city=&keyword=&allnet=yes&n=1&n=2&n=3&n=4&sid=0.8056887695354031";
	
	private static final Map<String, String> cookies = new HashMap<String, String>();
	
	//@Autowired
	private GrabCountryMapper countryMapper;
	//@Autowired
	private GrabCompanyMapper companyMapper;
	//@Autowired
	private GrabCompanyInfoMapper companyInfoMapper;
	
	private Logger logger = Logger.getLogger(WcaworldGradImpl.class);
	
	public boolean grabCountry() {
		//插入国家
		try {
			List<GrabCountry> countrylist = getCountry();
	        countryMapper.insert(countrylist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean grabCompany(){
		try {
			//List<GrabCountry> countrylist = countryMapper.selectAll();
			List<GrabCountry> countrylist = countryMapper.selectSection(430);
	        for (GrabCountry grabCountry : countrylist) {
	        	List<GrabCompany> grabCompanys = getCompanys(grabCountry);
	        	for (GrabCompany grabCompany : grabCompanys) {
					System.out.println(grabCompany.getName()+" = "+grabCompany.getUrl());
				}
	        	if(!grabCompanys.isEmpty()){
	        		companyMapper.insert(grabCompanys);
	        	}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	};
	
	public boolean grabCompanyInfo(){
		
		
		cookies.put("ASPSESSIONIDQQCTQCCR", "KBKGALFCLJFFJIJMPGBCLDKG");
		cookies.put("ASPSESSIONIDSSARRBBT", "LJJPPMHCHKEHDHCMBJPKCJEO");
		try {
			//List<GrabCompany> grabCompanys = companyMapper.selectAll();
			List<GrabCompany> grabCompanys = companyMapper.selectSection(938);
			for (GrabCompany grabCompany : grabCompanys) {
				List<GrabCompanyInfo> companyInfos = getCompanyInfo(grabCompany);
				if(!companyInfos.isEmpty()){
					for (GrabCompanyInfo grabCompanyInfo : companyInfos) {
		  				System.out.println(grabCompanyInfo.getEmail());
					}
		  			companyInfoMapper.insert(companyInfos);
		  			System.out.println("抓取完成，已插入数据库....");
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	};
	
	
	/**
	 * 获取国家列表
	 * @return
	 */
	private List<GrabCountry> getCountry(){
		String html = com.paul.bs.util.Utils.URLGrabHtml(urlCountry, null);
		String[] countrys = html.split("\\|");
		List<GrabCountry> list = new ArrayList<GrabCountry>();
		for (String s : countrys) {
			if(!s.startsWith("#")){
				String[] ss = s.split("#");
				if(ss.length==2){
					list.add(new GrabCountry(ss[1], ss[0]));
				}
				
			}
		}
		return list;
	}
	
	
	/**
	 * 获取公司列表
	 * @param countryMap 国家map
	 * @return
	 */
	private List<GrabCompany> getCompanys(GrabCountry grabCountry){
		List<GrabCompany> grabCompanys = new ArrayList<GrabCompany>();
		
		//for (GrabCountry grabCountry : grabCountrys) {
			String urlCompanyl = WcaworldGradImpl.urlCompanyl +"&name="+grabCountry.getKey();//+"&page=3"
			String html = com.paul.bs.util.Utils.URLGrabHtml(urlCompanyl, null);//获取每家公司list表
			List<GrabCompany> companys = getCompany(html,1,grabCountry.getId());
			grabCompanys.addAll(companys);
		//}
		return grabCompanys;
	}
	/**
	 * 获取分页的具体国家
	 * @param html
	 * @param page
	 * @return
	 */
	private List<GrabCompany> getCompany(String html,int page,int grabCountryId){
		List<GrabCompany> grabCompanys = new ArrayList<GrabCompany>();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("ul[style='list-style-type:disc']");
		for (Element element : elements) {
			element = element.getElementsByTag("li").first().getElementsByTag("a").first();
			grabCompanys.add(new GrabCompany(element.text(), element.attr("href"), grabCountryId));
		}
		
		if(!doc.select("a.loadmore").isEmpty() && !doc.select("tr").isEmpty()){
			page = page+1;
			System.out.println("递归开始...."+(page-1));
			String a = doc.select(".navparam").first().attr("value");
			String url ="http://www.wcaworld.com/common/scripts/advance_search_result.asp?"+a+"&page="+page;
			String newhtml = com.paul.bs.util.Utils.URLGrabHtml(url, null);
			List<GrabCompany> newGrabCompanys = getCompany(newhtml,page,grabCountryId);
			grabCompanys.addAll(newGrabCompanys);
		}
		return grabCompanys;
	}
	
	
	private List<GrabCompanyInfo> getCompanyInfo(GrabCompany grabCompany){
		List<GrabCompanyInfo> listInfo = new ArrayList<GrabCompanyInfo>();
		
			String url = grabCompany.getUrl();
			if(url.startsWith("members.asp")){
				url = "http://www.wcaworld.com/eng/"+url;
			}
			System.out.println("开始抓取:"+url);
			Document document;
			try {
				document = Jsoup.connect(url)
						.cookies(cookies)
						.timeout(50000)
						.get();
				Element table = document.select("#content_right .memberprofile_mainbox .table-responsive .table-bordered").first();
				Elements as = table.getElementsByTag("a");
	    		for (Element element : as) {
					if(element.text().contains("@")){
						String[] ss = element.text().split("@");
						listInfo.add(new GrabCompanyInfo(grabCompany.getId(), element.text(),ss[1],ss[0]));
					}
				}
	    	
			}catch(NullPointerException e){
				logger.error("url抓取失败:"+url);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			catch (IOException e) {
				logger.error("url抓取失败:"+url);
				logger.error(e.getMessage());
				e.printStackTrace();
			}
			
			
		
		return listInfo;
	}
	
}
