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
import com.jshx.lddbqk.entity.LeaCla;
import com.jshx.lddbqk.service.LeaClaService;
import com.jshx.module.admin.service.UserService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

/**
 * 领导带班情况审核
 * @author 费谦
 *
 */

public class GetLddbqkReviewCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private LeaClaService leaClaService = (LeaClaService) SpringContextHolder.getBean("leaClaService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		try {	
			String userId = obj.getString("userId");//用户id
			String id = obj.getString("id");//领导带班情况id
			String result = obj.getString("result");//审核结果
			if("1".equals(result)){
				result="审核未通过";
			}else{
				result="审核通过";
			}
			//修改审核状态
			LeaCla leaCla=leaClaService.getById(id);
			leaCla.setAuditState(result);
			leaClaService.update(leaCla);
			//保存审核记录
			CheckRecord cr=new CheckRecord();
			cr.setCheckResult(result);
			cr.setDelFlag(0);
			cr.setCheckUserid(userId);
			cr.setCheckUsername(userService.findUserById(userId).getDisplayName());
			cr.setInfoId(id);
			checkRecordService.save(cr);
			bd.setCode("0");
			bd.setMessage("审核成功!");
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("审核失败!");
			e.printStackTrace();
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
