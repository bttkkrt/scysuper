package com.jshx.http.command;

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
import com.jshx.zybwhys.entity.OccDisInd;
import com.jshx.zybwhys.service.OccDisIndService;
import com.jshx.zybwhysfbqk.entity.OccDis;
import com.jshx.zybwhysfbqk.service.OccDisService;
import com.jshx.zywhjbysqk.entity.OccHazBas;
import com.jshx.zywhjbysqk.service.OccHazBasService;
import com.jshx.zywsfgfzr.service.OccChaInfService;
import com.jshx.zywsgljbxx.service.OccHeaInfoService;

/**
 * 职业危害基本因素情况
 * @author 费谦 2015-10-13
 */
public class GetZywhjbysqkDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private OccHazBasService occHazBasService = (OccHazBasService) SpringContextHolder.getBean("occHazBasService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private OccHeaInfoService occHeaInfoService = (OccHeaInfoService) SpringContextHolder.getBean("occHeaInfoService");
	private OccDisService occDisService = (OccDisService) SpringContextHolder.getBean("occDisService");
	private OccDisIndService occDisIndService = (OccDisIndService) SpringContextHolder.getBean("occDisIndService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String companyId = obj.getString("companyId");
		try{
			Map<String, Object> paraMap01=new HashMap<String, Object>();
			paraMap01.put("companyId", companyId);
			List occHazBasList=occHazBasService.findOccHazBas(paraMap01);
			if(occHazBasList.size()>0){
				OccHazBas occHazBas=occHazBasService.getById(((OccHazBas) occHazBasList.get(0)).getId());
				if(null!=occHazBas){
					bd.setCode("0");
					bd.setMessage("查询成功");
					JSONObject json = new JSONObject();
					json.put("id",occHazBas.getId());
					//粉尘类
					Map m = new HashMap();
					m.put("codeName", "有或无");
					m.put("itemValue", occHazBas.getIfDust());
					json.put("ifDust",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//粉尘类接触人数
					json.put("dustConNum", null==occHazBas.getDustContactNumber()?"":occHazBas.getDustContactNumber() );
					//化学物质类
					m.put("itemValue", occHazBas.getIfChemical());
					json.put("ifChemical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//化学物质类接触人数
					json.put("cheConNum", null==occHazBas.getChemicalContactNumber()?"":occHazBas.getChemicalContactNumber() );
					
					//物理因素类
					m.put("itemValue", occHazBas.getIfPhysical());
					json.put("ifPhysical",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//物理因素类接触人数
					json.put("phyConNum", null==occHazBas.getPhysicalContactNumber()?"":occHazBas.getPhysicalContactNumber() );
					//放射性物质类
					m.put("itemValue", occHazBas.getIfRadioactivity());
					json.put("ifRadio",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//放射性物质类接触人数
					json.put("radConNum", null==occHazBas.getRadioactivityContactNumber()?"":occHazBas.getRadioactivityContactNumber() );
					//其他
					m.put("itemValue", occHazBas.getIfOther());
					json.put("ifOther",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
					//其他接触人数
					json.put("othConNum", null==occHazBas.getOtherContactNumber()?"":occHazBas.getOtherContactNumber() );
					//接触职业病危害总人数
					int t=0;
					try {
						t=Integer.parseInt("".equals(json.getString("dustConNum").toString())?"0":json.getString("dustConNum").toString())+
						Integer.parseInt("".equals(json.getString("cheConNum").toString())?"0":json.getString("cheConNum").toString())+
						Integer.parseInt("".equals(json.getString("phyConNum").toString())?"0":json.getString("phyConNum").toString())+
						Integer.parseInt("".equals(json.getString("radConNum").toString())?"0":json.getString("radConNum").toString())+
						Integer.parseInt("".equals(json.getString("othConNum").toString())?"0":json.getString("othConNum").toString());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					json.put("totalNum", t );
					
					
					JSONArray jsonArray0 = new JSONArray();
					Map<String, Object> paraMap=new HashMap<String, Object>();
					paraMap.put("proId", occHazBas.getId());
					List<OccDis> occDisList=(List<OccDis>) occDisService.findOccDis(paraMap);
					for(OccDis od:occDisList){
						JSONObject j0 = new JSONObject();
						j0.put("workPlace", od.getWorkPlace());
						j0.put("contactNum", od.getContactNum());
						JSONArray jsonArray1 = new JSONArray();
						Map<String, Object> paraMap0=new HashMap<String, Object>();
						paraMap0.put("occDisId", od.getId());
						List<OccDisInd> occDisIndList=occDisIndService.findOccDisInd(paraMap0);
						for(OccDisInd odi:occDisIndList){
							JSONObject j1 = new JSONObject();
							j1.put("occDisName", odi.getOccupationalDiseaseName());
							j1.put("fieldConcent", odi.getFieldConcentration());
							j1.put("conNum", odi.getContactNumber());
							jsonArray1.add(j1);
						}
						j0.put("zybwhys", jsonArray1);
						jsonArray0.add(j0);
					}
					json.put("zybwhfbqk", jsonArray0);
					
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
				//粉尘类
				json.put("ifDust","" );
				//粉尘类接触人数
				json.put("dustConNum","");
				//化学物质类
				json.put("ifChemical","");
				//化学物质类接触人数
				json.put("cheConNum", "");
				//物理因素类
				json.put("ifPhysical","" );
				//物理因素类接触人数
				json.put("phyConNum","" );
				//放射性物质类
				json.put("ifRadio","");
				//放射性物质类接触人数
				json.put("radConNum","");
				//其他
				json.put("ifOther","");
				//其他接触人数
				json.put("othConNum", "");
				//接触职业病危害总人数
				json.put("totalNum", "" );
				
				JSONArray jsonArray0 = new JSONArray();
				json.put("zybwhfbqk", jsonArray0);
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