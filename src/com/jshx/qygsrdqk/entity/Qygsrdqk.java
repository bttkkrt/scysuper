package com.jshx.qygsrdqk.entity;

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
@Table(name="QYGSRDQK")
public class Qygsrdqk extends BaseModel
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
	 * 月份
	 */
	private Date monthTime;

	/**
	 * 14.1
	 */
	private String btrd1;

	/**
	 * 工伤申报总数
	 */
	private String gssbzs;

	/**
	 * 其中个人申报
	 */
	private String qzgrsb;

	/**
	 * 14.2
	 */
	private String btrd2;

	/**
	 * 14.3
	 */
	private String btrd3;

	/**
	 * 14.4
	 */
	private String btrd4;

	/**
	 * 14.5
	 */
	private String btrd5;

	/**
	 * 14.6
	 */
	private String btrd6;

	/**
	 * 15.1
	 */
	private String btrd7;

	/**
	 * 15.2
	 */
	private String btrd8;

	/**
	 * 15.3
	 */
	private String btrd9;

	/**
	 * 轻伤
	 */
	private String qdss;

	/**
	 * 重伤
	 */
	private String zdss;

	/**
	 * 工亡
	 */
	private String gzsw;

	/**
	 * 生产
	 */
	private String sclb;

	/**
	 * 交通
	 */
	private String jtlb;

	/**
	 * 其他
	 */
	private String qtlb;

	
	public Qygsrdqk(){
	}
	
	public Qygsrdqk(String id, Date monthTime){
this.id = id;

this.monthTime = monthTime;
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

	
	@Column(name="MONTH_TIME")
	public Date getMonthTime()
	{
		return this.monthTime;
	}

	public void setMonthTime(Date monthTime)
	{
		this.monthTime = monthTime;
	}

	@Column(name="BTRD1")
	public String getBtrd1()
	{
		return this.btrd1;
	}

	public void setBtrd1(String btrd1)
	{
		this.btrd1 = btrd1;
	}

	@Column(name="GSSBZS")
	public String getGssbzs()
	{
		return this.gssbzs;
	}

	public void setGssbzs(String gssbzs)
	{
		this.gssbzs = gssbzs;
	}

	@Column(name="QZGRSB")
	public String getQzgrsb()
	{
		return this.qzgrsb;
	}

	public void setQzgrsb(String qzgrsb)
	{
		this.qzgrsb = qzgrsb;
	}

	@Column(name="BTRD2")
	public String getBtrd2()
	{
		return this.btrd2;
	}

	public void setBtrd2(String btrd2)
	{
		this.btrd2 = btrd2;
	}

	@Column(name="BTRD3")
	public String getBtrd3()
	{
		return this.btrd3;
	}

	public void setBtrd3(String btrd3)
	{
		this.btrd3 = btrd3;
	}

	@Column(name="BTRD4")
	public String getBtrd4()
	{
		return this.btrd4;
	}

	public void setBtrd4(String btrd4)
	{
		this.btrd4 = btrd4;
	}

	@Column(name="BTRD5")
	public String getBtrd5()
	{
		return this.btrd5;
	}

	public void setBtrd5(String btrd5)
	{
		this.btrd5 = btrd5;
	}

	@Column(name="BTRD6")
	public String getBtrd6()
	{
		return this.btrd6;
	}

	public void setBtrd6(String btrd6)
	{
		this.btrd6 = btrd6;
	}

	@Column(name="BTRD7")
	public String getBtrd7()
	{
		return this.btrd7;
	}

	public void setBtrd7(String btrd7)
	{
		this.btrd7 = btrd7;
	}

	@Column(name="BTRD8")
	public String getBtrd8()
	{
		return this.btrd8;
	}

	public void setBtrd8(String btrd8)
	{
		this.btrd8 = btrd8;
	}

	@Column(name="BTRD9")
	public String getBtrd9()
	{
		return this.btrd9;
	}

	public void setBtrd9(String btrd9)
	{
		this.btrd9 = btrd9;
	}

	@Column(name="QDSS")
	public String getQdss()
	{
		return this.qdss;
	}

	public void setQdss(String qdss)
	{
		this.qdss = qdss;
	}

	@Column(name="ZDSS")
	public String getZdss()
	{
		return this.zdss;
	}

	public void setZdss(String zdss)
	{
		this.zdss = zdss;
	}

	@Column(name="GZSW")
	public String getGzsw()
	{
		return this.gzsw;
	}

	public void setGzsw(String gzsw)
	{
		this.gzsw = gzsw;
	}

	@Column(name="SCLB")
	public String getSclb()
	{
		return this.sclb;
	}

	public void setSclb(String sclb)
	{
		this.sclb = sclb;
	}

	@Column(name="JTLB")
	public String getJtlb()
	{
		return this.jtlb;
	}

	public void setJtlb(String jtlb)
	{
		this.jtlb = jtlb;
	}

	@Column(name="QTLB")
	public String getQtlb()
	{
		return this.qtlb;
	}

	public void setQtlb(String qtlb)
	{
		this.qtlb = qtlb;
	}

}
