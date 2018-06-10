package com.jshx.checkBasic.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.activiti.service.ActivityManageService;
import com.jshx.checkBasic.entity.CheckBasic;
import com.jshx.checkBasic.service.CheckBasicService;
import com.jshx.checkCategory.entity.CheckCategory;
import com.jshx.checkCategory.service.CheckCategoryService;
import com.jshx.checkContent.entity.CheckContent;
import com.jshx.checkLawEnforce.entity.CheckLawEnforce;
import com.jshx.checkLawEnforce.service.CheckLawEnforceService;
import com.jshx.checkResult.entity.CategoryResult;
import com.jshx.checkResult.entity.CheckResult;
import com.jshx.checkResult.entity.ContentResult;
import com.jshx.checkResult.service.CheckResultService;
import com.jshx.checkSituation.entity.CheckSituation;
import com.jshx.checkSituation.service.CheckSituationService;
import com.jshx.company.entity.Company;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.utils.DateUtil;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.docReview.entity.DocReview;
import com.jshx.http.bean.DataBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.module.infomation.entity.Dept;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.rectifyOpinion.entity.RectifyOpinion;
import com.jshx.rectifyOpinion.service.RectifyOpinionService;
import com.jshx.utils.DateUtils;
import com.jshx.utils.NumberFormat;
import com.jshx.utils.XwpfUtils;
import com.jshx.yhqd.entity.Yhqd;
import com.jshx.yhqd.service.YhqdService;
import com.sun.jndi.toolkit.url.UrlUtil;

