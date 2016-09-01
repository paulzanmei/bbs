package com.paul.bs.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片处理
 * @author zhongbaoluo
 *
 */
public class ImgUtil {
	
	public static Map<String,String> getImgStr(String htmlStr){     
	     String img="";     
	     Pattern p_image;     
	     Matcher m_image;     
	     Map<String,String> pics = new HashMap<String,String>();  
	  
	     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址     
	     p_image = Pattern.compile   
	             (regEx_img,Pattern.CASE_INSENSITIVE);     
	    m_image = p_image.matcher(htmlStr);   
	    while(m_image.find()){     
	         img = img + "," + m_image.group();  
	         
	         Matcher m  = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src  
	         while(m.find()){
	        	String imgurl = m.group(1);
	        	int la = imgurl.lastIndexOf("/");
	        	int lb = imgurl.lastIndexOf(".");
	        	String name = imgurl.substring(la+1, lb);
	        	pics.put(name, imgurl);
	         }  
	     }     
	        return pics;     
	 }
	
	public static String replaceImgStr(String html,Map<String,String> htmlStrs){
		for (String key : htmlStrs.keySet()) {
			String url = htmlStrs.get(key);
			html = html.replace(url, "cid:"+key);
		}
		return html;
	}
	
	public static String replaceHtmlImgStr(String html){
		Map<String,String> map = getImgStr(html);
		return replaceImgStr(html,map);
	}
	
	public static void setImgFile(Map<String,String> imgStrs,Map<String,File> imgFiles){
		for (String key : imgStrs.keySet()) {
			String name = imgStrs.get(key);
			String path = ImgUtil.class.getResource("/../../").getPath()+"/.."+name;
			File file = new File(path);
			if(file.exists()){
				imgFiles.put(key, file);
			}
		}
	}
}
