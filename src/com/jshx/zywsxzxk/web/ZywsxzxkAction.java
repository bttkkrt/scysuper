/**
 * Class Name: ZywsxzxkAction
 * Class Description：职业病危害预评价报告
 */
package com.jshx.zywsxzxk.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.aqscfk.entity.Aqscxzcfglb;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;
import com.jshx.whpclsc.service.WhpClscService;
import com.jshx.zywsxzxk.entity.Zywsxzxk;
import com.jshx.zywsxzxk.service.ZywsxzxkService;

public class ZywsxzxkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zywsxzxk zywsxzxk = new Zywsxzxk();

	/**
	 * 业务类
	 */
	@Autowired
	private ZywsxzxkService zywsxzxkService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private WhpClscService whpClscService;
	@Autowired
	private UserService userService;
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private String picName;
	private String type;
	
	private Date createTimeStart;

	private Date createTimeEnd;
	
	private List<PhotoPic> picList1 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList2 = new ArrayList<PhotoPic>();
	
	private List<PhotoPic> picList3 = new ArrayList<PhotoPic>();
	private String[] shenheList ;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	
	private String ysrqStart;
	
	private String ysrqEnd;
	
	private String sprqStart;
	
	private String sprqEnd;
	
	private String jsrqStart;
	
	private String jsrqEnd;
	
	private String sjrqStart;
	
	private String sjrqEnd;
	
	@Autowired
	private ReviewLogService reviewLogService;
	private String deptCodeLenth;
	private String ifzsqy;
	private String xjshState;//县级审核状态
	private String xjBack;//县级审核备注
	private String sjshState;//市级审核状态
	private String sjBack;//市级审核备注
	private String deptflag;
	private String deptcode;
	private String deptrole;
	private String tempDeptCode;
	

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 当前登录人id
	 * @return
	 */
	private String createUserID;
	
	private List<User> userList = new ArrayList<User>();
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	private Aqscxzcfglb aqscxzcfglb = new Aqscxzcfglb();
	/**
	 * 初始化安全附件情况列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public String init()
	{
		createUserID = this.getLoginUserId();
		flag = "0";
//		String deptCode = this.getLoginUserDepartment().getDeptCode();
//		if(deptCode.startsWith("002002"))
//		{
//			List<UserRight> list = this.getLoginUser().getUserRoles();
//			for(UserRight u:list)
//			{
//				String rolecode = u.getRole().getRoleCode();
//				if(rolecode.equals("A06"))
//				{
//					flag = "1";
//					break;
//				}
//			}
//		}
//		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
//		{
//			flag = "2";
//		}
		
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		String deptRole = this.getLoginUser().getDeptRole();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		//根据部门长度进行数据量权限划分
		if(deptCode.length() == countyDeptCodeLength && null != deptRole && deptRole.contains(SysPropertiesUtil.getProperty("zywhCode"))){//县级部门
			//String tempDeptCode = this.getLoginUserDepartment().getDeptCode();
			//tempDeptCode = tempDeptCode.substring(0, countyDeptCodeLength-3);
			//paraMap.put("deptCode",tempDeptCode+ "%");
			flag = "1";
		}
		if(deptCode.length() == cityDeptCodeLength && null != deptRole && deptRole.contains(SysPropertiesUtil.getProperty("zywhCode"))){//市级部门
			//String tempDeptCode = this.getLoginUserDepartment().getDeptCode();
			//tempDeptCode = tempDeptCode.substring(0, countyDeptCodeLength-3);
			//paraMap.put("deptCode",tempDeptCode+ "%");
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		
		//////////////
		deptflag="99";
		if(null != deptRole && deptRole.equals("21")){//企业人员
			deptflag = "2";
		}else{
			//根据部门角色进行查询条件过滤
			if(null != deptRole && deptRole.contains(SysPropertiesUtil.getProperty("zywhCode"))){//部门如果是危化品
				deptflag = "1";
			} else {
				deptflag = "0";
			}
		}
		
		
		if("1".equals(type))
		{
			return "success1";
		}
		else if("2".equals(type))
		{
			return "success2";
		}
		else if("3".equals(type))
		{
			return "success3";
		}
		else
		{
			return "success4";
		}
	}
	
	public String inits()
	{
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
		    
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != createTimeStart){
				paraMap.put("startCreateTime", createTimeStart);
			}

			if (null != createTimeEnd){
				paraMap.put("endCreateTime", createTimeEnd);
			}
			
			aqscxzcfglb = zywsxzxkService.getWhpZywsXkzByMap(paraMap);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
		    
		if(null != zywsxzxk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zywsxzxk.getSzzid()) && (0 < zywsxzxk.getSzzid().trim().length())){
				paraMap.put("szzid", zywsxzxk.getSzzid().trim());
			}

			if ((null != zywsxzxk.getQymc()) && (0 < zywsxzxk.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zywsxzxk.getQymc().trim() + "%");
			}

			if ((null != zywsxzxk.getState()) && (0 < zywsxzxk.getState().trim().length())){
				if(!"3".equals(zywsxzxk.getState()))
				{
					paraMap.put("state", zywsxzxk.getState().trim());
				}
			}
			if ((null != zywsxzxk.getType()) && (0 < zywsxzxk.getType().trim().length())){
				paraMap.put("type", zywsxzxk.getType().trim());
			}
			
			if ((null != zywsxzxk.getSzc()) && (0 < zywsxzxk.getSzc().trim().length())){
				paraMap.put("szc", zywsxzxk.getSzc().trim());
			}
			
			
			if ((null != zywsxzxk.getHyfl()) && (0 < zywsxzxk.getHyfl().trim().length())){
				paraMap.put("hyfl", zywsxzxk.getHyfl().trim());
			}
			if ((null != zywsxzxk.getPjjg()) && (0 < zywsxzxk.getPjjg().trim().length())){
				paraMap.put("pjjg", "%" + zywsxzxk.getPjjg().trim() + "%");
			}
			if ((null != zywsxzxk.getSpbh()) && (0 < zywsxzxk.getSpbh().trim().length())){
				paraMap.put("spbh", zywsxzxk.getSpbh().trim());
			}
			if ((null != zywsxzxk.getYszj()) && (0 < zywsxzxk.getYszj().trim().length())){
				paraMap.put("yszj", zywsxzxk.getYszj().trim());
			}
			if ((null != zywsxzxk.getXmxz()) && (0 < zywsxzxk.getXmxz().trim().length())){
				paraMap.put("xmxz", zywsxzxk.getXmxz().trim());
			}
			if ((null != zywsxzxk.getProjectContent()) && (0 < zywsxzxk.getProjectContent().trim().length())){
				paraMap.put("projectContent", "%"+zywsxzxk.getProjectContent().trim()+"%");
			}
		}
		
		if (null != ysrqStart){
			paraMap.put("ysrqStart", ysrqStart);
		}

		if (null != ysrqEnd){
			paraMap.put("ysrqEnd", ysrqEnd);
		}

		if (null != sprqStart){
			paraMap.put("sprqStart", sprqStart);
		}

		if (null != sprqEnd){
			paraMap.put("sprqEnd", sprqEnd);
		}
		if (null != jsrqStart){
			paraMap.put("jsrqStart", jsrqStart);
		}

		if (null != jsrqEnd){
			paraMap.put("jsrqEnd", jsrqEnd);
		}
		if (null != sjrqStart){
			paraMap.put("sjrqStart", sjrqStart);
		}

		if (null != sjrqEnd){
			paraMap.put("sjrqEnd", sjrqEnd);
		}
		
		
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇安监中队
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("szzid",deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		pagination = zywsxzxkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zywsxzxk)&&(null != zywsxzxk.getId()))
		{
			zywsxzxk = zywsxzxkService.getById(zywsxzxk.getId());
			if(zywsxzxk.getLinkid() == null || "".equals(zywsxzxk.getLinkid()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				zywsxzxk.setLinkid(linkId);
			}
			else
			{
				Map map = new HashMap();
				map.put("taskProId",zywsxzxk.getLinkid());
				map.put("picType","zywsxzxk1");
				picList1 = szwxPhotoService.findPicPath(map);//获取检查文书
				map.put("picType","zywsxzxk2");
				picList2 = szwxPhotoService.findPicPath(map);//获取复查文书
				map.put("picType","zywsxzxk3");
				picList3 = szwxPhotoService.findPicPath(map);//获取整改方案	
			}
			if(zywsxzxk.getShenhe() != null && !"".equals(zywsxzxk.getShenhe()))
			{
				shenheList = zywsxzxk.getShenhe().split("#");
			}
		}
		else
		{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			zywsxzxk.setLinkid(linkId);
		}
		Map m = new HashMap();
		List<String> list = new ArrayList<String>();
	    list.add("A19");
	    list.add("A17");
	    list.add("A05");
	    m.put("roleCode", list);
	    userList = whpClscService.getUserListByMap(m);
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
	    String deptCode = this.getLoginUserDepartment().getDeptCode();
	    if(deptCode.length() == countyDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
	    }else if(deptCode.length() == cityDeptCodeLength){
	    	tempDeptCode = "1";
	    }else if(deptCode.length() == townDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, townDeptCodeLength-6);
	    }

	    setType(type);
		/**
		 * hyc 2014-12-11
		 * 获得登陆人的部门长度，方便jsp页面判断审核是市级还是县级还是镇级
		 * 获得登陆用户名
		 */
		deptCodeLenth = this.getLoginUserDepartment().getDeptCode().length()+"";
		/**
		 * end
		 */
		/**
		 * 查询审核日志表
		 */
		if(zywsxzxk.getIfzsqy() != null && zywsxzxk.getIfzsqy().equals("1")){
			ifzsqy = "1";
		}else{
			ifzsqy = "0";
		}
		
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String dutyFlag = "";

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		paraMap.put("itemId", zywsxzxk.getId());
		paraMap.put("itemType", "1");
		pagination = reviewLogService.findByPage(pagination, paraMap);
		List reviewList = pagination.getList();
		if(reviewList != null && reviewList.size() > 0){
			for(int i=0;i<reviewList.size();i++){
				ReviewLog reviewLog = (ReviewLog) reviewList.get(i);
				dutyFlag = reviewLog.getDutyFlag();
				if(dutyFlag.equals("2")){//县级领导
					xjshState = reviewLog.getState();
					xjBack = reviewLog.getRemark();
				}else if(dutyFlag.equals("1")){//市级领导
					sjshState = reviewLog.getState();
					sjBack = reviewLog.getRemark();
				}
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
		if(zywsxzxk != null && zywsxzxk.getQyid() != null && !"".equals(zywsxzxk.getQyid()))
		{
			Map map = new HashMap();
			map.put("companyId", zywsxzxk.getQyid());
			CompanyBackUp company = companyService.getCompanyBackupById(map);
			zywsxzxk.setDutyFlag("0");
			zywsxzxk.setIfzsqy(company.getIfzsqy());
			zywsxzxk.setZsqytype(company.getZsqytype());
			if(!zywsxzxk.getQymc().equals(company.getCompanyname()))
			{
				zywsxzxk.setQyid(null);
				Department dept = deptService.findDeptByDeptCode(zywsxzxk.getSzzid());
				zywsxzxk.setSzzid(dept.getDeptCode());
				zywsxzxk.setSzzname(dept.getDeptName());
			}
			else
			{
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				zywsxzxk.setSzzid(dept.getDeptCode());
				zywsxzxk.setSzzname(dept.getDeptName());
				zywsxzxk.setQyid(company.getId());
				zywsxzxk.setQymc(company.getCompanyname());
				zywsxzxk.setQylx(company.getQylx());
				zywsxzxk.setHyfl(company.getHyfl());
				zywsxzxk.setQygm(company.getQygm());
				zywsxzxk.setQyzclx(company.getQyzclx());
				zywsxzxk.setSzc(company.getSzc());
				zywsxzxk.setSzcname(company.getSzcname());
			}
		}
		else if(zywsxzxk.getSzzid() != null && !"".equals(zywsxzxk.getSzzid()))
		{
			Department dept = deptService.findDeptByDeptCode(zywsxzxk.getSzzid());
			zywsxzxk.setSzzid(dept.getDeptCode());
			zywsxzxk.setSzzname(dept.getDeptName());
		}
		zywsxzxk.setState("0");
		//User user = userService.findUserById(zywsxzxk.getShuserid());
		//zywsxzxk.setShusername(user.getDisplayName());
		if ("add".equalsIgnoreCase(this.flag)){
			zywsxzxk.setDeptId(this.getLoginUserDepartmentId());
			zywsxzxk.setDelFlag(0);
			zywsxzxk.setCreateUserID(this.getLoginUserId());
			zywsxzxk.setCreateTime(new Date());
			zywsxzxkService.save(zywsxzxk);
			
			//hanxc 20141211 生成待审核任务 start
			ReviewLog newReviewLog = new ReviewLog();
			if(null != zywsxzxk.getIfzsqy() && zywsxzxk.getIfzsqy().equals("1")){
				newReviewLog.setDutyFlag("1");//市级部门审核任务
			}else{
				newReviewLog.setDutyFlag("2");//县级部门审核任务
			}
			newReviewLog.setState("0");
			newReviewLog.setItemId(zywsxzxk.getId());
			newReviewLog.setItemType("1");//企业信息类型：type=1 
			newReviewLog.setStartTime(new Date());
			reviewLogService.saveNewTask(newReviewLog);
		}else{
			zywsxzxkService.update(zywsxzxk);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zywsxzxkService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 初始化审核页面
	 * 陆婷
	 * 2013-12-11
	 * @return
	 * @throws Exception
	 */
	public String shenhe() throws Exception
	{
		view();
		return SUCCESS;
	}
	
	/**
	 * 保存审核结果
	 * 陆婷
	 * 2013-12-12
	 * @return
	 */
	public String shenhesave()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String state = zywsxzxk.getState();
		String remark = zywsxzxk.getRemark();
		String a = "";
		if((null != zywsxzxk)&&(null != zywsxzxk.getId()))
		{
			Zywsxzxk g = zywsxzxkService.getById(zywsxzxk.getId());
			g.setState(state);
			g.setRemark(remark);
			/**
			if("2".equals(state))
			{
				a = "审核不通过";
				g.setState(state);
				g.setRemark(remark);
			}
			else if("3".equals(state))
			{
				a = "审核通过";
				String isfinish = zywsxzxk.getIsfinish();
				if("0".equals(isfinish))
				{
					g.setState("1");
					String shuserid = zywsxzxk.getShuserid();
					User user = userService.findUserById(shuserid);
					g.setShuserid(shuserid);
					g.setShusername(user.getDisplayName());
				}
				else
				{
					g.setState(state);
					g.setRemark(remark);
				}
				
			}
			String ss = this.getLoginUser().getDisplayName()+"于" + sdf.format(new Date()) + "进行了审核，审核结果为：["+a + "],备注为：[" + remark+"]";
			String shenhe = g.getShenhe();
			if(shenhe == null || "".equals(shenhe))
			{
				shenhe = ss;
			}
			else
			{
				shenhe = shenhe + "#" + ss ;
			}
			g.setShenhe(shenhe);
			**/
			
			/**
			 * 安监领导审批信息
			 */
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 start
			String dutyFlag = "0";
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"))){//市级部门
				dutyFlag = "1";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"))){//县级部门
				dutyFlag = "2";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"))){//镇级部门
				dutyFlag = "3";
			}else if(deptCode.length() == Integer.parseInt(SysPropertiesUtil.getProperty("villageDeptCodeLength"))){//村级部门
				dutyFlag = "4";
			}
			g.setDutyFlag(dutyFlag);
			
			Pagination page = new Pagination(this.getRequest());
			Map<String, Object> tempParaMap = new HashMap<String, Object>();
			tempParaMap.put("itemId", g.getId());
			tempParaMap.put("dutyFlag", dutyFlag);
			page = reviewLogService.findByPage(page, tempParaMap);
			List rlList = page.getList();
			if(!rlList.isEmpty()){ 
				ReviewLog reviewLog = (ReviewLog)rlList.get(0);
				reviewLog.setItemType("1");
				reviewLog.setState(g.getState());
				reviewLog.setUserId(this.getLoginUserId());
				reviewLog.setUserName(this.getLoginUser().getDisplayName());
				reviewLog.setUserDeptCode(deptCode);
				reviewLog.setEndTime(new Date());
				reviewLog.setRecord("");
				reviewLog.setRemark(g.getRemark());
				reviewLogService.saveReviewLogAndSetNextTask(reviewLog);
			}
			//hanxc 20141211 修改审批流程，该一级审批为三级审批 end
			
			zywsxzxkService.update(g);
		}
		return RELOAD;
	}
	/**
	 * 跳转到图片导入界面  lj  2013-07-18
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 图片保存 lj  2013-07-18
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
			root = root.replace("webapps","virtualdir/upload");
			destFName.append(root).append("file/zywsxzxk/");
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
			taskPic.setPicName("zywsxzxk/"+ imgName);
			String[] ts = type.split(",");
			if(ts.length>=2){
				taskPic.setPicType(ts[0]);
				taskPic.setTaskProId(ts[1]);
			}
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", imgName);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	 * 删除信息图片
	 */
	public String deleteImage() throws Exception{
	    try{
	    	szwxPhotoService.deleteImageWithFlag(picName);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 图片 文字 另存为功能 
	 *  李军 2013-08-15
	 */
	public void saveFile()
  	{
  		try
  		{//根据附件id获取附件对象
			PhotoPic photoPic = szwxPhotoService.getById(picName);
  			String filePath = new String();
  			filePath = "virtualdir/upload/file/";
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			path = path.substring(0,path.indexOf("webapps"));
  			File fis = new File(path + filePath + photoPic.getPicName());
  			System.out.print("图片的地址是："+path + filePath + picName);
  			if (fis.exists()) {
  				InputStream in = new FileInputStream(fis);

	        String browName = new String();
	        browName = URLEncoder.encode(photoPic.getFileName(), "UTF-8");
	        String clientInfo = getRequest().getHeader("User-agent");
	        if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
	          if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
	            browName = new String((photoPic.getFileName()).getBytes("GBK"), "ISO-8859-1");
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
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
	
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zywsxzxk.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("相城区安监局职业卫生行政许可会审记录");
			sheet.setColumnWidth(0, 10000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 10000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
	        sheet.setColumnWidth(9, 10000);
	        sheet.setColumnWidth(10, 10000);
	        sheet.setColumnWidth(11, 10000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("相城区安监局职业卫生行政许可会审记录");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 11)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(23*20));
	        HSSFCell ccl0 = r3.createCell(0);
	        ccl0.setCellValue("企业名称");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("所在镇");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("申请材料是否齐全");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("乡镇预审意见");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(4);
	        ccl4.setCellValue("签字领导");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(5);
	        ccl5.setCellValue("现场核查部门");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("核查结论");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("本次领证情况");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r3.createCell(8);
	        ccl8.setCellValue("是否涉及存储");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r3.createCell(9);
	        ccl9.setCellValue("材料审查情况");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r3.createCell(10);
	        ccl10.setCellValue("行政许可建议");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r3.createCell(11);
	        ccl11.setCellValue("局会审记录");
	        ccl11.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        
	        Map<String, Object> paraMap = new HashMap<String, Object>();
	        
	        if(flag == null || "".equals(flag))
    		{
	        	zywsxzxk = (Zywsxzxk) getSessionAttribute("zywsxzxk");
    		}
	        if(null != zywsxzxk){
	        	setSessionAttribute("zywsxzxk", zywsxzxk);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != zywsxzxk.getSzzid()) && (0 < zywsxzxk.getSzzid().trim().length())){
					paraMap.put("szzid", zywsxzxk.getSzzid().trim());
				}

				if ((null != zywsxzxk.getQymc()) && (0 < zywsxzxk.getQymc().trim().length())){
					paraMap.put("qymc", "%" + zywsxzxk.getQymc().trim() + "%");
				}

				if ((null != zywsxzxk.getState()) && (0 < zywsxzxk.getState().trim().length())){
					if(!"3".equals(zywsxzxk.getState()))
					{
						paraMap.put("state", zywsxzxk.getState().trim());
					}
				}
				if ((null != zywsxzxk.getType()) && (0 < zywsxzxk.getType().trim().length())){
					paraMap.put("type", zywsxzxk.getType().trim());
				}
				
				if ((null != zywsxzxk.getSzc()) && (0 < zywsxzxk.getSzc().trim().length())){
					paraMap.put("szc", zywsxzxk.getSzc().trim());
				}
			}
			if (null != createTimeStart){
				paraMap.put("startCreateTime", createTimeStart);
				setSessionAttribute("createTimeStart", createTimeStart);
			}

			if (null != createTimeEnd){
				paraMap.put("endCreateTime", createTimeEnd);
				setSessionAttribute("createTimeEnd", createTimeEnd);
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
			{
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				paraMap.put("qyid", company.getId());
			}
			else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇安监中队
			{
				if(deptCode.length() == 12)
				{
					paraMap.put("szzid",deptCode);
				}
				else
				{
					paraMap.put("szc", deptCode);
				}
			}
			
			List<Zywsxzxk> list = zywsxzxkService.findZywsxzxk(paraMap);
			int num = 2;
			for(Zywsxzxk zywsxzxk:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(zywsxzxk.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(zywsxzxk.getSzzname());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue((zywsxzxk.getIsCaiLiao() != null && zywsxzxk.getIsCaiLiao().equals("1"))?"是":"否");
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(zywsxzxk.getXzysyj());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(zywsxzxk.getQzld());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(zywsxzxk.getXchcbm());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(zywsxzxk.getHcjl());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(zywsxzxk.getBclzqk());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue((zywsxzxk.getIsCunChu() != null && zywsxzxk.getIsCunChu().equals("1"))?"是":"否");
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(zywsxzxk.getClscqk());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(zywsxzxk.getXzxkjy());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(zywsxzxk.getJhsjl());
		        ce11.setCellStyle(c);
		        num++;
			}
	        
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	public Zywsxzxk getZywsxzxk(){
		return this.zywsxzxk;
	}

	public void setZywsxzxk(Zywsxzxk zywsxzxk){
		this.zywsxzxk = zywsxzxk;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
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

	public List<PhotoPic> getPicList3() {
		return picList3;
	}

	public void setPicList3(List<PhotoPic> picList3) {
		this.picList3 = picList3;
	}

	public String[] getShenheList() {
		return shenheList;
	}

	public void setShenheList(String[] shenheList) {
		this.shenheList = shenheList;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String getYsrqStart() {
		return ysrqStart;
	}

	public void setYsrqStart(String ysrqStart) {
		this.ysrqStart = ysrqStart;
	}

	public String getYsrqEnd() {
		return ysrqEnd;
	}

	public void setYsrqEnd(String ysrqEnd) {
		this.ysrqEnd = ysrqEnd;
	}

	public String getSprqStart() {
		return sprqStart;
	}

	public void setSprqStart(String sprqStart) {
		this.sprqStart = sprqStart;
	}

	public String getSprqEnd() {
		return sprqEnd;
	}

	public void setSprqEnd(String sprqEnd) {
		this.sprqEnd = sprqEnd;
	}

	public String getJsrqStart() {
		return jsrqStart;
	}

	public void setJsrqStart(String jsrqStart) {
		this.jsrqStart = jsrqStart;
	}

	public String getJsrqEnd() {
		return jsrqEnd;
	}

	public void setJsrqEnd(String jsrqEnd) {
		this.jsrqEnd = jsrqEnd;
	}

	public String getSjrqStart() {
		return sjrqStart;
	}

	public void setSjrqStart(String sjrqStart) {
		this.sjrqStart = sjrqStart;
	}

	public String getSjrqEnd() {
		return sjrqEnd;
	}

	public void setSjrqEnd(String sjrqEnd) {
		this.sjrqEnd = sjrqEnd;
	}

	public Aqscxzcfglb getAqscxzcfglb() {
		return aqscxzcfglb;
	}

	public void setAqscxzcfglb(Aqscxzcfglb aqscxzcfglb) {
		this.aqscxzcfglb = aqscxzcfglb;
	}

	public String getIfzsqy() {
		return ifzsqy;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}

	public String getXjshState() {
		return xjshState;
	}

	public void setXjshState(String xjshState) {
		this.xjshState = xjshState;
	}

	public String getXjBack() {
		return xjBack;
	}

	public void setXjBack(String xjBack) {
		this.xjBack = xjBack;
	}

	public String getSjshState() {
		return sjshState;
	}

	public void setSjshState(String sjshState) {
		this.sjshState = sjshState;
	}

	public String getSjBack() {
		return sjBack;
	}

	public void setSjBack(String sjBack) {
		this.sjBack = sjBack;
	}

	public String getDeptflag() {
		return deptflag;
	}

	public void setDeptflag(String deptflag) {
		this.deptflag = deptflag;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptrole() {
		return deptrole;
	}

	public void setDeptrole(String deptrole) {
		this.deptrole = deptrole;
	}

	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}

	public String getDeptCodeLenth() {
		return deptCodeLenth;
	}

	public void setDeptCodeLenth(String deptCodeLenth) {
		this.deptCodeLenth = deptCodeLenth;
	}
    
    
}
