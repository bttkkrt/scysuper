package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.CodeService;
import com.jshx.qyzyfzr.entity.EntChaPer;
import com.jshx.qyzyfzr.service.EntChaPerService;

/**
 * 企业主要负责人详情
 * @author 费谦 2015-10-13
 */
public class GetQyzyfzrDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EntChaPerService entChaPerService = (EntChaPerService) SpringContextHolder.getBean("entChaPerService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			EntChaPer entChaPer=entChaPerService.getById(id);
			if(null!=entChaPer){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==entChaPer.getChargeName()?"":entChaPer.getChargeName()  );
				//性别
				Map m = new HashMap();
				m.put("codeName", "性别");
				m.put("itemValue", entChaPer.getChargeSex());
				json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//国籍
				json.put("nation", null==entChaPer.getChargeNationnality()?"":entChaPer.getChargeNationnality());
				//学历
				m.put("codeName", "学历");
				m.put("itemValue", entChaPer.getChargeHighestSchool());
				json.put("highSchool",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				//学位
				m.put("codeName", "学位");
				m.put("itemValue", entChaPer.getChargeHighestDegree());
				json.put("highDegree", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				json.put("school",null==entChaPer.getChargeSchool()?"":entChaPer.getChargeSchool());
				json.put("specialized", null==entChaPer.getChargeSpecialized()?"":entChaPer.getChargeSpecialized() );
				json.put("title", null==entChaPer.getChargeTitle()?"":entChaPer.getChargeTitle() );
				json.put("phone", null==entChaPer.getChargePhone()?"":entChaPer.getChargePhone() );
				json.put("email", null==entChaPer.getChargeEmail()?"":entChaPer.getChargeEmail() );
				json.put("address", null==entChaPer.getChargeAddress()?"":entChaPer.getChargeAddress() );
				json.put("dutyDate", null==entChaPer.getChargeDutyDate()?"":sdf.format(entChaPer.getChargeDutyDate()) );
				json.put("postdate", null==entChaPer.getChargePostDate()?"":sdf.format(entChaPer.getChargePostDate()) );
				json.put("specializeNum", null==entChaPer.getChargeSpecializedNum()?"":entChaPer.getChargeSpecializedNum() );
				json.put("remark",null==entChaPer.getChargeRemark()?"":entChaPer.getChargeRemark() );
				
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