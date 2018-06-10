package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.Information;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.swgl.service.ReceiveInformationService;

public class GetRecInfoListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ReceiveInformationService receiveInformationService = (ReceiveInformationService)SpringContextHolder.getBean("receiveInformationService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
		public BaseResponse execute(JSONObject obj) {
		 	SummaryBean bd = new  SummaryBean();
		    String userId = obj.getString("userId").toString();
		    String pageNum = obj.getString("pageNum");//页数
			String pageSize = obj.getString("pageSize");//每页条数
			String type=obj.getString("type");//0：能查看的 1：待审核的
			String reTitle = obj.getString("reTitle");//标题
			int start = Integer.parseInt(pageNum);
			int limit = Integer.parseInt(pageSize);
			int s = (start-1)*limit;
			Map map = new HashMap();
			map.put("pageNum", s);
			map.put("pageSize", limit);
			map.put("type",type);
			map.put("createUserID", userId);
			if(reTitle != null && !"".equals(reTitle))
		    {
				 	map.put("infoTitle", "%"+reTitle+"%");
		 	}
			try {
				
					map.put("sqlID", "querySwglReadCountByMap");
					int total = httpService.getListCountbyMap(map);
					map.put("sqlID", "querySwglReadListByMap");
					List<Map>  receiveInformation= httpService.getListByMap(map);
				     if(receiveInformation!=null&&!receiveInformation.isEmpty()){
				    	JSONArray ja = new JSONArray();
				    	for(Map mm:receiveInformation){
				    		JSONObject jo = new JSONObject();
				    		jo.put("id",null==mm.get("id")?"":mm.get("id"));
				    		jo.put("reTitle",null==mm.get("reTitle")?"":mm.get("reTitle"));//收文标题
				    		Map m = new HashMap();
							m.put("codeName", "来文类型");
							m.put("itemValue", mm.get("retype"));
							String retype=codeService.findCodeValueByMap(m).getItemText();
				    		jo.put("retype",retype);//来文类型
				    		jo.put("reDept",null==mm.get("reDept")?"":mm.get("reDept"));//来文单位
				    		jo.put("retime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mm.get("retime")));//来文时间
				    		jo.put("readed",null==mm.get("readed")?"":mm.get("readed"));//阅读记录
				    		ja.add(jo);
				    	}
					    bd.setTotal(total+"");
					    bd.setContent(ja.toString());
					    int page = total%limit==0?total/limit:(total/limit+1);
					    bd.setPage(page+"");
					    bd.setCode("0");
					    bd.setMessage("查询成功!");
				        }else{
					     bd.setCode("1");
					     bd.setMessage("无查询结果!");
				        }
			
				
			} catch (Exception e) {
				bd.setCode("1");
				bd.setMessage("查询失败!");
				e.printStackTrace();
			}
			return bd;
		}
	}
