package com.jshx.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候中取出ApplicaitonContext.
 * 
 * @author Chenjian
 *
 */
public class SpringContextHolder implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext;

	/**
	 * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
	 * 
	 * @param applicationContext   当前Spring环境的ApplicationContext
	 * 
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}
	
	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	
	/**
	 * 判断ApplicationContext是否注入
	 * 
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}

	/**
	 * 根据Bean的名称查找Bean
	 * 
	 * @param beanName bean名称
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(String beanName){
		checkApplicationContext();
		return (T)applicationContext.getBean(beanName);
	}
	
	/**
	 * 根据Bean的类别查找Bean
	 * @param clazz   Bean的类别
	 */
	@SuppressWarnings("unchecked")
	public static <T>T getBean(Class<T> clazz){
		checkApplicationContext();
		return (T)applicationContext.getBeansOfType(clazz);
	}
	
	/**
	 * 返回全部的bean
	 */
	public static Map<String,Object> getAllBeans(){
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		Map<String,Object> beanMap = new HashMap<String,Object>();
		for(String beanName : beanNames){
			if(beanName.indexOf("ProxyTemplate")!=-1)
				continue;
			Object bean = applicationContext.getBean(beanName);
			beanMap.put(beanName, bean);
		}
		return beanMap;
	}
}
