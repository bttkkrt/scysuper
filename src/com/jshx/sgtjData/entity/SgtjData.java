package com.jshx.sgtjData.entity;

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
@Table(name="SGTJ_DATA")
public class SgtjData extends BaseModel
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
	 * 事故统计id
	 */
	private String linkid;

	/**
	 * 统计类型
	 */
	private String sgtjlx;
	/**
	 * 数据 data_1
	 * @return
	 */
	private String data_1;
	/**
	 * 数据 data_2
	 * @return
	 */
	private String data_2;
	/**
	 * 数据 data_3
	 * @return
	 */
	private String data_3;
	/**
	 * 数据 data_4
	 * @return
	 */
	private String data_4;
	/**
	 * 数据 data_5
	 * @return
	 */
	private String data_5;
	/**
	 * 数据 data_6
	 * @return
	 */
	private String data_6;
	/**
	 * 数据 data_7
	 * @return
	 */
	private String data_7;
	/**
	 * 数据 data_8
	 * @return
	 */
	private String data_8;
	/**
	 * 数据 data_9
	 * @return
	 */
	private String data_9;
	/**
	 * 数据 data_10
	 * @return
	 */
	private String data_10;
	/**
	 * 数据 data_11
	 * @return
	 */
	private String data_11;
	
	/**
	 * 数据 data_12
	 * @return
	 */
	private String data_12;
	/**
	 * 数据 data_13
	 * @return
	 */
	private String data_13;
	/**
	 * 序号
	 * @return
	 */
	private String sort;
	
	@Column
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
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

	
	@Column(name="LINKID")
	public String getLinkid()
	{
		return this.linkid;
	}

	public void setLinkid(String linkid)
	{
		this.linkid = linkid;
	}

	@Column(name="SGTJLX")
	public String getSgtjlx()
	{
		return this.sgtjlx;
	}

	public void setSgtjlx(String sgtjlx)
	{
		this.sgtjlx = sgtjlx;
	}
	@Column
	public String getData_1() {
		return data_1;
	}

	public void setData_1(String data_1) {
		if(data_1 == null || "".equals(data_1))
		{
			this.data_1 = "0";
		}
		else
		{
			this.data_1 = data_1;
		}
	}
	@Column
	public String getData_2() {
		return data_2;
	}

	public void setData_2(String data_2) {
		if(data_2 == null || "".equals(data_2))
		{
			this.data_2 = "0";
		}
		else
		{
			this.data_2 = data_2;
		}
	}
	@Column
	public String getData_3() {
		return data_3;
	}

	public void setData_3(String data_3) {
		if(data_3 == null || "".equals(data_3))
		{
			this.data_3 = "0";
		}
		else
		{
			this.data_3 = data_3;
		}
	}
	@Column
	public String getData_4() {
		return data_4;
	}

	public void setData_4(String data_4) {
		if(data_4 == null || "".equals(data_4))
		{
			this.data_4 = "0";
		}
		else
		{
			this.data_4 = data_4;
		}
	}
	@Column
	public String getData_5() {
		return data_5;
	}

	public void setData_5(String data_5) {
		if(data_5 == null || "".equals(data_5))
		{
			this.data_5 = "0";
		}
		else
		{
			this.data_5 = data_5;
		}
	}
	@Column
	public String getData_6() {
		return data_6;
	}

	public void setData_6(String data_6) {
		if(data_6 == null || "".equals(data_6))
		{
			this.data_6 = "0";
		}
		else
		{
			this.data_6 = data_6;
		}
	}
	@Column
	public String getData_7() {
		return data_7;
	}

	public void setData_7(String data_7) {
		if(data_7 == null || "".equals(data_7))
		{
			this.data_7 = "0";
		}
		else
		{
			this.data_7 = data_7;
		}
	}
	@Column
	public String getData_8() {
		return data_8;
	}

	public void setData_8(String data_8) {
		if(data_8 == null || "".equals(data_8))
		{
			this.data_8 = "0";
		}
		else
		{
			this.data_8 = data_8;
		}
	}
	@Column
	public String getData_9() {
		return data_9;
	}

	public void setData_9(String data_9) {
		if(data_9 == null || "".equals(data_9))
		{
			this.data_9 = "0";
		}
		else
		{
			this.data_9 = data_9;
		}
	}
	@Column
	public String getData_10() {
		return data_10;
	}

	public void setData_10(String data_10) {
		if(data_10 == null || "".equals(data_10))
		{
			this.data_10 = "0";
		}
		else
		{
			this.data_10 = data_10;
		}
	}
	@Column
	public String getData_11() {
		return data_11;
	}

	public void setData_11(String data_11) {
		if(data_11 == null || "".equals(data_11))
		{
			this.data_11 = "0";
		}
		else
		{
			this.data_11 = data_11;
		}
	}
	@Column
	public String getData_12() {
		return data_12;
	}

	public void setData_12(String data_12) {
		if(data_12 == null || "".equals(data_12))
		{
			this.data_12 = "0";
		}
		else
		{
			this.data_12 = data_12;
		}
	}
	@Column
	public String getData_13() {
		return data_13;
	}

	public void setData_13(String data_13) {
		if(data_13 == null || "".equals(data_13))
		{
			this.data_13 = "0";
		}
		else
		{
			this.data_13 = data_13;
		}
	}

}
