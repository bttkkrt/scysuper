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
package com.jshx.checkResult.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryResult
{
	/**
	 * 栏目内容
	 */
	private String categoryContent;
	
	
	private List<ContentResult> contentList = new ArrayList<ContentResult>();
	
	/**
	 * 排序
	 */
	private String indexNum;

	public String getCategoryContent()
	{
		return categoryContent;
	}

	public void setCategoryContent(String categoryContent)
	{
		this.categoryContent = categoryContent;
	}


	public List<ContentResult> getContentList()
	{
		return contentList;
	}

	public void setContentList(List<ContentResult> contentList)
	{
		this.contentList = contentList;
	}

	public String getIndexNum()
	{
		return indexNum;
	}

	public void setIndexNum(String indexNum)
	{
		this.indexNum = indexNum;
	}
	
}
