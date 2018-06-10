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

public class ContentResult
{

	/**
	 * 栏目内容
	 */
	private String content;
	
	/**
	 * 检查结果
	 */
	private String checkResult;

	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 排序
	 */
	private String indexNum;
	
	private String id;


	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public String getCheckResult()
	{
		return checkResult;
	}

	public void setCheckResult(String checkResult)
	{
		this.checkResult = checkResult;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	public String getIndexNum()
	{
		return indexNum;
	}

	public void setIndexNum(String indexNum)
	{
		this.indexNum = indexNum;
	}

	/**  
	 * 获取id  
	 * @return id id  
	 */
	public String getId() {
		return id;
	}

	/**  
	 * 设置id  
	 * @param id id  
	 */
	public void setId(String id) {
		this.id = id;
	}
	
}
