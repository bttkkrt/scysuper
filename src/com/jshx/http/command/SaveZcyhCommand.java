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
import com.jshx.qyzcyhglb.entity.Qyzcyhglb;
import com.jshx.qyzcyhglb.service.QyzcyhglbService;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.service.JshxZcyhsbService;

/**
 * 上报企业自查隐患信息 
 * @author 陆婷 2013-11-6
 *
 */

public class SaveZcyhCommand implements Command
{
	private JshxZcyhsbService jshxZcyhsbService = (JshxZcyhsbService) SpringContextHolder.getBean("jshxZcyhsbService");
	private QyzcyhglbService qyzcyhglbService = (QyzcyhglbService) SpringContextHolder.getBean("qyzcyhglbService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取上报人id
		String id = json.getString("id");//编号
		String jclb	= json.getString("jclb");//检查类别
		String jcsj	= json.getString("jcsj");//检查时间
		String jcry	= json.getString("jcry");//检查人员
		String jhwcsj = json.getString("jhwcsj");	//计划完成时间
		String fcsj	= json.getString("fcsj");//复查时间
		String fcr	= json.getString("fcr");//复查人
		String jb = json.getString("jb");	//级别
		String zgtrzj = json.getString("zgtrzj");	//整改投入资金(万元)
		String csfa	= json.getString("csfa");//具体情况及整改措施方案
		String wtyh	= json.getString("wtyh");//存在问题和隐患
		String fcysqk = json.getString("fcysqk");	//复查验收情况
		String jcbw	= json.getString("jcbw");//被检查部位
		String yhdl	= json.getString("yhdl");//隐患大类
		String yhzl	= json.getString("yhzl");//隐患中类
		String zgzrbm = json.getString("zgzrbm");	//整改责任部门
		String zgzrr = json.getString("zgzrr");	//整改责任人
		
		Object s = json.get("yhzgwcsj");

		
		JshxZcyhsb jshxZcyhsb = new JshxZcyhsb();
		try {
			if(zgtrzj == null || "".equals(zgtrzj))
			{
				zgtrzj = "0";
			}
			if(id != null && !"".equals(id) && isNum(zgtrzj) == false)
			{
				bd.setCode("1");
				bd.setMessage("整改投入资金必须为数字!");
			}
			else
			{
				if(id != null && !"".equals(id)){//修改
					jshxZcyhsb = jshxZcyhsbService.getById(id);
					jshxZcyhsb.setMqzt("0");//目前状态
					jshxZcyhsb.setFcsj(fcsj);//复查时间
					jshxZcyhsb.setFcr(fcr);//复查人
					jshxZcyhsb.setZgtrzj(zgtrzj);//整改投入资金(万元)
					jshxZcyhsb.setCsfa(csfa);//具体情况及整改措施方案
					jshxZcyhsb.setFcysqk(fcysqk);//复查验收情况
					jshxZcyhsbService.update(jshxZcyhsb);
					Map map = new HashMap();
					map.put("yhid", jshxZcyhsb.getId());
					jshxZcyhsbService.deleteQyzcyhglbByMap(map);
				}
				else
				{
					User user = userService.findUserById(userId);
					CompanyBackUp company = companyService.getCompanyByLoginId(deptService.findDeptByDeptCode(user.getDeptCode().substring(0,9)).getCreateUserID());
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					jshxZcyhsb.setJclb(jclb);//检查类别
					jshxZcyhsb.setJcsj(java.sql.Date.valueOf(jcsj));//检查时间
					jshxZcyhsb.setJcry(jcry);//检查人员
					jshxZcyhsb.setJhwcsj(java.sql.Date.valueOf(jhwcsj));//计划完成时间
					jshxZcyhsb.setMqzt("1");//目前状态
					jshxZcyhsb.setJb(jb);//级别
					jshxZcyhsb.setWtyh(wtyh);//存在问题和隐患
					jshxZcyhsb.setSzzid(dept.getDeptCode());
					jshxZcyhsb.setSzzname(dept.getDeptName());
					jshxZcyhsb.setQyid(company.getId());
					jshxZcyhsb.setQymc(company.getCompanyname());
					jshxZcyhsb.setDeptId(user.getDept().getId());
					jshxZcyhsb.setDelFlag(0);
					jshxZcyhsb.setCreateUserID(user.getId());
					jshxZcyhsb.setCreateTime(new Date());
					jshxZcyhsb.setQylx(company.getQylx());
					jshxZcyhsb.setHyfl(company.getHyfl());
					jshxZcyhsb.setQygm(company.getQygm());
					jshxZcyhsb.setQyzclx(company.getQyzclx());
					jshxZcyhsb.setIfwhpqylx(company.getIfwhpqylx());
					jshxZcyhsb.setIfyhbzjyqy(company.getIfyhbzjyqy());
					jshxZcyhsb.setIfzywhqylx(company.getIfzywhqylx());
					jshxZcyhsb.setYhsl("1");
					jshxZcyhsb.setType("0");
					jshxZcyhsb.setSzc(company.getSzc());
					jshxZcyhsb.setSzcname(company.getSzcname());
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					jshxZcyhsb.setLinkId(linkId);
					jshxZcyhsbService.save(jshxZcyhsb);
				}
				Qyzcyhglb qyzcyhglb = new Qyzcyhglb();
				qyzcyhglb.setType("1");
				qyzcyhglb.setCreateTime(new Date());
				qyzcyhglb.setDelFlag(0);
				qyzcyhglb.setYhid(jshxZcyhsb.getId());
				qyzcyhglb.setJcbw(jcbw);
				qyzcyhglb.setYhdl(yhdl);
				qyzcyhglb.setYhzl(yhzl);
				qyzcyhglb.setZgzrbm(zgzrbm);
				qyzcyhglb.setZgzrr(zgzrr);
				if(s != null)
				{
					String yhzgwcsj = json.getString("yhzgwcsj");
					qyzcyhglb.setYhzgwcsj(yhzgwcsj);
				}
				qyzcyhglbService.save(qyzcyhglb);
				
				bd.setCode("0");
				bd.setMessage("上报成功");
				JSONObject jn = new JSONObject();
				jn.put("linkId", jshxZcyhsb.getLinkId());
				bd.setContent(jn.toString());
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
