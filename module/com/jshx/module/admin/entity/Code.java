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
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;

/**  
 * 一维代码实体类
 */
@Table(name="CODE")
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Code extends BaseModel{

	private static final long serialVersionUID = 1L;

	private String codeName;
	
	private Integer sortSQ;
	
	private Integer delFlag;
	
	private String comments;

	/**  
	 * 获取代码名称
	 */
	@Column(name = "CODE_NAME", length = 20, nullable = false)
	public String getCodeName() {
		return codeName;
	}

	/**  
	 * 设置代码名称
	 */
	public void setCodeName(String codeName) {
		this.codeName = codeName;
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
	 * 获取删除状态
	 */
	@Column(name = "DEL_FLAG", length = 1, nullable = false)
	public Integer getDelFlag() {
		return delFlag;
	}

	/**  
	 * 设置删除状态
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	/**  
	 * 获取备注 
	 */
	@Column(name = "COMMENTS", length = 255)
	public String getComments() {
		return comments;
	}

	/**  
	 * 设置备注 
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
}