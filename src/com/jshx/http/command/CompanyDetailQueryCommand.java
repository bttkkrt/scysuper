package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
/**
 * 
 * @author gq
 * @date 2013-7-25
 * @category 企业详情查询
 * 修改：企业信息直接从备份表中获取 2013-08-06 by 陆婷
 */
public class CompanyDetailQueryCommand implements Command {
	protected Logger logger = LoggerFactory.getLogger(CompanyDetailQueryCommand.class);
	private CompanyService companyService = SpringContextHolder.getBean("companyService");

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject bean) {
		// TODO Auto-generated method stub
		SummaryBean bd = new SummaryBean();
		/**
		 *  单位id
		 */
		String companyId = bean.getString("companyId");
		JSONObject json = new JSONObject();
    	SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
		try {
			/**
			 * 根据id查询企业id
			 */
			Map map = new HashMap();
			map.put("companyId", companyId);
			CompanyBackUp companybu = companyService.getCompanyBackupById(map);	
			
			if(companybu != null)
			{
				/**
		    	 * 单位名称
		    	 */
		    	json.put("companyname", StringTools.NullToStr(companybu.getCompanyname(),""));
		    	/**
		    	 * 法定代表人
		    	 */
		    	json.put("fddbr", StringTools.NullToStr(companybu.getFddbr(),""));
		    	/**
		    	 * 法定代表人联系号码
		    	 */
		    	json.put("fddbrlxhm", StringTools.NullToStr(companybu.getFddbrlxhm(),""));
		    	/**
		    	 * 单位地址
		    	 */
		    	json.put("dwdz", StringTools.NullToStr(companybu.getDwdz(),""));
		    	/**
		    	 * 详细地址
		    	 */
		    	json.put("dwdz2", StringTools.NullToStr(companybu.getDwdz2(),""));
		    	/**
		    	 * 工商注册号
		    	 */
		    	json.put("gszch", StringTools.NullToStr(companybu.getGszch(),""));
				/**
				 * 企业类型
				 */
			   json.put("qylx", null!=companybu.getQylx()?companyService.findCompanyTypeNameByKey(companybu.getQylx(),"4028e56c3ff0d189013ff0e6b99e000c"):"");
		    	/**
		    	 * 行业分类
		    	 */
		    	json.put("hyfl", null!=companybu.getHyfl()?companyService.findCompanyTypeNameByKey(companybu.getHyfl(),"402880484076bce30140a04236590a02"):"");
		    	/**
		    	 * 安全管理员
		    	 */
		    	json.put("lxr", StringTools.NullToStr(companybu.getLxr(),""));
		    	/**
		    	 * 手机号码
		    	 */
		    	json.put("mobile",StringTools.NullToStr(companybu.getMobile(),""));
		    	/**
		    	 * 联系方式
		    	 */
		    	json.put("lxfs", StringTools.NullToStr(companybu.getLxfs(),""));
		    	/**
		    	 * 组织机构代码
		    	 */
		    	json.put("zzjgdm", StringTools.NullToStr(companybu.getZzjgdm(),""));
		    	
		    	/**
		    	 * 企业邮箱
		    	 */
		    	json.put("qyyx", StringTools.NullToStr(companybu.getQyyx(),""));
		    	
        		/**
		    	 * 企业规模
		    	 */
		    	json.put("qygm", null!=companybu.getQygm()?companyService.findCompanyTypeNameByKey(companybu.getQygm(),"4028e56c3ff0d189013ff0feee650023"):"");
		    	/**
		    	 * 企业注册类型
		    	 */
		    	json.put("qyzclx", null!=companybu.getQyzclx()?companyService.findCompanyTypeNameByKey(companybu.getQyzclx(),"402880484076bce30140a04025e509f7"):"");
		    	/**
		    	 * 注册资金（万元）
		    	 */
		    	json.put("zczj", StringTools.NullToStr(companybu.getZczj(),""));
		    	/**
		    	 * 是否危化品企业类型
		    	 */
		    	json.put("ifwhpqylx", (null!=companybu.getIfwhpqylx() && companybu.getIfwhpqylx().equals("1"))?"是":"否");
		    	/**
		    	 * 危化品企业类型
		    	 */
		    	String whpqylx = "";
		    	if(null!=companybu.getWhpqylx())
		    	{
		    		String[] a = companybu.getWhpqylx().replaceAll(" ", "").split(",");
		    		for(String b:a)
		    		{
		    			whpqylx += companyService.findCompanyTypeNameByKey(b,"4028e56c40a9a6750140a9c91e2f0007") + ",";
		    		}
		    		if(whpqylx.length() > 1)
		    		{
		    			whpqylx = whpqylx.substring(0,whpqylx.length()-1);
		    		}
		    	}
		    	
		    	json.put("whpqylx", whpqylx);
		    	/**
		    	 * 是否职业危害企业类型
		    	 */
		    	json.put("ifzywhqylx", (null!=companybu.getIfzywhqylx() && companybu.getIfzywhqylx().equals("1"))?"是":"否");
		    	/**
		    	 * 职业危害企业类型
		    	 */
		    	String zywhqylx = "";
		    	if(null!=companybu.getZywhqylx())
		    	{
		    		String[] a = companybu.getZywhqylx().replaceAll(" ", "").split(",");
		    		for(String b:a)
		    		{
		    			zywhqylx += companyService.findCompanyTypeNameByKey(b,"4028e56c3ff0d189013ff1096cbc0037") + ",";
		    		}
		    		if(zywhqylx.length() > 1)
		    		{
		    			zywhqylx = zywhqylx.substring(0,zywhqylx.length()-1);
		    		}
		    	}
		    	json.put("zywhqylx", zywhqylx);
		    	/**
		    	 * 是否烟花爆竹企业
		    	 */
		    	json.put("ifyhbzjyqy", (null!=companybu.getIfyhbzjyqy() && companybu.getIfyhbzjyqy().equals("1"))?"是":"否");
		    	/**
		    	 * 烟花爆竹企业
		    	 */
		    	String yhbzjyqy = "";
		    	if(null!=companybu.getYhbzjyqy())
		    	{
		    		String[] a = companybu.getYhbzjyqy().replaceAll(" ", "").split(",");
		    		for(String b:a)
		    		{
		    			yhbzjyqy += companyService.findCompanyTypeNameByKey(b,"4028804840b9689c0140c440505a033b") + ",";
		    		}
		    		if(zywhqylx.length() > 1)
		    		{
		    			yhbzjyqy = yhbzjyqy.substring(0,yhbzjyqy.length()-1);
		    		}
		    	}
		    	json.put("yhbzjyqy", yhbzjyqy);
		    	/**
		    	 * 企业成立时间
		    	 */
		        if(null!=companybu.getQyclsj())
		        	json.put("qyclsj", sdf.format(companybu.getQyclsj()));
		        else
		        	json.put("qyclsj", "");
		    	/**
		    	 * 固定电话
		    	 */
		    	json.put("gddh", StringTools.NullToStr(companybu.getGddh(),""));
		    	/**
		    	 * 传       真
		    	 */
		    	json.put("cz", StringTools.NullToStr(companybu.getCz(),""));
		    	/**
		    	 * 邮政编码
		    	 */
		    	json.put("yzbm", StringTools.NullToStr(companybu.getYzbm(),""));
		    	/**
		    	 * 上年销售收入（万元）
		    	 */
		    	json.put("snxssr", StringTools.NullToStr(companybu.getSnxssr(),""));
		    	/**
		    	 * 上年上交税收（万元）
		    	 */
		    	json.put("snsjss", StringTools.NullToStr(companybu.getSnsjss(),""));
		    	/**
		    	 * 上年固定资产
		    	 */
		    	json.put("sngdzc", StringTools.NullToStr(companybu.getSngdzc(),""));
		    	/**
		    	 * 上年安全投入（万元）
		    	 */
		    	json.put("snwqtr", StringTools.NullToStr(companybu.getSnwqtr(),""));
		    	/**
		    	 * 上年安全生产费用提取数（万元）
		    	 */
		    	json.put("snaqscf", StringTools.NullToStr(companybu.getSnaqscf(),""));
		    	/**
		    	 * 是否设立安全管理机构
		    	 */
		    	json.put("sfaqjg", null!=companybu.getSfaqjg()?companyService.findCompanyTypeNameByKey(companybu.getSfaqjg(),"402809812e7f8c28012e7fa82239000c"):"");
		    	/**
		    	 * 安全管理员（人）
		    	 */
		    	json.put("aqglr", null!=companybu.getAqglr()?companybu.getAqglr():"");
		    	/**
		    	 * 是否设立职业卫生管理机构
		    	 */
		    	json.put("sfzywsjg", null!=companybu.getSfzywsjg()?companyService.findCompanyTypeNameByKey(companybu.getSfzywsjg(),"402809812e7f8c28012e7fa82239000c"):"");
		    	/**
		    	 * 职业卫生管理人员（人）
		    	 */
		    	json.put("zywsglry", null!=companybu.getZywsglry()?companybu.getZywsglry():"");
		    	/**
		    	 * 否为专职或兼职职业卫生管理员
		    	 */
		    	json.put("sfqzwsgly", null!=companybu.getSfqzwsgly()?companyService.findCompanyTypeNameByKey(companybu.getSfqzwsgly(),"402809812e7f8c28012e7fa82239000c"):"");
		    	/**
		    	 * 占地面积（m2）
		    	 */
		    	json.put("zdmj", null!=companybu.getZdmj()?companybu.getZdmj():"");
		    	
		    	/**
		    	 * 建筑面积（m2）
		    	 */
		    	json.put("jzmj", null!=companybu.getJzmj()?companybu.getJzmj():"");
		    	/**
		    	 * 从业人员（人）
		    	 */
		    	json.put("cyry", null!=companybu.getCyry()?companybu.getCyry():"");
		    	/**
		    	 * 是否有员工宿舍
		    	 */
		    	json.put("sfyygss", null!=companybu.getSfyygss()?companyService.findCompanyTypeNameByKey(companybu.getSfyygss(),"402809812e7f8c28012e7fa82239000c"):"");
		    	/**
		    	 * 安全生产标准化达标级别
		    	 */
		    	json.put("aqbzdbjb", null!=companybu.getAqbzdbjb()?companyService.findCompanyTypeNameByKey(companybu.getAqbzdbjb(),"402880484028bef601404c8b68a40047"):"");
		    	
		    	/**
		    	 * 经营范围
		    	 */
		    	json.put("jyfw", StringTools.NullToStr(companybu.getJyfw(),""));
		    	/**
		    	 * 是否有网址
		    	 */
		    	json.put("ifurl", (null!=companybu.getIfurl() && companybu.getIfurl().equals("1"))?"有":"无");
		    	/**
		    	 * 网址
		    	 */
		    	json.put("url", StringTools.NullToStr(companybu.getUrl(),""));
		    	bd.setContent(json.toString());
				bd.setCode("0");
			}
			else
			{
				bd.setCode("1");
				bd.setMessage("无企业信息");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			bd.setCode("1");
			bd.setMessage("查询异常");
			e.printStackTrace();
		}

		return bd;
	}
}
