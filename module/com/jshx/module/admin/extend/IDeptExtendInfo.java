package com.jshx.module.admin.extend;

import com.jshx.module.admin.entity.Department;
/**
 * 组织机构扩展信息接口
 * 
 * @author Chenjian
 *
 */
public interface IDeptExtendInfo extends IExtendInfo {
	
	/**
	 * 获取部门扩展信息ID
	 * 
	 * @return
	 */
	public String getId();
	
	/**
	 * 设置部门
	 * 
	 * @param dept
	 */
	public void setDept(Department dept);
	
	/**
	 * 设置部门ID
	 * 
	 * @param deptId
	 */
	public void setDeptId(String deptId);

}
