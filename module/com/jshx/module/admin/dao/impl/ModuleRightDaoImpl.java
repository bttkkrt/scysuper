/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-22        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.module.admin.dao.ModuleRightDao;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.ModuleRight;
import com.jshx.module.admin.entity.UserRole;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-22 下午06:49:11  
 * 类说明  
 */
@Component("moduleRightDao")
public class ModuleRightDaoImpl extends BaseDaoImpl implements ModuleRightDao {

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleRightDao#findRightByModule(com.jshx.module.admin.entity.Module)
	 */
	@SuppressWarnings("unchecked")
	public List<ModuleRight> findRightByModule(Module module) {
		return super.findListBy(ModuleRight.class, "module", module);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleRightDao#findRightByRole(com.jshx.module.admin.entity.UserRole)
	 */
	@SuppressWarnings("unchecked")
	public List<ModuleRight> findRightByRole(UserRole role) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("role", role);
		return super.findListByHqlId("findModuleRightByRole", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.dao.ModuleRightDao#removeByRole(com.jshx.module.admin.entity.UserRole)
	 */
	@Transactional
	public void removeByRole(UserRole role) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("role", role);
		super.executeUpdateByHqlId("delModuleRightByRole", paraMap);
	}
	
	/**
	 * 模块管理中角色设置中的角色树
	 */
	@SuppressWarnings("unchecked")
	public List<ModuleRight> findRightForModule(Map<String, Object> paraMap){
		return super.findListByHqlId("findRightForModule", paraMap); 
	}

}
