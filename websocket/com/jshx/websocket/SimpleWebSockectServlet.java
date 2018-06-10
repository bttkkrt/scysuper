package com.jshx.websocket;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;  
import org.apache.catalina.websocket.WebSocketServlet;

public class SimpleWebSockectServlet extends WebSocketServlet{
	
	private static final long serialVersionUID = -7178893327801338294L;  
	
	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {
		// TODO Auto-generated method stub
		return new SimpleMessageInbound();
	} 

}
