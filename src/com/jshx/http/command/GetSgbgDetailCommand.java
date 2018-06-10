package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import com.jshx.sgbgdchcl.entity.AccRepInvHan;
import com.jshx.sgbgdchcl.service.AccRepInvHanService;
import com.jshx.yjya.entity.EmePla;
import com.jshx.yjya.service.EmePlaService;

/**
 * 获取事故报告、调查和处理详情接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetSgbgDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private AccRepInvHanService accRepInvHanService = (AccRepInvHanService) SpringContextHolder.getBean("accRepInvHanService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 AccRepInvHan accRepInvHan=accRepInvHanService.getById(id);
				if(null!=accRepInvHan){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(accRepInvHan.getId(),""));//主键
					json.put("accidentId",StringTools.NullToStr(accRepInvHan.getAccidentId(),""));//事故编号
					json.put("accidentName",StringTools.NullToStr(accRepInvHan.getAccidentName(),""));//事故名称
					json.put("accidentTime",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(accRepInvHan.getAccidentTime()),""));//事故发生时间
					json.put("descript",StringTools.NullToStr(accRepInvHan.getAccidentDescrip(),""));//事故经过
					json.put("reason",StringTools.NullToStr(accRepInvHan.getAccidentReason(),""));//事故原因
					json.put("concussionsNum",StringTools.NullToStr(accRepInvHan.getConcussionsNum(),""));//轻伤人数
					json.put("woundedNum",StringTools.NullToStr(accRepInvHan.getWoundedNum(),""));//重伤人数
					json.put("deathNum",StringTools.NullToStr(accRepInvHan.getDeathNum(),""));//死亡人数
					json.put("loss",StringTools.NullToStr(accRepInvHan.getEconomicLoss(),""));//经济损失
					Map m = new HashMap();
					m.put("codeName", "事故级别");
					m.put("itemValue", accRepInvHan.getAccidentLevel());
					String level=codeService.findCodeValueByMap(m).getItemText();
					json.put("level",level);//事故级别
					
					Map m2 = new HashMap();
					m2.put("codeName", "事故类别");
					m2.put("itemValue", accRepInvHan.getAccidentLevel());
					String type=codeService.findCodeValueByMap(m2).getItemText();
					json.put("type",type);//事故类别
					json.put("teamNumber",StringTools.NullToStr(accRepInvHan.getInverstTeamNumber(),""));//调查组成员
					json.put("responsible",StringTools.NullToStr(accRepInvHan.getAccidentResponsible(),""));//事故责任
					json.put("suggest",StringTools.NullToStr(accRepInvHan.getHandleSuggest(),""));//处理建议
					json.put("zgcs",StringTools.NullToStr(accRepInvHan.getMethod(),""));//处理建议
					json.put("remark",StringTools.NullToStr(accRepInvHan.getRemark(),""));//备注
					Map map = new HashMap();
					map.put("linkId",accRepInvHan.getLinkId());
					map.put("mkType", "sgbgdchcl1");
					map.put("picType","sgbgdchclfj1");
					DataBean bean = httpService.getPhotoListByType(map);
					String sgPhoto = bean.getRname();
					json.put("sgPhoto",null==sgPhoto?"":sgPhoto);//事故图片
					
					Map map2 = new HashMap();
					map.put("linkId",accRepInvHan.getLinkId());
					map.put("mkType", "sgbgdchcl2");
					map.put("picType","sgbgdchclfj2");
					DataBean bean2 = httpService.getPhotoListByType(map2);
					String zghPhoto = bean.getRname();
					json.put("zghPhoto",null==zghPhoto?"":zghPhoto);//整改后图片
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
