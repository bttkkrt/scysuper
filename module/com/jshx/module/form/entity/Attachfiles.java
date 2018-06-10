package com.jshx.module.form.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//import oracle.sql.BLOB;

import com.jshx.core.base.entity.BaseModel;

import java.sql.Blob;
/**
 * Attachfiles entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "attachfiles")
public class Attachfiles extends BaseModel  implements java.io.Serializable {

	// Fields

	//private Integer attachId;
	private String attachName;
	private String contentType;
	private Blob content;
	private String attachType;
	private String tempId;
	private Integer messageId;
	private Integer infoId;
	private String formRowGuid;
	private String rowguid;
	private Integer msgid;
	private Integer onlinemsgid;
	private String fileName;

	// Constructors
	@Column(name = "infoID")
	public Integer getInfoId() {
		return infoId;
	}

	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}

	/** default constructor */
	public Attachfiles() {
	}

	

	/** full constructor */
	public Attachfiles(String attachName, String contentType, Blob content,
			String attachType, String tempId) {
		this.attachName = attachName;
		this.contentType = contentType;
		this.content = content;
		this.attachType = attachType;
		this.tempId = tempId;
	}



	@Column(name = "AttachName", length = 500)
	public String getAttachName() {
		return this.attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}

	@Column(name = "ContentType", length = 100)
	public String getContentType() {
		return this.contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	@Column(name = "Content",columnDefinition="BLOB",nullable=true)
	public Blob getContent() {
		return this.content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	@Column(name = "AttachType", length = 100)
	public String getAttachType() {
		return this.attachType;
	}

	public void setAttachType(String attachType) {
		this.attachType = attachType;
	}

	@Column(name = "TempID", length = 100)
	public String getTempId() {
		return this.tempId;
	}

	public void setTempId(String tempId) {
		this.tempId = tempId;
	}

	@Column(name = "MessageID")
	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	@Column(name = "MsgID")
	public Integer getMsgid() {
		return msgid;
	}


	@Column(name = "FormRowGuid")
	public String getFormRowGuid() {
		return formRowGuid;
	}

	public void setFormRowGuid(String formRowGuid) {
		this.formRowGuid = formRowGuid;
	}


	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}
	@Column(name = "OnlineMsgID")
	public Integer getOnlinemsgid() {
		return onlinemsgid;
	}

	public void setOnlinemsgid(Integer onlinemsgid) {
		this.onlinemsgid = onlinemsgid;
	}

	public String getRowguid() {
		return rowguid;
	}

	public void setRowguid(String rowguid) {
		this.rowguid = rowguid;
	}

	@Column(name = "FileName", length = 100)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}