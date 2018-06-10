package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.zdwxysbpgfj.entity.ComDanIde;
import com.jshx.zdwxysbpgfj.service.ComDanIdeService;
import com.jshx.zdwxyyjcz.entity.ComDanEme;
import com.jshx.zdwxyyjcz.service.ComDanEmeService;
/**
 * 获取企业重点危险源的应急处置详情接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetQyzdwxyyjczDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ComDanEmeService comDanEmeService = (ComDanEmeService) SpringContextHolder.getBean("comDanEmeService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		 try{
			 ComDanEme comDanEme=comDanEmeService.getById(id);
				if(null!=comDanEme){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(comDanEme.getId(),""));//主键
					json.put("dangerLevel",StringTools.NullToStr(comDanEme.getDangerLevel(),""));//重点危险源级别
					json.put("dangerType",StringTools.NullToStr(comDanEme.getDangerType(),""));//重点危险源类别
					json.put("emerName",StringTools.NullToStr(comDanEme.getEmergencyName(),""));//应急处置名称
					json.put("emerContent",StringTools.NullToStr(comDanEme.getEmergencyContent(),""));//应急处置内容
					json.put("dangerName",StringTools.NullToStr(comDanEme.getDangerName(),""));//重点危险源名称
					Map map = new HashMap();
					map.put("linkId",comDanEme.getLinkId());
					map.put("mkType", "zdwxyyjcz");
					map.put("picType","zdwxyyjczfj");
					DataBean bean = httpService.getPhotoListByType(map);
					json.put("filePath",null==bean.getRname()?"":bean.getRname());//附件url
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
