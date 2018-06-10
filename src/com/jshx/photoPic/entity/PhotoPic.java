package com.jshx.photoPic.entity;

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
//@SuppressWarnings("serial")
//@Entity
//@Table(name="PHOTO_PIC_OLD")
public class PhotoPic extends BaseModel
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
	 * 附件名称
	 */
	private String picName;

	/**
	 * 附件关联id
	 */
	private String linkId;

	/**
	 * 附件类型
	 */
	private String picType;

	/**
	 * 模块类型
	 */
	private String mkType;

	/**
	 * 附件真实名称
	 */
	private String fileName;

	/**
	 * 服务器url
	 */
	private String httpUrl;

	/**
	 * 内网url
	 */
	private String nwUrl;
	
	/**
	 * 附件大小
	 */
	private String fileSize;
	
	public PhotoPic(){
	}
	
	public PhotoPic(String id){
this.id = id;
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

	
	@Column(name="PIC_NAME")
	public String getPicName()
	{
		return this.picName;
	}

	public void setPicName(String picName)
	{
		this.picName = picName;
	}

	@Column(name="LINK_ID")
	public String getLinkId()
	{
		return this.linkId;
	}

	public void setLinkId(String linkId)
	{
		this.linkId = linkId;
	}

	@Column(name="PIC_TYPE")
	public String getPicType()
	{
		return this.picType;
	}

	public void setPicType(String picType)
	{
		this.picType = picType;
	}

	@Column(name="MK_TYPE")
	public String getMkType()
	{
		return this.mkType;
	}

	public void setMkType(String mkType)
	{
		this.mkType = mkType;
	}

	@Column(name="FILE_NAME")
	public String getFileName()
	{
		return this.fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	@Column(name="HTTP_URL")
	public String getHttpUrl()
	{
		return this.httpUrl;
	}

	public void setHttpUrl(String httpUrl)
	{
		this.httpUrl = httpUrl;
	}

	@Column(name="NW_URL")
	public String getNwUrl()
	{
		return this.nwUrl;
	}

	public void setNwUrl(String nwUrl)
	{
		this.nwUrl = nwUrl;
	}
	@Column(name="FILE_SIZE")
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

}
