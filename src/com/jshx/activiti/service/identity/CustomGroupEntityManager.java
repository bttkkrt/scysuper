/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-5-13          YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.service.identity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.GroupQueryImpl;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jshx.core.exception.BasalException;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.UserRoleService;
import com.jshx.module.admin.service.UserService;

/**  
 * 自定义Activiti用户组管理类
 * 
 * @author   YuWeitao
 * @version  创建时间：2013-5-13  15:32:01
 * 
 */
@Service  
public class CustomGroupEntityManager extends GroupEntityManager {   
    @Autowired
    private UserService userService; 
    
    @Autowired
    private UserRoleService userRoleService;
  
    @Override  
    public GroupEntity findGroupById(final String groupCode) {   
        if (groupCode == null)   
            return null;   
           
            try {   
                UserRole userRole = userRoleService.findRoleById(groupCode);   
                   
                GroupEntity groupEntity = new GroupEntity();   
                groupEntity.setRevision(1);   
  
                // activiti有3种预定义的组类型：security-role、assignment、user   
                // 如果使用Activiti   
                // Explorer，需要security-role才能看到manage页签，需要assignment才能claim任务   
                groupEntity.setType("assignment");   
  
                groupEntity.setId(userRole.getId());   
                groupEntity.setName(userRole.getRoleName());   
                return groupEntity;   
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
        List<Group> gs = new ArrayList<Group>();   
        GroupEntity groupEntity;   
        for (UserRole userRole : userRoleList) {   
        	groupEntity = new GroupEntity();   
        	groupEntity.setRevision(1);   
        	groupEntity.setType("assignment");   
  
        	groupEntity.setId(userRole.getRoleName());   //taskCandidateUser等方法是通过groupEntity.id来查询的，对应流程定义文件中的activiti:candidateGroups等值
        	groupEntity.setName(userRole.getRoleName());   
            gs.add(groupEntity);   
        }   
        return gs;   
    }   
  
    @Override  
    public List<Group> findGroupByQueryCriteria(GroupQueryImpl query, Page page) {   
        throw new RuntimeException("not implement method.");   
    }   
  
    @Override  
    public long findGroupCountByQueryCriteria(GroupQueryImpl query) {   
        throw new RuntimeException("not implement method.");   
    }   
}  
