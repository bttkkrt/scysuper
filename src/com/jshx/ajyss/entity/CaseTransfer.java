package com.jshx.ajyss.entity;

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
@Table(name="CASE_TRANSFER")
public class CaseTransfer extends BaseModel
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
	 * 规定
	 */
	private String provision;
	
	private String proName;
	
	
	private String ygcl;

	
	public CaseTransfer(){
	}
	
	public CaseTransfer(String id){
this.id = id;
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

	@Column(name="PROVISION")
	public String getProvision()
	{
		return this.provision;
	}

	public void setProvision(String provision)
	{
		this.provision = provision;
	}
	@Column(name="PRO_NAME")
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	@Column(name="YGCL")
	public String getYgcl() {
		return ygcl;
	}

	public void setYgcl(String ygcl) {
		this.ygcl = ygcl;
	}

}
