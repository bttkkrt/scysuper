/**
 * Class Name: WhpClscAction
 * Class Description：危险化学品生产企业安全生产许可证审批
 */
package com.jshx.whpclsc.web;

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

import com.jshx.company.entity.Company;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.DeptService;
import com.jshx.module.admin.service.UserService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.reviewLog.entity.ReviewLog;
import com.jshx.reviewLog.service.ReviewLogService;
import com.jshx.whpclsc.entity.ImageListBean;
import com.jshx.whpclsc.entity.WhpClsc;
import com.jshx.whpclsc.service.WhpClscService;

public class WhpClscAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;
	private String[] titles = new String[]{"危险化学品生产或使用企业安全生产许可证审查","危险化学品经营企业的行政许可",
			"危险化学品建设项目安全条件审查","危险化学品建设项目安全设施设计审查","　　　危险化学品建设项目安全设施试生产（使用）备案",
			"危险化学品建设项目安全设施竣工验收","非药品类易制毒化学品生产许可证审核","非药品类易制毒化学品经营许可证审核",
			"第二、第三类非药品类易制毒化学品生产备案","第二、三类非药品类易制毒化学品经营备案"};
	
	private String[] data01 = 
		new String[]{"危险化学品生产许可证申请书","危险化学品生产许可证申请材料","安全评价机构出具的安全评价报告","危化品生产企业安全生产许可证现场核查表",
			"危化品生产企业安全生产许可证现场审查表","申请材料登记表","集体会审记录","危险化学品生产许可证副本"};
	private String[] data02 = new String[]{"危险化学品经营许可证申请书","危险化学品经营许可证申请材料","带有存储设施经营危险化学品企业（包括加油站），应提供具有法定资质的安全评价机构出具的安全评价报告及安全评价报告备案证明",
			"行政审批文件","危险化学品经营许可证副本"};
	private String[] data03 = new String[]{"危险化学品生产、储存企业设立和新建改建扩建项目安全审查批准书申请书","项目批准、核准或备案证明",
			"具有法定资质的工程咨询机构出具的项目可行性研究报告（含项目安全条件论证专篇）或具有法定资质的中介机构出具的项目安全条件论证报告",
			"具有法定资质的安全评价机构出具的项目安全预评价报告","项目最终产品、中间产品或储存的危险化学品的产品安全技术说明书或具有法定资质单位出具的产品理化性能指标资料",
			"采用的生产工艺、设备或储存方式、设施的情况说明","拟建工厂、仓库与周边建筑物、设施的安全防护距离的情况说明","事故应急救援措施",
			"法律法规规定的其它材料","区安监局行政许可审查表"};
	private String[] data04 = new String[]{"建设项目安全设施设计的审查申请表","设计单位的设计资质证明文件","经过专家审查并修改确认后的建设项目安全设施设计专篇",
			"危险化学品建设项目安全条件审查意见书（扫描件）","投资管理部门出具的建设项目审批（核准、备案）文件（扫描件）","规划管理部门出具的规划许可文件（扫描件）",
			"危险化学品建设项目安全审查专家审查意见（扫描件）","危险化学品建设项目安全条件审查意见书（扫描件）","危险化学品建设项目安全审查审查表",
			"区安监局行政许可审查表"};
	private String[] data05 = new String[]{"生产（使用）备案表","试生产（使用）方案","安全设施设计落实情况及安全设施设计重大变更情况的报告",
			"组织设计漏项、工程质量、工程隐患的检查情况，以及整改措施的落实情况报告","建设项目施工、监理单位资质证书和质量监督手续","建设项目安全设施的检验检测情况",
			"安全生产责任制文件和安全生产规章制度、岗位操作安全规程清单","设置安全生产管理机构和配备专职安全生产管理人员的文件复制件",
			"主要负责人、分管安全负责人、安全生产管理人员的安全资格证复制件和特种作业人员名单","相关从业人员安全教育、培训合格的证明材料",
			"劳动防护用品配备情况说明","设计审查意见书","其他应当提供的文件、资料","区安监局行政许可审查表"};
	private String[] data06 = new String[]{"危险化学品建设项目安全设施竣工验收申请表","建设项目安全设施施工、监理情况报告","建设项目安全设施竣工验收评价报告",
			"为从业人员缴纳工伤保险费的证明材料复制件","危险化学品事故应急预案备案登记表复制件","试生产情况报告",
			"建设项目安全条件审查意见书、安全设施设计审查意见书和试生产（使用）方案备案文件复制件",
			"构成危险化学品重大危险源的，还应当提交危险化学品重大危险源备案证明文件复制件","法律法规规定的其它材料","区安监局行政许可审查表"};
	private String[] data07 = new String[]{"非药品类易制毒化学品生产许可证申请书","工商营业执照副本","易制毒化学品管理制度、安全生产管理制度",
			"单位法定代表人或主要负责人和技术、管理人员具备易制毒化学品有关知识的证明材料以及无毒品犯罪记录的证明材料",
			"单位法定代表人或主要负责人和安全生产管理人员经安全生产监督管理部门培训合格后颁发的安全资格证书和执业资格证书",
			"环境突发事件应急预案","标明产品的名称（含学名和通用名）、化学分子式和成分的产品包装说明和使用说明书",
			"属于危险化学品生产企业的，还需提交危险化学品生产企业安全生产许可证和危险化学品登记证","生产设备、仓储设施和污染物处理设施情况说明材料",
			"法律、法规和规章规定的其他条件的证明文件、资料","区安监局行政许可审查表"};
	private String[] data08 = new String[]{"非药品类易制毒化学品经营许可证申请书","工商营业执照副本（复印件）",
			"易制毒化学品经营管理制度和包括销售机构、代理商、最终用户、销售地点、电话、地址等内容的销售网络文件",
			"单位法定代表人或主要负责人和销售、管理人员具有易制毒化学品知识的证明材料以及无毒品犯罪记录的证明材料",
			"单位法定代表人或主要负责人和安全生产管理人员经安全生产监督管理部门培训合格后颁发的安全资格证书和执业资格证书（复印件）",
			"经营场所、仓储设施情况说明材料","产品包装说明和使用说明书","属于危险化学品经营单位的，还需提交危险化学品经营许可证（复印件）",
			"法律、法规和规章规定的其他条件的证明文件、资料","区安监局行政许可审查表"};
	private String[] data09 = new String[]{"非药品类易制毒化学品备案申请书","非药品类易制毒化学品备案申请材料","集体会审记录","非药品类易制毒化学品备案证明副本"};
	private String[] data10 = new String[]{"非药品类易制毒化学品备案申请书","易制毒化学品管理制度","产品包装说明和使用说明书",
			"工商营业执照副本","属于危险化学品经营单位的，还需提交危险化学品经营许可证（扫描件）","区安监局行政许可审查表"};

	/**
	 * 实体类
	 */
	private WhpClsc whpClsc = new WhpClsc();
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private String[] shenheList ;
	  /**
     * 安全生产机构管理表
     */
	private List<PhotoPic> picList01 = new ArrayList<PhotoPic>();
	
	private List<ImageListBean> images = new ArrayList<ImageListBean>();

	/**
	 * 业务类
	 */
	@Autowired
	private WhpClscService whpClscService;
	 @Autowired
    private SzwxPhotoService szwxPhotoService;
	 @Autowired
	private CompanyService companyService;
	 @Autowired
	private DeptService deptService;
	 @Autowired
		private UserService userService;
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;
    /**
     * 类型
     */
    private String type;
    /**
     * 不同模块标示符
     */
    private String code;
    /**
     * 标题
     */
    private String title;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	

	/**
	 * 当前登录人id
	 * @return
	 */
	private String createUserID;
	
	private String createTimeStart;
	private String createTimeEnd;
	
	private String createTimeStartJs;
	private String createTimeEndJs;
	private String createTimeStartSb;
	private String createTimeEndSb;
	
	private String createTimeStartSl;
	private String createTimeEndSl;
	private String createTimeStartXk;
	private String createTimeEndXk;
	
	
	private String createTimeStartSc;
	private String createTimeEndSc;
	private String createTimeStartSp;
	private String createTimeEndSp;
	
	
	private String createTimeStartBa;
	private String createTimeEndBa;
	
	private String createTimeStartYs;
	private String createTimeEndYs;
	
	private String createTimeStartYxq;
	private String createTimeEndYxq;
	
	
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
	
	
	
	private List<User> userList = new ArrayList<User>();
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	/**
	 * 初始化危化科材料审查列表
	 * 陆婷
	 * 2013-12-11
	 */
	public String init(){
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		String deptRole = this.getLoginUser().getDeptRole();
		if(type!=null&&!"".equals(type)){
			int index= Integer.parseInt(type)-1;
			title = titles[index];
		}
		createUserID = this.getLoginUserId();
		flag = "0";
		
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		//根据部门长度进行数据量权限划分
		if(deptCode.length() == countyDeptCodeLength && null != deptRole && (deptRole.contains("a") || deptRole.contains("b"))){//县级部门
			//String tempDeptCode = this.getLoginUserDepartment().getDeptCode();
			//tempDeptCode = tempDeptCode.substring(0, countyDeptCodeLength-3);
			//paraMap.put("deptCode",tempDeptCode+ "%");
			flag = "1";
		}
		if(deptCode.length() == cityDeptCodeLength && null != deptRole && (deptRole.contains("a") || deptRole.contains("b"))){//市级部门
			//String tempDeptCode = this.getLoginUserDepartment().getDeptCode();
			//tempDeptCode = tempDeptCode.substring(0, countyDeptCodeLength-3);
			//paraMap.put("deptCode",tempDeptCode+ "%");
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
//		if(SysPropertiesUtil.getProperty("whpCode").equals(deptRole)){//部门如果是监管二处（危化）
//			
//		}
//		if(deptCode.startsWith("002001"))
//		{
//			List<UserRight> list = this.getLoginUser().getUserRoles();
//			for(UserRight u:list)
//			{
//				String rolecode = u.getRole().getRoleCode();
//				if(rolecode.equals("A04") || rolecode.equals("A23"))
//				{
//					flag = "1";
//					break;
//				}
//			}
//		}
		
		//////////////
		deptflag="99";
		if(null != deptRole && deptRole.equals("21")){//企业人员
			deptflag = "2";
		}else{
			//根据部门角色进行查询条件过滤
			if(null != deptRole && deptRole.contains(SysPropertiesUtil.getProperty("whpCode"))){//部门如果是危化品
				deptflag = "1";
			} else {
				deptflag = "0";
			}
		}
//		
//		deptcode = deptCode.length()+"";
//		if(!"1".equals(type) || (this.getLoginUserDepartment().getDeptName().contains("安监中队") && deptCode.length() != 9))
//		{
//			whpClsc.setState("3");
//		}
//		if(this.getLoginUser().getLoginId().equals("admin"))
//		{
//			flag = "1";
//		}
		
		return SUCCESS+type;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != whpClsc){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != whpClsc.getState()) && (0 < whpClsc.getState().trim().length())){
				paraMap.put("state", whpClsc.getState().trim());
			}
			if ((null != whpClsc.getSzzid()) && (0 < whpClsc.getSzzid().trim().length())){
				paraMap.put("szzid", whpClsc.getSzzid().trim());
			}
			if ((null != whpClsc.getQymc()) && (0 < whpClsc.getQymc().trim().length())){
				paraMap.put("qymc", "%" + whpClsc.getQymc().trim() + "%");
			}
			paraMap.put("sclx",whpClsc.getSclx().trim());
				
			if ((null != whpClsc.getPjjg()) && (0 < whpClsc.getPjjg().trim().length())){
				paraMap.put("pjjg", whpClsc.getPjjg().trim());
			}	
			if ((null != whpClsc.getFzdw()) && (0 < whpClsc.getFzdw().trim().length())){
				paraMap.put("fzdw", whpClsc.getFzdw().trim());
			}	
			
			
			if ((null != whpClsc.getXmnr()) && (0 < whpClsc.getXmnr().trim().length())){
				paraMap.put("xmnr", "%"+whpClsc.getXmnr().trim() + "%");
			}	
			if ((null != whpClsc.getSczj()) && (0 < whpClsc.getSczj().trim().length())){
				paraMap.put("sczj", whpClsc.getSczj().trim() + "%");
			}	
			if ((null != whpClsc.getScjg()) && (0 < whpClsc.getScjg().trim().length())){
				paraMap.put("scjg", "%"+whpClsc.getScjg().trim() + "%");
			}	
			if ((null != whpClsc.getSpbh()) && (0 < whpClsc.getSpbh().trim().length())){
				paraMap.put("spbh","%"+ whpClsc.getSpbh().trim() + "%");
			}	
			if ((null != whpClsc.getXmxz()) && (0 < whpClsc.getXmxz().trim().length())){
				paraMap.put("xmxz", whpClsc.getXmxz().trim());
			}	
			if ((null != whpClsc.getXmlx()) && (0 < whpClsc.getXmlx().trim().length())){
				paraMap.put("xmlx", "%"+whpClsc.getXmlx().trim() + "%");
			}	
			
			if ((null != whpClsc.getSjdw()) && (0 < whpClsc.getSjdw().trim().length())){
				paraMap.put("sjdw", "%"+whpClsc.getSjdw().trim()+ "%");
			}
			
			if ((null != whpClsc.getBabh()) && (0 < whpClsc.getBabh().trim().length())){
				paraMap.put("babh", "%"+whpClsc.getBabh().trim()+ "%");
			}
			
			if ((null != whpClsc.getPjdw()) && (0 < whpClsc.getPjdw().trim().length())){
				paraMap.put("pjdw", "%"+whpClsc.getPjdw().trim()+ "%");
			}
			if ((null != whpClsc.getYszj()) && (0 < whpClsc.getYszj().trim().length())){
				paraMap.put("yszj", "%"+whpClsc.getYszj().trim()+ "%");
			}
			
			if ((null != whpClsc.getFzjg()) && (0 < whpClsc.getFzjg().trim().length())){
				paraMap.put("fzjg", "%"+whpClsc.getFzjg().trim()+ "%");
			}
			if ((null != whpClsc.getYzdhxp()) && (0 < whpClsc.getYzdhxp().trim().length())){
				paraMap.put("yzdhxp", "%"+whpClsc.getYzdhxp().trim()+ "%");
			}
			
			
		}
		
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}
		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		
		
		if (null != createTimeStartSl){
			paraMap.put("startCreateTimeSl", createTimeStartSl);
		}

		if (null != createTimeEndSl){
			paraMap.put("endCreateTimeSl", createTimeEndSl);
		}
		
		if (null != createTimeStartXk){
			paraMap.put("startCreateTimeXk", createTimeStartXk);
		}

		if (null != createTimeEndXk){
			paraMap.put("endCreateTimeXk", createTimeEndXk);
		}
		

		
		if (null != createTimeStartJs){
			paraMap.put("startCreateTimeJs", createTimeStartJs);
		}

		if (null != createTimeEndJs){
			paraMap.put("endCreateTimeJs", createTimeEndJs);
		}
		
		if (null != createTimeStartSb){
			paraMap.put("startCreateTimeSb", createTimeStartSb);
		}

		if (null != createTimeEndSb){
			paraMap.put("endCreateTimeSb", createTimeEndSb);
		}
		
		
		if (null != createTimeStartSc){
			paraMap.put("startCreateTimeSc", createTimeStartSc);
		}

		if (null != createTimeEndSc){
			paraMap.put("endCreateTimeSc", createTimeEndSc);
		}
		
		if (null != createTimeStartSp){
			paraMap.put("startCreateTimeSp", createTimeStartSp);
		}

		if (null != createTimeEndSp){
			paraMap.put("endCreateTimeSp", createTimeEndSp);
		}
		
		
		if (null != createTimeStartBa){
			paraMap.put("startCreateTimeBa", createTimeStartBa);
		}
		if (null != createTimeEndBa){
			paraMap.put("endCreateTimeBa", createTimeEndBa);
		}
		
		if (null != createTimeStartYs){
			paraMap.put("startCreateTimeYs", createTimeStartYs);
		}
		if (null != createTimeEndYs){
			paraMap.put("endCreateTimeYs", createTimeEndYs);
		}
		if (null != createTimeStartYxq){
			paraMap.put("startCreateTimeYxq", createTimeStartYxq);
		}
		if (null != createTimeEndYxq){
			paraMap.put("endCreateTimeYxq", createTimeEndYxq);
		}
		
		//判断登录人是否为企业人员，要是，则查询自己登记
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))//企业
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇安监中队
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("szzid", deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		
		pagination = whpClscService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 * 修改： 增加审核人员列表查询 by 陆婷 2013-12-11
	 */
	public String view() throws Exception{
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		if((null != whpClsc)&&(null != whpClsc.getId())){
			whpClsc = whpClscService.getById(whpClsc.getId());
			if(whpClsc.getShenhe() != null && !"".equals(whpClsc.getShenhe()))
			{
				shenheList = whpClsc.getShenhe().split("#");
			}
			if(whpClsc.getLinkId() == null || "".equals(whpClsc.getLinkId()))
			{
				String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
				whpClsc.setLinkId(linkId);
			}
		}else{
			String linkId = java.util.UUID.randomUUID().toString().replace("-", "");
			whpClsc.setLinkId(linkId);
			whpClsc.setSclx(type);
		}
		Map map = new HashMap();
		map.put("taskProId",whpClsc.getLinkId());
	    if("1".equals(type)){
	    		code="A";
	    	 for(int i=0;i<data01.length;i++){
	 	    	ImageListBean image = new ImageListBean();
	 	    	image.setName(data01[i]);
	 	    	map.put("picType","A00"+(i+1));
	 	    	image.setImages(szwxPhotoService.findPicPath(map));
	 	    	images.add(image);
	 	    }
	    }else if("2".equals(type)){
	    	 code="B";
	    	 for(int i=0;i<data02.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data02[i]);
		 	    	map.put("picType","B00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("3".equals(type)){
	    	 code="C";
	    	 for(int i=0;i<data03.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data03[i]);
		 	    	map.put("picType","C00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("4".equals(type)){
	    	 code="D";
	    	 for(int i=0;i<data04.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data04[i]);
		 	    	map.put("picType","D00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("5".equals(type)){
	    	 code="E";
	    	 for(int i=0;i<data05.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data05[i]);
		 	    	map.put("picType","E00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("6".equals(type)){
	    	 code="F";
	    	 for(int i=0;i<data06.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data06[i]);
		 	    	map.put("picType","F00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("7".equals(type)){
	    	 code="G";
	    	 for(int i=0;i<data07.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data07[i]);
		 	    	map.put("picType","G00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("8".equals(type)){
	    	 code="H";
	    	 for(int i=0;i<data08.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data08[i]);
		 	    	map.put("picType","H00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("9".equals(type)){
	    	 code="I";
	    	 for(int i=0;i<data09.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data09[i]);
		 	    	map.put("picType","I00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }else if("10".equals(type)){
	    	 code="J";
	    	 for(int i=0;i<data10.length;i++){
		 	    	ImageListBean image = new ImageListBean();
		 	    	image.setName(data10[i]);
		 	    	map.put("picType","J00"+(i+1));
		 	    	image.setImages(szwxPhotoService.findPicPath(map));
		 	    	images.add(image);
		 	    }
	    }
	    Map m = new HashMap();
	    List<String> list = new ArrayList<String>();
	    list.add("A19");
	    list.add("A17");
	    list.add("A03");
	    m.put("roleCode", list);
	    userList = whpClscService.getUserListByMap(m);
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
	    
	    ////////////
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
		if(whpClsc.getIfzsqy() != null && whpClsc.getIfzsqy().equals("1")){
			ifzsqy = "1";
		}else{
			ifzsqy = "0";
		}
		
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String dutyFlag = "";

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		paraMap.put("itemId", whpClsc.getId());
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
	    
		return VIEW+type;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		 return EDIT+type;
	   
	}

	/**
	 * 保存信息（包括新增和修改）
	 * 修改： 增加审核人员信息 by 陆婷 2013-12-12
	 */
	public String save() throws Exception{
		try {
			if(whpClsc != null && whpClsc.getQyid() != null && !"".equals(whpClsc.getQyid()))
			{
				Map map = new HashMap();
				map.put("companyId", whpClsc.getQyid());
				CompanyBackUp company = companyService.getCompanyBackupById(map);
				whpClsc.setDutyFlag("0");
				whpClsc.setIfzsqy(company.getIfzsqy());
				whpClsc.setZsqytype(company.getZsqytype());
				//Company company = companyService
				if(!whpClsc.getQymc().equals(company.getCompanyname()))
				{
					whpClsc.setQyid(null);
					Department dept = deptService.findDeptByDeptCode(whpClsc.getSzzid());
					whpClsc.setSzzname(dept.getDeptName());
				}
				else
				{
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					whpClsc.setSzzid(dept.getDeptCode());
					whpClsc.setSzzname(dept.getDeptName());
					whpClsc.setQyid(company.getId());
					whpClsc.setQymc(company.getCompanyname());
					whpClsc.setQylx(company.getQylx());
					whpClsc.setHyfl(company.getHyfl());
					whpClsc.setQygm(company.getQygm());
					whpClsc.setQyzclx(company.getQyzclx());
				}
			}
			else if(whpClsc.getSzzid() != null && !"".equals(whpClsc.getSzzid()))
			{
				Department dept = deptService.findDeptByDeptCode(whpClsc.getSzzid());
				whpClsc.setSzzid(dept.getDeptCode());
				whpClsc.setSzzname(dept.getDeptName());
			}
			whpClsc.setState("0");
			
			//User user = userService.findUserById(whpClsc.getShuserid());
			//whpClsc.setShusername(user.getDisplayName());
			if ("add".equalsIgnoreCase(this.flag)){
				whpClsc.setDeptId(this.getLoginUserDepartmentId());
				whpClsc.setDelFlag(0);
				whpClsc.setCreateUserID(this.getLoginUserId());
				whpClsc.setCreateTime(new Date());
				whpClscService.save(whpClsc);
				
				//hanxc 20141211 生成待审核任务 start
				ReviewLog newReviewLog = new ReviewLog();
				if(null != whpClsc.getIfzsqy() && whpClsc.getIfzsqy().equals("1")){
					newReviewLog.setDutyFlag("1");//市级部门审核任务
				}else{
					newReviewLog.setDutyFlag("2");//县级部门审核任务
				}
				newReviewLog.setState("0");
				newReviewLog.setItemId(whpClsc.getId());
				newReviewLog.setItemType("1");//企业信息类型：type=1 
				newReviewLog.setStartTime(new Date());
				reviewLogService.saveNewTask(newReviewLog);
			}else{
				whpClscService.update(whpClsc);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
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
		return SUCCESS+type;
	}
	/**
	 * 更新的状态 李军 2013-09-22
	 * 修改 ： 增加审核人员信息 by 陆婷 2013-12-12
	 */
	public String updateStatus() throws Exception{
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if((null != whpClsc)&&(null != whpClsc.getId())){
				String status = whpClsc.getState();
				String remark = whpClsc.getRemark();
				String a = "";
				WhpClsc g = whpClscService.getById(whpClsc.getId());
				g.setState(status);
				g.setRemark(remark);
				
				/**
				if("2".equals(status))
				{
					a = "审核不通过";
					g.setState(status);
					g.setRemark(remark);
				}
				else if("1".equals(status))
				{
					a = "审核通过";
					String isfinish = whpClsc.getIsfinish();
					if("0".equals(isfinish))
					{
						g.setState("0");
						String shuserid = whpClsc.getShuserid();
						User user = userService.findUserById(shuserid);
						g.setShuserid(shuserid);
						g.setShusername(user.getDisplayName());
					}
					else
					{
						g.setState(status);
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
				
				whpClscService.update(g);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RELOAD;
	}
	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			whpClscService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 跳转到图片导入界面  lj  2013-08-15
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
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
			root = root.replace("webapps","virtualdir/upload/file/clsc/");
			destFName.append(root);// 2013-08-21 按统一格式 模块名称/公司名称/附件名称
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
			String picname = "clsc/"+imgName;
			taskPic.setPicName(picname);
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
			jn.put("picName", picname);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
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
			getResponse().addHeader("Content-Disposition", "attachment;filename=whpclsc.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("相城区安监局危化品行政许可会审记录");
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
	        cell.setCellValue("相城区安监局危化品行政许可会审记录");
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
	        	whpClsc = (WhpClsc) getSessionAttribute("whpClsc");
	        	createTimeStart = (String) getSessionAttribute("createTimeStart");
	        	createTimeEnd = (String) getSessionAttribute("createTimeEnd");
    		}
	        if(null != whpClsc){
	        	setSessionAttribute("whpClsc", whpClsc);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != whpClsc.getState()) && (0 < whpClsc.getState().trim().length())){
					paraMap.put("state", whpClsc.getState().trim());
				}
				if ((null != whpClsc.getSzzid()) && (0 < whpClsc.getSzzid().trim().length())){
					paraMap.put("szzid", whpClsc.getSzzid().trim());
				}
				if ((null != whpClsc.getQymc()) && (0 < whpClsc.getQymc().trim().length())){
					paraMap.put("qymc", "%" + whpClsc.getQymc().trim() + "%");
				}
					paraMap.put("sclx",whpClsc.getSclx().trim());

			}
			
			if (null != createTimeStart){
				setSessionAttribute("createTimeStart", createTimeStart);
				paraMap.put("startCreateTime", createTimeStart);
			}

			if (null != createTimeEnd){
				setSessionAttribute("createTimeEnd", createTimeEnd);
				paraMap.put("endCreateTime", createTimeEnd);
			}
			//判断登录人是否为企业人员，要是，则查询自己登记
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))//企业
			{
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				paraMap.put("qyid", company.getId());
			}
			else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//乡镇安监中队
			{
				if(deptCode.length() == 12)
				{
					paraMap.put("szzid", deptCode);
				}
				else
				{
					paraMap.put("szc", deptCode);
				}
			}
			List<WhpClsc> list = whpClscService.findWhpClsc(paraMap);
			int num = 2;
			for(WhpClsc whpClsc:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(whpClsc.getQymc());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(whpClsc.getSzzname());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue((whpClsc.getIsCaiLiao() != null && whpClsc.getIsCaiLiao().equals("1"))?"是":"否");
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(whpClsc.getXzysyj());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(whpClsc.getQzld());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(whpClsc.getXchcbm());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(whpClsc.getHcjl());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(whpClsc.getBclzqk());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue((whpClsc.getIsCunChu() != null && whpClsc.getIsCunChu().equals("1"))?"是":"否");
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(whpClsc.getClscqk());
		        ce9.setCellStyle(c);
		        HSSFCell ce10 = ro.createCell(10);
		        ce10.setCellValue(whpClsc.getXzxkjy());
		        ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(whpClsc.getJhsjl());
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

	public WhpClsc getWhpClsc(){
		return this.whpClsc;
	}

	public void setWhpClsc(WhpClsc whpClsc){
		this.whpClsc = whpClsc;
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

	public List<PhotoPic> getPicList01() {
		return picList01;
	}

	public void setPicList01(List<PhotoPic> picList01) {
		this.picList01 = picList01;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getData01() {
		return data01;
	}

	public void setData01(String[] data01) {
		this.data01 = data01;
	}

	public List<ImageListBean> getImages() {
		return images;
	}

	public void setImages(List<ImageListBean> images) {
		this.images = images;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCreateTimeStartJs() {
		return createTimeStartJs;
	}

	public void setCreateTimeStartJs(String createTimeStartJs) {
		this.createTimeStartJs = createTimeStartJs;
	}

	public String getCreateTimeEndJs() {
		return createTimeEndJs;
	}

	public void setCreateTimeEndJs(String createTimeEndJs) {
		this.createTimeEndJs = createTimeEndJs;
	}

	public String getCreateTimeStartSb() {
		return createTimeStartSb;
	}

	public void setCreateTimeStartSb(String createTimeStartSb) {
		this.createTimeStartSb = createTimeStartSb;
	}

	public String getCreateTimeEndSb() {
		return createTimeEndSb;
	}

	public void setCreateTimeEndSb(String createTimeEndSb) {
		this.createTimeEndSb = createTimeEndSb;
	}

	public String getCreateTimeStartSl() {
		return createTimeStartSl;
	}

	public void setCreateTimeStartSl(String createTimeStartSl) {
		this.createTimeStartSl = createTimeStartSl;
	}

	public String getCreateTimeEndSl() {
		return createTimeEndSl;
	}

	public void setCreateTimeEndSl(String createTimeEndSl) {
		this.createTimeEndSl = createTimeEndSl;
	}

	public String getCreateTimeStartXk() {
		return createTimeStartXk;
	}

	public void setCreateTimeStartXk(String createTimeStartXk) {
		this.createTimeStartXk = createTimeStartXk;
	}

	public String getCreateTimeEndXk() {
		return createTimeEndXk;
	}

	public void setCreateTimeEndXk(String createTimeEndXk) {
		this.createTimeEndXk = createTimeEndXk;
	}

	public String getCreateTimeStartSc() {
		return createTimeStartSc;
	}

	public void setCreateTimeStartSc(String createTimeStartSc) {
		this.createTimeStartSc = createTimeStartSc;
	}

	public String getCreateTimeEndSc() {
		return createTimeEndSc;
	}

	public void setCreateTimeEndSc(String createTimeEndSc) {
		this.createTimeEndSc = createTimeEndSc;
	}

	public String getCreateTimeStartSp() {
		return createTimeStartSp;
	}

	public void setCreateTimeStartSp(String createTimeStartSp) {
		this.createTimeStartSp = createTimeStartSp;
	}

	public String getCreateTimeEndSp() {
		return createTimeEndSp;
	}

	public void setCreateTimeEndSp(String createTimeEndSp) {
		this.createTimeEndSp = createTimeEndSp;
	}

	public String getCreateTimeStartBa() {
		return createTimeStartBa;
	}

	public void setCreateTimeStartBa(String createTimeStartBa) {
		this.createTimeStartBa = createTimeStartBa;
	}

	public String getCreateTimeEndBa() {
		return createTimeEndBa;
	}

	public void setCreateTimeEndBa(String createTimeEndBa) {
		this.createTimeEndBa = createTimeEndBa;
	}

	public String getCreateTimeStartYs() {
		return createTimeStartYs;
	}

	public void setCreateTimeStartYs(String createTimeStartYs) {
		this.createTimeStartYs = createTimeStartYs;
	}

	public String getCreateTimeEndYs() {
		return createTimeEndYs;
	}

	public void setCreateTimeEndYs(String createTimeEndYs) {
		this.createTimeEndYs = createTimeEndYs;
	}

	public String getCreateTimeStartYxq() {
		return createTimeStartYxq;
	}

	public void setCreateTimeStartYxq(String createTimeStartYxq) {
		this.createTimeStartYxq = createTimeStartYxq;
	}

	public String getCreateTimeEndYxq() {
		return createTimeEndYxq;
	}

	public void setCreateTimeEndYxq(String createTimeEndYxq) {
		this.createTimeEndYxq = createTimeEndYxq;
	}
	
	
	///
	
	public String getDeptCodeLenth() {
		return deptCodeLenth;
	}

	public void setDeptCodeLenth(String deptCodeLenth) {
		this.deptCodeLenth = deptCodeLenth;
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
	
       
    
}
