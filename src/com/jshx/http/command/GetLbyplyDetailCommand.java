package com.jshx.http.command;

import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.lbyplygl.entity.PpeUseManage;
import com.jshx.lbyplygl.service.PpeUseManageService;

/**
 * 劳保用品领用详情
 * @author 费谦 2015-10-13
 */
public class GetLbyplyDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private PpeUseManageService ppeUseManageService = (PpeUseManageService) SpringContextHolder.getBean("ppeUseManageService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			PpeUseManage ppeUseManage=ppeUseManageService.getById(id);
			if(null!=ppeUseManage){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==ppeUseManage.getPpeName()?"":ppeUseManage.getPpeName()  );
				json.put("num",null==ppeUseManage.getPpeUseNum()?"":ppeUseManage.getPpeUseNum());
				json.put("people", null==ppeUseManage.getPpeUsePeople()?"":ppeUseManage.getPpeUsePeople() );
				json.put("time", null==ppeUseManage.getPpeUseTime()?"":sdf.format(ppeUseManage.getPpeUseTime()) );
				json.put("remark", null==ppeUseManage.getPpeUseRemark()?"":ppeUseManage.getPpeUseRemark() );
				bd.setContent(json.toString());
				
			}else{
				bd.setCode("1");
				bd.setMessage("无数据");
			}
		}catch(Exception e){
			bd.setCode("2");
			bd.setMessage("异常");
			e.printStackTrace();
		}
		System.out.println(bd.toString());
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}