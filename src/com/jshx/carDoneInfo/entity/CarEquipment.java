package com.jshx.carDoneInfo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.core.base.entity.BaseModel;

/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CAREQUIPMENT")
public class CarEquipment extends BaseModel {
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 视频名称
	 */
	private String detailName;

	/**
	 * 本地视频名称
	 */
	private String localName;

	private String guid;
	/**
	 * 通道ID
	 */
	private String puid;
	private String streamId;
	/**
	 * 公司名称
	 */
	private String companyName;
	
	/**
	 * 企业类型
	 */
	private String qylx;

	@Column
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	@Column
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	@Column(name = "DETAILNAME")
	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	@Column(name = "GUID")
	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Column(name = "PUID")
	public String getPuid() {
		return puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	@Column(name = "STREAMID")
	public String getStreamId() {
		return streamId;
	}

	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}

	@Column(name = "COMPANYNAME")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Transient
	public String getQylx() {
		return qylx;
	}

	public void setQylx(String qylx) {
		this.qylx = qylx;
	}

	@Transient
	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

}
