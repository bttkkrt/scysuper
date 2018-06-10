package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.zbhjxx.entity.EnvInf;
import com.jshx.zbhjxx.service.EnvInfService;

/**
 * 周边环境信息详情
 * @author 费谦 2015-10-13
 */
public class GetZbhjxxDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private EnvInfService envInfService = (EnvInfService) SpringContextHolder.getBean("envInfService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			EnvInf envInf=envInfService.getById(id);
			if(null!=envInf){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				//周边环境类型
				Map m = new HashMap();
				m.put("codeName", "周边环境类型");
				m.put("itemValue", envInf.getSurroundingEnvironmentType());
				json.put("surEnvType",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );

				json.put("surEnvName",null==envInf.getSurroundingEnvironmentName()?"":envInf.getSurroundingEnvironmentName());
				//周边环境方位
				m.put("codeName", "周边环境方位");
				m.put("itemValue", envInf.getSurroundingEnvironment());
				json.put("surEnvAdd",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				json.put("minDistance",null==envInf.getMinimumDistance()?"":envInf.getMinimumDistance()  );
				//建筑结构
				m.put("codeName", "建筑结构");
				m.put("itemValue", envInf.getBuildingStructure());
				json.put("buildStructure",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				
				json.put("buildHeight",null==envInf.getBuildingHeight()?"":envInf.getBuildingHeight()  );
				//周围环境人员类型
				m.put("codeName", "周围环境人员类型");
				m.put("itemValue", envInf.getDangerousChemicalName());
				json.put("perType",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				
				json.put("perNum",null==envInf.getPersonnelType()?"":envInf.getPersonnelType() );
				json.put("contPerson",null==envInf.getContactPerson()?"":envInf.getContactPerson()  );
				json.put("tele", null==envInf.getTelephone()?"":envInf.getTelephone()  );
				json.put("mobile",null==envInf.getMobile()?"":envInf.getMobile()   );
				json.put("email",null==envInf.getEmail()?"":envInf.getEmail()  );
				json.put("longitude",null==envInf.getLongitude()?"":envInf.getLongitude()   );
				json.put("latitude",null==envInf.getLatitude()?"":envInf.getLatitude()   );
				json.put("remark",null==envInf.getRemark()?"":envInf.getRemark()   );
				
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("picType", "xgzp");
				map.put("linkId", envInf.getLinkId());
				map.put("mkType", "zbhjxx");
				DataBean bean = httpService.getPhotoListByType(map);
				String filepath = bean.getRname();
				json.put("filePath",null==filepath?"":filepath );
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