package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

/**
 * 企业隐患列表
 * @author lj
 *
 */

public class GetQyyhListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private EntBaseInfoService entBaseInfoService = (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String companyId = obj.getString("companyId");//企业名称
		String pageNum = obj.getString("pageNum");//页数
		String pageSize = obj.getString("pageSize");//每页条数
		String type = obj.getString("type");//0:待整改 1：待审核  2 所有信息列表    3 我的上报历史数据
		int start = Integer.parseInt(pageNum);
		int limit = Integer.parseInt(pageSize);
		int s = (start-1)*limit;
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("type",type);
		map.put("companyId", companyId);
		map.put("pageNum", s);
		map.put("pageSize", limit);
		
		String userType="7";//判断登录人的角色
		if(httpService.judgeRoleCode(userId, "A23")){//企业角色
			userType="0";
		}
		
		if(httpService.judgeRoleCode(userId, "A17")){//安委会办公室
			userType="1";
		}
		if(httpService.judgeRoleCode(userId, "A12")){//安监中队队员
			userType="2";
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
			paraMapEnt.put("userId",userId);
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			if(ents.size()>0){
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				map.put("companyIds", companmyIds);
			}else{
				map.put("companyIds", "-");
			}
		}
		if(httpService.judgeRoleCode(userId, "A11")){//安监中队队长
			userType="3";
			Map<String, Object> paraMapEnt = new HashMap<String, Object>();
			paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
			paraMapEnt.put("deptCode",userService.findUserById(userId).getDeptCode());
			List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
			String companmyIds="";
			if(ents.size()>0){
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				map.put("companyIds", companmyIds);
			}else{
				map.put("companyIds", "-");
			}
			
			
		}
		if(httpService.judgeRoleCode(userId, "A09")){//监察大队队长
			userType="4";
		}
		if(httpService.judgeRoleCode(userId, "A02")){//安监局局领导
			userType="5";
		}
		User user = userService.findUserById(userId);
		if(user!=null&&user.getDeptCode().startsWith("002")&&user.getDeptCode().length()==6&&!"002001".equals(user.getDeptCode())){
			userType= "6";
		}
		if(user!=null){
			map.put("deptCode", user.getDeptCode());
		}
		map.put("userType",userType);
		
		
		map.put("sqlID", "queryTroubleListCountByMap");
		try {
			int total = httpService.getListCountbyMap(map);//获取隐患列表总数
			map.put("sqlID", "queryTroubleListByMap");
			List<Map> plans = httpService.getListByMap(map);//获取隐患列表
			if(plans!=null&&!plans.isEmpty()){
				bd.setTotal(total+"");
				bd.setContent(JSONArray.fromObject(plans).toString());
				int page = total%limit==0?total/limit:(total/limit+1);
				bd.setPage(page+"");
				bd.setCode("0");
				bd.setMessage("查询成功!");
			}else{
				bd.setCode("1");
				bd.setMessage("无查询结果!");
			}
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("查询失败!");
			e.printStackTrace();
		}
		return bd;
	}
	/**
	 * 判断当前时间是否在有效期内
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private boolean vailTime(String startTime,String endTime){
		boolean flag = false;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(startTime));
			long stime = c.getTimeInMillis();
			c.setTime(new Date());
			long ntime = c.getTimeInMillis();
			c.setTime(sdf.parse(endTime));
			long etime = c.getTimeInMillis();
			
			if(ntime>=stime&&ntime<=etime){
				flag = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
