package com.jshx.xwbl.entity;

import java.util.Date;

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
@Table(name="INQREC_RECORD")
public class InqRecRecord extends BaseModel
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
	 * 关联文书编号
	 */
	private String relatedId;
	
	private String askRecord;
	
	private String recRecord;


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

	
	@Column(name="RELATED_ID")
	public String getRelatedId()
	{
		return this.relatedId;
	}

	public void setRelatedId(String relatedId)
	{
		this.relatedId = relatedId;
	}
	@Column(name="ASK_RECORD")
	public String getAskRecord() {
		return askRecord;
	}

	public void setAskRecord(String askRecord) {
		this.askRecord = askRecord;
	}
	@Column(name="REC_RECORD")
	public String getRecRecord() {
		return recRecord;
	}

	public void setRecRecord(String recRecord) {
		this.recRecord = recRecord;
	}

	
}
