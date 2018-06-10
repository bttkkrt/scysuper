package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.ggl.entity.PublicBoard;
import com.jshx.ggl.service.PublicBoardService;
import com.jshx.gzk.entity.InformCard;
import com.jshx.gzk.service.InformCardService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;

/**
 * 获取告知卡详情接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetGzkDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private InformCardService informCardService = (InformCardService) SpringContextHolder.getBean("informCardService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 InformCard informCard=informCardService.getById(id);
				if(null!=informCard){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(informCard.getId(),""));//主键
					json.put("type",StringTools.NullToStr(informCard.getInformType(),""));//告知卡类别
					json.put("content",StringTools.NullToStr(informCard.getInformContent(),""));//告知卡内容
					json.put("address",StringTools.NullToStr(informCard.getInformAddress(),""));//告知卡地址
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
