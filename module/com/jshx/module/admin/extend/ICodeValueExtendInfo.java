package com.jshx.module.admin.extend;

import com.jshx.module.admin.entity.CodeValue;

/**
 * 一维代码值扩展信息接口
 * 
 * @author Chenjian
 *
 */
public interface ICodeValueExtendInfo extends IExtendInfo{
	/**
	 * 设置一维代码项
	 */
	public void setCodeValue(CodeValue value);
	/**
	 * 设置一维代码项ID
	 */
	public void setCodeValueId(String codeValueId);

}
