package com.jshx.http.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.jshx.ajclcpb.service.CaseProcessApprovalService;
import com.jshx.ajsp.service.CloseApprovalService;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.ajyss.service.CaseTransferService;
import com.jshx.ajyssp.service.CaseReferService;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.cyqzpz.entity.SamplingEvidence;
import com.jshx.cyqzpz.service.SamplingEvidenceService;
import com.jshx.cyzjglb.service.SamplingAssociateService;
import com.jshx.dcbg.entity.Dcbg;
import com.jshx.dcbg.service.DcbgService;
import com.jshx.dsrcssbbl.entity.PartyStateNote;
import com.jshx.dsrcssbbl.service.PartyStateNoteService;
import com.jshx.http.bean.SummaryBean;
import com.jshx.inventorycheck.service.InventoryCheckService;
import com.jshx.inwentorydecision.service.InventoryDecisionService;
import com.jshx.jdwpglb.service.IdentifyItemAssociateService;
import com.jshx.jdwts.service.IdentifyAttorneyService;
import com.jshx.kcbl.entity.InquestRecord;
import com.jshx.kcbl.service.InquestRecordService;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qzcsjds.service.EnforenceDecisionService;
import com.jshx.qzzxsqs.service.EnfAppService;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.tssxspb.service.SpecialItemService;
import com.jshx.tzbl.service.HearingNoteService;
import com.jshx.tzgzs.service.HearingTellService;
import com.jshx.tzhbgs.service.HearingReportService;
import com.jshx.tztzs.service.HearingNoticeService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;
import com.jshx.xcclcsjds.service.LiveActionDecisionService;
import com.jshx.xcjcjl.service.SiteCheckRecordService;
import com.jshx.xwbl.entity.InquiryRecord;
import com.jshx.xwbl.service.InquiryRecordService;
import com.jshx.xwtzs.service.InquiryNoticeService;
import com.jshx.xxdjbczjqdglb.service.InventoryAssociateService;
import com.jshx.xxdjbczjtzs.service.NoticeEvidenceService;
import com.jshx.xxdjzjspb.service.PreserveEvidenceService;
import com.jshx.xzcfgzs.entity.PenaltyNotice;
import com.jshx.xzcfgzs.service.PenaltyNoticeService;
import com.jshx.xzcfjdsdw.service.PenDecComService;
import com.jshx.xzcfjdsgr.service.PenDecPerService;
import com.jshx.xzcfjttljl.service.PenBraRecService;
import com.jshx.xzdccfjdgrs.service.SpoPenDecPerService;
import com.jshx.xzdccfjdsdw.service.SpoPenDecComService;
import com.jshx.yqjnfkpzs.service.PosFinRatService;
import com.jshx.yqjnfkspb.service.PosFinAppService;
import com.jshx.zfyj.service.LawBasisService;
import com.jshx.zgfcyjs.service.ReviewSubmissionService;
import com.jshx.zlqxzgzls.service.OrderDeadlineBookService;

/**
 * 文书初始化
 * @author 陆婷 2015-11-5
 *
 */
public class GetWsInitCommand implements Command{
	private InstrumentsInfoService instrumentsInfoService= (InstrumentsInfoService) SpringContextHolder.getBean("instrumentsInfoService");
	private CodeService codeService= (CodeService) SpringContextHolder.getBean("codeService");
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
		String caseId = obj.getString("caseId");//获取文书编号
		String wstype = obj.getString("wstype");//获取文书类型
		
