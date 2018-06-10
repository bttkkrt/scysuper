package com.jshx.http.command;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkSituation.entity.CheckSituation;
import com.jshx.checkSituation.service.CheckSituationService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;

/**
 * 上报现场检查检查情况 
 *  GY-UPDATE 2015-02-11
 */

public class SaveCheckSituationCommand implements Command
{

	private CheckSituationService checkSituationService = (CheckSituationService) SpringContextHolder.getBean("checkSituationService");

	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String basicId = json.getString("checkbasicid");// 检查基本表ID
		String discreption = json.getString("discreption");//描述
		String checkFlag = json.getString("checkFlag");//检查标记 
		try
		{
			discreption = new String(discreption.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		Integer flag = 0;
		try
		{
			flag = Integer.parseInt(checkFlag);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		try
		{
			//保存检查情况表数据
			// 根据基本表ID查询
			CheckBasic checkBasic = checkBasicService.getById(basicId);
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			CheckSituation situation = new CheckSituation();
			situation.setBasic(checkBasic);
			situation.setCheckFlag(flag);
			situation.setDelFlag(0);
			situation.setDiscreption(discreption);
			situation.setLinkid(linkId);
			checkSituationService.save(situation);
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", linkId);
			jn.put("linkType", "check");//返回附件类型 check
			bd.setContent(jn.toString());
		}
		catch (RuntimeException e)
		{
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		return bd;
	}

}
