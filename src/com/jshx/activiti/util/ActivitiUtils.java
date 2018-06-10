/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-5-13          YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.util;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;

import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;

/**  
 * Activiti工具类
 * 
 * @author   YuWeitao
 * @version  创建时间：2013-5-13  15:32:01
 * 
 */
public class ActivitiUtils {  
	/**
	 * 将统一开发平台的User对象转化为Activiti的用户
	 * 
	 * @param bUser
	 * @return
	 */
    public static UserEntity toActivitiUser(User bUser){   
        UserEntity userEntity = new UserEntity();   
        userEntity.setId(bUser.getId().toString());   
        userEntity.setFirstName(bUser.getDisplayName());   
        userEntity.setLastName(bUser.getLoginId());   
        userEntity.setPassword(bUser.getPassword());   
        userEntity.setEmail(bUser.getEmail());   
        userEntity.setRevision(1);   
        return userEntity;   
    }   
      
    /**
     * 将统一开发平台的角色转化为Activiti的组
     * 
     * @param userRole
     * @return
     */
    public static GroupEntity toActivitiGroup(UserRole userRole){   
        GroupEntity groupEntity = new GroupEntity();   
        groupEntity.setRevision(1);   
        groupEntity.setType("assignment");   
        //taskCandidateUser等方法是通过groupEntity.id来查询的，对应流程定义文件中的activiti:candidateGroups等值
        groupEntity.setId(userRole.getRoleName());   
        groupEntity.setName(userRole.getRoleName());   
        return groupEntity;   
    }   
       
    /**
     * 将统一开发平台的角色List转化为Activiti的组List
     * 
     * @param userRoleList
     * @return
     */
    public static List<org.activiti.engine.identity.Group> toActivitiGroups(List<UserRole> userRoleList){   
        List<Group> groupEntitys = new ArrayList<org.activiti.engine.identity.Group>();   
           
        for (UserRole userRole : userRoleList) {   
            GroupEntity groupEntity = toActivitiGroup(userRole);   
            groupEntitys.add(groupEntity);   
        }   
        return groupEntitys;   
    }   
}  
