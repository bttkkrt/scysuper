package com.jshx.http.command;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.qygjzzhzdbw.entity.KeyPar;
import com.jshx.qygjzzhzdbw.service.KeyParService;
import com.jshx.zdwxyczgc.entity.ComOpeRul;
import com.jshx.zdwxyczgc.service.ComOpeRulService;

/**
 * 获取企业关键装置和重点部位详情接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetQygjzzhzdbwDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private KeyParService keyParService = (KeyParService) SpringContextHolder.getBean("keyParService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		 try{
			 KeyPar keyPar=keyParService.getById(id);
				if(null!=keyPar){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(keyPar.getId(),""));//主键
					json.put("keyName",StringTools.NullToStr(keyPar.getKeyPartName(),""));//关键装置、重点部位名称
					json.put("majorRiskFactors",StringTools.NullToStr(keyPar.getMajorRiskFactors(),""));//主要危险因素
					json.put("resPerson",StringTools.NullToStr(keyPar.getResponsiblePerson(),""));//责任人
					json.put("postEmpNum",StringTools.NullToStr(keyPar.getPostEmployeeNumber(),""));//岗位员工数量
					Map map = new HashMap();
					map.put("linkId",keyPar.getLinkId());
					map.put("mkType", "qygjzzhzdbw1");
					map.put("picType","qygjzzhzdbwfj1");
					DataBean bean1 = httpService.getPhotoListByType(map);
					map.put("mkType", "qygjzzhzdbw2");
					map.put("picType","qygjzzhzdbwfj2");
					DataBean bean2 = httpService.getPhotoListByType(map);
					json.put("filePath1",null==bean1.getRname()?"":bean1.getRname());
					json.put("filePath2",null==bean2.getRname()?"":bean2.getRname());
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
