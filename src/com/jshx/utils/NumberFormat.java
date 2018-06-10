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

public class NumberFormat
{
	static String[] numArray = { "零", "一", "二","三", "四", "五", "六", "七", "八", "九" ,"十","十一","十二","十三","十四","十五","十六","十七",
		"十八","十九", "二十","二十一", "二十二", "二十三", "二十四", "二十五", "二十六", "二十七" ,"二十八","二十九","三十"};

	public static void main(String[] args)
	{
		int num = 21;
		String numStr = foematInteger(num);
		System.out.println(numStr);
	}

	public static String foematInteger(int num)
	{
		String indexNum = "";
		if (num <= 30)
		{
			indexNum = numArray[num];
		}
		return indexNum;
	}

}
