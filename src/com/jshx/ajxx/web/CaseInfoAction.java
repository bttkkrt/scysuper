package com.jshx.ajxx.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.ajxx.entity.CaseCl;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.entity.CaseZj;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;

public class CaseInfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CaseInfo caseInfo = new CaseInfo();
	
	private InstrumentsInfo instrumentsInfo = new InstrumentsInfo();

	/**
	 * 业务类
	 */
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private UserService userService;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private InstrumentsInfoService instrumentsInfoService;
	@Autowired
	private PhotoPicService photoPicService;

	
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryCaseTimeStart;

	private Date queryCaseTimeEnd;
	
	private List<CheckRecord> checkRecords = new ArrayList<CheckRecord>();
	
	private List<InstrumentsInfo> wsList = new ArrayList<InstrumentsInfo>();
	
	private String type;
	
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	private List<User> userList = new ArrayList<User>();
	
	private List<PhotoPic> picList =  new ArrayList<PhotoPic>();
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	private String loginUserId;
	
	
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	
	private CaseCl caseCl = new CaseCl();
	
	private CaseZj caseZj = new CaseZj();
	
	private List<CaseCl> clList = new ArrayList<CaseCl>();
	
	private List<CaseZj> zjList = new ArrayList<CaseZj>();
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	
	/**
	 * 查询登录人角色
	 * @author luting
	 * 2015-10-21
	 * @return
	 */
	public String init()
	{
		loginUserId = this.getLoginUser().getId();
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		flag = "";
		for(UserRight ur:list)
		{
			flag += ur.getRole().getRoleCode()+ ",";
		}
		return SUCCESS;
	}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != caseInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != caseInfo.getAreaId()) && (0 < caseInfo.getAreaId().trim().length())){
				paraMap.put("areaId",  caseInfo.getAreaId().trim() );
			}

			if ((null != caseInfo.getCompanyName()) && (0 < caseInfo.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + caseInfo.getCompanyName().trim() + "%");
			}

			if ((null != caseInfo.getCaseName()) && (0 < caseInfo.getCaseName().trim().length())){
				paraMap.put("caseName", "%" + caseInfo.getCaseName().trim() + "%");
			}

			if ((null != caseInfo.getCaseSource()) && (0 < caseInfo.getCaseSource().trim().length())){
				paraMap.put("caseSource", caseInfo.getCaseSource().trim());
			}

			if ((null != caseInfo.getCaseStatus()) && (0 < caseInfo.getCaseStatus().trim().length())){
				List<String> ll = new ArrayList<String>();
				if("0".equals(caseInfo.getCaseStatus()))
				{
					flag = "";
					List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
					for(UserRight ur:list)
					{
						flag += ur.getRole().getRoleCode()+ ",";
					}
					String level = "";
					//登录人为安监局领导
					if(flag.contains("A02")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					//登录人为监察大队队长
					if(flag.contains("A09")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					//登录人为大队队员
					if(flag.contains("A10")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					//登录人为法务
					if(flag.contains("A30")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					paraMap.put("level", level);
					paraMap.put("undertakerId", this.getLoginUser().getId());
				}
				else
				{
					paraMap.put("caseStatus", caseInfo.getCaseStatus().trim());
				}
			}


			if (null != queryCaseTimeStart){
				paraMap.put("startCaseTime", queryCaseTimeStart);
			}

			if (null != queryCaseTimeEnd){
				paraMap.put("endCaseTime", queryCaseTimeEnd);
			}
		}
		if(type != null && "1".equals(type))
		{
			paraMap.put("userId", this.getLoginUser().getId());
		}
		
		if(this.getLoginUser().getDeptCode().equals("009"))
		{
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("caseSource", "402880fe506f9d9801506fa93b2e0008");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|caseTime|caseName|caseSource|caseStatus|createUserID|undertakerId|ifNr|wszt|undertakerName|";
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
		pagination = caseInfoService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		try {
			if((null != caseInfo)&&(null != caseInfo.getId()))
			{
				caseInfo = caseInfoService.getById(caseInfo.getId());
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("infoId", caseInfo.getId());
				checkRecords=checkRecordService.findCheckRecord(paraMap);
				paraMap.put("caseId", caseInfo.getId());
				paraMap.put("notin", "35");
				paraMap.put("ifPrint", "1");
				wsList = instrumentsInfoService.findInstrumentsInfos(paraMap);
				clList = caseInfoService.getCaseClList(paraMap);
				for(CaseCl cl:clList)
				{
					Map map = new HashMap();
					map.put("linkId",cl.getLinkId());
					map.put("mkType", "ajxx");
					map.put("picType","ajclfj");
					cl.setPicList(photoPicService.findPicPath(map));//获取执法文书材料
				}
				zjList = caseInfoService.getCaseZjList(paraMap);
			}
			else
			{
				caseInfo.setUndertakerName1(this.getLoginUser().getDisplayName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		Map map = new HashMap();
		map.put("userId", this.getLoginUser().getId());
		userList = caseInfoService.queryCaseUserList(map);
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", caseInfo.getAreaId());
		caseInfo.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		String undertakerName = caseInfo.getUndertakerName1();
		if(caseInfo.getUndertakerId() != null)
		{
			User user = userService.findUserById(caseInfo.getUndertakerId().trim());
			undertakerName += "," + user.getDisplayName();
		}
		caseInfo.setUndertakerName(undertakerName);
		if ("add".equalsIgnoreCase(this.flag)){
			String day[] = sdf.format(caseInfo.getLaTime()).split("-");
			caseInfo.setGlh(day[0]);
			caseInfo.setDeptId(this.getLoginUserDepartmentId());
			caseInfo.setDelFlag(0);
			caseInfo.setCaseStatus("8");
			caseInfoService.save(caseInfo);
			
		}else{
			if(caseInfo.getCaseStatus() != null && "5".equals(caseInfo.getCaseStatus()))
			{
				caseInfo.setCaseStatus("8");
			}
			caseInfoService.update(caseInfo);
			if(type != null && "1".equals(type) )//超级管理员修改，重新生成文书
			{
				m.put("caseId", caseInfo.getId());
				m.put("instrumentType", "100");//立案审批表
				List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
				
				if(list1.size() != 0)
				{
					InstrumentsInfo ins = list1.get(0);
					String root = this.getRequest().getRealPath("/");
					root = root.replaceAll("\\\\", "/");
					Map<String, Object> map=new HashMap<String, Object>();
					
					
					String lsname = ins.getLastFile();
					String filename = ins.getFileName();
					SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String destName = sdf1.format(new Date());
					String newfilename = "立案审批表"+destName+".docx";
					ins.setFileName(newfilename);
					ins.setLastFile(lsname + "," + filename);
					
					ins.setCaseId(caseInfo.getId());
					ins.setCaseName(caseInfo.getCaseName());
					ins.setTime(caseInfo.getCaseTime());
					
					map.put("ajbz", NullToString(ins.getAjbz()));
					map.put("glh", NullToString(ins.getAjh()));
					map.put("glhNum", NullToString(ins.getAjhNum()));
					map.put("caseCause", NullToString(caseInfo.getCaseCause()));
					m.put("codeName", "案件来源");
					m.put("itemValue", caseInfo.getCaseSource());
					map.put("caseSource", codeService.findCodeValueByMap(m).getItemText());
					if(caseInfo.getCaseTime() != null)
					{
						map.put("caseTime", sdf.format(caseInfo.getCaseTime()));
					}
					else
					{
						map.put("caseTime", "");
					}
					map.put("caseName", NullToString(caseInfo.getCaseName()));
					if(caseInfo.getPersonType().equals("1"))
					{
						map.put("person", NullToString(caseInfo.getPerson()));
					}
					else
					{
						map.put("person", NullToString(caseInfo.getCompanyName()));
					}
					map.put("tele", NullToString(caseInfo.getTele()));
					map.put("personCondition", NullToString(caseInfo.getPersonCondition()));
					map.put("personAddress", NullToString(caseInfo.getCompanyAddress()));
					map.put("personCode", NullToString(caseInfo.getZipCode()));
					map.put("caseCondition", NullToString(caseInfo.getCaseCondition()));
					map.put("cbr2zh", "");
//					map.put("cbr2qm", "");
					User uu = userService.findUserById(caseInfo.getCreateUserID());
					map.put("cbr1zh", NullToString(uu.getZfzh()));
//					Map<String,Object> cbr1qm = new HashMap<String, Object>();  
//					if(uu.getFilePath() != null && !"".equals(uu.getFilePath()))
//					{
//						URL url1 = new URL(uu.getFilePath()); 
//						HttpURLConnection conn1 = (HttpURLConnection)url1.openConnection();   
//						cbr1qm.put("content", FileDocUtil.inputStream2ByteArray(conn1.getInputStream(), true));  
//					}
//					map.put("cbr1qm", cbr1qm);
//					Map<String,Object> cbr2qm = new HashMap<String, Object>();  
//					if(caseInfo.getUndertakerId() != null && !"".equals(caseInfo.getUndertakerId()))
//					{
//						User user = userService.findUserById(caseInfo.getUndertakerId());
//						map.put("cbr2zh", NullToString(user.getZfzh()));
//						
//						if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//						{
//							URL url2 = new URL(user.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							cbr2qm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("cbr2qm", cbr2qm);
					map.put("undertakerComment", NullToString(caseInfo.getUndertakerComment()));
					map.put("underTime",changeTimeToZw(caseInfo.getUnderTime()));
					map.put("checkComment", NullToString(caseInfo.getCheckComment()));
					map.put("checkTime", changeTimeToZw(caseInfo.getCheckTime()));
//					Map<String,Object> checkQm = new HashMap<String, Object>();  
//					if(caseInfo.getCheckPersonId() != null && !"".equals(caseInfo.getCheckPersonId()))
//					{
//						User user = userService.findUserById(caseInfo.getCheckPersonId());
//						if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//						{
//							URL url2 = new URL(user.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							checkQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("checkQm", checkQm);
					map.put("approvalComment", NullToString(caseInfo.getApprovalComment()));
					map.put("approvalTime", changeTimeToZw(caseInfo.getApprovalTime()));
//					Map<String,Object> approvalQm = new HashMap<String, Object>();  
//					if(caseInfo.getApprovalId() != null && !"".equals(caseInfo.getApprovalId()))
//					{
//						User user = userService.findUserById(caseInfo.getApprovalId());
//						if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//						{
//							URL url2 = new URL(user.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							approvalQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("approvalQm", approvalQm);
					FileDocUtil fileDocUtil = new FileDocUtil();	
					String[] s = fileDocUtil.createDocFile(root + "立案审批表.docx", ins.getFileName(),root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
					
					ins.setFileSize(s[0]);
					ins.setPageSize(s[1]);
					instrumentsInfoService.update(ins);
				}
			}
		}
		
		//将案件相关文书案件名称更新
		Map mmm = new HashMap();
		mmm.put("caseId", caseInfo.getId());
		mmm.put("caseName", caseInfo.getCaseName());
		instrumentsInfoService.updateAllWsInfoByMap(mmm);
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != caseInfo)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到caseInfo中去
				
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
			caseInfoService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	/**
	 * 调转至审核
	 * @author luting
	 * 2015-10-20
	 * @return
	 * @throws Exception 
	 */
	public String shenhe() throws Exception
	{
		view();
		caseInfo.setUnderTime(caseInfo.getLaTime());
		if(caseInfo.getCaseStatus() != null && "8".equals(caseInfo.getCaseStatus()))
		{
			Map map = new HashMap();
			String day[] = sdf.format(caseInfo.getLaTime()).split("-");
			map.put("fineType", caseInfo.getFineType());
			map.put("glh", day[0]);
			flag = caseInfoService.getGlhNumListByMap(map);
			int glhNum = caseInfoService.getMaxGlhNumByMap(map)+1;
			String ss = glhNum + "";
			if(ss.length() == 1)
			{
				ss = "00" + ss;
			}
			else if(ss.length() == 2)
			{
				ss = "0" + ss;
			}
			caseInfo.setGlhNum(ss);
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 保存审核信息
	 * @author luting
	 * 2015-10-20
	 * @return
	 * @throws Exception 
	 */
	public String shenheSave() throws Exception
	{
		try {
			CaseInfo ca = caseInfoService.getById(caseInfo.getId());
			String result = caseInfo.getResult();
			String status = caseInfo.getCaseStatus();
			String remark = caseInfo.getUndertakerComment();
			Date checkTime = caseInfo.getUnderTime();
			CheckRecord checkrecord = new CheckRecord();
			checkrecord.setInfoId(caseInfo.getId());
			checkrecord.setCheckRemark(remark);
			checkrecord.setCheckTime(checkTime);
			if(status.equals("8"))
			{
				if(result.equals("0"))
				{
					ca.setCaseStatus("7");
					checkrecord.setCheckResult("审核通过");
					ca.setFwcheck("1");
					
					String ss = caseInfo.getGlhNum();
					if(ss.length() == 1)
					{
						ss = "00" + ss;
					}
					else if(ss.length() == 2)
					{
						ss = "0" + ss;
					}
					ca.setGlhNum(ss);
					
					if(ca.getFineType().equals("0"))
					{
						ca.setCaseId("苏园安监违立字〔" + ca.getGlh() + "〕"  + ca.getGlhNum() +  "号");
					}
					else
					{
						ca.setCaseId("苏园安监立字〔" + ca.getGlh() + "〕"  + ca.getGlhNum() +  "号");
					}
				}
				else
				{
					ca.setCaseStatus("5");
					checkrecord.setCheckResult("审核不通过");
				}
			}
			else if(status.equals("7"))
			{
				ca.setUnderTime(checkTime);
				if(result.equals("0"))
				{
					ca.setCaseStatus("0");
					checkrecord.setCheckResult("审核通过");
					ca.setDzqmcheck("1");
					
				}
				else
				{
					ca.setCaseStatus("5");
					checkrecord.setCheckResult("审核不通过");
				}
			}
			else if(status.equals("0"))
			{
				ca.setCheckTime(checkTime);
				ca.setCheckComment(remark);
				ca.setCheckPersonId(this.getLoginUser().getId());
				ca.setCheckPersonName(this.getLoginUser().getDisplayName());
				if(result.equals("0"))
				{
					ca.setCaseStatus("1");
					checkrecord.setCheckResult("审核通过");
					ca.setDzcheck("1");
					
				}
				else
				{
					ca.setCaseStatus("5");
					checkrecord.setCheckResult("审核不通过");
				}
			}
			else
			{
				ca.setApprovalTime(checkTime);
				ca.setApprovalComment(remark);
				ca.setApprovalId(this.getLoginUser().getId());
				ca.setApprovalName(this.getLoginUser().getDisplayName());
				if(result.equals("0"))
				{
					ca.setCaseStatus("2");
					ca.setJzcheck("1");
					checkrecord.setCheckResult("审批通过");
					String root = this.getRequest().getRealPath("/");
					root = root.replaceAll("\\\\", "/");
					Map<String, Object> map=new HashMap<String, Object>();
					
					InstrumentsInfo ins = new InstrumentsInfo();
					ins.setCaseId(ca.getId());
					ins.setCaseName(ca.getCaseName());
					ins.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
					ins.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
					ins.setInstrumentType("100");
					ins.setTime(ca.getCaseTime());
					String fileName = "立案审批表";
					SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String destName = sdf1.format(new Date());
					ins.setIfCheck("0");
					ins.setIfPrint("1");
					ins.setDeptId(this.getLoginUserDepartmentId());
					ins.setFileName(fileName+destName+".docx");
					SimpleDateFormat sdf11 =  new SimpleDateFormat("yyyyMMdd");
					String instrumentsName = fileName + sdf11.format(ins.getTime());
					ins.setInstrumentName(instrumentsName);
					//获取文书号 luting 2015-10-25
					
					if(ca.getFineType().equals("0"))
					{
						ins.setAjbz("苏园安监违立字");
					}
					else
					{
						ins.setAjbz("苏园安监立字");
					}
					ins.setAjh(ca.getGlh());
					ins.setAjhNum(ca.getGlhNum());
					ins.setCompanyName(ca.getCompanyName());
					String wsh = ins.getAjbz() +  "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
					ins.setWsh(wsh);
					map.put("ajbz", NullToString(ins.getAjbz()));
					map.put("glh", NullToString(ins.getAjh()));
					map.put("glhNum", NullToString(ins.getAjhNum()));
					map.put("caseCause", NullToString(ca.getCaseCause()));
					Map m = new HashMap();
					m.put("codeName", "案件来源");
					m.put("itemValue", ca.getCaseSource());
					map.put("caseSource", codeService.findCodeValueByMap(m).getItemText());
					if(ca.getCaseTime() != null)
					{
						map.put("caseTime", sdf.format(ca.getCaseTime()));
					}
					else
					{
						map.put("caseTime", "");
					}
					map.put("caseName", NullToString(ca.getCaseName()));
					if(ca.getPersonType().equals("1"))
					{
						map.put("person", NullToString(ca.getPerson()));
					}
					else
					{
						map.put("person", NullToString(ca.getCompanyName()));
					}
					map.put("tele", NullToString(ca.getTele()));
					map.put("personCondition", NullToString(ca.getPersonCondition()));
					map.put("personAddress", NullToString(ca.getCompanyAddress()));
					map.put("personCode", NullToString(ca.getZipCode()));
					map.put("caseCondition", NullToString(ca.getCaseCondition()));
					map.put("cbr2zh", "");
//					map.put("cbr2qm", "");
					User uu = userService.findUserById(ca.getCreateUserID());
					map.put("cbr1zh", NullToString(uu.getZfzh()));
//					Map<String,Object> cbr1qm = new HashMap<String, Object>();  
//					if(uu.getFilePath() != null && !"".equals(uu.getFilePath()))
//					{
//						URL url1 = new URL(uu.getFilePath()); 
//						HttpURLConnection conn1 = (HttpURLConnection)url1.openConnection();   
//						cbr1qm.put("content", FileDocUtil.inputStream2ByteArray(conn1.getInputStream(), true));  
//					}
//					map.put("cbr1qm", cbr1qm);
//					Map<String,Object> cbr2qm = new HashMap<String, Object>();  
//					if(ca.getUndertakerId() != null && !"".equals(ca.getUndertakerId()))
//					{
//						User user = userService.findUserById(ca.getUndertakerId());
//						map.put("cbr2zh", NullToString(user.getZfzh()));
//						
//						if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//						{
//							URL url2 = new URL(user.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							cbr2qm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("cbr2qm", cbr2qm);
					map.put("undertakerComment", NullToString(ca.getUndertakerComment()));
					map.put("underTime",changeTimeToZw(ca.getUnderTime()));
					map.put("checkComment", NullToString(ca.getCheckComment()));
					map.put("checkTime", changeTimeToZw(ca.getCheckTime()));
//					Map<String,Object> checkQm = new HashMap<String, Object>();  
//					if(ca.getCheckPersonId() != null && !"".equals(ca.getCheckPersonId()))
//					{
//						User user = userService.findUserById(ca.getCheckPersonId());
//						if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//						{
//							URL url2 = new URL(user.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							checkQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("checkQm", checkQm);
					map.put("approvalComment", NullToString(ca.getApprovalComment()));
					map.put("approvalTime", changeTimeToZw(ca.getApprovalTime()));
//					Map<String,Object> approvalQm = new HashMap<String, Object>();  
//					if(ca.getApprovalId() != null && !"".equals(ca.getApprovalId()))
//					{
//						User user = userService.findUserById(ca.getApprovalId());
//						if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//						{
//							URL url2 = new URL(user.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							approvalQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("approvalQm", approvalQm);
					FileDocUtil fileDocUtil = new FileDocUtil();	
					String[] s = fileDocUtil.createDocFile(root + "立案审批表.docx", ins.getFileName(),root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
					
					ins.setNeedCheck("2");
					ins.setDzqmCheck("2");
					ins.setDzCheck("2");
					ins.setJzCheck("2");
					ins.setDelFlag(0);
					ins.setFileSize(s[0]);
					ins.setPageSize(s[1]);
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					ins.setLinkId(linkId);
					instrumentsInfoService.save(ins);
				}
				else
				{
					ca.setCaseStatus("5");
					checkrecord.setCheckResult("审批不通过");
				}
			}
			caseInfoService.update(ca);
			checkrecord.setCheckUserid(this.getLoginUser().getId());
			checkrecord.setCheckUsername(this.getLoginUser().getDisplayName());
			checkrecord.setDelFlag(0);
			checkRecordService.save(checkrecord);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}
	
	/**
	 * 将null值转换为空字符串
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 */
	public String NullToString(String object)
	{
		String s = "";
		if(object != null)
		{
			s = object;
		}
		return s;
	}
	
	/**
	 * 跳转至归档页面
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 * @throws IOException 
	 */
	public String guidnag() throws IOException
	{
		if(caseInfo != null && caseInfo.getId() != null && !"".equals(caseInfo.getId()))
		{
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("caseId", caseInfo.getId());
			paraMap.put("notin", "35");
			wsList = instrumentsInfoService.findInstrumentsInfos(paraMap);
		}
		caseInfo.setBcqx("永久");
		return SUCCESS;
	}
	
	/***
     * <b>function:</b> 将数字转化为大写
     * @author luting
     * 2015-10-30
     * @param num 数字
     * @return 转换后的大写数字
     */
	public static String numToUpper(int num) {
		 String[] str = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		 String ss[] = new String[] { "", "十", "百", "千", "万", "十", "百", "千", "亿" };
		 String s = String.valueOf(num);
		 StringBuffer sb = new StringBuffer();
		 for (int i = 0; i < s.length(); i++) {
            String index = String.valueOf(s.charAt(i));
            sb = sb.append(str[Integer.parseInt(index)]);
		 }
		 String sss = String.valueOf(sb);
		 int i = 0;
		 for (int j = sss.length(); j > 0; j--) {
			 sb = sb.insert(j, ss[i++]);
		 }
		 return sb.toString();
    }
	/**
	 * 保存归档信息
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 */
	public String guidnagSave() throws Exception
	{
		try {
			CaseInfo ca = caseInfoService.getById(caseInfo.getId());
			ca.setCaseStatus("4");
			String bcqx = caseInfo.getBcqx();
			Date gdTime = caseInfo.getGdTime();
			ca.setGdNum(new SimpleDateFormat("yyyyMMddHHmmss").format(gdTime));
			ca.setBcqx(bcqx);
			ca.setGdTime(gdTime);
			ca.setGdhttpurl(SysPropertiesUtil.getProperty("httpurl"));
			ca.setGdnwurl(SysPropertiesUtil.getProperty("nwurl"));
			
			if(ca.getFineType().equals("0"))
			{
				ca.setGajbz("苏园安监违案字");
			}
			else
			{
				ca.setGajbz("苏园安监案字");
			}
			Map m = new HashMap();
			String root = this.getRequest().getRealPath("/");
			root = root.replaceAll("\\\\", "/");
			
			FileDocUtil fileDocUtil = new FileDocUtil();	
			
			//生成证据列表
			SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String destName = sdf1.format(new Date());
			
			Map map1 = new HashMap();
			Map mmm = new HashMap();
			mmm.put("caseId", ca.getId());
			//生成证据列表
			List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
			zjList = caseInfoService.getCaseZjList(mmm);
			for(int i=0;i<zjList.size();i++)
			{
				CaseZj zjlb = zjList.get(i);
				Map<String, Object> mm = new HashMap<String, Object>();
				int index = i+1;
				mm.put("wpmc", index+"."+NullToString(zjlb.getZjContent()));
				newList.add(mm);
			}
			map1.put("zjList", newList);
			if(ca.getPersonType().equals("1"))
			{
				map1.put("companyName", NullToString(ca.getPerson())+"行政处罚案（个人）证据清单及证明内容");
			}
			else
			{
				map1.put("companyName", NullToString(ca.getCompanyName())+"行政处罚案（单位）证据清单及证明内容");
				
			}
			map1.put("time", changeTimeToZw(gdTime));
			fileDocUtil.createDocFile(root+"证据清单及证明内容.docx", "证据清单及证明内容"+destName+".docx", root+"../../virtualdir/file/"+ca.getCaseName(), map1).split(",");
			
			//生成照片
			Map map3 = new HashMap();
			List<Map<String, Object>> picList = new ArrayList<Map<String, Object>>();
			mmm.put("zjType", "1");
			clList = caseInfoService.getCaseClList(mmm);
			int num=1;
			for(CaseCl cl:clList)
			{
				Map ss = new HashMap();
				ss.put("linkId",cl.getLinkId());
				ss.put("mkType", "ajxx");
				ss.put("picType","ajclfj");
				List<PhotoPic> list = photoPicService.findPicPath(ss);
				Map<String, Object> mm = new HashMap<String, Object>();
				mm.put("picName", "照片" + numToUpper(num));
				mm.put("picTime", changeTimeToZw(cl.getPicTime()));
				mm.put("picAdd", 	NullToString(cl.getPicAdd()));
				mm.put("picContent", NullToString(cl.getPicContent()));
				for(PhotoPic photo:list)
				{
					Map<String,Object> header = new HashMap<String, Object>();  
					int idx = photo.getPicName().lastIndexOf('.');
				    header.put("type", photo.getPicName().substring(idx));  
				    URL url = new URL(photo.getNwUrl()+"/upload/photo/"+photo.getPicName()); 
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
					conn.setRequestMethod("GET"); 
		            conn.setConnectTimeout(6000); 
					InputStream in = conn.getInputStream();
					byte[] buf = new byte[1024];  
					int size = 0;  
					File file = new File(root+"../../virtualdir/upload/photo/xz");
					if(!file.exists())
					{
						file.mkdir();
					}
					FileOutputStream out = new FileOutputStream(root+"../../virtualdir/upload/photo/xz/"+photo.getPicName());
					while ((size = in.read(buf)) != -1) {  
						out.write(buf, 0, size);  
					} 
					out.flush();
					out.close();  
			        in.close();  
				    header.put("content", FileDocUtil.inputStream2ByteArray(new FileInputStream(root+"../../virtualdir/upload/photo/xz/"+photo.getPicName()), true));  
				    mm.put("pic",header);  
				    picList.add(mm);
					num ++;
				}
			}
			map3.put("picList", picList);
			fileDocUtil.createDocFile(root+"照片.docx", "照片"+destName+".docx", root+"../../virtualdir/file/"+ca.getCaseName(), map3).split(",");

			
			//生成案卷（首页）
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("ajbz", NullToString(ca.getGajbz()));
			map.put("gah", NullToString(ca.getGlh()));
			map.put("gahNum", NullToString(ca.getGlhNum()));
			map.put("caseName", NullToString(ca.getCaseName()));
			map.put("caseCause", NullToString(ca.getCaseCause()));
			map.put("approvalResult", NullToString(ca.getApprovalResult()));
			map.put("laTime", changeTimeToZw(ca.getLaTime()));
			map.put("jaTime", changeTimeToZw(ca.getJaTime()));
			map.put("zfry1", "");
			map.put("zfry2", "");
			map.put("zfry1", NullToString(userService.findUserById(ca.getCreateUserID()).getDisplayName()));
			if(ca.getUndertakerId() != null && !"".equals(ca.getUndertakerId()))
			{
				User user = userService.findUserById(ca.getUndertakerId());
				map.put("zfry2", NullToString(user.getDisplayName()));
			}
			map.put("gdTime", changeTimeToZw(ca.getGdTime()));
			map.put("gdNum", NullToString(ca.getGdNum()));
			map.put("bcqx", NullToString(ca.getBcqx()));
			String[] s = fileDocUtil.createDocFile(root+"案卷（首页）.docx","案卷（首页）.docx", root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
			ca.setSySize(s[0]); 
			
			//生成封面
			fileDocUtil.createDocFile(root+"封面.docx","封面.docx", root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
			
			
			if(instrumentsInfo.getId() != null && !"".equals(instrumentsInfo.getId()))
			{
				String[] wsids = instrumentsInfo.getId().replaceAll(" ", "").split(",");
				for(int i=0;i<wsids.length;i++)
				{
					InstrumentsInfo ins = instrumentsInfoService.getById(wsids[i].trim());
					String sort = i + "";
					if(sort.length() == 1)
					{
						sort = "00" + sort;
					}
					else if(sort.length() == 2)
					{
						sort = "0" + sort;
					}
					ins.setSort(sort);
					instrumentsInfoService.update(ins);
				}
			}
			
			//生成卷内目录
			Map<String, Object> map2=new HashMap<String, Object>();
			m.put("caseId", ca.getId());
			List<InstrumentsInfo> wslist = instrumentsInfoService.findInstrumentsInfos(m);
			
			List<Map<String, Object>> newList2 = new ArrayList<Map<String, Object>>();
			int page = 1;
			for(int i=0;i<wslist.size();i++)
			{
				InstrumentsInfo in = wslist.get(i);
				if(in.getPageSize() != null && !"".equals(in.getPageSize()))
				{
					Map<String, Object> mm2 = new HashMap<String, Object>();
					m.put("codeName", "文书类型");
					m.put("itemValue", in.getInstrumentType());
					String fileName = codeService.findCodeValueByMap(m).getItemText();
					int index = i+1;
					mm2.put("xh", index + "");
					if(in.getWsh() != null && !"".equals(in.getWsh()))
					{
						mm2.put("wjmc",fileName+ "(" + in.getWsh() + ")" );
					}
					else
					{
						mm2.put("wjmc",fileName);
					}
					mm2.put("ys", page);
					page += Integer.parseInt(in.getPageSize());
					mm2.put("time", changeTimeToZw(in.getTime()));
					newList2.add(mm2);
				}
			}
			map2.put("mlList", newList2);
			String[] sss = fileDocUtil.createDocFile(root+"卷内目录.docx","卷内目录.docx", root+"../../virtualdir/file/"+ca.getCaseName(), map2).split(",");
			ca.setJnmuSize(sss[0]);
			caseInfoService.update(ca);
			
			//将案件相关文书置为不可回执
			mmm.put("ifCheck", "7");
			instrumentsInfoService.updateAllWsInfoByMap(mmm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return RELOAD;
	}
	
	/**
	 * 下载文书
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 */
	public void downloadAllFile()
	{
		try
  		{
  			CaseInfo ca = caseInfoService.getById(caseInfo.getId());
  			String urls = "";
  			String fileName = "";
  			if(flag.equals("1"))//案卷（首页）
  			{
  				urls = ca.getGdnwurl() +"/file/" + URLEncoder.encode(ca.getCaseName(), "utf-8")+"/"+URLEncoder.encode("案卷（首页）.docx", "utf-8"); 
  				fileName = "案卷（首页）.docx";
  			}
  			else if(flag.equals("2"))//卷内目录
  			{
  				urls = ca.getGdnwurl() +"/file/" + URLEncoder.encode(ca.getCaseName(), "utf-8")+"/"+URLEncoder.encode("卷内目录.docx", "utf-8"); 
  				fileName = "卷内目录.docx";
  			}
  			else if(flag.equals("3"))//封面
  			{
  				urls = ca.getGdnwurl() +"/file/" + URLEncoder.encode(ca.getCaseName(), "utf-8")+"/"+URLEncoder.encode("封面.docx", "utf-8"); 
  				fileName = "封面.docx";
  			}
  			else if(flag.equals("4"))//照片
  			{
  				urls = ca.getGdnwurl() +"/file/" + URLEncoder.encode(ca.getCaseName(), "utf-8")+"/"+URLEncoder.encode("照片.docx", "utf-8"); 
  				fileName = "照片.docx";
  			}
  			else if(flag.equals("5"))//证据清单及证明内容
  			{
  				urls = ca.getGdnwurl() +"/file/" + URLEncoder.encode(ca.getCaseName(), "utf-8")+"/"+URLEncoder.encode("证据清单及证明内容.docx", "utf-8"); 
  				fileName = "证据清单及证明内容.docx";
  			}
  			URL url = new URL(urls); 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
			InputStream in = conn.getInputStream();

	        String browName = new String();
	        browName = URLEncoder.encode(fileName, "UTF-8");
	        String clientInfo = getRequest().getHeader("User-agent");
	        if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
	          if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
	            browName = new String(fileName.getBytes("GBK"), "ISO-8859-1");
	        }
	        Struts2Util.getResponse()
	          .addHeader(
	          "Content-Disposition", 
	          "attachment;filename=" + 
	          browName);
	        OutputStream out = Struts2Util.getResponse().getOutputStream();
        try {
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) != -1)
          {
            out.write(buf, 0, len);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          in.close();
          out.close();
        }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
	}
	
	/**
	 * 调转至审核
	 * @author luting
	 * 2015-10-20
	 * @return
	 * @throws Exception 
	 */
	public String shenheAll() throws Exception
	{
		return SUCCESS;
	}
	
	
	/**
	 * 保存审核信息
	 * @author luting
	 * 2015-10-20
	 * @return
	 * @throws Exception 
	 */
	public String shenheAllSave() throws Exception
	{
		String result = caseInfo.getResult();
		String remark = caseInfo.getUndertakerComment();
		String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
				if(id!=null && !id.trim().equals(""))
				{
					CaseInfo ca = caseInfoService.getById(id);
					Date time = ca.getCaseTime();
					String status = ca.getCaseStatus();
					CheckRecord checkrecord = new CheckRecord();
					checkrecord.setInfoId(caseInfo.getId());
					checkrecord.setCheckRemark(remark);
					if(status.equals("8"))
					{
						if(result.equals("0"))
						{
							ca.setCaseStatus("7");
							checkrecord.setCheckResult("审核通过");
							ca.setFwcheck("1");
						}
						else
						{
							ca.setCaseStatus("5");
							checkrecord.setCheckResult("审核不通过");
						}
					}
					else if(status.equals("7"))
					{
						ca.setUnderTime(time);
						if(result.equals("0"))
						{
							ca.setCaseStatus("0");
							checkrecord.setCheckResult("审核通过");
							ca.setDzqmcheck("1");
						}
						else
						{
							ca.setCaseStatus("5");
							checkrecord.setCheckResult("审核不通过");
						}
					}
					else if(status.equals("0"))
					{
						ca.setCheckComment(remark);
						ca.setCheckPersonId(this.getLoginUser().getId());
						ca.setCheckPersonName(this.getLoginUser().getDisplayName());
						ca.setCheckTime(time);
						if(result.equals("0"))
						{
							ca.setCaseStatus("1");
							checkrecord.setCheckResult("审核通过");
							ca.setDzcheck("1");
						}
						else
						{
							ca.setCaseStatus("5");
							checkrecord.setCheckResult("审核不通过");
						}
					}
					else
					{
						ca.setApprovalComment(remark);
						ca.setApprovalId(this.getLoginUser().getId());
						ca.setApprovalName(this.getLoginUser().getDisplayName());
						ca.setApprovalTime(time);
						if(result.equals("0"))
						{
							Map m = new HashMap();
							ca.setCaseStatus("2");
							ca.setJzcheck("1");
							checkrecord.setCheckResult("审批通过");
							String root = this.getRequest().getRealPath("/");
							root = root.replaceAll("\\\\", "/");
							Map<String, Object> map=new HashMap<String, Object>();
							
							InstrumentsInfo ins = new InstrumentsInfo();
							ins.setCaseId(ca.getId());
							ins.setCaseName(ca.getCaseName());
							ins.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
							ins.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
							ins.setInstrumentType("100");
							ins.setTime(ca.getCaseTime());
							String fileName = "立案审批表";
							SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
							String destName = sdf1.format(new Date());
							ins.setIfCheck("0");
							ins.setIfPrint("1");
							ins.setDeptId(this.getLoginUserDepartmentId());
							ins.setFileName(fileName+destName+".docx");
							SimpleDateFormat sdf11 =  new SimpleDateFormat("yyyyMMdd");
							String instrumentsName = fileName + sdf11.format(ins.getTime());
							ins.setInstrumentName(instrumentsName);
							//获取文书号 luting 2015-10-25
							
							if(ca.getFineType().equals("0"))
							{
								ins.setAjbz("苏园安监违立字");
							}
							else
							{
								ins.setAjbz("苏园安监立字");
							}
							ins.setAjh(ca.getGlh());
							ins.setAjhNum(ca.getGlhNum());
							ins.setCompanyName(ca.getCompanyName());
							String wsh = ins.getAjbz() +  "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
							ins.setWsh(wsh);
							map.put("ajbz", NullToString(ins.getAjbz()));
							map.put("glh", NullToString(ins.getAjh()));
							map.put("glhNum", NullToString(ins.getAjhNum()));
							map.put("caseCause", NullToString(ca.getCaseCause()));
							m.put("codeName", "案件来源");
							m.put("itemValue", ca.getCaseSource());
							map.put("caseSource", codeService.findCodeValueByMap(m).getItemText());
							if(ca.getCaseTime() != null)
							{
								map.put("caseTime", sdf.format(ca.getCaseTime()));
							}
							else
							{
								map.put("caseTime", "");
							}
							map.put("caseName", NullToString(ca.getCaseName()));
							if(ca.getPersonType().equals("1"))
							{
								map.put("person", NullToString(ca.getPerson()));
							}
							else
							{
								map.put("person", NullToString(ca.getCompanyName()));
							}
							map.put("tele", NullToString(ca.getTele()));
							map.put("personCondition", NullToString(ca.getPersonCondition()));
							map.put("personAddress", NullToString(ca.getCompanyAddress()));
							map.put("personCode", NullToString(ca.getZipCode()));
							map.put("caseCondition", NullToString(ca.getCaseCondition()));
							map.put("cbr2zh", "");
//							map.put("cbr2qm", "");
							User uu = userService.findUserById(ca.getCreateUserID());
							map.put("cbr1zh", NullToString(uu.getZfzh()));
//							Map<String,Object> cbr1qm = new HashMap<String, Object>();  
//							if(uu.getFilePath() != null && !"".equals(uu.getFilePath()))
//							{
//								URL url1 = new URL(uu.getFilePath()); 
//								HttpURLConnection conn1 = (HttpURLConnection)url1.openConnection();   
//								cbr1qm.put("content", FileDocUtil.inputStream2ByteArray(conn1.getInputStream(), true));  
//							}
//							map.put("cbr1qm", cbr1qm);
//							Map<String,Object> cbr2qm = new HashMap<String, Object>();  
//							if(ca.getUndertakerId() != null && !"".equals(ca.getUndertakerId()))
//							{
//								User user = userService.findUserById(ca.getUndertakerId());
//								map.put("cbr2zh", NullToString(user.getZfzh()));
//								
//								if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//								{
//									URL url2 = new URL(user.getFilePath()); 
//									HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//									cbr2qm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//								}
//							}
//							map.put("cbr2qm", cbr2qm);
							map.put("undertakerComment", NullToString(ca.getUndertakerComment()));
							map.put("underTime",changeTimeToZw(ca.getUnderTime()));
							map.put("checkComment", NullToString(ca.getCheckComment()));
							map.put("checkTime", changeTimeToZw(ca.getCheckTime()));
//							Map<String,Object> checkQm = new HashMap<String, Object>();  
//							if(ca.getCheckPersonId() != null && !"".equals(ca.getCheckPersonId()))
//							{
//								User user = userService.findUserById(ca.getCheckPersonId());
//								if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//								{
//									URL url2 = new URL(user.getFilePath()); 
//									HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//									checkQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//								}
//							}
//							map.put("checkQm", checkQm);
							map.put("approvalComment", NullToString(ca.getApprovalComment()));
							map.put("approvalTime", changeTimeToZw(ca.getApprovalTime()));
//							Map<String,Object> approvalQm = new HashMap<String, Object>();  
//							if(ca.getApprovalId() != null && !"".equals(ca.getApprovalId()))
//							{
//								User user = userService.findUserById(ca.getApprovalId());
//								if(user.getFilePath() != null && !"".equals(user.getFilePath()))
//								{
//									URL url2 = new URL(user.getFilePath()); 
//									HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//									approvalQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//								}
//							}
//							map.put("approvalQm", approvalQm);
							FileDocUtil fileDocUtil = new FileDocUtil();	
							String[] s = fileDocUtil.createDocFile(root + "立案审批表.docx", ins.getFileName(),root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
							
							ins.setNeedCheck("2");
							ins.setDzqmCheck("2");
							ins.setDzCheck("2");
							ins.setJzCheck("2");
							ins.setDelFlag(0);
							ins.setFileSize(s[0]);
							ins.setPageSize(s[1]);
							String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
							ins.setLinkId(linkId);
							instrumentsInfoService.save(ins);
						}
						else
						{
							ca.setCaseStatus("5");
							checkrecord.setCheckResult("审批不通过");
						}
					}
					checkrecord.setCheckUserid(this.getLoginUser().getId());
					checkrecord.setCheckUsername(this.getLoginUser().getDisplayName());
					checkrecord.setCheckTime(time);
					checkrecord.setDelFlag(0);
					caseInfoService.update(ca);
					checkRecordService.save(checkrecord);
				}
			}
		}
		return RELOAD;
	}
	
	public String initUploadFile()
	{
		caseCl.setCaseId(caseInfo.getId());
		loginUserId = this.getLoginUser().getId();
		return SUCCESS;
	}
	
	public void uploadFileList()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != caseCl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != caseCl.getCaseId()) && (0 < caseCl.getCaseId().trim().length())){
				paraMap.put("caseId",  caseCl.getCaseId().trim() );
			}
			if ((null != caseCl.getZjType()) && (0 < caseCl.getZjType().trim().length())){
				paraMap.put("zjType",  caseCl.getZjType().trim() );
			}

		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|zjType|createUserID|";
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
		pagination = caseInfoService.findCaseClByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	
	
	public void zwtfilelist()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != caseCl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != caseCl.getCaseId()) && (0 < caseCl.getCaseId().trim().length())){
				paraMap.put("caseId",  caseCl.getCaseId().trim() );
			}
			if ((null != caseCl.getZjType()) && (0 < caseCl.getZjType().trim().length())){
				paraMap.put("zjType",  caseCl.getZjType().trim() );
			}

		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|zjType|createUserID|";
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
		pagination = caseInfoService.findCaseClByPage(pagination, paraMap);
		
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
	
	/**
	 * 查看详细信息
	 */
	public String uploadFileView() throws Exception{
		try {
			if((null != caseCl)&&(null != caseCl.getId()))
			{
				caseCl = caseInfoService.getCaseClById(caseCl.getId());
				if(caseCl.getLinkId() != null && !"".equals(caseCl.getLinkId()))
				{
					Map map = new HashMap();
					map.put("linkId",caseCl.getLinkId());
					map.put("mkType", "ajxx");
					map.put("picType","ajclfj");
					picList = photoPicService.findPicPath(map);//获取执法文书材料
				}
				else
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					caseCl.setLinkId(linkId);
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				caseCl.setLinkId(linkId);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String uploadFileInitEdit() throws Exception{
		uploadFileView();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String saveUploadFile() throws Exception{
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		if ("add".equalsIgnoreCase(this.flag)){
			caseCl.setDeptId(this.getLoginUserDepartmentId());
			caseCl.setDelFlag(0);
			caseInfoService.saveCaseCl(caseCl);
		}else{
			caseInfoService.updateCaseCl(caseCl);
		}
		return RELOAD;
	}
	
	/**
	 * 删除信息
	 */
	public String deleteUploadFile() throws Exception{
	    try{
			caseInfoService.deleteCaseClWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	public String initUploadZjlb()
	{
		caseZj.setCaseId(caseInfo.getId());
		loginUserId = this.getLoginUser().getId();
		return SUCCESS;
	}
	
	public void uploadZjlbList()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != caseZj){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != caseZj.getCaseId()) && (0 < caseZj.getCaseId().trim().length())){
				paraMap.put("caseId",  caseZj.getCaseId().trim() );
			}
			if ((null != caseZj.getZjContent()) && (0 < caseZj.getZjContent().trim().length())){
				paraMap.put("zjContent", "%" +  caseZj.getZjContent().trim()+ "%" );
			}

		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|zjContent|createUserID|";
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
		pagination = caseInfoService.findCaseZjByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}
	
	public void zwtzjlblist()
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != caseZj){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != caseZj.getCaseId()) && (0 < caseZj.getCaseId().trim().length())){
				paraMap.put("caseId",  caseZj.getCaseId().trim() );
			}
			if ((null != caseZj.getZjContent()) && (0 < caseZj.getZjContent().trim().length())){
				paraMap.put("zjContent", "%" +  caseZj.getZjContent().trim()+ "%" );
			}

		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|zjContent|createUserID|";
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
		pagination = caseInfoService.findCaseZjByPage(pagination, paraMap);
		
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
	
	/**
	 * 查看详细信息
	 */
	public String uploadZjlbView() throws Exception{
		try {
			if((null != caseZj)&&(null != caseZj.getId()))
			{
				caseZj = caseInfoService.getCaseZjById(caseZj.getId());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String uploadZjlbInitEdit() throws Exception{
		uploadZjlbView();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String saveUploadZjlb() throws Exception{
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		if ("add".equalsIgnoreCase(this.flag)){
			caseZj.setDeptId(this.getLoginUserDepartmentId());
			caseZj.setDelFlag(0);
			caseInfoService.saveCaseZj(caseZj);
		}else{
			caseInfoService.updateCaseZj(caseZj);
		}
		return RELOAD;
	}
	
	/**
	 * 删除信息
	 */
	public String deleteUploadZjlb() throws Exception{
	    try{
			caseInfoService.deleteCaseZjWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String ajgl()
	{
		loginUserId = this.getLoginUser().getId();
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		flag = "";
		for(UserRight ur:list)
		{
			flag += ur.getRole().getRoleCode()+ ",";
		}
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != caseInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != caseInfo.getAreaId()) && (0 < caseInfo.getAreaId().trim().length())){
				paraMap.put("areaId",  caseInfo.getAreaId().trim() );
			}

			if ((null != caseInfo.getCompanyName()) && (0 < caseInfo.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + caseInfo.getCompanyName().trim() + "%");
			}

			if ((null != caseInfo.getCaseName()) && (0 < caseInfo.getCaseName().trim().length())){
				paraMap.put("caseName", "%" + caseInfo.getCaseName().trim() + "%");
			}

			if ((null != caseInfo.getCaseSource()) && (0 < caseInfo.getCaseSource().trim().length())){
				paraMap.put("caseSource", caseInfo.getCaseSource().trim());
			}

			if ((null != caseInfo.getCaseStatus()) && (0 < caseInfo.getCaseStatus().trim().length())){
				List<String> ll = new ArrayList<String>();
				if("0".equals(caseInfo.getCaseStatus()))
				{
					flag = "";
					List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
					for(UserRight ur:list)
					{
						flag += ur.getRole().getRoleCode()+ ",";
					}
					String level = "";
					//登录人为安监局领导
					if(flag.contains("A02")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					//登录人为监察大队队长
					if(flag.contains("A09")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					//登录人为大队队员
					if(flag.contains("A10")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					//登录人为法务
					if(flag.contains("A30")) 
					{
						level += "1";
					}
					else
					{
						level += "0";
					}
					paraMap.put("level", level);
					paraMap.put("undertakerId", this.getLoginUser().getId());
				}
				else
				{
					paraMap.put("caseStatus", caseInfo.getCaseStatus().trim());
				}
			}


			if (null != queryCaseTimeStart){
				paraMap.put("startCaseTime", queryCaseTimeStart);
			}

			if (null != queryCaseTimeEnd){
				paraMap.put("endCaseTime", queryCaseTimeEnd);
			}
		}
		if(type != null && "1".equals(type))
		{
			paraMap.put("userId", this.getLoginUser().getId());
		}
		
		if(this.getLoginUser().getDeptCode().equals("009"))
		{
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			paraMap.put("companyId", entBaseInfo.getId());
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("caseSource", "402880fe506f9d9801506fa93b2e0008");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|areaName|companyName|caseTime|caseName|caseSource|caseStatus|createUserID|undertakerId|ifNr|wszt|undertakerName|";
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
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或案件名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = caseInfoService.findByPage(pagination, paraMap);
		
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

	public CaseInfo getCaseInfo(){
		return this.caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo){
		this.caseInfo = caseInfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryCaseTimeStart(){
		return this.queryCaseTimeStart;
	}

	public void setQueryCaseTimeStart(Date queryCaseTimeStart){
		this.queryCaseTimeStart = queryCaseTimeStart;
	}

	public Date getQueryCaseTimeEnd(){
		return this.queryCaseTimeEnd;
	}

	public void setQueryCaseTimeEnd(Date queryCaseTimeEnd){
		this.queryCaseTimeEnd = queryCaseTimeEnd;
	}
	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}
	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<InstrumentsInfo> getWsList() {
		return wsList;
	}
	public void setWsList(List<InstrumentsInfo> wsList) {
		this.wsList = wsList;
	}
	public InstrumentsInfo getInstrumentsInfo() {
		return instrumentsInfo;
	}
	public void setInstrumentsInfo(InstrumentsInfo instrumentsInfo) {
		this.instrumentsInfo = instrumentsInfo;
	}
	
	public List<PhotoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
	public String changeTimeToZw(Date d)
	{
		String time = "";
		if(d != null)
		{
			String day[] = sdf.format(d).split("-");
			time = day[0] + "年" + day[1] + "月" + day[2] + "日";
		}
		return time;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public CaseCl getCaseCl() {
		return caseCl;
	}
	public void setCaseCl(CaseCl caseCl) {
		this.caseCl = caseCl;
	}
	public CaseZj getCaseZj() {
		return caseZj;
	}
	public void setCaseZj(CaseZj caseZj) {
		this.caseZj = caseZj;
	}
	public List<CaseCl> getClList() {
		return clList;
	}
	public void setClList(List<CaseCl> clList) {
		this.clList = clList;
	}
	public List<CaseZj> getZjList() {
		return zjList;
	}
	public void setZjList(List<CaseZj> zjList) {
		this.zjList = zjList;
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
	
	

}
