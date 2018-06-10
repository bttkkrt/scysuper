/**
 * Class Name: YgtjbghzbAction
 * Class Description：员工体检报告汇总表列表
 */
package com.jshx.ygtjbghzb.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.safetysheet.service.SafetysheetService;
import com.jshx.ygtjbghzb.entity.Ygtjbghzb;
import com.jshx.ygtjbghzb.service.YgtjbghzbService;

public class YgtjbghzbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Ygtjbghzb ygtjbghzb = new Ygtjbghzb();

	/**
	 * 业务类
	 */
	@Autowired
	private YgtjbghzbService ygtjbghzbService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	/**
	 * 业务类
	 */
	@Autowired
	private SafetysheetService safetysheetService;
	 /**
	 * 图片业务类
	 */
	 @Autowired
	private SzwxPhotoService szwxPhotoService;
	 /**
	 * 部门业务类
	 */
 @Autowired
	private DeptService deptService;
 /**
	 * 企业业务类
	 */
	@Autowired
	private CompanyService companyService;
 private CompanyBackUp company=null;
 private String createTimeStart;

	private String createTimeEnd;


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


/** @author gq
	 * @date 8yue 24 
  *用于区分监管部门0和企业1
  */
 private String role;
 /** @author gq
	 * @date 8yue 24
  *用于区分监管部门0和更高级别1
  */
 private String visable;
	/**
	 * @author gq
	 * @date 8yue 14
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	
	private String createUserID;
	
	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @author gq
	 * @date 8yue 24
	 * @function 初始化安全生产页面
	 * @return 上传附件页面
	 */
	public String initlist() throws Exception{
		createUserID = this.getLoginUserId();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			visable = "0";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			visable = "1";
		}
		else if(null != deptRole && deptRole.contains(SysPropertiesUtil.getProperty("zywhCode")))
		{
			visable = "2";
		}
		else
		{
			visable = "3";
		}
		return SUCCESS;
	}
	
	public String initlists() throws Exception{
		return SUCCESS;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != ygtjbghzb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != ygtjbghzb.getDeptId()) && (0 < ygtjbghzb.getDeptId().trim().length())){
				paraMap.put("deptCodes", ygtjbghzb.getDeptId().trim());
			}
			if ((null != ygtjbghzb.getQymc()) && (0 < ygtjbghzb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + ygtjbghzb.getQymc().trim() + "%");
			}
			if ((null != ygtjbghzb.getHzname()) && (0 < ygtjbghzb.getHzname().trim().length())){
				paraMap.put("hzname", "%" + ygtjbghzb.getHzname().trim() + "%");
			}
			if ((null != ygtjbghzb.getSzc() )&& (0 < ygtjbghzb.getSzc().trim().length())){
				paraMap.put("szc",ygtjbghzb.getSzc().trim());
			}
			if ((null != ygtjbghzb.getZrs() )&& (0 < ygtjbghzb.getZrs().trim().length())){
				paraMap.put("zrs",ygtjbghzb.getZrs().trim());
			}
			if ((null != ygtjbghzb.getResult() )&& (0 < ygtjbghzb.getResult().trim().length())){
				paraMap.put("result",ygtjbghzb.getResult().trim());
			}
			if ((null != ygtjbghzb.getTjlx() )&& (0 < ygtjbghzb.getTjlx().trim().length())){
				paraMap.put("tjlx",ygtjbghzb.getTjlx().trim());
			}
			if ((null != ygtjbghzb.getTjjg() )&& (0 < ygtjbghzb.getTjjg().trim().length())){
				paraMap.put("tjjg",ygtjbghzb.getTjjg().trim());
			}
		}
		if (null != createTimeStart && !"".equals(createTimeStart)){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd && !"".equals(createTimeEnd)){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("comId", company.getId());
		}else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			if(deptCode.length() == 12)
			{
				paraMap.put("deptCodes", deptCode);
			}
			else
			{
				paraMap.put("szc", deptCode);
			}
		}
		pagination = ygtjbghzbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != ygtjbghzb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != ygtjbghzb.getDeptId()) && (0 < ygtjbghzb.getDeptId().trim().length())){
				paraMap.put("deptCodes", ygtjbghzb.getDeptId().trim());
			}
			if ((null != ygtjbghzb.getQymc()) && (0 < ygtjbghzb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + ygtjbghzb.getQymc().trim() + "%");
			}
			if ((null != ygtjbghzb.getHzname()) && (0 < ygtjbghzb.getHzname().trim().length())){
				paraMap.put("hzname", "%" + ygtjbghzb.getHzname().trim() + "%");
			}
			if ((null != ygtjbghzb.getSzc() )&& (0 < ygtjbghzb.getSzc().trim().length())){
				paraMap.put("szc",ygtjbghzb.getSzc().trim());
			}
			if ((null != ygtjbghzb.getZrs() )&& (0 < ygtjbghzb.getZrs().trim().length())){
				paraMap.put("zrs",ygtjbghzb.getZrs().trim());
			}
			if ((null != ygtjbghzb.getResult() )&& (0 < ygtjbghzb.getResult().trim().length())){
				paraMap.put("result",ygtjbghzb.getResult().trim());
			}
			if ((null != ygtjbghzb.getTjlx() )&& (0 < ygtjbghzb.getTjlx().trim().length())){
				paraMap.put("tjlx",ygtjbghzb.getTjlx().trim());
			}
			if ((null != ygtjbghzb.getTjjg() )&& (0 < ygtjbghzb.getTjjg().trim().length())){
				paraMap.put("tjjg",ygtjbghzb.getTjjg().trim());
			}
		}
		if (null != createTimeStart && !"".equals(createTimeStart)){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd && !"".equals(createTimeEnd)){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		pagination = ygtjbghzbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != ygtjbghzb)&&(null != ygtjbghzb.getId()))
			ygtjbghzb = ygtjbghzbService.getById(ygtjbghzb.getId());
		if(ygtjbghzb.getLinkid()==null)
		{
			ygtjbghzb.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",ygtjbghzb.getLinkid());
		map.put("picType","ygtjbghzb");
	    list = szwxPhotoService.findPicPath(map);//获取执法文书材料
	    
	    String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			visable = "0";
		}
		else
		{
			visable = "1";
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
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			ygtjbghzb.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			ygtjbghzb.setDeptId(company.getDwdz1());
			ygtjbghzb.setComid(company.getId());
			ygtjbghzb.setQymc(company.getCompanyname());
				//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			ygtjbghzb.setQylx(company.getQylx());
			ygtjbghzb.setHyfl(company.getHyfl());
			ygtjbghzb.setQygm(company.getQygm());
			ygtjbghzb.setQyzclx(company.getQyzclx());
			ygtjbghzb.setIfwhpqylx(company.getIfwhpqylx());
			ygtjbghzb.setIfyhbzjyqy(company.getIfyhbzjyqy());
			ygtjbghzb.setIfzywhqylx(company.getIfzywhqylx());
			ygtjbghzb.setSzc(company.getSzc());
			ygtjbghzb.setSzcname(company.getSzcname());
		}
		else
		{
			if(ygtjbghzb.getComid() != null && !"".equals(ygtjbghzb.getComid()))
			{
				Map map = new HashMap();
				map.put("companyId", ygtjbghzb.getComid());
				CompanyBackUp company = companyService.getCompanyBackupById(map);
				if(!ygtjbghzb.getQymc().equals(company.getCompanyname()))
				{
					ygtjbghzb.setComid(null);
					Department dept = deptService.findDeptByDeptCode(ygtjbghzb.getDeptId());
					ygtjbghzb.setDeptId(dept.getDeptCode());
					ygtjbghzb.setSzz(dept.getDeptName());
				}
				else
				{
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					ygtjbghzb.setDeptId(dept.getDeptCode());
					ygtjbghzb.setSzz(dept.getDeptName());
					ygtjbghzb.setComid(company.getId());
					ygtjbghzb.setQymc(company.getCompanyname());
					ygtjbghzb.setQylx(company.getQylx());
					ygtjbghzb.setHyfl(company.getHyfl());
					ygtjbghzb.setQygm(company.getQygm());
					ygtjbghzb.setQyzclx(company.getQyzclx());
					ygtjbghzb.setIfwhpqylx(company.getIfwhpqylx());
					ygtjbghzb.setIfyhbzjyqy(company.getIfyhbzjyqy());
					ygtjbghzb.setIfzywhqylx(company.getIfzywhqylx());
					ygtjbghzb.setSzc(company.getSzc());
					ygtjbghzb.setSzcname(company.getSzcname());
				}
			}
			else if(ygtjbghzb.getDeptId() != null && !"".equals(ygtjbghzb.getDeptId()))
			{
				Department dept = deptService.findDeptByDeptCode(ygtjbghzb.getDeptId());
				ygtjbghzb.setDeptId(dept.getDeptCode());
				ygtjbghzb.setSzz(dept.getDeptName());
			}
		}
		
		if ("add".equalsIgnoreCase(this.flag)){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ygtjbghzb.setUploadtime(sdf.format(new Date()));
			ygtjbghzb.setDelFlag(0);
			ygtjbghzb.setCreateUserID(this.getLoginUserId());
			ygtjbghzb.setCreateTime(new Date());
			ygtjbghzbService.save(ygtjbghzb);
		}else{
			ygtjbghzbService.update(ygtjbghzb);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			ygtjbghzbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zyjktjbg.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("职业病健康体检报告统计");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 10000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 5000); 
	        sheet.setColumnWidth(5, 5000); 
	        sheet.setColumnWidth(6, 6000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 6000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 6000);
	        sheet.setColumnWidth(11, 5000); 
	        sheet.setColumnWidth(12, 6000);
	        sheet.setColumnWidth(13, 5000);
	        sheet.setColumnWidth(14, 6000); 
	        sheet.setColumnWidth(15, 5000); 
	        sheet.setColumnWidth(16, 6000);
	        sheet.setColumnWidth(17, 5000);
	        sheet.setColumnWidth(18, 6000);
	        sheet.setColumnWidth(19, 5000);
	        sheet.setColumnWidth(20, 6000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("职业病健康体检报告统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 20)); 
	        
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
	        ccl0.setCellValue("乡镇");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业名称");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 2, (short) 1)); 
	        HSSFCell ccl3 = r3.createCell(2);
	        ccl3.setCellValue("体检日期");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 2, (short) 2)); 
	        HSSFCell ccl4 = r3.createCell(3);
	        ccl4.setCellValue("体检机构");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 2, (short) 3)); 
	        HSSFCell ccl5 = r3.createCell(4);
	        ccl5.setCellValue("体检人数");
	        ccl5.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 4, 2, (short) 4)); 
	        HSSFCell ccl2 = r3.createCell(5);
	        ccl2.setCellValue("上岗");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 5, 1, (short) 8)); 
	        HSSFCell ccl6 = r3.createCell(9);
	        ccl6.setCellValue("在岗");
	        ccl6.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 9, 1, (short) 12)); 
	        HSSFCell ccl7 = r3.createCell(13);
	        ccl7.setCellValue("下岗");
	        ccl7.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 13, 1, (short) 16)); 
	        HSSFCell ccl8 = r3.createCell(17);
	        ccl8.setCellValue("应急");
	        ccl8.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 17, 1, (short) 20)); 
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl9 = r4.createCell(5);
	        ccl9.setCellValue("正常人数");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r4.createCell(6);
	        ccl10.setCellValue("职业相关异常人数");
	        ccl10.setCellStyle(cs);
	        HSSFCell ccl11 = r4.createCell(7);
	        ccl11.setCellValue("职业禁忌人数");
	        ccl11.setCellStyle(cs);
	        HSSFCell ccl12 = r4.createCell(8);
	        ccl12.setCellValue("疑似职业病人人数");
	        ccl12.setCellStyle(cs);
	        HSSFCell ccl13 = r4.createCell(9);
	        ccl13.setCellValue("正常人数");
	        ccl13.setCellStyle(cs);
	        HSSFCell ccl14 = r4.createCell(10);
	        ccl14.setCellValue("职业相关异常人数");
	        ccl14.setCellStyle(cs);
	        HSSFCell ccl15 = r4.createCell(11);
	        ccl15.setCellValue("职业禁忌人数");
	        ccl15.setCellStyle(cs);
	        HSSFCell ccl16 = r4.createCell(12);
	        ccl16.setCellValue("疑似职业病人人数");
	        ccl16.setCellStyle(cs);
	        HSSFCell ccl17 = r4.createCell(13);
	        ccl17.setCellValue("正常人数");
	        ccl17.setCellStyle(cs);
	        HSSFCell ccl18 = r4.createCell(14);
	        ccl18.setCellValue("职业相关异常人数");
	        ccl18.setCellStyle(cs);
	        HSSFCell ccl19 = r4.createCell(15);
	        ccl19.setCellValue("职业禁忌人数");
	        ccl19.setCellStyle(cs);
	        HSSFCell ccl20 = r4.createCell(16);
	        ccl20.setCellValue("疑似职业病人人数");
	        ccl20.setCellStyle(cs);
	        HSSFCell ccl21 = r4.createCell(17);
	        ccl21.setCellValue("正常人数");
	        ccl21.setCellStyle(cs);
	        HSSFCell ccl22 = r4.createCell(18);
	        ccl22.setCellValue("职业相关异常人数");
	        ccl22.setCellStyle(cs);
	        HSSFCell ccl23 = r4.createCell(19);
	        ccl23.setCellValue("职业禁忌人数");
	        ccl23.setCellStyle(cs);
	        HSSFCell ccl24 = r4.createCell(20);
	        ccl24.setCellValue("疑似职业病人人数");
	        ccl24.setCellStyle(cs);
	        
	        
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
	        	ygtjbghzb = (Ygtjbghzb) getSessionAttribute("ygtjbghzb");
	        	createTimeStart = (String) getSessionAttribute("createTimeStart");
	        	createTimeEnd = (String) getSessionAttribute("createTimeEnd");
    		}
	        if(null != ygtjbghzb){
	        	setSessionAttribute("ygtjbghzb", ygtjbghzb);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != ygtjbghzb.getDeptId()) && (0 < ygtjbghzb.getDeptId().trim().length())){
					paraMap.put("deptCodes", ygtjbghzb.getDeptId().trim());
				}
				if ((null != ygtjbghzb.getQymc()) && (0 < ygtjbghzb.getQymc().trim().length())){
					paraMap.put("qymc", "%" + ygtjbghzb.getQymc().trim() + "%");
				}
				if ((null != ygtjbghzb.getHzname()) && (0 < ygtjbghzb.getHzname().trim().length())){
					paraMap.put("hzname", "%" + ygtjbghzb.getHzname().trim() + "%");
				}
				if ((null != ygtjbghzb.getSzc() )&& (0 < ygtjbghzb.getSzc().trim().length())){
					paraMap.put("szc",ygtjbghzb.getSzc().trim());
				}
				if ((null != ygtjbghzb.getZrs() )&& (0 < ygtjbghzb.getZrs().trim().length())){
					paraMap.put("zrs",ygtjbghzb.getZrs().trim());
				}
				if ((null != ygtjbghzb.getResult() )&& (0 < ygtjbghzb.getResult().trim().length())){
					paraMap.put("result",ygtjbghzb.getResult().trim());
				}
				if ((null != ygtjbghzb.getTjlx() )&& (0 < ygtjbghzb.getTjlx().trim().length())){
					paraMap.put("tjlx",ygtjbghzb.getTjlx().trim());
				}
				if ((null != ygtjbghzb.getTjjg() )&& (0 < ygtjbghzb.getTjjg().trim().length())){
					paraMap.put("tjjg",ygtjbghzb.getTjjg().trim());
				}
			}
			if (null != createTimeStart && !"".equals(createTimeStart)){
				setSessionAttribute("createTimeStart", createTimeStart);
				paraMap.put("startCreateTime", createTimeStart);
			}

			if (null != createTimeEnd && !"".equals(createTimeEnd)){
				setSessionAttribute("createTimeEnd", createTimeEnd);
				paraMap.put("endCreateTime", createTimeEnd);
			}
	        
			List<Ygtjbghzb> list = ygtjbghzbService.findYgtjbghzb(paraMap);
			
			int num = 3;
			int a = 0;
			int a1 = 0;
	        int a2 = 0;
	        int a3 = 0;
	        int a4 = 0;
	        int a5 = 0;
	        int a6 = 0;
	        int a7 = 0;
	        int a8 = 0;
	        int a9 = 0;
	        int a10 = 0;
	        int a11 = 0;
	        int a12 = 0;
	        int a13 = 0;
	        int a14 = 0;
	        int a15 = 0;
	        int a16 = 0;
			for(Ygtjbghzb ygtjbghzb:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(ygtjbghzb.getSzz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(ygtjbghzb.getQymc());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(ygtjbghzb.getTjsj());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(null!=ygtjbghzb.getTjjg()?(String)companyService.findCompanyTypeNameByKey(ygtjbghzb.getTjjg(),"40288048470f52d3014714ca4c290d63"):"");
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(ygtjbghzb.getZrs());
		        ce4.setCellStyle(c);
		        a += Integer.parseInt((ygtjbghzb.getZrs() != null && !"".equals(ygtjbghzb.getZrs()))?ygtjbghzb.getZrs():"0");
		        
		        
		        String tjlx = ygtjbghzb.getTjlx();
		        if(tjlx != null)
		        {
		        	int zcrs = Integer.parseInt((ygtjbghzb.getZcrs() != null && !"".equals(ygtjbghzb.getZcrs()))?ygtjbghzb.getZcrs():"0");
	        		int zybrs = Integer.parseInt((ygtjbghzb.getZybrs() != null && !"".equals(ygtjbghzb.getZybrs()))?ygtjbghzb.getZybrs():"0");
	        		int zyjjrs = Integer.parseInt((ygtjbghzb.getZyjjrs() != null && !"".equals(ygtjbghzb.getZyjjrs()))?ygtjbghzb.getZyjjrs():"0");
	        		int yszybrs = Integer.parseInt((ygtjbghzb.getYszybrs() != null && !"".equals(ygtjbghzb.getYszybrs()))?ygtjbghzb.getYszybrs():"0");
		        	if("1".equals(tjlx))
		        	{
		        		a1 += zcrs;
		        		a2 += zybrs;
		        		a3 += zyjjrs;
		        		a4 += yszybrs;
		        		HSSFCell ce5 = ro.createCell(5);
				        ce5.setCellValue(zcrs);
				        ce5.setCellStyle(c);
				        HSSFCell ce6 = ro.createCell(6);
				        ce6.setCellValue(zybrs);
				        ce6.setCellStyle(c);
				        HSSFCell ce7 = ro.createCell(7);
				        ce7.setCellValue(zyjjrs);
				        ce7.setCellStyle(c);
				        HSSFCell ce8 = ro.createCell(8);
				        ce8.setCellValue(yszybrs);
				        ce8.setCellStyle(c);
		        	}
		        	else if("2".equals(tjlx))
		        	{
		        		a5 += zcrs;
		        		a6 += zybrs;
		        		a7 += zyjjrs;
		        		a8 += yszybrs;
		        		HSSFCell ce5 = ro.createCell(9);
				        ce5.setCellValue(zcrs);
				        ce5.setCellStyle(c);
				        HSSFCell ce6 = ro.createCell(10);
				        ce6.setCellValue(zybrs);
				        ce6.setCellStyle(c);
				        HSSFCell ce7 = ro.createCell(11);
				        ce7.setCellValue(zyjjrs);
				        ce7.setCellStyle(c);
				        HSSFCell ce8 = ro.createCell(12);
				        ce8.setCellValue(yszybrs);
				        ce8.setCellStyle(c);
		        	}
		        	else if("3".equals(tjlx))
		        	{
		        		a9 += zcrs;
		        		a10 += zybrs;
		        		a11 += zyjjrs;
		        		a12 += yszybrs;
		        		HSSFCell ce5 = ro.createCell(13);
				        ce5.setCellValue(zcrs);
				        ce5.setCellStyle(c);
				        HSSFCell ce6 = ro.createCell(14);
				        ce6.setCellValue(zybrs);
				        ce6.setCellStyle(c);
				        HSSFCell ce7 = ro.createCell(15);
				        ce7.setCellValue(zyjjrs);
				        ce7.setCellStyle(c);
				        HSSFCell ce8 = ro.createCell(16);
				        ce8.setCellValue(yszybrs);
				        ce8.setCellStyle(c);
		        	}
		        	else if("4".equals(tjlx))
		        	{
		        		a13 += zcrs;
		        		a14 += zybrs;
		        		a15 += zyjjrs;
		        		a16 += yszybrs;
		        		HSSFCell ce5 = ro.createCell(17);
				        ce5.setCellValue(zcrs);
				        ce5.setCellStyle(c);
				        HSSFCell ce6 = ro.createCell(18);
				        ce6.setCellValue(zybrs);
				        ce6.setCellStyle(c);
				        HSSFCell ce7 = ro.createCell(19);
				        ce7.setCellValue(zyjjrs);
				        ce7.setCellStyle(c);
				        HSSFCell ce8 = ro.createCell(20);
				        ce8.setCellValue(yszybrs);
				        ce8.setCellStyle(c);
		        	}
		        }
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			sheet.addMergedRegion(new Region(num, (short) 0, num, (short) 3)); 
			HSSFCell ce1 = ro.createCell(4);
			ce1.setCellValue(a);
			ce1.setCellStyle(c);
			HSSFCell ce2 = ro.createCell(5);
			ce2.setCellValue(a1);
			ce2.setCellStyle(c);
			HSSFCell ce3 = ro.createCell(6);
			ce3.setCellValue(a2);
			ce3.setCellStyle(c);
			HSSFCell ce4 = ro.createCell(7);
			ce4.setCellValue(a3);
			ce4.setCellStyle(c);
			HSSFCell ce5 = ro.createCell(8);
			ce5.setCellValue(a4);
			ce5.setCellStyle(c);
			HSSFCell ce6 = ro.createCell(9);
			ce6.setCellValue(a5);
			ce6.setCellStyle(c);
			HSSFCell ce7 = ro.createCell(10);
			ce7.setCellValue(a6);
			ce7.setCellStyle(c);
			HSSFCell ce8 = ro.createCell(11);
			ce8.setCellValue(a7);
			ce8.setCellStyle(c);
			HSSFCell ce9 = ro.createCell(12);
			ce9.setCellValue(a8);
			ce9.setCellStyle(c);
			HSSFCell ce10 = ro.createCell(13);
			ce10.setCellValue(a9);
			ce10.setCellStyle(c);
			HSSFCell ce11 = ro.createCell(14);
			ce11.setCellValue(a10);
			ce11.setCellStyle(c);
			HSSFCell ce12 = ro.createCell(15);
			ce12.setCellValue(a11);
			ce12.setCellStyle(c);
			HSSFCell ce13 = ro.createCell(16);
			ce13.setCellValue(a12);
			ce13.setCellStyle(c);
			HSSFCell ce14 = ro.createCell(17);
			ce14.setCellValue(a13);
			ce14.setCellStyle(c);
			HSSFCell ce15 = ro.createCell(18);
			ce15.setCellValue(a14);
			ce15.setCellStyle(c);
			HSSFCell ce16 = ro.createCell(19);
			ce16.setCellValue(a15);
			ce16.setCellStyle(c);
			HSSFCell ce17 = ro.createCell(20);
			ce17.setCellValue(a16);
			ce17.setCellStyle(c);
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

	public Ygtjbghzb getYgtjbghzb(){
		return this.ygtjbghzb;
	}

	public void setYgtjbghzb(Ygtjbghzb ygtjbghzb){
		this.ygtjbghzb = ygtjbghzb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getVisable() {
		return visable;
	}


	public void setVisable(String visable) {
		this.visable = visable;
	}


	public List<PhotoPic> getList() {
		return list;
	}


	public void setList(List<PhotoPic> list) {
		this.list = list;
	}
       
    
}
