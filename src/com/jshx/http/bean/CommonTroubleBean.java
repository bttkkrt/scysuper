package com.jshx.http.bean;

public class CommonTroubleBean {
	/**
	 * id
	 */
	private String rid;

	/**
	 * 检查企业名称
	 */
	private String qymc = "";

	/**
	 * 检查时间
	 */
	private String jcsj = "";

	/**
	 * 检查人员
	 */
	private String jcry = "";
	/**
	 * 挂牌时间
	 * @return
	 */
	private String gpsj = "";
	/**
	 * 责任人
	 * @return
	 */
	private String zrr = "";
	/**
	 * 添加 关联附件id lj 2013-07-26
	 * @return
	 */
	private String linkId;
	
	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public String getGpsj() {
		return gpsj;
	}

	public void setGpsj(String gpsj) {
		this.gpsj = gpsj;
	}

	public String getZrr() {
		return zrr;
	}

	public void setZrr(String zrr) {
		this.zrr = zrr;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getQymc() {
		return qymc;
	}

	public void setQymc(String qymc) {
		this.qymc = qymc;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}

	public String getJcry() {
		return jcry;
	}

	public void setJcry(String jcry) {
		this.jcry = jcry;
	}

	
}
