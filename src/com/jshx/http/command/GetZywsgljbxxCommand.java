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
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.zywsfgfzr.entity.OccChaInf;
import com.jshx.zywsfgfzr.service.OccChaInfService;
import com.jshx.zywsgljbxx.entity.OccHeaInfo;
import com.jshx.zywsgljbxx.service.OccHeaInfoService;

/**
 * 职业卫生管理基本信息
 * @author 费谦 2015-10-13
 */
public class GetZywsgljbxxCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private OccHeaInfoService occHeaInfoService = (OccHeaInfoService) SpringContextHolder.getBean("occHeaInfoService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private OccChaInfService occChaInfService = (OccChaInfService) SpringContextHolder.getBean("occChaInfService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String companyId = obj.getString("companyId");
		try{
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("companyId", companyId);
			List occHeaInfoList=occHeaInfoService.findOccHeaInfo(paraMap);
			if(occHeaInfoList.size()>0){
				OccHeaInfo occHeaInfo=occHeaInfoService.getById(((OccHeaInfo) occHeaInfoList.get(0)).getId());
				if(null!=occHeaInfo){
					bd.setCode("0");
					bd.setMessage("查询成功");
					JSONObject json = new JSONObject();
					json.put("id",occHeaInfo.getId());
					//女职工人数
					json.put("femaleNum", null==occHeaInfo.getFemaleWorkersNumber()?"":occHeaInfo.getFemaleWorkersNumber());
					//接触职业病危害因素人数
					json.put("accDisNum", null==occHeaInfo.getOccupationalDiseasersNumber()?"":occHeaInfo.getOccupationalDiseasersNumber());
					//职业病危害行业类别
					Map m = new HashMap();
					m.put("codeName", "职业病危害行业类别");
					m.put("itemValue", occHeaInfo.getHazardIndustryCategory());
					json.put("hazardIndusCate",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//
					m.put("codeName", "企业职业病危害类别");
					m.put("itemValue", occHeaInfo.getCompanyHazardCategory());
					json.put("companyHazardCategory",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					
					
					//接触职业病危害因素女工人数
					json.put("femaleDisNum", null==occHeaInfo.getFemaleWorkersDiseasesNumber()?"":occHeaInfo.getFemaleWorkersDiseasesNumber() );
					//职业病危害岗位数
					json.put("occDisPosts", null==occHeaInfo.getOccupationDiseasePosts()?"":occHeaInfo.getOccupationDiseasePosts() );
					
					JSONArray jsonArray0 = new JSONArray();
					JSONObject j0 = new JSONObject();
					//职业卫生分管负责人 姓名
					j0.put("name", null==occHeaInfo.getZywsfgfzrName()?"":occHeaInfo.getZywsfgfzrName() );
					//职业卫生分管负责人 职务
					j0.put("duty", null==occHeaInfo.getZywsfgfzrDuty()?"":occHeaInfo.getZywsfgfzrDuty() );
					//职业卫生分管负责人 办公电话
					j0.put("tele", null==occHeaInfo.getZywsfgfzrTelephone()?"":occHeaInfo.getZywsfgfzrTelephone() );
					//职业卫生分管负责人 手机
					j0.put("mobile", null==occHeaInfo.getZywsfgfzrMobile()?"":occHeaInfo.getZywsfgfzrMobile());
					//职业卫生分管负责人 文化程度
					m.put("codeName", "学历");
					m.put("itemValue", occHeaInfo.getZywsfgfzrEducation());
					j0.put("degree",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//职业卫生分管负责人 专业
					j0.put("professional", null==occHeaInfo.getZywsfgfzrProfession()?"":occHeaInfo.getZywsfgfzrProfession());
					//职业卫生分管负责人 培训日期
					String start=null==occHeaInfo.getZywsfgfzrTrainingDateStart()?"":sdf.format(occHeaInfo.getZywsfgfzrTrainingDateStart());
					String end=null==occHeaInfo.getZywsfgfzrTrainingDateEnd()?"":sdf.format(occHeaInfo.getZywsfgfzrTrainingDateEnd());
					j0.put("trainDate", start+"-"+end);
					//职业卫生分管负责人 培训合格证号
					j0.put("trainCerNum", null==occHeaInfo.getZywsfgfzrTrainingNo()?"":occHeaInfo.getZywsfgfzrTrainingNo());
					jsonArray0.add(j0);
					json.put("zywsfgfzr", jsonArray0);
					
					JSONArray jsonArray1 = new JSONArray();
					JSONObject j1 = new JSONObject();
					//职业卫生管理机构负责人  姓名
					j1.put("name", null==occHeaInfo.getZywsgljgfzrName()?"":occHeaInfo.getZywsgljgfzrName() );
					//职业卫生管理机构负责人  职务
					j1.put("duty", null==occHeaInfo.getZywsgljgfzrDuty()?"":occHeaInfo.getZywsgljgfzrDuty() );
					//职业卫生管理机构负责人  办公电话
					j1.put("tele", null==occHeaInfo.getZywsgljgfzrTelephone()?"":occHeaInfo.getZywsgljgfzrTelephone() );
					//职业卫生管理机构负责人  手机
					j1.put("mobile", null==occHeaInfo.getZywsgljgfzrMobile()?"":occHeaInfo.getZywsgljgfzrMobile());
					//职业卫生管理机构负责人  文化程度
					m.put("codeName", "学历");
					m.put("itemValue", occHeaInfo.getZywsgljgfzrEducation());
					j1.put("degree",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//职业卫生管理机构负责人  专业
					j1.put("professional", null==occHeaInfo.getZywsgljgfzrProfession()?"":occHeaInfo.getZywsgljgfzrProfession());
					//职业卫生管理机构负责人  培训日期
					String start1=null==occHeaInfo.getZywsgljgfzrTrainingDateStart()?"":sdf.format(occHeaInfo.getZywsgljgfzrTrainingDateStart());
					String end1=null==occHeaInfo.getZywsgljgfzrTrainingDateEnd()?"":sdf.format(occHeaInfo.getZywsgljgfzrTrainingDateEnd());
					j1.put("trainDate", start1+"-"+end1);
					//职业卫生管理机构负责人  培训合格证号
					j1.put("trainCerNum", null==occHeaInfo.getZywsgljgfzrTrainingNo()?"":occHeaInfo.getZywsgljgfzrTrainingNo());
					jsonArray1.add(j1);
					json.put("zywsgljgfzr", jsonArray1);
					
					Map<String, Object> paraMap1 = new HashMap<String, Object>();
					paraMap1.put("occHeaInfoId", occHeaInfo.getId());
					List<OccChaInf> occChaInfs=occChaInfService.findOccChaInf(paraMap1);
					
					JSONArray jsonArray = new JSONArray();
					for(OccChaInf oci:occChaInfs){
						JSONObject j = new JSONObject();
						j.put("name", null==oci.getJshxName()?"":oci.getJshxName());
						String start2=null==oci.getTrainingDateStart()?"":sdf.format(oci.getTrainingDateStart());
						String end2=null==oci.getTrainingDateEnd()?"":sdf.format(oci.getTrainingDateEnd());
						j.put("trainDate", start2+"-"+end2);
						j.put("trainCerNum", null==oci.getTrainingCertificatNumber()?"":oci.getTrainingCertificatNumber());
						j.put("duty", oci.getDuty());
						j.put("tele", oci.getTelephone());
						j.put("mobile", oci.getMobile());
						m.put("codeName", "学历");
						m.put("itemValue", oci.getDegreeEducation());
						j.put("degree",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
						j.put("professional", oci.getProfessional());
						jsonArray.add(j);
					}
					
					json.put("zywsglry", jsonArray);
					
					bd.setContent(json.toString());
					
				}else{
					bd.setCode("1");
					bd.setMessage("无数据");
				}
			}else{
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id","");
				//女职工人数
				json.put("femaleNum", "");
				//接触职业病危害因素人数
				json.put("accDisNum", "");
				//职业病危害行业类别
				json.put("hazardIndusCate","");
				//企业职业病危害类别
				json.put("companyHazardCategory","");
				//接触职业病危害因素女工人数
				json.put("femaleDisNum","");
				//职业病危害岗位数
				json.put("occDisPosts","" );
				
				JSONArray jsonArray0 = new JSONArray();
				json.put("zywsfgfzr", jsonArray0);
				
				JSONArray jsonArray1 = new JSONArray();
				json.put("zywsgljgfzr", jsonArray1);
				JSONArray jsonArray = new JSONArray();
				json.put("zywsglry", jsonArray);
				bd.setContent(json.toString());
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