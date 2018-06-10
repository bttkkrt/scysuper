package com.jshx.yhb.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cn.jpush.api.JPushClient;

import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.service.CaseInfoService;
import com.jshx.ajxx.util.FileDocUtil;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.service.HttpService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.service.PhotoPicService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.sendSms.SendSms;
import com.jshx.shjl.entity.CheckRecord;
import com.jshx.shjl.service.CheckRecordService;
import com.jshx.wsgl.entity.InstrumentsInfo;
import com.jshx.wsgl.service.InstrumentsInfoService;
import com.jshx.wxyxcrwgl.entity.DanTasMan;
import com.jshx.wxyxcrwgl.service.DanTasManService;
import com.jshx.xcjcjl.entity.SiteCheckRecord;
import com.jshx.xcjcjl.service.SiteCheckRecordService;
import com.jshx.yhb.entity.TjYhBean;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

public class TroManAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	private String mapKey;
	/**
	 * 实体类
	 */
	private TroMan troMan = new TroMan();
	TjYhBean tjyhBean = new TjYhBean();
	/**
	 * 审核记录
	 */
	private List<CheckRecord> checkRecords;
	
	private CheckRecord checkRecord=new CheckRecord();
	
	private CaseInfo caseInfo = new CaseInfo();

	/**
	 * 业务类
	 */
	@Autowired
	private TroManService troManService;
	@Autowired
	private PhotoPicService photoPicService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private CheckRecordService checkRecordService;
	@Autowired
	private DanTasManService danTasManService;
	@Autowired
	private UserService userService;
	@Autowired
	private CaseInfoService caseInfoService;
	
	
	private List<User> userList = new ArrayList<User>();
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private String linkId;
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryReportTimeStart;

	private Date queryReportTimeEnd;

	private Date queryRectificationTermStart;

	private Date queryRectificationTermEnd;

	private Date queryRecfinishTimeStart;

	private Date queryRecfinishTimeEnd;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();

	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	
	private List<HashMap> rectInfos = new ArrayList<HashMap>();

	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private SimpleDateFormat sdfFormat=new SimpleDateFormat("yyyy-MM-dd");
	
	List<TjYhBean> tjyhList = new ArrayList<TjYhBean>();
	
	private Map counts = new HashMap();
	
	private String jcbl;
	
	private String xcbl;
	
	//现场检查记录
	@Autowired
	private SiteCheckRecordService siteCheckRecordService;
	private SiteCheckRecord siteCheckRecord = new SiteCheckRecord();
	@Autowired
	private InstrumentsInfoService instrumentsInfoService;
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	String roleName = "";
	
	private String userType="";//1:安委会办公室（角色），2：安监中队队员（角色），3：安监中队队长（角色），4：监察大队队长（角色），5：安监局局领导（角色）,6:职能部门（部门）
	
	private String wxyType;
	private int pageNo;
	
	private int pageSize;
	
	private String userId;
	
	private String starttime;
	private String endtime;
	
	private String tongjiType="dept";;
	
	private String deptId;
	
	private String searchLike;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String init(){
		ids=this.getLoginUser().getId();
		userType=this.getUserTypeByLoginUser();
		String rut="";
		if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A23")){
			roleName="0";
			rut =  "qy";
		}else if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A10")){
			roleName="1";
			rut =  "aj";
		}else{
			if("qy_troman".equals(flag)){//非企业角色查看企业的关联隐患
				rut =  "qy";
			}else{
				rut =  "aj";
			}
			
		}
		deptId = this.getLoginUserDepartment().getDeptCode();
		return rut;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != troMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != troMan.getTroubleName()) && (0 < troMan.getTroubleName().trim().length())){
				paraMap.put("troubleName", "%" + troMan.getTroubleName().trim() + "%");
			}

			if ((null != troMan.getTroubleSource()) && (0 < troMan.getTroubleSource().trim().length())){
				paraMap.put("troubleSource", "%" + troMan.getTroubleSource().trim() + "%");
			}

			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				paraMap.put("areaId", troMan.getAreaId().trim());
			}

			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
			}
			if ((null != troMan.getCompanyId()) && (0 < troMan.getCompanyId().trim().length())){
				paraMap.put("companyId", troMan.getCompanyId().trim() );
			}
			if ((null != troMan.getUserName()) && (0 < troMan.getUserName().trim().length())){
				paraMap.put("userName", "%" + troMan.getUserName().trim() + "%");
			}

			if ((null != troMan.getTroubleLevel()) && (0 < troMan.getTroubleLevel().trim().length())){
				paraMap.put("troubleLevel", troMan.getTroubleLevel().trim());
			}

			if ((null != troMan.getTroubleSort()) && (0 < troMan.getTroubleSort().trim().length())){
				paraMap.put("troubleSort", troMan.getTroubleSort().trim());
			}

			if ((null != troMan.getRectificationState()) && (0 < troMan.getRectificationState().trim().length())){
				if("待审核".equals(troMan.getRectificationState().trim())){
					List dsh=new ArrayList();
					dsh.add("2");
					dsh.add("3");
					dsh.add("5");
					dsh.add("7");
					dsh.add("20");
					dsh.add("21");
					paraMap.put("dsh", dsh);
					
					
					
				}else if("待整改".equals(troMan.getRectificationState().trim())){
					List dzg=new ArrayList();
					dzg.add("11");
					paraMap.put("dzg", dzg);
				}else if("审核未通过".equals(troMan.getRectificationState().trim())){
					List dzg=new ArrayList();
					dzg.add("1");
					paraMap.put("dzg", dzg);
				}else if("整改未完成".equals(troMan.getRectificationState().trim())){
					paraMap.put("zgwwc", "zgwwc");
				}else{//整改完成
					paraMap.put("zgwc", "zgwc");
				}
				
				
			}
			if ((null != troMan.getTaskId()) && (0 < troMan.getTaskId().trim().length())){
				paraMap.put("taskId", troMan.getTaskId().trim());
			}

		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("troubleLevel","402880084196a4a301419759d52a024b");
		codeMap.put("troubleSort","402880084196a4a301419759b28e0249");
		//codeMap.put("rectificationState","402880084196a4a30141975da934025d");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|troubleName|troubleSource|areaId|companyName|userName|troubleLevel|troubleSort|rectificationState|createUserID|auditResult|ifCorrected|userId|ifReportAwh|dealState|dealDeptId|ifRedirect|areaName|ifla|";
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
		//企业只能看到自己的 其他角色看到所有
		//判断登录人的角色 
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		for(UserRight ur:list)
		{
			//登录人为企业 只可以查看自己添加信息
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				Map map = new HashMap();
				map.put("loginId", this.getLoginUser().getLoginId());
				EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
				paraMap.put("companyId", entBaseInfo.getId());
				break;
			}
		}
		pagination = troManService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		if((null != troMan)&&(null != troMan.getId())){
			troMan = troManService.getById(troMan.getId());
			if(troMan.getLinkId() == null || "".equals(troMan.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				troMan.setLinkId(linkId);
			}
			else
			{
				try {
					Map map = new HashMap();
					map.put("linkId",troMan.getLinkId());
					map.put("mkType", "troMan");
					map.put("picType","troManfj");
					picList1 = photoPicService.findPicPath(map);//获取整改前图片
					map.put("picType","troManRect");
					
					//此处进行调整 整改的信息为列表展示
					map.put("yhbId", troMan.getId());
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
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			troMan.setLinkId(linkId);
		}
		
		
//		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "0";
		if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A23")){
			roleName = "1";
		}
//		for(UserRight ur:list)
//		{
//			//登录人为企业
//			if(ur.getRole().getRoleCode().equals("A23")) 
//			{
//				roleName = "1";
//				break;
//			}
//		}
		if("1".equals(roleName)){
			//根据登录角色设置隐患来源
			troMan.setTroubleSource("企业");
			//设置企业的区域和名称
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			troMan.setAreaId(enBaseInfo.getEnterprisePossession());
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			troMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			troMan.setCompanyId(enBaseInfo.getId());
			troMan.setCompanyName(enBaseInfo.getEnterpriseName());
		}else{
			//根据登录角色设置隐患来源
			troMan.setTroubleSource("安监");
		}
		//审核记录
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("infoId", troMan.getId());
		checkRecords=checkRecordService.findCheckRecord(paraMap);
		
		if(!troMan.getTaskId().equals("")&&troMan.getTaskId()!=null){
			DanTasMan danTasMan=danTasManService.getById(troMan.getTaskId());
			if(danTasMan.getCheckKind().equals("日常巡查")){
			   wxyType="0";
			}else if(danTasMan.getCheckKind().equals("危险源巡查")){
				wxyType="1";
			}
		}
		
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
		if("1".equals(troMan.getIfReportAwh())){
			troMan.setRectificationState("3");
		}else{
			troMan.setRectificationState("11");//待整改
		}
		if ("add".equalsIgnoreCase(this.flag)){
			//设置上报人
			troMan.setUserId(this.getLoginUser().getId());
			troMan.setUserName(this.getLoginUser().getDisplayName());
		
			troMan.setDeptId(this.getLoginUserDepartmentId());
			troMan.setDelFlag(0);
			troMan.setIfCorrected("0");
			
			
			troManService.save(troMan);
			
			SendSms sendSms =new SendSms();
			String result = sendSms.send(troMan.getRectTel(),"您有一个待整改隐患:"+troMan.getTroubleName()+",请尽快整改！");
			System.out.print(result);
		}else{
			try {
				
				troManService.update(troMan);
				if("11".equals(troMan.getRectificationState())){
					pushInfoComp(troMan.getId(),troMan.getCreateUserID(),troMan.getTroubleName());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return RELOAD;
	}
	
	private void pushInfoComp(String id,String userId,String title){
		   try {
					// 信息推送
					Map send = new HashMap();

					String[] userIds = new String[]{userId};
					send.put("type", "11");
					JSONObject json = new JSONObject();
					json.put("id", id);
					send.put("content",json.toString());
						//信息推送
					JPushClient jpush = new JPushClient();
					jpush.sendAndroidNotificationWithAlias("园区安监","待整改的隐患："+title, send,userIds);
				
				} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	
	/**
	 * 跳转到上传整改信息界面
	 * lj 
	 * 2015-10-20
	 */
	public String uploadRect() throws Exception{
		view();
		linkId = java.util.UUID.randomUUID().toString().replace("-", "");
		return EDIT;
	}
	/**
	 * 保存整改信息
	 * 修改于2015-11-07 整改信息单独存储在一张表中
	 * lj 2015-10-20
	 */
	@SuppressWarnings("unchecked")
	public String rectSave() throws Exception{
		//获取整改信息
		Date recfinishTime = troMan.getRecfinishTime();//整改完成时间
		String rectificationMoney = troMan.getRectificationMoney();//整改资金
		String rectificationState = troMan.getRectificationState();//整改状态
		String rectRemark = troMan.getRectRemark();
		if((null != troMan)&&(null != troMan.getId())){
			TroMan newTroMan = troManService.getById(troMan.getId());
			//newTroMan.setRecfinishTime(recfinishTime);
			//newTroMan.setRectificationMoney(rectificationMoney);
			//newTroMan.setRectificationState(rectificationState);
			newTroMan.setRectificationState("0");//企业上传整改后 任务结束
			newTroMan.setDealState(troMan.getDealState());
			newTroMan.setIfCorrected("1");
			newTroMan.setRectRemark(rectRemark);
			troManService.update(newTroMan);
		}
		
		Map map = new HashMap();
		map.put("money", rectificationMoney);
		map.put("rectTime", sdfFormat.format(recfinishTime));
		map.put("state",troMan.getDealState());//企业上报整改信息 默认整改完成 lj 2015-12-07
		map.put("userId", this.getLoginUser().getId());
		map.put("insertTime", sdf.format(new Date()));
		map.put("yhbId", troMan.getId());
		map.put("linkId", linkId);
		map.put("remark", rectRemark);
		
		troManService.saveRectInfo(map);
		
		
		
		
		return RELOAD;
	}
	/**
	 * 跳转到审核界面
	 * lj 2015-10-20
	 */
	public String audit() throws Exception{
		view();
		return EDIT;
	}
	/**
	 * 保存审核界面信息
	 * lj 
	 * 2015-10-20
	 */
	public String  auditSave() throws Exception{
		//获取审核信息
		String auditResult  = troMan.getAuditResult();//审核结果
		String remark = troMan.getRemark();//审核备注
		if((null != troMan)&&(null != troMan.getId())){
			TroMan newTroMan = troManService.getById(troMan.getId());
			newTroMan.setAuditResult(auditResult);
			newTroMan.setRemark(remark);
			if("审核通过".equals(auditResult)){
				newTroMan.setRectificationState("0");//整改完成
			}else{
				newTroMan.setRectificationState("11");//待整改
			}
			
			troManService.update(newTroMan);
		}
		return RELOAD;
		
	}
	/**
	 * 
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != troMan)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到troMan中去
				
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
			troManService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	//1:安委会办公室（角色），2：安监中队队员（网格管理员角色），3：安监中队队长（角色），4：监察大队队长（角色），5：安监局局领导（角色）,6:职能部门（部门）
	/**
	 * 判断登录人角色
	 * 费谦
	 * 2015-10-30
	 */
	public String getUserTypeByLoginUser(){
		String userId=getLoginUser().getId();
		if(httpService.judgeRoleCode(userId, "A17")){
			return "1";
		}
		if(httpService.judgeRoleCode(userId, "A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
			return "2";
		}
		if(httpService.judgeRoleCode(userId, "A11")){
			return "3";
		}
		if(httpService.judgeRoleCode(userId, "A09")){
			return "4";
		}
		if(httpService.judgeRoleCode(userId, "A02")){
			return "5";
		}
		String deptCode =this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith("002")&&deptCode.length()==6&&!"002001".equals(deptCode)){
			return "6";
		}
		
		return "7";//其他（只能查看）
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 * FQ 2010-10-30
	 */
	@SuppressWarnings("unchecked")
	public void listAJ() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
	//	paraMap.put("aj", "aj");//只查询非企业上报的隐患
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != troMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != troMan.getTroubleName()) && (0 < troMan.getTroubleName().trim().length())){
				paraMap.put("troubleName", "%" + troMan.getTroubleName().trim() + "%");
			}

			if ((null != troMan.getTroubleSource()) && (0 < troMan.getTroubleSource().trim().length())){
				paraMap.put("troubleSource", "%" + troMan.getTroubleSource().trim() + "%");
			}

			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				paraMap.put("areaId",  troMan.getAreaId().trim() );
			}

			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
			}

			if ((null != troMan.getUserName()) && (0 < troMan.getUserName().trim().length())){
				paraMap.put("userName", "%" + troMan.getUserName().trim() + "%");
			}

			if ((null != troMan.getTroubleLevel()) && (0 < troMan.getTroubleLevel().trim().length())){
				paraMap.put("troubleLevel", troMan.getTroubleLevel().trim());
			}

			if ((null != troMan.getTroubleSort()) && (0 < troMan.getTroubleSort().trim().length())){
				paraMap.put("troubleSort", troMan.getTroubleSort().trim());
			}

			if ((null != troMan.getRectificationState()) && (0 < troMan.getRectificationState().trim().length())){
				if("待审核".equals(troMan.getRectificationState().trim())){
					List dsh=new ArrayList();
					if("1".equals(getUserTypeByLoginUser())){
						dsh.add("21");
					}else if("2".equals(getUserTypeByLoginUser())){
						dsh.add("3");
					}else if("3".equals(getUserTypeByLoginUser())){
						dsh.add("2");
						dsh.add("20");
					}else if("4".equals(getUserTypeByLoginUser())){
						dsh.add("5");
					}else if("5".equals(getUserTypeByLoginUser())){
						dsh.add("7");
					}else{
						dsh.add("2");
						dsh.add("3");
						dsh.add("5");
						dsh.add("7");
						dsh.add("20");
						dsh.add("21");
					}
					paraMap.put("dsh", dsh);
					
					
					
				}else if("待整改".equals(troMan.getRectificationState().trim())){
					
					List dzg=new ArrayList();
					if("6".equals(getUserTypeByLoginUser())){
						dzg.add("4");
					}else if("2".equals(getUserTypeByLoginUser())){
						dzg.add("6");
					}else{
						dzg.add("4");
						dzg.add("6");
						dzg.add("11");
					}
					paraMap.put("dzg", dzg);
				}else if("审核未通过".equals(troMan.getRectificationState().trim())){
					List dzg=new ArrayList();
					dzg.add("1");
					paraMap.put("dzg", dzg);
				}else if("整改未完成".equals(troMan.getRectificationState().trim())){
					paraMap.put("zgwwc", "zgwwc");
				}else{//整改完成
					paraMap.put("zgwc", "zgwc");
				}
				
				
			}
			if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A11")){//中队长
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
				paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				} 
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}
			if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A12")){//中队队员
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
				paraMapEnt.put("userId",this.getLoginUser().getId());
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}

		}
		//1:安委会办公室（角色），2：网格监管员（角色），3：安监中队队长（角色），4：监察大队队长（角色），5：安监局局领导（角色）
		//根据登录人的部门或角色设置查询条件
