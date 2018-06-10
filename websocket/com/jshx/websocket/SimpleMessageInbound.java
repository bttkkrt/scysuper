package com.jshx.websocket;

import java.io.IOException;  
import java.nio.ByteBuffer;  
import java.nio.CharBuffer;

import org.apache.catalina.websocket.MessageInbound;  
import org.apache.catalina.websocket.WsOutbound; 

public class SimpleMessageInbound extends MessageInbound {
	
	 @Override  
	    protected void onBinaryMessage(ByteBuffer arg0) throws IOException {  
	        // TODO Auto-generated method stub  
	          
	    }  
	  
	    @Override  
	    protected void onTextMessage(CharBuffer msg) throws IOException {  
	        for (MessageInbound messageInbound : InitServlet.getSocketList()) {  
	            CharBuffer buffer = CharBuffer.wrap(msg);  
	            WsOutbound outbound = messageInbound.getWsOutbound();  
	            outbound.writeTextMessage(buffer);  
	            outbound.flush();  
	        }  
	          
	    }  
	  
	    @Override  
	    protected void onClose(int status) {  
	        InitServlet.getSocketList().remove(this);  
	        super.onClose(status);  
	    }  
	  
	    @Override  
	    protected void onOpen(WsOutbound outbound) {  
	        super.onOpen(outbound);  
	        InitServlet.getSocketList().add(this);  
	    } 

}
