package com.jshx.http.command;


import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsb.service.ZjjtzsbService;

/**
 * 质监局特种设备核实结果
 * @author 陆婷 2014-1-2
 *
 */

public class ZjjtzsbShenheCommand implements Command
{
	private ZjjtzsbService zjjtzsbService = (ZjjtzsbService) SpringContextHolder.getBean("zjjtzsbService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//编号
		String zgqk = json.getString("zgqk"); //整改情况
		String zgwcsj = json.getString("zgwcsj"); //整改完成时间
		try {
			Zjjtzsb zjjtzsb = zjjtzsbService.getById(id);
    		zjjtzsb.setState("2");
    		zjjtzsb.setShbs("2");
    		zjjtzsb.setIsFirst("1");
    		zjjtzsb.setZgqk(zgqk);
    		zjjtzsb.setZgwcsj(zgwcsj);
			zjjtzsbService.update(zjjtzsb);
			JSONObject jo = new JSONObject();
			jo.put("linkId", zjjtzsb.getLinkId());
			bd.setContent(jo.toString());
			bd.setCode("0");
			bd.setMessage("上报成功");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
