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
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

/**
 * 隐患审核信息上报
 * @author lj
 *
 */

public class GetQyyhAuditUploadCommand implements Command
{
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private DeptService deptService = (DeptService) SpringContextHolder.getBean("deptService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String result = obj.getString("result");//审核结果 1：未通过  0：审核通过
		String remark = obj.getString("remark");//审核备注
		String id = obj.getString("id");//隐患id
		
		//将审核状态调整成中文 lj 2015-12-07 
		if("1".equals(result)){
			result = "审核未通过";
		}else{
			result="审核通过";
		}

		String userType="7";//判断登录人的角色
		if(httpService.judgeRoleCode(userId, "A17")){//安委会办公室
			userType="1";
		}
		if(httpService.judgeRoleCode(userId, "A12")){//中队队员
			userType="2";
		}
		if(httpService.judgeRoleCode(userId, "A11")){//安监中队队长
			userType="3";
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
		
		
		try {	
			TroMan troMan=troManService.getById(id);
			if("1".equals(userType)){//安委会办公室审核 状态21 转接审核
				if("审核未通过".equals(result)){
					troMan.setRectificationState("1");//不通过，返回上报人修改整改信息
					troMan.setAuditResult("审核未通过");
				}else{
					if("1".equals(troMan.getIfReply())){
						troMan.setRectificationState("4");
					}else{
						troMan.setRectificationState("0");//待整改，但是暂时没人管了
						troMan.setDealState("整改完成");
					}
				}
			}else if("2".equals(userType)){//安监中队队员 3 是否上报安委会审核
				if("审核未通过".equals(result)){
					troMan.setRectificationState("1");//不通过，返回上报人修改整改信息
					troMan.setAuditResult("审核未通过");
				}else{
					troMan.setRectificationState("20");//待中队长审核
					troMan.setAuditResult("审核通过");
				}
			}else if("3".equals(userType)){//安监中队队长审核
				if("20".equals(troMan.getRectificationState())){//转接审核
					if("审核未通过".equals(result)){
						troMan.setRectificationState("1");//不通过，返回上报人修改整改信息
						troMan.setAuditResult("审核未通过");
					}else{
						troMan.setRectificationState("21");//待安委会审核
						troMan.setAuditResult("审核通过");
						String dealDept = obj.getString("dealDept");//处理职能部门
						String ifReply = obj.getString("ifReply");//是否需要回复
						String need = "1".equals(ifReply)?",需要回复":",不需要回复";
						troMan.setDealDeptId(dealDept);
						troMan.setIfReply(ifReply);
						result="审核通过:"+deptService.findDeptByDeptCode(dealDept).getDeptName()+need;
					}
				}else{
					if("审核未通过".equals(result)){
						if("0".equals(troMan.getIfCorrected())){
							troMan.setRectificationState("1");//不通过，返回上报人修改隐患信息
							troMan.setAuditResult("审核未通过");
						}else{
							troMan.setRectificationState("6");//不通过，返回上报人修改整改信息
							troMan.setAuditResult("整改未通过");
						}
					}else{//审核通过
						if("1".equals(troMan.getTroubleLevel())){
							if("0".equals(troMan.getIfCorrected())){//一般隐患 未整改
								troMan.setRectificationState("6");
								troMan.setAuditResult("审核通过");
							}else{
								troMan.setRectificationState("0");
								if("整改未完成".equals(troMan.getDealState())){
									troMan.setAuditResult("整改未完成");
								}else{
									troMan.setAuditResult("整改通过");
								}
							}
						}else{//不是一般隐患
							troMan.setRectificationState("5");
						}
						
						if("0".equals(troMan.getIfCorrected())){//未整改
							troMan.setAuditResult("审核通过");
						}else{
							troMan.setAuditResult("整改通过");
						}
					}
				}
			}else if("4".equals(userType)){//监察大队队长审核
				if("审核未通过".equals(result)){
					if("0".equals(troMan.getIfCorrected())){//未整改
						troMan.setAuditResult("审核通过");
						troMan.setTroubleLevel("1");//设置隐患级别为一般
					}else{
						troMan.setAuditResult("整改未通过");
					}
					troMan.setRectificationState("6");//审核不过，给网格监管员整改
				}else{//审核通过
					if("0".equals(troMan.getIfCorrected())){//未整改
						troMan.setAuditResult("审核通过");
						if("2".equals(troMan.getTroubleLevel())){
							if("0".equals(troMan.getIfCorrected())){//重大隐患 未整改
								troMan.setRectificationState("6");
							}else{//重大隐患 已整改
								troMan.setRectificationState("0");
							}
						}else{//特别重大隐患
							troMan.setRectificationState("7");//特别重大隐患，需领导审核
						}
					}else{
						if("整改未完成".equals(troMan.getDealState())){
							troMan.setAuditResult("整改未完成");
						}else{
							troMan.setAuditResult("整改通过");
						}
						troMan.setRectificationState("0");
					}
				}
			}else if("5".equals(userType)){//局领导审核
				if("审核未通过".equals(result)){
					if("0".equals(troMan.getIfCorrected())){//未整改
						troMan.setAuditResult("审核未通过");
						troMan.setTroubleLevel("2");
					}else{
						troMan.setAuditResult("整改未通过");
					}
					troMan.setRectificationState("6");//审核不过，给网格监管员整改
				}else{//审核通过
					if("0".equals(troMan.getIfCorrected())){//未整改
						troMan.setAuditResult("审核通过");
						troMan.setRectificationState("6");//给网格监管员整改
					}else{
						if("整改未完成".equals(troMan.getDealState())){
							troMan.setAuditResult("整改未完成");
						}else{
							troMan.setAuditResult("整改通过");
						}
						troMan.setRectificationState("0");
					}
				}
			}
			
			//troMan.setRemark(remark); 此处不应该更新整改备注
			troManService.update(troMan);
			CheckRecord checkRecord=new CheckRecord();
			checkRecord.setInfoId(id);
			checkRecord .setCheckUserid(userId);
			checkRecord.setDelFlag(0);
			checkRecord.setCheckUsername(user.getDisplayName());
			checkRecord.setCheckRemark(remark);
			checkRecord.setCheckResult(result);
			checkRecordService.save(checkRecord);
			bd.setCode("0");
			bd.setMessage("审核成功!");
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("审核失败!");
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
