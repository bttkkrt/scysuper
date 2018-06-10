package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.scsbss.entity.ProDev;
import com.jshx.scsbss.service.ProDevService;

/**
 * 生产设备设施详情
 * @author 费谦 2015-10-13
 */
public class GetScsbssDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ProDevService proDevService = (ProDevService) SpringContextHolder.getBean("proDevService");
//	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			ProDev proDev=proDevService.getById(id);
			if(null!=proDev){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("workName",null==proDev.getDeviceWorkshopName()?"":proDev.getDeviceWorkshopName()  );
				json.put("name",null==proDev.getDeviceName()?"":proDev.getDeviceName());
				json.put("use", null==proDev.getDeviceUse()?"":proDev.getDeviceUse() );
				json.put("code", null==proDev.getDeviceCode()?"":proDev.getDeviceCode() );
				json.put("type", null==proDev.getDeviceType()?"":proDev.getDeviceType() );
				json.put("manufactureDate", null==proDev.getDeviceManufactureDate()?"":sdf.format(proDev.getDeviceManufactureDate()) );
				json.put("proCompany", null==proDev.getDeviceProductCommpnay()?"":proDev.getDeviceProductCommpnay() );
				json.put("remark", null==proDev.getDeviceRemark()?"":proDev.getDeviceRemark() );
				
				Map<String,Object> map1=new HashMap<String, Object>();
				map1.put("picType", "cchgzfj");
				map1.put("linkId", proDev.getLinkId());
				map1.put("mkType", "scsbss");
				DataBean bean1 = httpService.getPhotoListByType(map1);
				String filepath1 = bean1.getRname();
				json.put("filePath1",null==filepath1?"":filepath1 );
				
				Map<String,Object> map2=new HashMap<String, Object>();
				map2.put("picType", "sywhsmfj");
				map2.put("linkId", proDev.getLinkId());
				map2.put("mkType", "scsbss");
				DataBean bean2 = httpService.getPhotoListByType(map2);
				String filepath2 = bean2.getRname();
				json.put("filePath2",null==filepath2?"":filepath2 );
				
				Map<String,Object> map3=new HashMap<String, Object>();
				map3.put("picType", "azjswjfj");
				map3.put("linkId", proDev.getLinkId());
				map3.put("mkType", "scsbss");
				DataBean bean3 = httpService.getPhotoListByType(map3);
				String filepath3 = bean3.getRname();
				json.put("filePath3",null==filepath3?"":filepath3 );
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