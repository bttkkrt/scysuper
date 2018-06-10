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
import com.jshx.module.admin.service.CodeService;
import com.jshx.swwzxx.entity.MatInf;
import com.jshx.swwzxx.service.MatInfService;

/**
 * 涉危物质信息详情
 * @author 费谦 2015-10-13
 */
public class GetSwwzxxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private MatInfService matInfService = (MatInfService) SpringContextHolder.getBean("matInfService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			MatInf matInf=matInfService.getById(id);
			if(null!=matInf){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("danSouName",null==matInf.getDangerouSourceName()?"":matInf.getDangerouSourceName()  );
				//是否剧毒化学品
				Map m = new HashMap();
				m.put("codeName", "是或否");
				m.put("itemValue", matInf.getIfToxicChemicals());
				json.put("ifToxChemical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				json.put("device", null==matInf.getDevice()?"":matInf.getDevice() );
				json.put("criticalValue", null==matInf.getCriticalValue()?"":matInf.getCriticalValue() );
				json.put("hazProcess", null==matInf.getHazardousProcess()?"":matInf.getHazardousProcess() );
				json.put("tranNote", null==matInf.getTransportationNote()?"":matInf.getTransportationNote() );
				json.put("danCheName", null==matInf.getDangerousChemicalName()?"":matInf.getDangerousChemicalName() );
				json.put("riskGauge", null==matInf.getRiskGauge()?"":matInf.getRiskGauge() );
				json.put("existQuantity", null==matInf.getExistenceQuantity()?"":matInf.getExistenceQuantity() );
				json.put("corFactor", null==matInf.getCorrectionFactor()?"":matInf.getCorrectionFactor() );
				json.put("opePersonal", null==matInf.getOperatingPersonnel()?"":matInf.getOperatingPersonnel() );
				json.put("remark", null==matInf.getRemark()?"":matInf.getRemark() );
				
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