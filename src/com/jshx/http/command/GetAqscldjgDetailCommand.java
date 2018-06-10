package com.jshx.http.command;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.aqscldjg.entity.ProLegOrg;
import com.jshx.aqscldjg.service.ProLegOrgService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

/**
 * 安全生产领导机构详情
 * @author 费谦 2015-10-13
 */
public class GetAqscldjgDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ProLegOrgService proLegOrgService = (ProLegOrgService) SpringContextHolder.getBean("proLegOrgService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			ProLegOrg proLegOrg=proLegOrgService.getById(id);
			if(null!=proLegOrg){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("orgenName",null==proLegOrg.getOrgenizationName()?"":proLegOrg.getOrgenizationName() );
				json.put("orgenResponse",null==proLegOrg.getOrgenizationResponsibility()?"":proLegOrg.getOrgenizationResponsibility()  );
				json.put("orgenMenCount", null==proLegOrg.getOrgenizationMenberCount()?"":proLegOrg.getOrgenizationMenberCount() );
				json.put("orgenCharge", null==proLegOrg.getOrgenizationCharge()?"":proLegOrg.getOrgenizationCharge() );
				json.put("orgenChargePhone", null==proLegOrg.getOrgenizationChargePhone()?"":proLegOrg.getOrgenizationChargePhone() );
				json.put("orgenChargeEmail", null==proLegOrg.getOrgenizationChargeEmail()?"":proLegOrg.getOrgenizationChargeEmail() );
				json.put("orgenRemark", null==proLegOrg.getOrgenizationRemark()?"":proLegOrg.getOrgenizationRemark() );
				Map map = new HashMap();
				map.put("linkId",proLegOrg.getLinkId());
				map.put("mkType", "aqscldjg");
				map.put("picType","aqscldjgfj");
				DataBean bean = httpService.getPhotoListByType(map);
				json.put("filePath",null==bean.getRname()?"":bean.getRname());
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