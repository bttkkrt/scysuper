/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-13        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.jshx.module.admin.entity.Department;
import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-13 上午10:11:35 类说明
 */
public interface DeptDAO extends BaseDao{

	/**
	 * 查找部门，返回列表
	 * 
	 * @param hqlId
	 * @param paraMap
	 * @return List<Department>
	 */
	public List<Department> findDeptByList(Map<String, Object> paraMap) ;

	/**
	 * 分页查找部门
	 * 
	 * @param hqlId
	 * @param page
	 * @param paraMap
	 * @return Pagination
	 */
	public Pagination findDeptByPage(Pagination page,
			Map<String, Object> paraMap);
	
	/**
	 * 根据上层部门编码查找下层部门编码的最大值
	 * 
	 * @param parentDeptCode
	 * @return BigDecimal   
	 */
	public BigDecimal getMaxDeptCodeByParent(String parentDeptCode) ;
	
	/**
	 * 根据查询条件统计部门数
	 * 
	 * @param paraMap
	 * @return Long   
	 */
	public Long cntDept(Map<String, Object> paraMap);
	
	/**
	 * 禁用部门
	 * 
	 * @param deptCode
	 * @return void  
	 */
	public void inactiveDept(String deptCode);
	
	/**
	 * 启用被禁用的部门
	 * 
	 * @param id
	 * @return void 
	 */
	public void activeDept(String id);
	
	/**
	 * 根据主键查找部门
	 * 
	 * @param id
	 * @return Department   
	 */
	public Department findById(String id);
	
	/**
	 * 查询按照部门的编码排序的部门列表
	 * 
	 *  @param paraMap
	 * @return List<Department>   
	 * @throws
	 */
	public List<Department> findDeptListOrderByDeptCode(Map<String, Object> paraMap) ;
	
	/**
	 * 根据主部门删除关联部门
	 * 
	 * @param mainDept
	 */
	public void delLinkedDept(Department mainDept);
	
	/**
	 * 根据主部门查找关联部门列表
	 * 
	 * @param mainDept
	 * @return
	 */
	public List<Department> findLinkedDpet(Department mainDept);
	
	public List<Department> findAllDeptByList(Map<String, Object> paraMap);

}
