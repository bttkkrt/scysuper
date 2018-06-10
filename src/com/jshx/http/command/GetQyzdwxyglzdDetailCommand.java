package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.zdwxyczgc.entity.ComOpeRul;
import com.jshx.zdwxyczgc.service.ComOpeRulService;
import com.jshx.zdwxyglzd.entity.ComManSys;
import com.jshx.zdwxyglzd.service.ComManSysService;

/**
 * 获取企业重点危险源的管理制度详情接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetQyzdwxyglzdDetailCommand {
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ComManSysService comManSysService = (ComManSysService) SpringContextHolder.getBean("comManSysService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		 try{
			 ComManSys comManSys=comManSysService.getById(id);
				if(null!=comManSys){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(comManSys.getId().toString(),""));//主键
					json.put("dangerLevel",StringTools.NullToStr(comManSys.getDangerLevel().toString(),""));//重点危险源级别
					json.put("dangerType",StringTools.NullToStr(comManSys.getDangerType().toString(),""));//重点危险源类别
					json.put("systemName",StringTools.NullToStr(comManSys.getSystemName().toString(),""));//制度名称
					json.put("dangerName",StringTools.NullToStr(comManSys.getDangerName(),""));//重点危险源名称
					Map map = new HashMap();
					map.put("linkId",comManSys.getLinkId());
					map.put("mkType", "zdwxyglzd");
					map.put("picType","zdwxyglzdfj");
					DataBean bean = httpService.getPhotoListByType(map);
					String filepath = bean.getRname();
					json.put("filepath",StringTools.NullToStr(filepath,""));//主键
					br.setContent(json.toString());
					
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			}catch(Exception e){
				br.setCode("2");
				br.setMessage("异常");
				e.printStackTrace();
			}
		return br;
	}
}