public class CheckBasicAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private CheckBasic checkBasic = new CheckBasic();
	@Autowired
	private CheckBasicService checkBasicService;
	@Autowired
	private CheckSituationService checkSituationService;
	@Autowired
	private CheckCategoryService checkCategoryService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private UserService userService;
	@Autowired
	private CheckLawEnforceService checkLawEnforceService;
	@Autowired
	private CheckResultService checkResultService;
	@Autowired
	private RectifyOpinionService rectifyOpinionService;
	
	@Autowired
	private ActivityManageService activityManageService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected YhqdService yhqdService;
	
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	/**
	 * 分页信息
	 */
	private Pagination pagination;

	private Date queryCheckTimeStart;

	private Date queryCheckTimeEnd;

	List<CategoryResult> crList = new ArrayList<CategoryResult>();

	List<CheckSituation> situationList = new ArrayList<CheckSituation>();
	
	List<PhotoPic> photoPics = new ArrayList<PhotoPic>();
	
	List<RectifyOpinion> opinionList = new ArrayList<RectifyOpinion>();

	List<CheckSituation> rectifyList = new ArrayList<CheckSituation>();
	 @Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private HttpService httpService;

	private String lawEnforceName = "";// 执法人员名称

	private String idNum = "";// 证件号码
	private String lawEnforceIds="";
	private String picName;
	private String isExistYh;
	private String taskId;
	private Yhqd yhqd;
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list()  
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
try{
		if (pagination == null)
			pagination = new Pagination(this.getRequest());

		if (null != checkBasic)
		{
			// 设置查询条件，开发人员可以在此增加过滤条件
			if(checkBasic.getCompany()!=null&&StringUtil.isNotNullAndNotEmpty(checkBasic.getCompany().getCompanyname())){
				paraMap.put("companyname", "%"+checkBasic.getCompany().getCompanyname()+"%");
			}
			if(checkBasic.getChecker()!=null&&StringUtil.isNotNullAndNotEmpty(checkBasic.getChecker().getDisplayName())){
				paraMap.put("checkerName", "%"+checkBasic.getChecker().getDisplayName()+"%");
			}
		}
		String deptCode = this.getLoginUser().getDeptCode();
		if(deptCode.startsWith("002014") && deptCode.length() >= 9){
			paraMap.put("deptCode", deptCode.substring(0,9));
		}
		pagination = checkBasicService.findByPage(pagination, paraMap);
  		List<CheckBasic> checkBasics = pagination.getListOfObject();
  		List<CheckBasic> data = new ArrayList<CheckBasic>();
  		for (CheckBasic oldcheckBasic : checkBasics) {
  			checkBasic = new CheckBasic();
			Company company =  new Company();
			checkBasic.setId(oldcheckBasic.getId());
			company.setCompanyname(oldcheckBasic.getCompany().getCompanyname());
			checkBasic.setCompany(company);
			User user = new User();
			user.setId(oldcheckBasic.getChecker().getId());
			user.setDisplayName(oldcheckBasic.getChecker().getDisplayName());
			checkBasic.setChecker(user);
			checkBasic.setRectifyFlag(oldcheckBasic.getRectifyFlag());
			checkBasic.setCheckTime(oldcheckBasic.getCheckTime());
			List<CheckLawEnforce> checkLawEnforces = new ArrayList<CheckLawEnforce>();
			List<CheckLawEnforce> tcheckLawEnforces =oldcheckBasic.getEnforceList();
				for (CheckLawEnforce tcheckLawEnforce : tcheckLawEnforces) {
					CheckLawEnforce checkLawEnforce =new CheckLawEnforce();
					User t = new User();
					t.setId(tcheckLawEnforce.getCheckUser().getId());
					t.setDisplayName(tcheckLawEnforce.getCheckUser().getDisplayName());
					checkLawEnforce.setCheckUser(t);
					checkLawEnforces.add(checkLawEnforce);
			}
				checkBasic.setEnforceList(checkLawEnforces);
			data.add(checkBasic);
		}
  		pagination.setList(data);
		convObjectToJson(pagination, null);
}catch(Exception e ){
	e.printStackTrace();
}
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception
	{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List categoryList = checkCategoryService.findCheckCategory(paraMap);
		for (int i = 0; i < categoryList.size(); i++)
		{
			CheckCategory checkCategory = (CheckCategory) categoryList.get(i);
			CategoryResult categoryResult = new CategoryResult();
			categoryResult.setCategoryContent(checkCategory.getContent());// 栏目内容
			categoryResult.setIndexNum(NumberFormat.foematInteger(i + 1));// 序号
			List<ContentResult> contentResults = new ArrayList<ContentResult>();
			for (CheckContent checkContent : checkCategory.getContents()) {
				ContentResult contentResult = new ContentResult();
				contentResult.setCheckResult("0");
				contentResult.setContent(checkContent.getContent());
				contentResult.setIndexNum(checkContent.getIndexNum()+"");
				contentResult.setId(checkContent.getId());
				contentResult.setRemark("");
				contentResults.add(contentResult);
			}
			categoryResult.setContentList(contentResults);
			crList.add(categoryResult);
		}
		if ((null != checkBasic) && (null != checkBasic.getId()))
		{ crList= new ArrayList<CategoryResult>();
			checkBasic = checkBasicService.getById(checkBasic.getId());
			// 封装安全生产检查实体类
			List<CheckResult> 	 resultList = checkBasic.getResultList();
	 
			for (int i = 0; i < categoryList.size(); i++)
			{
				CheckCategory checkCategory = (CheckCategory) categoryList.get(i);
				CategoryResult categoryResult = new CategoryResult();
				categoryResult.setCategoryContent(checkCategory.getContent());// 栏目内容
				categoryResult.setIndexNum(NumberFormat.foematInteger(i + 1));// 序号
				List<ContentResult> contentList = new ArrayList<ContentResult>();
				for (int m = 0; m < resultList.size(); m++)
				{
					CheckResult checkResult = resultList.get(m);
					if (checkCategory.getId().equals(checkResult.getContent().getCategory().getId()))
					{
						ContentResult contentResult = new ContentResult();
						contentResult.setContent(checkResult.getContent().getContent());
						contentResult.setCheckResult(checkResult.getCheckResult());
						contentResult.setRemark(checkResult.getRemark());
						contentResult.setId(checkResult.getContent().getId());
						contentResult.setIndexNum(checkResult.getId());
						// contentResult.setIndexNum(String.valueOf(m+1));
						contentList.add(contentResult);
					}
				}
				categoryResult.setContentList(contentList);// 内容列表
				crList.add(categoryResult);
			}

			// 处理执法人员
			List<CheckLawEnforce> enforceList = checkBasic.getEnforceList();
			String lawEnforceIds="";
			for (CheckLawEnforce enforce : enforceList)
			{
				User checker = enforce.getCheckUser();
				lawEnforceName +=  checker.getDisplayName() + "、";
				idNum += (checker.getIdNum() != null ? checker.getIdNum() : "") + "、";
				lawEnforceIds+= checker.getId()+"|";
			}
			if (lawEnforceName.length() > 0)
			{
				lawEnforceName = lawEnforceName.substring(0, lawEnforceName.length() - 1);
			}
			if (idNum.length() > 0)
			{
				idNum = idNum.substring(0, idNum.length() - 1);
			}
			setRequestAttribute("lawEnforceIds", lawEnforceIds);
			// 处理检查情况集合
			List<CheckSituation> checkList = checkBasic.getSituationList();
			for (CheckSituation situation : checkList)
			{
				Map map = new HashMap();
				map.put("linkId", situation.getLinkid());
				map.put("type", "check");
				DataBean bean = httpService.getPhotoListByType(map);
				String linkPath = StringTools.NullToStr(bean.getRname(), "");
				List<String> pathList = new ArrayList<String>();
				String [] paths = linkPath.split("\\,");
				for (String path : paths)
				{
					pathList.add(path);
				}
				situation.setPathList(pathList);
				situationList.add(situation);
			}

			// 处理责令限期整改问题
			List<CheckSituation> checkSituationList = checkBasic.getRectifyList();
			for (CheckSituation situation : checkSituationList)
			{
				Map map = new HashMap();
				map.put("linkId", situation.getLinkid());
				map.put("type", "check");
				DataBean bean = httpService.getPhotoListByType(map);
				String linkPath = StringTools.NullToStr(bean.getRname(), "");
				List<String> pathList = new ArrayList<String>();
				String [] paths = linkPath.split("\\,");
				for (String path : paths)
				{
					pathList.add(path);
				}
				situation.setPathList(pathList);
				rectifyList.add(situation);
			}

			// 处理整改意见
			List<RectifyOpinion> rectifyOpinionList = checkBasic.getRectifyOpinionList();
			for (RectifyOpinion opinion : rectifyOpinionList)
			{
				Map map = new HashMap();
				map.put("linkId", opinion.getLinkid());
				map.put("type", "rectify");
				DataBean bean = httpService.getPhotoListByType(map);
				String linkPath = StringTools.NullToStr(bean.getRname(), "");
				List<String> pathList = new ArrayList<String>();
				String [] paths = linkPath.split("\\,");
				for (String path : paths)
				{
					pathList.add(path);
				}
				opinion.setPathList(pathList);
				opinionList.add(opinion);
			}
			List<Yhqd> yhqds = checkBasic.getYhqdList();
			for (Yhqd yhqd : yhqds) {
				Map map = new HashMap();
				map.put("taskProId", yhqd.getId());
				List<PhotoPic> list = szwxPhotoService.findPicPath(map);
				yhqd.setPicList(list);
				map.put("taskProId", "ZG"+yhqd.getId());
				List<PhotoPic> zglist = szwxPhotoService.findPicPath(map);
				yhqd.setZgpicList(zglist);
			}
		}
		return VIEW;
	}
	
	/**
	 *  自定义上传附件的名称 以时间格式来处理
	 * @param fname
	 * @return
	 */
	public String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		return  result.toString();
	}
	 
	/**
	 * 图片保存 lj  2013-08-15
	 * @return
	 */
	public void savePhoto(){
		try {
			FileOutputStream fos = null;
			BufferedInputStream bis = null;
			File outfile = null;
			File outdir = null;
			byte[] bs = new byte[1024];
			String imgName="";
			StringBuffer destFName = new StringBuffer();
			String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
			root = root.substring(0,root.indexOf("webapps")+8);
			root = root.replaceAll("\\\\", "/");
			root = root.replace("webapps","virtualdir/upload/photo/");
			destFName.append(root);
			outdir = new File(destFName.toString());
			if(Filedata!= null && !Filedata.isEmpty()){//获取附件文件 进行判断是否存在
			    imgName =getDatedFName(FiledataFileName.get(0));
				outfile = new File(destFName+imgName);
				InputStream stream  = new FileInputStream(Filedata.get(0));
				bis = new BufferedInputStream(stream);
				if (!outdir.exists())
					outdir.mkdirs();
				if (!outfile.exists())
					outfile.createNewFile();

				fos = new FileOutputStream(outfile);
				int i;
				while ((i = bis.read(bs)) != -1) {
					fos.write(bs, 0, i);
				}
			}
			PhotoPic taskPic = new PhotoPic();
			taskPic.setCreateTime(new Date());
			String picname = imgName;
			taskPic.setPicName(picname);
			taskPic.setTaskRemark("");
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			taskPic.setTaskProId(linkId);
			taskPic.setPicType(getRequestParameter("type"));//设置图片类型 安全管理表
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", picname);
			jn.put("pid", taskPic.getId());
			jn.put("linkId", linkId);
			this.getResponse().getWriter().print(jn);
		} catch (Exception e) {
				e.printStackTrace();
			}
	}

  	/**
  	 * 查询部门用户列表
  	 */
  	public String selectUsers()
  	{
  		if(checkBasic != null &&StringUtil.isNotNullAndNotEmpty(checkBasic.getId()))
  		{
  			checkBasic = checkBasicService.getById(checkBasic.getId());
  			setRequestAttribute("checkLawEnforceList", checkBasic.getEnforceList());
  		}
  		return SUCCESS;
  	}
  	
	/**
  	 * 查询部门用户列表
  	 * author：陆婷
  	 * 2013-07-19
  	 * @throws IOException 
  	 */
  	public void getDepartUser() throws IOException
  	{
  		JSONArray jsonArry = new JSONArray();
  		Map map = new HashMap();
  		if(checkBasic != null &&StringUtil.isNotNullAndNotEmpty(checkBasic.getId()))
  		{
  			checkBasic = checkBasicService.getById(checkBasic.getId());
  		}
  			map.put("deptCode", "002%");
  			List<Department> deptList = deptService.findAllDept(map);
  			
  			for (Department dept : deptList) {
				JSONObject jsonObject = new JSONObject();
		  		jsonObject.put("id", dept.getId());
		  		if(dept.getParentDept() == null )
		  		{
		  			jsonObject.put("pId", "-1");
		  			jsonObject.put("open", true);
		  		}
		  		else
		  		{
		  			jsonObject.put("pId", dept.getParentDept().getId());
		  		}
		  		jsonObject.put("name", dept.getDeptName());
		  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/folder.gif");
		  		jsonArry.add(jsonObject);
			}
			
  			
  			List<UserRight> userRightList = userService.findByRole("8a8184ca4b78635a014b787b97a00005");
  			for (UserRight userRight : userRightList)
  			{
  				User user = userRight.getUser();
  				JSONObject jsonObject = new JSONObject();
  		  		jsonObject.put("id", user.getId());
  		  		jsonObject.put("pId",user.getDept().getId());
  		  		jsonObject.put("name", user.getDisplayName());
  		  		jsonObject.put("flag", "1");
  		  		jsonObject.put("icon", getRequest().getContextPath() + "/webResources/images/SmallIcon/user.gif");
  		  		if(checkBasic != null&&!checkBasic.getId().equals("")){
  		  			for (CheckLawEnforce checkLawEnforce : checkBasic.getEnforceList()) {
  		  				if(checkLawEnforce.getCheckUser()!=null&&checkLawEnforce.getCheckUser().getId().equals(user.getId())){
  		  					jsonObject.put("checked", Boolean.valueOf(true));
  		  					break;
  		  				}
  		  			}
  		  		}
  		  		jsonArry.add(jsonObject);
  			}
		getResponse().getWriter().write(jsonArry.toString());
  	}

  	
	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception
	{
		view();
		if(this.flag.equalsIgnoreCase("fucha")){
			return "fucha";
		}else if(this.flag.equalsIgnoreCase("zhenggai")){
			return "zhenggai";
		}else if(this.flag.equalsIgnoreCase("yanshou")){
			return "yanshou";
		}
		return EDIT;
	}

	/**
	 * 复查
	 */
	public String fucha()  
	{
		try{
			List<RectifyOpinion> rectifyOpinionList = checkBasic.getRectifyOpinionList();
		if(rectifyOpinionList!=null){
			for (RectifyOpinion rectifyOpinion : rectifyOpinionList)
			{
				rectifyOpinion.setBasic(checkBasic);
				rectifyOpinionService.update(rectifyOpinion);
			}
		}
		CheckBasic basic = checkBasicService.getById(checkBasic.getId());
		basic.setRectifyBeginTime(checkBasic.getRectifyBeginTime());
		basic.setReviewContent(checkBasic.getReviewContent());
		basic.setRectifyYear(checkBasic.getRectifyYear());
		basic.setRectifyNum(checkBasic.getRectifyNum());
		basic.setRectifyFlag("2");//整改完成
		checkBasicService.update(basic);
		}catch(Exception e){
			e.printStackTrace();
		}
		return RELOAD;
	}
	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save()  
	{
		try{
			List<CheckResult> 	 resultList = checkBasic.getResultList();
			List<CheckSituation> checkList = checkBasic.getSituationList();
			List<Yhqd> yhqds = checkBasic.getYhqdList();
		if ("add".equalsIgnoreCase(this.flag))
		{
			checkBasic.setChecker(getLoginUser());
			checkBasic.setDeptId(this.getLoginUserDepartmentId());
			checkBasic.setDelFlag(0);
			checkBasic.setCheckTime(new Date());
			checkBasic.setResultList(null);
			checkBasic.setSituationList(null);
			checkBasic.setYhqdList(null);
			checkBasic.setCreateTime(new Date());
			checkBasic.setCreateUserID(getLoginUserId());
			checkBasicService.save(checkBasic);
		}
		else
		{
			checkBasic.setYhqdList(null);
			checkBasic.setUpdateTime(new Date());
			checkBasic.setUpdateUserID(getLoginUserId());
			checkBasicService.update(checkBasic);
		}
		//关联生产检查列表
		for (CheckResult checkResult : resultList) {
			checkResult.setBasic(checkBasic);
			checkResult.setDelFlag(0);
			checkResultService.update(checkResult);
		}
		//关联隐患列表
		if(yhqds!=null){
			for (Yhqd yhqd : yhqds) {
				yhqd.setBasic(checkBasic);
				if(isExistYh.equals("0")){
					yhqd.setDelFlag(1);
					try{
						activityManageService.endProcess(yhqd.getDefId());
					}catch(Exception e){
						System.out.println("有未开启的流程的对象结束流程"+e.getMessage());
					}
					yhqd.setEnded(-1);
				} 
				yhqdService.update(yhqd);
				if(yhqd.getPicList()!=null){
					for (PhotoPic tt : yhqd.getPicList()) {
						PhotoPic photoPic = szwxPhotoService.getById(tt.getId());
						photoPic.setTaskRemark(tt.getTaskRemark());
						photoPic.setTaskProId(yhqd.getId());
						photoPic.setDelFlag(tt.getDelFlag());
						szwxPhotoService.update(photoPic);
					}
				}
			}
		}
		//关联新的执法人员
		saveLawEnforce(lawEnforceIds);
		//关联处理检查情况集合
		if(checkList!=null){
			for (CheckSituation situation : checkList)
			{
				situation.setBasic(checkBasic);
				checkSituationService.update(situation);
			}
		}
		//checkBasicService.update(checkBasic);
		 
		//开启隐患整改流程
		 Map<String,Object> variables = new HashMap<String,Object> ();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		//deptCode = deptCode.substring(0,deptCode.length()-3);
		variables.put("deptCode", deptCode);
		variables.put("moveStatus",  "zhenggaitask");
		variables.put("flag", "zhenggai");
		for (Yhqd yhqd : yhqds) {
			if(StringUtil.isNullOrEmpty(yhqd.getDefId())){
				variables.put("workFlowModelId",  yhqd.getId());
				String defId = activityManageService.StartProcessInstance(yhqd.getId(), "checkTrouble", getLoginUserId(), variables) ;
				Yhqd tyhqd  = yhqdService.getById( yhqd.getId());
				tyhqd.setDefId(defId);
				yhqdService.update(tyhqd);
			}
		}  
	 
		}catch(Exception e){
			e.printStackTrace();
		}
		return RELOAD;
	}
	
	/**
	 * 整改隐患  2016-07-14 zhangzh
	 */
	public String doZhenggai() throws Exception
	{
		try
		{
			String zgInfo = yhqd.getZgInfo();
			if(yhqd.getZgpicList()!=null){
				for (PhotoPic tt : yhqd.getZgpicList()) {
					PhotoPic photoPic = szwxPhotoService.getById(tt.getId());
					photoPic.setTaskRemark(tt.getTaskRemark());
					photoPic.setTaskProId("ZG"+yhqd.getId());
					photoPic.setDelFlag(tt.getDelFlag());
					szwxPhotoService.update(photoPic);
				}
			}
			yhqd = yhqdService.getById(yhqd.getId());
			 //保存整改信息   
			yhqd.setDealFlag(1);
			yhqd.setPassFlag(null);
			yhqd.setZgInfo(zgInfo);
			yhqdService.update(yhqd);
			Map<String,Object> variables = new HashMap<String, Object>();
			variables.put("workFlowModelId",  yhqd.getId());
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			//deptCode = deptCode.substring(0,deptCode.length()-3);
			variables.put("deptCode", deptCode);
			variables.put("moveStatus", "yanshoutask");
			variables.put("flag", "yanshou");
			//进入下一流程
			activityManageService.passProcess(taskId, "验收整改", variables);	
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return RELOAD;
	}
	/**
	 * 整改隐患  2016-07-14 zhangzh
	 */
	public String doYanshou() throws Exception
	{
		try
		{
			Map<String,Object> variables = new HashMap<String, Object>();
			variables.put("workFlowModelId",  yhqd.getId());
			//deptCode = deptCode.substring(0,deptCode.length()-3);
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			variables.put("deptCode", deptCode);
			String passflag =String.valueOf(yhqd.getPassFlag());
			String resultContent = yhqd.getResultContent();
			yhqd = yhqdService.getById(yhqd.getId());
			//验收不通过整改，流程返回整改
			 if(passflag.equals("0")){
				 variables.put("passflag", "0");
				 variables.put("flag", "zhenggai");
				 yhqd.setPassFlag(0);
				 activityManageService.passProcess(taskId, "去整改", variables);
			 }else if (passflag.equals("1")){
				 //验收通过，结束流程
				 yhqd.setEnded(1);
				 variables.put("passflag", "1");
				 yhqd.setPassFlag(1);
				 activityManageService.passProcess(taskId, "结束流程", variables);
			 }
			String lastYsr = yhqd.getZgysr();
			String lastresultContent = yhqd.getResultContent();
			String lastYssj = yhqd.getYssj();
			if(StringUtil.isNotNullAndNotEmpty(lastYsr)){
				lastYsr+="‖" ;
			}else{
				lastYsr="" ;
			}
			lastYsr+=getLoginUser().getDisplayName();
			yhqd.setZgysr(lastYsr);
			if(StringUtil.isNotNullAndNotEmpty(lastresultContent)){
				lastresultContent+="‖" ;
			} else{
				lastresultContent="" ;
			}
			lastresultContent+=resultContent;
			yhqd.setResultContent(lastresultContent);
			if(StringUtil.isNotNullAndNotEmpty(lastYssj)){
				lastYssj+="‖" ;
			} else{
				lastYssj="" ;
			}
			lastYssj+=DateUtil.getDate(new Date(), "yyyy-MM-dd HH:mm");  
			yhqd.setYssj(lastYssj);
			yhqdService.update(yhqd);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return RELOAD;
	}
	
	
	/**
	 * 待签收任务列表
	 * 
	 * @author zhangzh
	 * @return
	 */
	public void findToClaimTaskList(){
		try{
			pagination = new Pagination(super.getRequest());
			TaskQuery taskToClaimQuery = taskService.createTaskQuery().processDefinitionKey("checkTrouble").taskCandidateUser(getLoginUserId()).active().orderByTaskId().desc();
			List<Task> taskListToClaim = taskToClaimQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
			
	        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	        for(Task task : taskListToClaim){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	String processInstanceId = task.getProcessInstanceId();
	        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	        	String moduleId = "";
	        	if(null != processInstance.getBusinessKey()){
	        		moduleId = processInstance.getBusinessKey();
	        		yhqd = yhqdService.getById(moduleId);
	        		if(yhqd!=null){
		        		map.put("id", moduleId);
		        		map.put("name", yhqd.getBasic().getCompany().getCompanyname());
		        		map.put("description", yhqd.getYhContent());
			        	map.put("task_id", task.getId());
			        	map.put("task_type", task.getName());
			        	map.put("createTime", task.getCreateTime());
			        	map.put("dueDate", task.getDueDate());
			        	map.put("owner", task.getOwner());
			        	mapList.add(map);
	        		}
	        	}
	        }
	        
			StringBuffer data = new StringBuffer("{\n");
			data.append("  \"total\":").append(mapList.size()).append(",\n");
			data.append("  \"rows\":\n");
			
			JSONArray json = JSONArray.fromObject(mapList);
			data.append(json.toString());
			data.append("  \n").append("}");	
			
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
	    	e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;
		}
	}
	
	
	/**
	 * 办理中任务列表
	 * 
	 * @author zhangzh
	 * @return
	 */
	public void findClaimedTaskList(){
		try{
			pagination = new Pagination(super.getRequest());
			TaskQuery claimedTaskQuery = taskService.createTaskQuery().processDefinitionKey("checkTrouble").taskAssignee(getLoginUserId()).active().orderByTaskId().desc();
			List<Task> claimedTaskList = claimedTaskQuery.listPage(pagination.getFirstResult(), pagination.getPageSize());		
			
	        List<HashMap<String, Object>> mapList = new ArrayList<HashMap<String, Object>>();
	        for(Task task : claimedTaskList){
	        	HashMap<String,Object> map = new HashMap<String,Object>();
	        	Date claimTime = historyService.createHistoricTaskInstanceQuery().taskId(task.getId()).singleResult().getClaimTime();
	        	String processInstanceId = task.getProcessInstanceId();
	        	ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active().singleResult();
	        	String moduleId = "";
	        	if(null != processInstance.getBusinessKey()){
	        		moduleId = processInstance.getBusinessKey();
	        		yhqd = yhqdService.getById(moduleId);
	        		if(yhqd!=null){
	        			map.put("id", moduleId);
		        		map.put("name", yhqd.getBasic().getCompany().getCompanyname());
		        		map.put("description", yhqd.getYhContent());
		        		map.put("task_id", task.getId());
		        		map.put("task_type", task.getName());
		        		map.put("createTime", task.getCreateTime());
			        	map.put("dueDate", claimTime);
			        	map.put("owner", task.getOwner());
			        	mapList.add(map);
	        		}
	        	}
	        }
	        
			StringBuffer data = new StringBuffer("{\n");
			data.append("  \"total\":").append(mapList.size()).append(",\n");
			data.append("  \"rows\":\n");
			
			JSONArray json = JSONArray.fromObject(mapList);
			data.append(json.toString());
			data.append("  \n").append("}");		
			
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
			BasalException be = new BasalException(BasalException.ERROR, e);
			throw be;			
		}
	}
	
	public void endProcess() throws IOException {
		try {
			yhqd = yhqdService.getById(yhqd.getId());
			activityManageService.endProcess(yhqd.getDefId());
			yhqd.setEnded(-1);
			yhqdService.update(yhqd);
			this.getResponse().getWriter().println("{\"result\":true}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
		}
	}
	private void saveLawEnforce(String lawEnforce){
//		  
		//去除原来关联的执法人员 
		Map map = new HashMap();
		map.put("basic", checkBasic.getId());
		List<CheckLawEnforce> oldCheckLawEnforceList =checkLawEnforceService.findListByMap(map);
		for (CheckLawEnforce checkLawEnforce : oldCheckLawEnforceList) {
				checkLawEnforce.setDelFlag(1);
				checkLawEnforceService.update(checkLawEnforce);
		}
		String [] lawEnforceS = lawEnforce.split("\\|");
		for(String userId : lawEnforceS)
		{
			User lawer = userService.findUserById(userId);
			if ( null != lawer)
			{
				CheckLawEnforce  checkLawEnforce = new CheckLawEnforce();
				checkLawEnforce.setBasic(checkBasic);
				checkLawEnforce.setCheckUser(lawer);
				checkLawEnforce.setDelFlag(0);
				checkLawEnforceService.save(checkLawEnforce);
			}
		}
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception
	{
		try
		{
			checkBasicService.deleteWithFlag(ids);
			 String[] idArray = ids.split("\\|");
				if(null != idArray)
				{
					for(String id : idArray)
					{
						CheckBasic tchecBasic = checkBasicService.getById(id);
						for (Yhqd tyhqd : tchecBasic.getYhqdList()) {
							activityManageService.endProcess(tyhqd.getDefId());
							tyhqd.setDelFlag(1);
							tyhqd.setEnded(-1);
							yhqdService.update(tyhqd);
						}
					}
				}
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}
		catch (Exception e)
		{
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	/**
	 * 生成DOC方法
	 */
	public void createSaveCheckDoc()
	{
		try
		{
			view();
			if ((null != checkBasic) && (null != checkBasic.getId()) && (null != crList) && (crList.size() > 0))
			{
				String fileDir = getSession().getServletContext().getRealPath("/");
				InputStream is = new FileInputStream(new File(fileDir + "/" + "check.docx"));
				XWPFDocument doc = new XWPFDocument(is);
				// 组建替换内容map
				Map<String, Object> params = new HashMap<String, Object>();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				params.put("checktime", df.format(checkBasic.getCheckTime()));
				params.put("companyname", checkBasic.getCompany().getCompanyname());
				params.put("address", checkBasic.getCompany().getDwdz());
				params.put("fzr", checkBasic.getCompany().getFddbr());
				params.put("phone", checkBasic.getCompany().getFddbrlxhm());
				// 首先替换word
				XwpfUtils.replaceInPara(doc, params);
				XwpfUtils.replaceInTable(doc, params);

				// 针对word 创建动态表格
				Iterator<XWPFTable> iterator = doc.getTablesIterator();
				XWPFTable table = iterator.next();
				List<XWPFTableRow> rows = table.getRows();
				int height = rows.get(0).getHeight();// 取模板行高

				// 填充第4行内容
				XWPFTableRow row4 = rows.get(3);
				List<XWPFTableCell> cells4 = row4.getTableCells();
				cells4.get(0).setText("序号");
				cells4.get(1).setText("检查内容");
				cells4.get(2).setText("检查结果");
				cells4.get(3).setText("备注");

				// 填充第5行内容
				CategoryResult result1 = crList.get(0);
				XWPFTableRow row5 = rows.get(4);
				List<XWPFTableCell> cells5 = row5.getTableCells();
				cells5.get(0).setText(result1.getIndexNum());
				cells5.get(1).setText(result1.getCategoryContent());

				// 添加后续表格
				int rowNum = 5;// 从第6行开始
				// 首先添加第一个栏目的检查结果列，单独处理
				List<ContentResult> contentList1 = result1.getContentList();
				for (int i = 0; i < contentList1.size(); i++)
				{
					// 添加一行检查结果
					table.createRow();
					XWPFTableRow newContent = rows.get(rowNum);
					newContent.setHeight(height);
					newContent.addNewTableCell();
					newContent.addNewTableCell();
					newContent.addNewTableCell();
					newContent.addNewTableCell();
					XwpfUtils.mergeCellsHorizontal(table, rowNum, 1, 3);

					// 添加内容
					List<XWPFTableCell> newCell = rows.get(rowNum).getTableCells();
					XwpfUtils.setCellText(newCell.get(0), String.valueOf(i + 1), null, 0);
					XwpfUtils.setCellText(newCell.get(1), contentList1.get(i).getContent(), null, 0);
					XwpfUtils
							.setCellText(newCell.get(4), getCheckResult(contentList1.get(i).getCheckResult()), null, 0);
					XwpfUtils.setCellText(newCell.get(5), contentList1.get(i).getRemark(), null, 0);

					// 行数自增
					rowNum++;

				}

				// 添加后面栏目和检查内容列
				for (int n = 1; n < crList.size(); n++)
				{
					// 添加一行检查栏目
					table.createRow();
					XWPFTableRow newCategory = rows.get(rowNum);
					newCategory.setHeight(height);
					newCategory.addNewTableCell();
					newCategory.addNewTableCell();
					newCategory.addNewTableCell();
					newCategory.addNewTableCell();
					XwpfUtils.mergeCellsHorizontal(table, rowNum, 1, 5);

					// 添加内容
					List<XWPFTableCell> categoryCell = rows.get(rowNum).getTableCells();
					XwpfUtils.setCellText(categoryCell.get(0), crList.get(n).getIndexNum(), null, 0);
					XwpfUtils.setCellText(categoryCell.get(1), crList.get(n).getCategoryContent(), null, 0);

					// 行数自增
					rowNum++;

					List<ContentResult> resultList = crList.get(n).getContentList();
					for (int m = 0; m < resultList.size(); m++)
					{
						// 添加一行检查结果
						table.createRow();
						XWPFTableRow newContent = rows.get(rowNum);
						newContent.setHeight(height);
						newContent.addNewTableCell();
						newContent.addNewTableCell();
						newContent.addNewTableCell();
						newContent.addNewTableCell();
						XwpfUtils.mergeCellsHorizontal(table, rowNum, 1, 3);

						// 添加内容
						List<XWPFTableCell> contentCell = rows.get(rowNum).getTableCells();
						XwpfUtils.setCellText(contentCell.get(0), String.valueOf(m + 1), null, 0);
						XwpfUtils.setCellText(contentCell.get(1), resultList.get(m).getContent(), null, 0);
						XwpfUtils.setCellText(contentCell.get(4), getCheckResult(resultList.get(m).getCheckResult()),
								null, 0);
						XwpfUtils.setCellText(contentCell.get(5), resultList.get(m).getRemark(), null, 0);

						// 行数自增
						rowNum++;
					}
				}

				// 输出word文件
				ByteArrayOutputStream ostream = new ByteArrayOutputStream();
				doc.write(ostream); // 输出字节流
				ostream.close();
				String fileName = checkBasic.getCompany().getCompanyname() + "安全生产执法检查表.docx";
				XwpfUtils.downLoad(ostream, fileName, getRequest());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 转换检查结果
	 */
	private String getCheckResult(String checkResult)
	{
		if (checkResult.equals("0"))
		{
			return "良好";
		}
		else if (checkResult.equals("1"))
		{
			return "一般";
		}
		else
		{
			return "较差";
		}
	}

	/**
	 * 生成现场检查记录表 因XWPFDocument操作换行较为复杂，因此采用HWPFDocument
	 */
	public void createFieldCheckDoc()
	{
		try
		{
			view();
			if ((null != checkBasic) && (null != checkBasic.getId()))
			{

				String fileDir = getSession().getServletContext().getRealPath("/");
				InputStream is = new FileInputStream(new File(fileDir + "/" + "fieldCheck.doc"));
				HWPFDocument hdt = new HWPFDocument(is);
				// 替换读取到的word模板内容的指定字符
				Range range = hdt.getRange();
				// 组建替换内容map
				Map<String, String> params = new HashMap<String, String>();
				params.put("${companyname}", checkBasic.getCompany().getCompanyname());
				params.put("${address}", checkBasic.getCompany().getDwdz());
				params.put("${fzr}", checkBasic.getCompany().getFddbr());
				params.put("${fzrphone}", checkBasic.getCompany().getFddbrlxhm());
				params.put("${checkPlace}", checkBasic.getCheckPlace());
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
				params.put("${checkSta}", DateUtils.DateToString(checkBasic.getCheckSta(), sdf1));
				params.put("${checkEnd}", DateUtils.DateToString(checkBasic.getCheckEnd(), sdf1));
				params.put("${checkOrganization}", checkBasic.getCheckOrganization());
				params.put("${lawEnforceName}", lawEnforceName);
				params.put("${idNum}", idNum);
				// 处理执法人员和证件号码

				// 处理检查情况
				String checkSituation = "";
				List<CheckSituation> situationList = checkBasic.getSituationList();
				int num = 1;
				for (CheckSituation situation : situationList)
				{
					checkSituation = checkSituation + "	" + NumberFormat.foematInteger(num) + "、"
							+ situation.getDiscreption() + "。" + String.valueOf((char) 11);
					num++;
				}
				params.put("${checkSituation}", checkSituation);

				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
				params.put("${nowDate}", DateUtils.DateToString(new Date(), sdf2));

				// 替换word
				// 将填写内容写入Word文档
				for (Map.Entry<String, String> entry : params.entrySet())
				{
					range.replaceText(entry.getKey(), (entry.getValue() != null ? entry.getValue().trim() : ""));
				}

				// 输出word文件
				ByteArrayOutputStream ostream = new ByteArrayOutputStream();
				hdt.write(ostream); // 输出字节流
				ostream.close();
				String fileName = checkBasic.getCompany().getCompanyname() + "安全生产行政执法文书.doc";
				XwpfUtils.downLoad(ostream, fileName, getRequest());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 生成责令限期整改指令书 因XWPFDocument操作换行较为复杂，因此采用HWPFDocument
	 */
	public void createRectifyCheckDoc()
	{
		try
		{
			view();
			if ((null != checkBasic) && (null != checkBasic.getId()))
			{

				String fileDir = getSession().getServletContext().getRealPath("/");
				InputStream is = new FileInputStream(new File(fileDir + "/" + "rectifyCheck.doc"));
				HWPFDocument hdt = new HWPFDocument(is);
				// 替换读取到的word模板内容的指定字符
				Range range = hdt.getRange();
				// 组建替换内容map
				Map<String, String> params = new HashMap<String, String>();
				params.put("${companyname}", checkBasic.getCompany().getCompanyname());
				params.put("${rectifyNum}", checkBasic.getRectifyNum());
				// params.put("rectifyState",
				// checkBasic.getRectifyState());
				params.put("${rectifyTerm}", checkBasic.getRectifyTerm());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				params.put("${rectifyDate}", DateUtils.DateToString(checkBasic.getRectifyDate(), sdf));
				params.put("${nowDate}", DateUtils.DateToString(new Date(), sdf));
				params.put("${rectifyYear}", checkBasic.getRectifyYear() != null ? checkBasic.getRectifyYear() : "");
				params.put("${rectifyNum}", checkBasic.getRectifyNum() != null ? checkBasic.getRectifyNum() : "");

				// 处理整改问题
				String rectifyState = "";
				List<CheckSituation> rectifyList = checkBasic.getRectifyList();
				int num = 1;
				for (CheckSituation situation : rectifyList)
				{
					rectifyState = rectifyState + "	  " + NumberFormat.foematInteger(num) + "、"
							+ situation.getDiscreption() + "。" + String.valueOf((char) 11);
					num++;
				}
				params.put("${rectifyState}", rectifyState);

				// 替换word
				// 将填写内容写入Word文档
				for (Map.Entry<String, String> entry : params.entrySet())
				{
					range.replaceText(entry.getKey(), (entry.getValue() != null ? entry.getValue().trim() : ""));
				}

				// 输出word文件
				ByteArrayOutputStream ostream = new ByteArrayOutputStream();
				hdt.write(ostream); // 输出字节流
				ostream.close();
				String fileName = checkBasic.getCompany().getCompanyname() + "责令限期整改指令书.doc";
				XwpfUtils.downLoad(ostream, fileName, getRequest());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 生成整改复查意见书
	 */
	public void createRectifyOpinionDoc()
	{
		try
		{
			view();
			if ((null != checkBasic) && (null != checkBasic.getId()))
			{

				String fileDir = getSession().getServletContext().getRealPath("/");
				InputStream is = new FileInputStream(new File(fileDir + "/" + "rectifyOpinion.doc"));
				HWPFDocument hdt = new HWPFDocument(is);
				Range range = hdt.getRange();
				// 组建替换内容map
				Map<String, String> params = new HashMap<String, String>();
				params.put("${companyname}", checkBasic.getCompany().getCompanyname());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
				params.put("${rectifyBeginTime}", DateUtils.DateToString(checkBasic.getRectifyBeginTime(), sdf));
				params.put("${reviewContent}", checkBasic.getReviewContent() != null ? checkBasic.getReviewContent()
						: "");
				params.put("${rectifyYear}", checkBasic.getRectifyYear() != null ? checkBasic.getRectifyYear() : "");
				params.put("${rectifyNum}", checkBasic.getRectifyNum() != null ? checkBasic.getRectifyNum() : "");

				// 处理检查情况
				String rectifyOpinion = "";
				List<RectifyOpinion> rectifyOpinionList = checkBasic.getRectifyOpinionList();
				int num = 1;
				for (RectifyOpinion opinion : rectifyOpinionList)
				{
					rectifyOpinion = rectifyOpinion + "	  " + NumberFormat.foematInteger(num) + "、"
							+ opinion.getOpinion() + "。" + String.valueOf((char) 11);
					num++;
				}
				params.put("${rectifyOpinion}", rectifyOpinion);

				params.put("${nowDate}", DateUtils.DateToString(new Date(), sdf));

				// 替换word
				// 将填写内容写入Word文档
				for (Map.Entry<String, String> entry : params.entrySet())
				{
					range.replaceText(entry.getKey(), (entry.getValue() != null ? entry.getValue().trim() : ""));
				}

				// 输出word文件
				ByteArrayOutputStream ostream = new ByteArrayOutputStream();
				hdt.write(ostream); // 输出字节流
				ostream.close();
				String fileName = checkBasic.getCompany().getCompanyname() + "整改复查意见书.doc";
				XwpfUtils.downLoad(ostream, fileName, getRequest());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 生成责令限期整改指令书套打模板
	 */
	public void createTDRectifyCheckDoc()
	{
		try
		{
			view();
			if ((null != checkBasic) && (null != checkBasic.getId()))
			{

				String fileDir = getSession().getServletContext().getRealPath("/");
				InputStream is = new FileInputStream(new File(fileDir + "/" + "tdRectifyCheck.doc"));
				HWPFDocument hdt = new HWPFDocument(is);
				// 替换读取到的word模板内容的指定字符
				Range range = hdt.getRange();
				// 组建替换内容map
				Map<String, String> params = new HashMap<String, String>();
				params.put("${companyname}", checkBasic.getCompany().getCompanyname());
				params.put("${rectifyNum}", checkBasic.getRectifyNum());
				// params.put("rectifyState",
				// checkBasic.getRectifyState());
				params.put("${rectifyTerm}", checkBasic.getRectifyTerm());
				params.put("${rectifyYear}", checkBasic.getRectifyYear() != null ? checkBasic.getRectifyYear() : "");
				params.put("${rectifyNum}", checkBasic.getRectifyNum() != null ? checkBasic.getRectifyNum() : "");

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String rectifyDate = DateUtils.DateToString(checkBasic.getRectifyDate(), sdf);
				if (null != rectifyDate && rectifyDate.length() == 8)
				{
					params.put("${year1}", rectifyDate.substring(0, 4));
					params.put("${month1}", rectifyDate.substring(4, 6));
					params.put("${day1}", rectifyDate.substring(6));
				}
				else
				{
					params.put("${year1}", "");
					params.put("${month1}", "");
					params.put("${day1}", "");
				}
				String nowDate = DateUtils.DateToString(new Date(), sdf);
				params.put("${year2}", nowDate.substring(0, 4));
				params.put("${month2}", nowDate.substring(4, 6));
				params.put("${day2}", nowDate.substring(6));

				// 处理整改问题
				String rectifyState = "";
				List<CheckSituation> rectifyList = checkBasic.getRectifyList();
				int num = 1;
				for (CheckSituation situation : rectifyList)
				{
					rectifyState = rectifyState + "  " + NumberFormat.foematInteger(num) + "、"
							+ situation.getDiscreption() + "。" + String.valueOf((char) 11);
					num++;
				}
				params.put("${rectifyState}", rectifyState);

				// 替换word
				for (Map.Entry<String, String> entry : params.entrySet())
				{
					range.replaceText(entry.getKey(), (entry.getValue() != null ? entry.getValue().trim() : ""));
				}

				// 输出word文件
				ByteArrayOutputStream ostream = new ByteArrayOutputStream();
				hdt.write(ostream); // 输出字节流
				ostream.close();
				String fileName = checkBasic.getCompany().getCompanyname() + "责令限期整改指令书套打模板.doc";
				XwpfUtils.downLoad(ostream, fileName, getRequest());
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public String getIds()
	{
		return ids;
	}

	public void setIds(String ids)
	{
		this.ids = ids;
	}

	public Pagination getPagination()
	{
		return pagination;
	}

	public void setPagination(Pagination pagination)
	{
		this.pagination = pagination;
	}

	public CheckBasic getCheckBasic()
	{
		return this.checkBasic;
	}

	public void setCheckBasic(CheckBasic checkBasic)
	{
		this.checkBasic = checkBasic;
	}

	public String getFlag()
	{
		return flag;
	}

	public void setFlag(String flag)
	{
		this.flag = flag;
	}

	public Date getQueryCheckTimeStart()
	{
		return this.queryCheckTimeStart;
	}

	public void setQueryCheckTimeStart(Date queryCheckTimeStart)
	{
		this.queryCheckTimeStart = queryCheckTimeStart;
	}

	public Date getQueryCheckTimeEnd()
	{
		return this.queryCheckTimeEnd;
	}

	public void setQueryCheckTimeEnd(Date queryCheckTimeEnd)
	{
		this.queryCheckTimeEnd = queryCheckTimeEnd;
	}

	public List<CategoryResult> getCrList()
	{
		return crList;
	}

	public void setCrList(List<CategoryResult> crList)
	{
		this.crList = crList;
	}

	public List<CheckSituation> getSituationList()
	{
		return situationList;
	}

	public void setSituationList(List<CheckSituation> situationList)
	{
		this.situationList = situationList;
	}

	public List<RectifyOpinion> getOpinionList()
	{
		return opinionList;
	}

	public void setOpinionList(List<RectifyOpinion> opinionList)
	{
		this.opinionList = opinionList;
	}

	public String getLawEnforceName()
	{
		return lawEnforceName;
	}

	public void setLawEnforceName(String lawEnforceName)
	{
		this.lawEnforceName = lawEnforceName;
	}

	public String getIdNum()
	{
		return idNum;
	}

	public void setIdNum(String idNum)
	{
		this.idNum = idNum;
	}

	public List<CheckSituation> getRectifyList()
	{
		return rectifyList;
	}

	public void setRectifyList(List<CheckSituation> rectifyList)
	{
		this.rectifyList = rectifyList;
	}

	/**  
	 * 获取filedata  
	 * @return filedata filedata  
	 */
	public List<File> getFiledata() {
		return Filedata;
	}

	/**  
	 * 设置filedata  
	 * @param filedata filedata  
	 */
	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	/**  
	 * 获取filedataFileName  
	 * @return filedataFileName filedataFileName  
	 */
	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	/**  
	 * 设置filedataFileName  
	 * @param filedataFileName filedataFileName  
	 */
	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	/**  
	 * 获取filedataContentType  
	 * @return filedataContentType filedataContentType  
	 */
	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	/**  
	 * 设置filedataContentType  
	 * @param filedataContentType filedataContentType  
	 */
	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

	/**  
	 * 获取lawEnforceIds  
	 * @return lawEnforceIds lawEnforceIds  
	 */
	public String getLawEnforceIds() {
		return lawEnforceIds;
	}

	/**  
	 * 设置lawEnforceIds  
	 * @param lawEnforceIds lawEnforceIds  
	 */
	public void setLawEnforceIds(String lawEnforceIds) {
		this.lawEnforceIds = lawEnforceIds;
	}

	/**  
	 * 获取picName  
	 * @return picName picName  
	 */
	public String getPicName() {
		return picName;
	}

	/**  
	 * 设置picName  
	 * @param picName picName  
	 */
	public void setPicName(String picName) {
		this.picName = picName;
	}

	/**  
	 * 获取photoPics  
	 * @return photoPics photoPics  
	 */
	public List<PhotoPic> getPhotoPics() {
		return photoPics;
	}

	/**  
	 * 设置photoPics  
	 * @param photoPics photoPics  
	 */
	public void setPhotoPics(List<PhotoPic> photoPics) {
		this.photoPics = photoPics;
	}

	/**  
	 * 获取isExistYh  
	 * @return isExistYh isExistYh  
	 */
	public String getIsExistYh() {
		return isExistYh;
	}

	/**  
	 * 设置isExistYh  
	 * @param isExistYh isExistYh  
	 */
	public void setIsExistYh(String isExistYh) {
		this.isExistYh = isExistYh;
	}

	/**  
	 * 获取yhqd  
	 * @return yhqd yhqd  
	 */
	public Yhqd getYhqd() {
		return yhqd;
	}

	/**  
	 * 设置yhqd  
	 * @param yhqd yhqd  
	 */
	public void setYhqd(Yhqd yhqd) {
		this.yhqd = yhqd;
	}

	/**  
	 * 获取taskId  
	 * @return taskId taskId  
	 */
	public String getTaskId() {
		return taskId;
	}

	/**  
	 * 设置taskId  
	 * @param taskId taskId  
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
