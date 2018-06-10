package com.jshx.aqscjchzxzz.entity;

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
@Table(name="AQSCJCHZXZZ")
public class Aqscjchzxzz extends BaseModel
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
	 * 区域
	 */
	private String areaName;

	/**
	 * 监管企业数
	 */
	private String jgqys;

	/**
	 * 检查企业目标
	 */
	private String jcqymb;

	/**
	 * 检查企业数
	 */
	private String jcqys;

	/**
	 * 一般隐患排查数
	 */
	private String ybyhpcs;

	/**
	 * 一般隐患已整治数
	 */
	private String ybyhyzzs;

	/**
	 * 一般隐患整改率
	 */
	private String ybyhzgl;

	/**
	 * 重大隐患排查数
	 */
	private String zdyhpcs;

	/**
	 * 重大隐患已整治数
	 */
	private String zdyhyzzs;

	/**
	 * 重大隐患整改率
	 */
	private String zdyhzgl;

	/**
	 * 打击非法违法行为
	 */
	private String djffwfxw;

	/**
	 * 整治违规违法行为
	 */
	private String zzwgwfxw;

	/**
	 * 处罚总起数(违法)
	 */
	private String cfzqs;

	/**
	 * 已结案事故(违法)
	 */
	private String yjasg;

	/**
	 * 处罚金额（违法）
	 */
	private String cfje;
	
	/**
	 * 处罚总起数(事故)
	 */
	private String cfzqssg;

	/**
	 * 已结案事故(事故)
	 */
	private String yjasgsg;

	/**
	 * 处罚金额（事故）
	 */
	private String cfjesg;

	/**
	 * 追究刑事责任
	 */
	private String zjxszr;

	private String areaId;
	public Aqscjchzxzz(){
	}
	
	public Aqscjchzxzz(String id, Date monthTime, String areaName){
this.id = id;

this.monthTime = monthTime;

this.areaName = areaName;
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

	@Column(name="AREA_NAME")
	public String getAreaName()
	{
		return this.areaName;
	}

	public void setAreaName(String areaName)
	{
		this.areaName = areaName;
	}

	@Column(name="JGQYS")
	public String getJgqys()
	{
		return this.jgqys;
	}

	public void setJgqys(String jgqys)
	{
		this.jgqys = jgqys;
	}

	@Column(name="JCQYMB")
	public String getJcqymb()
	{
		return this.jcqymb;
	}

	public void setJcqymb(String jcqymb)
	{
		this.jcqymb = jcqymb;
	}

	@Column(name="JCQYS")
	public String getJcqys()
	{
		return this.jcqys;
	}

	public void setJcqys(String jcqys)
	{
		this.jcqys = jcqys;
	}

	@Column(name="YBYHPCS")
	public String getYbyhpcs()
	{
		return this.ybyhpcs;
	}

	public void setYbyhpcs(String ybyhpcs)
	{
		this.ybyhpcs = ybyhpcs;
	}

	@Column(name="YBYHYZZS")
	public String getYbyhyzzs()
	{
		return this.ybyhyzzs;
	}

	public void setYbyhyzzs(String ybyhyzzs)
	{
		this.ybyhyzzs = ybyhyzzs;
	}

	@Column(name="YBYHZGL")
	public String getYbyhzgl()
	{
		return this.ybyhzgl;
	}

	public void setYbyhzgl(String ybyhzgl)
	{
		this.ybyhzgl = ybyhzgl;
	}

	@Column(name="ZDYHPCS")
	public String getZdyhpcs()
	{
		return this.zdyhpcs;
	}

	public void setZdyhpcs(String zdyhpcs)
	{
		this.zdyhpcs = zdyhpcs;
	}

	@Column(name="ZDYHYZZS")
	public String getZdyhyzzs()
	{
		return this.zdyhyzzs;
	}

	public void setZdyhyzzs(String zdyhyzzs)
	{
		this.zdyhyzzs = zdyhyzzs;
	}

	@Column(name="ZDYHZGL")
	public String getZdyhzgl()
	{
		return this.zdyhzgl;
	}

	public void setZdyhzgl(String zdyhzgl)
	{
		this.zdyhzgl = zdyhzgl;
	}

	@Column(name="DJFFWFXW")
	public String getDjffwfxw()
	{
		return this.djffwfxw;
	}

	public void setDjffwfxw(String djffwfxw)
	{
		this.djffwfxw = djffwfxw;
	}

	@Column(name="ZZWGWFXW")
	public String getZzwgwfxw()
	{
		return this.zzwgwfxw;
	}

	public void setZzwgwfxw(String zzwgwfxw)
	{
		this.zzwgwfxw = zzwgwfxw;
	}

	@Column(name="CFZQS")
	public String getCfzqs()
	{
		return this.cfzqs;
	}

	public void setCfzqs(String cfzqs)
	{
		this.cfzqs = cfzqs;
	}

	@Column(name="YJASG")
	public String getYjasg()
	{
		return this.yjasg;
	}

	public void setYjasg(String yjasg)
	{
		this.yjasg = yjasg;
	}

	@Column(name="CFJE")
	public String getCfje()
	{
		return this.cfje;
	}

	public void setCfje(String cfje)
	{
		this.cfje = cfje;
	}

	@Column(name="ZJXSZR")
	public String getZjxszr()
	{
		return this.zjxszr;
	}

	public void setZjxszr(String zjxszr)
	{
		this.zjxszr = zjxszr;
	}
	@Column(name="AREA_ID")
	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	@Column(name="CFZQSSG")
	public String getCfzqssg() {
		return cfzqssg;
	}

	public void setCfzqssg(String cfzqssg) {
		this.cfzqssg = cfzqssg;
	}
	@Column(name="YJASGSG")
	public String getYjasgsg() {
		return yjasgsg;
	}

	public void setYjasgsg(String yjasgsg) {
		this.yjasgsg = yjasgsg;
	}
	@Column(name="CFJESG")
	public String getCfjesg() {
		return cfjesg;
	}

	public void setCfjesg(String cfjesg) {
		this.cfjesg = cfjesg;
	}

}
