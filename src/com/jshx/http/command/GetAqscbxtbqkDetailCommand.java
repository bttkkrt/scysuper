package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscbxtbqk.entity.SecProIns;
import com.jshx.aqscbxtbqk.service.SecProInsService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.dgjl.entity.ProRec;
import com.jshx.dgjl.service.ProRecService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;

/**
 * 获取安全生产保险投保情况详情接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetAqscbxtbqkDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProInsService secProInsService = (SecProInsService) SpringContextHolder.getBean("secProInsService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 SecProIns secProIns=secProInsService.getById(id);
				if(null!=secProIns){
					
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(secProIns.getId(),""));//主键
					json.put("enterPerson",StringTools.NullToStr(secProIns.getInsuranceEnterprisePersons(),""));//企业总人数
					json.put("workCount",StringTools.NullToStr(secProIns.getInsuranceWorkerCount(),""));//一线员工数
					json.put("personCount",StringTools.NullToStr(secProIns.getInsurancePersonCount(),""));//安全生产责任险参保人数
					json.put("totalFee",StringTools.NullToStr(secProIns.getInsuranceTotalFee(),""));//安全生产责任险总保费
					json.put("totalInsured",StringTools.NullToStr(secProIns.getInsuranceTotalInsured(),""));//安全生产责任险总保额
					json.put("employCount",StringTools.NullToStr(secProIns.getInsuranceEmployerCount(),""));//雇主责任险参保人数
					json.put("employFee",StringTools.NullToStr(secProIns.getInsuranceEmployerFee(),""));//雇主责任险总保费
					json.put("employInsured",StringTools.NullToStr(secProIns.getInsuranceEmployerInsured(),""));//雇主责任险总保额
					json.put("publicFee",StringTools.NullToStr(secProIns.getInsurancePublicFee(),""));//公众责任险总保费
					json.put("publicInsured",StringTools.NullToStr(secProIns.getInsurancePublicInsured(),""));//公众责任险总保额
					json.put("teamFee",StringTools.NullToStr(secProIns.getInsuranceTeamFee(),""));//团体人身意外伤害险总保费
					json.put("teamInsured",StringTools.NullToStr(secProIns.getInsuranceTeamInsured(),""));//团体人身意外伤害险总保额
					json.put("company",StringTools.NullToStr(secProIns.getInsuranceCompnay(),""));//承保保险公司
					json.put("time",null==secProIns.getInsuranceTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(secProIns.getInsuranceTime()));//投保时间

					
					//增加其他保险情况 2016-03-03
					json.put("otherEmployerCount",StringTools.NullToStr(secProIns.getOtherEmployerCount(),""));//其他参保人数
					json.put("otherEmployerFee",StringTools.NullToStr(secProIns.getOtherEmployerFee(),""));//其他总保费
					json.put("otherEmployerInsured",StringTools.NullToStr(secProIns.getOtherEmployerInsured(),""));//其他总保额
					
					br.setContent(json.toString());
					br.setCode("0");
					br.setMessage("成功");
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
