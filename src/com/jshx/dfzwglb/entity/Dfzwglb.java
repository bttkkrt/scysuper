package com.jshx.dfzwglb.entity;

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
@Table(name="DFZWGLB")
public class Dfzwglb extends BaseModel
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
	 * 非煤矿山
	 */
	private String num1;

	/**
	 * 道路交通
	 */
	private String num2;

	/**
	 * 水上交通
	 */
	private String num3;

	/**
	 * 建筑施工
	 */
	private String num4;

	/**
	 * 消防
	 */
	private String num5;

	/**
	 * 危化品
	 */
	private String num6;

	/**
	 * 烟花爆竹
	 */
	private String num7;

	/**
	 * 民爆物品
	 */
	private String num8;

	/**
	 * 冶金
	 */
	private String num9;

	/**
	 * 其他
	 */
	private String num10;


	/**
	 * 关联id
	 */
	private String proid;

	/**
	 * 类型
	 */
	private String linktype;


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

	
	@Column(name="NUM1")
	public String getNum1()
	{
		return this.num1;
	}

	public void setNum1(String num1)
	{
		if(num1 == null || "".equals(num1))
		{
			this.num1 = "0";
		}
		else
		{
			this.num1 = num1;
		}
	}

	@Column(name="NUM2")
	public String getNum2()
	{
		return this.num2;
	}

	public void setNum2(String num2)
	{
		if(num2 == null || "".equals(num2))
		{
			this.num2 = "0";
		}
		else
		{
			this.num2 = num2;
		}
	}

	@Column(name="NUM3")
	public String getNum3()
	{
		return this.num3;
	}

	public void setNum3(String num3)
	{
		if(num3 == null || "".equals(num3))
		{
			this.num3 = "0";
		}
		else
		{
			this.num3 = num3;
		}
	}

	@Column(name="NUM4")
	public String getNum4()
	{
		return this.num4;
	}

	public void setNum4(String num4)
	{
		if(num4 == null || "".equals(num4))
		{
			this.num4 = "0";
		}
		else
		{
			this.num4 = num4;
		}
	}

	@Column(name="NUM5")
	public String getNum5()
	{
		return this.num5;
	}

	public void setNum5(String num5)
	{
		if(num5 == null || "".equals(num5))
		{
			this.num5 = "0";
		}
		else
		{
			this.num5 = num5;
		}
	}

	@Column(name="NUM6")
	public String getNum6()
	{
		return this.num6;
	}

	public void setNum6(String num6)
	{
		if(num6 == null || "".equals(num6))
		{
			this.num6 = "0";
		}
		else
		{
			this.num6 = num6;
		}
	}

	@Column(name="NUM7")
	public String getNum7()
	{
		return this.num7;
	}

	public void setNum7(String num7)
	{
		if(num7 == null || "".equals(num7))
		{
			this.num7 = "0";
		}
		else
		{
			this.num7 = num7;
		}
	}

	@Column(name="NUM8")
	public String getNum8()
	{
		return this.num8;
	}

	public void setNum8(String num8)
	{
		if(num8 == null || "".equals(num8))
		{
			this.num8 = "0";
		}
		else
		{
			this.num8 = num8;
		}
	}

	@Column(name="NUM9")
	public String getNum9()
	{
		return this.num9;
	}

	public void setNum9(String num9)
	{
		if(num9 == null || "".equals(num9))
		{
			this.num9 = "0";
		}
		else
		{
			this.num9 = num9;
		}
	}

	@Column(name="NUM10")
	public String getNum10()
	{
		return this.num10;
	}

	public void setNum10(String num10)
	{
		if(num10 == null || "".equals(num10))
		{
			this.num10 = "0";
		}
		else
		{
			this.num10 = num10;
		}
	}

	@Column(name="PROID")
	public String getProid()
	{
		return this.proid;
	}

	public void setProid(String proid)
	{
		this.proid = proid;
	}

	@Column(name="LINKTYPE")
	public String getLinktype()
	{
		return this.linktype;
	}

	public void setLinktype(String linktype)
	{
		this.linktype = linktype;
	}

}
