package com.jshx.mobile.customer.entity;

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
@Table(name="CUSTOMER")
public class Customer extends BaseModel
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
	 * 客户规模
	 */
	private Long customerscale;

	/**
	 * 客户名称
	 */
	private String customername;

	/**
	 * 客户类型
	 */
	private String customertype;


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

	
	@Column(name="CUSTOMERSCALE")
	public Long getCustomerscale()
	{
		return this.customerscale;
	}

	public void setCustomerscale(Long customerscale)
	{
		this.customerscale = customerscale;
	}

	@Column(name="CUSTOMERNAME")
	public String getCustomername()
	{
		return this.customername;
	}

	public void setCustomername(String customername)
	{
		this.customername = customername;
	}

	@Column(name="CUSTOMERTYPE")
	public String getCustomertype()
	{
		return this.customertype;
	}

	public void setCustomertype(String customertype)
	{
		this.customertype = customertype;
	}

}
