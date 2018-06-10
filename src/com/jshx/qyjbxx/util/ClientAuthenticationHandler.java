package com.jshx.qyjbxx.util;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class ClientAuthenticationHandler implements SOAPHandler<SOAPMessageContext> {

	String userName;
	String passWord;
	
	public ClientAuthenticationHandler(String userName,String passWord){
		this.userName = userName;
		this.passWord = passWord;
	}
	
	@Override
	public boolean handleMessage(SOAPMessageContext ctx) {
		Boolean request_p = (Boolean) ctx
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
 
        if (request_p) {
            try {
                SOAPMessage msg = ctx.getMessage();
                SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
                SOAPHeader hdr = env.getHeader();
                if (hdr == null)
                    hdr = env.addHeader();
 
                SOAPHeaderElement security =  (SOAPHeaderElement) hdr.addChildElement("Security", "wsse",
                        "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                security.setAttribute("xmlns:wsu", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
                security.setMustUnderstand(true);
                SOAPElement  userNameToken = security.addChildElement("UsernameToken", "wsse");
                 
                userNameToken.setAttribute("wsu:Id", "UsernameToken-1");
                userNameToken.addChildElement("Username", "wsse").addTextNode(this.userName);
                SOAPElement password = userNameToken.addChildElement("Password", "wsse");
                password.setAttribute("Type", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");
                password.addTextNode(this.passWord);
 
                msg.saveChanges();
 
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
