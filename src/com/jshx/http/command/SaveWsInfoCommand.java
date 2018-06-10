package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajclcpb.entity.CaseProcessApproval;
import com.jshx.ajclcpb.service.CaseProcessApprovalService;
import com.jshx.ajsp.entity.CloseApproval;
import com.jshx.ajsp.service.CloseApprovalService;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
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
import com.jshx.http.bean.SummaryBean;
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
import com.jshx.wsgl.util.InstrumentsInfoUtil;
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

/**
 * 文书上报接口
 * @author 陆婷 2015-11-6
 *
 */
public class SaveWsInfoCommand implements Command{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private CaseInfoService caseInfoService= (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private UserService userService= (UserService) SpringContextHolder.getBean("userService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
	private InstrumentsInfo instrumentsInfo = new InstrumentsInfo();
	private LawBasisService lawBasisService = (LawBasisService) SpringContextHolder.getBean("lawBasisService");
	private InquiryNotice inquiryNotice = new InquiryNotice();
	private InquiryRecord inquiryRecord = new InquiryRecord();
	private InqRecRecord inqRecRecord = new InqRecRecord();
	private InquestRecord inquestRecord = new InquestRecord();
	private SamplingEvidence samplingEvidence = new SamplingEvidence();
	private SamplingAssociate samplingAssociate = new SamplingAssociate();
	private PreserveEvidence preserveEvidence = new PreserveEvidence();
	private InventoryAssociate inventoryAssociate = new InventoryAssociate();
	private NoticeEvidence noticeEvidence = new NoticeEvidence();
	private InventoryCheck inventoryCheck = new InventoryCheck();
	private InventoryDecision inventoryDecision = new InventoryDecision();
	private SiteCheckRecord siteCheckRecord = new SiteCheckRecord();
	private LiveActionDecision liveActionDecision = new LiveActionDecision();
	private OrderDeadlineBook orderDeadlineBook = new OrderDeadlineBook();
	private ReviewSubmission reviewSubmission = new ReviewSubmission();
	private EnforenceDecision enforenceDecision = new EnforenceDecision();
	private IdentifyAttorney identifyAttorney = new IdentifyAttorney();
	private IdentifyItemAssociate identifyItemAssociate = new IdentifyItemAssociate();
	private PenaltyNotice penaltyNotice = new PenaltyNotice();
	private PartyStateNote partyStateNote = new PartyStateNote();
	private HearingTell hearingTell = new HearingTell();
	private HearingNotice hearingNotice = new HearingNotice();
	private HearingNote hearingNote = new HearingNote();
	private HearingReport hearingReport = new HearingReport();
	private CaseProcessApproval caseProcessApproval = new CaseProcessApproval();
	private PenBraRec penBraRec = new PenBraRec();
	private SpoPenDecCom spoPenDecCom = new SpoPenDecCom();
	private SpoPenDecPer spoPenDecPer = new SpoPenDecPer();
	private PenDecCom penDecCom = new PenDecCom();
	private PenDecPer penDecPer = new PenDecPer();
	private PosFinApp posFinApp = new PosFinApp();
	private PosFinRat posFinRat = new PosFinRat();
	private EnfApp enfApp = new EnfApp();
	private CloseApproval closeApproval = new CloseApproval();
	private CaseRefer caseRefer = new CaseRefer();
	private CaseTransfer caseTransfer = new CaseTransfer();
	private Dcbg dcbg = new Dcbg();
	private SpecialItem specialItem = new SpecialItem();
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
	SimpleDateFormat sdff =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String caseId = obj.getString("caseId");//获取分页的起始页
		String wstype = obj.getString("instrumentType");//获取分页的每页条数
		String time = obj.getString("time");
		String needCheck = obj.getString("needCheck");//是否需法务审核
		
		User user = userService.findUserById(userId);
		String flag = "add";
		String type = "";
		
		try {
			CaseInfo caseInfo = caseInfoService.getById(caseId);
			instrumentsInfo.setNeedCheck(needCheck);
			instrumentsInfo.setTime(sdf.parse(time));
			instrumentsInfo.setCaseId(caseId);
			instrumentsInfo.setInstrumentType(wstype);
			String root = this.getClass().getResource("/").getPath();
			root = root.replaceAll("\\\\", "/") + "../../";
			InstrumentsInfoUtil instrumentsInfoUtil = new InstrumentsInfoUtil();
			
			
			instrumentsInfo.setDeptId(user.getDept().getId());
			instrumentsInfo.setCreateUserID(userId);
			instrumentsInfo.setCreateTime(new Date());
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			instrumentsInfo.setLinkId(linkId);
			
			//根据类型保存文书关联信息 luting 2015-10-25
			if(wstype.equals("1")) //询问通知书 luting 2015-10-25
			{
				inquiryNotice.setAskPerson(obj.getString("askPerson"));//询问人员:askPerson
				inquiryNotice.setInquiryTime(sdff.parse(obj.getString("inquiryTime")));//询问时间:inquiryTime  yyyy-MM-dd HH:mm:ss
				inquiryNotice.setInquiryAddress(obj.getString("inquiryAddress"));//询问地点:inquiryAddress
				//询问材料（均为checkbox，选中传值1，未选中传0）
				inquiryNotice.setSfz(obj.getString("sfz"));//sfz:身份证
				inquiryNotice.setYyzz(obj.getString("yyzz"));//yyzz:营业执照
				inquiryNotice.setFddbzm(obj.getString("fddbzm"));//fddbzm:法定代表人身份证明或者委托书
				inquiryNotice.setQt(obj.getString("qt"));//qt其他 
				inquiryNotice.setDocMaterial(obj.getString("docMaterial"));//docMaterial(输入框)
				inquiryNotice.setCreateUserID(userId);
				inquiryNotice.setCreateTime(new Date());
				inquiryNotice.setDeptId(user.getDept().getId());
				String askPersons[] = inquiryNotice.getAskPerson().replaceAll(" ", "").split(",");
				for(String s:askPersons)
				{
					if(s != null && !"".equals(s))
					{
						InstrumentsInfo ins = new InstrumentsInfo();
						ins.setCaseId(instrumentsInfo.getCaseId());
						ins.setCreateTime(new Date());
						ins.setCreateUserID(user.getId());
						ins.setDeptId(user.getDept().getId());
						ins.setInstrumentType(instrumentsInfo.getInstrumentType());
						ins.setTime(instrumentsInfo.getTime());
						ins.setNeedCheck(instrumentsInfo.getNeedCheck());
						ins = instrumentsInfoUtil.saveInstrumentsInfo(ins, caseInfo, root, wstype);
						InquiryNotice inq = new InquiryNotice();
						inq.setAskPerson(s);
						inq.setInquiryTime(inquiryNotice.getInquiryTime());
						inq.setInquiryAddress(inquiryNotice.getInquiryAddress());
						inq.setSfz(inquiryNotice.getSfz());
						inq.setYyzz(inquiryNotice.getYyzz());
						inq.setFddbzm(inquiryNotice.getFddbzm());
						inq.setQt(inquiryNotice.getQt());
						ins = instrumentsInfoUtil.saveInquiryNotice(ins,caseInfo,inq,flag,type,root);
						if(ins.getAjbz() != null && !"".equals(ins.getAjbz()))
						{
							String wsh = ins.getAjbz() + "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
							ins.setWsh(wsh);
						}
						ins.setDelFlag(0);
						instrumentsInfoService.update(ins);
					}
				}
			}
			else if(wstype.equals("2"))//询问笔录 luting 2015-10-25
			{
				inquiryRecord.setInquiryPeriod(sdff.parse(obj.getString("inquiryPeriod")));//询问开始时间：inquiryPeriod   yyyy-MM-dd HH:mm:ss
				inquiryRecord.setEndTime(sdff.parse(obj.getString("endTime")));//询问结束时间：endTime   yyyy-MM-dd HH:mm:ss
				inquiryRecord.setAskedPerson(obj.getString("askedPerson"));//被询问人姓名：askedPerson
				inquiryRecord.setSex(obj.getString("sex"));//性别：sex
				inquiryRecord.setPeopleAge(obj.getString("peopleAge"));//年龄:peopleAge
				inquiryRecord.setCardId(obj.getString("cardId"));//身份证号:cardId
				inquiryRecord.setPosition(obj.getString("position"));//职位:position
				inquiryRecord.setTele(obj.getString("tele"));//电话:tele
				inquiryRecord.setAddress(obj.getString("address"));//住址:address
				inquiryRecord.setPresentPeople(obj.getString("presentPeople"));//在场人:presentPeople
				inquiryRecord.setInquiryPerson(obj.getString("inquiryPerson"));//询问人:inquiryPerson 调用接口5获取数据，type=1 单选
				 
				inquiryRecord.setXwcs(obj.getString("xwcs"));//询问次数:xwcs
				inquiryRecord.setInquiryAddress(obj.getString("inquiryAddress"));//询问地点:inquiryAddress
				inquiryRecord.setCompanyName(obj.getString("companyName"));//工作单位:companyName
				
				
				
				inquiryRecord.setCreateUserID(userId);
				inquiryRecord.setCreateTime(new Date());
				inquiryRecord.setDeptId(user.getDept().getId());
				
				inqRecRecord.setAskRecord(obj.getString("askRecord"));//问:askRecord,以“，”分隔
				inqRecRecord.setRecRecord(obj.getString("recRecord"));//答:recRecord,以“，”分隔
				
				inqRecRecord.setCreateUserID(userId);
				inqRecRecord.setCreateTime(new Date());
				inqRecRecord.setDeptId(user.getDept().getId());
				
				inquiryRecord.setRecordPerson(userId);//记录人
				inquiryRecord.setRecordPersonName(user.getDisplayName());//记录人姓名
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveInquiryRecord(instrumentsInfo, caseInfo, inquiryRecord,inqRecRecord, flag,type,root);
				
			}
			else if(wstype.equals("3"))//勘验笔录 luting 2015-10-25
			{
				inquestRecord.setStartTime(sdff.parse(obj.getString("startTime")));//勘验开始时间：startTime   yyyy-MM-dd HH:mm:ss  
				inquestRecord.setEndTime(sdff.parse(obj.getString("endTime")));//勘验结束时间：endTime   yyyy-MM-dd HH:mm:ss
				inquestRecord.setInquestAddress(obj.getString("inquestAddress"));//勘验场所：inquestAddress
				inquestRecord.setWeatherCondition(obj.getString("weatherCondition"));//天气情况：weatherCondition
				inquestRecord.setParty1(obj.getString("party1"));//当事人：party1
				inquestRecord.setParty1Company(obj.getString("party1Company"));//当事人单位及职务：party1Company
				inquestRecord.setParty1Tel(obj.getString("party1Tel"));//当事人联系方式：party1Tel
				inquestRecord.setParty2(obj.getString("party2"));//当事人：party2
				inquestRecord.setParty2Company(obj.getString("party2Company"));//当事人单位及职务：party2Company
				inquestRecord.setParty2Tel(obj.getString("party2Tel"));//当事人联系方式：party2Tel
				inquestRecord.setInvitee(obj.getString("invitee"));//被邀请人：invitee
				inquestRecord.setInviteeCompany(obj.getString("inviteeCompany"));//被邀请人单位及职务：inviteeCompany
				inquestRecord.setInviteeTel(obj.getString("inviteeTel"));//被邀请人联系方式：inviteeTel
				inquestRecord.setInquestCondition(obj.getString("inquestCondition"));//勘验情况：inquestCondition 
				inquestRecord.setInquestPerson(obj.getString("inquiryPerson"));//勘验人:inquiryPerson 调用接口5获取数据，两个
				
				inquestRecord.setCreateUserID(userId);
				inquestRecord.setCreateTime(new Date());
				inquestRecord.setDeptId(user.getDept().getId());
				inquestRecord.setRecordPerson(userId);
				inquestRecord.setRecordPersonName(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveInquestRecord(instrumentsInfo, caseInfo, inquestRecord, flag,type,root);
			}
			else if(wstype.equals("4"))//抽样取证凭证 luting 2015-10-25
			{
				samplingEvidence.setChargePerson(obj.getString("chargePerson"));//现场负责人：chargePerson
				samplingEvidence.setForensicAddress(obj.getString("forensicAddress"));//抽样地点：forensicAddress
				samplingEvidence.setStartTime(sdff.parse(obj.getString("startTime")));//抽样取证开始时间：startTime   yyyy-MM-dd HH:mm:ss  
				samplingEvidence.setEndTime(sdff.parse(obj.getString("endTime")));//抽样取证结束时间：endTime   yyyy-MM-dd HH:mm:ss
				samplingEvidence.setLawOfficer(obj.getString("lawOfficer"));//执法人员：lawOfficer 调用接口5获取数据，type= 1个
				
				samplingEvidence.setCreateUserID(userId);
				samplingEvidence.setCreateTime(new Date());
				samplingEvidence.setDeptId(user.getDept().getId());
				
				samplingAssociate.setEvidenceName(obj.getString("evidenceName"));//证据物品名称: evidenceName,多个以“，”分隔
				samplingAssociate.setSpecificationLot(obj.getString("specificationLot"));//规格及批号：specificationLot,多个以“，”分隔
				samplingAssociate.setSamplingNum(obj.getString("samplingNum"));//数量：samplingNum,多个以“，”分隔
				
				samplingAssociate.setCreateUserID(userId);
				samplingAssociate.setCreateTime(new Date());
				samplingAssociate.setDeptId(user.getDept().getId());
				samplingEvidence.setLawOfficerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveSamplingEvidence(instrumentsInfo, caseInfo, samplingEvidence,samplingAssociate, flag,type,root);
			}
			else if(wstype.equals("5"))//先行登记保存证据审批表 luting 2015-10-25
			{
				preserveEvidence.setReasonBasic(obj.getString("reasonBasic"));//提请理由及依据:reasonBasic
				preserveEvidence.setPreserveMethod(obj.getString("preserveMethod"));//保存方式:preserveMethod
				preserveEvidence.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type= 1个
				preserveEvidence.setUndertakerComment(obj.getString("undertakerComment"));//承办人意见:undertakerComment
				
				preserveEvidence.setCreateUserID(userId);
				preserveEvidence.setCreateTime(new Date());
				preserveEvidence.setDeptId(user.getDept().getId());
				
				inventoryAssociate.setEvidenceName(obj.getString("evidenceName"));//证据名称：evidenceName,多个以“，”分隔
				inventoryAssociate.setSpecificationModel(obj.getString("specificationModel"));//规格型号：specificationModel,多个以“，”分隔
				inventoryAssociate.setOriginPlace(obj.getString("originPlace"));//产地：originPlace,多个以“，”分隔
				inventoryAssociate.setCondition(obj.getString("condition"));//成色（品级）：condition,多个以“，”分隔
				inventoryAssociate.setCompany(obj.getString("company"));//单位：company,多个以“，”分隔
				inventoryAssociate.setPrice(obj.getString("price"));//价格：price,多个以“，”分隔
				inventoryAssociate.setInventoryNum(obj.getString("inventoryNum"));//数量：inventoryNum,多个以“，”分隔
				inventoryAssociate.setRemark(obj.getString("remark"));//备注：remark,多个以“，”分隔

				
				inventoryAssociate.setCreateUserID(userId);
				inventoryAssociate.setCreateTime(new Date());
				inventoryAssociate.setDeptId(user.getDept().getId());
				preserveEvidence.setUndertakerName1(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePreserveEvidence(instrumentsInfo, caseInfo, preserveEvidence,inventoryAssociate, flag,type,root);
			}
			else if(wstype.equals("6"))//先行登记保存证据通知书
			{
				noticeEvidence.setViolation(obj.getString("violation"));//违法行为:violation
				noticeEvidence.setDealAddress(obj.getString("dealAddress"));//处理地点:dealAddress
				noticeEvidence.setDealTime(sdff.parse(obj.getString("dealTime")));//处理时间:dealTime     yyyy-MM-dd HH:mm:ss
				noticeEvidence.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type=
				noticeEvidence.setUndertakerName1(user.getDisplayName()) ;
				
				noticeEvidence.setCreateUserID(userId);
				noticeEvidence.setCreateTime(new Date());
				noticeEvidence.setDeptId(user.getDept().getId());
				
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveNoticeEvidence(instrumentsInfo, caseInfo,noticeEvidence, flag,type,root);
			}
			else if(wstype.equals("7"))//先行登记保存证据处理审批表
			{
				inventoryCheck.setReasonBasic(obj.getString("reasonBasic"));//提请理由及依据:reasonBasic
				inventoryCheck.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type=
				inventoryCheck.setUndertakerComment(obj.getString("undertakerComment"));//承办人意见：undertakerComment
				
				inventoryCheck.setCreateUserID(userId);
				inventoryCheck.setCreateTime(new Date());
				inventoryCheck.setDeptId(user.getDept().getId());
				
				inventoryCheck.setUndertakerName1(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo =instrumentsInfoUtil.saveInventoryCheck(instrumentsInfo, caseInfo, inventoryCheck, flag,type,root);
					
				}
			else if(wstype.equals("8"))//先行登记保存证据处理决定书 
			{
				inventoryDecision.setDeal(obj.getString("deal"));//处理:deal
				
				inventoryDecision.setCreateUserID(userId);
				inventoryDecision.setCreateTime(new Date());
				inventoryDecision.setDeptId(user.getDept().getId());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveInventoryDecision(instrumentsInfo, caseInfo, inventoryDecision, flag,type,root);
			}
			else if(wstype.equals("9"))//现场检查记录
			{
				siteCheckRecord.setCheckAddress(obj.getString("checkAddress"));//检查场所:checkAddress
				siteCheckRecord.setStartTime(sdff.parse(obj.getString("startTime")));//检查开始时间:startTime     yyyy-MM-dd HH:mm:ss
				siteCheckRecord.setEndTime(sdff.parse(obj.getString("endTime")));//检查结束时间:endTime     yyyy-MM-dd HH:mm:ss
				siteCheckRecord.setCheckCondition(obj.getString("checkCondition"));//检查情况:checkCondition
				siteCheckRecord.setCheckPerson(obj.getString("checkPerson"));//检查人员:checkPerson 调用接口5获取数据，type=
				
				siteCheckRecord.setCreateUserID(userId);
				siteCheckRecord.setCreateTime(new Date());
				siteCheckRecord.setDeptId(user.getDept().getId());
				
				siteCheckRecord.setCheckPersonName1(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveSiteCheckRecord(instrumentsInfo, caseInfo,siteCheckRecord, flag,type,root);
			}
			else if(wstype.equals("10"))//现场处理措施决定书
			{
				liveActionDecision.setIllegalAccident(obj.getString("illegalAccident"));//违法违规行为和事故隐患:illegalAccident
				liveActionDecision.setDealDecision(obj.getString("dealDecision"));//处理决定:dealDecision
				liveActionDecision.setLawBasic(obj.getString("lawBasic"));//执法依据:lawBasic 调用接口12，将id传回给我
				
				
				String lawname = "";
				String[] ss = liveActionDecision.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						lawname += lawBasis.getLawName() + ",";
					}
				}
				if(lawname.length() != 0)
				{
					lawname = lawname.substring(0,lawname.length()-1);
				}
				
				liveActionDecision.setLawName(lawname);
				liveActionDecision.setLawOfficer(obj.getString("lawOfficer"));//执法人员:lawOfficer 调用接口5获取数据，type=
				
				
				liveActionDecision.setCreateUserID(userId);
				liveActionDecision.setCreateTime(new Date());
				liveActionDecision.setDeptId(user.getDept().getId());
				
				liveActionDecision.setLawOfficerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveLiveActionDecision(instrumentsInfo, caseInfo,liveActionDecision, flag,type,root);
				
			}
			else if(wstype.equals("11"))//责令限期整改指令书
			{
				orderDeadlineBook.setProblem(obj.getString("problem"));//问题：problem
				orderDeadlineBook.setChangeItem(obj.getString("changeItem"));//整改项：changeItem
				orderDeadlineBook.setStartTime(sdf.parse(obj.getString("startTime")));//整改期限：startTime  yyyy-MM-dd
				orderDeadlineBook.setLawOfficer(obj.getString("lawOfficer"));//执法人员:lawOfficer 调用接口5获取数据，type=
				
				orderDeadlineBook.setCreateUserID(userId);
				orderDeadlineBook.setCreateTime(new Date());
				orderDeadlineBook.setDeptId(user.getDept().getId());
				orderDeadlineBook.setLawOfficerName1(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveOrderDeadlineBook(instrumentsInfo, caseInfo,orderDeadlineBook, flag,type,root);
				
			}
			else if(wstype.equals("12"))//整改复查意见书
			{
				reviewSubmission.setReviewComment(obj.getString("reviewComment"));//复查意见：reviewComment
				reviewSubmission.setLawOfficer(obj.getString("lawOfficer"));//执法人员:lawOfficer 调用接口5获取数据，type=
				
				reviewSubmission.setCreateUserID(userId);
				reviewSubmission.setCreateTime(new Date());
				reviewSubmission.setDeptId(user.getDept().getId());
				reviewSubmission.setLawOfficerName1(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveReviewSubmission(instrumentsInfo, caseInfo,reviewSubmission, flag,type,root);
				
			}
			else if(wstype.equals("13"))//强制措施决定书
			{
				enforenceDecision.setProblem(obj.getString("problem"));//问题：problem
				enforenceDecision.setLawBasic(obj.getString("lawBasic"));//执法依据：lawBasic调用接口12，将id传回给我
				
				String lawname = "";
				String[] ss = enforenceDecision.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						lawname += lawBasis.getLawName() + ",";
					}
				}
				if(lawname.length() != 0)
				{
					lawname = lawname.substring(0,lawname.length()-1);
				}
				
				enforenceDecision.setLawName(lawname);
				enforenceDecision.setMethod(obj.getString("method"));//措施：method
				
				enforenceDecision.setCreateUserID(userId);
				enforenceDecision.setCreateTime(new Date());
				enforenceDecision.setDeptId(user.getDept().getId());

				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveEnforenceDecision(instrumentsInfo, caseInfo,enforenceDecision, flag,type,root);
			}
			else if(wstype.equals("14"))//鉴定委托书
			{
				identifyAttorney.setJdjgName(obj.getString("jdjgName"));//鉴定机构名称：jdjgName
				identifyAttorney.setSubmitTime(sdf.parse(obj.getString("submitTime")));//提交时间：submitTime  yyyy-MM-dd
				identifyAttorney.setInentifyRequire(obj.getString("inentifyRequire"));//鉴定要求：inentifyRequire
				
				identifyAttorney.setCreateUserID(userId);
				identifyAttorney.setCreateTime(new Date());
				identifyAttorney.setDeptId(user.getDept().getId());
				
				identifyItemAssociate.setItemName(obj.getString("itemName"));//物品名称：itemName,多个以“，”分隔
				identifyItemAssociate.setSpecificationModel(obj.getString("specificationModel"));//规格型号：specificationModel,多个以“，”分隔
				identifyItemAssociate.setIdentifyNum(obj.getString("identifyNum"));//数量：identifyNum,多个以“，”分隔
				identifyItemAssociate.setRemark(obj.getString("remark"));//备注：remark,多个以“，”分隔
				
				identifyItemAssociate.setCreateUserID(userId);
				identifyItemAssociate.setCreateTime(new Date());
				identifyItemAssociate.setDeptId(user.getDept().getId());
				
				
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveIdentifyAttorney(instrumentsInfo, caseInfo,identifyAttorney,identifyItemAssociate, flag,type,root);
			}
			else if(wstype.equals("15"))//行政处罚告知书
			{
				penaltyNotice.setWfxw(obj.getString("wfxw"));//违法行为：wfxw
				penaltyNotice.setProvision(obj.getString("provision"));//违反规定：provision 调用接口12，将id传回给我
				penaltyNotice.setCaseCondition(obj.getString("caseCondition"));//案件基本情况：caseCondition
				
				String proname = "";
				String[] ss = penaltyNotice.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						proname += lawBasis.getLawName() + ",";
					}
				}
				if(proname.length() != 0)
				{
					proname = proname.substring(0,proname.length()-1);
				}
				penaltyNotice.setProName(proname);
				penaltyNotice.setLawBasic(obj.getString("lawBasic"));//执法依据：lawBasic 调用接口12，将id传回给我
				
				String lawname = "";
				String[] sss = penaltyNotice.getLawBasic().replaceAll(" ", "").split(",");
				for(String s:sss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						lawname += lawBasis.getLawName() + ",";
					}
				}
				if(lawname.length() != 0)
				{
					lawname = lawname.substring(0,lawname.length()-1);
				}
				penaltyNotice.setLawName(lawname);
				
				penaltyNotice.setAdminPenality(obj.getString("adminPenality"));//行政处罚：adminPenality
				penaltyNotice.setIftzgz(obj.getString("ifTzgz"));//是否生成听证告知书
				
				penaltyNotice.setCreateUserID(userId);
				penaltyNotice.setCreateTime(new Date());
				penaltyNotice.setDeptId(user.getDept().getId());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePenaltyNotice(instrumentsInfo, caseInfo,penaltyNotice, flag,type,root);
				if(instrumentsInfo.getAjbz() != null && !"".equals(instrumentsInfo.getAjbz()))
				{
					String wsh = instrumentsInfo.getAjbz() + "〔" + instrumentsInfo.getAjh() + "〕"  + instrumentsInfo.getAjhNum() +  "号";
					instrumentsInfo.setWsh(wsh);
				}
				instrumentsInfo.setDelFlag(0);
				instrumentsInfoService.update(instrumentsInfo);
				
				if(penaltyNotice.getIftzgz() != null && "1".equals(penaltyNotice.getIftzgz()))
				{
					//生成听证告知书
					InstrumentsInfo ins = new InstrumentsInfo();
					ins.setCaseId(instrumentsInfo.getCaseId());
					ins.setCreateTime(new Date());
					ins.setCreateUserID(user.getId());
					ins.setDeptId(user.getDept().getId());
					ins.setInstrumentType("17");
					ins.setTime(instrumentsInfo.getTime());
					ins.setNeedCheck(instrumentsInfo.getNeedCheck());
					ins = instrumentsInfoUtil.saveInstrumentsInfo(ins, caseInfo, root, "17");
					ins = instrumentsInfoUtil.saveHearingTell(ins,caseInfo,hearingTell,flag,type,root);
					if(ins.getAjbz() != null && !"".equals(ins.getAjbz()))
					{
						String wsh = ins.getAjbz() + "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
						ins.setWsh(wsh);
					}
					ins.setDelFlag(0);
					instrumentsInfoService.update(ins);
				}
				
			}
			else if(wstype.equals("16"))//当事人陈述申辩笔录
			{
				partyStateNote.setStartTime(sdff.parse(obj.getString("startTime")));//陈述开始时间：startTime       yyyy-MM-dd HH:mm:ss
				partyStateNote.setEndTime(sdff.parse(obj.getString("endTime")));//陈述结束时间：endTime     yyyy-MM-dd HH:mm:ss
				partyStateNote.setStateAddress(obj.getString("stateAddress"));//陈述地点：stateAddress
				partyStateNote.setStateDefense(obj.getString("stateDefense"));//陈述申辩人：stateDefense
				partyStateNote.setSex(obj.getString("sex"));//性别：sex
				partyStateNote.setPosition(obj.getString("position"));//职务：position
				partyStateNote.setTele(obj.getString("tele"));//电话：tele
				partyStateNote.setAddress(obj.getString("address"));//联系地址：address
				partyStateNote.setZipCode(obj.getString("zipCode"));//邮编：zipCode
				partyStateNote.setStateRecord(obj.getString("stateRecord"));//陈述申辩记录：stateRecord
				partyStateNote.setUndertaker(obj.getString("undertaker"));//承办人：undertaker  调用接口5获取数据，type=3
				
				partyStateNote.setCreateUserID(userId);
				partyStateNote.setCreateTime(new Date());
				partyStateNote.setDeptId(user.getDept().getId());
				partyStateNote.setRecorder(userId);
				partyStateNote.setRecorderName(user.getDisplayName());
				
			
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePartyStateNote(instrumentsInfo, caseInfo,partyStateNote, flag,type,root);
				
			}
			else if(wstype.equals("18"))//听证会通知书
			{
				hearingNotice.setHearingTime(sdff.parse(obj.getString("hearingTime")));//听证会时间：hearingTime  yyyy-MM-dd HH:mm:ss
				hearingNotice.setPublicCondition(obj.getString("publicCondition"));//是否公开：publicCondition
				hearingNotice.setHearingAddress(obj.getString("hearingAddress"));//听证地点：hearingAddress
				hearingNotice.setHearingChairperson(obj.getString("hearingChairperson"));//听证主持人姓名：hearingChairperson调用接口5获取数据，type=5
				hearingNotice.setHearingOfficer(obj.getString("hearingOfficer"));//听证员：hearingOfficer调用接口5获取数据，type=7 2个
				
				hearingNotice.setCreateUserID(userId);
				hearingNotice.setCreateTime(new Date());
				hearingNotice.setDeptId(user.getDept().getId());
				
				hearingNotice.setClerk(userId) ;
				hearingNotice.setClerkName(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveHearingNotice(instrumentsInfo, caseInfo,hearingNotice, flag,type,root);
			}
			else if(wstype.equals("19"))//听证笔录
			{
				hearingNote.setStartTime(sdff.parse(obj.getString("startTime")));//听证开始时间:startTime  yyyy-MM-dd HH:mm:ss
				hearingNote.setEndTime(sdff.parse(obj.getString("endTime")));//听证结束时间:endTime  yyyy-MM-dd HH:mm:ss
				hearingNote.setAttorney1(obj.getString("attorney1"));//委托代理人:attorney1
				hearingNote.setAttorney1Sex(obj.getString("attorney1Sex"));//性别:attorney1Sex
				hearingNote.setAttorney1Age(obj.getString("attorney1Age"));//年龄:attorney1Age
				hearingNote.setAttorney1Company(obj.getString("attorney1Company"));//工作单位:attorney1Company
				hearingNote.setAttorney2(obj.getString("attorney2"));//委托代理人:attorney2
				hearingNote.setAttorney2Sex(obj.getString("attorney2Sex"));//性别:attorney2Sex
				hearingNote.setAttorney2Age(obj.getString("attorney2Age"));//年龄:attorney2Age
				hearingNote.setAttorney2Company(obj.getString("attorney2Company"));//工作单位:attorney2Company
				hearingNote.setThirdPerson(obj.getString("thirdPerson"));//第三人:thirdPerson
				hearingNote.setOtherParticipants(obj.getString("otherParticipants"));//其他参与人员:otherParticipants
				hearingNote.setHearingRecord(obj.getString("hearingRecord"));//听证记录:hearingRecord
				hearingNote.setUndertaker(obj.getString("undertaker"));//调查人员:undertaker 2个  调用接口5获取数据，type=
				
				hearingNote.setCreateUserID(userId);
				hearingNote.setCreateTime(new Date());
				hearingNote.setDeptId(user.getDept().getId());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveHearingNote(instrumentsInfo, caseInfo,hearingNote, flag,type,root);
				
			}
			else if(wstype.equals("20"))//听证会报告书
			{
				hearingReport.setHearingSummary(obj.getString("hearingSummary"));//听证会基本情况摘要:hearingSummary
				
				hearingReport.setCreateUserID(userId);
				hearingReport.setCreateTime(new Date());
				hearingReport.setDeptId(user.getDept().getId());
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveHearingReport(instrumentsInfo, caseInfo,hearingReport, flag,type,root);
			}
			else if(wstype.equals("21"))//案件处理呈批表
			{
				caseProcessApproval.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type=
				caseProcessApproval.setUndertakerComment(obj.getString("undertakerComment"));//承办人意见：undertakerComment
				caseProcessApproval.setWfss(obj.getString("wfss"));//违法事实及处罚依据：wfss
				caseProcessApproval.setDsrsbyj(obj.getString("dsrsbyj"));//当事人申辩意见：dsrsbyj

				
				caseProcessApproval.setCreateUserID(userId);
				caseProcessApproval.setCreateTime(new Date());
				caseProcessApproval.setDeptId(user.getDept().getId());
				
				caseProcessApproval.setUndertakerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveCaseProcessApproval(instrumentsInfo, caseInfo,caseProcessApproval, flag,type,root);
			}
			else if(wstype.equals("22"))//行政处罚集体讨论记录
			{
				penBraRec.setStartTime(sdff.parse(obj.getString("startTime")));//讨论开始时间:startTime  yyyy-MM-dd HH:mm:ss
				penBraRec.setEndTime(sdff.parse(obj.getString("endTime")));//讨论结束时间:endTime  yyyy-MM-dd HH:mm:ss
				penBraRec.setDiscussionAddress(obj.getString("discussionAddress"));//讨论地点:discussionAddress
				penBraRec.setChairperson(obj.getString("chairperson"));//主持人:chairperson  调用接口5获取数据，type=
				penBraRec.setReportPerson(obj.getString("reportPerson"));//汇报人:reportPerson  调用接口5获取数据，type=
				penBraRec.setDiscussionContent(obj.getString("discussionContent"));//讨论内容:discussionContent
				penBraRec.setDiscussionRecord(obj.getString("discussionRecord"));//讨论记录:discussionRecord
				penBraRec.setConclusionComment(obj.getString("conclusionComment"));//结论性意见:conclusionComment
				penBraRec.setAttendName(obj.getString("attendName"));//出席人员姓名:attendName，多个，以“，”分隔  调用接口5获取数据，type=
				penBraRec.setAttendId(obj.getString("attendId"));//出席人员id:attendId，多个，以“，”分隔  调用接口5获取数据，type=
				
				penBraRec.setCreateUserID(userId);
				penBraRec.setCreateTime(new Date());
				penBraRec.setDeptId(user.getDept().getId());
				
				penBraRec.setRecordPerson(userId);
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePenBraRec(instrumentsInfo, caseInfo,penBraRec, flag,type,root);
				
			}
			else if(wstype.equals("23"))//行政（当场）处罚决定书（单位）
			{
				spoPenDecCom.setAdminPenalties(obj.getString("adminPenalties"));//行政处罚:adminPenalties
				spoPenDecCom.setPunishedSpecies(obj.getString("punishedSpecies"));//处罚种类: punishedSpecies 
				spoPenDecCom.setIllegalIncome(obj.getString("illegalIncome"));//没收违法所得: illegalIncome 
				spoPenDecCom.setFines(obj.getString("fines"));//罚款金额: fines
				
				spoPenDecCom.setFineMethod(obj.getString("fineMethod"));//罚款方式:fineMethod('0':'当场缴纳','1':'缴纳至银行卡')

				
				spoPenDecCom.setLawOfficer(obj.getString("lawOfficer"));//执法人员:lawOfficer 调用接口5获取数据，type=
				
				spoPenDecCom.setCreateUserID(userId);
				spoPenDecCom.setCreateTime(new Date());
				spoPenDecCom.setDeptId(user.getDept().getId());
				spoPenDecCom.setLawOfficerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveSpoPenDecCom(instrumentsInfo, caseInfo,spoPenDecCom, flag,type,root);
				
			}
			else if(wstype.equals("24"))//行政（当场）处罚决定书（个人）
			{
				spoPenDecPer.setAdminPenalties(obj.getString("adminPenalties"));//行政处罚:adminPenalties
				spoPenDecPer.setPunishedSpecies(obj.getString("punishedSpecies"));//处罚种类: punishedSpecies 
				spoPenDecPer.setIllegalIncome(obj.getString("illegalIncome"));//没收违法所得: illegalIncome 
				spoPenDecPer.setFines(obj.getString("fines"));//罚款金额: fines
				spoPenDecPer.setFineMethod(obj.getString("fineMethod"));//罚款方式:fineMethod('0':'当场缴纳','1':'缴纳至银行卡')
				
				spoPenDecPer.setCreateUserID(userId);
				spoPenDecPer.setCreateTime(new Date());
				spoPenDecPer.setDeptId(user.getDept().getId());

				spoPenDecPer.setLawOfficer(obj.getString("lawOfficer"));//执法人员:lawOfficer 调用接口5获取数据，type=
				spoPenDecPer.setLawOfficerName1(user.getDisplayName());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveSpoPenDecPer(instrumentsInfo, caseInfo,spoPenDecPer, flag,type,root);
			}
			else if(wstype.equals("25"))//行政处罚决定书（单位）
			{
				penDecCom.setAdminPenalties(obj.getString("adminPenalties"));//行政处罚:adminPenalties
				penDecCom.setPunishedSpecies(obj.getString("punishedSpecies"));//处罚种类: punishedSpecies 
				penDecCom.setIllegalIncome(obj.getString("illegalIncome"));//没收违法所得: illegalIncome 
				penDecCom.setFines(obj.getString("fines"));//罚款金额: fines
				penDecCom.setWfss(obj.getString("wfss"));//违法事实：wfss
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePenDecCom(instrumentsInfo, caseInfo,penDecCom, flag,type,root);
				
			}
			else if(wstype.equals("26"))//行政处罚决定书（个人）
			{
				penDecPer.setAdminPenalties(obj.getString("adminPenalties"));//行政处罚:adminPenalties
				penDecPer.setPunishedSpecies(obj.getString("punishedSpecies"));//处罚种类: punishedSpecies 
				penDecPer.setIllegalIncome(obj.getString("illegalIncome"));//没收违法所得: illegalIncome 
				penDecPer.setFines(obj.getString("fines"));//罚款金额: fines
				penDecPer.setWfss(obj.getString("wfss"));//违法事实：wfss
				
				penDecPer.setCreateUserID(userId);
				penDecPer.setCreateTime(new Date());
				penDecPer.setDeptId(user.getDept().getId());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePenDecPer(instrumentsInfo, caseInfo,penDecPer, flag,type,root);
			}
			else if(wstype.equals("27"))//罚款催缴通知书
			{
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveFkcjd(instrumentsInfo, caseInfo,type,root);
			}
			else if(wstype.equals("28"))//延期（分期）缴纳罚款审批表
			{
				posFinApp.setReason(obj.getString("reason"));//理由:reason
				posFinApp.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type=
				posFinApp.setUndertakerComment(obj.getString("undertakerComment"));//承办人意见：undertakerComment
				
				posFinApp.setCreateUserID(userId);
				posFinApp.setCreateTime(new Date());
				posFinApp.setDeptId(user.getDept().getId());
				posFinApp.setUndertakerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePosFinApp(instrumentsInfo, caseInfo,posFinApp, flag,type,root);
			}
			else if(wstype.equals("29"))//延期（分期）缴纳罚款批准书
			{
				posFinRat.setFineUppercase(obj.getString("fineUppercase"));//罚款(大写):fineUppercase
				posFinRat.setPostponeMethod(obj.getString("postponeMethod"));//延期方式:postponeMethod('0':'延期缴纳罚款','1':'分期缴纳罚款')

				posFinRat.setRepayPeriod(obj.getString("repayPeriod").substring(0,10));//缴费期限:repayPeriod  yyyy-MM-dd

				if(posFinRat.getPostponeMethod().equals("1"))
				{
					posFinRat.setStageLength(obj.getString("stageLength"));//缴费期:stageLength
					posFinRat.setPay(obj.getString("pay"));//缴纳罚款(大写):pay
					posFinRat.setNoPay(obj.getString("noPay"));//尚未缴纳罚款(大写):noPay
				}
				
				
				posFinRat.setCreateUserID(userId);
				posFinRat.setCreateTime(new Date());
				posFinRat.setDeptId(user.getDept().getId());
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.savePosFinRat(instrumentsInfo, caseInfo,posFinRat, flag,type,root);
			}
			else if(wstype.equals("30"))//强制执行申请书
			{
				enfApp.setCourtName(obj.getString("courtName"));//法院名称:courtName
				enfApp.setYgcl(obj.getString("ygcl"));//有关材料:ygcl
				
				enfApp.setCreateUserID(userId);
				enfApp.setCreateTime(new Date());
				enfApp.setDeptId(user.getDept().getId());
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveEnfApp(instrumentsInfo, caseInfo,enfApp, flag,type,root);
			}
			else if(wstype.equals("31"))//结案审批表
			{
				closeApproval.setApprovalResult(obj.getString("approvalResult"));//处理结果:approvalResult
				closeApproval.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type=
				closeApproval.setExecuteCondition(obj.getString("executeCondition"));//执行情况：executeCondition
				
				closeApproval.setCreateUserID(userId);
				closeApproval.setCreateTime(new Date());
				closeApproval.setDeptId(user.getDept().getId());
				closeApproval.setUndertakerName1(user.getDisplayName());
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveCloseApproval(instrumentsInfo, caseInfo,closeApproval, flag,type,root);
			}
			else if(wstype.equals("32"))//案件移送审批表
			{
				caseRefer.setTransferAuthority(obj.getString("transferAuthority"));//受移送机关:transferAuthority
				caseRefer.setFeedingGrounds(obj.getString("feedingGrounds"));//移送理由:feedingGrounds
				caseRefer.setUndertaker(obj.getString("undertaker"));//承办人：undertaker 调用接口5获取数据，type=
				caseRefer.setUndertakerComment(obj.getString("undertakerComment"));//承办人意见：undertakerComment
				
				caseRefer.setCreateUserID(userId);
				caseRefer.setCreateTime(new Date());
				caseRefer.setDeptId(user.getDept().getId());
				caseRefer.setUndertakerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveCaseRefer(instrumentsInfo, caseInfo,caseRefer, flag,type,root);
			}
			else if(wstype.equals("33"))//案件移送书
			{
				caseTransfer.setProvision(obj.getString("provision"));//规定:provision
				String proname = "";
				String[] ss = caseTransfer.getProvision().replaceAll(" ", "").split(",");
				for(String s:ss)
				{
					LawBasis lawBasis = lawBasisService.getById(s);
					if(lawBasis != null)
					{
						proname += lawBasis.getLawName() + ",";
					}
				}
				if(proname.length() != 0)
				{
					proname = proname.substring(0,proname.length()-1);
				}
				caseTransfer.setProName(proname);
				caseTransfer.setYgcl(obj.getString("ygcl"));//有关材料:ygcl
				
				caseTransfer.setCreateUserID(userId);
				caseTransfer.setCreateTime(new Date());
				caseTransfer.setDeptId(user.getDept().getId());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveCaseTransfer(instrumentsInfo, caseInfo,caseTransfer, flag,type,root);
			}
			else if(wstype.equals("34"))//调查报告
			{
				dcbg.setWfwgxw(obj.getString("wfwgqk"));//违法违规情况:wfwgqk
				dcbg.setUndertaker(obj.getString("undertaker"));//承办人:undertaker 调用接口5获取数据，type=
				dcbg.setDcbgqk(obj.getString("dcbgqk"));//调查取证情况:dcbgqk
				dcbg.setCaseCondition(obj.getString("caseCondition"));//案件基本情况：caseCondition
				dcbg.setUndertakerComment(obj.getString("undertakerComment"));//拟办意见: undertakerComment

				
				dcbg.setCreateUserID(userId);
				dcbg.setCreateTime(new Date());
				dcbg.setDeptId(user.getDept().getId());
				dcbg.setUndertakerName1(user.getDisplayName()) ;
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveDcbg(instrumentsInfo, caseInfo,dcbg, flag,type,root);
			}
			else if(wstype.equals("35"))//特殊事项审批表
			{
				specialItem.setTitle(obj.getString("title"));//事项标题:title
				specialItem.setContent(obj.getString("content"));//事项理由:content
				
				specialItem.setCreateUserID(userId);
				specialItem.setCreateTime(new Date());
				specialItem.setDeptId(user.getDept().getId());
				
				instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				instrumentsInfo = instrumentsInfoUtil.saveSpecialItem(instrumentsInfo, caseInfo, specialItem, flag);
			}
			if(instrumentsInfo.getAjbz() != null && !"".equals(instrumentsInfo.getAjbz()))
			{
				String wsh = instrumentsInfo.getAjbz() + "〔" + instrumentsInfo.getAjh() + "〕"  + instrumentsInfo.getAjhNum() +  "号";
				instrumentsInfo.setWsh(wsh);
			}
			if(instrumentsInfo.getId() != null && !"".equals(instrumentsInfo.getId()))
			{
				instrumentsInfo.setDelFlag(0);
				instrumentsInfoService.update(instrumentsInfo);
			}
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
