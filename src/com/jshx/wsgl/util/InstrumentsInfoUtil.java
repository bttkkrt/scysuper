package com.jshx.wsgl.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jshx.ajclcpb.entity.CaseProcessApproval;
import com.jshx.ajclcpb.service.CaseProcessApprovalService;
import com.jshx.ajsp.entity.CloseApproval;
import com.jshx.ajsp.service.CloseApprovalService;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.ajyss.entity.CaseTransfer;
import com.jshx.ajyss.service.CaseTransferService;
import com.jshx.ajyssp.entity.CaseRefer;
import com.jshx.ajyssp.service.CaseReferService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.cyqzpz.entity.SamplingEvidence;
import com.jshx.cyqzpz.service.SamplingEvidenceService;
import com.jshx.cyzjglb.entity.SamplingAssociate;
import com.jshx.cyzjglb.service.SamplingAssociateService;
import com.jshx.dcbg.entity.Dcbg;
import com.jshx.dcbg.service.DcbgService;
import com.jshx.dsrcssbbl.entity.PartyStateNote;
import com.jshx.dsrcssbbl.service.PartyStateNoteService;
import com.jshx.inventorycheck.entity.InventoryCheck;
import com.jshx.inventorycheck.service.InventoryCheckService;
import com.jshx.inwentorydecision.entity.InventoryDecision;
import com.jshx.inwentorydecision.service.InventoryDecisionService;
import com.jshx.jdwpglb.entity.IdentifyItemAssociate;
import com.jshx.jdwpglb.service.IdentifyItemAssociateService;
import com.jshx.jdwts.entity.IdentifyAttorney;
import com.jshx.jdwts.service.IdentifyAttorneyService;
import com.jshx.kcbl.entity.InquestRecord;
import com.jshx.kcbl.service.InquestRecordService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qzcsjds.entity.EnforenceDecision;
import com.jshx.qzcsjds.service.EnforenceDecisionService;
import com.jshx.qzzxsqs.entity.EnfApp;
import com.jshx.qzzxsqs.service.EnfAppService;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.tssxspb.entity.SpecialItem;
import com.jshx.tssxspb.service.SpecialItemService;
import com.jshx.tzbl.entity.HearingNote;
import com.jshx.tzbl.service.HearingNoteService;
import com.jshx.tzgzs.entity.HearingTell;
import com.jshx.tzgzs.service.HearingTellService;
import com.jshx.tzhbgs.entity.HearingReport;
import com.jshx.tzhbgs.service.HearingReportService;
import com.jshx.tztzs.entity.HearingNotice;
import com.jshx.tztzs.service.HearingNoticeService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;
import com.jshx.xcclcsjds.entity.LiveActionDecision;
import com.jshx.xcclcsjds.service.LiveActionDecisionService;
import com.jshx.xcjcjl.entity.SiteCheckRecord;
import com.jshx.xcjcjl.service.SiteCheckRecordService;
import com.jshx.xwbl.entity.InqRecRecord;
import com.jshx.xwbl.entity.InquiryRecord;
import com.jshx.xwbl.service.InquiryRecordService;
import com.jshx.xwtzs.entity.InquiryNotice;
import com.jshx.xwtzs.service.InquiryNoticeService;
import com.jshx.xxdjbczjqdglb.entity.InventoryAssociate;
import com.jshx.xxdjbczjqdglb.service.InventoryAssociateService;
import com.jshx.xxdjbczjtzs.entity.NoticeEvidence;
import com.jshx.xxdjbczjtzs.service.NoticeEvidenceService;
import com.jshx.xxdjzjspb.entity.PreserveEvidence;
import com.jshx.xxdjzjspb.service.PreserveEvidenceService;
import com.jshx.xzcfgzs.entity.PenaltyNotice;
import com.jshx.xzcfgzs.service.PenaltyNoticeService;
import com.jshx.xzcfjdsdw.entity.PenDecCom;
import com.jshx.xzcfjdsdw.service.PenDecComService;
import com.jshx.xzcfjdsgr.entity.PenDecPer;
import com.jshx.xzcfjdsgr.service.PenDecPerService;
import com.jshx.xzcfjttljl.entity.PenBraRec;
import com.jshx.xzcfjttljl.service.PenBraRecService;
import com.jshx.xzdccfjdgrs.entity.SpoPenDecPer;
import com.jshx.xzdccfjdgrs.service.SpoPenDecPerService;
import com.jshx.xzdccfjdsdw.entity.SpoPenDecCom;
import com.jshx.xzdccfjdsdw.service.SpoPenDecComService;
import com.jshx.yqjnfkpzs.entity.PosFinRat;
import com.jshx.yqjnfkpzs.service.PosFinRatService;
import com.jshx.yqjnfkspb.entity.PosFinApp;
import com.jshx.yqjnfkspb.service.PosFinAppService;
import com.jshx.zfyj.entity.LawBasis;
import com.jshx.zfyj.service.LawBasisService;
import com.jshx.zgfcyjs.entity.ReviewSubmission;
import com.jshx.zgfcyjs.service.ReviewSubmissionService;
import com.jshx.zlqxzgzls.entity.OrderDeadlineBook;
import com.jshx.zlqxzgzls.service.OrderDeadlineBookService;

