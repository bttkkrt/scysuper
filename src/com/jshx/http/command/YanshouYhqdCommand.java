package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.activiti.service.ActivityManageService;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.StringUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;

/**
 * 验收隐患清单
 *  zhangzhouhua 2016-08-02
 */

public class YanshouYhqdCommand implements Command
{

	private YhqdService  yhqdService= (YhqdService) SpringContextHolder.getBean("yhqdService");
	private UserService  userService= (UserService) SpringContextHolder.getBean("userService");

	private ActivityManageService activityManageService = (ActivityManageService) SpringContextHolder.getBean("activityManageService");
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String userId = json.getString("userId");//  
		String yhId = json.getString("yhId");// 隐患ID
		String taskId = json.getString("taskId"); 
		String passFlag = json.getString("passFlag"); 
		String resultContent = json.getString("resultContent"); 
		try
		{
			resultContent = new String(resultContent.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		try
		{
		 
			Map<String,Object> variables = new HashMap<String, Object>();
			variables.put("workFlowModelId",  yhId);
			variables.put("deptCode", "");
			Yhqd yhqd = yhqdService.getById(yhId);
			User user = userService.findUserById(userId);
			//验收不通过整改，流程返回整改
			 if(passFlag.equals("0")){
				 variables.put("passflag", "0");
				 variables.put("flag", "zhenggai");
				 yhqd.setPassFlag(0);
				 activityManageService.passProcess(taskId, "去整改", variables);
			 }else if (passFlag.equals("1")){
				 //验收通过，结束流程
				 yhqd.setEnded(1);
				 variables.put("passflag", "1");
				 yhqd.setPassFlag(1);
				 activityManageService.passProcess(taskId, "结束流程", variables);
			 }
			String lastYsr = yhqd.getZgysr();
			String lastresultContent = yhqd.getResultContent();
			String lastYssj = yhqd.getYssj();
			if(StringUtil.isNotNullAndNotEmpty(lastYsr)){
				lastYsr+="‖" ;
			}else{
				lastYsr="" ;
			}
			lastYsr+=user.getDisplayName();
			yhqd.setZgysr(lastYsr);
			if(StringUtil.isNotNullAndNotEmpty(lastresultContent)){
				lastresultContent+="‖" ;
			} else{
				lastresultContent="" ;
			}
			lastresultContent+=resultContent;
			yhqd.setResultContent(lastresultContent);
			if(StringUtil.isNotNullAndNotEmpty(lastYssj)){
				lastYssj+="‖" ;
			} else{
				lastYssj="" ;
			}
			lastYssj+=DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm");  
			yhqd.setYssj(lastYssj);
			yhqdService.update(yhqd);
			
			bd.setCode("0");
			bd.setMessage("验收成功");
			bd.setContent("");
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("验收失败");
		}
		return bd;
	}

}
