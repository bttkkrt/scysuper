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
 * 质监局特种设备报验
 * @author 陆婷 2014-1-2
 *
 */

public class ZjjtzsbByCommand implements Command
{
	private ZjjtzsbService zjjtzsbService = (ZjjtzsbService) SpringContextHolder.getBean("zjjtzsbService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//获取质监局特种设备id
		try{
			Zjjtzsb	zjjtzsb = zjjtzsbService.getById(id);
			zjjtzsb.setState("1");
			zjjtzsb.setShbs("1");
			zjjtzsb.setIsFirst("1");
			zjjtzsb.setRemark("");
			zjjtzsbService.update(zjjtzsb);
			bd.setCode("0");
			bd.setMessage("报验成功");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("报验失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
