package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.dgjl.entity.ProRec;
import com.jshx.dgjl.service.ProRecService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;


/**
 * 获取调岗记录详情接口
 * @author 周云琳 2015-10-14
 *
 */
public class GetTgjlDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private ProRecService proRecService = (ProRecService) SpringContextHolder.getBean("proRecService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 ProRec proRec=proRecService.getById(id);
				if(null!=proRec){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(proRec.getId(),""));//主键
					json.put("time",StringTools.NullToStr(new SimpleDateFormat("yyyy-MM-dd").format(proRec.getJshxTime()),""));//调岗时间
					json.put("cause",StringTools.NullToStr(proRec.getCausing(),""));//调岗事由
					json.put("remark",StringTools.NullToStr(proRec.getRemark(),""));//备注
					Map map = new HashMap();
					map.put("linkId",proRec.getLinkId());
					map.put("mkType", "dgjl");
					map.put("picType","dgjlfj");
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
