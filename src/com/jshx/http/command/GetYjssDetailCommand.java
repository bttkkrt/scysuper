package com.jshx.http.command;

import java.text.SimpleDateFormat;
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
import com.jshx.module.admin.service.CodeService;
import com.jshx.yjjgydw.entity.EmeAge;
import com.jshx.yjjgydw.service.EmeAgeService;
import com.jshx.yjsszbwz.entity.EmeFac;
import com.jshx.yjsszbwz.service.EmeFacService;

/**
 * 获取应急设施、装备、物资详情接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetYjssDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EmeFacService emeFacService = (EmeFacService) SpringContextHolder.getBean("emeFacService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 EmeFac emeFac=emeFacService.getById(id);
				if(null!=emeFac){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(emeFac.getId().toString(),""));//主键
					json.put("facilityName",StringTools.NullToStr(emeFac.getFacilityName().toString(),""));//物资名称
					Map m = new HashMap();
					m.put("codeName", "应急物资级别");
					m.put("itemValue", emeFac.getFacilityLevel());
					String level=codeService.findCodeValueByMap(m).getItemText();
					json.put("level",level);//物资级别
					json.put("number",StringTools.NullToStr(emeFac.getFacilityNumber().toString(),""));//物资数量
					json.put("model",StringTools.NullToStr(emeFac.getFacilityModel().toString(),""));//物资型号
					json.put("specific",StringTools.NullToStr(emeFac.getFacilitySpecific().toString(),""));//物资规格
					json.put("purchaseDate",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(emeFac.getPurchaseDate()),""));//购入日期
					json.put("vender",StringTools.NullToStr(emeFac.getVender().toString(),""));//生产厂家
					json.put("produceTime",StringTools.NullToStr(emeFac.getProduceTime().toString(),""));//出厂日期
					json.put("expiryDate",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(emeFac.getExpiryDate()),""));//有效期至
					json.put("purpose",StringTools.NullToStr(emeFac.getPurposeDescrip().toString(),""));//用途说明
					json.put("performance",StringTools.NullToStr(emeFac.getPerformanceDescrip().toString(),""));//性能说明
					json.put("location",StringTools.NullToStr(emeFac.getStorageLocation().toString(),""));//存放地点
					json.put("keeper",StringTools.NullToStr(emeFac.getKeeper().toString(),""));//负责保管人
					json.put("phone",StringTools.NullToStr(emeFac.getKeeperPhone().toString(),""));//保管人联系方式
					json.put("remark",StringTools.NullToStr(emeFac.getRemark().toString(),""));//备注
					json.put("longitude",StringTools.NullToStr(emeFac.getLongitude().toString(),""));//经度
					json.put("latitude",StringTools.NullToStr(emeFac.getLatitude().toString(),""));//纬度
					Map map = new HashMap();
					map.put("linkId",emeFac.getLinkId());
					map.put("mkType", "yjsszbwz");
					map.put("picType","yjsszbwzfj");
					DataBean bean = httpService.getPhotoListByType(map);
					String filepath = bean.getRname();
					json.put("filePath",StringTools.NullToStr(filepath,""));//附件url
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
