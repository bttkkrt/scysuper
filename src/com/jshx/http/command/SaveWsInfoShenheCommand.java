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
import com.jshx.cyqzpz.entity.SamplingEvidence;
import com.jshx.cyqzpz.service.SamplingEvidenceService;
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
import com.jshx.jdwpglb.service.IdentifyItemAssociateService;
import com.jshx.jdwts.entity.IdentifyAttorney;
import com.jshx.jdwts.service.IdentifyAttorneyService;
import com.jshx.kcbl.entity.InquestRecord;
import com.jshx.kcbl.service.InquestRecordService;
import com.jshx.module.admin.entity.User;
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
import com.jshx.xwbl.entity.InquiryRecord;
import com.jshx.xwbl.service.InquiryRecordService;
import com.jshx.xwtzs.entity.InquiryNotice;
import com.jshx.xwtzs.service.InquiryNoticeService;
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
import com.jshx.zfyj.service.LawBasisService;
import com.jshx.zgfcyjs.entity.ReviewSubmission;
import com.jshx.zgfcyjs.service.ReviewSubmissionService;
import com.jshx.zlqxzgzls.entity.OrderDeadlineBook;
import com.jshx.zlqxzgzls.service.OrderDeadlineBookService;

/**
 * 文书审核接口
 *@author 陆婷 2015-11-5
 *
 */
public class SaveWsInfoShenheCommand implements Command{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private CaseInfoService caseInfoService= (CaseInfoService) SpringContextHolder.getBean("caseInfoService");
	private UserService userService= (UserService) SpringContextHolder.getBean("userService");
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
	
