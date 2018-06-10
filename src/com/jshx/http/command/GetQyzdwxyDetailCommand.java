package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.zdwxyczgc.entity.ComOpeRul;
import com.jshx.zdwxyczgc.service.ComOpeRulService;
import com.jshx.zdwxysbpgfj.entity.ComDanIde;
import com.jshx.zdwxysbpgfj.service.ComDanIdeService;

/**
 * 获取企业重点危险源详情接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetQyzdwxyDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private ComDanIdeService comDanIdeService = (ComDanIdeService) SpringContextHolder.getBean("comDanIdeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		 try{
			 ComDanIde comDanIde=comDanIdeService.getById(id);
				if(null!=comDanIde){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(comDanIde.getId(),""));//主键
					json.put("companyName",StringTools.NullToStr(comDanIde.getCompanyName(),""));//企业名称
					json.put("dangerName",StringTools.NullToStr(comDanIde.getDangerName(),""));//危险源名称
					json.put("dangerType",StringTools.NullToStr(comDanIde.getDangerType(),""));//重点危险源类别
					json.put("dangerLevel",StringTools.NullToStr(comDanIde.getDangerLevel(),""));//重点危险源级别
					json.put("dangerAdd",StringTools.NullToStr(comDanIde.getDangerAddress(),""));//重点危险源地址
					json.put("safePerson",StringTools.NullToStr(comDanIde.getSafePerson(),""));//安全负责人
					json.put("tele",StringTools.NullToStr(comDanIde.getTele(),""));//联系方式
					json.put("longitude",StringTools.NullToStr(comDanIde.getLongitude(),""));//经度
					json.put("latitude",StringTools.NullToStr(comDanIde.getLatitude(),""));//纬度
					Map<String, Object> paraMap = new HashMap<String, Object>();
					paraMap.put("infoId", comDanIde.getId());
					List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
					if(checkRecords.size()==0){
						json.put("checkUsername","");//审核人
						json.put("checkResult","");//审核结果
						json.put("checkRecords","");//审核记录
					}else{
					json.put("checkUsername",checkRecords.get(0).getCheckUsername());//审核人
					json.put("checkResult",checkRecords.get(0).getCheckResult());//审核结果
					json.put("checkRecords",checkRecords.get(0).getCheckUsername()+checkRecords.get(0).getCheckResult());//审核记录
					}
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
