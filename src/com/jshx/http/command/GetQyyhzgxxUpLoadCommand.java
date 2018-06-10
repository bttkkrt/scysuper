package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.patrolUser.entity.PatrolUser;
import com.jshx.patrolUser.service.PatrolUserService;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

/**
 * 隐患整改信息上报
 * @author lj
 *
 */

public class GetQyyhzgxxUpLoadCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private PatrolUserService patrolUserService = (PatrolUserService) SpringContextHolder.getBean("patrolUserService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//隐患id
		String recTime = obj.getString("recTime");//整改完成时间
		String recMoney = obj.getString("recMoney");//整改资金
		String remark = obj.getString("remark");//备注
		String state = obj.getString("state");//整改状态 0：整改完成 1：整改未完成
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("id", id);
		map.put("recTime", recTime);
		map.put("recMoney", recMoney);
		map.put("sqlID", "troubleRectUploadByMap");
		
		try {
			TroMan troMan = troManService.getById(id);
			String dealState="整改完成";
			if("1".equals(state)){
				dealState="整改未完成";
			}else{//整改完成 才有整改时间
				troMan.setRecfinishTime(sdf.parse(recTime));
			}
			troMan.setRectificationMoney(recMoney);
			troMan.setDealState(dealState);
			troMan.setRectRemark(remark);
			User user = userService.findUserById(userId);
			PatrolUser patrolUser=patrolUserService.getById(userId);
			if(httpService.judgeRoleCode(userId, "A23") || patrolUser != null){//企业角色
				troMan.setIfCorrected("1");//已整改
				troMan.setRectificationState("0");//上报后流程结束
			}
			if(user!=null&&user.getDeptCode().startsWith("002003")){//职能部门
				troMan.setIfCorrected("1");//已整改
				troMan.setRectificationState("0");//职能部门上报后流程结束
			}
			if(!httpService.judgeRoleCode(userId, "A23")&&user!=null&&!user.getDeptCode().startsWith("002003"))
			{//非企业和职能部门上传整改信息
				troMan.setIfCorrected("1");
				if("1".equals(troMan.getTroubleLevel())){
					troMan.setRectificationState("2");//网格监管人员上报后需中队审核，返回到状态2
				}else if("2".equals(troMan.getTroubleLevel())){
					troMan.setRectificationState("5");//网格监管人员上报后需大队审核，返回到状态5
				}else{
					troMan.setRectificationState("7");//网格监管人员上报后需局领导审核，返回到状态7
				}
			}
			troManService.update(troMan);//更新隐患的状态
			
			//httpService.updateMapByMap(map);//更新map 对象
			bd.setCode("0");
			bd.setMessage("隐患整改上报成功!");
			JSONObject json = new JSONObject();
			//保存整改信息到历史记录中 lj 2015-11-26
				
			map.put("money", recMoney);
			map.put("rectTime", recTime);
			map.put("state",dealState);
			map.put("userId", userId);
			map.put("remark", remark);
			map.put("insertTime", sdf.format(new Date()));
			map.put("yhbId", id);
			
			
			
			map.put("id", id);
			map.put("sqlID", "queryTroubleDetailByMap");
			Map detail = httpService.getMapByMap(map);//获取map 对象
			String 	linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			if(detail!=null){
				json.put("linkId", "troManRect" + linkId);
				map.put("linkId",  linkId);
			}
			bd.setContent(json.toString());
			troManService.saveRectInfo(map);//保存整改信息到历史记录中 
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("隐患整改上报失败!");
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
