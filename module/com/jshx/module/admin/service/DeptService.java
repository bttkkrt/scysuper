/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-13        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-13 下午04:32:53  
 * 部门管理接口  
 */
public interface DeptService extends BaseService{
	
	/**
	 * 根据上层部门ID生成部门编号<br>
	 * 例如上层部门编号为001，下层已有001001，则新加子层编号为001002
	 *  
	 * @param parentDeptCode
	 * @return String   
	 */
	public String createDeptCode(String parentDeptCode);
	
	/**
	 * 根据部门主键查找
	 * 
	 * @param id
	 * @return Department 
	 */
	public Department findDeptById(String id);
	
	/**
	 * 根据部门编码查找
	 * 
	 * @param deptCode
	 * @return Department 
	 */
	public Department findDeptByDeptCode(String deptCode);
	
	/**
	 * 根据部门名称查找
	 * 
	 * @param deptName
	 * @return
	 * @return List<Department>   
	 * @throws
	 */
	public List<Department> findDeptByName(String deptName);

	/**
	 * 根据上层部门主键查找下层部门
	 * 
	 * @param id
	 * @return List<Department>  
	 */
	public List<Department> findDeptByParentId(String id);
	
	/**
	 * 根据上层部门编号查找下层部门
	 * 
	 * @param parentDeptCode
	 * @return List<Department>
	 */
	public List<Department> findDeptByParentDeptCode(String parentDeptCode);
	
	
	/**
	 * 分页查找部门
	 * 
	 * @param page
	 * @param paraMap
	 * @return Pagination  
	 */
	public Pagination findDeptByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 保存部门
	 * 
	 * @param dept
	 * @return Department 
	 */
	public Department save(Department dept);
	
	/**
	 * 修改部门，如果上层部门变更，需要修改其下属部门以及用户的部门编号
	 * 
	 * @param dept
	 * @return Department  
	 */
	public Department modify(Department dept);
	
	/**
	 * 禁用部门，同时禁用其下属部门以及用户
	 * 
	 * @param deptCode
	 * @return void 
	 */
	public Department inactiveDept(String deptCode);
	
	/**
	 * 启用被禁用的部门
	 * 
	 * @param id
	 * @return void   
	 */
	public Department activeDept(String id);
	
	/**
	 * 检查部门，同级下不能有同名的部门，部门编号不能重复
	 * 
	 * @param parentDeptCode
	 * @param deptName
	 * @param deptCode
	 * @return Integer    返回0：部门名重复
	 *                    返回1：部门编号重复
	 *                    返回2：验证通过
	 */
	public Integer checkDept(String parentDeptCode, String deptName, String deptCode);
	
	/**
	 * 查找下层部门的ID
	 * 
	 * @param deptCode
	 * @return List<String>  
	 */
	public List<String> findChildDeptIds(String deptCode);
	
	/**
	 * 根据主部门查找关联部门列表
	 * 
	 * @param mainDept
	 * @return
	 */
	public List<Department> findLinkedDpet(Department mainDept);
	
	/**
	 * 保存关联部门
	 * 
	 * @param mainDeptId
	 * @param subDeptIds
	 */
	public void saveLinkedDept(String mainDeptId, String[] subDeptIds);
	
	/**
	 * 根据部门类型查找部门
	 * 
	 * @param deptTypeCode
	 * @return
	 */
	public List<Department> findDeptByTpye(String deptTypeCode);
	
	/**
	 * 物理删除部门
	 * @param deptId
	 */
	public void delDept(String deptId);
	
	/**
	 * 逻辑删除部门
	 * @param deptId
	 */
	public void logicDelDept(String deptId);
	
	/**
	 * 
	 * @return
	 */
	public List<Department> getProvinces();

	/**
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<Department> findChildrenByParentId(Map<String, Object> paraMap);
	
	/**
	   * 查找所有部门集合
	   * GY-UPDATE 2015-02-26
	   */
	  public List<Department> findAllDept(Map<String, Object> paramMap);
	  
	  public Department findIndependenceDept(String paramString);
}
