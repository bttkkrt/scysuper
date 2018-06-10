/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-13        Chenjian          create
 * 2011-1-20        Chenjian          修改采用Hibernate的annotation方式
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.DeptDAO;
import com.jshx.module.admin.entity.Department;

/**  
 * 部门DAO的实现
 * 
 * @author   Chenjian
 * @version 创建时间：2011-1-13 下午02:09:28  
 * 
 */
@Component("deptDAO")
public class DeptDAOImpl extends BaseDaoImpl implements DeptDAO {

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#findDeptByList(java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	public List<Department> findDeptByList(Map<String, Object> paraMap) {
		return this.findListByHqlId("searchDept", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#findDeptByPage(java.lang.String, com.jshx.core.base.vo.Pagination, java.util.Map)
	 */
	public Pagination findDeptByPage(Pagination page,
			Map<String, Object> paraMap)  {
		return this.findPageByHqlId("searchDept", paraMap, page);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#createDeptCode(java.lang.String)
	 */
	
	@SuppressWarnings("rawtypes")
	public BigDecimal getMaxDeptCodeByParent(String parentDeptCode)  {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(parentDeptCode!=null ){
			Integer length = parentDeptCode.length();			
			paraMap.put("parentDeptCode", parentDeptCode);
			paraMap.put("length", length);
		}
		List list = this.findListByHqlId("getMaxDeptCodeByParent", paraMap);
		BigDecimal maxID = null;
		if(list.get(0)!=null){
			maxID = new BigDecimal((Integer)list.get(0));
		}
		logger.debug("获得的最大编码是："+maxID);
		return maxID;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#cntDept(java.util.Map)
	 */
	
	@SuppressWarnings("rawtypes")
	public Long cntDept(Map<String, Object> paraMap)  {
		List list = this.findListByHqlId("cntDepts", paraMap);
		Long cnt = null;
		if(list.get(0)!=null){
			cnt = (Long)list.get(0);
		}
		return cnt;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#activeDept(java.lang.String)
	 */

	public void activeDept(String id) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("id", id);
		this.executeUpdateByHqlId("activeDept", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#inactiveDept(java.lang.String)
	 */

	public void inactiveDept(String deptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("deptCode", deptCode+"%");
		this.executeUpdateByHqlId("inactiveDept", paraMap);
		this.executeUpdateByHqlId("inactiveUser", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.dao.DeptDAO#findById(java.lang.String)
	 */

	public Department findById(String id) {
		return (Department)this.getObjectById(Department.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Department> findDeptListOrderByDeptCode(
			Map<String, Object> paraMap) {
		return this.findListByHqlId("searchOrderedDept", paraMap);
	}

	public void delLinkedDept(Department mainDept) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("mainDept",mainDept);
		executeUpdateByHqlId("delLinkedDept", paraMap);
	}

	@SuppressWarnings("unchecked")
	public List<Department> findLinkedDpet(Department mainDept) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("mainDept",mainDept);
		return findListByHqlId("findLinkedDept", paraMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findAllDeptByList(Map<String, Object> paraMap)
	{
		return findListByHqlId("searchAllDept", paraMap);
	}
}
