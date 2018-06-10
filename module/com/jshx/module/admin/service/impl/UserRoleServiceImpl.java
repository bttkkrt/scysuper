/**
 * 
 */
package com.jshx.module.admin.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.UserRightDao;
import com.jshx.module.admin.dao.UserRoleDao;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.UserRoleService;

/**
 * @author f_cheng
 * 
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl implements
		UserRoleService {

	@Autowired() 
	@Qualifier("userRoleDao")
	private UserRoleDao userRoleDao;

	@Autowired() 
	@Qualifier("userRightDao")
	private UserRightDao userRightDao;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.userrole.service.UserRoleService#delete(java.lang.Integer
	 * )
	 */
	@Transactional(propagation = Propagation.NESTED)
	public void delete(String roleId) {
		UserRole role = userRoleDao.findUserRoleById(roleId);
		role.setDelFlag(1);
		userRoleDao.updateObject(role);

		// 删除下层的角色
		String roleCode = role.getRoleCode();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roleCode", roleCode + "%");
		userRoleDao.executeUpdateByHqlId("inactiveRole", paraMap);

		// 删除所有有该角色的用户
		paraMap = new HashMap<String, Object>();
		paraMap.put("role", role);
		userRoleDao.executeUpdateByHqlId("delUserRightByRole", paraMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.userrole.service.UserRoleService#deletes(java.lang.Integer
	 * [])
	 */

	@Transactional(propagation = Propagation.NESTED)
	public void deletes(String[] roleIds) {
		for (String id : roleIds)
			delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jshx.module.userrole.service.UserRoleService#getAll()
	 */
	@Transactional
	public List<UserRole> getAll() {
		return userRoleDao.findUserRoleList(new HashMap<String, Object>());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.userrole.service.UserRoleService#isReg(java.lang.String)
	 */

	public Integer isReg(String id, String roleName, String roleCode) {
		try {
			UserRole role = userRoleDao.findUserRoleByName(roleName);
			if (role != null && role.getDelFlag()==0) {
				if(role.getId().equals(id))
					return 2;
				else
					return 0;
			} else {
				role = findRoleByCode(roleCode);
				if (role != null && role.getDelFlag()==0){
					if(role.getId().equals(id))
						return 2;
					else
						return 1;
					//return 1;
				}else
					return 2;
			}
		} catch (Exception ex) {
			return 3;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.userrole.service.UserRoleService#modify(com.jshx.module
	 * .userrole.entity.UserRole)
	 */

	@Transactional(propagation = Propagation.NESTED)
	public UserRole modify(UserRole userRole) {
		UserRole role = userRoleDao.findUserRoleById(userRole.getId());
		String oldRoleCode = role.getRoleCode();
		String newRoleCode = userRole.getRoleCode();
		String oldRoleType = role.getRoleType();
		String newRoleType = userRole.getRoleType();
		role.setCaption(userRole.getCaption());
		role.setIsSupAdmin(userRole.getIsSupAdmin());
		role.setRoleName(userRole.getRoleName());
		role.setSortSq(userRole.getSortSq());
		role.setRoleCode(userRole.getRoleCode());
//		role.setRoleType(userRole.getRoleType());
		role.setParentRoleId(userRole.getParentRoleId());
		role.setDelFlag(userRole.getDelFlag());
		// role.setDeptCode(userRole.getDeptCode());
		userRoleDao.merge(role);

		if(role.getParentRoleId() !=null && !oldRoleType.equals(newRoleType)){
			//修改了最上层角色的类型，调整所有下层角色的类型
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("roleType", newRoleType);
			paraMap.put("roleCode", oldRoleCode + "%");
			userRoleDao.executeUpdateByHqlId("updateRoleType", paraMap);
		}
		
		// 调整下层的角色
		if (!oldRoleCode.equals(newRoleCode)) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			Integer length = oldRoleCode.length() + 1;
			paraMap.put("length", length);
			paraMap.put("newRoleCode", newRoleCode);
			paraMap.put("oldRoleCode", oldRoleCode + "%");
			userRoleDao.executeUpdateByHqlId("updateRole", paraMap);
		}
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.userrole.service.UserRoleService#save(com.jshx.module
	 * .userrole.entity.UserRole)
	 */

	@Transactional(propagation = Propagation.NESTED)
	public UserRole save(UserRole userRole) {
		userRole.setDelFlag(0);
		userRoleDao.saveObject(userRole);
		return userRole;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.userrole.service.UserRoleService#listUserRoleByPage(com
	 * .jshx.core.base.vo.Pagination, java.util.Map)
	 */

	public Pagination listUserRoleByPage(Pagination page,
			Map<String, Object> paraMap) {
		return userRoleDao.findUserRoleByPage(page, paraMap);
	}

	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.admin.service.UserRoleService#findRoleById(java.lang.
	 * String)
	 */
	public UserRole findRoleById(String id) {
		return (UserRole) userRoleDao.getObjectById(UserRole.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.admin.service.UserRoleService#findRole(java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	public List<UserRole> findRole(Integer isLower) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("isLower", isLower);
		return userRoleDao.findListByHqlId("queryUserRole", paraMap);
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.admin.service.UserRoleService#createRoleCode(java.lang
	 * .String)
	 */
	public String createRoleCode(String parentRoleCode) {
		Integer maxID = userRoleDao.getMaxModuleCodeByParent(parentRoleCode);
		if (maxID == null || maxID.intValue() == 0) {
			if (parentRoleCode == null || parentRoleCode.equals(""))
				return "001";
			else
				return parentRoleCode + "01";
		} else {
			if (parentRoleCode == null || parentRoleCode.equals("")) {
				if (new Integer(maxID.intValue() + 1) < 10)
					return "00" + new Integer(maxID.intValue() + 1);
				else if (new Integer(maxID.intValue() + 1) < 100
						&& new Integer(maxID.intValue() + 1) >= 10)
					return "0" + new Integer(maxID.intValue() + 1);
				else
					return "" + new Integer(maxID.intValue() + 1);
			} else {
				if (new Integer(maxID.intValue() + 1) < 10)
					return parentRoleCode + "0"
							+ new Integer(maxID.intValue() + 1);
				else
					return parentRoleCode + new Integer(maxID.intValue() + 1);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jshx.module.admin.service.UserRoleService#findRoleByCode(java.lang
	 * .String)
	 */
	public UserRole findRoleByCode(String roleCode) {
		UserRole role = (UserRole) userRoleDao.getObjectByProperty(UserRole.class,
				"roleCode", roleCode);
		return role;
	}

	public List<UserRole> findLeafRole(String roleType, String selNode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (selNode != null) {
			paraMap.put("roleCode", selNode + "%");
			paraMap.put("length", selNode + "00");
		} else
			paraMap.put("length", "000");
		if(roleType!=null && !roleType.trim().equals("ALL"))
			paraMap.put("roleType", roleType);
		return userRoleDao.findLeafRole(paraMap);
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserRoleService#getRoleByUser(java.lang.String, java.lang.String, com.jshx.module.admin.entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserRole> getRoleByUser(String roleCode, String roleName,
			User user) {

		List<UserRole> list = new ArrayList<UserRole>();
		Set<UserRole> set = new HashSet<UserRole>();
		if (roleCode == null || roleCode.trim().equals("")) {
			// 加载用户所有的权限
			List<UserRight> rightList = userRightDao.findRightByUser(user);
			for (UserRight right : rightList) {
				UserRole role = right.getRole();
				set.add(role);
			}

			// 加载自己创建的角色
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("length", "000");
			paraMap.put("roleName", "%" + roleName + "%");

			List<UserRole> roleList = userRoleDao.findListByHqlId(
					"queryRoleForTree", paraMap);
			for (UserRole role2 : roleList) {
				if (null != user.getIsSuperAdmin() && user.getIsSuperAdmin()) {
					set.add(role2);
				} else if (role2.getCreateUserID() != null
						&& role2.getCreateUserID().equals(user.getId())) {
					set.add(role2);
				}
			}
			Iterator<UserRole> itr = set.iterator();
			while(itr.hasNext()){
				UserRole role = itr.next();
				Boolean flag = true;
				Iterator<UserRole> itr1 = set.iterator();
				while(itr1.hasNext()){
					UserRole roleTmp = itr1.next();
					if(!role.equals(roleTmp)&&role.getRoleCode().indexOf(roleTmp.getRoleCode())!=-1){
						flag = false;
						break;
					}
				}
				if (flag) {
					list.add(role);
				}
			}
		} else {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if (!roleCode.equals("A")) {
				paraMap.put("roleCode", roleCode + "%");
				paraMap.put("length", roleCode + "00");
			} else
				paraMap.put("length", "000");
			paraMap.put("roleName", "%" + roleName + "%");
			List<UserRole> roleList = userRoleDao.findListByHqlId(
					"queryRoleForTree", paraMap);
			Boolean flag = null;
			for (UserRole role : roleList) {
				flag = false;
				if(!user.getIsSuperAdmin()){
					if (role.getCreateUserID() != null && role.getCreateUserID().equals(user.getId())) 
						flag = true;
				}else
					flag = true;

				if (flag) {
					list.add(role);
				}
			}
		}
		Collections.sort(list,new SortByCode());
		return list;
	}

	/*
	 * (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserRoleService#getRoleByUserForList(java.lang.String, java.lang.String, java.lang.String, com.jshx.module.admin.entity.User)
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UserRole> getRoleByUserForList(String roleType, String roleCode,
			String roleName, User user) {

		List<UserRole> list = new ArrayList<UserRole>();
		if (roleCode == null || roleCode.trim().equals("")) {
			Map<String, UserRole> roleMap = new LinkedHashMap<String, UserRole>();
			// 加载用户所有的权限
			List<UserRight> rightList = userRightDao.findRightByUser(user);
			for (UserRight right : rightList) {
				UserRole role = right.getRole();
				if(role==null)
					continue;
				if(roleType==null){
					//roleMap.put(role.getId(), role);此处注释掉为了查询的时候不把系统管理员查出来
				}
				else{
					if(role.getRoleType().equals(roleType))
						roleMap.put(role.getId(), role);
				}
			}

			// 加载自己创建的角色
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("length", "000");
			paraMap.put("roleName", "%" + roleName + "%");
			
			List<UserRole> roleList = userRoleDao.findListByHqlId(
					"queryRoleForTree", paraMap);
			for (UserRole role2 : roleList) {
				if (null != user.getIsSuperAdmin() && user.getIsSuperAdmin()) {
					if(roleType==null)
						roleMap.put(role2.getId(), role2);
					else{
						if(role2.getRoleType().equals(roleType))
							roleMap.put(role2.getId(), role2);
					}
				} else if (role2.getCreateUserID() != null
						&& role2.getCreateUserID().equals(user.getId())) {
					if(roleType==null)
						roleMap.put(role2.getId(), role2);
					else{
						if(role2.getRoleType().equals(roleType))
							roleMap.put(role2.getId(), role2);
					}
				}
			}

			Iterator<String> keyIt = roleMap.keySet().iterator();
			while (keyIt.hasNext()) {
				String key = keyIt.next();
				UserRole role = roleMap.get(key);
				// 去除以有的上层角色中包含的下层角色和重复的
				Boolean flag = true;
				Iterator<String> keyIt1 = roleMap.keySet().iterator();
				while (keyIt1.hasNext()) {
					String key1 = keyIt1.next();
					UserRole role1 = roleMap.get(key1);

					if (!role.equals(role1)
							&& role.getRoleCode().indexOf(role1.getRoleCode()) != -1) {
						flag = false;
						break;
					}
				}
				if (flag) {
					list.add(role);
				}
			}
			
			
		} else {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			//判断点击
			if (!roleCode.equals("A")) {
				paraMap.put("roleCode", roleCode + "%");
				paraMap.put("length", roleCode + "00");
			} else{
				paraMap.put("length", "000");
			}
				
			paraMap.put("roleName", "%" + roleName + "%");
			
			List<UserRole> roleList = userRoleDao.findListByHqlId(
					"queryRoleForTree", paraMap);
			Boolean flag = null;
			for (UserRole role : roleList) {
				flag = false;
				if(!user.getIsSuperAdmin()){
					if (role.getCreateUserID() != null
							&& role.getCreateUserID().equals(user.getId())) 
						flag = true;
				}else
					flag = true;
				

				if (flag) {
					list.add(role);
				}
			}
		}
		Collections.sort(list,new SortByCodeLength());
		return list;
	}

	

	/**
	 * ext树换成easyui树，用户管理中角色设置，显示所有角色
	 */
	@SuppressWarnings("rawtypes")
	public List findUserRoleList(Map<String, Object> paraMap) {
		return userRoleDao.findListByHqlId("queryRoleForTree", paraMap);
	}

	public List<UserRole> findUserRole(Map<String, Object> paraMap) {
		return userRightDao.findUserRole(paraMap);
	}

	public UserRole findUserRoleById(String roleId) {
		return userRoleDao.findUserRoleById(roleId);
	}

	@Override
	public Integer cntUsersinRole(String[] ids) {
		// TODO Auto-generated method stub
		return userRoleDao.cntUsersinRole(ids);
	}
}
class SortByCodeLength implements Comparator<UserRole>{
	public int compare(UserRole obj1, UserRole obj2) {
		//if (Integer.parseInt(code1.getItemValue()) > Integer.parseInt(code2.getItemValue()))
		//if(obj1.getRoleCode().length() > obj2.getRoleCode().length())
		if(obj1.getSortSq()<obj2.getSortSq())
			return 1;
		else
			return -1;
	}
}
class SortByCode implements Comparator<UserRole>{
	public int compare(UserRole obj1, UserRole obj2) {
		//if (Integer.parseInt(code1.getItemValue()) > Integer.parseInt(code2.getItemValue()))
		//if(Integer.parseInt((obj1.getRoleCode().substring(1, obj1.getRoleCode().length()))) > Integer.parseInt((obj2.getRoleCode().substring(1, obj2.getRoleCode().length()))))
		if(obj1.getSortSq()<obj2.getSortSq())
		    return 1;
		else
			return -1;
	}
}
