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
import com.jshx.zywsaqglzd.entity.OccHeaMan;
import com.jshx.zywsaqglzd.service.OccHeaManService;
import com.jshx.zzqk.entity.IntSit;
import com.jshx.zzqk.service.IntSitService;

/**
 *职业卫生安全管理制度详情
 * 陆妍婷
 */
public class GetZywsaqglzdDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private OccHeaManService occHeaManService= (OccHeaManService)SpringContextHolder.getBean("occHeaManService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			OccHeaMan occHeaMan=occHeaManService.getById(id);
			if(null!=occHeaMan){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==occHeaMan.getSystemName()?"":occHeaMan.getSystemName());
				Map map = new HashMap();
				map.put("codeName", "职业卫生安全制度类型");
				map.put("itemValue", occHeaMan.getSystemType());
				json.put("type",null==codeService.findCodeValueByMap(map).getItemText()?"":codeService.findCodeValueByMap(map).getItemText());
				//json.put("intelType",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(occHeaMan.getTrainingTime()),"") + "~" + StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(occHeaMan.getTrainingTimeEnd()),""));
				json.put("time",null==occHeaMan.getUploadTime()?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(occHeaMan.getUploadTime())); 
				map.put("linkId",occHeaMan.getLinkid());
				map.put("mkType", "zywsaqglzd");
				map.put("picType","zdfj");
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