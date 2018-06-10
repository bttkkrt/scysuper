package com.jshx.aqscData.entity;

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
@Table(name="AQSC_DATA")
public class AqscData extends BaseModel
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
	 * 序号 指定某条信息关联的三条数据顺序
	 */
	private String sort;
	/**
	 * 管理经费表id
	 */
	private String linkid;

	/**
	 * 安全生产类型
	 */
	private String aqscType;
	/**
	 * 数据字段data_1
	 * @return
	 */
	private String data_1;
	/**
	 * 数据字段data_2
	 * @return
	 */
	private String data_2;
	/**
	 * 数据字段data_3
	 * @return
	 */
	private String data_3;
	/**
	 * 数据字段data_4
	 * @return
	 */
	private String data_4;
	/**
	 * 数据字段data_5
	 * @return
	 */
	private String data_5;
	/**
	 * 数据字段data_6
	 * @return
	 */
	private String data_6;
	/**
	 * 数据字段data_7
	 * @return
	 */
	private String data_7;
	/**
	 * 数据字段data_8
	 * @return
	 */
	private String data_8;
	/**
	 * 数据字段data_9
	 * @return
	 */
	private String data_9;
	/**
	 * 数据字段data_10
	 * @return
	 */
	private String data_10;
	/**
	 * 数据字段data_11
	 * @return
	 */
	private String data_11;
	/**
	 * 数据字段data_12
	 * @return
	 */
	private String data_12;
	/**
	 * 数据字段data_13
	 * @return
	 */
	private String data_13;
	/**
	 * 数据字段data_14
	 * @return
	 */
	private String data_14;
	/**
	 * 数据字段data_15
	 * @return
	 */
	private String data_15;
	/**
	 * 数据字段data_16
	 * @return
	 */
	private String data_16;
	/**
	 * 数据字段data_17
	 * @return
	 */
	private String data_17;
	/**
	 * 数据字段data_18
	 * @return
	 */
	private String data_18;
	/**
	 * 数据字段data_19
	 * @return
	 */
	private String data_19;
	/**
	 * 数据字段data_20
	 * @return
	 */
	private String data_20;
	/**
	 * 数据字段data_21
	 * @return
	 */
	private String data_21;
	/**
	 * 数据字段data_22
	 * @return
	 */
	private String data_22;
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
	@Column
	public String getData_14() {
		return data_14;
	}

	public void setData_14(String data_14) {
		if(data_14 == null || "".equals(data_14))
		{
			this.data_14 = "0";
		}
		else
		{
			this.data_14 = data_14;
		}
	}
	@Column
	public String getData_15() {
		return data_15;
	}

	public void setData_15(String data_15) {
		if(data_15 == null || "".equals(data_15))
		{
			this.data_15 = "0";
		}
		else
		{
			this.data_15 = data_15;
		}
	}
	@Column
	public String getData_16() {
		return data_16;
	}

	public void setData_16(String data_16) {
		if(data_16 == null || "".equals(data_16))
		{
			this.data_16 = "0";
		}
		else
		{
			this.data_16 = data_16;
		}
	}
	@Column
	public String getData_17() {
		return data_17;
	}

	public void setData_17(String data_17) {
		if(data_17 == null || "".equals(data_17))
		{
			this.data_17 = "0";
		}
		else
		{
			this.data_17 = data_17;
		}
	}
	@Column
	public String getData_18() {
		return data_18;
	}

	public void setData_18(String data_18) {
		if(data_18 == null || "".equals(data_18))
		{
			this.data_18 = "0";
		}
		else
		{
			this.data_18 = data_18;
		}
	}
	@Column
	public String getData_19() {
		return data_19;
	}

	public void setData_19(String data_19) {
		if(data_19 == null || "".equals(data_19))
		{
			this.data_19 = "0";
		}
		else
		{
			this.data_19 = data_19;
		}
	}
	@Column
	public String getData_20() {
		return data_20;
	}

	public void setData_20(String data_20) {
		if(data_20 == null || "".equals(data_20))
		{
			this.data_20 = "0";
		}
		else
		{
			this.data_20 = data_20;
		}
	}
	@Column
	public String getData_21() {
		return data_21;
	}

	public void setData_21(String data_21) {
		if(data_21 == null || "".equals(data_21))
		{
			this.data_21 = "0";
		}
		else
		{
			this.data_21 = data_21;
		}
	}
	@Column
	public String getData_22() {
		return data_22;
	}

	public void setData_22(String data_22) {
		if(data_22 == null || "".equals(data_22))
		{
			this.data_22 = "0";
		}
		else
		{
			this.data_22 = data_22;
		}
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

	@Column(name="AQSC_TYPE")
	public String getAqscType()
	{
		return this.aqscType;
	}

	public void setAqscType(String aqscType)
	{
		this.aqscType = aqscType;
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
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
