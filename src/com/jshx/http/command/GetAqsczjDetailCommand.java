package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqsczj.entity.SecProMaj;
import com.jshx.aqsczj.service.SecProMajService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.CodeService;

/**
 * 安全生产总监详情
 * @author 费谦 2015-10-13
 */
public class GetAqsczjDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProMajService secProMajService = (SecProMajService) SpringContextHolder.getBean("secProMajService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			SecProMaj secProMaj=secProMajService.getById(id);
			if(null!=secProMaj){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==secProMaj.getMajordomoName()?"":secProMaj.getMajordomoName()  );
				//性别
				Map m = new HashMap();
				m.put("codeName", "性别");
				m.put("itemValue", secProMaj.getMajordomoSex());
				json.put("sex",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//国籍
				json.put("nation", null==secProMaj.getMajordomoNationnality()?"":secProMaj.getMajordomoNationnality());
				//学历
				m.put("codeName", "学历");
				m.put("itemValue", secProMaj.getMajordomoHighestSchool());
				json.put("highSchool",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				//学位
				m.put("codeName", "学位");
				m.put("itemValue", secProMaj.getMajordomoHighestDegree());
				json.put("highDegree", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				json.put("school",null==secProMaj.getMajordomoSchool()?"":secProMaj.getMajordomoSchool());
				json.put("specialized", null==secProMaj.getMajordomoSpecialized()?"":secProMaj.getMajordomoSpecialized() );
				json.put("title", null==secProMaj.getMajordomoTitle()?"":secProMaj.getMajordomoTitle() );
				json.put("phone", null==secProMaj.getMajordomoPhone()?"":secProMaj.getMajordomoPhone() );
				json.put("email", null==secProMaj.getMajordomoEmail()?"":secProMaj.getMajordomoEmail() );
				json.put("address", null==secProMaj.getMajordomoAddress()?"":secProMaj.getMajordomoAddress() );
				json.put("dutyDate", null==secProMaj.getMajordomoDutyDate()?"":sdf.format(secProMaj.getMajordomoDutyDate()) );
				json.put("postdate", null==secProMaj.getMajordomoPostDate()?"":sdf.format(secProMaj.getMajordomoPostDate()) );
				json.put("specializeNum", null==secProMaj.getMajordomoSpecializedNum()?"":secProMaj.getMajordomoSpecializedNum() );
				json.put("remark",null==secProMaj.getMajordomoRemark()?"":secProMaj.getMajordomoRemark() );
				
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