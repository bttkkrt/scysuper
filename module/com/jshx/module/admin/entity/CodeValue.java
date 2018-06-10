/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-1-18         Huairu.Li           create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.module.admin.extend.ICodeValueExtendInfo;

/**  
 * 一维代码项实体类
 * @author   Huairu.Li
 * @version 创建时间：2011-1-18 下午04:23:57  
 * 
 */
@Table(name="CODEVALUE")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CodeValue extends BaseModel{

	private static final long serialVersionUID = 2L;

	private String itemValue;

	private String itemText;

	private Integer sortSQ;
	
	private Integer delFlag;

	private String codeId;
	
	private Integer childNum;
	
	private String comment;
	
	/** 一维代码编号，用于多级的一维代码 */
	private String itemCode;
	
	/** 上层一维代码 */
	private CodeValue parentItem;
	
	/** 一维代码值的扩展信息	 */
	private ICodeValueExtendInfo extendInfo;

	/**  
	 * 获取一维代码项值
	 */
	@Column(name = "ITEM_VALUE", length = 20, nullable = false)
	public String getItemValue() {
		return itemValue;
	}

	/**  
	 * 设置一维代码项值
	 */
	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	/**  
	 * 获取一维代码项显示文本
	 */
	@Column(name = "ITEM_TEXT", length = 100, nullable = false)
	public String getItemText() {
		return itemText;
	}

	/**  
	 * 设置一维代码项显示文本
	 */
	public void setItemText(String itemText) {
		this.itemText = itemText;
	}

	/**  
	 * 获取同级排序
	 */
	@Column(name = "SORT_SQ", length = 3, nullable = false)
	public Integer getSortSQ() {
		return sortSQ;
	}

	/**  
	 * 设置同级排序
	 */
	public void setSortSQ(Integer sortSQ) {
		this.sortSQ = sortSQ;
	}

	/**  
	 * 获取删除标志位
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	public Integer getDelFlag() {
		return delFlag;
	}

	/**  
	 * 设置删除标志位
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	/**  
	 * 获取一维代码ID
	 */
	@Column(name = "CODE_ID", length = 50, nullable = false)
	public String getCodeId() {
		return codeId;
	}

	/**  
	 * 设置一维代码ID
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	/**  
	 * 获取代码项编号
	 */
	@Column(name = "ITEM_CODE", length = 100, nullable = false)
	public String getItemCode() {
		return itemCode;
	}

	/**  
	 * 设置代码项编号
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**  
	 * 获取父层代码项ID
	 */
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=CodeValue.class)
	@JoinColumn(name = "PARRENT_ITEM_ID")
	public CodeValue getParentItem() {
		return parentItem;
	}

	/**  
	 * 设置父层代码项ID
	 */
	public void setParentItem(CodeValue parentItem) {
		this.parentItem = parentItem;
	}

	/**  
	 * 获取子代码项个数
	 */
	@Column(name = "CHILD_NUM", length = 5)
	public Integer getChildNum() {
		return childNum;
	}

	/**  
	 * 设置子代码项个数
	 */
	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}
	
	/**  
	 * 获取备注
	 */
	@Column(name = "COMMENTS", length = 255)
	public String getComment() {
		return comment;
	}
	/**  
	 * 设置备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/** 获取一维代码值的扩展信息	 */
	@Transient
	public ICodeValueExtendInfo getExtendInfo() {
		return extendInfo;
	}
	/** 设置一维代码值的扩展信息	 */

	public void setExtendInfo(ICodeValueExtendInfo extendInfo) {
		this.extendInfo = extendInfo;
	}
}
