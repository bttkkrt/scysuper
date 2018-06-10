package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;

/**
 * 获取案件列表接口
 * @author 陆婷 2015-11-5
 *
 */
public class GetCaseInfoListCommand implements Command{
	
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String pageNum = obj.getString("pageNum");//获取分页的起始页
		String pageSize = obj.getString("pageSize");//获取分页的每页条数
		String title = obj.getString("title");//获取模糊搜索条件
		String type = obj.getString("type");//0表示行政执法列表，1表示领导审核列表，2表示所有 ，3表示当前人员所有 4 结案待归档 
		int s = Integer.parseInt(pageNum);
		int l = Integer.parseInt(pageSize);
		
		Map map = new HashMap();
		if(title != null && !"".equals(title))
		{
			map.put("areaName", "%" + title + "%");
			map.put("companyName", "%" + title + "%");
			map.put("caseCause", "%" + title + "%");
			map.put("caseName", "%" + title + "%");
			map.put("person", "%" + title + "%");
			map.put("sfzh", "%" + title + "%");
			map.put("zipCode", "%" + title + "%");
			map.put("caseCondition", "%" + title + "%");
		}
		
		if(type.equals("0"))//0表示行政执法列表
		{
			map.put("userId", userId);
			map.put("caseStatus", "2");
		}
		else if(type.equals("1"))//1表示领导审核列表
		{
			User user = userService.findUserById(userId);
			List<UserRight> list = (List<UserRight>) user.getUserRoles();
			String flag = "";
			for(UserRight ur:list)
			{
				flag += ur.getRole().getRoleCode()+ ",";
			}
			String level = "";
			//登录人为安监局领导
			if(flag.contains("A02")) 
			{
				level += "1";
			}
			else
			{
				level += "0";
			}
			//登录人为监察大队队长
			if(flag.contains("A09")) 
			{
				level += "1";
			}
			else
			{
				level += "0";
			}
			if(flag.contains("A10")) 
			{
				level += "1";
			}
			else
			{
				level += "0";
			}
			//登录人为法务
			if(flag.contains("A30")) 
			{
				level += "1";
			}
			else
			{
				level += "0";
			}
			map.put("level", level);
			map.put("undertakerId",userId);
		}
		else if(type.equals("3"))//3当前人员历史
		{
			map.put("userId", userId);
		}
		else if(type.equals("4"))//4结案待归档
		{
			map.put("userId", userId);
			map.put("caseStatus", "3");
		}
		else if(type.equals("5"))//4结案待归档
		{
			map.put("userId", userId);
			map.put("caseStatus", "3");
		}
		List<CaseInfo> list = caseInfoService.getCaseInfoListByUserAndType(map, s, l);
		int total=caseInfoService.getCaseInfoListSizeByUserAndType(map);
		int page=total/(l);
		page = total%l==0?page:(page+1);
		
		JSONArray ja = new JSONArray();
		try {
				if(list!=null&&!list.isEmpty()){
					for(CaseInfo ca:list){
						   JSONObject jo = new JSONObject();
						   jo.put("id", StringTools.NullToStr(ca.getId(),""));//主键
						   jo.put("companyName", StringTools.NullToStr(ca.getCompanyName(),""));//企业名称
						   jo.put("caseTime", ca.getCaseTime() != null?new SimpleDateFormat("yyyy-MM-dd").format(ca.getCaseTime()):"");//案件时间
						   jo.put("caseName", StringTools.NullToStr(ca.getCaseName(),""));
						   Map m = new HashMap();
							if(ca.getCaseSource() != null && !"".equals(ca.getCaseSource()))
							{
								m.put("codeName", "案件来源");
								m.put("itemValue", ca.getCaseSource());
								jo.put("caseSource",codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"");//案件来源
							}
							else
							{
								jo.put("caseSource","");//案件来源
							}
							jo.put("caseStatus", StringTools.NullToStr(ca.getCaseCause(),""));
						   ja.add(jo);
					}
					br.setCode("0");
					br.setMessage("成功");
					br.setTotal(total+"");
					br.setPage(page+"");
					br.setContent(ja.toString());
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
}
