/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-13        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.dao.DeptDAO;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.dao.UserRightDao;
import com.jshx.module.admin.dao.UserRoleDao;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.extend.IUserExtendInfo;
import com.jshx.module.admin.extend.IUserExtendInfoDao;
//import com.jshx.module.admin.service.SecurityService;
import com.jshx.module.admin.service.UserService;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-1-13 下午07:08:31  
 * 用户管理服务的实现  
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	
	@Autowired() 
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	@Autowired() 
	@Qualifier("userRightDao")
	private UserRightDao userRightDao;
	
	@Autowired() 
	@Qualifier("userRoleDao")
	private UserRoleDao userRoleDao;
	
	@Autowired() 
	@Qualifier("deptDAO")
	private DeptDAO deptDAO;
		
	//@Autowired() @Qualifier
	//private SecurityService securityService;

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#activeUser(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public User activeUser(String id) {
		userDAO.activeUser(id);
		return userDAO.findUserById(id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#findUserById(java.lang.String)
	 */
	
	public User findUserById(String id) {
		User user = (User)userDAO.getObjectById(User.class, id);
		IUserExtendInfoDao extendDao = getExtendDao();
		if(extendDao!=null&&user!=null){
			IUserExtendInfo userExtendInfo = extendDao.getByUserId(user.getId());
			user.setUserExtendInfo(userExtendInfo);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#findUserByLoginId(java.lang.String)
	 */
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NESTED)
	public User findUserByLoginId(String loginId) {
		//逻辑 删除用户后，存在多个同loginId的用户，因此不能通过只找loginId的方法获得user
		//return (User)userDAO.getObjectByProperty(User.class, "loginId", loginId);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("loginId", loginId);
		List<User> users = userDAO.findListByHqlId("queryUsers", paraMap);
		if(null!=users&& users.size()==1){
			User user = users.get(0);
			IUserExtendInfoDao extendDao = getExtendDao();
			if(extendDao!=null){
				IUserExtendInfo userExtendInfo = extendDao.getByUserId(user.getId());
				user.setUserExtendInfo(userExtendInfo);
			}
			return user;
		}else{
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#findUserByLoginId(java.lang.String)
	 */
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NESTED)
	public User findUserByLoginIds(String loginId) {
		//逻辑 删除用户后，存在多个同loginId的用户，因此不能通过只找loginId的方法获得user
		//return (User)userDAO.getObjectByProperty(User.class, "loginId", loginId);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("loginIds", loginId);
		List<User> users = null;
		try {
			users = userDAO.findListByHqlId("queryUsers", paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(null!=users&& users.size()==1){
			User user = users.get(0);
			IUserExtendInfoDao extendDao = getExtendDao();
			if(extendDao!=null){
				IUserExtendInfo userExtendInfo = extendDao.getByUserId(user.getId());
				user.setUserExtendInfo(userExtendInfo);
			}
			return user;
		}else{
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#findUserByPage(com.jshx.core.base.vo.Pagination, java.util.Map)
	 */
	
	public Pagination findUserByPage(Pagination page,
			Map<String, Object> paraMap) {
		return userDAO.findPageByHqlId("queryUsers", paraMap, page);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#inactiveUser(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public User inactiveUser(String id) {
		userDAO.inactiveUser(id);
		return userDAO.findUserById(id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#initPassword(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public void initPassword(String id) {
		User user = (User)userDAO.getObjectById(User.class, id);
		String password = CodeUtil.encode("99999", CodeUtil.MD5);
		user.setPassword(password);
		userDAO.updateObject(user);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#isReg(java.lang.String)
	 */
	
	public Boolean isReg(String loginId)  {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("loginId", loginId);
		List<?> list = userDAO.findListByHqlId("isReg", paraMap);
		if(list==null)
			return false;
		else{
			Long cnt = (Long)list.get(0);
			if(cnt>0)
				return true;
			else
				return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#modify(com.jshx.module.mgt.entity.User)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public User modify(User user, String[] roleIds) {
		User user1 = (User)userDAO.getObjectById(User.class, user.getId());
		user1.setEmail(user.getEmail());
		user1.setDept((Department)user.getDept());
		user1.setDuty(user.getDuty());
		user1.setDisplayName(user.getDisplayName());
		user1.setTel(user.getTel());
		user1.setMobile(user.getMobile());
		user1.setDeptCode(user.getDeptCode());
		user1.setSortSq(user.getSortSq());
		user1.setZfzh(user.getZfzh());
		user1.setFilePath(user.getFilePath());
		//可以修改用户名
		user1.setLoginId(user.getLoginId());
		//删除原有角色
		//Map<String, Object> paraMap = new HashMap<String, Object>();
		//paraMap.put("user", user1);
		//userDAO.executeUpdateByHqlId("delUserRight", paraMap);
		//添加角色
		if(roleIds!=null && roleIds.length>0){
			for(String roleId:roleIds){
				UserRight right = new UserRight();
				right.setUser(user1);
				UserRole role = (UserRole)userRoleDao.getObjectById(UserRole.class, roleId);
				right.setRole(role);
				userRightDao.saveObject(right);
			}
		}
		userDAO.updateObject(user1);
		IUserExtendInfo userExtendInfo = user.getUserExtendInfo();
		if(userExtendInfo != null){
			IUserExtendInfoDao extendDao = getExtendDao();
			if(extendDao!=null){
				userExtendInfo.setUserId(user.getId());
				if(userExtendInfo.getId()!=null)
					extendDao.updateIUserExtendInfo(userExtendInfo);
				else
					extendDao.saveUserExtendInfo(userExtendInfo);
				user1.setUserExtendInfo(userExtendInfo);
			}
		}
		return user1;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#save(com.jshx.module.mgt.entity.User)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public User save(User user, String[] roleIds) {
		String password = CodeUtil.encode("99999", CodeUtil.MD5);
		user.setDelFlag(0);
		user.setPassword(password);
		user.setCssId(SysPropertiesUtil.getString("DEFAULT_CSS", "default"));
		userDAO.saveObject(user);
		//添加角色
		if(roleIds!=null && roleIds.length>0){
			for(String roleId:roleIds){
				UserRight right = new UserRight();
				right.setUser(user);
				UserRole role = (UserRole)userRoleDao.getObjectById(UserRole.class, roleId);
				right.setRole(role);
				userRightDao.saveObject(right);
			}
		}
		IUserExtendInfo userExtendInfo = user.getUserExtendInfo();
		if(userExtendInfo != null){
			IUserExtendInfoDao extendDao = getExtendDao();
			if(extendDao!=null){
				userExtendInfo.setUserId(user.getId());
				extendDao.saveUserExtendInfo(userExtendInfo);
				user.setUserExtendInfo(userExtendInfo);
			}
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#getUsersByDept(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	
	public List<User> getUsersByDept(String deptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("deptCode", deptCode.trim());
		return (List<User>)userDAO.findListByHqlId("queryUsers", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#modifyStyle(java.lang.String, java.lang.String, java.lang.Integer)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public void modifyStyle(String id, String cssId, Integer displayNum) {
		User user = this.findUserById(id);
		user.setCssId(cssId);
		user.setDisplayNum(displayNum);
		userDAO.updateObject(user);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#checkPassword(java.lang.String, java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED)
	public User checkPassword(String loginId, String password) {
		User user = this.findUserByLoginId(loginId);		
		if(null!=user && user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#checkPassword(java.lang.String, java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED)
	public User checkPasswords(String loginId, String password) {
		User user = this.findUserByLoginIds(loginId);		
		if(null!=user && user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.mgt.service.UserService#modifyPassword(java.lang.String, java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public void modifyPassword(String id, String password) {
		password = CodeUtil.encode(password, CodeUtil.MD5);
		User user = this.findUserById(id);
		user.setPassword(password);
		userDAO.updateObject(user);
	}

	/**
	 * @return the userRightDao
	 */
	public UserRightDao getUserRightDao() {
		return userRightDao;
	}

	/**
	 * @param userRightDao the userRightDao to set
	 */
	public void setUserRightDao(UserRightDao userRightDao) {
		this.userRightDao = userRightDao;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#checkMobileUnique(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Boolean checkMobileUnique(String mobile) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("mobile", mobile);
		List<Object> list = userDAO.findListByHqlId("cntUserByMobile", paraMap);
		Long cnt = Long.valueOf(list.get(0).toString());
		if(cnt>0)
			return true;
		else
			return false;
	}
	
	public Boolean checkDeptExist(String deptCode){
		Department department = (Department) deptDAO.getObjectByProperty(Department.class, "deptCode", deptCode);
		if(null!=department && 1!=department.getDelFlag()){
			return true;
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#checkPasswordByMobile(java.lang.String, java.lang.String)
	 */
	public User checkPasswordByMobile(String mobile, String password) {
		User user = (User)userDAO.getObjectByProperty(User.class, "mobile", mobile);
		password = CodeUtil.encode(password, CodeUtil.MD5);
		
		if(null!=user && user.getPassword().equals(password))
			return user;
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#delByRole(java.lang.String)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public void delByRole(String roleId) {
		UserRole role = userRoleDao.findUserRoleById(roleId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("role", role);
		userRightDao.executeUpdateByHqlId("delUserRightByRole", paraMap);
	}
	
	/**
	 * 根据用户删除权限
	 * 
	 * @Title: delByRole 
	 * @Description: 
	 * @param roleId
	 * @return void   
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public void delByUser(String userId){
		User user = userDAO.findUserById(userId);
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("user", user);
		userRightDao.executeUpdateByHqlId("delUserRightByUser", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#findByRole(java.lang.String)
	 */
	public List<UserRight> findByRole(String roleId) {
		UserRole role = userRoleDao.findUserRoleById(roleId);
		return userRightDao.findRightByRole(role);
	}

	

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#saveRight(com.jshx.module.admin.entity.UserRight)
	 */
	@Transactional(propagation=Propagation.NESTED) 
	public UserRight saveRight(UserRight right) {
		userRightDao.saveBaseModelObject(right);
		return right;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#checkUserByMobile(java.lang.String, java.lang.String)
	 */
	
	public User findUserByMobile(String mobile) {
		User user = (User)userDAO.getObjectByProperty(User.class, "mobile", mobile);
		
		if(null!=user){
			List<String> deptIds = new ArrayList<String>();
			deptIds.add(user.getDept().getId());
//			List<Department> deptList= securityService.findDataAccessAuth(user.getId());
//	    	if(deptList!=null && deptList.size()>0){
//	    		for(int i=0;i<deptList.size();i++){
//	    			deptIds.add(deptList.get(i).getId());
//	    		}
//	    	}	
	    	
	    	user.setDeptIds(deptIds);
    		List<?> rightList = user.getUserRoles();
    		
    		if(rightList!=null && rightList.size()>0){
    			String[] roleIds = new String[rightList.size()];
    			for(int i = 0; i<rightList.size(); i++){
    				UserRight right = (UserRight)rightList.get(i);
    				roleIds[i] = right.getRole().getId();
    				if(right.getRole().getIsSupAdmin()==1){
    					user.setIsSuperAdmin(true);
    				}
    			}
    			user.setRoleIds(roleIds);
    		}
    		user.setLogTime(new Date());
    		
			IUserExtendInfoDao extendDao = getExtendDao();
			if(extendDao!=null){
				IUserExtendInfo userExtendInfo = extendDao.getByUserId(user.getId());
				user.setUserExtendInfo(userExtendInfo);
			}
			return user;
		}else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#findByRole(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<UserRight> findByRole(String roleId, String deptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("roleId", roleId);
		if(deptCode!=null)
			paraMap.put("deptCode", deptCode+"%");
		return userDAO.findListByHqlId("findUserRight", paraMap);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.UserService#findAllUserByDept(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAllUsersByDept(String deptCode) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(null!=deptCode)
			paraMap.put("parentDeptCode", deptCode.trim()+"%");
		return (List<User>)userDAO.findListByHqlId("queryUsers", paraMap);
	}

	@Transactional
	public void saveUser(User user) {		
		userDAO.saveUser(user);
		IUserExtendInfo userExtendInfo = user.getUserExtendInfo();
		if(userExtendInfo != null){
			IUserExtendInfoDao extendDao = getExtendDao();
			if(extendDao!=null){
				userExtendInfo.setUserId(user.getId());
				extendDao.saveUserExtendInfo(userExtendInfo);
				user.setUserExtendInfo(userExtendInfo);
			}
		}
	}
	
	@Transactional
	public void delUser(String userId){
	    User user = findUserById(userId);
	    IUserExtendInfo userExtendInfo = user.getUserExtendInfo();
        if(userExtendInfo != null){
            IUserExtendInfoDao extendDao = getExtendDao();
            if(extendDao!=null){
                extendDao.removeObject(userExtendInfo);
            }
        }
		userDAO.removeObjectById(User.class, userId);
	}
	
	@Transactional
	public void logicDelUser(String userId){
		User user = this.findUserById(userId);
		user.setDelFlag(2);
		userDAO.updateObject(user);
	}
	
	@Transactional
	public void save(List<User> userList) throws Exception{
		for (int i = 0; i < userList.size(); i++) {
			if (this.isReg(userList.get(i).getLoginId())) {
				throw new Exception("注册Id: " + userList.get(i).getLoginId() + " 已经存在,请重新输入！");
			}
			if (this.checkMobileUnique(userList.get(i).getMobile())) {
				throw new Exception("手机号: " + userList.get(i).getMobile() + " 已经注册，请重新输入！");
			}
			this.save(userList.get(i),null);
		}
	}
	
	private IUserExtendInfoDao getExtendDao(){
		try{
			IUserExtendInfoDao extendDao = (IUserExtendInfoDao)SpringContextHolder.getBean("userExtendDao");
			return extendDao;
		}catch(Exception e){
			return null;
		}
	}

	public List findUsers(Map<String, Object> paraMap) {
		return userDAO.findListByHqlId("queryUsers", paraMap);
	}
}
