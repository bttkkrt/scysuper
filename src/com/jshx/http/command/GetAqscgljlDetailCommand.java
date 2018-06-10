package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqscgljl.entity.SecProMan;
import com.jshx.aqscgljl.service.SecProManService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.CodeService;

/**
 * 安全生产管理经理详情
 * @author 费谦 2015-10-13
 */
public class GetAqscgljlDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProManService secProManService = (SecProManService) SpringContextHolder.getBean("secProManService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SecProMan secProMan=secProManService.getById(id);
			if(null!=secProMan){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==secProMan.getManagerName()?"":secProMan.getManagerName()  );
				//性别
				Map m = new HashMap();
				m.put("codeName", "性别");
				m.put("itemValue", secProMan.getManagerSex());
				json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//国籍
				json.put("nation", null==secProMan.getManagerNationnality()?"":secProMan.getManagerNationnality());
				//学历
				m.put("codeName", "学历");
				m.put("itemValue", secProMan.getManagerHighestSchool());
				json.put("highSchool",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				//学位
				m.put("codeName", "学位");
				m.put("itemValue", secProMan.getManagerHighestDegree());
				json.put("highDegree", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				json.put("school",null==secProMan.getManagerSchool()?"":secProMan.getManagerSchool());
				json.put("specialized", null==secProMan.getManagerSpecialized()?"":secProMan.getManagerSpecialized() );
				json.put("title", null==secProMan.getManagerTitle()?"":secProMan.getManagerTitle() );
				json.put("phone", null==secProMan.getManagerPhone()?"":secProMan.getManagerPhone() );
				json.put("email", null==secProMan.getManagerEmail()?"":secProMan.getManagerEmail() );
				json.put("address", null==secProMan.getManagerAddress()?"":secProMan.getManagerAddress() );
				json.put("dutyDate", null==secProMan.getManagerDutyDate()?"":sdf.format(secProMan.getManagerDutyDate()) );
				json.put("postdate", null==secProMan.getManagerPostDate()?"":sdf.format(secProMan.getManagerPostDate()) );
				json.put("specializeNum", null==secProMan.getManagerSpecializedNum()?"":secProMan.getManagerSpecializedNum() );
				json.put("remark",null==secProMan.getManagerRemark()?"":secProMan.getManagerRemark() );
				
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