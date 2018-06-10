/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-18        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service.impl;

import java.util.Arrays;
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
import com.jshx.module.admin.dao.ModuleDao;
import com.jshx.module.admin.dao.ModuleRightDao;
import com.jshx.module.admin.dao.UserRoleDao;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.ModuleRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.extend.IModuleExtendInfo;
import com.jshx.module.admin.extend.IModuleExtendInfoDao;
import com.jshx.module.admin.security.EdpShiroFilterFactoryBean;
import com.jshx.module.admin.service.ModuleService;

/**
 * @author Chenjian
 * @version 创建时间：2011-1-18 上午10:25:20 类说明
 */
@Service("moduleService")
public class ModuleServiceImpl extends BaseServiceImpl implements ModuleService {

	@Autowired() 
	@Qualifier("moduleDao")
	private ModuleDao moduleDao;

	@Autowired() 
	@Qualifier("userRoleDao")
	private UserRoleDao userRoleDao;

	@Autowired() 
	@Qualifier("moduleRightDao")
	private ModuleRightDao moduleRightDao;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#activeModule(java.lang.String)
	 */

	@Transactional(propagation = Propagation.NESTED)
	public Module activeModule(String id) {
		Module module = moduleDao.findById(id);
		module.setIsVisible(1);
		moduleDao.updateObject(module);
		return module;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#createModuleCode(java.lang.String)
	 */

	public String createModuleCode(String parentModuleCode) {
		Integer maxID = moduleDao.getMaxModuleCodeByParent(parentModuleCode);
		if (maxID == null || maxID.intValue() == 0) {
			if (parentModuleCode == null || parentModuleCode.equals(""))
				return "M01";
			else
				return parentModuleCode + "01";
		} else {
			if (parentModuleCode == null || parentModuleCode.equals("")) {
				if (new Integer(maxID.intValue() + 1) < 10)
					return "M0" + new Integer(maxID.intValue() + 1);
				else if (new Integer(maxID.intValue() + 1) < 100
						&& new Integer(maxID.intValue() + 1) >= 10)
					return "M" + new Integer(maxID.intValue() + 1);
				else
					return "" + new Integer(maxID.intValue() + 1);
			} else {
				if (new Integer(maxID.intValue() + 1) < 10)
					return parentModuleCode + "0"
							+ new Integer(maxID.intValue() + 1);
				else
					return parentModuleCode + new Integer(maxID.intValue() + 1);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#getChildModulesById(java.lang.String)
	 */

	public List<Module> findChildModulesById(String parentModuleId) {
		Module parentModule = moduleDao.findById(parentModuleId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("parentModule", parentModule);
		return moduleDao.findModuleByList(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#getChildModulesByModuleCode(java.lang.String)
	 */

	public List<Module> findChildModulesByModuleCode(String parentModuleCode) {
		Module parentModule = (Module) moduleDao.getObjectByProperty(
				Module.class, "moduleCode", parentModuleCode);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (parentModule != null)
			paraMap.put("parentModule", parentModule);
		else
			paraMap.put("length", "000");
		paraMap.put("isVisible", 1);
		return moduleDao.findModuleByList(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#getModuleByModuleCode(java.lang.String)
	 */

	public Module findModuleByModuleCode(String moduleCode) {
		Module module = (Module) moduleDao.getObjectByProperty(Module.class,
				"moduleCode", moduleCode);
		if(module!=null && this.getExtendDao()!=null){
			IModuleExtendInfoDao extendDao = getExtendDao();
			IModuleExtendInfo extendInfo = extendDao.getByModuleId(module.getId());
			module.setModuleExtendInfo(extendInfo);
		}
		return module;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#inactiveModule(java.lang.String)
	 */
	@Transactional(propagation = Propagation.NESTED)
	public Module inactiveModule(String id) {
		Module module = moduleDao.findById(id);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("moduleCode", module.getModuleCode().trim() + "%");
		moduleDao.executeUpdateByHqlId("inactiveModule", paraMap);
		return module;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#modify(com.jshx.module.admin.entity.Module)
	 */

	@Transactional(propagation = Propagation.NESTED)
	public Module modify(Module module, String[] roleIds) {
		Module module1 = moduleDao.findById(module.getId());
		String oldModuleCode = module1.getModuleCode();
		String newModuleCode = module.getModuleCode();
		
		boolean changePublic = module1.getIsPublic().equals(module.getIsPublic());
				
		module1.setBigIconAddr(module.getBigIconAddr());
		module1.setModuleAddr(module.getModuleAddr());
		module1.setModuleCode(module.getModuleCode());
		module1.setModuleName(module.getModuleName());
		module1.setOtherProperty(module.getOtherProperty());
		module1.setSmallIconAddr(module.getSmallIconAddr());
		module1.setSortSq(module.getSortSq());
		module1.setTarget(module.getTarget());
		module1.setModuleFullName(module.getModuleFullName());
		module1.setIsPublic(module.getIsPublic());
		module1.setIsVisible(module.getIsVisible());
		module1.setIsAutoExpand(module.getIsAutoExpand()); //2013.3.6 李淮如 修改
		
		//修改扩展信息
		if(this.getExtendDao()!=null && module.getModuleExtendInfo()!=null){
			IModuleExtendInfoDao extendDao = getExtendDao();
			IModuleExtendInfo extendInfo = module.getModuleExtendInfo();
			extendInfo.setModuleId(module.getId());
			if(extendInfo.getId()!=null)
				extendDao.updateModuleExtendInfo(extendInfo);
			else
				extendDao.saveModuleExtendInfo(extendInfo);
			module1.setModuleExtendInfo(extendInfo);
		}

		// 删除原有角色
		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.put("module", module1);
//		moduleDao.executeUpdateByHqlId("delModuleRight", paraMap);
		// 添加角色
		if (roleIds != null && roleIds.length > 0) {
			for (String roleId : roleIds) {
				ModuleRight right = new ModuleRight();
				right.setModule(module1);
				UserRole role = (UserRole) userRoleDao.getObjectById(
						UserRole.class, roleId);
				right.setRole(role);
				moduleRightDao.saveObject(right);
			}
		}
		if (newModuleCode.equals(oldModuleCode)) {
			moduleDao.updateObject(module1);
		} else {
			// 更改了上层部门
			Module parentModule = module1.getParentModule();
			if (parentModule != null) {
				parentModule.setHasChild(parentModule.getHasChild() - 1);
				moduleDao.updateObject(parentModule);
			}

			parentModule = module.getParentModule();
			if (parentModule != null) {
				// parentModule =
				// moduleDao.findById(module.getParentModule().getId());
				parentModule.setHasChild(parentModule.getHasChild() + 1);
			}
			module1.setParentModule(parentModule);
			moduleDao.updateObject(module1);

			paraMap = new HashMap<String, Object>();
			Integer length = oldModuleCode.length() + 1;
			paraMap.put("length", length);
			paraMap.put("newModuleCode", newModuleCode);
			paraMap.put("oldModuleCode", oldModuleCode + "%");
			moduleDao.executeUpdateByHqlId("updateModule", paraMap);
			
			// 修改模块权限
			paraMap.clear();
			String newPermissionExpression = "{module:" + newModuleCode + "}";
			String oldPermissionExpression = "{module:" + oldModuleCode + "}";
			paraMap.put("newPermissionExpression", newPermissionExpression);
			paraMap.put("oldPermissionExpression", oldPermissionExpression);
			moduleDao.executeUpdateByHqlId("updateModulePermission", paraMap);
		}
		//TODO
		if(!changePublic){
			EdpShiroFilterFactoryBean.SpringShiroFilter shiroFilter = (EdpShiroFilterFactoryBean.SpringShiroFilter)SpringContextHolder.getBean("shiroFilter");
			shiroFilter.updateFilterChainDefinitionMap();
		}
		
		return module1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#save(com.jshx.module.admin.entity.Module)
	 */

	@Transactional(propagation = Propagation.NESTED)
	public Module save(Module module, String[] roleIds) {
		module.setIsVisible(1);
		module.setHasChild(0);
		Module parentModule = module.getParentModule();
		if (parentModule != null) {
			parentModule.setHasChild(parentModule.getHasChild() + 1);
			module.setParentModule(parentModule);
		}
		moduleDao.saveObject(module);
		
		//保存扩展信息
		if(this.getExtendDao()!=null && module.getModuleExtendInfo()!=null){
			IModuleExtendInfoDao extendDao = getExtendDao();
			IModuleExtendInfo extendInfo = module.getModuleExtendInfo();
			extendDao.saveModuleExtendInfo(extendInfo);
			module.setModuleExtendInfo(extendInfo);
		}
		// 添加角色
		if (roleIds != null && roleIds.length > 0) {
			for (String roleId : roleIds) {
				ModuleRight right = new ModuleRight();
				right.setModule(module);
				UserRole role = (UserRole) userRoleDao.getObjectById(
						UserRole.class, roleId);
				right.setRole(role);
				moduleRightDao.saveObject(right);
			}
		}
		return module;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#findModuleById(java.lang.String)
	 */

	public Module findModuleById(String id) {
		Module module = (Module) moduleDao.findById(id);
		if(this.getExtendDao()!=null ){
			IModuleExtendInfoDao extendDao = getExtendDao();
			module.setModuleExtendInfo(extendDao.getByModuleId(module.getId()));
		}
		return module;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#findModuleByPage(com.jshx.core.base.vo.Pagination,
	 *      java.util.Map)
	 */

	public Pagination findModuleByPage(Pagination page,
			Map<String, Object> paraMap) {
		return moduleDao.findModuleByPage(page, paraMap);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#findModuleRightByRole(com.jshx.module.admin.entity.UserRole)
	 */
	@Transactional
	public List<ModuleRight> findModuleRightByRole(String roleId) {
		UserRole role = this.userRoleDao.findUserRoleById(roleId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("role", role);
		return this.moduleRightDao.findRightByRole(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#delModuleRightByRole(java.lang.String)
	 */

	public void delModuleRightByRole(String roleId) {
		UserRole role = this.userRoleDao.findUserRoleById(roleId);
		this.moduleRightDao.removeByRole(role);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#saveRight(com.jshx.module.admin.entity.ModuleRight)
	 */

	@Transactional(propagation = Propagation.NESTED)
	public ModuleRight saveRight(ModuleRight right) {
		this.moduleRightDao.saveBaseModelObject(right);
		return right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#findModuleTrace(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<Module> findModuleTrace(String moduleCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("moduleCode", moduleCode);
		return moduleDao.findListByHqlId("findModuleTrace", paraMap);
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.admin.service.ModuleService#checkModuleCode(java.lang.String)
	 */
	public Boolean checkModuleCode(String moduleCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("moduleCode", moduleCode);
		List<?> list = moduleDao.findListByHqlId("cntModuleByCode", paraMap);
		if (list != null && list.size() > 0
				&& Integer.valueOf(list.get(0).toString()) > 0)
			return true;
		else
			return false;
	}
	
	@Transactional
	public void deleteRoleByModule(Map<String, Object> paraMap){
		moduleDao.executeUpdateByHqlId("delModuleRight", paraMap);
	}
	
	@SuppressWarnings("unchecked")
	public List<Module> findModuleForLeft(Map<String, Object> paraMap){
		return (List<Module>)moduleDao.findListBySqlId("findModuleTree", paraMap);
//		return moduleDao.findListByHqlId("findModuleTree", paraMap);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Module> findModuleForAdmin(Map<String, Object> paraMap) {
		return moduleDao.findListByHqlId("findModuleTreeAdmin", paraMap);
	}
	
	@SuppressWarnings("rawtypes")
	public List findModuleRight(Map<String, Object> paraMap){
		return moduleDao.findListByHqlId("cntModuleRight", paraMap);
	}
	
	public List<ModuleRight> findRightForModule(Map<String, Object> paraMap){
		return moduleRightDao.findRightForModule(paraMap);
	}
	
	@SuppressWarnings("rawtypes")
	public List findQuicklyStart(Map<String, Object> paraMap){
		return moduleDao.findListByHqlId("cntQuickStartByModule", paraMap);
	}
	
	public List<Module> findModuleByRole(Map<String, Object> paraMap){
		return moduleDao.findListByHqlId("findModuleByRole", paraMap);
	}
	
	private IModuleExtendInfoDao getExtendDao(){
		try{
			IModuleExtendInfoDao extendDao = (IModuleExtendInfoDao)SpringContextHolder.getBean("moduleExtendDao");
			return extendDao;
		}catch(Exception e){
			return null;
		}
	}
	
	@Override
	public int findButton(String moduleCode, String[] roleIds, int num) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("roles", Arrays.asList(roleIds));
		map.put("subnum", num);
		map.put("moduleCode", moduleCode);
		List list = moduleRightDao.findListBySqlId("findButtons", map);
		int i = 0;
		if (null != list && list.size() > 0) {
			i = Integer.parseInt(list.get(0).toString());
		}
		return i;
	}

	
}