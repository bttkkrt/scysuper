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
@Table(name="SSJJC_GZ")
public class SsjjcGz extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	private String ifWg;
	
	private String ryids;
	
	private String ldids;
	
	public SsjjcGz(){
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
	@Column
	public String getIfWg() {
		return ifWg;
	}

	public void setIfWg(String ifWg) {
		this.ifWg = ifWg;
	}

	@Column
	public String getRyids() {
		return ryids;
	}

	public void setRyids(String ryids) {
		this.ryids = ryids;
	}
	@Column
	public String getLdids() {
		return ldids;
	}

	public void setLdids(String ldids) {
		this.ldids = ldids;
	}

}
