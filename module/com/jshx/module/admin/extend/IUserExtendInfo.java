package com.jshx.module.admin.extend;

import com.jshx.module.admin.entity.User;
/**
 * 用户扩展信息接口
 * 
 * @author Chenjian
 *
 */
public interface IUserExtendInfo extends IExtendInfo {
	/**
	 * 获取用户
	 * 
	 * @return
	 */
	public User getUser();
	
	/**
	 * 设置用户的ID
	 * 
	 * @param userId
	 */
	public void setUserId(String userId);

}
