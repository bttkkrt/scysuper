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
import com.jshx.bzzpx.entity.TeaLeaTra;
import com.jshx.bzzpx.service.TeaLeaTraService;
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
 * 班组长培训培训详情
 * 陆妍婷
 */
public class GetBzzpxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private TeaLeaTraService teaLeaTraService= (TeaLeaTraService)SpringContextHolder.getBean("teaLeaTraService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			TeaLeaTra teaLeaTra=teaLeaTraService.getById(id);
			if(null!=teaLeaTra){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("workName",null==teaLeaTra.getTrainingWorkshopName()?"":teaLeaTra.getTrainingWorkshopName());
				json.put("personName",null==teaLeaTra.getTrainingName()?"":teaLeaTra.getTrainingName());
				Map map = new HashMap();
				map.put("codeName", "性别");
				map.put("itemValue", teaLeaTra.getTrainingSex());
				json.put("sex",null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText() );
				json.put("position",null==teaLeaTra.getTrainingPosition()?"":teaLeaTra.getTrainingPosition());
				json.put("phone",null==teaLeaTra.getTrainingPhone()?"":teaLeaTra.getTrainingPhone());
				String time1=  null==teaLeaTra.getTrainingTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(teaLeaTra.getTrainingTime());
				String time2= null==teaLeaTra.getTrainingTimeEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(teaLeaTra.getTrainingTimeEnd());
				json.put("time", time1+ "~" + time2); 
				json.put("content",null==teaLeaTra.getTrainingContent()?"":teaLeaTra.getTrainingContent());
				json.put("teacher",null==teaLeaTra.getTrainingTeacher()?"":teaLeaTra.getTrainingTeacher());
				json.put("address",null==teaLeaTra.getTrainingAddress()?"":teaLeaTra.getTrainingAddress());
				json.put("teachTime",null==teaLeaTra.getTrainingTeacheTime()?"":teaLeaTra.getTrainingTeacheTime());
				//map.put("linkId",teaLeaTra.getLinkId());
				//map.put("mkType", "qytzrypx");
				//map.put("picType","pxfj");
				//DataBean bean = httpService.getPhotoListByType(map);
				//json.put("filePath",null==bean.getRname()?"":bean.getRname());
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