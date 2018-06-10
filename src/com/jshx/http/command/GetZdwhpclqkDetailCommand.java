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
import com.jshx.zdwhpclqkb.entity.CheRes;
import com.jshx.zdwhpclqkb.service.CheResService;

/**
 * 重点危化品储量情况详情
 * @author 费谦 2015-10-13
 */
public class GetZdwhpclqkDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CheResService cheResService = (CheResService) SpringContextHolder.getBean("cheResService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			CheRes cheRes=cheResService.getById(id);
			if(null!=cheRes){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("dangerCheName",null==cheRes.getDangerousChemicalName()?"":cheRes.getDangerousChemicalName()  );
				json.put("riskGauge",null==cheRes.getRiskGauge()?"":cheRes.getRiskGauge()  );
				json.put("unNum",null==cheRes.getUnNumber()?"":cheRes.getUnNumber()  );
				json.put("attachment",null==cheRes.getAnnualUsage()?"":cheRes.getAnnualUsage()  );
				json.put("maxStoCapacity",null==cheRes.getMaximumStorageCapacity()?"":cheRes.getMaximumStorageCapacity()  );
				json.put("stroMode",null==cheRes.getStorageMode()?"":cheRes.getStorageMode()  );
				json.put("storLocation",null==cheRes.getStorageLocation()?"":cheRes.getStorageLocation()  );
				json.put("packing",null==cheRes.getPacking()?"":cheRes.getPacking()  );
				
				Map m = new HashMap();
				m.put("codeName", "是或否");
				m.put("itemValue", cheRes.getIsChemical());
				json.put("isChemical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				m.put("itemValue", cheRes.getIsRegulatorChemical());
				json.put("isRegulatorChemical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText()   );
				m.put("itemValue", cheRes.getIsExplosiveChemical());
				json.put("isExplosiceChemical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText()   );
				m.put("itemValue", cheRes.getIsToxicChemical());
				json.put("isToxicChemical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText()  );
				json.put("storage",null==cheRes.getStorage()?"":cheRes.getStorage()  );
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