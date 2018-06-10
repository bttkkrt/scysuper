package com.jshx.qyjbxx.util;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import com.jshx.qyjbxx.getartificialpersonbyorgcode.GetArtificialPersonByOrgCodePortType;
import com.jshx.qyjbxx.getartificialpersonbyorgcode.GetArtificialPersonByOrgCode_Service;

public class WebServiceFactory {

	/**
	 * servermanager wsdl 调用的用户名
	 */
	public static final String SERVERMANAGER_USER_NAME = "Zhaj_seivice@APP-00199";
	/**
	 * servermanager wsdl 调用的密钥用户名
	 */
	public static final String SERVERMANAGER_PASS_WORD = "zhaj";
	
	private static GetArtificialPersonByOrgCodePortType getArtificialPersonByOrgCodeService;
	
	public static String getFrxxInfoByOrgCode(String orgCode)
	{
		//初始化信息获取
		GetArtificialPersonByOrgCode_Service testService = new GetArtificialPersonByOrgCode_Service();
		testService.setHandlerResolver(new HandlerResolver() {
			
			@Override
			public List<Handler> getHandlerChain(PortInfo arg0) {
				List<Handler> handlerList = new ArrayList();
				handlerList.add(new ClientAuthenticationHandler(
						WebServiceFactory.SERVERMANAGER_USER_NAME,
						WebServiceFactory.SERVERMANAGER_PASS_WORD));
				return handlerList;
			}
		});
		getArtificialPersonByOrgCodeService = testService.getGetArtificialPersonByOrgCodeHttpSoap11Endpoint();
		String jsonStr = getArtificialPersonByOrgCodeService.getArtificialPersonByOrgCode(orgCode);
		return jsonStr;
	}

	public static void main(String[] args)
	{
		
		String jsonStr = getFrxxInfoByOrgCode("744821768");
		System.out.println(jsonStr);
	}
}
