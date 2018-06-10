package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.aqscgzzd.entity.SecProSys;
import com.jshx.aqscgzzd.service.SecProSysService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.tjbhgjl.entity.PhyUnqRec;
import com.jshx.tjbhgjl.service.PhyUnqRecService;
/**
 * 获取安全生产规章制度详情接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetAqscgzzdDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SecProSysService secProSysService = (SecProSysService) SpringContextHolder.getBean("secProSysService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 SecProSys secProSys=secProSysService.getById(id);
				if(null!=secProSys){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(secProSys.getId(),""));//主键
					json.put("systemName",StringTools.NullToStr(secProSys.getSystemName(),""));//制度名称
					String zdlx = "";
					String[] zdlxs =  secProSys.getSystemType().split(",");
					Map m = new HashMap();
					m.put("codeName", "安全生产规章制度类型");
					for(String s:zdlxs)
					{
						m.put("itemValue", s);
						String systemType=codeService.findCodeValueByMap(m).getItemText();
						if(systemType != null && !"".equals(systemType))
						{
							zdlx += systemType + ",";
						}
					}
					if(zdlx.length() != 0)
					{
						zdlx = zdlx.substring(0,zdlx.length()-1);
					}
					
					json.put("systemType",zdlx);//制度类型
					json.put("systemTime",null==secProSys.getSystemTime()?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(secProSys.getSystemTime()));//上传时间
					Map map = new HashMap();
					map.put("linkId",secProSys.getLinkId());
					map.put("mkType", "aqscgzzd");
					map.put("picType","aqscgzzdfj");
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
