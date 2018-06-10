package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.zzbw.entity.JshxZzbw;

/**
 * 获取企业自查隐患列表
 * @author 陆婷 2013-09-25
 *
 */

public class GetZzbwListCommand implements Command
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
		
		List<JshxZzbw> commons = httpService.getZzbwListByMap(map);//获取关键装置部位列表

		
		int total = httpService.getZzbwListSizeByMap(map);//获取关键装置部位列表
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray ja = new JSONArray();
		try {
				if(commons!=null&&!commons.isEmpty()){
					for(JshxZzbw bean:commons){
						   JSONObject jo = new JSONObject();
						   jo.put("gjzzmc", StringTools.NullToStr(bean.getGjzzmc(),""));//装置、重点部位
						   jo.put("zywxys", StringTools.NullToStr(bean.getZywxys(),""));//主要危险因素
						   jo.put("zrr", StringTools.NullToStr(bean.getZrr(),""));//责任人
						   jo.put("gwygsl", StringTools.NullToStr(bean.getGwygsl(),""));//岗位员工数量
						   Map m = new HashMap();
						   m.put("type", "zzbw");
						   m.put("linkId", bean.getProId());
						   DataBean bean01 = httpService.getPhotoListByType(m);
						   jo.put("aqczgc", StringTools.NullToStr(bean01.getRname(),""));//安全操作规程
						   m.put("type", "zzbwtp");
						   DataBean bean02 = httpService.getPhotoListByType(m);
						   jo.put("xctp", StringTools.NullToStr(bean02.getRname(),""));//现场图片
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
