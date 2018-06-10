package com.jshx.carDoneInfo.entity;

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
@Table(name="CAREQUIPMENTSTATE")
public class CarEquipmentState extends BaseModel
{
	private String password;
	private String month;
	
	@Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}

}
