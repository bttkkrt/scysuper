package com.jshx.http.command;

import java.io.UnsupportedEncodingException;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.rectifyOpinion.entity.RectifyOpinion;
import com.jshx.rectifyOpinion.service.RectifyOpinionService;

/**
 * 上报现场检查检查情况 
 *  GY-UPDATE 2015-02-11
 */

public class SaveRectifyOpinionCommand implements Command
{

	private RectifyOpinionService rectifyOpinionService = (RectifyOpinionService) SpringContextHolder.getBean("rectifyOpinionService");

	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String basicId = json.getString("checkbasicid");// 检查基本表ID
		String opinion = json.getString("opinion");//意见
		try
		{
			opinion = new String(opinion.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		try
		{
			//保存检查情况表数据
			// 根据基本表ID查询
			CheckBasic checkBasic = checkBasicService.getById(basicId);
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			RectifyOpinion rectifyOpinion = new RectifyOpinion();
			rectifyOpinion.setBasic(checkBasic);
			rectifyOpinion.setDelFlag(0);
			rectifyOpinion.setOpinion(opinion);
			rectifyOpinion.setLinkid(linkId);
			rectifyOpinionService.save(rectifyOpinion);
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", linkId);
			jn.put("linkType", "rectify");//返回附件类型 rectify 
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
