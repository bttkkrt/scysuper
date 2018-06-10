package com.jshx.jcjl.entity;

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
@Table(name="INS_REC")
public class InsRec extends BaseModel
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
	 * 案件来源
	 */
	private String caseSource;

	/**
	 * 检查记录
	 */
	private String inspectionRecord;
	
	private String instrumentType;

	
	public InsRec(){
	}
	
	public InsRec(String id, String caseSource,String instrumentType){
this.id = id;

this.caseSource = caseSource;

this.instrumentType = instrumentType;
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

	
	@Column(name="CASE_SOURCE")
	public String getCaseSource()
	{
		return this.caseSource;
	}

	public void setCaseSource(String caseSource)
	{
		this.caseSource = caseSource;
	}

	@Column(name="INSPECTION_RECORD")
	public String getInspectionRecord()
	{
		return this.inspectionRecord;
	}

	public void setInspectionRecord(String inspectionRecord)
	{
		this.inspectionRecord = inspectionRecord;
	}
	@Column(name="INSTRUMENT_TYPE")
	public String getInstrumentType() {
		return instrumentType;
	}

	public void setInstrumentType(String instrumentType) {
		this.instrumentType = instrumentType;
	}

}
