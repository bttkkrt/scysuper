package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqscfgfzr.entity.SecProCha;
import com.jshx.aqscfgfzr.service.SecProChaService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.CodeService;

/**
 * 安全生产分管负责人详情
 * @author 费谦 2015-10-13
 */
public class GetAqscfgfzrDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProChaService secProChaService = (SecProChaService) SpringContextHolder.getBean("secProChaService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SecProCha secProCha=secProChaService.getById(id);
			if(null!=secProCha){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==secProCha.getChargerName()?"":secProCha.getChargerName()  );
				//性别
				Map m = new HashMap();
				m.put("codeName", "性别");
				m.put("itemValue", secProCha.getChargerSex());
				json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//国籍
				json.put("nation", null==secProCha.getChargerNationnality()?"":secProCha.getChargerNationnality());
				//学历
				m.put("codeName", "学历");
				m.put("itemValue", secProCha.getChargerHighestSchool());
				json.put("highSchool",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				//学位
				m.put("codeName", "学位");
				m.put("itemValue", secProCha.getChargerHighestDegree());
				json.put("highDegree", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				json.put("school",null==secProCha.getChargerSchool()?"":secProCha.getChargerSchool());
				json.put("specialized", null==secProCha.getChargerSpecialized()?"":secProCha.getChargerSpecialized() );
				json.put("title", null==secProCha.getChargerTitle()?"":secProCha.getChargerTitle() );
				json.put("phone", null==secProCha.getChargerPhone()?"":secProCha.getChargerPhone() );
				json.put("email", null==secProCha.getChargerEmail()?"":secProCha.getChargerEmail() );
				json.put("address", null==secProCha.getChargerAddress()?"":secProCha.getChargerAddress() );
				json.put("dutyDate", null==secProCha.getChargerDutyDate()?"":sdf.format(secProCha.getChargerDutyDate()) );
				json.put("postdate", null==secProCha.getChargerPostDate()?"":sdf.format(secProCha.getChargerPostDate()) );
				json.put("specializeNum", null==secProCha.getChargerSpecializedNum()?"":secProCha.getChargerSpecializedNum() );
				json.put("remark",null==secProCha.getChargerRemark()?"":secProCha.getChargerRemark() );
				
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