package com.jshx.module.admin.service;

import java.util.List;

import com.jshx.core.base.service.BaseService;
import com.jshx.module.admin.entity.UserLinkedDept;
/**
 * 用户管理部门服务
 * 
 * @author Chenjian
 *
 */
public interface UserLinkedDeptService extends BaseService {
	
	/**
	 * 保存用户的关联部门
	 * 
	 * @param userId
	 * @param linkedDeptIds
	 */
	public void saveLinkedDept(String userId, String[] linkedDeptIds);
	
	/**
	 * 根据用户获得关联部门
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserLinkedDept> getLinkedDeptByUser(String userId);
	
	/**
	 * 根据部门获取部门下属的用户关联部门
	 * 
	 * @param userId
	 * @param deptCode
	 * @return
	 */
	public List<UserLinkedDept> getLinkedDeptByUser(String userId, String deptCode);

}
