package com.jshx.wzcompany.entity;

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
@Table(name="WZCOMPANY")
public class Wzcompany extends BaseModel
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
	 * 单位名称
	 */
	private String companyname;
	
	/**
	 * 镇，开发区等
	 */
	private String szzid;
	
	private String szzname;
	
	private String szc;
	
	private String szcname;
	
	/**
	 * 
	 * 负责人
	 */
	private String fzr;
	
	/**
	 * 手机号码
	 */
	private String mobile;
	
	/**
	 * 传       真
	 */
	private String cz;
	
	/**
	 * 身份证
	 */
	private String sfz;
	
	/**
	 * 行业分类
	 */
	private String hyfl;
	
	/**
	 * 危化品企业类型
	 */
	private String whpqylx;
	/**
	 * 是否危化品企业类型
	 */
	private String ifwhpqylx;
	/**
	 * 是否职业危害企业类型
	 */
	private String ifzywhqylx;
	
	/**
	 * 职业危害企业类型
	 */
	private String zywhqylx;
	
	/**
	 * 是否为五小企业
	 */
	private String ifwxqy;
	
	/**
	 * 员工数
	 */
	private String ygs;
	
	/**
	 * 年销售收入（万元）
	 */
	private String nxssr;
	
	/**
	 * 占地面积（m2）
	 */
	private Long zdmj;
	
	/**
	 * 建筑面积（m2）
	 */
	private Long jzmj;
	
	/**
	 * 经营场所性质
	 */
	private String jycsxz;
	
	/**
	 * 房东姓名
	 */
	private String fdxm;
	
	/**
	 * 手机号码
	 */
	private String fdsjh;
	
	/**
	 * 身份证
	 */
	private String fdsfz;
	
	/**
	 * 经营范围
	 */
	private String jyfw;
	
	private String binduserid;
	
	private String bindusername;
	
	private String longitude;
	
	private String latitude;

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
	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	@Column
	public String getSzzid() {
		return szzid;
	}

	public void setSzzid(String szzid) {
		this.szzid = szzid;
	}
	@Column
	public String getSzzname() {
		return szzname;
	}

	public void setSzzname(String szzname) {
		this.szzname = szzname;
	}
	@Column
	public String getSzc() {
		return szc;
	}

	public void setSzc(String szc) {
		this.szc = szc;
	}
	@Column
	public String getSzcname() {
		return szcname;
	}

	public void setSzcname(String szcname) {
		this.szcname = szcname;
	}
	@Column
	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}
	@Column
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column
	public String getCz() {
		return cz;
	}

	public void setCz(String cz) {
		this.cz = cz;
	}
	@Column
	public String getSfz() {
		return sfz;
	}

	public void setSfz(String sfz) {
		this.sfz = sfz;
	}
	@Column
	public String getHyfl() {
		return hyfl;
	}

	public void setHyfl(String hyfl) {
		this.hyfl = hyfl;
	}
	@Column
	public String getWhpqylx() {
		return whpqylx;
	}

	public void setWhpqylx(String whpqylx) {
		this.whpqylx = whpqylx;
	}
	@Column
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}
	@Column
	public String getIfzywhqylx() {
		return ifzywhqylx;
	}

	public void setIfzywhqylx(String ifzywhqylx) {
		this.ifzywhqylx = ifzywhqylx;
	}
	@Column
	public String getZywhqylx() {
		return zywhqylx;
	}

	public void setZywhqylx(String zywhqylx) {
		this.zywhqylx = zywhqylx;
	}
	@Column
	public String getIfwxqy() {
		return ifwxqy;
	}

	public void setIfwxqy(String ifwxqy) {
		this.ifwxqy = ifwxqy;
	}
	@Column
	public String getYgs() {
		return ygs;
	}

	public void setYgs(String ygs) {
		this.ygs = ygs;
	}
	@Column
	public String getNxssr() {
		return nxssr;
	}

	public void setNxssr(String nxssr) {
		this.nxssr = nxssr;
	}
	@Column
	public Long getZdmj() {
		return zdmj;
	}

	public void setZdmj(Long zdmj) {
		this.zdmj = zdmj;
	}
	@Column
	public Long getJzmj() {
		return jzmj;
	}

	public void setJzmj(Long jzmj) {
		this.jzmj = jzmj;
	}
	@Column
	public String getJycsxz() {
		return jycsxz;
	}

	public void setJycsxz(String jycsxz) {
		this.jycsxz = jycsxz;
	}
	@Column
	public String getFdxm() {
		return fdxm;
	}

	public void setFdxm(String fdxm) {
		this.fdxm = fdxm;
	}
	@Column
	public String getFdsjh() {
		return fdsjh;
	}

	public void setFdsjh(String fdsjh) {
		this.fdsjh = fdsjh;
	}
	@Column
	public String getFdsfz() {
		return fdsfz;
	}

	public void setFdsfz(String fdsfz) {
		this.fdsfz = fdsfz;
	}
	@Column
	public String getJyfw() {
		return jyfw;
	}

	public void setJyfw(String jyfw) {
		this.jyfw = jyfw;
	}
	@Column
	public String getBinduserid() {
		return binduserid;
	}

	public void setBinduserid(String binduserid) {
		this.binduserid = binduserid;
	}
	@Column
	public String getBindusername() {
		return bindusername;
	}

	public void setBindusername(String bindusername) {
		this.bindusername = bindusername;
	}
	@Column
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 所在县
	 */
	private String county;
	
	@Column(name="county")
	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}
	private String countyName;

	@Column(name="countyname")
	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}
