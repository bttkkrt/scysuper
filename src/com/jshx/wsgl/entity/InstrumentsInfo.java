package com.jshx.wsgl.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jshx.core.base.entity.BaseModel;


/**
 * 实体类模板（目前仅适配MS-SQLServer数据库）
 * @author
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="INSTRUMENTS_INFO")
public class InstrumentsInfo extends BaseModel
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
	 * 案件编号
	 */
	private String caseId;

	/**
	 * 案件名称
	 */
	private String caseName;

	/**
	 * 文书类型
	 */
	private String instrumentType;
	
	/**
	 * 文书名称
	 */
	private String instrumentName;

	/**
	 * 附件关联id
	 */
	private String linkId;
	
	/**
	 * 是否可打印
	 */
	private String ifPrint;
	
	/**
	 * 是否需审核 0:不需审核  1：待审核  2：待审批  3：电子签名待确认   4：不通过  5:审批通过 6:不可修改 7：不可回执  8:法务
	 */
	private String ifCheck;
	
	private String ifReturn;
	
	/**
	 * 文书外网地址
	 */
	private String httpurl;
	
	/**
	 * 文书内网地址
	 */
	private String nwurl;
	
	/**
	 * 时间
	 */
	private Date time;
	
	/**
	 * 审核结果
	 */
	private String result;
	
	/**
	 * 审核时间
	 */
	private Date checkTime;
	
	/**
	 * 文书名称
	 */
	private String fileName;
	
	private String remark;
	
	
	/**
	 * 送达地点
	 */
	private String address;
	
	/**
	 * 送达日期
	 */
	private Date returnTime;
	
	/**
	 * 送达方式
	 */
	private String returnWay;

	/**
	 * 送达人
	 */
	private String returnPerson;
	
	private String wsh;
	
	private String ajbz;
	
	private String ajh;
	
	private String ajhNum;
	
	private String fileSize;
	
	private String pageSize;
	
	private String sort;
	
	private String needCheckUser;
	
	private String needCheck;//法务审核  0不审核，1需审核，2已审核
	
	private String dzqmCheck;//电子签名审核 0不审核，1需审核，2已审核
	
	private String dzCheck;//队长审核  0不审核，1需审核，2已审核
	
	private String jzCheck;//局长审核 0不审核，1需审核，2已审核
	
	private String lastFile;
	
	private String companyName;//企业名称
	
	private String personType;
	
	@Column(name="WSH")
	public String getWsh() {
		return wsh;
	}

	public void setWsh(String wsh) {
		this.wsh = wsh;
	}
	
	public InstrumentsInfo(){
	}
	
	public InstrumentsInfo(String id, String  caseName,String instrumentType,String ifPrint,String ifCheck,String createUserID,String needCheckUser,String instrumentName,String companyName){
this.id = id;

this.caseName = caseName;
this.instrumentType = instrumentType;

this.ifPrint = ifPrint;

this.ifCheck = ifCheck;

this.createUserID = createUserID;

this.needCheckUser = needCheckUser;

this.instrumentName = instrumentName;

this.companyName = companyName;
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

	
	@Column(name="CASE_ID")
	public String getCaseId()
	{
		return this.caseId;
	}

	public void setCaseId(String caseId)
	{
		this.caseId = caseId;
	}

	@Column(name="CASE_NAME")
	public String getCaseName()
	{
		return this.caseName;
	}

	public void setCaseName(String caseName)
	{
		this.caseName = caseName;
	}

	@Column(name="INSTRUMENT_TYPE")
	public String getInstrumentType()
	{
		return this.instrumentType;
	}

	public void setInstrumentType(String instrumentType)
	{
		this.instrumentType = instrumentType;
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
	@Column(name="IF_PRINT")
	public String getIfPrint() {
		return ifPrint;
	}

	public void setIfPrint(String ifPrint) {
		this.ifPrint = ifPrint;
	}
	@Column(name="IF_CHECK")
	public String getIfCheck() {
		return ifCheck;
	}

	public void setIfCheck(String ifCheck) {
		this.ifCheck = ifCheck;
	}
	@Column(name="HTTP_URL")
	public String getHttpurl() {
		return httpurl;
	}

	public void setHttpurl(String httpurl) {
		this.httpurl = httpurl;
	}
	@Column(name="NW_URL")
	public String getNwurl() {
		return nwurl;
	}

	public void setNwurl(String nwurl) {
		this.nwurl = nwurl;
	}
	@Column(name="TIME")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@Transient
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Transient
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="RETURN_TIME")
	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}
	@Column(name="RETURN_WAY")
	public String getReturnWay() {
		return returnWay;
	}

	public void setReturnWay(String returnWay) {
		this.returnWay = returnWay;
	}
	@Column(name="RETURN_PERSON")
	public String getReturnPerson() {
		return returnPerson;
	}

	public void setReturnPerson(String returnPerson) {
		this.returnPerson = returnPerson;
	}
	@Column(name="IF_RETURN")
	public String getIfReturn() {
		return ifReturn;
	}

	public void setIfReturn(String ifReturn) {
		this.ifReturn = ifReturn;
	}
	@Column(name="AJBZ")
	public String getAjbz() {
		return ajbz;
	}

	public void setAjbz(String ajbz) {
		this.ajbz = ajbz;
	}
	@Column(name="AJH")
	public String getAjh() {
		return ajh;
	}

	public void setAjh(String ajh) {
		this.ajh = ajh;
	}
	@Column(name="AJH_NUM")
	public String getAjhNum() {
		return ajhNum;
	}

	public void setAjhNum(String ajhNum) {
		this.ajhNum = ajhNum;
	}
	@Column(name="FILE_SIZE")
	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	@Column
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	@Column
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	@Column(name="NEED_CHECK_USER")
	public String getNeedCheckUser() {
		return needCheckUser;
	}

	public void setNeedCheckUser(String needCheckUser) {
		this.needCheckUser = needCheckUser;
	}
	@Transient
	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	@Column
	public String getNeedCheck() {
		return needCheck;
	}

	public void setNeedCheck(String needCheck) {
		this.needCheck = needCheck;
	}
	@Column
	public String getInstrumentName() {
		return instrumentName;
	}

	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	@Column
	public String getDzqmCheck() {
		return dzqmCheck;
	}

	public void setDzqmCheck(String dzqmCheck) {
		this.dzqmCheck = dzqmCheck;
	}
	@Column
	public String getDzCheck() {
		return dzCheck;
	}

	public void setDzCheck(String dzCheck) {
		this.dzCheck = dzCheck;
	}
	@Column
	public String getJzCheck() {
		return jzCheck;
	}

	public void setJzCheck(String jzCheck) {
		this.jzCheck = jzCheck;
	}
	@Column
	public String getLastFile() {
		return lastFile;
	}

	public void setLastFile(String lastFile) {
		this.lastFile = lastFile;
	}
	@Column
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column
	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}
}
