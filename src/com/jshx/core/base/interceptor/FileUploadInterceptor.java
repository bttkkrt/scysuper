/**
 * Copyright 2014 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2014-02-24         YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.interceptor;

import java.util.ArrayList;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import com.jshx.core.utils.SysPropertiesUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FileUploadInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = -5177938896905631273L;

	@SuppressWarnings("finally")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null; 
		
		try {
			String[] allowableFileTypes = null;
			String allowableFileTypesStr = SysPropertiesUtil.getProperty("allowableUploadFileTypes");
			if(null!=allowableFileTypesStr && !"".equals(allowableFileTypesStr)){
				allowableFileTypes = allowableFileTypesStr.split(",");
				ArrayList<String> arrayList = new ArrayList<String>();
				for(String type : allowableFileTypes){
					arrayList.add(type);
				}
				
				if(!arrayList.isEmpty()){
					HttpServletRequest request = ServletActionContext.getRequest();
			        if(ServletFileUpload.isMultipartContent(request)){
			        	request.getSession().removeAttribute("FileUploadErrorMsg");
			        	MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper)request;
			        	Enumeration<String> enu = wrapper.getFileParameterNames();
			        	int count = 0;
						while (enu.hasMoreElements()) {
							count++;
							String param = enu.nextElement();
							String[] fileNames = wrapper.getFileNames(param);
							for(String fileName : fileNames){
								if(null!=fileName && !"".equals(fileName)){
									String fileType = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
									if(arrayList.contains(fileType)){
										//允许上传的文件类型
										result = invocation.invoke();
									}else{
										//不允许上传的文件类型
										request.getSession().setAttribute("FileUploadErrorMsg", fileType+"文件在配置中是不允许上传的");
										result = Action.INPUT;
									}
								}
							}
						}
						if(0 == count)
							result = invocation.invoke();
			        }else{
			        	result = invocation.invoke();
			        }
				}else{
					result = invocation.invoke();
				}				
			}else{
				result = invocation.invoke();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			return result;		
		}
	}

}
