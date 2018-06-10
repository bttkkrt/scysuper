package com.jshx.http.command;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.commonTrouble.service.CommoTroubleService;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.JsonToObject;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;

/**
 * 上报一般安全隐患信息 
 * @author 李军 2013-07-23
 *
 */

public class SaveCommonTroubleCommand implements Command
{
	private CommoTroubleService CommoTroubleService = (CommoTroubleService) SpringContextHolder.getBean("commoTroubleService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取上报人id
		String id = (String)json.get("id");//获取主键id  lj - 2013-09-24
		CommoTrouble common = new CommoTrouble();
		try {
			if(id == null || "".equals(id)){//上报基本信息
				common = (CommoTrouble)JsonToObject.convert(common,json);//将json格式转换为一般安全隐患对象
				
				if(common.getCdcs() == null || "".equals(common.getCdcs()))
				{
					common.setCdcs("0");
				}
				if(common.getYhs() == null || "".equals(common.getYhs()))
				{
					common.setYhs("0");
				}
				if(common.getZdyhs() == null || "".equals(common.getZdyhs()))
				{
					common.setZdyhs("0");
				}
				
				if(isNum(common.getCdcs()) == false)
				{
					bd.setCode("1");
					bd.setMessage("出动人次必须为数字!");
				}
				else if(isNum(common.getYhs()) == false)
				{
					bd.setCode("1");
					bd.setMessage("隐患数必须为数字!");
				}
				else if(isNum(common.getZdyhs()) == false)
				{
					bd.setCode("1");
					bd.setMessage("重大隐患数必须为数字!");
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					common.setLinkId(linkId);
					common.setCreateUserID(userId);
					common.setDelFlag(0);
					common.setCreateTime(new Date());
					User user = userService.findUserById(userId);
					common.setDeptId(user.getDept().getId());
					Map mm = new HashMap();
					mm.put("companyId", json.getString("qymcId"));
					CompanyBackUp	company=companyService.getCompanyBackupById(mm);
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					common.setSzz(dept.getDeptCode());
					common.setSzzname(dept.getDeptName());
					common.setQylx(company.getQylx());
					common.setHyfl(company.getHyfl());
					common.setQygm(company.getQygm());
					common.setQyzclx(company.getQyzclx());
					common.setIfwhpqylx(company.getIfwhpqylx());
					common.setIfyhbzjyqy(company.getIfyhbzjyqy());
					common.setIfzywhqylx(company.getIfzywhqylx());
					common.setSzc(company.getSzc());
					common.setSzcname(company.getSzcname());
					common.setShzt("0");//设置默认状态 为 0 ：待整改
					common.setYhzgs("");
					common.setZdyhzgs("");
					common.setZgzj("");
					common.setZgwcsj("");
					common.setCompanyType(company.getCompanyType());//hanxc 20150117 企业所属监管类型
					common.setIfzsqy(company.getIfzsqy());//hanxc 20150117 是否直属企业
					CommoTroubleService.save(common);
					
					bd.setCode("0");
					bd.setMessage("上报成功");
					JSONObject jn = new JSONObject();
					jn.put("id", common.getLinkId());
					bd.setContent(jn.toString());
				}
			}else{//上报整改情况
				common = CommoTroubleService.getById(id);
				String yhzgs = json.getString("yhzgs");
				if(yhzgs == null || "".equals(yhzgs))
				{
					yhzgs = "0";
				}
				String zdyhzgs = json.getString("zdyhzgs");
				if(zdyhzgs == null || "".equals(zdyhzgs))
				{
					zdyhzgs = "0";
				}
				String zgzj = json.getString("zgzj");
				if(zgzj == null || "".equals(zgzj))
				{
					zgzj = "0";
				}
				
				if(isNum(yhzgs) == false)
				{
					bd.setCode("1");
					bd.setMessage("隐患整改数必须为数字!");
				}
				else if(isNum(zdyhzgs) == false)
				{
					bd.setCode("1");
					bd.setMessage("重大隐患整改数必须为数字!");
				}
				else if(isNum(zgzj) == false)
				{
					bd.setCode("1");
					bd.setMessage("整改资金必须为数字!");
				}
				else
				{
					common.setYhzgs(yhzgs);
					common.setZdyhzgs(zdyhzgs);
					common.setZgzj(zgzj);
					common.setZgwcsj(json.getString("zgwcsj"));
					common.setShzt("1");
					CommoTroubleService.update(common);
					
					bd.setCode("0");
					bd.setMessage("上报成功");
					JSONObject jn = new JSONObject();
					jn.put("id", common.getLinkId());
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
