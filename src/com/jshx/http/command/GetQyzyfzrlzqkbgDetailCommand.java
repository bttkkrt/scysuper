package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscbxtbqk.entity.SecProIns;
import com.jshx.aqscbxtbqk.service.SecProInsService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.qyzyfzrlzqk.entity.MaiPerRep;
import com.jshx.qyzyfzrlzqk.service.MaiPerRepService;

/**
 * 获取企业主要负责人履职情况报告详情接口
 * @author 周云琳 2015-10-13
 *
 */
public class GetQyzyfzrlzqkbgDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private MaiPerRepService maiPerRepService = (MaiPerRepService) SpringContextHolder.getBean("maiPerRepService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 MaiPerRep maiPerRep=maiPerRepService.getById(id);
				if(null!=maiPerRep){
					
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(maiPerRep.getId(),""));//主键
					json.put("priResPerson",StringTools.NullToStr(maiPerRep.getPrincipalResponsiblePerson(),""));//主要负责人
					json.put("perReport",StringTools.NullToStr(maiPerRep.getPerformanceReport(),""));//履职报告
					
					Map m = new HashMap();
					m.put("codeName", "是或否");
					m.put("itemValue", maiPerRep.getIfHazardousProcess());
					String ifHazProcess=codeService.findCodeValueByMap(m).getItemText();
					json.put("ifHazProcess",ifHazProcess);//是否危险工艺
					Map m2 = new HashMap();
					m2.put("codeName", "是或否");
					m2.put("itemValue", maiPerRep.getIfHazardSources());
					String ifHazSource=codeService.findCodeValueByMap(m2).getItemText();
					json.put("ifHazSource",ifHazSource);//是否构成重大危险源
					Map map = new HashMap();
					map.put("linkId",maiPerRep.getLinkId());
					map.put("mkType", "qyzyfzrlzqk");
					map.put("picType","qyzyfzrlzqkfj");
					DataBean bean = httpService.getPhotoListByType(map);
					json.put("filePath",null==bean.getRname()?"":bean.getRname());//附件url
					br.setContent(json.toString());
					br.setCode("0");
					br.setMessage("成功");
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
