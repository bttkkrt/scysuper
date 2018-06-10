package com.jshx.http.command;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.service.DistributeItemService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.JsonToObject;
import com.jshx.safeInspectDistribute.entity.SafeInspectDistribute;
import com.jshx.safeInspectDistribute.service.SafeInspectDistributeService;

public class SaveInspectItemCommand implements Command
{
	private DistributeItemService distributeItemService = (DistributeItemService) SpringContextHolder.getBean("distributeItemService");
	private SafeInspectDistributeService safeInspectDistributeService = (SafeInspectDistributeService) SpringContextHolder.getBean("safeInspectDistributeService");

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		try {
			String id = json.getString("id");//获取任务项的id
			String isinspect = json.getString("isinspect");//获取是否符合 2:符合  3:不符合
			String userId = json.getString("userId");//获取用户的id
			String longitude = json.getString("longitude");//经度
			String latitude = json.getString("latitude");//纬度
			String local = json.getString("local");//位置
			String describe = json.getString("describe");//描述
			describe=new String(describe.getBytes("ISO-8859-1"),"utf-8");  
			local=new String(local.getBytes("ISO-8859-1"),"utf-8");  
			
			DistributeItem item = new DistributeItem();
			item = distributeItemService.getById(id);
			//DistributeItem tempItem = (DistributeItem)JsonToObject.convert(item,json);//将json格式转换为安全检查项对象
			item.setIsinspect(isinspect);
			item.setUserId(userId); 
			item.setId(id);
			item.setReportTime(df.format(new Date()));
			item.setLongitude(longitude);
			item.setLatitude(latitude);
			item.setLocal(local);
			item.setDescribe(describe);
			distributeItemService.update(item);
			
			SafeInspectDistribute safeInspectDistribute = safeInspectDistributeService.getById(item.getDistributeId());
			//0=源任务，1=某天某人安全检查初始化任务  2:安全检查项全部符合  3：安全检查项存在不符合项
			if("1".equals(safeInspectDistribute.getTaskStatus())){//如果任务状态为1，
				safeInspectDistribute.setTaskStatus("2");
				
			}
			if("3".equals(isinspect)){
				safeInspectDistribute.setTaskStatus(isinspect);
				int count = Integer.parseInt(safeInspectDistribute.getCount());
				count += 1;
				safeInspectDistribute.setCount("" + count);//设置不合格数
			}
			safeInspectDistribute.setLongitude(longitude);
			safeInspectDistribute.setLatitude(latitude);
			safeInspectDistribute.setLocal(local);
			safeInspectDistribute.setReportTime(df.format(new Date()));
			safeInspectDistributeService.update(safeInspectDistribute);
			
			
			bd.setCode("0");
			bd.setMessage("上报成功");
			JSONObject jn = new JSONObject();
			jn.put("linkId", item.getId());
			bd.setContent(jn.toString());
		} catch (RuntimeException e) {
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("上报失败");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
	
	public boolean isNum(String str){		
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");	
	}

}
