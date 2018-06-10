package com.jshx.ssjjc.entity;

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
@Table(name="SSJJC")
public class Ssjjc extends BaseModel
{
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	private Date jcsj;
	
	private String qylx;
	
	private String sflddd;
	
	private String jcbl;
	
	private String zrs;
	
	private String ifrw;
	
	public Ssjjc(){
	}
	
	public Ssjjc(String id, Date jcsj,String qylx,String jcbl,Date createTime,String ifrw){
this.id = id;
this.jcsj = jcsj;
this.qylx = qylx;
this.jcbl = jcbl;
this.createTime = createTime;
this.ifrw = ifrw;
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
	@Column
	public Date getJcsj() {
		return jcsj;
	}

	public void setJcsj(Date jcsj) {
		this.jcsj = jcsj;
	}
	@Column
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	@Column
	public String getSflddd() {
		return sflddd;
	}

	public void setSflddd(String sflddd) {
		this.sflddd = sflddd;
	}
	@Column
	public String getJcbl() {
		return jcbl;
	}

	public void setJcbl(String jcbl) {
		this.jcbl = jcbl;
	}
	@Column
	public String getZrs() {
		return zrs;
	}

	public void setZrs(String zrs) {
		this.zrs = zrs;
	}
	@Column
	public String getIfrw() {
		return ifrw;
	}

	public void setIfrw(String ifrw) {
		this.ifrw = ifrw;
	}

}
