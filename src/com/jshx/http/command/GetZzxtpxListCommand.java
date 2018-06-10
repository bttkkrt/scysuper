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
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;

/**
 * 获取企业自查隐患列表
 * @author 陆婷 2013-09-25
 *
 */

public class GetZzxtpxListCommand implements Command
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
		
		List<JshxZzxtPxb> commons = httpService.getZzxtpxListByMap(map);//获取一般安全隐患列表信息

		
		int total = httpService.getZzxtpxListSizeByMap(map);//获取一般安全隐患总条数
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray ja = new JSONArray();
		try {
				if(commons!=null&&!commons.isEmpty()){
					for(JshxZzxtPxb bean:commons){
						   JSONObject jo = new JSONObject();
						   jo.put("personName", StringTools.NullToStr(bean.getPersonName(),""));//姓名
						   jo.put("sex", null!=bean.getSex()?companyService.findCompanyTypeNameByKey(bean.getSex(),"4ae6e5a340b8711b0140b89fe9840062"):"");//性别
						   jo.put("whcd", null!=bean.getWhcd()?companyService.findCompanyTypeNameByKey(bean.getWhcd(),"4028804840b9689c0140c45e4fa50341"):"");//文化程度
						   jo.put("csgw", StringTools.NullToStr(bean.getCsgw(),""));//从事岗位
						   jo.put("pxsj", null!=bean.getPxsj()?StringTools.NullToStr(sdf.format(bean.getPxsj()),""):"");//培训时间
						   jo.put("pxxs", StringTools.NullToStr(bean.getPxxs() ,""));//培训学时
						   jo.put("skr ", StringTools.NullToStr(bean.getSkr(),""));//授课人
						   jo.put("khcj", StringTools.NullToStr(bean.getKhcj(),""));//考核成绩
						   jo.put("pxnr", StringTools.NullToStr(bean.getPxnr(),""));//培训内容
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
