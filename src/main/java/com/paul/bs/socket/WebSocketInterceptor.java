package com.paul.bs.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class WebSocketInterceptor implements HandshakeInterceptor{

	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if(request instanceof ServerHttpRequest){
		      ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
		      HttpSession session = servletRequest.getServletRequest().getSession();
		      if(session!=null){
		    	  attributes.put("user", "zhang");//session.getAttribute("user")
		      }
		    }
		    return true;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}

}
