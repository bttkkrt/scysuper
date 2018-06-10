package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.activiti.service.ActivityManageService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;

/**
 * 上报现场检查隐患清单
 *  zhangzhouhua 2016-08-02
 */

public class ZhenggaiYhqdCommand implements Command
{

	private YhqdService  yhqdService= (YhqdService) SpringContextHolder.getBean("yhqdService");

	private ActivityManageService activityManageService = (ActivityManageService) SpringContextHolder.getBean("activityManageService");
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String userId = json.getString("userId");//  
		String yhId = json.getString("yhId");// 隐患ID
		String taskId = json.getString("taskId");// 隐患ID
		String zgInfo = json.getString("zgInfo");//检测时间
		try
		{
			zgInfo = new String(zgInfo.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		try
		{
		Yhqd yhqd = yhqdService.getById(yhId);
			 //保存整改信息   
			yhqd.setDealFlag(1);
			yhqd.setPassFlag(null);
			yhqd.setZgInfo(zgInfo);
			yhqd.setUpdateUserID(userId);
			yhqd.setUpdateTime(new Date());
			yhqdService.update(yhqd);
			Map<String,Object> variables = new HashMap<String, Object>();
			variables.put("workFlowModelId",  yhqd.getId());
			variables.put("moveStatus", "yanshoutask");
			variables.put("flag", "yanshou");
			//进入下一流程
			activityManageService.passProcess(taskId, "验收整改", variables);	
			bd.setCode("0");
			bd.setMessage("整改成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", "ZG"+yhqd.getId());
			jn.put("linkType", "check");//返回附件类型 check
			bd.setContent(jn.toString());
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("整改失败");
		}
		return bd;
	}

}
