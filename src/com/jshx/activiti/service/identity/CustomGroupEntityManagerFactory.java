/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-5-13          YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.service.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Activiti用户组管理工厂类
 * 
 * @author	YuWeitao
 * @version	创建时间：2013-5-13  14:25:44
 */
public class CustomGroupEntityManagerFactory implements SessionFactory {   
    private GroupEntityManager groupEntityManager;   
  
    @Autowired  
    public void setGroupEntityManager(GroupEntityManager groupEntityManager) {   
        this.groupEntityManager = groupEntityManager;   
    }   
  
    public Class<?> getSessionType() {   
        // 返回原始的GroupEntityManager类型   
        return GroupEntityManager.class;   
    }   
  
    public Session openSession() {   
        // 返回自定义的GroupEntityManager实例   
        return groupEntityManager;   
    }   
}  