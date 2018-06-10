package com.jshx.wsgl.web;

import java.io.FileInputStream;
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
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Struts2Util;
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
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.module.infomation.service.ContentInformationsService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
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
import com.jshx.wsgl.entity.CheckPro;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.entity.LockUser;
import com.jshx.wsgl.entity.WsInfo;
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
import com.jshx.zgfcyjs.entity.ReviewSubmission;
import com.jshx.zgfcyjs.service.ReviewSubmissionService;
import com.jshx.zlqxzgzls.entity.OrderDeadlineBook;
import com.jshx.zlqxzgzls.service.OrderDeadlineBookService;

public class InstrumentsInfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private InstrumentsInfo instrumentsInfo = new InstrumentsInfo();

	/**
	 * 业务类
	 */
	@Autowired
	private InstrumentsInfoService instrumentsInfoService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private CaseInfoService caseInfoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ContentInformationsService contentInformationsService;
	
	private CaseInfo caseInfo = new CaseInfo();

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<PhotoPic> picList =  new ArrayList<PhotoPic>();
	
	//询问通知书
	@Autowired
	private InquiryNoticeService inquiryNoticeService;
	private InquiryNotice inquiryNotice = new InquiryNotice();
	
	//询问笔录
	@Autowired
	private InquiryRecordService inquiryRecordService;
	private InquiryRecord inquiryRecord = new InquiryRecord();
	private InqRecRecord inqRecRecord = new InqRecRecord();
	private List<InqRecRecord> recordList = new ArrayList<InqRecRecord>();
	
	//勘验笔录
	@Autowired
	private InquestRecordService inquestRecordService;
	private InquestRecord inquestRecord = new InquestRecord();
	
	//抽样取证凭证
	@Autowired
	private SamplingEvidenceService samplingEvidenceService;
	private SamplingEvidence samplingEvidence = new SamplingEvidence();
	
	//抽样取样证据关联表
	@Autowired
	private SamplingAssociateService samplingAssociateService;
	private SamplingAssociate samplingAssociate = new SamplingAssociate();
	private List<SamplingAssociate> cyqyglbList = new ArrayList<SamplingAssociate>();
	
	//先行登记保存证据审批表
	@Autowired
	private PreserveEvidenceService preserveEvidenceService;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private InventoryAssociateService inventoryAssociateService;
	private PreserveEvidence preserveEvidence = new PreserveEvidence();
	private InventoryAssociate inventoryAssociate = new InventoryAssociate();
	private List<CheckRecord> checkRecords = new ArrayList<CheckRecord>();
	private List<InventoryAssociate> zzdjbczjglbList = new ArrayList<InventoryAssociate>();
	SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd");
	
	//先行登记保存证据通知书
	@Autowired
	private NoticeEvidenceService noticeEvidenceService;
	private NoticeEvidence noticeEvidence = new NoticeEvidence();
	
	
	//先行登记保存证据处理审批表
	@Autowired
	private InventoryCheckService inventoryCheckService;
	private InventoryCheck inventoryCheck = new InventoryCheck();
	
	//先行登记保存证据处理决定书 
	@Autowired
	private InventoryDecisionService inventoryDecisionService;
	private InventoryDecision inventoryDecision = new InventoryDecision();

	//现场检查记录
	@Autowired
	private SiteCheckRecordService siteCheckRecordService;
	private SiteCheckRecord siteCheckRecord = new SiteCheckRecord();
	
	//现场处理措施决定书
	@Autowired
	private LiveActionDecisionService liveActionDecisionService;
	private LiveActionDecision liveActionDecision = new LiveActionDecision();
	
	
	//责令限期整改指令书
	@Autowired
	private OrderDeadlineBookService orderDeadlineBookService;
	private OrderDeadlineBook orderDeadlineBook = new OrderDeadlineBook();
	
	//整改复查意见书
	@Autowired
	private ReviewSubmissionService reviewSubmissionService;
	private ReviewSubmission reviewSubmission = new ReviewSubmission();

	
	//强制措施决定书
	@Autowired
	private EnforenceDecisionService enforenceDecisionService;
	private EnforenceDecision enforenceDecision = new EnforenceDecision();
	
	//鉴定委托书
	@Autowired
	private IdentifyAttorneyService identifyAttorneyService;
	@Autowired
	private IdentifyItemAssociateService identifyItemAssociateService;
	private IdentifyAttorney identifyAttorney = new IdentifyAttorney();
	private IdentifyItemAssociate identifyItemAssociate = new IdentifyItemAssociate();
	private List<IdentifyItemAssociate> jdwpList = new ArrayList<IdentifyItemAssociate>();
	
	
	//行政处罚告知书
	@Autowired
	private PenaltyNoticeService penaltyNoticeService;
	private PenaltyNotice penaltyNotice = new PenaltyNotice();
	
	//当事人陈述申辩笔录
	@Autowired
	private PartyStateNoteService partyStateNoteService;
	private PartyStateNote partyStateNote = new PartyStateNote();

	
	//听证告知书
	@Autowired
	private HearingTellService hearingTellService;
	private HearingTell hearingTell = new HearingTell();
	
	//听证会通知书
	@Autowired
	private HearingNoticeService hearingNoticeService;
	private HearingNotice hearingNotice = new HearingNotice();

	
	
	//听证笔录
	@Autowired
	private HearingNoteService hearingNoteService;
	private HearingNote hearingNote = new HearingNote();

	
	
	//听证会报告书
	@Autowired
	private HearingReportService hearingReportService;
	private HearingReport hearingReport = new HearingReport();


	
	//案件处理呈批表
	@Autowired
	private CaseProcessApprovalService caseProcessApprovalService;
	private CaseProcessApproval caseProcessApproval = new CaseProcessApproval();

	
	
	//行政处罚集体讨论记录
	@Autowired
	private PenBraRecService penBraRecService;
	private PenBraRec penBraRec = new PenBraRec();
	
	
	//行政（当场）处罚决定书（单位）
	@Autowired
	private SpoPenDecComService spoPenDecComService;
	private SpoPenDecCom spoPenDecCom = new SpoPenDecCom();
	
	
	//行政（当场）处罚决定书（个人）
	@Autowired
	private SpoPenDecPerService spoPenDecPerService;
	private SpoPenDecPer spoPenDecPer = new SpoPenDecPer();
	
	
	//行政处罚决定书（单位）
	@Autowired
	private PenDecComService penDecComService;
	private PenDecCom penDecCom = new PenDecCom();
	
	//行政处罚决定书（个人）
	@Autowired
	private PenDecPerService penDecPerService;
	private PenDecPer penDecPer = new PenDecPer();
	
	
	//罚款催缴通知书

	
	
	//延期（分期）缴纳罚款审批表
	@Autowired
	private PosFinAppService posFinAppService;
	private PosFinApp posFinApp = new PosFinApp();

	
	
	//延期（分期）缴纳罚款批准书
	@Autowired
	private PosFinRatService posFinRatService;
	private PosFinRat posFinRat = new PosFinRat();
	
	
	//强制执行申请书
	@Autowired
	private EnfAppService enfAppService;
	private EnfApp enfApp = new EnfApp();

	
	//结案审批表
	@Autowired
	private CloseApprovalService closeApprovalService;
	private CloseApproval closeApproval = new CloseApproval();
	
	//案件移送审批表
	@Autowired
	private CaseReferService caseReferService;
	private CaseRefer caseRefer = new CaseRefer();
	
	//案件移送书
	@Autowired
	private CaseTransferService caseTransferService;
	private CaseTransfer caseTransfer = new CaseTransfer();
	
	//调查报告
	@Autowired
	private DcbgService dcbgService;
	private Dcbg dcbg = new Dcbg();
	
	//特殊事项申请表
	@Autowired
	private SpecialItemService specialItemService;
	private SpecialItem specialItem = new SpecialItem();
	
	 private String userIds;
		
	private String userNames;
	
	private List<User> userList = new ArrayList<User>();
	
	private String lockTime;
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	//登录名
	private String loginUserId;
	
	//类型
	private String type;
	
	//案件文书列表
	private List<WsInfo> wsList = new ArrayList<WsInfo>();
	
	//其他文书列表
	private List<WsInfo> qtList = new ArrayList<WsInfo>();
	
	private String ifcheck;
	
	private List<CheckPro> proList = new ArrayList<CheckPro>();
	
	private List<String> lastFile = new ArrayList<String>();
	
	private String fileName;
	
	
	private int pageNo;
	private int pageSize;
	private String searchLike;
	
	
	/**
	 * 查询登录人角色
	 * @author luting
	 * 2015-10-22
	 * @return
	 */
	public String init()
	{
		loginUserId = this.getLoginUser().getId();
		List<UserRight> list = (List<UserRight>) this.getLoginUser().getUserRoles();
		flag = "0";
		for(UserRight ur:list)
		{
			flag += ur.getRole().getRoleCode()+ ",";
		}
		if(this.getLoginUser().getDeptCode().startsWith("002001"))
		{
			flag += "B00";
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
		    
		if(null != instrumentsInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != instrumentsInfo.getInstrumentType()) && (0 < instrumentsInfo.getInstrumentType().trim().length())){
				paraMap.put("instrumentType", instrumentsInfo.getInstrumentType().trim());
			}
			
			if ((null != instrumentsInfo.getInstrumentName()) && (0 < instrumentsInfo.getInstrumentName().trim().length())){
				paraMap.put("instrumentName",  "%" + instrumentsInfo.getInstrumentName().trim()+ "%");
			}

			if ((null != instrumentsInfo.getCompanyName()) && (0 < instrumentsInfo.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + instrumentsInfo.getCompanyName().trim() + "%");
			}
			if ((null != instrumentsInfo.getCaseId()) && (0 < instrumentsInfo.getCaseId().trim().length())){
				paraMap.put("caseId", instrumentsInfo.getCaseId().trim() );
			}
			if ((null != instrumentsInfo.getIfCheck()) && (0 < instrumentsInfo.getIfCheck().trim().length())){
				List<String> ll = new ArrayList<String>();
				if(instrumentsInfo.getIfCheck().equals("1"))
				{
					List<UserRight> list = (List<UserRight>) this.getLoginUser().getUserRoles();
					flag = "0";
					for(UserRight ur:list)
					{
						//登录人为安监局领导
						if(ur.getRole().getRoleCode().equals("A02")) 
						{
							flag = "1";
							break;
						}
						//登录人为监察大队队长
						if(ur.getRole().getRoleCode().equals("A09")) 
						{
							flag = "2";
							break;
						}
						//登录人为法务
						if(ur.getRole().getRoleCode().equals("A30")) 
						{
							flag = "4";
							break;
						}
					}
					if(this.getLoginUser().getDeptCode().startsWith("002001"))
					{
						if(!"1".equals(flag)&&!"2".equals(flag)&&!"4".equals(flag))
						{
							flag = "3";
						}
					}
					if(flag.equals("1"))//安监领导待审核状态 2
					{
						paraMap.put("ifcheck", "2");
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else if(flag.equals("2")) //大队队长待审核状态 1
					{
						paraMap.put("ifcheck", "1");
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else if(flag.equals("3")) //安监局其他人员
					{
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else if(flag.equals("4")) //法务
					{
						paraMap.put("ifcheck", "8");
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else
					{
						ll.add("1");
						ll.add("2");
						ll.add("3");
						ll.add("8");
					}
				}
				else if(instrumentsInfo.getIfCheck().equals("2"))
				{
					ll.add("0");
					ll.add("5");
				}
				else if(instrumentsInfo.getIfCheck().equals("4"))
				{
					ll.add("4");
				}
				paraMap.put("ifCheck", ll);
			}
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|caseName|instrumentType|ifPrint|ifCheck|createUserID|needCheckUser|instrumentName|companyName|";
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
		pagination = instrumentsInfoService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		try {
			if((null != instrumentsInfo)&&(null != instrumentsInfo.getId()))
			{
				instrumentsInfo = instrumentsInfoService.getById(instrumentsInfo.getId());
				if(instrumentsInfo.getLinkId() == null || "".equals(instrumentsInfo.getLinkId())){
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					instrumentsInfo.setLinkId(linkId);
				}else{
					Map mmm = new HashMap();
					mmm.put("linkId",instrumentsInfo.getLinkId());
					mmm.put("mkType", "wsgl");
					mmm.put("picType","wsglfj");
					picList = photoPicService.findPicPath(mmm);//获取执法文书材料
				}
				String type=instrumentsInfo.getInstrumentType();
				Map m = new HashMap();
				m.put("relatedId", instrumentsInfo.getId());
				if(type.equals("1"))//询问通知书
				{
					inquiryNotice = (InquiryNotice) inquiryNoticeService.findInquiryNotice(m).get(0);
				}
				else if(type.equals("2"))//询问笔录
				{
					inquiryRecord = (InquiryRecord) inquiryRecordService.findInquiryRecord(m).get(0);
					m.put("relatedId", instrumentsInfo.getId());
					recordList = inquiryRecordService.findInqRecRecord(m);
				}
				else if(type.equals("3"))//勘验笔录
				{
					inquestRecord = (InquestRecord) inquestRecordService.findInquestRecord(m).get(0);
				}
				else if(type.equals("4"))//抽样取证凭证
				{
					samplingEvidence = (SamplingEvidence) samplingEvidenceService.findSamplingEvidence(m).get(0);
					m.put("forensicId", instrumentsInfo.getId());
					cyqyglbList = samplingAssociateService.findSamplingAssociate(m);
				}
				else if(type.equals("5"))//先行登记保存证据审批表
				{
					preserveEvidence = (PreserveEvidence) preserveEvidenceService.findPreserveEvidence(m).get(0);
					m.put("relatedId", instrumentsInfo.getId());
					zzdjbczjglbList = inventoryAssociateService.findInventoryAssociate(m);
				}
				else if(type.equals("6"))//先行登记保存证据通知书
				{
					noticeEvidence = (NoticeEvidence) noticeEvidenceService.findNoticeEvidence(m).get(0);
				}
				else if(type.equals("7"))//先行登记保存证据处理审批表
				{
					inventoryCheck = (InventoryCheck) inventoryCheckService.findInventoryCheck(m).get(0);
				}
				else if(type.equals("8"))//先行登记保存证据处理决定书 
				{
					inventoryDecision = (InventoryDecision) inventoryDecisionService.findInventoryDecision(m).get(0);
				}
				else if(type.equals("9"))//现场检查记录
				{
					siteCheckRecord = (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(m).get(0);
				}
				else if(type.equals("10"))//现场处理措施决定书
				{
					liveActionDecision = (LiveActionDecision) liveActionDecisionService.findLiveActionDecision(m).get(0);
				}
				else if(type.equals("11"))//责令限期整改指令书
				{
					orderDeadlineBook = (OrderDeadlineBook) orderDeadlineBookService.findOrderDeadlineBook(m).get(0);
				}
				else if(type.equals("12"))//整改复查意见书
				{
					reviewSubmission = (ReviewSubmission) reviewSubmissionService.findReviewSubmission(m).get(0);
				}
				else if(type.equals("13"))//强制措施决定书
				{
					enforenceDecision = (EnforenceDecision) enforenceDecisionService.findEnforenceDecision(m).get(0);
				}
				else if(type.equals("14"))//鉴定委托书
				{ 
					identifyAttorney = (IdentifyAttorney) identifyAttorneyService.findIdentifyAttorney(m).get(0);
					m.put("attorenyId", instrumentsInfo.getId());
					jdwpList = identifyItemAssociateService.findIdentifyItemAssociate(m);
				}
				else if(type.equals("15"))//行政处罚告知书
				{
					penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
				}
				else if(type.equals("16"))//当事人陈述申辩笔录
				{
					partyStateNote = (PartyStateNote) partyStateNoteService.findPartyStateNote(m).get(0);
				}
				else if(type.equals("17"))//听证告知书
				{
					hearingTell = (HearingTell) hearingTellService.findHearingTell(m).get(0);
					//查询行政处罚告知书
					m.put("caseId", instrumentsInfo.getCaseId());
					m.put("instrumentType", "15");
					List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
					if(list.size() != 0)
					{
						m.put("relatedId", list.get(0).getId());
						penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					}
				}
				else if(type.equals("18"))//听证会通知书
				{
					hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
				}
				else if(type.equals("19"))//听证笔录
				{
					hearingNote = (HearingNote) hearingNoteService.findHearingNote(m).get(0);
					//查询听证会通知书
					m.put("caseId", instrumentsInfo.getCaseId());
					m.put("instrumentType", "18");
					List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
					if(list.size() != 0)
					{
						m.put("relatedId", list.get(0).getId());
						hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
					}
				}
				else if(type.equals("20"))//听证会报告书
				{
					hearingReport = (HearingReport) hearingReportService.findHearingReport(m).get(0);
					//查询听证会通知书
					m.put("caseId", instrumentsInfo.getCaseId());
					m.put("instrumentType", "18");
					List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
					if(list.size() != 0)
					{
						m.put("relatedId", list.get(0).getId());
						hearingNotice = (HearingNotice) hearingNoticeService.findHearingNotice(m).get(0);
					}
				}
				else if(type.equals("21"))//案件处理呈批表
				{
					caseProcessApproval = (CaseProcessApproval) caseProcessApprovalService.findCaseProcessApproval(m).get(0);
				}
				else if(type.equals("22"))//行政处罚集体讨论记录
				{
					penBraRec = (PenBraRec) penBraRecService.findPenBraRec(m).get(0);
				}
				else if(type.equals("23"))//行政（当场）处罚决定书（单位）
				{
					spoPenDecCom = (SpoPenDecCom) spoPenDecComService.findSpoPenDecCom(m).get(0);
				}
				else if(type.equals("24"))//行政（当场）处罚决定书（个人）
				{
					spoPenDecPer = (SpoPenDecPer) spoPenDecPerService.findSpoPenDecPer(m).get(0);
				}
				else if(type.equals("25"))//行政处罚决定书（单位）
				{
					penDecCom = (PenDecCom) penDecComService.findPenDecCom(m).get(0);
					//查询行政处罚告知书
					m.put("caseId", instrumentsInfo.getCaseId());
					m.put("instrumentType", "15");
					List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
					if(list1.size() != 0)
					{
						m.put("relatedId", list1.get(0).getId());
						penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					}
				}
				else if(type.equals("26"))//行政处罚决定书（个人）
				{
					penDecPer = (PenDecPer) penDecPerService.findPenDecPer(m).get(0);
					//查询行政处罚告知书
					m.put("caseId", instrumentsInfo.getCaseId());
					m.put("instrumentType", "15");
					List<InstrumentsInfo> list1 = instrumentsInfoService.findInstrumentsInfoss(m);
					if(list1.size() != 0)
					{
						m.put("relatedId", list1.get(0).getId());
						penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
					}
				}
				else if(type.equals("27"))//罚款催缴通知书
				{
					
				}
				else if(type.equals("28"))//延期（分期）缴纳罚款审批表
				{
					posFinApp = (PosFinApp) posFinAppService.findPosFinApp(m).get(0);
				}
				else if(type.equals("29"))//延期（分期）缴纳罚款批准书
				{
					posFinRat = (PosFinRat) posFinRatService.findPosFinRat(m).get(0);
				}
				else if(type.equals("30"))//强制执行申请书
				{
					enfApp = (EnfApp) enfAppService.findEnfApp(m).get(0);
				}
				else if(type.equals("31"))//结案审批表
				{
					closeApproval = (CloseApproval) closeApprovalService.findCloseApproval(m).get(0);
				}
				else if(type.equals("32"))//案件移送审批表
				{
					caseRefer = (CaseRefer) caseReferService.findCaseRefer(m).get(0);
				}
				else if(type.equals("33"))//案件移送书
				{
					caseTransfer = (CaseTransfer) caseTransferService.findCaseTransfer(m).get(0);
				}
				else if(type.equals("34"))//调查报告
				{
					dcbg = (Dcbg) dcbgService.findDcbg(m).get(0);
				}
				else if(type.equals("35"))//调查报告
				{
					specialItem = (SpecialItem) specialItemService.findSpecialItem(m).get(0);
				}
				else if(type.equals("100"))//立案调查
				{
					caseInfo = caseInfoService.getById(instrumentsInfo.getCaseId());
				}
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("infoId", instrumentsInfo.getId());
				checkRecords=checkRecordService.findCheckRecord(paraMap);
				
				int num = 2;
				if(instrumentsInfo.getNeedCheck() != null && (instrumentsInfo.getNeedCheck().equals("1") || instrumentsInfo.getNeedCheck().equals("2"))) //法务审核
				{
					CheckPro pro = new CheckPro();
					pro.setNum(num+"");
					pro.setName("法务审核");
					if(instrumentsInfo.getNeedCheck().equals("2"))
					{
						pro.setSfwc("1");
					}
					proList.add(pro);
					num++;
				}
				if(instrumentsInfo.getDzqmCheck() != null && (instrumentsInfo.getDzqmCheck().equals("1") || instrumentsInfo.getDzqmCheck().equals("2"))) //电子签名确认
				{
					CheckPro pro = new CheckPro();
					pro.setNum(num+"");
					if(instrumentsInfo.getInstrumentType().equals("19") || instrumentsInfo.getInstrumentType().equals("20"))
					{
						pro.setName("听证主持人审核");
					}
					else
					{
						pro.setName("电子签名确认");
					}
					if(instrumentsInfo.getDzqmCheck().equals("2"))
					{
						pro.setSfwc("1");
					}
					proList.add(pro);
					num++;
				}
				if(instrumentsInfo.getDzCheck() != null && (instrumentsInfo.getDzCheck().equals("1") || instrumentsInfo.getDzCheck().equals("2"))) //监察大队大队长审核
				{
					CheckPro pro = new CheckPro();
					pro.setNum(num+"");
					pro.setName("监察大队大队长审核");
					if(instrumentsInfo.getDzCheck().equals("2"))
					{
						pro.setSfwc("1");
					}
					proList.add(pro);
					num++;
				}
				if(instrumentsInfo.getJzCheck() != null && (instrumentsInfo.getJzCheck().equals("1") || instrumentsInfo.getJzCheck().equals("2"))) //局长审核
				{
					CheckPro pro = new CheckPro();
					pro.setNum(num+"");
					pro.setName("局长审核");
					if(instrumentsInfo.getJzCheck().equals("2"))
					{
						pro.setSfwc("1");
					}
					proList.add(pro);
					num++;
				}
				if(instrumentsInfo.getLastFile() != null && !"".equals(instrumentsInfo.getLastFile()))
				{
					String[]  ss = instrumentsInfo.getLastFile().split(",");
					for(String s:ss)
					{
						if(s != null && !"".equals(s))
						lastFile.add(s);
					}
				}
			}
			else
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				instrumentsInfo.setLinkId(linkId);
				
				if(instrumentsInfo.getCaseId() != null && !"".equals(instrumentsInfo.getCaseId()))
				{
					//查询行政处罚告知书
					Map m = new HashMap();
					m.put("caseId", instrumentsInfo.getCaseId());
					String wstype= instrumentsInfo.getInstrumentType();
					if(wstype.equals("2"))//询问笔录
					{
						inquiryRecord.setRecordPerson(this.getLoginUser().getId());
						inquiryRecord.setRecordPersonName(this.getLoginUser().getDisplayName());
						inquiryRecord.setRecordPersonZh(this.getLoginUser().getZfzh());
					}
					else if(wstype.equals("3"))//勘验笔录
					{
						inquestRecord.setRecordPerson(this.getLoginUser().getId());
						inquestRecord.setRecordPersonName(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("4"))//抽样取证凭证
					{
						samplingEvidence.setLawOfficerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("5"))//先行登记保存证据审批表
					{
						preserveEvidence.setUndertakerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("6"))//先行登记保存证据通知书
					{
						noticeEvidence.setUndertakerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("7"))//先行登记保存证据处理审批表
					{
						inventoryCheck.setUndertakerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("9"))//现场检查记录
					{
						siteCheckRecord.setCheckPersonName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("10"))//现场处理措施决定书
					{
						liveActionDecision.setLawOfficerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("11"))//责令限期整改指令书
					{
						orderDeadlineBook.setLawOfficerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("12"))//整改复查意见书
					{
						reviewSubmission.setLawOfficerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("15"))//行政处罚告知书
					{
						//查询调查报告
						m.put("instrumentType", "34");
						List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
						if(list.size() != 0)
						{
							m.put("relatedId", list.get(0).getId());
							Dcbg dcbg = (Dcbg) dcbgService.findDcbg(m).get(0);
							penaltyNotice.setCaseCondition(dcbg.getCaseCondition());
						}
					}
					else if(wstype.equals("16"))//当事人陈述申辩笔录
					{
						partyStateNote.setRecorder(this.getLoginUser().getId());
						partyStateNote.setRecorderName(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("18"))//听证会通知书
					{
						hearingNotice.setClerk(this.getLoginUser().getId());
						hearingNotice.setClerkName(this.getLoginUser().getDisplayName());
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
							caseProcessApproval.setWfss(penaltyNotice.getCaseCondition()+"经查，你（单位）"+penaltyNotice.getWfxw()+"。以上事实违反了"+penaltyNotice.getProName()+"的规定，依据"+penaltyNotice.getLawName()+"的规定，实施行政处罚");
						}
						m.put("instrumentType", "16");
						List<InstrumentsInfo> list2 = instrumentsInfoService.findInstrumentsInfoss(m);
						if(list2.size() != 0)
						{
							m.put("relatedId", list2.get(0).getId());
							PartyStateNote partyStateNote = (PartyStateNote) partyStateNoteService.findPartyStateNote(m).get(0);
							caseProcessApproval.setDsrsbyj(NullToString(partyStateNote.getStateRecord()));
						}
						else
						{
							caseProcessApproval.setDsrsbyj("放弃陈述、申辩和听证");
						}
						caseProcessApproval.setUndertakerName1(this.getLoginUser().getDisplayName());
						
					}
					else if(wstype.equals("22"))//行政处罚集体讨论记录
					{
						penBraRec.setRecordPerson(this.getLoginUser().getId());
						penBraRec.setRecordPersonName(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("23"))//行政（当场）处罚决定书（单位）
					{
						m.put("instrumentType", "15");
						List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
						if(list.size() != 0)
						{
							m.put("relatedId", list.get(0).getId());
							PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
							spoPenDecCom.setAdminPenalties(penaltyNotice.getAdminPenality());
						}
						spoPenDecCom.setLawOfficerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("24"))//行政（当场）处罚决定书（个人）
					{
						m.put("instrumentType", "15");
						List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
						if(list.size() != 0)
						{
							m.put("relatedId", list.get(0).getId());
							PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
							spoPenDecPer.setAdminPenalties(penaltyNotice.getAdminPenality());
						}
						spoPenDecPer.setLawOfficerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("25"))//行政处罚决定书（单位）
					{
						m.put("instrumentType", "15");
						List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
						if(list.size() != 0)
						{
							m.put("relatedId", list.get(0).getId());
							PenaltyNotice penaltyNotice = (PenaltyNotice) penaltyNoticeService.findPenaltyNotice(m).get(0);
							penDecCom.setAdminPenalties(penaltyNotice.getAdminPenality());
							penDecCom.setWfss(penaltyNotice.getCaseCondition()+"经查，你（单位）"+penaltyNotice.getWfxw()+"。以上事实有询问笔录、现场照片等证据为证。");
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
							penDecPer.setAdminPenalties(penaltyNotice.getAdminPenality());
							penDecPer.setWfss(penaltyNotice.getCaseCondition()+"经查，你（单位）"+penaltyNotice.getWfxw()+"。以上事实有询问笔录、现场照片等证据为证。");
						}
					}
					else if(wstype.equals("28"))//延期（分期）缴纳罚款审批表
					{
						posFinApp.setUndertakerName1(this.getLoginUser().getDisplayName());
					}
					else if(wstype.equals("31"))//结案审批表
					{
						closeApproval.setUndertakerName1(this.getLoginUser().getDisplayName());
						closeApproval.setExecuteCondition("全部执行，建议结案");
					}
					else if(wstype.equals("32"))//案件移送审批表
					{
						caseRefer.setUndertakerName1(this.getLoginUser().getDisplayName());
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
						
						dcbg.setDcbgqk(dcbgqk);
						dcbg.setUndertakerName1(this.getLoginUser().getDisplayName());
						caseInfo = caseInfoService.getById(instrumentsInfo.getCaseId());
						dcbg.setCaseCondition(caseInfo.getCaseCondition());
					}
				}
				else
				{
					siteCheckRecord.setCheckPersonName1(this.getLoginUser().getDisplayName());
					orderDeadlineBook.setLawOfficerName1(this.getLoginUser().getDisplayName());
					reviewSubmission.setLawOfficerName1(this.getLoginUser().getDisplayName());
				}
			}
			String needcheck = SysPropertiesUtil.getProperty("needCheck");
			String nocheck = SysPropertiesUtil.getProperty("noCheck");
			if(needcheck.contains("," + instrumentsInfo.getInstrumentType()+ ","))
			{
				ifcheck = "1";//必须审核
			}
			else if(nocheck.contains("," + instrumentsInfo.getInstrumentType()+ ","))
			{
				ifcheck = "0";//不须审核
			}
			else
			{
				ifcheck = "2";//询问是否审核
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if(instrumentsInfo.getCaseId() != null && !"".equals(instrumentsInfo.getCaseId()))
		{
			return VIEW;
		}
		else
		{
			return VIEW+"s";
		}
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		loginUserId = this.getLoginUser().getId();
		if(instrumentsInfo.getCaseId() != null && !"".equals(instrumentsInfo.getCaseId()))
		{
			CaseInfo ca = caseInfoService.getById(instrumentsInfo.getCaseId());
			instrumentsInfo.setCaseName(ca.getCaseName());
			lockTime = sdf.format(instrumentsInfo.getTime());
			Map map = new HashMap();
		   	map.put("caseId",ca.getId());
		   	map.put("wstype", instrumentsInfo.getInstrumentType());
		   	map.put("lockTime", lockTime);
		   	map.put("doUserId", this.getLoginUser().getId());
		    instrumentsInfoService.deleteLockUser(map);
		    
		    LockUser lockUser = new LockUser();
		    lockUser.setCaseId(ca.getId());
		    lockUser.setUserId(this.getLoginUser().getId());
		    lockUser.setWstype(instrumentsInfo.getInstrumentType());
		    lockUser.setLockTime(lockTime);
		    lockUser.setDoUserId(this.getLoginUser().getId());
		    instrumentsInfoService.saveLockUser(lockUser);
		    return EDIT;
		}
		else
		{
			return EDIT+"s";
		}
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
		try {
			CaseInfo caseInfo = caseInfoService.getById(instrumentsInfo.getCaseId());
			String wstype = instrumentsInfo.getInstrumentType();
			InstrumentsInfoUtil instrumentsInfoUtil = new InstrumentsInfoUtil();
			String root = this.getRequest().getRealPath("/");
			root = root.replaceAll("\\\\", "/");
			instrumentsInfo.setCreateUserID(this.getLoginUser().getId());
			instrumentsInfo.setDeptId(this.getLoginUserDepartmentId());
			instrumentsInfo.setCreateTime(new Date());
			
			if ("add".equalsIgnoreCase(flag)){
				if(wstype.equals("1")) //询问通知书 luting 2015-10-25
				{
					String askPersons[] = inquiryNotice.getAskPerson().replaceAll(" ", "").split(",");
					for(String s:askPersons)
					{
						InstrumentsInfo ins = new InstrumentsInfo();
						ins.setCaseId(instrumentsInfo.getCaseId());
						ins.setCreateTime(new Date());
						ins.setCreateUserID(this.getLoginUser().getId());
						ins.setDeptId(this.getLoginUserDepartment().getId());
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
				else if(wstype.equals("15"))//行政处罚告知书
				{
					instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
					instrumentsInfo = instrumentsInfoUtil.savePenaltyNotice(instrumentsInfo, caseInfo,penaltyNotice, flag,type,root);
					if(instrumentsInfo.getAjbz() != null && !"".equals(instrumentsInfo.getAjbz()) && "add".equalsIgnoreCase(this.flag))
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
						ins.setCreateUserID(this.getLoginUser().getId());
						ins.setDeptId(this.getLoginUserDepartment().getId());
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
				else
				{
					instrumentsInfo = instrumentsInfoUtil.saveInstrumentsInfo(instrumentsInfo, caseInfo, root, wstype);
				}
			}
			else{//修改文书信息
				Map m = new HashMap();
				m.put("codeName", "文书类型");
				m.put("itemValue", instrumentsInfo.getInstrumentType());
				String fileName = codeService.findCodeValueByMap(m).getItemText();
				SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyyMMdd");
				String instrumentsName = fileName + sdf2.format(instrumentsInfo.getTime());
				instrumentsInfo.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
				instrumentsInfo.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
				instrumentsInfo.setInstrumentName(instrumentsName);
				instrumentsInfo.setCompanyName(caseInfo.getCompanyName());
				//如果已生成文书，将之前生成文书置为历史版本
				if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
				{
					String lsname = instrumentsInfo.getLastFile();
					String filename = instrumentsInfo.getFileName();
					SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String destName = sdf1.format(new Date());
					String newfilename = fileName+destName+".docx";
					instrumentsInfo.setFileName(newfilename);
					instrumentsInfo.setLastFile(lsname + "," + filename);
				}
			}
			//根据类型保存文书关联信息 luting 2015-10-25
			if("mod".equalsIgnoreCase(flag)&&wstype.equals("1")) //询问通知书 luting 2015-10-25
			{
				instrumentsInfo = instrumentsInfoUtil.saveInquiryNotice(instrumentsInfo,caseInfo,inquiryNotice,flag,type,root);
			}
			else if(wstype.equals("2"))//询问笔录 luting 2015-10-25
			{
				instrumentsInfo = instrumentsInfoUtil.saveInquiryRecord(instrumentsInfo, caseInfo, inquiryRecord,inqRecRecord, flag,type,root);
			}
			else if(wstype.equals("3"))//勘验笔录 luting 2015-10-25
			{
				instrumentsInfo = instrumentsInfoUtil.saveInquestRecord(instrumentsInfo, caseInfo, inquestRecord, flag,type,root);
			}
			else if(wstype.equals("4"))//抽样取证凭证 luting 2015-10-25
			{
				instrumentsInfo = instrumentsInfoUtil.saveSamplingEvidence(instrumentsInfo, caseInfo, samplingEvidence,samplingAssociate, flag,type,root);
			}
			else if(wstype.equals("5"))//先行登记保存证据审批表 luting 2015-10-25
			{
				instrumentsInfo = instrumentsInfoUtil.savePreserveEvidence(instrumentsInfo, caseInfo, preserveEvidence,inventoryAssociate, flag,type,root);
			}
			else if(wstype.equals("6"))//先行登记保存证据通知书
			{
				instrumentsInfo = instrumentsInfoUtil.saveNoticeEvidence(instrumentsInfo, caseInfo,noticeEvidence, flag,type,root);
			}
			else if(wstype.equals("7"))//先行登记保存证据处理审批表
			{
				instrumentsInfo = instrumentsInfoUtil.saveInventoryCheck(instrumentsInfo, caseInfo, inventoryCheck, flag,type,root);
			}
			else if(wstype.equals("8"))//先行登记保存证据处理决定书 
			{
				instrumentsInfo = instrumentsInfoUtil.saveInventoryDecision(instrumentsInfo, caseInfo, inventoryDecision, flag,type,root);
			}
			else if(wstype.equals("9"))//现场检查记录
			{
				instrumentsInfo = instrumentsInfoUtil.saveSiteCheckRecord(instrumentsInfo, caseInfo,siteCheckRecord, flag,type,root);
			}
			else if(wstype.equals("10"))//现场处理措施决定书
			{
				instrumentsInfo = instrumentsInfoUtil.saveLiveActionDecision(instrumentsInfo, caseInfo,liveActionDecision, flag,type,root);
			}
			else if(wstype.equals("11"))//责令限期整改指令书
			{
				instrumentsInfo = instrumentsInfoUtil.saveOrderDeadlineBook(instrumentsInfo, caseInfo,orderDeadlineBook, flag,type,root);
			}
			else if(wstype.equals("12"))//整改复查意见书
			{
				instrumentsInfo = instrumentsInfoUtil.saveReviewSubmission(instrumentsInfo, caseInfo,reviewSubmission, flag,type,root);
			}
			else if(wstype.equals("13"))//强制措施决定书
			{
				instrumentsInfo = instrumentsInfoUtil.saveEnforenceDecision(instrumentsInfo, caseInfo,enforenceDecision, flag,type,root);
			}
			else if(wstype.equals("14"))//鉴定委托书
			{
				instrumentsInfo = instrumentsInfoUtil.saveIdentifyAttorney(instrumentsInfo, caseInfo,identifyAttorney,identifyItemAssociate, flag,type,root);
			}
			else if("mod".equalsIgnoreCase(flag)&&wstype.equals("15"))//行政处罚告知书
			{
				instrumentsInfo = instrumentsInfoUtil.savePenaltyNotice(instrumentsInfo, caseInfo,penaltyNotice, flag,type,root);
			}
			else if(wstype.equals("16"))//当事人陈述申辩笔录
			{
				instrumentsInfo = instrumentsInfoUtil.savePartyStateNote(instrumentsInfo, caseInfo,partyStateNote, flag,type,root);
			}
			else if("mod".equalsIgnoreCase(flag)&&wstype.equals("17"))//听证告知书
			{
				instrumentsInfo = instrumentsInfoUtil.saveHearingTell(instrumentsInfo,caseInfo,hearingTell,flag,type,root);
			}
			else if(wstype.equals("18"))//听证会通知书
			{
				instrumentsInfo = instrumentsInfoUtil.saveHearingNotice(instrumentsInfo, caseInfo,hearingNotice, flag,type,root);
			}
			else if(wstype.equals("19"))//听证笔录
			{
				instrumentsInfo = instrumentsInfoUtil.saveHearingNote(instrumentsInfo, caseInfo,hearingNote, flag,type,root);
			}
			else if(wstype.equals("20"))//听证会报告书
			{
				instrumentsInfo = instrumentsInfoUtil.saveHearingReport(instrumentsInfo, caseInfo,hearingReport, flag,type,root);
			}
			else if(wstype.equals("21"))//案件处理呈批表
			{
				instrumentsInfo = instrumentsInfoUtil.saveCaseProcessApproval(instrumentsInfo, caseInfo,caseProcessApproval, flag,type,root);
			}
			else if(wstype.equals("22"))//行政处罚集体讨论记录
			{
				instrumentsInfo = instrumentsInfoUtil.savePenBraRec(instrumentsInfo, caseInfo,penBraRec, flag,type,root);
			}
			else if(wstype.equals("23"))//行政（当场）处罚决定书（单位）
			{
				instrumentsInfo = instrumentsInfoUtil.saveSpoPenDecCom(instrumentsInfo, caseInfo,spoPenDecCom, flag,type,root);
			}
			else if(wstype.equals("24"))//行政（当场）处罚决定书（个人）
			{
				instrumentsInfo = instrumentsInfoUtil.saveSpoPenDecPer(instrumentsInfo, caseInfo,spoPenDecPer, flag,type,root);
			}
			else if(wstype.equals("25"))//行政处罚决定书（单位）
			{
				instrumentsInfo = instrumentsInfoUtil.savePenDecCom(instrumentsInfo, caseInfo,penDecCom, flag,type,root);
			}
			else if(wstype.equals("26"))//行政处罚决定书（个人）
			{
				instrumentsInfo = instrumentsInfoUtil.savePenDecPer(instrumentsInfo, caseInfo,penDecPer, flag,type,root);
			}
			else if(wstype.equals("27"))//罚款催缴通知书
			{
				instrumentsInfo = instrumentsInfoUtil.saveFkcjd(instrumentsInfo, caseInfo,type,root);
			}
			else if(wstype.equals("28"))//延期（分期）缴纳罚款审批表
			{
				instrumentsInfo = instrumentsInfoUtil.savePosFinApp(instrumentsInfo, caseInfo,posFinApp, flag,type,root);
			}
			else if(wstype.equals("29"))//延期（分期）缴纳罚款批准书
			{
				instrumentsInfo = instrumentsInfoUtil.savePosFinRat(instrumentsInfo, caseInfo,posFinRat, flag,type,root);
			}
			else if(wstype.equals("30"))//强制执行申请书
			{
				instrumentsInfo = instrumentsInfoUtil.saveEnfApp(instrumentsInfo, caseInfo,enfApp, flag,type,root);
			}
			else if(wstype.equals("31"))//结案审批表
			{
				instrumentsInfo = instrumentsInfoUtil.saveCloseApproval(instrumentsInfo, caseInfo,closeApproval, flag,type,root);
			}
			else if(wstype.equals("32"))//案件移送审批表
			{
				instrumentsInfo = instrumentsInfoUtil.saveCaseRefer(instrumentsInfo, caseInfo,caseRefer, flag,type,root);
			}
			else if(wstype.equals("33"))//案件移送书
			{
				instrumentsInfo = instrumentsInfoUtil.saveCaseTransfer(instrumentsInfo, caseInfo,caseTransfer, flag,type,root);
			}
			else if(wstype.equals("34"))//调查报告
			{
				instrumentsInfo = instrumentsInfoUtil.saveDcbg(instrumentsInfo, caseInfo,dcbg, flag,type,root);
			}
			else if(wstype.equals("35"))//特殊事项审批表
			{
				instrumentsInfo = instrumentsInfoUtil.saveSpecialItem(instrumentsInfo, caseInfo, specialItem, flag);
			}
			if(instrumentsInfo.getAjbz() != null && !"".equals(instrumentsInfo.getAjbz()) && "add".equalsIgnoreCase(this.flag))
			{
				String wsh = instrumentsInfo.getAjbz() + "〔" + instrumentsInfo.getAjh() + "〕"  + instrumentsInfo.getAjhNum() +  "号";
				instrumentsInfo.setWsh(wsh);
			}
			if(instrumentsInfo.getId() != null && !"".equals(instrumentsInfo.getId()))
			{
				instrumentsInfo.setDelFlag(0);
				instrumentsInfoService.update(instrumentsInfo);
			}
			
			//删除锁定人员
			Map map = new HashMap();
			map.put("caseId",instrumentsInfo.getCaseId());
			map.put("wstype", instrumentsInfo.getInstrumentType());
			map.put("lockTime", sdf.format(instrumentsInfo.getTime()));
			map.put("doUserId", this.getLoginUser().getId());
			instrumentsInfoService.deleteLockUser(map);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}
	
	
	
	/**
	 * 保存信息（包括新增和修改）
	 */
	public String saves() throws Exception{
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
		try {
			String wstype = instrumentsInfo.getInstrumentType();
			InstrumentsInfoUtil instrumentsInfoUtil = new InstrumentsInfoUtil();
			String root = this.getRequest().getRealPath("/");
			root = root.replaceAll("\\\\", "/");
			instrumentsInfo.setCreateUserID(this.getLoginUser().getId());
			instrumentsInfo.setDeptId(this.getLoginUserDepartmentId());
			instrumentsInfo.setCreateTime(new Date());
			
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
			
			if ("add".equalsIgnoreCase(flag)){
				
				SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
				String destName = sdf1.format(new Date());
				
				instrumentsInfo.setFileName(fileName+destName+".docx");
				
				String day[] = sdf.format(instrumentsInfo.getTime()).split("-");
				instrumentsInfo.setAjh(day[0]);
				
				if(instrumentsInfo.getLinkId() == null || "".equals(instrumentsInfo.getLinkId()))
				{
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					instrumentsInfo.setLinkId(linkId);
				}
				m.put("nocaseId", "1");
				m.put("instrumentType", wstype);
				m.put("personType", instrumentsInfo.getPersonType());
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				int num = list.size() + 1;
				String ss = num + "";
				if(ss.length() == 1)
				{
					ss = "00" + ss;
				}
				else if(ss.length() == 2)
				{
					ss = "0" + ss;
				}
				instrumentsInfo.setAjhNum("D-" + ss);
				
				instrumentsInfo.setIfCheck("0");
				instrumentsInfo.setIfPrint("1");
				instrumentsInfo.setDzqmCheck("0");
				instrumentsInfo.setDzCheck("0");
				instrumentsInfo.setJzCheck("0");
				instrumentsInfoService.save(instrumentsInfo);
			}
			else{//修改文书信息
				//如果已生成文书，将之前生成文书置为历史版本
				if(type != null && "1".equals(type) && instrumentsInfo.getIfPrint() != null && "1".equals(instrumentsInfo.getIfPrint()))
				{
					String lsname = instrumentsInfo.getLastFile();
					String filename = instrumentsInfo.getFileName();
					SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String destName = sdf1.format(new Date());
					String newfilename = fileName+destName+".docx";
					instrumentsInfo.setFileName(newfilename);
					instrumentsInfo.setLastFile(lsname + "," + filename);
				}
			}
			Map<String, Object> map=new HashMap<String, Object>();
			FileDocUtil fileDocUtil = new FileDocUtil();
			if(wstype.equals("9"))//现场检查记录
			{
				String undertakerName = siteCheckRecord.getCheckPersonName1();
				if(siteCheckRecord.getCheckPerson() != null)
				{
					User user = userService.findUserById(siteCheckRecord.getCheckPerson().trim());
					undertakerName += "," +user.getDisplayName();
				}
				siteCheckRecord.setCheckPersonName(undertakerName);
				siteCheckRecord.setRelatedId(instrumentsInfo.getId());
				siteCheckRecord.setDelFlag(0);
				siteCheckRecord.setCreateUserID(instrumentsInfo.getCreateUserID());
				siteCheckRecord.setCreateTime(instrumentsInfo.getCreateTime());
				if ("add".equalsIgnoreCase(flag)){
					siteCheckRecordService.save(siteCheckRecord);
				}else{
					siteCheckRecordService.update(siteCheckRecord);
				}
				
				
				//生成文书	
				map.put("companyName", NullToString(instrumentsInfo.getCompanyName()));
				map.put("companyAddress", NullToString(siteCheckRecord.getCompanyAddress()));
				map.put("chargePersonZw", NullToString(siteCheckRecord.getChargePersonZw()));
				map.put("chargePersonTel", NullToString(siteCheckRecord.getChargePersonTel()));
				map.put("chargePerson", NullToString(siteCheckRecord.getChargePerson()));
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
				String[] s = fileDocUtil.createDocFile(root+"现场检查记录.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/行政执法"+fileName, map).split(",");
				instrumentsInfo.setFileSize(s[0]);
				instrumentsInfo.setPageSize(s[1]);
				instrumentsInfoService.update(instrumentsInfo);
			}
			else if(wstype.equals("11"))//责令限期整改指令书
			{
				String undertakerName = orderDeadlineBook.getLawOfficerName1();
				if(orderDeadlineBook.getLawOfficer() != null)
				{
					User user = userService.findUserById(orderDeadlineBook.getLawOfficer().trim());
					undertakerName += "," +user.getDisplayName();
				}
				orderDeadlineBook.setLawOfficerName(undertakerName);
				if(instrumentsInfo.getPersonType().equals("0"))
				{
					instrumentsInfo.setAjbz("苏园安监违责改字");
				}
				else
				{
					instrumentsInfo.setAjbz("苏园安监责改字");
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
				
				//生成文书	
				map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
				map.put("zgh", NullToString(instrumentsInfo.getAjh()));
				map.put("zghNum", NullToString(instrumentsInfo.getAjhNum()));
				map.put("companyName", NullToString(instrumentsInfo.getCompanyName()));
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
				String[] s = fileDocUtil.createDocFile(root+"责令限期整改指令书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/行政执法"+fileName, map).split(",");
				instrumentsInfo.setFileSize(s[0]);
				instrumentsInfo.setPageSize(s[1]);
				instrumentsInfoService.update(instrumentsInfo);
			}
			else if(wstype.equals("12"))//整改复查意见书
			{
				String undertakerName =reviewSubmission.getLawOfficerName1() ;
				if(reviewSubmission.getLawOfficer() != null)
				{
					User user = userService.findUserById(reviewSubmission.getLawOfficer().trim());
					undertakerName +="," + user.getDisplayName();
				}
				reviewSubmission.setLawOfficerName(undertakerName);
				if(instrumentsInfo.getPersonType().equals("0"))
				{
					instrumentsInfo.setAjbz("苏园安监违复查字");
				}
				else
				{
					instrumentsInfo.setAjbz("苏园安监复查字");
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
				
				//生成文书	
				map.put("ajbz", NullToString(instrumentsInfo.getAjbz()));
				map.put("fch", NullToString(instrumentsInfo.getAjh()));
				map.put("fchNum", NullToString(instrumentsInfo.getAjhNum()));
				map.put("companyName", NullToString(instrumentsInfo.getCompanyName()));

				map.put("xqzgTime",changeTimeToZw(reviewSubmission.getXqzgTime())); 
				map.put("zgjd",NullToString(reviewSubmission.getZgjd()));
				map.put("jdajbz",NullToString(reviewSubmission.getJdajbz()));
				map.put("zfh",NullToString(reviewSubmission.getZfh()));
				map.put("zfhNum",NullToString(reviewSubmission.getZfhNum()));
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
				String[] s = fileDocUtil.createDocFile(root+"整改复查意见书.docx", instrumentsInfo.getFileName(), root+"../../virtualdir/file/行政执法"+fileName, map).split(",");
				instrumentsInfo.setFileSize(s[0]);
				instrumentsInfo.setPageSize(s[1]);
					
				instrumentsInfoService.update(instrumentsInfo);
			}
			if(instrumentsInfo.getAjbz() != null && !"".equals(instrumentsInfo.getAjbz()) && "add".equalsIgnoreCase(this.flag))
			{
				String wsh = instrumentsInfo.getAjbz() + "〔" + instrumentsInfo.getAjh() + "〕"  + instrumentsInfo.getAjhNum() +  "号";
				instrumentsInfo.setWsh(wsh);
			}
			if(instrumentsInfo.getId() != null && !"".equals(instrumentsInfo.getId()))
			{
				instrumentsInfo.setDelFlag(0);
				instrumentsInfoService.update(instrumentsInfo);
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
		if (null != instrumentsInfo)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到instrumentsInfo中去
				
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
			instrumentsInfoService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public void export()
	{
		try
  		{
			instrumentsInfo = instrumentsInfoService.getById(instrumentsInfo.getId());
			if(fileName == null || "".equals(fileName))
			{
				fileName = instrumentsInfo.getFileName();
			}
			String u = "";
			if(instrumentsInfo.getCaseName() != null && !"".equals(instrumentsInfo.getCaseName()))
			{
				u = instrumentsInfo.getNwurl()+"/file/"+URLEncoder.encode(instrumentsInfo.getCaseName(), "utf-8")+"/"+URLEncoder.encode(fileName, "utf-8");
			}
			else
			{
				Map m = new HashMap();
				m.put("codeName", "文书类型");
				m.put("itemValue", instrumentsInfo.getInstrumentType());
				String filename = codeService.findCodeValueByMap(m).getItemText();
				u = instrumentsInfo.getNwurl()+"/file/"+URLEncoder.encode("行政执法"+filename, "utf-8")+"/"+URLEncoder.encode(fileName, "utf-8");
			}
  			URL url = new URL(u); 
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
	 * 录入回执
	 * @author luting
	 * 2015-10-22
	 * @return
	 */
	public String returnPic()
	{
		if((null != instrumentsInfo)&&(null != instrumentsInfo.getId()))
		{
			instrumentsInfo = instrumentsInfoService.getById(instrumentsInfo.getId());
			Map map = new HashMap();
			map.put("linkId",instrumentsInfo.getLinkId());
			map.put("mkType", "wsgl");
			map.put("picType","wsglfj");
			picList = photoPicService.findPicPath(map);//获取执法文书材料
		}
		return SUCCESS;
	}
	
	/**
	 * 保存回执信息
	 * @author luting
	 * 2015-10-31
	 * @throws IOException 
	 */
	public String picSave() throws IOException
	{
		try {
			if((null != instrumentsInfo)&&(null != instrumentsInfo.getId()))
			{
				InstrumentsInfo inn = instrumentsInfoService.getById(instrumentsInfo.getId());
				inn.setAddress(instrumentsInfo.getAddress());
				inn.setReturnWay(instrumentsInfo.getReturnWay());
				inn.setReturnTime(instrumentsInfo.getReturnTime());
				inn.setReturnPerson(instrumentsInfo.getReturnPerson());
				inn.setIfReturn("1");
				instrumentsInfoService.update(inn);
				
				String root = this.getRequest().getRealPath("/");
				root = root.replaceAll("\\\\", "/");
				CaseInfo ca = caseInfoService.getById(inn.getCaseId());
				
				Map m = new HashMap();
				m.put("caseId", inn.getCaseId());
				m.put("instrumentType", "102");
				List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
				InstrumentsInfo ins = new InstrumentsInfo();
				if(list.size() != 0)
				{
					ins = list.get(0);
				}
				else
				{
					ins.setCaseId(inn.getCaseId());
					ins.setCaseName(inn.getCaseName());
					ins.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
					ins.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
					ins.setInstrumentType("102");
					ins.setTime(new Date());
					String fileName = "文书送达回执";
					
					SimpleDateFormat sdf1 =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
					String destName = sdf1.format(ins.getTime());
					SimpleDateFormat sdf11 =  new SimpleDateFormat("yyyyMMdd");
					String instrumentsName = fileName + sdf11.format(ins.getTime());
					ins.setInstrumentName(instrumentsName);
					ins.setIfCheck("0");
					ins.setIfPrint("1");
					ins.setDeptId(this.getLoginUserDepartmentId());
					ins.setFileName(fileName+destName+".docx");
					ins.setCompanyName(ca.getCompanyName());
					//获取文书号 luting 2015-10-25
					if(ca.getFineType().equals("0"))
					{
						ins.setAjbz("苏园安监违管回字");
					}
					else
					{
						ins.setAjbz("苏园安监管回字");
					}
					ins.setAjh(ca.getGlh());
					ins.setAjhNum(ca.getGlhNum());
					String wsh = ins.getAjbz() +  "〔" + ins.getAjh() + "〕"  + ins.getAjhNum() +  "号";
					ins.setWsh(wsh);
					ins.setDelFlag(0);
					String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					ins.setLinkId(linkId);
					instrumentsInfoService.save(ins);
				}
				
				Map<String, Object> map1=new HashMap<String, Object>();
				map1.put("ajbz", NullToString(ins.getAjbz()));
				map1.put("ghh", NullToString(ins.getAjh()));
				map1.put("ghhNum", NullToString(ins.getAjhNum()));
				map1.put("caseName", NullToString(ca.getCaseName()));
				if(ca.getPersonType().equals("1"))
				{
					map1.put("person", NullToString(ca.getPerson()));
				}
				else
				{
					map1.put("person", NullToString(ca.getCompanyName()));
				}
				
				Map mm = new HashMap();
				mm.put("caseId", ca.getId());
				List<InstrumentsInfo> wslist = instrumentsInfoService.findInstrumentsInfos(mm);
				
				List<Map<String, Object>> newList1 = new ArrayList<Map<String, Object>>();
				for(int i=0;i<wslist.size();i++)
				{
					InstrumentsInfo in = wslist.get(i);
					Map<String, Object> mm1 = new HashMap<String, Object>();
					m.put("codeName", "文书类型");
					m.put("itemValue", in.getInstrumentType());
					String fileName = codeService.findCodeValueByMap(m).getItemText();
					if(in.getIfReturn() != null && in.getIfReturn().equals("1"))
					{
						if(in.getWsh() != null && !"".equals(in.getWsh()))
						{
							mm1.put("wsmc",fileName+ "(" + in.getWsh() + ")" );
						}
						else
						{
							mm1.put("wsmc",fileName);
						}
						mm1.put("sddz", NullToString(in.getAddress()));
						mm1.put("sdrq", changeTimeToZw(in.getReturnTime()));
						mm1.put("sdfs", NullToString(in.getReturnWay()));
						mm1.put("sdr", NullToString(in.getReturnPerson()));
						newList1.add(mm1);
					}
				}
				map1.put("wshzList", newList1);
//			size = wordUtil.createWord("文书送达回执.ftl", "文书送达回执.doc", root+"../../virtualdir/file/"+ca.getCaseName(), map1);
				FileDocUtil fileDocUtil = new FileDocUtil();	
				String[] s = fileDocUtil.createDocFile(root+"文书送达回执.docx",ins.getFileName(),root+"../../virtualdir/file/"+ca.getCaseName(), map1).split(",");
				ins.setFileSize(s[0]);
				ins.setPageSize(s[1]);
				instrumentsInfoService.update(ins);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}
	
	/**
	 * 调转至审核
	 * @author luting
	 * 2015-10-25
	 * @return
	 * @throws Exception 
	 */
	public String shenhe() throws Exception
	{
		view();
		return SUCCESS;
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
	 * 保存审核信息
	 * @author luting
	 * 2015-10-25
	 * @return
	 * @throws Exception 
	 */
	public String shenheSave() throws Exception
	{
		InstrumentsInfo in = instrumentsInfoService.getById(instrumentsInfo.getId());
		CaseInfo ca = caseInfoService.getById(in.getCaseId());
		String type = in.getInstrumentType();
		String result = instrumentsInfo.getResult();
		String remark = instrumentsInfo.getRemark();
		Date checkTime = instrumentsInfo.getCheckTime();
		User user = this.getLoginUser();
		
		FileDocUtil fileDocUtil = new FileDocUtil();	
		String root = this.getRequest().getRealPath("/");
		root = root.replaceAll("\\\\", "/");
		InstrumentsInfoUtil instrumentsInfoUtil = new InstrumentsInfoUtil();
		
		if(type.equals("1"))//询问通知书
		{
			InquiryNotice inq = inquiryNoticeService.getById(inquiryNotice.getId());
			instrumentsInfoUtil.shenheInquiryNotice(in, ca, inq, user, root, result, remark, checkTime);
		}
		else if(type.equals("2"))//询问笔录
		{
			InquiryRecord inq = inquiryRecordService.getById(inquiryRecord.getId());
			instrumentsInfoUtil.shenheInquiryRecord(in, ca, inq, user, root, result, remark, checkTime);
		}
		else if(type.equals("3"))//勘验笔录
		{
			InquestRecord inq = inquestRecordService.getById(inquestRecord.getId());
			instrumentsInfoUtil.shenheInquestRecord(in, ca, inq, user, root, result, remark, checkTime);
		}
		else if(type.equals("4"))//抽样取证凭证
		{
			SamplingEvidence sam = samplingEvidenceService.getById(samplingEvidence.getId());
			instrumentsInfoUtil.shenheSamplingEvidence(in, ca, sam, user, root, result, remark, checkTime);
		}
		else if(type.equals("5"))//先行登记保存证据审批表
		{
			PreserveEvidence pe = preserveEvidenceService.getById(preserveEvidence.getId());
			instrumentsInfoUtil.shenhePreserveEvidence(in, ca, pe, user, root, result, remark, checkTime);
		}
		else if(type.equals("6"))//先行登记保存证据通知书
		{
			NoticeEvidence no = noticeEvidenceService.getById(noticeEvidence.getId());
			instrumentsInfoUtil.shenheNoticeEvidence(in, ca, no, user, root, result, remark, checkTime);
		}
		else if(type.equals("7"))//先行登记保存证据处理审批表
		{
			InventoryCheck inv = inventoryCheckService.getById(inventoryCheck.getId());
			instrumentsInfoUtil.shenheInventoryCheck(in, ca, inv, user, root, result, remark, checkTime);
		}
		else if(type.equals("8"))//先行登记保存证据处理决定书 
		{
			InventoryDecision inv = inventoryDecisionService.getById(inventoryDecision.getId());
			instrumentsInfoUtil.shenheInventoryDecision(in, ca, inv, user, root, result, remark, checkTime);
		}
		else if(type.equals("9"))//现场检查记录
		{
			SiteCheckRecord sit = siteCheckRecordService.getById(siteCheckRecord.getId());
			instrumentsInfoUtil.shenheSiteCheckRecord(in, ca, sit, user, root, result, remark, checkTime);
		}
		else if(type.equals("10"))//现场处理措施决定书
		{
			LiveActionDecision liv = liveActionDecisionService.getById(liveActionDecision.getId());
			instrumentsInfoUtil.shenheLiveActionDecision(in, ca, liv, user, root, result, remark, checkTime);
		}
		else if(type.equals("11"))//责令限期整改指令书
		{
			OrderDeadlineBook od = orderDeadlineBookService.getById(orderDeadlineBook.getId());
			instrumentsInfoUtil.shenheOrderDeadlineBook(in, ca, od, user, root, result, remark, checkTime);
		}
		else if(type.equals("12"))//整改复查意见书
		{
			ReviewSubmission rev =reviewSubmissionService.getById(reviewSubmission.getId());
			instrumentsInfoUtil.shenheReviewSubmission(in, ca, rev, user, root, result, remark, checkTime);
		}
		else if(type.equals("13"))//强制措施决定书
		{
			EnforenceDecision enf = enforenceDecisionService.getById(enforenceDecision.getId());
			instrumentsInfoUtil.shenheEnforenceDecision(in, ca, enf, user, root, result, remark, checkTime);
		}
		else if(type.equals("14"))//鉴定委托书
		{ 
			IdentifyAttorney ide = identifyAttorneyService.getById(identifyAttorney.getId());
			instrumentsInfoUtil.shenheIdentifyAttorney(in, ca, ide, user, root, result, remark, checkTime);
		}
		else if(type.equals("15"))//行政处罚告知书
		{
			PenaltyNotice pen = penaltyNoticeService.getById(penaltyNotice.getId());
			instrumentsInfoUtil.shenhePenaltyNotice(in, ca, pen, user, root, result, remark, checkTime);
		}
		else if(type.equals("16"))//当事人陈述申辩笔录
		{
			PartyStateNote par = partyStateNoteService.getById(partyStateNote.getId());
			instrumentsInfoUtil.shenhePartyStateNote(in, ca, par, user, root, result, remark, checkTime);
		}
		else if(type.equals("17"))//听证告知书
		{
			HearingTell he = hearingTellService.getById(hearingTell.getId());
			instrumentsInfoUtil.shenheHearingTell(in, ca, he, user, root, result, remark, checkTime);
		}
		else if(type.equals("18"))//听证会通知书
		{
			HearingNotice hea = hearingNoticeService.getById(hearingNotice.getId());
			instrumentsInfoUtil.shenheHearingNotice(in, ca, hea, user, root, result, remark, checkTime);
		}
		else if(type.equals("19"))//听证笔录
		{
			HearingNote hea = hearingNoteService.getById(hearingNote.getId());
			instrumentsInfoUtil.shenheHearingNote(in, ca, hea, user, root, result, remark, checkTime);
		}
		else if(type.equals("20"))//听证会报告书
		{
			HearingReport he = hearingReportService.getById(hearingReport.getId());
			instrumentsInfoUtil.shenheHearingReport(in, ca, he, user, root, result, remark, checkTime);
		}
		else if(type.equals("21"))//案件处理呈批表
		{
			CaseProcessApproval cas = caseProcessApprovalService.getById(caseProcessApproval.getId());
			instrumentsInfoUtil.shenheCaseProcessApproval(in, ca, cas, user, root, result, remark, checkTime);
		}
		else if(type.equals("22"))//行政处罚集体讨论记录
		{
			PenBraRec pen  = penBraRecService.getById(penBraRec.getId());
			instrumentsInfoUtil.shenhePenBraRec(in, ca, pen, user, root, result, remark, checkTime);
		}
		else if(type.equals("23"))//行政（当场）处罚决定书（单位）
		{
			SpoPenDecCom spo = spoPenDecComService.getById(spoPenDecCom.getId());
			instrumentsInfoUtil.shenheSpoPenDecCom(in, ca, spo, user, root, result, remark, checkTime);
		}
		else if(type.equals("24"))//行政（当场）处罚决定书（个人）
		{
			SpoPenDecPer spo = spoPenDecPerService.getById(spoPenDecPer.getId());
			instrumentsInfoUtil.shenheSpoPenDecPer(in, ca, spo, user, root, result, remark, checkTime);
		}
		else if(type.equals("25"))//行政处罚决定书（单位）
		{
			PenDecCom pen = penDecComService.getById(penDecCom.getId());
			instrumentsInfoUtil.shenhePenDecCom(in, ca, pen, user, root, result, remark, checkTime);
		}
		else if(type.equals("26"))//行政处罚决定书（个人）
		{
			PenDecPer pen =penDecPerService.getById(penDecPer.getId());
			instrumentsInfoUtil.shenhePenDecPer(in, ca, pen, user, root, result, remark, checkTime);
		}
		else if(type.equals("27"))//罚款催缴通知书
		{
			instrumentsInfoUtil.shenheFkcjd(in, ca, user, root, result, remark, checkTime);
		}
		else if(type.equals("28"))//延期（分期）缴纳罚款审批表
		{
			PosFinApp pos =posFinAppService.getById(posFinApp.getId());
			instrumentsInfoUtil.shenhePosFinApp(in, ca, pos, user, root, result, remark, checkTime);
		}
		else if(type.equals("29"))//延期（分期）缴纳罚款批准书
		{
			PosFinRat pos = posFinRatService.getById(posFinRat.getId());
			instrumentsInfoUtil.shenhePosFinRat(in, ca, pos, user, root, result, remark, checkTime);
		}
		else if(type.equals("30"))//强制执行申请书
		{
			EnfApp enf = enfAppService.getById(enfApp.getId());
			instrumentsInfoUtil.shenheEnfApp(in, ca, enf, user, root, result, remark, checkTime);
		}
		else if(type.equals("31"))//结案审批表
		{
			CloseApproval clo =closeApprovalService.getById(closeApproval.getId());
			instrumentsInfoUtil.shenheCloseApproval(in, ca, clo, user, root, result, remark, checkTime);
		}
		else if(type.equals("32"))//案件移送审批表
		{
			CaseRefer cas = caseReferService.getById(caseRefer.getId());
			instrumentsInfoUtil.shenheCaseRefer(in, ca, cas, user, root, result, remark, checkTime);
		}
		else if(type.equals("33"))//案件移送书
		{
			CaseTransfer cas = caseTransferService.getById(caseTransfer.getId());
			instrumentsInfoUtil.shenheCaseTransfer(in, ca, cas, user, root, result, remark, checkTime);
		}
		else if(type.equals("34"))//调查报告
		{
			Dcbg dcb= dcbgService.getById(dcbg.getId());
			instrumentsInfoUtil.shenheDcbg(in, ca, dcb, user, root, result, remark, checkTime);
		}
		else if(type.equals("35"))//特殊事项审批表
		{
			SpecialItem spe= specialItemService.getById(specialItem.getId());
			instrumentsInfoUtil.shenheSpecialItem(in, ca, spe, user, root, result, remark, checkTime);
		}
		return RELOAD;
	}
	
	/**
	 * 验证文书信息是否能录入
	 * @author luting
	 * 2015-10-25
	 * @return
	 * @throws Exception 
	 */
	public String queryWsxx() throws IOException
	{
		Map m = new HashMap();
		m.put("caseId", instrumentsInfo.getCaseId());
		List<InstrumentsInfo> sylist  = instrumentsInfoService.findInstrumentsInfos(m);
		m.put("ifChecklist", "1");
		List<InstrumentsInfo> list  = instrumentsInfoService.findInstrumentsInfos(m);
		String syws = ",";
		String ws = ",";
		for(InstrumentsInfo in:sylist)
		{
			syws += in.getInstrumentType() + ",";
		}
		for(InstrumentsInfo in:list)
		{
			ws += in.getInstrumentType() + ",";
		}
		String type = instrumentsInfo.getInstrumentType();
		String ifCan = "0";
		String message = "";
		if(type.equals("2"))
		{
			if(!syws.contains(",1,") && !ws.contains(",1,"))
			{
				ifCan = "1";
				message = "录入询问笔录前必须先录入询问通知书！";
			}
			else if(syws.contains(",1,") && !ws.contains(",1,"))
			{
				ifCan = "1";
				message = "询问通知书审批通过后才可录入询问笔录！";
			}
		}
		else if(type.equals("6"))
		{
			if(!syws.contains(",5,") && !ws.contains(",5,"))
			{
				ifCan = "1";
				message = "录入先行登记保存证据通知书前必须先录入先行登记保存证据审批表！";
			}
			else if(syws.contains(",5,") && !ws.contains(",5,"))
			{
				ifCan = "1";
				message = "先行登记保存证据审批表审批通过后才可录入先行登记保存证据通知书！";
			}
		}
		else if(type.equals("7"))
		{
			if(!syws.contains(",5,") && !ws.contains(",5,"))
			{
				ifCan = "1";
				message = "录入先行登记保存证据处理审批表前必须先录入先行登记保存证据审批表！";
			}
			else if(syws.contains(",5,") && !ws.contains(",5,"))
			{
				ifCan = "1";
				message = "先行登记保存证据审批表审批通过后才可录入先行登记保存证据处理审批表！";
			}
		}
		else if(type.equals("8"))
		{
			if(!syws.contains(",5,") && !ws.contains(",5,"))
			{
				ifCan = "1";
				message = "录入先行登记保存证据处理决定书前必须先录入先行登记保存证据审批表";
			}
			else if(syws.contains(",5,") && !ws.contains(",5,"))
			{
				ifCan = "1";
				message = "先行登记保存证据审批表审批通过后才可录入先行登记保存证据处理决定书！";
			}
			else if(!syws.contains(",6,") && !ws.contains(",6,"))
			{
				ifCan = "1";
				message = "录入先行登记保存证据处理决定书前必须先录入先行登记保存证据通知书";
			}
			else if(syws.contains(",6,") && !ws.contains(",6,"))
			{
				ifCan = "1";
				message = "先行登记保存证据通知书审核通过后才可录入先行登记保存证据处理决定书";
			}
		}
		else if(type.equals("10"))
		{
			if(!syws.contains(",9,") && !ws.contains(",9,"))
			{
				ifCan = "1";
				message = "录入现场处理措施决定书前必须先录入现场检查记录！";
			}
			else if(syws.contains(",9,") && !ws.contains(",9,"))
			{
				ifCan = "1";
				message = "现场检查记录审核通过后才可录入现场处理措施决定书！";
			}
		}
		else if(type.equals("12"))
		{
			if(!(syws.contains(",10,") || syws.contains(",11,") || syws.contains(",13,")) && !(ws.contains(",10,") || ws.contains(",11,") || ws.contains(",13,")))
			{
				ifCan = "1";
				message = "录入整改复查意见书前必须先录入现场处理措施决定书、责令限期整改指令书和强制措施决定书中的任意一个！";
			}
			else if((syws.contains(",10,") || syws.contains(",11,") || syws.contains(",13,")) && !(ws.contains(",10,") || ws.contains(",11,") || ws.contains(",13,")))
			{
				ifCan = "1";
				message = "现场处理措施决定书、责令限期整改指令书和强制措施决定书中的任意一个审核通过后才可录入整改复查意见书！";
			}
		}
		else if(type.equals("16"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入当事人陈述申辩笔录前必须先录入行政处罚告知书 ！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入当事人陈述申辩笔录！";
			}
		}
		else if(type.equals("17"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入听证告知书前必须先录入行政处罚告知书 ！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入听证告知书！";
			}
		}
		else if(type.equals("19"))
		{
			if(!syws.contains(",18,") && !ws.contains(",18,"))
			{
				ifCan = "1";
				message = "录入听证笔录前必须先录入听证会通知书！";
			}
			else if(syws.contains(",18,") && !ws.contains(",18,"))
			{
				ifCan = "1";
				message = "听证会通知书审核通过后才可录入听证笔录！";
			}
		}
		else if(type.equals("20"))
		{
			if(!syws.contains(",18,") && !ws.contains(",18,"))
			{
				ifCan = "1";
				message = "录入听证会报告书前必须先录入听证会通知书！";
			}
			else if(syws.contains(",18,") && !ws.contains(",18,"))
			{
				ifCan = "1";
				message = "听证会通知书审核通过后才可录入听证会报告书！";
			}
		}
		else if(type.equals("21"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入案件处理呈批表前必须先录入行政处罚告知书！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入案件处理呈批表！";
			}
		}
		else if(type.equals("23"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入行政（当场）处罚决定书（单位）前必须先录入行政处罚告知书！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入行政（当场）处罚决定书（单位）！";
			}
		}
		else if(type.equals("24"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入行政（当场）处罚决定书（个人）前必须先录入行政处罚告知书！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入行政（当场）处罚决定书（个人）！";
			}
		}
		else if(type.equals("25"))
		{
			if(!syws.contains(",21,") && !ws.contains(",21,"))
			{
				ifCan = "1";
				message = "录入行政处罚决定书（单位）前必须先录入案件处理呈批表！";
			}
			else if(syws.contains(",21,") && !ws.contains(",21,"))
			{
				ifCan = "1";
				message = "案件处理呈批表审核通过后才可录入行政处罚决定书（单位）！";
			}
		}
		else if(type.equals("26"))
		{
			if(!syws.contains(",21,") && !ws.contains(",21,"))
			{
				ifCan = "1";
				message = "录入行政处罚决定书（个人）前必须先录入案件处理呈批表！";
			}
			else if(syws.contains(",21,") && !ws.contains(",21,"))
			{
				ifCan = "1";
				message = "案件处理呈批表审核通过后才可录入行政处罚决定书（个人）！";
			}
		}
		else if(type.equals("27"))
		{
			if(!(ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,")))
			{
				ifCan = "1";
				message = "录入罚款催缴通知书前必须先录入行政处罚决定书 ！";
			}
		}
		else if(type.equals("28"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入延期（分期）缴纳罚款审批表前必须先录入行政处罚告知书！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入延期（分期）缴纳罚款审批表！";
			}
			else if(!((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
			{
				ifCan = "1";
				message = "录入延期（分期）缴纳罚款审批表前必须先录入行政处罚决定书！";
			}
			else if(((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
			{
				ifCan = "1";
				message = "行政处罚决定书审核通过后才可录入延期（分期）缴纳罚款审批表！";
			}
		}
		else if(type.equals("29"))
		{
			if(!((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
			{
				ifCan = "1";
				message = "录入延期（分期）缴纳罚款批准书前必须先录入行政处罚决定书！";
			}
			else if(((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
			{
				ifCan = "1";
				message = "行政处罚决定书审核通过后才可延期（分期）缴纳罚款批准书！";
			}
			else if(!syws.contains(",28,") &&  !ws.contains(",28,"))
			{
				ifCan = "1";
				message = "录入延期（分期）缴纳罚款批准书前必须先录入延期（分期）缴纳罚款审批表 ！";
			}
			else if(syws.contains(",28,") &&  !ws.contains(",28,"))
			{
				ifCan = "1";
				message = "延期（分期）缴纳罚款审批表审批通过后才可录入延期（分期）缴纳罚款批准书 ！";
			}
		}
		else if(type.equals("30"))
		{
			if(!syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "录入强制执行申请书前必须先录入行政处罚告知书！";
			}
			else if(syws.contains(",15,") && !ws.contains(",15,"))
			{
				ifCan = "1";
				message = "行政处罚告知书审核通过后才可录入强制执行申请书！";
			}
			else if(!((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
			{
				ifCan = "1";
				message = "录入强制执行申请书前必须先录入行政处罚决定书！";
			}
			else if(((syws.contains(",23,") || syws.contains(",24,")|| syws.contains(",25,")|| syws.contains(",26,"))) && !((ws.contains(",23,") || ws.contains(",24,")|| ws.contains(",25,")|| ws.contains(",26,"))))
			{
				ifCan = "1";
				message = "行政处罚决定书审核通过后才可录入强制执行申请书！";
			}
		}
		else if(type.equals("31"))
		{
			if(!syws.equals(ws))
			{
				ifCan = "1";
				message = "所有录入文书均审核通过后才可录入结案审批表";
			}
		}
		else if(type.equals("32"))
		{
			if(!syws.contains(",34,") && !ws.contains(",34,"))
			{
				ifCan = "1";
				message = "录入案件移送审批表前必须先录入调查报告 ！";
			}
			else if(syws.contains(",34,") && !ws.contains(",34,"))
			{
				ifCan = "1";
				message = "调查报告审核通过后才可录入案件移送审批表！";
			}
		}
		else if(type.equals("33"))
		{
			if(!syws.contains(",32,") && !ws.contains(",32,"))
			{
				ifCan = "1";
				message = "录入案件移送书前必须先录入案件移送审批表！";
			}
			else if(syws.contains(",32,") && !ws.contains(",32,"))
			{
				ifCan = "1";
				message = "案件移送审批表审核通过后才可录入案件移送书！";
			}
		}
		JSONObject json = new JSONObject();
		json.put("ifCan", ifCan);
		json.put("message", message);
		this.getResponse().getWriter().println(json.toString());
		return null;
	}
	
	public String queryWsType()
	{
		String[] wsztname = {"","询问通知书","询问笔录","勘验笔录","抽样取证凭证","先行登记保存证据审批表","先行登记保存证据通知书","先行登记保存证据处理审批表",
				"先行登记保存证据处理决定书","现场检查记录","现场处理措施决定书","责令限期整改指令书","整改复查意见书","强制措施决定书","鉴定委托书","行政处罚告知书",
				"当事人陈述申辩笔录","听证告知书","听证会通知书","听证笔录","听证会报告书","案件处理呈批表","行政处罚集体讨论记录","行政（当场）处罚决定书（单位）",
				"行政（当场）处罚决定书（个人）","行政处罚决定书（单位）","行政处罚决定书（个人）","罚款催缴通知书","延期（分期）缴纳罚款审批表","延期（分期）缴纳罚款批准书",
				"强制执行申请书","结案审批表","案件移送审批表","案件移送书","调查报告","特殊事项审批表"};
			
		Map m = new HashMap();
		m.put("caseId", instrumentsInfo.getCaseId());
		List<InstrumentsInfo> list  = instrumentsInfoService.findInstrumentsInfos(m);
		CaseInfo ca = caseInfoService.getById(instrumentsInfo.getCaseId());
		
		String[] sl = new String[100];
		String[] sfcz = new String[100];
		
		for(int i=1;i<=wsztname.length;i++)
		{
			int num = 0;
			String aa = "0";
			for(InstrumentsInfo in:list)
			{
				if(in.getInstrumentType().equals(i+""))
				{
					num ++;
				}
			}
			sl[i] = num+"";
			if(num > 0)
			{
				aa = "1";
			}
			sfcz[i] = aa;
		}
		
		//取证 1 2 3 4 
		//询问通知书
		wsList.add(getWsInfo("1",wsztname[1],sl[1]+"",sfcz[1],"0"));
		// 询问笔录 
		wsList.add(getWsInfo("2",wsztname[2],sl[2]+"",sfcz[2],"0"));
		//勘验笔录
		wsList.add(getWsInfo("3",wsztname[3],sl[3]+"",sfcz[3],"0"));
		// 抽样取证凭证
		wsList.add(getWsInfo("4",wsztname[4],sl[4]+"",sfcz[4],"1"));
		
		//调查报告 34
		wsList.add(getWsInfo("34",wsztname[34],sl[34]+"",sfcz[34],"1"));
		
		//行政处罚集体讨论记录 22
		wsList.add(getWsInfo("22",wsztname[22],sl[22]+"",sfcz[22],"1"));
		
		//行政处罚告知书 15
		wsList.add(getWsInfo("15",wsztname[15],sl[15]+"",sfcz[15],"1"));
		
		//当事人陈述申辩笔录 16
		wsList.add(getWsInfo("16",wsztname[16],sl[16]+"",sfcz[16],"0"));
		
		//听证会通知书 18
		wsList.add(getWsInfo("18",wsztname[18],sl[18]+"",sfcz[18],"0"));
		 
		//听证笔录 19
		wsList.add(getWsInfo("19",wsztname[19],sl[19]+"",sfcz[19],"0"));
		
		//听证会报告书 20
		wsList.add(getWsInfo("20",wsztname[20],sl[20]+"",sfcz[20],"1"));
		
		//案件处理呈批表 21
		wsList.add(getWsInfo("21",wsztname[21],sl[21]+"",sfcz[21],"1"));
		
		//行政处罚决定书
		if(ca.getPersonType().equals("1")) //个人  行政处罚决定书（个人） 26
		{
			wsList.add(getWsInfo("26",wsztname[26],sl[26]+"",sfcz[26],"1"));
		}
		else //行政处罚决定书（单位） 25
		{
			wsList.add(getWsInfo("25",wsztname[25],sl[25]+"",sfcz[25],"1"));
		}
		
		//结案审批表 31
		wsList.add(getWsInfo("31",wsztname[31],sl[31]+"",sfcz[31],"0"));
		
		//其他文书
		//先行登记保存证据审批表5
		qtList.add(getWsInfo("5",wsztname[5],sl[5]+"",sfcz[5],"0"));

		//先行登记保存证据通知书6
		qtList.add(getWsInfo("6",wsztname[6],sl[6]+"",sfcz[6],"0"));

		//先行登记保存证据处理审批表7
		qtList.add(getWsInfo("7",wsztname[7],sl[7]+"",sfcz[7],"0"));

		//先行登记保存证据处理决定书8
		qtList.add(getWsInfo("8",wsztname[8],sl[8]+"",sfcz[8],"0"));

		//现场检查记录9
		qtList.add(getWsInfo("9",wsztname[9],sl[9]+"",sfcz[9],"0"));

		//现场处理措施决定书10
		qtList.add(getWsInfo("10",wsztname[10],sl[10]+"",sfcz[10],"0"));

		//责令限期整改指令书11
		qtList.add(getWsInfo("11",wsztname[11],sl[11]+"",sfcz[11],"0"));

		//整改复查意见书12
		qtList.add(getWsInfo("12",wsztname[12],sl[12]+"",sfcz[12],"0"));

		//强制措施决定书13
		qtList.add(getWsInfo("13",wsztname[13],sl[13]+"",sfcz[13],"0"));

		//鉴定委托书14
		qtList.add(getWsInfo("14",wsztname[14],sl[14]+"",sfcz[14],"0"));

		//行政（当场）处罚决定书（单位）23
		qtList.add(getWsInfo("23",wsztname[23],sl[23]+"",sfcz[23],"0"));
		
		//行政（当场）处罚决定书（个人）24
		qtList.add(getWsInfo("24",wsztname[24],sl[24]+"",sfcz[24],"0"));

		//罚款催缴通知书27
		qtList.add(getWsInfo("27",wsztname[27],sl[27]+"",sfcz[27],"0"));

		//延期（分期）缴纳罚款审批表28
		qtList.add(getWsInfo("28",wsztname[28],sl[28]+"",sfcz[28],"0"));

		//延期（分期）缴纳罚款批准书29
		qtList.add(getWsInfo("29",wsztname[29],sl[29]+"",sfcz[29],"0"));

		//强制执行申请书30
		qtList.add(getWsInfo("30",wsztname[30],sl[30]+"",sfcz[30],"0"));

		//案件移送审批表32
		qtList.add(getWsInfo("32",wsztname[32],sl[32]+"",sfcz[32],"0"));

		//案件移送书33
		qtList.add(getWsInfo("33",wsztname[33],sl[33]+"",sfcz[33],"0"));
		
		//特殊事项审批表35
		qtList.add(getWsInfo("35",wsztname[35],sl[35]+"",sfcz[35],"0"));
		
		return SUCCESS;
	}
	
	public WsInfo getWsInfo(String wsid,String wsname,String wssl,String sfcz,String jt)
	{
		WsInfo wsInfo = new WsInfo();
		wsInfo.setWsid(wsid);
		wsInfo.setWsname(wsname);
		wsInfo.setWssl(wssl);
		wsInfo.setSfcz(sfcz);
		wsInfo.setJt(jt);
		return wsInfo;
	}
	
	public String shenheAll()
	{
		return SUCCESS;
	}
	
	public String shenheAllSave() throws IOException
	{
			String result = instrumentsInfo.getResult();
			String remark = instrumentsInfo.getRemark();
			User user = this.getLoginUser();
			String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				for(String id : idArray)
				{
					if(id!=null && !id.trim().equals(""))
					{
						InstrumentsInfo in = instrumentsInfoService.getById(id);
						Date checkTime = in.getTime();
						CaseInfo ca = caseInfoService.getById(in.getCaseId());
						String type = in.getInstrumentType();
						
						FileDocUtil fileDocUtil = new FileDocUtil();	
						String root = this.getRequest().getRealPath("/");
						root = root.replaceAll("\\\\", "/");
						InstrumentsInfoUtil instrumentsInfoUtil = new InstrumentsInfoUtil();
						Map mm = new HashMap();
						mm.put("relatedId", id);
						
						if(type.equals("1"))//询问通知书
						{
							InquiryNotice inq = (InquiryNotice) inquiryNoticeService.findInquiryNotice(mm).get(0);
							instrumentsInfoUtil.shenheInquiryNotice(in, ca, inq, user, root, result, remark, checkTime);
						}
						else if(type.equals("2"))//询问笔录 luting 2015-10-25
						{
							InquiryRecord inq = (InquiryRecord) inquiryRecordService.findInquiryRecord(mm).get(0);
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
					}
				}
			}
			return RELOAD;
	}
	
	/**
  	 * 查询部门用户列表
  	 */
  	public String selectUsers()
  	{
  		return SUCCESS;
  	}
  	
  	/**
  	 * 跳转部门用户列表
  	 */
  	public void getDepartUser() throws IOException
  	{
  		JSONArray jsonArry = new JSONArray();
		List<Dept> deptList = instrumentsInfoService.getAllDepartByMap(null);
		for (Dept dept : deptList) {
			JSONObject jsonObject = new JSONObject();
	  		jsonObject.put("id", dept.getId());
	  		Map map1 = new HashMap();
	  		map1.put("deptcode", dept.getId());
	  		List<User> userList = instrumentsInfoService.getAllUsersByMap(map1);
	  		boolean ifqx = false;
			for(User user : userList){
				JSONObject jo = new JSONObject();
		  		jo.put("id", user.getId());
		  		jo.put("pId",user.getDeptCode());
		  		jo.put("name", user.getDisplayName());
		  		jo.put("flag", "1");
		  		jo.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
		  		
		  		if(userIds.contains(user.getId())){
		  			jo.put("checked",true);
		  			ifqx = true;
		  		}
		  		jsonArry.add(jo);
			}
	  		if(ifqx==true){
	  			jsonObject.put("checked",true);
	  		}
	  		if(dept.getParentId() == null || "".equals(dept.getParentId()) || "002".equals(dept.getParentId()))
	  		{
	  			jsonObject.put("pId", "-1");
	  		}
	  		else
	  		{
	  			jsonObject.put("pId", dept.getParentId());
	  		}
	  		jsonObject.put("name", dept.getDeptName());
	  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
	  		jsonObject.put("open", true);
	  		jsonArry.add(jsonObject);
		}
		JSONObject jsonObject2 = new JSONObject();
  		jsonObject2.put("id", "-1");
  		jsonObject2.put("pId","1");
  		jsonObject2.put("open", true);
  		String[] id = userIds.split(",");
  		if(userIds != null && !"".equals(userIds)){
  			jsonObject2.put("checked",true);
  			}
  		jsonObject2.put("name", "全选");
  		jsonObject2.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
  		jsonArry.add(jsonObject2);
		getResponse().getWriter().write(jsonArry.toString());
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
	
	public boolean compare(String s1,String s2){
  		boolean a=false;
  		if(!s1.equals("")){
  		String ids1[]=s1.split(",");//S1表示部门ids拼接字符串，S2表示传入的字符串
  		for(int i=0;i<ids1.length;i++){
  			if(s2.contains(ids1[i])){
  				a=true;
  			}else{
  				a=false;
  				break;
  			}
  		}
  		}
  		return a;
  	}
	
	public String lockUser() throws IOException
	{
	   try{
		   if(instrumentsInfo != null)
		   {
			   if(instrumentsInfo.getCaseId() != null && !"".equals(instrumentsInfo.getCaseId()))
			   {
				    Map map = new HashMap();
				   	String caseId = instrumentsInfo.getCaseId();
				   	String wstype = instrumentsInfo.getInstrumentType();
				   	String time = sdf.format(instrumentsInfo.getTime());
				   	map.put("caseId",caseId);
				   	map.put("wstype", wstype);
				   	map.put("lockTime", time);
					map.put("doUserId", this.getLoginUser().getId());
				    instrumentsInfoService.deleteLockUser(map);
				    
				    if(instrumentsInfo.getInstrumentType().equals("2") 
				    || instrumentsInfo.getInstrumentType().equals("3")
				    || instrumentsInfo.getInstrumentType().equals("4")
				    || instrumentsInfo.getInstrumentType().equals("9")
				    || instrumentsInfo.getInstrumentType().equals("16")
				    || instrumentsInfo.getInstrumentType().equals("22")
				    )
				    {
				    	LockUser lockUser = new LockUser();
					    lockUser.setCaseId(caseId);
					    lockUser.setUserId(this.getLoginUser().getId());
					    lockUser.setWstype(wstype);
					    lockUser.setLockTime(time);
					    lockUser.setDoUserId(this.getLoginUser().getId());
					    instrumentsInfoService.saveLockUser(lockUser);
				    }
				    
				    if(ids != null && !"".equals(ids))
				   {
					   String[] idss = ids.split(",");
					   for(String id:idss)
					   {
						   if(!id.equals(this.getLoginUser().getId()))
						   {
							   LockUser lockUser = new LockUser();
							   lockUser.setCaseId(caseId);
							   lockUser.setUserId(id);
							   lockUser.setWstype(wstype);
							   lockUser.setLockTime(time);
							   lockUser.setDoUserId(this.getLoginUser().getId());
							   instrumentsInfoService.saveLockUser(lockUser);
						   }
					   }
				   }
			   }
		   }
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public String lockAndShowUser() throws IOException
	{
		JSONObject jo = new JSONObject();
	   try{
		   if(instrumentsInfo != null)
		   {
			   if(instrumentsInfo.getCaseId() != null && !"".equals(instrumentsInfo.getCaseId()))
			   {
				    Map map = new HashMap();
				   	String caseId = instrumentsInfo.getCaseId();
				   	String wstype = instrumentsInfo.getInstrumentType();
				   	String time = sdf.format(instrumentsInfo.getTime());
				   	map.put("caseId",caseId);
				   	map.put("wstype", wstype);
				   	map.put("lockTime", time);
					map.put("doUserId", this.getLoginUser().getId());
				    instrumentsInfoService.deleteLockUser(map);
				    
				    LockUser lockUser = new LockUser();
				    lockUser.setCaseId(caseId);
				    lockUser.setUserId(this.getLoginUser().getId());
				    lockUser.setWstype(wstype);
				    lockUser.setLockTime(time);
				    lockUser.setDoUserId(this.getLoginUser().getId());
				    instrumentsInfoService.saveLockUser(lockUser);
				    
				    if(ids != null && !"".equals(ids))
				   {
					   String[] idss = ids.split(",");
					   for(String id:idss)
					   {
						   if(!id.equals(this.getLoginUser().getId()))
						   {
							   lockUser = new LockUser();
							   lockUser.setCaseId(caseId);
							   lockUser.setUserId(id);
							   lockUser.setWstype(wstype);
							   lockUser.setLockTime(time);
							   lockUser.setDoUserId(this.getLoginUser().getId());
							   instrumentsInfoService.saveLockUser(lockUser);
						   }
					   }
				   }
			   }
		   }
		    User user = userService.findUserById(ids);
		    jo.put("result", true);
		    jo.put("name", user.getDisplayName());
		    jo.put("zfzh", NullToString(user.getZfzh()));
			this.getResponse().getWriter().println(jo.toString());
		}catch(Exception e){
			jo.put("result", false);
			this.getResponse().getWriter().println(jo.toString());
		}
		return null;
	}
	
	public String unlockUser() throws IOException
	{
	   try{
		   if(instrumentsInfo != null)
		   {
			   if(instrumentsInfo.getCaseId() != null && !"".equals(instrumentsInfo.getCaseId()))
			   {
				   	Map map = new HashMap();
				   	String caseId = instrumentsInfo.getCaseId();
				   	String wstype = instrumentsInfo.getInstrumentType();
				   	String time = sdf.format(instrumentsInfo.getTime());
				   	map.put("caseId",caseId);
				   	map.put("wstype", wstype);
				   	map.put("lockTime", time);
					map.put("doUserId", this.getLoginUser().getId());
				    instrumentsInfoService.deleteLockUser(map);
			   }
		   }
		    
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	
	
	public String wsgl()
	{
		loginUserId = this.getLoginUser().getId();
		List<UserRight> list = (List<UserRight>) this.getLoginUser().getUserRoles();
		flag = "0";
		for(UserRight ur:list)
		{
			flag += ur.getRole().getRoleCode()+ ",";
		}
		if(this.getLoginUser().getDeptCode().startsWith("002001"))
		{
			flag += "B00";
		}
		return SUCCESS;
	}
	
	public void zwtList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != instrumentsInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != instrumentsInfo.getInstrumentType()) && (0 < instrumentsInfo.getInstrumentType().trim().length())){
				paraMap.put("instrumentType", instrumentsInfo.getInstrumentType().trim());
			}
			
			if ((null != instrumentsInfo.getInstrumentName()) && (0 < instrumentsInfo.getInstrumentName().trim().length())){
				paraMap.put("instrumentName",  "%" + instrumentsInfo.getInstrumentName().trim()+ "%");
			}

			if ((null != instrumentsInfo.getCaseName()) && (0 < instrumentsInfo.getCaseName().trim().length())){
				paraMap.put("caseName", "%" + instrumentsInfo.getCaseName().trim() + "%");
			}
			if ((null != instrumentsInfo.getCaseId()) && (0 < instrumentsInfo.getCaseId().trim().length())){
				paraMap.put("caseId", instrumentsInfo.getCaseId().trim() );
			}
			if ((null != instrumentsInfo.getIfCheck()) && (0 < instrumentsInfo.getIfCheck().trim().length())){
				List<String> ll = new ArrayList<String>();
				if(instrumentsInfo.getIfCheck().equals("1"))
				{
					List<UserRight> list = (List<UserRight>) this.getLoginUser().getUserRoles();
					flag = "0";
					for(UserRight ur:list)
					{
						//登录人为安监局领导
						if(ur.getRole().getRoleCode().equals("A02")) 
						{
							flag = "1";
							break;
						}
						//登录人为监察大队队长
						if(ur.getRole().getRoleCode().equals("A09")) 
						{
							flag = "2";
							break;
						}
						//登录人为法务
						if(ur.getRole().getRoleCode().equals("A30")) 
						{
							flag = "4";
							break;
						}
					}
					if(this.getLoginUser().getDeptCode().startsWith("002001"))
					{
						if(!"1".equals(flag)&&!"2".equals(flag)&&!"4".equals(flag))
						{
							flag = "3";
						}
					}
					if(flag.equals("1"))//安监领导待审核状态 2
					{
						paraMap.put("ifcheck", "2");
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else if(flag.equals("2")) //大队队长待审核状态 1
					{
						paraMap.put("ifcheck", "1");
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else if(flag.equals("3")) //安监局其他人员
					{
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else if(flag.equals("4")) //法务
					{
						paraMap.put("ifcheck", "8");
						paraMap.put("needCheckUser", ","+this.getLoginUser().getId()+",");
					}
					else
					{
						ll.add("1");
						ll.add("2");
						ll.add("3");
						ll.add("8");
					}
				}
				else if(instrumentsInfo.getIfCheck().equals("2"))
				{
					ll.add("0");
					ll.add("5");
				}
				else if(instrumentsInfo.getIfCheck().equals("4"))
				{
					ll.add("4");
				}
				paraMap.put("ifCheck", ll);
			}
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("instrumentType","402880fc50460b9a0150467811e301fe");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|caseName|instrumentType|ifPrint|ifCheck|createUserID|needCheckUser|instrumentName|companyName|";
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
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或文书名称".equals(searchLike)){
			paraMap.put("searchLike", "%" + searchLike.trim() + "%");
		}
		
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = instrumentsInfoService.findByPage(pagination, paraMap);
		
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

	public InstrumentsInfo getInstrumentsInfo(){
		return this.instrumentsInfo;
	}

	public void setInstrumentsInfo(InstrumentsInfo instrumentsInfo){
		this.instrumentsInfo = instrumentsInfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
	public List<PhotoPic> getPicList() {
		return picList;
	}
	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
	public InquiryNotice getInquiryNotice() {
		return inquiryNotice;
	}
	public void setInquiryNotice(InquiryNotice inquiryNotice) {
		this.inquiryNotice = inquiryNotice;
	}
	public InquiryRecord getInquiryRecord() {
		return inquiryRecord;
	}
	public void setInquiryRecord(InquiryRecord inquiryRecord) {
		this.inquiryRecord = inquiryRecord;
	}
	public InquestRecord getInquestRecord() {
		return inquestRecord;
	}
	public void setInquestRecord(InquestRecord inquestRecord) {
		this.inquestRecord = inquestRecord;
	}
	public SamplingEvidence getSamplingEvidence() {
		return samplingEvidence;
	}
	public void setSamplingEvidence(SamplingEvidence samplingEvidence) {
		this.samplingEvidence = samplingEvidence;
	}
	public SamplingAssociate getSamplingAssociate() {
		return samplingAssociate;
	}
	public void setSamplingAssociate(SamplingAssociate samplingAssociate) {
		this.samplingAssociate = samplingAssociate;
	}
	public List<SamplingAssociate> getCyqyglbList() {
		return cyqyglbList;
	}
	public void setCyqyglbList(List<SamplingAssociate> cyqyglbList) {
		this.cyqyglbList = cyqyglbList;
	}
	public PreserveEvidence getPreserveEvidence() {
		return preserveEvidence;
	}
	public void setPreserveEvidence(PreserveEvidence preserveEvidence) {
		this.preserveEvidence = preserveEvidence;
	}
	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}
	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}
	public InventoryAssociate getInventoryAssociate() {
		return inventoryAssociate;
	}
	public void setInventoryAssociate(InventoryAssociate inventoryAssociate) {
		this.inventoryAssociate = inventoryAssociate;
	}
	public List<InventoryAssociate> getZzdjbczjglbList() {
		return zzdjbczjglbList;
	}
	public void setZzdjbczjglbList(List<InventoryAssociate> zzdjbczjglbList) {
		this.zzdjbczjglbList = zzdjbczjglbList;
	}
	public NoticeEvidence getNoticeEvidence() {
		return noticeEvidence;
	}
	public void setNoticeEvidence(NoticeEvidence noticeEvidence) {
		this.noticeEvidence = noticeEvidence;
	}
	public InventoryCheck getInventoryCheck() {
		return inventoryCheck;
	}
	public void setInventoryCheck(InventoryCheck inventoryCheck) {
		this.inventoryCheck = inventoryCheck;
	}
	public InventoryDecision getInventoryDecision() {
		return inventoryDecision;
	}
	public void setInventoryDecision(InventoryDecision inventoryDecision) {
		this.inventoryDecision = inventoryDecision;
	}
	public SiteCheckRecord getSiteCheckRecord() {
		return siteCheckRecord;
	}
	public void setSiteCheckRecord(SiteCheckRecord siteCheckRecord) {
		this.siteCheckRecord = siteCheckRecord;
	}
	public LiveActionDecision getLiveActionDecision() {
		return liveActionDecision;
	}
	public void setLiveActionDecision(LiveActionDecision liveActionDecision) {
		this.liveActionDecision = liveActionDecision;
	}
	public OrderDeadlineBook getOrderDeadlineBook() {
		return orderDeadlineBook;
	}
	public void setOrderDeadlineBook(OrderDeadlineBook orderDeadlineBook) {
		this.orderDeadlineBook = orderDeadlineBook;
	}
	public ReviewSubmission getReviewSubmission() {
		return reviewSubmission;
	}
	public void setReviewSubmission(ReviewSubmission reviewSubmission) {
		this.reviewSubmission = reviewSubmission;
	}
	public EnforenceDecision getEnforenceDecision() {
		return enforenceDecision;
	}
	public void setEnforenceDecision(EnforenceDecision enforenceDecision) {
		this.enforenceDecision = enforenceDecision;
	}
	public IdentifyAttorney getIdentifyAttorney() {
		return identifyAttorney;
	}
	public void setIdentifyAttorney(IdentifyAttorney identifyAttorney) {
		this.identifyAttorney = identifyAttorney;
	}
	public PenaltyNotice getPenaltyNotice() {
		return penaltyNotice;
	}
	public void setPenaltyNotice(PenaltyNotice penaltyNotice) {
		this.penaltyNotice = penaltyNotice;
	}
	public PartyStateNote getPartyStateNote() {
		return partyStateNote;
	}
	public void setPartyStateNote(PartyStateNote partyStateNote) {
		this.partyStateNote = partyStateNote;
	}
	public HearingNote getHearingNote() {
		return hearingNote;
	}
	public void setHearingNote(HearingNote hearingNote) {
		this.hearingNote = hearingNote;
	}
	public HearingReport getHearingReport() {
		return hearingReport;
	}
	public void setHearingReport(HearingReport hearingReport) {
		this.hearingReport = hearingReport;
	}
	public CaseProcessApproval getCaseProcessApproval() {
		return caseProcessApproval;
	}
	public void setCaseProcessApproval(CaseProcessApproval caseProcessApproval) {
		this.caseProcessApproval = caseProcessApproval;
	}
	public PenBraRec getPenBraRec() {
		return penBraRec;
	}
	public void setPenBraRec(PenBraRec penBraRec) {
		this.penBraRec = penBraRec;
	}
	public SpoPenDecCom getSpoPenDecCom() {
		return spoPenDecCom;
	}
	public void setSpoPenDecCom(SpoPenDecCom spoPenDecCom) {
		this.spoPenDecCom = spoPenDecCom;
	}
	public SpoPenDecPer getSpoPenDecPer() {
		return spoPenDecPer;
	}
	public void setSpoPenDecPer(SpoPenDecPer spoPenDecPer) {
		this.spoPenDecPer = spoPenDecPer;
	}
	public PenDecCom getPenDecCom() {
		return penDecCom;
	}
	public void setPenDecCom(PenDecCom penDecCom) {
		this.penDecCom = penDecCom;
	}
	public PenDecPer getPenDecPer() {
		return penDecPer;
	}
	public void setPenDecPer(PenDecPer penDecPer) {
		this.penDecPer = penDecPer;
	}
	public PosFinApp getPosFinApp() {
		return posFinApp;
	}
	public void setPosFinApp(PosFinApp posFinApp) {
		this.posFinApp = posFinApp;
	}
	public PosFinRat getPosFinRat() {
		return posFinRat;
	}
	public void setPosFinRat(PosFinRat posFinRat) {
		this.posFinRat = posFinRat;
	}
	public EnfApp getEnfApp() {
		return enfApp;
	}
	public void setEnfApp(EnfApp enfApp) {
		this.enfApp = enfApp;
	}
	public CloseApproval getCloseApproval() {
		return closeApproval;
	}
	public void setCloseApproval(CloseApproval closeApproval) {
		this.closeApproval = closeApproval;
	}
	public CaseRefer getCaseRefer() {
		return caseRefer;
	}
	public void setCaseRefer(CaseRefer caseRefer) {
		this.caseRefer = caseRefer;
	}
	public CaseTransfer getCaseTransfer() {
		return caseTransfer;
	}
	public void setCaseTransfer(CaseTransfer caseTransfer) {
		this.caseTransfer = caseTransfer;
	}
	public IdentifyItemAssociate getIdentifyItemAssociate() {
		return identifyItemAssociate;
	}
	public void setIdentifyItemAssociate(IdentifyItemAssociate identifyItemAssociate) {
		this.identifyItemAssociate = identifyItemAssociate;
	}
	public List<IdentifyItemAssociate> getJdwpList() {
		return jdwpList;
	}
	public void setJdwpList(List<IdentifyItemAssociate> jdwpList) {
		this.jdwpList = jdwpList;
	}
	public HearingNotice getHearingNotice() {
		return hearingNotice;
	}
	public void setHearingNotice(HearingNotice hearingNotice) {
		this.hearingNotice = hearingNotice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Dcbg getDcbg() {
		return dcbg;
	}
	public void setDcbg(Dcbg dcbg) {
		this.dcbg = dcbg;
	}
	
	public InqRecRecord getInqRecRecord() {
		return inqRecRecord;
	}
	public void setInqRecRecord(InqRecRecord inqRecRecord) {
		this.inqRecRecord = inqRecRecord;
	}
	public List<InqRecRecord> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<InqRecRecord> recordList) {
		this.recordList = recordList;
	}
	public HearingTell getHearingTell() {
		return hearingTell;
	}
	public void setHearingTell(HearingTell hearingTell) {
		this.hearingTell = hearingTell;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public List<WsInfo> getWsList() {
		return wsList;
	}

	public void setWsList(List<WsInfo> wsList) {
		this.wsList = wsList;
	}

	public List<WsInfo> getQtList() {
		return qtList;
	}

	public void setQtList(List<WsInfo> qtList) {
		this.qtList = qtList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public SpecialItem getSpecialItem() {
		return specialItem;
	}

	public void setSpecialItem(SpecialItem specialItem) {
		this.specialItem = specialItem;
	}

	public String getLockTime() {
		return lockTime;
	}

	public void setLockTime(String lockTime) {
		this.lockTime = lockTime;
	}

	public String getIfcheck() {
		return ifcheck;
	}

	public void setIfcheck(String ifcheck) {
		this.ifcheck = ifcheck;
	}

	public List<CheckPro> getProList() {
		return proList;
	}

	public void setProList(List<CheckPro> proList) {
		this.proList = proList;
	}

	public CaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}

	public List<String> getLastFile() {
		return lastFile;
	}

	public void setLastFile(List<String> lastFile) {
		this.lastFile = lastFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
