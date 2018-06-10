package com.lkdj.lkLog.entity;

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
@Table(name="LK_LOG")
public class LkLog extends BaseModel
{
	private String nrid;
	private String lb;
	private String result;
	@Column
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column
	public String getLb() {
		return lb;
	}
	public void setLb(String lb) {
		this.lb = lb;
	}
	@Column
	public String getNrid() {
		return nrid;
	}
	public void setNrid(String nrid) {
		this.nrid = nrid;
	}
}
