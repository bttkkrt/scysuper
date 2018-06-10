package com.jshx.module.admin.extend;

import com.jshx.core.base.dao.BaseDao;

/**
 * 一维代码扩展信息DAO
 * 
 * @author Chenjian
 *
 */
public interface ICodeValueExtendInfoDao extends BaseDao {
	
	/**
	 * 根据一维代码值的ID查找扩展信息
	 * 
	 * @param codeValueId
	 * @return
	 */
	public ICodeValueExtendInfo getByCodeValueId(String codeValueId);
	
	/**
	 * 保存扩展信息
	 * 
	 * @param codeValueExtendInfo
	 * @return
	 */
	public ICodeValueExtendInfo saveCodeValueExtendInfo(ICodeValueExtendInfo codeValueExtendInfo);
	
	/**
	 * 更新扩展信息
	 * 
	 * @param codeValueExtendInfo
	 * @return
	 */
	public ICodeValueExtendInfo updateCodeValueExtendInfo(ICodeValueExtendInfo codeValueExtendInfo);


}
