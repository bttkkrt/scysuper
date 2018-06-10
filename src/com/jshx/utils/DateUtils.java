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

/**
 *	日期处理方法类 
 *	GY-UPDATE 2015-02-10
 */
public class DateUtils
{
	/**
	 *	 String 转 Date
	 */
	public static Date  StringToDate(String dateStr,SimpleDateFormat sdf)
	{
		try  
		{  
		    Date date = sdf.parse(dateStr);  
		    return date;
		}  
		catch (Exception e)  
		{  
			e.printStackTrace();
		    return null;
		}  
	}
	
	public static String DateToString(Date date,SimpleDateFormat sdf)
	{
		String strDate = "";
		try
		{
			if (null != date)
			{
				strDate=sdf.format(date);
			}
			return strDate;
		}
		catch (Exception e)
		{	
			e.printStackTrace();
			return "";
		}
	}
}
