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
import com.jshx.qynbqypx.entity.AllMenTra;
import com.jshx.qynbqypx.service.AllMenTraService;
import com.jshx.qytzrypx.entity.SpeJobTra;
import com.jshx.qytzrypx.service.SpeJobTraService;
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzr.service.EntChaPerService;
import com.jshx.qyzyfzrpx.entity.MaiChaTra;
import com.jshx.qyzyfzrpx.service.MaiChaTraService;
import com.jshx.zzqk.entity.IntSit;
import com.jshx.zzqk.service.IntSitService;

/**
 *资质情况详情
 * 陆妍婷
 */
public class GetZzqkDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private IntSitService intSitService= (IntSitService)SpringContextHolder.getBean("intSitService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			IntSit intSit=intSitService.getById(id);
			if(null!=intSit){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("intelCardNum",null==intSit.getIntelligenceCardnum()?"":intSit.getIntelligenceCardnum());
				Map map = new HashMap();
				//map.put("codeName", "性别");
				//map.put("itemValue", intSit.getTrainingSex());
				//json.put("sex",null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText() );
				json.put("intelCardName",null==intSit.getIntelligenceCardname()?"":intSit.getIntelligenceCardname());
				map.put("codeName", "资质类型");
				map.put("itemValue", intSit.getIntelligenceType());
				json.put("intelType",null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				//json.put("intelType",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(intSit.getTrainingTime()),"") + "~" + StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(intSit.getTrainingTimeEnd()),""));
				json.put("quaLevel",null==intSit.getZzjb()?"":intSit.getZzjb());
				json.put("bussinessScope",null==intSit.getBussinessScope()?"":intSit.getBussinessScope());
				json.put("intelContent",null==intSit.getIntelligenceContent()?"":intSit.getIntelligenceContent());
				json.put("intelInstitution",null==intSit.getIntelligenceInstitution()?"":intSit.getIntelligenceInstitution());
				json.put("intelCardDate",null==intSit.getIntelligenceCardDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceCardDate())); 
				json.put("changeDate",null==intSit.getChangeDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getChangeDate())); 
				json.put("intelValiStart",null==intSit.getIntelligenceValidityStart()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceValidityStart())); 
				json.put("state",null==intSit.getAuditState()?"":intSit.getAuditState());
				map.put("linkId",intSit.getLinkId());
				map.put("mkType", "zzqk");
				map.put("picType","zzfj");
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