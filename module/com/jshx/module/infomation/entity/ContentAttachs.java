package com.jshx.module.infomation.entity;

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
@Table(name="CONTENT_ATTACHS")
public class ContentAttachs extends BaseModel
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
	 * 内容信息id
	 */
	private String infoId;

	/**
	 * 附件原名称
	 */
	private String docName;

	/**
	 * 附件路径
	 */
	private String docUrl;

	/**
	 * 附件类型
	 */
	private String docType;
	
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
	
	/**
	 * 附件名称
	 */
	private String attachName;
	/**
	 * 获取附件名称
	 */
	@Column(name="ATTACH_NAME")
	public String getAttachName() {
		return attachName;
	}
	/**
	 * 设置附件名称
	 */
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	/**
	 * 获取部门ID
	 */
	@Column
	public String getDeptId()
	{
		return deptId;
	}
	/**
	 * 设置部门ID
	 */
	public void setDeptId(String deptId)
	{
		this.deptId = deptId;
	}
	/**  
	 * 获取删除状态
	 */
	@Column
	public Integer getDelFlag()
	{
		return delFlag;
	}
	/**  
	 * 设置删除状态
	 */
	public void setDelFlag(Integer delFlag)
	{
		this.delFlag = delFlag;
	}

	/**  
	 * 获取信息ID
	 */
	@Column(name="INFO_ID")
	public String getInfoId()
	{
		return this.infoId;
	}
	/**  
	 * 设置信息ID
	 */
	public void setInfoId(String infoId)
	{
		this.infoId = infoId;
	}
	/**
	 * 获取附件原名称
	 */
	@Column(name="DOC_NAME")
	public String getDocName()
	{
		return this.docName;
	}
	/**
	 * 设置附件原名称
	 */
	public void setDocName(String docName)
	{
		this.docName = docName;
	}
	/**
	 * 获取附件路径
	 */
	@Column(name="DOC_URL")
	public String getDocUrl()
	{
		return this.docUrl;
	}
	/**
	 * 设置附件路径
	 */
	public void setDocUrl(String docUrl)
	{
		this.docUrl = docUrl;
	}
	/**
	 * 获取附件类型
	 */
	@Column(name="DOC_TYPE")
	public String getDocType()
	{
		return this.docType;
	}
	/**
	 * 设置附件类型
	 */
	public void setDocType(String docType)
	{
		this.docType = docType;
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
