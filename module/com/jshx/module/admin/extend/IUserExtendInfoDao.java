package com.jshx.module.admin.extend;

import com.jshx.core.base.dao.BaseDao;
/**
 * 用户扩展信息DAO
 * 
 * @author Chenjian
 *
 */
public interface IUserExtendInfoDao extends BaseDao {
	
	/**
	 * 根据用户ID查找用户扩展信息
	 * 
	 * @param userId
	 * @return
	 */
	public IUserExtendInfo getByUserId(String userId);
	
	/**
	 * 保存用户扩展信息
	 * 
	 * @param userExtendInfo
	 * @return
	 */
	public IUserExtendInfo saveUserExtendInfo(IUserExtendInfo userExtendInfo);
	
	/**
	 * 更新用户扩展信息
	 * 
	 * @param userExtendInfo
	 * @return
	 */
	public IUserExtendInfo updateIUserExtendInfo(IUserExtendInfo userExtendInfo);

}
