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
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzr.service.EntChaPerService;
import com.jshx.qyzyfzrpx.entity.MaiChaTra;
import com.jshx.qyzyfzrpx.service.MaiChaTraService;

/**
 * 安全生产负责人员培训详情
 * 陆妍婷
 */
public class GetAqscfzrypxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProChaTraService secProChaTraService= (SecProChaTraService) SpringContextHolder.getBean("secProChaTraService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SecProChaTra secProChaTra=secProChaTraService.getById(id);
			if(null!=secProChaTra){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("personName",null==secProChaTra.getTrainingPersonName()?"":secProChaTra.getTrainingPersonName());
				String time1=  null==secProChaTra.getTrainingTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(secProChaTra.getTrainingTime());
				String time2= null==secProChaTra.getTrainingTimeEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(secProChaTra.getTrainingTimeEnd());
				json.put("time", time1+ "~" + time2); 
				json.put("address",null==secProChaTra.getTrainingAddress()?"":secProChaTra.getTrainingAddress());
				json.put("teachTime",null==secProChaTra.getTrainingTeacheTime()?"":secProChaTra.getTrainingTeacheTime());
				json.put("teacher",null==secProChaTra.getTrainingTeacher()?"":secProChaTra.getTrainingTeacher());
				json.put("cardNum",null==secProChaTra.getTrainingCardnum()?"":secProChaTra.getTrainingCardnum());
				json.put("cardPickDate",null==secProChaTra.getTrainingCardPickDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(secProChaTra.getTrainingCardPickDate())); 
				json.put("cardValidity",null==secProChaTra.getTrainingCardValidity()?"":new SimpleDateFormat("yyyy-MM-dd").format(secProChaTra.getTrainingCardValidity())); 
				json.put("content",null==secProChaTra.getTrainingContent()?"":secProChaTra.getTrainingContent());
				json.put("fzdw",null==secProChaTra.getFzdw()?"":secProChaTra.getFzdw());
				Map map = new HashMap();
				map.put("linkId",secProChaTra.getLinkId());
				map.put("mkType", "aqscfzrypx");
				map.put("picType","pxfj");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("filePath",null==bean.getRname()?"":bean.getRname());
				//Map m = new HashMap();
				//m.put("codeName", "性别");
				//m.put("itemValue", secProChaTra.getChargeSex());
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