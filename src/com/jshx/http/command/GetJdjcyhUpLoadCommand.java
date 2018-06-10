package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

public class GetJdjcyhUpLoadCommand implements Command{
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String troubleName = obj.getString("troubleName");//隐患名称
		String troubleSource = obj.getString("troubleSource");//隐患来源
		String troubleAdd = obj.getString("troubleAdd");//隐患地址
		String troubleLevel = obj.getString("troubleLevel");//隐患级别
		String troubleSort = obj.getString("troubleSort");//隐患类别
		String recTerm = obj.getString("recTerm");//整改期限
		
		
		try {	
			TroMan troMan = new TroMan();
			troMan.setTroubleAdd(troubleAdd);
			troMan.setTroubleLevel(troubleLevel);
			troMan.setTroubleName(troubleName);
			troMan.setTroubleSort(troubleSort);
			troMan.setTroubleSource(troubleSource);
			troMan.setDelFlag(0);
			troMan.setRectificationTerm(new SimpleDateFormat("yyyy-MM-dd").parse(recTerm));
			troMan.setUserId(userId);
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			//根据获取一样用户的区域信息
			Map map = new HashMap();
			map.put("id",userId);
			map.put("sqlID", "queryCompanyInfoHttp");
			Map company = httpService.getMapByMap(map);
			if(company!=null){
				troMan.setCompanyId(company.get("companyId")+"");
				troMan.setCompanyName(company.get("companyName")+"");
				troMan.setAreaId(company.get("areaId")+"");
				troMan.setAreaName(company.get("areaName")+"");
			}
			
			troMan.setLinkId(linkId);
			troManService.save(troMan);
			bd.setCode("0");
			bd.setMessage("隐患信息上报成功!");
			JSONObject json = new JSONObject();
			json.put("linkId", linkId);
			bd.setContent(json.toString());
			
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("隐患整改上报失败!");
			e.printStackTrace();
		}
		return bd;
	}
}
