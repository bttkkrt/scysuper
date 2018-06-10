package com.jshx.module.admin.dao;

import java.util.List;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.module.admin.entity.UserLinkedDept;
/**
 * 用户与部门的多对多关系
 * 
 * @author Chenjian
 *
 */
public interface UserLinkedDeptDao extends BaseDao {
	
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
