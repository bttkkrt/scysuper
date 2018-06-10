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
import com.jshx.fwgl.service.SendInformationService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.impl.UserServiceImpl;
import com.jshx.swgl.service.ReceiveInformationService;

public class GetSendInfoListCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SendInformationService sendInformationService = (SendInformationService)SpringContextHolder.getBean("sendInformationService");
	private UserServiceImpl userService = (UserServiceImpl)SpringContextHolder.getBean("userService");
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
			try {
				if(type.equals("0")){
					//map.put("createUserID", userId);
					map.put("sqlID", "queryFwglReadCountByMap");
					int total = httpService.getListCountbyMap(map);
					map.put("sqlID", "queryFwglReadListByMap");
					List<Map>  sendInformation= httpService.getListByMap(map);
				     if(sendInformation!=null&&!sendInformation.isEmpty()){
				    	 JSONArray ja = new JSONArray();
					    	for(Map mm:sendInformation){
					    		JSONObject jo = new JSONObject();
					    		jo.put("id",mm.get("id").toString());
					    		jo.put("sendTitle",mm.get("sendTitle").toString());//发文标题
					    		Map m = new HashMap();
								m.put("codeName", "来文类型");
								m.put("itemValue", mm.get("sendType"));
								String sendType=codeService.findCodeValueByMap(m).getItemText();
					    		jo.put("sendType",sendType);//发文类型
                                jo.put("signName",null==mm.get("signName")?"":mm.get("signName"));//签发人
                                jo.put("sendTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mm.get("sendTime")));//发文时间
					    		if(mm.get("signTime")==null){
					    			String signTime="";
					    			jo.put("signTime",signTime);//签发时间
					    		}else{
	
					    			jo.put("signTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mm.get("signTime")));//签发时间
					    		}
					    		jo.put("readed",mm.get("readed").toString());//阅读记录
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
					
				}else{
					 //map.put("createUserID", userId);
				     map.put("sqlID", "queryFwglCountByMap");
				     int total = httpService.getListCountbyMap(map);
				     map.put("sqlID", "queryFwglListByMap");
				     List<Map>  sendInformation= httpService.getListByMap(map);
				     if(sendInformation!=null&&!sendInformation.isEmpty()){
				    	 JSONArray ja = new JSONArray();
					    	for(Map mm:sendInformation){
					    		JSONObject jo = new JSONObject();
					    		jo.put("id",mm.get("id").toString());
					    		jo.put("sendTitle",mm.get("sendTitle").toString());//发文标题
					    		Map m = new HashMap();
								m.put("codeName", "来文类型");
								m.put("itemValue", mm.get("sendType"));
								String sendType=codeService.findCodeValueByMap(m).getItemText();
					    		jo.put("sendType",sendType);//发文类型
					    		jo.put("signName",null==mm.get("signName")?"":mm.get("signName"));//签发人
					    		jo.put("sendTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mm.get("sendTime")));//发文时间
					    		if(mm.get("signTime")==null){
					    			String signTime="";
					    			jo.put("signTime",signTime);//签发时间
					    		}else{
					    			jo.put("signTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mm.get("signTime")));//签发时间
					    		}
					    		
					    		jo.put("readed",mm.get("readed").toString());//阅读记录
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
				
				}
				
			} catch (Exception e) {
				bd.setCode("1");
				bd.setMessage("查询失败!");
				e.printStackTrace();
			}
			return bd;
		}
	}
