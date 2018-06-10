package com.webService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.DataBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qywghjdgl.entity.ComGriMan;
import com.jshx.qywghjdgl.service.ComGriManService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.yhb.service.TroManService;
import com.jshx.zzqk.entity.IntSit;
import com.jshx.zzqk.service.IntSitService;

public class ZhajInfo {
	private EntBaseInfoService entBaseInfoService = SpringContextHolder.getBean("entBaseInfoService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	private TroManService troManService = SpringContextHolder.getBean("troManService");
	private ComGriManService comGriManService= (ComGriManService) SpringContextHolder.getBean("comGriManService");
	private HttpService httpService= (HttpService) SpringContextHolder.getBean("httpService");
	private CheckRecordService checkRecordService= (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private PhotoPicService photoPicService= (PhotoPicService) SpringContextHolder.getBean("photoPicService");
	private IntSitService intSitService= (IntSitService) SpringContextHolder.getBean("intSitService");
	private UserService userService= (UserService) SpringContextHolder.getBean("userService");
	
	
	private Pagination pagination;
	public String getCompanyInfo(String recvBody) throws UnsupportedEncodingException{
		System.out.println(AESEncrypt.decode(AESEncrypt.decode(recvBody)));
		JSONObject jsonJ = JSONObject.fromObject(AESEncrypt.decode(AESEncrypt.decode(recvBody)));
		String actionName=jsonJ.getString("actionName");
		String companyName = jsonJ.getString("companyName");
		String passWord="";
		String loginId="";
		Map map=new HashMap();
		map.put("enterpriseName", companyName);
		EntBaseInfo ent=entBaseInfoService.findEntBaseInfoByMap(map);
		if(null==ent.getId()){
			JSONObject jsonErr=new JSONObject();
			jsonErr.put("code", "1");
			return jsonErr.toString();
		}else{
			loginId=ent.getLoginId();
			passWord=userService.findUserByLoginId(loginId).getPassword();
		}
		
		String result="";
		if("baseInfo".equals(actionName)){
			JSONObject json=new JSONObject();
			try {
				json.put("companyName", ent.getEnterpriseName());//企业名称
				json.put("enterpriseAddress", ent.getEnterpriseAddress());//注册地址
				json.put("registrationNumber", ent.getRegistrationNumber());//工商注册号;
				json.put("enterpriseCode", ent.getEnterpriseCode());//组织机构代码
				json.put("factoryAddress", ent.getFactoryAddress());//生产经营地址
				json.put("enterprisePossession", ent.getEnterprisePossessionName());//企业属地
				json.put("enterpriseZipcode", ent.getEnterpriseZipcode());//邮政编码
				if(ent.getEnterpriseNature() != null && !"".equals(ent.getEnterpriseNature()))
				{
					map.put("codeName", "企业性质");
					map.put("itemValue", ent.getEnterpriseNature());
					json.put("enterpriseNature", codeService.findCodeValueByMap(map).getItemText());//企业性质
				}
				else
				{
					json.put("enterpriseNature", "");//企业性质
				}
				
				if(ent.getEnterpriseScale() != null && !"".equals(ent.getEnterpriseScale()))
				{
					map.put("codeName", "企业规模");
					map.put("itemValue", ent.getEnterpriseScale());
					json.put("enterpriseScale", codeService.findCodeValueByMap(map).getItemText());//企业规模
				}
				else
				{
					json.put("enterpriseScale", "");//企业规模
				}
				
				String qyfl = "";
				if(ent.getEnterpriseType() != null && !"".equals(ent.getEnterpriseType()))
				{
					String[] qyfls = ent.getEnterpriseType().replaceAll(" ", "").split(",");
					for(String s:qyfls)
					{
						map.put("codeName", "企业分类");
						map.put("itemValue", s);
						qyfl += codeService.findCodeValueByMap(map).getItemText() + ",";
					}
					if(qyfl.length() != 0)
					{
						qyfl = qyfl.substring(0,qyfl.length()-1);
					}
				}
				json.put("enterpriseType", qyfl);//企业分类
				
				if(ent.getEnterpriseCategory() != null && !"".equals(ent.getEnterpriseCategory()))
				{
					map.put("codeName", "行业类别");
					map.put("itemValue", ent.getEnterpriseCategory());
					json.put("enterpriseCategory", codeService.findCodeValueByMap(map).getItemText());//行业类别
				}
				else
				{
					json.put("enterpriseCategory", "");//行业类别
				}
				
				json.put("enterpriseNationnality", ent.getEnterpriseNationnality());//投资方国籍
				json.put("enterpriseLegalName", ent.getEnterpriseLegalName());//法人姓名
				
				if(ent.getEnterpriseLegalSex() != null && !"".equals(ent.getEnterpriseLegalSex()))
				{
					map.put("codeName", "性别");
					map.put("itemValue", ent.getEnterpriseLegalSex());
					json.put("enterpriseLegalSex", codeService.findCodeValueByMap(map).getItemText());//法人性别
				}
				else
				{
					json.put("enterpriseLegalSex", "");//法人性别
				}
				json.put("enterpriseLegalAge", ent.getEnterpriseLegalAge());//法人年龄
				json.put("enterpriseLegalPhone", ent.getEnterpriseLegalPhone());//法人联系电话
				json.put("enterpriseLegalCardnum", ent.getEnterpriseLegalCardnum());//法人证件号码
				json.put("enterpriseLegalEmail", ent.getEnterpriseLegalEmail());//法人电子邮箱
				json.put("enterpriseLegalZw", ent.getEnterpriseLegalZw());//法人职务
				json.put("enterpriseFoundDate", ent.getEnterpriseFoundDate());//企业设立日期
				json.put("enterpriseProductDate", ent.getEnterpriseProductDate());//企业投产日期
				
				String zcdw = "";
				map.put("codeName", "货币种类");
				if(ent.getEnterpriseRegisterMoneyDw() != null && !"".equals(ent.getEnterpriseRegisterMoneyDw()))
				{
					map.put("itemValue", ent.getEnterpriseRegisterMoneyDw());
					zcdw = codeService.findCodeValueByMap(map).getItemText();
				}
				
				json.put("enterpriseRegisterMoney", StringTools.NullToStr(ent.getEnterpriseRegisterMoney(),"")+zcdw);//注册资本
				
				if(ent.getEnterpriseInvestMoneyDw() != null && !"".equals(ent.getEnterpriseInvestMoneyDw()))
				{
					map.put("itemValue", ent.getEnterpriseInvestMoneyDw());
					zcdw = codeService.findCodeValueByMap(map).getItemText();
				}
				
				json.put("enterpriseInvestMoney", StringTools.NullToStr(ent.getEnterpriseInvestMoney(),"")+zcdw);//投资总额

				if(ent.getEnterpriseFixedassetMoneyDw() != null && !"".equals(ent.getEnterpriseFixedassetMoneyDw()))
				{
					map.put("itemValue", ent.getEnterpriseFixedassetMoneyDw());
					zcdw = codeService.findCodeValueByMap(map).getItemText();
				}
				json.put("enterpriseFixedassetMoney", StringTools.NullToStr(ent.getEnterpriseFixedassetMoney(),"")+zcdw);//固定资产总额
				
				json.put("enterpriseFloorArea", ent.getEnterpriseFloorArea());//占地面积
				json.put("enterpriseOfficeArea", ent.getEnterpriseOfficeArea());//建筑面积
				json.put("enterpriseWorkshopArea", ent.getEnterpriseWorkshopArea());//车间厂房面积
				json.put("enterpriseWearhouseArea", ent.getEnterpriseWearhouseArea());//仓库面积
				
				if(ent.getEnterprisWorkshopOwn() != null && !"".equals(ent.getEnterprisWorkshopOwn()))
				{
					map.put("codeName", "厂房归属");
					map.put("itemValue", ent.getEnterprisWorkshopOwn());
					json.put("enterprisWorkshopOwn", codeService.findCodeValueByMap(map).getItemText());//厂房归属
					if(ent.getEnterprisWorkshopOwn().equals("2"))
					{
						json.put("houseOwner", StringTools.NullToStr(ent.getHouseOwner(),""));//出租方
						json.put("ownerTel", StringTools.NullToStr(ent.getOwnerTel(),""));//出租方联系方式
					}
				}
				else
				{
					json.put("enterprisWorkshopOwn", "");//厂房归属
				}
				
				json.put("enterpriseStaffCount", ent.getEnterpriseStaffCount());//员工总数
				json.put("enterpriseManagerCount", ent.getEnterpriseManagerCount());//管理人员数
				json.put("enterpriseWorkerCount", ent.getEnterpriseWorkerCount());//工人数
				json.put("gridName", ent.getGridName());//所属网格
				json.put("gridManageteamName", ent.getGridManageteamName());//网格管理中队
				
				if(ent.getGrid() != null && !"".equals(ent.getGrid()))
				{
					ComGriMan comGriMan = comGriManService.getById(ent.getGrid());
					json.put("gridManagePersonName", StringTools.NullToStr(comGriMan.getGridManagePersonName(),""));//网格管理人员
				}
				else
				{
					json.put("gridManagePersonName", "");//网格管理人员
				}
				
				json.put("enterpriseScope", ent.getEnterpriseScope());//经营范围
				
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("infoId", ent.getId());
				List<CheckRecord> checkRecords=checkRecordService.findCheckRecord(paraMap);
				String recordStr = StringTools.checkRecordToStr(checkRecords);//将列表转换成Str
				
				paraMap.put("linkId",ent.getLinkId());
				paraMap.put("mkType", "qyxx");
				paraMap.put("picType","cqtp");
				List<PhotoPic> picList = photoPicService.findPicPath(paraMap);//获取执法文书材料
				
				json.put("code", "0");
				json.put("checkRecords", recordStr);
				json.put("picList", picList);
				result=json.toString();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}else if("aqscyhlb".equals(actionName)){
			int  pageNo = Integer.parseInt(jsonJ.getString("pageNo"));
			int  pageSize = Integer.parseInt(jsonJ.getString("pageSize"));
			String searchLike=jsonJ.getString("searchLike");
			try {
				result=aqscyhList(pageNo,pageSize,ent.getEnterpriseName(),searchLike);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("aqscyhxq".equals(actionName)){
			String yhid = jsonJ.getString("yhid");
			try {
				Map mm = new HashMap();
				mm.put("id", yhid);
				mm.put("sqlID", "queryTroubleDetailByMap");
				Map detail = httpService.getMapByMap(mm);//获取map 对象
				List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
				List<HashMap> rectInfos = new ArrayList<HashMap>();
				if(detail!=null){
					
					map.put("linkId",detail.get("linkId"));
					map.put("mkType", "troMan");
					map.put("picType","troManfj");
					picList1 = photoPicService.findPicPath(map);//获取整改前图片
					map.put("picType","troManRect");
					
					//此处进行调整 整改的信息为列表展示
					map.put("yhbId", yhid);
					rectInfos = troManService.queryRectInfosByMap(map);
					
					if(rectInfos!=null&&!rectInfos.isEmpty()){
						for(Map m:rectInfos){
							if(m.get("linkId") != null && !"".equals(m.get("linkId")))
							{
								map.put("linkId", m.get("linkId"));
								List<PhotoPic> pics = photoPicService.findPicPath(map);//获取整改后图片
								m.put("photos", pics);
							}
						}
					}
					
				}
					//获取隐患的审核记录
					map.put("infoId", detail.get("id"));
					List<CheckRecord>  checkRecords=checkRecordService.findCheckRecord(map);
					String recordStr = StringTools.checkRecordToStr(checkRecords);//将列表转换成Str
					JSONObject j = JSONObject.fromObject(detail);
					j.put("checkrecord",recordStr);
					j.put("picList1", picList1);
					j.put("rectInfos", rectInfos);
					j.put("code", "0");
					result = j.toString();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("zzqklb".equals(actionName)){
			int  pageNo = Integer.parseInt(jsonJ.getString("pageNo"));
			int  pageSize = Integer.parseInt(jsonJ.getString("pageSize"));
//			String searchLike=jsonJ.getString("searchLike");
			String searchLike="";
			try {
				result=zzqkList(pageNo,pageSize,ent.getEnterpriseName(),searchLike);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("zzqkxq".equals(actionName)){
			String zzqkId = jsonJ.getString("zzqkId");
			try {
				
				IntSit intSit=intSitService.getById(zzqkId);
				if(null!=intSit){
					JSONObject json = new JSONObject();
					json.put("id",zzqkId);
					json.put("intelCardNum",null==intSit.getIntelligenceCardnum()?"":intSit.getIntelligenceCardnum());
					Map zzqkMap = new HashMap();
					json.put("intelCardName",null==intSit.getIntelligenceCardname()?"":intSit.getIntelligenceCardname());
					zzqkMap.put("codeName", "资质类型");
					zzqkMap.put("itemValue", intSit.getIntelligenceType());
					json.put("intelType",null==codeService.findCodeValueByMap(zzqkMap).getItemText()?"":codeService.findCodeValueByMap(zzqkMap).getItemText());
					zzqkMap.put("codeName", "资质级别");
					zzqkMap.put("itemValue", intSit.getZzjb());
					json.put("quaLevel",null==codeService.findCodeValueByMap(zzqkMap).getItemText()?"":codeService.findCodeValueByMap(zzqkMap).getItemText());
					json.put("bussinessScope",null==intSit.getBussinessScope()?"":intSit.getBussinessScope());
					json.put("intelContent",null==intSit.getIntelligenceContent()?"":intSit.getIntelligenceContent());
					json.put("intelInstitution",null==intSit.getIntelligenceInstitution()?"":intSit.getIntelligenceInstitution());
					json.put("intelCardDate",null==intSit.getIntelligenceCardDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceCardDate())); 
					json.put("changeDate",null==intSit.getChangeDate()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getChangeDate())); 
					json.put("intelValiStart",null==intSit.getIntelligenceValidityStart()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceValidityStart()));
					json.put("intelValiEnd",null==intSit.getIntelligenceValidityEnd()?"":new SimpleDateFormat("yyyy-MM-dd").format(intSit.getIntelligenceValidityEnd()));
					json.put("state",null==intSit.getAuditState()?"":intSit.getAuditState());
					json.put("intelligenceRemark",null==intSit.getIntelligenceRemark()?"":intSit.getIntelligenceRemark());
					
					zzqkMap.put("linkId",intSit.getLinkId());
					zzqkMap.put("mkType", "zzqk");
					zzqkMap.put("picType","zzfj");
					json.put("picList",photoPicService.findPicPath(zzqkMap));
					
					zzqkMap.put("infoId", intSit.getId());
					List<CheckRecord>  checkRecords=checkRecordService.findCheckRecord(zzqkMap);
					String recordStr = StringTools.checkRecordToStr(checkRecords);//将列表转换成Str
					json.put("checkrecord",recordStr);
					json.put("code", "0");
					result = json.toString();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("validateCompanyName".equals(actionName)){
			
			JSONObject jsonSuc=new JSONObject();
			jsonSuc.put("code", "0");
			jsonSuc.put("passWord", passWord);
			jsonSuc.put("loginId", loginId);
			
			result = AESEncrypt.encode(jsonSuc.toString());
		}
		System.out.println("返回" + result);
		return result;
	}

	public static void main(String[] args){
//		Endpoint.publish("http://localhost:8088/webService/ZhajInfo", new ZhajInfo());
//		System.out.println("service success!");
		
		JSONObject json=new JSONObject();
		json.put("companyId", "2c99fe8456b2c6660156b67fdd6f0698");
		json.put("actionName", "baseInfo");
		System.out.println(json.toString());
	}
	
	public String  aqscyhList(int pageNo,int pageSize,String companyName,String searchLike) throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
	//	paraMap.put("aj", "aj");//只查询非企业上报的隐患
		if(pagination==null)
		    pagination = new Pagination(pageNo,pageSize);

		paraMap.put("companyName", "%" +companyName + "%");
		if(null!=searchLike&&!"".equals(searchLike)){
			if(searchLike.contains("paramStr")){
				System.out.println(searchLike);
				//searchLike="paramStr"+",yhmc="+yhmc+",yhly="+yhly+",sbrymc="+sbrymc+",yhjb="+yhjb+",yhlb="+yhlb+",zgzt="+zgzt;
				String[] paramStr=searchLike.split(",");
				if(paramStr[1].split("=").length>1){
					paraMap.put("troubleName", "%" + paramStr[1].split("=")[1].trim() + "%");
				}
				
				if(paramStr[2].split("=").length>1){
					paraMap.put("troubleSource", "%" + paramStr[2].split("=")[1].trim() + "%");
				}
				
				if(paramStr[3].split("=").length>1){
					paraMap.put("userName", "%" + paramStr[3].split("=")[1].trim() + "%");
				}
				
				if(paramStr[4].split("=").length>1){
					paraMap.put("troubleLevel",  paramStr[4].split("=")[1].trim());
				}
				
				if(paramStr[5].split("=").length>1){
					paraMap.put("troubleSort",  paramStr[5].split("=")[1].trim());
				}
				
				if(paramStr[6].split("=").length>1){
					String zgzt=paramStr[6].split("=")[1].trim();
					if("待审核".equals(zgzt.trim())){
						List dsh=new ArrayList();
						dsh.add("2");
						dsh.add("3");
						dsh.add("5");
						dsh.add("7");
						dsh.add("20");
						dsh.add("21");
						paraMap.put("dsh", dsh);
					}else if("待整改".equals(zgzt.trim())){
						List dzg=new ArrayList();
						dzg.add("11");
						paraMap.put("dzg", dzg);
					}else if("审核未通过".equals(zgzt.trim())){
						List dzg=new ArrayList();
						dzg.add("1");
						paraMap.put("dzg", dzg);
					}else if("整改未完成".equals(zgzt.trim())){
						paraMap.put("zgwwc", "zgwwc");
					}else{//整改完成
						paraMap.put("zgwc", "zgwc");
					}
				}

				
			}else{
				paraMap.put("searchLike", "%" +searchLike + "%");
			}
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("troubleLevel","402880084196a4a301419759d52a024b");
		codeMap.put("troubleSort","402880084196a4a301419759b28e0249");
		//codeMap.put("rectificationState","402880084196a4a30141975da934025d");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
//		final String filter = "id|troubleName|troubleSource|areaId|companyName|userName|troubleLevel|troubleSort|rectificationState|createUserID|auditResult|ifCorrected|userId|ifReportAwh|dealState|dealDeptId|ifRedirect|areaName|ifla|";
//		if (filter != null && filter.length() > 1) {
//			config.setJsonPropertyFilter(new PropertyFilter() {
//				public boolean apply(Object source, String name, Object value) {
//					if (filter.indexOf(name + "|") != -1)
//						return false;
//					else
//						return true;
//				}
//			});
//		}
//		pagination.setPageNumber(pageNo);
//		pagination.setPageSize(pageSize);
		pagination = troManService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		json.put("code", "0");
		return json.toString();
	}
	
	public String  zzqkList(int pageNo,int pageSize,String companyName,String searchLike) throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
	//	paraMap.put("aj", "aj");//只查询非企业上报的隐患
		if(pagination==null)
		    pagination = new Pagination(pageNo,pageSize);

		paraMap.put("companyName", "%" +companyName + "%");
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("intelligenceType", "40288008416c6c1a01416ccf6ab100a7");
		config.registerJsonValueProcessor(String.class,
				new CodeJsonValueProcessor(codeMap));
		final String filter = "id|areaName|companyName|intelligenceCardnum|intelligenceCardname|intelligenceType|auditResult|auditState|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name,
						Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
//		pagination.setPageNumber(pageNo);
//		pagination.setPageSize(pageSize);
		pagination = intSitService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		json.put("code", "0");
		return json.toString();
	}
	
}
