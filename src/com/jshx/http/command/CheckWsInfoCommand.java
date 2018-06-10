package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;

/**
 * 文书是否可录入
 * @author 陆婷 2015-11-5
 *
 */

public class CheckWsInfoCommand implements Command
{
	private InstrumentsInfoService instrumentsInfoService = (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String caseId = obj.getString("caseId");//案件编号
		String type = obj.getString("instrumentType");//文书类型
		
		try {	
			Map m = new HashMap();
			m.put("caseId", caseId);
			List<InstrumentsInfo> sylist  = instrumentsInfoService.findInstrumentsInfos(m);
			m.put("ifChecklist", "1");
			List<InstrumentsInfo> list  = instrumentsInfoService.findInstrumentsInfos(m);
			String syws = ",";
			String ws = ",";
			for(InstrumentsInfo in:sylist)
			{
				syws += in.getInstrumentType() + ",";
			}
			for(InstrumentsInfo in:list)
			{
				ws += in.getInstrumentType() + ",";
			}
			String ifCan = "0";
			String message = "";
			if(type.equals("2"))
			{
				if(!syws.contains(",1,") && !ws.contains(",1,"))
				{
					ifCan = "1";
					message = "录入询问笔录前必须先录入询问通知书！";
				}
				else if(syws.contains(",1,") && !ws.contains(",1,"))
				{
					ifCan = "1";
					message = "询问通知书审批通过后才可录入询问笔录！";
				}
			}
			else if(type.equals("6"))
			{
				if(!syws.contains(",5,") && !ws.contains(",5,"))
				{
					ifCan = "1";
					message = "录入先行登记保存证据通知书前必须先录入先行登记保存证据审批表！";
				}
				else if(syws.contains(",5,") && !ws.contains(",5,"))
				{
					ifCan = "1";
					message = "先行登记保存证据审批表审批通过后才可录入先行登记保存证据通知书！";
				}
			}
			else if(type.equals("7"))
			{
				if(!syws.contains(",5,") && !ws.contains(",5,"))
				{
					ifCan = "1";
					message = "录入先行登记保存证据处理审批表前必须先录入先行登记保存证据审批表！";
				}
				else if(syws.contains(",5,") && !ws.contains(",5,"))
				{
					ifCan = "1";
					message = "先行登记保存证据审批表审批通过后才可录入先行登记保存证据处理审批表！";
				}
			}
			else if(type.equals("8"))
			{
				if(!syws.contains(",5,") && !ws.contains(",5,"))
				{
					ifCan = "1";
					message = "录入先行登记保存证据处理决定书前必须先录入先行登记保存证据审批表";
				}
				else if(syws.contains(",5,") && !ws.contains(",5,"))
				{
					ifCan = "1";
					message = "先行登记保存证据审批表审批通过后才可录入先行登记保存证据处理决定书！";
				}
				else if(!syws.contains(",6,") && !ws.contains(",6,"))
				{
					ifCan = "1";
					message = "录入先行登记保存证据处理决定书前必须先录入先行登记保存证据通知书";
				}
				else if(syws.contains(",6,") && !ws.contains(",6,"))
				{
					ifCan = "1";
					message = "先行登记保存证据通知书审核通过后才可录入先行登记保存证据处理决定书";
				}
			}
			else if(type.equals("10"))
			{
				if(!syws.contains(",9,") && !ws.contains(",9,"))
				{
					ifCan = "1";
					message = "录入现场处理措施决定书前必须先录入现场检查记录！";
				}
				else if(syws.contains(",9,") && !ws.contains(",9,"))
				{
					ifCan = "1";
					message = "现场检查记录审核通过后才可录入现场处理措施决定书！";
				}
			}
			else if(type.equals("12"))
			{
				if(!(syws.contains(",10,") || syws.contains(",11,") || syws.contains(",13,")) && !(ws.contains(",10,") || ws.contains(",11,") || ws.contains(",13,")))
				{
					ifCan = "1";
					message = "录入整改复查意见书前必须先录入现场处理措施决定书、责令限期整改指令书和强制措施决定书中的任意一个！";
				}
				else if((syws.contains(",10,") || syws.contains(",11,") || syws.contains(",13,")) && !(ws.contains(",10,") || ws.contains(",11,") || ws.contains(",13,")))
				{
					ifCan = "1";
					message = "现场处理措施决定书、责令限期整改指令书和强制措施决定书中的任意一个审核通过后才可录入整改复查意见书！";
				}
			}
			else if(type.equals("16"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入当事人陈述申辩笔录前必须先录入行政处罚告知书 ！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入当事人陈述申辩笔录！";
				}
			}
			else if(type.equals("17"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入听证告知书前必须先录入行政处罚告知书 ！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入听证告知书！";
				}
			}
			else if(type.equals("19"))
			{
				if(!syws.contains(",18,") && !ws.contains(",18,"))
				{
					ifCan = "1";
					message = "录入听证笔录前必须先录入听证会通知书！";
				}
				else if(syws.contains(",18,") && !ws.contains(",18,"))
				{
					ifCan = "1";
					message = "听证会通知书审核通过后才可录入听证笔录！";
				}
			}
			else if(type.equals("20"))
			{
				if(!syws.contains(",18,") && !ws.contains(",18,"))
				{
					ifCan = "1";
					message = "录入听证会报告书前必须先录入听证会通知书！";
				}
				else if(syws.contains(",18,") && !ws.contains(",18,"))
				{
					ifCan = "1";
					message = "听证会通知书审核通过后才可录入听证会报告书！";
				}
			}
			else if(type.equals("21"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入案件处理呈批表前必须先录入行政处罚告知书！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入案件处理呈批表！";
				}
			}
			else if(type.equals("23"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入行政（当场）处罚决定书（单位）前必须先录入行政处罚告知书！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入行政（当场）处罚决定书（单位）！";
				}
			}
			else if(type.equals("24"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入行政（当场）处罚决定书（个人）前必须先录入行政处罚告知书！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入行政（当场）处罚决定书（个人）！";
				}
			}
			else if(type.equals("25"))
			{
				if(!syws.contains(",21,") && !ws.contains(",21,"))
				{
					ifCan = "1";
					message = "录入行政处罚决定书（单位）前必须先录入案件处理呈批表！";
				}
				else if(syws.contains(",21,") && !ws.contains(",21,"))
				{
					ifCan = "1";
					message = "案件处理呈批表审核通过后才可录入行政处罚决定书（单位）！";
				}
			}
			else if(type.equals("26"))
			{
				if(!syws.contains(",21,") && !ws.contains(",21,"))
				{
					ifCan = "1";
					message = "录入行政处罚决定书（个人）前必须先录入案件处理呈批表！";
				}
				else if(syws.contains(",21,") && !ws.contains(",21,"))
				{
					ifCan = "1";
					message = "案件处理呈批表审核通过后才可录入行政处罚决定书（个人）！";
				}
			}
			else if(type.equals("27"))
			{
				if(!(ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,")))
				{
					ifCan = "1";
					message = "录入罚款催缴通知书前必须先录入行政处罚决定书 ！";
				}
			}
			else if(type.equals("28"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入延期（分期）缴纳罚款审批表前必须先录入行政处罚告知书！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入延期（分期）缴纳罚款审批表！";
				}
				else if(!((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
				{
					ifCan = "1";
					message = "录入延期（分期）缴纳罚款审批表前必须先录入行政处罚决定书！";
				}
				else if(((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
				{
					ifCan = "1";
					message = "行政处罚决定书审核通过后才可录入延期（分期）缴纳罚款审批表！";
				}
			}
			else if(type.equals("29"))
			{
				if(!((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
				{
					ifCan = "1";
					message = "录入延期（分期）缴纳罚款批准书前必须先录入行政处罚决定书！";
				}
				else if(((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
				{
					ifCan = "1";
					message = "行政处罚决定书审核通过后才可延期（分期）缴纳罚款批准书！";
				}
				else if(!syws.contains(",28,") &&  !ws.contains(",28,"))
				{
					ifCan = "1";
					message = "录入延期（分期）缴纳罚款批准书前必须先录入延期（分期）缴纳罚款审批表 ！";
				}
				else if(syws.contains(",28,") &&  !ws.contains(",28,"))
				{
					ifCan = "1";
					message = "延期（分期）缴纳罚款审批表审批通过后才可录入延期（分期）缴纳罚款批准书 ！";
				}
			}
			else if(type.equals("30"))
			{
				if(!syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "录入强制执行申请书前必须先录入行政处罚告知书！";
				}
				else if(syws.contains(",15,") && !ws.contains(",15,"))
				{
					ifCan = "1";
					message = "行政处罚告知书审核通过后才可录入强制执行申请书！";
				}
				else if(!((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
				{
					ifCan = "1";
					message = "录入强制执行申请书前必须先录入行政处罚决定书！";
				}
				else if(((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
				{
					ifCan = "1";
					message = "行政处罚决定书审核通过后才可录入强制执行申请书！";
				}
			}
			else if(type.equals("31"))
			{
				if(!syws.equals(ws))
				{
					ifCan = "1";
					message = "所有录入文书均审核通过后才可录入结案审批表";
				}
			}
			else if(type.equals("32"))
			{
				if(!syws.contains(",34,") && !ws.contains(",34,"))
				{
					ifCan = "1";
					message = "录入案件移送审批表前必须先录入调查报告 ！";
				}
				else if(syws.contains(",34,") && !ws.contains(",34,"))
				{
					ifCan = "1";
					message = "调查报告审核通过后才可录入案件移送审批表！";
				}
			}
			else if(type.equals("33"))
			{
				if(!syws.contains(",32,") && !ws.contains(",32,"))
				{
					ifCan = "1";
					message = "录入案件移送书前必须先录入案件移送审批表！";
				}
				else if(syws.contains(",32,") && !ws.contains(",32,"))
				{
					ifCan = "1";
					message = "案件移送审批表审核通过后才可录入案件移送书！";
				}
			}
			JSONObject json = new JSONObject();
			json.put("code", ifCan);
			json.put("message", message);
			bd.setContent(json.toString());
			bd.setCode("0");
			bd.setMessage("验证成功");
		} catch (Exception e) {
			bd.setCode("2");
			bd.setMessage("系统出错!");
			e.printStackTrace();
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
