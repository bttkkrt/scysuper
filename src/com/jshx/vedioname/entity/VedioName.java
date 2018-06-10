package com.jshx.vedioname.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.jshx.core.base.entity.BaseModel;

/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "VEDIO_NAME")
public class VedioName extends BaseModel {
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 监控平台视频名称
	 */
	private String vedioname;

	/**
	 * 显示名称
	 */
	private String showname;

	/**
	 * 企业名称
	 */
	private String companyname;

	private String companyid;

	/**
	 * 备注
	 */
	private String remark;

	private String qylx;

	private Integer sort;

	private String dwdz1;

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

	@Column(name = "VEDIONAME")
	public String getVedioname() {
		return this.vedioname;
	}

	public void setVedioname(String vedioname) {
		this.vedioname = vedioname;
	}

	@Column(name = "SHOWNAME")
	public String getShowname() {
		return this.showname;
	}

	public void setShowname(String showname) {
		this.showname = showname;
	}

	@Column(name = "COMPANYNAME")
	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(name = "REMARK")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}

	@Column
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column
	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	@Column
	public String getDwdz1() {
		return dwdz1;
	}

	public void setDwdz1(String dwdz1) {
		this.dwdz1 = dwdz1;
	}
}
