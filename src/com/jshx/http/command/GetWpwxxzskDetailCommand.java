package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.wpwxxzsk.entity.RisKnoBas;
import com.jshx.wpwxxzsk.service.RisKnoBasService;

/**
 * 物品危险性知识库详情
 * @author 费谦 2015-10-13
 */
public class GetWpwxxzskDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private RisKnoBasService risKnoBasService = (RisKnoBasService) SpringContextHolder.getBean("risKnoBasService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			RisKnoBas risKnoBas=risKnoBasService.getById(id);
			if(null!=risKnoBas){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("itemName",null==risKnoBas.getItemName()?"":risKnoBas.getItemName()  );
				json.put("dangerContent",null==risKnoBas.getDangerousContent()?"":risKnoBas.getDangerousContent()  );
				json.put("measure",null==risKnoBas.getResponseMeasures()?"":risKnoBas.getResponseMeasures()  );
				json.put("remark",null==risKnoBas.getRemark()?"":risKnoBas.getRemark()  );
				
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("picType", "wpwxxzskfj");
				map.put("linkId", risKnoBas.getLinkId());
				map.put("mkType", "wpwxxzsk");
				DataBean bean = httpService.getPhotoListByType(map);
				String filepath = bean.getRname();
				json.put("filePath",null==filepath?"":filepath );
				
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