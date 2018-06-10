package com.jshx.http.command;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.entity.WsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;

/**
 * 获取一维代码表数据
 * @author lj
 *
 */

public class GetWsTypeListCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private InstrumentsInfoService instrumentsInfoService = (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");//用户id
		String caseId = obj.getString("caseId");//案件id
		String[] wsztname = {"","询问通知书","询问笔录","勘验笔录","抽样取证凭证","先行登记保存证据审批表","先行登记保存证据通知书","先行登记保存证据处理审批表",
				"先行登记保存证据处理决定书","现场检查记录","现场处理措施决定书","责令限期整改指令书","整改复查意见书","强制措施决定书","鉴定委托书","行政处罚告知书",
				"当事人陈述申辩笔录","听证告知书","听证会通知书","听证笔录","听证会报告书","案件处理呈批表","行政处罚集体讨论记录","行政（当场）处罚决定书（单位）",
				"行政（当场）处罚决定书（个人）","行政处罚决定书（单位）","行政处罚决定书（个人）","罚款催缴通知书","延期（分期）缴纳罚款审批表","延期（分期）缴纳罚款批准书",
				"强制执行申请书","结案审批表","案件移送审批表","案件移送书","调查报告","特殊事项审批表"};
		
		try {
			JSONObject jo = new JSONObject();
			JSONArray wsList = new JSONArray();
			JSONArray qtList = new JSONArray();
			Map m = new HashMap();
			m.put("caseId", caseId);
			List<InstrumentsInfo> list  = instrumentsInfoService.findInstrumentsInfos(m);
			CaseInfo ca = caseInfoService.getById(caseId);
			
			String[] sl = new String[100];
			String[] sfcz = new String[100];
			
			for(int i=1;i<=wsztname.length;i++)
			{
				int num = 0;
				String aa = "0";
				for(InstrumentsInfo in:list)
				{
					if(in.getInstrumentType().equals(i+""))
					{
						num ++;
					}
				}
				sl[i] = num+"";
				if(num > 0)
				{
					aa = "1";
				}
				sfcz[i] = aa;
			}
			
			//取证 1 2 3 4 
			//询问通知书
			wsList.add(getWsInfo("1",wsztname[1],sl[1]+"",sfcz[1],"0"));
			// 询问笔录 
			wsList.add(getWsInfo("2",wsztname[2],sl[2]+"",sfcz[2],"0"));
			//勘验笔录
			wsList.add(getWsInfo("3",wsztname[3],sl[3]+"",sfcz[3],"0"));
			// 抽样取证凭证
			wsList.add(getWsInfo("4",wsztname[4],sl[4]+"",sfcz[4],"1"));
			
			//调查报告 34
			wsList.add(getWsInfo("34",wsztname[34],sl[34]+"",sfcz[34],"1"));
			
			//行政处罚集体讨论记录 22
			wsList.add(getWsInfo("22",wsztname[22],sl[22]+"",sfcz[22],"1"));
			
			//行政处罚告知书 15
			wsList.add(getWsInfo("15",wsztname[15],sl[15]+"",sfcz[15],"1"));
			
			//当事人陈述申辩笔录 16
			wsList.add(getWsInfo("16",wsztname[16],sl[16]+"",sfcz[16],"0"));
			
			//听证会通知书 18
			wsList.add(getWsInfo("18",wsztname[18],sl[18]+"",sfcz[18],"0"));
			 
			//听证笔录 19
			wsList.add(getWsInfo("19",wsztname[19],sl[19]+"",sfcz[19],"0"));
			
			//听证会报告书 20
			wsList.add(getWsInfo("20",wsztname[20],sl[20]+"",sfcz[20],"1"));
			
			//案件处理呈批表 21
			wsList.add(getWsInfo("21",wsztname[21],sl[21]+"",sfcz[21],"1"));
			
			//行政处罚决定书
			if(ca.getPersonType().equals("1")) //个人  行政处罚决定书（个人） 26
			{
				wsList.add(getWsInfo("26",wsztname[26],sl[26]+"",sfcz[26],"1"));
			}
			else //行政处罚决定书（单位） 25
			{
				wsList.add(getWsInfo("25",wsztname[25],sl[25]+"",sfcz[25],"1"));
			}
			
			//结案审批表 31
			wsList.add(getWsInfo("31",wsztname[31],sl[31]+"",sfcz[31],"0"));
			
			//其他文书
			//先行登记保存证据审批表5
			qtList.add(getWsInfo("5",wsztname[5],sl[5]+"",sfcz[5],"0"));

			//先行登记保存证据通知书6
			qtList.add(getWsInfo("6",wsztname[6],sl[6]+"",sfcz[6],"0"));

			//先行登记保存证据处理审批表7
			qtList.add(getWsInfo("7",wsztname[7],sl[7]+"",sfcz[7],"0"));

			//先行登记保存证据处理决定书8
			qtList.add(getWsInfo("8",wsztname[8],sl[8]+"",sfcz[8],"0"));

			//现场检查记录9
			qtList.add(getWsInfo("9",wsztname[9],sl[9]+"",sfcz[9],"0"));

			//现场处理措施决定书10
			qtList.add(getWsInfo("10",wsztname[10],sl[10]+"",sfcz[10],"0"));

			//责令限期整改指令书11
			qtList.add(getWsInfo("11",wsztname[11],sl[11]+"",sfcz[11],"0"));

			//整改复查意见书12
			qtList.add(getWsInfo("12",wsztname[12],sl[12]+"",sfcz[12],"0"));

			//强制措施决定书13
			qtList.add(getWsInfo("13",wsztname[13],sl[13]+"",sfcz[13],"0"));

			//鉴定委托书14
			qtList.add(getWsInfo("14",wsztname[14],sl[14]+"",sfcz[14],"0"));

			//行政（当场）处罚决定书（单位）23
			qtList.add(getWsInfo("23",wsztname[23],sl[23]+"",sfcz[23],"0"));
			
			//行政（当场）处罚决定书（个人）24
			qtList.add(getWsInfo("24",wsztname[24],sl[24]+"",sfcz[24],"0"));

			//罚款催缴通知书27
			qtList.add(getWsInfo("27",wsztname[27],sl[27]+"",sfcz[27],"0"));

			//延期（分期）缴纳罚款审批表28
			qtList.add(getWsInfo("28",wsztname[28],sl[28]+"",sfcz[28],"0"));

			//延期（分期）缴纳罚款批准书29
			qtList.add(getWsInfo("29",wsztname[29],sl[29]+"",sfcz[29],"0"));

			//强制执行申请书30
			qtList.add(getWsInfo("30",wsztname[30],sl[30]+"",sfcz[30],"0"));

			//案件移送审批表32
			qtList.add(getWsInfo("32",wsztname[32],sl[32]+"",sfcz[32],"0"));

			//案件移送书33
			qtList.add(getWsInfo("33",wsztname[33],sl[33]+"",sfcz[33],"0"));
			
			//特殊事项审批表35
			qtList.add(getWsInfo("35",wsztname[35],sl[35]+"",sfcz[35],"0"));
			
			jo.put("ajws", wsList);
			jo.put("qtws", qtList);
			
			bd.setContent(jo.toString());
			bd.setCode("0");
			bd.setMessage("查询成功!");
		} catch (Exception e) {
			bd.setCode("1");
			bd.setMessage("查询失败!");
			e.printStackTrace();
		}
		return bd;
	}
	
	public JSONObject getWsInfo(String wsid,String wsname,String wssl,String sfcz,String jt)
	{
		JSONObject jo = new JSONObject();
		jo.put("id", wsid);
		jo.put("wsname", wsname);
		jo.put("wssl", wssl);
		jo.put("sfcz", sfcz);
		jo.put("jt", jt);
		return jo;
	}
	
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