	public BaseResponse execute(JSONObject obj) {
		SummaryBean br = new  SummaryBean();
		String userId = obj.getString("userId");//获取用户的id
		String id = obj.getString("id");//文书编号
		String result = obj.getString("result");//审核结果（'0':'通过','1':'不通过'）
		String remark = obj.getString("remark");//备注
		String time = obj.getString("time");//审核时间
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String, Object> map=new HashMap<String, Object>();
		String root = this.getClass().getResource("/").getPath();
		root = root.replaceAll("\\\\", "/") + "../../";
		User user = userService.findUserById(userId);
		try {
			Date checkTime = sdf.parse(time);
			InstrumentsInfo in = instrumentsInfoService.getById(id);
			CaseInfo ca = caseInfoService.getById(in.getCaseId());
			String type = in.getInstrumentType();
			Map mm = new HashMap();
			mm.put("relatedId", id);
			InstrumentsInfoUtil instrumentsInfoUtil = new InstrumentsInfoUtil();
			if(type.equals("1"))//询问通知书
			{
				List<InquiryNotice> inqList = inquiryNoticeService.findInquiryNotice(mm);
				InquiryNotice inq = inqList.get(0);
				instrumentsInfoUtil.shenheInquiryNotice(in, ca, inq, user, root, result, remark, checkTime);
			}
			else if(type.equals("2"))//询问笔录 luting 2015-10-25
			{
				List<InquiryRecord> inqList = inquiryRecordService.findInquiryRecord(mm);
				InquiryRecord inq = inqList.get(0);
				instrumentsInfoUtil.shenheInquiryRecord(in, ca, inq, user, root, result, remark, checkTime);
			}
			else if(type.equals("3"))//勘验笔录 luting 2015-10-25
			{
				InquestRecord inq = (InquestRecord) inquestRecordService.findInquestRecord(mm).get(0);
				instrumentsInfoUtil.shenheInquestRecord(in, ca, inq, user, root, result, remark, checkTime);
			}
			else if(type.equals("4"))//抽样取证凭证 luting 2015-10-25
			{
				SamplingEvidence sam = (SamplingEvidence) samplingEvidenceService.findSamplingEvidence(mm).get(0);
				instrumentsInfoUtil.shenheSamplingEvidence(in, ca, sam, user, root, result, remark, checkTime);
			}
			else if(type.equals("5"))//先行登记保存证据审批表 luting 2015-10-25
			{
				PreserveEvidence pe = (PreserveEvidence) preserveEvidenceService.findPreserveEvidence(mm).get(0);
				instrumentsInfoUtil.shenhePreserveEvidence(in, ca, pe, user, root, result, remark, checkTime);
			}
			else if(type.equals("6"))//先行登记保存证据通知书
			{
				NoticeEvidence no = (NoticeEvidence) noticeEvidenceService.findNoticeEvidence(mm).get(0);
				instrumentsInfoUtil.shenheNoticeEvidence(in, ca, no, user, root, result, remark, checkTime);
			}
			else if(type.equals("7"))//先行登记保存证据处理审批表
			{
				InventoryCheck inv = (InventoryCheck) inventoryCheckService.findInventoryCheck(mm).get(0);
				instrumentsInfoUtil.shenheInventoryCheck(in, ca, inv, user, root, result, remark, checkTime);
			}
			else if(type.equals("8"))//先行登记保存证据处理决定书 
			{
				InventoryDecision inv = (InventoryDecision) inventoryDecisionService.findInventoryDecision(mm).get(0);
				instrumentsInfoUtil.shenheInventoryDecision(in, ca, inv, user, root, result, remark, checkTime);
			}
			else if(type.equals("9"))//现场检查记录
			{
				SiteCheckRecord sit = (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(mm).get(0);
				instrumentsInfoUtil.shenheSiteCheckRecord(in, ca, sit, user, root, result, remark, checkTime);
			}
			else if(type.equals("10"))//现场处理措施决定书
			{
				LiveActionDecision liv = (LiveActionDecision) liveActionDecisionService.findLiveActionDecision(mm).get(0);
				instrumentsInfoUtil.shenheLiveActionDecision(in, ca, liv, user, root, result, remark, checkTime);
			}
			else if(type.equals("11"))//责令限期整改指令书
			{
				OrderDeadlineBook od = (OrderDeadlineBook) orderDeadlineBookService.findOrderDeadlineBook(mm).get(0);
				instrumentsInfoUtil.shenheOrderDeadlineBook(in, ca, od, user, root, result, remark, checkTime);
			}
			else if(type.equals("12"))//整改复查意见书
			{
				ReviewSubmission rev = (ReviewSubmission) reviewSubmissionService.findReviewSubmission(mm).get(0);
				instrumentsInfoUtil.shenheReviewSubmission(in, ca, rev, user, root, result, remark, checkTime);
			}
			else if(type.equals("13"))//强制措施决定书
			{
				EnforenceDecision enf = (EnforenceDecision) enforenceDecisionService.findEnforenceDecision(mm).get(0);
				instrumentsInfoUtil.shenheEnforenceDecision(in, ca, enf, user, root, result, remark, checkTime);
			}
			else if(type.equals("14"))//鉴定委托书
			{ 
				IdentifyAttorney ide = (IdentifyAttorney) identifyAttorneyService.findIdentifyAttorney(mm).get(0);
				instrumentsInfoUtil.shenheIdentifyAttorney(in, ca, ide, user, root, result, remark, checkTime);
			}
			else if(type.equals("15"))//行政处罚告知书
			{
				PenaltyNotice pen = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(mm).get(0);
				instrumentsInfoUtil.shenhePenaltyNotice(in, ca, pen, user, root, result, remark, checkTime);
			}
			else if(type.equals("16"))//当事人陈述申辩笔录
			{
				PartyStateNote par = (PartyStateNote) partyStateNoteService.findPartyStateNote(mm).get(0);
				instrumentsInfoUtil.shenhePartyStateNote(in, ca, par, user, root, result, remark, checkTime);
			}
			else if(type.equals("17"))//听证告知书
			{
				HearingTell he = (HearingTell) hearingTellService.findHearingTell(mm).get(0);
				instrumentsInfoUtil.shenheHearingTell(in, ca, he, user, root, result, remark, checkTime);
			}
			else if(type.equals("18"))//听证会通知书
			{
				HearingNotice hea = (HearingNotice) hearingNoticeService.findHearingNotice(mm).get(0);
				instrumentsInfoUtil.shenheHearingNotice(in, ca, hea, user, root, result, remark, checkTime);
			}
			else if(type.equals("19"))//听证笔录
			{
				HearingNote hea = (HearingNote) hearingNoteService.findHearingNote(mm).get(0);
				instrumentsInfoUtil.shenheHearingNote(in, ca, hea, user, root, result, remark, checkTime);
			}
			else if(type.equals("20"))//听证会报告书
			{
				HearingReport he = (HearingReport) hearingReportService.findHearingReport(mm).get(0);
				instrumentsInfoUtil.shenheHearingReport(in, ca, he, user, root, result, remark, checkTime);
			}
			else if(type.equals("21"))//案件处理呈批表
			{
				CaseProcessApproval cas = (CaseProcessApproval) caseProcessApprovalService.findCaseProcessApproval(mm).get(0);
				instrumentsInfoUtil.shenheCaseProcessApproval(in, ca, cas, user, root, result, remark, checkTime);
			}
			else if(type.equals("22"))//行政处罚集体讨论记录
			{
				PenBraRec pen  = (PenBraRec) penBraRecService.findPenBraRec(mm).get(0);
				instrumentsInfoUtil.shenhePenBraRec(in, ca, pen, user, root, result, remark, checkTime);
			}
			else if(type.equals("23"))//行政（当场）处罚决定书（单位）
			{
				SpoPenDecCom spo = (SpoPenDecCom) spoPenDecComService.findSpoPenDecCom(mm).get(0);
				instrumentsInfoUtil.shenheSpoPenDecCom(in, ca, spo, user, root, result, remark, checkTime);
			}
			else if(type.equals("24"))//行政（当场）处罚决定书（个人）
			{
				SpoPenDecPer spo = (SpoPenDecPer) spoPenDecPerService.findSpoPenDecPer(mm).get(0);
				instrumentsInfoUtil.shenheSpoPenDecPer(in, ca, spo, user, root, result, remark, checkTime);
			}
			else if(type.equals("25"))//行政处罚决定书（单位）
			{
				PenDecCom pen = (PenDecCom) penDecComService.findPenDecCom(mm).get(0);
				instrumentsInfoUtil.shenhePenDecCom(in, ca, pen, user, root, result, remark, checkTime);
			}
			else if(type.equals("26"))//行政处罚决定书（个人）
			{
				PenDecPer pen = (PenDecPer) penDecPerService.findPenDecPer(mm).get(0);
				instrumentsInfoUtil.shenhePenDecPer(in, ca, pen, user, root, result, remark, checkTime);
			}
			else if(type.equals("27"))//罚款催缴通知书
			{
				instrumentsInfoUtil.shenheFkcjd(in, ca, user, root, result, remark, checkTime);
			}
			else if(type.equals("28"))//延期（分期）缴纳罚款审批表
			{
				PosFinApp pos = (PosFinApp) posFinAppService.findPosFinApp(mm).get(0);
				instrumentsInfoUtil.shenhePosFinApp(in, ca, pos, user, root, result, remark, checkTime);
			}
			else if(type.equals("29"))//延期（分期）缴纳罚款批准书
			{
				PosFinRat pos = (PosFinRat) posFinRatService.findPosFinRat(mm).get(0);
				instrumentsInfoUtil.shenhePosFinRat(in, ca, pos, user, root, result, remark, checkTime);
			}
			else if(type.equals("30"))//强制执行申请书
			{
				EnfApp enf = (EnfApp) enfAppService.findEnfApp(mm).get(0);
				instrumentsInfoUtil.shenheEnfApp(in, ca, enf, user, root, result, remark, checkTime);
			}
			else if(type.equals("31"))//结案审批表
			{
				CloseApproval clo = (CloseApproval) closeApprovalService.findCloseApproval(mm).get(0);
				instrumentsInfoUtil.shenheCloseApproval(in, ca, clo, user, root, result, remark, checkTime);
			}
			else if(type.equals("32"))//案件移送审批表
			{
				CaseRefer cas = (CaseRefer) caseReferService.findCaseRefer(mm).get(0);
				instrumentsInfoUtil.shenheCaseRefer(in, ca, cas, user, root, result, remark, checkTime);
			}
			else if(type.equals("33"))//案件移送书
			{
				CaseTransfer cas = (CaseTransfer) caseTransferService.findCaseTransfer(mm).get(0);
				instrumentsInfoUtil.shenheCaseTransfer(in, ca, cas, user, root, result, remark, checkTime);
			}
			else if(type.equals("34"))//调查报告
			{
				Dcbg dcb= (Dcbg) dcbgService.findDcbg(mm).get(0);
				instrumentsInfoUtil.shenheDcbg(in, ca, dcb, user, root, result, remark, checkTime);
			}
			else if(type.equals("35"))//特殊事项审批表
			{
				SpecialItem spe= (SpecialItem) specialItemService.findSpecialItem(mm).get(0);
				instrumentsInfoUtil.shenheSpecialItem(in, ca, spe, user, root, result, remark, checkTime);
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
}
