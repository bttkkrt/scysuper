package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.jjrktg.entity.ShuHol;
import com.jshx.jjrktg.service.ShuHolService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

/**
 * 节假日开停工详情
 * @author 费谦 2015-10-13
 */
public class GetJjrktgDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ShuHolService shuHolService = (ShuHolService) SpringContextHolder.getBean("shuHolService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			ShuHol shuHol=shuHolService.getById(id);
			if(null!=shuHol){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				String start=(null==shuHol.getHolidayTimeStart()?"":sdf.format(shuHol.getHolidayTimeStart()));
				String end=(null==shuHol.getHolidayTimeEnd()?"":sdf.format(shuHol.getHolidayTimeEnd()));
				json.put("holidayTime",start+"~"+end);
				json.put("companyName",null==shuHol.getCompanyName()?"":shuHol.getCompanyName());
				json.put("areaName", null==shuHol.getAreaName()?"":shuHol.getAreaName());
				Map m = new HashMap();
				m.put("codeName", "是或否");
				m.put("itemValue", shuHol.getIfStart());
				json.put("ifStart",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				json.put("remark", shuHol.getRemark());
				
				//审核记录
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("infoId", shuHol.getId());
				List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
				String recordStr = StringTools.checkRecordToStr(checkRecords);
				json.put("auditRecord",recordStr );
				
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("picType", "jjrktgfj");
				map.put("linkId", shuHol.getLinkId());
				map.put("mkType", "jjrktg");
				DataBean bean = httpService.getPhotoListByType(map);
				String filepath = bean.getRname();
				json.put("filePath",null==filepath?"":filepath );
				
				bd.setContent(json.toString());
				
			}else{
				bd.setCode("1");
				bd.setMessage("无数据");
			}
		}catch(Exception e){
			bd.setCode("2");
			bd.setMessage("异常");
			e.printStackTrace();
		}
		System.out.println(bd.toString());
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}