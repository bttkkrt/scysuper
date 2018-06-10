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
import com.jshx.aqscfzrypx.entity.SecProChaTra;
import com.jshx.aqscfzrypx.service.SecProChaTraService;
import com.jshx.aqscgljlpx.entity.ManagerTraining;
import com.jshx.aqscgljlpx.service.ManagerTrainingService;
import com.jshx.aqsczjpx.entity.MajordomoTraining;
import com.jshx.aqsczjpx.service.MajordomoTrainingService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.qytzrypx.entity.SpeJobTra;
import com.jshx.qytzrypx.service.SpeJobTraService;
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzr.service.EntChaPerService;
import com.jshx.qyzyfzrpx.entity.MaiChaTra;
import com.jshx.qyzyfzrpx.service.MaiChaTraService;

/**
 * 企业特种人员培训详情
 * 陆妍婷
 */
public class GetQytzrypxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SpeJobTraService speJobTraService= (SpeJobTraService)SpringContextHolder.getBean("speJobTraService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SpeJobTra speJobTra=speJobTraService.getById(id);
			if(null!=speJobTra){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("personName",null==speJobTra.getTrainingPersonName()?"":speJobTra.getTrainingPersonName());
				String time1=  null==speJobTra.getTrainingTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(speJobTra.getTrainingTime());
				String time2= null==speJobTra.getTrainingTimeEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(speJobTra.getTrainingTimeEnd());
				json.put("time", time1+ "~" + time2); 
				json.put("address",null==speJobTra.getTrainingAddress()?"":speJobTra.getTrainingAddress());
				json.put("teachTime",null==speJobTra.getTrainingTeacheTime()?"":speJobTra.getTrainingTeacheTime());
				json.put("teacher",null==speJobTra.getTrainingTeacher()?"":speJobTra.getTrainingTeacher());
				json.put("cardNum",null==speJobTra.getTrainingCardnum()?"":speJobTra.getTrainingCardnum());
				json.put("cardPickDate",null==speJobTra.getTrainingCardPickDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(speJobTra.getTrainingCardPickDate())); 
				json.put("cardValidity",null==speJobTra.getTrainingCardValidity()?"":new SimpleDateFormat("yyyy-MM-dd").format(speJobTra.getTrainingCardValidity())); 
				json.put("content",null==speJobTra.getTrainingContent()?"":speJobTra.getTrainingContent());
				json.put("remark",null==speJobTra.getTrainingRemark()?"":speJobTra.getTrainingRemark());
				json.put("fzdw",null==speJobTra.getFzdw()?"":speJobTra.getFzdw());
				Map map = new HashMap();
				map.put("linkId",speJobTra.getLinkId());
				map.put("mkType", "qytzrypx");
				map.put("picType","pxfj");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("filePath",null==bean.getRname()?"":bean.getRname());
				//Map m = new HashMap();
				//m.put("codeName", "性别");
				//m.put("itemValue", speJobTra.getChargeSex());
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