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
import com.jshx.xygsgpx.entity.NewWorTra;
import com.jshx.xygsgpx.service.NewWorTraService;

/**
 * 新员工上岗培训详情
 * 陆妍婷
 */
public class GetXygsgpxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private NewWorTraService newWorTraService= (NewWorTraService)SpringContextHolder.getBean("newWorTraService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			 NewWorTra newWorTra=newWorTraService.getById(id);
			if(null!=newWorTra){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("workName",null==newWorTra.getTrainingName()?"":newWorTra.getTrainingName());
				Map map = new HashMap();
				String time1=  null==newWorTra.getTrainingTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(newWorTra.getTrainingTime());
				String time2= null==newWorTra.getTrainingTimeEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(newWorTra.getTrainingTimeEnd());
				json.put("time", time1+ "~" + time2); 
				json.put("persons",null==newWorTra.getTrainingPersons()?"":newWorTra.getTrainingPersons());
				json.put("content",null==newWorTra.getTrainingContent()?"":newWorTra.getTrainingContent());
				json.put("teacher",null==newWorTra.getTrainingTeacher()?"":newWorTra.getTrainingTeacher());
				json.put("remark",null==newWorTra.getTrainingRemark()?"":newWorTra.getTrainingRemark());
				map.put("linkId",newWorTra.getLinkId());
				map.put("mkType", "xygsgpx");
				map.put("picType","pxfj");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("filePath",null==bean.getRname()?"":bean.getRname());
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