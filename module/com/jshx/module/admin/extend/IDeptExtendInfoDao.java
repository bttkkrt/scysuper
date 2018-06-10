package com.jshx.module.admin.extend;

import com.jshx.core.base.dao.BaseDao;
/**
 * 组织机构扩展信息DAO
 * 
 * @author Chenjian
 *
 */
public interface IDeptExtendInfoDao extends BaseDao {
	
	/**
	 * 根据组织机构ID查找组织机构扩展信息
	 * 
	 * @param deptId
	 * @return
	 */
	public IDeptExtendInfo getByDeptId(String deptId);
	
	/**
	 * 保存组织机构扩展信息
	 * 
	 * @param deptExtendInfo
	 * @return
	 */
	public IDeptExtendInfo saveDeptExtendInfo(IDeptExtendInfo deptExtendInfo);
	
	/**
	 * 更新组织机构扩展信息
	 * 
	 * @param deptExtendInfo
	 * @return
	 */
	public IDeptExtendInfo updateDeptExtendInfo(IDeptExtendInfo deptExtendInfo);

}
