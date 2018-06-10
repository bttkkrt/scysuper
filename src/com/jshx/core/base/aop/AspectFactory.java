/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-2-17        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.core.base.aop;

import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshx.core.exception.AopException;
import com.jshx.core.utils.SpringContextHolder;

/**  
 * @author   Chenjian
 * @version 创建时间：2011-2-17 上午11:03:20  
 * 
 * adviceMap保存切入点以及其对应的AOP具体实现的映射<br>
 * 在调用AOP的时候，需要根据adviceMap中配置的信息查找具体的接入点的Aspect并执行
 */
public class AspectFactory  {
	
	protected Logger logger = LoggerFactory.getLogger(AspectFactory.class);
	
	private String getBeanName(String methodInfo){
		if(this.adviceMap==null)
			return null;
		Iterator<String> keyIt = this.adviceMap.keySet().iterator();
		while(keyIt.hasNext()){
			String regex = keyIt.next();
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(methodInfo);
			if(m.matches()){
				return adviceMap.get(regex);
			}
		}
		return null;
	}
		
	/** 切入点以及其对应的AOP具体实现类的映射 */
	private Map<String, String> adviceMap;

	/**
	 * 方法运行后执行的操作
	 * 
	 * @param jp 切点
	 * @param retVal 方法返回值
	 * @return 
	 * @throws com.jshx.core.exception.AopException  
	 */
	public void doAfter(JoinPoint jp, Object retVal) throws Throwable {
		if(adviceMap==null)
			return;
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		if(beanName==null)
			return;
		Object aspect = SpringContextHolder.getBean(beanName);		
		if(aspect==null)
			throw new AopException("AOP配置错误！Bean"+beanName+"未配置！");
		if(!(aspect instanceof AfterAdvice))
			return ;
		AfterAdvice advice = (AfterAdvice)aspect;
		advice.doAfter(jp, retVal);
	}

	/**
	 * 实现包围通知
	 * 
	 * @param pjp 切点
	 * @throws com.jshx.core.exception.AopException
	 * @return   
	 */
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		if(adviceMap==null){
			Object retVal = null;
	        try{
	        	retVal = pjp.proceed();
	        }catch(Throwable cause){
	        	throw new AopException(cause.getLocalizedMessage(),cause);
	        }
	        return retVal;
		}
		String className = pjp.getTarget().getClass().getName();
		String methodName = pjp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		if(beanName==null){
			Object retVal = null;
	        try{
	        	retVal = pjp.proceed();
	        }catch(Throwable cause){
	        	throw cause;
	        }
	        return retVal;
		}
		Object aspect = SpringContextHolder.getBean(beanName);		
		if(aspect==null)
			throw new AopException("AOP配置错误！Bean"+beanName+"未配置！");
		if(!(aspect instanceof AroundAdvice)){
			Object retVal = pjp.proceed();
	        return retVal;
		}
		AroundAdvice advice = (AroundAdvice)aspect;
		return advice.doAround(pjp);
	}

	/**
	 * 方法运行后执行前的操作
	 * 
	 * @param jp 切点
	 * @return void   
	 * @throws com.jshx.core.exception.AopException
	 */
	public void doBefore(JoinPoint jp) throws AopException{
		if(adviceMap==null)
			return;
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		if(beanName==null)
			return;
		
		Object aspect = SpringContextHolder.getBean(beanName);	
		if(aspect==null)
			throw new AopException("AOP配置错误！Bean"+beanName+"未配置！");
		if(!(aspect instanceof BeforeAdvice))
			return;
		BeforeAdvice advice = (BeforeAdvice)aspect;
		advice.doBefore(jp);
	}

	/**
	 * 异常处理
	 * 
	 * @param jp
	 * @param ex
	 * @return void  
	 * @throws com.jshx.core.exception.AopException
	 */
	public void doThrowing(JoinPoint jp, Throwable ex) throws AopException,Throwable{
		String className = jp.getTarget().getClass().getName();
		String methodName = jp.getSignature().getName();
		String beanName = getBeanName(className+"."+methodName);
		
		if(beanName==null){
			//默认的异常处理
			throw ex;
		}else{
			Object aspect = SpringContextHolder.getBean(beanName);
			if(aspect==null)
				throw new AopException("AOP配置错误！Bean"+beanName+"未配置！");
			if(!(aspect instanceof ExceptionAdvice)){
				throw ex;
			}
			ExceptionAdvice advice = (ExceptionAdvice)SpringContextHolder.getBean(beanName);
			advice.doThrowing(jp, ex);
		}		
	}

	/**
	 * @return the adviceMap
	 */
	public Map<String, String> getAdviceMap() {
		return adviceMap;
	}

	/**
	 * @param adviceMap the adviceMap to set
	 */
	public void setAdviceMap(Map<String, String> adviceMap) {
		this.adviceMap = adviceMap;
	}
}
