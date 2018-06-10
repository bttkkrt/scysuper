package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.lbypgl.entity.PpeManag;
import com.jshx.lbypgl.service.PpeManagService;
import com.jshx.module.admin.service.CodeService;

/**
 * 劳保用品详情
 * @author 费谦 2015-10-13
 */
public class GetLbypDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private PpeManagService ppeManagService = (PpeManagService) SpringContextHolder.getBean("ppeManagService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			PpeManag ppeManag=ppeManagService.getById(id);
			if(null!=ppeManag){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("no",null==ppeManag.getPpeNo()?"":ppeManag.getPpeNo()  );
				json.put("name",null==ppeManag.getPpeName()?"":ppeManag.getPpeName());
				//劳保用品分类
				Map m = new HashMap();
				m.put("codeName", "劳保用品分类");
				m.put("itemValue",ppeManag.getPpeType());
				json.put("type", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				json.put("company", null==ppeManag.getPpeCompany()?"":ppeManag.getPpeCompany() );
				json.put("num", null==ppeManag.getPpeNum()?"":ppeManag.getPpeNum() );
				json.put("remark", null==ppeManag.getPpeRemark()?"":ppeManag.getPpeRemark() );
				bd.setContent(json.toString());
				
			}else{
				bd.setCode("1");
				bd.setMessage("无数据");
			}
		}catch(Exception e){
			bd.setCode("2");
			bd.setMessage("异常");
			e.printStackTrace();
		}
		System.out.println(bd.toString());
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}