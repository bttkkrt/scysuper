package com.jshx.tzzyry.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Constants;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.qyjbxx.util.ClientAuthenticationHandler;
import com.jshx.tzzyry.entity.SpeJobPer;
import com.jshx.tzzyry.service.SpeJobPerService;
import com.lkdj.kzk.GetTkzxxRequest;
import com.lkdj.kzk.GetTkzxxResponse;
import com.lkdj.kzk.TkzxxPort;
import com.lkdj.kzk.TkzxxPortService;
import com.lkdj.kzk.GetTkzxxRequest.Tkzxxs;
import com.lkdj.lkLog.entity.LkLog;
import com.lkdj.lkLog.service.LkLogService;
import com.lkdj.rkk.GetLkRKRequest;
import com.lkdj.rkk.GetLkRKResponse;
import com.lkdj.rkk.LkrekouPort;
import com.lkdj.rkk.LkrekouPortService;
import com.lkdj.rkk.GetLkRKRequest.Lkrk;
import com.lkdj.util.ceateLkRKUtil;
import com.lkdj.util.ceateTkzxxUtil;

public class SpeJobPerAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private SpeJobPer speJobPer = new SpeJobPer();

	/**
	 * 业务类
	 */
	@Autowired
	private SpeJobPerService speJobPerService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date querySpecialVerificationDateStart;

	private Date querySpecialVerificationDateEnd;


	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private CodeService codeService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private HttpService httpService;
	
	private File userFile;
	private String message;
	
	private String loginUserId;
	private String roleName;
	private int pageNo;
	private int pageSize;
	private String searchLike;
	@Autowired
	private UserService userService;
	@Autowired
	private LkLogService lkLogService;
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String init(){
		loginUserId = this.getLoginUser().getId();
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "1";
		for(UserRight ur:list)
		{
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "0";
				break;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != speJobPer){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != speJobPer.getAreaId()) && (0 < speJobPer.getAreaId().trim().length())){
				paraMap.put("areaId",  speJobPer.getAreaId() );
			}

			if ((null != speJobPer.getCompanyName()) && (0 < speJobPer.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + speJobPer.getCompanyName().trim() + "%");
			}
			
			if ((null != speJobPer.getCompanyId()) && (0 < speJobPer.getCompanyId().trim().length())){
				paraMap.put("companyId",  speJobPer.getCompanyId() );
			}

			
			if ((null != speJobPer.getSpecialName()) && (0 < speJobPer.getSpecialName().trim().length())){
				paraMap.put("specialName", "%" + speJobPer.getSpecialName().trim() + "%");
			}

			if ((null != speJobPer.getSpecialJobCradnum()) && (0 < speJobPer.getSpecialJobCradnum().trim().length())){
				paraMap.put("specialJobCradnum", "%" + speJobPer.getSpecialJobCradnum().trim() + "%");
			}

			if ((null != speJobPer.getSpecialJobType()) && (0 < speJobPer.getSpecialJobType().trim().length())){
				paraMap.put("specialJobType", speJobPer.getSpecialJobType().trim() );
			}
			
			if (null != querySpecialVerificationDateStart){
				paraMap.put("startSpecialVerificationDate", querySpecialVerificationDateStart);
			}

			if (null != querySpecialVerificationDateEnd){
				paraMap.put("endSpecialVerificationDate", querySpecialVerificationDateEnd);
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("specialJobType","4028800550653c270150654287c90005");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|specialName|specialJobCradnum|specialJobType|specialVerificationDate|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination = speJobPerService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != speJobPer)&&(null != speJobPer.getId()))
			speJobPer = speJobPerService.getById(speJobPer.getId());
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
	
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		speJobPer.setAreaId(enBaseInfo.getEnterprisePossession());
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
		speJobPer.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		speJobPer.setCompanyId(enBaseInfo.getId());
		speJobPer.setCompanyName(enBaseInfo.getEnterpriseName());
		
		if ("add".equalsIgnoreCase(this.flag)){
			speJobPer.setDeptId(this.getLoginUserDepartmentId());
			speJobPer.setDelFlag(0);
			speJobPerService.save(speJobPer);
		}else{
			speJobPerService.update(speJobPer);
		}
		
		

		try {
			String zjhm = NullToString(speJobPer.getSpecialJobCradnum()).replaceAll(" ", "");
			if(zjhm != null && !"".equals(zjhm))
			{
				LkLog log1 = new LkLog();
				LkLog log2 = new LkLog();
				log1.setNrid(speJobPer.getId());
				log1.setLb("speJobPer基本信息");
				log2.setNrid(speJobPer.getId());
				log2.setLb("speJobPer扩展信息");
				ceateLkRKUtil ceateLkRKUtil = new ceateLkRKUtil();
				GetLkRKRequest rkqq = new GetLkRKRequest();
				Lkrk lkrk = ceateLkRKUtil.ceateLkRK(
				"99",//证件类型
				NullToString(zjhm) ,//证件号码
				NullToString(speJobPer.getSpecialName()),//姓名
				NullToString(speJobPer.getSpecialSex()),//性别
				gj(speJobPer.getSpecialNationnality()),//国籍
				xl(speJobPer.getSpecialHighestSchool()),//文化程度
				"特种作业人员",//职业
				"1");//人员状态
				rkqq.getLkrk().add(lkrk);
				LkrekouPortService lkrekouPortService = new LkrekouPortService();
				lkrekouPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				LkrekouPort lkrekouPort = lkrekouPortService.getLkrekouPortSoap11();
				GetLkRKResponse rkhf = lkrekouPort.getLkRK(rkqq);
				System.out.println("特种作业人员基本信息"+rkhf.getMsg());
				log1.setResult(rkhf.getMsg());
				lkLogService.save(log1);
				
				//人口扩展数据
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				JSONArray ja = new JSONArray();
				String specialDutyDate = speJobPer.getSpecialDutyDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(speJobPer.getSpecialDutyDate()):"";
				String specialPostDate = speJobPer.getSpecialPostDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(speJobPer.getSpecialPostDate()):"";
				String specialFirstPickDate = speJobPer.getSpecialFirstPickDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(speJobPer.getSpecialFirstPickDate()):"";
				String specialVerificationDate = speJobPer.getSpecialVerificationDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(speJobPer.getSpecialVerificationDate()):"";
				ja.add((new JSONObject()).put("QYMC", NullToString(speJobPer.getCompanyName())));//企业名称
				ja.add((new JSONObject()).put("ZGXW", NullToString(speJobPer.getSpecialHighestDegree())));//最高学位
				ja.add((new JSONObject()).put("BYXY",NullToString(speJobPer.getSpecialSchool())));//毕业院校
				ja.add((new JSONObject()).put("ZY", NullToString(speJobPer.getSpecialSpecialized()) ));//专业
				ja.add((new JSONObject()).put("ZC", NullToString(speJobPer.getSpecialTitle())));//职称
				ja.add((new JSONObject()).put("AQSCZGZH",NullToString(speJobPer.getSpecialSpecializedNum())));//专业资格证号
				ja.add((new JSONObject()).put("LXDH1" , NullToString(speJobPer.getSpecialPhone())));//联系电话
				ja.add((new JSONObject()).put("LXDH2" , NullToString(speJobPer.getSpecialPhone2())));//联系电话2
				ja.add((new JSONObject()).put("EMAIL" , NullToString(speJobPer.getSpecialEmail())));//电子邮箱
				ja.add((new JSONObject()).put("ZZ" , NullToString(speJobPer.getSpecialAddress()) ));//住址
				ja.add((new JSONObject()).put("JRDWSJ" , specialDutyDate ));//进入本单位日期
				ja.add((new JSONObject()).put("CSGWSJ" , specialPostDate ));//从事本岗位时间
				ja.add((new JSONObject()).put("TZZYLX" , NullToString(speJobPer.getSpecialJobType())));//特种作业类型
				ja.add((new JSONObject()).put("PXDW" , NullToString(speJobPer.getSpecialTrainingUnit()) ));//培训单位
				ja.add((new JSONObject()).put("FZJG" , NullToString(speJobPer.getSpecialCardInstitution())));//发证机关
				ja.add((new JSONObject()).put("CLRQ" , specialFirstPickDate ));//初领日期
				ja.add((new JSONObject()).put("FSRQ" , specialVerificationDate));//复审日期
				JSONObject jo = new JSONObject();
				jo.put("rkkzxx", ja.toString());
				String kzxx = jo.toString();
				Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("tzzyryb",speJobPer.getCreateTime(), kzxx, zjhm, "");
				kzqq.getTkzxxs().add(tkzxxs);
				
				TkzxxPortService  tkzxxPortService= new TkzxxPortService();
				tkzxxPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
				GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
				System.out.println("特种作业人员扩展信息"+getTkzxxResponse.getMsg());
				log2.setResult(getTkzxxResponse.getMsg());
				lkLogService.save(log2);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != speJobPer)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到speJobPer中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			speJobPerService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	/**
	 * 导入初始化，需要传递参数
	 * fq 2016-3-4
	 */
	public String initImport(){
		return SUCCESS;
	}
	
	
	/**
	 * 导入特种作业人员
	 * fq 2016-3-4
	 */
	public String importSpeJobPer(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
		StringBuffer errorInfo = new StringBuffer();
		Map map = new HashMap();
		map.put("loginId", this.getLoginUser().getLoginId());
		EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", enBaseInfo.getEnterprisePossession());
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(userFile);
			} catch (Exception e) {
				e.printStackTrace();
				message = "导入失败，请使用系统模板！";
			}
			Sheet sheet = workbook.getSheet(0);
			
			int column = sheet.getColumns();
			int row = sheet.getRows();
			List<SpeJobPer> speJobPerList = new ArrayList<SpeJobPer>();
			for (int i = 1; i < row; i++) {
				
				try {
					Cell[] cells = new Cell[column];
					Cell[] cellsTmp = sheet.getRow(i);
					for (int j = 0; j < cellsTmp.length; j++) {
						cells[j] = cellsTmp[j];
					}
					
					if ((cells[0]==null ||cells[0].getContents().equals(""))&&(cells[1]==null ||cells[1].getContents().equals(""))&&(cells[2]==null ||cells[2].getContents().equals("")) ){
						workbook.close();
						break;
					}
					SpeJobPer sjb = new SpeJobPer();
					sjb.setAreaId(enBaseInfo.getEnterprisePossession());
					sjb.setAreaName(codeService.findCodeValueByMap(m).getItemText());
					sjb.setCompanyId(enBaseInfo.getId());
					sjb.setCompanyName(enBaseInfo.getEnterpriseName());
					sjb.setDeptId(this.getLoginUserDepartmentId());
					sjb.setDelFlag(0);
					
					StringBuffer colError = null;
					//姓名
					if (cells[0]==null || cells[0].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：姓名输入有误，不能为空，请检查!</span><br>");
					}else{
						sjb.setSpecialName(cells[0].getContents().trim());
					}
					//特种作业证号
					if (cells[1]==null ||cells[1].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：特种作业证号输入有误，不能为空，请检查!</span><br>");
					}else{
							sjb.setSpecialJobCradnum(cells[1].getContents().trim());
					}
					//联系电话1
					if (cells[2]==null || cells[2].getContents().equals("")) {
						if(colError==null)
							colError = new StringBuffer();
						colError.append("<span style='color:red'>错误：联系电话1输入有误，不能为空，请检查!</span><br>");
					}else{
						sjb.setSpecialPhone(cells[2].getContents().trim());
					}
					
					//特种作业类型
					if (cells[3] != null){
						if(!"".equals(cells[3].getContents().trim())){
							Map codeMap = new HashMap();
							codeMap.put("codeName", "特种作业类型");
							codeMap.put("itemText", cells[3].getContents().trim());
							sjb.setSpecialJobType(null==codeService.findCodeValueByMap(codeMap).getItemValue()?"":codeService.findCodeValueByMap(codeMap).getItemValue());
						}
					}
					
					//复审日期
					if (cells[4] != null){
						if(!"".equals(cells[4].getContents().trim())){
							try {
								if(cells[4].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[4]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjb.setSpecialVerificationDate(c00.getDate());
								}
					            else
								{
					            	sjb.setSpecialVerificationDate(sdf.parse(cells[4].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：复审日期格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					
					//性别
					if (cells[5] != null)
						if(!"".equals(cells[5].getContents().trim())){
							Map codeMap = new HashMap();
							codeMap.put("codeName", "性别");
							codeMap.put("itemText", cells[5].getContents().trim());
							sjb.setSpecialSex(null==codeService.findCodeValueByMap(codeMap).getItemValue()?"":codeService.findCodeValueByMap(codeMap).getItemValue());
						}
					
					//国籍
					if (cells[6] != null){
						sjb.setSpecialNationnality(cells[6].getContents().trim());
					}
					//最高学历
					if (cells[7] != null){
						if(!"".equals(cells[7].getContents().trim())){
							Map codeMap = new HashMap();
							codeMap.put("codeName", "学历");
							codeMap.put("itemText", cells[7].getContents().trim());
							sjb.setSpecialHighestSchool(null==codeService.findCodeValueByMap(codeMap).getItemValue()?"":codeService.findCodeValueByMap(codeMap).getItemValue());
						}
					}
					//最高学位
					if (cells[8] != null){
						if(!"".equals(cells[8].getContents().trim())){
							Map codeMap = new HashMap();
							codeMap.put("codeName", "学位");
							codeMap.put("itemText", cells[8].getContents().trim());
							sjb.setSpecialHighestDegree(null==codeService.findCodeValueByMap(codeMap).getItemValue()?"":codeService.findCodeValueByMap(codeMap).getItemValue());
							
						}
					}
					//毕业院校
					if (cells[9] != null){
						sjb.setSpecialSchool(cells[9].getContents().trim());
					}
					//专业
					if (cells[10] != null){
						sjb.setSpecialSpecialized(cells[10].getContents().trim());
					}
					//职称
					if (cells[11] != null){
						sjb.setSpecialTitle(cells[11].getContents().trim());
					}
					//电子邮箱
					if (cells[12] != null){
						sjb.setSpecialEmail(cells[12].getContents().trim());
					}
					//进入本单位日期
					if (cells[13] != null){
						if(!"".equals(cells[13].getContents().trim())){
							try {
								if(cells[13].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[13]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjb.setSpecialDutyDate(c00.getDate());
								}
					            else
								{
					            	sjb.setSpecialDutyDate(sdf.parse(cells[13].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：进入本单位日期格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					//从事本岗位时间
					if (cells[14] != null){
						if(!"".equals(cells[14].getContents().trim())){
							try {
								if(cells[14].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[14]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjb.setSpecialPostDate(c00.getDate());
								}
					            else
								{
					            	sjb.setSpecialPostDate(sdf.parse(cells[14].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：从事本岗位时间格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					
					
					//培训单位
					if (cells[15] != null){
						sjb.setSpecialEmail(cells[15].getContents().trim());
					}
					//发证机关
					if (cells[16] != null){
						sjb.setSpecialCardInstitution(cells[16].getContents().trim());
					}
					//初领日期
					if (cells[17] != null){
						if(!"".equals(cells[17].getContents().trim())){
							try {
								if(cells[17].getType() == CellType.DATE)
								{
									 DateCell c00 = (DateCell)cells[17]; 
								     TimeZone tz = TimeZone.getTimeZone("GMT"); 
								     sdf.setTimeZone(tz) ; 
								     sjb.setSpecialFirstPickDate(c00.getDate());
								}
					            else
								{
					            	sjb.setSpecialFirstPickDate(sdf.parse(cells[17].getContents().trim()));
								}
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								if(colError==null)
									colError = new StringBuffer();
								colError.append("<span style='color:red'>错误：初领日期格式错误，应为yyyy/MM/dd，请检查!</span><br>");
							}
						}
					}
					
					//联系电话2
					if (cells[18] != null){
						sjb.setSpecialPhone2(cells[18].getContents().trim());
					}
					//住址
					if (cells[19] != null){
						sjb.setSpecialAddress(cells[19].getContents().trim());
					}
					//备注
					if (cells[20] != null){
						sjb.setSpecialRemark(cells[20].getContents().trim());
					}
					
					if(colError==null){
						speJobPerList.add(sjb);
						errorInfo.append("导入第").append(i).append("条记录成功！<br><br>");
					}else{
						errorInfo.append("导入第").append(i).append("条记录失败，错误信息如下：<br>");
						errorInfo.append(colError).append("<br>");
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			message = errorInfo.toString();
			try {
				List<String> ids = new ArrayList<String>();
				for(SpeJobPer s:speJobPerList){
					speJobPerService.save(s);
					ids.add(s.getId());
				}
				
				if(ids.size() != 0)
				{
					try {
						LkLog log1 = new LkLog();
						LkLog log2 = new LkLog();
						log1.setNrid(ids.toString());
						log1.setLb("speJobPer基本信息");
						log2.setNrid(ids.toString());
						log2.setLb("speJobPer扩展信息");
						//对接特种作业人员表
						Map mm = new HashMap();
						mm.put("ids", ids);
						List<SpeJobPer> tzzylist = speJobPerService.findSpeJobPer(mm);
						int total7 = tzzylist.size();
						int num7 = total7/100;
						int ys7 = total7%100;
						if(ys7 != 0)
						{
							num7 = num7 + 1;
						}
						for(int i=0;i<num7;i++)
						{
							int start = 100*i;
							int end = 100*(i+1);
							if(end > total7)
							{
								end = total7;
							}
							GetLkRKRequest rkqq = new GetLkRKRequest();
							int index = i+1;
							rkqq.setPici(index+"");
							ceateLkRKUtil ceateLkRKUtil = new ceateLkRKUtil();
							GetTkzxxRequest kzqq = new GetTkzxxRequest();
							ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
							for(int j=start;j<end;j++)
							{
								SpeJobPer tzzy = tzzylist.get(j);
								String zjhm = NullToString(tzzy.getSpecialJobCradnum()).replaceAll(" ", "");
								if(zjhm != null && !"".equals(zjhm))
								{
									Lkrk lkrk = ceateLkRKUtil.ceateLkRK(
									"99",//证件类型
									NullToString(zjhm) ,//证件号码
									NullToString(tzzy.getSpecialName()),//姓名
									NullToString(tzzy.getSpecialSex()),//性别
									gj(tzzy.getSpecialNationnality()),//国籍
									xl(tzzy.getSpecialHighestSchool()),//文化程度
									"特种作业人员",//职业
									"1");//人员状态
									rkqq.getLkrk().add(lkrk);
									
									//人口扩展数据
									JSONArray ja = new JSONArray();
									String specialDutyDate = tzzy.getSpecialDutyDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialDutyDate()):"";
									String specialPostDate = tzzy.getSpecialPostDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialPostDate()):"";
									String specialFirstPickDate = tzzy.getSpecialFirstPickDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialFirstPickDate()):"";
									String specialVerificationDate = tzzy.getSpecialVerificationDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialVerificationDate()):"";
									ja.add((new JSONObject()).put("QYMC", NullToString(tzzy.getCompanyName())));//企业名称
									ja.add((new JSONObject()).put("ZGXW", NullToString(tzzy.getSpecialHighestDegree())));//最高学位
									ja.add((new JSONObject()).put("BYXY",NullToString(tzzy.getSpecialSchool())));//毕业院校
									ja.add((new JSONObject()).put("ZY", NullToString(tzzy.getSpecialSpecialized()) ));//专业
									ja.add((new JSONObject()).put("ZC", NullToString(tzzy.getSpecialTitle())));//职称
									ja.add((new JSONObject()).put("AQSCZGZH",NullToString(tzzy.getSpecialSpecializedNum())));//专业资格证号
									ja.add((new JSONObject()).put("LXDH1" , NullToString(tzzy.getSpecialPhone())));//联系电话1
									ja.add((new JSONObject()).put("LXDH2" , NullToString(tzzy.getSpecialPhone2())));//联系电话1
									ja.add((new JSONObject()).put("EMAIL" , NullToString(tzzy.getSpecialEmail())));//电子邮箱
									ja.add((new JSONObject()).put("ZZ" , NullToString(tzzy.getSpecialAddress()) ));//住址
									ja.add((new JSONObject()).put("JRDWSJ" , specialDutyDate ));//进入本单位日期
									ja.add((new JSONObject()).put("CSGWSJ" , specialPostDate ));//从事本岗位时间
									ja.add((new JSONObject()).put("TZZYLX" , NullToString(tzzy.getSpecialJobType())));//特种作业类型
									ja.add((new JSONObject()).put("PXDW" , NullToString(tzzy.getSpecialTrainingUnit()) ));//培训单位
									ja.add((new JSONObject()).put("FZJG" , NullToString(tzzy.getSpecialCardInstitution())));//发证机关
									ja.add((new JSONObject()).put("CLRQ" , specialFirstPickDate ));//初领日期
									ja.add((new JSONObject()).put("FSRQ" , specialVerificationDate));//复审日期
									JSONObject jo = new JSONObject();
									jo.put("rkkzxx", ja.toString());
									String kzxx = jo.toString();
									Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("tzzyryb",tzzy.getCreateTime(), kzxx, zjhm, "");
									kzqq.getTkzxxs().add(tkzxxs);
									
								}
							}
							
							LkrekouPortService lkrekouPortService = new LkrekouPortService();
							lkrekouPortService.setHandlerResolver(new HandlerResolver() {
								
								@Override
								public List<Handler> getHandlerChain(PortInfo arg0) {
									List<Handler> handlerList = new ArrayList();
									handlerList.add(new ClientAuthenticationHandler(
											"Zhaj_seivice@APP-00199",
											"zhaj"));
									return handlerList;
								}
							});
							LkrekouPort lkrekouPort = lkrekouPortService.getLkrekouPortSoap11();
							GetLkRKResponse rkhf = lkrekouPort.getLkRK(rkqq);
							System.out.println("特种作业人员基本信息"+rkhf.getMsg());
							log1.setResult(rkhf.getMsg());
							lkLogService.save(log1);
							
							TkzxxPortService  tkzxxPortService= new TkzxxPortService();
							tkzxxPortService.setHandlerResolver(new HandlerResolver() {
								
								@Override
								public List<Handler> getHandlerChain(PortInfo arg0) {
									List<Handler> handlerList = new ArrayList();
									handlerList.add(new ClientAuthenticationHandler(
											"Zhaj_seivice@APP-00199",
											"zhaj"));
									return handlerList;
								}
							});
							TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
							GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
							System.out.println("特种作业人员扩展信息"+getTkzxxResponse.getMsg());
							log2.setResult(getTkzxxResponse.getMsg());
							lkLogService.save(log2);
							
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				workbook.close();
					message = "导入失败！";
				BasalException ex = new BasalException(BasalException.ERROR, Constants.SAVING_DB_ERROR, e.getCause());
				throw ex;
			}
			workbook.close();
			if(message.equals("") && speJobPerList.size() == 0)
			{
				message = "导入失败，未读取到数据，请使用系统模板！";
			}
			if(message.contains("失败")){
				return ERROR;
			}else{
				message="";
				return SUCCESS;
			}
		
	}
	
	
	public String zwtInit(){
		
		roleName = "1";
		User user=userService.findUserByLoginId("zdz1");
		setSessionAttribute("curr_user", user);
		String userId=user.getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			roleName = "0";
		}
		return SUCCESS;
	}


	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap=httpService.addParamByRole(paraMap,this.getLoginUser().getId(), this.getLoginUser().getDeptCode());
		String userId=this.getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A23")){
			paraMap.put("createUserId", userId);
		}
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != speJobPer){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != speJobPer.getAreaId()) && (0 < speJobPer.getAreaId().trim().length())){
				paraMap.put("areaId",  speJobPer.getAreaId() );
			}

			if ((null != speJobPer.getCompanyName()) && (0 < speJobPer.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + speJobPer.getCompanyName().trim() + "%");
			}

			if ((null != speJobPer.getSpecialName()) && (0 < speJobPer.getSpecialName().trim().length())){
				paraMap.put("specialName", "%" + speJobPer.getSpecialName().trim() + "%");
			}

			if ((null != speJobPer.getSpecialJobCradnum()) && (0 < speJobPer.getSpecialJobCradnum().trim().length())){
				paraMap.put("specialJobCradnum", "%" + speJobPer.getSpecialJobCradnum().trim() + "%");
			}

			if ((null != speJobPer.getSpecialJobType()) && (0 < speJobPer.getSpecialJobType().trim().length())){
				paraMap.put("specialJobType", speJobPer.getSpecialJobType().trim() );
			}
			
			if (null != querySpecialVerificationDateStart){
				paraMap.put("startSpecialVerificationDate", querySpecialVerificationDateStart);
			}

			if (null != querySpecialVerificationDateEnd){
				paraMap.put("endSpecialVerificationDate", querySpecialVerificationDateEnd);
			}

		}
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称、姓名或特种作业证号".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("specialJobType","4028800550653c270150654287c90005");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaId|companyName|specialName|specialJobCradnum|specialJobType|specialVerificationDate|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = speJobPerService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage;
		totalPage = (pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public SpeJobPer getSpeJobPer(){
		return this.speJobPer;
	}

	public void setSpeJobPer(SpeJobPer speJobPer){
		this.speJobPer = speJobPer;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
	public Date getQuerySpecialVerificationDateStart() {
		return querySpecialVerificationDateStart;
	}
	public void setQuerySpecialVerificationDateStart(
			Date querySpecialVerificationDateStart) {
		this.querySpecialVerificationDateStart = querySpecialVerificationDateStart;
	}
	public Date getQuerySpecialVerificationDateEnd() {
		return querySpecialVerificationDateEnd;
	}
	public void setQuerySpecialVerificationDateEnd(
			Date querySpecialVerificationDateEnd) {
		this.querySpecialVerificationDateEnd = querySpecialVerificationDateEnd;
	}
	public File getUserFile() {
		return userFile;
	}

	public void setUserFile(File userFile) {
		this.userFile = userFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchLike() {
		return searchLike;
	}
	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}
	public void sendInfo()
	{

		try {
			//对接特种作业人员表
			List<SpeJobPer> tzzylist = speJobPerService.findSpeJobPer(null);
			int total7 = tzzylist.size();
			int num7 = total7/100;
			int ys7 = total7%100;
			if(ys7 != 0)
			{
				num7 = num7 + 1;
			}
			for(int i=0;i<num7;i++)
			{
				int start = 100*i;
				int end = 100*(i+1);
				if(end > total7)
				{
					end = total7;
				}
				GetLkRKRequest rkqq = new GetLkRKRequest();
				int index = i+1;
				rkqq.setPici(index+"");
				ceateLkRKUtil ceateLkRKUtil = new ceateLkRKUtil();
				GetTkzxxRequest kzqq = new GetTkzxxRequest();
				ceateTkzxxUtil ceateTkzxxUtil = new ceateTkzxxUtil();
				for(int j=start;j<end;j++)
				{
					SpeJobPer tzzy = tzzylist.get(j);
					String zjhm = NullToString(tzzy.getSpecialJobCradnum()).replaceAll(" ", "");
					if(zjhm != null && !"".equals(zjhm))
					{
						Lkrk lkrk = ceateLkRKUtil.ceateLkRK(
						"99",//证件类型
						NullToString(zjhm) ,//证件号码
						NullToString(tzzy.getSpecialName()),//姓名
						NullToString(tzzy.getSpecialSex()),//性别
						gj(tzzy.getSpecialNationnality()),//国籍
						xl(tzzy.getSpecialHighestSchool()),//文化程度
						"特种作业人员",//职业
						"1");//人员状态
						
						rkqq.getLkrk().add(lkrk);
						
						//人口扩展数据
						JSONArray ja = new JSONArray();
						String specialDutyDate = tzzy.getSpecialDutyDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialDutyDate()):"";
						String specialPostDate = tzzy.getSpecialPostDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialPostDate()):"";
						String specialFirstPickDate = tzzy.getSpecialFirstPickDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialFirstPickDate()):"";
						String specialVerificationDate = tzzy.getSpecialVerificationDate() != null?new SimpleDateFormat("yyyy-MM-dd").format(tzzy.getSpecialVerificationDate()):"";
						ja.add((new JSONObject()).put("QYMC", NullToString(tzzy.getCompanyName())));//企业名称
						ja.add((new JSONObject()).put("ZGXW", NullToString(tzzy.getSpecialHighestDegree())));//最高学位
						ja.add((new JSONObject()).put("BYXY",NullToString(tzzy.getSpecialSchool())));//毕业院校
						ja.add((new JSONObject()).put("ZY", NullToString(tzzy.getSpecialSpecialized()) ));//专业
						ja.add((new JSONObject()).put("ZC", NullToString(tzzy.getSpecialTitle())));//职称
						ja.add((new JSONObject()).put("AQSCZGZH",NullToString(tzzy.getSpecialSpecializedNum())));//专业资格证号
						ja.add((new JSONObject()).put("LXDH1" , NullToString(tzzy.getSpecialPhone())));//联系电话
						ja.add((new JSONObject()).put("LXDH2" , NullToString(tzzy.getSpecialPhone2())));//联系电话2
						ja.add((new JSONObject()).put("EMAIL" , NullToString(tzzy.getSpecialEmail())));//电子邮箱
						ja.add((new JSONObject()).put("ZZ" , NullToString(tzzy.getSpecialAddress()) ));//住址
						ja.add((new JSONObject()).put("JRDWSJ" , specialDutyDate ));//进入本单位日期
						ja.add((new JSONObject()).put("CSGWSJ" , specialPostDate ));//从事本岗位时间
						ja.add((new JSONObject()).put("TZZYLX" , NullToString(tzzy.getSpecialJobType())));//特种作业类型
						ja.add((new JSONObject()).put("PXDW" , NullToString(tzzy.getSpecialTrainingUnit()) ));//培训单位
						ja.add((new JSONObject()).put("FZJG" , NullToString(tzzy.getSpecialCardInstitution())));//发证机关
						ja.add((new JSONObject()).put("CLRQ" , specialFirstPickDate ));//初领日期
						ja.add((new JSONObject()).put("FSRQ" , specialVerificationDate));//复审日期
						JSONObject jo = new JSONObject();
						jo.put("rkkzxx", ja.toString());
						String kzxx = jo.toString();
						Tkzxxs tkzxxs = ceateTkzxxUtil.ceateTkzxxs("tzzyryb",tzzy.getCreateTime(), kzxx, zjhm, "");
						kzqq.getTkzxxs().add(tkzxxs);
						
					}
				}
				
				LkrekouPortService lkrekouPortService = new LkrekouPortService();
				lkrekouPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});
				LkrekouPort lkrekouPort = lkrekouPortService.getLkrekouPortSoap11();
				GetLkRKResponse rkhf = lkrekouPort.getLkRK(rkqq);
				System.out.println("特种作业人员基本信息"+rkhf.getStatus());
				
				TkzxxPortService  tkzxxPortService= new TkzxxPortService();
				tkzxxPortService.setHandlerResolver(new HandlerResolver() {
					
					@Override
					public List<Handler> getHandlerChain(PortInfo arg0) {
						List<Handler> handlerList = new ArrayList();
						handlerList.add(new ClientAuthenticationHandler(
								"Zhaj_seivice@APP-00199",
								"zhaj"));
						return handlerList;
					}
				});	
				TkzxxPort tkzxxPort = tkzxxPortService.getTkzxxPortSoap11();
				GetTkzxxResponse getTkzxxResponse = tkzxxPort.getTkzxx(kzqq);
				System.out.println("特种作业人员扩展信息"+getTkzxxResponse.getStatus());
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	//学历
	public String xl(String xl)
	{
		if(xl != null)
		{
			if(xl.equals("1"))//小学
			{
				return "81";
			}
			else if(xl.equals("2"))//初中
			{
				return "71";
			}
			else if(xl.equals("3"))//中专
			{
				return "41";
			}
			else if(xl.equals("4"))//高中
			{
				return "61";
			}
			else if(xl.equals("5"))//专科
			{
				return "31";
			}
			else if(xl.equals("6"))//本科
			{
				return "21";
			}
			else if(xl.equals("7"))//硕士研究生
			{
				return "14";
			}
			else if(xl.equals("8"))//博士研究生
			{
				return "11";
			}
		}
		return "99";
	}
       
	public String NullToString(String s)
	{
		if(s == null)
		{
			return "";
		}
		else
		{
			return s;
		}
	}
	
	public String gj(String s)
	{
		if(s != null && !"".equals("s"))
		{
			if(s.equals("安道尔"))	return "AD";
			else if(s.equals("阿拉伯联合酋长国"))	return "AE";
			else if(s.equals("阿富汗"))	return "AF";
			else if(s.equals("安提瓜和巴布达"))	return "AG";
			else if(s.equals("安圭拉"))	return "AI";
			else if(s.equals("阿尔巴尼亚"))	return "AL";
			else if(s.equals("亚美尼亚"))	return "AM";
			else if(s.equals("荷属安的列斯群岛"))	return "AN";
			else if(s.equals("安哥拉"))	return "AO";
			else if(s.equals("南极洲"))	return "AQ";
			else if(s.equals("阿根廷"))	return "AR";
			else if(s.equals("奥地利"))	return "AT";
			else if(s.equals("澳大利亚"))	return "AU";
			else if(s.equals("阿鲁巴"))	return "AW";
			else if(s.equals("阿塞拜疆"))	return "AZ";
			else if(s.equals("波黑"))	return "BA";
			else if(s.equals("巴巴多斯"))	return "BB";
			else if(s.equals("孟加拉国"))	return "BD";
			else if(s.equals("比利时"))	return "BE";
			else if(s.equals("布基纳法索"))	return "BF";
			else if(s.equals("保加利亚"))	return "BG";
			else if(s.equals("巴林"))	return "BH";
			else if(s.equals("布隆迪"))	return "BI";
			else if(s.equals("贝宁"))	return "BJ";
			else if(s.equals("百慕大"))	return "BM";
			else if(s.equals("文莱"))	return "BN";
			else if(s.equals("玻利维亚"))	return "BO";
			else if(s.equals("巴西"))	return "BR";
			else if(s.equals("巴哈马"))	return "BS";
			else if(s.equals("不丹"))	return "BT";
			else if(s.equals("缅甸"))	return "BU";
			else if(s.equals("布维岛"))	return "BV";
			else if(s.equals("博茨瓦纳"))	return "BW";
			else if(s.equals("白俄罗斯"))	return "BY";
			else if(s.equals("伯利兹"))	return "BZ";
			else if(s.equals("加拿大"))	return "CA";
			else if(s.equals("科科斯(基林)群岛"))	return "CC";
			else if(s.equals("刚果（金）"))	return "CD";
			else if(s.equals("中非共和国"))	return "CF";
			else if(s.equals("刚果（布）"))	return "CG";
			else if(s.equals("瑞士"))	return "CH";
			else if(s.equals("科特迪瓦"))	return "CI";
			else if(s.equals("库克群岛"))	return "CK";
			else if(s.equals("智利"))	return "CL";
			else if(s.equals("喀麦隆"))	return "CM";
			else if(s.equals("中国"))	return "CN";
			else if(s.equals("哥伦比亚"))	return "CO";
			else if(s.equals("哥斯达黎加"))	return "CR";
			else if(s.equals("捷克斯洛伐克"))	return "CS";
			else if(s.equals("古巴"))	return "CU";
			else if(s.equals("佛得角"))	return "CV";
			else if(s.equals("圣诞岛"))	return "CX";
			else if(s.equals("塞浦路斯"))	return "CY";
			else if(s.equals("联邦德国"))	return "DE";
			else if(s.equals("吉布提"))	return "DJ";
			else if(s.equals("丹麦"))	return "DK";
			else if(s.equals("多米尼克"))	return "DM";
			else if(s.equals("多米尼加共和国"))	return "DO";
			else if(s.equals("阿尔及利亚"))	return "DZ";
			else if(s.equals("厄瓜多尔"))	return "EC";
			else if(s.equals("爱沙尼亚"))	return "EE";
			else if(s.equals("埃及"))	return "EG";
			else if(s.equals("西撒哈拉"))	return "EH";
			else if(s.equals("欧洲专利局"))	return "EP";
			else if(s.equals("厄立特里亚"))	return "ER";
			else if(s.equals("西班牙"))	return "ES";
			else if(s.equals("埃塞俄比亚"))	return "ET";
			else if(s.equals("芬兰"))	return "FI";
			else if(s.equals("斐济"))	return "FJ";
			else if(s.equals("福克兰群岛(马尔维纳斯)"))	return "FK";
			else if(s.equals("密克罗尼西亚联邦"))	return "FM";
			else if(s.equals("法罗群岛"))	return "FO";
			else if(s.equals("法国"))	return "FR";
			else if(s.equals("加蓬"))	return "GA";
			else if(s.equals("英国"))	return "GB";
			else if(s.equals("格林那达"))	return "GD";
			else if(s.equals("格鲁吉亚"))	return "GE";
			else if(s.equals("加纳"))	return "GH";
			else if(s.equals("直布罗陀"))	return "GI";
			else if(s.equals("格陵兰"))	return "GL";
			else if(s.equals("冈比亚"))	return "GM";
			else if(s.equals("几内亚"))	return "GN";
			else if(s.equals("瓜德罗普"))	return "GP";
			else if(s.equals("赤道几内亚"))	return "GQ";
			else if(s.equals("希腊"))	return "GR";
			else if(s.equals("南乔治亚岛和南桑德韦奇岛"))	return "GS";
			else if(s.equals("危地马拉"))	return "GT";
			else if(s.equals("关岛"))	return "GU";
			else if(s.equals("几内亚比绍"))	return "GW";
			else if(s.equals("圭亚那"))	return "GY";
			else if(s.equals("香港"))	return "HK";
			else if(s.equals("赫德岛和麦克唐纳岛"))	return "HM";
			else if(s.equals("洪都拉斯"))	return "HN";
			else if(s.equals("克罗地亚"))	return "HR";
			else if(s.equals("海地"))	return "HT";
			else if(s.equals("匈牙利"))	return "HU";
			else if(s.equals("上沃尔特"))	return "HV";
			else if(s.equals("印度尼西亚"))	return "ID";
			else if(s.equals("爱尔兰"))	return "IE";
			else if(s.equals("以色列"))	return "IL";
			else if(s.equals("印度"))	return "IN";
			else if(s.equals("英属印度洋领土"))	return "IO";
			else if(s.equals("伊拉克"))	return "IQ";
			else if(s.equals("伊朗"))	return "IR";
			else if(s.equals("冰岛"))	return "IS";
			else if(s.equals("意大利"))	return "IT";
			else if(s.equals("泽西岛"))	return "JE";
			else if(s.equals("牙买加"))	return "JM";
			else if(s.equals("约旦"))	return "JO";
			else if(s.equals("日本"))	return "JP";
			else if(s.equals("肯尼亚"))	return "KE";
			else if(s.equals("吉尔吉斯"))	return "KG";
			else if(s.equals("柬埔寨"))	return "KH";
			else if(s.equals("基里巴斯"))	return "KI";
			else if(s.equals("科摩罗"))	return "KM";
			else if(s.equals("圣基茨和尼维斯"))	return "KN";
			else if(s.equals("朝鲜"))	return "KP";
			else if(s.equals("韩国"))	return "KR";
			else if(s.equals("科威特"))	return "KW";
			else if(s.equals("开曼群岛"))	return "KY";
			else if(s.equals("哈萨克"))	return "KZ";
			else if(s.equals("老挝"))	return "LA";
			else if(s.equals("黎巴嫩"))	return "LB";
			else if(s.equals("圣卢西亚岛"))	return "LC";
			else if(s.equals("列支敦士登"))	return "LI";
			else if(s.equals("斯里兰卡"))	return "LK";
			else if(s.equals("利比里亚"))	return "LR";
			else if(s.equals("莱索托"))	return "LS";
			else if(s.equals("立陶宛"))	return "LT";
			else if(s.equals("卢森堡"))	return "LU";
			else if(s.equals("拉脱维亚"))	return "LV";
			else if(s.equals("利比亚"))	return "LY";
			else if(s.equals("摩洛哥"))	return "MA";
			else if(s.equals("摩纳哥"))	return "MC";
			else if(s.equals("摩尔多瓦"))	return "MD";
			else if(s.equals("黑山"))	return "ME";
			else if(s.equals("马达加斯加"))	return "MG";
			else if(s.equals("马绍尔群岛"))	return "MH";
			else if(s.equals("马其顿"))	return "MK";
			else if(s.equals("马里"))	return "ML";
			else if(s.equals("蒙古"))	return "MN";
			else if(s.equals("澳门"))	return "MO";
			else if(s.equals("北马里亚纳"))	return "MP";
			else if(s.equals("马提尼克"))	return "MQ";
			else if(s.equals("毛里塔尼亚"))	return "MR";
			else if(s.equals("蒙特塞拉特岛"))	return "MS";
			else if(s.equals("马耳他"))	return "MT";
			else if(s.equals("毛里求斯"))	return "MU";
			else if(s.equals("马尔代夫"))	return "MV";
			else if(s.equals("马拉维"))	return "MW";
			else if(s.equals("墨西哥"))	return "MX";
			else if(s.equals("马来西亚"))	return "MY";
			else if(s.equals("莫桑比克"))	return "MZ";
			else if(s.equals("纳米比亚"))	return "NA";
			else if(s.equals("新喀里多尼亚"))	return "NC";
			else if(s.equals("尼日尔"))	return "NE";
			else if(s.equals("诺福克岛"))	return "NF";
			else if(s.equals("尼日利亚"))	return "NG";
			else if(s.equals("新赫布里底"))	return "NH";
			else if(s.equals("尼加拉瓜"))	return "NI";
			else if(s.equals("荷兰"))	return "NL";
			else if(s.equals("挪威"))	return "NO";
			else if(s.equals("尼泊尔"))	return "NP";
			else if(s.equals("瑙鲁"))	return "NR";
			else if(s.equals("纽埃"))	return "NU";
			else if(s.equals("新西兰"))	return "NZ";
			else if(s.equals("非洲知识产权组织"))	return "OA";
			else if(s.equals("阿曼"))	return "OM";
			else if(s.equals("巴拿马"))	return "PA";
			else if(s.equals("PCT"))	return "PC";
			else if(s.equals("秘鲁"))	return "PE";
			else if(s.equals("法属波利尼西亚"))	return "PF";
			else if(s.equals("巴布亚新几内亚"))	return "PG";
			else if(s.equals("菲律宾"))	return "PH";
			else if(s.equals("巴基斯坦"))	return "PK";
			else if(s.equals("波兰"))	return "PL";
			else if(s.equals("圣皮埃尔和密克隆"))	return "PM";
			else if(s.equals("皮特凯恩群岛"))	return "PN";
			else if(s.equals("波多黎各"))	return "PR";
			else if(s.equals("巴勒斯坦"))	return "PS";
			else if(s.equals("葡萄牙"))	return "PT";
			else if(s.equals("帕劳"))	return "PW";
			else if(s.equals("巴拉圭"))	return "PY";
			else if(s.equals("卡塔尔"))	return "QA";
			else if(s.equals("留尼汪"))	return "RE";
			else if(s.equals("罗马尼亚"))	return "RO";
			else if(s.equals("塞尔维亚"))	return "RS";
			else if(s.equals("俄罗斯联邦"))	return "RU";
			else if(s.equals("卢旺达"))	return "RW";
			else if(s.equals("沙特阿拉伯"))	return "SA";
			else if(s.equals("所罗门群岛"))	return "SB";
			else if(s.equals("塞舌尔"))	return "SC";
			else if(s.equals("苏丹"))	return "SD";
			else if(s.equals("瑞典"))	return "SE";
			else if(s.equals("新加坡"))	return "SG";
			else if(s.equals("圣赫勒拿岛"))	return "SH";
			else if(s.equals("斯洛文尼亚"))	return "SI";
			else if(s.equals("斯瓦尔巴群岛"))	return "SJ";
			else if(s.equals("塞拉利昂"))	return "SL";
			else if(s.equals("圣马利诺"))	return "SM";
			else if(s.equals("塞内加尔"))	return "SN";
			else if(s.equals("索马里"))	return "SO";
			else if(s.equals("苏里南"))	return "SR";
			else if(s.equals("圣多美和普林西比岛"))	return "ST";
			else if(s.equals("苏联"))	return "SU";
			else if(s.equals("萨尔瓦多"))	return "SV";
			else if(s.equals("叙利亚"))	return "SY";
			else if(s.equals("斯威士兰"))	return "SZ";
			else if(s.equals("特克斯科斯群岛"))	return "TC";
			else if(s.equals("乍得"))	return "TD";
			else if(s.equals("法属南部领土"))	return "TF";
			else if(s.equals("多哥"))	return "TG";
			else if(s.equals("泰国"))	return "TH";
			else if(s.equals("塔吉克斯坦"))	return "TJ";
			else if(s.equals("托克劳"))	return "TK";
			else if(s.equals("东帝汶"))	return "TL";
			else if(s.equals("土库曼"))	return "TM";
			else if(s.equals("突尼斯"))	return "TN";
			else if(s.equals("汤加"))	return "TO";
			else if(s.equals("土耳其"))	return "TR";
			else if(s.equals("特立尼达和多巴哥"))	return "TT";
			else if(s.equals("图瓦卢"))	return "TV";
			else if(s.equals("台湾"))	return "TW";
			else if(s.equals("坦桑尼亚"))	return "TZ";
			else if(s.equals("乌克兰"))	return "UA";
			else if(s.equals("乌干达"))	return "UG";
			else if(s.equals("美国本土外小岛屿"))	return "UM";
			else if(s.equals("美国"))	return "US";
			else if(s.equals("乌拉圭"))	return "UY";
			else if(s.equals("乌兹别克"))	return "UZ";
			else if(s.equals("梵蒂冈"))	return "VA";
			else if(s.equals("圣文森特和格林纳丁斯"))	return "VC";
			else if(s.equals("委内瑞拉"))	return "VE";
			else if(s.equals("维尔京群岛"))	return "VG";
			else if(s.equals("越南"))	return "VN";
			else if(s.equals("瓦努阿图"))	return "VU";
			else if(s.equals("瓦利斯和富图纳"))	return "WF";
			else if(s.equals("世界知识产权组织"))	return "WO";
			else if(s.equals("萨摩亚"))	return "WS";
			else if(s.equals("民主也门"))	return "YD";
			else if(s.equals("也门"))	return "YE";
			else if(s.equals("马约特"))	return "YT";
			else if(s.equals("南斯拉夫"))	return "YU";
			else if(s.equals("南非"))	return "ZA";
			else if(s.equals("赞比亚"))	return "ZM";
			else if(s.equals("扎伊尔"))	return "ZR";
			else if(s.equals("津巴布韦"))	return "ZW";
			else return "ZZ";

		}
		else
		{
			return "";
		}
	}
}