//		userType=this.getUserTypeByLoginUser();
//		if("1".equals(userType)){
//			paraMap.put("ifReportAwh", "1");//查询上报安委会的隐患
//		}else if("2".equals(userType)){
//			//TODO
//		}else if("3".equals(userType)){
//			//TODO
//		}else if("4".equals(userType)){
//			//TODO
//		}else if("5".equals(userType)){
//			//TODO
//		}else if("6".equals(userType)){
//			paraMap.put("deptCode", this.getLoginUserDepartment().getDeptCode());//查询上报安委会的隐患
//		}
		
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("troubleLevel","402880084196a4a301419759d52a024b");
		codeMap.put("troubleSort","402880084196a4a301419759b28e0249");
		//codeMap.put("rectificationState","402880084196a4a30141975da934025d");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|troubleName|troubleSource|areaId|companyName|userName|troubleLevel|troubleSort|rectificationState|createUserID|auditResult|ifCorrected|userId|ifReportAwh|dealState|dealDeptId|ifRedirect|areaName|ifla|";
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
			
		try {
			pagination = troManService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 * FQ 2010-10-30
	 */
	public String viewAJ() throws Exception{
		userType=this.getUserTypeByLoginUser();
		if((null != troMan)&&(null != troMan.getId())){
			troMan = troManService.getById(troMan.getId());
			if(troMan.getLinkId() == null || "".equals(troMan.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				troMan.setLinkId(linkId);
			}
			else
			{
				try {
					Map map = new HashMap();
					map.put("linkId",troMan.getLinkId());
					map.put("mkType", "troMan");
					map.put("picType","troManfj");
					picList1 = photoPicService.findPicPath(map);//获取整改前图片
					map.put("picType","troManRect");//新增 设置整改后的类型 
					//此处进行调整 整改的信息为列表展示
					map.put("yhbId", troMan.getId());
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
					
//					map.put("picType","zghtpfj");
//					picList2 = photoPicService.findPicPath(map);//获取整改后图片
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//审核记录
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("infoId", troMan.getId());
			checkRecords=checkRecordService.findCheckRecord(paraMap);
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			troMan.setLinkId(linkId);
		}
		
		
		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
		roleName = "0";
		for(UserRight ur:list)
		{
			//登录人为企业
			if(ur.getRole().getRoleCode().equals("A23")) 
			{
				roleName = "1";
				break;
			}
		}
		if("1".equals(roleName)){
			//根据登录角色设置隐患来源
			troMan.setTroubleSource("企业");
			//设置企业的区域和名称
			Map map = new HashMap();
			map.put("loginId", this.getLoginUser().getLoginId());
			EntBaseInfo enBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
			troMan.setAreaId(enBaseInfo.getEnterprisePossession());
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", enBaseInfo.getEnterprisePossession());
			troMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			troMan.setCompanyId(enBaseInfo.getId());
			troMan.setCompanyName(enBaseInfo.getEnterpriseName());
		}else{
			//根据登录角色设置隐患来源
			if(null==troMan.getTroubleSource()||"".equals(troMan.getTroubleSource())){
				troMan.setTroubleSource("安监");
			}
		}
		
		
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 * FQ 2010-10-30
	 */
	public String initEditAJ() throws Exception{
		viewAJ();
		if("add".equals(flag)){
			return EDIT;
		}else if("cxxg".equals(flag)){
			return EDIT;
		}
		else{
			troMan=troManService.getById(troMan.getId());
			if("1".equals(troMan.getRectificationState())||"2".equals(troMan.getRectificationState())||("3".equals(troMan.getRectificationState())&&!"1".equals(userType))){//安委会办公室
				return EDIT;
			}else{
				if("1".equals(userType)){
					if(troMan.getCreateUserID().equals(this.getLoginUser().getId())&&"1".equals(troMan.getRectificationState())){
						return "edit0";
					}else{
						return "edit1";
					}
				}else if("2".equals(userType)){//网格监管人员
					linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					return "edit2";
				}else if("3".equals(userType)){
					return "edit3";
				}else if("4".equals(userType)){
					return "edit4";
				}else if("5".equals(userType)){
					return "edit5";
				}else if("6".equals(userType)){//职能部门
					linkId = java.util.UUID.randomUUID().toString().replace("-", "");
					return "edit6";
				}else {
					return EDIT;
				}
			}
		}
	}

	
	/**
	 * 保存上报信息（包括新增和修改）
	 * FQ 2010-10-30
	 */
	public String saveBaseAJ() throws Exception{
		userType=this.getUserTypeByLoginUser();
		if("1".equals(troMan.getIfReportAwh())){
			troMan.setRectificationState("20");//中队长审核
		}else if("0".equals(troMan.getIfReportAwh())){
			troMan.setDealDeptId("002001");
			if("1".equals(troMan.getTroubleLevel())){//一般隐患 直接整改 不需审核
				troMan.setRectificationState("6");
			}else{
				troMan.setRectificationState("2");//不上报安委会 需中队长审核
			}
		}else {
			troMan.setRectificationState("1");//待整改
		}
		if(troMan.getAreaId() != null && !"".equals(troMan.getAreaId()))
		{
			Map m = new HashMap();
			m.put("codeName", "企业属地");
			m.put("itemValue", troMan.getAreaId());
			troMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		}
		if ("add".equalsIgnoreCase(this.flag)){
			//设置上报人
			troMan.setUserId(this.getLoginUser().getId());
			troMan.setUserName(this.getLoginUser().getDisplayName());
		
			troMan.setIfCorrected("0");//未整改
			troMan.setDeptId(this.getLoginUserDepartmentId());
			troMan.setDelFlag(0);
			troManService.save(troMan);
			
			SendSms sendSms =new SendSms();
			String result = sendSms.send(troMan.getRectTel(),"您有一个待整改隐患:"+troMan.getTroubleName()+",请尽快整改！");
			System.out.print(result);
		}else{
			troManService.update(troMan);
		}
		
		return RELOAD;
	}
	
	/**
	 * 保存信息（包括新增和修改还有整改）
	 * FQ 2010-10-30
	 */
	
	public String saveAJ() throws Exception{
		userType=this.getUserTypeByLoginUser();
		if("1".equals(troMan.getIfReportAwh())){
			troMan.setRectificationState("3");//上报安委会
		}else if("0".equals(troMan.getIfReportAwh())){
			if("1".equals(troMan.getTroubleLevel())){//一般隐患 直接整改 不需审核
				troMan.setRectificationState("6");
			}else{
				troMan.setRectificationState("2");//不上报安委会 需中队长审核
			}
		}else {
			troMan.setRectificationState("1");//待整改
		}
			if("1".equals(userType)){//安委会办公室 保存
				String ifNeedAj=troMan.getIfNeedAj();//1:是（需要），0：否（不需要）
				String dealDeptId=troMan.getDealDeptId();
				troMan=troManService.getById(troMan.getId());
				troMan.setIfNeedAj(ifNeedAj);
				if("0".equals(ifNeedAj)){//不需要安监处理
					troMan.setDealDeptId(dealDeptId);
					troMan.setRectificationState("4");
				}else{//需要安监处理
					if("1".equals(troMan.getTroubleLevel())){//一般隐患 直接整改 不需审核
						troMan.setRectificationState("6");
					}else{
						troMan.setRectificationState("2");//不上报安委会 需中队长审核
					}
					troMan.setDealDeptId(dealDeptId);
				}
			}else if("2".equals(userType)){//网格监管人员上报
				//TODO
				Date finishDate=troMan.getRecfinishTime();
				String money=troMan.getRectificationMoney();
				String dealState=troMan.getDealState();
				String rectRemark=troMan.getRectRemark();
				troMan=troManService.getById(troMan.getId());
				troMan.setUserId(this.getLoginUser().getId());//保存整改信息上报人id
				troMan.setDealState(dealState);
				troMan.setRectRemark(rectRemark);
				if("整改未完成".equals(dealState)){
					troMan.setRecfinishTime(null);
					troMan.setRectificationMoney(null);
				}else{
					troMan.setRecfinishTime(finishDate);
					troMan.setRectificationMoney(money);
				}
				
//				troMan.setDealState(dealState);
				troMan.setIfCorrected("1");//已整改
				if("1".equals(troMan.getTroubleLevel())){
					troMan.setRectificationState("2");//网格监管人员上报后需中队审核，返回到状态2
				}else if("2".equals(troMan.getTroubleLevel())){
					troMan.setRectificationState("5");//网格监管人员上报后需大队审核，返回到状态5
				}else{
					troMan.setRectificationState("7");//网格监管人员上报后需局领导审核，返回到状态7
				}
				Map map = new HashMap();
				map.put("money", money);
				map.put("rectTime", sdfFormat.format(finishDate));
				map.put("state",dealState);
				map.put("userId", this.getLoginUser().getId());
				map.put("insertTime", sdf.format(new Date()));
				map.put("yhbId", troMan.getId());
				map.put("linkId", linkId);
				map.put("remark", rectRemark);
				
				troManService.saveRectInfo(map);
			}else if("3".equals(userType)){
				//TODO
			}else if("4".equals(userType)){
				//TODO
			}else if("5".equals(userType)){
				//TODO
			}else if("6".equals(userType)){
				//TODO
				String dealState=troMan.getDealState();
				Date finishDate=troMan.getRecfinishTime();
				String money=troMan.getRectificationMoney();
				String rectRemark=troMan.getRectRemark();
				troMan=troManService.getById(troMan.getId());
				troMan.setRectRemark(rectRemark);
				troMan.setUserId(this.getLoginUser().getId());//保存整改信息上报人id
				troMan.setRecfinishTime(finishDate);
				troMan.setRectificationMoney(money);
				troMan.setDealState(dealState);
				troMan.setIfCorrected("1");//已整改
				troMan.setRectificationState("0");//职能部门上报后流程结束
				
				Map map = new HashMap();
				map.put("money", money);
				map.put("rectTime", sdfFormat.format(finishDate));
				map.put("state",dealState);
				map.put("userId", this.getLoginUser().getId());
				map.put("insertTime", sdf.format(new Date()));
				map.put("yhbId", troMan.getId());
				map.put("linkId", linkId);
				map.put("remark", rectRemark);
				
				troManService.saveRectInfo(map);
			}else {
				//TODO
			}
			
			if(troMan.getAreaId() != null && !"".equals(troMan.getAreaId()))
			{
				Map m = new HashMap();
				m.put("codeName", "企业属地");
				m.put("itemValue", troMan.getAreaId());
				troMan.setAreaName(codeService.findCodeValueByMap(m).getItemText());
			}
			troManService.update(troMan);
		
			if("6".equals(troMan.getRectificationState())){//需要中队队员进行整改
				pushInfo(troMan.getId(),troMan.getUserId(),troMan.getTroubleName());
			}
			
			return RELOAD;
	}
	/**
	 * 跳转到上传整改信息界面
	 * FQ 2010-10-30
	 */
	public String uploadRectAJ() throws Exception{
		viewAJ();
		return EDIT;
	}
	/**
	 * 保存整改信息
	 * FQ 2010-10-30
	 */
	public String rectSaveAJ() throws Exception{
		userType=this.getUserTypeByLoginUser();
		//获取整改信息
		Date recfinishTime = troMan.getRecfinishTime();//整改完成时间
		String rectificationMoney = troMan.getRectificationMoney();//整改资金
		String rectificationState = troMan.getRectificationState();//整改状态
		if((null != troMan)&&(null != troMan.getId())){
			TroMan newTroMan = troManService.getById(troMan.getId());
			newTroMan.setRecfinishTime(recfinishTime);
			newTroMan.setRectificationMoney(rectificationMoney);
			newTroMan.setRectificationState(rectificationState);
			newTroMan.setRectificationState("2");//已整改待审核
			troManService.update(newTroMan);
		}
		
		return RELOAD;
	}
	/**
	 * 跳转到审核界面
	 * FQ 2010-10-30
	 */
	public String auditAJ() throws Exception{
		viewAJ();
		return EDIT;
	}
	/**
	 * 保存审核界面信息
	 * FQ 2010-10-30
	 * 
	 */
	public String  auditSaveAJ() throws Exception{
		userType=this.getUserTypeByLoginUser();
		String dealDept=troMan.getDealDeptId();
		String ifReply=troMan.getIfReply();
		String need = "1".equals(ifReply)?",需要回复":",不需要回复";
		troMan=troManService.getById(troMan.getId());
		if("1".equals(userType)){//安委会办公室审核 状态21 转接审核
			if("审核未通过".equals(checkRecord.getCheckResult())){
				troMan.setRectificationState("1");//不通过，返回上报人修改隐患信息
				troMan.setAuditResult("审核未通过");
			}else{
				if("1".equals(troMan.getIfReply())){
					troMan.setRectificationState("4");
				}else{
					troMan.setRectificationState("0");//待整改，但是暂时没人管了
					troMan.setDealState("整改完成");
				}
			}
		}else if("2".equals(userType)){//安监中队队员 3 是否上报安委会审核
			if("审核未通过".equals(checkRecord.getCheckResult())){
				troMan.setRectificationState("1");//不通过，返回上报人修改整改信息
				troMan.setAuditResult("审核未通过");
			}else{
				troMan.setRectificationState("20");//待中队长审核
				troMan.setAuditResult("审核通过");
			}
		}else if("3".equals(userType)){//安监中队队长审核
			if("20".equals(troMan.getRectificationState())){//转接审核
				if("审核未通过".equals(checkRecord.getCheckResult())){
					troMan.setRectificationState("1");//不通过，返回上报人修改整改信息
					troMan.setAuditResult("审核未通过");
				}else{
					troMan.setRectificationState("21");//待安委会审核
					troMan.setAuditResult("审核通过");
					troMan.setDealDeptId(dealDept);
					troMan.setIfReply(ifReply);
					String r="审核通过:"+deptService.findDeptByDeptCode(dealDept).getDeptName()+need;
					checkRecord.setCheckResult(r);
				}
			}else{
				if("审核未通过".equals(checkRecord.getCheckResult())){
					if("0".equals(troMan.getIfCorrected())){
						troMan.setRectificationState("1");//不通过，返回上报人修改隐患信息
						troMan.setAuditResult("审核未通过");
					}else{
						troMan.setRectificationState("6");//不通过，返回上报人修改整改信息
						troMan.setAuditResult("整改未通过");
					}
				}else{//审核通过
					if("1".equals(troMan.getTroubleLevel())){
						if("0".equals(troMan.getIfCorrected())){//一般隐患 未整改
							troMan.setRectificationState("6");
							troMan.setAuditResult("审核通过");
						}else{
							troMan.setRectificationState("0");
							if("整改未完成".equals(troMan.getDealState())){
								troMan.setAuditResult("整改未完成");
							}else{
								troMan.setAuditResult("整改通过");
							}
						}
					}else{//不是一般隐患
						troMan.setRectificationState("5");
					}
					
					if("0".equals(troMan.getIfCorrected())){//未整改
						troMan.setAuditResult("审核通过");
					}else{
						troMan.setAuditResult("整改通过");
					}
				}
			}
		}else if("4".equals(userType)){//监察大队队长审核
			if("审核未通过".equals(checkRecord.getCheckResult())){
				if("0".equals(troMan.getIfCorrected())){//未整改
					troMan.setAuditResult("审核通过");
					troMan.setTroubleLevel("1");//设置隐患级别为一般
				}else{
					troMan.setAuditResult("整改未通过");
				}
				troMan.setRectificationState("6");//审核不过，给网格监管员整改
			}else{//审核通过
				if("0".equals(troMan.getIfCorrected())){//未整改
					troMan.setAuditResult("审核通过");
					if("2".equals(troMan.getTroubleLevel())){
						if("0".equals(troMan.getIfCorrected())){//重大隐患 未整改
							troMan.setRectificationState("6");
						}else{//重大隐患 已整改
							troMan.setRectificationState("0");
						}
					}else{//特别重大隐患
						troMan.setRectificationState("7");//特别重大隐患，需领导审核
					}
				}else{
					if("整改未完成".equals(troMan.getDealState())){
						troMan.setAuditResult("整改未完成");
					}else{
						troMan.setAuditResult("整改通过");
					}
					troMan.setRectificationState("0");
				}
			}
		}else if("5".equals(userType)){//局领导审核
			if("审核未通过".equals(checkRecord.getCheckResult())){
				if("0".equals(troMan.getIfCorrected())){//未整改
					troMan.setAuditResult("审核未通过");
					troMan.setTroubleLevel("2");
				}else{
					troMan.setAuditResult("整改未通过");
				}
				troMan.setRectificationState("6");//审核不过，给网格监管员整改
			}else{//审核通过
				if("0".equals(troMan.getIfCorrected())){//未整改
					troMan.setAuditResult("审核通过");
					troMan.setRectificationState("6");//给网格监管员整改
				}else{
					if("整改未完成".equals(troMan.getDealState())){
						troMan.setAuditResult("整改未完成");
					}else{
						troMan.setAuditResult("整改通过");
					}
					troMan.setRectificationState("0");
				}
			}
		}
		
		
		troManService.update(troMan);
		checkRecord.setCheckUserid(this.getLoginUser().getId());
		checkRecord.setDelFlag(0);
		checkRecord.setCheckUsername(this.getLoginUser().getDisplayName());
		checkRecordService.save(checkRecord);
		
		if("6".equals(troMan.getRectificationState())){//需要中队队员进行整改
			pushInfo(troMan.getId(),troMan.getUserId(),troMan.getTroubleName());
		}
		
		return RELOAD;
		
	}
	private void pushInfo(String id,String userId,String title){
		   try {
					// 信息推送
					Map send = new HashMap();

					String[] userIds = new String[]{userId};
					send.put("type", "8");
					JSONObject json = new JSONObject();
					json.put("id", id);
					send.put("content",json.toString());
						//信息推送
					JPushClient jpush = new JPushClient();
					jpush.sendAndroidNotificationWithAlias("园区安监","待整改的隐患："+title, send,userIds);
				
				} catch (Exception e) {
				e.printStackTrace();
			}
	   }
	
	@SuppressWarnings("unchecked")
	public void exportXls() {
		try {
			Map<String, Object> paraMap =getSearchParamMap();
			paraMap.put("sqlId", "findExportData");
			List<Map<String, Object>> troManList=httpService.findListDataByMap(paraMap);
			
			HttpServletResponse res=ServletActionContext.getResponse();
			res.setContentType("application/vnd.ms-excel") ;//excel的MIME类型
			String fileName="隐患列表.xls";
			res.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet("隐患列表");
			sheet.setColumnWidth(0, 4000);
			sheet.setColumnWidth(1, 4000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 4000);
			sheet.setColumnWidth(5, 4000);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 5000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 4000);
			sheet.setColumnWidth(11, 4000);
			sheet.setColumnWidth(12, 4000);
			sheet.setColumnWidth(13, 4000);
			sheet.setColumnWidth(14, 6000);
			sheet.setColumnWidth(15, 4000);
			sheet.setColumnWidth(16, 4000);
			sheet.setColumnWidth(17, 30000);
			sheet.setColumnWidth(18, 4000);
			sheet.setColumnWidth(19, 4000);
			sheet.setColumnWidth(20, 4000);
			sheet.setColumnWidth(21, 4000);
			sheet.setColumnWidth(22, 30000);
			sheet.setColumnWidth(23, 4000);
			
			CellStyle style = wb.createCellStyle();
			HSSFFont font = wb.createFont();
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//粗体显示
			style.setFont(font);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			
			HSSFRow row = sheet.createRow(0); 
			row.setHeight((short)500);
			HSSFCell numberCell = row.createCell(0);  
			numberCell.setCellValue("所在区域");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(1);
			numberCell.setCellValue("企业名称");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(2);
			numberCell.setCellValue("隐患名称 ");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(3);
			numberCell.setCellValue("隐患来源 ");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(4);
			numberCell.setCellValue("上报人员名称");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(5);
			numberCell.setCellValue("上报时间");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(6);
			numberCell.setCellValue("隐患地点");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(7);
			numberCell.setCellValue("处理职能部门名称");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(8);
			numberCell.setCellValue("隐患级别");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(9);
			numberCell.setCellValue("隐患类别");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(10);
			numberCell.setCellValue("检查项");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(11);
			numberCell.setCellValue("整改期限");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(12);
			numberCell.setCellValue("整改责任部门");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(13);
			numberCell.setCellValue("整改责任人");
			numberCell.setCellStyle(style);
			
			
			numberCell = row.createCell(14);
			numberCell.setCellValue("整改责任人联系方式");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(15);
			numberCell.setCellValue("隐患详情");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(16);
			numberCell.setCellValue("整改状态 ");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(17);
			numberCell.setCellValue("整改前图片");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(18);
			numberCell.setCellValue("整改时间");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(19);
			numberCell.setCellValue("整改资金（元）");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(20);
			numberCell.setCellValue("整改结果");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(21);
			numberCell.setCellValue("备注");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(22);
			numberCell.setCellValue("整改图片");
			numberCell.setCellStyle(style);
			
			numberCell = row.createCell(23);
			numberCell.setCellValue("审核记录");
			numberCell.setCellStyle(style);
			
			
			CellStyle csStyle = wb.createCellStyle();
			csStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			csStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			csStyle.setWrapText(true);
			Map<String, Object> map=new HashMap<String, Object>();
			for (int i = 0; i < troManList.size(); i++) {
					map = troManList.get(i);
					row = sheet.createRow(i+1); 
					row.setHeight((short)1500);				
					numberCell = row.createCell(0);  
					numberCell.setCellValue(map.get("areaName")==null?"":map.get("areaName").toString());
					numberCell.setCellStyle(csStyle);
									
					numberCell = row.createCell(1);
					numberCell.setCellValue(map.get("COMPANY_NAME")==null?"":map.get("COMPANY_NAME").toString());
					numberCell.setCellStyle(csStyle);
									
					numberCell = row.createCell(2);
					numberCell.setCellValue(map.get("TROUBLE_NAME")==null?"":map.get("TROUBLE_NAME").toString());
					numberCell.setCellStyle(csStyle);
									
					numberCell = row.createCell(3);
					numberCell.setCellValue(map.get("TROUBLE_SOURCE")==null?"":map.get("TROUBLE_SOURCE").toString());
					numberCell.setCellStyle(csStyle);
									
					numberCell = row.createCell(4);
					numberCell.setCellValue(map.get("USER_NAME")==null?"":map.get("USER_NAME").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(5);
					numberCell.setCellValue(map.get("REPORT_TIME")==null?"":sdfFormat.format(sdfFormat.parse(map.get("REPORT_TIME").toString())));
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(6);
					numberCell.setCellValue(map.get("TROUBLE_ADD")==null?"":map.get("TROUBLE_ADD").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(7);
					numberCell.setCellValue(map.get("dealDeptName")==null?"":map.get("dealDeptName").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(8);
					numberCell.setCellValue(map.get("troubleLevel")==null?"":map.get("troubleLevel").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(9);
					numberCell.setCellValue(map.get("troubleSort")==null?"":map.get("troubleSort").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(10);
					numberCell.setCellValue(map.get("checkItemName")==null?"":map.get("checkItemName").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(11);
					numberCell.setCellValue(map.get("RECTIFICATION_TERM")==null?"":sdfFormat.format(sdfFormat.parse(map.get("RECTIFICATION_TERM").toString())));
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(12);
					numberCell.setCellValue(map.get("rectDept")==null?"":map.get("rectDept").toString());
					numberCell.setCellStyle(csStyle);
									
					numberCell = row.createCell(13);
					numberCell.setCellValue(map.get("rectPerson")==null?"":map.get("rectPerson").toString());
					numberCell.setCellStyle(csStyle);
					
					numberCell = row.createCell(14);
					numberCell.setCellValue(map.get("RECT_TEL")==null?"":map.get("RECT_TEL").toString());
					numberCell.setCellStyle(csStyle);
									
					numberCell = row.createCell(15);
					numberCell.setCellValue(map.get("introduce")==null?"":map.get("introduce").toString());
					numberCell.setCellStyle(csStyle);
					
					//整改状态
					String state="";
					String stateNo=map.get("RECTIFICATION_STATE")==null?"":map.get("RECTIFICATION_STATE").toString();
					if("1".equals(stateNo)){
						state= "审核未通过";
					}
					else if("4".equals(stateNo)||"6".equals(stateNo)||"11".equals(stateNo)){
						state= "待整改";
					}
					else if("2".equals(stateNo)||"3".equals(stateNo)||"5".equals(stateNo)||"7".equals(stateNo)||"20".equals(stateNo)||"21".equals(stateNo)){
						state=  "待审核";
					}else{
						if("整改未完成".equals(map.get("DEAL_STATE").toString())){
							state=  "整改未完成";
						}else{
							state=  "整改完成 ";
						}
					}
					numberCell = row.createCell(16);
					numberCell.setCellValue(state);
					numberCell.setCellStyle(csStyle);
					
					HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
					if(map.get("LINK_ID") != null && !"".equals(map.get("LINK_ID").toString())){
						Map picMap = new HashMap();
						picMap.put("linkId",map.get("LINK_ID").toString());
						picMap.put("mkType", "troMan");
						picMap.put("picType","troManfj");
						picList1 = photoPicService.findPicPath(picMap);//获取整改前图片
						int size=picList1.size();
						
						if(size>0){
							try {
								int num = 1000/size;
								if(num > 200)
								{
									num = 200;
								}
								for(int j=0;j<size;j++){
									ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
									String  InputimagePath =picList1.get(j).getHttpUrl()+"/upload/photo/"+picList1.get(j).getPicName();
									URL url = new URL(InputimagePath);
									HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
									InputStream in = conn.getInputStream();
									BufferedImage  bufferImg  =  Thumbnails.of(in).size(150,150).keepAspectRatio(false).asBufferedImage();
									ImageIO.write(bufferImg, "png", byteArrayOut);
									HSSFClientAnchor anchor = new HSSFClientAnchor(j*num, 0,(j+1)*num, 0, (short) 17, i+1,(short)17,i+2);
									anchor.setAnchorType(0);
									// 插入图片
									patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
									
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
							}
							
						}
					}
						
					//整改记录（取最后一次）
					Map rectMap = new HashMap();
					rectMap.put("yhbId", map.get("ROW_ID").toString());
					rectInfos = troManService.queryRectInfosByMap(rectMap);
					int rectSize=rectInfos.size();
					if(rectSize>0){
						numberCell = row.createCell(18);
						try {
							if("".equals(rectInfos.get(rectSize-1).get("rectTime"))){
								numberCell.setCellValue("");
							}else{
								numberCell.setCellValue(sdfFormat.format(sdfFormat.parse(rectInfos.get(rectSize-1).get("rectTime").toString())));
							}
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						numberCell.setCellStyle(csStyle);
						numberCell = row.createCell(19);
						numberCell.setCellValue(rectInfos.get(rectSize-1).get("money").toString());
						numberCell.setCellStyle(csStyle);
						numberCell = row.createCell(20);
						numberCell.setCellValue(rectInfos.get(rectSize-1).get("state").toString());
						numberCell.setCellStyle(csStyle);
						numberCell = row.createCell(21);
						numberCell.setCellValue(rectInfos.get(rectSize-1).get("remark").toString());
						numberCell.setCellStyle(csStyle);
						
						if(rectInfos.get(rectSize-1).get("linkId") != null && !"".equals(rectInfos.get(rectSize-1).get("linkId").toString())){
							Map picRectMap = new HashMap();
							picRectMap.put("linkId",rectInfos.get(rectSize-1).get("linkId").toString());
							picRectMap.put("mkType", "troMan");
							picRectMap.put("picType","troManRect");
							picList2 = photoPicService.findPicPath(picRectMap);//获取整改图片
							int sizeRectPic=picList2.size();
							if(sizeRectPic>0){
								try {
									int num = 1000/sizeRectPic;
									if(num > 200)
									{
										num = 200;
									}
									for(int j=0;j<sizeRectPic;j++){
											ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
											String  InputimagePath =picList2.get(j).getHttpUrl()+"/upload/photo/"+picList2.get(j).getPicName();
											URL url = new URL(InputimagePath);
											HttpURLConnection conn = (HttpURLConnection)url.openConnection();   
											InputStream in = conn.getInputStream();
											BufferedImage  bufferImg  =  Thumbnails.of(in).size(150,150).keepAspectRatio(false).asBufferedImage();
											ImageIO.write(bufferImg, "png", byteArrayOut);
											HSSFClientAnchor anchor = new HSSFClientAnchor(j*num, 0,(j+1)*num, 0, (short) 22, i+1,(short)22,i+2);
											anchor.setAnchorType(0);
											// 插入图片
											patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
											
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
								}
								
							}
						}
					}
					//审核记录
					String cRecords="";
					Map<String, Object> checkMap = new HashMap<String, Object>();
					checkMap.put("infoId",map.get("ROW_ID").toString());
					checkRecords=checkRecordService.findCheckRecord(checkMap);
					for(CheckRecord c:checkRecords){
						cRecords+=sdf.format(c.getCreateTime())+"，"+c.getCheckUsername()+c.getCheckResult()+"["+c.getCheckRemark()+"];";
					}
					
					numberCell = row.createCell(23);
					numberCell.setCellValue(cRecords);
					numberCell.setCellStyle(csStyle);
			}
					
			wb.write(res.getOutputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, Object> getSearchParamMap(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(flag == null || "".equals(flag))
		{
			flag = (String) getSessionAttribute("flag");
			troMan = (TroMan) getSessionAttribute("troMan");
			ids = (String) getSessionAttribute("ids");
		}
		if("qy".equals(flag)){
			setSessionAttribute("flag", flag);
			if(null != troMan){
				setSessionAttribute("troMan", troMan);
				//设置查询条件，开发人员可以在此增加过滤条件
				if ((null != troMan.getTroubleName()) && (0 < troMan.getTroubleName().trim().length())){
					paraMap.put("troubleName", "%" + troMan.getTroubleName().trim() + "%");
				}

				if ((null != troMan.getTroubleSource()) && (0 < troMan.getTroubleSource().trim().length())){
					paraMap.put("troubleSource", "%" + troMan.getTroubleSource().trim() + "%");
				}

				if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
					paraMap.put("areaId", troMan.getAreaId().trim());
				}

				if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
				}
				if ((null != troMan.getCompanyId()) && (0 < troMan.getCompanyId().trim().length())){
					paraMap.put("companyId", troMan.getCompanyId().trim() );
				}
				if ((null != troMan.getUserName()) && (0 < troMan.getUserName().trim().length())){
					paraMap.put("userName", "%" + troMan.getUserName().trim() + "%");
				}

				if ((null != troMan.getTroubleLevel()) && (0 < troMan.getTroubleLevel().trim().length())){
					paraMap.put("troubleLevel", troMan.getTroubleLevel().trim());
				}

				if ((null != troMan.getTroubleSort()) && (0 < troMan.getTroubleSort().trim().length())){
					paraMap.put("troubleSort", troMan.getTroubleSort().trim());
				}

				if ((null != troMan.getRectificationState()) && (0 < troMan.getRectificationState().trim().length())){
					if("待审核".equals(troMan.getRectificationState().trim())){
						List dsh=new ArrayList();
						dsh.add("2");
						dsh.add("3");
						dsh.add("5");
						dsh.add("7");
						dsh.add("20");
						dsh.add("21");
						paraMap.put("dsh", dsh);
						
						
						
					}else if("待整改".equals(troMan.getRectificationState().trim())){
						List dzg=new ArrayList();
						dzg.add("11");
						paraMap.put("dzg", dzg);
					}else if("审核未通过".equals(troMan.getRectificationState().trim())){
						List dzg=new ArrayList();
						dzg.add("1");
						paraMap.put("dzg", dzg);
					}else if("整改未完成".equals(troMan.getRectificationState().trim())){
						paraMap.put("zgwwc", "zgwwc");
					}else{//整改完成
						paraMap.put("zgwc", "zgwc");
					}
					
				}
				if ((null != troMan.getTaskId()) && (0 < troMan.getTaskId().trim().length())){
					paraMap.put("taskId", troMan.getTaskId().trim());
				}
				//企业只能看到自己的 其他角色看到所有
				//判断登录人的角色 
				List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
				for(UserRight ur:list)
				{
					//登录人为企业 只可以查看自己添加信息
					if(ur.getRole().getRoleCode().equals("A23")) 
					{
						Map map = new HashMap();
						map.put("loginId", this.getLoginUser().getLoginId());
						EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
						paraMap.put("companyId", entBaseInfo.getId());
						break;
					}
				}
			}
		}else{
			setSessionAttribute("flag", flag);
			if(null != troMan){
				setSessionAttribute("troMan", troMan);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != troMan.getTroubleName()) && (0 < troMan.getTroubleName().trim().length())){
					paraMap.put("troubleName", "%" + troMan.getTroubleName().trim() + "%");
				}

				if ((null != troMan.getTroubleSource()) && (0 < troMan.getTroubleSource().trim().length())){
					paraMap.put("troubleSource", "%" + troMan.getTroubleSource().trim() + "%");
				}

				if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
					paraMap.put("areaId",  troMan.getAreaId().trim() );
				}

				if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
					paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
				}

				if ((null != troMan.getUserName()) && (0 < troMan.getUserName().trim().length())){
					paraMap.put("userName", "%" + troMan.getUserName().trim() + "%");
				}

				if ((null != troMan.getTroubleLevel()) && (0 < troMan.getTroubleLevel().trim().length())){
					paraMap.put("troubleLevel", troMan.getTroubleLevel().trim());
				}

				if ((null != troMan.getTroubleSort()) && (0 < troMan.getTroubleSort().trim().length())){
					paraMap.put("troubleSort", troMan.getTroubleSort().trim());
				}

				if ((null != troMan.getRectificationState()) && (0 < troMan.getRectificationState().trim().length())){
					if("待审核".equals(troMan.getRectificationState().trim())){
						List dsh=new ArrayList();
						if("1".equals(getUserTypeByLoginUser())){
							dsh.add("21");
						}else if("2".equals(getUserTypeByLoginUser())){
							dsh.add("3");
						}else if("3".equals(getUserTypeByLoginUser())){
							dsh.add("2");
							dsh.add("20");
						}else if("4".equals(getUserTypeByLoginUser())){
							dsh.add("5");
						}else if("5".equals(getUserTypeByLoginUser())){
							dsh.add("7");
						}else{
							dsh.add("2");
							dsh.add("3");
							dsh.add("5");
							dsh.add("7");
							dsh.add("20");
							dsh.add("21");
						}
						paraMap.put("dsh", dsh);
						
						
						
					}else if("待整改".equals(troMan.getRectificationState().trim())){
						
						List dzg=new ArrayList();
						if("6".equals(getUserTypeByLoginUser())){
							dzg.add("4");
						}else if("2".equals(getUserTypeByLoginUser())){
							dzg.add("6");
						}else{
							dzg.add("4");
							dzg.add("6");
							dzg.add("11");
						}
						paraMap.put("dzg", dzg);
					}else if("审核未通过".equals(troMan.getRectificationState().trim())){
						List dzg=new ArrayList();
						dzg.add("1");
						paraMap.put("dzg", dzg);
					}else if("整改未完成".equals(troMan.getRectificationState().trim())){
						paraMap.put("zgwwc", "zgwwc");
					}else{//整改完成
						paraMap.put("zgwc", "zgwc");
					}
					
					
				}
				if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A11")){//中队长
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
					paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					} 
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("companmyIds", companmyIds);
				}
				if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A12")){//中队队员
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
					paraMapEnt.put("userId",this.getLoginUser().getId());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					}
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("companmyIds", companmyIds);
				}

			}
		}
		if(!"".equals(ids)){
			paraMap.put("ids", ids);
			setSessionAttribute("ids", ids);
		}
		
		return paraMap;
		
	}
	
	public String zwtInit(){
//		String url="http://172.25.127.9/services/authorization/validateredirect";
//    	Map<String,String> dataMap = new HashMap<String,String>();
//        dataMap.put("token", "2dbe362a536d4311931a7ea22b4b2095");
//        dataMap.put("appId", "1DBE552B23D440128A12BA5D6ECE72B2");
//        JSONObject j=HttpRequestUtils.httpPost(url,JSONObject.fromObject(dataMap));
//        if("200".equals(j.get("code").toString())){
//        	String loginName=j.get("username").toString();
//        	User u=userService.findUserByLoginId(loginName);
//			setSessionAttribute("curr_user", u);
//        	ids=u.getId();
//        	String deptCode =u.getDeptCode();
//    		if(httpService.judgeRoleCode(ids, "A17")){
//    			userType="1";
//    		}else if(httpService.judgeRoleCode(ids, "A12")){//此处修改为安监中队队员才可以上报整改信息 lj 2015-12-14
//    			userType= "2";
//    		}else if(httpService.judgeRoleCode(ids, "A11")){
//    			userType= "3";
//    		}else if(httpService.judgeRoleCode(ids, "A09")){
//    			userType= "4";
//    		}else if(httpService.judgeRoleCode(ids, "A02")){
//    			userType= "5";
//    		}else if(deptCode.startsWith("002")&&deptCode.length()==6&&!"002001".equals(deptCode)){
//    			userType= "6";
//    		}else{
//    			userType= "7";
//    		}
//        	return SUCCESS;
//        }else{
//        	return ERROR;
//        }
		User user=userService.findUserByLoginId("jcdddz");
		setSessionAttribute("curr_user", user);
		ids=this.getLoginUser().getId();
		userType=this.getUserTypeByLoginUser();
        return SUCCESS;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void zwtList(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		//	paraMap.put("aj", "aj");//只查询非企业上报的隐患
			if(pagination==null)
			    pagination = new Pagination(this.getRequest());
			    
			if(null != troMan){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != troMan.getTroubleName()) && (0 < troMan.getTroubleName().trim().length())){
					paraMap.put("troubleName", "%" + troMan.getTroubleName().trim() + "%");
				}

				if ((null != troMan.getTroubleSource()) && (0 < troMan.getTroubleSource().trim().length())){
					if("企业".equals(troMan.getTroubleSource())){
						paraMap.put("troubleSource", "%企业%");
					}else if("非企业".equals(troMan.getTroubleSource())){
						paraMap.put("troubleSourceNot", "企业");
					}else{
						paraMap.put("troubleSource", "%"+troMan.getTroubleSource().trim()+"%");
					}
				}

				if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
					paraMap.put("areaId",  troMan.getAreaId().trim() );
				}

				if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
					if(!"搜索企业名称或隐患名称".equals(troMan.getCompanyName())){
						paraMap.put("nameString", "%" + troMan.getCompanyName().trim() + "%");
					}
				}

				if ((null != troMan.getUserName()) && (0 < troMan.getUserName().trim().length())){
					paraMap.put("userName", "%" + troMan.getUserName().trim() + "%");
				}

				if ((null != troMan.getTroubleLevel()) && (0 < troMan.getTroubleLevel().trim().length())){
					paraMap.put("troubleLevel", troMan.getTroubleLevel().trim());
				}

				if ((null != troMan.getTroubleSort()) && (0 < troMan.getTroubleSort().trim().length())){
					paraMap.put("troubleSort", troMan.getTroubleSort().trim());
				}

				if ((null != troMan.getRectificationState()) && (0 < troMan.getRectificationState().trim().length())){
					if("待审核".equals(troMan.getRectificationState().trim())){
						List dsh=new ArrayList();
						if("1".equals(getUserTypeByLoginUser())){
							dsh.add("21");
						}else if("3".equals(getUserTypeByLoginUser())){
							dsh.add("2");
							dsh.add("20");
						}else if("4".equals(getUserTypeByLoginUser())){
							dsh.add("5");
						}else if("5".equals(getUserTypeByLoginUser())){
							dsh.add("7");
						}else{
							dsh.add("2");
							dsh.add("5");
							dsh.add("7");
						}
						paraMap.put("dsh", dsh);
						
						
						
					}else if("待整改".equals(troMan.getRectificationState().trim())){
						
						List dzg=new ArrayList();
						if("1".equals(getUserTypeByLoginUser())){
							dzg.add("3");
						}else if("6".equals(getUserTypeByLoginUser())){
							dzg.add("4");
						}else if("2".equals(getUserTypeByLoginUser())){
							dzg.add("6");
						}else{
							dzg.add("1");
							dzg.add("3");
							dzg.add("4");
							dzg.add("6");
							dzg.add("11");
						}
						paraMap.put("dzg", dzg);
					}else if("整改未完成".equals(troMan.getRectificationState().trim())){
						paraMap.put("zgwwc", "zgwwc");
					}else{//整改完成
						paraMap.put("zgwc", "zgwc");
					}
					
					
				}
				if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A11")){//中队长
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
					paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					} 
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("companmyIds", companmyIds);
				}
				if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A12")){//中队队员
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
					paraMapEnt.put("userId",this.getLoginUser().getId());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					}
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("companmyIds", companmyIds);
				}

			}
			//1:安委会办公室（角色），2：网格监管员（角色），3：安监中队队长（角色），4：监察大队队长（角色），5：安监局局领导（角色）
			//根据登录人的部门或角色设置查询条件
