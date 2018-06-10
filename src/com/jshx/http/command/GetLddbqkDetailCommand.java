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
import com.jshx.lddbqk.entity.LeaCla;
import com.jshx.lddbqk.service.LeaClaService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;

/**
 * 相关方详情
 * @author 费谦 2015-10-13
 */
public class GetLddbqkDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private LeaClaService leaClaService = (LeaClaService) SpringContextHolder.getBean("leaClaService");
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
			LeaCla leaCla=leaClaService.getById(id);
			if(null!=leaCla){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("planMonth",null==leaCla.getPlannedMonth()?"":new SimpleDateFormat("yyyy-MM").format(leaCla.getPlannedMonth() ) );
				json.put("companyName",null==leaCla.getCompanyName()?"":leaCla.getCompanyName() );
				
				//审核记录
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("infoId", leaCla.getId());
				List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
				String recordStr = StringTools.checkRecordToStr(checkRecords);
				json.put("auditRecord",recordStr );
				
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("picType", "jhfj");
				map.put("linkId", leaCla.getLinkId());
				map.put("mkType", "lddbqk");
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