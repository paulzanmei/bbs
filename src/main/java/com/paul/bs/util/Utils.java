package com.paul.bs.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Utils {
	private static final String defaultCharSet = "UTF-8";
	public static String URLGrabHtml(String urls,String cooke){
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(urls);
		String entiryString = null;
		try {
			CloseableHttpResponse httpResponse = httpClient.execute(httpget);
			HttpEntity httpEntity = httpResponse.getEntity();
			if(httpResponse.getStatusLine().getStatusCode()==200){
				 entiryString = EntityUtils.toString(httpEntity, defaultCharSet);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entiryString;
	}
}
