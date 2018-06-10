package com.jshx.http.command;


import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.service.JshxZcyhsbService;

/**
 * 获取企业自查隐患列表
 * @author 陆婷 2013-09-25
 *
 */

public class GetZcyhDetailCommand implements Command
{
	private JshxZcyhsbService jshxZcyhsbService = (JshxZcyhsbService) SpringContextHolder.getBean("jshxZcyhsbService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//获取用户的id
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			JshxZcyhsb bean = jshxZcyhsbService.getById(id);
			if(bean != null)
			{
			    JSONObject jo = new JSONObject();
			    String jclb = bean.getJclb();
			    if(jclb != null)
			    {
			    	if("0".equals(jclb))
			    	{
			    		jclb = "企业自查";
			    	}
			    	else if("1".equals(jclb))
			    	{
			    		jclb = "专家检查";
			    	}
			    	else
			    	{
			    		jclb = "企业互评互查";
			    	}
			    }
			    else
			    {
			    	jclb = "";
			    }
			    jo.put("jclb", jclb);//检查类别
			    jo.put("jcsj", null!=bean.getJcsj()?StringTools.NullToStr(sdf.format(bean.getJcsj()),""):"");//检查时间
			    jo.put("jcry", StringTools.NullToStr(bean.getJcry(),""));//检查人员
			    jo.put("yhsl", StringTools.NullToStr(bean.getYhsl(),""));//隐患数量
			    jo.put("jhwcsj", null!=bean.getJhwcsj()?StringTools.NullToStr(sdf.format(bean.getJhwcsj()),""):"");//计划完成时间
			    
			    String mqzt = bean.getMqzt();
			    if(mqzt != null)
			    {
				   if("0".equals(mqzt))
				   {
					   mqzt  = "整改完成";
				   }
				   else
				   {
					   mqzt = "整改中";
				   }
			    }
			    else
			    {
				    mqzt = "";
			    }
			    jo.put("mqzt", mqzt);//目前状态
			    jo.put("fcsj", StringTools.NullToStr(bean.getFcsj(),""));//复查时间
			    jo.put("fcr", StringTools.NullToStr(bean.getFcr(),""));//复查人
			    String jb = bean.getJb();
			    if(jb != null)
			    {
			    	if("0".equals(jb))
			    	{
			    		jb = "一般隐患";
			    	}
			    	else if("1".equals(jb))
			    	{
			    		jb = "一级隐患";
			    	}
			    	else if("2".equals(jb))
			    	{
			    		jb = "二级隐患";
			    	}
			    	else
			    	{
			    		jb = "三级隐患";
			    	}
			    }
			    else
			    {
			    	jb = "";
			    }
			    jo.put("jb", jb);//级别
			    jo.put("zgtrzj", StringTools.NullToStr(bean.getZgtrzj(),""));//整改投入资金(万元)
			    
			    jo.put("csfa", StringTools.NullToStr(bean.getCsfa(),""));//具体情况及整改措施方案
			    jo.put("wtyh", StringTools.NullToStr(bean.getWtyh(),""));//存在问题和隐患
			    jo.put("fcysqk", StringTools.NullToStr(bean.getFcysqk(),""));//复查验收情况
				bd.setCode("0");
				bd.setMessage("查询成功");
				bd.setContent(jo.toString());
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果");
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
