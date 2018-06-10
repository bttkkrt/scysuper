/**
 * 
 */
package com.jshx.module.admin.service;

import java.util.List;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.OnlineUser;

/**
 * @author f_cheng
 *
 */
public interface OnlineUserService {
	/**
	 * 保存在线用户
	 */
	public void save(OnlineUser user);
	/**
	 * 删除在线用户
	 */
	public void del(String userId);
	/**
	 * 列表查找在线用户
	 */
	public List<OnlineUser> list();
	/**
	 * 分页查找在线用户
	 */
	public Pagination findOnlineUserByPage(Pagination pagination);
	/**
	 * 清空全部在线用户
	 */
	public void clear();
	/**
	 * 判断用户是否在线
	 */
	public boolean isUserLogon(String userId);
	/**
	 * 根据用户ID获取在线用户信息
	 */
	public OnlineUser getOnlineUserByUserId(String userId);
}
