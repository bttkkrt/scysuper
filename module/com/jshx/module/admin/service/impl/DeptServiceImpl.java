/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-13        Chenjian          create
 * 2011-1-20        Chenjian          修改采用Hibernate的annotation方式
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.dao.DeptDAO;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.LinkedDept;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.extend.IDeptExtendInfo;
import com.jshx.module.admin.extend.IDeptExtendInfoDao;
import com.jshx.module.admin.service.DeptService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-13 下午05:21:21  
 * 部门管理接口的实现  
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl implements DeptService {
	
	@Autowired() 
	@Qualifier("deptDAO")
	private DeptDAO deptDAO;
	@Autowired() @Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	
	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#createDeptCode(java.lang.String)
	 */
	
	public String createDeptCode(String parentDeptCode) {
		BigDecimal maxID = deptDAO.getMaxDeptCodeByParent(parentDeptCode);
		
		if (maxID==null || maxID.intValue() == 0) {
			if (parentDeptCode == null || parentDeptCode.equals("0"))
				return "001";
			else
				return parentDeptCode + "001";
		} else {
			if (parentDeptCode == null || parentDeptCode.equals("0")){
				if(new Integer(maxID.intValue() + 1)<10)
				    return "00"+new Integer(maxID.intValue() + 1);
				else if(new Integer(maxID.intValue() + 1)<100&&new Integer(maxID.intValue() + 1)>=10)
					return "0"+new Integer(maxID.intValue() + 1);
				else
					return ""+new Integer(maxID.intValue() + 1);
			} else{
				if(new Integer(maxID.intValue() + 1)<10)
				    return parentDeptCode + "00"+new Integer(maxID.intValue() + 1);
				else if(new Integer(maxID.intValue() + 1)<100&&new Integer(maxID.intValue() + 1)>=10)
					return parentDeptCode + "0"+new Integer(maxID.intValue() + 1);
				else
					return parentDeptCode + new Integer(maxID.intValue() + 1);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#findDeptByDeptCode(java.lang.String)
	 */
	
	public Department findDeptByDeptCode(String deptCode) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.put("deptCode", deptCode);
		Department dept = (Department)deptDAO.findObjectByFieldsMap(Department.class, paraMap);
		
		IDeptExtendInfoDao deptExtendInfoDao = getExtendDao();
		if(dept!=null && deptExtendInfoDao!=null){
			IDeptExtendInfo deptExtendInfo = deptExtendInfoDao.getByDeptId(dept.getId());
			dept.setDeptExtendInfo(deptExtendInfo);
		}
		return dept;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#findDeptById(java.lang.String)
	 */
	
	public Department findDeptById(String id) {
		Department dept = deptDAO.findById(id);
		IDeptExtendInfoDao deptExtendInfoDao = getExtendDao();
		if(deptExtendInfoDao!=null){
			if(null!=dept && !"".equals(dept.getId())){
				IDeptExtendInfo deptExtendInfo = deptExtendInfoDao.getByDeptId(dept.getId());
				dept.setDeptExtendInfo(deptExtendInfo);
			}
		}
		return dept;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#findDeptByName(java.lang.String)
	 */
	
	public List<Department> findDeptByName(String deptName) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("deptName", deptName);
		return deptDAO.findDeptByList(paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#findDeptByPage(java.util.Map)
	 */
	
	public Pagination findDeptByPage(Pagination page,Map<String, Object> paraMap) {
		return deptDAO.findDeptByPage(page, paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#findDeptByParentDeptCode(java.lang.String)
	 */
	
	public List<Department> findDeptByParentDeptCode(String parentDeptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("delFlag", 0);
		if (!parentDeptCode.equals("")) {
			paraMap.put("parentDeptCode", parentDeptCode + "%");
			paraMap.put("length", parentDeptCode.length() + 3);
		}
		return deptDAO.findDeptByList(paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#findDeptByParentId(java.lang.String)
	 */
	
	public List<Department> findDeptByParentId(String id) {
		Department parentDept = null;
		if(id!=null && !"".equals(id))
			parentDept = this.findDeptById(id);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentDept", parentDept);
		return deptDAO.findListByHqlId("searchDeptByParentId", paraMap);
	}

	
	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#modify(com.jshx.module.mgt.entity.Department)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public Department modify(Department dept) {
				
		Department dept1 = deptDAO.findById(dept.getId());
		Department oldDept = dept1.getParentDept();
		String oldDeptCode = dept1.getDeptCode();
		String newDeptCode = dept.getDeptCode();
		Department parentDept = dept.getParentDept();
		if(parentDept!=null && parentDept.getId()!=null){
			parentDept = deptDAO.findById(parentDept.getId());
		}
		
		dept1.setParentDept(parentDept);
		dept1.setDelFlag(dept.getDelFlag());
		dept1.setDeptName(dept.getDeptName());
		dept1.setHasChild(dept.getHasChild());
		dept1.setSortSQ(dept.getSortSQ());
		dept1.setDeptCode(dept.getDeptCode());
		dept1.setDeptTypeCode(dept.getDeptTypeCode());
		dept1.setLinkedDeptTypeCode(dept.getLinkedDeptTypeCode());
		if(newDeptCode.equals(oldDeptCode))
			deptDAO.updateObject(dept1);
		else{
			Long length = (long)oldDeptCode.length()+1;
			
			Map<String,Object> paraMap = new HashMap<String, Object>();
			paraMap.put("oldDeptCode", oldDeptCode+"%");
			paraMap.put("newDeptCode", newDeptCode);
			paraMap.put("length", length);
			if(parentDept!=null){
				//现父层部门子部门数+1
				parentDept.setHasChild(parentDept.getHasChild()+1);
			}
			dept.setParentDept(parentDept);
			
			//执行变更下层部门和用户部门编号
			deptDAO.executeUpdateByHqlId("updateDept", paraMap);
			deptDAO.executeUpdateByHqlId("changeUserDept", paraMap);
			deptDAO.updateObject(dept1);
			
			//原父层子部门数-1
			if(oldDept!=null){
				oldDept.setHasChild(oldDept.getHasChild()-1);
				deptDAO.updateObject(oldDept);
			}
		}
		if(dept.getDeptExtendInfo()!=null){
			IDeptExtendInfoDao deptExtendInfoDao = getExtendDao();
			if(deptExtendInfoDao!=null){
				IDeptExtendInfo deptExtendInfo = dept.getDeptExtendInfo();
				deptExtendInfo.setDeptId(dept.getId());
				if(deptExtendInfo.getId()!=null)
					deptExtendInfoDao.updateDeptExtendInfo(deptExtendInfo);
				else
					deptExtendInfoDao.saveDeptExtendInfo(deptExtendInfo);
				dept1.setDeptExtendInfo(deptExtendInfo);
			}
		}
		return dept1;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#save(com.jshx.module.mgt.entity.Department)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public Department save(Department dept) {
		Department parentDept = dept.getParentDept();
		
		dept.setHasChild(0);
        dept.setDelFlag(0);
		deptDAO.saveObject(dept);
		
		//父层部门不为空，父层部门的子层部门个数+1
		if(parentDept!=null){
			if(parentDept.getHasChild()==null)
				parentDept.setHasChild(1);
			else
				parentDept.setHasChild(parentDept.getHasChild()+1);
			deptDAO.updateObject(parentDept);
		}
		if(dept.getDeptExtendInfo()!=null){
			IDeptExtendInfoDao deptExtendInfoDao = getExtendDao();
			if(deptExtendInfoDao!=null){
				IDeptExtendInfo deptExtendInfo = dept.getDeptExtendInfo();
				deptExtendInfo.setDeptId(dept.getId());
				deptExtendInfoDao.saveDeptExtendInfo(deptExtendInfo);
				dept.setDeptExtendInfo(deptExtendInfo);
			}
		}
		return dept;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#activeDept(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public Department activeDept(String id) {
		//激活部门
		deptDAO.activeDept(id);	
		return deptDAO.findById(id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.DeptService#inactiveDept(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public Department inactiveDept(String id) {
		Department dept = deptDAO.findById(id);
		//禁用部门
		deptDAO.inactiveDept(dept.getDeptCode());
		return deptDAO.findById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.DeptService#checkDept(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * no longer used???
	 */
	@SuppressWarnings("unchecked")
	public Integer checkDept(String parentDeptCode, String deptName, String deptCode) {
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("deptCode", parentDeptCode+"%");
		paraMap.put("deptName", deptName);
		String length = "000";
		if(parentDeptCode!=null && !parentDeptCode.equals(""))
			length = parentDeptCode+length;
		paraMap.put("length", length);
		List<Object> list = deptDAO.findListByHqlId("checkDept", paraMap);
		Long cnt = Long.valueOf(list.get(0).toString());
		if(cnt>0)
			return 0;
		else{
			paraMap = new HashMap<String, Object>();
			paraMap.put("deptCode", deptCode);
			list = deptDAO.findListByHqlId("checkDept", paraMap);
			cnt = Long.valueOf(list.get(0).toString());
			if(cnt>0)
				return 1;
			else
				return 2;
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.DeptService#findChildDeptIds(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<String> findChildDeptIds(String deptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("deptCode", deptCode+"%");
		return deptDAO.findListByHqlId("findChildDeptIds", paraMap);
	}

	public List<Department> findLinkedDpet(Department mainDept) {
		return deptDAO.findLinkedDpet(mainDept);
	}

	@Transactional(propagation=Propagation.NESTED) 
	public void saveLinkedDept(String mainDeptId, String[] subDeptIds) {
		Department mainDept = deptDAO.findById(mainDeptId);
		deptDAO.delLinkedDept(mainDept);
		if(subDeptIds!=null && subDeptIds.length>0){
			for(String subDeptId : subDeptIds){
				Department subDept = deptDAO.findById(subDeptId);
				LinkedDept linkedDept = new LinkedDept();
				linkedDept.setMainDept(mainDept);
				linkedDept.setSubDept(subDept);
				deptDAO.saveBaseModelObject(linkedDept);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Department> findDeptByTpye(String deptTypeCode) {
		return deptDAO.findListBy(Department.class, "deptTypeCode", deptTypeCode);
	}

	@Transactional
	public void delDept(String deptId) {
		deptDAO.removeObjectById(Department.class, deptId);
	}

	@Transactional
	public void logicDelDept(String deptId) {
		Department dept = deptDAO.findById(deptId);
		logicOp(dept);
	}
	
	private void logicOp(Department dept){
		int childDept = 0;
		if(null!=dept.getChildDeptIds()){
			childDept = dept.getChildDeptIds().size();
		}
		
		if(childDept>0){
			for (Department department : dept.getChildDepts()) {
				logicOp(department);
			}
		}
		
		List<User> users = dept.getUsers();
		for (User user : users) {
			user.setDelFlag(2);
			userDAO.updateObject(user);
		}
		dept.setDelFlag(2);
		dept.setMainLinkedDepts(null);
		dept.setSubLinkedDepts(null);
		deptDAO.saveOrUpdateObject(dept);
	}
	
	private IDeptExtendInfoDao getExtendDao(){
		try{
			IDeptExtendInfoDao extendDao = (IDeptExtendInfoDao)SpringContextHolder.getBean("deptExtendDao");
			return extendDao;
		}catch(Exception e){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Department> getProvinces() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		return deptDAO.findListByHqlId("getProvinces", paraMap);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Department> findChildrenByParentId(Map<String, Object> paraMap) {
		return deptDAO.findListByHqlId("findChildrenByParentId", paraMap);
	}
	
	/**
	   * 查找所有部门集合
	   * GY-UPDATE 2015-02-26
	   */
	public List<Department> findAllDept(Map<String, Object> paraMap)
	{
		//增加逻辑删除判断
		paraMap.put("delFlag", Integer.valueOf(0));
		return this.deptDAO.findAllDeptByList(paraMap);
	}
	
	@Transactional
	public Department findIndependenceDept(String deptCode)
	{
		Map paraMap = new HashMap();
		paraMap.put("deptCode", deptCode);
		List list = this.deptDAO.findListByHqlId("findIndependenceDept", paraMap);
		if ((list != null) && (list.size() > 0))
		{
			return ((Department) list.get(0));
		}
		return null;
	}
}