package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.UserService;
import com.jshx.regionCode.service.RegionCodeService;
import com.jshx.utils.DateUtils;
import com.jshx.utils.NumberGenerater;

/**
 * 上报责令限期整改指令书
 *  GY-UPDATE 2015-02-11
 */

public class SaveCheckRectifyCommand implements Command
{
	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");

	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	
	private RegionCodeService regionCodeService = (RegionCodeService) SpringContextHolder.getBean("regionCodeService");
	
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String basicId = json.getString("checkbasicid");// 检查基本表ID
		String rectifyTerm = json.getString("rectifyTerm");//整改项
		String rectifyDate = json.getString("rectifyDate");// 整改日期 yyyy-MM-dd格式
		String rectifyState = json.getString("rectifyState");// 整改问题
		String userId = json.getString("userId");//获取上报人id
		try
		{
			rectifyTerm = new String(rectifyTerm.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		try
		{
			rectifyState = new String(rectifyState.getBytes("ISO-8859-1"), "utf-8");
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
				//根据上报人所属部门生成监管号
				User user = userService.findUserById(userId);
				String deptCode = user.getDeptCode();
				//获取编号开头
				String regionCode = regionCodeService.findCodeByDept(deptCode);
				//生成序号
				NumberGenerater numberGenerater = NumberGenerater.getInstance();
				String currentNum = numberGenerater.geneterNextNumber();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
				String nowYear = formatter.format(new Date());
				
				// 更新基本表内容
				checkBasic.setRectifyYear(nowYear);
				checkBasic.setRectifyNum(regionCode + currentNum);
				
				checkBasic.setRectifyTerm(rectifyTerm);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				checkBasic.setRectifyDate(DateUtils.StringToDate(rectifyDate,sdf));
				checkBasic.setRectifyState(rectifyState);
				checkBasic.setRectifyBeginTime(new Date());//设置整改提出时间为当前
				checkBasic.setRectifyFlag("1");//整改状态设置为有待整改项
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
