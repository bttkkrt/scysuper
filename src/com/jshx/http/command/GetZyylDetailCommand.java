package com.jshx.http.command;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.tjbhgjl.entity.PhyUnqRec;
import com.jshx.tjbhgjl.service.PhyUnqRecService;
import com.jshx.zyyl.entity.MaiMat;
import com.jshx.zyyl.service.MaiMatService;
/**
 * 获取主要原料详情接口
 * @author 周云琳 2015-10-15
 *
 */
public class GetZyylDetailCommand implements Command{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private MaiMatService maiMatService = (MaiMatService) SpringContextHolder.getBean("maiMatService");
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//获取编号
	
		
		 try{
			 MaiMat maiMat=maiMatService.getById(id);
				if(null!=maiMat){
					br.setCode("0");
					br.setMessage("成功");
					JSONObject json = new JSONObject();
					json.put("id",StringTools.NullToStr(maiMat.getId(),""));//主键
					json.put("material",StringTools.NullToStr(maiMat.getMaterial(),""));//物料
					json.put("riskAnalysisi",StringTools.NullToStr(maiMat.getRiskAnalysis(),""));//危险性分析
					json.put("stoMode",StringTools.NullToStr(maiMat.getStorageMode(),""));//存放方式
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
