package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.gpdb.entity.Supervice;
import com.jshx.gpdb.service.SuperviceService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.yhb.service.TroManService;

/**
 * 挂牌督办详情
 * @author 费谦 2015-10-13
 */
public class GetGpdbDetailCommand implements Command
{
	private HttpService httpService = (HttpService) SpringContextHolder.getBean("httpService");
	private SuperviceService superviceService = (SuperviceService) SpringContextHolder.getBean("superviceService");
	private TroManService troManService = (TroManService) SpringContextHolder.getBean("troManService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject obj) {
		SummaryBean bd = new  SummaryBean();
		String userId = obj.getString("userId");
		String id = obj.getString("id");
		try{
			Supervice supervice=superviceService.getById(id);
			if(null!=supervice){
				bd.setCode("0");
				bd.setMessage("查询成功");
				JSONObject json = new JSONObject();
				json.put("id",id);
				json.put("companyName",null==supervice.getCompanyName()?"":supervice.getCompanyName()  );
				json.put("dangerName",null==supervice.getDangerName()?"":supervice.getDangerName()  );
				json.put("content",null==supervice.getDangerContent()?"":supervice.getDangerContent()  );
				json.put("listingTime", null==supervice.getListingTime()?"":sdf.format(supervice.getListingTime()) );
				json.put("responsible",null==supervice.getResponsible()?"":supervice.getResponsible()  );
				json.put("mobile",null==supervice.getResponsibleMobile()?"":supervice.getResponsibleMobile()  );
				json.put("unit",null==supervice.getResponsibleUnit()?"":supervice.getResponsibleUnit()  );
				json.put("address",null==supervice.getAddress()?"":supervice.getAddress()  );
				json.put("term", null==supervice.getRectificationTerm()?"":sdf.format(supervice.getRectificationTerm()) );
				json.put("recfinishTime", null==supervice.getRecfinishTime()?"":sdf.format(supervice.getRecfinishTime()) );
				json.put("acceptTime", null==supervice.getAcceptTime()?"":sdf.format(supervice.getAcceptTime()) );
				json.put("rectificationMoney",null==supervice.getRectificationMoney()?"":supervice.getRectificationMoney()  );
				json.put("unit",null==supervice.getResponsibleUnit()?"":supervice.getResponsibleUnit()  );
				json.put("danrecNum",null==supervice.getDanrecNum()?"":supervice.getDanrecNum()  );
				json.put("state",null==supervice.getRectificationState()?"":supervice.getRectificationState()  );
				
				
				//企业属地
				Map m = new HashMap();
				m.put("codeName", "企业属地");
				m.put("itemValue", supervice.getAreaId());
				json.put("areaName",null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText() );
				//隐患类别
				m.put("codeName", "隐患类别");
				m.put("itemValue", supervice.getDangerSort());
				json.put("sort", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				//隐患级别
				m.put("codeName", "隐患级别");
				m.put("itemValue", supervice.getDangerLevel());
				json.put("level", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				//挂牌督办整改级别
				m.put("codeName", "挂牌督办整改级别");
				m.put("itemValue", supervice.getRectificationLevel());
				json.put("rectificationLevel", null==codeService.findCodeValueByMap(m).getItemText()?"":codeService.findCodeValueByMap(m).getItemText());
				
				//附件 整改前
				Map<String,Object> map1=new HashMap<String, Object>();
				map1.put("picType", "jcwsfj");
				map1.put("linkId", supervice.getLinkId());
				map1.put("mkType", "gpdb");
				DataBean bean1 = httpService.getPhotoListByType(map1);
				String filepath1 = bean1.getRname();
				json.put("filePath1",null==filepath1?"":filepath1 );
				
				Map<String,Object> map2=new HashMap<String, Object>();
				map2.put("picType", "zgqtpfj");
				map2.put("linkId", supervice.getLinkId());
				map2.put("mkType", "gpdb");
				DataBean bean2 = httpService.getPhotoListByType(map2);
				String filepath2 = bean2.getRname();
				json.put("filePath2",null==filepath2?"":filepath2 );
				
				Map<String,Object> map3=new HashMap<String, Object>();
				map3.put("picType", "zgfafj");
				map3.put("linkId", supervice.getLinkId());
				map3.put("mkType", "gpdb");
				DataBean bean3 = httpService.getPhotoListByType(map3);
				String filepath3 = bean3.getRname();
				json.put("filePath3",null==filepath3?"":filepath3 );
				
				Map<String,Object> map4=new HashMap<String, Object>();
				map4.put("picType", "ffcsfj");
				map4.put("linkId", supervice.getLinkId());
				map4.put("mkType", "gpdb");
				DataBean bean4 = httpService.getPhotoListByType(map4);
				String filepath4 = bean4.getRname();
				json.put("filePath4",null==filepath4?"":filepath4 );
				
				//最后一次整改记录的linkid
				Map map = new HashMap();
				map.put("yhbId", supervice.getId());
				List<HashMap> rectInfos = troManService.queryRectInfosByMap(map);
				if(rectInfos.size()>0){
					String linkId=rectInfos.get(rectInfos.size()-1).get("linkId").toString();
					Map<String,Object> map5=new HashMap<String, Object>();
					map5.put("picType", "fcwsfj");
					map5.put("linkId", linkId);
					map5.put("mkType", "gpdb");
					DataBean bean5 = httpService.getPhotoListByType(map5);
					String filepath5 = bean5.getRname();
					json.put("filePath5",null==filepath5?"":filepath5 );
					
					Map<String,Object> map6=new HashMap<String, Object>();
					map6.put("picType", "zghtpfj");
					map6.put("linkId", linkId);
					map6.put("mkType", "gpdb");
					DataBean bean6 = httpService.getPhotoListByType(map6);
					String filepath6 = bean6.getRname();
					json.put("filePath6",null==filepath6?"":filepath6 );
				}else{
					Map<String,Object> map5=new HashMap<String, Object>();
					map5.put("picType", "fcwsfj");
					map5.put("linkId", supervice.getLinkId());
					map5.put("mkType", "gpdb");
					DataBean bean5 = httpService.getPhotoListByType(map5);
					String filepath5 = bean5.getRname();
					json.put("filePath5",null==filepath5?"":filepath5 );
					
					Map<String,Object> map6=new HashMap<String, Object>();
					map6.put("picType", "zghtpfj");
					map6.put("linkId", supervice.getLinkId());
					map6.put("mkType", "gpdb");
					DataBean bean6 = httpService.getPhotoListByType(map6);
					String filepath6 = bean6.getRname();
					json.put("filePath6",null==filepath6?"":filepath6 );
				}
				
				
				//审核记录
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("infoId", supervice.getId());
				List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
				String recordStr = StringTools.checkRecordToStr(checkRecords);
				json.put("auditRecord",recordStr );
				
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