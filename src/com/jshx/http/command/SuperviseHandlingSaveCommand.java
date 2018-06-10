package com.jshx.http.command;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.gpdb.entity.Gpdb;
import com.jshx.gpdb.service.GpdbService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;

/**
 * 上报挂牌督办信息 
 * @author lht 2013-09-25
 *
 */

public class SuperviseHandlingSaveCommand implements Command
{
	private GpdbService gpdbService= (GpdbService) SpringContextHolder.getBean("gpdbService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		Gpdb item = new  Gpdb();
		String id = json.get("id").toString();
		try {
			if(id != null && !"".equals(id)){//上报整改信息
				String zgzj= json.get("zgzj").toString();
				String yhzgs= json.get("yhzgs").toString();
				if(zgzj == null || "".equals(zgzj))
				{
					zgzj = "0";
				}
				if(yhzgs == null || "".equals(yhzgs))
				{
					yhzgs = "0";
				}
				if(isNum(zgzj) == false)
				{
					bd.setCode("1");
					bd.setMessage("整改资金必须为数字!");
				}
				else if(isNum(yhzgs) == false)
				{
					bd.setCode("1");
					bd.setMessage("隐患整改数必须为数字!");
				}
				else
				{
					String zgwcsj= json.get("zgwcsj").toString();
					String yssj= json.get("yssj").toString();
					item = gpdbService.getById(id);
					item.setZgzj(zgzj);
					item.setZgwcsj(java.sql.Date.valueOf(zgwcsj));
					item.setYssj(java.sql.Date.valueOf(yssj));
					item.setYhzgs(yhzgs);
					item.setState("1");
					gpdbService.update(item);
					
					bd.setCode("0");
					bd.setMessage("上报成功");
					JSONObject jn = new JSONObject();
					jn.put("linkId", item.getLinkid());
					bd.setContent(jn.toString());
				}
				
			}else{
				String yhs = json.get("yhs").toString();
				if(yhs == null || "".equals(yhs))
				{
					yhs = "0";
				}
				if(isNum(yhs) == false)
				{
					bd.setCode("1");
					bd.setMessage("隐患数必须为数字!");
				}
				else
				{
					String yhmc = json.get("yhmc").toString();
					String gpsj = json.get("gpsj").toString();
					String yhlb = json.get("yhlb").toString();
					String zrr = json.get("zrr").toString();
					String userId = json.get("userId").toString();
					User user = userService.findUserById(userId);
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					Map map = new HashMap();
					map.put("companyId", json.get("qyid").toString());
					CompanyBackUp company = companyService.getCompanyBackupById(map);
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					item.setSzzid(dept.getDeptCode());
					item.setSzzname(dept.getDeptName());
					item.setQyid(company.getId());
					item.setQymc(company.getCompanyname());
					item.setQylx(company.getQylx());
					item.setHyfl(company.getHyfl());
					item.setQygm(company.getQygm());
					item.setQyzclx(company.getQyzclx());
					item.setIfwhpqylx(company.getIfwhpqylx());
					item.setIfyhbzjyqy(company.getIfyhbzjyqy());
					item.setIfzywhqylx(company.getIfzywhqylx());
					item.setDeptId(user.getDept().getId());
					item.setDelFlag(0);
					item.setState("0");
					item.setCreateTime(new Date());
					item.setCreateUserID(user.getId());
					item.setUsername(user.getDisplayName());
					item.setYhmc(yhmc);
					item.setGpsj(java.sql.Date.valueOf(gpsj));
					item.setYhlb(yhlb);
					item.setYhs(yhs);
					item.setZrr(zrr);
					item.setLinkid(linkId);
					item.setSzc(company.getSzc());
					item.setSzcname(company.getSzcname());
					gpdbService.save(item);
					
					bd.setCode("0");
					bd.setMessage("上报成功");
					JSONObject jn = new JSONObject();
					jn.put("linkId", item.getLinkid());
					bd.setContent(jn.toString());
				}
			}
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
	
	public boolean isNum(String str){		
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	
	}
}
