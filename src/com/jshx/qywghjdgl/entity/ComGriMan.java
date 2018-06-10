package com.jshx.qywghjdgl.entity;

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
@Table(name="COM_GRI_MAN")
public class ComGriMan extends BaseModel
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
	 * 网格管理中队
	 */
	private String gridManageDept;
	
	/**
	 * 网格管理中队名称
	 */
	private String gridManageDeptName;

	/**
	 * 网格管理人员id
	 */
	private String gridManagePerson;
	
	/**
	 * 网格管理人员姓名
	 */
	private String gridManagePersonName;

	/**
	 * 网格名称
	 */
	private String gridName;

	/**
	 * 管辖范围
	 */
	private String manageScope;
	

	/**
	 * 地图关联key
	 */
	private String mapKey;
	public ComGriMan(){
	}
	
	public ComGriMan(String id, String gridManageDeptName, String gridManagePersonName, String gridName,String createUserID){
this.id = id;

this.gridManageDeptName = gridManageDeptName;

this.gridManagePersonName = gridManagePersonName;

this.gridName = gridName;
this.createUserID=createUserID;
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

	
	@Column(name="GRID_MANAGE_DEPT")
	public String getGridManageDept()
	{
		return this.gridManageDept;
	}

	public void setGridManageDept(String gridManageDept)
	{
		this.gridManageDept = gridManageDept;
	}

	@Column(name="GRID_MANAGE_PERSON")
	public String getGridManagePerson()
	{
		return this.gridManagePerson;
	}

	public void setGridManagePerson(String gridManagePerson)
	{
		this.gridManagePerson = gridManagePerson;
	}

	@Column(name="GRID_NAME")
	public String getGridName()
	{
		return this.gridName;
	}

	public void setGridName(String gridName)
	{
		this.gridName = gridName;
	}

	@Column(name="MANAGE_SCOPE")
	public String getManageScope()
	{
		return this.manageScope;
	}

	public void setManageScope(String manageScope)
	{
		this.manageScope = manageScope;
	}
	@Column(name="GRID_MANAGE_DEPT_NAME")
	public String getGridManageDeptName() {
		return gridManageDeptName;
	}

	public void setGridManageDeptName(String gridManageDeptName) {
		this.gridManageDeptName = gridManageDeptName;
	}
	@Column(name="GRID_MANAGE_PERSON_NAME")
	public String getGridManagePersonName() {
		return gridManagePersonName;
	}

	public void setGridManagePersonName(String gridManagePersonName) {
		this.gridManagePersonName = gridManagePersonName;
	}
	@Column(name="MAP_KEY")
	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	
}
