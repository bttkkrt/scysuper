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
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.MyUserBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;

/**
 * 获取一维代码表数据
 * @author lj
 *
 */

public class GetCodeListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String type = obj.getString("type");//类型 0:隐患级别 1：隐患类别   2：隐患级别 + 隐患类别  3：案件来源+事故级别+事故类别+案发区域  7:处罚分类5：乡镇区+企业类型 +行业分类 11:隐患处理职能部门  12：隐患级别 + 隐患类别 + 挂牌督办整改级别+乡镇 13:隐患处理职能部门 (安监局除外) 

		Map map = new HashMap();
		map.put("userId", userId);
		map.put("type", type);
		map.put("pageNum", 0);
		map.put("pageSize", 1000);
		map.put("sqlID", "queryCodeListCountByMap");
		try {
			JSONObject json = new JSONObject();
			if("2".equals(type)){
				map.put("type", "0");
				List<Map> yhjbs = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "1");
				List<Map> yhlbs = httpService.getListByMap(map);//获取一维代码表数据列表
				if(yhjbs!=null&&!yhjbs.isEmpty()){
					JSONArray ja = JSONArray.fromObject(yhjbs);
					json.put("yhjb",ja.toString());
				}else{
					json.put("yhjb","[]");
				}
				if(yhlbs!=null&&!yhlbs.isEmpty()){
					JSONArray ja = JSONArray.fromObject(yhlbs);
					json.put("yhlb",ja.toString());
				}else{
					json.put("yhlb","[]");
				}
				
			}
			else if("3".equals(type)){// 3：案件来源+事故级别+事故类别+案发区域
				map.put("type", "3");
				List<Map> ajly = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "4");
				List<Map> sgjb = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "5");
				List<Map> sglb = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "8");
				List<Map> afqy = httpService.getListByMap(map);//获取乡镇区
				
				if(ajly!=null&&!ajly.isEmpty()){
					JSONArray ja = JSONArray.fromObject(ajly);
					json.put("ajly",ja.toString());
				}else{
					json.put("ajly","[]");
				}
				if(sgjb!=null&&!sgjb.isEmpty()){
					JSONArray ja = JSONArray.fromObject(sgjb);
					json.put("sgjb",ja.toString());
				}else{
					json.put("sgjb","[]");
				}
				if(sglb!=null&&!sglb.isEmpty()){
					JSONArray ja = JSONArray.fromObject(sglb);
					json.put("sglb",ja.toString());
				}else{
					json.put("sglb","[]");
				}
				
				if(afqy!=null&&!afqy.isEmpty()){
					JSONArray ja = JSONArray.fromObject(afqy);
					json.put("afqy",ja.toString());
				}else{
					json.put("afqy","[]");
				}
				
			}
			else if("5".equals(type)){// 5：乡镇区+企业类型 +行业分类
				map.put("type", "8");
				List<Map> xzlist = httpService.getListByMap(map);//获取乡镇区
				map.put("type", "9");
				List<Map> qylx = httpService.getListByMap(map);//获取企业类型 
				map.put("type", "10");
				List<Map> hyfl = httpService.getListByMap(map);//获取行业分类
				
				if(xzlist!=null&&!xzlist.isEmpty()){
					JSONArray ja = JSONArray.fromObject(xzlist);
					json.put("xzlist",ja.toString());
				}else{
					json.put("xzlist","[]");
				}
				
				if(qylx!=null&&!qylx.isEmpty()){
					JSONArray ja = JSONArray.fromObject(qylx);
					json.put("qylx",ja.toString());
				}else{
					json.put("qylx","[]");
				}
				
				if(hyfl!=null&&!hyfl.isEmpty()){
					JSONArray ja = JSONArray.fromObject(hyfl);
					json.put("hyfl",ja.toString());
				}else{
					json.put("hyfl","[]");
				}
			}else if("11".equals(type)){// 11：隐患处理职能部门
				map.put("sqlID", "findDealDepts");
				List<Map> dealDeptlist = httpService.getListByMap(map);
				
				if(dealDeptlist!=null&&!dealDeptlist.isEmpty()){
					JSONArray ja = JSONArray.fromObject(dealDeptlist);
					json.put("code",ja.toString());
				}else{
					json.put("code","[]");
				}
				
				
			}if("12".equals(type)){
				map.put("type", "0");
				List<Map> yhjbs = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "1");
				List<Map> yhlbs = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "12");
				List<Map> gpdbzgjbs = httpService.getListByMap(map);//获取一维代码表数据列表
				map.put("type", "8");
				List<Map> xzlist = httpService.getListByMap(map);//获取乡镇区
				
				if(xzlist!=null&&!xzlist.isEmpty()){
					JSONArray ja = JSONArray.fromObject(xzlist);
					json.put("szqy",ja.toString());
				}else{
					json.put("szqy","[]");
				}
				if(yhjbs!=null&&!yhjbs.isEmpty()){
					JSONArray ja = JSONArray.fromObject(yhjbs);
					json.put("yhjb",ja.toString());
				}else{
					json.put("yhjb","[]");
				}
				if(yhlbs!=null&&!yhlbs.isEmpty()){
					JSONArray ja = JSONArray.fromObject(yhlbs);
					json.put("yhlb",ja.toString());
				}else{
					json.put("yhlb","[]");
				}
				if(gpdbzgjbs!=null&&!gpdbzgjbs.isEmpty()){
					JSONArray ja = JSONArray.fromObject(gpdbzgjbs);
					json.put("gpdbzgjb",ja.toString());
				}else{
					json.put("gpdbzgjb","[]");
				}
			}else if("13".equals(type)){// 13：隐患处理职能部门(安监局除外)
				map.put("sqlID", "findDealDeptsWithout002001");
				List<Map> dealDeptlist = httpService.getListByMap(map);
				
				if(dealDeptlist!=null&&!dealDeptlist.isEmpty()){
					JSONArray ja = JSONArray.fromObject(dealDeptlist);
					json.put("code",ja.toString());
				}else{
					json.put("code","[]");
				}
			}
			else{
				List<Map> codes = httpService.getListByMap(map);//获取一维代码表数据列表
				if(codes!=null&&!codes.isEmpty()){
					JSONArray ja = JSONArray.fromObject(codes);
					json.put("code", ja.toString());
				}else{
					json.put("code","[]");
				}
			}
			bd.setContent(json.toString());
			bd.setCode("0");
			bd.setMessage("查询成功!");
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
