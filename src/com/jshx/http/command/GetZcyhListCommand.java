package com.jshx.http.command;


import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.zcyhsb.entity.JshxZcyhsb;

/**
 * 获取企业自查隐患列表
 * @author 陆婷 2013-09-25
 *
 */

public class GetZcyhListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String companyId = json.getString("companyId");//获取用户的id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map mm = new HashMap();
		mm.put("companyId", companyId);
		CompanyBackUp comb = companyService.getCompanyBackupById(mm);
		Map map = new HashMap();
		map.put("start", s);
		map.put("limit", l);
		map.put("qyid", comb.getId());
		map.put("companyid", comb.getCompanyId());
		
		List<JshxZcyhsb> commons = httpService.getZcyhListByMap(map);//获取企业自查隐患列表

		
		int total = httpService.getZcyhListSizeByMap(map);//获取企业自查隐患列表总条数
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
		JSONArray ja = new JSONArray();
		try {
				if(commons!=null&&!commons.isEmpty()){
					for(JshxZcyhsb bean:commons){
						   JSONObject jo = new JSONObject();
						   jo.put("id", StringTools.NullToStr(bean.getId(),""));//主键
						   jo.put("jcsj", null!=bean.getJcsj()?StringTools.NullToStr(sdf.format(bean.getJcsj()),""):"");//检查企业名称
						   jo.put("jcry", StringTools.NullToStr(bean.getJcry(),""));//检查人员
						   jo.put("jhwcsj", null!=bean.getJhwcsj()?StringTools.NullToStr(sdf.format(bean.getJhwcsj()),""):"");//检查时间
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
						   jo.put("mqzt", mqzt);//关联id
						   ja.add(jo);
					}
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(ja.toString());
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
