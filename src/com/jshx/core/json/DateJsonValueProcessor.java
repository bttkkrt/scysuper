package com.jshx.core.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * json-lib Date类型处理
 * 
 * @author lihuairu
 * 
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

	private String format = "yyyy-MM-dd HH:mm:ss";
	private SimpleDateFormat sdf = new SimpleDateFormat(format);

	// 处理数组的值
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return this.process(value);
	}

	// 处理对象的值
	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		return this.process(value);
	}

	// 处理Date类型返回的Json数值
	private Object process(Object value) {
		if (value == null) {
			return "";
		} else if (value instanceof Date)
			return sdf.format((Date) value);
		else {
			return value.toString();
		}
	}
}
