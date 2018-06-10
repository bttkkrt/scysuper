package com.jshx.http.command;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.ggl.entity.PublicBoard;
import com.jshx.ggl.service.PublicBoardService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.jccbgl.entity.DetOverStaMan;
import com.jshx.jccbgl.service.DetOverStaManService;
import com.jshx.module.admin.service.CodeService;

public class GetJccbDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private DetOverStaManService detOverStaManService = (DetOverStaManService) SpringContextHolder.getBean("detOverStaManService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 DetOverStaMan detOverStaMan=detOverStaManService.getById(id);
				if(null!=detOverStaMan){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(detOverStaMan.getId(),""));//主键
					json.put("samEncode",StringTools.NullToStr(detOverStaMan.getSampleEncoding(),""));//样品编码
					Map m = new HashMap();
					m.put("codeName", "检测类别");
					m.put("itemValue",detOverStaMan.getDetectionCategory() );
					String deteCategory=codeService.findCodeValueByMap(m).getItemText();
					json.put("deteCategory",deteCategory);//检测类别
					json.put("testPosition",StringTools.NullToStr(detOverStaMan.getTestPosition(),""));//检测岗位
					json.put("testItems",StringTools.NullToStr(detOverStaMan.getTestItems(),""));//检测项目
					json.put("testResult",StringTools.NullToStr(detOverStaMan.getTestResult(),""));//检测结果
					json.put("standValue",StringTools.NullToStr(detOverStaMan.getStandardValue(),""));//标准值
					json.put("deteMechanism",StringTools.NullToStr(detOverStaMan.getDetectionMechanism(),""));//检测机构
					json.put("remark",StringTools.NullToStr(detOverStaMan.getRemark(),""));//备注
					br.setContent(json.toString());
					
				}else{
					br.setCode("1");
					br.setMessage("无数据");
				}
			}catch(Exception e){
				br.setCode("2");
				br.setMessage("异常");
				e.printStackTrace();
			}
		return br;
	}
}
