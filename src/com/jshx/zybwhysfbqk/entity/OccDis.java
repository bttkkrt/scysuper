package com.jshx.zybwhysfbqk.entity;

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
@Table(name="OCC_DIS")
public class OccDis extends BaseModel
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
	 * 关联基本因素id
	 */
	private String proId;

	/**
	 * 作业场所名称
	 */
	private String workPlace;

	/**
	 * 接触人数
	 */
	private String contactNum;

	
	public OccDis(){
	}
	
	public OccDis(String id,String workPlace,String contactNum,String proId){
this.id = id;
this.workPlace=workPlace;
this.contactNum=contactNum;
this.proId=proId;
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

	
	@Column(name="PRO_ID")
	public String getProId()
	{
		return this.proId;
	}

	public void setProId(String proId)
	{
		this.proId = proId;
	}

	@Column(name="WORK_PLACE")
	public String getWorkPlace()
	{
		return this.workPlace;
	}

	public void setWorkPlace(String workPlace)
	{
		this.workPlace = workPlace;
	}

	@Column(name="CONTACT_NUM")
	public String getContactNum()
	{
		return this.contactNum;
	}

	public void setContactNum(String contactNum)
	{
		this.contactNum = contactNum;
	}

}
