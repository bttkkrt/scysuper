package com.jshx.aqscwyhywc.entity;

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
@Table(name="AQSCWYHYWC")
public class Aqscwyhywc extends BaseModel
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
	 * 召开安委会全体成员会议完成次数
	 */
	private String zkwccs;

	/**
	 * 主要领导带队安全检查完成次数
	 */
	private String zywccs;

	/**
	 * 分管领导带队安全检查完成次数
	 */
	private String fgwccs;

	/**
	 * 其他领导带队安全检查完成次数
	 */
	private String qtwccs;

	/**
	 * 工委会研究安全生产完成次数
	 */
	private String gwwccs;

	/**
	 * 关联ID
	 */
	private String glId;
	
	
	/**
	 * 主要领导批示安全生产工作次数
	 */
	private String zyaqsccs;

	
	public Aqscwyhywc(){
	}
	
	public Aqscwyhywc(String id, Date monthTime){
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

	@Column(name="ZKWCCS")
	public String getZkwccs()
	{
		return this.zkwccs;
	}

	public void setZkwccs(String zkwccs)
	{
		this.zkwccs = zkwccs;
	}

	@Column(name="ZYWCCS")
	public String getZywccs()
	{
		return this.zywccs;
	}

	public void setZywccs(String zywccs)
	{
		this.zywccs = zywccs;
	}

	@Column(name="FGWCCS")
	public String getFgwccs()
	{
		return this.fgwccs;
	}

	public void setFgwccs(String fgwccs)
	{
		this.fgwccs = fgwccs;
	}

	@Column(name="QTWCCS")
	public String getQtwccs()
	{
		return this.qtwccs;
	}

	public void setQtwccs(String qtwccs)
	{
		this.qtwccs = qtwccs;
	}

	@Column(name="GWWCCS")
	public String getGwwccs()
	{
		return this.gwwccs;
	}

	public void setGwwccs(String gwwccs)
	{
		this.gwwccs = gwwccs;
	}
	@Column(name="GLID")
	public String getGlId() {
		return glId;
	}

	public void setGlId(String glId) {
		this.glId = glId;
	}

	@Column(name="ZYAQSCCS")
	public String getZyaqsccs()
	{
		return this.zyaqsccs;
	}

	public void setZyaqsccs(String zyaqsccs)
	{
		this.zyaqsccs = zyaqsccs;
	}
	

}