//			userType=this.getUserTypeByLoginUser();
//			if("1".equals(userType)){
//				paraMap.put("ifReportAwh", "1");//查询上报安委会的隐患
//			}else if("2".equals(userType)){
//				//TODO
//			}else if("3".equals(userType)){
//				//TODO
//			}else if("4".equals(userType)){
//				//TODO
//			}else if("5".equals(userType)){
//				//TODO
//			}else if("6".equals(userType)){
//				paraMap.put("deptCode", this.getLoginUserDepartment().getDeptCode());//查询上报安委会的隐患
//			}
			
			
			JsonConfig config = new JsonConfig();
			config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
			Map<String, String> codeMap = new HashMap<String, String>();
			//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
			codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
			codeMap.put("troubleLevel","402880084196a4a301419759d52a024b");
			codeMap.put("troubleSort","402880084196a4a301419759b28e0249");
		//codeMap.put("rectificationState","402880084196a4a30141975da934025d");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|troubleName|troubleSource|areaId|companyName|userName|troubleLevel|troubleSort|rectificationState|createUserID|auditResult|ifCorrected|userId|ifReportAwh|dealState|dealDeptId|ifRedirect|";
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
//		//企业只能看到自己的 其他角色看到所有
//		//判断登录人的角色 
//		List<UserRight> list =  (List<UserRight>) this.getLoginUser().getUserRoles();
//		for(UserRight ur:list)
//		{
//			//登录人为企业 只可以查看自己添加信息
//			if(ur.getRole().getRoleCode().equals("A23")) 
//			{
//				Map map = new HashMap();
//				map.put("loginId", this.getLoginUser().getLoginId());
//				EntBaseInfo entBaseInfo = entBaseInfoService.findEntBaseInfoByMap(map);
//				paraMap.put("companyId", entBaseInfo.getId());
//				break;
//			}
//		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		pagination = troManService.findByPage(pagination, paraMap);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage=(pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String troManXcjc(){
		if(troMan.getId() != null && !"".equals(troMan.getId()))
		{
			troMan = troManService.getById(troMan.getId());
			//查找对应现场检查记录
			Map m = new HashMap();
			m.put("caseId", troMan.getId());
			m.put("instrumentType", "9");
			List<InstrumentsInfo> list = instrumentsInfoService.findInstrumentsInfoss(m);
			if(list.size() != 0)
			{
				m.put("relatedId", list.get(0).getId());
				siteCheckRecord = (SiteCheckRecord) siteCheckRecordService.findSiteCheckRecord(m).get(0);
			}
			else
			{
				siteCheckRecord.setCheckPersonName1(this.getLoginUser().getDisplayName());
				siteCheckRecord.setCheckAddress(troMan.getTroubleAdd());
				siteCheckRecord.setCheckCondition(troMan.getIntroduce());
			}
		}
		
		userId = this.getLoginUser().getId();
		return EDIT;
	}
	
	public String troManXcjcSave() throws IOException
	{
		troMan = troManService.getById(troMan.getId());
		
		SimpleDateFormat sdff =  new SimpleDateFormat("yyyyMMdd");
		String fileName = troMan.getCompanyName()+troMan.getTroubleName()+sdff.format(troMan.getReportTime())+"现场检查记录.docx";
		
		troMan.setFileName(fileName);
		troMan.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
		troMan.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
		troManService.update(troMan);
		
		if(siteCheckRecord.getId() != null && !"".equals(siteCheckRecord.getId()))
		{
			siteCheckRecord.setDelFlag(0);
			siteCheckRecordService.update(siteCheckRecord);
		}
		else
		{
			InstrumentsInfo instrumentsInfo = new InstrumentsInfo();
			instrumentsInfo.setCaseId(troMan.getId());
			instrumentsInfo.setDelFlag(0);
			instrumentsInfo.setDeptId(this.getLoginUserDepartment().getId());
			instrumentsInfo.setDzCheck("0");
			instrumentsInfo.setDzqmCheck("0");
			instrumentsInfo.setFileName(fileName);
			instrumentsInfo.setCaseName("隐患现场检查记录");
			instrumentsInfo.setHttpurl(SysPropertiesUtil.getProperty("httpurl"));
			instrumentsInfo.setNwurl(SysPropertiesUtil.getProperty("nwurl"));
			instrumentsInfo.setIfCheck("7");
			instrumentsInfo.setIfPrint("1");
			instrumentsInfo.setInstrumentType("9");
			instrumentsInfo.setJzCheck("0");
			instrumentsInfo.setTime(new Date());
			instrumentsInfo.setNeedCheck("0");
			instrumentsInfo.setCompanyName(troMan.getCompanyName());
			Map m = new HashMap();
			m.put("codeName", "文书类型");
			m.put("itemValue", "9");
			String fileNames = codeService.findCodeValueByMap(m).getItemText();
			SimpleDateFormat sdf2 =  new SimpleDateFormat("yyyyMMdd");
			String instrumentsName = fileNames + sdf2.format(instrumentsInfo.getTime());
			instrumentsInfo.setInstrumentName(instrumentsName);
			instrumentsInfoService.save(instrumentsInfo);
			
			siteCheckRecord.setRelatedId(instrumentsInfo.getId());
			siteCheckRecord.setCreateUserID(troMan.getCreateUserID());
			siteCheckRecord.setDelFlag(0);
			siteCheckRecordService.save(siteCheckRecord);
		}
			
			//生成文书	
			FileDocUtil fileDocUtil = new FileDocUtil();
			Map<String, Object> map=new HashMap<String, Object>();
			String root = this.getRequest().getRealPath("/");
			root = root.replaceAll("\\\\", "/");
			EntBaseInfo entBaseInfo = entBaseInfoService.getById(troMan.getCompanyId());
			map.put("companyName", NullToString(entBaseInfo.getEnterpriseName()));
			map.put("companyAddress", NullToString(entBaseInfo.getEnterpriseAddress()));
			map.put("chargePersonZw", NullToString(entBaseInfo.getEnterpriseLegalZw()));
			map.put("chargePersonTel", NullToString(entBaseInfo.getEnterpriseLegalPhone()));
			map.put("chargePerson", NullToString(entBaseInfo.getEnterpriseLegalName()));
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
			map.put("time", changeTimeToZw(troMan.getReportTime()));
			map.put("zfry1", "");
			map.put("zfry2", "");
			map.put("zfry1Zj", "");
			map.put("zfry2Zj", "");
			if(siteCheckRecord.getCreateUserID() != null && !"".equals(siteCheckRecord.getCreateUserID()))
			{
				User uu = userService.findUserById(siteCheckRecord.getCreateUserID());
				map.put("zfry1", NullToString(uu.getDisplayName()));
				map.put("zfry1Zj", NullToString(uu.getZfzh()));
			}
			if(siteCheckRecord.getCheckPerson() != null && !"".equals(siteCheckRecord.getCheckPerson()))
			{
				User uu = userService.findUserById(siteCheckRecord.getCheckPerson());
				map.put("zfry2", NullToString(uu.getDisplayName()));
				map.put("zfry2Zj", NullToString(uu.getZfzh()));
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
			fileDocUtil.createDocFile(root+"现场检查记录.docx", fileName, root+"../../virtualdir/file/隐患现场检查记录", map);
		
		return RELOAD;
	}

	public void exportXcjc()
	{
		try
  		{
			troMan = troManService.getById(troMan.getId());
			String fileName = troMan.getFileName();
  			URL url = new URL(troMan.getNwurl()+"/file/"+URLEncoder.encode("隐患现场检查记录", "utf-8")+"/"+URLEncoder.encode(fileName, "utf-8")); 
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
	
	public String changeTimeToZw(Date d)
	{
		String time = "";
		if(d != null)
		{
			String day[] = sdfFormat.format(d).split("-");
			time = day[0] + "年" + day[1] + "月" + day[2] + "日";
		}
		return time;
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
	 * 企业自查隐患统计 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String tongJi(){
		return "tongji";
	}
	
	/**
	 * 按部门统计隐患信息 
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public String deptTongJi() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map map = new HashMap();	
		if(troMan != null){
			if(starttime != null && !"".equals(starttime) )
			{
				map.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				map.put("queryJcsjEnd", sdf.parse(endtime));
			}
			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				map.put("areaId",  troMan.getAreaId().trim() );
			}
			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				map.put("companyName", "%"+ troMan.getCompanyName().trim() +"%");
			}
		}
		if("dept".equals(tongjiType)){
			tjyhList = troManService.getTjYhListFromQy("query_tjyh_list_data",map);
			tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data",map);
		}else if("grid".equals(tongjiType)){
			tjyhList = troManService.getTjYhListFromQy("query_tjyh_list_data_grid",map);
			tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data_grid",map);
		}else{
			
		}
		
		
		return "dataDept";
	}
	
	public String companyTongJi(){
		return SUCCESS;
	}
	
	/**
	 * 获取现场和基础管理比例
	 * @return
	 * @throws ParseException 
	 */
	public String getCounts(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map map=new HashMap();
		map.put("sqlID", "getZcyhCounts");
		try {
			if(starttime != null && !"".equals(starttime) )
			{
				map.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				map.put("queryJcsjEnd", sdf.parse(endtime));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		counts=httpService.getMapByMap(map);
		int xcCount=Integer.parseInt(counts.get("xcCount").toString());
		int jcCount=Integer.parseInt(counts.get("jcCount").toString());
		
		 JSONObject jo = new JSONObject();
		 jo.put("data2",jcCount);
		 jo.put("data3",xcCount);
		 try {
			this.getResponse().getWriter().print(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return null;
	}
	
	
	
	/**
	 * 按企业名称统计隐患信息
	 * fq
	 * 2016-5-20
	 */
	public void companyTroManTongji() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != troMan){

			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
			}

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(starttime != null && !"".equals(starttime) )
			{
				paraMap.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				paraMap.put("queryJcsjEnd", sdf.parse(endtime));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data",paraMap);
		int total=tjyhBean.getSbqy();
		
		paraMap.put("start", (pagination.getPageNumber()-1)*pagination.getPageSize());
		paraMap.put("limit", pagination.getPageSize());
		paraMap.put("sqlId", "query_tjyh_list_data_company");
		List<Map<String,Object>> a=httpService.findListDataByMap(paraMap);
		List<TjYhBean> l=new ArrayList<TjYhBean>();
		for(Map m:a){
			TjYhBean t=new TjYhBean();
			t.setDwdz(m.get("dwdz").toString());
			t.setYhTotal(Integer.parseInt(m.get("yhTotal").toString()));
			t.setZgwc(Integer.parseInt(m.get("zgwc").toString()));
			t.setZgwwc(Integer.parseInt(m.get("zgwwc").toString()));
			t.setSbcs(Integer.parseInt(m.get("yhTotal").toString()));
			//t.setZgl("1");
			l.add(t);
		}
		pagination .setList(l);
		pagination.setTotalCount(total);
		try {
			convObjectToJson(pagination, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 安监隐患统计 
	 * @return
	 */
	public String ajyhTjList(){
		return SUCCESS;
	}
	
	/**
	 * 按部门统计隐患信息 
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public String ajyhDataDept() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map map = new HashMap();	
		if(troMan != null){
			if(starttime != null && !"".equals(starttime) )
			{
				map.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				map.put("queryJcsjEnd", sdf.parse(endtime));
			}
			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				map.put("areaId",  troMan.getAreaId().trim() );
			}
			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				map.put("companyName", "%"+ troMan.getCompanyName().trim() +"%");
			}
		}
		if("dept".equals(tongjiType)){
			tjyhList = troManService.getTjYhListFromQy("query_ajyh_list_data",map);
			tjyhBean = troManService.getTjYhDataFromQy("query_ajYh_data",map);
		}else if("grid".equals(tongjiType)){
			tjyhList = troManService.getTjYhListFromQy("query_ajyh_list_data_grid",map);
			tjyhBean = troManService.getTjYhDataFromQy("query_ajYh_data_grid",map);
		}else{
			
		}
		
		
		return "dataDept";
	}
	
	/**
	 * 获取现场和基础管理比例(安监隐患)
	 * @return
	 * @throws ParseException 
	 */
	public String getCountsAj(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map map=new HashMap();
		map.put("sqlID", "getAjyhCounts");
		try {
			if(starttime != null && !"".equals(starttime) )
			{
				map.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				map.put("queryJcsjEnd", sdf.parse(endtime));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		counts=httpService.getMapByMap(map);
		int xcCount=Integer.parseInt(counts.get("xcCount").toString());
		int jcCount=Integer.parseInt(counts.get("jcCount").toString());
		
		 JSONObject jo = new JSONObject();
		 jo.put("data2",jcCount);
		 jo.put("data3",xcCount);
		 try {
			this.getResponse().getWriter().print(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return null;
	}
	
	
	
	
	
	
	/**
	 * 按上报人统计隐患信息
	 * fq
	 * 2016-5-24
	 */
	public void reportmanTroManTongji() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
//		if(null != troMan){
//
//			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
//				paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
//			}
//
//		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(starttime != null && !"".equals(starttime) )
			{
				paraMap.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				paraMap.put("queryJcsjEnd", sdf.parse(endtime));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		paraMap.put("start", (pagination.getPageNumber()-1)*pagination.getPageSize());
		paraMap.put("limit", pagination.getPageSize());
		paraMap.put("sqlId", "query_tjyh_list_data_reportman");
		List<Map<String,Object>> a=httpService.findListDataByMap(paraMap);
		List<TjYhBean> l=new ArrayList<TjYhBean>();
		for(Map m:a){
			TjYhBean t=new TjYhBean();
			t.setSzzid(m.get("szzid").toString());
			t.setDwdz(m.get("dwdz").toString());
			t.setYhTotal(Integer.parseInt(m.get("sbcs").toString()));
			t.setZgwc(Integer.parseInt(m.get("zgwc").toString()));
			t.setZgwwc(Integer.parseInt(m.get("zgwwc").toString()));
			t.setSbcs(Integer.parseInt(m.get("sbcs").toString()));
			//t.setZgl("1");
			l.add(t);
		}
		pagination .setList(l);
		
		Map<String, Object> paraMap1 = new HashMap<String, Object>();
		paraMap1.put("sqlId", "query_tjyh_list_data_reportman_count");
		try {
			int total=Integer.parseInt(httpService.findListDataByMap(paraMap1).get(0).get("num").toString());
			
			pagination.setTotalCount(total);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			convObjectToJson(pagination, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * js获取网格的隐患统计信息
	 * fq 2016-6-6
	 */
	public String getTongjiByGridCode(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map map = new HashMap();	
		map.put("mapKey",  mapKey );
		tjyhList = troManService.getTjYhListFromQy("query_yhtj_list_data_grid",map);
		
		try {
		 JSONObject jo =  JSONObject.fromObject(tjyhList.get(0));
			 System.out.println(jo.toString());
			this.getResponse().getWriter().print(jo.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return null;
	}
	
	
	public String aqscyh(){
		ids=this.getLoginUser().getId();
		userType=this.getUserTypeByLoginUser();
		String rut="";
		if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A23")){
			roleName="0";
			rut =  "qy";
		}else if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A10")){
			roleName="1";
			rut =  "aj";
		}else{
			if("qy_troman".equals(flag)){//非企业角色查看企业的关联隐患
				rut =  "qy";
			}else{
				rut =  "aj";
			}
			
		}
		deptId = this.getLoginUserDepartment().getDeptCode();
		return SUCCESS;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void aqscyhList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
	//	paraMap.put("aj", "aj");//只查询非企业上报的隐患
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != troMan){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != troMan.getTroubleName()) && (0 < troMan.getTroubleName().trim().length())){
				paraMap.put("troubleName", "%" + troMan.getTroubleName().trim() + "%");
			}

			if ((null != troMan.getTroubleSource()) && (0 < troMan.getTroubleSource().trim().length())){
				paraMap.put("troubleSource", "%" + troMan.getTroubleSource().trim() + "%");
			}

			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				paraMap.put("areaId",  troMan.getAreaId().trim() );
			}

			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
			}

			if ((null != troMan.getUserName()) && (0 < troMan.getUserName().trim().length())){
				paraMap.put("userName", "%" + troMan.getUserName().trim() + "%");
			}

			if ((null != troMan.getTroubleLevel()) && (0 < troMan.getTroubleLevel().trim().length())){
				paraMap.put("troubleLevel", troMan.getTroubleLevel().trim());
			}

			if ((null != troMan.getTroubleSort()) && (0 < troMan.getTroubleSort().trim().length())){
				paraMap.put("troubleSort", troMan.getTroubleSort().trim());
			}

			if ((null != troMan.getRectificationState()) && (0 < troMan.getRectificationState().trim().length())){
				if("待审核".equals(troMan.getRectificationState().trim())){
					List dsh=new ArrayList();
					if("1".equals(getUserTypeByLoginUser())){
						dsh.add("21");
					}else if("2".equals(getUserTypeByLoginUser())){
						dsh.add("3");
					}else if("3".equals(getUserTypeByLoginUser())){
						dsh.add("2");
						dsh.add("20");
					}else if("4".equals(getUserTypeByLoginUser())){
						dsh.add("5");
					}else if("5".equals(getUserTypeByLoginUser())){
						dsh.add("7");
					}else{
						dsh.add("2");
						dsh.add("3");
						dsh.add("5");
						dsh.add("7");
						dsh.add("20");
						dsh.add("21");
					}
					paraMap.put("dsh", dsh);
					
					
					
				}else if("待整改".equals(troMan.getRectificationState().trim())){
					
					List dzg=new ArrayList();
					if("6".equals(getUserTypeByLoginUser())){
						dzg.add("4");
					}else if("2".equals(getUserTypeByLoginUser())){
						dzg.add("6");
					}else{
						dzg.add("4");
						dzg.add("6");
						dzg.add("11");
					}
					paraMap.put("dzg", dzg);
				}else if("审核未通过".equals(troMan.getRectificationState().trim())){
					List dzg=new ArrayList();
					dzg.add("1");
					paraMap.put("dzg", dzg);
				}else if("整改未完成".equals(troMan.getRectificationState().trim())){
					paraMap.put("zgwwc", "zgwwc");
				}else{//整改完成
					paraMap.put("zgwc", "zgwc");
				}
				
				
			}
			if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A11")){//中队长
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZdzDeptCode");
				paraMapEnt.put("deptCode",this.getLoginUser().getDeptCode());
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				} 
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}
			if(httpService.judgeRoleCode(this.getLoginUser().getId(), "A12")){//中队队员
				Map<String, Object> paraMapEnt = new HashMap<String, Object>();
				paraMapEnt.put("sqlId","findCompanyIdsByZddyId");
				paraMapEnt.put("userId",this.getLoginUser().getId());
				List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
				String companmyIds="";
				for(int i=0;i< ents.size();i++){
					companmyIds+=ents.get(i).get("row_id")+",";
				}
				if("".equals(companmyIds)){
					companmyIds="0";
				}
				paraMap.put("companmyIds", companmyIds);
			}

		}
		//1:安委会办公室（角色），2：网格监管员（角色），3：安监中队队长（角色），4：监察大队队长（角色），5：安监局局领导（角色）
		//根据登录人的部门或角色设置查询条件
//		userType=this.getUserTypeByLoginUser();
//		if("1".equals(userType)){
//			paraMap.put("ifReportAwh", "1");//查询上报安委会的隐患
//		}else if("2".equals(userType)){
//			//TODO
//		}else if("3".equals(userType)){
//			//TODO
//		}else if("4".equals(userType)){
//			//TODO
//		}else if("5".equals(userType)){
//			//TODO
//		}else if("6".equals(userType)){
//			paraMap.put("deptCode", this.getLoginUserDepartment().getDeptCode());//查询上报安委会的隐患
//		}
		
		if(null!=searchLike&&!"".equals(searchLike)&&!"搜索企业名称或隐患名称".equals(searchLike)){
			paraMap.put("nameString", "%" + searchLike.trim() + "%");
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
		final String filter = "id|troubleName|troubleSource|areaId|companyName|userName|troubleLevel|troubleSort|rectificationState|createUserID|auditResult|ifCorrected|userId|ifReportAwh|dealState|dealDeptId|ifRedirect|areaName|ifla|";
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
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String troManCase()
	{
		if(troMan.getId() != null && !"".equals(troMan.getId()))
		{
			troMan = troManService.getById(troMan.getId());
			caseInfo.setCaseTime(troMan.getReportTime());
			caseInfo.setAreaId(troMan.getAreaId());
			caseInfo.setAreaName(troMan.getAreaName());
			caseInfo.setCompanyId(troMan.getCompanyId());
			caseInfo.setCompanyName(troMan.getCompanyName());
			caseInfo.setCaseSource("2");
			caseInfo.setUndertakerName1(this.getLoginUser().getDisplayName());
			caseInfo.setCaseCondition(troMan.getIntroduce());
		}
		
		Map map = new HashMap();
		map.put("userId", this.getLoginUser().getId());
		userList = caseInfoService.queryCaseUserList(map);
		return EDIT;
	}
	
	public String troManCaseSave()
	{
		Map m = new HashMap();
		m.put("codeName", "企业属地");
		m.put("itemValue", caseInfo.getAreaId());
		caseInfo.setAreaName(codeService.findCodeValueByMap(m).getItemText());
		String undertakerName = this.getLoginUser().getDisplayName();
		if(caseInfo.getUndertakerId() != null)
		{
			User user = userService.findUserById(caseInfo.getUndertakerId().trim());
			undertakerName += "," + user.getDisplayName();
		}
		caseInfo.setUndertakerName(undertakerName);
		caseInfo.setDeptId(this.getLoginUserDepartmentId());
		caseInfo.setDelFlag(0);
		caseInfo.setCaseStatus("8");
		caseInfoService.save(caseInfo);
		
		
		if(troMan.getId() != null && !"".equals(troMan.getId()))
		{
			troMan = troManService.getById(troMan.getId());
			troMan.setIfla("1");
			troManService.update(troMan);
		}
		
		return RELOAD;
	}
	
	public String cxtjSsqy(){
		
		return SUCCESS;
	}
	
	public String cxtjSbry(){
		
		return SUCCESS;
	}

	/**
	 * 按企业名称统计隐患信息
	 * fq
	 * 2016-5-20
	 */
	public void companyTroManTongjiZwt() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != troMan){

			if ((null != troMan.getCompanyName()) && (0 < troMan.getCompanyName().trim().length())){
				paraMap.put("companyName", "%" + troMan.getCompanyName().trim() + "%");
			}

		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(starttime != null && !"".equals(starttime) )
			{
				paraMap.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				paraMap.put("queryJcsjEnd", sdf.parse(endtime));
			}
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		tjyhBean = troManService.getTjYhDataFromQy("query_tjYh_data",paraMap);
		int total=tjyhBean.getSbqy();
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		
		paraMap.put("start", pagination.getPageNumber());
		paraMap.put("limit", pagination.getPageSize());
		paraMap.put("sqlId", "query_tjyh_list_data_company");
		List<Map<String,Object>> a=httpService.findListDataByMap(paraMap);
		List<TjYhBean> l=new ArrayList<TjYhBean>();
		for(Map m:a){
			TjYhBean t=new TjYhBean();
			t.setDwdz(m.get("dwdz").toString());
			t.setYhTotal(Integer.parseInt(m.get("yhTotal").toString()));
			t.setZgwc(Integer.parseInt(m.get("zgwc").toString()));
			t.setZgwwc(Integer.parseInt(m.get("zgwwc").toString()));
			t.setSbcs(Integer.parseInt(m.get("yhTotal").toString()));
			l.add(t);
		}
		pagination .setList(l);
		pagination.setTotalCount(total);
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getList());
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage=(pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 按上报人统计隐患信息
	 * fq
	 * 2016-5-24
	 */
	public void reportmanTroManTongjiZwt() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if(starttime != null && !"".equals(starttime) )
			{
				paraMap.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				paraMap.put("queryJcsjEnd", sdf.parse(endtime));
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		pagination.setPageNumber(pageNo);
		pagination.setPageSize(pageSize);
		paraMap.put("start", pagination.getPageNumber());
		paraMap.put("limit", pagination.getPageSize());
		paraMap.put("sqlId", "query_tjyh_list_data_reportman");
		List<Map<String,Object>> a=httpService.findListDataByMap(paraMap);
		List<TjYhBean> l=new ArrayList<TjYhBean>();
		for(Map m:a){
			TjYhBean t=new TjYhBean();
			t.setSzzid(m.get("szzid").toString());
			t.setDwdz(m.get("dwdz").toString());
			t.setYhTotal(Integer.parseInt(m.get("sbcs").toString()));
			t.setZgwc(Integer.parseInt(m.get("zgwc").toString()));
			t.setZgwwc(Integer.parseInt(m.get("zgwwc").toString()));
			t.setSbcs(Integer.parseInt(m.get("sbcs").toString()));
			//t.setZgl("1");
			l.add(t);
		}
		pagination .setList(l);
		
		Map<String, Object> paraMap1 = new HashMap<String, Object>();
		paraMap1.put("sqlId", "query_tjyh_list_data_reportman_count");
		int total=Integer.parseInt(httpService.findListDataByMap(paraMap1).get(0).get("num").toString());
		
		pagination.setTotalCount(total);
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(pagination.getList());
		json.put("result", ja);
		json.put("count", pagination.getTotalCount());
		int totalPage=(pagination.getTotalCount()%pageSize==0?pagination.getTotalCount()/pageSize:(pagination.getTotalCount()/pageSize+1));
		json.put("totalPage", totalPage);
		json.put("pageNo", pageNo);
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public String aqscyhView(){
		return SUCCESS;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void aqscyhLists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != troMan){

			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				if(flag != null && flag.equals("0dept"))
				{
					paraMap.put("troubleSource", "企业");
					paraMap.put("areaId",  troMan.getAreaId().trim() );
				}
				if(flag != null && flag.equals("0grid"))
				{
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByWgId");
					paraMapEnt.put("grid",troMan.getAreaId().trim());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					} 
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("troubleSource", "企业");
					paraMap.put("companmyIds", companmyIds);
				}
				if(flag != null && flag.equals("1dept"))
				{
					paraMap.put("troubleSource", "安监");
					paraMap.put("areaId",  troMan.getAreaId().trim() );
				}
				if(flag != null && flag.equals("1grid"))
				{
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByWgId");
					paraMapEnt.put("grid",troMan.getAreaId().trim());
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					} 
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("troubleSource", "安监");
					paraMap.put("companmyIds", companmyIds);
				}
			}
			else
			{
				if(flag != null && flag.equals("0dept"))
				{
					paraMap.put("troubleSource", "企业");
					paraMap.put("noareaId",  "1" );
				}
				if(flag != null && flag.equals("0grid"))
				{
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByWgId");
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					} 
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("troubleSource", "企业");
					paraMap.put("companmyIds", companmyIds);
				}
				if(flag != null && flag.equals("1dept"))
				{
					paraMap.put("troubleSource", "安监");
					paraMap.put("noareaId",  "1" );
				}
				if(flag != null && flag.equals("1grid"))
				{
					Map<String, Object> paraMapEnt = new HashMap<String, Object>();
					paraMapEnt.put("sqlId","findCompanyIdsByWgId");
					List<Map<String, Object>> ents= httpService.findListDataByMap(paraMapEnt);
					String companmyIds="";
					for(int i=0;i< ents.size();i++){
						companmyIds+=ents.get(i).get("row_id")+",";
					} 
					if("".equals(companmyIds)){
						companmyIds="0";
					}
					paraMap.put("troubleSource", "安监");
					paraMap.put("companmyIds", companmyIds);
				}
			}
			
			if ((null != troMan.getTaskId()) && (0 < troMan.getTaskId().trim().length())){
				paraMap.put("taskId", troMan.getTaskId().trim());
			}
			
			if(starttime != null && !"".equals(starttime) )
			{
				paraMap.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				paraMap.put("queryJcsjEnd", sdf.parse(endtime));
			}
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("areaId","40288008416c6c1a01416c95d3c50019");
		codeMap.put("troubleLevel","402880084196a4a301419759d52a024b");
		codeMap.put("troubleSort","402880084196a4a301419759b28e0249");

		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|troubleName|troubleSource|areaId|companyName|userName|troubleLevel|troubleSort|rectificationState|createUserID|auditResult|ifCorrected|userId|ifReportAwh|dealState|dealDeptId|ifRedirect|areaName|ifla|";
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
		try {
			this.getResponse().getWriter().println(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public String yhcomView(){
		return SUCCESS;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void yhcomList() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		    
		if(flag.contains("00") || flag.contains("01"))
		{
			paraMap.put("troubleSource", "企业");
		}
		if(flag.contains("10") || flag.contains("11"))
		{
			paraMap.put("troubleSource", "安监");
		}
		
		if(null != troMan){

			if ((null != troMan.getAreaId()) && (0 < troMan.getAreaId().trim().length())){
				if(flag.contains("dept"))
				{
					paraMap.put("areaId",  troMan.getAreaId().trim() );
				}
				if(flag.contains("grid"))
				{
					paraMap.put("grid",  troMan.getAreaId().trim() );
				}
			}
			else{
				if(flag.contains("dept"))
				{
					paraMap.put("noareaId",  "1");
				}
				if(flag.contains("grid"))
				{
					paraMap.put("nogrid",  "1" );
				}
			}
			if(starttime != null && !"".equals(starttime) )
			{
				paraMap.put("queryJcsjStart", sdf.parse(starttime));
			}
			if(endtime != null && !"".equals(endtime))
			{
				paraMap.put("queryJcsjEnd", sdf.parse(endtime));
			}
		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		codeMap.put("enterpriseNature", "40288008416c6c1a01416ca5177c003d");
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|enterpriseName|enterpriseCode|enterpriseLegalName|enterpriseNature|gridName|";
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
		List<EntBaseInfo> list = new ArrayList<EntBaseInfo>();
		int start = (pageNo - 1)*pageSize;
		int limit = pageSize;
		int total = 0;
		if(flag.contains("00") || flag.contains("10"))
		{
			list = troManService.getYhsbqyByMap(paraMap,start,limit);
			total = troManService.getYhsbqyTotalByMap(paraMap);
		}
		if(flag.contains("01") || flag.contains("11"))
		{
			list = troManService.getYhwsbqyByMap(paraMap,start,limit);
			total = troManService.getYhwsbqyTotalByMap(paraMap);
		}
		
		JSONObject json=new JSONObject();
		JSONArray ja = JSONArray.fromObject(list,
				config);
		json.put("result", ja);
		json.put("count", total);
		int totalPage;
		totalPage = total%pageSize==0?total/pageSize:(total/pageSize+1);
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

	public TroMan getTroMan(){
		return this.troMan;
	}

	public void setTroMan(TroMan troMan){
		this.troMan = troMan;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryReportTimeStart(){
		return this.queryReportTimeStart;
	}

	public void setQueryReportTimeStart(Date queryReportTimeStart){
		this.queryReportTimeStart = queryReportTimeStart;
	}

	public Date getQueryReportTimeEnd(){
		return this.queryReportTimeEnd;
	}

	public void setQueryReportTimeEnd(Date queryReportTimeEnd){
		this.queryReportTimeEnd = queryReportTimeEnd;
	}

	public Date getQueryRectificationTermStart(){
		return this.queryRectificationTermStart;
	}

	public void setQueryRectificationTermStart(Date queryRectificationTermStart){
		this.queryRectificationTermStart = queryRectificationTermStart;
	}

	public Date getQueryRectificationTermEnd(){
		return this.queryRectificationTermEnd;
	}

	public void setQueryRectificationTermEnd(Date queryRectificationTermEnd){
		this.queryRectificationTermEnd = queryRectificationTermEnd;
	}

	public Date getQueryRecfinishTimeStart(){
		return this.queryRecfinishTimeStart;
	}

	public void setQueryRecfinishTimeStart(Date queryRecfinishTimeStart){
		this.queryRecfinishTimeStart = queryRecfinishTimeStart;
	}

	public Date getQueryRecfinishTimeEnd(){
		return this.queryRecfinishTimeEnd;
	}

	public void setQueryRecfinishTimeEnd(Date queryRecfinishTimeEnd){
		this.queryRecfinishTimeEnd = queryRecfinishTimeEnd;
	}
	public List<PhotoPic> getPicList1() {
		return picList1;
	}

	public void setPicList1(List<PhotoPic> picList1) {
		this.picList1 = picList1;
	}

	public List<PhotoPic> getPicList2() {
		return picList2;
	}

	public void setPicList2(List<PhotoPic> picList2) {
		this.picList2 = picList2;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<CheckRecord> getCheckRecords() {
		return checkRecords;
	}

	public void setCheckRecords(List<CheckRecord> checkRecords) {
		this.checkRecords = checkRecords;
	}

	public CheckRecord getCheckRecord() {
		return checkRecord;
	}

	public void setCheckRecord(CheckRecord checkRecord) {
		this.checkRecord = checkRecord;
	}

	public String getLinkId() {
		return linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	public List<HashMap> getRectInfos() {
		return rectInfos;
	}

	public void setRectInfos(List<HashMap> rectInfos) {
		this.rectInfos = rectInfos;
	}

	public String getWxyType() {
		return wxyType;
	}

	public void setWxyType(String wxyType) {
		this.wxyType = wxyType;
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

	public SiteCheckRecord getSiteCheckRecord() {
		return siteCheckRecord;
	}

	public void setSiteCheckRecord(SiteCheckRecord siteCheckRecord) {
		this.siteCheckRecord = siteCheckRecord;
	}

	public TjYhBean getTjyhBean() {
		return tjyhBean;
	}

	public void setTjyhBean(TjYhBean tjyhBean) {
		this.tjyhBean = tjyhBean;
	}

	public List<TjYhBean> getTjyhList() {
		return tjyhList;
	}

	public void setTjyhList(List<TjYhBean> tjyhList) {
		this.tjyhList = tjyhList;
	}

	public String getJcbl() {
		return jcbl;
	}

	public void setJcbl(String jcbl) {
		this.jcbl = jcbl;
	}

	public String getXcbl() {
		return xcbl;
	}

	public void setXcbl(String xcbl) {
		this.xcbl = xcbl;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getTongjiType() {
		return tongjiType;
	}

	public void setTongjiType(String tongjiType) {
		this.tongjiType = tongjiType;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public CaseInfo getCaseInfo() {
		return caseInfo;
	}

	public void setCaseInfo(CaseInfo caseInfo) {
		this.caseInfo = caseInfo;
	}

	public String getSearchLike() {
		return searchLike;
	}

	public void setSearchLike(String searchLike) {
		this.searchLike = searchLike;
	}
	
	
	

}
