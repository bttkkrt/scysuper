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
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.gpdb.entity.Gpdb;

/**
 * 获取挂牌督办的列表
 * @author lht 2013-09-25
 *
 */

public class SuperviseHandlingListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map map = new HashMap();
		map.put("start", s);
		map.put("limit", l);
		
		map.put("userId", userId);
		List<Gpdb> superviseHandlingList = httpService.getSuperviseHandlingListByMap(map);//

		int total = httpService.getTotalNumberOfSupreviseHandlingByMap(map);//获取挂牌督办总条数
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray jsonArray = new JSONArray();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if(superviseHandlingList!=null&&!superviseHandlingList.isEmpty()){
					for(Gpdb item:superviseHandlingList){
						   JSONObject jsonObj = new JSONObject();
						   jsonObj.put("id", StringTools.NullToStr(item.getId(),""));//主键
						   jsonObj.put("szzname", StringTools.NullToStr(item.getSzzname(),""));//主键
						   jsonObj.put("qymc", StringTools.NullToStr(item.getQymc(),""));//检查企业名称
						   jsonObj.put("yhmc", StringTools.NullToStr(item.getYhmc(),""));//隐患名称
						   jsonObj.put("gpsj", null != item.getGpsj()?StringTools.NullToStr(sdf.format(item.getGpsj()),""):"");//挂牌时间
						 //隐患类别
						   jsonObj.put("yhlb",null != item.getYhlb()?companyService.findCompanyTypeNameByKey(item.getYhlb(),"402880484101fccb01412eadbcb009ec").toString():"");//隐患类别
						   jsonObj.put("zgwcsj", null != item.getZgwcsj()?StringTools.NullToStr(sdf.format(item.getZgwcsj()),""):"");//整改完成时间
						   jsonObj.put("yssj", null != item.getYssj()?StringTools.NullToStr(sdf.format(item.getYssj()),""):"");//验收时间
							//状态获取
							if (item.getState().equals("0")) {
								jsonObj.put("state","待整改");
							}else if (item.getState().equals("1")) {
								jsonObj.put("state","已整改待审核");
							}else if (item.getState().equals("2")) {
								jsonObj.put("state","审核不通过");
							}else {
								jsonObj.put("state","审核通过");
							}
						   jsonArray.add(jsonObj);
					}
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(jsonArray.toString());
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
