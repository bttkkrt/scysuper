package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkContent.service.CheckContentService;
import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkResult.service.CheckResultService;
import com.jshx.company.service.CompanyService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.service.UserService;

/**
 * 上报企业安全检查检查 GY-UPDATE 2015-01-19
 */

public class SaveCheckResultCommand implements Command
{
	private CheckResultService checkResultService = (CheckResultService) SpringContextHolder
			.getBean("checkResultService");

	private CheckBasicService checkBasicService = (CheckBasicService) SpringContextHolder.getBean("checkBasicService");

	private CompanyService companyService = (CompanyService) SpringContextHolder.getBean("companyService");

	private CheckContentService checkContentService = (CheckContentService) SpringContextHolder
			.getBean("checkContentService");

	private UserService userService = (UserService) SpringContextHolder.getBean("userService");

	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json)
	{
		SummaryBean bd = new SummaryBean();
		String userId = json.getString("userId");// 检查人id
		String companyId = json.getString("companyId");// 企业ID
		String resultJson = json.getString("resultlist");// 检查结果
		try
		{
			// 存在结果数据保存检查基本表
			JSONObject basicJson = new JSONObject();
			CheckBasic basic = new CheckBasic();
			basic.setChecker(userService.findUserById(userId));
			basic.setDelFlag(0);
			basic.setRectifyFlag("0");// 整改状态设置为无整改项
			basic.setCheckTime(new Date());
			basic.setCompany(companyService.getById(companyId));
			checkBasicService.save(basic);
			basicJson.put("checkbasicid", basic.getId());// 上报之后企业基本表ID
			// 保存检查结果表
			if (!"".equals(resultJson))
			{
				JSONArray resultArry = JSONArray.fromObject(resultJson);
				for (int i = 0; i < resultArry.size(); i++)
				{
					JSONObject resultObj = resultArry.getJSONObject(i);
					String contentid = resultObj.getString("contentid");// 内容Id
					String jcjg = resultObj.getString("jcjg");// 检查结果
					String bz = resultObj.getString("bz");// 备注
	
					try
					{
						bz = new String(bz.getBytes("ISO-8859-1"), "utf-8");
					}
					catch (UnsupportedEncodingException e)
					{
						e.printStackTrace();
					}
	
					CheckResult checkResult = new CheckResult();
					checkResult.setDelFlag(0);
					checkResult.setBasic(basic);
					checkResult.setContent(checkContentService.getById(contentid));
					checkResult.setCheckResult(jcjg);
					checkResult.setRemark(bz);
					checkResultService.save(checkResult);
				}
			}
			bd.setContent(basicJson.toString());
			bd.setCode("0");
			bd.setMessage("上报成功");
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
