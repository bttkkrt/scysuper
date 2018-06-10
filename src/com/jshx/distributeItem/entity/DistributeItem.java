package com.jshx.distributeItem.entity;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name="DISTRIBUTE_ITEM")
public class DistributeItem extends BaseModel
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
	 * 分配记录标识
	 */
	private String distributeId;

	/**
	 * 巡检项
	 */
	private String item;

	/**
	 * 巡检要求
	 */
	private String requirement;

	/**
	 * 是否已检查标识
	 */
	private String isinspect;

	/**
	 * 检测上传图片
	 */
	private String image;

	/**
	 * 不合格数量
	 */
	private String count;

	/**
	 * 检测备注
	 */
	private String remark;
	/**
	 * 检查人员
	 */
	private String userId;
	
	/**
	 * 任务时间
	 */
	private String taskTime;
	/**
	 * 上报时间
	 */
	private String reportTime;
	/**
	 * 经度
	 */
	private String longitude;
	
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 位置
	 */
	private String local;
	
	/**
	 * 顺序序号
	 * GY-UPDATE 2015-01-07
	 */
	private Integer indexNum;
	
	/**
	 * 描述
	 * GY-UPDATE 2015-01-14
	 */
	private String describe;
	
	/**
	 * 复查结果
	 */
	private String reviewResult;

	/**
	 * 复查备注
	 */
	private String reviewRemark;
	

	/**
	 * 复查纬度位置纬度
	 */
	private String reviewLatitude;

	/**
	 * 复查位置经度
	 */
	private String reviewLongitude;
	

	/**
	 * 复查人ID
	 */
	private String reviewUserId;

	/**
	 * 复查时间
	 */
	private String reviewTime;
	
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

	
	@Column(name="DISTRIBUTE_ID")
	public String getDistributeId()
	{
		return this.distributeId;
	}

	public void setDistributeId(String distributeId)
	{
		this.distributeId = distributeId;
	}

	@Column(name="ITEM")
	public String getItem()
	{
		return this.item;
	}

	public void setItem(String item)
	{
		this.item = item;
	}

	@Column(name="REQUIREMENT")
	public String getRequirement()
	{
		return this.requirement;
	}

	public void setRequirement(String requirement)
	{
		this.requirement = requirement;
	}

	@Column(name="ISINSPECT")
	public String getIsinspect()
	{
		return this.isinspect;
	}

	public void setIsinspect(String isinspect)
	{
		this.isinspect = isinspect;
	}

	@Column(name="IMAGE")
	public String getImage()
	{
		return this.image;
	}

	public void setImage(String image)
	{
		this.image = image;
	}

	@Column(name="COUNT")
	public String getCount()
	{
		return this.count;
	}

	public void setCount(String count)
	{
		this.count = count;
	}

	@Column(name="REMARK")
	public String getRemark()
	{
		return this.remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name="task_time")
	public String getTaskTime() {
		return taskTime;
	}

	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}

	@Column(name="report_time")
	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	@Column(name="longitude")
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name="latitude")
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name="local")
	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Column(name="INDEX_NUM")
	public Integer getIndexNum()
	{
		return indexNum;
	}

	public void setIndexNum(Integer indexNum)
	{
		this.indexNum = indexNum;
	}

	@Column(name="DESCRIBE")
	public String getDescribe()
	{
		return describe;
	}

	public void setDescribe(String describe)
	{
		this.describe = describe;
	}

	@Column(name="Review_result")
	public String getReviewResult() {
		return reviewResult;
	}

	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}

	@Column(name="Review_remark")
	public String getReviewRemark() {
		return reviewRemark;
	}

	public void setReviewRemark(String reviewRemark) {
		this.reviewRemark = reviewRemark;
	}

	@Column(name="Review_user_id")
	public String getReviewUserId() {
		return reviewUserId;
	}

	public void setReviewUserId(String reviewUserId) {
		this.reviewUserId = reviewUserId;
	}

	@Column(name="Review_LATITUDE")
	public String getReviewLatitude() {
		return reviewLatitude;
	}

	public void setReviewLatitude(String reviewLatitude) {
		this.reviewLatitude = reviewLatitude;
	}

	@Column(name="Review_LONGITUDE")
	public String getReviewLongitude() {
		return reviewLongitude;
	}

	public void setReviewLongitude(String reviewLongitude) {
		this.reviewLongitude = reviewLongitude;
	}

	@Column(name="Review_time")
	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}
	
}
