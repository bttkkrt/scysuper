package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
//import com.jshx.gpdb.entity.Supervice;
//import com.jshx.gpdb.service.SuperviceService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.UserService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

/**
 * 审核挂牌督办整改信息
 * @author 费谦
 *
 */

public class CheckGpdbZgxxCommand implements Command
{
//	private SuperviceService superviceService = (SuperviceService) SpringContextHolder.getBean("superviceService");
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		/*SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String id = obj.getString("id");//挂牌督办id
		String result = obj.getString("result");//审核结果  0：审核通过，1：审核未通过
		String remark = obj.getString("remark");//审核备注
		
		try {	
			Supervice supervice=superviceService.getById(id);
			if("check1".equals(supervice.getNextOperation())){
				if("0".equals(result)){
					supervice.setNextOperation("check2");
					supervice.setNextRoleCode("A02");
					supervice.setRectificationState("待审批");
					result = "审核通过";
				}else{
					supervice.setNextOperation("update1");
					supervice.setNextRoleCode("A04");
					supervice.setRectificationState("待修改");
					result = "审核未通过";
				}
			}else if("check2".equals(supervice.getNextOperation())){
				if("0".equals(result)){
					supervice.setNextOperation("update2");
					supervice.setNextRoleCode("A04");
					supervice.setRectificationState("待整改");
					result = "审批通过";
				}else{
					supervice.setNextOperation("update1");
					supervice.setNextRoleCode("A04");
					supervice.setRectificationState("待修改");
					result = "审批未通过";
				}
			}else if("check3".equals(supervice.getNextOperation())){
				if("0".equals(result)){
					supervice.setNextOperation("check4");
					supervice.setNextRoleCode("A02");
					supervice.setRectificationState("已整改待审批");
					result = "审核通过";
				}else{
					supervice.setNextOperation("update2");
					supervice.setNextRoleCode("A04");
					supervice.setRectificationState("待整改");
					result = "审核未通过";
				}
			}else if("check4".equals(supervice.getNextOperation())){
				if("0".equals(result)){
					supervice.setNextOperation("finish");
					supervice.setNextRoleCode("finish");
					supervice.setRectificationState("完成");
					result = "审批通过";
				}else{
					supervice.setNextOperation("update2");
					supervice.setNextRoleCode("A04");
					supervice.setRectificationState("待整改");
					result = "审批未通过";
				}
			}
			superviceService.update(supervice);
			CheckRecord checkRecord=new CheckRecord();
			checkRecord.setCheckUserid(userId);
			checkRecord.setDelFlag(0);
			checkRecord.setInfoId(id);
			checkRecord.setCheckRemark(remark);
			checkRecord.setCheckResult(result);
			checkRecord.setCheckUsername(userService.findUserById(userId).getDisplayName());
			checkRecordService.save(checkRecord);
			bd.setCode("0");
			bd.setMessage("审核成功!");
			JSONObject json = new JSONObject();
			json.put("linkId", supervice.getLinkId());
			bd.setContent(json.toString());
			
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("审核失败!");
			e.printStackTrace();
		}
		return bd;*/
		return null;
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
