package com.jshx.http.command;


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
import com.jshx.majorTrouble.entity.MajorTrouble;
import com.jshx.majorTrouble.service.MajorTroubleService;

/**
 * 获取重大安全隐患信息的详情
 * @author 李军 2013-07-23
 *
 */

public class GetMajorTroubleViewCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private MajorTroubleService majorTroubleService = (MajorTroubleService) SpringContextHolder.getBean("majorTroubleService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//获取重大安全隐患信息id
		String linkId = json.getString("linkId");//获取关联id
		try {
			Map map = new HashMap();

			map.put("linkId", linkId);
			MajorTrouble majorTrouble = majorTroubleService.getById(id);
			

			map.put("type", "zfws01");
			DataBean bean01 = httpService.getPhotoListByType(map);
			majorTrouble.setZfws01(StringTools.NullToStr(bean01.getRname(),""));//此处获取执法文书01附件
			map.put("type", "zfws02");
			DataBean bean02 = httpService.getPhotoListByType(map);
			majorTrouble.setZfws02(StringTools.NullToStr(bean02.getRname(),""));//此处获取执法文书02附件
			map.put("type", "zgfa");
			DataBean bean03 = httpService.getPhotoListByType(map);
			majorTrouble.setZgfa(StringTools.NullToStr(bean03.getRname(),""));//此处获取整改方案附件
			map.put("type", "jkcs");
			DataBean bean04 = httpService.getPhotoListByType(map);
			majorTrouble.setJkcs(StringTools.NullToStr(bean04.getRname(),""));//此处获取监控措施附件
			map.put("type", "zgqtp");
			DataBean bean05 = httpService.getPhotoListByType(map);
			majorTrouble.setZgqtp(StringTools.NullToStr(bean05.getRname(),""));//此处获取整改前图片
			map.put("type", "zghtp");
			DataBean bean06 = httpService.getPhotoListByType(map);
			majorTrouble.setZghtp(StringTools.NullToStr(bean06.getRname(),""));//此处获取整改后图片
			
			map.put("tbbm", majorTrouble.getTbbm());
			List<DataBean> depts = httpService.getDeptList(map);//根据填报部门代号获取名称
			if(depts!=null&&!depts.isEmpty()){
				majorTrouble.setTbbm(StringTools.NullToStr(depts.get(0).getRname(),""));
			}
			majorTrouble.setSzz(majorTrouble.getSzzname());
			JSONObject jon = JSONObject.fromObject(majorTrouble);
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
