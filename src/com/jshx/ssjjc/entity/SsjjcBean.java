package com.jshx.ssjjc.entity;

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
@Table(name="SSJJC_BEAN")
public class SsjjcBean extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	private String jcry;
	
	private String jcsj;
	
	private String jcqy;
	
	private String cqid;
	
	private String jcxlx;
	
	private String jcryid;
	
	private String jcqyid;
	

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
	@Column
	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}
	@Column
	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	@Column
	public String getJcqy() {
		return jcqy;
	}

	public void setJcqy(String jcqy) {
		this.jcqy = jcqy;
	}
	@Column
	public String getCqid() {
		return cqid;
	}

	public void setCqid(String cqid) {
		this.cqid = cqid;
	}
	@Column
	public String getJcxlx() {
		return jcxlx;
	}

	public void setJcxlx(String jcxlx) {
		this.jcxlx = jcxlx;
	}
	@Column
	public String getJcryid() {
		return jcryid;
	}

	public void setJcryid(String jcryid) {
		this.jcryid = jcryid;
	}
	@Column
	public String getJcqyid() {
		return jcqyid;
	}

	public void setJcqyid(String jcqyid) {
		this.jcqyid = jcqyid;
	}

}
