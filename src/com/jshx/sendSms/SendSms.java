package com.jshx.sendSms;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.message.SOAPHeaderElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class SendSms {
		public static void main(String[] args) {
			try {
				SendSms sendSms =new SendSms();
				String result = sendSms.send("18036810195;18951603172", "测试短信");
				System.out.print(result);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public String send(String phone,String content) throws ParserConfigurationException
		{
			Service service = new Service();
			String url = "http://esb.sipac.gov.cn:5888/services/SmsService?wsdl";
			// 在浏览器中打开url，可以找到SOAPAction: ""
			String namespace = "http://www.royasoft.com.cn/sms/RemoteSendSMS/";
			String actionUri = "sendSMS";  // Action路径
			String op = "sendSMS";         //  要调用的方法名  发送短信
			Call call;
			try {
				call = (Call) service.createCall();
				call.setTargetEndpointAddress(new java.net.URL(url));

				call.setUseSOAPAction(true);
				call.setSOAPActionURI(namespace + actionUri); // action uri


				call.setOperationName(new QName(namespace, op));// 设置要调用哪个方法
				call.addParameter("sendSMSRequest",XMLType.SOAP_STRING,ParameterMode.IN);// 接口的参数
				call.setReturnType(XMLType.SOAP_STRING);
				call.setReturnClass(String.class);

                SOAPHeaderElement ppElement = setSoapHeader();
				call.addHeader(ppElement);

				Object[] params = new Object[] {getXML(phone,content)};

				String result = (String)call.invoke(params);//发送短信
				return result;

			} catch (ServiceException e1) {
				e1.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			return null;
		}
		/**
		 *
	     * 设置头信息SOAPHeader验证
	     * @return
	     * @throws ParserConfigurationException
	     * @see [类、类#方法、类#成员]
	     */
	    private SOAPHeaderElement setSoapHeader() throws ParserConfigurationException
	    {
	        // create document of w3c
	        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document document = db.newDocument();
	        // create Element
	        Element security = document.createElementNS("namespace", "Security");
	        security.setAttribute("xmlns:wsse", "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");  
	        Element usernameToken = document.createElementNS("namespace", "UsernameToken");
            usernameToken.setAttribute("xmlns:wsu","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd");
	        Element username = document.createElementNS("namespace", "Username");  
	        Element password = document.createElementNS("namespace", "Password");  

	        password.setAttribute("Type",  
	                "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText");

	        usernameToken.appendChild(username);
	        usernameToken.appendChild(password);
	        security.appendChild(usernameToken);
	       
	        // set value
	        Text usernameValue = document.createTextNode("Zhaj_seivice@APP-00199");
	        username.appendChild(usernameValue);
	        Text passwordValue = document.createTextNode("zhaj");
	        password.appendChild(passwordValue);
	       
	        // create SOAPHeaderElement
	        SOAPHeaderElement ppElement = new SOAPHeaderElement(security);
	        ppElement.setPrefix("wsse");
	        return ppElement;
	    }
	    
	    
	    private static String getXML(String phone,String content){
	    	String xmlString  = "<request>"
							    	+"<sendSmsRequest>"
							    	+"<ApplicationID>P000000000000088</ApplicationID>"
							    	+"<DestinationAddresses>" + phone + "</DestinationAddresses>"
							    	+"<Content>" + content + "</Content>"
							    	+"<Precedence>1</Precedence>"
							    	+"<Sms_Status>true</Sms_Status>"
							    	+"<DeliveryResultRequest>false</DeliveryResultRequest>"
							    	+"</sendSmsRequest>"
							    +"</request>";
	    	return xmlString;
	    }
	
	}

