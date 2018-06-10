package com.jshx.http.command;


import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.dailyInspection.entity.Dailyinspection;
import com.jshx.dailyInspection.service.DailyinspectionService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.DeptService;

/**
 * 上报日常巡检信息 
 * @author 高强  2013-09-6
 *
 */

public class SaveDailyInspectionCommand implements Command
{
	private DailyinspectionService dailyinspectionService = (DailyinspectionService) SpringContextHolder.getBean("dailyinspectionService");
	private CompanyService companyService=(CompanyService) SpringContextHolder.getBean("companyService");
	private DeptService deptService=(DeptService) SpringContextHolder.getBean("deptService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取上报人id
		Dailyinspection dailyinspection = new Dailyinspection();
		try {
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			dailyinspection.setLinkid(linkId);
			dailyinspection.setCreateUserID(userId);
			dailyinspection.setDelFlag(0);
			dailyinspection.setComid(json.getString("comId"));
			Map mm = new HashMap();
			mm.put("companyId", json.getString("comId"));
			CompanyBackUp	company=companyService.getCompanyBackupById(mm);
			
			dailyinspection.setComname(company.getCompanyname());
			dailyinspection.setDeptId(company.getDwdz1());
			dailyinspection.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			dailyinspection.setRemark(json.getString("remark"));
			
			//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			dailyinspection.setQylx(company.getQylx());
			dailyinspection.setHyfl(company.getHyfl());
			dailyinspection.setQygm(company.getQygm());
			dailyinspection.setQyzclx(company.getQyzclx());
			
			dailyinspection.setIfwhpqylx(company.getIfwhpqylx());
			dailyinspection.setIfyhbzjyqy(company.getIfyhbzjyqy());
			dailyinspection.setIfzywhqylx(company.getIfzywhqylx());
			
			dailyinspectionService.save(dailyinspection);
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("id", linkId);
			bd.setContent(jn.toString());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
