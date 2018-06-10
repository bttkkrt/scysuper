package com.jshx.whpjyxk.entity;

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
@Table(name="WHPJYXK")
public class Whpjyxk extends BaseModel
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
	 * 剧毒品上月底持证数
	 */
	private String jdpsyd;

	/**
	 * 易制爆上月底持证数
	 */
	private String yzbsyd;

	/**
	 * 分装充装稀释上月持证数
	 */
	private String fzsyd;

	/**
	 * 仓储储存上月底持证数
	 */
	private String ccsyd;

	/**
	 * 中新区本月新领证数
	 */
	private String zxqbyxl;

	/**
	 * 街道本月新领证数
	 */
	private String jdbyxl;

	/**
	 * 总计本月新领证数
	 */
	private String zjbyxl;

	/**
	 * 园区无储存本月新领证数
	 */
	private String yqwccbyxl;

	/**
	 * 园区零售本月新领证数
	 */
	private String yqlsbyxl;

	/**
	 * 园区设储存本月新领证数
	 */
	private String yqsccbyxl;

	/**
	 * 加油站本月新领证数
	 */
	private String jyzbyxl;

	/**
	 * 剧毒品本月新领证数
	 */
	private String jdpbyxl;

	/**
	 * 易制爆本月新领证数
	 */
	private String yzbbyxl;

	/**
	 * 分装充装稀释本月新领证数
	 */
	private String fzbyxl;

	/**
	 * 分装充装稀释申报换证数
	 */
	private String fzsbhz;

	/**
	 * 仓储储存申报换证
	 */
	private String ccsbhz;

	/**
	 * 中新区本月注销证数
	 */
	private String zxqbyzx;

	/**
	 * 街道本月注销证数
	 */
	private String jdbyzx;

	/**
	 * 总计本月注销证数
	 */
	private String zjbyzx;

	/**
	 * 园区无储存本月注销证数
	 */
	private String yqwccbyzx;

	/**
	 * 园区零售本月注销证数
	 */
	private String yqlsbyzx;

	/**
	 * 园区设储存本月注销证数
	 */
	private String yqsccbyzx;

	/**
	 * 加油站本月注销证数
	 */
	private String jyzbyzx;

	/**
	 * 剧毒品本月注销证数
	 */
	private String jdpbyzx;

	/**
	 * 易制爆本月注销证数
	 */
	private String yzbbyzx;

	/**
	 * 分装充装稀释本月注销证数
	 */
	private String fzbyzx;

	/**
	 * 仓储储存本月注销证数
	 */
	private String ccbyzx;

	/**
	 * 仓储存储本月新领证数
	 */
	private String ccbyxl;

	/**
	 * 中新区本月换证数
	 */
	private String zxqbyhz;

	/**
	 * 街道本月换证数
	 */
	private String jdbyhz;

	/**
	 * 总计本月换证数
	 */
	private String zjbyhz;

	/**
	 * 园区无储存本月换证数
	 */
	private String yqwccbyhz;

	/**
	 * 园区零售本月换证数
	 */
	private String yqlsbyhz;

	/**
	 * 园区设储存本月换证数
	 */
	private String yqsccbyhz;

	/**
	 * 加油站本月换证数
	 */
	private String jyzbyhz;

	/**
	 * 剧毒品本月换证数
	 */
	private String jdpbyhz;

	/**
	 * 易制爆本月换证数
	 */
	private String yzbbyhz;

	/**
	 * 中新区本月过期许可证数
	 */
	private String zxqbygq;

	/**
	 * 分装充装稀释本月换证数
	 */
	private String fzbyhz;

	/**
	 * 仓储储存本月换证数
	 */
	private String ccbyhz;

	/**
	 * 街道本月过期许可证数
	 */
	private String jdbygq;

	/**
	 * 总计本月过期许可证数
	 */
	private String zjbygq;

	/**
	 * 园区无储存本月过期许可证数
	 */
	private String yqwccbygq;

	/**
	 * 园区零售本月过期许可证数
	 */
	private String yqlsbygq;

	/**
	 * 园区设储存本月过期许可证数
	 */
	private String yqsccbygq;

	/**
	 * 加油站本月过期许可证数
	 */
	private String jyzbygq;

	/**
	 * 剧毒品本月过期许可证数
	 */
	private String jdpbygq;

	/**
	 * 易制爆本月过期许可证数
	 */
	private String yzbbygq;

	/**
	 * 分装充装稀释本月过期许可证数
	 */
	private String fzbygq;

	/**
	 * 仓储储存本月过期许可证数
	 */
	private String ccbygq;

	/**
	 * 中新区申报换证数
	 */
	private String zxqsbhz;

	/**
	 * 街道申报换证数
	 */
	private String jdsbhz;

	/**
	 * 总计申报换证数
	 */
	private String zjsbhz;

	/**
	 * 园区无储存申报换证数
	 */
	private String yqwccsbhz;

	/**
	 * 园区零售申报换证数
	 */
	private String yqlssbhz;

	/**
	 * 园区设储存申报换证数
	 */
	private String yqsccsbhz;

	/**
	 * 加油站申报换证数
	 */
	private String jyzsbhz;

	/**
	 * 剧毒品申报换证数
	 */
	private String jdpsbhz;

	/**
	 * 易制爆申报换证数
	 */
	private String yzbsbhz;

	/**
	 * 中新区本月底持证数
	 */
	private String zxqbycz;

	/**
	 * 街道本月底持证数
	 */
	private String jdbycz;

	/**
	 * 月份
	 */
	private Date monthTime;

	/**
	 * 中新区上月底持证数
	 */
	private String zxqsyd;

	/**
	 * 街道上月底持证数
	 */
	private String jdsyd;

	/**
	 * 总计上月底持证数
	 */
	private String zjsyd;

	/**
	 * 园区无储存上月底持证数
	 */
	private String yqwccsyd;

	/**
	 * 园区零售上月底持证数
	 */
	private String yqlssyd;

	/**
	 * 园区设储存上月底持证数
	 */
	private String yqsccsyd;

	/**
	 * 加油站上月底持证数
	 */
	private String jyzsyd;

	/**
	 * 园区无储存本月底持证数
	 */
	private String yqwccbycz;

	/**
	 * 园区零售本月底持证数
	 */
	private String yqlsbycz;

	/**
	 * 园区设储存本月底持证数
	 */
	private String yqsccbycz;

	/**
	 * 加油站本月底持证数
	 */
	private String jyzbycz;

	/**
	 * 剧毒品本月底持证数
	 */
	private String jdpbycz;

	/**
	 * 易制爆本月底持证数
	 */
	private String yzbbycz;

	/**
	 * 分装充装稀释本月底持证数
	 */
	private String fzbycz;

	/**
	 * 仓储储存本月底持证数
	 */
	private String ccbycz;
	/**
	 * 总计本月底持证数
	 */
	private String zjbycz;
	
	public Whpjyxk(){
	}
	
	public Whpjyxk(String id, Date monthTime){
this.id = id;

this.monthTime = monthTime;
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

	
	@Column(name="JDPSYD")
	public String getJdpsyd()
	{
		return this.jdpsyd;
	}

	public void setJdpsyd(String jdpsyd)
	{
		this.jdpsyd = jdpsyd;
	}

	@Column(name="YZBSYD")
	public String getYzbsyd()
	{
		return this.yzbsyd;
	}

	public void setYzbsyd(String yzbsyd)
	{
		this.yzbsyd = yzbsyd;
	}

	@Column(name="FZSYD")
	public String getFzsyd()
	{
		return this.fzsyd;
	}

	public void setFzsyd(String fzsyd)
	{
		this.fzsyd = fzsyd;
	}

	@Column(name="CCSYD")
	public String getCcsyd()
	{
		return this.ccsyd;
	}

	public void setCcsyd(String ccsyd)
	{
		this.ccsyd = ccsyd;
	}

	@Column(name="ZXQBYXL")
	public String getZxqbyxl()
	{
		return this.zxqbyxl;
	}

	public void setZxqbyxl(String zxqbyxl)
	{
		this.zxqbyxl = zxqbyxl;
	}

	@Column(name="JDBYXL")
	public String getJdbyxl()
	{
		return this.jdbyxl;
	}

	public void setJdbyxl(String jdbyxl)
	{
		this.jdbyxl = jdbyxl;
	}

	@Column(name="ZJBYXL")
	public String getZjbyxl()
	{
		return this.zjbyxl;
	}

	public void setZjbyxl(String zjbyxl)
	{
		this.zjbyxl = zjbyxl;
	}

	@Column(name="YQWCCBYXL")
	public String getYqwccbyxl()
	{
		return this.yqwccbyxl;
	}

	public void setYqwccbyxl(String yqwccbyxl)
	{
		this.yqwccbyxl = yqwccbyxl;
	}

	@Column(name="YQLSBYXL")
	public String getYqlsbyxl()
	{
		return this.yqlsbyxl;
	}

	public void setYqlsbyxl(String yqlsbyxl)
	{
		this.yqlsbyxl = yqlsbyxl;
	}

	@Column(name="YQSCCBYXL")
	public String getYqsccbyxl()
	{
		return this.yqsccbyxl;
	}

	public void setYqsccbyxl(String yqsccbyxl)
	{
		this.yqsccbyxl = yqsccbyxl;
	}

	@Column(name="JYZBYXL")
	public String getJyzbyxl()
	{
		return this.jyzbyxl;
	}

	public void setJyzbyxl(String jyzbyxl)
	{
		this.jyzbyxl = jyzbyxl;
	}

	@Column(name="JDPBYXL")
	public String getJdpbyxl()
	{
		return this.jdpbyxl;
	}

	public void setJdpbyxl(String jdpbyxl)
	{
		this.jdpbyxl = jdpbyxl;
	}

	@Column(name="YZBBYXL")
	public String getYzbbyxl()
	{
		return this.yzbbyxl;
	}

	public void setYzbbyxl(String yzbbyxl)
	{
		this.yzbbyxl = yzbbyxl;
	}

	@Column(name="FZBYXL")
	public String getFzbyxl()
	{
		return this.fzbyxl;
	}

	public void setFzbyxl(String fzbyxl)
	{
		this.fzbyxl = fzbyxl;
	}

	@Column(name="FZSBHZ")
	public String getFzsbhz()
	{
		return this.fzsbhz;
	}

	public void setFzsbhz(String fzsbhz)
	{
		this.fzsbhz = fzsbhz;
	}

	@Column(name="CCSBHZ")
	public String getCcsbhz()
	{
		return this.ccsbhz;
	}

	public void setCcsbhz(String ccsbhz)
	{
		this.ccsbhz = ccsbhz;
	}

	@Column(name="ZXQBYZX")
	public String getZxqbyzx()
	{
		return this.zxqbyzx;
	}

	public void setZxqbyzx(String zxqbyzx)
	{
		this.zxqbyzx = zxqbyzx;
	}

	@Column(name="JDBYZX")
	public String getJdbyzx()
	{
		return this.jdbyzx;
	}

	public void setJdbyzx(String jdbyzx)
	{
		this.jdbyzx = jdbyzx;
	}

	@Column(name="ZJBYZX")
	public String getZjbyzx()
	{
		return this.zjbyzx;
	}

	public void setZjbyzx(String zjbyzx)
	{
		this.zjbyzx = zjbyzx;
	}

	@Column(name="YQWCCBYZX")
	public String getYqwccbyzx()
	{
		return this.yqwccbyzx;
	}

	public void setYqwccbyzx(String yqwccbyzx)
	{
		this.yqwccbyzx = yqwccbyzx;
	}

	@Column(name="YQLSBYZX")
	public String getYqlsbyzx()
	{
		return this.yqlsbyzx;
	}

	public void setYqlsbyzx(String yqlsbyzx)
	{
		this.yqlsbyzx = yqlsbyzx;
	}

	@Column(name="YQSCCBYZX")
	public String getYqsccbyzx()
	{
		return this.yqsccbyzx;
	}

	public void setYqsccbyzx(String yqsccbyzx)
	{
		this.yqsccbyzx = yqsccbyzx;
	}

	@Column(name="JYZBYZX")
	public String getJyzbyzx()
	{
		return this.jyzbyzx;
	}

	public void setJyzbyzx(String jyzbyzx)
	{
		this.jyzbyzx = jyzbyzx;
	}

	@Column(name="JDPBYZX")
	public String getJdpbyzx()
	{
		return this.jdpbyzx;
	}

	public void setJdpbyzx(String jdpbyzx)
	{
		this.jdpbyzx = jdpbyzx;
	}

	@Column(name="YZBBYZX")
	public String getYzbbyzx()
	{
		return this.yzbbyzx;
	}

	public void setYzbbyzx(String yzbbyzx)
	{
		this.yzbbyzx = yzbbyzx;
	}

	@Column(name="FZBYZX")
	public String getFzbyzx()
	{
		return this.fzbyzx;
	}

	public void setFzbyzx(String fzbyzx)
	{
		this.fzbyzx = fzbyzx;
	}

	@Column(name="CCBYZX")
	public String getCcbyzx()
	{
		return this.ccbyzx;
	}

	public void setCcbyzx(String ccbyzx)
	{
		this.ccbyzx = ccbyzx;
	}

	@Column(name="CCBYXL")
	public String getCcbyxl()
	{
		return this.ccbyxl;
	}

	public void setCcbyxl(String ccbyxl)
	{
		this.ccbyxl = ccbyxl;
	}

	@Column(name="ZXQBYHZ")
	public String getZxqbyhz()
	{
		return this.zxqbyhz;
	}

	public void setZxqbyhz(String zxqbyhz)
	{
		this.zxqbyhz = zxqbyhz;
	}

	@Column(name="JDBYHZ")
	public String getJdbyhz()
	{
		return this.jdbyhz;
	}

	public void setJdbyhz(String jdbyhz)
	{
		this.jdbyhz = jdbyhz;
	}

	@Column(name="ZJBYHZ")
	public String getZjbyhz()
	{
		return this.zjbyhz;
	}

	public void setZjbyhz(String zjbyhz)
	{
		this.zjbyhz = zjbyhz;
	}

	@Column(name="YQWCCBYHZ")
	public String getYqwccbyhz()
	{
		return this.yqwccbyhz;
	}

	public void setYqwccbyhz(String yqwccbyhz)
	{
		this.yqwccbyhz = yqwccbyhz;
	}

	@Column(name="YQLSBYHZ")
	public String getYqlsbyhz()
	{
		return this.yqlsbyhz;
	}

	public void setYqlsbyhz(String yqlsbyhz)
	{
		this.yqlsbyhz = yqlsbyhz;
	}

	@Column(name="YQSCCBYHZ")
	public String getYqsccbyhz()
	{
		return this.yqsccbyhz;
	}

	public void setYqsccbyhz(String yqsccbyhz)
	{
		this.yqsccbyhz = yqsccbyhz;
	}

	@Column(name="JYZBYHZ")
	public String getJyzbyhz()
	{
		return this.jyzbyhz;
	}

	public void setJyzbyhz(String jyzbyhz)
	{
		this.jyzbyhz = jyzbyhz;
	}

	@Column(name="JDPBYHZ")
	public String getJdpbyhz()
	{
		return this.jdpbyhz;
	}

	public void setJdpbyhz(String jdpbyhz)
	{
		this.jdpbyhz = jdpbyhz;
	}

	@Column(name="YZBBYHZ")
	public String getYzbbyhz()
	{
		return this.yzbbyhz;
	}

	public void setYzbbyhz(String yzbbyhz)
	{
		this.yzbbyhz = yzbbyhz;
	}

	@Column(name="ZXQBYGQ")
	public String getZxqbygq()
	{
		return this.zxqbygq;
	}

	public void setZxqbygq(String zxqbygq)
	{
		this.zxqbygq = zxqbygq;
	}

	@Column(name="FZBYHZ")
	public String getFzbyhz()
	{
		return this.fzbyhz;
	}

	public void setFzbyhz(String fzbyhz)
	{
		this.fzbyhz = fzbyhz;
	}

	@Column(name="CCBYHZ")
	public String getCcbyhz()
	{
		return this.ccbyhz;
	}

	public void setCcbyhz(String ccbyhz)
	{
		this.ccbyhz = ccbyhz;
	}

	@Column(name="JDBYGQ")
	public String getJdbygq()
	{
		return this.jdbygq;
	}

	public void setJdbygq(String jdbygq)
	{
		this.jdbygq = jdbygq;
	}

	@Column(name="ZJBYGQ")
	public String getZjbygq()
	{
		return this.zjbygq;
	}

	public void setZjbygq(String zjbygq)
	{
		this.zjbygq = zjbygq;
	}

	@Column(name="YQWCCBYGQ")
	public String getYqwccbygq()
	{
		return this.yqwccbygq;
	}

	public void setYqwccbygq(String yqwccbygq)
	{
		this.yqwccbygq = yqwccbygq;
	}

	@Column(name="YQLSBYGQ")
	public String getYqlsbygq()
	{
		return this.yqlsbygq;
	}

	public void setYqlsbygq(String yqlsbygq)
	{
		this.yqlsbygq = yqlsbygq;
	}

	@Column(name="YQSCCBYGQ")
	public String getYqsccbygq()
	{
		return this.yqsccbygq;
	}

	public void setYqsccbygq(String yqsccbygq)
	{
		this.yqsccbygq = yqsccbygq;
	}

	@Column(name="JYZBYGQ")
	public String getJyzbygq()
	{
		return this.jyzbygq;
	}

	public void setJyzbygq(String jyzbygq)
	{
		this.jyzbygq = jyzbygq;
	}

	@Column(name="JDPBYGQ")
	public String getJdpbygq()
	{
		return this.jdpbygq;
	}

	public void setJdpbygq(String jdpbygq)
	{
		this.jdpbygq = jdpbygq;
	}

	@Column(name="YZBBYGQ")
	public String getYzbbygq()
	{
		return this.yzbbygq;
	}

	public void setYzbbygq(String yzbbygq)
	{
		this.yzbbygq = yzbbygq;
	}

	@Column(name="FZBYGQ")
	public String getFzbygq()
	{
		return this.fzbygq;
	}

	public void setFzbygq(String fzbygq)
	{
		this.fzbygq = fzbygq;
	}

	@Column(name="CCBYGQ")
	public String getCcbygq()
	{
		return this.ccbygq;
	}

	public void setCcbygq(String ccbygq)
	{
		this.ccbygq = ccbygq;
	}

	@Column(name="ZXQSBHZ")
	public String getZxqsbhz()
	{
		return this.zxqsbhz;
	}

	public void setZxqsbhz(String zxqsbhz)
	{
		this.zxqsbhz = zxqsbhz;
	}

	@Column(name="JDSBHZ")
	public String getJdsbhz()
	{
		return this.jdsbhz;
	}

	public void setJdsbhz(String jdsbhz)
	{
		this.jdsbhz = jdsbhz;
	}

	@Column(name="ZJSBHZ")
	public String getZjsbhz()
	{
		return this.zjsbhz;
	}

	public void setZjsbhz(String zjsbhz)
	{
		this.zjsbhz = zjsbhz;
	}

	@Column(name="YQWCCSBHZ")
	public String getYqwccsbhz()
	{
		return this.yqwccsbhz;
	}

	public void setYqwccsbhz(String yqwccsbhz)
	{
		this.yqwccsbhz = yqwccsbhz;
	}

	@Column(name="YQLSSBHZ")
	public String getYqlssbhz()
	{
		return this.yqlssbhz;
	}

	public void setYqlssbhz(String yqlssbhz)
	{
		this.yqlssbhz = yqlssbhz;
	}

	@Column(name="YQSCCSBHZ")
	public String getYqsccsbhz()
	{
		return this.yqsccsbhz;
	}

	public void setYqsccsbhz(String yqsccsbhz)
	{
		this.yqsccsbhz = yqsccsbhz;
	}

	@Column(name="JYZSBHZ")
	public String getJyzsbhz()
	{
		return this.jyzsbhz;
	}

	public void setJyzsbhz(String jyzsbhz)
	{
		this.jyzsbhz = jyzsbhz;
	}

	@Column(name="JDPSBHZ")
	public String getJdpsbhz()
	{
		return this.jdpsbhz;
	}

	public void setJdpsbhz(String jdpsbhz)
	{
		this.jdpsbhz = jdpsbhz;
	}

	@Column(name="YZBSBHZ")
	public String getYzbsbhz()
	{
		return this.yzbsbhz;
	}

	public void setYzbsbhz(String yzbsbhz)
	{
		this.yzbsbhz = yzbsbhz;
	}

	@Column(name="ZXQBYCZ")
	public String getZxqbycz()
	{
		return this.zxqbycz;
	}

	public void setZxqbycz(String zxqbycz)
	{
		this.zxqbycz = zxqbycz;
	}

	@Column(name="JDBYCZ")
	public String getJdbycz()
	{
		return this.jdbycz;
	}

	public void setJdbycz(String jdbycz)
	{
		this.jdbycz = jdbycz;
	}

	@Column(name="MONTH_TIME")
	public Date getMonthTime()
	{
		return this.monthTime;
	}

	public void setMonthTime(Date monthTime)
	{
		this.monthTime = monthTime;
	}

	@Column(name="ZXQSYD")
	public String getZxqsyd()
	{
		return this.zxqsyd;
	}

	public void setZxqsyd(String zxqsyd)
	{
		this.zxqsyd = zxqsyd;
	}

	@Column(name="JDSYD")
	public String getJdsyd()
	{
		return this.jdsyd;
	}

	public void setJdsyd(String jdsyd)
	{
		this.jdsyd = jdsyd;
	}

	@Column(name="ZJSYD")
	public String getZjsyd()
	{
		return this.zjsyd;
	}

	public void setZjsyd(String zjsyd)
	{
		this.zjsyd = zjsyd;
	}

	@Column(name="YQWCCSYD")
	public String getYqwccsyd()
	{
		return this.yqwccsyd;
	}

	public void setYqwccsyd(String yqwccsyd)
	{
		this.yqwccsyd = yqwccsyd;
	}

	@Column(name="YQLSSYD")
	public String getYqlssyd()
	{
		return this.yqlssyd;
	}

	public void setYqlssyd(String yqlssyd)
	{
		this.yqlssyd = yqlssyd;
	}

	@Column(name="YQSCCSYD")
	public String getYqsccsyd()
	{
		return this.yqsccsyd;
	}

	public void setYqsccsyd(String yqsccsyd)
	{
		this.yqsccsyd = yqsccsyd;
	}

	@Column(name="JYZSYD")
	public String getJyzsyd()
	{
		return this.jyzsyd;
	}

	public void setJyzsyd(String jyzsyd)
	{
		this.jyzsyd = jyzsyd;
	}

	@Column(name="YQWCCBYCZ")
	public String getYqwccbycz()
	{
		return this.yqwccbycz;
	}

	public void setYqwccbycz(String yqwccbycz)
	{
		this.yqwccbycz = yqwccbycz;
	}

	@Column(name="YQLSBYCZ")
	public String getYqlsbycz()
	{
		return this.yqlsbycz;
	}

	public void setYqlsbycz(String yqlsbycz)
	{
		this.yqlsbycz = yqlsbycz;
	}

	@Column(name="YQSCCBYCZ")
	public String getYqsccbycz()
	{
		return this.yqsccbycz;
	}

	public void setYqsccbycz(String yqsccbycz)
	{
		this.yqsccbycz = yqsccbycz;
	}

	@Column(name="JYZBYCZ")
	public String getJyzbycz()
	{
		return this.jyzbycz;
	}

	public void setJyzbycz(String jyzbycz)
	{
		this.jyzbycz = jyzbycz;
	}

	@Column(name="JDPBYCZ")
	public String getJdpbycz()
	{
		return this.jdpbycz;
	}

	public void setJdpbycz(String jdpbycz)
	{
		this.jdpbycz = jdpbycz;
	}

	@Column(name="YZBBYCZ")
	public String getYzbbycz()
	{
		return this.yzbbycz;
	}

	public void setYzbbycz(String yzbbycz)
	{
		this.yzbbycz = yzbbycz;
	}

	@Column(name="FZBYCZ")
	public String getFzbycz()
	{
		return this.fzbycz;
	}

	public void setFzbycz(String fzbycz)
	{
		this.fzbycz = fzbycz;
	}

	@Column(name="CCBYCZ")
	public String getCcbycz()
	{
		return this.ccbycz;
	}

	public void setCcbycz(String ccbycz)
	{
		this.ccbycz = ccbycz;
	}
	@Column(name="ZJBYCZ")
	public String getZjbycz() {
		return zjbycz;
	}

	public void setZjbycz(String zjbycz) {
		this.zjbycz = zjbycz;
	}

}
