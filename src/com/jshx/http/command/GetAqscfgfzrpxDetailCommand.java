package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqscfgfzrpx.entity.ChargeTraining;
import com.jshx.aqscfgfzrpx.service.ChargeTrainingService;
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
 * 安全生产分管负责人培训详情
 * 陆妍婷
 */
public class GetAqscfgfzrpxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ChargeTrainingService chargeTrainingService = (ChargeTrainingService) SpringContextHolder.getBean("chargeTrainingService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			ChargeTraining chargeTraining=chargeTrainingService.getById(id);
			if(null!=chargeTraining){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("personName",null==chargeTraining.getTrainingPersonName()?"":chargeTraining.getTrainingPersonName());
				String time1=  null==chargeTraining.getTrainingTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(chargeTraining.getTrainingTime());
				String time2= null==chargeTraining.getTrainingTimeEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(chargeTraining.getTrainingTimeEnd());
				json.put("time", time1+ "~" + time2); 
				json.put("address",null==chargeTraining.getTrainingAddress()?"":chargeTraining.getTrainingAddress());
				json.put("teachTime",null==chargeTraining.getTrainingTeacheTime()?"":chargeTraining.getTrainingTeacheTime());
				json.put("teacher",null==chargeTraining.getTrainingteacher()?"":chargeTraining.getTrainingteacher());
				json.put("content",null==chargeTraining.getTrainingContent()?"":chargeTraining.getTrainingContent());
				json.put("cardNum",null==chargeTraining.getTrainingCardnum()?"":chargeTraining.getTrainingCardnum());
				json.put("cardPickDate",null==chargeTraining.getTrainingCardPickDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(chargeTraining.getTrainingCardPickDate())); 
				json.put("cardValidity",null==chargeTraining.getTrainingCardValidity()?"":new SimpleDateFormat("yyyy-MM-dd").format(chargeTraining.getTrainingCardValidity())); 
				json.put("fzdw",null==chargeTraining.getFzdw()?"":chargeTraining.getFzdw());//发证单位
				Map map = new HashMap();
				map.put("linkId",chargeTraining.getLinkId());
				map.put("mkType", "aqscfgfzrpx");
				map.put("picType","pxfj");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("filePath",null==bean.getRname()?"":bean.getRname());
				//Map m = new HashMap();
				//m.put("codeName", "性别");
				//m.put("itemValue", chargeTraining.getChargeSex());
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