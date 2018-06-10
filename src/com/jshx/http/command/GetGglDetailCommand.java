package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscgzzd.entity.SecProSys;
import com.jshx.aqscgzzd.service.SecProSysService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.ggl.entity.PublicBoard;
import com.jshx.ggl.service.PublicBoardService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;

/**
 * 获取公告栏详情接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetGglDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private PublicBoardService publicBoardService = (PublicBoardService) SpringContextHolder.getBean("publicBoardService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 PublicBoard publicBoard=publicBoardService.getById(id);
				if(null!=publicBoard){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(publicBoard.getId(),""));//主键
					json.put("type",StringTools.NullToStr(publicBoard.getPublicType(),""));//公告栏类别

					json.put("content",StringTools.NullToStr(publicBoard.getPublicContent(),""));//公告栏内容
					json.put("address",StringTools.NullToStr(publicBoard.getPublicAddress(),""));//公告栏地址
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
