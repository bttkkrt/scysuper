package com.jshx.http.command;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.company.entity.Company;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.UserService;
import com.jshx.terminlLoginInfo.entity.TerminalLoginInfo;
import com.jshx.terminlLoginInfo.service.TerminalLoginInfoService;

/**
 * <终端手机登陆平台>
 * @author gq
 *
 */

public class LoginCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private TerminalLoginInfoService terminalLoginInfoService = (TerminalLoginInfoService) SpringContextHolder.getBean("terminalLoginInfoService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override  
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String loginName = obj.getString("loginName");
		String password = obj.getString("password");
		TerminalLoginInfo terminalLoginInfo = new TerminalLoginInfo();
     	terminalLoginInfo.setCreateTime(new Date());
     	terminalLoginInfo.setUserName(loginName);
     	terminalLoginInfo.setDelFlag(0);
     	terminalLoginInfoService.save(terminalLoginInfo);
		Map map = new HashMap();
		map.put("loginName", loginName);
		List<MyUserBean> users = httpService.getUserBeanByMap(map);
		String pass = CodeUtil.encode(password, "MD5");//获取加密后的密码
		if(users!=null&&!users.isEmpty()){
			for(MyUserBean user:users){
	        	if(pass!=null&&pass.equals(user.getPassword()))
	        	{//表示密码通过
	        			bd.setCode("0");
	        			bd.setMessage("登录成功");
	        			JSONObject json = new JSONObject();
	        			String companyId = "";
	        			json.put("userId",user.getId());
	        			json.put("deptId", user.getDeptId());
	        			json.put("userDeptCode", user.getDeptCode());
	        			json.put("userName", user.getUserName());
	        			json.put("loginName", user.getLoginName());
	        			String deptCode = user.getDeptCode();
	        			if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")) && deptCode.length() == 6){
	        				Company company = companyService.findCompanyByLoginID(user.getLoginName());
	        				companyId = company.getId();
	        				json.put("type", "1");
	        			}
	        			else if(deptCode.startsWith("004")){
	        				json.put("type", "2");
	        			}else{
	        				json.put("type", "0");
	        			}

	        			User tempuser = userService.findUserById(user.getId());
	        			List<UserRight> list = tempuser.getUserRoles();
	        			for(UserRight u:list)
	        			{
	        				String rolecode = u.getRole().getRoleCode();
	        				if(rolecode.equals("A4504") || rolecode.equals("A4510")){//企业负责人、企业管理员
	        					json.put("type", "3");
	        					break;
	        				}else if(rolecode.equals("A4511")){//企业安全负责人
	        					json.put("type", "4");
	        					break;
	        				}else if(rolecode.equals("A4509")){//企业车间主任
	        					json.put("type", "5");
	        					break;
	        				}else if(rolecode.equals("A4512")){//企业车间安全员
	        					json.put("type", "6");
	        					break;
	        				}else if(rolecode.equals("A4513")){//企业班组兼职安全员
	        					json.put("type", "7");
	        					break;
	        				}else if(rolecode.equals("A4514")){//企业岗位人员
	        					json.put("type", "8");
	        					break;
	        				}
	        			}
	        			
	        			json.put("companyId", companyId);
	        			bd.setContent(json.toString());
	        			break;
	        	}else{
	        		bd.setCode("1");
        			bd.setMessage("密码错误");
	        	}
			}
			
		}else{
			bd.setCode("1");
			bd.setMessage("用户名不存在");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
