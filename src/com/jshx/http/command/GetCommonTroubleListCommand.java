package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.CommonTroubleBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;

/**
 * 获取执法文书的列表
 * @author 李军 2013-07-23
 *
 */

public class GetCommonTroubleListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String userId = json.getString("userId");//获取用户的id
		String start = json.getString("start");//获取分页的起始页
		String limit = json.getString("limit");//获取分页的每页条数
		int s = Integer.parseInt(start);
		int l = Integer.parseInt(limit);
		Map map = new HashMap();
		map.put("start", s);
		map.put("limit", l);
		
//		List<DataBean> roles = httpService.getRoleByUserId(map);//根据用户id查询该用户的角色
//		DataBean deptBean = httpService.getDeptCodeByUserId(map);//根据用户id查询该用户的关联下的部门id信息
//		if(deptBean!=null){
//			map.put("deptId", deptBean.getRname());
//			map.put("deptCode",deptBean.getRid());
//		}
//		if(deptBean!=null){
//			String deptCode = deptBean.getRid();
//			if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))){
//				//乡镇安监中队 
//				map.put("deptCode","%" + deptCode+ "%");
//			}else if(deptCode.startsWith("002001")){//部门如果是危化科
//				map.put("whp", "1");
//			}else if(deptCode.startsWith("002002")){//部门如果是职业健康科
//				map.put("zyjk", "1");
//			}else if(deptCode.startsWith("002004")){//部门是综合科
//				map.put("yhbz", "1");
//			}
//		}
		
		
		User user = userService.findUserById(userId);
		String deptCode = user.getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(user.getLoginId());
			map.put("qymcId", company.getCompanyId());
//			map.put("isqysb", "0");
		}
		else
		{
			map.put("userId", userId);
//			map.put("isqysb", "1");
			String deptRole = user.getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//添加部门、部门职责查询条件
				map = Condition.getContiton(map, deptRole, deptCode, companyService,"", "");
			}
		}
		
		List<CommonTroubleBean> commons = httpService.getCommonTroubleList(map);//获取一般安全隐患列表信息

		
		int total = httpService.getCommonTroubleListCount(map);//获取一般安全隐患总条数
		int page=total/(l);
		 page = total%l==0?page:(page+1);
		 
		JSONArray ja = new JSONArray();
		try {
				if(commons!=null&&!commons.isEmpty()){
					for(CommonTroubleBean bean:commons){
						   JSONObject jo = new JSONObject();
						   jo.put("rid", StringTools.NullToStr(bean.getRid(),""));//主键
						   jo.put("qymc", StringTools.NullToStr(bean.getQymc(),""));//检查企业名称
						   jo.put("jcry", StringTools.NullToStr(bean.getJcry(),""));//检查人员
						   jo.put("jcsj", StringTools.NullToStr(bean.getJcsj(),""));//检查时间
						   jo.put("linkId", StringTools.NullToStr(bean.getLinkId(),""));//关联id
						   ja.add(jo);
					}
					bd.setCode("0");
					bd.setMessage("查询成功");
					bd.setTotal(total+"");
					bd.setPage(page+"");
					bd.setContent(ja.toString());
				}else{
					bd.setCode("1");
					bd.setMessage("无查询结果");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
