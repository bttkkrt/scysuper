package com.jshx.lawsRegulations.entity;

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
@Table(name="LAWS_REGULATIONS")
public class LawsRegulations extends BaseModel
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
	 * 生效时间
	 */
	private Date sxtime;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 发布时间
	 */
	private Date fbtime;

	/**
	 * 发布人
	 */
	private String fabr;

	/**
	 * 状态
	 */
	private String fgstatus;

	/**
	 * 法规级别
	 */
	private String regutionsid;
	
	/**
	 * 法规内容
	 */
	private String content;

	/**
	 * 行业分类
	 */
	private String hyfl;

	@Column(name="HYFL")
	public String getHyfl()
	{
		return this.hyfl;
	}

	public void setHyfl(String hyfl)
	{
		this.hyfl = hyfl;
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

	
	@Column(name="SXTIME")
	public Date getSxtime()
	{
		return this.sxtime;
	}

	public void setSxtime(Date sxtime)
	{
		this.sxtime = sxtime;
	}

	@Column(name="TITLE")
	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	@Column(name="FBTIME")
	public Date getFbtime()
	{
		return this.fbtime;
	}

	public void setFbtime(Date fbtime)
	{
		this.fbtime = fbtime;
	}

	@Column(name="FABR")
	public String getFabr()
	{
		return this.fabr;
	}

	public void setFabr(String fabr)
	{
		this.fabr = fabr;
	}

	@Column(name="FGSTATUS")
	public String getFgstatus()
	{
		return this.fgstatus;
	}

	public void setFgstatus(String fgstatus)
	{
		this.fgstatus = fgstatus;
	}

	@Column(name="REGUTIONSID")
	public String getRegutionsid()
	{
		return this.regutionsid;
	}

	public void setRegutionsid(String regutionsid)
	{
		this.regutionsid = regutionsid;
	}
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
