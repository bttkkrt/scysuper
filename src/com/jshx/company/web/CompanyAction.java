/**
 * Class Name: CompanyAction
 * Class Description：企业管理
 */
package com.jshx.company.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.common.Assert;
import jxl.read.biff.BiffException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.aqglb.entity.JshxAqglb;
import com.jshx.aqglb.service.JshxAqglbService;
import com.jshx.common.util.Condition;
import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BusinessException;
import com.jshx.core.utils.CodeUtil;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.gwaq.entity.JshxGwaq;
import com.jshx.gwaq.service.JshxGwaqService;
import com.jshx.gwznb.entity.JshxGwznb;
import com.jshx.gwznb.service.JshxGwznbService;
import com.jshx.http.bean.DataBean;
import com.jshx.http.service.HttpService;
import com.jshx.http.util.StringTools;
import com.jshx.hyfl.entity.Hyfl;
import com.jshx.hyfl.service.HyflService;
import com.jshx.maintenp.entity.Maintenp;
import com.jshx.maintenp.service.MaintenpService;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.Permission;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRight;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserRoleService;
import com.jshx.module.admin.service.UserService;
import com.jshx.occupationalControlPlan.entity.OccupationalControlPlan;
import com.jshx.occupationalControlPlan.service.OccupationalControlPlanService;
import com.jshx.occupationalEvaluatePlan.entity.OccupationalEvaluatePlan;
import com.jshx.occupationalEvaluatePlan.service.OccupationalEvaluatePlanService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;
import com.jshx.safeleader.entity.Safeleader;
import com.jshx.safeleader.service.SafeleaderService;
import com.jshx.safetysheet.entity.Safetysheet;
import com.jshx.safetysheet.service.SafetysheetService;
import com.jshx.safetywarining.entity.Safetywarining;
import com.jshx.safetywarining.service.SafetywariningService;
import com.jshx.segmentplant.entity.Segmentplant;
import com.jshx.segmentplant.service.SegmentplantService;
import com.jshx.whpaqglzd.entity.Whpaqglzd;
import com.jshx.whpaqglzd.service.WhpaqglzdService;
import com.jshx.whysjbqk.entity.Whysjbqk;
import com.jshx.whysjbqk.service.WhysjbqkService;
import com.jshx.xrcPxb.entity.JshxXrcPxb;
import com.jshx.xrcPxb.service.JshxXrcPxbService;
import com.jshx.ygtjbghzb.entity.Ygtjbghzb;
import com.jshx.ygtjbghzb.service.YgtjbghzbService;
import com.jshx.zsgl.entity.JshxZsgl;
import com.jshx.zsgl.service.JshxZsglService;
import com.jshx.zybfzjf.entity.Zybfzjf;
import com.jshx.zybfzjf.service.ZybfzjfService;
import com.jshx.zycsjcbg.entity.Zycsjcbg;
import com.jshx.zycsjcbg.service.ZycsjcbgService;
import com.jshx.zycsjcry.entity.Zycsjcry;
import com.jshx.zycsjcry.service.ZycsjcryService;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.service.ZycsqkService;
import com.jshx.zywsglzd.entity.Zywsglzd;
import com.jshx.zywsglzd.service.ZywsglzdService;
import com.jshx.zywsjbxx.entity.Zywsjbxx;
import com.jshx.zywsjbxx.service.ZywsjbxxService;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzbw.service.JshxZzbwService;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;
import com.jshx.zzxtPxb.service.JshxZzxtPxbService;

/**
 * 
 * @author gq 7月19 企业管理业务类
 */
public class CompanyAction extends BaseAction {
	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Company company = new Company();

	private CompanyBackUp companyBackUp = new CompanyBackUp();

	private ReviewLog reviewLog = new ReviewLog();

	/**
	 * 企业业务类
	 */
	@Autowired
	private CompanyService companyService;
	@Autowired
	private HttpService httpService;
	@Autowired
	private ReviewLogService reviewLogService;
	@Autowired
	private JshxZzbwService jshxZzbwService;
	@Autowired
	private SafetywariningService safetywariningService;
	@Autowired
	private JshxXrcPxbService jshxXrcPxbService;
	@Autowired
	private JshxZzxtPxbService jshxZzxtPxbService;
	@Autowired
	private JshxZsglService jshxZsglService;
	@Autowired
	private JshxAqglbService jshxAqglbService;
	@Autowired
	private JshxGwznbService jshxGwznbService;
	@Autowired
	private MaintenpService maintenpService;
	@Autowired
	private SafetysheetService safetysheetService;
	@Autowired
	private JshxGwaqService jshxGwaqService;
	@Autowired
	private WhpaqglzdService whpaqglzdService;
	@Autowired
	private ZywsglzdService zywsglzdService;
	@Autowired
	private SegmentplantService segmentplantService;
	@Autowired
	private SafeleaderService safeleaderService;

	@Autowired
	private OccupationalControlPlanService occupationalControlPlanService;
	@Autowired
	private OccupationalEvaluatePlanService occupationalEvaluatePlanService;
	@Autowired
	private ZybfzjfService zybfzjfService;
	@Autowired
	private ZycsjcryService zycsjcryService;
	@Autowired
	private ZycsqkService zycsqkService;
	@Autowired
	private WhysjbqkService whysjbqkService;
	@Autowired
	private ZywsjbxxService zywsjbxxService;
	@Autowired
	private ZycsjcbgService zycsjcbgService;
	@Autowired
	private YgtjbghzbService ygtjbghzbService;
	/**
	 * 用户业务类
	 */
	@Autowired
	private UserService userService;

	/**
	 * 用户角色业务类
	 */
	@Autowired
	private UserRoleService userRoleService;
	/**
	 * 部门业务类
	 */
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private HyflService hyflService;

	private String deptCodeLenth;
	private String tempDeptCode;
	// @Autowired
	// private JshxZcyhsbService jshxZcyhsbService;
	// @Autowired
	// private JshxZazPxbService jshxZazPxbService;
	// @Autowired
	// private JshxTzzyPxbService jshxTzzyPxbService;
	// @Autowired
	// private JshxZzxtPxbService jshxZzxtPxbService;
	// @Autowired
	// private JshxXrcPxbService jshxXrcPxbService;
	// @Autowired
	// private JshxXkzsbService jshxXkzsbService;
	// @Autowired
	// private FactorypictureService factorypictureService;
	// @Autowired
	// private JshxZzbwService jshxZzbwService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination; 

	private String deptcode;

	private String deptrole;

	private Date queryQyclsjStart;

	private Date queryQyclsjEnd;

	private String type; // type为0表示只能看，type为1表示能审核

	private String isShenhe;// 0 标示查看 1标示审核，可修改

	private List<CompanyBackUp> companyBackUpList = new ArrayList<CompanyBackUp>();

	private String zjshState;// 镇级审核状态

	private String zjBack;// 镇级审核备注

	private String xjshState;// 县级审核状态

	private String xjBack;// 县级审核备注

	private String sjshState;// 市级审核状态

	private String sjBack;// 市级审核备注

	private String deptflag;//是否为审核部门人员 0：非审核部门人员  1：审核部门人员

	private String passFlag;

	private String ifzsqy;
	private String county;
	private String code;
	private String updateFlag;
	private String cityflag;
	private String jglx;
	private List<ReviewLog> reviewList;
	
	public String getJglx() {
		return jglx;
	}

	public void setJglx(String jglx) {
		this.jglx = jglx;
	}

	public String getCityflag() {
		return cityflag;
	}

	public void setCityflag(String cityflag) {
		this.cityflag = cityflag;
	}

	/**
	 * 导入数据返回信息
	 */
	private String logInfo;
	
	private File companyFile;
	private int pageNumber;

	public List<CompanyBackUp> getCompanyBackUpList() {
		return companyBackUpList;
	}

	public void setCompanyBackUpList(List<CompanyBackUp> companyBackUpList) {
		this.companyBackUpList = companyBackUpList;
	}

	private List<Department> deptlist = new ArrayList<Department>();

