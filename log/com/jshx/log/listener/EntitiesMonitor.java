package com.jshx.log.listener;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.SysPropertiesUtil;

public class EntitiesMonitor {
	
	@SuppressWarnings("unchecked")
	public static boolean checkEntity(String entityClass){
		String monitorEntites = SysPropertiesUtil.getProperty("MONITOR_ENTITIES");
		List<String> entityList = StringUtil.getListByToken(monitorEntites, ",");
		
		for(String entity : entityList){
			Pattern pattern = Pattern.compile(entity);
			Matcher matcher = pattern.matcher(entityClass);
			if(matcher.matches())
				return true;
		}
		return false;
	}

}
