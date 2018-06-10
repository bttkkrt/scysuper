package com.jshx.ajxx.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.photoPic.entity.PhotoPic;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@Entity
@Table(name="CASE_CL")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CaseCl extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 案件id
	 */
	private String caseId;
	
	/**
	 * 图片关联id
	 */
	private String linkId;
	
	/**
	 * 证据类型 1现场图片 2罚没款收据回执
	 */
	private String zjType;
	
	/**
	 * 拍摄时间
	 */
	private Date picTime;
	
	/**
	 * 拍摄地点
	 */
	private String picAdd;
	
	/**
	 * 照片内容
	 */
	private String picContent;
	
	private List<PhotoPic> picList;

	@Column
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	@Column
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@Column
	public String getZjType() {
		return zjType;
	}

	public void setZjType(String zjType) {
		this.zjType = zjType;
	}

	@Column
	public Date getPicTime() {
		return picTime;
	}

	public void setPicTime(Date picTime) {
		this.picTime = picTime;
	}

	@Column
	public String getPicAdd() {
		return picAdd;
	}

	public void setPicAdd(String picAdd) {
		this.picAdd = picAdd;
	}

	@Column
	public String getPicContent() {
		return picContent;
	}

	public void setPicContent(String picContent) {
		this.picContent = picContent;
	}
	@Transient
	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
	
	
}
