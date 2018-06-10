package com.jshx.core.utils;

import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Map;  
  
import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;  
/**
 * Struts2日期型转变
 * 
* @author   Chenjian
* @version 创建时间：2011-1-25 上午10:01:40  
* 类说明
 */
public class DateConverter extends DefaultTypeConverter {  
    private static final SimpleDateFormat[] ACCEPT_DATE_FORMATS = { 
    	new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"), 
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
        new SimpleDateFormat("yyyy.MM.dd HH:mm:ss"),  
        new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
        new SimpleDateFormat("dd/MM/yyyy"), 
        new SimpleDateFormat("yyyy-MM-dd"),  
        new SimpleDateFormat("yyyy.MM.dd"),
        new SimpleDateFormat("yyyy/MM/dd"), 
        new SimpleDateFormat("MM/yyyy"), 
        new SimpleDateFormat("yyyy-MM"),  
        new SimpleDateFormat("yyyy.MM"),
        new SimpleDateFormat("yyyy/MM"),
        new SimpleDateFormat("yyyy")};//支持转换的日期格式   
  
	@SuppressWarnings("rawtypes")
	@Override   
    public Object convertValue(Map context, Object value, Class toType) {   
        if (toType == Date.class) {  //浏览器向服务器提交时，进行String to Date的转换   
            //Date date = null;   
            String dateString = null;   
            String[] params = (String[])value;   
            dateString = params[0];//获取日期的字符串   
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
        else if (toType == String.class) {   //服务器向浏览器输出时，进行Date to String的类型转换   
            Date date = (Date)value;   
            return new SimpleDateFormat("yyyy-MM-dd").format(date);//输出的格式是yyyy-MM-dd   
        }   
          
        return null;   
    }  
} 
