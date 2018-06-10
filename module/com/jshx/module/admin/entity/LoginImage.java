package com.jshx.module.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.jshx.core.base.entity.BaseModel;

/**
 * 登陆背景图实体类
 * 
 * @author
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "LOGIN_IMAGE")
public class LoginImage extends BaseModel {
	/**
	 * 部门代码
	 */
	private String deptId;

	/**
	 * 删除标记
	 */
	private Integer delFlag;

	/**
	 * 图片名
	 */
	private String imageName;

	/**
	 * 图片地址
	 */
	private String imageUrl;

	/**
	 * 使用标识
	 */
	private Long isUsing;

	/**
	 * 缺省构造函数
	 */
	public LoginImage() {
	}

	/**
	 * 构造函数
	 */
	public LoginImage(String id, String imageName, String imageUrl, Long isUsing) {
		this.id = id;

		this.imageName = imageName;

		this.imageUrl = imageUrl;

		this.isUsing = isUsing;
	}

	/**
	 * 获取部门id
	 */
	@Column
	public String getDeptId() {
		return deptId;
	}
	/**
	 * 设置部门id
	 */
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	/**
	 * 获取删除标志位
	 */
	@Column
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 *设置删除标志位
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 *获取背景图片名称
	 */
	@Column(name = "IMAGE_NAME")
	public String getImageName() {
		return this.imageName;
	}
	/**
	 *设置背景图片名称
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 *获取背景图片路径
	 */
	@Column(name = "IMAGE_URL")
	public String getImageUrl() {
		return this.imageUrl;
	}
	/**
	 *设置背景图片路径
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * 获取是否使用标志位
	 */
	@Column(name = "IS_USING")
	public Long getIsUsing() {
		return this.isUsing;
	}
	/**
	 * 设置是否使用标志位
	 */
	public void setIsUsing(Long isUsing) {
		this.isUsing = isUsing;
	}

}
