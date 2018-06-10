package com.jshx.qyzcyhglb.entity;

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
@Table(name="QYZCYHGLB")
public class Qyzcyhglb extends BaseModel
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
	 * 隐患中类
	 */
	private String yhzl;

	/**
	 * 被检查部位
	 */
	private String jcbw;

	/**
	 * 隐患大类
	 */
	private String yhdl;

	/**
	 * 整改责任部门
	 */
	private String zgzrbm;

	/**
	 * 整改责任人
	 */
	private String zgzrr;

	/**
	 * 隐患关联id
	 */
	private String yhid;
	
	private String type;

	/**
	 * 隐患整改完成时间
	 */
	private String yhzgwcsj;
	
	@Column
	public String getYhzgwcsj() {
		return yhzgwcsj;
	}

	public void setYhzgwcsj(String yhzgwcsj) {
		this.yhzgwcsj = yhzgwcsj;
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

	
	@Column(name="YHZL")
	public String getYhzl()
	{
		return this.yhzl;
	}

	public void setYhzl(String yhzl)
	{
		this.yhzl = yhzl;
	}

	@Column(name="JCBW")
	public String getJcbw()
	{
		return this.jcbw;
	}

	public void setJcbw(String jcbw)
	{
		this.jcbw = jcbw;
	}

	@Column(name="YHDL")
	public String getYhdl()
	{
		return this.yhdl;
	}

	public void setYhdl(String yhdl)
	{
		this.yhdl = yhdl;
	}

	@Column(name="ZGZRBM")
	public String getZgzrbm()
	{
		return this.zgzrbm;
	}

	public void setZgzrbm(String zgzrbm)
	{
		this.zgzrbm = zgzrbm;
	}

	@Column(name="ZGZRR")
	public String getZgzrr()
	{
		return this.zgzrr;
	}

	public void setZgzrr(String zgzrr)
	{
		this.zgzrr = zgzrr;
	}

	@Column(name="YHID")
	public String getYhid()
	{
		return this.yhid;
	}

	public void setYhid(String yhid)
	{
		this.yhid = yhid;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
