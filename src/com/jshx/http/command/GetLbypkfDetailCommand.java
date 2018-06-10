package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.lbypkfgl.entity.PpeWareManag;
import com.jshx.lbypkfgl.service.PpeWareManagService;
import com.jshx.module.admin.service.CodeService;

/**
 * 劳保用品库房详情
 * @author 费谦 2015-10-13
 */
public class GetLbypkfDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private PpeWareManagService ppeWareManagService = (PpeWareManagService) SpringContextHolder.getBean("ppeWareManagService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			PpeWareManag ppeWareManag=ppeWareManagService.getById(id);
			if(null!=ppeWareManag){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("name",null==ppeWareManag.getPpeName()?"":ppeWareManag.getPpeName()  );
				//进库出库
				Map m = new HashMap();
				m.put("codeName", "进库出库");
				m.put("itemValue", ppeWareManag.getPpeWareType());
				json.put("type",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				
				json.put("num",null==ppeWareManag.getPpeWareNum()?"":ppeWareManag.getPpeWareNum());
				json.put("people", null==ppeWareManag.getPpeWarePeople()?"":ppeWareManag.getPpeWarePeople() );
				json.put("time", null==ppeWareManag.getPpeWareTime()?"":sdf.format(ppeWareManag.getPpeWareTime()) );
				json.put("remark", null==ppeWareManag.getPpeWareRemark()?"":ppeWareManag.getPpeWareRemark() );
				
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