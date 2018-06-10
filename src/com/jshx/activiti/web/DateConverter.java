package com.jshx.activiti.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.beanutils.Converter;
/**
 * 日期型转变
 * 
* @author   YuWeitao
* @version  创建时间：2013-7-1 下午16:44:35  
* 
*/
public class DateConverter implements Converter {
    private static final SimpleDateFormat[] ACCEPT_DATE_FORMATS = { 
    	new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), 
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
        new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"),  
        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
        new SimpleDateFormat("dd/MM/yyyy"), 
        new SimpleDateFormat("yyyy-MM-dd"),  
        new SimpleDateFormat("yyyy.MM.dd"),
        new SimpleDateFormat("yyyy/MM/dd")}; //支持转换的日期格式   
    
	@Override
	public Object convert(Class type, Object value) {
		try {
			if (type == Date.class) {
	            String dateString = (String)value;
	            if(dateString== null || dateString.trim().length()==0){       
	                return null;       
	            }  
	            if(dateString.length()>19)
	            	dateString = dateString.substring(0,19);
	            for (SimpleDateFormat format : ACCEPT_DATE_FORMATS) {   
	                try {   
	                	return format.parse(dateString);//遍历日期支持格式，进行转换   
	                } catch(Exception e) {   
	                    continue;   
	                }   
	            }   
	            return null;   
	        }   
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null; 
	}
}
