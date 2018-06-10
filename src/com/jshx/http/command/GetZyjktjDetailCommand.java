package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.zyjktj.entity.OccHeaExa;
import com.jshx.zyjktj.entity.OccHeaExaList;
import com.jshx.zyjktj.service.OccHeaExaService;

/**
 * 职业健康体检详情
 * @author 费谦 2015-10-13
 */
public class GetZyjktjDetailCommand implements Command
{
//	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private OccHeaExaService occHeaExaService = (OccHeaExaService) SpringContextHolder.getBean("occHeaExaService");
//	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			OccHeaExa occHeaExa=occHeaExaService.getById(id);
			if(null!=occHeaExa){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("medInstitute",null==occHeaExa.getMedicalInstitutionName()?"":occHeaExa.getMedicalInstitutionName()  );
				json.put("createTime",sdf.format(occHeaExa.getCreateTime()));
				Map<String, Object> paraMap1 = new HashMap<String, Object>();
				paraMap1.put("occHeaExaId", occHeaExa.getId());
				List<OccHeaExaList>  occHeaExaLists=occHeaExaService.findOccHeaExaLists(paraMap1);
				JSONArray jsonArray = new JSONArray();
				for(OccHeaExaList ohel:occHeaExaLists){
					JSONObject j = new JSONObject();
					j.put("occDisHazName", ohel.getOccupationalDiseasName());
					j.put("preOccHeaNum", ohel.getPreOccupationHealthNumber());
					j.put("postOccHea", ohel.getPostOccupationalHealth());
					j.put("postOccHeaNum", ohel.getPostOccupationHealthNumber());
					j.put("foundPostNum", ohel.getFoundPostsNumber());
					j.put("actPostNum", ohel.getActualPositionNumber());
					jsonArray.add(j);
				}
				json.put("list", jsonArray);
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