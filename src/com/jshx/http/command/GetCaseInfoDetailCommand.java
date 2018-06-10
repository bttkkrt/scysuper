package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.entity.CaseCl;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.entity.CaseZj;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

/**
 * 获取案件详情接口
 * @author 陆婷 2015-11-5
 *
 */
public class GetCaseInfoDetailCommand implements Command{
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取案件id
		
		CaseInfo ca = caseInfoService.getById(id);
		try {
				if(ca!=null){
					JSONObject jo = new JSONObject();
					jo.put("id", StringTools.NullToStr(ca.getId(),""));//主键
					jo.put("caseName",StringTools.NullToStr(ca.getCaseName(),""));//案件名称
					jo.put("caseTime",ca.getCaseTime() != null?new SimpleDateFormat("yyyy-MM-dd").format(ca.getCaseTime()):"");//案件时间
					jo.put("areaName",StringTools.NullToStr(ca.getAreaName(),""));//案发区域
					jo.put("companyName",StringTools.NullToStr(ca.getCompanyName(),""));//企业名称
					
					Map m = new HashMap();
					if(ca.getCaseSource() != null && !"".equals(ca.getCaseSource()))
					{
						m.put("codeName", "案件来源");
						m.put("itemValue", ca.getCaseSource());
						jo.put("caseSource",codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"");//案件来源
					}
					else
					{
						jo.put("caseSource","");//案件来源
					}
					if(ca.getCaseSource().equals("1"))
					{
						jo.put("miborNum",StringTools.NullToStr(ca.getMiborNum(),""));//	轻伤人数（caseSource=1）
						jo.put("injuriesNum",StringTools.NullToStr(ca.getInjuriesNum(),""));//	重伤人数（caseSource=1）
						jo.put("dieNum",StringTools.NullToStr(ca.getDieNum(),""));//	死亡人数（caseSource=1）
						if(ca.getAccidentLevel() != null && !"".equals(ca.getAccidentLevel()))
						{
							m.put("codeName", "事故级别");
							m.put("itemValue", ca.getAccidentLevel());
							jo.put("accidentLevel",codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"");//事故级别
						}
						else
						{
							jo.put("accidentLevel","");//事故级别
						}
						if(ca.getAccidentCategory() != null && !"".equals(ca.getAccidentCategory()))
						{
							m.put("codeName", "事故类别");
							m.put("itemValue", ca.getAccidentCategory());
							jo.put("accidentCategory",codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"");//事故类别
						}
						else
						{
							jo.put("accidentCategory","");//事故类别
						}
					}
					else
					{
						jo.put("miborNum","");//	轻伤人数（caseSource=1）
						jo.put("injuriesNum","");//	重伤人数（caseSource=1）
						jo.put("dieNum","");//	死亡人数（caseSource=1）
						jo.put("accidentLevel","");//事故级别
						jo.put("accidentCategory","");//事故类别
					}
					jo.put("personType",StringTools.NullToStr(ca.getPersonType(),""));//处罚对象
					if(ca.getPersonType().equals("1"))
					{
						jo.put("person",StringTools.NullToStr(ca.getPerson(),""));//被处罚人
						jo.put("sfzh",StringTools.NullToStr(ca.getSfzh(),""));//身份证号
						jo.put("address",StringTools.NullToStr(ca.getAddress(),""));//家庭住址
						jo.put("fddbr","");//法定代表人
						jo.put("xyhm","");//统一社会信用代码
					}
					else if(ca.getPersonType().equals("2"))
					{
						jo.put("person","");//被处罚人
						jo.put("sfzh","");//身份证号
						jo.put("address","");//家庭住址
						jo.put("fddbr",StringTools.NullToStr(ca.getFddbr(),""));//法定代表人
						jo.put("xyhm",StringTools.NullToStr(ca.getXyhm(),""));//统一社会信用代码
					}
					jo.put("companyAddress",StringTools.NullToStr(ca.getCompanyAddress(),""));//单位住址
					jo.put("zw",StringTools.NullToStr(ca.getZw(),""));//职务
					jo.put("age",StringTools.NullToStr(ca.getAge(),""));//年龄
					if(ca.getSex() != null && !"".equals(ca.getSex()))
					{
						m.put("codeName", "性别");
						m.put("itemValue", ca.getSex());
						jo.put("sex",codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"");//性别
					}
					else
					{
						jo.put("sex","");//性别
					}
					jo.put("tele",StringTools.NullToStr(ca.getTele(),""));//电话
					jo.put("zipCode",StringTools.NullToStr(ca.getZipCode(),""));//邮编
					jo.put("caseCause",StringTools.NullToStr(ca.getCaseCause(),""));//案由
					jo.put("caseCondition",StringTools.NullToStr(ca.getCaseCondition(),""));//案件基本情况
					jo.put("personCondition",StringTools.NullToStr(ca.getPersonCondition(),""));//当事人基本情况
					
					String fineType = "";
					if(ca.getFineType() != null)
					{
						if("0".equals(ca.getFineType()))
						{
							fineType = "违法处罚";
						}
						else
						{
							fineType = "事故处罚";
						}
					}
					jo.put("fineType",fineType);//处罚类型
					
					jo.put("undertakerComment",StringTools.NullToStr(ca.getUndertakerComment(),""));//承办人意见
					jo.put("laTime", ca.getLaTime() != null?new SimpleDateFormat("yyyy-MM-dd").format(ca.getLaTime()):"");//立案时间
					jo.put("caseStatus",StringTools.NullToStr(ca.getCaseStatus(),""));//状态
					if(ca.getCaseStatus() != null && "8".equals(ca.getCaseStatus()))
					{
						Map map = new HashMap();
						String day[] = new SimpleDateFormat("yyyy-MM-dd").format(ca.getLaTime()).split("-");
						map.put("fineType", ca.getFineType());
						map.put("glh", day[0]);
						String syah = caseInfoService.getGlhNumListByMap(map);
						jo.put("syah", StringTools.NullToStr(syah,""));//使用案号
						int glhNum = caseInfoService.getMaxGlhNumByMap(map)+1;
						String ss = glhNum + "";
						if(ss.length() == 1)
						{
							ss = "00" + ss;
						}
						else if(ss.length() == 2)
						{
							ss = "0" + ss;
						}
						jo.put("caseId", ss);//立案号
					}
					else
					{
						jo.put("syah", "");//使用案号
						jo.put("caseId", StringTools.NullToStr(ca.getCaseId(),""));//立案号
					}
					
					m.put("infoId", ca.getId());
					List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(m);
					String ss = "";
					for(CheckRecord c:checkRecords)
					{
						ss += new SimpleDateFormat("yyyy-MM-dd").format(c.getCheckTime()==null ?c.getCreateTime():c.getCheckTime())+","+c.getCheckUsername()+c.getCheckResult()+"[备注："+c.getCheckRemark()+"]" + ";\n";
					}
					if(ss.length() != 0)
					{
						ss = ss.substring(0,ss.length()-1);
					}
					jo.put("checkrecord",ss);//审核记录
					m.put("caseId", id);
					JSONArray ja = new JSONArray();
					List<CaseCl> clList = caseInfoService.getCaseClList(m);
					for(CaseCl cl:clList)
					{
						JSONObject jj = new JSONObject();
						jj.put("zjType","");
						if(cl.getZjType() != null)
						{
							if(cl.getZjType().equals("1"))
							{
								jj.put("zjType","现场图片");
							}
							else if(cl.getZjType().equals("2"))
							{
								jj.put("zjType","罚没款收据回执");
							}
						}
						Map map = new HashMap();
						map.put("linkId",cl.getLinkId());
						map.put("mkType", "ajxx");
						map.put("picType","ajclfj");
						DataBean bean =  httpService.getPhotoListByType(map);
						if(bean!=null){
							jj.put("clfj", bean.getRname());
						}
						ja.add(jj);
					}
					JSONArray jb = new JSONArray();
					List<CaseZj> zjList = caseInfoService.getCaseZjList(m);
					for(CaseZj cl:zjList)
					{
						JSONObject jj = new JSONObject();
						jj.put("zjContent", cl.getZjContent());
						jb.add(jj);
					}
					jo.put("clList",ja.toString());//材料列表
					jo.put("zjList",jb.toString());//证据列表
					
					br.setCode("0");
					br.setMessage("成功");
					br.setContent(jo.toString());
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		return br;
	}
}
