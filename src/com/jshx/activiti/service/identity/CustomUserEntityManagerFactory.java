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
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Activiti用户管理工厂类
 * 
 * @author		YuWeitao
 * @version		创建时间：2013-5-13  14:23:56
 */
public class CustomUserEntityManagerFactory implements SessionFactory {   
    private UserEntityManager userEntityManager;   
  
    @Autowired  
    public void setUserEntityManager(UserEntityManager userEntityManager) {   
        this.userEntityManager = userEntityManager;   
    }   
  
    public Class<?> getSessionType() {   
        // 返回原始的UserManager类型   
        return UserEntityManager.class;   
    }   
  
    public Session openSession() {   
        // 返回自定义的UserManager实例   
        return userEntityManager;   
    }   
}  
