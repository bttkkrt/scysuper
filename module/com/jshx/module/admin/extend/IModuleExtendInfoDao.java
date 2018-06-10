package com.jshx.module.admin.extend;

import com.jshx.core.base.dao.BaseDao;
/**
 * 模块扩展信息DAO
 * 
 * @author Chenjian
 *
 */
public interface IModuleExtendInfoDao extends BaseDao {
	
	/**
	 * 根据模块ID查找模块扩展信息
	 * 
	 * @param moduleId
	 * @return
	 */
	public IModuleExtendInfo getByModuleId(String moduleId);
	
	/**
	 * 保存模块扩展信息
	 * 
	 * @param moduleExtendInfo
	 * @return
	 */
	public IModuleExtendInfo saveModuleExtendInfo(IModuleExtendInfo moduleExtendInfo);
	
	/**
	 * 更新模块扩展信息
	 * 
	 * @param moduleExtendInfo
	 * @return
	 */
	public IModuleExtendInfo updateModuleExtendInfo(IModuleExtendInfo moduleExtendInfo);

}
