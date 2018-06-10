package com.jshx.module.admin.extend;

import com.jshx.module.admin.entity.Module;
/**
 * 模块扩展信息接口
 * 
 * @author Chenjian
 *
 */
public interface IModuleExtendInfo extends IExtendInfo {
	/**
	 * 获取模块
	 * 
	 * @param module
	 */
	public void setModule(Module module);
	
	/**
	 * 设置模块的ID
	 * 
	 * 
	 * @param moduleId
	 */
	public void setModuleId(String moduleId);

}
