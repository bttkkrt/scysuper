package com.jshx.http.command;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.bean.SummaryBean;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;

/**
 * 案件审核接口
 * @author 陆婷 2015-11-5
 *
 */
public class SaveCaseInfoShenheCommand implements Command{
	private CaseInfoService caseInfoService = (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private UserService userService = (UserService) SpringContextHolder.getBean("userService");
	private EntBaseInfoService entBaseInfoService = (EntBaseInfoService) SpringContextHolder.getBean("entBaseInfoService");
	private CodeService codeService = (CodeService) SpringContextHolder.getBean("codeService");
	private InstrumentsInfoService instrumentsInfoService = (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//案件编号
		String result = obj.getString("result");//审核结果（'0':'通过','1':'不通过'）
		String remark = obj.getString("remark");//备注
		String checkTime = obj.getString("time");//审核时间
	
		User user = userService.findUserById(userId);
		try {
			Date time = sdf.parse(checkTime);
			CaseInfo ca = caseInfoService.getById(id);
			String status = ca.getCaseStatus();
			CheckRecord checkrecord = new CheckRecord();
			checkrecord.setInfoId(id);
			checkrecord.setCheckRemark(remark);
			checkrecord.setCheckTime(time);
			if(status.equals("8"))
			{
				if(result.equals("0"))
				{
					ca.setCaseStatus("7");
					checkrecord.setCheckResult("审核通过");
					ca.setFwcheck("1");
					
					
					String ss = obj.getString("caseId");//使用案号
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
				ca.setCheckPersonId(user.getId());
				ca.setCheckPersonName(user.getDisplayName());
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
				ca.setApprovalId(user.getId());
				ca.setApprovalName(user.getDisplayName());
				ca.setApprovalTime(time);
				if(result.equals("0"))
				{
					ca.setCaseStatus("2");
					ca.setJzcheck("1");
					checkrecord.setCheckResult("审批通过");
					String root = this.getClass().getResource("/").getPath();
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
					ins.setDeptId(user.getDept().getId());
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
					String wsh = ins.getAjbz() +  "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
					ins.setWsh(wsh);
					ins.setCompanyName(ca.getCompanyName());
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
//						User u = userService.findUserById(ca.getUndertakerId());
//						map.put("cbr2zh", NullToString(u.getZfzh()));
//						
//						if(u.getFilePath() != null && !"".equals(u.getFilePath()))
//						{
//							URL url2 = new URL(u.getFilePath()); 
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
//						User u = userService.findUserById(ca.getCheckPersonId());
//						if(u.getFilePath() != null && !"".equals(u.getFilePath()))
//						{
//							URL url2 = new URL(u.getFilePath()); 
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
//						User u = userService.findUserById(ca.getApprovalId());
//						if(u.getFilePath() != null && !"".equals(u.getFilePath()))
//						{
//							URL url2 = new URL(u.getFilePath()); 
//							HttpURLConnection conn2 = (HttpURLConnection)url2.openConnection();   
//							approvalQm.put("content", FileDocUtil.inputStream2ByteArray(conn2.getInputStream(), true));  
//						}
//					}
//					map.put("approvalQm", approvalQm);
//					WordUtil wordUtil = new WordUtil();
//					long size = wordUtil.createWord("立案审批表.ftl", "立案审批表.doc", root+"../../../../virtualdir/file/"+ca.getCaseName(), map);
					FileDocUtil fileDocUtil = new FileDocUtil();	
					String[] s = fileDocUtil.createDocFile(root+"../../立案审批表.docx", ins.getFileName(), root+"../../../../virtualdir/file/"+ca.getCaseName(), map).split(",");
					
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
			checkrecord.setCheckUserid(user.getId());
			checkrecord.setCheckUsername(user.getDisplayName());

			checkrecord.setDelFlag(0);
			caseInfoService.update(ca);
			checkRecordService.save(checkrecord);
			br.setCode("0");
			br.setMessage("成功");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
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
}
