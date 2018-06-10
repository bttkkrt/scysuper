package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscgzzd.entity.SecProSys;
import com.jshx.aqscgzzd.service.SecProSysService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.czgc.entity.OpePro;
import com.jshx.czgc.service.OpeProService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;

/**
 * 获取操作规程详情接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetCzgcDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private OpeProService opeProService = (OpeProService) SpringContextHolder.getBean("opeProService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 OpePro opePro=opeProService.getById(id);
				if(null!=opePro){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(opePro.getId(),""));//主键
					json.put("workName",StringTools.NullToStr(opePro.getOperationWorkshopName(),""));//车间名称
					json.put("postName",StringTools.NullToStr(opePro.getOperationPostname(),""));//岗位名称
					json.put("mostWorkTime",StringTools.NullToStr(opePro.getOperationMostWorktime(),""));//最大工作时间
					json.put("postCount",StringTools.NullToStr(opePro.getOperationPostCount(),""));//岗位员工数
					Map m = new HashMap();
					m.put("codeName", "是或否");
					m.put("itemValue", opePro.getOperationShiftsOrnot());
					String shiftsOrnot=codeService.findCodeValueByMap(m).getItemText();
					json.put("shiftsOrnot",shiftsOrnot);//是否倒班
					json.put("shiftsPersons",StringTools.NullToStr(opePro.getOperationShiftsPersons(),""));//倒班总人数
					json.put("draftPerson",StringTools.NullToStr(opePro.getOperationDraftPerson(),""));//起草人
					json.put("authorization",StringTools.NullToStr(opePro.getOperationAuthorization(),""));//批准人
					json.put("code",StringTools.NullToStr(opePro.getOperationCode(),""));//操作规程编号
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					json.put("yxsj", opePro.getEffectiveDate()==null?"":sdf.format(opePro.getEffectiveDate()));//有效时间
					Map map = new HashMap();
					map.put("linkId",opePro.getLinkId());
					map.put("mkType", "czgc");
					map.put("picType","czgcfj");
					DataBean bean = httpService.getPhotoListByType(map);
					json.put("filePath",null==bean.getRname()?"":bean.getRname());//附件url
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
