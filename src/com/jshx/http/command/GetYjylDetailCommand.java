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
import com.jshx.yjya.entity.EmePla;
import com.jshx.yjya.service.EmePlaService;
import com.jshx.yjyl.entity.EmeDri;
import com.jshx.yjyl.service.EmeDriService;

/**
 * 获取应急演练详情接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetYjylDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EmeDriService emeDriService = (EmeDriService) SpringContextHolder.getBean("emeDriService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 EmeDri emeDri=emeDriService.getById(id);
				if(null!=emeDri){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(emeDri.getId(),""));//主键
					json.put("drillName",StringTools.NullToStr(emeDri.getDrillName(),""));//演练名称
					Map m = new HashMap();
					    m.put("codeName", "应急演练类型");
						m.put("itemValue", emeDri.getDrillType());
						String type=codeService.findCodeValueByMap(m).getItemText();
					json.put("type",type);//演练类型
					json.put("address",StringTools.NullToStr(emeDri.getDrillAddress(),""));//演练地点
					Map m2 = new HashMap();
				    m2.put("codeName", "应急演练形式");
					m2.put("itemValue", emeDri.getDrillForm());
					String form=codeService.findCodeValueByMap(m2).getItemText();
					json.put("form",form);//演练形式
					json.put("content",StringTools.NullToStr(emeDri.getDrillContent(),""));//演练内容
					json.put("startTime",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(emeDri.getDrillStartTime()),""));//演练开始时间
					json.put("stopTime",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(emeDri.getDrillStopTime()),""));//演练结束时间
					json.put("organizer",StringTools.NullToStr(emeDri.getOrganizer(),""));//主办单位
					json.put("company",StringTools.NullToStr(emeDri.getDrillCompany(),""));//演练单位
					json.put("personNum",StringTools.NullToStr(emeDri.getDrillPersonNum(),""));//参演人数
					json.put("summary",StringTools.NullToStr(emeDri.getEvaluateSummary(),""));//评估总结
					Map map = new HashMap();
					map.put("linkId",emeDri.getLinkId());
					map.put("mkType", "yjyl");
					map.put("picType","yjylfj");
					DataBean bean = httpService.getPhotoListByType(map);
					json.put("filePath",null==bean.getRname()?"":bean.getRname());//附件url
					map.put("picType","yltp");
					DataBean bean2 = httpService.getPhotoListByType(map); //演练图片
					json.put("filePath2",null==bean2.getRname()?"":bean2.getRname());//
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
