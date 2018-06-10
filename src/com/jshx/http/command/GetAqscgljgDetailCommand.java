package com.jshx.http.command;

import net.sf.json.JSONObject;
import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqscgljg.entity.ProManOrg;
import com.jshx.aqscgljg.service.ProManOrgService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

/**
 * 安全生产管理机构详情
 * @author 费谦 2015-10-13
 */
public class GetAqscgljgDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ProManOrgService proManOrgService = (ProManOrgService) SpringContextHolder.getBean("proManOrgService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			ProManOrg proManOrg=proManOrgService.getById(id);
			if(null!=proManOrg){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("orgenName",null==proManOrg.getOrgenizationName()?"":proManOrg.getOrgenizationName()  );
				json.put("orgenResponse",null==proManOrg.getOrgenizationResponsibility()?"":proManOrg.getOrgenizationResponsibility()   );
				json.put("orgenMenCount",null==proManOrg.getOrgenizationMenberCount()?"":proManOrg.getOrgenizationMenberCount()  );
				json.put("orgenCharge",null==proManOrg.getOrgenizationCharge()?"":proManOrg.getOrgenizationCharge()   );
				json.put("orgenChargePhone",null==proManOrg.getOrgenizationChargePhone()?"":proManOrg.getOrgenizationChargePhone()   );
				json.put("orgenChargeEmail",null==proManOrg.getOrgenizationChargeEmail()?"":proManOrg.getOrgenizationChargeEmail()  );
				json.put("orgenRemark",null==proManOrg.getOrgenizationRemark()?"":proManOrg.getOrgenizationRemark()  );
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