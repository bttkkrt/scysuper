package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.UserLinkedDeptDao;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.UserLinkedDept;
/**
 * 
 * @author Chenjian
 * @see com.jshx.module.admin.dao.UserLinkedDeptDao
 *
 */
@Component("userLinkedDeptDao")
public class UserLinkedDeptDaoImpl extends BaseDaoImpl implements
		UserLinkedDeptDao {

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserLinkedDeptDao#saveLinkedDept(java.lang.String, java.lang.String[])
	 */
	@Override
	public void saveLinkedDept(String userId, String[] linkedDeptIds) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		executeUpdateByHqlId("delLinkedDeptByUser", paraMap);
		if(linkedDeptIds!=null){
			for(String linkedDeptId : linkedDeptIds){
				UserLinkedDept linkedDept = new UserLinkedDept();
				Department dept = (Department)getObjectByProperty(Department.class, "deptCode", linkedDeptId);
				if(dept==null)
					continue;
				linkedDept.setUserId(userId);
				linkedDept.setLinkedDeptId(dept.getId());
				saveBaseModelObject(linkedDept);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserLinkedDeptDao#getLinkedDeptByUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserLinkedDept> getLinkedDeptByUser(String userId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		return findListByHqlId("findLinkedDeptByUser", paraMap);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.dao.UserLinkedDeptDao#getLinkedDeptByUser(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<UserLinkedDept> getLinkedDeptByUser(String userId,
			String deptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("deptCode", deptCode);
		int length = 3;
		if(deptCode.length()>1)
			length =  deptCode.length() + 2;
		paraMap.put("length", length);
		return findListByHqlId("findLinkedDeptByUser", paraMap);
	}

}
