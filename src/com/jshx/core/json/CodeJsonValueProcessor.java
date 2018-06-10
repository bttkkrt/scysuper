package com.jshx.core.json;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import com.jshx.core.utils.Constants;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;

/**
 * 将一维代码转换为json数据
 * 
 * @author Chenjian
 *
 */
public class CodeJsonValueProcessor implements JsonValueProcessor {
	
	private Map<String, String> codeMap;
	
	public CodeJsonValueProcessor(Map<String, String> codeMap){
		this.codeMap = codeMap;
	}
	
	@Override
	public Object processArrayValue(Object obj, JsonConfig config) {
		return obj;
	}

	@Override
	public Object processObjectValue(String key, Object obj, JsonConfig config) {
	    Object object = process(key, obj);
	    /*if(key.equals("siId")){
	        System.out.println(object+" key="+key+" obj="+obj);
	    }*/	    
		return object;
	}
	
	private Object process(String property, Object value) {	    
		String codeId = codeMap.get(property);
		if(codeId == null)
			return value;
		else{
			if(codeId.toLowerCase().contains("select")){
				//从SQL中查找
				CodeService codeService = (CodeService)SpringContextHolder.getBean("codeService");
				if ((codeId.indexOf(Constants.CODE_SQL_PATTERN_DEPAR_ID) >= 0)
						|| (codeId.indexOf(Constants.CODE_SQL_PATTERN_USER_ID) >= 0)) {
					HttpSession session = Struts2Util.getSession();
					User user = (User) session.getAttribute(Constants.CURR_USER);
					if (codeId.indexOf(Constants.CODE_SQL_PATTERN_DEPAR_ID) >= 0) {
						codeId = codeId.replace(Constants.CODE_SQL_PATTERN_DEPAR_ID, "'"
								+ ((Department) user.getDept()).getId() + "'");
					}
					if (codeId.indexOf(Constants.CODE_SQL_PATTERN_USER_ID) >= 0) {
						codeId = codeId.replace(Constants.CODE_SQL_PATTERN_USER_ID, "'"
								+ user.getId() + "'");
					}
				}
				List<CodeValue> codeValueList = null;
				codeValueList = codeService.getCodeValueBySql(codeId);
				for (CodeValue codeValue : codeValueList) {
					if (codeValue.getItemValue().equals(value)) {
						return codeValue.getItemText();
					}
				}
			}else{
				//从缓存中查找
				Map<String, String> valueMap = Constants.CODE_MAP.get(codeId);
				if(valueMap!=null && value!=null)
					return valueMap.get(value.toString());
			}
			return null;
		}
	}
	
}
