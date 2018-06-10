package com.jshx.qzzxsqs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="ENF_APP")
public class EnfApp extends BaseModel
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
	 * 法院名称
	 */
	private String courtName;
	
	private String contact;
	
	private String tele;
	
	
	/**
	 * 有关材料
	 */
	private String ygcl;

	
	public EnfApp(){
	}
	
	public EnfApp(String id){
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

	@Column(name="COURT_NAME")
	public String getCourtName()
	{
		return this.courtName;
	}

	public void setCourtName(String courtName)
	{
		this.courtName = courtName;
	}
	@Column(name="CONTACT")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name="TELE")
	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}
	@Column(name="YGCL")
	public String getYgcl() {
		return ygcl;
	}

	public void setYgcl(String ygcl) {
		this.ygcl = ygcl;
	}

}
