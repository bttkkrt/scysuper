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
import com.jshx.lbypkfgl.service.PpeWareManagService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.xgfgl.entity.PartyManage;
import com.jshx.xgfgl.service.PartyManageService;

/**
 * 相关方详情
 * @author 费谦 2015-10-13
 */
public class GetXgfDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private PartyManageService partyManageService = (PartyManageService) SpringContextHolder.getBean("partyManageService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			PartyManage partyManage=partyManageService.getById(id);
			if(null!=partyManage){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("partyName",null==partyManage.getPartyName()?"":partyManage.getPartyName()  );
				json.put("serviceType",null==partyManage.getServiceType()?"":partyManage.getServiceType()  );
				json.put("risk",null==partyManage.getBehaveRisk()?"":partyManage.getBehaveRisk()  );
				json.put("measures",null==partyManage.getControlMeasures()?"":partyManage.getControlMeasures()  );
				json.put("safeProDuty",null==partyManage.getSafetyProDuty()?"":partyManage.getSafetyProDuty()  );
				json.put("obligation",null==partyManage.getObligation()?"":partyManage.getObligation()  );
				json.put("remark",null==partyManage.getRemark()?"":partyManage.getRemark()  );
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("picType", "zzzmfj");
				map.put("linkId", partyManage.getLinkId());
				map.put("mkType", "xgfgl");
				DataBean bean1 = httpService.getPhotoListByType(map);
				String filepath1 = bean1.getRname();
				json.put("filePath",null==filepath1?"":filepath1 );
				map.put("picType","aqxyfj");
				DataBean bean2 = httpService.getPhotoListByType(map);//安全协议
				String filepath2 = bean2.getRname();
				json.put("filePath2",null==filepath2?"":filepath2 ); 
				map.put("picType","xcjcfj");
				DataBean bean3 = httpService.getPhotoListByType(map);//现场检查图片
				String filepath3 = bean3.getRname();
				json.put("filePath3",null==filepath3?"":filepath3 ); 
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