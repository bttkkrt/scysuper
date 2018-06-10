package com.jshx.http.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajclcpb.entity.CaseProcessApproval;
import com.jshx.ajclcpb.service.CaseProcessApprovalService;
import com.jshx.ajsp.entity.CloseApproval;
import com.jshx.ajsp.service.CloseApprovalService;
import com.jshx.ajyss.entity.CaseTransfer;
import com.jshx.ajyss.service.CaseTransferService;
import com.jshx.ajyssp.entity.CaseRefer;
import com.jshx.ajyssp.service.CaseReferService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.cyqzpz.entity.SamplingEvidence;
import com.jshx.cyqzpz.service.SamplingEvidenceService;
import com.jshx.cyzjglb.entity.SamplingAssociate;
import com.jshx.cyzjglb.service.SamplingAssociateService;
import com.jshx.dcbg.entity.Dcbg;
import com.jshx.dcbg.service.DcbgService;
import com.jshx.dsrcssbbl.entity.PartyStateNote;
import com.jshx.dsrcssbbl.service.PartyStateNoteService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.http.util.StringTools;
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
import com.jshx.zfyj.service.LawBasisService;
import com.jshx.zgfcyjs.entity.ReviewSubmission;
import com.jshx.zgfcyjs.service.ReviewSubmissionService;
import com.jshx.zlqxzgzls.entity.OrderDeadlineBook;
import com.jshx.zlqxzgzls.service.OrderDeadlineBookService;

/**
 * 获取文书详情接口
 * @author 陆婷 2015-11-5
 *
 */
