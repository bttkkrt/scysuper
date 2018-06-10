package com.jshx.whysjbqk.entity;

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
@Table(name="whysjbqkglb")
public class Whysjbqkglb extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;
	
	private String fl;	//分类
	
	private String code; //代码
	
	private String qtwhys;	//其他危害因素
	
	private String whysjbqkid;	//危害因素关联id

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
	public String getFl() {
		return fl;
	}

	public void setFl(String fl) {
		this.fl = fl;
	}
	@Column
	public String getQtwhys() {
		return qtwhys;
	}

	public void setQtwhys(String qtwhys) {
		this.qtwhys = qtwhys;
	}
	@Column
	public String getWhysjbqkid() {
		return whysjbqkid;
	}

	public void setWhysjbqkid(String whysjbqkid) {
		this.whysjbqkid = whysjbqkid;
	}
	@Column
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
