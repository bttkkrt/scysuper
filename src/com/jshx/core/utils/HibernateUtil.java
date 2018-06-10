/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-4        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**  
 * Hibernate工具类
 * 
 * @author   Chenjian
 * @version 创建时间：2011-3-4 下午04:54:03  
 * 
 */
public class HibernateUtil {
	
	/**
	 * 强行回滚当前事务
	 */
	public static void rollback(){
		SessionFactory sessionFactory = (SessionFactory)SpringContextHolder.getBean("sessionFactory");
		sessionFactory.getCurrentSession().getTransaction().rollback();
	}
	
	/**
	 * 提交当前事务
	 */
	public static void commit(){
		SessionFactory sessionFactory = (SessionFactory)SpringContextHolder.getBean("sessionFactory");
		sessionFactory.getCurrentSession().getTransaction().commit();
	}
	
	/**
	 * 手动开始事务
	 */
	public static Transaction beginTransaction(){
		SessionFactory sessionFactory = (SessionFactory)SpringContextHolder.getBean("sessionFactory");
		return sessionFactory.getCurrentSession().beginTransaction();
	}
	
	/**
	 * 获得当前Session
	 */
	public static Session getSession(){
		SessionFactory sessionFactory = (SessionFactory)SpringContextHolder.getBean("sessionFactory");
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 打开新的Session
	 */
	public static Session openSession(){
		SessionFactory sessionFactory = (SessionFactory)SpringContextHolder.getBean("sessionFactory");
		return sessionFactory.openSession();
	}

}
