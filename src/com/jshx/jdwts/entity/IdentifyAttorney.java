package com.jshx.jdwts.entity;

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
@Table(name="IDENTIFY_ATTORNEY")
public class IdentifyAttorney extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;

	/**
	 * 鉴定要求
	 */
	private String inentifyRequire;

	/**
	 * 提交时间
	 */
	private Date submitTime;
	

	private String jdjgName;
	
	
	public IdentifyAttorney(){
	}
	
	public IdentifyAttorney(String id, String relatedId){
this.id = id;

this.relatedId = relatedId;
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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}

	@Column(name="INENTIFY_REQUIRE")
	public String getInentifyRequire()
	{
		return this.inentifyRequire;
	}

	public void setInentifyRequire(String inentifyRequire)
	{
		this.inentifyRequire = inentifyRequire;
	}

	@Column(name="SUBMIT_TIME")
	public Date getSubmitTime()
	{
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime)
	{
		this.submitTime = submitTime;
	}
	@Column(name="JDJG_NAME")
	public String getJdjgName() {
		return jdjgName;
	}

	public void setJdjgName(String jdjgName) {
		this.jdjgName = jdjgName;
	}

}
