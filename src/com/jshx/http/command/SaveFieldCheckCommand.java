package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkLawEnforce.service.CheckLawEnforceService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.UserService;
import com.jshx.utils.DateUtils;

/**
 * 上报现场检查记录 GY-UPDATE 2015-02-10
 */

public class SaveFieldCheckCommand implements Command
{
	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");

	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	
	private CheckLawEnforceService checkLawEnforceService = (CheckLawEnforceService) SpringContextHolder.getBean("checkLawEnforceService");
	
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String basicId = json.getString("checkbasicid");// 检查基本表ID
		String checkPlace = json.getString("checkPlace");// 检查场所
		String checkTime = json.getString("checkTime");// 检查时间
		String jgld = json.getString("jgld");// 监管部门主管领导
		String gsld = json.getString("jgld");// 被检查公司领导
		String cjzj = json.getString("cjzj");//参检专家
		String glfm = json.getString("glfm");//管理方面
		String xwfm = json.getString("xwfm");//行为方面
		String zyhjsb = json.getString("zyhjsb");//作业环境及设备设施
		try
		{
			checkPlace = new String(checkPlace.getBytes("ISO-8859-1"), "utf-8");
			jgld = new String(jgld.getBytes("ISO-8859-1"), "utf-8");
			gsld = new String(gsld.getBytes("ISO-8859-1"), "utf-8");
			cjzj = new String(cjzj.getBytes("ISO-8859-1"), "utf-8");
			glfm = new String(glfm.getBytes("ISO-8859-1"), "utf-8");
			xwfm = new String(xwfm.getBytes("ISO-8859-1"), "utf-8");
			zyhjsb = new String(zyhjsb.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		//2016-08-02 zhangzhouhua 变更检查任务字段 

		/*String checkSta = json.getString("checkSta");// 检查时间起
		String checkEnd = json.getString("checkEnd");// 检查时间止
		String checkOrganization = json.getString("checkOrganization");// 检查机构
		String lawEnforce = json.getString("lawEnforce");// 执法人ID，以|分隔
		try
		{
			checkPlace = new String(checkPlace.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		try
		{
			checkOrganization = new String(checkOrganization.getBytes("ISO-8859-1"), "utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}*/
		
		try
		{
			// 根据基本表ID查询
			CheckBasic checkBasic = checkBasicService.getById(basicId);
			if (null != checkBasic)
			{
				// 更新基本表内容
				checkBasic.setCheckPlace(checkPlace);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				checkBasic.setCheckTime(DateUtils.StringToDate(checkTime,sdf));
				checkBasic.setJgld(jgld);
				checkBasic.setGsld(gsld);
				checkBasic.setCjzj(cjzj);
				checkBasic.setXwfm(xwfm);
				checkBasic.setGlfm(glfm);
				checkBasic.setZyhjsb(zyhjsb);
				checkBasicService.update(checkBasic);
				
/*				//保存执法人信息
				String [] lawEnforceS = lawEnforce.split("\\|");
				for(String userId : lawEnforceS)
				{
					User lawer = userService.findUserById(userId);
					if ( null != lawer)
					{
						CheckLawEnforce  checkLawEnforce = new CheckLawEnforce();
						checkLawEnforce.setBasic(checkBasic);
						checkLawEnforce.setCheckUser(lawer);
						checkLawEnforce.setDelFlag(0);
						checkLawEnforceService.save(checkLawEnforce);
					}
				}*/
				
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

	public boolean isNum(String str)
	{
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}
