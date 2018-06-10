package com.jshx.http.command;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.qyzcyhglb.entity.Qyzcyhglb;
import com.jshx.qyzcyhglb.service.QyzcyhglbService;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.service.JshxZcyhsbService;

/**
 * 企业获取自查隐患列表
 * @author 陆婷 2013-11-6
 *
 */

public class GetZcyhOwnDetailCommand implements Command
{
	private JshxZcyhsbService jshxZcyhsbService = (JshxZcyhsbService) SpringContextHolder.getBean("jshxZcyhsbService");
	private QyzcyhglbService qyzcyhglbService = (QyzcyhglbService) SpringContextHolder.getBean("qyzcyhglbService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//获取id
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
			    
			    Map map = new HashMap();
				map.put("yhid",id);
				map.put("type", "1");
				Qyzcyhglb qyzcyhglb = new Qyzcyhglb();
			    List<Qyzcyhglb> list = qyzcyhglbService.findQyzcyhglb(map);
			    if(list.size() != 0)
			    {
			    	qyzcyhglb  = list.get(0);
			    }
			    jo.put("jcbw", StringTools.NullToStr(qyzcyhglb.getJcbw(),""));//被检查部位
			    String a[] = {" ","资质证照","安全生产管理机构及人员","安全生产责任制",
						"安全生产管理制度","安全操作规程","教育培训","安全生产管理档案","安全生产投入",
						"应急管理","特征设备基础管理","职业卫生基础管理","相关方基础管理","其他基础管理"};
			    String b[] = {" ","特种设备现场管理","生产设备设施及工艺","场所环境",
						"从业人员操作行为","消防安全","用电安全","职业卫生现场安全","有限空间现场安全",
						"辅助动力系统","相关方现场管理","其他现场管理"};
			    String yhdl = qyzcyhglb.getYhdl();
			    String yhzl = qyzcyhglb.getYhzl();
		    	if(yhdl != null)
			    {
			    	if("1".equals(yhdl))
			    	{
			    		yhdl = "基础管理";
			    		String s = yhzl.substring(2,3);
			    		System.out.println("!!!!!!!!!!!!!!!!!!!!!!" + s);
			    		yhzl = a[Integer.parseInt(s)];
			    	}
			    	else if("2".equals(yhdl))
			    	{
			    		yhdl = "现场管理";
			    		String s = yhzl.substring(2,3);
			    		yhzl = b[Integer.parseInt(s)];
			    	}
			    	else
			    	{
			    		yhdl = "";
			    	}
			    }
			    else
			    {
			    	yhdl = "";
			    }
			    jo.put("yhdl", yhdl);//隐患大类
			    jo.put("yhzl", yhzl);//隐患中类
			    jo.put("zgzrbm", StringTools.NullToStr(qyzcyhglb.getZgzrbm(),""));//整改责任部门
			    jo.put("zgzrr", StringTools.NullToStr(qyzcyhglb.getZgzrr(),""));//整改责任人
			    
			    map.put("linkId",bean.getLinkId());
				map.put("type","zgqtp1");
				DataBean bean01 = httpService.getPhotoListByType(map);
			    jo.put("zgqtp", StringTools.NullToStr(bean01.getRname(),""));//整改前图片
			   
			    map.put("type","zghtp1");
			    DataBean bean02 = httpService.getPhotoListByType(map);
			    jo.put("zghtp", StringTools.NullToStr(bean02.getRname(),""));//整改后图片
			    
			    
			    jo.put("jhwcsj", null!=bean.getJhwcsj()?StringTools.NullToStr(sdf.format(bean.getJhwcsj()),""):"");//计划完成时间
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
			    jo.put("id", id);//编号
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