public class GetWsInfoDetailCommand implements Command{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
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
		String id = obj.getString("id");//获取文书编号
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdff =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		JSONObject json = new JSONObject();
		try {
			InstrumentsInfo instrumentsInfo = instrumentsInfoService.getById(id);
			json.put("id", instrumentsInfo.getId());
			json.put("caseName", instrumentsInfo.getCaseName());
			Map map = new HashMap();
			map.put("codeName", "文书类型");
			map.put("itemValue", instrumentsInfo.getInstrumentType());
			json.put("instrumentType", codeService.findCodeValueByMap(map).getItemText());
			json.put("type", instrumentsInfo.getInstrumentType());
			json.put("ifCheck", instrumentsInfo.getIfCheck());
			String type=instrumentsInfo.getInstrumentType();
			List<CheckRecord> checkRecords = new ArrayList<CheckRecord>();
			String jtxq = "";
			String checkrecord = "";
			String zwlb = "[]";
			Map m = new HashMap();
			m.put("relatedId", instrumentsInfo.getId());
			if(type.equals("1"))//询问通知书
			{
				InquiryNotice inquiryNotice = (InquiryNotice) inquiryNoticeService.findInquiryNotice(m).get(0);
				String cl = "";
				if(inquiryNotice.getSfz() !=null && "1".equals(inquiryNotice.getSfz()))
				{
					cl += "身份证,";
				}
				if(inquiryNotice.getYyzz() !=null && "1".equals(inquiryNotice.getYyzz()))
				{
					cl += "营业执照,";
				}
				if(inquiryNotice.getFddbzm() !=null && "1".equals(inquiryNotice.getFddbzm()))
				{
					cl += "法定代表人身份证明或者委托书,";
				}
				if(inquiryNotice.getQt() !=null && "1".equals(inquiryNotice.getQt()))
				{
					cl += StringTools.NullToStr(inquiryNotice.getDocMaterial(),"");
				}
				jtxq = "被询问人姓名|"+ StringTools.NullToStr(inquiryNotice.getAskPerson(),"") 
				+ "#询问时间|" + sdff.format(inquiryNotice.getInquiryTime()) 
				+ "#询问地点|"+ StringTools.NullToStr(inquiryNotice.getInquiryAddress(),"") 
				+ "#询问材料|" + cl;
			}
			if(type.equals("2"))//询问笔录
			{
				InquiryRecord inquiryRecord = (InquiryRecord) inquiryRecordService.findInquiryRecord(m).get(0);
				String sex = "";
				if(inquiryRecord.getSex() != null && !"".equals(inquiryRecord.getSex()))
				{
					m.put("codeName", "性别");
					m.put("itemValue", inquiryRecord.getSex());
					sex  = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
				}
				jtxq = "询问开始时间|" + sdff.format(inquiryRecord.getInquiryPeriod()) 
				+ "#询问结束时间|" + sdff.format(inquiryRecord.getEndTime())
				+ "#询问次数|" + StringTools.NullToStr(inquiryRecord.getXwcs(),"") 
				+ "#询问地点|" + StringTools.NullToStr(inquiryRecord.getInquiryAddress(),"") 
				+ "#被询问人姓名|" + StringTools.NullToStr(inquiryRecord.getAskedPerson(),"")
				+ "#性别|" + StringTools.NullToStr(sex,"") 
				+ "#年龄|" + StringTools.NullToStr(inquiryRecord.getPeopleAge(),"") 
				+ "#身份证号|" + StringTools.NullToStr(inquiryRecord.getCardId(),"")
				+ "#工作单位|" + StringTools.NullToStr(inquiryRecord.getCompanyName(),"")
				+ "#职位|" + StringTools.NullToStr(inquiryRecord.getPosition(),"") 
				+ "#住址|" + StringTools.NullToStr(inquiryRecord.getAddress(),"") 
				+ "#电话|" + StringTools.NullToStr(inquiryRecord.getTele(),"") 
				+ "#询问人|" + StringTools.NullToStr(inquiryRecord.getInquiryPersonName(),"")
				+ "#记录人|" + StringTools.NullToStr(inquiryRecord.getRecordPersonName(),"")
				+ "#在场人|" + StringTools.NullToStr(inquiryRecord.getPresentPeople(),"");
				
				List<InqRecRecord> recordList = inquiryRecordService.findInqRecRecord(m);
				jtxq += ",询问记录|";
				for(int i=0;i<recordList.size();i++)
				{
					InqRecRecord inq = recordList.get(i);
					int index = i+1;
					jtxq += index + "、问：" +  inq.getAskRecord() + "\r\n" + "答：" + inq.getRecRecord() + "\r\n";
				}
			}
			else if(type.equals("3"))//勘验笔录
			{
				InquestRecord inquestRecord = (InquestRecord) inquestRecordService.findInquestRecord(m).get(0);
				jtxq = "勘验开始时间|" + sdff.format(inquestRecord.getStartTime())
				+ "#勘验结束时间|" + sdff.format(inquestRecord.getEndTime())
				+ "#勘验场所|" + StringTools.NullToStr(inquestRecord.getInquestAddress(),"")
				+ "#天气情况|" + StringTools.NullToStr(inquestRecord.getWeatherCondition(),"")
				+ "#当事人1|" + StringTools.NullToStr(inquestRecord.getParty1(),"")
				+ "#当事人1单位职务|" + StringTools.NullToStr(inquestRecord.getParty1Company(),"")
				+ "#当事人1联系方式|" + StringTools.NullToStr(inquestRecord.getParty1Tel(),"")
				+ "#当事人2|" + StringTools.NullToStr(inquestRecord.getParty2(),"")
				+ "#当事人2单位职务|" + StringTools.NullToStr(inquestRecord.getParty2Company(),"")
				+ "#当事人2联系方式|" + StringTools.NullToStr(inquestRecord.getParty2Tel(),"")
				+ "#被邀请人|" + StringTools.NullToStr(inquestRecord.getInvitee(),"")
				+ "#被邀请人单位职务|" + StringTools.NullToStr(inquestRecord.getInviteeCompany(),"")
				+ "#被邀请人联系方式|" +  StringTools.NullToStr(inquestRecord.getInviteeTel(),"")
				+ "#勘验人|" +  StringTools.NullToStr(inquestRecord.getInquestPersonName(),"")
				+ "#记录人|" +  StringTools.NullToStr(inquestRecord.getRecordPersonName(),"")
				+ "#勘验情况|" + StringTools.NullToStr(inquestRecord.getInquestCondition(),"");
			}
			else if(type.equals("4"))//抽样取证凭证
			{
				SamplingEvidence samplingEvidence = (SamplingEvidence) samplingEvidenceService.findSamplingEvidence(m).get(0);
				m.put("forensicId", instrumentsInfo.getId());
				List<SamplingAssociate> cyqyglbList = samplingAssociateService.findSamplingAssociate(m);
				
				jtxq = "现场负责人|" + StringTools.NullToStr(samplingEvidence.getChargePerson(),"")
				+ "#抽样地点|" +  StringTools.NullToStr(samplingEvidence.getForensicAddress(),"")
				+ "#抽样取证开始时间|" + sdff.format(samplingEvidence.getStartTime())
				+ "#抽样取证结束时间|" + sdff.format(samplingEvidence.getEndTime())
				+ "#执法人员|" + StringTools.NullToStr(samplingEvidence.getLawOfficerName(),"");
				
				JSONArray ja = new JSONArray();
				for(SamplingAssociate inv:cyqyglbList)
				{
					JSONObject jo = new JSONObject();
					jo.put("evidenceName", StringTools.NullToStr(inv.getEvidenceName(),""));//证据物品名称
					jo.put("specificationModel", StringTools.NullToStr(inv.getSpecificationLot(),""));//规格及批号
					jo.put("inventoryNum", StringTools.NullToStr(inv.getSamplingNum(),""));//数量
					ja.add(jo);
				}
				zwlb = ja.toString();
			}
			else if(type.equals("5"))//先行登记保存证据审批表
			{
				PreserveEvidence preserveEvidence = (PreserveEvidence) preserveEvidenceService.findPreserveEvidence(m).get(0);
				jtxq = "提请理由及依据|"  + StringTools.NullToStr(preserveEvidence.getReasonBasic(),"") 
				+ "#保存方式|" + StringTools.NullToStr(preserveEvidence.getPreserveMethod(),"")
				+ "#承办人|" + StringTools.NullToStr(preserveEvidence.getUndertakerName(),"")
				+ "#承办人意见|" + StringTools.NullToStr(preserveEvidence.getUndertakerComment(),"");
				
				m.put("relatedId", instrumentsInfo.getId());
				List<InventoryAssociate> zzdjbczjglbList = inventoryAssociateService.findInventoryAssociate(m);
				JSONArray ja = new JSONArray();
				for(InventoryAssociate inv:zzdjbczjglbList)
				{
					JSONObject jo = new JSONObject();
					jo.put("evidenceName", StringTools.NullToStr(inv.getEvidenceName(),""));//证据名称
					jo.put("specificationModel", StringTools.NullToStr(inv.getSpecificationModel(),""));//规格型号
					jo.put("originPlace", StringTools.NullToStr(inv.getOriginPlace(),""));//产地
					jo.put("condition", StringTools.NullToStr(inv.getCondition(),""));//成色（品级）
					jo.put("company", StringTools.NullToStr(inv.getCompany(),""));//单位
					jo.put("price", StringTools.NullToStr(inv.getPrice(),""));//价格
					jo.put("inventoryNum", StringTools.NullToStr(inv.getInventoryNum(),""));//数量
					jo.put("remark", StringTools.NullToStr(inv.getRemark(),""));//备注
					ja.add(jo);
				}
				zwlb = ja.toString();
			}
			else if(type.equals("6"))//先行登记保存证据通知书
			{
				NoticeEvidence noticeEvidence = (NoticeEvidence) noticeEvidenceService.findNoticeEvidence(m).get(0);
				jtxq = "处理地点|" + StringTools.NullToStr(noticeEvidence.getDealAddress(),"")
				+ "#处理时间|" + sdff.format(noticeEvidence.getDealTime())
				+ "#违法行为|" + StringTools.NullToStr(noticeEvidence.getViolation(),"")
				+ "#承办人|" + StringTools.NullToStr(noticeEvidence.getUndertakerName(),"");
			}
			else if(type.equals("7"))//先行登记保存证据处理审批表
			{
				InventoryCheck inventoryCheck = (InventoryCheck) inventoryCheckService.findInventoryCheck(m).get(0);
				jtxq = "提请理由及依据|" + StringTools.NullToStr(inventoryCheck.getReasonBasic(),"")
				+ "#承办人|" + StringTools.NullToStr(inventoryCheck.getUndertakerName(),"")
				+ "#承办人意见|" + StringTools.NullToStr(inventoryCheck.getUndertakerComment(),"");
			}
			else if(type.equals("8"))//先行登记保存证据处理决定书 
			{
				InventoryDecision inventoryDecision = (InventoryDecision) inventoryDecisionService.findInventoryDecision(m).get(0);
				jtxq = "处理|" + StringTools.NullToStr(inventoryDecision.getDeal(),"");
			}
			else if(type.equals("9"))//现场检查记录
			{
				SiteCheckRecord siteCheckRecord = (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(m).get(0);
				jtxq = "检查开始时间|" + sdff.format(siteCheckRecord.getStartTime())
				+ "#检查结束时间|" + sdff.format(siteCheckRecord.getEndTime())
				+ "#检查场所|" + StringTools.NullToStr(siteCheckRecord.getCheckAddress(),"")
				+ "#检查情况|" + StringTools.NullToStr(siteCheckRecord.getCheckCondition(),"")
				+ "#检查人员|" + StringTools.NullToStr(siteCheckRecord.getCheckPersonName(),"");
			}
			else if(type.equals("10"))//现场处理措施决定书
			{
				LiveActionDecision liveActionDecision = (LiveActionDecision) liveActionDecisionService.findLiveActionDecision(m).get(0);
				jtxq = "违法违规行为|" + StringTools.NullToStr(liveActionDecision.getIllegalAccident(),"")
				+ "#处理决定|" + StringTools.NullToStr(liveActionDecision.getDealDecision(),"")
				+ "#执法依据|" + StringTools.NullToStr(liveActionDecision.getLawName(),"")
				+ "#执法人员|" + StringTools.NullToStr(liveActionDecision.getLawOfficerName(),"");
			}
			else if(type.equals("11"))//责令限期整改指令书
			{
				OrderDeadlineBook orderDeadlineBook = (OrderDeadlineBook) orderDeadlineBookService.findOrderDeadlineBook(m).get(0);
				jtxq = "问题|" + StringTools.NullToStr(orderDeadlineBook.getProblem(),"")
				+ "#整改项|" + StringTools.NullToStr(orderDeadlineBook.getChangeItem(),"")
				+ "#整改期限|" + sdf.format(orderDeadlineBook.getStartTime())
				+ "#执法人员|" + StringTools.NullToStr(orderDeadlineBook.getLawOfficerName(),"");
				 
			}
			else if(type.equals("12"))//整改复查意见书
			{
				ReviewSubmission reviewSubmission = (ReviewSubmission) reviewSubmissionService.findReviewSubmission(m).get(0);
				jtxq = "复查意见|" + StringTools.NullToStr(reviewSubmission.getReviewComment(),"")
				+ "#执法人员|" + StringTools.NullToStr(reviewSubmission.getLawOfficerName(),"");
			}
			else if(type.equals("13"))//强制措施决定书
			{
				EnforenceDecision enforenceDecision = (EnforenceDecision) enforenceDecisionService.findEnforenceDecision(m).get(0);
				jtxq = "问题|" + StringTools.NullToStr(enforenceDecision.getProblem(),"")
				+ "#执法依据|" + StringTools.NullToStr(enforenceDecision.getLawName(),"")
				+ "#措施|" + StringTools.NullToStr(enforenceDecision.getMethod(),"");
			}
			else if(type.equals("14"))//鉴定委托书
			{ 
				IdentifyAttorney identifyAttorney = (IdentifyAttorney) identifyAttorneyService.findIdentifyAttorney(m).get(0);
				
				jtxq = "鉴定机构名称|"  + StringTools.NullToStr(identifyAttorney.getJdjgName(),"")
				+ "#提交时间|"  + sdf.format(identifyAttorney.getSubmitTime())
				+ "#鉴定要求|"  + StringTools.NullToStr(identifyAttorney.getInentifyRequire(),"");
				
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("attorenyId", instrumentsInfo.getId());
				List<IdentifyItemAssociate> jdwpList = identifyItemAssociateService.findIdentifyItemAssociate(paraMap);
				JSONArray ja = new JSONArray();
				for(IdentifyItemAssociate inv:jdwpList)
				{
					JSONObject jo = new JSONObject();
					jo.put("evidenceName", StringTools.NullToStr(inv.getItemName(),""));//物品名称
					jo.put("specificationModel", StringTools.NullToStr(inv.getSpecificationModel(),""));//规格型号
					jo.put("inventoryNum", StringTools.NullToStr(inv.getIdentifyNum(),""));//数量
					jo.put("remark", StringTools.NullToStr(inv.getRemark(),""));//备注
					ja.add(jo);
				}
				zwlb = ja.toString();
			}
			else if(type.equals("15"))//行政处罚告知书
			{
				PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
				jtxq = "案件基本情况|" + StringTools.NullToStr(penaltyNotice.getCaseCondition(),"")
				+ "#违法行为|" + StringTools.NullToStr(penaltyNotice.getWfxw(),"")
				+ "#违反规定|" + StringTools.NullToStr(penaltyNotice.getProName(),"")
				+ "#执法依据|" + StringTools.NullToStr(penaltyNotice.getLawName(),"")
				+ "#行政处罚|" + StringTools.NullToStr(penaltyNotice.getAdminPenality(),"");
			}
			else if(type.equals("16"))//当事人陈述申辩笔录
			{
				PartyStateNote partyStateNote = (PartyStateNote) partyStateNoteService.findPartyStateNote(m).get(0);
				String sex = "";
				if(partyStateNote.getSex() != null && !"".equals(partyStateNote.getSex()))
				{
					m.put("codeName", "性别");
					m.put("itemValue", partyStateNote.getSex());
					sex  = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
				}
				jtxq = "陈述开始时间|" + sdff.format(partyStateNote.getStartTime())
				+ "#陈述结束时间|" + sdff.format(partyStateNote.getEndTime())
				+ "#陈述地点|" + StringTools.NullToStr(partyStateNote.getStateAddress(),"")
				+ "#陈述申辩人|" + StringTools.NullToStr(partyStateNote.getStateDefense(),"")
				+ "#性别|" + sex
				+ "#职务|" + StringTools.NullToStr(partyStateNote.getPosition(),"")
				+ "#电话|" + StringTools.NullToStr(partyStateNote.getTele(),"")						
				+ "#联系地址|" + StringTools.NullToStr(partyStateNote.getAddress(),"")
				+ "#邮编|" + StringTools.NullToStr(partyStateNote.getZipCode(),"")
				+ "#陈述申辩记录|" + StringTools.NullToStr(partyStateNote.getStateRecord(),"")
				+ "#承办人|" + StringTools.NullToStr(partyStateNote.getUndertakerName(),"")
				+ "#记录人|" + StringTools.NullToStr(partyStateNote.getRecorderName(),"");
			}
			else if(type.equals("17"))//听证告知书
			{
				//查询行政处罚告知书
				m.put("caseId", instrumentsInfo.getCaseId());
				m.put("instrumentType", "15");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					jtxq = "案件基本情况|" + StringTools.NullToStr(penaltyNotice.getCaseCondition(),"")
					+ "#违法行为|" + StringTools.NullToStr(penaltyNotice.getWfxw(),"")
					+ "#违反规定|" + StringTools.NullToStr(penaltyNotice.getProName(),"")
					+ "#执法依据|" + StringTools.NullToStr(penaltyNotice.getLawName(),"")
					+ "#行政处罚|" + StringTools.NullToStr(penaltyNotice.getAdminPenality(),"");
				}
			}
			else if(type.equals("18"))//听证会通知书
			{
				HearingNotice hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
				String sfgk = "";
				if(hearingNotice.getPublicCondition() != null && !"".equals(hearingNotice.getPublicCondition()))
				{
					m.put("codeName", "是或否");
					m.put("itemValue", hearingNotice.getPublicCondition());
					sfgk  = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
				}
				jtxq = "听证会时间|" + sdf.format(hearingNotice.getHearingTime())
				+ "#是否公开|" + sfgk
				+ "#听证地点|" + StringTools.NullToStr(hearingNotice.getHearingAddress(),"")
				+ "#听证主持人姓名|" + StringTools.NullToStr(hearingNotice.getHearingChairpersonName(),"")
				+ "#书记员|" + StringTools.NullToStr(hearingNotice.getClerkName(),"")
				+ "#听证员|" + StringTools.NullToStr(hearingNotice.getHearingOfficerName(),"");
			}
			else if(type.equals("19"))//听证笔录
			{
				HearingNote hearingNote = (HearingNote) hearingNoteService.findHearingNote(m).get(0);
				String sex1 = "";
				if(hearingNote.getAttorney1Sex() != null && !"".equals(hearingNote.getAttorney1Sex()))
				{
					m.put("codeName", "性别");
					m.put("itemValue", hearingNote.getAttorney1Sex());
					sex1  = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
				}
				String sex2 = "";
				if(hearingNote.getAttorney2Sex() != null && !"".equals(hearingNote.getAttorney2Sex()))
				{
					m.put("codeName", "性别");
					m.put("itemValue", hearingNote.getAttorney2Sex());
					sex2  = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
				}
				jtxq = "听证开始时间|" + sdff.format(hearingNote.getStartTime())
				+ "#听证结束时间|" + sdff.format(hearingNote.getEndTime())
				+ "#委托代理人1|" + StringTools.NullToStr(hearingNote.getAttorney1(),"")
				+ "#委托代理人1性别|" + sex1
				+ "#委托代理人1工作单位|" + StringTools.NullToStr(hearingNote.getAttorney1Company(),"")
				+ "#委托代理人1年龄|" + StringTools.NullToStr(hearingNote.getAttorney1Age(),"")
				+ "#委托代理人2|" + StringTools.NullToStr(hearingNote.getAttorney2(),"")
				+ "#委托代理人2性别|" + sex2
				+ "#委托代理人2工作单位|" + StringTools.NullToStr(hearingNote.getAttorney2Company(),"")
				+ "#委托代理人2年龄|" + StringTools.NullToStr(hearingNote.getAttorney2Age(),"")
				+ "#第三人|" + StringTools.NullToStr(hearingNote.getThirdPerson(),"")
				+ "#其他参与人员|" + StringTools.NullToStr(hearingNote.getOtherParticipants(),"")
				+ "#听证记录|" + StringTools.NullToStr(hearingNote.getHearingRecord(),"")
				+ "#调查人员|" + StringTools.NullToStr(hearingNote.getUndertakerName(),"");
			}
			else if(type.equals("20"))//听证会报告书
			{
				HearingReport hearingReport = (HearingReport) hearingReportService.findHearingReport(m).get(0);
				jtxq = "听证会基本情况摘要|" + StringTools.NullToStr(hearingReport.getHearingSummary(),"") ;

			}
			else if(type.equals("21"))//案件处理呈批表
			{
				CaseProcessApproval caseProcessApproval = (CaseProcessApproval) caseProcessApprovalService.findCaseProcessApproval(m).get(0);
				jtxq = "承办人|" + StringTools.NullToStr(caseProcessApproval.getUndertakerName(),"") 
				+ "#承办人意见|" + StringTools.NullToStr(caseProcessApproval.getUndertakerComment(),"") 
				+ "#违法事实及处罚依据|" + StringTools.NullToStr(caseProcessApproval.getWfss(),"")
				+ "#当事人申辩意见|" + StringTools.NullToStr(caseProcessApproval.getDsrsbyj(),"");
			}
			else if(type.equals("22"))//行政处罚集体讨论记录
			{
				PenBraRec penBraRec = (PenBraRec) penBraRecService.findPenBraRec(m).get(0);
				jtxq = "讨论开始时间|" + sdff.format(penBraRec.getStartTime())
				+ "#讨论结束时间|" + sdff.format(penBraRec.getEndTime())
				+ "#讨论地点|" + StringTools.NullToStr(penBraRec.getDiscussionAddress(),"")
				+ "#主持人|" + StringTools.NullToStr(penBraRec.getChairpersonName(),"")
				+ "#汇报人|" + StringTools.NullToStr(penBraRec.getReportPersonName(),"")
				+ "#记录人|" + StringTools.NullToStr(penBraRec.getRecordPersonName(),"")
				+ "#出席人员姓名及职务|" + StringTools.NullToStr(penBraRec.getAttendName(),"")
				+ "#讨论内容|" + StringTools.NullToStr(penBraRec.getDiscussionContent(),"")
				+ "#讨论记录|" + StringTools.NullToStr(penBraRec.getDiscussionRecord(),"")
				+ "#结论性意见|" + StringTools.NullToStr(penBraRec.getConclusionComment(),"");
				
			}
			else if(type.equals("23"))//行政（当场）处罚决定书（单位）
			{
				SpoPenDecCom spoPenDecCom = (SpoPenDecCom) spoPenDecComService.findSpoPenDecCom(m).get(0);
				String cfzl = "";
				if(spoPenDecCom.getPunishedSpecies() != null && !"".equals(spoPenDecCom.getPunishedSpecies()))
				{
					String ss[] = spoPenDecCom.getPunishedSpecies().replaceAll(" ", "").split(",");
					for(String s:ss)
					{
						m.put("codeName", "处罚种类");
						m.put("itemValue", s);
						String aa = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
						if(aa != null && !"".equals(aa))
						{
							cfzl += aa + "，";
						}
					}
				}
				if(cfzl.length() != 0)
				{
					cfzl = cfzl.substring(0,cfzl.length()-1);
				}
				
				String fkfs = "";
				if(spoPenDecCom.getFineMethod() != null)
				{
					if(spoPenDecCom.getFineMethod().equals("0"))
					{
						fkfs = "当场缴纳";
					}
					else
					{
						fkfs = "缴纳至银行卡";
					}
				}
				
				jtxq = "处罚种类|" + cfzl 
				+ "#没收违法所得|" + StringTools.NullToStr(spoPenDecCom.getIllegalIncome(),"")
				+ "#罚款金额|" + StringTools.NullToStr(spoPenDecCom.getFines(),"")
				+ "#罚款方式|" + fkfs
				+ "#行政处罚|" + StringTools.NullToStr(spoPenDecCom.getAdminPenalties(),"")
				+ "#执法人员|" + StringTools.NullToStr(spoPenDecCom.getLawOfficerName(),"");
			}
			else if(type.equals("24"))//行政（当场）处罚决定书（个人）
			{
				SpoPenDecPer spoPenDecPer = (SpoPenDecPer) spoPenDecPerService.findSpoPenDecPer(m).get(0);
				String cfzl = "";
				if(spoPenDecPer.getPunishedSpecies() != null && !"".equals(spoPenDecPer.getPunishedSpecies()))
				{
					String ss[] = spoPenDecPer.getPunishedSpecies().replaceAll(" ", "").split(",");
					for(String s:ss)
					{
						m.put("codeName", "处罚种类");
						m.put("itemValue", s);
						String aa = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
						if(aa != null && !"".equals(aa))
						{
							cfzl += aa + "，";
						}
					}
				}
				if(cfzl.length() != 0)
				{
					cfzl = cfzl.substring(0,cfzl.length()-1);
				}
				String fkfs = "";
				if(spoPenDecPer.getFineMethod() != null)
				{
					if(spoPenDecPer.getFineMethod().equals("0"))
					{
						fkfs = "当场缴纳";
					}
					else
					{
						fkfs = "缴纳至银行卡";
					}
				}
				jtxq = "处罚种类|" + cfzl
				+ "#没收违法所得|" + StringTools.NullToStr(spoPenDecPer.getIllegalIncome(),"")
				+ "#罚款金额|" + StringTools.NullToStr(spoPenDecPer.getFines(),"")
				+ "#罚款方式|" + fkfs
				+ "#行政处罚|" + StringTools.NullToStr(spoPenDecPer.getAdminPenalties(),"")
				+ "#执法人员|" + StringTools.NullToStr(spoPenDecPer.getLawOfficerName(),"");
			}
			else if(type.equals("25"))//行政处罚决定书（单位）
			{
				PenDecCom penDecCom = (PenDecCom) penDecComService.findPenDecCom(m).get(0);
				String cfzl = "";
				if(penDecCom.getPunishedSpecies() != null && !"".equals(penDecCom.getPunishedSpecies()))
				{
					String ss[] = penDecCom.getPunishedSpecies().replaceAll(" ", "").split(",");
					for(String s:ss)
					{
						m.put("codeName", "处罚种类");
						m.put("itemValue", s);
						String aa = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
						if(aa != null && !"".equals(aa))
						{
							cfzl += aa + "，";
						}
					}
				}
				if(cfzl.length() != 0)
				{
					cfzl = cfzl.substring(0,cfzl.length()-1);
				}
				jtxq = "处罚种类|" + cfzl
				+ "#没收违法所得|" + StringTools.NullToStr(penDecCom.getIllegalIncome(),"")
				+ "#罚款金额|" + StringTools.NullToStr(penDecCom.getFines(),"")
				+ "#违法事实|" + StringTools.NullToStr(penDecCom.getWfss(),"")
				+ "#行政处罚|" + StringTools.NullToStr(penDecCom.getAdminPenalties(),"");
			}
			else if(type.equals("26"))//行政处罚决定书（个人）
			{
				PenDecPer penDecPer = (PenDecPer) penDecPerService.findPenDecPer(m).get(0);
				String cfzl = "";
				if(penDecPer.getPunishedSpecies() != null && !"".equals(penDecPer.getPunishedSpecies()))
				{
					String ss[] = penDecPer.getPunishedSpecies().replaceAll(" ", "").split(",");
					for(String s:ss)
					{
						m.put("codeName", "处罚种类");
						m.put("itemValue", s);
						String aa = codeService.findCodeValueByMap(m)!= null?codeService.findCodeValueByMap(m).getItemText():"";
						if(aa != null && !"".equals(aa))
						{
							cfzl += aa + "，";
						}
					}
				}
				if(cfzl.length() != 0)
				{
					cfzl = cfzl.substring(0,cfzl.length()-1);
				}
				jtxq = "处罚种类|" + cfzl
				+ "#没收违法所得|" + StringTools.NullToStr(penDecPer.getIllegalIncome(),"")
				+ "#罚款金额|" + StringTools.NullToStr(penDecPer.getFines(),"")
				+ "#违法事实|" + StringTools.NullToStr(penDecPer.getWfss(),"")
				+ "#行政处罚|" + StringTools.NullToStr(penDecPer.getAdminPenalties(),"");
			}
			else if(type.equals("27"))//罚款催缴通知书
			{
				
			}
			else if(type.equals("28"))//延期（分期）缴纳罚款审批表
			{
				PosFinApp posFinApp = (PosFinApp) posFinAppService.findPosFinApp(m).get(0);
				jtxq = "理由|" + StringTools.NullToStr(posFinApp.getReason(),"") 
				+ "#承办人|" + StringTools.NullToStr(posFinApp.getUndertakerName(),"")
				+ "#承办人意见|" + StringTools.NullToStr(posFinApp.getUndertakerComment(),"");

			}
			else if(type.equals("29"))//延期（分期）缴纳罚款批准书
			{
				PosFinRat posFinRat = (PosFinRat) posFinRatService.findPosFinRat(m).get(0);
				String yqfs = "";
				String fq = "";
				if(posFinRat.getPostponeMethod() != null && "0".equals(posFinRat.getPostponeMethod()))
				{
					yqfs = "延期缴纳罚款";
				}
				else if(posFinRat.getPostponeMethod() != null && "1".equals(posFinRat.getPostponeMethod()))
				{
					yqfs = "分期缴纳罚款";
					fq = "#缴费期|" + StringTools.NullToStr(posFinRat.getStageLength(),"")
						+"#缴纳罚款(大写)|" + StringTools.NullToStr(posFinRat.getPay(),"")
						+"#尚未缴纳罚款(大写)|" + StringTools.NullToStr(posFinRat.getNoPay(),"");
				}
				jtxq =  "罚款(大写)|" + StringTools.NullToStr(posFinRat.getFineUppercase(),"")
				+ "#延期方式|" + yqfs
				+ "#缴费期限|" + StringTools.NullToStr(posFinRat.getRepayPeriod(),"") + fq;
				
			}
			else if(type.equals("30"))//强制执行申请书
			{
				EnfApp enfApp = (EnfApp) enfAppService.findEnfApp(m).get(0);
				jtxq = "法院名称|" + StringTools.NullToStr(enfApp.getCourtName(),"")
				+ "#有关材料|" + StringTools.NullToStr(enfApp.getYgcl(),"");
			}
			else if(type.equals("31"))//结案审批表
			{
				CloseApproval closeApproval = (CloseApproval) closeApprovalService.findCloseApproval(m).get(0);
				jtxq = "处理结果|" + StringTools.NullToStr(closeApproval.getApprovalResult(),"") 
				+ "#承办人|" + StringTools.NullToStr(closeApproval.getUndertakerName(),"")
				+ "#执行情况|" + StringTools.NullToStr(closeApproval.getExecuteCondition(),"");
			}
			else if(type.equals("32"))//案件移送审批表
			{
				CaseRefer caseRefer = (CaseRefer) caseReferService.findCaseRefer(m).get(0);
				jtxq = "受移送机关|" + StringTools.NullToStr(caseRefer.getTransferAuthority(),"") 
				+ "#移送理由|" + StringTools.NullToStr(caseRefer.getFeedingGrounds(),"")
				+ "#承办人|" + StringTools.NullToStr(caseRefer.getUndertakerName(),"")
				+ "#承办人意见|" + StringTools.NullToStr(caseRefer.getUndertakerComment(),"");
			}
			else if(type.equals("33"))//案件移送书
			{
				CaseTransfer caseTransfer = (CaseTransfer) caseTransferService.findCaseTransfer(m).get(0);
				jtxq = "规定|" + StringTools.NullToStr(caseTransfer.getProName(),"")
				+ "#有关材料|" + StringTools.NullToStr(caseTransfer.getYgcl(),"");
			}
			else if(type.equals("34"))//调查报告
			{
				Dcbg dcbg = (Dcbg) dcbgService.findDcbg(m).get(0);
				jtxq = "案件基本情况|" + StringTools.NullToStr(dcbg.getCaseCondition(),"") 
				+ "#违法违规情况|" + StringTools.NullToStr(dcbg.getWfwgxw(),"") 
				+ "#调查取证情况|" + StringTools.NullToStr(dcbg.getDcbgqk(),"")
				+ "#承办人|" + StringTools.NullToStr(dcbg.getUndertakerName(),"")
				+ "#拟办意见|" + StringTools.NullToStr(dcbg.getUndertakerComment(),"");
			}
			else if(type.equals("35"))//特殊事项审批表
			{
				SpecialItem specialItem= (SpecialItem) specialItemService.findSpecialItem(m).get(0);
				jtxq = "事项标题|" + StringTools.NullToStr(specialItem.getTitle(),"") 
				+ "#事项理由|" + StringTools.NullToStr(specialItem.getContent(),"");
			}
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", instrumentsInfo.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
			for(CheckRecord c:checkRecords)
			{
				String time = "";
				if(c.getCheckTime()!=null)
				{
					time = new SimpleDateFormat("yyyy-MM-dd").format(c.getCheckTime());
				}
				checkrecord += time+","+c.getCheckUsername()+c.getCheckResult()+"[备注："+StringTools.NullToStr(c.getCheckRemark(),"")+"]" + ";";
			}
			if(checkrecord.length() != 0)
			{
				checkrecord = checkrecord.substring(0,checkrecord.length()-1);
			}
			json.put("jtxq", jtxq);
			json.put("zwlb", zwlb);
			json.put("checkrecord", checkrecord);
			
			br.setCode("0");
			br.setMessage("成功");
			br.setContent(json.toString());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			br.setCode("2");
			br.setMessage("异常");
		}
		
		
		return br;
	}
}
