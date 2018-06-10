/**************************************************************************/
/*                                                                        */
/* Copyright (c) 2012-2013 Jiangsu Hongxin System Integration Co., Ltd.   */
/*                                                                        */
/* PROPRIETARY RIGHTS of Jiangsu Hongxin are involved in the 　　　　　　 */
/* subject matter of this material.  All manufacturing, reproduction, use,*/
/* and sales rights pertaining to this subject matter are governed by the */
/* license agreement. The recipient of this software implicitly accepts   */ 
/* the terms of the license.                                              */
/* 本软件文档资料是江苏鸿信公司的资产,任何人士阅读和使用本资料必须获得    */
/* 相应的书面授权,承担保密责任和接受相应的法律约束.                       */
/*                                                                        */
/**************************************************************************/
package com.jshx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.core.utils.SpringContextHolder;

/**
 * 安全检查生成整改号
 * GY-UPDATE 2015-02-27
 */
public class NumberGenerater
{
	private static CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	
	private static final String SERIAL_NUMBER = "0000";		//流水号格式
	
	private  static NumberGenerater numberGenerater	=	null;
	
	private NumberGenerater(){
		
	}
	
	/**
	 * 取得NumberGenerater的单例实现
	 * @return
	 */
	public static NumberGenerater getInstance(){
		if(numberGenerater==null){
			synchronized(NumberGenerater.class){
				if(numberGenerater==null){
					numberGenerater = new NumberGenerater();
				}
			};
		}
		return numberGenerater;
	}
	
	public synchronized String geneterNextNumber()
	{
		//取得序列号格式化字符串
		java.text.DecimalFormat df = new java.text.DecimalFormat(SERIAL_NUMBER);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		String nowYear = formatter.format(new Date());
		//根据年份取得当前最大序列号值
		String maxNum = checkBasicService.getMaxNumByYear(nowYear);
		if(maxNum.length() == 5)
		{
			maxNum = maxNum.substring(1);
		}
		int maxNumInt = Integer.parseInt(maxNum);
		int currentNumInt = maxNumInt + 1;
		String currentNum = df.format(currentNumInt);
		return currentNum;
	}
	
}
