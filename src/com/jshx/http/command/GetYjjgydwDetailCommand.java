package com.jshx.http.command;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.fczycsgl.entity.DusWorMan;
import com.jshx.fczycsgl.service.DusWorManService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.yjjgydw.entity.EmeAge;
import com.jshx.yjjgydw.service.EmeAgeService;

/**
 * 获取应急机构与队伍详情接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetYjjgydwDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EmeAgeService emeAgeService = (EmeAgeService) SpringContextHolder.getBean("emeAgeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 EmeAge emeAge=emeAgeService.getById(id);
				if(null!=emeAge){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(emeAge.getId().toString(),""));//主键
					json.put("name",StringTools.NullToStr(emeAge.getAgencyName().toString(),""));//机构名称
					json.put("response",StringTools.NullToStr(emeAge.getAgencyResponsible().toString(),""));//机构职责
					json.put("num",StringTools.NullToStr(emeAge.getMemberNumber().toString(),""));//成员数量
					json.put("charge",StringTools.NullToStr(emeAge.getPersonInCharge().toString(),""));//负责人
					json.put("phone",StringTools.NullToStr(emeAge.getInChargePhone().toString(),""));//负责人联系方式
					json.put("email",StringTools.NullToStr(emeAge.getInChargeEmail().toString(),""));//负责人邮箱
					json.put("remark",StringTools.NullToStr(emeAge.getRemark().toString(),""));//备注
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
