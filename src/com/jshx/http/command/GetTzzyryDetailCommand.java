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
import com.jshx.tzzyry.entity.SpeJobPer;
import com.jshx.tzzyry.service.SpeJobPerService;

/**
 * 特种作业人员详情
 * @author 费谦 2015-10-13
 */
public class GetTzzyryDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SpeJobPerService speJobPerService = (SpeJobPerService) SpringContextHolder.getBean("speJobPerService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SpeJobPer speJobPer=speJobPerService.getById(id);
			if(null!=speJobPer){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==speJobPer.getSpecialName()?"":speJobPer.getSpecialName()  );
				//性别
				Map m = new HashMap();
				m.put("codeName", "性别");
				m.put("itemValue", speJobPer.getSpecialSex());
				json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//国籍
				json.put("nation", null==speJobPer.getSpecialNationnality()?"":speJobPer.getSpecialNationnality());
				//学历
				m.put("codeName", "学历");
				m.put("itemValue", speJobPer.getSpecialHighestSchool());
				json.put("highSchool",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				//学位
				m.put("codeName", "学位");
				m.put("itemValue", speJobPer.getSpecialHighestDegree());
				json.put("highDegree", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				json.put("school",null==speJobPer.getSpecialSchool()?"":speJobPer.getSpecialSchool());
				json.put("specialized", null==speJobPer.getSpecialSpecialized()?"":speJobPer.getSpecialSpecialized() );
				json.put("title", null==speJobPer.getSpecialTitle()?"":speJobPer.getSpecialTitle() );
				json.put("phone", null==speJobPer.getSpecialPhone()?"":speJobPer.getSpecialPhone() );
				json.put("email", null==speJobPer.getSpecialEmail()?"":speJobPer.getSpecialEmail() );
				json.put("address", null==speJobPer.getSpecialAddress()?"":speJobPer.getSpecialAddress() );
				json.put("dutyDate", null==speJobPer.getSpecialDutyDate()?"":sdf.format(speJobPer.getSpecialDutyDate()) );
				json.put("postdate", null==speJobPer.getSpecialPostDate()?"":sdf.format(speJobPer.getSpecialPostDate()) );
				//特种作业类型
				m.put("codeName", "特种作业类型");
				m.put("itemValue", speJobPer.getSpecialJobType());
				json.put("jobType", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				json.put("jobCardNum",null==speJobPer.getSpecialJobCradnum()?"":speJobPer.getSpecialJobCradnum() );
				json.put("trainingUnit",null==speJobPer.getSpecialTrainingUnit()?"":speJobPer.getSpecialTrainingUnit() );
				json.put("cardInstitution",null==speJobPer.getSpecialCardInstitution()?"":speJobPer.getSpecialCardInstitution() );
				json.put("firstDate",null==speJobPer.getSpecialFirstPickDate()?"":sdf.format(speJobPer.getSpecialFirstPickDate()) );
				json.put("verDate",null==speJobPer.getSpecialVerificationDate()?"":sdf.format(speJobPer.getSpecialVerificationDate()) );
				json.put("remark",null==speJobPer.getSpecialRemark()?"":speJobPer.getSpecialRemark() );
				
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