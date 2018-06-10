package com.jshx.zybwhzl.entity;

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
@Table(name="ZYBWHZL")
public class Zybwhzl extends BaseModel
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
	 * 粉尘类其中女工数
	 */
	private String fclngs;

	/**
	 * 所在镇id
	 */
	private String szzid;

	/**
	 * 所在镇
	 */
	private String szzname;

	/**
	 * 企业id
	 */
	private String qyid;

	/**
	 * 企业名称
	 */
	private String qymc;

	/**
	 * 企业类型
	 */
	private String qylx;

	/**
	 * 行业分类
	 */
	private String hyfl;

	/**
	 * 企业规模
	 */
	private String qygm;

	/**
	 * 粉尘类有或无
	 */
	private String fclyw;

	/**
	 * 粉尘类接触人数
	 */
	private String fcljcrs;

	/**
	 * 粉尘类接触职业病危害总人数
	 */
	private String fclzrs;

	/**
	 * 化学物质类有或无
	 */
	private String hxwzlyw;

	/**
	 * 化学物质类接触人数
	 */
	private String hxwzljcrs;

	/**
	 * 化学物质类接触职业病危害总人数
	 */
	private String hxwzlzrs;

	/**
	 * 化学物质类其中女工数
	 */
	private String hxwzlngs;

	/**
	 * 物理因素类有或无
	 */
	private String wlysyw;

	/**
	 * 物理因素类接触人数
	 */
	private String wlysjcrs;

	/**
	 * 物理因素类接触职业病危害总人数
	 */
	private String wlyszrs;

	/**
	 * 物理因素类其中女工数
	 */
	private String wlysngs;

	/**
	 * 放射性物质类有或无
	 */
	private String fsxwzlyw;

	/**
	 * 放射性物质类接触人数
	 */
	private String fsxwzljcrs;

	/**
	 * 放射性物质类接触职业病危害总人数
	 */
	private String fsxwzlzrs;

	/**
	 * 放射性物质类其中女工数
	 */
	private String fsxwzlngs;

	/**
	 * 其他有或无
	 */
	private String qtyw;

	/**
	 * 其他接触人数
	 */
	private String qtjcrs;

	/**
	 * 其他接触职业病危害总人数
	 */
	private String qtzrs;

	/**
	 * 其他其中女工数
	 */
	private String qtngs;

	/**
	 * 填报人
	 */
	private String tbr;

	/**
	 * 联系电话
	 */
	private String lxdh;

	/**
	 * 企业注册类型
	 */
	private String qyzclx;
	/**
	 * 是否危化品企业类型
	 */
	private String ifwhpqylx;
	/**
	 * 是否职业危害企业类型
	 */
	private String ifzywhqylx;
	
	
	/**
	 * 是否烟花爆竹经营企业
	 */
	private String ifyhbzjyqy;
	
	private String szc;
	
	private String szcname;
	
	//heyc 20141210 修改 start
	/**
	 * 是否非煤矿山企业
	 */
	private String iffmksjyqy;

	@Column(name="IFFMKSJYQY")
	public String getIffmksjyqy() {
		return iffmksjyqy;
	}

	public void setIffmksjyqy(String iffmksjyqy) {
		this.iffmksjyqy = iffmksjyqy;
	}
	//heyc 20141210 修改 end

	
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
	
	@Column(name="IFWHPQYLX")
	public String getIfwhpqylx() {
		return ifwhpqylx;
	}

	public void setIfwhpqylx(String ifwhpqylx) {
		this.ifwhpqylx = ifwhpqylx;
	}
	@Column(name="IFZYWHQYLX")
	public String getIfzywhqylx() {
		return ifzywhqylx;
	}

	public void setIfzywhqylx(String ifzywhqylx) {
		this.ifzywhqylx = ifzywhqylx;
	}
	@Column(name="IFYHBZJYQY")
	public String getIfyhbzjyqy() {
		return ifyhbzjyqy;
	}

	public void setIfyhbzjyqy(String ifyhbzjyqy) {
		this.ifyhbzjyqy = ifyhbzjyqy;
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

	
	@Column(name="FCLNGS")
	public String getFclngs()
	{
		return this.fclngs;
	}

	public void setFclngs(String fclngs)
	{
		this.fclngs = fclngs;
	}

	@Column(name="SZZID")
	public String getSzzid()
	{
		return this.szzid;
	}

	public void setSzzid(String szzid)
	{
		this.szzid = szzid;
	}

	@Column(name="SZZNAME")
	public String getSzzname()
	{
		return this.szzname;
	}

	public void setSzzname(String szzname)
	{
		this.szzname = szzname;
	}

	@Column(name="QYID")
	public String getQyid()
	{
		return this.qyid;
	}

	public void setQyid(String qyid)
	{
		this.qyid = qyid;
	}

	@Column(name="QYMC")
	public String getQymc()
	{
		return this.qymc;
	}

	public void setQymc(String qymc)
	{
		this.qymc = qymc;
	}

	@Column(name="QYLX")
	public String getQylx()
	{
		return this.qylx;
	}

	public void setQylx(String qylx)
	{
		this.qylx = qylx;
	}

	@Column(name="HYFL")
	public String getHyfl()
	{
		return this.hyfl;
	}

	public void setHyfl(String hyfl)
	{
		this.hyfl = hyfl;
	}

	@Column(name="QYGM")
	public String getQygm()
	{
		return this.qygm;
	}

	public void setQygm(String qygm)
	{
		this.qygm = qygm;
	}

	@Column(name="FCLYW")
	public String getFclyw()
	{
		return this.fclyw;
	}

	public void setFclyw(String fclyw)
	{
		this.fclyw = fclyw;
	}

	@Column(name="FCLJCRS")
	public String getFcljcrs()
	{
		return this.fcljcrs;
	}

	public void setFcljcrs(String fcljcrs)
	{
		this.fcljcrs = fcljcrs;
	}

	@Column(name="FCLZRS")
	public String getFclzrs()
	{
		return this.fclzrs;
	}

	public void setFclzrs(String fclzrs)
	{
		this.fclzrs = fclzrs;
	}

	@Column(name="HXWZLYW")
	public String getHxwzlyw()
	{
		return this.hxwzlyw;
	}

	public void setHxwzlyw(String hxwzlyw)
	{
		this.hxwzlyw = hxwzlyw;
	}

	@Column(name="HXWZLJCRS")
	public String getHxwzljcrs()
	{
		return this.hxwzljcrs;
	}

	public void setHxwzljcrs(String hxwzljcrs)
	{
		this.hxwzljcrs = hxwzljcrs;
	}

	@Column(name="HXWZLZRS")
	public String getHxwzlzrs()
	{
		return this.hxwzlzrs;
	}

	public void setHxwzlzrs(String hxwzlzrs)
	{
		this.hxwzlzrs = hxwzlzrs;
	}

	@Column(name="HXWZLNGS")
	public String getHxwzlngs()
	{
		return this.hxwzlngs;
	}

	public void setHxwzlngs(String hxwzlngs)
	{
		this.hxwzlngs = hxwzlngs;
	}

	@Column(name="WLYSYW")
	public String getWlysyw()
	{
		return this.wlysyw;
	}

	public void setWlysyw(String wlysyw)
	{
		this.wlysyw = wlysyw;
	}

	@Column(name="WLYSJCRS")
	public String getWlysjcrs()
	{
		return this.wlysjcrs;
	}

	public void setWlysjcrs(String wlysjcrs)
	{
		this.wlysjcrs = wlysjcrs;
	}

	@Column(name="WLYSZRS")
	public String getWlyszrs()
	{
		return this.wlyszrs;
	}

	public void setWlyszrs(String wlyszrs)
	{
		this.wlyszrs = wlyszrs;
	}

	@Column(name="WLYSNGS")
	public String getWlysngs()
	{
		return this.wlysngs;
	}

	public void setWlysngs(String wlysngs)
	{
		this.wlysngs = wlysngs;
	}

	@Column(name="FSXWZLYW")
	public String getFsxwzlyw()
	{
		return this.fsxwzlyw;
	}

	public void setFsxwzlyw(String fsxwzlyw)
	{
		this.fsxwzlyw = fsxwzlyw;
	}

	@Column(name="FSXWZLJCRS")
	public String getFsxwzljcrs()
	{
		return this.fsxwzljcrs;
	}

	public void setFsxwzljcrs(String fsxwzljcrs)
	{
		this.fsxwzljcrs = fsxwzljcrs;
	}

	@Column(name="FSXWZLZRS")
	public String getFsxwzlzrs()
	{
		return this.fsxwzlzrs;
	}

	public void setFsxwzlzrs(String fsxwzlzrs)
	{
		this.fsxwzlzrs = fsxwzlzrs;
	}

	@Column(name="FSXWZLNGS")
	public String getFsxwzlngs()
	{
		return this.fsxwzlngs;
	}

	public void setFsxwzlngs(String fsxwzlngs)
	{
		this.fsxwzlngs = fsxwzlngs;
	}

	@Column(name="QTYW")
	public String getQtyw()
	{
		return this.qtyw;
	}

	public void setQtyw(String qtyw)
	{
		this.qtyw = qtyw;
	}

	@Column(name="QTJCRS")
	public String getQtjcrs()
	{
		return this.qtjcrs;
	}

	public void setQtjcrs(String qtjcrs)
	{
		this.qtjcrs = qtjcrs;
	}

	@Column(name="QTZRS")
	public String getQtzrs()
	{
		return this.qtzrs;
	}

	public void setQtzrs(String qtzrs)
	{
		this.qtzrs = qtzrs;
	}

	@Column(name="QTNGS")
	public String getQtngs()
	{
		return this.qtngs;
	}

	public void setQtngs(String qtngs)
	{
		this.qtngs = qtngs;
	}

	@Column(name="TBR")
	public String getTbr()
	{
		return this.tbr;
	}

	public void setTbr(String tbr)
	{
		this.tbr = tbr;
	}

	@Column(name="LXDH")
	public String getLxdh()
	{
		return this.lxdh;
	}

	public void setLxdh(String lxdh)
	{
		this.lxdh = lxdh;
	}

	@Column(name="QYZCLX")
	public String getQyzclx()
	{
		return this.qyzclx;
	}

	public void setQyzclx(String qyzclx)
	{
		this.qyzclx = qyzclx;
	}

	/**
	 * 是否直属企业
	 */
	private String ifzsqy;
	/**
	 * 企业所属监管类型
	 */
	private String companyType;
	
	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	@Column(name="ifzsqy")
	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}
}
