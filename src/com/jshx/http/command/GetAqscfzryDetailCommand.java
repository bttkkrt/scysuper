package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqscfzry.entity.SecProChaPer;
import com.jshx.aqscfzry.service.SecProChaPerService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.CodeService;

/**
 * 安全生产负责人员详情
 * @author 费谦 2015-10-13
 */
public class GetAqscfzryDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProChaPerService secProChaPerService = (SecProChaPerService) SpringContextHolder.getBean("secProChaPerService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SecProChaPer secProChaPer=secProChaPerService.getById(id);
			if(null!=secProChaPer){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==secProChaPer.getChargeName()?"":secProChaPer.getChargeName()  );
				//性别
				Map m = new HashMap();
				m.put("codeName", "性别");
				m.put("itemValue", secProChaPer.getChargeSex());
				json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//国籍
				json.put("nation", null==secProChaPer.getChargeNationnality()?"":secProChaPer.getChargeNationnality());
				//学历
				m.put("codeName", "学历");
				m.put("itemValue", secProChaPer.getChargeHighestSchool());
				json.put("highSchool",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				//学位
				m.put("codeName", "学位");
				m.put("itemValue", secProChaPer.getChargeHighestDegree());
				json.put("highDegree", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				json.put("school",null==secProChaPer.getChargeSchool()?"":secProChaPer.getChargeSchool());
				json.put("specialized", null==secProChaPer.getChargeSpecialized()?"":secProChaPer.getChargeSpecialized() );
				json.put("title", null==secProChaPer.getChargeTitle()?"":secProChaPer.getChargeTitle() );
				json.put("phone", null==secProChaPer.getChargePhone()?"":secProChaPer.getChargePhone() );
				json.put("email", null==secProChaPer.getChargeEmail()?"":secProChaPer.getChargeEmail() );
				json.put("address", null==secProChaPer.getChargeAddress()?"":secProChaPer.getChargeAddress() );
				json.put("dutyDate", null==secProChaPer.getChargeDutyDate()?"":sdf.format(secProChaPer.getChargeDutyDate()) );
				json.put("postdate", null==secProChaPer.getChargePostDate()?"":sdf.format(secProChaPer.getChargePostDate()) );
				json.put("specializeNum", null==secProChaPer.getChargeSpecializedNum()?"":secProChaPer.getChargeSpecializedNum() );
				json.put("remark",null==secProChaPer.getChargeRemark()?"":secProChaPer.getChargeRemark() );
				
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