	// private List<JshxZcyhsb> jshxZcyhsbList = new ArrayList<JshxZcyhsb>();
	//
	// private List<JshxZazPxb> jshxZazPxbList = new ArrayList<JshxZazPxb>();
	//
	// private List<JshxTzzyPxb> jshxTzzyPxbList = new ArrayList<JshxTzzyPxb>();
	//
	// private List<JshxXrcPxb> jshxXrcPxbList = new ArrayList<JshxXrcPxb>();
	//
	// private List<JshxZzxtPxb> jshxZzxtPxbList = new ArrayList<JshxZzxtPxb>();
	//
	// private List<JshxXkzsb> jshxXkzsbList = new ArrayList<JshxXkzsb>();
	//
	// private List<Factorypicture> factorypictureList = new
	// ArrayList<Factorypicture>();
	//
	// private List<JshxZzbw> jshxZzbwList = new ArrayList<JshxZzbw>();
	/**
	 * 高强 7月19 企业注册页面
	 */
	public String companyRegisterUI() {
		try {
			company = (Company) this.getRequest().getSession().getAttribute("company");
			if (company != null && company.getDwdz1() != null) {
				deptlist = deptService.findDeptByParentDeptCode(company.getDwdz1());
				company = new Company();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String companyRegisterNextUI() {
		try {
			if (null != company && company.getDwdz1() != null){
				this.getRequest().getSession().setAttribute("company", company);
				deptlist = deptService.findDeptByParentDeptCode(company.getDwdz1());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 初始化列表页面
	 * 1、高强 7月29   修改
	 * 2、二次审核改为一次审核 2013-08-02 by 陆婷
	 * 3、hanxc 2015-02-28 一次审核改为二次审核 确认登陆用户是否为具有审核权限的部门人员
	 */
	public String initList() {
		String deptRole = userService.findUserById(this.getLoginUser().getId()).getDeptRole();//获取登陆人员部门角色职责
		String deptCode = this.getLoginUserDepartment().getDeptCode();// 用户部门编码
		company.setBasePass(3);
		if(deptCode.length()==6 && deptCode.startsWith("002")){
			cityflag = "1";
			company.setIfzsqy("1");
		}
		if(null != deptRole){
//		  && //非企业人员
//		  (deptRole.contains("a") || deptRole.contains("b") || deptRole.contains("c") || deptRole.contains("d") || deptRole.contains("e"))){//如果为一二三四处人员登录
			//非企业人员
			if(!deptRole.equals("21")){
				deptflag = "1";
			}else{
				cityflag = "0";
			}
//			deptflag = "1";
//				company.setBasePass(3);//有审核任务的部门，查询所有企业信息
		} 
		
		else {
			deptflag = "1";
//			company.setBasePass(3);//有审核任务的部门，查询所有企业信息
//				deptflag = "0";
//				company.setBasePass(3);
////				company.setBasePass(1);//无审核任务的部门，仅查询审核通过的企业信息
		}
		if(this.getLoginUser().getLoginId().equals("admin")){
//			company.setBasePass(3);//管理员查询所有企业信息
			flag = "1";
			cityflag = "1";
		}
		if(StringUtil.isNotNullAndNotEmpty(isShenhe)){
			company.setBasePass(Integer.valueOf(isShenhe));
		}
		return SUCCESS;
	}
	/**
	 * 查看当前登陆用户对某条企业信息的审核任务状态
	 * hanxc 20150228 
	 */
	public void findReviewLogState(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String stateStr = "{\"state\":\"3\"}";//企业信息审核状态：0、待审核 1、审核通过 2、审核不通过 3： 未找到审核数据
		
		String dutyFlag = "";//审核职责标识，1：市级审核职责标识，2：县级审核职责标识
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
				.getProperty("cityDeptCodeLength"))) {// 市级部门
			dutyFlag = "1";
		} else if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
				.getProperty("countyDeptCodeLength"))) {// 县级部门
			dutyFlag = "2";
		} else if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
				.getProperty("townDeptCodeLength"))) {// 镇级部门
			dutyFlag = "3";
		} else if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
				.getProperty("villageDeptCodeLength"))) {// 村级部门
			dutyFlag = "4";
		}
		
		if (pagination == null)
			pagination = new Pagination(this.getRequest());
		if (null != ids) {
			paraMap.put("itemId", ids);//企业ID
			paraMap.put("dutyFlag", dutyFlag);
			pagination = reviewLogService.findByPage(pagination, paraMap);
			List list = pagination.getList();
			if (list != null && list.size() > 0) {//如果未查询到数据，表示该用户对该条数据无审核任务
				ReviewLog reviewLog = (ReviewLog) list.get(0);
				stateStr = "{\"state\":\"" + reviewLog.getState() + "\"}";
			}
		}
		try {
			this.getResponse().getWriter().println(stateStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取企业重点模块填报完成进度
	 * hanxc 20150525 
	 */
	public void findInputProgress(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		JSONObject jo = new JSONObject();
		if (pagination == null)
			pagination = new Pagination(this.getRequest());
		if (null != ids) {
			Map map = new HashMap();
			map.put("companyId", ids);
			CompanyBackUp comBc = companyService.getCompanyBackupById(map);
			paraMap.put("qyid",comBc.getId());
			
			//1关键装置和重点部位
			List<JshxZzbw> jshxZzbwList = jshxZzbwService.findJshxZzbw(paraMap);
			if (jshxZzbwList != null && jshxZzbwList.size() > 0) {
				jo.put("jshxZzbw", "2");//填报完成
			}else{
				jo.put("jshxZzbw", "0");//尚未填报
			}
			
			//2安全警示标志设置情况
			pagination = safetywariningService.findByPage(pagination, paraMap);
			List<Safetywarining> safetywariningList = pagination.getListOfObject();
			if (safetywariningList != null && safetywariningList.size() > 0) {
				jo.put("safetywarining", "2");//填报完成
			}else{
				jo.put("safetywarining", "0");//尚未填报
			}
			//3培训
			int pxflag = 0;
			//3a新入厂员工培训
			List<JshxXrcPxb> jshxXrcPxbList = jshxXrcPxbService.findJshxXrcPxb(paraMap);
			if (jshxXrcPxbList != null && jshxXrcPxbList.size() > 0) {
				pxflag++;
			}
			//3b再培训及转岗下岗脱岗培训
			List<JshxZzxtPxb> jshxZzxtPxbList = jshxZzxtPxbService.findJshxZzxtPxb(paraMap);
			if (jshxZzxtPxbList != null && jshxZzxtPxbList.size() > 0) {
				pxflag++;
			}
			//3c资格证书扫描管理
			pagination = jshxZsglService.findByPage(pagination, paraMap);
			List<JshxZsgl> jshxZsglList = pagination.getListOfObject();
			if (jshxZsglList != null && jshxZsglList.size() > 0) {
				pxflag++;
			}
			//3d安全管理机构文件
			pagination = jshxAqglbService.findByPage(pagination, paraMap);
			List<JshxAqglb> jshxAqglbList = pagination.getListOfObject();
			if (jshxAqglbList != null && jshxAqglbList.size() > 0) {
				pxflag++;
			}
			
			if (pxflag == 0) {
				jo.put("px", "0");//尚未填报
			}else if (pxflag == 4){
				jo.put("px", "2");//填报完成
			}else{
				jo.put("px", "1");//部分填报
			}
			
			//4安全管理制度及安全操作规程
			int glzdflag = 0;
			//4a岗位和职能部门责任制
			pagination = jshxGwznbService.findByPage(pagination, paraMap);
			List<JshxGwznb> jshxGwznbList = pagination.getListOfObject();
			if (jshxGwznbList != null && jshxGwznbList.size() > 0) {
				glzdflag++;
			}
			
			//4b检修作业制度
			pagination = maintenpService.findByPage(pagination, paraMap);
			List<Maintenp> maintenpList = pagination.getListOfObject();
			if (maintenpList != null && maintenpList.size() > 0) {
				glzdflag++;
			}
			//4c安全生产规章制度
			pagination = safetysheetService.findByPage(pagination, paraMap);
			List<Safetysheet> safetysheetList = pagination.getListOfObject();
			if (safetysheetList != null && safetysheetList.size() > 0) {
				glzdflag++;
			}
			
			//4d岗位安全操作规程 
			pagination = jshxGwaqService.findByPage(pagination, paraMap);
			List<JshxGwaq> jshxGwaqList = pagination.getListOfObject();
			if (jshxGwaqList != null && jshxGwaqList.size() > 0) {
				glzdflag++;
			}
			
			//4e危化品安全管理制度
			pagination = whpaqglzdService.findByPage(pagination, paraMap);
			List<Whpaqglzd> whpaqglzdList = pagination.getListOfObject();
			if (whpaqglzdList != null && whpaqglzdList.size() > 0) {
				glzdflag++;
			}
			
			//4f职业卫生管理制度
			pagination = zywsglzdService.findByPage(pagination, paraMap);
			List<Zywsglzd> zywsglzdList = pagination.getListOfObject();
			if (zywsglzdList != null && zywsglzdList.size() > 0) {
				glzdflag++;
			}

			if (glzdflag == 0) {
				jo.put("glzd", "0");//尚未填报
			}else if (glzdflag == 6){
				jo.put("glzd", "2");//填报完成
			}else{
				jo.put("glzd", "1");//部分填报
			}
			
			//5安全设施
			int aqssflag = 0;
			//5a消防设施分布图
			pagination = segmentplantService.findByPage(pagination, paraMap);
			List<Segmentplant> segmentplantList = pagination.getListOfObject();
			if (segmentplantList != null && segmentplantList.size() > 0) {
				aqssflag++;
			}
			//5b安全设施登记台帐
			pagination = safeleaderService.findByPage(pagination, paraMap);
			List<Safeleader> safeleaderList = pagination.getListOfObject();
			if (safeleaderList != null && safeleaderList.size() > 0) {
				aqssflag++;
			}
			if (aqssflag == 0) {
				jo.put("aqss", "0");//尚未填报
			}else if (aqssflag == 2){
				jo.put("aqss", "2");//填报完成
			}else{
				jo.put("aqss", "1");//部分填报
			}
			//6职业卫生
			int zywsflag = 0;
			//6a职业健康检查报告上传
			List<Ygtjbghzb> ygtjbghzblist = ygtjbghzbService.findYgtjbghzb(paraMap);
			if (ygtjbghzblist != null && ygtjbghzblist.size() > 0) {
				zywsflag++;
			}
			
			//6b作业场危害因素检测报告上传
			List<Zycsjcbg> zycsjcbglist = zycsjcbgService.findZycsjcbg(paraMap);
			if (zycsjcbglist != null && zycsjcbglist.size() > 0) {
				zywsflag++;
			}
			//6c基本信息
			pagination = zywsjbxxService.findByPage(pagination, paraMap);
			List<Zywsjbxx> zywsjbxxList = pagination.getListOfObject();
			if (zywsjbxxList != null && zywsjbxxList.size() > 0) {
				zywsflag++;
			}
			//6d危害因素基本情况
			pagination = whysjbqkService.findByPage(pagination, paraMap);
			List<Whysjbqk> whysjbqkList = pagination.getListOfObject();
			if (whysjbqkList != null && whysjbqkList.size() > 0) {
				zywsflag++;
			}
			//6e作业场所情况、职业病危害因素监测(同样的数据)
			pagination = zycsqkService.findByPage(pagination, paraMap);
			List<Zycsqk> zycsqkList = pagination.getListOfObject();
			if (zycsqkList != null && zycsqkList.size() > 0) {
				zywsflag++;
			}
			//6f职业健康监护
			pagination = zycsjcryService.findByPage(pagination, paraMap);
			List<Zycsjcry> zycsjcryList = pagination.getListOfObject();
			if (zycsjcryList != null && zycsjcryList.size() > 0) {
				zywsflag++;
			}
			//6g年度职业病防治经费
			pagination = zybfzjfService.findByPage(pagination, paraMap);
			List<Zybfzjf> zybfzjfList = pagination.getListOfObject();
			if (zybfzjfList != null && zybfzjfList.size() > 0) {
				zywsflag++;
			}
			//6h职业病危害预评价报告表备案通知书
			pagination = occupationalEvaluatePlanService.findByPage(pagination, paraMap);
			List<OccupationalEvaluatePlan> occupationalEvaluatePlanList = pagination.getListOfObject();
			if (occupationalEvaluatePlanList != null && occupationalEvaluatePlanList.size() > 0) {
				zywsflag++;
			}
			//6i职业病危害控制效果评价报告表备案通知书
			pagination = occupationalControlPlanService.findByPage(pagination, paraMap);
			List<OccupationalControlPlan> occupationalControlPlanList = pagination.getListOfObject();
			if (occupationalControlPlanList != null && occupationalControlPlanList.size() > 0) {
				zywsflag++;
			}
			if (aqssflag == 0) {
				jo.put("zyws", "0");//尚未填报
			}else if (aqssflag == 9){
				jo.put("zyws", "2");//填报完成
			}else{
				jo.put("zyws", "1");//部分填报
			}
			
		}
		try {
			this.getResponse().getWriter().println(jo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String initLists() {
		return SUCCESS;
	}

	/**
	 * 高强 7月19 提交注册信息 
	 * 修改：增加详细地址 2013-08-02 by 陆婷 
	 * 修改：增加视频名称 2013-12-18 by 陆婷
	 * 修改：相城版本迁移到抚顺 2014-11-11 hanxc
	 */
	public String companyRegister() throws Exception {
		if (company != null && company.getLoginname() != null)
			if (companyService.findCompanyBYLoginname(company.getLoginname())
					|| userService.findUserByLoginId(company.getLoginname()) != null) {
				ServletActionContext.getRequest().getSession()
						.setAttribute("message", "该用户已注册");
				ServletActionContext.getRequest().getSession()
						.setAttribute("urladdress", "companyRegisterUI.action");
				return EDIT;
			} else {
				company.setDelFlag(0);
				company.setCreateTime(new Date());
				try {
					// company.setDwdz("抚顺市"+companyService.findCompanyTypeNameByKey(company.getDwdz1(),"4028e56c4014f290014014f981510003"));
					// hanxc 2014/11/8 所属地域由一维代码改为部门
					String county = companyService.findCompanyTypeNameByKey(company.getCounty(),"402881e5498e018801498e688b530028").toString();//获取县区名称
					String dwdz1 =company.getDwdz1();
					if(null != dwdz1){
						dwdz1 = dwdz1.replaceAll(",", "").trim();
						company.setDwdz1(dwdz1);
						company.setSzc(dwdz1);
					}
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());//获取乡镇安监中队名称
					String xzName = "";
					if(null != dept){
						xzName = dept.getDeptName();
//						xzName = xzName.substring(0, xzName.lastIndexOf("安监站"));
					}
					company.setDwdz("抚顺市" + county + xzName);
					company.setLoginname(company.getLoginname().trim());
					String companyId = companyService.save(company);
					// 备份企业信息到CompanyBackUp中
					CompanyBackUp cbu = new CompanyBackUp();
					cbu.setCompanyId(companyId);
					cbu.setCompanyname(company.getCompanyname());
					cbu.setCreateTime(new Date());
					cbu.setLoginname(company.getLoginname().trim());
					cbu.setFddbr(company.getFddbr());
					cbu.setFddbrlxhm(company.getFddbrlxhm());
					cbu.setDwdz(company.getDwdz());
					cbu.setDwdz1(company.getDwdz1());
					cbu.setDwdz2(company.getDwdz2());
					cbu.setDelFlag(0);
					cbu.setZzjgdm(company.getZzjgdm());
					cbu.setQylx(company.getQylx());
					cbu.setHyfl(company.getHyfl());
					cbu.setLxr(company.getLxr());
					cbu.setMobile(company.getMobile());
					cbu.setLxfs(company.getLxfs());
					cbu.setQyyx(company.getQyyx());
					cbu.setGszch(company.getGszch());
					cbu.setQylx(company.getQylx());
					cbu.setQygm(company.getQygm());
					cbu.setQyzclx(company.getQyzclx());
					cbu.setZczj(company.getZczj());
					cbu.setWhpqylx(company.getWhpqylx());
					cbu.setIfwhpqylx(company.getIfwhpqylx());
					cbu.setZywhqylx(company.getZywhqylx());
					cbu.setIfzywhqylx(company.getIfzywhqylx());
					cbu.setFrdm(company.getFrdm());
					cbu.setQyclsj(company.getQyclsj());
					cbu.setGddh(company.getGddh());
					cbu.setCz(company.getCz());
					cbu.setYzbm(company.getYzbm());
					cbu.setSnxssr(company.getSnxssr());
					cbu.setSnsjss(company.getSnsjss());
					cbu.setSngdzc(company.getSngdzc());
					cbu.setSnwqtr(company.getSnwqtr());
					cbu.setSnaqscf(company.getSnaqscf());
					cbu.setSfaqjg(company.getSfaqjg());
					cbu.setAqglr(company.getAqglr());
					cbu.setSfzywsjg(company.getSfzywsjg());
					cbu.setZywsglry(company.getZywsglry());
					cbu.setSfqzwsgly(company.getSfqzwsgly());
					cbu.setZdmj(company.getZdmj());
					cbu.setJzmj(company.getJzmj());
					cbu.setCyry(company.getCyry());
					cbu.setSfyygss(company.getSfyygss());
					cbu.setAqbzdbjb(company.getAqbzdbjb());
					cbu.setJyfw(company.getJyfw());
					cbu.setUrl(company.getUrl());
					cbu.setIfurl(company.getIfurl());
					//gq 8-27 bug添加
					cbu.setIfyhbzjyqy(company.getIfyhbzjyqy());
					cbu.setYhbzjyqy(company.getYhbzjyqy());
					cbu.setSzc(company.getSzc());
					cbu.setSzcname(company.getSzcname());
					cbu.setLongitude(company.getLongitude());
					cbu.setLatitude(company.getLatitude());
					// hanxc 20141212 为抚顺版本添加字段添加 start
					cbu.setIffmksjyqy(company.getIffmksjyqy());
					cbu.setFmksjyqy(company.getFmksjyqy());
					cbu.setDiggingstype(company.getDiggingstype());
					cbu.setMetal(company.getMetal());
					cbu.setVentilate(company.getVentilate());
					cbu.setTransport(company.getTransport());
					cbu.setRaisetype(company.getRaisetype());
					cbu.setSixsys(company.getSixsys());
					cbu.setCounty(company.getCounty());
					cbu.setIfzsqy(company.getIfzsqy());
					cbu.setZsqytype(company.getZsqytype());
					cbu.setZcCode(company.getZcCode());
					cbu.setDutyFlag(company.getDutyFlag());
					cbu.setAqscxkzh(company.getAqscxkzh());
					cbu.setHyflOneLevel(company.getHyflOneLevel());
					cbu.setHyflTwoLevel(company.getHyflTwoLevel());
					cbu.setHyflThreeLevel(company.getHyflThreeLevel());
					cbu.setHyflFourLevel(company.getHyflFourLevel());
					// hanxc 20141212 为抚顺版本添加字段添加 end
					companyService.saveCompanyBackUp(cbu);
					//将新增企业作为部门 hanxc 2014-11-04
					Department companydept = new Department();
					companydept.setCreateTime(new Date());
					companydept.setCreateUserID(company.getLoginname().trim());
					companydept.setDelFlag(0);
					companydept.setDeptCode(deptService.createDeptCode("033"));
					companydept.setDeptName(company.getCompanyname());
					companydept.setDeptTypeCode("04");
					companydept.setHasChild(0);
					companydept.setNeedSubFlow("1");
					companydept.setSortSQ(1);
					companydept.setParentDept(deptService.findDeptByDeptCode("033"));
					deptService.save(companydept);
					
					User user = new User();
					user.setDelFlag(0);
					user.setDeptCode(companydept.getDeptCode());
					user.setCssId("default");
					user.setDisplayName(company.getCompanyname().trim());
					user.setLoginId(company.getLoginname().trim());
					user.setPassword(CodeUtil.encode(company.getLoginword()
							.trim(), "MD5"));
					user.setMobile(company.getMobile());
					user.setSortSq(1);
					user.setDeptRole("21");// 设置为企业部门角色
					user.setDept(companydept);
					userService.saveUser(user);

					UserRight userRight = new UserRight();
					userRight.setUser(user);
					userRight.setRole(userRoleService
							.findRoleByCode(SysPropertiesUtil
									.getProperty("tempRoleCode")));// 临时企业人员
					userService.saveRight(userRight);
					// hanxc 20141211 生成待审核任务 start
					ReviewLog newReviewLog = new ReviewLog();
					if (company.getIfzsqy().equals("1")) {
						newReviewLog.setDutyFlag("1");// 市级部门审核任务
					} else {
						newReviewLog.setDutyFlag("2");// 县级部门审核任务
					}
					newReviewLog.setItemId(company.getId());
					newReviewLog.setItemType("1");// 企业信息类型：type=1
					newReviewLog.setStartTime(new Date());
					reviewLogService.saveNewTask(newReviewLog);
					// hanxc 20141211 生成待审核任务 end

					// XfjCompany xfjCompany = new XfjCompany();
					// xfjCompany.setId(company.getId());
					// xfjCompany.setGszch(company.getGszch());
					// xfjCompany.setZzjgdm(company.getZzjgdm());
					// xfjCompany.setCompanyname(company.getCompanyname());
					// xfjCompany.setYzbm(company.getYzbm());
					// xfjCompany.setQyyx(company.getQyyx());
					// xfjCompany.setGddh(company.getGddh());
					// xfjCompany.setCz(company.getCz());
					// xfjCompany.setQyclsj(company.getQyclsj());
					// xfjCompany.setFddbr(company.getFddbr());
					// xfjCompany.setFddbrlxhm(company.getFddbrlxhm());
					// xfjCompany.setCyry(company.getCyry());
					// xfjCompany.setZdmj(company.getZdmj());
					// xfjCompany.setJzmj(company.getJzmj());
					// xfjCompany.setLongitude(company.getLongitude());
					// xfjCompany.setLatitude(company.getLatitude());
					// xfjCompany.setLoginname(company.getLoginname());
					// xfjCompany.setLoginword(company.getLoginword());
					// xfjCompany.setDwdz(company.getDwdz());
					// xfjCompany.setDwdz1("002"+company.getDwdz1().substring(6,9));
					// xfjCompany.setDwdz2(company.getDwdz2());
					// xfjCompany.setBasePass(0);
					// xfjCompany.setCompanyId(cbu.getId());
					// xfjCompany.setDelFlag(0);
					// xfjCompany.setCreateTime(new Date());
					// companyService.saveXfjcompany(xfjCompany);
					//
					// companyService.saveXfjUser(user);

					ServletActionContext.getRequest().getSession()
							.setAttribute("message", "注册成功,我们会在最快时间内审核,点击返回登录");
					ServletActionContext.getRequest().getSession()
							.setAttribute("urladdress", "logout.action");
				} catch (Exception e) {
					e.printStackTrace();
					return EDIT;
				}
			}
		return SUCCESS;
	}
	/**
	 * 高强 7月19 校验企业用户是否被注册
	 */
	public void checkCompany() {
		try {
			if (company != null && company.getLoginname() != null) {
				User user = userService.findUserByLoginId(company
						.getLoginname());
				if (user != null)
					this.getResponse().getWriter()
							.println("{\"result\":\"true\"}");
				else
					this.getResponse().getWriter()
							.println("{\"result\":\"flase\"}");
			} else if (company != null && company.getGszch() != null) {
				Map map = new HashMap();
				map.put("gszch", company.getGszch());
				CompanyBackUp company = companyService
						.getCompanyBackupById(map);
				if (company != null && company.getId() != null
						&& !"".equals(company.getId()))
					this.getResponse().getWriter()
							.println("{\"result\":\"true\"}");
				else
					this.getResponse().getWriter().println("{\"result\":\"flase\"}");
			}
			ServletActionContext.getRequest().getSession().setAttribute("message", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createCompDept(){
		Map<String, Object> paraMap = new HashMap<String, Object>();
		List<Company> list = companyService.findCompany(paraMap);
		for (Company tempcompany : list) {
			//将新增企业作为部门 hanxc 2014-11-04
			Department dept = new Department();
			dept.setCreateTime(new Date());
			dept.setCreateUserID(tempcompany.getLoginname().trim());
			dept.setDelFlag(0);
			dept.setDeptCode(deptService.createDeptCode("033"));
			dept.setDeptName(tempcompany.getCompanyname());
			dept.setDeptTypeCode("04");
			dept.setHasChild(0);
			dept.setNeedSubFlow("1");
			dept.setSortSQ(1);
			dept.setParentDept(deptService.findDeptByDeptCode("033"));
			deptService.save(dept);
		}
	}
	private void updateCompDept(){
		List<Department> tempdeptList = deptService.findDeptByParentDeptCode("033");
		for (Department dept : tempdeptList) {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("companyname", "%"+ dept.getDeptName().trim() + "%");
			List<Company> tempCompanyList = companyService.findCompany(paraMap);
			if(null != tempCompanyList && tempCompanyList.size() > 0){
				dept.setCreateUserID(tempCompanyList.get(0).getLoginname().trim());
				deptService.save(dept);
			}
		}
	}
	private void updateUserDept(){
		List<User> userList = userService.findAllUsersByDept("033");
		for (User user : userList) {
			List<Department> tempdeptList = deptService.findDeptByName(user.getDisplayName());
			if(null != tempdeptList && tempdeptList.size() > 0){
				user.setDeptCode(tempdeptList.get(0).getDeptCode());
				user.setDept(tempdeptList.get(0));
				userService.modify(user, null);
			}
		}
		
	}

	/**
	 * 高强 7月19 执行查询的方法，返回json数据
	 */
	public void list() throws Exception {
		//createCompDept();
		//updateUserDept();
		//updateCompDept();
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (pagination == null)
			pagination = new Pagination(this.getRequest());
		pageNumber = pagination.getPageNumber();
		if (null != company) {
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != company.getCompanyname())
					&& (0 < company.getCompanyname().trim().length())) {
				paraMap.put("companyname", "%"
						+ company.getCompanyname().trim() + "%");
			}

			if ((null != company.getFddbr())
					&& (0 < company.getFddbr().trim().length())) {
				paraMap.put("fddbr", "%" + company.getFddbr().trim() + "%");
			}

			if ((null != company.getZzjgdm())
					&& (0 < company.getZzjgdm().trim().length())) {
				paraMap.put("zzjgdm", "%" + company.getZzjgdm().trim() + "%");
			}

			if ((null != company.getGszch())
					&& (0 < company.getGszch().trim().length())) {
				paraMap.put("gszch", "%" + company.getGszch().trim() + "%");
			}

			if ((null != company.getQylx())
					&& (0 < company.getQylx().trim().length())) {
				paraMap.put("qylx", company.getQylx().trim());
			}

			if ((null != company.getHyfl())
					&& (0 < company.getHyfl().trim().length())) {
				paraMap.put("hyfl", company.getHyfl().trim());
			}

			if ((null != company.getQyzclx())
					&& (0 < company.getQyzclx().trim().length())) {
				paraMap.put("qyzclx", company.getQyzclx().trim());
			}

			if ((null != company.getYhbzjyqy())
					&& (0 < company.getYhbzjyqy().trim().length())) {
				paraMap.put("ifyhbzjyqy", "1");
				paraMap.put("Qyhbzjyqy", "%" + company.getYhbzjyqy().trim()
						+ "%");

			}
			if ((null != company.getZywhqylx())
					&& (0 < company.getZywhqylx().trim().length())) {
				paraMap.put("ifzywhqylx", "1");
				paraMap.put("Qzywhqylx", "%" + company.getZywhqylx().trim()
						+ "%");

			}

			if ((null != company.getWhpqylx())
					&& (0 < company.getWhpqylx().trim().length())) {
				paraMap.put("ifwhpqylx", "1");
				paraMap.put("Qwhpqylx", "%" + company.getWhpqylx().trim() + "%");

			}

			if ((null != company.getDwdz1())
					&& (0 < company.getDwdz1().trim().length())) {
				paraMap.put("dwdz1", company.getDwdz1());
			}else if((null != county) && (0 < county.trim().length())){
				paraMap.put("dwdz1", county+"%");
			}
			if ((null != company.getSzc())
					&& (0 < company.getSzc().trim().length())) {
				paraMap.put("szc", company.getSzc());
			}
			if(company.getBasePass()==1 || company.getBasePass()==2){
				paraMap.put("basePass", company.getBasePass());
			}else{
				paraMap.put("basePass", company.getBasePass());
			}

			if (null != queryQyclsjStart) {
				paraMap.put("startQyclsj", queryQyclsjStart);
			}

			if (null != queryQyclsjEnd) {
				paraMap.put("endQyclsj", queryQyclsjEnd);
			}
			if ((null != company.getIfzywhqylx())
					&& (0 < company.getIfzywhqylx().trim().length())) {
				paraMap.put("ifzywhqylx", company.getIfzywhqylx().trim());
			}
			if(StringUtils.isNotEmpty(jglx)){
				if("1".equals(jglx)){
					paraMap.put("iffmksjyqy", "1");
				}else if("2".equals(jglx)){
					paraMap.put("ifwhpqylx", "1");
				}else if("3".equals(jglx)){
					paraMap.put("iffmgmqylx", "1");
				}
			}

			if ((null != company.getIfwhpqylx())
					&& (0 < company.getIfwhpqylx().trim().length())) {
				paraMap.put("ifwhpqylx", company.getIfwhpqylx().trim());
			}
			if ((null != company.getIffmksjyqy())
					&& (0 < company.getIffmksjyqy().trim().length())) {
				paraMap.put("iffmksjyqy", company.getIffmksjyqy().trim());
			}
			if ((null != company.getIffmgmqylx())
					&& (0 < company.getIffmgmqylx().trim().length())) {
				paraMap.put("iffmgmqylx", company.getIffmgmqylx().trim());
			}
			if ((null != company.getAqbzdbjb())
					&& (0 < company.getAqbzdbjb().trim().length())) {
				paraMap.put("aqbzdbjb", company.getAqbzdbjb().trim());
			}

			// 添加直属类型查询条件 begin 2014-12-10 hyc start
			if ((null != company.getZsqytype())
					&& (0 < company.getZsqytype().trim().length())) {
				paraMap.put("zsqytype", company.getZsqytype());
				paraMap.put("ifzsqy", "1");
			}
			//市局查看
			if(StringUtils.isNotEmpty(company.getIfzsqy())){
				paraMap.put("ifzsqy", company.getIfzsqy());
			}
			// 添加直属类型查询条件 begin 2014-12-10 hyc end
			if ((null != company.getFeature())
					&& (0 < company.getFeature().trim().length())) {
				paraMap.put("feature", company.getFeature().trim());
			}
		}
		// hanxc 20141223 修改 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();// 用户部门编码
		String deptRole = this.getLoginUser().getDeptRole();//用户部门角色职责			
		
		List<?> rightList = this.getLoginUser().getUserRoles();
		String[] roleNames = new String[rightList.size()];
		if (rightList != null && rightList.size() > 0) {
			for (int i = 0; i < rightList.size(); i++) {
				UserRight right = (UserRight) rightList.get(i);
				roleNames[i] = right.getRole().getRoleName();
			}
		}
		List list = Arrays.asList(roleNames);  
		if(null != deptRole && SysPropertiesUtil.getProperty("qiyeCode").equals(deptRole)){//企业角色
			paraMap.put("loginname", this.getLoginUser().getLoginId());
		}else if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)&&!list.contains("系统管理员")){//非管理员登录
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), list.toString());
		}
		// hanxc 20141223 修改 end

		try {
			pagination = companyService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		convObjectToJson(pagination,null);
		
	}
	public String convObjectToJson(Pagination pagination, JsonConfig config) {
	    StringBuffer data = new StringBuffer("{\n");
	    try
	    {
	      data.append("  \"total\":").append(pagination.getTotalCount())
	        .append(",\n");
	      data.append("  \"rows\":\n");
	      JSONArray json = new JSONArray();
	      if (config != null)
	        json = JSONArray.fromObject(pagination.getListOfObject(), 
	          config);
	      else {
	        json = JSONArray.fromObject(pagination.getListOfObject());
	      }
	      data.append(json.toString());
	      data.append("  \n").append("}");
	      getResponse().setContentType("application/json;charset=UTF-8");
	      getResponse().setCharacterEncoding("utf-8");
	      getResponse().setHeader("Charset", "utf-8");
	      getResponse().setHeader("Cache-Control", "no-cache");
	      getResponse().getWriter().println(data);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return data.toString();
	  }
	

	public void lists() throws Exception {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (pagination == null)
			pagination = new Pagination(this.getRequest());

		if (null != company) {
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != company.getCompanyname())
					&& (0 < company.getCompanyname().trim().length())) {
				paraMap.put("companyname", "%"
						+ company.getCompanyname().trim() + "%");
			}
			if ((null != company.getLoginname())
					&& (0 < company.getLoginname().trim().length())) {
				paraMap.put("loginname", "%" + company.getLoginname().trim()
						+ "%");
			}
			if ((null != company.getGszch())
					&& (0 < company.getGszch().trim().length())) {
				paraMap.put("gszch", "%" + company.getGszch().trim() + "%");
			}
			if ((null != company.getDwdz1())
					&& (0 < company.getDwdz1().trim().length())) {
				paraMap.put("dwdz1", company.getDwdz1());
			}
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if (this.getLoginUserDepartment().getDeptName()
				.contains(SysPropertiesUtil.getProperty("townstr"))) {
			paraMap.put("deptCodes", deptCode + "%");
		}
		try {
			pagination = companyService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		convObjectToJson(pagination, null);
	}

	/**
	 * 高强 7月19 查看详细信息 
	 * 修改：增加自查隐患、培训情况、厂区图片、行政许可、关键装置位置 2013-09-17 by 陆婷
	 * 修改：根据审核日志过期审核信息 150228 hanxc
	 */
	public String view() throws Exception {
		if ((null != company) && (null != company.getId())) {
			company = companyService.getById(company.getId());
			deptlist = deptService.findDeptByParentDeptCode(company.getDwdz1());
		}
		setType(type);
		//hyc 2014-12-11 获得登陆人的部门长度，方便jsp页面判断审核是市级还是县级还是镇级 获得登陆用户名
		deptCodeLenth = this.getLoginUserDepartment().getDeptCode().length() + "";
		//查询是否直属企业
		if (company.getIfzsqy() != null && "1".equals(company.getIfzsqy())) {
			ifzsqy = "1";
		} else {
			ifzsqy = "0";
		}

		if (pagination == null)
			pagination = new Pagination(this.getRequest());
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("itemId", company.getId());
		paraMap.put("itemType", "1");
		pagination = reviewLogService.findByPage(pagination, paraMap);
		List reviewListTemp = pagination.getList();
		reviewList = (List<ReviewLog>)reviewListTemp;
		if (reviewList != null && reviewList.size() > 0) {//如果未查询到数据，表示该用户对该条数据无审核任务
			for (int i = 0; i < reviewList.size(); i++) {
				ReviewLog reviewLog = (ReviewLog) reviewList.get(i);
				String dutyFlag = reviewLog.getDutyFlag();//审核任务所属职责部门标识
//				if (dutyFlag.equals("2")) {// 县级领导审核任务
//					xjshState = reviewLog.getState();
//					xjBack = reviewLog.getRemark();
//				} else if (dutyFlag.equals("1")) {// 市级领导审核任务
//					sjshState = reviewLog.getState();
//					sjBack = reviewLog.getRemark();
//				}
				sjshState = reviewLog.getState();
				sjBack = reviewLog.getRemark();
			}
		}
		if (isShenhe.equals("1")){
			flag = "add";
			return "shenhe";
		}else {
			return VIEW;
		}
	}

	/**
	 * 高强 7月19 初始化修改信息 
	 * 修改：只有不通过才能修改，否则只能查看 2013-08-05 by 陆婷
	 * 修改：根据审核日志过期审核信息 150228 hanxc
	 */
	@SuppressWarnings("unchecked")
	public String initEdit() {
		if (company.getId() != null && !"".equals(company.getId())) {
			company = companyService.getById(company.getId());
		}else{
			company = companyService.findCompanyByLoginID(this.getLoginUser().getLoginId());
			
		}
		if (company.getDwdz1() != null && !"".equals(company.getDwdz1())) {
			deptlist = deptService.findDeptByParentDeptCode(company.getDwdz1());
		}

		if (company.getIfzsqy() != null && company.getIfzsqy().equals("1")) {
			ifzsqy = "1";
		} else {
			ifzsqy = "0";
		}

		deptCodeLenth = this.getLoginUserDepartment().getDeptCode().length()
				+ "";
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (pagination == null)
			pagination = new Pagination(this.getRequest());
		paraMap.put("itemId", company.getId());
		paraMap.put("itemType", "1");
		pagination = reviewLogService.findByPage(pagination, paraMap);
		List reviewListTemp = pagination.getList();
		reviewList = (List<ReviewLog>)reviewListTemp;
		if (reviewList != null && reviewList.size() > 0) {//如果未查询到数据，表示该用户对该条数据无审核任务
			for (int i = 0; i < reviewList.size(); i++) {
//				Map<String, String> objMap = new HashMap<String, String>();
				ReviewLog reviewLog = (ReviewLog) reviewList.get(i);
//				String dutyFlag = reviewLog.getDutyFlag();//审核任务所属职责部门标识
//				if (dutyFlag.equals("2")) {// 县级领导审核任务
//					xjshState = reviewLog.getState();
//					xjBack = reviewLog.getRemark();
//				} else if (dutyFlag.equals("1")) {// 市级领导审核任务
//					sjshState = reviewLog.getState();
//					sjBack = reviewLog.getRemark();
//				}
				sjshState = reviewLog.getState();
				sjBack = reviewLog.getRemark();
			}
		}
		return EDIT;
	}

	private void filtCompanyType(Company tempCompany, String flag) {
		String ct = tempCompany.getCompanyType();
		String[] strArr = ct.split(",");
		String tempct = "";
		for (int i = 0; i < strArr.length; i++) {

			if (!"".equals(strArr[i].trim())
					&& !strArr[i].trim().substring(0, 1).equals(flag)) {
				tempct += strArr[i].trim() + ",";
			}
		}
		if(!"".equals(tempct)){
			tempct = tempct.substring(0, tempct.length() - 1);
		}
		company.setCompanyType(tempct);
	}

	/**
	 * 高强 7月19 保存信息（包括新增和修改） 修改： 企业信息修改时同时更新备份表 修改：增加视频名称 2013-12-18 by 陆婷
	 * 
	 */
	public String save() throws Exception {
		if (company.getIfwhpqylx().equals("0")) {
			filtCompanyType(company, "a");
			company.setWhpqylx("");
		}
		if (company.getIfzywhqylx().equals("0")) {
			filtCompanyType(company, "c");
			company.setZywhqylx("");
		}
		if (company.getIfyhbzjyqy().equals("0")) {
			filtCompanyType(company, "b");
			company.setYhbzjyqy("");
		}
		if (company.getIffmksjyqy().equals("0")) {
			filtCompanyType(company, "d");
			company.setFmksjyqy("");
		}
		if (company.getIffmgmqylx().equals("0")) {
			filtCompanyType(company, "e");
			company.setFmksjyqy("");
		}
		/*
		 * if(company.getBasePass()!=1) company.setBasePass(3);
		 */
		company.setDwdz("抚顺市"
				+ companyService.findCompanyTypeNameByKey(company.getDwdz1(),
						"4028e56c4014f290014014f981510003"));

		try {
			if ("add".equalsIgnoreCase(this.flag)) {
				company.setDeptId(this.getLoginUserDepartmentId());
				company.setDelFlag(0);
				companyService.save(company);
				// hanxc 20141211 生成带审核任务 start
				ReviewLog newReviewLog = new ReviewLog();
				newReviewLog.setItemId(company.getId());
				newReviewLog.setItemType("1"); // 审核项类型 1、企业注册审核
				if ("1".equals(company.getIfzsqy())) {
					newReviewLog.setDutyFlag("1");// 市级部门审核任务
				} else {
					newReviewLog.setDutyFlag("2");// 县级部门审核任务
				}
				newReviewLog.setStartTime(new Date());
				reviewLogService.saveNewTask(newReviewLog);
				// hanxc 20141211 生成带审核任务 end
			} else {
				// hanxc 20141211 生成带审核任务 start
				ReviewLog newReviewLog = new ReviewLog();
				newReviewLog.setItemId(company.getId());
				newReviewLog.setItemType("1");// 审核项类型 1、企业注册审核
				if ("1".equals(company.getIfzsqy())) {
					newReviewLog.setDutyFlag("1");// 市级部门审核任务
				} else {
					newReviewLog.setDutyFlag("2");// 县级部门审核任务
				}
				newReviewLog.setStartTime(new Date());
				reviewLogService.saveNewTask(newReviewLog);
				// hanxc 20141211 生成带审核任务 end
				company.setBasePass(3);// hanxc 20141212 企业修改自己的信息后，需要再次审核
				company.setDutyFlag("0");
				companyService.update(company);
			}
			updateFlag = "1";
		} catch (Exception e) {
			updateFlag = "2";
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 高强 7月19 审核基本信息保存信息（包括新增和修改） 修改：增加视频名称 2013-12-18 by 陆婷
	 */
	public String saveBase() throws Exception {
		if (company.getIfwhpqylx().equals("0")) {
			filtCompanyType(company, "a");
			company.setWhpqylx("");
		}
		if (company.getIfzywhqylx().equals("0")) {
			filtCompanyType(company, "c");
			company.setZywhqylx("");
		}
		if (company.getIfyhbzjyqy().equals("0")) {
			filtCompanyType(company, "b");
			company.setYhbzjyqy("");
		}
		if (company.getIffmksjyqy().equals("0")) {
			filtCompanyType(company, "d");
			company.setFmksjyqy("");
		}
		if (company.getIffmgmqylx().equals("0")) {
			filtCompanyType(company, "e");
			company.setFmksjyqy("");
		}
		if ("add".equalsIgnoreCase(this.flag)) {
//			company.setDeptId(this.getLoginUserDepartmentId());
//			company.setDelFlag(0);
//			companyService.save(company);
			// hanxc 20141211 生成带审核任务 start
			ReviewLog newReviewLog = new ReviewLog();
			newReviewLog.setDelFlag(0);
			newReviewLog.setItemId(company.getId());
			newReviewLog.setItemType("1");// 审核项类型 1、企业注册审核
			if ("1".equals(company.getIfzsqy())) {//如果企业为直属企业，则直接由市级部门进行审核
				newReviewLog.setDutyFlag("1");// 市级部门审核任务
			} else {
				newReviewLog.setDutyFlag("2");// 县级部门审核任务
			}
			newReviewLog.setCreateTime(new Date());
			newReviewLog.setStartTime(new Date());
			newReviewLog.setRemark(company.getBaseRemark());
			newReviewLog.setState(company.getBasePass()+"");
			newReviewLog.setUserId(this.getLoginUserId());
			newReviewLog.setUserDeptCode(this.getLoginUser().getDeptCode());
			newReviewLog.setUserName(this.getLoginUser().getDisplayName());
			reviewLogService.saveNewTask(newReviewLog);
			// hanxc 20141211 生成带审核任务 end
		} else {
			company.setDwdz("抚顺市"
					+ companyService.findCompanyTypeNameByKey(
							company.getDwdz1(),
							"4028e56c4014f290014014f981510003"));
			/**
			 * 安监领导审批信息
			 */
			// hanxc 20141211 修改审批流程，该一级审批为二级审批 start
			String dutyFlag = "0";
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
					.getProperty("cityDeptCodeLength"))) {// 市级部门
				dutyFlag = "1";
			} else if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
					.getProperty("countyDeptCodeLength"))) {// 县级部门
				dutyFlag = "2";
			} else if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
					.getProperty("townDeptCodeLength"))) {// 镇级部门
				dutyFlag = "3";
			} else if (deptCode.length() == Integer.parseInt(SysPropertiesUtil
					.getProperty("villageDeptCodeLength"))) {// 村级部门
				dutyFlag = "4";
			}
			company.setDutyFlag(dutyFlag);

			Pagination page = new Pagination(this.getRequest());
			Map<String, Object> tempParaMap = new HashMap<String, Object>();
			tempParaMap.put("itemId", company.getId());
			tempParaMap.put("dutyFlag", dutyFlag);
			page = reviewLogService.findByPage(page, tempParaMap);
			List rlList = page.getList();
			if (!rlList.isEmpty()) {
				ReviewLog reviewLog = (ReviewLog) rlList.get(0);
				reviewLog.setItemType("1");// 审核项类型 1、企业注册审核
				reviewLog.setState(company.getBasePass() + "");
				reviewLog.setUserId(this.getLoginUserId());
				reviewLog.setUserName(this.getLoginUser().getDisplayName());
				reviewLog.setUserDeptCode(deptCode);
				reviewLog.setEndTime(new Date());
				reviewLog.setRecord("");
				reviewLog.setRemark(company.getBaseRemark());
				reviewLogService.saveReviewLogAndSetNextTask(reviewLog);
			}
			// hanxc 20141211 修改审批流程，该一级审批为二级审批 end

		}
		if (company.getBasePass() == 1){// 如果基本信息原来的审核状态为1：通过// ，则备份基本信息到备份表CompanyBackUp中
			/**
			 * 根据企业的id查询出备份记录
			 */
			Map map = new HashMap();
			map.put("companyId", company.getId());
			CompanyBackUp comBc = companyService.getCompanyBackupById(map);
			comBc.setCompanyname(company.getCompanyname());
			comBc.setCreateTime(new Date());
			comBc.setLoginname(company.getLoginname());
			comBc.setFddbr(company.getFddbr());
			comBc.setFddbrlxhm(company.getFddbrlxhm());
			comBc.setDwdz(company.getDwdz());
			comBc.setDwdz1(company.getDwdz1());
			comBc.setDwdz2(company.getDwdz2());
			comBc.setDelFlag(0);
			comBc.setZzjgdm(company.getZzjgdm());
			
			comBc.setQylx(company.getQylx());
			comBc.setHyfl(company.getHyfl());
			comBc.setLxr(company.getLxr());
			comBc.setMobile(company.getMobile());
			comBc.setLxfs(company.getLxfs());
			comBc.setQyyx(company.getQyyx());
			comBc.setGszch(company.getGszch());
			comBc.setQygm(company.getQygm());
			comBc.setQyzclx(company.getQyzclx());
			comBc.setZczj(company.getZczj());
			comBc.setWhpqylx(company.getWhpqylx());
			comBc.setIfwhpqylx(company.getIfwhpqylx());
			comBc.setZywhqylx(company.getZywhqylx());
			comBc.setIfzywhqylx(company.getIfzywhqylx());
			comBc.setFrdm(company.getFrdm());
			comBc.setQyclsj(company.getQyclsj());
			comBc.setGddh(company.getGddh());
			comBc.setCz(company.getCz());
			comBc.setYzbm(company.getYzbm());
			comBc.setSnxssr(company.getSnxssr());
			comBc.setSnsjss(company.getSnsjss());
			comBc.setSngdzc(company.getSngdzc());
			comBc.setSnwqtr(company.getSnwqtr());
			comBc.setSnaqscf(company.getSnaqscf());
			comBc.setSfaqjg(company.getSfaqjg());
			comBc.setAqglr(company.getAqglr());
			comBc.setSfzywsjg(company.getSfzywsjg());
			comBc.setZywsglry(company.getZywsglry());
			comBc.setSfqzwsgly(company.getSfqzwsgly());
			comBc.setZdmj(company.getZdmj());
			comBc.setJzmj(company.getJzmj());
			comBc.setCyry(company.getCyry());
			comBc.setSfyygss(company.getSfyygss());
			comBc.setAqbzdbjb(company.getAqbzdbjb());
			comBc.setJyfw(company.getJyfw());
			comBc.setUrl(company.getUrl());
			comBc.setIfurl(company.getIfurl());
			comBc.setBasePass(1);
			
			/**
			 * @author gq
			 * @date 8-27
			 * @reason bug添加
			 */
			comBc.setIfyhbzjyqy(company.getIfyhbzjyqy());
			comBc.setYhbzjyqy(company.getYhbzjyqy());
			comBc.setSzc(company.getSzc());
			comBc.setSzcname(company.getSzcname());
			comBc.setLongitude(company.getLongitude());
			comBc.setLatitude(company.getLatitude());
			// hanxc 20141212 为抚顺版本添加字段添加 start
			comBc.setIffmksjyqy(company.getIffmksjyqy());
			comBc.setFmksjyqy(company.getFmksjyqy());
			comBc.setDiggingstype(company.getDiggingstype());
			comBc.setMetal(company.getMetal());
			comBc.setVentilate(company.getVentilate());
			comBc.setTransport(company.getTransport());
			comBc.setRaisetype(company.getRaisetype());
			comBc.setSixsys(company.getSixsys());
			comBc.setCounty(company.getCounty());
			comBc.setIfzsqy(company.getIfzsqy());
			comBc.setZsqytype(company.getZsqytype());
			comBc.setZcCode(company.getZcCode());
			comBc.setDutyFlag(company.getDutyFlag());
			comBc.setCompanyType(company.getCompanyType());
			comBc.setAqscxkzh(company.getAqscxkzh());
			comBc.setFeature(company.getFeature());
			//hanxc 20141212 为抚顺版本添加字段添加 end
			companyService.updateCompanyBackUp(comBc);
			
			// 审核通过，改变企业角色
			User user = userService.findUserByLoginId(company.getLoginname());
			try {
				userService.delByUser(user.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// hanxc 20141212 企业信息修改后重新审核 start
			UserRight userRight = new UserRight();
			userRight.setUser(user);
			userRight.setRole(userRoleService.findRoleByCode(SysPropertiesUtil.getProperty("qiyeRoleCode")));// 企业人员
			userService.saveRight(userRight);
			if (company.getIfwhpqylx() != null
					&& company.getIfwhpqylx().equals("1")) {
				UserRight userRight1 = new UserRight();
				userRight1.setUser(user);
				userRight1.setRole(userRoleService
						.findRoleByCode(SysPropertiesUtil
								.getProperty("whpRoleCode")));// 危化品企业人员
				userService.saveRight(userRight1);
			}
			if (company.getIfzywhqylx() != null
					&& company.getIfzywhqylx().equals("1")) {
				UserRight userRight2 = new UserRight();
				userRight2.setUser(user);
				userRight2.setRole(userRoleService
						.findRoleByCode(SysPropertiesUtil
								.getProperty("zywhRoleCode")));// 职业健康企业人员
				userService.saveRight(userRight2);
			}
			if (company.getIfyhbzjyqy() != null
					&& company.getIfyhbzjyqy().equals("1")) {
				UserRight userRight3 = new UserRight();
				userRight3.setUser(user);
				userRight3.setRole(userRoleService
						.findRoleByCode(SysPropertiesUtil
								.getProperty("yhbzRoleCode")));// 烟花爆竹企业
				userService.saveRight(userRight3);
			}
			if (company.getIffmksjyqy() != null
					&& company.getIffmksjyqy().equals("1")) {
				UserRight userRight4 = new UserRight();
				userRight4.setUser(user);
				userRight4.setRole(userRoleService
						.findRoleByCode(SysPropertiesUtil
								.getProperty("fmksRoleCode")));// 非煤矿山企业
				userService.saveRight(userRight4);
			}
			// hanxc 20141212 企业信息修改后重新审核 end
			String num = companyService.findMaxCode();
			// String str=company.getDwdz1().split("[,]")[1];
			// String county = company.getCounty();
			// Map<String, Object> itemMap = new HashMap<String, Object>();
			// itemMap.put("itemText", str);
			// itemMap.put("SqlId", "findYwei");
			// List<HashMap<String, String>> list =
			// companyService.findListBySqlId(itemMap);
			// String pinyStr = list.get(0).get("ITEM_TEXT");
			// String strs = this.pingYin(str);
			String nums = this.maxShzi(num);
			// strs = strs + nums;
			company.setZcCode(nums);
		}
		companyService.update(company);
		updateFlag = "1";
		return RELOAD;
	}

	/**
	 * 高强 7月19 删除信息
	 */
	public String delete() throws Exception {
		// 李军 2013-09-09 删除企业信息 包括注册信息（company 、companyback ） 用户表（user）
		// 用户角色绑定表(userright)
		try {
			companyService.deleteCompanyById(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	public String updatePass() throws Exception {
		try {
			if ((null != company) && (null != company.getId())) {
				company = companyService.getById(company.getId());
				company.setLoginword("123456");
				companyService.update(company);
				Map map = new HashMap();
				map.put("companyId", company.getId());
				CompanyBackUp cbu = companyService.getCompanyBackupById(map);
				cbu.setLoginword("123456");
				companyService.updateCompanyBackUp(cbu);
				User user = userService.findUserByLoginId(company
						.getLoginname());
				user.setPassword(CodeUtil.encode("123456", "MD5"));
				companyService.updateUser(user);

				// 更新消防平台
				// map.put("loginname", company.getLoginname());
				// map.put("password", CodeUtil.encode("123456", "MD5"));
				// companyService.updateXfjcompany(map);

				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	public String queryCompanyList() {
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if (this.getLoginUserDepartment().getDeptName()
				.contains(SysPropertiesUtil.getProperty("townstr"))) {
			flag = "0";
		} else {
			flag = "1";
		}
		return SUCCESS;
	}

	public void companyList() {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil
				.getProperty("cityDeptCodeLength"));
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil
				.getProperty("countyDeptCodeLength"));
		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil
				.getProperty("townDeptCodeLength"));
		int villageDeptCodeLength = Integer.parseInt(SysPropertiesUtil
				.getProperty("villageDeptCodeLength"));
		if (pagination == null)
			pagination = new Pagination(this.getRequest());

		if (null != company) {
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != company.getCompanyname())
					&& (0 < company.getCompanyname().trim().length())) {
				paraMap.put("companyname", "%"
						+ company.getCompanyname().trim() + "%");
			}

			if ((null != company.getDwdz1())
					&& (0 < company.getDwdz1().trim().length())) {
				paraMap.put("dwdz1", company.getDwdz1());
			}

			paraMap.put("basePass", "1");

		}
		

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		try {
			pagination = companyService.findByPage(pagination, paraMap);
		} catch (Exception e) {
			e.printStackTrace();
		}

		convObjectToJson(pagination, null);
	}

	/**
	 * 安监局账号转化成企业账号 2013-12-6 陆婷 修改：将当前登录人信息保存至session by 陆婷 2013-12-9
	 * 
	 * @return
	 * @throws IOException
	 */
	public void info() throws IOException {
		// if((null != company)&&(null != company.getId()))
		// {
		// setType(type);
		// Map mm = new HashMap();
		// mm.put("companyId", company.getId());
		// companyBackUp = companyService.getCompanyBackupById(mm);
		// }
		try {
			String loginName = "";
			String companyName = "";
			if ((null != company) && (null != company.getId())) {
				Company c = companyService.getById(company.getId());
				loginName = c.getLoginname();
				companyName = c.getCompanyname();
			} else if ((null != companyBackUp)
					&& (null != companyBackUp.getId())) {
				Map map = new HashMap();
				map.put("id", companyBackUp.getId());
				CompanyBackUp c = companyService.getCompanyBackupById(map);
				loginName = c.getLoginname();
				companyName = c.getCompanyname();
			}
			if (loginName != null && !"".equals(loginName)
					&& companyName != null && !"".equals(companyName)) {
				String userId = this.getLoginUserId();
				String displayname = this.getLoginUser().getDisplayName();
				User user = userService.findUserByLoginId(loginName);
				if (user != null) {
					Department dept = deptService.findIndependenceDept(user
							.getDeptCode());
					if ((user.getDelFlag().intValue() == 1)
							|| (((Department) user.getDept()).getDelFlag()
									.intValue() == 1)) {
						this.getResponse()
								.getWriter()
								.println(
										"{\"result\":\"false\",\"message\":\"该企业用户账号被禁用！\"}");
					}
					List rightList = user.getUserRoles();
					if ((rightList != null) && (rightList.size() > 0)) {
						String[] roleIds = new String[rightList.size()];
						for (int i = 0; i < rightList.size(); i++) {
							UserRight right = (UserRight) rightList.get(i);
							roleIds[i] = right.getRole().getId();
							if ((right.getRole().getIsSupAdmin() != null)
									&& (1 == right.getRole().getIsSupAdmin()
											.intValue())) {
								user.setIsSuperAdmin(Boolean.valueOf(true));
							}
							if ((right.getRole().getIsDeptAdmin() != null)
									&& (1 == right.getRole().getIsDeptAdmin()
											.intValue())) {
								user.setIsDeptAdmin(Boolean.valueOf(true));
							}
						}
						user.setRoleIds(roleIds);
					}
					List childDeptIds = deptService.findChildDeptIds(user
							.getDeptCode());
					if (dept != null) {
						if (!user.getDeptCode().equals(dept.getDeptCode())) {
							childDeptIds = deptService.findChildDeptIds(dept
									.getDeptCode());
						}
						dept.setChildDeptIds(childDeptIds);
						user.setIndepenceDept(dept);
					}
					if ((user.getIsSuperAdmin() == null)
							|| (!user.getIsSuperAdmin().booleanValue()))
						super.setSessionAttribute("INDEPENDENCE_DEPT", dept);
					else
						super.setSessionAttribute("INDEPENDENCE_DEPT", null);
					Map userMap = (Map) Struts2Util.getServletContext()
							.getAttribute("CURR_USERS");
					if (userMap == null)
						userMap = new HashMap();
					userMap.put(user.getId(), user);
					Struts2Util.getServletContext().setAttribute("CURR_USERS",
							userMap);
//					getSession().setAttribute("curr_user", user);
					getSession().setAttribute("LOGIN_USER_ID", user.getId());
					Struts2Util.getServletContext().setAttribute("CURR_USERS",
							userMap);
					setSessionAttribute("oldUser", userId);
					setSessionAttribute("func", companyName);
					setSessionAttribute("oldName", displayname);
					this.getResponse().getWriter()
							.println("{\"result\":\"true\"}");
				}
			} else {
				this.getResponse()
						.getWriter()
						.println(
								"{\"result\":\"false\",\"message\":\"该企业用户账号不存在！\"}");
			}
		} catch (Exception e) {
			this.getResponse().getWriter()
					.println("{\"result\":\"false\",\"message\":\"出错啦！\"}");
		}
	}

	/**
	 * 企业账号转化成安监账号 2013-12-6 陆婷 修改：获取之前登录人信息 by 陆婷 2013-12-9
	 * 
	 * @return
	 * @throws IOException
	 */
	public String companyToSelf() throws IOException {
		try {
			String userId = (String) getSessionAttribute("oldUser");
			User user = userService.findUserById(userId);
			if (user != null) {
				Department dept = deptService.findIndependenceDept(user
						.getDeptCode());
				List rightList = user.getUserRoles();
				if ((rightList != null) && (rightList.size() > 0)) {
					String[] roleIds = new String[rightList.size()];
					for (int i = 0; i < rightList.size(); i++) {
						UserRight right = (UserRight) rightList.get(i);
						roleIds[i] = right.getRole().getId();
						if ((right.getRole().getIsSupAdmin() != null)
								&& (1 == right.getRole().getIsSupAdmin()
										.intValue())) {
							user.setIsSuperAdmin(Boolean.valueOf(true));
						}
						if ((right.getRole().getIsDeptAdmin() != null)
								&& (1 == right.getRole().getIsDeptAdmin()
										.intValue())) {
							user.setIsDeptAdmin(Boolean.valueOf(true));
						}
					}
					user.setRoleIds(roleIds);
				}
				List childDeptIds = deptService.findChildDeptIds(user
						.getDeptCode());
				if (dept != null) {
					if (!user.getDeptCode().equals(dept.getDeptCode())) {
						childDeptIds = deptService.findChildDeptIds(dept
								.getDeptCode());
					}
					dept.setChildDeptIds(childDeptIds);
					user.setIndepenceDept(dept);
				}
				if ((user.getIsSuperAdmin() == null)
						|| (!user.getIsSuperAdmin().booleanValue()))
					super.setSessionAttribute("INDEPENDENCE_DEPT", dept);
				else
					super.setSessionAttribute("INDEPENDENCE_DEPT", null);
				Map userMap = (Map) Struts2Util.getServletContext()
						.getAttribute("CURR_USERS");
				if (userMap == null)
					userMap = new HashMap();
				userMap.put(user.getId(), user);
				Struts2Util.getServletContext().setAttribute("CURR_USERS",
						userMap);
				getSession().setAttribute("curr_user", user);
				getSession().setAttribute("LOGIN_USER_ID", user.getId());
				Struts2Util.getServletContext().setAttribute("CURR_USERS",
						userMap);
				setSessionAttribute("oldUser", null);
				setSessionAttribute("func", null);
				setSessionAttribute("oldName", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void companyQuerySzc()
	{
		try {
			JSONArray json = new JSONArray();
			if(company != null && company.getDwdz1() != null && !"".equals(company.getDwdz1()))
			{
				deptlist = deptService.findDeptByParentDeptCode(company.getDwdz1());
				for(Department dept :deptlist){
					JSONObject jj = new JSONObject();
					jj.put("id", dept.getDeptCode());
					jj.put("name", dept.getDeptName());
					json.add(jj);
				}
			}
			PrintWriter out = getResponse().getWriter();
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询该部门下用户异常");
		}
	}
	
	public void companyQuerySzz() {
		try {
			JSONArray json = new JSONArray();
			if (company != null && company.getCounty() != null && !"".equals(company.getCounty())) {
				/**
				 * hanxc 2014/11/8
				 */
				List<Department> parlist = new ArrayList<Department>();
				parlist = deptService.findDeptByParentDeptCode(company.getCounty());
				String tempCode = "";
				for (Department department : parlist) {
					if ("乡镇安监站".equals(department.getDeptName())) {
						tempCode = department.getDeptCode();
					}
				}
				deptlist = deptService.findDeptByParentDeptCode(tempCode);
				// deptlist =
				// deptService.findDeptByParentDeptCode(company.getDwdz1());
				/**
				 * hanxc 2014/11/8 获取所在镇信息
				 */
				for (Department dept : deptlist) {
					JSONObject jj = new JSONObject();
					jj.put("id", dept.getDeptCode());

					String tempStr = dept.getDeptName();
//					int index = tempStr.lastIndexOf("安监站");
//					if(index>0){
//						tempStr = tempStr.substring(0, tempStr.lastIndexOf("安监站"));
//					}

					jj.put("name", tempStr);
					json.add(jj);
				}
			}
			PrintWriter out = getResponse().getWriter();
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询该部门下用户异常");
		}
	}

	public void export() {
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition",
					"attachment;filename=company.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("企业信息");
			sheet.setColumnWidth(0, 10000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 5000);
			sheet.setColumnWidth(3, 5000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(5, 5000);
			sheet.setColumnWidth(6, 5000);
			sheet.setColumnWidth(7, 10000);
			sheet.setColumnWidth(8, 5000);
			sheet.setColumnWidth(9, 5000);
			sheet.setColumnWidth(10, 5000);
			sheet.setColumnWidth(11, 5000);
			sheet.setColumnWidth(12, 5000);
			sheet.setColumnWidth(13, 5000);
			sheet.setColumnWidth(14, 5000);
			sheet.setColumnWidth(15, 5000);
			sheet.setColumnWidth(16, 10000);
			sheet.setColumnWidth(17, 5000);

			HSSFRow row = sheet.createRow(0);
			row.setHeight((short) (28 * 20));
			HSSFCell cell = row.createCell(0);
			cell.setCellValue("企业信息");
			HSSFCellStyle css = wb.createCellStyle();
			css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			css.setWrapText(true);
			css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont fonts = wb.createFont();
			fonts.setFontHeight((short) (20 * 20));
			css.setFont(fonts);
			cell.setCellStyle(css);
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 17));

			HSSFCellStyle cs = wb.createCellStyle();
			cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cs.setWrapText(true);
			cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont font = wb.createFont();
			font.setFontHeight((short) (16 * 16));
			cs.setFont(font);

			HSSFRow r3 = sheet.createRow(1);
			r3.setHeight((short) (23 * 20));
			HSSFCell ccl0 = r3.createCell(0);
			ccl0.setCellValue("单位名称");
			ccl0.setCellStyle(cs);
			HSSFCell ccl1 = r3.createCell(1);
			ccl1.setCellValue("法定代表人");
			ccl1.setCellStyle(cs);
			HSSFCell ccl2 = r3.createCell(2);
			ccl2.setCellValue("所在县");
			ccl2.setCellStyle(cs);
			HSSFCell ccl3 = r3.createCell(3);
			ccl3.setCellValue("所在镇");
			ccl3.setCellStyle(cs);
			HSSFCell ccl17 = r3.createCell(4);
			ccl17.setCellValue("用户名");
			ccl17.setCellStyle(cs);
			HSSFCell ccl4 = r3.createCell(5);
			ccl4.setCellValue("工商注册号");
			ccl4.setCellStyle(cs);
			HSSFCell ccl5 = r3.createCell(6);
			ccl5.setCellValue("企业类型");
			ccl5.setCellStyle(cs);
			HSSFCell ccl6 = r3.createCell(7);
			ccl6.setCellValue("行业分类");
			ccl6.setCellStyle(cs);
			HSSFCell ccl7 = r3.createCell(8);
			ccl7.setCellValue("安全管理员");
			ccl7.setCellStyle(cs);

			HSSFCell ccl8 = r3.createCell(9);
			ccl8.setCellValue("组织机构代码");
			ccl8.setCellStyle(cs);
			HSSFCell ccl9 = r3.createCell(10);
			ccl9.setCellValue("企业规模");
			ccl9.setCellStyle(cs);
			HSSFCell ccl10 = r3.createCell(11);
			ccl10.setCellValue("企业成立时间");
			ccl10.setCellStyle(cs);
			HSSFCell ccl11 = r3.createCell(12);
			ccl11.setCellValue("企业注册类型");
			ccl11.setCellStyle(cs);
			HSSFCell ccl12 = r3.createCell(13);
			ccl12.setCellValue("占地面积（m2）");
			ccl12.setCellStyle(cs);
			HSSFCell ccl13 = r3.createCell(14);
			ccl13.setCellValue("建筑面积（m2）");
			ccl13.setCellStyle(cs);
			HSSFCell ccl14 = r3.createCell(15);
			ccl14.setCellValue("从业人员（人）");
			ccl14.setCellStyle(cs);
			HSSFCell ccl15 = r3.createCell(16);
			ccl15.setCellValue("安全生产标准化达标级别");
			ccl15.setCellStyle(cs);
			HSSFCell ccl16 = r3.createCell(17);
			ccl16.setCellValue("审核状态");
			ccl16.setCellStyle(cs);

			HSSFCellStyle c = wb.createCellStyle();
			c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			c.setWrapText(true);
			c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			HSSFFont fon = wb.createFont();
			fon.setFontHeight((short) (15 * 15));
			c.setFont(fon);

			Map<String, Object> paraMap = new HashMap<String, Object>();

			if (flag == null || "".equals(flag)) {
				company = (Company) getSessionAttribute("company");
				queryQyclsjStart = (Date) getSessionAttribute("queryQyclsjStart");
				queryQyclsjEnd = (Date) getSessionAttribute("queryQyclsjEnd");
			}
			if (null != company) {
				setSessionAttribute("company", company);
				// 设置查询条件，开发人员可以在此增加过滤条件
				if ((null != company.getCompanyname())
						&& (0 < company.getCompanyname().trim().length())) {
					paraMap.put("companyname", "%"
							+ company.getCompanyname().trim() + "%");
				}

				if ((null != company.getFddbr())
						&& (0 < company.getFddbr().trim().length())) {
					paraMap.put("fddbr", "%" + company.getFddbr().trim() + "%");
				}

				if ((null != company.getZzjgdm())
						&& (0 < company.getZzjgdm().trim().length())) {
					paraMap.put("zzjgdm", "%" + company.getZzjgdm().trim()
							+ "%");
				}

				if ((null != company.getGszch())
						&& (0 < company.getGszch().trim().length())) {
					paraMap.put("gszch", "%" + company.getGszch().trim() + "%");
				}

				if ((null != company.getQylx())
						&& (0 < company.getQylx().trim().length())) {
					paraMap.put("qylx", company.getQylx().trim());
				}

				if ((null != company.getHyfl())
						&& (0 < company.getHyfl().trim().length())) {
					paraMap.put("hyfl", company.getHyfl().trim());
				}

				if ((null != company.getQyzclx())
						&& (0 < company.getQyzclx().trim().length())) {
					paraMap.put("qyzclx", company.getQyzclx().trim());
				}

				if ((null != company.getYhbzjyqy())
						&& (0 < company.getYhbzjyqy().trim().length())) {
					paraMap.put("ifyhbzjyqy", "1");
					paraMap.put("Qyhbzjyqy", "%" + company.getYhbzjyqy().trim()
							+ "%");

				}
				if ((null != company.getZywhqylx())
						&& (0 < company.getZywhqylx().trim().length())) {
					paraMap.put("ifzywhqylx", "1");
					paraMap.put("Qzywhqylx", "%" + company.getZywhqylx().trim()
							+ "%");

				}

				if ((null != company.getWhpqylx())
						&& (0 < company.getWhpqylx().trim().length())) {
					paraMap.put("ifwhpqylx", "1");
					paraMap.put("Qwhpqylx", "%" + company.getWhpqylx().trim()
							+ "%");

				}

				if ((null != company.getDwdz1())
						&& (0 < company.getDwdz1().trim().length())) {
					paraMap.put("dwdz1", company.getDwdz1().trim());
				}
				if ((null != company.getSzc())
						&& (0 < company.getSzc().trim().length())) {
					paraMap.put("szc", company.getSzc().trim());
				}

				if (company.getBasePass() != 3)
					paraMap.put("basePass", company.getBasePass());

				if ((null != company.getIfzywhqylx())
						&& (0 < company.getIfzywhqylx().trim().length())) {
					paraMap.put("ifzywhqylx", company.getIfzywhqylx().trim());
				}

				if ((null != company.getIfwhpqylx())
						&& (0 < company.getIfwhpqylx().trim().length())) {
					paraMap.put("ifwhpqylx", company.getIfwhpqylx().trim());
				}
				if ((null != company.getAqbzdbjb())
						&& (0 < company.getAqbzdbjb().trim().length())) {
					paraMap.put("aqbzdbjb", company.getAqbzdbjb().trim());
				}
			}
			if (null != queryQyclsjStart) {
				paraMap.put("startQyclsj", queryQyclsjStart);
				setSessionAttribute("queryQyclsjStart", queryQyclsjStart);
			}

			if (null != queryQyclsjEnd) {
				paraMap.put("endQyclsj", queryQyclsjEnd);
				setSessionAttribute("queryQyclsjEnd", queryQyclsjEnd);
			}
			// hanxc 20141223 修改 start
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(null != deptRole && SysPropertiesUtil.getProperty("qiyeCode").equals(deptRole)){//企业角色
				paraMap.put("loginname", this.getLoginUser().getLoginId());
			}else if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//非管理员登录
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			// hanxc 20141223 修改 end
			 
			List<Company> list = companyService.findCompany(paraMap);
			int num = 2;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			for (Company company : list) {
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(StringTools.NullToStr(
						company.getCompanyname(), ""));
				ce0.setCellStyle(c);
				HSSFCell ce1 = ro.createCell(1);
				ce1.setCellValue(StringTools.NullToStr(company.getFddbr(), ""));
				ce1.setCellStyle(c);
				HSSFCell ce2 = ro.createCell(2);
				
				Map mm = new HashMap();
				mm.put("county", company.getCounty());
				List<DataBean> ll = httpService.getCountyList(mm);
				String county = "";
				if (ll.size() != 0) {
					county = ll.get(0).getRname();
				}
				
				Department tempDept = deptService.findDeptByDeptCode(company.getDwdz1());
				String szzname = "";
				if(null != tempDept){
					String tempSzzname = tempDept.getDeptName();
//					szzname = tempSzzname.substring(0, tempSzzname.lastIndexOf("安监中队"));
					szzname = tempDept.getDeptName();
				}
				ce2.setCellValue(county);
				
				ce2.setCellStyle(c);
				HSSFCell ce3 = ro.createCell(3);
				ce3.setCellValue(szzname);
				ce3.setCellStyle(c);
				HSSFCell ce17 = ro.createCell(4);
				ce17.setCellValue(StringTools.NullToStr(company.getLoginname(),
						""));
				ce17.setCellStyle(c);
				HSSFCell ce4 = ro.createCell(5);
				ce4.setCellValue(StringTools.NullToStr(company.getGszch(), ""));
				ce4.setCellStyle(c);
				HSSFCell ce5 = ro.createCell(6);
				ce5.setCellValue(null != company.getQylx() ? (String) companyService
						.findCompanyTypeNameByKey(company.getQylx(),
								"4028e56c3ff0d189013ff0e6b99e000c") : "");
				ce5.setCellStyle(c);
				HSSFCell ce6 = ro.createCell(7);
				ce6.setCellValue(null != company.getHyfl() ? (String) companyService
						.findCompanyTypeNameByKey(company.getHyfl(),
								"402880484076bce30140a04236590a02") : "");
				ce6.setCellStyle(c);
				HSSFCell ce7 = ro.createCell(8);
				ce7.setCellValue(StringTools.NullToStr(company.getLxr(), ""));
				ce7.setCellStyle(c);
				HSSFCell ce8 = ro.createCell(9);
				ce8.setCellValue(StringTools.NullToStr(company.getZzjgdm(), ""));
				ce8.setCellStyle(c);
				HSSFCell ce9 = ro.createCell(10);
				ce9.setCellValue(null != company.getQygm() ? (String) companyService
						.findCompanyTypeNameByKey(company.getQygm(),
								"4028e56c3ff0d189013ff0feee650023") : "");
				ce9.setCellStyle(c);
				HSSFCell ce10 = ro.createCell(11);
				String qyclsj = "";
				if (null != company.getQyclsj()) {
					qyclsj = sdf.format(company.getQyclsj());
				}
				ce10.setCellValue(qyclsj);
				ce10.setCellStyle(c);
				HSSFCell ce11 = ro.createCell(12);
				ce11.setCellValue(null != company.getQyzclx() ? (String) companyService
						.findCompanyTypeNameByKey(company.getQyzclx(),
								"402880484076bce30140a04025e509f7") : "");
				ce11.setCellStyle(c);
				HSSFCell ce12 = ro.createCell(13);
				ce12.setCellValue(null != company.getZdmj() ? company.getZdmj()
						+ "" : "");
				ce12.setCellStyle(c);
				HSSFCell ce13 = ro.createCell(14);
				ce13.setCellValue(null != company.getJzmj() ? company.getJzmj()
						+ "" : "");
				ce13.setCellStyle(c);
				HSSFCell ce14 = ro.createCell(15);
				ce14.setCellValue(null != company.getCyry() ? company.getCyry()
						+ "" : "");
				ce14.setCellStyle(c);
				HSSFCell ce15 = ro.createCell(16);
				ce15.setCellValue(null != company.getAqbzdbjb() ? (String) companyService
						.findCompanyTypeNameByKey(company.getAqbzdbjb(),
								"402880484028bef601404c8b68a40047") : "");
				ce15.setCellStyle(c);
				HSSFCell ce16 = ro.createCell(17);
				String basepass = "";
				if (company.getBasePass() == 3 || company.getBasePass() == 0) {
					basepass = "待审核";
				} else if (company.getBasePass() == 2) {
					basepass = "未通过";
				} else if (company.getBasePass() == 1) {
					basepass = "通过";
				}
				ce16.setCellValue(basepass);
				ce16.setCellStyle(c);
				num++;
			}

			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String initImportCompany(){
	    return "success";
	}
	private void throwBeException(String message) {
	    BusinessException be = new BusinessException();
	    be.add("EDP003", "error",  message);
	    throw be;
	}
	public String importCompany2()
	{
	      Workbook workbook = null;
	      try {
	        workbook = Workbook.getWorkbook(this.companyFile);
	      } catch (BiffException e) {
	        e.printStackTrace();
	        throwBeException("读取Excle文件出错，请检查!");
	      } catch (IOException e) {
	        e.printStackTrace();
	        throwBeException("读取Excle文件出错，请检查!");
	      }
	      Sheet sheet = workbook.getSheet(0);

	      int column = sheet.getColumns();
	      int row = sheet.getRows();
	      List<Company> comList = new ArrayList();
	      for (int i = 1; i < row; i++) {
		        Cell[] cells = new Cell[column];
		        Cell[] cellsTmp = sheet.getRow(i);
		        for (int j = 0; j < cellsTmp.length; j++) {
		          cells[j] = cellsTmp[j];
		        }
		        Company com = new Company();
		        User user = new User();
	
		        if (cells[0].getContents().equals("")) {
		          workbook.close();
		          throwBeException("组织机构代码输入有误，不能为空，请检查!");
		        }
		        com.setLoginname(cells[0].getContents().trim());
		        
		        User userCheck = userService.findUserByLoginId(company.getLoginname());
				if (userCheck != null){
			          workbook.close();
			          throwBeException("该组织机构代码:"+cells[0].getContents().trim()+"已经存在，请检查!");
			    }
					
		        if (cells[1].getContents().equals("")) {
		          workbook.close();
		          throwBeException("单位详细名称输入有误，不能为空，请检查!");
		        }
		        com.setCompanyname(cells[1].getContents().trim());
		        
		        if (cells[2].getContents().equals("")) {
		          workbook.close();
		          throwBeException("法定代表人输入有误，不能为空，请检查!");
		        }
		        com.setFddbr(cells[2].getContents().trim());
		        
		        if (cells[4] != null)
		        	com.setJyfw(cells[4].getContents().trim());
		        if (cells[5] != null)
		        	com.setCyry(Long.parseLong(cells[5].getContents().trim()));
		        if (cells[8] != null)
		            com.setDwdz2(cells[8].getContents().trim());
		        if (cells[10] != null)
		            com.setFddbrlxhm(cells[10].getContents().trim());
		        if (cells[11] != null)
		            com.setGddh(cells[11].getContents().trim());
		        
		        com.setDelFlag(Integer.valueOf(0));
		        comList.add(com);
	      }
	      try
	      {
	    	  for(Company com:comList){
	    		  companyService.save(com);
	    		  
	    		  //将新增企业作为部门 hanxc 2014-11-04
					Department companydept = new Department();
					companydept.setCreateTime(new Date());
					companydept.setCreateUserID(com.getLoginname().trim());
					companydept.setDelFlag(0);
					companydept.setDeptCode(deptService.createDeptCode("033"));
					companydept.setDeptName(com.getCompanyname());
					companydept.setDeptTypeCode("04");
					companydept.setHasChild(0);
					companydept.setNeedSubFlow("1");
					companydept.setSortSQ(1);
					companydept.setParentDept(deptService.findDeptByDeptCode("033"));
					deptService.save(companydept);
					
					User user = new User();
					user.setDelFlag(0);
					user.setDeptCode(companydept.getDeptCode());
					user.setCssId("default");
					user.setDisplayName(com.getCompanyname().trim());
					user.setLoginId(com.getLoginname().trim());
					user.setPassword(CodeUtil.encode("99999", "MD5"));
					user.setSortSq(1);
					user.setDeptRole("21");// 企业部门角色
					user.setDept(companydept);
					userService.saveUser(user);

					UserRight userRight = new UserRight();
					userRight.setUser(user);
					userRight.setRole(userRoleService.findRoleByCode(SysPropertiesUtil.getProperty("tempRoleCode")));// 临时企业人员
					userService.saveRight(userRight);
					// hanxc 20141211 生成待审核任务 start
					ReviewLog newReviewLog = new ReviewLog();
					newReviewLog.setDutyFlag("1");// 市级部门审核任务
					newReviewLog.setItemId(company.getId());
					newReviewLog.setItemType("1");// 企业信息类型：type=1
					newReviewLog.setStartTime(new Date());
					reviewLogService.saveNewTask(newReviewLog);
	    	  }
	      } catch (Exception e) {
	        workbook.close();
	        e.printStackTrace();
	        throwBeException("保存数据库出错!");
	      }
	      workbook.close();
	      return "success";
	  }
	private String dealCell(HSSFCell cell) {
		String result = "";

		try {
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				BigDecimal db = new BigDecimal(cell.toString().trim());
				result = db.toPlainString();
			} else {
				result = cell.toString().trim();
			}
		} catch (Exception e1) {
			System.out.println("Excel科学计数法数字转换失败："
					+ (null != cell ? cell.toString() : ""));
			e1.printStackTrace();
		}
		if (-1 != result.indexOf(".")) {
			result = result.substring(0, result.indexOf("."));
		}
		return result;
	}
	
	private String dealCell(XSSFCell cell) {
		String result = "";
		if(null == cell){
			return result;
		}

		try {
			if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
				BigDecimal db = new BigDecimal(cell.toString().trim());
				result = db.toPlainString();
			} else {
				result = cell.toString().trim();
			}
		} catch (Exception e1) {
			System.out.println("Excel科学计数法数字转换失败："
					+ (null != cell ? cell.toString() : ""));
			e1.printStackTrace();
		}
		if (-1 != result.indexOf(".")) {
			result = result.substring(0, result.indexOf("."));
		}
		return result;
	}
	
	public String importCompany() {
		StringBuffer log = new StringBuffer("导入企业日志：<br>");
		HSSFWorkbook workbook = null;
		XSSFWorkbook xssfWorkbook = null;
		try {
			workbook = new HSSFWorkbook(new FileInputStream(this.companyFile));
		}catch(OfficeXmlFileException e){
			try {
				xssfWorkbook =  new XSSFWorkbook(new FileInputStream(this.companyFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				log.append("导入失败：文件不存在!<br>");
				this.logInfo = log.toString();
				return "success";
			} catch (IOException e1) {
				e1.printStackTrace();
				log.append("导入失败：读取Excle文件出错，请检查!<br>");
				this.logInfo = log.toString();
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.append("导入失败：读取Excle文件出错，请检查!<br>");
			this.logInfo = log.toString();
			return "success";
		}
		List<Company> companyList = new ArrayList<Company>();
		List<Department> deptList = new ArrayList<Department>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String zzjgdm = null;
		String companyname = null;
		String deptName = null;
		Company company = null;
		String deptCode = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long aqglr = null;
		Long zywsglry = null;
		String zdmj = null;
		String jzmj = null;
		Long cyry = null;
		String gmqylx = null;
		String whpqylx = null;
		String zywhqylx = null;
		String yhbzqylx = null;
		String companytype = null;
		boolean addCompanyFlag = true;
		String tyshxydm = null;
		
		if(null != workbook){
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFRow row = null;
			int index = 0;
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				try {
					row = sheet.getRow(i);
					//企业名称
					companyname = null != row.getCell(0) ? row.getCell(0)
							.toString().trim() : "";
					if(StringUtils.isEmpty(companyname)){
						continue;
					}
					// 组织机构代码
//					zzjgdm = null != row.getCell(1) ? row.getCell(1).toString()
//							.trim() : "";
					// 统一社会信用代码
//					tyshxydm = null != row.getCell(50) ? row.getCell(50).toString()
//					.trim() : "";
//					if ("".equals(zzjgdm) && "".equals(tyshxydm)) {
//						log.append("excel行数").append(i)
//								.append("更新失败：组织机构代码和统一社会信用代码不能同时为空！<br>");
//						continue;
//					}
//					// 处理科学计数法数字
//					if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//						zzjgdm = dealCell(row.getCell(1));
//						tyshxydm = dealCell(row.getCell(50));
//					}

					paraMap.clear();
					paraMap.put("companyname", companyname);
					/*if(StringUtils.isNotEmpty(tyshxydm)){
						paraMap.put("tyshxydm", tyshxydm);
					}
					if(StringUtils.isNotEmpty(zzjgdm)){
						paraMap.put("zzjgdm", zzjgdm);
					}*/
					companyList = companyService.findCompList(paraMap);

					if (null != companyList && companyList.size() > 0) {// 更新企业信息
						continue;
//						company = companyList.get(0);
//						addCompanyFlag = false;
					} else {// 新增企业信息
						company = new Company();
						company.setLoginname(companyname);
						company.setLoginword(CodeUtil.encode("99999", "MD5"));
						addCompanyFlag = true;
					}
					company.setDelFlag(0);
					// 处理所在镇
					deptName = null != row.getCell(3) ? row.getCell(3).toString()
							.trim() : "";
					paraMap.clear();
					paraMap.put("deptName", deptName + "%");
					deptList = deptService.findAllDept(paraMap);
					deptCode = "";
					if (deptList.size() > 0) {
						deptCode = deptList.get(0).getDeptCode();
					}

					companytype = "";
					// 处理工贸企业类型
					if (null != row.getCell(16)
							&& !"".equals(row.getCell(16).toString())) {
						gmqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(16).toString().trim(),
								"402881e54a656177014a657c7b95000b").toString();
						if (null != gmqylx && !"".equals(gmqylx)) {
							companytype = companytype + gmqylx + ",";
						}
					}

					// 处理危化品企业类型
					if (null != row.getCell(18)
							&& !"".equals(row.getCell(18).toString())) {
						whpqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(18).toString().trim(),
								"4028e56c40a9a6750140a9c91e2f0007").toString();
						if (null != whpqylx && !"".equals(whpqylx)) {
							companytype = companytype + whpqylx + ",";
						}
					}
					// 处理职业危害企业类型
					if (null != row.getCell(20)
							&& !"".equals(row.getCell(20).toString())) {
						zywhqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(20).toString().trim(),
								"4028e56c3ff0d189013ff1096cbc0037").toString();
						if (null != zywhqylx && !"".equals(zywhqylx)) {
							companytype = companytype + zywhqylx + ",";
						}
					}
					// 处理烟花爆竹企业类型
					if (null != row.getCell(22)
							&& !"".equals(row.getCell(22).toString())) {
						yhbzqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(22).toString().trim(),
								"4028804840b9689c0140c440505a033b").toString();
						if (null != yhbzqylx && !"".equals(yhbzqylx)) {
							companytype = companytype + yhbzqylx + ",";
						}
					}
					company.setZzjgdm(null != row.getCell(1)
							&& !"".equals(row.getCell(1).toString().trim()) ? dealCell(row
							.getCell(1)) : company.getZzjgdm());
					company.setCompanyname(null != companyname
							&& !"".equals(companyname) ? companyname : company
							.getCompanyname());
					company.setFddbr(null != row.getCell(13)
							&& !"".equals(row.getCell(13).toString().trim()) ? row
							.getCell(13).toString().trim() : company.getFddbr());
					company.setFddbrlxhm(null != row.getCell(2)
							&& !"".equals(row.getCell(2).toString().trim()) ? dealCell(row
							.getCell(2)) : company.getFddbrlxhm());
					company.setDwdz(null != row.getCell(3)
							&& !"".equals(row.getCell(3).toString().trim()) ? row
							.getCell(3).toString().trim() : company.getDwdz());
					company.setDwdz1(deptCode);

					company.setSzcname(null != row.getCell(4)
							&& !"".equals(row.getCell(4).toString().trim()) ? row
							.getCell(4).toString().trim() : company.getSzcname());

					paraMap.clear();
					paraMap.put("deptName", company.getSzcname() + "%");
					List<Department> szcDeptList = deptService.findAllDept(paraMap);
					String szcDeptCode = "";
					if (szcDeptList.size() > 0) {
						szcDeptCode = szcDeptList.get(0).getDeptCode();
					}
					company.setSzc(szcDeptCode);

					company.setDwdz2(null != row.getCell(5)
							&& !"".equals(row.getCell(5).toString().trim()) ? row
							.getCell(5).toString().trim() : company.getDwdz2());
					company.setGszch(null != row.getCell(6)
							&& !"".equals(row.getCell(6).toString().trim()) ? dealCell(row
							.getCell(6)) : company.getGszch());
					company.setAqscxkzh(null != row.getCell(7)
							&& !"".equals(row.getCell(7).toString().trim()) ? dealCell(row
							.getCell(7)) : company.getAqscxkzh());
					if (null != row.getCell(8)
							&& !"".equals(row.getCell(8).toString())) {
						company.setQylx(companyService.findCompanyTypeNameByValue(
								row.getCell(8).toString().trim(),
								"4028e56c3ff0d189013ff0e6b99e000c").toString());
					}
					if (null != row.getCell(9)
							&& !"".equals(row.getCell(9).toString())) {
						company.setHyfl(companyService.findCompanyTypeNameByValue(
								row.getCell(9).toString().trim(),
								"402880484076bce30140a04236590a02").toString());
					}
					company.setLxr(null != row.getCell(10)
							&& !"".equals(row.getCell(10).toString().trim()) ? row
							.getCell(10).toString().trim() : company.getLxr());
					company.setMobile(null != row.getCell(11)
							&& !"".equals(row.getCell(11).toString().trim()) ? dealCell(row
							.getCell(11)) : company.getMobile());
					company.setLxfs(null != row.getCell(12)
							&& !"".equals(row.getCell(12).toString().trim()) ? dealCell(row
							.getCell(12)) : company.getLxfs());
					company.setQyyx(null != row.getCell(14)
							&& !"".equals(row.getCell(14).toString().trim()) ? row
							.getCell(14).toString().trim() : company.getQyyx());
					company.setIffmgmqylx((null != row.getCell(15)
							&& !"".equals(row.getCell(15).toString().trim()) && row
							.getCell(15).toString().trim().equals("是")) ? "1" : "0");
					company.setIfwhpqylx((null != row.getCell(17)
							&& !"".equals(row.getCell(17).toString().trim()) && row
							.getCell(17).toString().trim().equals("是")) ? "1" : "0");
					company.setIfzywhqylx((null != row.getCell(19)
							&& !"".equals(row.getCell(19).toString().trim()) && row
							.getCell(19).toString().trim().equals("是")) ? "1" : "0");
					company.setIfyhbzjyqy((null != row.getCell(21)
							&& !"".equals(row.getCell(21).toString().trim()) && row
							.getCell(21).toString().trim().equals("是")) ? "1" : "0");
					if (null != row.getCell(23)
							&& !"".equals(row.getCell(23).toString())) {
						company.setQygm(companyService.findCompanyTypeNameByValue(
								row.getCell(23).toString().trim(),
								"4028e56c3ff0d189013ff0feee650023").toString());
					}
					if (null != row.getCell(24)
							&& !"".equals(row.getCell(24).toString())) {
						try {
							// 1、判断是否是数值格式
							if (row.getCell(24).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								short format = row.getCell(24).getCellStyle()
										.getDataFormat();
								SimpleDateFormat simpleDF = null;
								if (format == 14 || format == 31 || format == 57
										|| format == 58) {
									// 日期
									simpleDF = new SimpleDateFormat("yyyy-MM-dd");
								} else if (format == 20 || format == 32) {
									// 时间
									simpleDF = new SimpleDateFormat("HH:mm");
								}
								double value = row.getCell(24)
										.getNumericCellValue();
								Date date = org.apache.poi.ss.usermodel.DateUtil
										.getJavaDate(value);
								company.setQyclsj(date);
							}
						} catch (Exception e) {
							log.append("excel行数").append(i).append("日期格式不正确！<br>");
						}
					}
					if (null != row.getCell(25)
							&& !"".equals(row.getCell(25).toString())) {
						company.setQyzclx(companyService
								.findCompanyTypeNameByValue(
										row.getCell(25).toString().trim(),
										"402880484076bce30140a04025e509f7")
								.toString());
					}
					company.setZczj(null != row.getCell(26)
							&& !"".equals(row.getCell(26).toString().trim()) ? dealCell(row
							.getCell(26)) : company.getZczj());
					company.setGddh(null != row.getCell(27)
							&& !"".equals(row.getCell(27).toString().trim()) ? dealCell(row
							.getCell(27)) : company.getGddh());
					company.setCz(null != row.getCell(28)
							&& !"".equals(row.getCell(28).toString().trim()) ? dealCell(row
							.getCell(28)) : company.getCz());
					company.setYzbm(null != row.getCell(29)
							&& !"".equals(row.getCell(29).toString().trim()) ? dealCell(row
							.getCell(29)) : company.getYzbm());
					company.setSnxssr(null != row.getCell(30)
							&& !"".equals(row.getCell(30).toString().trim()) ? dealCell(row
							.getCell(30)) : company.getSnxssr());
					company.setSnsjss(null != row.getCell(31)
							&& !"".equals(row.getCell(31).toString().trim()) ? (Integer
							.parseInt(dealCell(row.getCell(30))) * 0.03) + ""
							: company.getSnsjss());
					company.setSngdzc(null != row.getCell(32)
							&& !"".equals(row.getCell(32).toString().trim()) ? dealCell(row
							.getCell(32)) : company.getSngdzc());
					company.setSnwqtr(null != row.getCell(33)
							&& !"".equals(row.getCell(33).toString().trim()) ? dealCell(row
							.getCell(33)) : company.getSnwqtr());
					company.setSnaqscf(null != row.getCell(34)
							&& !"".equals(row.getCell(34).toString().trim()) ? dealCell(row
							.getCell(34)) : company.getSnaqscf());
					company.setSfaqjg((null != row.getCell(35)
							&& !"".equals(row.getCell(35).toString().trim()) && row
							.getCell(35).toString().trim().equals("是")) ? "1" : "0");
					try {
						if (null != row.getCell(36)
								&& !"".equals(row.getCell(36).toString())) {
							aqglr = Long.parseLong(dealCell(row.getCell(36)));
							company.setAqglr(aqglr);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("安全管理员（人）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}

					company.setSfqzaqgly((null != row.getCell(37) && row
							.getCell(37).toString().trim().equals("是")) ? "1" : "0");

					company.setSfzywsjg((null != row.getCell(38) && row.getCell(38)
							.toString().trim().equals("是")) ? "1" : "0");
					try {
						if (null != row.getCell(39)
								&& !"".equals(row.getCell(39).toString())) {
							zywsglry = Long.parseLong(dealCell(row.getCell(39)));
							company.setZywsglry(zywsglry);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("职业卫生管理人员（人）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					company.setSfqzwsgly((null != row.getCell(40) && row
							.getCell(40).toString().trim().equals("是")) ? "1" : "0");
					try {
						if (null != row.getCell(41)
								&& !"".equals(row.getCell(41).toString())) {
							zdmj = dealCell(row.getCell(41));
							company.setZdmj(zdmj);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("占地面积（m2）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					try {
						if (null != row.getCell(42)
								&& !"".equals(row.getCell(42).toString())) {
							jzmj = dealCell(row.getCell(42));
							company.setJzmj(jzmj);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("建筑面积（m2）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					try {
						if (null != row.getCell(43)
								&& !"".equals(row.getCell(43).toString())) {
							cyry = Long.parseLong(dealCell(row.getCell(43)));
							company.setCyry(cyry);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("从业人员（人）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					company.setSfyygss((null != row.getCell(44) && row.getCell(44)
							.toString().trim().equals("是")) ? "1" : "0");
					if (null != row.getCell(45)
							&& !"".equals(row.getCell(45).toString())) {
						company.setAqbzdbjb(companyService
								.findCompanyTypeNameByValue(
										row.getCell(45).toString().trim(),
										"402880484028bef601404c8b68a40047")
								.toString());
					}
					company.setJyfw(null != row.getCell(46)
							&& !"".equals(row.getCell(46).toString().trim()) ? dealCell(row
							.getCell(46)) : company.getJyfw());
					company.setUrl(null != row.getCell(47)
							&& !"".equals(row.getCell(47).toString().trim()) ? dealCell(row
							.getCell(47)) : company.getUrl());
					company.setCompanyType(companytype);
					//add by wuhuaqing begin
					company.setSfcjaqzrx(null != row.getCell(48)
							&& !"".equals(row.getCell(48).toString().trim()) ? dealCell(row
							.getCell(48)) : company.getSfcjaqzrx());
					company.setSfszaqzj(null != row.getCell(49)
							&& !"".equals(row.getCell(49).toString().trim()) ? dealCell(row
							.getCell(49)) : company.getSfszaqzj());
					company.setTyshxydm(null != row.getCell(50)
							&& !"".equals(row.getCell(50).toString().trim()) ? dealCell(row
							.getCell(50)) : company.getTyshxydm());
					//add by wuhuaqing end
					
					companyService.update(company);

					if (addCompanyFlag) {
						User user = new User();
						user.setDelFlag(0);
						user.setDeptCode(SysPropertiesUtil
								.getProperty("qiyeDeptCode"));
						user.setCssId("default");
						user.setDisplayName(company.getCompanyname().trim());
						//modify by wuhuaqing begin
						//将组织机构代码或社会信用代码设置为登录用户名
						String loginId = "";
						if(StringUtil.isNotEmpty(company.getZzjgdm()) && !"0".equals(company.getZzjgdm())){
							loginId = company.getZzjgdm().trim();
						}else if(StringUtil.isNotEmpty(company.getTyshxydm()) && !"0".equals(company.getTyshxydm())){
							loginId = company.getTyshxydm().trim();
						}
						//modify by wuhuaqing end
						user.setLoginId(company.getLoginname().trim());
						user.setPassword(CodeUtil.encode("99999", "MD5"));
						user.setMobile(company.getMobile());
						user.setSortSq(1);
						user.setDeptRole("21");// 企业部门角色
						user.setDept(deptService
								.findDeptByDeptCode(SysPropertiesUtil
										.getProperty("qiyeDeptCode")));
						userService.saveUser(user);

						UserRight userRight = new UserRight();
						userRight.setUser(user);
						userRight.setRole(userRoleService
								.findRoleByCode(SysPropertiesUtil
										.getProperty("tempRoleCode")));// 临时企业人员
						userService.saveRight(userRight);
						// hanxc 20141211 生成待审核任务 start
						ReviewLog newReviewLog = new ReviewLog();
						/*
						 * if (company.getIfzsqy().equals("1")) {
						 * newReviewLog.setDutyFlag("1");// 市级部门审核任务 } else {
						 * newReviewLog.setDutyFlag("2");// 县级部门审核任务 }
						 */
						newReviewLog.setDutyFlag("1");// 审核任务
						newReviewLog.setItemId(company.getId());
						newReviewLog.setItemType("1");// 企业信息类型：type=1
						newReviewLog.setStartTime(new Date());
						reviewLogService.saveNewTask(newReviewLog);
					}
					log.append("excel行数").append(i).append("更新成功！<br>");
				} catch (Exception e) {
					e.printStackTrace();
					log.append("excel行数").append(i).append("更新失败：未知的异常！<br>");
					continue;
				}
			}
		}else{
			XSSFSheet sheet = xssfWorkbook.getSheetAt(0);
			XSSFRow row = null;
			int index = 0;
			int updateIndex = 0;
			int fail = 0;
			for (int i = 2; i <= sheet.getLastRowNum(); i++) {
				try {
					row = sheet.getRow(i);
					//企业名称
					companyname = null != row.getCell(0) ? row.getCell(0)
							.toString().trim() : "";
					if(StringUtils.isEmpty(companyname)){
						continue;
					}
					// 组织机构代码
//					zzjgdm = null != row.getCell(1) ? row.getCell(1).toString()
//							.trim() : "";
//					// 统一社会信用代码
//					tyshxydm = null != row.getCell(50) ? row.getCell(50).toString()
//					.trim() : "";
				/*	if ("".equals(zzjgdm) && "".equals(tyshxydm)) {
						log.append("excel行数").append(i)
								.append("更新失败：组织机构代码和统一社会信用代码不能同时为空！<br>");
						continue;
					}*/
					// 处理科学计数法数字
//					if (row.getCell(1).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
//						zzjgdm = dealCell(row.getCell(1));
//						tyshxydm = dealCell(row.getCell(50));
//					}

					paraMap.clear();
					paraMap.put("companyname", companyname);
				/*	if(StringUtils.isNotEmpty(tyshxydm)){
						paraMap.put("tyshxydm", tyshxydm);
					}
					if(StringUtils.isNotEmpty(zzjgdm)){
						paraMap.put("zzjgdm", zzjgdm);
					}*/
					companyList = companyService.findCompList(paraMap);

					if (null != companyList && companyList.size() > 0) {// 更新企业信息
//						log.append("excel行数").append(i)
//						.append("更新失败：该企业名称已经存在！<br>");
//						continue;
						company = companyList.get(0);
						addCompanyFlag = false;
						updateIndex = updateIndex + 1;
					} else {// 新增企业信息
						company = new Company();
						company.setLoginname(companyname);
						company.setLoginword(CodeUtil.encode("99999", "MD5"));
						addCompanyFlag = true;
					}
					company.setDelFlag(0);
					// 处理所在镇
					deptName = null != row.getCell(3) ? row.getCell(3).toString()
							.trim() : "";
					paraMap.clear();
					paraMap.put("deptName", deptName + "%");
					deptList = deptService.findAllDept(paraMap);
					deptCode = "";
					if (deptList.size() > 0) {
						deptCode = deptList.get(0).getDeptCode();
					}

					companytype = "";
					// 处理工贸企业类型
					if (null != row.getCell(16)
							&& !"".equals(row.getCell(16).toString())) {
						gmqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(16).toString().trim(),
								"402881e54a656177014a657c7b95000b").toString();
						if (null != gmqylx && !"".equals(gmqylx)) {
							companytype = companytype + gmqylx + ",";
						}
					}

					// 处理危化品企业类型
					if (null != row.getCell(18)
							&& !"".equals(row.getCell(18).toString())) {
						whpqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(18).toString().trim(),
								"4028e56c40a9a6750140a9c91e2f0007").toString();
						if (null != whpqylx && !"".equals(whpqylx)) {
							companytype = companytype + whpqylx + ",";
						}
					}
					// 处理职业危害企业类型
					if (null != row.getCell(20)
							&& !"".equals(row.getCell(20).toString())) {
						zywhqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(20).toString().trim(),
								"4028e56c3ff0d189013ff1096cbc0037").toString();
						if (null != zywhqylx && !"".equals(zywhqylx)) {
							companytype = companytype + zywhqylx + ",";
						}
					}
					// 处理烟花爆竹企业类型
					if (null != row.getCell(22)
							&& !"".equals(row.getCell(22).toString())) {
						yhbzqylx = companyService.findCompanyTypeNameByValue(
								row.getCell(22).toString().trim(),
								"4028804840b9689c0140c440505a033b").toString();
						if (null != yhbzqylx && !"".equals(yhbzqylx)) {
							companytype = companytype + yhbzqylx + ",";
						}
					}
					company.setZzjgdm(null != row.getCell(1)
							&& !"".equals(row.getCell(1).toString().trim()) ? dealCell(row
							.getCell(1)) : company.getZzjgdm());
					company.setCompanyname(null != companyname
							&& !"".equals(companyname) ? companyname : company
							.getCompanyname());
					company.setFddbr(null != row.getCell(13)
							&& !"".equals(row.getCell(13).toString().trim()) ? row
							.getCell(13).toString().trim() : company.getFddbr());
					company.setFddbrlxhm(null != row.getCell(2)
							&& !"".equals(row.getCell(2).toString().trim()) ? dealCell(row
							.getCell(2)) : company.getFddbrlxhm());
					company.setDwdz(null != row.getCell(3)
							&& !"".equals(row.getCell(3).toString().trim()) ? row
							.getCell(3).toString().trim() : company.getDwdz());
					company.setDwdz1(deptCode);

					company.setSzcname(null != row.getCell(4)
							&& !"".equals(row.getCell(4).toString().trim()) ? row
							.getCell(4).toString().trim() : company.getSzcname());

					paraMap.clear();
					paraMap.put("deptName", company.getSzcname() + "%");
					List<Department> szcDeptList = deptService.findAllDept(paraMap);
					String szcDeptCode = "";
					if (szcDeptList.size() > 0) {
						szcDeptCode = szcDeptList.get(0).getDeptCode();
					}
					company.setSzc(szcDeptCode);

					company.setDwdz2(null != row.getCell(5)
							&& !"".equals(row.getCell(5).toString().trim()) ? row
							.getCell(5).toString().trim() : company.getDwdz2());
					company.setGszch(null != row.getCell(6)
							&& !"".equals(row.getCell(6).toString().trim()) ? dealCell(row
							.getCell(6)) : company.getGszch());
					company.setAqscxkzh(null != row.getCell(7)
							&& !"".equals(row.getCell(7).toString().trim()) ? dealCell(row
							.getCell(7)) : company.getAqscxkzh());
					if (null != row.getCell(8)
							&& !"".equals(row.getCell(8).toString())) {
						company.setQylx(companyService.findCompanyTypeNameByValue(
								row.getCell(8).toString().trim(),
								"4028e56c3ff0d189013ff0e6b99e000c").toString());
					}
					if (null != row.getCell(9)
							&& !"".equals(row.getCell(9).toString())) {
						company.setHyfl(companyService.findCompanyTypeNameByValue(
								row.getCell(9).toString().trim(),
								"402880484076bce30140a04236590a02").toString());
					}
					company.setLxr(null != row.getCell(10)
							&& !"".equals(row.getCell(10).toString().trim()) ? row
							.getCell(10).toString().trim() : company.getLxr());
					company.setMobile(null != row.getCell(11)
							&& !"".equals(row.getCell(11).toString().trim()) ? dealCell(row
							.getCell(11)) : company.getMobile());
					company.setLxfs(null != row.getCell(12)
							&& !"".equals(row.getCell(12).toString().trim()) ? dealCell(row
							.getCell(12)) : company.getLxfs());
					company.setQyyx(null != row.getCell(14)
							&& !"".equals(row.getCell(14).toString().trim()) ? row
							.getCell(14).toString().trim() : company.getQyyx());
					company.setIffmgmqylx((null != row.getCell(15)
							&& !"".equals(row.getCell(15).toString().trim()) && row
							.getCell(15).toString().trim().equals("是")) ? "1" : "0");
					company.setIfwhpqylx((null != row.getCell(17)
							&& !"".equals(row.getCell(17).toString().trim()) && row
							.getCell(17).toString().trim().equals("是")) ? "1" : "0");
					company.setIfzywhqylx((null != row.getCell(19)
							&& !"".equals(row.getCell(19).toString().trim()) && row
							.getCell(19).toString().trim().equals("是")) ? "1" : "0");
					company.setIfyhbzjyqy((null != row.getCell(21)
							&& !"".equals(row.getCell(21).toString().trim()) && row
							.getCell(21).toString().trim().equals("是")) ? "1" : "0");
					if (null != row.getCell(23)
							&& !"".equals(row.getCell(23).toString())) {
						company.setQygm(companyService.findCompanyTypeNameByValue(
								row.getCell(23).toString().trim(),
								"4028e56c3ff0d189013ff0feee650023").toString());
					}
					if (null != row.getCell(24)
							&& !"".equals(row.getCell(24).toString())) {
						String clsj = row.getCell(24).toString();
						Date date  = tranDate(clsj);
						company.setQyclsj(date);
						/*try {
							// 1、判断是否是数值格式
							if (row.getCell(24).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
								short format = row.getCell(24).getCellStyle()
										.getDataFormat();
								SimpleDateFormat simpleDF = null;
								if (format == 14 || format == 31 || format == 57
										|| format == 58) {
									// 日期
									simpleDF = new SimpleDateFormat("yyyy-MM-dd");
								} else if (format == 20 || format == 32) {
									// 时间
									simpleDF = new SimpleDateFormat("HH:mm");
								}
								double value = row.getCell(24)
										.getNumericCellValue();
								Date date = org.apache.poi.ss.usermodel.DateUtil
										.getJavaDate(value);
								company.setQyclsj(date);
							}
						} catch (Exception e) {
							log.append("excel行数").append(i).append("日期格式不正确！<br>");
						}*/
					}
					if (null != row.getCell(25)
							&& !"".equals(row.getCell(25).toString())) {
						company.setQyzclx(companyService
								.findCompanyTypeNameByValue(
										row.getCell(25).toString().trim(),
										"402880484076bce30140a04025e509f7")
								.toString());
					}
					company.setZczj(null != row.getCell(26)
							&& !"".equals(row.getCell(26).toString().trim()) ? dealCell(row
							.getCell(26)) : company.getZczj());
					company.setGddh(null != row.getCell(27)
							&& !"".equals(row.getCell(27).toString().trim()) ? dealCell(row
							.getCell(27)) : company.getGddh());
					company.setCz(null != row.getCell(28)
							&& !"".equals(row.getCell(28).toString().trim()) ? dealCell(row
							.getCell(28)) : company.getCz());
					company.setYzbm(null != row.getCell(29)
							&& !"".equals(row.getCell(29).toString().trim()) ? dealCell(row
							.getCell(29)) : company.getYzbm());
					company.setSnxssr(null != row.getCell(30)
							&& !"".equals(row.getCell(30).toString().trim()) ? dealCell(row
							.getCell(30)) : company.getSnxssr());
					company.setSnsjss(null != row.getCell(31)
							&& !"".equals(row.getCell(31).toString().trim()) ? (Integer
							.parseInt(dealCell(row.getCell(30))) * 0.03) + ""
							: company.getSnsjss());
					company.setSngdzc(null != row.getCell(32)
							&& !"".equals(row.getCell(32).toString().trim()) ? dealCell(row
							.getCell(32)) : company.getSngdzc());
					company.setSnwqtr(null != row.getCell(33)
							&& !"".equals(row.getCell(33).toString().trim()) ? dealCell(row
							.getCell(33)) : company.getSnwqtr());
					company.setSnaqscf(null != row.getCell(34)
							&& !"".equals(row.getCell(34).toString().trim()) ? dealCell(row
							.getCell(34)) : company.getSnaqscf());
					company.setSfaqjg((null != row.getCell(35)
							&& !"".equals(row.getCell(35).toString().trim()) && row
							.getCell(35).toString().trim().equals("是")) ? "1" : "0");
					try {
						if (null != row.getCell(36)
								&& !"".equals(row.getCell(36).toString())) {
							aqglr = Long.parseLong(dealCell(row.getCell(36)));
							company.setAqglr(aqglr);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("安全管理员（人）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}

					company.setSfqzaqgly((null != row.getCell(37) && row
							.getCell(37).toString().trim().equals("是")) ? "1" : "0");

					company.setSfzywsjg((null != row.getCell(38) && row.getCell(38)
							.toString().trim().equals("是")) ? "1" : "0");
					try {
						if (null != row.getCell(39)
								&& !"".equals(row.getCell(39).toString())) {
							zywsglry = Long.parseLong(dealCell(row.getCell(39)));
							company.setZywsglry(zywsglry);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("职业卫生管理人员（人）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					company.setSfqzwsgly((null != row.getCell(40) && row
							.getCell(40).toString().trim().equals("是")) ? "1" : "0");
					try {
						if (null != row.getCell(41)
								&& !"".equals(row.getCell(41).toString())) {
							zdmj = dealCell(row.getCell(41));
							company.setZdmj(zdmj);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("占地面积（m2）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					try {
						if (null != row.getCell(42)
								&& !"".equals(row.getCell(42).toString())) {
							jzmj = dealCell(row.getCell(42));
							company.setJzmj(jzmj);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("建筑面积（m2）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					try {
						if (null != row.getCell(43)
								&& !"".equals(row.getCell(43).toString())) {
							cyry = Long.parseLong(dealCell(row.getCell(43)));
							company.setCyry(cyry);
						}
					} catch (Exception e) {
						log.append("excel行数").append(i)
								.append("从业人员（人）需是数字，请重新导入或登录系统手动修改该项数据。<br>");
					}
					company.setSfyygss((null != row.getCell(44) && row.getCell(44)
							.toString().trim().equals("是")) ? "1" : "0");
					if (null != row.getCell(45)
							&& !"".equals(row.getCell(45).toString())) {
						company.setAqbzdbjb(companyService
								.findCompanyTypeNameByValue(
										row.getCell(45).toString().trim(),
										"402880484028bef601404c8b68a40047")
								.toString());
					}
					String jyfw = null != row.getCell(46)
							&& !"".equals(row.getCell(46).toString().trim()) ? dealCell(row
							.getCell(46)) : company.getJyfw();
//					Clob clob = Hibernate.createClob(jyfw);//2.通过hibernate将string转化为clob
//					company.setJyfw(clob);
					company.setJyfw(jyfw);
					company.setUrl(null != row.getCell(47)
							&& !"".equals(row.getCell(47).toString().trim()) ? dealCell(row
							.getCell(47)) : company.getUrl());
					company.setCompanyType(companytype);
					//add by wuhuaqing begin
					company.setSfcjaqzrx(null != row.getCell(48)
							&& !"".equals(row.getCell(48).toString().trim()) ? dealCell(row
							.getCell(48)) : company.getSfcjaqzrx());
					company.setSfszaqzj(null != row.getCell(49)
							&& !"".equals(row.getCell(49).toString().trim()) ? dealCell(row
							.getCell(49)) : company.getSfszaqzj());
					company.setTyshxydm(null != row.getCell(50)
							&& !"".equals(row.getCell(50).toString().trim()) ? dealCell(row
							.getCell(50)) : company.getTyshxydm());
					//导入企业直接审核通过
					company.setBasePass(1);
					company.setDutyFlag("1");
					//add by wuhuaqing end
					
					companyService.update(company);

					if (addCompanyFlag) {
						//将新增企业作为部门 hanxc 2014-11-04
						Department companydept = new Department();
						companydept.setCreateTime(new Date());
						companydept.setCreateUserID(company.getLoginname().trim());
						companydept.setDelFlag(0);
						companydept.setDeptCode(deptService.createDeptCode("033"));
						companydept.setDeptName(company.getCompanyname());
						companydept.setDeptTypeCode("04");
						companydept.setHasChild(0);
						companydept.setNeedSubFlow("1");
						companydept.setSortSQ(1);
						companydept.setParentDept(deptService.findDeptByDeptCode("033"));
						deptService.save(companydept);
						
						User user = new User();
						user.setDelFlag(0);
						user.setDeptCode(companydept.getDeptCode());
						user.setCssId("default");
						user.setDisplayName(company.getCompanyname().trim());
						user.setLoginId(company.getLoginname().trim());
						user.setPassword(CodeUtil.encode("99999", "MD5"));
						user.setSortSq(1);
						user.setDeptRole("21");// 企业部门角色
						user.setDept(companydept);
						userService.saveUser(user);

						UserRight userRight = new UserRight();
						userRight.setUser(user);
						userRight.setRole(userRoleService.findRoleByCode(SysPropertiesUtil.getProperty("tempRoleCode")));// 临时企业人员
						userService.saveRight(userRight);
						// hanxc 20141211 生成待审核任务 start
						ReviewLog newReviewLog = new ReviewLog();
						newReviewLog.setDutyFlag("1");// 市级部门审核任务
						newReviewLog.setItemId(company.getId());
						newReviewLog.setItemType("1");// 企业信息类型：type=1
						newReviewLog.setStartTime(new Date());
						reviewLogService.saveNewTask(newReviewLog);
						
//						User user = new User();
//						user.setDelFlag(0);
//						user.setDeptCode(SysPropertiesUtil
//								.getProperty("qiyeDeptCode"));
//						user.setCssId("default");
//						user.setDisplayName(company.getCompanyname().trim());
//						//modify by wuhuaqing begin
//						//将组织机构代码或社会信用代码设置为登录用户名
//						/*String loginId = "";
//						if(StringUtil.isNotEmpty(company.getZzjgdm()) && !"0".equals(company.getZzjgdm())){
//							loginId = company.getZzjgdm().trim();
//						}else if(StringUtil.isNotEmpty(company.getTyshxydm()) && !"0".equals(company.getTyshxydm())){
//							loginId = company.getTyshxydm().trim();
//						}*/
//						//modify by wuhuaqing end
//						user.setLoginId(company.getCompanyname().trim());
//						user.setPassword(CodeUtil.encode("99999", "MD5"));
//						user.setMobile(company.getMobile());
//						user.setSortSq(1);
//						user.setDeptRole("21");// 企业部门角色
//						user.setDept(deptService
//								.findDeptByDeptCode(SysPropertiesUtil
//										.getProperty("qiyeDeptCode")));
//						userService.saveUser(user);
//
//						UserRight userRight = new UserRight();
//						userRight.setUser(user);
//						userRight.setRole(userRoleService
//								.findRoleByCode(SysPropertiesUtil
//										.getProperty("tempRoleCode")));// 临时企业人员
//						userService.saveRight(userRight);
//						// hanxc 20141211 生成待审核任务 start
//						ReviewLog newReviewLog = new ReviewLog();
//						/*
//						 * if (company.getIfzsqy().equals("1")) {
//						 * newReviewLog.setDutyFlag("1");// 市级部门审核任务 } else {
//						 * newReviewLog.setDutyFlag("2");// 县级部门审核任务 }
//						 */
//						newReviewLog.setDutyFlag("1");// 审核任务
//						newReviewLog.setItemId(company.getId());
//						newReviewLog.setItemType("1");// 企业信息类型：type=1
//						newReviewLog.setStartTime(new Date());
//						reviewLogService.saveNewTask(newReviewLog);
					}
					log.append("excel行数").append(i).append("更新成功！<br>");
					index +=1;
				} catch (Exception e) {
					e.printStackTrace();
					log.append("excel行数").append(i).append(companyname+">>>更新失败：未知的异常！<br>");
					fail ++;
					continue;
				}
			}
			log.append("共导入成功："+index+"条！<br>");
			log.append("新增成功："+(index-updateIndex)+"条！<br>");
			log.append("更新成功："+updateIndex+"条！<br>");
			log.append("导入失败："+fail+"条！<br>");
		}
		
		this.logInfo = log.toString();
		return "success";
	}
	
	/**
	 * 将yyyy.mm.dd转换成yyyy-mm-dd格式
	 * @param date
	 * @return
	 */
	
	@SuppressWarnings("finally")
	private  Date tranDate(String date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd");//设置日期格式
		Date result = new Date();
		if(StringUtils.isEmpty(date)){
			
		}else{
			try {
				//字符串转换成日期
				ParsePosition pos = new ParsePosition(0);   
				java.util.Date datetime = df.parse(date, pos); 
				//再转换成MM-dd-yyyy的字符串
				SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				String date2 = df2.format(datetime);
				result = df2.parse(date2);
			} catch (ParseException e) {
				e.printStackTrace();
			}finally{
				return result;
			}
		}
		return result;
	}
	
	public String companyUser() throws IOException {
		try {
			if ((null != company) && (null != company.getId())) {
				company = companyService.getById(company.getId());
				if (company.getBasePass() == 1) {
					Map map = new HashMap();
					map.put("companyId", company.getId());
					CompanyBackUp comBc = companyService
							.getCompanyBackupById(map);
					comBc.setCompanyname(company.getCompanyname());
					comBc.setLoginname(company.getLoginname());
					comBc.setFddbr(company.getFddbr());
					comBc.setFddbrlxhm(company.getFddbrlxhm());
					comBc.setDwdz(company.getDwdz());
					comBc.setDwdz1(company.getDwdz1());
					comBc.setDwdz2(company.getDwdz2());
					comBc.setDelFlag(0);
					comBc.setZzjgdm(company.getZzjgdm());

					comBc.setQylx(company.getQylx());
					comBc.setHyfl(company.getHyfl());
					comBc.setLxr(company.getLxr());
					comBc.setMobile(company.getMobile());
					comBc.setLxfs(company.getLxfs());
					comBc.setQyyx(company.getQyyx());
					comBc.setGszch(company.getGszch());
					comBc.setQygm(company.getQygm());
					comBc.setQyzclx(company.getQyzclx());
					comBc.setZczj(company.getZczj());
					comBc.setWhpqylx(company.getWhpqylx());
					comBc.setIfwhpqylx(company.getIfwhpqylx());
					comBc.setZywhqylx(company.getZywhqylx());
					comBc.setIfzywhqylx(company.getIfzywhqylx());
					comBc.setFrdm(company.getFrdm());
					comBc.setQyclsj(company.getQyclsj());
					comBc.setGddh(company.getGddh());
					comBc.setCz(company.getCz());
					comBc.setYzbm(company.getYzbm());
					comBc.setSnxssr(company.getSnxssr());
					comBc.setSnsjss(company.getSnsjss());
					comBc.setSngdzc(company.getSngdzc());
					comBc.setSnwqtr(company.getSnwqtr());
					comBc.setSnaqscf(company.getSnaqscf());
					comBc.setSfaqjg(company.getSfaqjg());
					comBc.setAqglr(company.getAqglr());
					comBc.setSfzywsjg(company.getSfzywsjg());
					comBc.setZywsglry(company.getZywsglry());
					comBc.setSfqzwsgly(company.getSfqzwsgly());
					comBc.setZdmj(company.getZdmj());
					comBc.setJzmj(company.getJzmj());
					comBc.setCyry(company.getCyry());
					comBc.setSfyygss(company.getSfyygss());
					comBc.setAqbzdbjb(company.getAqbzdbjb());
					comBc.setJyfw(company.getJyfw());
					comBc.setUrl(company.getUrl());
					comBc.setIfurl(company.getIfurl());
					comBc.setBasePass(1);
					comBc.setIfyhbzjyqy(company.getIfyhbzjyqy());
					comBc.setYhbzjyqy(company.getYhbzjyqy());
					comBc.setSzc(company.getSzc());
					comBc.setSzcname(company.getSzcname());
					companyService.updateCompanyBackUp(comBc);

					User user = userService.findUserByLoginId(company
							.getLoginname());
					if (user != null) {
						try {
							userService.delByUser(user.getId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						try {
							user = new User();
							user.setDelFlag(0);
							user.setDeptCode(SysPropertiesUtil
									.getProperty("qiyeDeptCode"));
							user.setCssId("default");
							user.setDisplayName(company.getCompanyname().trim());
							user.setLoginId(company.getLoginname().trim());
							user.setPassword(CodeUtil
									.encode("123456789", "MD5"));
							user.setMobile(company.getMobile());
							user.setSortSq(1);
							user.setDept(deptService
									.findDeptByDeptCode(SysPropertiesUtil
											.getProperty("qiyeDeptCode")));
							userService.saveUser(user);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					try {
						UserRight userRight = new UserRight();
						userRight.setUser(user);
						userRight
								.setRole(userRoleService.findRoleByCode("A02"));
						userService.saveRight(userRight);

						if (company.getIfwhpqylx() != null
								&& company.getIfwhpqylx().equals("1")) {
							UserRight userRight1 = new UserRight();
							userRight1.setUser(user);
							userRight1.setRole(userRoleService
									.findRoleByCode("A24"));
							userService.saveRight(userRight1);
						}

						if (company.getIfzywhqylx() != null
								&& company.getIfzywhqylx().equals("1")) {
							UserRight userRight2 = new UserRight();
							userRight2.setUser(user);
							userRight2.setRole(userRoleService
									.findRoleByCode("A25"));
							userService.saveRight(userRight2);
						}
						if (company.getIfyhbzjyqy() != null
								&& company.getIfyhbzjyqy().equals("1")) {
							UserRight userRight3 = new UserRight();
							userRight3.setUser(user);
							userRight3.setRole(userRoleService
									.findRoleByCode("A27"));
							userService.saveRight(userRight3);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				this.getResponse().getWriter().println("{\"result\":\"true\"}");
			}
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	
	
	/**
	 * 编码数字加1
	 * 
	 * @param num
	 * @return
	 */
	public String maxShzi(String num) {
		String conet = "";
		int lshi = 0000000001;
		if (null != num && num.length() > 0) {
			lshi = Integer.valueOf(num);
			lshi = lshi + 1;
		}
		conet = String.valueOf(lshi);
		int len = conet.length();
		if (len < 6) {
			for (int i = 0; i < 10 - len; i++) {
				conet = "0" + conet;
			}
		}
		return conet;
	}

	/**
	 * 汉字转拼音
	 * 
	 * @param str
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public String pingYin(String str)
			throws BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		String convert = "";
		StringBuffer sb = new StringBuffer();
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// 提取汉字的首字母
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word,
					format);
			if (j == 0) {
				for (int i = 0; i < pinyinArray.length; i++) {
					sb.append(pinyinArray[i]);
				}
				convert += sb.toString();
			} else {
				if (pinyinArray != null) {
					convert += pinyinArray[0].charAt(0);
				} else {
					convert += word;
				}
			}
		}
		return convert;
	}
	
	/**
	 * 查询行业分类二级分类
	 * 
	 * @param num
	 * @return
	 */
	public void hyflTwoLevel() {
		try {
			if (pagination == null)
				pagination = new Pagination(this.getRequest());
			JSONArray json = new JSONArray();
			if (code != null && !"".equals(code)) {
				if (code.length() == 2) {
					Map<String, Object> paraMap = new HashMap<String, Object>();

					paraMap.put("code", code + "%");
					paraMap.put("codeLength", (Integer)2);
					pagination = hyflService.findByPage(pagination, paraMap);
					List<Hyfl> list = pagination.getListOfObject();
					for (Hyfl tempHyfl : list) {
						JSONObject jj = new JSONObject();
						jj.put("code", tempHyfl.getCode());
						jj.put("name", tempHyfl.getHyname());
						json.add(jj);
					}
				} else {
					int startNum = Integer.parseInt(code.substring(0, 2));
					int endNum = Integer.parseInt(code.substring(
							code.length() - 2, code.length()));
					for (int i = startNum; i <= endNum; i++) {
						Map<String, Object> paraMap = new HashMap<String, Object>();
						paraMap.put("code", (i < 10 ? "0" + i : i) + "%");
						paraMap.put("codeLength", (Integer)2);
						pagination = hyflService.findByPage(pagination, paraMap);
						List<Hyfl> list = pagination.getListOfObject();
						for (Hyfl tempHyfl : list) {
							JSONObject jj = new JSONObject();
							jj.put("code", tempHyfl.getCode());
							jj.put("name", tempHyfl.getHyname());
							json.add(jj);
						}
					}
				}
			}
			PrintWriter out = getResponse().getWriter();
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询该部门下用户异常");
		}

	}

	/**
	 * 查询行业分类三级分类
	 * 
	 * @param num
	 * @return
	 */
	public void hyflThreeLevel() {
		try {
			if (pagination == null)
				pagination = new Pagination(this.getRequest());

			JSONArray json = new JSONArray();
			if (code != null && !"".equals(code)) {
				Map<String, Object> paraMap = new HashMap<String, Object>();

				paraMap.put("code", code + "%");
				paraMap.put("codeLength", Integer.valueOf("3"));
				pagination = hyflService.findByPage(pagination, paraMap);
				List<Hyfl> list = pagination.getListOfObject();
				for (Hyfl tempHyfl : list) {
					JSONObject jj = new JSONObject();
					jj.put("code", tempHyfl.getCode());
					jj.put("name", tempHyfl.getHyname());
					json.add(jj);
				}

			}
			PrintWriter out = getResponse().getWriter();
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询该部门下用户异常");
		}

	}

	/**
	 * 查询行业分类四级分类
	 * 
	 * @param num
	 * @return
	 */
	public void hyflFourLevel() {
		try {
			if (pagination == null)
				pagination = new Pagination(this.getRequest());

			JSONArray json = new JSONArray();
			if (code != null && !"".equals(code)) {
				Map<String, Object> paraMap = new HashMap<String, Object>();
				paraMap.put("code", code + "%");
				paraMap.put("codeLength", Integer.valueOf("4"));
				pagination = hyflService.findByPage(pagination, paraMap);
				List<Hyfl> list = pagination.getListOfObject();
				for (Hyfl tempHyfl : list) {
					JSONObject jj = new JSONObject();
					jj.put("code", tempHyfl.getCode());
					jj.put("name", tempHyfl.getHyname());
					json.add(jj);
				}
			}
			PrintWriter out = getResponse().getWriter();
			out.print(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("查询该部门下用户异常");
		}

	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getQueryQyclsjStart() {
		return this.queryQyclsjStart;
	}

	public void setQueryQyclsjStart(Date queryQyclsjStart) {
		this.queryQyclsjStart = queryQyclsjStart;
	}

	public Date getQueryQyclsjEnd() {
		return this.queryQyclsjEnd;
	}

	public void setQueryQyclsjEnd(Date queryQyclsjEnd) {
		this.queryQyclsjEnd = queryQyclsjEnd;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsShenhe() {
		return isShenhe;
	}

	public void setIsShenhe(String isShenhe) {
		this.isShenhe = isShenhe;
	}

	// public List<JshxZcyhsb> getJshxZcyhsbList() {
	// return jshxZcyhsbList;
	// }
	// public void setJshxZcyhsbList(List<JshxZcyhsb> jshxZcyhsbList) {
	// this.jshxZcyhsbList = jshxZcyhsbList;
	// }
	// public List<JshxZazPxb> getJshxZazPxbList() {
	// return jshxZazPxbList;
	// }
	// public void setJshxZazPxbList(List<JshxZazPxb> jshxZazPxbList) {
	// this.jshxZazPxbList = jshxZazPxbList;
	// }
	// public List<JshxTzzyPxb> getJshxTzzyPxbList() {
	// return jshxTzzyPxbList;
	// }
	// public void setJshxTzzyPxbList(List<JshxTzzyPxb> jshxTzzyPxbList) {
	// this.jshxTzzyPxbList = jshxTzzyPxbList;
	// }
	// public List<JshxXrcPxb> getJshxXrcPxbList() {
	// return jshxXrcPxbList;
	// }
	// public void setJshxXrcPxbList(List<JshxXrcPxb> jshxXrcPxbList) {
	// this.jshxXrcPxbList = jshxXrcPxbList;
	// }
	// public List<JshxZzxtPxb> getJshxZzxtPxbList() {
	// return jshxZzxtPxbList;
	// }
	// public void setJshxZzxtPxbList(List<JshxZzxtPxb> jshxZzxtPxbList) {
	// this.jshxZzxtPxbList = jshxZzxtPxbList;
	// }
	// public List<JshxXkzsb> getJshxXkzsbList() {
	// return jshxXkzsbList;
	// }
	// public void setJshxXkzsbList(List<JshxXkzsb> jshxXkzsbList) {
	// this.jshxXkzsbList = jshxXkzsbList;
	// }
	// public List<Factorypicture> getFactorypictureList() {
	// return factorypictureList;
	// }
	// public void setFactorypictureList(List<Factorypicture>
	// factorypictureList) {
	// this.factorypictureList = factorypictureList;
	// }
	// public List<JshxZzbw> getJshxZzbwList() {
	// return jshxZzbwList;
	// }
	// public void setJshxZzbwList(List<JshxZzbw> jshxZzbwList) {
	// this.jshxZzbwList = jshxZzbwList;
	// }
	public CompanyBackUp getCompanyBackUp() {
		return companyBackUp;
	}

	public void setCompanyBackUp(CompanyBackUp companyBackUp) {
		this.companyBackUp = companyBackUp;
	}

	public List<Department> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}

	public void setDeptCodeLenth(String deptCodeLenth) {
		this.deptCodeLenth = deptCodeLenth;
	}

	public String getDeptCodeLenth() {
		return deptCodeLenth;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public String getDeptrole() {
		return deptrole;
	}

	public void setDeptrole(String deptrole) {
		this.deptrole = deptrole;
	}

	public void setReviewLog(ReviewLog reviewLog) {
		this.reviewLog = reviewLog;
	}

	public ReviewLog getReviewLog() {
		return reviewLog;
	}

	public void setZjshState(String zjshState) {
		this.zjshState = zjshState;
	}

	public String getZjshState() {
		return zjshState;
	}

	public void setZjBack(String zjBack) {
		this.zjBack = zjBack;
	}

	public String getZjBack() {
		return zjBack;
	}

	public void setXjshState(String xjshState) {
		this.xjshState = xjshState;
	}

	public String getXjshState() {
		return xjshState;
	}

	public void setXjBack(String xjBack) {
		this.xjBack = xjBack;
	}

	public String getXjBack() {
		return xjBack;
	}

	public void setSjshState(String sjshState) {
		this.sjshState = sjshState;
	}

	public String getSjshState() {
		return sjshState;
	}

	public void setSjBack(String sjBack) {
		this.sjBack = sjBack;
	}

	public String getSjBack() {
		return sjBack;
	}

	public void setDeptflag(String deptflag) {
		this.deptflag = deptflag;
	}

	public String getDeptflag() {
		return deptflag;
	}

	public String getPassFlag() {
		return passFlag;
	}

	public void setPassFlag(String passFlag) {
		this.passFlag = passFlag;
	}

	public void setIfzsqy(String ifzsqy) {
		this.ifzsqy = ifzsqy;
	}

	public String getIfzsqy() {
		return ifzsqy;
	}

	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public File getCompanyFile() {
		return companyFile;
	}

	public void setCompanyFile(File companyFile) {
		this.companyFile = companyFile;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public List<ReviewLog> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<ReviewLog> reviewList) {
		this.reviewList = reviewList;
	}

}
