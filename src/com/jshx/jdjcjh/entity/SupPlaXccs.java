package com.jshx.jdjcjh.entity;

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
@Table(name="SUP_PLA_XCCS")
public class SupPlaXccs extends BaseModel
{
	private String xccs;

	@Column
	public String getXccs() {
		return xccs;
	}

	public void setXccs(String xccs) {
		this.xccs = xccs;
	}


}
