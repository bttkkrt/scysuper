package com.jshx.tssxspb.entity;

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
@Table(name="SPECIAL_ITEM")
public class SpecialItem extends BaseModel
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
	
	/**
	 * 事项标题
	 * @return
	 */
	private String title;
	
	/**
	 * 事项理由
	 * @return
	 */
	private String content;
	

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
	@Column
	public String getRelatedId() {
		return relatedId;
	}

	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}
	@Column
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Column
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
