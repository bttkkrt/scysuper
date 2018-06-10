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
import com.jshx.gpdb.entity.Supervice;
import com.jshx.gpdb.service.SuperviceService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;

/**
 * 挂牌督办基本信息信息上报
 * @author 费谦
 *
 */

public class ReportGpdbBaseCommand implements Command
{
	private SuperviceService superviceService = (SuperviceService) SpringContextHolder.getBean("superviceService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EntBaseInfoService entBaseInfoService= (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String areaId = obj.getString("areaId");//企业属地id
		String areaName = obj.getString("areaName");//属地名称
		String companyId = obj.getString("companyId");//企业id
		String companyName = obj.getString("companyName");//企业名称
		String dangerName = obj.getString("dangerName");//隐患名称
		String listingTime = obj.getString("listingTime");//挂牌时间
		String sort = obj.getString("sort");//隐患类别
		String level = obj.getString("level");//隐患级别
		String content = obj.getString("content");//隐患内容
		String responsible = obj.getString("responsible");//责任人
		String mobile = obj.getString("mobile");//责任人联系电话
		String unit = obj.getString("unit");//责任单位
		String address = obj.getString("address");//地址
		String term = obj.getString("term");//整改期限
		String rectificationLevel = obj.getString("rectificationLevel");//挂牌督办整改级别
		
		
		try {	
			Supervice supervice = new Supervice();
			supervice.setAreaId(areaId);
			supervice.setAreaName(areaName);
			supervice.setCompanyName(companyName);
			if(companyId != null && !"".equals(companyId))
			{
				EntBaseInfo ent = entBaseInfoService.getById(companyId);
				if(ent.getEnterpriseName().equals(companyName))
				{
					supervice.setAreaId(ent.getEnterprisePossession());
					supervice.setAreaName(ent.getEnterprisePossessionName());
					supervice.setCompanyName(ent.getEnterpriseName());
					supervice.setCompanyId(ent.getId());
				}
			}
			supervice.setDangerName(dangerName);
			supervice.setListingTime(sdf.parse(listingTime));
			supervice.setDangerSort(sort);
			supervice.setDangerLevel(level);
			supervice.setDangerContent(content);
			supervice.setResponsible(responsible);
			supervice.setResponsibleMobile(mobile);
			supervice.setResponsibleUnit(unit);
			supervice.setAddress(address);
			supervice.setRectificationTerm(sdf.parse(term));
			supervice.setRectificationLevel(rectificationLevel);
			supervice.setDelFlag(0);
			supervice.setRectificationState("待审核");
			supervice.setNextRoleCode("A03");
			supervice.setNextOperation("check1");
			supervice.setCreateUserID(userId);
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			supervice.setLinkId(linkId);
			superviceService.save(supervice);
			bd.setCode("0");
			bd.setMessage("挂牌督办信息上报成功!");
			JSONObject json = new JSONObject();
			json.put("linkId", linkId);
			bd.setContent(json.toString());
			
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("挂牌督办信息上报失败!");
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
