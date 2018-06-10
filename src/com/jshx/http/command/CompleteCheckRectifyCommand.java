package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkContent.service.CheckContentService;
import com.jshx.checkResult.service.CheckResultService;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.UserService;
import com.jshx.utils.DateUtils;

/**
 * 完成整改复查意见书
 *  GY-UPDATE 2015-02-11
 */

public class CompleteCheckRectifyCommand implements Command
{
	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String basicId = json.getString("checkbasicid");// 检查基本表ID
		String rectifyNum = json.getString("rectifyNum");//整改号
		String reviewContent = json.getString("reviewContent");//复查内容
		try
		{
			rectifyNum = new String(rectifyNum.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		try
		{
			reviewContent = new String(reviewContent.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		try
		{
			// 根据基本表ID查询
			CheckBasic checkBasic = checkBasicService.getById(basicId);
			if (null != checkBasic)
			{
				// 更新基本表内容
				checkBasic.setRectifyNum(rectifyNum);
				checkBasic.setReviewContent(reviewContent);
				checkBasic.setRectifyFlag("2");//整改状态设置为完成整改
				checkBasic.setRectifyDoneTime(new Date());
				checkBasicService.update(checkBasic);
				
				bd.setCode("0");
				bd.setMessage("上报成功");
			}
			else
			{
				bd.setCode("1");
				bd.setMessage("上报失败");
			}
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
