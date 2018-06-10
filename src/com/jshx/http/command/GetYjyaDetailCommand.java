package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.ggl.entity.PublicBoard;
import com.jshx.ggl.service.PublicBoardService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.yjya.entity.EmePla;
import com.jshx.yjya.service.EmePlaService;

/**
 * 获取应急预案详情接口
 * @author 周云琳 2015-10-16
 *
 */
public class GetYjyaDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EmePlaService emePlaService = (EmePlaService) SpringContextHolder.getBean("emePlaService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 EmePla emePla=emePlaService.getById(id);
				if(null!=emePla){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(emePla.getId(),""));//主键
					json.put("planName",StringTools.NullToStr(emePla.getPlanName(),""));//预案名称
					
					Map m = new HashMap();
					m.put("codeName", "应急预案类别");
					m.put("itemValue", emePla.getPlanType());
					String planType=codeService.findCodeValueByMap(m).getItemText();
					json.put("planType",planType);//预案类别
					Map m2 = new HashMap();
					m2.put("codeName", "应急预案级别");
					m2.put("itemValue", emePla.getPlanType());
					String planLevel=codeService.findCodeValueByMap(m2).getItemText();
					
					json.put("planLevel",planLevel);//预案级别
					json.put("field",StringTools.NullToStr(emePla.getFiled(),""));//试用领域
					json.put("planSummary",StringTools.NullToStr(emePla.getPlanSummary(),""));//预案摘要
					json.put("drawInstitution",StringTools.NullToStr(emePla.getDrawUpInstitution(),""));//编制单位
					json.put("drawPerson",StringTools.NullToStr(emePla.getDrawUpPerson(),""));//编制人
					json.put("publishDate",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(emePla.getPublishDate()),""));//发布日期
					json.put("publishNumber",StringTools.NullToStr(emePla.getPublishNumber(),""));//发布文号
					json.put("publishInstitution",StringTools.NullToStr(emePla.getPublishInstitution(),""));//发布单位
					json.put("publisher",StringTools.NullToStr(emePla.getPublisher(),""));//签发人
					json.put("planFileTime",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(emePla.getPlanFilingTime()),""));//预案备案时间
					json.put("planFileDepart",StringTools.NullToStr(emePla.getPlanFilingDepart(),""));//预案备案部门
					json.put("planFileNum",StringTools.NullToStr(emePla.getPlanFilingNumber(),""));//预案备案编号
					json.put("remark",StringTools.NullToStr(emePla.getRemark(),""));//备注
					Map map = new HashMap();
					map.put("linkId",emePla.getLinkId());
					map.put("mkType", "yjya");
					map.put("picType","yjyafj");
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