public class InstrumentsInfoUtil{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private CaseInfoService caseInfoService= (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	
	
	private UserService userService= (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	//询问通知书
	private InquiryNoticeService inquiryNoticeService = (InquiryNoticeService) SpringContextHolder.getBean("inquiryNoticeService");
	//询问笔录
	private InquiryRecordService inquiryRecordService = (InquiryRecordService) SpringContextHolder.getBean("inquiryRecordService");
	//勘验笔录
	private InquestRecordService inquestRecordService = (InquestRecordService) SpringContextHolder.getBean("inquestRecordService");
	//抽样取证凭证
	private SamplingEvidenceService samplingEvidenceService = (SamplingEvidenceService) SpringContextHolder.getBean("samplingEvidenceService");
	//抽样取样证据关联表
	private SamplingAssociateService samplingAssociateService = (SamplingAssociateService) SpringContextHolder.getBean("samplingAssociateService");
	//先行登记保存证据审批表
	private PreserveEvidenceService preserveEvidenceService = (PreserveEvidenceService) SpringContextHolder.getBean("preserveEvidenceService");
	private CheckRecordService checkRecordService = (CheckRecordService) SpringContextHolder.getBean("checkRecordService");
	private InventoryAssociateService inventoryAssociateService = (InventoryAssociateService) SpringContextHolder.getBean("inventoryAssociateService");
	
	
	//先行登记保存证据通知书
	private NoticeEvidenceService noticeEvidenceService = (NoticeEvidenceService) SpringContextHolder.getBean("noticeEvidenceService");
	
	//先行登记保存证据处理审批表
	private InventoryCheckService inventoryCheckService = (InventoryCheckService) SpringContextHolder.getBean("inventoryCheckService");
	
	//先行登记保存证据处理决定书 
	private InventoryDecisionService inventoryDecisionService = (InventoryDecisionService) SpringContextHolder.getBean("inventoryDecisionService");
	//现场检查记录
	private SiteCheckRecordService siteCheckRecordService = (SiteCheckRecordService) SpringContextHolder.getBean("siteCheckRecordService");
	//现场处理措施决定书
	private LiveActionDecisionService liveActionDecisionService = (LiveActionDecisionService) SpringContextHolder.getBean("liveActionDecisionService");
	private LawBasisService lawBasisService = (LawBasisService) SpringContextHolder.getBean("lawBasisService");
	//责令限期整改指令书
	private OrderDeadlineBookService orderDeadlineBookService = (OrderDeadlineBookService) SpringContextHolder.getBean("orderDeadlineBookService");
	//整改复查意见书
	private ReviewSubmissionService reviewSubmissionService = (ReviewSubmissionService) SpringContextHolder.getBean("reviewSubmissionService");
	//强制措施决定书
	private EnforenceDecisionService enforenceDecisionService = (EnforenceDecisionService) SpringContextHolder.getBean("enforenceDecisionService");
	//鉴定委托书
	private IdentifyAttorneyService identifyAttorneyService = (IdentifyAttorneyService) SpringContextHolder.getBean("identifyAttorneyService");
	private IdentifyItemAssociateService identifyItemAssociateService = (IdentifyItemAssociateService) SpringContextHolder.getBean("identifyItemAssociateService");
	
	//行政处罚告知书
	private PenaltyNoticeService penaltyNoticeService = (PenaltyNoticeService) SpringContextHolder.getBean("penaltyNoticeService");
	//当事人陈述申辩笔录
	private PartyStateNoteService partyStateNoteService = (PartyStateNoteService) SpringContextHolder.getBean("partyStateNoteService");
	//听证告知书
	private HearingTellService hearingTellService = (HearingTellService) SpringContextHolder.getBean("hearingTellService");
	//听证会通知书
	private HearingNoticeService hearingNoticeService = (HearingNoticeService) SpringContextHolder.getBean("hearingNoticeService");
	//听证笔录
	private HearingNoteService hearingNoteService = (HearingNoteService) SpringContextHolder.getBean("hearingNoteService");
	//听证会报告书
	private HearingReportService hearingReportService = (HearingReportService) SpringContextHolder.getBean("hearingReportService");
	//案件处理呈批表
	private CaseProcessApprovalService caseProcessApprovalService = (CaseProcessApprovalService) SpringContextHolder.getBean("caseProcessApprovalService");
	//行政处罚集体讨论记录
	private PenBraRecService penBraRecService = (PenBraRecService) SpringContextHolder.getBean("penBraRecService");
	//行政（当场）处罚决定书（单位）
	private SpoPenDecComService spoPenDecComService = (SpoPenDecComService) SpringContextHolder.getBean("spoPenDecComService");
	//行政（当场）处罚决定书（个人）
	private SpoPenDecPerService spoPenDecPerService = (SpoPenDecPerService) SpringContextHolder.getBean("spoPenDecPerService");
	//行政处罚决定书（单位）
	private PenDecComService penDecComService = (PenDecComService) SpringContextHolder.getBean("penDecComService");
	//行政处罚决定书（个人）
	private PenDecPerService penDecPerService = (PenDecPerService) SpringContextHolder.getBean("penDecPerService");
	//延期（分期）缴纳罚款审批表
	private PosFinAppService posFinAppService = (PosFinAppService) SpringContextHolder.getBean("posFinAppService");
	//延期（分期）缴纳罚款批准书
	private PosFinRatService posFinRatService = (PosFinRatService) SpringContextHolder.getBean("posFinRatService");
	//强制执行申请书
	private EnfAppService enfAppService = (EnfAppService) SpringContextHolder.getBean("enfAppService");
	//结案审批表
	private CloseApprovalService closeApprovalService = (CloseApprovalService) SpringContextHolder.getBean("closeApprovalService");
	//案件移送审批表
	private CaseReferService caseReferService = (CaseReferService) SpringContextHolder.getBean("caseReferService");
	//案件移送书
	private CaseTransferService caseTransferService = (CaseTransferService) SpringContextHolder.getBean("caseTransferService");
	
	//调查报告
	private DcbgService dcbgService = (DcbgService) SpringContextHolder.getBean("dcbgService");
	
	//特殊事项审批表
	private SpecialItemService specialItemService = (SpecialItemService) SpringContextHolder.getBean("specialItemService");
	
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	
	Map<String, Object> map=new HashMap<String, Object>();
	
	FileDocUtil fileDocUtil = new FileDocUtil();
	
	/**
	 * 保存文书信息
	 * @throws IOException 
	 */
	public InstrumentsInfo saveInstrumentsInfo(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo,String root,String type) throws IOException
	{
		String wstype = instrumentsInfo.getInstrumentType();
		Map m = new HashMap();
		m.put("codeName", "文书类型");
		m.put("itemValue", wstype);
		String fileName = codeService.findCodeValueByMap(m).getItemText();
		SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyyMMdd");
		String instrumentsName = fileName + sdf2.format(instrumentsInfo.getTime());
		instrumentsInfo.setInstrumentName(instrumentsName);
		//保存服务器IP及端口 luting 2015-10-25
		instrumentsInfo.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
		instrumentsInfo.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
		//验证文书是否需审核 luting 2015-10-25
		//保存文书信息 luting 2015-10-25
		instrumentsInfo.setCaseName(caseInfo.getCaseName());
		instrumentsInfo.setCompanyName(caseInfo.getCompanyName());
		SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String destName = sdf1.format(new Date());
		
		instrumentsInfo.setFileName(fileName+destName+".docx");
		instrumentsInfo.setAjh(caseInfo.getGlh());
		
		if(instrumentsInfo.getLinkId() == null || "".equals(instrumentsInfo.getLinkId()))
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			instrumentsInfo.setLinkId(linkId);
		}
		
		instrumentsInfoService.save(instrumentsInfo);
		
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", wstype);
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list.size() == 0)
		{
			instrumentsInfo.setAjhNum(caseInfo.getGlhNum());
		}
		else
		{
			//更新第一个
			InstrumentsInfo ins = list.get(0);
			if(!ins.getWsh().contains("-"))
			{
				ins.setAjhNum(caseInfo.getGlhNum()+"-1");
				if(ins.getWsh() != null && !"".equals(ins.getWsh()))
				{
					ins.setWsh(ins.getWsh().replace("号", "-1号"));
				}
				instrumentsInfoService.update(ins);
				m.put("relatedId", ins.getId());
				if(wstype.equals("1"))//询问通知书
				{
					InquiryNotice inq = (InquiryNotice) inquiryNoticeService.findInquiryNotice(m).get(0);
					createInquiryNotice(ins, caseInfo, inq, root);
				}
				else if(wstype.equals("3"))//勘验笔录
				{
					InquestRecord inq = (InquestRecord) inquestRecordService.findInquestRecord(m).get(0);
					createInquestRecord(ins, caseInfo, inq, root);
				}
				else if(wstype.equals("4"))//抽样取证凭证
				{
					SamplingEvidence sam= (SamplingEvidence) samplingEvidenceService.findSamplingEvidence(m).get(0);
					createSamplingEvidence(ins, caseInfo, sam, root);
				}
				else if(wstype.equals("5"))//先行登记保存证据审批表
				{
					PreserveEvidence pe= (PreserveEvidence) preserveEvidenceService.findPreserveEvidence(m).get(0);
					createPreserveEvidence(ins, caseInfo, pe, root);
				}
				else if(wstype.equals("6"))//先行登记保存证据通知书
				{
					NoticeEvidence not= (NoticeEvidence) noticeEvidenceService.findNoticeEvidence(m).get(0);
					createNoticeEvidence(ins, caseInfo, not, root);
				}
				else if(wstype.equals("7"))//先行登记保存证据处理审批表
				{
					InventoryCheck inv= (InventoryCheck) inventoryCheckService.findInventoryCheck(m).get(0);
					createInventoryCheck(ins, caseInfo, inv, root);
				}
				else if(wstype.equals("8"))//先行登记保存证据处理决定书 
				{
					InventoryDecision inv= (InventoryDecision) inventoryDecisionService.findInventoryDecision(m).get(0);
					createInventoryDecision(ins, caseInfo, inv, root);
				}
				else if(wstype.equals("9"))//现场检查记录
				{
					SiteCheckRecord sit= (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(m).get(0);
					createSiteCheckRecord(ins, caseInfo, sit, root);
				}
				else if(wstype.equals("10"))//现场处理措施决定书
				{
					LiveActionDecision liv= (LiveActionDecision) liveActionDecisionService.findLiveActionDecision(m).get(0);
					createLiveActionDecision(ins, caseInfo, liv, root);
				}
				else if(wstype.equals("11"))//责令限期整改指令书
				{
					OrderDeadlineBook ord= (OrderDeadlineBook) orderDeadlineBookService.findOrderDeadlineBook(m).get(0);
					createOrderDeadlineBook(ins, caseInfo, ord, root);
				}
				else if(wstype.equals("12"))//整改复查意见书
				{
					ReviewSubmission rev= (ReviewSubmission) reviewSubmissionService.findReviewSubmission(m).get(0);
					createReviewSubmission(ins, caseInfo, rev, root);
				}
				else if(wstype.equals("13"))//强制措施决定书
				{
					EnforenceDecision enf= (EnforenceDecision) enforenceDecisionService.findEnforenceDecision(m).get(0);
					createEnforenceDecision(ins, caseInfo, enf, root);
				}
				else if(wstype.equals("14"))//鉴定委托书
				{ 
					IdentifyAttorney ide= (IdentifyAttorney) identifyAttorneyService.findIdentifyAttorney(m).get(0);
					createIdentifyAttorney(ins, caseInfo, ide, root);
				}
				else if(wstype.equals("15"))//行政处罚告知书
				{
					PenaltyNotice pen= (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					createPenaltyNotice(ins, caseInfo, pen, root);
				}
				else if(wstype.equals("16"))//当事人陈述申辩笔录
				{
					PartyStateNote par= (PartyStateNote) partyStateNoteService.findPartyStateNote(m).get(0);
					createPartyStateNote(ins, caseInfo, par, root);
				}
				else if(wstype.equals("17"))//听证告知书
				{
					HearingTell hea= (HearingTell) hearingTellService.findHearingTell(m).get(0);
					createHearingTell(ins, caseInfo, hea, root);
				}
				else if(wstype.equals("18"))//听证会通知书
				{
					HearingNotice hea= (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
					createHearingNotice(ins, caseInfo, hea, root);
				}
				else if(wstype.equals("19"))//听证笔录
				{
					HearingNote hea= (HearingNote) hearingNoteService.findHearingNote(m).get(0);
					createHearingNote(ins, caseInfo, hea, root);
				}
				else if(wstype.equals("20"))//听证会报告书
				{
					HearingReport hea= (HearingReport) hearingReportService.findHearingReport(m).get(0);
					createHearingReport(ins, caseInfo, hea, root);
				}
				else if(wstype.equals("21"))//案件处理呈批表
				{
					CaseProcessApproval cas= (CaseProcessApproval) caseProcessApprovalService.findCaseProcessApproval(m).get(0);
					createCaseProcessApproval(ins, caseInfo, cas, root);
				}
				else if(wstype.equals("22"))//行政处罚集体讨论记录
				{
					PenBraRec pen= (PenBraRec) penBraRecService.findPenBraRec(m).get(0);
					createPenBraRec(ins, caseInfo, pen, root);
				}
				else if(wstype.equals("23"))//行政（当场）处罚决定书（单位）
				{
					SpoPenDecCom spo= (SpoPenDecCom) spoPenDecComService.findSpoPenDecCom(m).get(0);
					createSpoPenDecCom(ins, caseInfo, spo, root);
				}
				else if(wstype.equals("24"))//行政（当场）处罚决定书（个人）
				{
					SpoPenDecPer spo= (SpoPenDecPer) spoPenDecPerService.findSpoPenDecPer(m).get(0);
					createSpoPenDecPer(ins, caseInfo, spo, root);
				}
				else if(wstype.equals("25"))//行政处罚决定书（单位）
				{
					PenDecCom pen = (PenDecCom) penDecComService.findPenDecCom(m).get(0);
					createPenDecCom(ins, caseInfo, pen, root);
				}
				else if(wstype.equals("26"))//行政处罚决定书（个人）
				{
					PenDecPer pen= (PenDecPer) penDecPerService.findPenDecPer(m).get(0);
					createPenDecPer(ins, caseInfo, pen, root);
				}
				else if(wstype.equals("27"))//罚款催缴通知书
				{
					createFkcjd(ins, caseInfo, root);
				}
				else if(wstype.equals("28"))//延期（分期）缴纳罚款审批表
				{
					PosFinApp pos= (PosFinApp) posFinAppService.findPosFinApp(m).get(0);
					createPosFinApp(ins, caseInfo, pos, root);
				}
				else if(wstype.equals("29"))//延期（分期）缴纳罚款批准书
				{
					PosFinRat pos= (PosFinRat) posFinRatService.findPosFinRat(m).get(0);
					createPosFinRat(ins, caseInfo, pos, root);
				}
				else if(wstype.equals("30"))//强制执行申请书
				{
					EnfApp ent= (EnfApp) enfAppService.findEnfApp(m).get(0);
					createEnfApp(ins, caseInfo, ent, root);
				}
				else if(wstype.equals("31"))//结案审批表
				{
					CloseApproval clo = (CloseApproval) closeApprovalService.findCloseApproval(m).get(0);
					createCloseApproval(ins, caseInfo, clo, root);
				}
				else if(wstype.equals("32"))//案件移送审批表
				{
					CaseRefer cas= (CaseRefer) caseReferService.findCaseRefer(m).get(0);
					createCaseRefer(ins, caseInfo, cas, root);
				}
				else if(wstype.equals("33"))//案件移送书
				{
					CaseTransfer cas= (CaseTransfer) caseTransferService.findCaseTransfer(m).get(0);
					createCaseTransfer(ins, caseInfo, cas, root);
				}
				else if(wstype.equals("34"))//调查报告
				{
					Dcbg dc = (Dcbg) dcbgService.findDcbg(m).get(0);
					createDcbg(ins, caseInfo, dc, root);
				}
			}
			//设置文书号
			int num = list.size() + 1;
			instrumentsInfo.setAjhNum(caseInfo.getGlhNum()+"-"+num);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 保存询问通知书
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public InstrumentsInfo saveInquiryNotice(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo,InquiryNotice inquiryNotice,String flag,String type,String root) throws IOException
	{
		if(inquiryNotice.getSfz() == null || "".equals(inquiryNotice.getSfz()))
		{
			inquiryNotice.setSfz("0");
		}
		if(inquiryNotice.getYyzz() == null || "".equals(inquiryNotice.getYyzz()))
		{
			inquiryNotice.setYyzz("0");
		}
		if(inquiryNotice.getFddbzm() == null || "".equals(inquiryNotice.getFddbzm()))
		{
			inquiryNotice.setFddbzm("0");
		}
		if(inquiryNotice.getDocMaterial() != null && !"".equals(inquiryNotice.getDocMaterial()))
		{
			inquiryNotice.setQt("1");
		}
		if(inquiryNotice.getQt() == null || "".equals(inquiryNotice.getQt()))
		{
			inquiryNotice.setQt("0");
		}
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违管询");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监管询");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		inquiryNotice.setRelatedId(instrumentsInfo.getId());
		inquiryNotice.setDelFlag(0);
		inquiryNotice.setCreateUserID(instrumentsInfo.getCreateUserID());
		inquiryNotice.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			inquiryNoticeService.save(inquiryNotice);
		}else{
			inquiryNoticeService.update(inquiryNotice);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createInquiryNotice(instrumentsInfo,caseInfo,inquiryNotice,root);
		}
		instrumentsInfo.setInstrumentName(instrumentsInfo.getInstrumentName()+inquiryNotice.getAskPerson());
		return instrumentsInfo;
	}
	
	/**
	 * 审核询问通知书 法务审核
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheInquiryNotice(InstrumentsInfo in,CaseInfo ca,InquiryNotice inquiryNotice,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			in.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书
			createInquiryNotice(in,ca,inquiryNotice,root);
			
			in.setIfPrint("1");
			in.setNeedCheck("2");
		}
		else
		{
			in.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(in);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成询问通知书
	 * @author luting
	 * 2016-3-10
	 * @throws IOException 
	 */
	public void createInquiryNotice(InstrumentsInfo in,CaseInfo ca,InquiryNotice inquiryNotice,String root) throws IOException
	{
		//生成文书	
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("xwh", NullToString(in.getAjh()));
		map.put("xwhNum", NullToString(in.getAjhNum()));
		map.put("person", NullToString(inquiryNotice.getAskPerson()));
		map.put("caseName", NullToString(ca.getCaseName()));
		map.put("inquiryTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH");
		if(inquiryNotice.getInquiryTime() != null)
		{
			String day[] = sdf.format(inquiryNotice.getInquiryTime()).split("-");
			String time = day[0] + "年" + day[1] + "月" + day[2] + "日" + day[3] + "时";
			map.put("inquiryTime", time);
		}
		map.put("inquiryAddress", NullToString(inquiryNotice.getInquiryAddress()));
		map.put("docMaterial", NullToDown(inquiryNotice.getDocMaterial()));
		map.put("time", changeTimeToZw(in.getTime()));
		
		String fileName = inquiryNotice.getSfz() + inquiryNotice.getYyzz()+ inquiryNotice.getFddbzm() + inquiryNotice.getQt();
		String[] s = fileDocUtil.createDocFile(root+"询问通知书" + fileName + ".docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
		instrumentsInfoService.update(in);
		
	}
	
	/**
	 * 保存询问笔录
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveInquiryRecord(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo,InquiryRecord inquiryRecord,InqRecRecord inqRecRecord,String flag,String type,String root) throws IOException
	{
		instrumentsInfo.setNeedCheckUser(","+inquiryRecord.getInquiryPerson().replaceAll(" ", "")+",");
		inquiryRecord.setInquiryPersonName(userService.findUserById(inquiryRecord.getInquiryPerson().trim()).getDisplayName());
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		inquiryRecord.setDelFlag(0);
		inquiryRecord.setRelatedId(instrumentsInfo.getId());
		inquiryRecord.setCreateUserID(instrumentsInfo.getCreateUserID());
		inquiryRecord.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			inquiryRecordService.save(inquiryRecord);
		}else{
			inquiryRecordService.update(inquiryRecord);
		}
		if(inqRecRecord.getAskRecord() != null && !"".equals(inqRecRecord.getAskRecord().replaceAll(" ", "")))
		{
			String[] glid = {};
			if(inqRecRecord.getId() != null)
			{
				glid = inqRecRecord.getId().replaceAll(" ", "").split(",");
			}
			String[] wt = inqRecRecord.getAskRecord().replaceAll(" ", "").split(",");
			String[] hd= inqRecRecord.getRecRecord().replaceAll(" ", "").split(",");
			for(int i=0;i<wt.length;i++)
			{
				if(glid != null && i<glid.length)
				{
					InqRecRecord ss = inquiryRecordService.getInqRecRecordById(glid[i]);
					ss.setAskRecord(NullToStr(wt,i));
					ss.setRecRecord(NullToStr(hd,i));
					inquiryRecordService.updateInqRecRecord(ss);
				}
				else
				{
					InqRecRecord ss = new InqRecRecord();
					ss.setAskRecord(NullToStr(wt,i));
					ss.setRecRecord(NullToStr(hd,i));
					ss.setDelFlag(0);
					ss.setRelatedId(instrumentsInfo.getId());
					inquiryRecordService.saveInqRecRecord(ss);
				}
			}
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createInquiryRecord(instrumentsInfo,caseInfo,inquiryRecord,root);
		}
		instrumentsInfo.setInstrumentName(instrumentsInfo.getInstrumentName()+inquiryRecord.getAskedPerson() + inquiryRecord.getXwcs());
		return instrumentsInfo;
	}
	
	/**
	 * 审核询问笔录 法务审核-》电子签名确认
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheInquiryRecord(InstrumentsInfo in,CaseInfo ca,InquiryRecord inq,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else  if(status.equals("3"))//电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				createInquiryRecord(in,ca,inq,root);
				
				in.setIfPrint("1");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(in);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
		
		inquiryRecordService.update(inq);
	}
	
	/**
	 * 生成询问笔录 
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createInquiryRecord(InstrumentsInfo in,CaseInfo ca,InquiryRecord inq,String root) throws IOException
	{
				
		//生成文书	
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		Map m = new HashMap();
		m.put("relatedId", in.getId());
		List<InqRecRecord> blList = inquiryRecordService.findInqRecRecord(m);
		for(int i=0;i<blList.size();i++)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			InqRecRecord ss = blList.get(i);
			mm.put("wt","问：" + NullToString(ss.getAskRecord()));
			mm.put("hd","答：" + NullToString(ss.getRecRecord()));
			newList.add(mm);
		}
		
		map.put("inquiryPeriod", "");
		map.put("endTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(inq.getInquiryPeriod() != null)
		{
			String[] day1 = sdf.format(inq.getInquiryPeriod()).split("-");
			map.put("inquiryPeriod", day1[0] + "年" + day1[1] + "月" + day1[2] + "日" + day1[3] + "时" + day1[4] + "分");
		}
		if(inq.getEndTime() != null)
		{
			String[] day2 = sdf.format(inq.getEndTime()).split("-");
			map.put("endTime", day2[2] + "日" + day2[3] + "时" + day2[4] + "分");
		}
		map.put("xwcs",NullToString(inq.getXwcs()));
		
		map.put("inquiryAddress", NullToString(inq.getInquiryAddress()));
		map.put("askedPerson", NullToString(inq.getAskedPerson()));
		if(inq.getSex() != null && !"".equals(inq.getSex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", inq.getSex());
			map.put("sex", NullToString(codeService.findCodeValueByMap(m).getItemText()));
		}
		else
		{
			map.put("sex", "");
		}
		map.put("peopleAge", NullToString(inq.getPeopleAge()));
		map.put("cardId", NullToString(inq.getCardId()));
		map.put("companyName", NullToString(inq.getCompanyName()));
		map.put("position", NullToString(inq.getPosition()));
		map.put("address", NullToString(inq.getAddress()));
		map.put("tele", NullToString(inq.getTele()));
		map.put("inquiryPerson","");
		map.put("inquiryPersonZh", "________");
		map.put("xwrzw","");
		if(inq.getInquiryPerson() != null && !"".equals(inq.getInquiryPerson()))
		{
			User user1 = userService.findUserById(inq.getInquiryPerson());
			if(user1 != null)
			{
				map.put("inquiryPerson",NullToString(user1.getDisplayName()) );
				map.put("inquiryPersonZh", NullToDown(user1.getZfzh()));
				map.put("xwrzw","苏州工业园区安全生产监察大队");
			}
		}
		
		map.put("recordPerson","");
		map.put("recordPersonZh","________" );
		map.put("jlrzw", "");
		
		if(inq.getRecordPerson() != null && !"".equals(inq.getRecordPerson()))
		{
			User user1 = userService.findUserById(inq.getRecordPerson());
			if(user1 != null)
			{
				map.put("recordPerson",NullToString(user1.getDisplayName()) );
				map.put("recordPersonZh",NullToDown(user1.getZfzh()) );
				map.put("jlrzw","苏州工业园区安全生产监察大队");
			}
		}
		
		
			
		map.put("presentPeople", NullToString(inq.getPresentPeople()));
		map.put("caseName", NullToString(ca.getCaseName()));
		map.put("blList", newList);
		map.put("time", changeTimeToZw(in.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"询问笔录.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存勘验笔录
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveInquestRecord(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,InquestRecord inquestRecord, String flag,String type,String root) throws IOException
	{
		String undertakerName = inquestRecord.getRecordPersonName();
		if(inquestRecord.getInquestPerson() != null)
		{
			User user = userService.findUserById(inquestRecord.getInquestPerson().trim());
			undertakerName += "," + user.getDisplayName();
		}
		inquestRecord.setInquestPersonName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+inquestRecord.getInquestPerson().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监管勘");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监管勘");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		inquestRecord.setRelatedId(instrumentsInfo.getId());
		inquestRecord.setDelFlag(0);
		inquestRecord.setCreateUserID(instrumentsInfo.getCreateUserID());
		inquestRecord.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			inquestRecordService.save(inquestRecord);
		}else{
			inquestRecordService.update(inquestRecord);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createInquestRecord(instrumentsInfo, caseInfo, inquestRecord, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核勘验笔录 法务审核-》电子签名确认
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheInquestRecord(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,InquestRecord inquestRecord,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String userId = user.getId();
		String needCheck = instrumentsInfo.getNeedCheckUser();
		
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3"))//电子签名确认
		{
			if(needCheck.equals("," + userId + ","))
			{
				if(result.equals("0"))//审核通过
				{
					instrumentsInfo.setIfCheck("5");
					checkrecord.setCheckResult("审核通过");
					
					//生成文书	
					createInquestRecord(instrumentsInfo, caseInfo, inquestRecord, root);
					
					instrumentsInfo.setIfPrint("1");
					instrumentsInfo.setDzqmCheck("2");
				}
				else
				{
					instrumentsInfo.setIfCheck("4");
					checkrecord.setCheckResult("审核不通过");
				}
			}
			else
			{
				if(result.equals("0"))//审核通过
				{
					needCheck = needCheck.replace(userId+ ",", "");
					instrumentsInfo.setNeedCheckUser(needCheck);
					instrumentsInfo.setIfCheck("3");
					checkrecord.setCheckResult("审核通过");
					
				}
				else
				{
					instrumentsInfo.setIfCheck("4");
					checkrecord.setCheckResult("审核不通过");
				}
			}
		}
		
		instrumentsInfoService.update(instrumentsInfo);
		
		inquestRecordService.update(inquestRecord);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成勘验笔录
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createInquestRecord(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,InquestRecord inquestRecord,String root) throws IOException
	{
					
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("kyh", NullToString(instrumentsInfo.getAjh()));
		map.put("kyhNum", NullToString(instrumentsInfo.getAjhNum()));
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(inquestRecord.getStartTime() != null)
		{
			String[] day1 = sdf.format(inquestRecord.getStartTime()).split("-");
			map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日" + day1[3] + "时" + day1[4] + "分");
		}
		else
		{
			map.put("startTime", "");
		}
		if(inquestRecord.getEndTime() != null)
		{
			String[] day2 = sdf.format(inquestRecord.getEndTime()).split("-");
			map.put("endTime", day2[2] + "日" + day2[3] + "时" + day2[4] + "分");
		}
		else
		{
			map.put("endTime", "");
		}
		map.put("inquestAddress", NullToString(inquestRecord.getInquestAddress()));
		map.put("weatherCondition", NullToString(inquestRecord.getWeatherCondition()));
			
		map.put("kyr2", "");
		map.put("kyrzw2","");
		map.put("kyr2zh", "________");
		if(inquestRecord.getInquestPerson() != null && !"".equals(inquestRecord.getInquestPerson()))
		{
			User user1 = userService.findUserById(inquestRecord.getInquestPerson());
			if(user1 != null)
			{
				map.put("kyr2", NullToString(user1.getDisplayName()));
				map.put("kyrzw2","苏州工业园区安全生产监察大队");
				map.put("kyr2zh", NullToDown(user1.getZfzh()));
			}
		}
		
		map.put("party1", NullToDown(inquestRecord.getParty1()));
		map.put("party1Company", NullToDown(inquestRecord.getParty1Company()));
		map.put("party1Tel", NullToDown(inquestRecord.getParty1Tel()));
		map.put("party2", NullToDown(inquestRecord.getParty2()));
		map.put("party2Company", NullToDown(inquestRecord.getParty2Company()));
		map.put("party2Tel", NullToDown(inquestRecord.getParty2Tel()));
		map.put("invitee", NullToDown(inquestRecord.getInvitee()));
		map.put("inviteeCompany", NullToDown(inquestRecord.getInviteeCompany()));
		map.put("inviteeTel", NullToDown(inquestRecord.getInviteeTel()));
		
		map.put("kyr1", "");
		map.put("kyrzw1","");
		map.put("kyr1zh", "________");
		map.put("recordPerson", "");
		map.put("recordCompany", "");
		if(inquestRecord.getRecordPerson() != null && !"".equals(inquestRecord.getRecordPerson()))
		{
			User user1 = userService.findUserById(inquestRecord.getRecordPerson());
			if(user1 != null)
			{
				map.put("recordPerson",NullToString(user1.getDisplayName()) );
				map.put("recordCompany","苏州工业园区安全生产监察大队");
				
				map.put("kyr1", NullToString(user1.getDisplayName()));
				map.put("kyrzw1","苏州工业园区安全生产监察大队");
				map.put("kyr1zh", NullToDown(user1.getZfzh()));
			}
		}
		map.put("inquestCondition", NullToString(inquestRecord.getInquestCondition()));
		
		String[] s = fileDocUtil.createDocFile(root+"勘验笔录.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		
		instrumentsInfoService.update(instrumentsInfo);
	}
	
	/**
	 * 保存抽样取样凭证
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveSamplingEvidence(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SamplingEvidence samplingEvidence,SamplingAssociate samplingAssociate, String flag,String type,String root) throws IOException
	{
		String undertakerName = samplingEvidence.getLawOfficerName1() ;
		if(samplingEvidence.getLawOfficer() != null)
		{
			User user = userService.findUserById(samplingEvidence.getLawOfficer().trim());
			undertakerName += "," + user.getDisplayName();
		}
		samplingEvidence.setLawOfficerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+samplingEvidence.getLawOfficer().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违管抽");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监管抽");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		samplingEvidence.setRelatedId(instrumentsInfo.getId());
		samplingEvidence.setDelFlag(0);
		samplingEvidence.setCreateUserID(instrumentsInfo.getCreateUserID());
		samplingEvidence.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			samplingEvidenceService.save(samplingEvidence);
		}else{
			samplingEvidenceService.update(samplingEvidence);
		}
		if(samplingAssociate.getEvidenceName() != null && !"".equals(samplingAssociate.getEvidenceName().replaceAll(" ", "")))
		{
			String[] glid = {};
			if(samplingAssociate.getId() != null)
			{
				glid = samplingAssociate.getId().replaceAll(" ", "").split(",");
			}
			String[] wpmc = samplingAssociate.getEvidenceName().replaceAll(" ", "").split(",");
			String[] gg= samplingAssociate.getSpecificationLot().replaceAll(" ", "").split(",");
			String[] sl = samplingAssociate.getSamplingNum().replaceAll(" ", "").split(",");
			for(int i=0;i<wpmc.length;i++)
			{
				if(glid != null && i<glid.length)
				{
					SamplingAssociate ss = samplingAssociateService.getById(glid[i]);
					ss.setEvidenceName(NullToStr(wpmc,i));
					ss.setSamplingNum(NullToStr(sl,i));
					ss.setSpecificationLot(NullToStr(gg,i));
					samplingAssociateService.update(ss);
				}
				else
				{
					SamplingAssociate ss = new SamplingAssociate();
					ss.setEvidenceName(NullToStr(wpmc,i));
					ss.setSamplingNum(NullToStr(sl,i));
					ss.setSpecificationLot(NullToStr(gg,i));
					ss.setDelFlag(0);
					ss.setForensicId(instrumentsInfo.getId());
					samplingAssociateService.save(ss);
				}
			}
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createSamplingEvidence(instrumentsInfo, caseInfo, samplingEvidence, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核抽样取样凭证 法务审核-》电子签名确认
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheSamplingEvidence(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SamplingEvidence samplingEvidence,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3"))//电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				createSamplingEvidence(instrumentsInfo, caseInfo, samplingEvidence, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		samplingEvidenceService.update(samplingEvidence);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
		
		
	}
	/**
	 * 生成抽样取样凭证 
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createSamplingEvidence(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SamplingEvidence samplingEvidence,String root) throws IOException
	{
		String name = "";
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		Map m = new HashMap();
		m.put("forensicId", instrumentsInfo.getId());
		List<SamplingAssociate> list = samplingAssociateService.findSamplingAssociate(m);
		for(int i=0;i<list.size();i++)
		{
			SamplingAssociate ss = list.get(i);
			Map<String, Object> mm = new HashMap<String, Object>();
			int index = i+1;
			mm.put("xh", index+"");
			mm.put("wpmc", NullToString(ss.getEvidenceName()));
			mm.put("gg", NullToString(ss.getSpecificationLot()));
			mm.put("sl", NullToString(ss.getSamplingNum()));
			newList.add(mm);
			name += NullToString(ss.getEvidenceName()) + ",";
		}
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("cyh", NullToString(instrumentsInfo.getAjh()));
		map.put("cyhNum", NullToString(instrumentsInfo.getAjhNum()));
		map.put("companyName", NullToString(caseInfo.getCompanyName()));
		map.put("chargePerson", NullToString(samplingEvidence.getChargePerson()));
		map.put("tele", NullToString(caseInfo.getTele()));
		map.put("yzcode", NullToString(caseInfo.getZipCode()));
		map.put("companyAddress", NullToString(caseInfo.getCompanyAddress()));
		
		map.put("startTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(samplingEvidence.getStartTime() != null)
		{
			String[] day1 = sdf.format(samplingEvidence.getStartTime()).split("-");
			map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日" + day1[3] + "时" + day1[4] + "分");
		}
		map.put("endTime", "");
		if(samplingEvidence.getEndTime() != null)
		{
			String[] day2 = sdf.format(samplingEvidence.getEndTime()).split("-");
			map.put("endTime", day2[2] + "日" + day2[3] + "时" + day2[4] + "分");
		}
		map.put("forensicAddress", NullToString(samplingEvidence.getForensicAddress()));
		map.put("cyqyList", newList);
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("zfry1zh", "________");
		map.put("zfry2zh", "________");
		if(samplingEvidence.getCreateUserID() != null && !"".equals(samplingEvidence.getCreateUserID()))
		{
			User uu = userService.findUserById(samplingEvidence.getCreateUserID());
			map.put("zfry1zh", NullToDown(uu.getZfzh()));
		}
		if(samplingEvidence.getLawOfficer() != null && !"".equals(samplingEvidence.getLawOfficer()))
		{
			User uu = userService.findUserById(samplingEvidence.getLawOfficer());
			map.put("zfry2zh", NullToDown(uu.getZfzh()));
		}
		String[] s = fileDocUtil.createDocFile(root+"抽样取样凭证.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
	}
	
	
	/**
	 * 保存先行登记保存证据审批表
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePreserveEvidence(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo,PreserveEvidence preserveEvidence,InventoryAssociate inventoryAssociate,String flag,String type,String root) throws IOException
	{
		String undertakerName = preserveEvidence.getUndertakerName1();
		if(preserveEvidence.getUndertaker() != null)
		{
			User user = userService.findUserById(preserveEvidence.getUndertaker().trim());
			undertakerName += "," + user.getDisplayName();
		}
		preserveEvidence.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+preserveEvidence.getUndertaker().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		preserveEvidence.setRelatedId(instrumentsInfo.getId());
		preserveEvidence.setDelFlag(0);
		preserveEvidence.setCreateUserID(instrumentsInfo.getCreateUserID());
		preserveEvidence.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			preserveEvidenceService.save(preserveEvidence);
		}else{
			preserveEvidenceService.update(preserveEvidence);
		}
		if(inventoryAssociate.getEvidenceName() != null && !"".equals(inventoryAssociate.getEvidenceName().replaceAll(" ", "")))
		{
			String[] glid = {};
			if(inventoryAssociate.getId() != null)
			{
				glid = inventoryAssociate.getId().replaceAll(" ", "").split(",");
			}
			String[] evidenceName = inventoryAssociate.getEvidenceName().replaceAll(" ", "").split(",");
			String[] specificationModel = inventoryAssociate.getSpecificationModel().replaceAll(" ", "").split(",");
			String[] originPlace = inventoryAssociate.getOriginPlace().replaceAll(" ", "").split(",");
			String[] condition = inventoryAssociate.getCondition().replaceAll(" ", "").split(",");
			String[] company = inventoryAssociate.getCompany().replaceAll(" ", "").split(",");
			String[] price = inventoryAssociate.getPrice().replaceAll(" ", "").split(",");
			String[] inventoryNum = inventoryAssociate.getInventoryNum().replaceAll(" ", "").split(",");
			String[] remark = inventoryAssociate.getRemark().replaceAll(" ", "").split(",");
			for(int i=0;i<evidenceName.length;i++)
			{
				if(glid != null && i<glid.length)
				{
					InventoryAssociate ss = inventoryAssociateService.getById(glid[i]);
					ss.setEvidenceName(NullToStr(evidenceName,i));
					ss.setSpecificationModel(NullToStr(specificationModel,i));
					ss.setOriginPlace(NullToStr(originPlace,i));
					ss.setCondition(NullToStr(condition,i));
					ss.setCompany(NullToStr(company,i));
					ss.setPrice(NullToStr(price,i));
					ss.setInventoryNum(NullToStr(inventoryNum,i));
					ss.setRemark(NullToStr(remark,i));
					inventoryAssociateService.update(ss);
				}
				else
				{
					InventoryAssociate ss = new InventoryAssociate();
					ss.setEvidenceName(NullToStr(evidenceName,i));
					ss.setSpecificationModel(NullToStr(specificationModel,i));
					ss.setOriginPlace(NullToStr(originPlace,i));
					ss.setCondition(NullToStr(condition,i));
					ss.setCompany(NullToStr(company,i));
					ss.setPrice(NullToStr(price,i));
					ss.setInventoryNum(NullToStr(inventoryNum,i));
					ss.setRemark(NullToStr(remark,i));
					ss.setDelFlag(0);
					ss.setRelatedId(instrumentsInfo.getId());
					inventoryAssociateService.save(ss);
				}
			}
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPreserveEvidence(instrumentsInfo, caseInfo, preserveEvidence, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核先行登记保存证据审批表 法务审核-》电子签名确认-》大队长-》局长
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenhePreserveEvidence(InstrumentsInfo in, CaseInfo ca,PreserveEvidence pe,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核  
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			pe.setUnderTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			pe.setDepartTime(checkTime);
			pe.setDepartComment(remark);
			pe.setDepartPerson(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2"))//局长审核
		{
			pe.setOfficeTime(checkTime);
			pe.setOfficeComment(remark);
			pe.setOfficePerson(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				
				createPreserveEvidence(in, ca, pe, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
		
		preserveEvidenceService.update(pe);
	}
	
	/**
	 * 生成先行登记保存证据审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createPreserveEvidence(InstrumentsInfo in, CaseInfo ca,PreserveEvidence pe,String root) throws IOException
	{
		//生成文书
		map.put("caseName",NullToString(ca.getCaseName()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("person", NullToString(ca.getPersonCondition()));
		}
		else
		{
			map.put("person", NullToString(ca.getPersonCondition()));
		}
		map.put("caseCondition", NullToString(ca.getCaseCondition()));
		map.put("relatedId", in.getId());
		List<InventoryAssociate> zzdjbczjglbList = inventoryAssociateService.findInventoryAssociate(map);
		String zjmc = "";
		for(InventoryAssociate inv:zzdjbczjglbList)
		{
			zjmc += NullToString(inv.getEvidenceName())+":" + NullToString(inv.getInventoryNum())+",";
		}
		if(zjmc.length() !=0 )
		{
			zjmc = zjmc.substring(0,zjmc.length()-1);
		}
		map.put("zjmc",zjmc );
		map.put("reasonBasic",NullToString(pe.getReasonBasic() ));
		map.put("preserveMethod",NullToString(pe.getPreserveMethod() ));
		map.put("undertakerComment",NullToString(pe.getUndertakerComment() ));
		map.put("underTime", changeTimeToZw(pe.getUnderTime()));
		map.put("departComment",NullToString(pe.getDepartComment() ));
		map.put("departTime",changeTimeToZw(pe.getDepartTime() ));
		map.put("officeComment",NullToString(pe.getOfficeComment()) );
		map.put("officeTime", changeTimeToZw(pe.getOfficeTime()));
		String[] s = fileDocUtil.createDocFile(root+"先行登记保存证据审批表.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
				in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	
	/**
	 * 保存先行登记保存证据通知书
	 * @author luting
	 * 2015-10-26
	 */
	public InstrumentsInfo saveNoticeEvidence(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, NoticeEvidence noticeEvidence,String flag,String type,String root) throws IOException
	{
		String undertakerName = noticeEvidence.getUndertakerName1() ;
		if(noticeEvidence.getUndertaker() != null)
		{
			User user = userService.findUserById(noticeEvidence.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		noticeEvidence.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+noticeEvidence.getUndertaker().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违先保通字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监先保通字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		noticeEvidence.setRelatedId(instrumentsInfo.getId());
		noticeEvidence.setDelFlag(0);
		noticeEvidence.setCreateUserID(instrumentsInfo.getCreateUserID());
		noticeEvidence.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			noticeEvidenceService.save(noticeEvidence);
		}else{
			noticeEvidenceService.update(noticeEvidence);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createNoticeEvidence(instrumentsInfo, caseInfo, noticeEvidence, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核先行登记保存证据通知书
	 * @author luting
	 * 2015-10-26
	 * @throws IOException 
	 */
	public void shenheNoticeEvidence(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, NoticeEvidence noticeEvidence,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3"))//电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				createNoticeEvidence(instrumentsInfo, caseInfo, noticeEvidence, root);
				
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		noticeEvidenceService.update(noticeEvidence);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成先行登记保存证据通知书
	 * @author luting
	 * 2015-10-26
	 * @throws IOException 
	 */
	public void createNoticeEvidence(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, NoticeEvidence noticeEvidence,String root) throws IOException
	{
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("bth", NullToString(instrumentsInfo.getAjh()));
		map.put("bthNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("person", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("person", NullToString(caseInfo.getCompanyName()));
		}
		map.put("violation", NullToString(noticeEvidence.getViolation()));
		
		if(noticeEvidence.getDealTime() != null)
		{
			String day[] = sdf.format(noticeEvidence.getDealTime()).split("-");
			String time = day[0] + "年" + day[1] + "月" + day[2] + "日";
			map.put("dealTime", time);
		}
		else
		{
			map.put("dealTime", "");
		}
		map.put("dealAddress", NullToString(noticeEvidence.getDealAddress()));
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		//查询先行登记保存证据审批表
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "5");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			List<InventoryAssociate> zzdjbczjglbList = inventoryAssociateService.findInventoryAssociate(m);
			for(int i=0;i<zzdjbczjglbList.size();i++)
			{
				Map<String, Object> mm = new HashMap<String, Object>();
				InventoryAssociate inv = zzdjbczjglbList.get(i);
				int index = i+1;
				mm.put("xh", index+"");
				mm.put("zjmc", NullToString(inv.getEvidenceName()));
				mm.put("gg", NullToString(inv.getSpecificationModel()));
				mm.put("cd", NullToString(inv.getOriginPlace()));
				mm.put("cs", NullToString(inv.getCondition()));
				mm.put("dw", NullToString(inv.getCompany()));
				mm.put("jg", NullToString(inv.getPrice()));
				mm.put("sl", NullToString(inv.getInventoryNum()));
				mm.put("bz", NullToString(inv.getRemark()));
				newList.add(mm);
			}
		}
		map.put("xxzjList", newList);
		String[] s = fileDocUtil.createDocFile(root+"先行登记保存证据通知书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	/**
	 * 保存先行登记保存证据处理审批表
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveInventoryCheck(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo,InventoryCheck inventoryCheck,String flag,String type,String root) throws IOException
	{
		String undertakerName = inventoryCheck.getUndertakerName1();
		if(inventoryCheck.getUndertaker() != null)
		{
			User user = userService.findUserById(inventoryCheck.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		inventoryCheck.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+inventoryCheck.getUndertaker().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		inventoryCheck.setRelatedId(instrumentsInfo.getId());
		inventoryCheck.setDelFlag(0);
		inventoryCheck.setCreateUserID(instrumentsInfo.getCreateUserID());
		inventoryCheck.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			inventoryCheckService.save(inventoryCheck);
		}else{
			inventoryCheckService.update(inventoryCheck);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createInventoryCheck(instrumentsInfo, caseInfo, inventoryCheck, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核先行登记保存证据处理审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheInventoryCheck(InstrumentsInfo in, CaseInfo ca,InventoryCheck inv,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			inv.setUnderTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			inv.setDepartTime(checkTime);
			inv.setDepartComment(remark);
			inv.setDepartPerson(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2"))//局长审核
		{
			inv.setOfficeTime(checkTime);
			inv.setOfficeComment(remark);
			inv.setOfficePerson(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				createInventoryCheck(in, ca, inv, root);
				
				in.setIfPrint("1");
				in.setJzCheck("2");
				
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
		
		inventoryCheckService.update(inv);
	}
	
	/**
	 * 生成先行登记保存证据处理审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createInventoryCheck(InstrumentsInfo in, CaseInfo ca,InventoryCheck inv,String root) throws IOException
	{
		//生成文书
		map.put("caseName",NullToString(ca.getCaseName()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("person", NullToString(ca.getPersonCondition()));
		}
		else
		{
			map.put("person", NullToString(ca.getPersonCondition()));
		}
		map.put("caseCondition", NullToString(ca.getCaseCondition()));
		Map m = new HashMap();
		m.put("caseId", ca.getId());
		m.put("instrumentType", "5");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		String zjmc = "";
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			List<InventoryAssociate> zzdjbczjglbList = inventoryAssociateService.findInventoryAssociate(m);
			for(InventoryAssociate inve:zzdjbczjglbList)
			{
				zjmc += NullToString(inve.getEvidenceName())+":" + NullToString(inve.getInventoryNum())+",";
			}
			if(zjmc.length() !=0 )
			{
				zjmc = zjmc.substring(0,zjmc.length()-1);
			}
		}
		map.put("zjmc",zjmc );
		map.put("reasonBasic",NullToString(inv.getReasonBasic() ));
		map.put("undertakerComment",NullToString(inv.getUndertakerComment() ));
		map.put("underTime", changeTimeToZw(inv.getUnderTime()));
		map.put("departComment",NullToString(inv.getDepartComment()) );
		map.put("departTime",changeTimeToZw(inv.getDepartTime() ));
		map.put("officeComment",NullToString(inv.getOfficeComment() ));
		map.put("officeTime", changeTimeToZw(inv.getOfficeTime()));
		String[] s = fileDocUtil.createDocFile(root+"先行登记保存证据处理审批表.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存先行登记保存证据处理决定书 
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveInventoryDecision(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, InventoryDecision inventoryDecision,String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违先保处字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监先保处字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		inventoryDecision.setRelatedId(instrumentsInfo.getId());
		inventoryDecision.setDelFlag(0);
		inventoryDecision.setCreateUserID(instrumentsInfo.getCreateUserID());
		inventoryDecision.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			inventoryDecisionService.save(inventoryDecision);
		}else{
			inventoryDecisionService.update(inventoryDecision);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createInventoryDecision(instrumentsInfo, caseInfo, inventoryDecision, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核先行登记保存证据处理决定书 
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheInventoryDecision(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, InventoryDecision inventoryDecision,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createInventoryDecision(instrumentsInfo, caseInfo, inventoryDecision, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成先行登记保存证据处理决定书 
	 * @author luting
	 * 2015-10-24
	 */
	public void createInventoryDecision(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, InventoryDecision inventoryDecision,String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("bch", NullToString(instrumentsInfo.getAjh()));
		map.put("bchNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("person", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("person", NullToString(caseInfo.getCompanyName()));
		}
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		//查询先行登记保存证据审批表
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "5");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		String mc = "";
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			List<InventoryAssociate> zzdjbczjglbList = inventoryAssociateService.findInventoryAssociate(m);
			for(int i=0;i<zzdjbczjglbList.size();i++)
			{
				mc += zzdjbczjglbList.get(i).getEvidenceName() + ",";
			}
			if(mc.length() != 0)
			{
				mc = mc.substring(0,mc.length()-1);
			}
		}
		map.put("zjmc", mc);
		//查询先行登记保存证据通知书
		Date bctime = null;
		String bth = "";
		String btajbz = "";
		String bthNum = "";
		m.put("instrumentType", "6");
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list1.size() != 0)
		{
			bctime = list1.get(0).getTime();
			bth = list1.get(0).getAjh();
			btajbz = list1.get(0).getAjbz();
			bthNum = list1.get(0).getAjhNum();
		}
		map.put("bctime", changeTimeToZw(bctime));
		map.put("bth", NullToString(bth));
		map.put("btajbz", NullToString(btajbz));
		map.put("bthNum", NullToString(bthNum));
		map.put("deal", NullToString(inventoryDecision.getDeal()));
		
		String[] s = fileDocUtil.createDocFile(root+"先行登记保存证据处理决定书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
	}

	/**
	 * 保存现场检查记录
	 * @author luting
	 * 2015-10-27
	 */
	public InstrumentsInfo saveSiteCheckRecord(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, SiteCheckRecord siteCheckRecord,String flag,String type,String root) throws IOException
	{
		String undertakerName = siteCheckRecord.getCheckPersonName1();
		if(siteCheckRecord.getCheckPerson() != null)
		{
			User user = userService.findUserById(siteCheckRecord.getCheckPerson().trim());
			undertakerName += "," +user.getDisplayName();
		}
		siteCheckRecord.setCheckPersonName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+siteCheckRecord.getCheckPerson().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		siteCheckRecord.setRelatedId(instrumentsInfo.getId());
		siteCheckRecord.setDelFlag(0);
		siteCheckRecord.setCreateUserID(instrumentsInfo.getCreateUserID());
		siteCheckRecord.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			siteCheckRecordService.save(siteCheckRecord);
		}else{
			siteCheckRecordService.update(siteCheckRecord);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createSiteCheckRecord(instrumentsInfo, caseInfo, siteCheckRecord, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核现场检查记录
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void shenheSiteCheckRecord(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, SiteCheckRecord siteCheckRecord,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3"))//电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书	
				
				createSiteCheckRecord(instrumentsInfo, caseInfo, siteCheckRecord, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		siteCheckRecordService.update(siteCheckRecord);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成现场检查记录
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void createSiteCheckRecord(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, SiteCheckRecord siteCheckRecord,String root) throws IOException
	{
				
		//生成文书	
		map.put("companyName", NullToString(caseInfo.getCompanyName()));
		map.put("companyAddress", NullToString(caseInfo.getCompanyAddress()));
		map.put("chargePersonZw", NullToString(caseInfo.getZw()));
		map.put("chargePersonTel", NullToString(caseInfo.getTele()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("chargePerson", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("chargePerson", NullToString(caseInfo.getFddbr()));
		}
		map.put("checkAddress", NullToString(siteCheckRecord.getCheckAddress()));
		map.put("startTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(siteCheckRecord.getStartTime() != null)
		{
			String[] day1 = sdf.format(siteCheckRecord.getStartTime()).split("-");
			map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日" + day1[3] + "时" + day1[4] + "分");
		}
		map.put("endTime", "");
		if(siteCheckRecord.getEndTime() != null)
		{
			String[] day2 = sdf.format(siteCheckRecord.getEndTime()).split("-");
			map.put("endTime", day2[2] + "日" + day2[3] + "时" + day2[4] + "分");
		}
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("zfry1", "");
		map.put("zfry2", "");
		map.put("zfry1Zj", "");
		map.put("zfry2Zj", "");
		if(siteCheckRecord.getCreateUserID() != null && !"".equals(siteCheckRecord.getCreateUserID()))
		{
			User uu = userService.findUserById(siteCheckRecord.getCreateUserID());
			map.put("zfry1", NullToString(uu.getDisplayName()));
			map.put("zfry1Zj", NullToDown(uu.getZfzh()));
		}
		if(siteCheckRecord.getCheckPerson() != null && !"".equals(siteCheckRecord.getCheckPerson()))
		{
			User uu = userService.findUserById(siteCheckRecord.getCheckPerson());
			map.put("zfry2", NullToString(uu.getDisplayName()));
			map.put("zfry2Zj", NullToDown(uu.getZfzh()));
		}
		String[] sgyh = siteCheckRecord.getCheckCondition().split("\r\n");
		List<Map<String, Object>> sgyhList = new ArrayList<Map<String, Object>>();
		for(String s:sgyh)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			if(s != null && !"".equals(s))
			{
				mm.put("sgyh", s);
				sgyhList.add(mm);
			}
		}
		map.put("xccssgyhList", sgyhList);
		String[] s = fileDocUtil.createDocFile(root+"现场检查记录.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	/**
	 * 保存现场处理措施决定书
	 * @author luting
	 * 2015-10-27
	 */
	public InstrumentsInfo saveLiveActionDecision(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,LiveActionDecision liveActionDecision, String flag,String type,String root) throws IOException
	{
		String undertakerName = liveActionDecision.getLawOfficerName1() ;
		if(liveActionDecision.getLawOfficer() != null)
		{
			User user = userService.findUserById(liveActionDecision.getLawOfficer().trim());
			undertakerName += "," +user.getDisplayName();
		}
		liveActionDecision.setLawOfficerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+liveActionDecision.getLawOfficer().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违现决字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监现决字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		liveActionDecision.setRelatedId(instrumentsInfo.getId());
		liveActionDecision.setDelFlag(0);
		liveActionDecision.setCreateUserID(instrumentsInfo.getCreateUserID());
		liveActionDecision.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			liveActionDecisionService.save(liveActionDecision);
		}else{
			liveActionDecisionService.update(liveActionDecision);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createLiveActionDecision(instrumentsInfo, caseInfo, liveActionDecision, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核现场处理措施决定书
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void shenheLiveActionDecision(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,LiveActionDecision liveActionDecision, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3"))
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书	
				
				createLiveActionDecision(instrumentsInfo, caseInfo, liveActionDecision, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		liveActionDecisionService.update(liveActionDecision);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成现场处理措施决定书
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void createLiveActionDecision(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,LiveActionDecision liveActionDecision, String root) throws IOException
	{
				
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("xjh", NullToString(instrumentsInfo.getAjh()));
		map.put("xjhNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(caseInfo.getCompanyName()));
		}
		
		//查找对应现场检查记录
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "9");
		map.put("jcsj", "");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			SiteCheckRecord siteCheckRecord = (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(m).get(0);
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
			if(siteCheckRecord.getStartTime() != null)
			{
				String[] day1 = sdf.format(siteCheckRecord.getStartTime()).split("-");
				map.put("jcsj", day1[0] + "年" + day1[1] + "月" + day1[2] + "日");
			}
		}
		String[] sgyh = liveActionDecision.getIllegalAccident().split("\r\n");
		List<Map<String, Object>> sgyhList = new ArrayList<Map<String, Object>>();
		for(String s:sgyh)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			if(s != null && !"".equals(s))
			{
				mm.put("sgyh", s);
				sgyhList.add(mm);
			}
		}
		map.put("xccssgyhList", sgyhList);
		String lawBasic = "";
		if(liveActionDecision.getLawBasic() != null)
		{
			String[] ss = liveActionDecision.getLawBasic().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				LawBasis lawBasis = lawBasisService.getById(s);
				if(lawBasis != null)
				{
					lawBasic += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
				}
			}
			if(lawBasic.length() != 0)
			{
				lawBasic = lawBasic.substring(0,lawBasic.length()-1);
			}
		}
		else
		{
			lawBasic =  NullToString(liveActionDecision.getLawName());
		}
		map.put("lawBasic", lawBasic);
		
		String[] cljd = liveActionDecision.getIllegalAccident().split("\r\n");
		List<Map<String, Object>> cljdList = new ArrayList<Map<String, Object>>();
		for(String s:cljd)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			if(s != null && !"".equals(s))
			{
				mm.put("cljd", s);
				cljdList.add(mm);
			}
		}
		map.put("xccscljdList", cljdList);
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("zfry1zh", "________");
		map.put("zfry2zh", "________");
		if(liveActionDecision.getCreateUserID() != null && !"".equals(liveActionDecision.getCreateUserID()))
		{
			User uu = userService.findUserById(liveActionDecision.getCreateUserID());
			map.put("zfry1zh", NullToDown(uu.getZfzh()));
		}
		if(liveActionDecision.getLawOfficer() != null && !"".equals(liveActionDecision.getLawOfficer()))
		{
			User uu = userService.findUserById(liveActionDecision.getLawOfficer());
			map.put("zfry2zh", NullToDown(uu.getZfzh()));
		}
		String[] s = fileDocUtil.createDocFile(root+"现场处理措施决定书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	/**
	 * 保存责令限期整改指令书
	 * @author luting
	 * 2015-10-27
	 */
	public InstrumentsInfo saveOrderDeadlineBook(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,OrderDeadlineBook orderDeadlineBook, String flag,String type,String root) throws IOException
	{ 
		String undertakerName = orderDeadlineBook.getLawOfficerName1();
		if(orderDeadlineBook.getLawOfficer() != null)
		{
			User user = userService.findUserById(orderDeadlineBook.getLawOfficer().trim());
			undertakerName += "," +user.getDisplayName();
		}
		orderDeadlineBook.setLawOfficerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+orderDeadlineBook.getLawOfficer().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违责改字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监责改字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		orderDeadlineBook.setRelatedId(instrumentsInfo.getId());
		orderDeadlineBook.setDelFlag(0);
		orderDeadlineBook.setCreateUserID(instrumentsInfo.getCreateUserID());
		orderDeadlineBook.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			orderDeadlineBookService.save(orderDeadlineBook);
		}else{
			orderDeadlineBookService.update(orderDeadlineBook);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createOrderDeadlineBook(instrumentsInfo, caseInfo, orderDeadlineBook, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核责令限期整改指令书
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void shenheOrderDeadlineBook(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,OrderDeadlineBook orderDeadlineBook,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				createOrderDeadlineBook(instrumentsInfo, caseInfo, orderDeadlineBook, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
		
		orderDeadlineBookService.update(orderDeadlineBook);
	}
	
	/**
	 * 生成责令限期整改指令书
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void createOrderDeadlineBook(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,OrderDeadlineBook orderDeadlineBook,String root) throws IOException
	{
				
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("zgh", NullToString(instrumentsInfo.getAjh()));
		map.put("zghNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(caseInfo.getCompanyName()));
		}
		String[] sgyh = orderDeadlineBook.getProblem().split("\r\n");
		List<Map<String, Object>> sgyhList = new ArrayList<Map<String, Object>>();
		for(String s:sgyh)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			if(s != null && !"".equals(s))
			{
				mm.put("sgyh", s);
				sgyhList.add(mm);
			}
		}
		map.put("xccssgyhList", sgyhList);
		map.put("changeItem", NullToString(orderDeadlineBook.getChangeItem()));
		map.put("changeTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		if(orderDeadlineBook.getStartTime() != null)
		{
			String[] day1 = sdf.format(orderDeadlineBook.getStartTime()).split("-");
			map.put("changeTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日");
		}
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("zfry1zh", "________");
		map.put("zfry2zh", "________");
		if(orderDeadlineBook.getCreateUserID() != null && !"".equals(orderDeadlineBook.getCreateUserID()))
		{
			User uu = userService.findUserById(orderDeadlineBook.getCreateUserID());
			map.put("zfry1zh", NullToDown(uu.getZfzh()));
		}
		if(orderDeadlineBook.getLawOfficer() != null && !"".equals(orderDeadlineBook.getLawOfficer()))
		{
			User uu = userService.findUserById(orderDeadlineBook.getLawOfficer());
			map.put("zfry2zh", NullToDown(uu.getZfzh()));
		}
		String[] s = fileDocUtil.createDocFile(root+"责令限期整改指令书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	
	/**
	 * 保存整改复查意见书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveReviewSubmission(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,ReviewSubmission reviewSubmission, String flag,String type,String root) throws IOException
	{
		String undertakerName =reviewSubmission.getLawOfficerName1() ;
		if(reviewSubmission.getLawOfficer() != null)
		{
			User user = userService.findUserById(reviewSubmission.getLawOfficer().trim());
			undertakerName +="," + user.getDisplayName();
		}
		reviewSubmission.setLawOfficerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+reviewSubmission.getLawOfficer().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违复查字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监复查字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		reviewSubmission.setRelatedId(instrumentsInfo.getId());
		reviewSubmission.setDelFlag(0);
		reviewSubmission.setCreateUserID(instrumentsInfo.getCreateUserID());
		reviewSubmission.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			reviewSubmissionService.save(reviewSubmission);
		}else{
			reviewSubmissionService.update(reviewSubmission);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createReviewSubmission(instrumentsInfo, caseInfo, reviewSubmission, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核整改复查意见书
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheReviewSubmission(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,ReviewSubmission reviewSubmission, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书	
				
				createReviewSubmission(instrumentsInfo, caseInfo, reviewSubmission, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
			
		instrumentsInfoService.update(instrumentsInfo);
		
		reviewSubmissionService.update(reviewSubmission);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成整改复查意见书
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createReviewSubmission(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,ReviewSubmission reviewSubmission, String root) throws IOException
	{
				
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("fch", NullToString(instrumentsInfo.getAjh()));
		map.put("fchNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(caseInfo.getCompanyName()));
		}

		Date xqzgTime = null;
		String zgjd = "";
		String jdajbz = "";
		String zfh = "";
		String zfhNum = "";
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "11");//责令限期整改指令书
		List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list2.size() != 0)
		{
			xqzgTime = list2.get(0).getTime();
			zgjd = "责令限期整改";
			jdajbz = list2.get(0).getAjbz();
			zfh = list2.get(0).getAjh();
			zfhNum = list2.get(0).getAjhNum();
		}
		map.put("xqzgTime",changeTimeToZw(xqzgTime)); 
		map.put("zgjd",NullToString(zgjd));
		map.put("jdajbz",NullToString(jdajbz));
		map.put("zfh",NullToString(zfh));
		map.put("zfhNum",NullToString(zfhNum));
		String[] sgyh = reviewSubmission.getReviewComment().split("\r\n");
		List<Map<String, Object>> sgyhList = new ArrayList<Map<String, Object>>();
		for(String s:sgyh)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			if(s != null && !"".equals(s))
			{
				mm.put("sgyh", s);
				sgyhList.add(mm);
			}
		}
		map.put("xccssgyhList", sgyhList);
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("zfry1zh", "________");
		map.put("zfry2zh", "________");
		if(reviewSubmission.getCreateUserID()!= null && !"".equals(reviewSubmission.getCreateUserID()))
		{
			User uu = userService.findUserById(reviewSubmission.getCreateUserID());
			map.put("zfry1zh", NullToDown(uu.getZfzh()));
		}
		if(reviewSubmission.getLawOfficer() != null && !"".equals(reviewSubmission.getLawOfficer()))
		{
			User uu = userService.findUserById(reviewSubmission.getLawOfficer());
			map.put("zfry2zh", NullToDown(uu.getZfzh()));
		}
		String[] s = fileDocUtil.createDocFile(root+"整改复查意见书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
			
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	
	/**
	 * 保存强制措施决定书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveEnforenceDecision(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,EnforenceDecision enforenceDecision, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违强措字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监强措字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		enforenceDecision.setRelatedId(instrumentsInfo.getId());
		enforenceDecision.setDelFlag(0);
		enforenceDecision.setCreateUserID(instrumentsInfo.getCreateUserID());
		enforenceDecision.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			enforenceDecisionService.save(enforenceDecision);
		}else{
			enforenceDecisionService.update(enforenceDecision);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createEnforenceDecision(instrumentsInfo, caseInfo, enforenceDecision, root);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核强制措施决定书
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheEnforenceDecision(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, EnforenceDecision enforenceDecision,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createEnforenceDecision(instrumentsInfo, caseInfo, enforenceDecision, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成强制措施决定书
	 * @author luting
	 * 2015-10-24
	 */
	public void createEnforenceDecision(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, EnforenceDecision enforenceDecision,String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("qch", NullToString(instrumentsInfo.getAjh()));
		map.put("qchNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(caseInfo.getCompanyName()));
		}
		map.put("problem", NullToString(enforenceDecision.getProblem()));
		
		String lawBasic = "";
		if(enforenceDecision.getLawBasic() != null)
		{
			String[] ss = enforenceDecision.getLawBasic().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				LawBasis lawBasis = lawBasisService.getById(s);
				if(lawBasis != null)
				{
					lawBasic += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
				}
			}
			if(lawBasic.length() != 0)
			{
				lawBasic = lawBasic.substring(0,lawBasic.length()-1);
			}
		}
		else
		{
			lawBasic =  NullToString(enforenceDecision.getLawName());
		}
		map.put("lawBasic", lawBasic);
		
		map.put("method", NullToString(enforenceDecision.getMethod()));
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"强制措施决定书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	/**
	 * 保存鉴定委托书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveIdentifyAttorney(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,IdentifyAttorney identifyAttorney,IdentifyItemAssociate identifyItemAssociate, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违管鉴");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监管鉴");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		identifyAttorney.setRelatedId(instrumentsInfo.getId());
		identifyAttorney.setDelFlag(0);
		identifyAttorney.setCreateUserID(instrumentsInfo.getCreateUserID());
		identifyAttorney.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			identifyAttorneyService.save(identifyAttorney);
		}else{
			identifyAttorneyService.update(identifyAttorney);
		}
		if(identifyItemAssociate.getItemName() != null && !"".equals(identifyItemAssociate.getItemName().replaceAll(" ", "")))
		{
			String[] glid = {};
			if(identifyItemAssociate.getId() != null)
			{
				glid = identifyItemAssociate.getId().replaceAll(" ", "").split(",");
			}
			String[] wpmc = identifyItemAssociate.getItemName().replaceAll(" ", "").split(",");
			String[] gg= identifyItemAssociate.getSpecificationModel().replaceAll(" ", "").split(",");
			String[] sl = identifyItemAssociate.getIdentifyNum().replaceAll(" ", "").split(",");
			String[] bz = identifyItemAssociate.getRemark().replaceAll(" ", "").split(",");
			for(int i=0;i<wpmc.length;i++)
			{
				if(glid != null && i<glid.length)
				{
					IdentifyItemAssociate ss = identifyItemAssociateService.getById(glid[i]);
					ss.setItemName(NullToStr(wpmc,i));
					ss.setSpecificationModel(NullToStr(gg,i));
					ss.setIdentifyNum(NullToStr(sl,i));
					ss.setRemark(NullToStr(bz,i));
					identifyItemAssociateService.update(ss);
				}
				else
				{
					IdentifyItemAssociate ss = new IdentifyItemAssociate();
					ss.setItemName(NullToStr(wpmc,i));
					ss.setSpecificationModel(NullToStr(gg,i));
					ss.setIdentifyNum(NullToStr(sl,i));
					ss.setRemark(NullToStr(bz,i));
					ss.setDelFlag(0);
					ss.setAttorenyId(instrumentsInfo.getId());
					identifyItemAssociateService.save(ss);
				}
			}
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createIdentifyAttorney(instrumentsInfo, caseInfo, identifyAttorney, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核鉴定委托书
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheIdentifyAttorney(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, IdentifyAttorney identifyAttorney,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createIdentifyAttorney(instrumentsInfo, caseInfo, identifyAttorney, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成鉴定委托书
	 * @author luting
	 * 2015-10-24
	 */
	public void createIdentifyAttorney(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, IdentifyAttorney identifyAttorney,String root) throws IOException
	{
			
		//生成文书	
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		Map m = new HashMap();
		m.put("attorenyId", instrumentsInfo.getId());
		List<IdentifyItemAssociate> list = identifyItemAssociateService.findIdentifyItemAssociate(m);
		for(IdentifyItemAssociate identifyItemAssociate:list)
		{
			Map<String, Object> mm = new HashMap<String, Object>();
			mm.put("wpmc", NullToString(identifyItemAssociate.getItemName()));
			mm.put("gg", NullToString(identifyItemAssociate.getSpecificationModel()));
			mm.put("sl", NullToString(identifyItemAssociate.getIdentifyNum()));
			mm.put("bz", NullToString(identifyItemAssociate.getRemark()));
			newList.add(mm);
		}
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("gjh", NullToString(instrumentsInfo.getAjh()));
		map.put("gjhNum", NullToString(instrumentsInfo.getAjhNum()));
		map.put("inentifyRequire", NullToString(identifyAttorney.getInentifyRequire()));
		map.put("jdjgName", NullToString(identifyAttorney.getJdjgName()));
		map.put("submitTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		if(identifyAttorney.getSubmitTime() != null)
		{
			String[] day1 = sdf.format(identifyAttorney.getSubmitTime()).split("-");
			map.put("submitTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日");
		}
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("jdwtList", newList);
		
		String[] s = fileDocUtil.createDocFile(root+"鉴定委托书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	
	/**
	 * 保存行政处罚告知书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePenaltyNotice(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PenaltyNotice penaltyNotice, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违罚告字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监罚告字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("1");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		
		penaltyNotice.setRelatedId(instrumentsInfo.getId());
		penaltyNotice.setDelFlag(0);
		penaltyNotice.setCreateUserID(instrumentsInfo.getCreateUserID());
		penaltyNotice.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			penaltyNoticeService.save(penaltyNotice);
		}else{
			penaltyNoticeService.update(penaltyNotice);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPenaltyNotice(instrumentsInfo, caseInfo, penaltyNotice, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核行政处罚告知书
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenhePenaltyNotice(InstrumentsInfo in, CaseInfo ca,PenaltyNotice pen, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				
				createPenaltyNotice(in, ca, pen, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
				
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
		
		penaltyNoticeService.update(pen);
	}
	
	/**
	 * 生成行政处罚告知书
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createPenaltyNotice(InstrumentsInfo in, CaseInfo ca,PenaltyNotice pen, String root) throws IOException
	{
		//生成文书
		map.put("caseCause", NullToString(pen.getCaseCondition()));
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("fgh", NullToString(in.getAjh()));
		map.put("fghNum", NullToString(in.getAjhNum()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(ca.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(ca.getCompanyName()));
		}
		
		map.put("wfxw", NullToString(pen.getWfxw()));
		
		String provision = "";
		if(pen.getProvision() != null)
		{
			String[] ss = pen.getProvision().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				LawBasis lawBasis = lawBasisService.getById(s);
				if(lawBasis != null)
				{
					provision += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
				}
			}
			if(provision.length() != 0)
			{
				provision = provision.substring(0,provision.length()-1);
			}
		}
		else
		{
			provision =  NullToString(pen.getProName());
		}
		map.put("provision", provision);
		
		
		String lawBasic = "";
		if(pen.getLawBasic() != null)
		{
			String[] ss = pen.getLawBasic().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				LawBasis lawBasis = lawBasisService.getById(s);
				if(lawBasis != null)
				{
					lawBasic += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
				}
			}
			if(lawBasic.length() != 0)
			{
				lawBasic = lawBasic.substring(0,lawBasic.length()-1);
			}
		}
		else
		{
			lawBasic =  NullToString(pen.getLawName());
		}
		map.put("lawBasic", lawBasic);
		
		map.put("adminPenality", NullToString(pen.getAdminPenality()));
		map.put("time", changeTimeToZw(in.getTime()));
		
		map.put("frxx", "");
		if(ca.getPersonType().equals("2"))
		{
			map.put("frxx", "（法定代表人："+NullToString(ca.getFddbr())+"；注册地址："+NullToString(ca.getCompanyAddress())+"；统一社会信用代码："+NullToString(ca.getXyhm()+"）"));
		}
		
		String[] s = fileDocUtil.createDocFile(root+"行政处罚告知书.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	
	/**
	 * 保存当事人陈述申辩笔录
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePartyStateNote(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PartyStateNote partyStateNote, String flag,String type,String root) throws IOException
	{
		if(partyStateNote.getUndertaker() != null)
		{
			User user = userService.findUserById(partyStateNote.getUndertaker().trim());
			partyStateNote.setUndertakerName(user.getDisplayName());
		}
		instrumentsInfo.setNeedCheckUser(","+partyStateNote.getUndertaker().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		partyStateNote.setRelatedId(instrumentsInfo.getId());
		partyStateNote.setDelFlag(0);
		partyStateNote.setCreateUserID(instrumentsInfo.getCreateUserID());
		partyStateNote.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			partyStateNoteService.save(partyStateNote);
		}else{
			partyStateNoteService.update(partyStateNote);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPartyStateNote(instrumentsInfo, caseInfo, partyStateNote, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核当事人陈述申辩笔录
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenhePartyStateNote(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PartyStateNote partyStateNote, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书	
				
				createPartyStateNote(instrumentsInfo, caseInfo, partyStateNote, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
		
		partyStateNoteService.update(partyStateNote);
	}
	
	/**
	 * 生成当事人陈述申辩笔录
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createPartyStateNote(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PartyStateNote partyStateNote, String root) throws IOException
	{
				
		//生成文书	
		map.put("startTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(partyStateNote.getStartTime() != null)
		{
			String[] day1 = sdf.format(partyStateNote.getStartTime()).split("-");
			map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日"+ day1[3] + "时" + day1[4] + "分");
		}
		map.put("endTime", "");
		if(partyStateNote.getEndTime() != null)
		{
			String[] day2 = sdf.format(partyStateNote.getEndTime()).split("-");
			map.put("endTime",  day2[2] + "日"+ day2[3] + "时" + day2[4] + "分");
		}
		map.put("stateAddress", NullToString(partyStateNote.getStateAddress()));
		map.put("stateDefense", NullToString(partyStateNote.getStateDefense()));
		Map m = new HashMap();
		if(partyStateNote.getSex() != null && !"".equals(partyStateNote.getSex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", partyStateNote.getSex());
			map.put("sex", NullToString(codeService.findCodeValueByMap(m).getItemText()));
		}
		else
		{
			map.put("sex", "");
		}
		map.put("position", NullToString(partyStateNote.getPosition()));
		map.put("companyName", NullToString(caseInfo.getCompanyName()));
		map.put("tele", NullToString(partyStateNote.getTele()));
		map.put("address", NullToString(partyStateNote.getAddress()));
		map.put("zipCode", NullToString(partyStateNote.getZipCode()));
		map.put("undertaker","");
		map.put("zfry1zh", "________");
		if(partyStateNote.getUndertaker() != null && !"".equals(partyStateNote.getUndertaker()))
		{
			User user1 = userService.findUserById(partyStateNote.getUndertaker());
			if(user1 != null)
			{
				map.put("undertaker",NullToString(user1.getDisplayName()) );
				map.put("zfry1zh", NullToDown(user1.getZfzh()));
			}
		}
		map.put("recorder","" );
		map.put("zfry2zh","________" );
		if(partyStateNote.getRecorder() != null && !"".equals(partyStateNote.getRecorder()))
		{
			User user1 = userService.findUserById(partyStateNote.getRecorder());
			if(user1 != null)
			{
				map.put("recorder",NullToString(user1.getDisplayName()) );
				map.put("zfry2zh", NullToDown(user1.getZfzh()));
			}
		}
		map.put("caseName", NullToString(caseInfo.getCaseName()));
		map.put("stateRecord", NullToString(partyStateNote.getStateRecord()));
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		String[] s = fileDocUtil.createDocFile(root+"当事人陈述申辩笔录.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
	}
	
	/**
	 * 保存听证告知书
	 * @author luting
	 * 2015-10-28
	 */
	public InstrumentsInfo saveHearingTell(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,HearingTell hearingTell,String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违听告字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监听告字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("1");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		hearingTell.setRelatedId(instrumentsInfo.getId());
		hearingTell.setDelFlag(0);
		hearingTell.setCreateUserID(instrumentsInfo.getCreateUserID());
		hearingTell.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			hearingTellService.save(hearingTell);
		}else{
			hearingTellService.update(hearingTell);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createHearingTell(instrumentsInfo, caseInfo, hearingTell, root);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核听证告知书
	 * @author luting
	 * 2015-10-28
	 * @throws IOException 
	 */
	public void shenheHearingTell(InstrumentsInfo in, CaseInfo ca,HearingTell hea,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			hea.setUndertakerComment(remark);
			hea.setUnderTime(checkTime);
			hea.setUndertaker(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			hea.setDepartComment(remark);
			hea.setDepartTime(checkTime);
			hea.setDepartPerson(user.getId());
			
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else  if(status.equals("2")) //局长审核
		{
			hea.setOfficeComment(remark);
			hea.setOfficeTime(checkTime);
			hea.setOfficePerson(user.getId());
			
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				
				createHearingTell(in, ca, hea, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
		
		hearingTellService.update(hea);
	}
	
	/**
	 * 生成听证告知书
	 * @author luting
	 * 2015-10-28
	 * @throws IOException 
	 */
	public void createHearingTell(InstrumentsInfo in, CaseInfo ca,HearingTell hea,String root) throws IOException
	{
		//查询行政处罚告知书
		Map m = new HashMap();
		m.put("caseId", ca.getId());
		m.put("instrumentType", "15");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("provision", "");
		map.put("wfxw","");
		map.put("lawBasic", "");
		map.put("adminPenality", "");
		map.put("caseCause", "");
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			map.put("wfxw", NullToString(penaltyNotice.getWfxw()));
			map.put("caseCause", NullToString(penaltyNotice.getCaseCondition()));
			
			String provision = "";
			if(penaltyNotice.getProvision() != null)
			{
				String[] ss = penaltyNotice.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						provision += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision())+ ",";
					}
				}
				if(provision.length() != 0)
				{
					provision = provision.substring(0,provision.length()-1);
				}
			}
			else
			{
				provision =  NullToString(penaltyNotice.getProName());
			}
			map.put("provision", provision);
			
			String lawBasic = "";
			if(penaltyNotice.getLawBasic() != null)
			{
				String[] ss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						lawBasic += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(lawBasic.length() != 0)
				{
					lawBasic = lawBasic.substring(0,lawBasic.length()-1);
				}
			}
			else
			{
				lawBasic =  NullToString(penaltyNotice.getLawName());
			}
			map.put("lawBasic", lawBasic);
			
			map.put("adminPenality", NullToString(penaltyNotice.getAdminPenality()));
		}
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("tgh", NullToString(in.getAjh()));
		map.put("tghNum", NullToString(in.getAjhNum()));
		map.put("time", changeTimeToZw(in.getTime()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(ca.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(ca.getCompanyName()));
		}
		
		map.put("frxx", "");
		if(ca.getPersonType().equals("2"))
		{
			map.put("frxx", "（法定代表人："+NullToString(ca.getFddbr())+"；注册地址："+NullToString(ca.getCompanyAddress())+"；统一社会信用代码："+NullToString(ca.getXyhm())+"）");
		}
		
		String[] s = fileDocUtil.createDocFile(root+"听证告知书.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	/**
	 * 保存听证通知书
	 * @author luting
	 * 2015-10-28
	 */
	public InstrumentsInfo saveHearingNotice(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,HearingNotice hearingNotice, String flag,String type,String root) throws IOException
	{
		if(hearingNotice.getHearingChairperson() != null)
		{
			User user = userService.findUserById(hearingNotice.getHearingChairperson().trim());
			hearingNotice.setHearingChairpersonName(user.getDisplayName());
		}
		String undertakerName = "";
		if(hearingNotice.getHearingOfficer() != null)
		{
			String[] ss = hearingNotice.getHearingOfficer().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				User user = userService.findUserById(s.trim());
				undertakerName += user.getDisplayName() + ",";
			}
		}
		hearingNotice.setHearingOfficerName(undertakerName.substring(0,undertakerName.length()-1));
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违听通字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监听通字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		hearingNotice.setRelatedId(instrumentsInfo.getId());
		hearingNotice.setDelFlag(0);
		hearingNotice.setCreateUserID(instrumentsInfo.getCreateUserID());
		hearingNotice.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			hearingNoticeService.save(hearingNotice);
		}else{
			hearingNoticeService.update(hearingNotice);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createHearingNotice(instrumentsInfo, caseInfo, hearingNotice, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核听证通知书 
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheHearingNotice(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, HearingNotice hearingNotice,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createHearingNotice(instrumentsInfo, caseInfo, hearingNotice, root);
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成听证通知书 
	 * @author luting
	 * 2015-10-24
	 */
	public void createHearingNotice(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, HearingNotice hearingNotice,String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("tth", NullToString(instrumentsInfo.getAjh()));
		map.put("tthNum", NullToString(instrumentsInfo.getAjhNum()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("companyName", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("companyName", NullToString(caseInfo.getCompanyName()));
		}
		map.put("caseName", NullToString(caseInfo.getCaseName()));
		map.put("hearingTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(hearingNotice.getHearingTime() != null)
		{
			String[] day1 = sdf.format(hearingNotice.getHearingTime()).split("-");
			map.put("hearingTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日"+ day1[3] + "时" + day1[4] + "分");
		}
		map.put("hearingAddress", NullToString(hearingNotice.getHearingAddress()));
		String ifgk = hearingNotice.getPublicCondition();
		if(ifgk != null && "1".equals(ifgk))
		{
			map.put("publicCondition", "公开");
		}
		else
		{
			map.put("publicCondition", "不公开");
		}
		map.put("tzzcrName", "");
		map.put("tzzcrZw", "");
		if(hearingNotice.getHearingChairperson() != null && !"".equals(hearingNotice.getHearingChairperson()))
		{
			User user1 = userService.findUserById(hearingNotice.getHearingChairperson());
			if(user1 != null)
			{
				map.put("tzzcrName", NullToString(user1.getDisplayName()));
				map.put("tzzcrZw", "苏州工业园区安全生产监察大队");
			}
		}
		map.put("tzy1Name", "");
		map.put("tzy1Zw", "");
		String[] tzy =  hearingNotice.getHearingOfficer().replaceAll(" ", "").split(",");
		if(tzy.length >= 1)
		{
			User user2 = userService.findUserById(tzy[0]);
			if(user2 != null)
			{
				map.put("tzy1Name", NullToString(user2.getDisplayName()));
				map.put("tzy1Zw", "苏州工业园区安全生产监察大队");
			}
		}
		map.put("tzy2Name", "");
		map.put("tzy2Zw","");
		if(tzy.length >= 2)
		{
			User user3 = userService.findUserById(tzy[1]);
			if(user3 != null)
			{
				map.put("tzy2Name", NullToString(user3.getDisplayName()));
				map.put("tzy2Zw", "苏州工业园区安全生产监察大队");
			}
		}
		map.put("sjyName", "");
		map.put("sjyZw", "");
		if(hearingNotice.getClerk() != null && !"".equals(hearingNotice.getClerk()))
		{
			User user4 = userService.findUserById(hearingNotice.getClerk());
			if(user4 != null)
			{
				map.put("sjyName", NullToString(user4.getDisplayName()));
				map.put("sjyZw", "苏州工业园区安全生产监察大队");
			}
		}
		
		map.put("hearingAddress", NullToString(hearingNotice.getHearingAddress()));
		
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"听证会通知书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	/**
	 * 保存听证笔录
	 * @author luting
	 * 2015-10-27
	 */
	public InstrumentsInfo saveHearingNote(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,HearingNote hearingNote, String flag,String type,String root) throws IOException
	{
		String undertakerName = "";
		if(hearingNote.getUndertaker() != null)
		{
			String[] ss = hearingNote.getUndertaker().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				User user = userService.findUserById(s.trim());
				undertakerName += user.getDisplayName() + ",";
			}
		}
		hearingNote.setUndertakerName(undertakerName.substring(0,undertakerName.length()-1));
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		hearingNote.setRelatedId(instrumentsInfo.getId());
		hearingNote.setDelFlag(0);
		hearingNote.setCreateUserID(instrumentsInfo.getCreateUserID());
		hearingNote.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			hearingNoteService.save(hearingNote);
		}else{
			hearingNoteService.update(hearingNote);
		}
		//查询听证会通知书
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "18");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			HearingNotice hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
			if(hearingNotice != null)
			{
				instrumentsInfo.setNeedCheckUser("," + hearingNotice.getHearingChairperson() + ",");
				instrumentsInfoService.update(instrumentsInfo);
			}
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createHearingNote(instrumentsInfo, caseInfo, hearingNote, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核听证笔录
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void shenheHearingNote(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,HearingNote hearingNote,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书
				createHearingNote(instrumentsInfo, caseInfo, hearingNote, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		hearingNoteService.update(hearingNote);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成听证笔录
	 * @author luting
	 * 2015-10-27
	 * @throws IOException 
	 */
	public void createHearingNote(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,HearingNote hearingNote,String root) throws IOException
	{
				
		//生成文书
		map.put("caseName", NullToString(caseInfo.getCaseName()));
		map.put("tzjg", "苏州工业园区安全生产监督管理局");
		//查询听证会通知书
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "18");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("tzzcrName", "");
		map.put("hearingAddress", "");
		map.put("tzy1Name", "");
		map.put("tzy2Name", "");
		map.put("sjyName", "");
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			HearingNotice hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
			if(hearingNotice != null)
			{
				map.put("hearingAddress", NullToString(hearingNotice.getHearingAddress()));
				if(hearingNotice.getHearingChairperson() != null && !"".equals(hearingNotice.getHearingChairperson()))
				{
					User user1 = userService.findUserById(hearingNotice.getHearingChairperson());
					if(user1 != null)
					{
						map.put("tzzcrName", NullToString(user1.getDisplayName()));
					}
				}
				String[] tzy =  hearingNotice.getHearingOfficer().replaceAll(" ", "").split(",");
				if(tzy.length >= 1)
				{
					User user2 = userService.findUserById(tzy[0]);
					if(user2 != null)
					{
						map.put("tzy1Name", NullToString(user2.getDisplayName()));
					}
				}
				if(tzy.length >= 2)
				{
					User user3 = userService.findUserById(tzy[1]);
					if(user3 != null)
					{
						map.put("tzy2Name", NullToString(user3.getDisplayName()));
					}
				}
				User uu = userService.findUserById(instrumentsInfo.getCreateUserID());
				if(uu != null)
				{
					map.put("sjyName", NullToString(uu.getDisplayName()));
				}
			}
		}
		String inqueryPerson = hearingNote.getUndertaker();
		String[] ss = inqueryPerson.replaceAll(" ", "").split(",");
		map.put("zfry1", "");
		map.put("zfry1zh", "________");
		if(ss.length >= 1)
		{
			User user1 = userService.findUserById(ss[0]);
			if(user1 != null)
			{
				map.put("zfry1", NullToString(user1.getDisplayName()));
				map.put("zfry1zh", NullToDown(user1.getZfzh()));
			}
		}
		map.put("zfry2", "");
		map.put("zfry2zh","________");
		if(ss.length >= 2)
		{
			User user2 = userService.findUserById(ss[1]);
			if(user2 != null)
			{
				map.put("zfry2", NullToString(user2.getDisplayName()));
				map.put("zfry2zh", NullToDown(user2.getZfzh()));
			}
		}
		map.put("frxm", "");
		map.put("xb", "");
		map.put("nl", "");
		map.put("companyName", NullToString(caseInfo.getCompanyName()));
		if(caseInfo.getPersonType().equals("1"))
		{
			map.put("frxm", NullToString(caseInfo.getPerson()));
		}
		else
		{
			map.put("frxm", NullToString(caseInfo.getFddbr()));
		}
		if(caseInfo.getSex() != null && !"".equals(caseInfo.getSex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", caseInfo.getSex());
			map.put("xb", NullToString(codeService.findCodeValueByMap(m).getItemText()));
		}
		map.put("nl", NullToString(caseInfo.getAge()));
		map.put("startTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(hearingNote.getStartTime() != null)
		{
			String[] day1 = sdf.format(hearingNote.getStartTime()).split("-");
			map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日"+ day1[3] + "时" + day1[4] + "分");
		}
		map.put("endTime", "");
		if(hearingNote.getEndTime() != null)
		{
			String[] day2 = sdf.format(hearingNote.getEndTime()).split("-");
			map.put("endTime", day2[2] + "日"+ day2[3] + "时" + day2[4] + "分");
		}
		map.put("attorney1", NullToString(hearingNote.getAttorney1()));
		map.put("attorney1Sex","");
		if(hearingNote.getAttorney1Sex() != null && !"".equals(hearingNote.getAttorney1Sex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", hearingNote.getAttorney1Sex());
			map.put("attorney1Sex", codeService.findCodeValueByMap(m).getItemText());
		}
		map.put("attorney1Age", NullToString(hearingNote.getAttorney1Age()));
		map.put("attorney1Company", NullToString(hearingNote.getAttorney1Company()));
		map.put("attorney2", NullToString(hearingNote.getAttorney2()));
		map.put("attorney2Sex","");
		if(hearingNote.getAttorney2Sex() != null && !"".equals(hearingNote.getAttorney2Sex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", hearingNote.getAttorney2Sex());
			map.put("attorney2Sex", codeService.findCodeValueByMap(m).getItemText());
		}
		map.put("attorney2Age", NullToString(hearingNote.getAttorney2Age()));
		map.put("attorney2Company", NullToString(hearingNote.getAttorney2Company()));
		map.put("thirdPerson", NullToString(hearingNote.getThirdPerson()));
		map.put("otherParticipants", NullToString(hearingNote.getOtherParticipants()));
		map.put("hearingRecord", NullToString(hearingNote.getHearingRecord()));
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"听证笔录.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	
	/**
	 * 保存听证会报告书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveHearingReport(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,HearingReport hearingReport, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违听报字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监听报字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("0");
		}
		
		hearingReport.setRelatedId(instrumentsInfo.getId());
		hearingReport.setDelFlag(0);
		hearingReport.setCreateUserID(instrumentsInfo.getCreateUserID());
		hearingReport.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			hearingReportService.save(hearingReport);
		}else{
			hearingReportService.update(hearingReport);
		}
		//查询听证会通知书
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "18");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			HearingNotice hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
			if(hearingNotice != null)
			{
				instrumentsInfo.setNeedCheckUser(","+hearingNotice.getHearingChairperson()+",");
				instrumentsInfoService.update(instrumentsInfo);
			}
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createHearingReport(instrumentsInfo, caseInfo, hearingReport, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核听证会报告书
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheHearingReport(InstrumentsInfo in, CaseInfo ca,HearingReport he,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			he.setHearChairTime(checkTime);
			he.setHearChairComment(remark);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			he.setCheckTime(checkTime);
			he.setCheckComment(remark);
			he.setCheckPerson(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				//生成文书
				
				createHearingReport(in, ca, he, root);
				in.setIfPrint("1");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		hearingReportService.update(he);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成听证会报告书
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createHearingReport(InstrumentsInfo in, CaseInfo ca,HearingReport he,String root) throws IOException
	{
		//生成文书
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("tbh", NullToString(in.getAjh()));
		map.put("tbhNum", NullToString(in.getAjhNum()));
		map.put("caseName", NullToString(ca.getCaseName()));
		Map m = new HashMap();
		m.put("caseId", ca.getId());
		m.put("instrumentType", "18");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("tzzcrName", "");
		map.put("tzy1Name", "");
		map.put("tzy2Name", "");
		map.put("sjyName", "");
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			HearingNotice hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
			if(hearingNotice != null)
			{
				if(hearingNotice.getHearingChairperson() != null && !"".equals(hearingNotice.getHearingChairperson()))
				{
					User user1 = userService.findUserById(hearingNotice.getHearingChairperson());
					if(user1 != null)
					{
						map.put("tzzcrName", NullToString(user1.getDisplayName()));
					}
				}
				String[] tzy =  hearingNotice.getHearingOfficer().replaceAll(" ", "").split(",");
				if(tzy.length >= 1)
				{
					User user2 = userService.findUserById(tzy[0]);
					if(user2 != null)
					{
						map.put("tzy1Name", NullToString(user2.getDisplayName()));
					}
				}
				if(tzy.length >= 2)
				{
					User user3 = userService.findUserById(tzy[1]);
					if(user3 != null)
					{
						map.put("tzy2Name", NullToString(user3.getDisplayName()));
					}
				}
				if(hearingNotice.getClerk() != null && !"".equals(hearingNotice.getClerk()))
				{
					User user4 = userService.findUserById(hearingNotice.getClerk());
					if(user4 != null)
					{
						map.put("sjyName", NullToString(user4.getDisplayName()));
					}
				}
			}
		}
		map.put("hearingSummary", NullToString(he.getHearingSummary()));
		map.put("hearChairComment", NullToString(he.getHearChairComment()));
		map.put("hearChairTime", changeTimeToZw(he.getHearChairTime()));
		map.put("checkComment", NullToString(he.getCheckComment()));
		map.put("checkTime", changeTimeToZw(he.getCheckTime()));
		String[] s = fileDocUtil.createDocFile(root+"听证会报告书.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	
	
	/**
	 * 保存案件处理呈批表
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveCaseProcessApproval(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,CaseProcessApproval caseProcessApproval, String flag,String type,String root) throws IOException
	{
		String undertakerName = caseProcessApproval.getUndertakerName1() ;
		if(caseProcessApproval.getUndertaker() != null)
		{
			User user = userService.findUserById(caseProcessApproval.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		caseProcessApproval.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+caseProcessApproval.getUndertaker().replaceAll(" ", "")+",");
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违处呈字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监处呈字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		caseProcessApproval.setRelatedId(instrumentsInfo.getId());
		caseProcessApproval.setDelFlag(0);
		caseProcessApproval.setCreateUserID(instrumentsInfo.getCreateUserID());
		caseProcessApproval.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			caseProcessApprovalService.save(caseProcessApproval);
		}else{
			caseProcessApprovalService.update(caseProcessApproval);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createCaseProcessApproval(instrumentsInfo, caseInfo, caseProcessApproval, root);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核案件处理呈批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheCaseProcessApproval(InstrumentsInfo in, CaseInfo ca,CaseProcessApproval capr,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			capr.setUnderTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			capr.setCheckComment(remark);
			capr.setCheckPerson(user.getId());
			capr.setCheckTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2"))//局长审核
		{
			capr.setApproverComment(remark);
			capr.setApproverPerson(user.getId());
			capr.setApproverTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				createCaseProcessApproval(in, ca, capr, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		caseProcessApprovalService.update(capr);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成案件处理呈批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createCaseProcessApproval(InstrumentsInfo in, CaseInfo ca,CaseProcessApproval capr,String root) throws IOException
	{
		//生成文书
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("cch", NullToString(in.getAjh()));
		map.put("cchNum", NullToString(in.getAjhNum()));
		map.put("caseName", NullToString(ca.getCaseName()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("companyName", "———");
			map.put("companyAddress", "———");
			map.put("fddbr", "———");
			map.put("zw", "———");
			map.put("yb", "———");
			map.put("person", NullToHx(ca.getPerson()));
			map.put("age", NullToHx(ca.getAge()));
			Map m = new HashMap();
			if(ca.getSex() != null && !"".equals(ca.getSex()))
			{
				m.put("codeName", "性别");
				m.put("itemValue", ca.getSex());
				map.put("sex", codeService.findCodeValueByMap(m).getItemText());
			}
			else
			{
				map.put("sex", "———");
			}
			map.put("cfrdw", NullToString(ca.getCompanyName()));
			map.put("dwdz", NullToString(ca.getCompanyAddress()));
			map.put("jtzz", NullToString(ca.getAddress()));
			map.put("lxdh", NullToString(ca.getTele()));
			map.put("cfryb", NullToString(ca.getZipCode()));
		}
		else
		{
			map.put("companyName", NullToHx(ca.getCompanyName()));
			map.put("fddbr", NullToHx(ca.getFddbr()));
			map.put("zw",NullToHx(ca.getZw()) );
			map.put("companyAddress",NullToHx(ca.getCompanyAddress()) );
			map.put("yb", NullToHx(ca.getZipCode()));
			map.put("person", "———");
			map.put("age", "———");
			map.put("sex", "———");
			map.put("cfrdw", "———");
			map.put("dwdz", "———");
			map.put("jtzz", "———");
			map.put("lxdh", "———");
			map.put("cfryb", "———");
		}
		map.put("wfss",NullToString(capr.getWfss()));
		map.put("sbyj", NullToString(capr.getDsrsbyj()));
		map.put("undertakerComment", NullToString(capr.getUndertakerComment()));
		map.put("underTime", changeTimeToZw(capr.getUnderTime()));
		map.put("checkComment", NullToString(capr.getCheckComment()));
		map.put("checkTime", changeTimeToZw(capr.getCheckTime()));
		map.put("approverComment", NullToString(capr.getApproverComment()));
		map.put("approverTime", changeTimeToZw(capr.getApproverTime()));
		String[] s = fileDocUtil.createDocFile(root+"案件处理呈批表.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存行政处罚集体讨论记录
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePenBraRec(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PenBraRec penBraRec, String flag,String type,String root) throws IOException
	{
		if(penBraRec.getReportPerson() != null)
		{
			User user = userService.findUserById(penBraRec.getReportPerson().trim());
			penBraRec.setReportPersonName(user.getDisplayName());
		}
		if(penBraRec.getChairperson() != null)
		{
			User user = userService.findUserById(penBraRec.getChairperson().trim());
			penBraRec.setChairpersonName(user.getDisplayName());
		}
		String needcheck = ","+penBraRec.getAttendId().replaceAll(" ", "")+",";
		instrumentsInfo.setNeedCheckUser(needcheck.replaceAll(instrumentsInfo.getCreateUserID()+",", ""));
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违管询");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监管询");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		penBraRec.setRelatedId(instrumentsInfo.getId());
		penBraRec.setDelFlag(0);
		penBraRec.setCreateUserID(instrumentsInfo.getCreateUserID());
		penBraRec.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			penBraRecService.save(penBraRec);
		}else{
			penBraRecService.update(penBraRec);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPenBraRec(instrumentsInfo, caseInfo, penBraRec, root);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核行政处罚集体讨论记录
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenhePenBraRec(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PenBraRec penBraRec, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String userId = user.getId();
		String needCheck = instrumentsInfo.getNeedCheckUser();
		
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8"))//法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3"))//电子签名确认
		{
			if(needCheck.equals("," + userId + ","))
			{
				if(result.equals("0"))//审核通过
				{
					instrumentsInfo.setIfCheck("5");
					checkrecord.setCheckResult("审核通过");
					
					//生成文书	
					createPenBraRec(instrumentsInfo, caseInfo, penBraRec, root);
					
					instrumentsInfo.setIfPrint("1");
					instrumentsInfo.setDzqmCheck("2");
				}
				else
				{
					instrumentsInfo.setIfCheck("4");
					checkrecord.setCheckResult("审核不通过");
				}
			}
			else
			{
				if(result.equals("0"))//审核通过
				{
					needCheck = needCheck.replace(userId + ",", "");
					instrumentsInfo.setNeedCheckUser(needCheck);
					instrumentsInfo.setIfCheck("3");
					checkrecord.setCheckResult("审核通过");
					
				}
				else
				{
					instrumentsInfo.setIfCheck("4");
					checkrecord.setCheckResult("审核不通过");
				}
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		penBraRecService.update(penBraRec);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
		
	}
	
	/**
	 * 生成行政处罚集体讨论记录
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createPenBraRec(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PenBraRec penBraRec,String root) throws IOException
	{
					
		//生成文书	
		map.put("caseName", NullToString(caseInfo.getCaseName()));
		map.put("startTime", "");
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		if(penBraRec.getStartTime() != null)
		{
			String[] day1 = sdf.format(penBraRec.getStartTime()).split("-");
			map.put("startTime", day1[0] + "年" + day1[1] + "月" + day1[2] + "日"+ day1[3] + "时" + day1[4] + "分");
		}
		map.put("endTime", "");
		if(penBraRec.getEndTime() != null)
		{
			String[] day2 = sdf.format(penBraRec.getEndTime()).split("-");
			map.put("endTime", day2[2] + "日"+ day2[3] + "时" + day2[4] + "分");
		}
		map.put("discussionAddress", NullToString(penBraRec.getDiscussionAddress()));
		if(penBraRec.getChairperson() != null && !"".equals(penBraRec.getChairperson()))
		{
			User uu = userService.findUserById(penBraRec.getChairperson());
			map.put("chairperson",NullToString(uu.getDisplayName()));
		}
		if(penBraRec.getReportPerson() != null && !"".equals(penBraRec.getReportPerson()))
		{
			User uu = userService.findUserById(penBraRec.getReportPerson());
			map.put("reportPerson", NullToString(uu.getDisplayName()));
		}
		if(penBraRec.getRecordPerson() != null && !"".equals(penBraRec.getRecordPerson()))
		{
			User uu = userService.findUserById(penBraRec.getRecordPerson());
			map.put("recordPerson", NullToString(uu.getDisplayName()));
		}
		
		String[] attendIds = penBraRec.getAttendId().replaceAll(" ", "").split(",");
		String[] attendNames = penBraRec.getAttendName().replaceAll(" ", "").split(",");
		String name = "";
		String name1 = "";
		for(int i=0;i<attendIds.length;i++)
		{
			User user = userService.findUserById(attendIds[i]);
			if(user.getDeptCode().equals("002001")&&user.getSortSq().equals("1"))
			{
				name += attendNames[i] + "  苏州工业园区综合行政执法局（安监局） 局长";
			}
			else
			{
				name1 += attendNames[i] + "、";
			}
		}
		if(name.length() != 0)
		{
			name = name + "\r\n";
		}
		if(name1.length() != 0)
		{
			name1 = name1 + "苏州工业园区综合行政执法局（安监局）、苏州工业园区安全生产监察大队";
		}
		map.put("attendNamePosition",name+name1);
		map.put("discussionContent", NullToString(penBraRec.getDiscussionContent()));
		map.put("discussionRecord", NullToString(penBraRec.getDiscussionRecord()));
		map.put("conclusionComment", NullToString(penBraRec.getConclusionComment()));
		String[] s = fileDocUtil.createDocFile(root+"行政处罚集体讨论记录.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	/**
	 * 保存行政（当场）处罚决定书（单位）
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveSpoPenDecCom(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpoPenDecCom spoPenDecCom, String flag,String type,String root) throws IOException
	{
		String undertakerName = spoPenDecCom.getLawOfficerName1() ;
		if(spoPenDecCom.getLawOfficer() != null)
		{
			User user = userService.findUserById(spoPenDecCom.getLawOfficer().trim());
			undertakerName += "," +user.getDisplayName();
		}
		spoPenDecCom.setLawOfficerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+spoPenDecCom.getLawOfficer().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违罚当字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监罚当字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		spoPenDecCom.setRelatedId(instrumentsInfo.getId());
		spoPenDecCom.setDelFlag(0);
		spoPenDecCom.setCreateUserID(instrumentsInfo.getCreateUserID());
		spoPenDecCom.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			spoPenDecComService.save(spoPenDecCom);
		}else{
			spoPenDecComService.update(spoPenDecCom);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createSpoPenDecCom(instrumentsInfo, caseInfo, spoPenDecCom, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核行政（当场）处罚决定书（单位）
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheSpoPenDecCom(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpoPenDecCom spoPenDecCom,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //队长审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setDzCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书
				createSpoPenDecCom(instrumentsInfo, caseInfo, spoPenDecCom, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setJzCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		instrumentsInfoService.update(instrumentsInfo);
		spoPenDecComService.update(spoPenDecCom);

		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成行政（当场）处罚决定书（单位）
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createSpoPenDecCom(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpoPenDecCom spoPenDecCom,String root) throws IOException
	{
				
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("fdh", NullToString(instrumentsInfo.getAjh()));
		map.put("fdhNum", NullToString(instrumentsInfo.getAjhNum()));
		map.put("companyName", NullToString(caseInfo.getCompanyName()));
		map.put("companyAddress", NullToString(caseInfo.getCompanyAddress()));
		map.put("companyZipCode", NullToString(caseInfo.getZipCode()));
		map.put("fddbr", NullToString(caseInfo.getFddbr()));
		map.put("zw", NullToString(caseInfo.getZw()));
		map.put("lxdh", NullToString(caseInfo.getTele()));
		
		map.put("xzcf", NullToString(spoPenDecCom.getAdminPenalties()));
		//查询行政处罚告知书
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "15");
		String wfss = "";
		map.put("gd", "");
		map.put("zfyj", "");
		map.put("xzcf","");
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list1.size() != 0)
		{
			m.put("relatedId", list1.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			wfss = "违法事实：" + NullToString(penaltyNotice.getWfxw());
			
			String lawBasic = "";
			if(penaltyNotice.getLawBasic() != null)
			{
				String[] ss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						lawBasic += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(lawBasic.length() != 0)
				{
					lawBasic = lawBasic.substring(0,lawBasic.length()-1);
				}
			}
			else
			{
				lawBasic =  NullToString(penaltyNotice.getLawName());
			}
			wfss += "；执法依据" + lawBasic;
			
			String gd = "";
			if(penaltyNotice.getProvision() != null)
			{
				String[] ss = penaltyNotice.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						gd += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(gd.length() != 0)
				{
					gd = gd.substring(0,gd.length()-1);
				}
			}
			else
			{
				gd =  NullToString(penaltyNotice.getProName());
			}
			map.put("gd", gd);
			
			
			String zfyj = "";
			if(penaltyNotice.getLawBasic() != null)
			{
				String[] ss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						zfyj += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision())  + ",";
					}
				}
				if(zfyj.length() != 0)
				{
					zfyj = zfyj.substring(0,zfyj.length()-1);
				}
			}
			else
			{
				zfyj =  NullToString(penaltyNotice.getLawName());
			}
			map.put("zfyj", zfyj);
		}
		map.put("wfss",wfss);
		
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
						
		String[] s = fileDocUtil.createDocFile(root+"行政（当场）处罚决定书（单位）" + spoPenDecCom.getFineMethod() + ".docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
	}
	
	
	
	/**
	 * 保存行政（当场）处罚决定书（个人）
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveSpoPenDecPer(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpoPenDecPer spoPenDecPer, String flag,String type,String root) throws IOException
	{
		String undertakerName = spoPenDecPer.getLawOfficerName1();
		if(spoPenDecPer.getLawOfficer() != null)
		{
			User user = userService.findUserById(spoPenDecPer.getLawOfficer().trim());
			undertakerName += "," +user.getDisplayName();
		}
		spoPenDecPer.setLawOfficerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+spoPenDecPer.getLawOfficer().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违罚当字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监罚当字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		spoPenDecPer.setRelatedId(instrumentsInfo.getId());
		spoPenDecPer.setDelFlag(0);
		spoPenDecPer.setCreateUserID(instrumentsInfo.getCreateUserID());
		spoPenDecPer.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			spoPenDecPerService.save(spoPenDecPer);
		}else{
			spoPenDecPerService.update(spoPenDecPer);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createSpoPenDecPer(instrumentsInfo, caseInfo, spoPenDecPer, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核行政（当场）处罚决定书（个人）
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void shenheSpoPenDecPer(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpoPenDecPer spoPenDecPer, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		String status = instrumentsInfo.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setNeedCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setDzqmCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队长审核
		{
			if(result.equals("0"))
			{
				instrumentsInfo.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				instrumentsInfo.setDzCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			if(result.equals("0"))//审核通过
			{
				instrumentsInfo.setIfCheck("5");
				checkrecord.setCheckResult("审核通过");
				
				//生成文书	
				createSpoPenDecPer(instrumentsInfo, caseInfo, spoPenDecPer, root);
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setJzCheck("2");
			}
			else
			{
				instrumentsInfo.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		spoPenDecPerService.update(spoPenDecPer);
		instrumentsInfoService.update(instrumentsInfo);

		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成行政（当场）处罚决定书（个人）
	 * @author luting
	 * 2015-10-24
	 * @throws IOException 
	 */
	public void createSpoPenDecPer(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpoPenDecPer spoPenDecPer, String root) throws IOException
	{
				
		//生成文书	
	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("fdh", NullToString(instrumentsInfo.getAjh()));
		map.put("fdhNum", NullToString(instrumentsInfo.getAjhNum()));
		map.put("person", NullToString(caseInfo.getPerson()));
		map.put("age", NullToString(caseInfo.getAge()));
		Map m = new HashMap();
		if(caseInfo.getSex() != null && !"".equals(caseInfo.getSex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", caseInfo.getSex());
			map.put("sex", NullToString(codeService.findCodeValueByMap(m).getItemText()));
		}
		else
		{
			map.put("sex", "");
		}
		map.put("cfrdw", NullToString(caseInfo.getCompanyName()));
		map.put("dwdz", NullToString(caseInfo.getCompanyAddress()));
		map.put("jtzz", NullToString(caseInfo.getAddress()));
		map.put("lxdh", NullToString(caseInfo.getTele()));
		map.put("zw", NullToString(caseInfo.getZw()));
		map.put("sfzh", NullToString(caseInfo.getSfzh()));
		//查询行政处罚告知书
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "15");
		map.put("gd", "");
		map.put("zfyj", "");
		map.put("xzcf","");
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list1.size() != 0)
		{
			m.put("relatedId", list1.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			
			String gd = "";
			if(penaltyNotice.getProvision() != null)
			{
				String[] ss = penaltyNotice.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						gd += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision())  + ",";
					}
				}
				if(gd.length() != 0)
				{
					gd = gd.substring(0,gd.length()-1);
				}
			}
			else
			{
				gd =  NullToString(penaltyNotice.getProName());
			}
			map.put("gd", gd);
			
			String zfyj = "";
			if(penaltyNotice.getLawBasic() != null)
			{
				String[] ss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						zfyj += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(zfyj.length() != 0)
				{
					zfyj = zfyj.substring(0,zfyj.length()-1);
				}
			}
			else
			{
				zfyj =  NullToString(penaltyNotice.getLawName());
			}
			map.put("zfyj", zfyj);
			
			map.put("wfss",NullToString(penaltyNotice.getWfxw()));
		}
		
		map.put("xzcf", NullToString(spoPenDecPer.getAdminPenalties()));
		
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"行政（当场）处罚决定书（个人）" + spoPenDecPer.getFineMethod() + ".docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);

	}
	
	
	/**
	 * 保存行政处罚决定书（单位）
	 * @author luting
	 * 2015-10-24
	 */ 
	public InstrumentsInfo savePenDecCom(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PenDecCom penDecCom, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违罚字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监罚字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("1");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		penDecCom.setRelatedId(instrumentsInfo.getId());
		penDecCom.setDelFlag(0);
		penDecCom.setCreateUserID(instrumentsInfo.getCreateUserID());
		penDecCom.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			penDecComService.save(penDecCom);
		}else{
			penDecComService.update(penDecCom);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPenDecCom(instrumentsInfo, caseInfo, penDecCom, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核行政处罚决定书（单位）
	 * @author luting
	 * 2015-10-28
	 * @throws IOException 
	 */
	public void shenhePenDecCom(InstrumentsInfo in, CaseInfo ca,PenDecCom pen,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书	
				createPenDecCom(in, ca, pen, root);
				
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		penDecComService.update(pen);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成行政处罚决定书（单位）
	 * @author luting
	 * 2015-10-28
	 * @throws IOException 
	 */
	public void createPenDecCom(InstrumentsInfo in, CaseInfo ca,PenDecCom pen,String root) throws IOException
	{
		//生成文书	
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("gfh", NullToString(in.getAjh()));
		map.put("gfhNum", NullToString(in.getAjhNum()));
		map.put("companyName", NullToString(ca.getCompanyName()));
		map.put("companyAddress", NullToString(ca.getCompanyAddress()));
		map.put("companyZipCode", NullToString(ca.getZipCode()));
		map.put("fddbr", NullToString(ca.getFddbr()));
		map.put("zw", NullToString(ca.getZw()));
		map.put("lxdh", NullToString(ca.getTele()));
		map.put("wfss",NullToString(pen.getWfss()));
		//查询行政处罚告知书
		Map m = new HashMap();
		m.put("caseId", ca.getId());
		m.put("instrumentType", "15");
		map.put("gd", "");
		map.put("zfyj", "");
		map.put("xzcf","");
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list1.size() != 0)
		{
			m.put("relatedId", list1.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			
			String gd = "";
			if(penaltyNotice.getProvision() != null)
			{
				String[] ss = penaltyNotice.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						gd += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(gd.length() != 0)
				{
					gd = gd.substring(0,gd.length()-1);
				}
			}
			else
			{
				gd =  NullToString(penaltyNotice.getProName());
			}
			map.put("gd", gd);
			
			
			String zfyj = "";
			if(penaltyNotice.getLawBasic() != null)
			{
				String[] ss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						zfyj += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(zfyj.length() != 0)
				{
					zfyj = zfyj.substring(0,zfyj.length()-1);
				}
			}
			else
			{
				zfyj =  NullToString(penaltyNotice.getLawName());
			}
			map.put("zfyj", zfyj);
		}
		
		map.put("xzcf", NullToString(pen.getAdminPenalties()));
		
		map.put("time", changeTimeToZw(in.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"行政处罚决定书（单位）.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	
	/**
	 * 保存行政处罚决定书（个人）
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePenDecPer(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PenDecPer penDecPer, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违罚字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监罚字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("1");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		penDecPer.setRelatedId(instrumentsInfo.getId());
		penDecPer.setDelFlag(0);
		penDecPer.setCreateUserID(instrumentsInfo.getCreateUserID());
		penDecPer.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			penDecPerService.save(penDecPer);
		}else{
			penDecPerService.update(penDecPer);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPenDecPer(instrumentsInfo, caseInfo, penDecPer, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核行政处罚决定书（个人）
	 * @author luting
	 * 2015-10-28
	 * @throws IOException 
	 */
	public void shenhePenDecPer(InstrumentsInfo in, CaseInfo ca,PenDecPer pen,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书	
				createPenDecPer(in, ca, pen, root);
				
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		penDecPerService.update(pen);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成行政处罚决定书（个人）
	 * @author luting
	 * 2015-10-28
	 * @throws IOException 
	 */
	public void createPenDecPer(InstrumentsInfo in, CaseInfo ca,PenDecPer pen,String root) throws IOException
	{
		//生成文书	
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("gfh", NullToString(in.getAjh()));
		map.put("gfhNum", NullToString(in.getAjhNum()));
		map.put("person", NullToString(ca.getPerson()));
		map.put("age", NullToString(ca.getAge()));
		Map m = new HashMap();
		if(ca.getSex() != null && !"".equals(ca.getSex()))
		{
			m.put("codeName", "性别");
			m.put("itemValue", ca.getSex());
			map.put("sex", NullToString(codeService.findCodeValueByMap(m).getItemText()));
		}
		else
		{
			map.put("sex", "");
		}
		map.put("cfrdw", NullToString(ca.getCompanyName()));
		map.put("dwdz", NullToString(ca.getCompanyAddress()));
		map.put("jtzz", NullToString(ca.getAddress()));
		map.put("lxdh", NullToString(ca.getTele()));
		map.put("zw", NullToString(ca.getZw()));
		map.put("yb", NullToString(ca.getZipCode()));
		map.put("wfss",NullToString(pen.getWfss()));
		//查询行政处罚告知书
		m.put("caseId", ca.getId());
		m.put("instrumentType", "15");
		map.put("gd", "");
		map.put("zfyj", "");
		map.put("xzcf","");
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list1.size() != 0)
		{
			m.put("relatedId", list1.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			
			String gd = "";
			if(penaltyNotice.getProvision() != null)
			{
				String[] ss = penaltyNotice.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						gd += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(gd.length() != 0)
				{
					gd = gd.substring(0,gd.length()-1);
				}
			}
			else
			{
				gd =  NullToString(penaltyNotice.getProName());
			}
			map.put("gd", gd);
			
			String zfyj = "";
			if(penaltyNotice.getLawBasic() != null)
			{
				String[] ss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						zfyj += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision()) + ",";
					}
				}
				if(zfyj.length() != 0)
				{
					zfyj = zfyj.substring(0,zfyj.length()-1);
				}
			}
			else
			{
				zfyj =  NullToString(penaltyNotice.getLawName());
			}
			map.put("zfyj", zfyj);
		}
		
		map.put("xzcf", NullToString(pen.getAdminPenalties()));
		
		map.put("time", changeTimeToZw(in.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"行政处罚决定书（个人）.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}

	/**
	 * 罚款催缴通知书
	 * @author luting
	 * 2015-10-24
	 * 
	 */
	public InstrumentsInfo saveFkcjd(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违管催");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监管催");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createFkcjd(instrumentsInfo, caseInfo, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核罚款催缴通知书
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheFkcjd(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createFkcjd(instrumentsInfo, caseInfo, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成罚款催缴通知书
	 * @author luting
	 * 2015-10-24
	 */
	public void createFkcjd(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("gch", NullToString(instrumentsInfo.getAjh()));
		map.put("gchNum", NullToString(instrumentsInfo.getAjhNum()));
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "23");//行政（当场）处罚决定书（单位）
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "24");//行政（当场）处罚决定书（个人）
		List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "25");//行政处罚决定书（单位）
		List<InstrumentsInfo> list3 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "26");//行政处罚决定书（个人）
		List<InstrumentsInfo> list4 = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("person", "");
		map.put("jdtime", "");
		map.put("jush", "");
		map.put("fkqx", "");
		Calendar ca = Calendar.getInstance();   
		if(list1.size() != 0)
		{
			m.put("relatedId", list1.get(0).getId());
			SpoPenDecCom spoPenDecCom = (SpoPenDecCom) spoPenDecComService.findSpoPenDecCom(m).get(0);
			map.put("person", NullToString(caseInfo.getCompanyName()));
			map.put("jdtime", changeTimeToZw(list1.get(0).getTime()));
			map.put("jush", NullToString(list1.get(0).getWsh()) );
			ca.setTime(list1.get(0).getTime());
			ca.add(Calendar.DATE,  15);
			map.put("fkqx", sdf.format(ca.getTime()));
		}
		else if(list2.size() != 0)//行政（当场）处罚决定书（个人）
		{
			m.put("relatedId", list2.get(0).getId());
			SpoPenDecPer spoPenDecPer = (SpoPenDecPer) spoPenDecPerService.findSpoPenDecPer(m).get(0);
			map.put("person", NullToString(caseInfo.getPerson()));
			map.put("jdtime", changeTimeToZw(list2.get(0).getTime()));
			map.put("jush", NullToString(list2.get(0).getWsh()));
			ca.setTime(list2.get(0).getTime());
			ca.add(Calendar.DATE,  15);
			map.put("fkqx", sdf.format(ca.getTime()));
		}
		else if(list3.size() != 0)//行政处罚决定书（单位）
		{
			m.put("relatedId", list3.get(0).getId());
			PenDecCom penDecCom = (PenDecCom) penDecComService.findPenDecCom(m).get(0);
			map.put("person", NullToString(caseInfo.getCompanyName()));
			map.put("jdtime", changeTimeToZw(list3.get(0).getTime()));
			map.put("jush", NullToString(list3.get(0).getWsh() ));
			ca.setTime(list3.get(0).getTime());
			ca.add(Calendar.DATE,  15);
			map.put("fkqx", sdf.format(ca.getTime()));
		}
		else if(list4.size() != 0)//行政处罚决定书（个人）
		{
			m.put("relatedId", list4.get(0).getId());
			PenDecPer penDecPer = (PenDecPer) penDecPerService.findPenDecPer(m).get(0);
			map.put("person", NullToString(caseInfo.getPerson()));
			map.put("jdtime", changeTimeToZw(list4.get(0).getTime()));
			map.put("jush", NullToString(list4.get(0).getWsh()));
			ca.setTime(list4.get(0).getTime());
			ca.add(Calendar.DATE,  15);
			map.put("fkqx", sdf.format(ca.getTime()));
		}
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"罚款催缴通知书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	/**
	 * 保存延期（分期）缴纳罚款审批表
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePosFinApp(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PosFinApp posFinApp, String flag,String type,String root) throws IOException
	{
		String undertakerName = posFinApp.getUndertakerName1() ;
		if(posFinApp.getUndertaker() != null)
		{
			User user = userService.findUserById(posFinApp.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		posFinApp.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+posFinApp.getUndertaker().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		posFinApp.setRelatedId(instrumentsInfo.getId());
		posFinApp.setDelFlag(0);
		posFinApp.setCreateUserID(instrumentsInfo.getCreateUserID());
		posFinApp.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			posFinAppService.save(posFinApp);
		}else{
			posFinAppService.update(posFinApp);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPosFinApp(instrumentsInfo, caseInfo, posFinApp, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核延期（分期）缴纳罚款审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenhePosFinApp(InstrumentsInfo in, CaseInfo ca,PosFinApp pos ,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			pos.setUnderTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			pos.setCheckComment(remark);
			pos.setCheckPerson(user.getId());
			pos.setCheckTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			pos.setApproverComment(remark);
			pos.setApprover(user.getId());
			pos.setApproverTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				createPosFinApp(in, ca, pos, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		posFinAppService.update(pos);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成延期（分期）缴纳罚款审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createPosFinApp(InstrumentsInfo in, CaseInfo ca,PosFinApp pos ,String root) throws IOException
	{
		//生成文书
		map.put("caseName", NullToString(ca.getCaseName()));
		Map m = new HashMap();
		m.put("caseId", ca.getId());
		m.put("instrumentType", "15");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		String wfss = "";
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			wfss = "违法事实：" + NullToString(penaltyNotice.getWfxw()) + ",处罚决定："+ NullToString(penaltyNotice.getAdminPenality()) ;
		}
		map.put("wfss", wfss);
		m.put("instrumentType", "23");//行政（当场）处罚决定书（单位）
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "24");//行政（当场）处罚决定书（个人）
		List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "25");//行政处罚决定书（单位）
		List<InstrumentsInfo> list3 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "26");//行政处罚决定书（个人）
		List<InstrumentsInfo> list4 = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("person", "");
		map.put("jdswh", "");
		if(list1.size() != 0)
		{
			map.put("person", NullToString(ca.getCompanyName()));
			map.put("jdswh", NullToString(list1.get(0).getWsh()) );
		}
		else if(list2.size() != 0)//行政（当场）处罚决定书（个人）
		{
			map.put("person", NullToString(ca.getPerson()));
			map.put("jdswh", NullToString(list2.get(0).getWsh() ));
		}
		else if(list3.size() != 0)//行政处罚决定书（单位）
		{
			map.put("person", NullToString(ca.getCompanyName()));
			map.put("jdswh", NullToString(list3.get(0).getWsh() ));
		}
		else if(list4.size() != 0)//行政处罚决定书（个人）
		{
			map.put("person", NullToString(ca.getPerson()));
			map.put("jdswh",NullToString(list4.get(0).getWsh() ));
		}
		map.put("address",NullToString(ca.getCompanyAddress()) );
		map.put("reason", NullToString(pos.getReason()));
		map.put("undertakerComment", NullToString(pos.getUndertakerComment()));
		map.put("underTime", changeTimeToZw(pos.getUnderTime()));
		map.put("checkComment", NullToString(pos.getCheckComment()));
		map.put("checkTime", changeTimeToZw(pos.getCheckTime()));
		map.put("approverComment", NullToString(pos.getApproverComment()));
		map.put("approverTime", changeTimeToZw(pos.getApproverTime()));
		String[] s = fileDocUtil.createDocFile(root+"延期（分期）缴纳罚款审批表.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存延期（分期）缴纳罚款批准书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo savePosFinRat(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,PosFinRat posFinRat, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违缴批字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监缴批字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		posFinRat.setRelatedId(instrumentsInfo.getId());
		posFinRat.setDelFlag(0);
		posFinRat.setCreateUserID(instrumentsInfo.getCreateUserID());
		posFinRat.setCreateTime(instrumentsInfo.getCreateTime());
		if(posFinRat.getRepayPeriod() != null && !"".equals(posFinRat.getRepayPeriod()))
		{
			String[] repayPeriod = posFinRat.getRepayPeriod().replaceAll(" ", "").split("-");
			posFinRat.setPostponeYear(numToUpper(Integer.parseInt(repayPeriod[0])));
			posFinRat.setPostponeMonth(monthToUppder(Integer.parseInt(repayPeriod[1])));
			posFinRat.setPostponeDate(dayToUppder(Integer.parseInt(repayPeriod[2])));
		}
		if ("add".equalsIgnoreCase(flag)){
			posFinRatService.save(posFinRat);
		}else{
			posFinRatService.update(posFinRat);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createPosFinRat(instrumentsInfo, caseInfo, posFinRat, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核延期（分期）缴纳罚款批准书
	 * @author luting
	 * 2015-10-24
	 */
	public void shenhePosFinRat(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, PosFinRat posFinRat,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createPosFinRat(instrumentsInfo, caseInfo, posFinRat, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成延期（分期）缴纳罚款批准书
	 * @author luting
	 * 2015-10-24
	 */
	public void createPosFinRat(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, PosFinRat posFinRat,String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("jph", NullToString(instrumentsInfo.getAjh()));
		map.put("jphNum", NullToString(instrumentsInfo.getAjhNum()));
		
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "23");//行政（当场）处罚决定书（单位）
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "24");//行政（当场）处罚决定书（个人）
		List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "25");//行政处罚决定书（单位）
		List<InstrumentsInfo> list3 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "26");//行政处罚决定书（个人）
		List<InstrumentsInfo> list4 = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("person", "");
		map.put("fkrq", "");
		map.put("jdswh", "");
		Calendar ca = Calendar.getInstance();   
		if(list1.size() != 0)
		{
			map.put("person", NullToString(caseInfo.getCompanyName()));
			map.put("fkrq", changeTimeToZw(list1.get(0).getTime()));
			map.put("jdswh", NullToString(list1.get(0).getWsh() ));
		}
		else if(list2.size() != 0)//行政（当场）处罚决定书（个人）
		{
			map.put("person", NullToString(caseInfo.getPerson()));
			map.put("fkrq", changeTimeToZw(list2.get(0).getTime()));
			map.put("jdswh", NullToString(list2.get(0).getWsh()) );
		}
		else if(list3.size() != 0)//行政处罚决定书（单位）
		{
			map.put("person", NullToString(caseInfo.getCompanyName()));
			map.put("fkrq", changeTimeToZw(list3.get(0).getTime()));
			map.put("jdswh", NullToString(list3.get(0).getWsh()));
		}
		else if(list4.size() != 0)//行政处罚决定书（个人）
		{
			map.put("person", NullToString(caseInfo.getPerson()));
			map.put("fkrq", changeTimeToZw(list4.get(0).getTime()));
			map.put("jdswh", NullToString(list4.get(0).getWsh() ));
		}
		
		map.put("fineUppercase", NullToString(posFinRat.getFineUppercase()));
		
		if(posFinRat.getPostponeMethod().equals("0"))
		{
			if(posFinRat.getRepayPeriod() != null && !"".equals(posFinRat.getRepayPeriod()))
			{
				map.put("repayPeriod1", posFinRat.getPostponeYear() + "年" + posFinRat.getPostponeMonth() + "月" + posFinRat.getPostponeDate() + "日");
			}
			else
			{
				map.put("repayPeriod1", "________________");
			}
			map.put("stageLength", "________________");
			map.put("repayPeriod2", "________________");
			map.put("pay", "________________");
			map.put("noPay","________________");
		}
		else
		{
			map.put("repayPeriod1", "________________");
			map.put("stageLength", NullToString(posFinRat.getStageLength()));
			if(posFinRat.getRepayPeriod() != null && !"".equals(posFinRat.getRepayPeriod()))
			{
				map.put("repayPeriod2", posFinRat.getPostponeYear() + "年" + posFinRat.getPostponeMonth() + "月" + posFinRat.getPostponeDate() + "日");
			}
			else
			{
				map.put("repayPeriod2", "________________");
			}
			map.put("pay", NullToString(posFinRat.getPay()));
			map.put("noPay", NullToString(posFinRat.getNoPay()));
		}
		
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"延期（分期）缴纳罚款批准书" + posFinRat.getPostponeMethod() + ".docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	/***
     * <b>function:</b> 将数字转化为大写
     * @author luting
     * 2015-10-30
     * @param num 数字
     * @return 转换后的大写数字
     */
	public static String numToUpper(int num) {
        String u[] = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
        char[] str = String.valueOf(num).toCharArray();
        String rstr = "";
        for (int i = 0; i < str.length; i++) {
            rstr = rstr + u[Integer.parseInt(str[i] + "")];
        }
        return rstr;
    }
 
    /***
     * <b>function:</b> 月转化为大写
      * @author luting
     * 2015-10-30
     * @param month 月份
     * @return 返回转换后大写月份
     */
    public static String monthToUppder(int month) {
        if (month < 10) {
            return numToUpper(month);
        } else if (month == 10) {
            return "拾";
        } else {
            return "拾" + numToUpper(month - 10);
        }
    }
 
    /***
     * <b>function:</b> 日转化为大写
      * @author luting
     * 2015-10-30
     * @param day 日期
     * @return 转换大写的日期格式
     */
    public static String dayToUppder(int day) {
        if (day < 20) {
            return monthToUppder(day);
        } else {
            char[] str = String.valueOf(day).toCharArray();
            if (str[1] == '0') {
                return numToUpper(Integer.parseInt(str[0] + "")) + "拾";
            } else {
                return numToUpper(Integer.parseInt(str[0] + "")) + "拾" + numToUpper(Integer.parseInt(str[1] + ""));
            }
        }
    }
	
	/**
	 * 保存强制执行申请书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveEnfApp(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,EnfApp enfApp, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违强执字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监强执字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		enfApp.setRelatedId(instrumentsInfo.getId());
		enfApp.setDelFlag(0);
		enfApp.setCreateUserID(instrumentsInfo.getCreateUserID());
		enfApp.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			enfAppService.save(enfApp);
		}else{
			enfAppService.update(enfApp);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createEnfApp(instrumentsInfo, caseInfo, enfApp, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核强制执行申请书
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheEnfApp(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, EnfApp enfApp,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createEnfApp(instrumentsInfo, caseInfo, enfApp, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成强制执行申请书
	 * @author luting
	 * 2015-10-24
	 */
	public void createEnfApp(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, EnfApp enfApp,String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("qzh", NullToString(instrumentsInfo.getAjh()));
		map.put("qzhNum", NullToString(instrumentsInfo.getAjhNum()));
		map.put("rmfy", NullToString(enfApp.getCourtName()));
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "23");//行政（当场）处罚决定书（单位）
		List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "24");//行政（当场）处罚决定书（个人）
		List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "25");//行政处罚决定书（单位）
		List<InstrumentsInfo> list3 = instrumentsInfoService.findInstrumentsInfoss(m);
		m.put("instrumentType", "26");//行政处罚决定书（个人）
		List<InstrumentsInfo> list4 = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("person", "");
		map.put("fkrq", "");
		map.put("jdswh", "");
		Calendar ca = Calendar.getInstance();   
		if(list1.size() != 0)
		{
			map.put("person", NullToString(caseInfo.getCompanyName()));
			map.put("fkrq", changeTimeToZw(list1.get(0).getTime()));
			map.put("jdswh", NullToString(list1.get(0).getWsh()) );
		}
		else if(list2.size() != 0)//行政（当场）处罚决定书（个人）
		{
			map.put("person", NullToString(caseInfo.getPerson()));
			map.put("fkrq", changeTimeToZw(list2.get(0).getTime()));
			map.put("jdswh", NullToString(list2.get(0).getWsh()) );
		}
		else if(list3.size() != 0)//行政处罚决定书（单位）
		{
			map.put("person", NullToString(caseInfo.getCompanyName()));
			map.put("fkrq", changeTimeToZw(list3.get(0).getTime()));
			map.put("jdswh", NullToString(list3.get(0).getWsh()) );
		}
		else if(list4.size() != 0)//行政处罚决定书（个人）
		{
			m.put("relatedId", list4.get(0).getId());
			PenDecPer penDecPer = (PenDecPer) penDecPerService.findPenDecPer(m).get(0);
			map.put("person", NullToString(caseInfo.getPerson()));
			map.put("fkrq", changeTimeToZw(list4.get(0).getTime()));
			map.put("jdswh", NullToString(list4.get(0).getWsh()));
		}
		//查询行政处罚告知书
		m.put("instrumentType", "15");
		map.put("adminPenality","");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
			map.put("adminPenality", NullToString(penaltyNotice.getAdminPenality()));
		}
		
		map.put("ygcl", NullToString(enfApp.getYgcl()));
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		map.put("contact", NullToString(enfApp.getContact()));
		map.put("tele", NullToString(enfApp.getTele()));
		
		String[] s = fileDocUtil.createDocFile(root+"强制执行申请书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	
	/**
	 * 保存结案审批表
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveCloseApproval(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,CloseApproval closeApproval, String flag,String type,String root) throws IOException
	{
		String undertakerName = closeApproval.getUndertakerName1();
		if(closeApproval.getUndertaker() != null)
		{
			User user = userService.findUserById(closeApproval.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		closeApproval.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+closeApproval.getUndertaker().replaceAll(" ", "")+",");
		
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违结字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监结字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		
		closeApproval.setRelatedId(instrumentsInfo.getId());
		closeApproval.setDelFlag(0);
		closeApproval.setCreateUserID(instrumentsInfo.getCreateUserID());
		closeApproval.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			closeApprovalService.save(closeApproval);
		}else{
			closeApprovalService.update(closeApproval);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createCloseApproval(instrumentsInfo, caseInfo, closeApproval, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核结案审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheCloseApproval(InstrumentsInfo in, CaseInfo ca,CloseApproval clo,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			clo.setUnderTime(checkTime);
			clo.setUndertaker(user.getId());
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			clo.setCheckComment(remark);
			clo.setCheckPerson(user.getId());
			clo.setCheckTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2"))//局长审核
		{
			clo.setApproverComment(remark);
			clo.setApprover(user.getId());
			clo.setApproverTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("6");
				checkrecord.setCheckResult("审批通过");
				
				//生成文书
				createCloseApproval(in, ca, clo, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
				
				Map mmm = new HashMap();
				mmm.put("caseId", ca.getId());
				//将案件相关文书置为不可修改
				
				mmm.put("ifCheck", "6");
				instrumentsInfoService.updateAllWsInfoByMap(mmm);
				
				ca.setCaseStatus("3");
				ca.setJaTime(checkTime);
				ca.setApprovalResult(clo.getApprovalResult());
				caseInfoService.update(ca);
				
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		closeApprovalService.update(clo);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成结案审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createCloseApproval(InstrumentsInfo in, CaseInfo ca,CloseApproval clo,String root) throws IOException
	{
				
		//生成文书
		map.put("ajbz", NullToString(in.getAjbz()));
		map.put("gjh", NullToString(in.getAjh()));
		map.put("gjhNum", NullToString(in.getAjhNum()));
		map.put("caseName", NullToString(ca.getCaseName()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("companyName", "———");
			map.put("companyAddress", "———");
			map.put("fddbr", "———");
			map.put("zw", "———");
			map.put("yb", "———");
			map.put("person", NullToHx(ca.getPerson()));
			map.put("age", NullToHx(ca.getAge()));
			Map m = new HashMap();
			if(ca.getSex() != null && !"".equals(ca.getSex()))
			{
				m.put("codeName", "性别");
				m.put("itemValue", ca.getSex());
				map.put("sex", codeService.findCodeValueByMap(m).getItemText());
			}
			else
			{
				map.put("sex", "———");
			}
			map.put("cfrdw", NullToHx(ca.getCompanyName()));
			map.put("dwdz", NullToHx(ca.getCompanyAddress()));
			map.put("jtzz", NullToHx(ca.getAddress()));
			map.put("lxdh", NullToHx(ca.getTele()));
			map.put("cfryb", NullToHx(ca.getZipCode()));
		}
		else
		{
			map.put("companyName", NullToHx(ca.getCompanyName()));
			map.put("companyAddress",NullToHx(ca.getCompanyAddress()) );
			map.put("fddbr", NullToHx(ca.getFddbr()));
			map.put("zw",NullToHx(ca.getZw()) );
			map.put("yb", NullToHx(ca.getZipCode()));
			map.put("person", "———");
			map.put("age", "———");
			map.put("sex", "———");
			map.put("cfrdw", "———");
			map.put("dwdz", "———");
			map.put("jtzz", "———");
			map.put("lxdh", "———");
			map.put("cfryb", "———");
		}
		map.put("approvalResult", NullToString(clo.getApprovalResult()));
		map.put("executeCondition", NullToString(clo.getExecuteCondition()));
		map.put("underTime", changeTimeToZw(clo.getUnderTime()));
		map.put("checkComment", NullToString(clo.getCheckComment()));
		map.put("checkTime", changeTimeToZw(clo.getCheckTime()));
		map.put("approverComment", NullToString(clo.getApproverComment()));
		map.put("approverTime", changeTimeToZw(clo.getApproverTime()));
		String[] s = fileDocUtil.createDocFile(root+"结案审批表.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存案件移送审批表
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveCaseRefer(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,CaseRefer caseRefer, String flag,String type,String root) throws IOException
	{
		String undertakerName = caseRefer.getUndertakerName1() ;
		if(caseRefer.getUndertaker() != null)
		{
			User user = userService.findUserById(caseRefer.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		caseRefer.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+caseRefer.getUndertaker().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		
		caseRefer.setRelatedId(instrumentsInfo.getId());
		caseRefer.setDelFlag(0);
		caseRefer.setCreateUserID(instrumentsInfo.getCreateUserID());
		caseRefer.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			caseReferService.save(caseRefer);
		}else{
			caseReferService.update(caseRefer);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createCaseRefer(instrumentsInfo, caseInfo, caseRefer, root);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核案件移送审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheCaseRefer(InstrumentsInfo in, CaseInfo ca,CaseRefer cas,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			cas.setUnderTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			cas.setDepartComment(remark);
			cas.setDepartPerson(user.getId());
			cas.setDepartTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			cas.setOfficeComment(remark);
			cas.setOfficePerson(user.getId());
			cas.setOfficeTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				createCaseRefer(in, ca, cas, root);
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		caseReferService.update(cas);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成案件移送审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createCaseRefer(InstrumentsInfo in, CaseInfo ca,CaseRefer cas,String root) throws IOException
	{
		//生成文书
		map.put("caseName", NullToString(ca.getCaseName()));
		map.put("address", NullToString(ca.getCompanyAddress()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("person", NullToString(ca.getPerson()));
			
		}
		else
		{
			map.put("person", NullToString(ca.getCompanyName()));
		}
		map.put("transferAuthority", NullToString(cas.getTransferAuthority()));
		//查询调查报告
		Map m = new HashMap();
		m.put("caseId", ca.getId());
		m.put("instrumentType", "34");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		map.put("ajjj", "");
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			Dcbg dcbg = (Dcbg) dcbgService.findDcbg(m).get(0);
			map.put("ajjj", NullToString(dcbg.getCaseCondition()));
		}
		map.put("feedingGrounds", NullToString(cas.getFeedingGrounds()));
		map.put("undertakerComment",NullToString(cas.getUndertakerComment() ));
		map.put("underTime", changeTimeToZw(cas.getUnderTime()));
		map.put("departComment",NullToString(cas.getDepartComment()) );
		map.put("departTime",changeTimeToZw(cas.getDepartTime() ));
		map.put("officeComment",NullToString(cas.getOfficeComment() ));
		map.put("officeTime", changeTimeToZw(cas.getOfficeTime()));
		String[] s = fileDocUtil.createDocFile(root+"案件移送审批表.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存案件移送书
	 * @author luting
	 * 2015-10-24
	 */
	public InstrumentsInfo saveCaseTransfer(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,CaseTransfer caseTransfer, String flag,String type,String root) throws IOException
	{
		if(caseInfo.getFineType().equals("0"))
		{
			instrumentsInfo.setAjbz("苏园安监违移字");
		}
		else
		{
			instrumentsInfo.setAjbz("苏园安监移字");
		}
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("5");
				instrumentsInfo.setIfPrint("1");
				type = "1";
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setJzCheck("0");
		}
		
		caseTransfer.setRelatedId(instrumentsInfo.getId());
		caseTransfer.setDelFlag(0);
		caseTransfer.setCreateUserID(instrumentsInfo.getCreateUserID());
		caseTransfer.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			caseTransferService.save(caseTransfer);
		}else{
			caseTransferService.update(caseTransfer);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createCaseTransfer(instrumentsInfo, caseInfo, caseTransfer, root);
		}
		return instrumentsInfo;
	}
	
	/**
	 * 审核案件移送书
	 * @author luting
	 * 2015-10-24
	 */
	public void shenheCaseTransfer(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, CaseTransfer caseTransfer,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(instrumentsInfo.getId());
		checkrecord.setCheckRemark(remark);
		checkrecord.setCheckTime(checkTime);
		
		//法务审核
		if(result.equals("0"))//审核通过
		{
			instrumentsInfo.setIfCheck("5");
			checkrecord.setCheckResult("审核通过");
			
			//生成文书	
			createCaseTransfer(instrumentsInfo, caseInfo, caseTransfer, root);
			
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setNeedCheck("2");
		}
		else
		{
			instrumentsInfo.setIfCheck("4");
			checkrecord.setCheckResult("审核不通过");
		}
		instrumentsInfoService.update(instrumentsInfo);
		
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成案件移送书
	 * @author luting
	 * 2015-10-24
	 */
	public void createCaseTransfer(InstrumentsInfo instrumentsInfo,CaseInfo caseInfo, CaseTransfer caseTransfer,String root) throws IOException
	{
			
		//生成文书	
		map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
		map.put("gyh", NullToString(instrumentsInfo.getAjh()));
		map.put("gyhNum", NullToString(instrumentsInfo.getAjhNum()));
		map.put("transferAuthority", "");
		map.put("feedingGrounds", "");
		//查询案件移送审批表
		Map m = new HashMap();
		m.put("caseId", caseInfo.getId());
		m.put("instrumentType", "32");
		List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
		if(list.size() != 0)
		{
			m.put("relatedId", list.get(0).getId());
			CaseRefer caseRefer = (CaseRefer) caseReferService.findCaseRefer(m).get(0);
			map.put("transferAuthority", NullToString(caseRefer.getTransferAuthority()));
			map.put("feedingGrounds", NullToString(caseRefer.getFeedingGrounds()));
		}
		map.put("lasj", changeTimeToZw(caseInfo.getApprovalTime()));
		map.put("caseName", NullToString(caseInfo.getCaseName()));
		map.put("gd", "");
		
		String gd = "";
		if(caseTransfer.getProvision() != null)
		{
			String[] ss = caseTransfer.getProvision().replaceAll(" ", "").split(",");
			for(String s:ss)
			{
				LawBasis lawBasis = lawBasisService.getById(s);
				if(lawBasis != null)
				{
					gd += NullToString(lawBasis.getLawName()) + NullToString(lawBasis.getLawProvision())+ ",";
				}
			}
			if(gd.length() != 0)
			{
				gd = gd.substring(0,gd.length()-1);
			}
		}
		else
		{
			gd =  NullToString(caseTransfer.getProName());
		}
		map.put("gd", gd);
		
		map.put("ygcl", NullToString(caseTransfer.getYgcl()));
		map.put("time", changeTimeToZw(instrumentsInfo.getTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"案件移送书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/"+caseInfo.getCaseName(), map).split(",");
		instrumentsInfo.setFileSize(s[0]);
		instrumentsInfo.setPageSize(s[1]);
		instrumentsInfoService.update(instrumentsInfo);
		
	}
	
	/**
	 * 保存调查报告
	 * @author luting
	 * 2016-1-18
	 */
	public InstrumentsInfo saveDcbg(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,Dcbg dcbg, String flag,String type,String root) throws IOException
	{
		String undertakerName = dcbg.getUndertakerName1() ;
		if(dcbg.getUndertaker() != null)
		{
			User user = userService.findUserById(dcbg.getUndertaker().trim());
			undertakerName += "," +user.getDisplayName();
		}
		dcbg.setUndertakerName(undertakerName);
		instrumentsInfo.setNeedCheckUser(","+dcbg.getUndertaker().replaceAll(" ", "")+",");
		
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("3");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("1");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		dcbg.setRelatedId(instrumentsInfo.getId());
		dcbg.setDelFlag(0);
		dcbg.setCreateUserID(instrumentsInfo.getCreateUserID());
		dcbg.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			dcbgService.save(dcbg);
		}else{
			dcbgService.update(dcbg);
		}
		if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
		{
			createDcbg(instrumentsInfo, caseInfo, dcbg, root);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核调查报告
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheDcbg(InstrumentsInfo in, CaseInfo ca,Dcbg dc,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("3");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("3")) //电子签名确认
		{
			dc.setUnderTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setDzqmCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			dc.setCheckComment(remark);
			dc.setCheckPerson(user.getId());
			dc.setCheckTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			dc.setApproverComment(remark);
			dc.setApproverPerson(user.getId());
			dc.setApproverTime(checkTime);
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				//生成文书
				createDcbg(in, ca, dc, root);
				
				in.setIfPrint("1");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		dcbgService.update(dc);
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
	}
	
	/**
	 * 生成调查报告
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void createDcbg(InstrumentsInfo in, CaseInfo ca,Dcbg dc,String root) throws IOException
	{
		//生成文书
		map.put("caseCause", NullToString(dc.getCaseCondition()));
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
		map.put("tele", NullToString(ca.getTele()));
		if(ca.getPersonType().equals("1"))
		{
			map.put("person", NullToString(ca.getPerson()));
			
		}
		else
		{
			map.put("person", NullToString(ca.getCompanyName()));
		}
		map.put("personCondition", NullToString(ca.getPersonCondition()));
		map.put("personAddress", NullToString(ca.getCompanyAddress()));
		map.put("personCode", NullToString(ca.getZipCode()));
		map.put("caseCondition", NullToString(dc.getCaseCondition()));
		map.put("dcqzqk", NullToString(dc.getDcbgqk()));
		map.put("wfwgqk", NullToString(dc.getWfwgxw()));
		map.put("undertakerComment", NullToString(dc.getUndertakerComment()));
		map.put("underTime", changeTimeToZw(dc.getUnderTime()));
		map.put("checkComment", NullToString(dc.getCheckComment()));
		map.put("checkTime", changeTimeToZw(dc.getCheckTime()));
		map.put("approvalComment", NullToString(dc.getApproverComment()));
		map.put("approvalTime", changeTimeToZw(dc.getApproverTime()));
		
		String[] s = fileDocUtil.createDocFile(root+"调查报告.docx", in.getFileName(), root+"../../virtualdir/file/"+ca.getCaseName(), map).split(",");
		in.setFileSize(s[0]);
		in.setPageSize(s[1]);
				
		instrumentsInfoService.update(in);
	}
	
	/**
	 * 保存特殊事项审批表
	 * @author luting
	 * 2016-1-18
	 */
	public InstrumentsInfo saveSpecialItem(InstrumentsInfo instrumentsInfo, CaseInfo caseInfo,SpecialItem specialItem, String flag) throws IOException
	{
		if (instrumentsInfo.getIfCheck() == null || "".equals(instrumentsInfo.getIfCheck()) || "4".equals(instrumentsInfo.getIfCheck())){
			if(instrumentsInfo.getNeedCheck() != null && "1".equals(instrumentsInfo.getNeedCheck()))//需要法务审核
			{
				instrumentsInfo.setIfCheck("8");
				instrumentsInfo.setIfPrint("0");
			}
			else
			{
				instrumentsInfo.setIfCheck("1");
				instrumentsInfo.setIfPrint("0");
			}
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setDzCheck("1");
			instrumentsInfo.setJzCheck("1");
		}
		
		specialItem.setRelatedId(instrumentsInfo.getId());
		specialItem.setDelFlag(0);
		specialItem.setCreateUserID(instrumentsInfo.getCreateUserID());
		specialItem.setCreateTime(instrumentsInfo.getCreateTime());
		if ("add".equalsIgnoreCase(flag)){
			specialItemService.save(specialItem);
		}else{
			specialItemService.update(specialItem);
		}
		return instrumentsInfo;
	}
	
	
	/**
	 * 审核特殊事项审批表
	 * @author luting
	 * 2016-1-15
	 * @throws IOException 
	 */
	public void shenheSpecialItem(InstrumentsInfo in, CaseInfo ca,SpecialItem spe,User user,String root,String result,String remark,Date checkTime) throws IOException
	{
		FileDocUtil fileDocUtil = new FileDocUtil();	
		CheckRecord checkrecord = new CheckRecord();
		checkrecord.setInfoId(in.getId());
		checkrecord.setCheckRemark(remark);
		String status = in.getIfCheck();
		
		if(status.equals("8")) //法务审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("1");
				checkrecord.setCheckResult("审核通过");
				in.setNeedCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("1")) //大队队长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("2");
				checkrecord.setCheckResult("审核通过");
				in.setDzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审核不通过");
			}
		}
		else if(status.equals("2")) //局长审核
		{
			if(result.equals("0"))
			{
				in.setIfCheck("5");
				checkrecord.setCheckResult("审批通过");
				in.setJzCheck("2");
			}
			else
			{
				in.setIfCheck("4");
				checkrecord.setCheckResult("审批不通过");
			}
		}
		checkrecord.setCheckUserid(user.getId());
		checkrecord.setCheckUsername(user.getDisplayName());
		checkrecord.setCheckTime(checkTime);
		checkrecord.setDelFlag(0);
		instrumentsInfoService.update(in);
		checkRecordService.save(checkrecord);
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
	
	/**
	 * 验证数组是否有值
	 * @author luting
	 * 2015-10-26
	 * @param object
	 * @param i
	 * @return
	 */
	public String NullToStr(Object[] object,int i)
	{
		String s = "";
		if(object != null && i<object.length)
		{
			s = object[i].toString();
		}
		return s;
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
		if(object != null && !"null".equals(object))
		{
			s = object;
		}
		return s;
	}
	
	/**
	 * 将null值和空值转换为下划线
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 */
	public String NullToDown(String object)
	{
		String s = "________";
		if(object != null && !"null".equals(object))
		{
			s = object;
		}
		return s;
	}
	
	/**
	 * 将将null值和空值转换为横线
	 * @author luting
	 * 2015-10-27
	 * @param object
	 * @param i
	 * @return
	 */
	public String NullToHx(String object)
	{
		String s = "———";
		if(object != null && !"null".equals(object)&& !"".equals(object))
		{
			s = object;
		}
		return s;
	}
}
