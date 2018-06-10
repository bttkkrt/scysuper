package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.scxcgl.entity.ProductionManage;
import com.jshx.scxcgl.service.ProductionManageService;
import com.jshx.zdwxyyjcz.entity.ComDanEme;
import com.jshx.zdwxyyjcz.service.ComDanEmeService;
/**
 * 获取生产现场管理详情接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetScxcDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ProductionManageService productionManageService = (ProductionManageService) SpringContextHolder.getBean("productionManageService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		 try{
			 ProductionManage productionManage=productionManageService.getById(id);
				if(null!=productionManage){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(productionManage.getId(),""));//主键
					 String jobtype = "";
					if(productionManage.getJobType()!=null&&!"".equals(productionManage.getJobType()))
					{
						String[] types = productionManage.getJobType().replaceAll(" ", "").split(",");
						for(String ss:types)
						{
							Map m = new HashMap();
							m.put("codeName", "生产作业类型");
							m.put("itemValue", ss);
							jobtype += codeService.findCodeValueByMap(m).getItemText() + ",";
						}
						if(jobtype.length() != 0)
						{
							jobtype = jobtype.substring(0,jobtype.length()-1);
						}
					}
					json.put("jobType", jobtype);
					json.put("jobContent",StringTools.NullToStr(productionManage.getJobContent(),""));//作业内容
					json.put("analysis",StringTools.NullToStr(productionManage.getHazardAnalysiss(),""));//危害因素分析
					json.put("safeMeasures",StringTools.NullToStr(productionManage.getSafeMeasures(),""));//安全措施
					json.put("emerMeasure",StringTools.NullToStr(productionManage.getEmerMeasure(),""));//应急措施
					json.put("personInCharge",StringTools.NullToStr(productionManage.getPersonInCharge(),""));//项目负责人
					json.put("personName",StringTools.NullToStr(productionManage.getPersonName(),""));//施工负责人
					json.put("jobTime",null==productionManage.getJobTime()?"":new SimpleDateFormat("yyyy-MM-dd").format(productionManage.getJobTime())); 
					Map map = new HashMap();
					map.put("linkId",productionManage.getLinkId());
					map.put("mkType", "scxcgl1");
					map.put("picType","scxcglfj1");
					DataBean bean1 = httpService.getPhotoListByType(map);
					map.put("mkType", "scxcgl2");
					map.put("picType","scxcglfj2");
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
