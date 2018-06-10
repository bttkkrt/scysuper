package com.wzxx.bgtxx.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="EXP_TAB_DET")
public class ExpTabDet extends BaseModel
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
	 * 描述
	 */
	private String descriptor;

	/**
	 * 发布时间
	 */
	private Date publicDate;

	/**
	 * 关联id
	 */
	private String linkId;

	/**
	 * 标题关联id
	 */
	private String titleId;

	/**
	 * 第一标题
	 */
	private String firTitle;

	@Column
	public String getFirTitle() {
		return firTitle;
	}

	public void setFirTitle(String firTitle) {
		this.firTitle = firTitle;
	}
	
	public ExpTabDet(){
	}
	
	public ExpTabDet(String id, String descriptor, Date publicDate){
this.id = id;

this.descriptor = descriptor;

this.publicDate = publicDate;
}


	@Column
	public String getDeptId()
	{
		return deptId;
	}

	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}

	@Column
	public Integer getDelFlag()
	{
		return delFlag;
	}

	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	
	@Column(name="DESCRIPTOR")
	public String getDescriptor()
	{
		return this.descriptor;
	}

	public void setDescriptor(String descriptor)
	{
		this.descriptor = descriptor;
	}

	@Column(name="PUBLIC_DATE")
	public Date getPublicDate()
	{
		return this.publicDate;
	}

	public void setPublicDate(Date publicDate)
	{
		this.publicDate = publicDate;
	}

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

	@Column(name="TITLE_ID")
	public String getTitleId()
	{
		return this.titleId;
	}

	public void setTitleId(String titleId)
	{
		this.titleId = titleId;
	}

}
