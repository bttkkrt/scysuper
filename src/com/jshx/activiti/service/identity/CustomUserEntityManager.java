/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-5-13          YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.service.identity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jshx.activiti.util.ActivitiUtils;
import com.jshx.core.exception.BasalException;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.UserRoleService;
import com.jshx.module.admin.service.UserService;

/**  
 * 自定义Activiti用户管理类
 * 
 * @author   YuWeitao
 * @version  创建时间：2013-5-13  15:32:01
 * 
 */
@Service  
public class CustomUserEntityManager extends UserEntityManager {   
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRoleService userRoleService;
    
  
    @Override  
    public UserEntity findUserById(final String userCode) {   
        if (userCode == null)   
            return null;   
  
        try {   
            UserEntity userEntity = null;   
            User user = userService.findUserById(userCode);
            userEntity = ActivitiUtils.toActivitiUser(user);   
            return userEntity;   
        } catch (Exception e) {   
        	BasalException be = new BasalException(BasalException.ERROR,e);
			throw be;
        }   
    }   
  
    @Override  
    public List<Group> findGroupsByUser(final String userCode) {   
        if (userCode == null)   
            return null; 
        
    	Map<String, Object> paraMap = new HashMap<String, Object>();
    	paraMap.put("userId", userCode);
    	List<UserRole> userRoleList = userRoleService.findUserRole(paraMap);
        List<Group> gs = null;   
        gs = ActivitiUtils.toActivitiGroups(userRoleList);   
        return gs;   
    }   
  
    @Override  
    public List<org.activiti.engine.identity.User> findUserByQueryCriteria(UserQueryImpl query, Page page) {   
        throw new RuntimeException("not implement method."); 
    }   
  
    @Override  
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId,   
            String key) {   
        throw new RuntimeException("not implement method.");   
    }   
  
    @Override  
    public List<String> findUserInfoKeysByUserIdAndType(String userId,   
            String type) {   
        throw new RuntimeException("not implement method.");   
    }   
  
    @Override  
    public long findUserCountByQueryCriteria(UserQueryImpl query) {   
        throw new RuntimeException("not implement method.");   
    }   
} 
