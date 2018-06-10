package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.commonTrouble.service.CommoTroubleService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;

/**
 * 获取一般安全隐患信息的详情
 * @author 李军 2013-07-23
 *
 */

public class GetCommonTroubleViewCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private CommoTroubleService commoTroubleService = (CommoTroubleService) SpringContextHolder.getBean("commoTroubleService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//获取一般安全隐患信息id
		String linkId = json.getString("linkId");//获取关联id
		try {
			CommoTrouble commoTrouble = commoTroubleService.getById(id);
			Map map = new HashMap();
			map.put("type", "zftp");
			map.put("linkId", linkId);
			DataBean bean01 = httpService.getPhotoListByType(map);
			commoTrouble.setZftp(StringTools.NullToStr(bean01.getRname(),""));//此处获取执法文书图片 附件

			map.put("type", "zgqk");
			DataBean bean02 = httpService.getPhotoListByType(map);
			commoTrouble.setZgqk(bean02.getRname());//此处获取整改情况附件
			commoTrouble.setSzz(commoTrouble.getSzzname());
			map.put("zfws", commoTrouble.getZfws());
			List<DataBean> zfws = httpService.getWSList(map);//根据执法文书代号获取名称
			if(zfws!=null&&!zfws.isEmpty()){
				commoTrouble.setZfws(StringTools.NullToStr(zfws.get(0).getRname(),""));
			}
			
			JSONObject jon = JSONObject.fromObject(commoTrouble);
			bd.setCode("0");
			bd.setMessage("查询成功");
			bd.setContent(jon.toString());
				
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("查询失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
