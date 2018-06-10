package com.jshx.http.action;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duanpf.utils.DateTools;
import com.duanpf.utils.httpUtil.HttpUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MyAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware{
	protected Logger logger = LoggerFactory.getLogger(MyAction.class);
	private String strTemp;
	protected Map<String,Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	public void command(){
		try {
			response.setContentType("charset=utf-8");
			request.setCharacterEncoding("utf-8");
			logger.info("requert jsonStr = "+ strTemp);
			System.out.println(DateTools.parseDate2Str(new Date(), "yyyy-MM-dd HH:mm:ss"));
			System.out.println("requert jsonStr = "+ strTemp);
			
//			JSONObject jsonObj = JSONObject.fromObject(strTemp);
			JSONObject jsonObj = new JSONObject();
			strTemp = strTemp.replaceAll("\\{", "").replaceAll("\\}", "").replaceAll("\'", "").replaceAll("\"", "");
			String[] sss = strTemp.split(",");
			for(String ss:sss)
			{
				String[] s = ss.split(":");
				if(s.length == 2)
				{
					jsonObj.put(s[0], URLDecoder.decode(URLDecoder.decode(s[1],"utf-8"),"utf-8"));
				}
				else
				{
					jsonObj.put(s[0], "");
				}
			}
			
			System.out.println("requert jsonObj = "+ jsonObj.toString());
			HttpUtil hu = new HttpUtil();
			jsonObj = JSONObject.fromObject(hu.execute(jsonObj));
			logger.info("response jsonStr = "+ jsonObj.toString());
			System.out.println("response jsonStr = "+ jsonObj.toString());
			ServletActionContext.getResponse().getWriter().println(jsonObj.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xx(){
		System.out.println("1234567");
	}
	public String getStrTemp() {
		return strTemp;
	}

	public void setStrTemp(String strTemp) {
		this.strTemp = strTemp;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
}




















