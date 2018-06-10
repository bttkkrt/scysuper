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
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzr.service.EntChaPerService;
import com.jshx.qyzyfzrpx.entity.MaiChaTra;
import com.jshx.qyzyfzrpx.service.MaiChaTraService;

/**
 * 企业主要负责人培训详情
 * 陆妍婷
 */
public class GetQyzyfzrpxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private MaiChaTraService maiChaTraService = (MaiChaTraService) SpringContextHolder.getBean("maiChaTraService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			MaiChaTra maiChaTra=maiChaTraService.getById(id);
			if(null!=maiChaTra){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("personName",null==maiChaTra.getTrainingPersonName()?"":maiChaTra.getTrainingPersonName());
				String time1=  null==maiChaTra.getTrainingTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingTime());
				String time2= null==maiChaTra.getTrainingTimeEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingTimeEnd());
				json.put("time", time1+ "~" + time2); 
				json.put("address",null==maiChaTra.getTrainingAddress()?"":maiChaTra.getTrainingAddress());
				json.put("teachTime",null==maiChaTra.getTrainingTeacheTime()?"":maiChaTra.getTrainingTeacheTime());
				json.put("teacher",null==maiChaTra.getTrainingTeacher()?"":maiChaTra.getTrainingTeacher());
				json.put("content",null==maiChaTra.getTrainingContent()?"":maiChaTra.getTrainingContent());
				json.put("cardNum",null==maiChaTra.getTrainingCardnum()?"":maiChaTra.getTrainingCardnum());
				json.put("cardPickDate",null==maiChaTra.getTrainingCardPickDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingCardPickDate())); 
				json.put("cardValidity",null==maiChaTra.getTrainingCardValidity()?"":new SimpleDateFormat("yyyy-MM-dd").format(maiChaTra.getTrainingCardValidity())); 
				json.put("fzdw",null==maiChaTra.getFzdw()?"":maiChaTra.getFzdw());
				Map map = new HashMap();
				map.put("linkId",maiChaTra.getLinkId());
				map.put("mkType", "qyzyfzrpx");
				map.put("picType","pxfj");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("filePath",null==bean.getRname()?"":bean.getRname());
				//Map m = new HashMap();
				//m.put("codeName", "性别");
				//m.put("itemValue", maiChaTra.getChargeSex());
				//json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
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