		JSONObject json = new JSONObject();
		try {
			//查询行政处罚告知书
			Map m = new HashMap();
			m.put("caseId", caseId);
			if(wstype.equals("15"))//行政处罚告知书
			{
				//查询调查报告
				m.put("instrumentType", "34");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					Dcbg dcbg = (Dcbg) dcbgService.findDcbg(m).get(0);
					json.put("input3", dcbg.getCaseCondition());
				}
			}
			else if(wstype.equals("21"))//案件处理呈批表
			{
				//查询行政处罚告知书
				m.put("instrumentType", "15");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					json.put("input2", penaltyNotice.getCaseCondition()+"经查，你（单位）"+penaltyNotice.getWfxw()+"。以上事实违反了"+penaltyNotice.getProName()+"的规定，依据"+penaltyNotice.getLawName()+"的规定，实施行政处罚");
				}
				m.put("instrumentType", "16");
				List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list2.size() != 0)
				{
					m.put("relatedId", list2.get(0).getId());
					PartyStateNote partyStateNote = (PartyStateNote) partyStateNoteService.findPartyStateNote(m).get(0);
					json.put("input3", partyStateNote.getStateRecord());
				}
				else
				{
					json.put("input3", "放弃陈述、申辩和听证");
				}
				
			}
			else if(wstype.equals("23"))//行政（当场）处罚决定书（单位）
			{
				m.put("instrumentType", "15");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					json.put("input1", penaltyNotice.getAdminPenality());
				}
			}
			else if(wstype.equals("24"))//行政（当场）处罚决定书（个人）
			{
				m.put("instrumentType", "15");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					json.put("input1", penaltyNotice.getAdminPenality());
				}
			}
			else if(wstype.equals("25"))//行政处罚决定书（单位）
			{
				m.put("instrumentType", "15");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					json.put("input1", penaltyNotice.getAdminPenality());
					json.put("input4", penaltyNotice.getCaseCondition()+"经查，你（单位）"+penaltyNotice.getWfxw()+"。以上事实有询问笔录、现场照片等证据为证。");
				}
			}
			else if(wstype.equals("26"))//行政处罚决定书（个人）
			{
				m.put("instrumentType", "15");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				if(list.size() != 0)
				{
					m.put("relatedId", list.get(0).getId());
					PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					json.put("input1", penaltyNotice.getAdminPenality());
					json.put("input4", penaltyNotice.getCaseCondition()+"经查，你（单位）"+penaltyNotice.getWfxw()+"。以上事实有询问笔录、现场照片等证据为证。");
				}
			}
			else if(wstype.equals("31"))//结案审批表
			{
				json.put("input1", "全部执行，建议结案");
			}
			else if(wstype.equals("34"))//调查报告
			{
				String dcbgqk = "";
				int num = 1;
				m.put("instrumentType", "2");//询问笔录
				List<InstrumentsInfo> xwbllist  = instrumentsInfoService.findInstrumentsInfoss(m);
				if(xwbllist.size() != 0)
				{
					dcbgqk += num + "、对";
					for(InstrumentsInfo xwbl:xwbllist)
					{
						m.put("relatedId", xwbl.getId());
						InquiryRecord inq = (InquiryRecord) inquiryRecordService.findInquiryRecord(m).get(0);
						dcbgqk += inq.getCompanyName() + inq.getPosition()+inq.getAskedPerson()+"、";
					}
					dcbgqk = dcbgqk.substring(0,dcbgqk.length()-1);
					dcbgqk += "进行了询问，制作了询问笔录"+xwbllist.size()+"份;\n";
					num ++;
				}
				m.put("instrumentType", "3");//勘验笔录
				List<InstrumentsInfo> kybllist  = instrumentsInfoService.findInstrumentsInfoss(m);
				if(kybllist.size() != 0)
				{
					dcbgqk += num + "、对";
					for(InstrumentsInfo kybl:kybllist)
					{
						m.put("relatedId", kybl.getId());
						InquestRecord inq = (InquestRecord) inquestRecordService.findInquestRecord(m).get(0);
						dcbgqk += inq.getInquestAddress()+"、";
					}
					dcbgqk = dcbgqk.substring(0,dcbgqk.length()-1);
					dcbgqk += "进行了勘验，制作了勘验笔录"+kybllist.size()+"份;\n";
					num++;
				}
				m.put("instrumentType", "4");//抽样取证凭证
				List<InstrumentsInfo> cyqzlist  = instrumentsInfoService.findInstrumentsInfoss(m);
				if(cyqzlist.size() != 0)
				{
					dcbgqk += num + "、对";
					for(InstrumentsInfo cyqz:cyqzlist)
					{
						m.put("relatedId", cyqz.getId());
						SamplingEvidence inq = (SamplingEvidence) samplingEvidenceService.findSamplingEvidence(m).get(0);
						dcbgqk += inq.getForensicAddress()+"、";
					}
					dcbgqk = dcbgqk.substring(0,dcbgqk.length()-1);
					dcbgqk += "进行了抽样;";
					num++;
				}
				json.put("input2", dcbgqk);
				CaseInfo caseInfo = caseInfoService.getById(caseId);
				json.put("input3", caseInfo.getCaseCondition());
			}
			String needcheck = SysPropertiesUtil.getProperty("needCheck");
			String nocheck = SysPropertiesUtil.getProperty("noCheck");
			if(needcheck.contains("," + wstype+ ","))
			{
				json.put("ifcheck", "1");//必须审核
			}
			else if(nocheck.contains("," + wstype+ ","))
			{
				json.put("ifcheck", "0");//不须审核
			}
			else
			{
				json.put("ifcheck", "2");;//询问是否审核
			}
			
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
