package com.paul.bs.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Service
public class SocketHandler implements WebSocketHandler{
	
	
	 private static final Logger logger = Logger.getLogger(SocketHandler.class);
	 private static final List<WebSocketSession> users = new ArrayList<>();
	
	/**
	 * 建立连接
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("成功建立socket连接");
		users.add(session);
		session.sendMessage(new TextMessage("我们已经成功建立soket通信了"));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 错误
	 */
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		if(session.isOpen()){
			session.close();
		}
		logger.error("连接出现错误:"+exception.toString());
		users.remove(session);
	}

	/**
	 * 连接关闭
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// TODO Auto-generated method stub
		logger.info("连接关闭");
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	/**
	 * 发送群消息
	 * @param message
	 */
	public void sendMessageToUsers(TextMessage message){
		for (WebSocketSession webSocketSession : users) {
			try{
				if(webSocketSession.isOpen()){
					webSocketSession.sendMessage(message);
				}
			}catch(IOException e){
				
			}
		}
	}
	
	/**
	 * 向单个用户发行消息
	 * @param userName
	 * @param message
	 */
	public void sendMessageToUser(String userName, TextMessage message){
		for (WebSocketSession user : users) {
            if (user.getAttributes().get("user").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
	}
}
