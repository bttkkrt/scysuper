package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
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
import com.jshx.mxbz.entity.Signs;
import com.jshx.mxbz.service.SignsService;

/**
 * 获取明显标志详情接口
 * @author 周云琳 2015-10-12
 *
 */
public class GetMxbzDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SignsService signsService = (SignsService) SpringContextHolder.getBean("signsService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 Signs signs=signsService.getById(id);
				if(null!=signs){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(signs.getId(),""));//主键
					json.put("name",StringTools.NullToStr(signs.getSignsName(),""));//清单名称
					
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("picType", "aqbsfj");
					map.put("linkId", signs.getMapkey());
					map.put("mkType", "aqbs");
					DataBean bean1 = httpService.getPhotoListByType(map);
					String filepath1 = bean1.getRname();
					json.put("filePath",null==filepath1?"":filepath1 );
					
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
