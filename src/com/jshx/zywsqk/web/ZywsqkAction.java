/**
 * Class Name: ZywsqkAction
 * Class Description：企业职业卫生情况
 */
package com.jshx.zywsqk.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.jshx.zywhglb.entity.Zywhglb;
import com.jshx.zywhglb.service.ZywhglbService;
import com.jshx.zywsqk.entity.Zywsqk;
import com.jshx.zywsqk.entity.ZywsqkAll;
import com.jshx.zywsqk.service.ZywsqkService;

public class ZywsqkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zywsqk zywsqk = new Zywsqk();

	/**
	 * 业务类
	 */
	@Autowired
	private ZywsqkService zywsqkService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private ZywhglbService zywhglbService;
	

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private String queryTjnfStart; //开始时间

	private String queryTjnfEnd; //结束时间
	
	private List<Zywhglb> fcList = new ArrayList<Zywhglb>();  //粉尘
	
	private List<Zywhglb> hxList = new ArrayList<Zywhglb>();  //化学
	
	private List<Zywhglb> wlList = new ArrayList<Zywhglb>(); //物理
	
	private List<Zywhglb> swList = new ArrayList<Zywhglb>();  //生物
	
	private String fcjcrs;  //粉尘接触人数
	private String fcjcds; //粉尘检测点数
	private String fcdbds; //粉尘达标点数
	private String fczywhmc; //粉尘职业病危害因素名称
	private String fczywhid; //粉尘职业病危害因素id
	private String hxjcrs; //化学接触人数
	private String hxjcds; //化学检测点数
	private String hxdbds; //化学达标点数
	private String hxzywhmc; //化学职业病危害因素名称
	private String hxzywhid; //化学职业病危害因素id
	private String wljcrs; //物理接触人数
	private String wljcds; //物理检测点数
	private String wldbds; //物理达标点数
	private String wlzywhmc; //物理职业病危害因素名称
	private String wlzywhid; //物理职业病危害因素id
	private String swjcrs; //生物接触人数
	private String swjcds; //生物检测点数
	private String swdbds; //生物达标点数
	private String swzywhmc; //生物职业病危害因素名称
	private String swzywhid; //生物职业病危害因素id
	
	private String dclx;

	/**
	 * 初始化职业卫生情况列表
	 * author：陆婷
	 * 2013-08-26
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		else
		{
			flag = "3";
		}
		return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 * author：陆婷
	 * 2013-08-26
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zywsqk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zywsqk.getQymc()) && (0 < zywsqk.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zywsqk.getQymc().trim() + "%");
			}

			if (null != queryTjnfStart && (0 < queryTjnfStart.trim().length())){
				paraMap.put("startTjnf", queryTjnfStart);
			}

			if (null != queryTjnfEnd && (0 < queryTjnfEnd.trim().length())){
				paraMap.put("endTjnf", queryTjnfEnd);
			}
			if ((null != zywsqk.getSzzid()) && (0 < zywsqk.getSzzid().trim().length())){
				paraMap.put("szzid",  zywsqk.getSzzid().trim() );
			}
			if ((null != zywsqk.getSzc()) && (0 < zywsqk.getSzc().trim().length())){
				paraMap.put("szc",  zywsqk.getSzc().trim() );
			}
		}
		//判断登录人是否为企业人员，要是，则查询自己登记
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			paraMap.put("qyid", company.getId());
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr"))) //判断为安监中队，则查看所在镇企业
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
		pagination = zywsqkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 * author：陆婷
	 * 2013-08-26
	 */
	public String view() throws Exception{
		Map map = new HashMap();
		if((null != zywsqk)&&(null != zywsqk.getId()))
		{
			zywsqk = zywsqkService.getById(zywsqk.getId());
			//获取粉尘列表
			map.put("zywsid", zywsqk.getId());
			map.put("type", "fc");
			fcList = zywhglbService.findZywhglb(map);
			//获取化学列表
			map.put("type", "hx");
			hxList = zywhglbService.findZywhglb(map);
			map.put("type", "wl");
			//获取物理列表
			wlList = zywhglbService.findZywhglb(map);
			map.put("type", "sw");
			//获取生物列表
			swList = zywhglbService.findZywhglb(map);
		}
		else
		{
			//获取粉尘列表
			map.put("id", "402880484076bce30140a570040f0b91");
			fcList = zywhglbService.getFcListByMap(map);
			//获取化学列表
			map.put("id", "402880484076bce30140a57205a40b9f");
			hxList = zywhglbService.getFcListByMap(map);
			//获取物理列表
			map.put("id", "402880484076bce30140a582ef330c24");
			wlList = zywhglbService.getFcListByMap(map);
			//获取生物列表
			map.put("id", "402880484076bce30140a584ab8e0c33");
			swList = zywhglbService.getFcListByMap(map);
		}
		return VIEW;
	}

	/**
	 * 初始化修改信息
	* author：陆婷
	 * 2013-08-26
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 * author：陆婷
	 * 2013-08-26
	 */
	public String save() throws Exception{
		try {
			if ("add".equalsIgnoreCase(this.flag)){
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				zywsqk.setSzzid(dept.getDeptCode());
				zywsqk.setSzzname(dept.getDeptName());
				zywsqk.setQyid(company.getId());
				zywsqk.setQymc(company.getCompanyname());
				zywsqk.setDeptId(this.getLoginUserDepartmentId());
				zywsqk.setDelFlag(0);
				zywsqk.setCreateUserID(this.getLoginUserId());
				zywsqk.setCreateTime(new Date());
				zywsqk.setQylx(company.getQylx());
				zywsqk.setHyfl(company.getHyfl());
				zywsqk.setQygm(company.getQygm());
				zywsqk.setQyzclx(company.getQyzclx());
				zywsqk.setZzjgdm(company.getZzjgdm());
				zywsqk.setFddbr(company.getFddbr());
				zywsqk.setFddbrlxdh(company.getFddbrlxhm());
				zywsqk.setSzc(company.getSzc());
				zywsqk.setSzcname(company.getSzcname());
				zywsqkService.save(zywsqk);
			}else{
				zywsqkService.update(zywsqk);
				Map map = new HashMap();
				map.put("zywsid", zywsqk.getId());
				zywsqkService.deleteZywhglbByMap(map);
			}
			//将职业卫生情况插入关联表
			if(fczywhid != null)
			{
				saveZywhglb(fczywhid,fczywhmc,fcdbds,fcjcds,fcjcrs,zywsqk,"fc");
			}
			if(hxzywhid != null)
			{
				saveZywhglb(hxzywhid,hxzywhmc,hxdbds,hxjcds,hxjcrs,zywsqk,"hx");
			}
			if(wlzywhid != null)
			{
				saveZywhglb(wlzywhid,wlzywhmc,wldbds,wljcds,wljcrs,zywsqk,"wl");
			}
			if(swzywhid != null)
			{
				saveZywhglb(swzywhid,swzywhmc,swdbds,swjcds,swjcrs,zywsqk,"sw");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return RELOAD;
	}
	/**
	 * 将职业卫生情况插入关联表
	 * author：陆婷
	 * 2013-08-26
	 */
	public void saveZywhglb(String zywhid,String zywhmc,String dbds,String jcds,String jcrs,Zywsqk zywsqk,String type)
	{
		String[] zywhids = zywhid.replaceAll(" ", "").split(",");
		String[] zywhmcs = zywhmc.replaceAll(" ", "").split(",");
		String[] jcrss = jcrs.replaceAll(" ", "").split(",");
		String[] jcdss = jcds.replaceAll(" ", "").split(",");
		String[] dbdss = dbds.replaceAll(" ", "").split(",");
		for(int i=0;i<zywhids.length;i++)
		{
			Zywhglb zywhglb = new Zywhglb();
			zywhglb.setCreateTime(new Date());
			zywhglb.setCreateUserID(this.getLoginUserId());
			zywhglb.setDbds(isNull(dbdss,i));
			zywhglb.setDelFlag(0);
			zywhglb.setHyfl(zywsqk.getHyfl());
			zywhglb.setJcds(isNull(jcdss,i));
			zywhglb.setJcrs(isNull(jcrss,i));
			zywhglb.setQygm(zywsqk.getQygm());
			zywhglb.setQyid(zywsqk.getQyid());
			zywhglb.setQylx(zywsqk.getQylx());
			zywhglb.setQymc(zywsqk.getQymc());
			zywhglb.setSzzid(zywsqk.getSzzid());
			zywhglb.setSzzname(zywsqk.getSzzname());
			zywhglb.setZywhid(isNull(zywhids,i));
			zywhglb.setZywhmc(isNull(zywhmcs,i));
			zywhglb.setZywsid(zywsqk.getId());
			zywhglb.setType(type);
			zywhglb.setTjnf(zywsqk.getTjnf());
			zywhglbService.save(zywhglb);
		}
	}
	/**
	 * 判断值是否为空
	 * author：陆婷
	 * 2013-08-26
	 */
	public String isNull(String[] object,int i)
	{
		String s;
		if(object.length < i+1)
		{
			s = "0";
		}
		else
		{
			if("".equals(object[i]))
			{
				s = "0";
			}
			else
			{
				s = object[i];
			}
		}
		return s;
	}
	/**
	 * 删除信息
	 * author：陆婷
	 * 2013-08-26
	 */
	public String delete() throws Exception{
	    try{
			zywsqkService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	
	/**
	 * 按乡镇导出职业卫生情况
	 * author：陆婷
	 * 2013-12-23 2013-12-24
	 */
	public void export()
	{
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
	        
	        if(flag == null || "".equals(flag))
    		{
        		dclx = (String) getSessionAttribute("dclx");
        		queryTjnfStart = (String) getSessionAttribute("queryTjnfStart");
        		queryTjnfEnd = (String) getSessionAttribute("queryTjnfEnd");
    		}
	        if (null != queryTjnfStart && (0 < queryTjnfStart.trim().length())){
				paraMap.put("startTjnf", queryTjnfStart);
				setSessionAttribute("queryTjnfStart", queryTjnfStart.trim());
			}

			if (null != queryTjnfEnd && (0 < queryTjnfEnd.trim().length())){
				paraMap.put("endTjnf", queryTjnfEnd);
				setSessionAttribute("queryTjnfEnd", queryTjnfEnd.trim());
			}
			if(null != dclx && (0 < dclx.trim().length()))
			{
				paraMap.put("dclx", dclx);
				setSessionAttribute("dclx", dclx.trim());
			}
			String filename = "";
			String filename1 = "";
			String filename2 = "";
			if("0".equals(dclx))
			{
				filename = "xz";
				filename1 = "按乡镇";
				filename2 = "地区";
			}
			else if("1".equals(dclx))
			{
				filename = "hy";
				filename1 = "按行业类型";
				filename2 = "行业";
			}
			else if("2".equals(dclx))
			{
				filename = "gm";
				filename1 = "按企业规模";
				filename2 = "规模";
			}
			else if("3".equals(dclx))
			{
				filename = "lx";
				filename1 = "按注册类型";
				filename2 = "登记注册类型";
			}
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=" + filename + "zyws.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("工矿商贸企业职业卫生统计表（" +filename1 + "）");
			sheet.createFreezePane(1,4,2,4);
		    sheet.setColumnWidth(0, 5000); 
	        sheet.setColumnWidth(1, 5000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 10000);
	        sheet.setColumnWidth(9, 5000);
	        sheet.setColumnWidth(10, 5000);
	        sheet.setColumnWidth(11, 5000);
	        sheet.setColumnWidth(12, 5000);
	        sheet.setColumnWidth(13, 5000);
	        sheet.setColumnWidth(14, 5000);
	        sheet.setColumnWidth(15, 5000);
	        sheet.setColumnWidth(16, 5000);
	        sheet.setColumnWidth(17, 5000);
	        sheet.setColumnWidth(18, 5000);
	        sheet.setColumnWidth(19, 10000);
	        sheet.setColumnWidth(20, 5000);
	        sheet.setColumnWidth(21, 5000);
	        sheet.setColumnWidth(22, 5000);
	        sheet.setColumnWidth(23, 5000);
	        sheet.setColumnWidth(24, 5000);
	        sheet.setColumnWidth(25, 5000);
	        sheet.setColumnWidth(26, 5000);
	        sheet.setColumnWidth(27, 5000);
	        sheet.setColumnWidth(28, 5000);
	        sheet.setColumnWidth(29, 5000);
	        sheet.setColumnWidth(30, 5000);
	        sheet.setColumnWidth(31, 5000);
	        sheet.setColumnWidth(32, 5000);
	        sheet.setColumnWidth(33, 5000);
	        sheet.setColumnWidth(34, 5000);
	        sheet.setColumnWidth(35, 5000);
	        sheet.setColumnWidth(36, 5000);
	        sheet.setColumnWidth(37, 5000);
	        sheet.setColumnWidth(38, 5000);
	        sheet.setColumnWidth(39, 5000);
	        sheet.setColumnWidth(40, 5000);
	        
	        
		    sheet.setColumnWidth(41, 5000); 
	        sheet.setColumnWidth(42, 5000); 
	        sheet.setColumnWidth(43, 5000);
	        sheet.setColumnWidth(44, 5000);
	        sheet.setColumnWidth(45, 5000);
	        sheet.setColumnWidth(46, 5000);
	        sheet.setColumnWidth(47, 5000);
	        sheet.setColumnWidth(48, 5000);
	        sheet.setColumnWidth(49, 5000);
	        sheet.setColumnWidth(50, 5000);
	        sheet.setColumnWidth(51, 5000);
	        sheet.setColumnWidth(52, 5000);
	        sheet.setColumnWidth(53, 5000);
	        sheet.setColumnWidth(54, 5000);
	        sheet.setColumnWidth(55, 5000);
	        sheet.setColumnWidth(56, 5000);
	        sheet.setColumnWidth(57, 5000);
	        sheet.setColumnWidth(58, 5000);
	        sheet.setColumnWidth(59, 5000);
	        sheet.setColumnWidth(60, 10000);
	        sheet.setColumnWidth(61, 5000);
	        sheet.setColumnWidth(62, 5000);
	        sheet.setColumnWidth(63, 5000);
	        sheet.setColumnWidth(64, 5000);
	        sheet.setColumnWidth(65, 5000);
	        sheet.setColumnWidth(66, 5000);
	        sheet.setColumnWidth(67, 5000);
	        sheet.setColumnWidth(68, 5000);
	        sheet.setColumnWidth(69, 5000);
	        sheet.setColumnWidth(70, 5000);
	        sheet.setColumnWidth(71, 10000);
	        sheet.setColumnWidth(72, 5000);
	        sheet.setColumnWidth(73, 5000);
	        sheet.setColumnWidth(74, 5000);
	        sheet.setColumnWidth(75, 5000);
	        sheet.setColumnWidth(76, 5000);
	        sheet.setColumnWidth(77, 5000);
	        sheet.setColumnWidth(78, 5000);
	        sheet.setColumnWidth(79, 5000);
	        sheet.setColumnWidth(80, 5000);
	        sheet.setColumnWidth(81, 5000);
	        
	        
		    sheet.setColumnWidth(82, 10000); 
	        sheet.setColumnWidth(83, 5000); 
	        sheet.setColumnWidth(84, 5000);
	        sheet.setColumnWidth(85, 5000);
	        sheet.setColumnWidth(86, 5000);
	        sheet.setColumnWidth(87, 5000);
	        sheet.setColumnWidth(88, 5000);
	        sheet.setColumnWidth(89, 5000);
	        sheet.setColumnWidth(90, 5000);
	        sheet.setColumnWidth(91, 5000);
	        sheet.setColumnWidth(92, 5000);
	        sheet.setColumnWidth(93, 5000);
	        sheet.setColumnWidth(94, 5000);
	        sheet.setColumnWidth(95, 5000);
	        sheet.setColumnWidth(96, 5000);
	        sheet.setColumnWidth(97, 5000);
	        sheet.setColumnWidth(98, 5000);
	        sheet.setColumnWidth(99, 5000);
	        sheet.setColumnWidth(100, 5000);
	        sheet.setColumnWidth(101, 5000);
	        sheet.setColumnWidth(102, 5000);
	        sheet.setColumnWidth(103, 5000);
	        sheet.setColumnWidth(104, 5000);
	        sheet.setColumnWidth(105, 5000);
	        sheet.setColumnWidth(106, 5000);
	        sheet.setColumnWidth(107, 5000);
	        sheet.setColumnWidth(108, 5000);
	        sheet.setColumnWidth(109, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("工矿商贸企业职业卫生统计表（" +filename1 + "）");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 108)); 
	        
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
	        ccl0.setCellValue(filename2);
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 3, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业数量（个）");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 3, (short) 1)); 
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("从业人数（人）");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 3, (short) 2)); 
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("接触职业病危害因素人数（人）");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 3, 1, (short) 13)); 
	        HSSFCell ccl4 = r3.createCell(14);
	        ccl4.setCellValue("职业病危害因素接触率（％）");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 14, 1, (short) 24));
	        HSSFCell ccl5 = r3.createCell(25);
	        ccl5.setCellValue("申报职业病危害企业数（个）");
	        ccl5.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 25, 3, (short) 25));
	        HSSFCell ccl6 = r3.createCell(26);
	        ccl6.setCellValue("职业病危害申报率（％）");
	        ccl6.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 26, 3, (short) 26));
	        HSSFCell ccl7 = r3.createCell(27);
	        ccl7.setCellValue("应职业病危害预评价项目数（个）");
	        ccl7.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 27, 3, (short) 27));
	        
	        HSSFCell ccl8 = r3.createCell(28);
	        ccl8.setCellValue("实际职业病危害预评价项目数（个）");
	        ccl8.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 28, 3, (short) 28));
	        HSSFCell ccl9 = r3.createCell(29);
	        ccl9.setCellValue("建设项目职业病危害预评价率（％）");
	        ccl9.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 29, 3, (short) 29));
	        HSSFCell ccl10 = r3.createCell(30);
	        ccl10.setCellValue("应职业病危害控制效果评价项目数（个）");
	        ccl10.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 30, 3, (short) 30));
	        HSSFCell ccl11 = r3.createCell(31);
	        ccl11.setCellValue("实际职业病危害控制效果评价项目数（个）");
	        ccl11.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 31, 3, (short) 31));
	        HSSFCell ccl12 = r3.createCell(32);
	        ccl12.setCellValue("建设项目职业病危害控制效果评价率（％）");
	        ccl12.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 32, 3, (short) 32));
	        
	        HSSFCell ccl13 = r3.createCell(33);
	        ccl13.setCellValue("合同告知职业病危害人数（人）");
	        ccl13.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 33, 3, (short) 33));
	        HSSFCell ccl14 = r3.createCell(34);
	        ccl14.setCellValue("职业病危害告知率（％）");
	        ccl14.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 34, 3, (short) 34));
	        HSSFCell ccl15 = r3.createCell(35);
	        ccl15.setCellValue("建立职业健康监护档案人数（人）");
	        ccl15.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 35, 3, (short) 35));
	        HSSFCell ccl16 = r3.createCell(36);
	        ccl16.setCellValue("职业健康监护档案建立率（％）");
	        ccl16.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 36, 3, (short) 36));
	        HSSFCell ccl17 = r3.createCell(37);
	        ccl17.setCellValue("职业病危害作业岗位数（个）");
	        ccl17.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 37, 3, (short) 37));
	        HSSFCell ccl18 = r3.createCell(38);
	        ccl18.setCellValue("设置警示标识岗位数（个）");
	        ccl18.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 38, 3, (short) 38));
	        HSSFCell ccl19 = r3.createCell(39);
	        ccl19.setCellValue("警示标识设置率（％）");
	        ccl19.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 39, 3, (short) 39));
	        HSSFCell ccl20 = r3.createCell(40);
	        ccl20.setCellValue("主要负责人已职业卫生培训企业数（个）");
	        ccl20.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 40, 3, (short) 40));
	        HSSFCell ccl21 = r3.createCell(41);
	        ccl21.setCellValue("主要负责人职业卫生培训率（％）");
	        ccl21.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 41, 3, (short) 41));
	        HSSFCell ccl22 = r3.createCell(42);
	        ccl22.setCellValue("应职业卫生培训人数（人）");
	        ccl22.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 42, 3, (short) 42));
	        HSSFCell ccl23 = r3.createCell(43);
	        ccl23.setCellValue("实际职业卫生培训人数（人）");
	        ccl23.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 43, 3, (short) 43));
	        HSSFCell ccl24 = r3.createCell(44);
	        ccl24.setCellValue("职业卫生培训率（％）");
	        ccl24.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 44, 3, (short) 44));
	        HSSFCell ccl25 = r3.createCell(45);
	        ccl25.setCellValue("配备专职职业卫生管理人员企业数（个）");
	        ccl25.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 45, 3, (short) 45));
	        HSSFCell ccl26 = r3.createCell(46);
	        ccl26.setCellValue("专职职业卫生管理人员配备率（％）");
	        ccl26.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 46, 3, (short) 46));
	        HSSFCell ccl27= r3.createCell(47);
	        ccl27.setCellValue("配备兼职职业卫生管理人员企业数（个）");
	        ccl27.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 47, 3, (short) 47));
	        HSSFCell ccl28 = r3.createCell(48);
	        ccl28.setCellValue("兼职职业卫生管理人员配备率（％）");
	        ccl28.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 48, 3, (short) 48));
	        HSSFCell ccl29 = r3.createCell(49);
	        ccl29.setCellValue("专职职业卫生管理人数（人）");
	        ccl29.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 49, 3, (short) 49));
	        HSSFCell ccl30 = r3.createCell(50);
	        ccl30.setCellValue("万名工人专职职业卫生管理人数（人）");
	        ccl30.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 50, 3, (short) 50));
	        HSSFCell ccl31 = r3.createCell(51);
	        ccl31.setCellValue("兼职职业卫生管理人数（人）");
	        ccl31.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 51, 3, (short) 51));
	        HSSFCell ccl32 = r3.createCell(52);
	        ccl32.setCellValue("万名工人兼职职业卫生管理人数（人）");
	        ccl32.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 52, 3, (short) 52));
	        
	        HSSFCell ccl33 = r3.createCell(53);
	        ccl33.setCellValue("职业病危害因素检测企业数（个）");
	        ccl33.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 53, 3, (short) 53));
	        HSSFCell ccl34 = r3.createCell(54);
	        ccl34.setCellValue("检测率（％）");
	        ccl34.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 54, 3, (short) 54));
	        HSSFCell ccl35 = r3.createCell(55);
	        ccl35.setCellValue("检测点数（个）");
	        ccl35.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 55, 1, (short) 65));
	        HSSFCell ccl36 = r3.createCell(66);
	        ccl36.setCellValue("达标点数（个）");
	        ccl36.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 66, 1, (short) 76));
	        HSSFCell ccl37 = r3.createCell(77);
	        ccl37.setCellValue("达标率（％）");
	        ccl37.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 77, 1, (short) 87));
	        HSSFCell ccl38 = r3.createCell(88);
	        ccl38.setCellValue("应职业健康检查人数（人）");
	        ccl38.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 88, 2, (short) 91));
	        HSSFCell ccl39 = r3.createCell(92);
	        ccl39.setCellValue("实际职业健康检查人数（人）");
	        ccl39.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 92, 2, (short) 95));
	        HSSFCell ccl40 = r3.createCell(96);
	        ccl40.setCellValue("职业健康检查率（％）");
	        ccl40.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 96, 2, (short) 99));
	        HSSFCell ccl41 = r3.createCell(100);
	        ccl41.setCellValue("新增职业病病例数（人）");
	        ccl41.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 100, 2, (short) 104));
	        HSSFCell ccl42 = r3.createCell(105);
	        ccl42.setCellValue("累计职业病病例数（人）");
	        ccl42.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 105, 2, (short) 109));
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(23*20));
	        HSSFCell ccl43 = r4.createCell(3);
	        ccl43.setCellValue("合计");
	        ccl43.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 3, 3, (short) 3));
	        HSSFCell ccl44 = r4.createCell(4);
	        ccl44.setCellValue("粉尘");
	        ccl44.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 4, 2, (short) 6));
	        HSSFCell ccl45 = r4.createCell(7);
	        ccl45.setCellValue("化学毒物");
	        ccl45.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 7, 2, (short) 8));
	        HSSFCell ccl46 = r4.createCell(9);
	        ccl46.setCellValue("物理因素");
	        ccl46.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 9, 2, (short) 11));
	        HSSFCell ccl47 = r4.createCell(12);
	        ccl47.setCellValue("生物因素");
	        ccl47.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 12, 2, (short) 13));
	        HSSFCell ccl48 = r4.createCell(14);
	        ccl48.setCellValue("合计");
	        ccl48.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 14, 3, (short) 14));
	        HSSFCell ccl49 = r4.createCell(15);
	        ccl49.setCellValue("粉尘");
	        ccl49.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 15, 2, (short) 17));
	        HSSFCell ccl50 = r4.createCell(18);
	        ccl50.setCellValue("化学毒物");
	        ccl50.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 18, 2, (short) 19));
	        HSSFCell ccl51 = r4.createCell(20);
	        ccl51.setCellValue("物理因素");
	        ccl51.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 20, 2, (short) 22));
	        HSSFCell ccl52 = r4.createCell(23);
	        ccl52.setCellValue("生物因素");
	        ccl52.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 23, 2, (short) 24));
	        
	        
	        HSSFCell ccl53 = r4.createCell(55);
	        ccl53.setCellValue("合计");
	        ccl53.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 55, 3, (short) 55));
	        HSSFCell ccl54 = r4.createCell(56);
	        ccl54.setCellValue("粉尘");
	        ccl54.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 56, 2, (short) 58));
	        HSSFCell ccl55 = r4.createCell(59);
	        ccl55.setCellValue("化学毒物");
	        ccl55.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 59, 2, (short) 60));
	        HSSFCell ccl56 = r4.createCell(61);
	        ccl56.setCellValue("物理因素");
	        ccl56.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 61, 2, (short) 63));
	        HSSFCell ccl57 = r4.createCell(64);
	        ccl57.setCellValue("生物因素");
	        ccl57.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 64, 2, (short) 65));
	        
	        
	        HSSFCell ccl58 = r4.createCell(66);
	        ccl58.setCellValue("合计");
	        ccl58.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 66, 3, (short) 66));
	        HSSFCell ccl59 = r4.createCell(67);
	        ccl59.setCellValue("粉尘");
	        ccl59.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 67, 2, (short) 69));
	        HSSFCell ccl60 = r4.createCell(70);
	        ccl60.setCellValue("化学毒物");
	        ccl60.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 70, 2, (short) 71));
	        HSSFCell ccl61 = r4.createCell(72);
	        ccl61.setCellValue("物理因素");
	        ccl61.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 72, 2, (short) 74));
	        HSSFCell ccl62 = r4.createCell(75);
	        ccl62.setCellValue("生物因素");
	        ccl62.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 75, 2, (short) 76));
	        
	        
	        HSSFCell ccl63 = r4.createCell(77);
	        ccl63.setCellValue("合计");
	        ccl63.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 77, 3, (short) 77));
	        HSSFCell ccl64 = r4.createCell(78);
	        ccl64.setCellValue("粉尘");
	        ccl64.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 78, 2, (short) 80));
	        HSSFCell ccl65 = r4.createCell(81);
	        ccl65.setCellValue("化学毒物");
	        ccl65.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 81, 2, (short) 82));
	        HSSFCell ccl66 = r4.createCell(83);
	        ccl66.setCellValue("物理因素");
	        ccl66.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 83, 2, (short) 85));
	        HSSFCell ccl67 = r4.createCell(86);
	        ccl67.setCellValue("生物因素");
	        ccl67.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 86, 2, (short) 87));
	        
	        HSSFRow r5 = sheet.createRow(3);
	        r5.setHeight((short)(23*20));
	        HSSFCell ccl68 = r5.createCell(4);
	        ccl68.setCellValue("小计");
	        ccl68.setCellStyle(cs);
	        HSSFCell ccl69 = r5.createCell(5);
	        ccl69.setCellValue("矽尘");
	        ccl69.setCellStyle(cs);
	        HSSFCell ccl70 = r5.createCell(6);
	        ccl70.setCellValue("石棉尘");
	        ccl70.setCellStyle(cs);
	        HSSFCell ccl71 = r5.createCell(7);
	        ccl71.setCellValue("小计");
	        ccl71.setCellStyle(cs);
	        HSSFCell ccl72 = r5.createCell(8);
	        ccl72.setCellValue("高毒和极度、高度危害化学物质");
	        ccl72.setCellStyle(cs);
	        HSSFCell ccl73 = r5.createCell(9);
	        ccl73.setCellValue("小计");
	        ccl73.setCellStyle(cs);
	        HSSFCell ccl74 = r5.createCell(10);
	        ccl74.setCellValue("电离辐射");
	        ccl74.setCellStyle(cs);
	        HSSFCell ccl75 = r5.createCell(11);
	        ccl75.setCellValue("噪声");
	        ccl75.setCellStyle(cs);
	        HSSFCell ccl76 = r5.createCell(12);
	        ccl76.setCellValue("小计");
	        ccl76.setCellStyle(cs);
	        HSSFCell ccl77 = r5.createCell(13);
	        ccl77.setCellValue("炭疽杆菌");
	        ccl77.setCellStyle(cs);
	        
	        
	        HSSFCell ccl78 = r5.createCell(15);
	        ccl78.setCellValue("小计");
	        ccl78.setCellStyle(cs);
	        HSSFCell ccl79 = r5.createCell(16);
	        ccl79.setCellValue("矽尘");
	        ccl79.setCellStyle(cs);
	        HSSFCell ccl80 = r5.createCell(17);
	        ccl80.setCellValue("石棉尘");
	        ccl80.setCellStyle(cs);
	        HSSFCell ccl81 = r5.createCell(18);
	        ccl81.setCellValue("小计");
	        ccl81.setCellStyle(cs);
	        HSSFCell ccl82 = r5.createCell(19);
	        ccl82.setCellValue("高毒和极度、高度危害化学物质");
	        ccl82.setCellStyle(cs);
	        HSSFCell ccl83 = r5.createCell(20);
	        ccl83.setCellValue("小计");
	        ccl83.setCellStyle(cs);
	        HSSFCell ccl84 = r5.createCell(21);
	        ccl84.setCellValue("电离辐射");
	        ccl84.setCellStyle(cs);
	        HSSFCell ccl85 = r5.createCell(22);
	        ccl85.setCellValue("噪声");
	        ccl85.setCellStyle(cs);
	        HSSFCell ccl86 = r5.createCell(23);
	        ccl86.setCellValue("小计");
	        ccl86.setCellStyle(cs);
	        HSSFCell ccl87 = r5.createCell(24);
	        ccl87.setCellValue("炭疽杆菌");
	        ccl87.setCellStyle(cs);
	        
	        
	        HSSFCell ccl88 = r5.createCell(56);
	        ccl88.setCellValue("小计");
	        ccl88.setCellStyle(cs);
	        HSSFCell ccl89 = r5.createCell(57);
	        ccl89.setCellValue("矽尘");
	        ccl89.setCellStyle(cs);
	        HSSFCell ccl90 = r5.createCell(58);
	        ccl90.setCellValue("石棉尘");
	        ccl90.setCellStyle(cs);
	        HSSFCell ccl91 = r5.createCell(59);
	        ccl91.setCellValue("小计");
	        ccl91.setCellStyle(cs);
	        HSSFCell ccl92 = r5.createCell(60);
	        ccl92.setCellValue("高毒和极度、高度危害化学物质");
	        ccl92.setCellStyle(cs);
	        HSSFCell ccl93 = r5.createCell(61);
	        ccl93.setCellValue("小计");
	        ccl93.setCellStyle(cs);
	        HSSFCell ccl94 = r5.createCell(62);
	        ccl94.setCellValue("电离辐射");
	        ccl94.setCellStyle(cs);
	        HSSFCell ccl95 = r5.createCell(63);
	        ccl95.setCellValue("噪声");
	        ccl95.setCellStyle(cs);
	        HSSFCell ccl96 = r5.createCell(64);
	        ccl96.setCellValue("小计");
	        ccl96.setCellStyle(cs);
	        HSSFCell ccl97 = r5.createCell(65);
	        ccl97.setCellValue("炭疽杆菌");
	        ccl97.setCellStyle(cs);
	        
	        
	        HSSFCell ccl98 = r5.createCell(67);
	        ccl98.setCellValue("小计");
	        ccl98.setCellStyle(cs);
	        HSSFCell ccl99 = r5.createCell(68);
	        ccl99.setCellValue("矽尘");
	        ccl99.setCellStyle(cs);
	        HSSFCell ccl100 = r5.createCell(69);
	        ccl100.setCellValue("石棉尘");
	        ccl70.setCellStyle(cs);
	        HSSFCell ccl101 = r5.createCell(70);
	        ccl101.setCellValue("小计");
	        ccl101.setCellStyle(cs);
	        HSSFCell ccl102 = r5.createCell(71);
	        ccl102.setCellValue("高毒和极度、高度危害化学物质");
	        ccl102.setCellStyle(cs);
	        HSSFCell ccl103 = r5.createCell(72);
	        ccl103.setCellValue("小计");
	        ccl103.setCellStyle(cs);
	        HSSFCell ccl104 = r5.createCell(73);
	        ccl104.setCellValue("电离辐射");
	        ccl104.setCellStyle(cs);
	        HSSFCell ccl105 = r5.createCell(74);
	        ccl105.setCellValue("噪声");
	        ccl105.setCellStyle(cs);
	        HSSFCell ccl106 = r5.createCell(75);
	        ccl106.setCellValue("小计");
	        ccl106.setCellStyle(cs);
	        HSSFCell ccl107 = r5.createCell(76);
	        ccl107.setCellValue("炭疽杆菌");
	        ccl107.setCellStyle(cs);
	        
	        
	        HSSFCell ccl108 = r5.createCell(78);
	        ccl108.setCellValue("小计");
	        ccl108.setCellStyle(cs);
	        HSSFCell ccl109 = r5.createCell(79);
	        ccl109.setCellValue("矽尘");
	        ccl109.setCellStyle(cs);
	        HSSFCell ccl110 = r5.createCell(80);
	        ccl110.setCellValue("石棉尘");
	        ccl110.setCellStyle(cs);
	        HSSFCell ccl111 = r5.createCell(81);
	        ccl111.setCellValue("小计");
	        ccl111.setCellStyle(cs);
	        HSSFCell ccl112 = r5.createCell(82);
	        ccl112.setCellValue("高毒和极度、高度危害化学物质");
	        ccl112.setCellStyle(cs);
	        HSSFCell ccl113 = r5.createCell(83);
	        ccl113.setCellValue("小计");
	        ccl113.setCellStyle(cs);
	        HSSFCell ccl114 = r5.createCell(84);
	        ccl114.setCellValue("电离辐射");
	        ccl114.setCellStyle(cs);
	        HSSFCell ccl115 = r5.createCell(85);
	        ccl115.setCellValue("噪声");
	        ccl115.setCellStyle(cs);
	        HSSFCell ccl116 = r5.createCell(86);
	        ccl116.setCellValue("小计");
	        ccl116.setCellStyle(cs);
	        HSSFCell ccl117 = r5.createCell(87);
	        ccl117.setCellValue("炭疽杆菌");
	        ccl117.setCellStyle(cs);
	        
	        
	        HSSFCell ccl118 = r5.createCell(88);
	        ccl118.setCellValue("合计");
	        ccl118.setCellStyle(cs);
	        HSSFCell ccl119 = r5.createCell(89);
	        ccl119.setCellValue("岗前");
	        ccl119.setCellStyle(cs);
	        HSSFCell ccl120 = r5.createCell(90);
	        ccl120.setCellValue("在岗");
	        ccl120.setCellStyle(cs);
	        HSSFCell ccl121 = r5.createCell(91);
	        ccl121.setCellValue("离岗");
	        ccl121.setCellStyle(cs);
	        
	        HSSFCell ccl122 = r5.createCell(92);
	        ccl122.setCellValue("合计");
	        ccl122.setCellStyle(cs);
	        HSSFCell ccl123 = r5.createCell(93);
	        ccl123.setCellValue("岗前");
	        ccl123.setCellStyle(cs);
	        HSSFCell ccl124 = r5.createCell(94);
	        ccl124.setCellValue("在岗");
	        ccl124.setCellStyle(cs);
	        HSSFCell ccl125 = r5.createCell(95);
	        ccl125.setCellValue("离岗");
	        ccl125.setCellStyle(cs);
	        
	        HSSFCell ccl126 = r5.createCell(96);
	        ccl126.setCellValue("合计");
	        ccl126.setCellStyle(cs);
	        HSSFCell ccl127 = r5.createCell(97);
	        ccl127.setCellValue("岗前");
	        ccl127.setCellStyle(cs);
	        HSSFCell ccl128 = r5.createCell(98);
	        ccl128.setCellValue("在岗");
	        ccl128.setCellStyle(cs);
	        HSSFCell ccl129 = r5.createCell(99);
	        ccl129.setCellValue("离岗");
	        ccl129.setCellStyle(cs);
	        
	        
	        HSSFCell ccl130 = r5.createCell(100);
	        ccl130.setCellValue("合计");
	        ccl130.setCellStyle(cs);
	        HSSFCell ccl131 = r5.createCell(101);
	        ccl131.setCellValue("尘肺");
	        ccl131.setCellStyle(cs);
	        HSSFCell ccl132 = r5.createCell(102);
	        ccl132.setCellValue("职业中毒");
	        ccl132.setCellStyle(cs);
	        HSSFCell ccl133 = r5.createCell(103);
	        ccl133.setCellValue("噪声聋");
	        ccl133.setCellStyle(cs);
	        HSSFCell ccl134 = r5.createCell(104);
	        ccl134.setCellValue("职业性皮肤病");
	        ccl134.setCellStyle(cs);
	        HSSFCell ccl135 = r5.createCell(105);
	        ccl135.setCellValue("合计");
	        ccl135.setCellStyle(cs);
	        HSSFCell ccl136 = r5.createCell(106);
	        ccl136.setCellValue("尘肺");
	        ccl136.setCellStyle(cs);
	        HSSFCell ccl137 = r5.createCell(107);
	        ccl137.setCellValue("职业中毒");
	        ccl137.setCellStyle(cs);
	        HSSFCell ccl138 = r5.createCell(108);
	        ccl138.setCellValue("噪声聋");
	        ccl138.setCellStyle(cs);
	        HSSFCell ccl139 = r5.createCell(109);
	        ccl139.setCellValue("职业性皮肤病");
	        ccl139.setCellStyle(cs);
	        
	        
	        HSSFCellStyle c = wb.createCellStyle();
		    c.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    c.setWrapText(true);
		    c.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fon = wb.createFont();
	        fon.setFontHeight((short) (15*15));
	        c.setFont(fon);
	        
	        
			List<ZywsqkAll> list = zywsqkService.getZywsqkAllListByMap(paraMap);
			ZywsqkAll z = zywsqkService.getZywsqkAllByMap(paraMap);
			
			int num = 4;
			for(ZywsqkAll zywsqkAll:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(zywsqkAll.getData0());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(zywsqkAll.getData1());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(zywsqkAll.getData2());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        double d3 = Double.valueOf(zywsqkAll.getData4()) + Double.valueOf(zywsqkAll.getData7()) + Double.valueOf(zywsqkAll.getData9()) + Double.valueOf(zywsqkAll.getData12());
		        ce3.setCellValue(new BigDecimal(d3).setScale(0,BigDecimal.ROUND_HALF_UP)+"");
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(zywsqkAll.getData4());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(zywsqkAll.getData5());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(zywsqkAll.getData6());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(zywsqkAll.getData7());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(zywsqkAll.getData8());
		        ce8.setCellStyle(c);
		        HSSFCell ce9 = ro.createCell(9);
		        ce9.setCellValue(zywsqkAll.getData9());
		        ce9.setCellStyle(c);
		        
		        HSSFCell ce10 = ro.createCell(10);
				ce10.setCellValue(zywsqkAll.getData0());
				ce10.setCellStyle(c);
		        HSSFCell ce11 = ro.createCell(11);
		        ce11.setCellValue(zywsqkAll.getData1());
		        ce11.setCellStyle(c);
		        HSSFCell ce12 = ro.createCell(12);
		        ce12.setCellValue(zywsqkAll.getData2());
		        ce12.setCellStyle(c);
		        HSSFCell ce13 = ro.createCell(13);
		        ce13.setCellValue(zywsqkAll.getData13());
		        ce13.setCellStyle(c);
		        double d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d34,d36;
		        if(zywsqkAll.getData2() == null || "".equals(zywsqkAll.getData2()) || "0".equals(zywsqkAll.getData2()))
		        {
		        	d14=d15=d16=d17=d18=d19=d20=d21=d22=d23=d24=d34=d36=0.00;
		        }
		        else
		        {
		        	d14 = d3/Double.valueOf(zywsqkAll.getData2());
		        	d15 = Double.valueOf(zywsqkAll.getData4())/Double.valueOf(zywsqkAll.getData2());
		        	d16 = Double.valueOf(zywsqkAll.getData5())/Double.valueOf(zywsqkAll.getData2());
		        	d17 = Double.valueOf(zywsqkAll.getData6())/Double.valueOf(zywsqkAll.getData2());
		        	d18 = Double.valueOf(zywsqkAll.getData7())/Double.valueOf(zywsqkAll.getData2());
		        	d19 = Double.valueOf(zywsqkAll.getData8())/Double.valueOf(zywsqkAll.getData2());
		        	d20 = Double.valueOf(zywsqkAll.getData9())/Double.valueOf(zywsqkAll.getData2());
		        	d21 = Double.valueOf(zywsqkAll.getData10())/Double.valueOf(zywsqkAll.getData2());
		        	d22 = Double.valueOf(zywsqkAll.getData11())/Double.valueOf(zywsqkAll.getData2());
		        	d23 = Double.valueOf(zywsqkAll.getData12())/Double.valueOf(zywsqkAll.getData2());
		        	d24 = Double.valueOf(zywsqkAll.getData13())/Double.valueOf(zywsqkAll.getData2());
		        	d34 = Double.valueOf(zywsqkAll.getData33())/Double.valueOf(zywsqkAll.getData2()); ;
		        	d36 = Double.valueOf(zywsqkAll.getData35())/Double.valueOf(zywsqkAll.getData2()); ;
		        }
		        HSSFCell ce14 = ro.createCell(14);
		        ce14.setCellValue(new BigDecimal(d14).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce14.setCellStyle(c);
		        HSSFCell ce15 = ro.createCell(15);
		        ce15.setCellValue(new BigDecimal(d15).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce15.setCellStyle(c);
		        HSSFCell ce16 = ro.createCell(16);
		        ce16.setCellValue(new BigDecimal(d16).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce16.setCellStyle(c);
		        HSSFCell ce17 = ro.createCell(17);
		        ce17.setCellValue(new BigDecimal(d17).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce17.setCellStyle(c);
		        HSSFCell ce18 = ro.createCell(18);
		        ce18.setCellValue(new BigDecimal(d18).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce18.setCellStyle(c);
		        HSSFCell ce19 = ro.createCell(19);
		        ce19.setCellValue(new BigDecimal(d19).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce19.setCellStyle(c);
		        
		        HSSFCell ce20 = ro.createCell(20);
				ce20.setCellValue(new BigDecimal(d20).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
				ce20.setCellStyle(c);
		        HSSFCell ce21 = ro.createCell(21);
		        ce21.setCellValue(new BigDecimal(d21).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce21.setCellStyle(c);
		        HSSFCell ce22 = ro.createCell(22);
		        ce22.setCellValue(new BigDecimal(d22).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce22.setCellStyle(c);
		        HSSFCell ce23 = ro.createCell(23);
		        ce23.setCellValue(new BigDecimal(d23).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce23.setCellStyle(c);
		        HSSFCell ce24 = ro.createCell(24);
		        ce24.setCellValue(new BigDecimal(d24).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce24.setCellStyle(c);
		        HSSFCell ce25 = ro.createCell(25);
		        ce25.setCellValue(zywsqkAll.getData25());
		        ce25.setCellStyle(c);
		        double d26,d41,d46,d48,d54;
		        if(zywsqkAll.getData1() == null || "".equals(zywsqkAll.getData1()) || "0".equals(zywsqkAll.getData1()))
		        {
		        	d26=d41=d46=d48=d54=0.00;
		        }
		        else
		        {
		        	d26 = Double.valueOf(zywsqkAll.getData25())/Double.valueOf(zywsqkAll.getData1());
		        	d41 = Double.valueOf(zywsqkAll.getData40())/Double.valueOf(zywsqkAll.getData1());
		        	d46 = Double.valueOf(zywsqkAll.getData45())/Double.valueOf(zywsqkAll.getData1());
		        	d48 = Double.valueOf(zywsqkAll.getData47())/Double.valueOf(zywsqkAll.getData1());
		        	d54 = Double.valueOf(zywsqkAll.getData53())/Double.valueOf(zywsqkAll.getData1());
		        }
		        HSSFCell ce26 = ro.createCell(26);
		        ce26.setCellValue(new BigDecimal(d26).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce26.setCellStyle(c);
		        HSSFCell ce27 = ro.createCell(27);
		        ce27.setCellValue(zywsqkAll.getData27());
		        ce27.setCellStyle(c);
		        HSSFCell ce28 = ro.createCell(28);
		        ce28.setCellValue(zywsqkAll.getData28());
		        ce28.setCellStyle(c);
		        double d29;
		        if(zywsqkAll.getData27() == null || "".equals(zywsqkAll.getData27()) || "0".equals(zywsqkAll.getData27()))
		        {
		        	d29 = 0.00;
		        }
		        else
		        {
		        	d29 = Double.valueOf(zywsqkAll.getData28())/Double.valueOf(zywsqkAll.getData27());
		        }
		        HSSFCell ce29 = ro.createCell(29);
		        ce29.setCellValue(new BigDecimal(d29).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce29.setCellStyle(c);
		        
		        HSSFCell ce30 = ro.createCell(30);
				ce30.setCellValue(zywsqkAll.getData30());
				ce30.setCellStyle(c);
		        HSSFCell ce31 = ro.createCell(31);
		        ce31.setCellValue(zywsqkAll.getData31());
		        ce31.setCellStyle(c);
		        double d32;
		        if(zywsqkAll.getData30() == null || "".equals(zywsqkAll.getData30()) || "0".equals(zywsqkAll.getData30()))
		        {
		        	d32 = 0.00;
		        }
		        else
		        {
		        	d32 = Double.valueOf(zywsqkAll.getData31())/Double.valueOf(zywsqkAll.getData30());
		        }
		        HSSFCell ce32 = ro.createCell(32);
		        ce32.setCellValue(new BigDecimal(d32).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce32.setCellStyle(c);
		        HSSFCell ce33 = ro.createCell(33);
		        ce33.setCellValue(zywsqkAll.getData33());
		        ce33.setCellStyle(c);
		        HSSFCell ce34 = ro.createCell(34);
		        ce34.setCellValue(new BigDecimal(d34).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce34.setCellStyle(c);
		        HSSFCell ce35 = ro.createCell(35);
		        ce35.setCellValue(zywsqkAll.getData35());
		        ce35.setCellStyle(c);
		        HSSFCell ce36 = ro.createCell(36);
		        ce36.setCellValue(new BigDecimal(d36).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce36.setCellStyle(c);
		        HSSFCell ce37 = ro.createCell(37);
		        ce37.setCellValue(zywsqkAll.getData37());
		        ce37.setCellStyle(c);
		        HSSFCell ce38 = ro.createCell(38);
		        ce38.setCellValue(zywsqkAll.getData38());
		        ce38.setCellStyle(c);
		        double d39;
		        if(zywsqkAll.getData37() == null || "".equals(zywsqkAll.getData37()) || "0".equals(zywsqkAll.getData37()))
		        {
		        	d39 = 0.00;
		        }
		        else
		        {
		        	d39 = Double.valueOf(zywsqkAll.getData38())/Double.valueOf(zywsqkAll.getData37());
		        }
		        HSSFCell ce39 = ro.createCell(39);
		        ce39.setCellValue(new BigDecimal(d39).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce39.setCellStyle(c);
		        
		        HSSFCell ce40 = ro.createCell(40);
				ce40.setCellValue(zywsqkAll.getData40());
				ce40.setCellStyle(c);
		        HSSFCell ce41 = ro.createCell(41);
		        ce41.setCellValue(new BigDecimal(d41).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce41.setCellStyle(c);
		        HSSFCell ce42 = ro.createCell(42);
		        ce42.setCellValue(zywsqkAll.getData42());
		        ce42.setCellStyle(c);
		        HSSFCell ce43 = ro.createCell(43);
		        ce43.setCellValue(zywsqkAll.getData43());
		        ce43.setCellStyle(c);
		        double d44;
		        if(zywsqkAll.getData42() == null || "".equals(zywsqkAll.getData42()) || "0".equals(zywsqkAll.getData42()))
		        {
		        	d44 = 0.00;
		        }
		        else
		        {
		        	d44 = Double.valueOf(zywsqkAll.getData43())/Double.valueOf(zywsqkAll.getData42());
		        }
		        HSSFCell ce44 = ro.createCell(44);
		        ce44.setCellValue(new BigDecimal(d44).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce44.setCellStyle(c);
		        HSSFCell ce45 = ro.createCell(45);
		        ce45.setCellValue(zywsqkAll.getData45());
		        ce45.setCellStyle(c);
		        HSSFCell ce46 = ro.createCell(46);
		        ce46.setCellValue(new BigDecimal(d46).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce46.setCellStyle(c);
		        HSSFCell ce47 = ro.createCell(47);
		        ce47.setCellValue(zywsqkAll.getData47());
		        ce47.setCellStyle(c);
		        HSSFCell ce48 = ro.createCell(48);
		        ce48.setCellValue(new BigDecimal(d48).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce48.setCellStyle(c);
		        HSSFCell ce49 = ro.createCell(49);
		        ce49.setCellValue(zywsqkAll.getData49());
		        ce49.setCellStyle(c);
		        
		        HSSFCell ce50 = ro.createCell(50);
				ce50.setCellValue(zywsqkAll.getData50());
				ce50.setCellStyle(c);
		        HSSFCell ce51 = ro.createCell(51);
		        ce51.setCellValue(zywsqkAll.getData51());
		        ce51.setCellStyle(c);
		        HSSFCell ce52 = ro.createCell(52);
		        ce52.setCellValue(zywsqkAll.getData52());
		        ce52.setCellStyle(c);
		        HSSFCell ce53 = ro.createCell(53);
		        ce53.setCellValue(zywsqkAll.getData53());
		        ce53.setCellStyle(c);
		        HSSFCell ce54 = ro.createCell(54);
		        ce54.setCellValue(new BigDecimal(d54).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce54.setCellStyle(c);
		        double d55 = Double.valueOf(zywsqkAll.getData56()) + Double.valueOf(zywsqkAll.getData59()) + Double.valueOf(zywsqkAll.getData61()) + Double.valueOf(zywsqkAll.getData64());
		        HSSFCell ce55 = ro.createCell(55);
		        ce55.setCellValue(new BigDecimal(d55).setScale(0,BigDecimal.ROUND_HALF_UP)+"");
		        ce55.setCellStyle(c);
		        HSSFCell ce56 = ro.createCell(56);
		        ce56.setCellValue(zywsqkAll.getData56());
		        ce56.setCellStyle(c);
		        HSSFCell ce57 = ro.createCell(57);
		        ce57.setCellValue(zywsqkAll.getData57());
		        ce57.setCellStyle(c);
		        HSSFCell ce58 = ro.createCell(58);
		        ce58.setCellValue(zywsqkAll.getData58());
		        ce58.setCellStyle(c);
		        HSSFCell ce59 = ro.createCell(59);
		        ce59.setCellValue(zywsqkAll.getData59());
		        ce59.setCellStyle(c);
		        
		        HSSFCell ce60 = ro.createCell(60);
				ce60.setCellValue(zywsqkAll.getData60());
				ce60.setCellStyle(c);
		        HSSFCell ce61 = ro.createCell(61);
		        ce61.setCellValue(zywsqkAll.getData61());
		        ce61.setCellStyle(c);
		        HSSFCell ce62 = ro.createCell(62);
		        ce62.setCellValue(zywsqkAll.getData62());
		        ce62.setCellStyle(c);
		        HSSFCell ce63 = ro.createCell(63);
		        ce63.setCellValue(zywsqkAll.getData63());
		        ce63.setCellStyle(c);
		        HSSFCell ce64 = ro.createCell(64);
		        ce64.setCellValue(zywsqkAll.getData64());
		        ce64.setCellStyle(c);
		        HSSFCell ce65 = ro.createCell(65);
		        ce65.setCellValue(zywsqkAll.getData65());
		        ce65.setCellStyle(c);
		        double d66 = Double.valueOf(zywsqkAll.getData67()) + Double.valueOf(zywsqkAll.getData70()) + Double.valueOf(zywsqkAll.getData72()) + Double.valueOf(zywsqkAll.getData75());
		        HSSFCell ce66 = ro.createCell(66);
		        ce66.setCellValue(new BigDecimal(d66).setScale(0,BigDecimal.ROUND_HALF_UP)+"");
		        ce66.setCellStyle(c);
		        HSSFCell ce67 = ro.createCell(67);
		        ce67.setCellValue(zywsqkAll.getData67());
		        ce67.setCellStyle(c);
		        HSSFCell ce68 = ro.createCell(68);
		        ce68.setCellValue(zywsqkAll.getData68());
		        ce68.setCellStyle(c);
		        HSSFCell ce69 = ro.createCell(69);
		        ce69.setCellValue(zywsqkAll.getData69());
		        ce69.setCellStyle(c);
		        
		        HSSFCell ce70 = ro.createCell(70);
				ce70.setCellValue(zywsqkAll.getData70());
				ce70.setCellStyle(c);
		        HSSFCell ce71 = ro.createCell(71);
		        ce71.setCellValue(zywsqkAll.getData71());
		        ce71.setCellStyle(c);
		        HSSFCell ce72 = ro.createCell(72);
		        ce72.setCellValue(zywsqkAll.getData72());
		        ce72.setCellStyle(c);
		        HSSFCell ce73 = ro.createCell(73);
		        ce73.setCellValue(zywsqkAll.getData73());
		        ce73.setCellStyle(c);
		        HSSFCell ce74 = ro.createCell(74);
		        ce74.setCellValue(zywsqkAll.getData74());
		        ce74.setCellStyle(c);
		        HSSFCell ce75 = ro.createCell(75);
		        ce75.setCellValue(zywsqkAll.getData75());
		        ce75.setCellStyle(c);
		        HSSFCell ce76 = ro.createCell(76);
		        ce76.setCellValue(zywsqkAll.getData76());
		        ce76.setCellStyle(c);
		        double d77;
		        if(d55 == 0.00)
		        {
		        	d77=0.00;
		        }
		        else
		        {
		        	d77 = d66/d55;
		        }
		        HSSFCell ce77 = ro.createCell(77);
		        ce77.setCellValue(new BigDecimal(d77).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce77.setCellStyle(c);
		        double d78;
		        if(zywsqkAll.getData56() == null || "".equals(zywsqkAll.getData56()) || "0".equals(zywsqkAll.getData56()))
		        {
		        	d78 = 0.00;
		        }
		        else
		        {
		        	d78 = Double.valueOf(zywsqkAll.getData67())/Double.valueOf(zywsqkAll.getData56());
		        }
		        HSSFCell ce78 = ro.createCell(78);
		        ce78.setCellValue(new BigDecimal(d78).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce78.setCellStyle(c);
		        double d79;
		        if(zywsqkAll.getData57() == null || "".equals(zywsqkAll.getData57()) || "0".equals(zywsqkAll.getData57()))
		        {
		        	d79 = 0.00;
		        }
		        else
		        {
		        	d79 = Double.valueOf(zywsqkAll.getData68())/Double.valueOf(zywsqkAll.getData57());
		        }
		        HSSFCell ce79 = ro.createCell(79);
		        ce79.setCellValue(new BigDecimal(d79).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce79.setCellStyle(c);
		        double d80;
		        if(zywsqkAll.getData58() == null || "".equals(zywsqkAll.getData58()) || "0".equals(zywsqkAll.getData58()))
		        {
		        	d80 = 0.00;
		        }
		        else
		        {
		        	d80 = Double.valueOf(zywsqkAll.getData69())/Double.valueOf(zywsqkAll.getData58());
		        }
		        HSSFCell ce80 = ro.createCell(80);
				ce80.setCellValue(new BigDecimal(d80).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
				ce80.setCellStyle(c);
				double d81;
		        if(zywsqkAll.getData59() == null || "".equals(zywsqkAll.getData59()) || "0".equals(zywsqkAll.getData59()))
		        {
		        	d81 = 0.00;
		        }
		        else
		        {
		        	d81 = Double.valueOf(zywsqkAll.getData70())/Double.valueOf(zywsqkAll.getData59());
		        }
		        HSSFCell ce81 = ro.createCell(81);
		        ce81.setCellValue(new BigDecimal(d81).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce81.setCellStyle(c);
		        double d82;
		        if(zywsqkAll.getData60() == null || "".equals(zywsqkAll.getData60()) || "0".equals(zywsqkAll.getData60()))
		        {
		        	d82 = 0.00;
		        }
		        else
		        {
		        	d82 = Double.valueOf(zywsqkAll.getData71())/Double.valueOf(zywsqkAll.getData60());
		        }
		        HSSFCell ce82 = ro.createCell(82);
		        ce82.setCellValue(new BigDecimal(d82).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce82.setCellStyle(c);
		        double d83;
		        if(zywsqkAll.getData61() == null || "".equals(zywsqkAll.getData61()) || "0".equals(zywsqkAll.getData61()))
		        {
		        	d83 = 0.00;
		        }
		        else
		        {
		        	d83 = Double.valueOf(zywsqkAll.getData72())/Double.valueOf(zywsqkAll.getData61());
		        }
		        HSSFCell ce83 = ro.createCell(83);
		        ce83.setCellValue(new BigDecimal(d83).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce83.setCellStyle(c);
		        double d84;
		        if(zywsqkAll.getData62() == null || "".equals(zywsqkAll.getData62()) || "0".equals(zywsqkAll.getData62()))
		        {
		        	d84 = 0.00;
		        }
		        else
		        {
		        	d84 = Double.valueOf(zywsqkAll.getData73())/Double.valueOf(zywsqkAll.getData62());
		        }
		        HSSFCell ce84 = ro.createCell(84);
		        ce84.setCellValue(new BigDecimal(d84).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce84.setCellStyle(c);
		        double d85;
		        if(zywsqkAll.getData63() == null || "".equals(zywsqkAll.getData63()) || "0".equals(zywsqkAll.getData63()))
		        {
		        	d85 = 0.00;
		        }
		        else
		        {
		        	d85 = Double.valueOf(zywsqkAll.getData74())/Double.valueOf(zywsqkAll.getData63());
		        }
		        HSSFCell ce85 = ro.createCell(85);
		        ce85.setCellValue(new BigDecimal(d85).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce85.setCellStyle(c);
		        double d86;
		        if(zywsqkAll.getData64() == null || "".equals(zywsqkAll.getData64()) || "0".equals(zywsqkAll.getData64()))
		        {
		        	d86 = 0.00;
		        }
		        else
		        {
		        	d86 = Double.valueOf(zywsqkAll.getData75())/Double.valueOf(zywsqkAll.getData64());
		        }
		        HSSFCell ce86 = ro.createCell(86);
		        ce86.setCellValue(new BigDecimal(d86).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce86.setCellStyle(c);
		        double d87;
		        if(zywsqkAll.getData65() == null || "".equals(zywsqkAll.getData65()) || "0".equals(zywsqkAll.getData65()))
		        {
		        	d87 = 0.00;
		        }
		        else
		        {
		        	d87 = Double.valueOf(zywsqkAll.getData76())/Double.valueOf(zywsqkAll.getData65());
		        }
		        HSSFCell ce87 = ro.createCell(87);
		        ce87.setCellValue(new BigDecimal(d87).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce87.setCellStyle(c);
		        HSSFCell ce88 = ro.createCell(88);
		        ce88.setCellValue(zywsqkAll.getData88());
		        ce88.setCellStyle(c);
		        HSSFCell ce89 = ro.createCell(89);
		        ce89.setCellValue(zywsqkAll.getData89());
		        ce89.setCellStyle(c);
		        
		        HSSFCell ce90 = ro.createCell(90);
				ce90.setCellValue(zywsqkAll.getData90());
				ce90.setCellStyle(c);
		        HSSFCell ce91 = ro.createCell(91);
		        ce91.setCellValue(zywsqkAll.getData91());
		        ce91.setCellStyle(c);
		        HSSFCell ce92 = ro.createCell(92);
		        ce92.setCellValue(zywsqkAll.getData92());
		        ce92.setCellStyle(c);
		        HSSFCell ce93 = ro.createCell(93);
		        ce93.setCellValue(zywsqkAll.getData93());
		        ce93.setCellStyle(c);
		        HSSFCell ce94 = ro.createCell(94);
		        ce94.setCellValue(zywsqkAll.getData94());
		        ce94.setCellStyle(c);
		        HSSFCell ce95 = ro.createCell(95);
		        ce95.setCellValue(zywsqkAll.getData95());
		        ce95.setCellStyle(c);
		        double d96;
		        if(zywsqkAll.getData88() == null || "".equals(zywsqkAll.getData88()) || "0".equals(zywsqkAll.getData88()))
		        {
		        	d96 = 0.00;
		        }
		        else
		        {
		        	d96 = Double.valueOf(zywsqkAll.getData92())/Double.valueOf(zywsqkAll.getData88());
		        }
		        HSSFCell ce96 = ro.createCell(96);
		        ce96.setCellValue(new BigDecimal(d96).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce96.setCellStyle(c);
		        double d97;
		        if(zywsqkAll.getData89() == null || "".equals(zywsqkAll.getData89()) || "0".equals(zywsqkAll.getData89()))
		        {
		        	d97 = 0.00;
		        }
		        else
		        {
		        	d97 = Double.valueOf(zywsqkAll.getData93())/Double.valueOf(zywsqkAll.getData89());
		        }
		        HSSFCell ce97 = ro.createCell(97);
		        ce97.setCellValue(new BigDecimal(d97).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce97.setCellStyle(c);
		        double d98;
		        if(zywsqkAll.getData90() == null || "".equals(zywsqkAll.getData90()) || "0".equals(zywsqkAll.getData90()))
		        {
		        	d98 = 0.00;
		        }
		        else
		        {
		        	d98 = Double.valueOf(zywsqkAll.getData94())/Double.valueOf(zywsqkAll.getData90());
		        }
		        HSSFCell ce98 = ro.createCell(98);
		        ce98.setCellValue(new BigDecimal(d98).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce8.setCellStyle(c);
		        double d99;
		        if(zywsqkAll.getData91() == null || "".equals(zywsqkAll.getData91()) || "0".equals(zywsqkAll.getData91()))
		        {
		        	d99 = 0.00;
		        }
		        else
		        {
		        	d99 = Double.valueOf(zywsqkAll.getData95())/Double.valueOf(zywsqkAll.getData91());
		        }
		        HSSFCell ce99 = ro.createCell(99);
		        ce99.setCellValue(new BigDecimal(d99).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
		        ce99.setCellStyle(c);
		        
		        HSSFCell ce100 = ro.createCell(100);
				ce100.setCellValue(zywsqkAll.getData100());
				ce100.setCellStyle(c);
		        HSSFCell ce101 = ro.createCell(101);
		        ce101.setCellValue(zywsqkAll.getData101());
		        ce101.setCellStyle(c);
		        HSSFCell ce102 = ro.createCell(102);
		        ce102.setCellValue(zywsqkAll.getData102());
		        ce102.setCellStyle(c);
		        HSSFCell ce103 = ro.createCell(103);
		        ce103.setCellValue(zywsqkAll.getData103());
		        ce103.setCellStyle(c);
		        HSSFCell ce104 = ro.createCell(104);
		        ce104.setCellValue(zywsqkAll.getData104());
		        ce104.setCellStyle(c);
		        HSSFCell ce105 = ro.createCell(105);
		        ce105.setCellValue(zywsqkAll.getData105());
		        ce105.setCellStyle(c);
		        HSSFCell ce106 = ro.createCell(106);
		        ce106.setCellValue(zywsqkAll.getData106());
		        ce106.setCellStyle(c);
		        HSSFCell ce107 = ro.createCell(107);
		        ce107.setCellValue(zywsqkAll.getData107());
		        ce107.setCellStyle(c);
		        HSSFCell ce108 = ro.createCell(108);
		        ce108.setCellValue(zywsqkAll.getData108());
		        ce108.setCellStyle(c);
		        HSSFCell ce109 = ro.createCell(109);
		        ce109.setCellValue(zywsqkAll.getData109());
		        ce109.setCellStyle(c);
		        num++;
			}
			
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
	        HSSFCell ce1 = ro.createCell(1);
	        ce1.setCellValue(z.getData1());
	        ce1.setCellStyle(c);
	        HSSFCell ce2 = ro.createCell(2);
	        ce2.setCellValue(z.getData2());
	        ce2.setCellStyle(c);
	        HSSFCell ce3 = ro.createCell(3);
	        double d3 = Double.valueOf(z.getData4()) + Double.valueOf(z.getData7()) + Double.valueOf(z.getData9()) + Double.valueOf(z.getData12());
	        ce3.setCellValue(new BigDecimal(d3).setScale(0,BigDecimal.ROUND_HALF_UP)+"");
	        ce3.setCellStyle(c);
	        HSSFCell ce4 = ro.createCell(4);
	        ce4.setCellValue(z.getData4());
	        ce4.setCellStyle(c);
	        HSSFCell ce5 = ro.createCell(5);
	        ce5.setCellValue(z.getData5());
	        ce5.setCellStyle(c);
	        HSSFCell ce6 = ro.createCell(6);
	        ce6.setCellValue(z.getData6());
	        ce6.setCellStyle(c);
	        HSSFCell ce7 = ro.createCell(7);
	        ce7.setCellValue(z.getData7());
	        ce7.setCellStyle(c);
	        HSSFCell ce8 = ro.createCell(8);
	        ce8.setCellValue(z.getData8());
	        ce8.setCellStyle(c);
	        HSSFCell ce9 = ro.createCell(9);
	        ce9.setCellValue(z.getData9());
	        ce9.setCellStyle(c);
	        
	        HSSFCell ce10 = ro.createCell(10);
			ce10.setCellValue(z.getData0());
			ce10.setCellStyle(c);
	        HSSFCell ce11 = ro.createCell(11);
	        ce11.setCellValue(z.getData1());
	        ce11.setCellStyle(c);
	        HSSFCell ce12 = ro.createCell(12);
	        ce12.setCellValue(z.getData2());
	        ce12.setCellStyle(c);
	        HSSFCell ce13 = ro.createCell(13);
	        ce13.setCellValue(z.getData13());
	        ce13.setCellStyle(c);
	        double d14,d15,d16,d17,d18,d19,d20,d21,d22,d23,d24,d34,d36;
	        if(z.getData2() == null || "".equals(z.getData2()) || "0".equals(z.getData2()))
	        {
	        	d14=d15=d16=d17=d18=d19=d20=d21=d22=d23=d24=d34=d36=0.00;
	        }
	        else
	        {
	        	d14 = d3/Double.valueOf(z.getData2());
	        	d15 = Double.valueOf(z.getData4())/Double.valueOf(z.getData2());
	        	d16 = Double.valueOf(z.getData5())/Double.valueOf(z.getData2());
	        	d17 = Double.valueOf(z.getData6())/Double.valueOf(z.getData2());
	        	d18 = Double.valueOf(z.getData7())/Double.valueOf(z.getData2());
	        	d19 = Double.valueOf(z.getData8())/Double.valueOf(z.getData2());
	        	d20 = Double.valueOf(z.getData9())/Double.valueOf(z.getData2());
	        	d21 = Double.valueOf(z.getData10())/Double.valueOf(z.getData2());
	        	d22 = Double.valueOf(z.getData11())/Double.valueOf(z.getData2());
	        	d23 = Double.valueOf(z.getData12())/Double.valueOf(z.getData2());
	        	d24 = Double.valueOf(z.getData13())/Double.valueOf(z.getData2());
	        	d34 = Double.valueOf(z.getData33())/Double.valueOf(z.getData2()); ;
	        	d36 = Double.valueOf(z.getData35())/Double.valueOf(z.getData2()); ;
	        }
	        HSSFCell ce14 = ro.createCell(14);
	        ce14.setCellValue(new BigDecimal(d14).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce14.setCellStyle(c);
	        HSSFCell ce15 = ro.createCell(15);
	        ce15.setCellValue(new BigDecimal(d15).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce15.setCellStyle(c);
	        HSSFCell ce16 = ro.createCell(16);
	        ce16.setCellValue(new BigDecimal(d16).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce16.setCellStyle(c);
	        HSSFCell ce17 = ro.createCell(17);
	        ce17.setCellValue(new BigDecimal(d17).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce17.setCellStyle(c);
	        HSSFCell ce18 = ro.createCell(18);
	        ce18.setCellValue(new BigDecimal(d18).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce18.setCellStyle(c);
	        HSSFCell ce19 = ro.createCell(19);
	        ce19.setCellValue(new BigDecimal(d19).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce19.setCellStyle(c);
	        
	        HSSFCell ce20 = ro.createCell(20);
			ce20.setCellValue(new BigDecimal(d20).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
			ce20.setCellStyle(c);
	        HSSFCell ce21 = ro.createCell(21);
	        ce21.setCellValue(new BigDecimal(d21).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce21.setCellStyle(c);
	        HSSFCell ce22 = ro.createCell(22);
	        ce22.setCellValue(new BigDecimal(d22).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce22.setCellStyle(c);
	        HSSFCell ce23 = ro.createCell(23);
	        ce23.setCellValue(new BigDecimal(d23).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce23.setCellStyle(c);
	        HSSFCell ce24 = ro.createCell(24);
	        ce24.setCellValue(new BigDecimal(d24).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce24.setCellStyle(c);
	        HSSFCell ce25 = ro.createCell(25);
	        ce25.setCellValue(z.getData25());
	        ce25.setCellStyle(c);
	        double d26,d41,d46,d48,d54;
	        if(z.getData1() == null || "".equals(z.getData1()) || "0".equals(z.getData1()))
	        {
	        	d26=d41=d46=d48=d54=0.00;
	        }
	        else
	        {
	        	d26 = Double.valueOf(z.getData25())/Double.valueOf(z.getData1());
	        	d41 = Double.valueOf(z.getData40())/Double.valueOf(z.getData1());
	        	d46 = Double.valueOf(z.getData45())/Double.valueOf(z.getData1());
	        	d48 = Double.valueOf(z.getData47())/Double.valueOf(z.getData1());
	        	d54 = Double.valueOf(z.getData53())/Double.valueOf(z.getData1());
	        }
	        HSSFCell ce26 = ro.createCell(26);
	        ce26.setCellValue(new BigDecimal(d26).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce26.setCellStyle(c);
	        HSSFCell ce27 = ro.createCell(27);
	        ce27.setCellValue(z.getData27());
	        ce27.setCellStyle(c);
	        HSSFCell ce28 = ro.createCell(28);
	        ce28.setCellValue(z.getData28());
	        ce28.setCellStyle(c);
	        double d29;
	        if(z.getData27() == null || "".equals(z.getData27()) || "0".equals(z.getData27()))
	        {
	        	d29 = 0.00;
	        }
	        else
	        {
	        	d29 = Double.valueOf(z.getData28())/Double.valueOf(z.getData27());
	        }
	        HSSFCell ce29 = ro.createCell(29);
	        ce29.setCellValue(new BigDecimal(d29).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce29.setCellStyle(c);
	        
	        HSSFCell ce30 = ro.createCell(30);
			ce30.setCellValue(z.getData30());
			ce30.setCellStyle(c);
	        HSSFCell ce31 = ro.createCell(31);
	        ce31.setCellValue(z.getData31());
	        ce31.setCellStyle(c);
	        double d32;
	        if(z.getData30() == null || "".equals(z.getData30()) || "0".equals(z.getData30()))
	        {
	        	d32 = 0.00;
	        }
	        else
	        {
	        	d32 = Double.valueOf(z.getData31())/Double.valueOf(z.getData30());
	        }
	        HSSFCell ce32 = ro.createCell(32);
	        ce32.setCellValue(new BigDecimal(d32).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce32.setCellStyle(c);
	        HSSFCell ce33 = ro.createCell(33);
	        ce33.setCellValue(z.getData33());
	        ce33.setCellStyle(c);
	        HSSFCell ce34 = ro.createCell(34);
	        ce34.setCellValue(new BigDecimal(d34).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce34.setCellStyle(c);
	        HSSFCell ce35 = ro.createCell(35);
	        ce35.setCellValue(z.getData35());
	        ce35.setCellStyle(c);
	        HSSFCell ce36 = ro.createCell(36);
	        ce36.setCellValue(new BigDecimal(d36).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce36.setCellStyle(c);
	        HSSFCell ce37 = ro.createCell(37);
	        ce37.setCellValue(z.getData37());
	        ce37.setCellStyle(c);
	        HSSFCell ce38 = ro.createCell(38);
	        ce38.setCellValue(z.getData38());
	        ce38.setCellStyle(c);
	        double d39;
	        if(z.getData37() == null || "".equals(z.getData37()) || "0".equals(z.getData37()))
	        {
	        	d39 = 0.00;
	        }
	        else
	        {
	        	d39 = Double.valueOf(z.getData38())/Double.valueOf(z.getData37());
	        }
	        HSSFCell ce39 = ro.createCell(39);
	        ce39.setCellValue(new BigDecimal(d39).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce39.setCellStyle(c);
	        
	        HSSFCell ce40 = ro.createCell(40);
			ce40.setCellValue(z.getData40());
			ce40.setCellStyle(c);
	        HSSFCell ce41 = ro.createCell(41);
	        ce41.setCellValue(new BigDecimal(d41).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce41.setCellStyle(c);
	        HSSFCell ce42 = ro.createCell(42);
	        ce42.setCellValue(z.getData42());
	        ce42.setCellStyle(c);
	        HSSFCell ce43 = ro.createCell(43);
	        ce43.setCellValue(z.getData43());
	        ce43.setCellStyle(c);
	        double d44;
	        if(z.getData42() == null || "".equals(z.getData42()) || "0".equals(z.getData42()))
	        {
	        	d44 = 0.00;
	        }
	        else
	        {
	        	d44 = Double.valueOf(z.getData43())/Double.valueOf(z.getData42());
	        }
	        HSSFCell ce44 = ro.createCell(44);
	        ce44.setCellValue(new BigDecimal(d44).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce44.setCellStyle(c);
	        HSSFCell ce45 = ro.createCell(45);
	        ce45.setCellValue(z.getData45());
	        ce45.setCellStyle(c);
	        HSSFCell ce46 = ro.createCell(46);
	        ce46.setCellValue(new BigDecimal(d46).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce46.setCellStyle(c);
	        HSSFCell ce47 = ro.createCell(47);
	        ce47.setCellValue(z.getData47());
	        ce47.setCellStyle(c);
	        HSSFCell ce48 = ro.createCell(48);
	        ce48.setCellValue(new BigDecimal(d48).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce48.setCellStyle(c);
	        HSSFCell ce49 = ro.createCell(49);
	        ce49.setCellValue(z.getData49());
	        ce49.setCellStyle(c);
	        
	        HSSFCell ce50 = ro.createCell(50);
			ce50.setCellValue(z.getData50());
			ce50.setCellStyle(c);
	        HSSFCell ce51 = ro.createCell(51);
	        ce51.setCellValue(z.getData51());
	        ce51.setCellStyle(c);
	        HSSFCell ce52 = ro.createCell(52);
	        ce52.setCellValue(z.getData52());
	        ce52.setCellStyle(c);
	        HSSFCell ce53 = ro.createCell(53);
	        ce53.setCellValue(z.getData53());
	        ce53.setCellStyle(c);
	        HSSFCell ce54 = ro.createCell(54);
	        ce54.setCellValue(new BigDecimal(d54).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce54.setCellStyle(c);
	        double d55 = Double.valueOf(z.getData56()) + Double.valueOf(z.getData59()) + Double.valueOf(z.getData61()) + Double.valueOf(z.getData64());
	        HSSFCell ce55 = ro.createCell(55);
	        ce55.setCellValue(new BigDecimal(d55).setScale(0,BigDecimal.ROUND_HALF_UP)+"");
	        ce55.setCellStyle(c);
	        HSSFCell ce56 = ro.createCell(56);
	        ce56.setCellValue(z.getData56());
	        ce56.setCellStyle(c);
	        HSSFCell ce57 = ro.createCell(57);
	        ce57.setCellValue(z.getData57());
	        ce57.setCellStyle(c);
	        HSSFCell ce58 = ro.createCell(58);
	        ce58.setCellValue(z.getData58());
	        ce58.setCellStyle(c);
	        HSSFCell ce59 = ro.createCell(59);
	        ce59.setCellValue(z.getData59());
	        ce59.setCellStyle(c);
	        
	        HSSFCell ce60 = ro.createCell(60);
			ce60.setCellValue(z.getData60());
			ce60.setCellStyle(c);
	        HSSFCell ce61 = ro.createCell(61);
	        ce61.setCellValue(z.getData61());
	        ce61.setCellStyle(c);
	        HSSFCell ce62 = ro.createCell(62);
	        ce62.setCellValue(z.getData62());
	        ce62.setCellStyle(c);
	        HSSFCell ce63 = ro.createCell(63);
	        ce63.setCellValue(z.getData63());
	        ce63.setCellStyle(c);
	        HSSFCell ce64 = ro.createCell(64);
	        ce64.setCellValue(z.getData64());
	        ce64.setCellStyle(c);
	        HSSFCell ce65 = ro.createCell(65);
	        ce65.setCellValue(z.getData65());
	        ce65.setCellStyle(c);
	        double d66 = Double.valueOf(z.getData67()) + Double.valueOf(z.getData70()) + Double.valueOf(z.getData72()) + Double.valueOf(z.getData75());
	        HSSFCell ce66 = ro.createCell(66);
	        ce66.setCellValue(new BigDecimal(d66).setScale(0,BigDecimal.ROUND_HALF_UP)+"");
	        ce66.setCellStyle(c);
	        HSSFCell ce67 = ro.createCell(67);
	        ce67.setCellValue(z.getData67());
	        ce67.setCellStyle(c);
	        HSSFCell ce68 = ro.createCell(68);
	        ce68.setCellValue(z.getData68());
	        ce68.setCellStyle(c);
	        HSSFCell ce69 = ro.createCell(69);
	        ce69.setCellValue(z.getData69());
	        ce69.setCellStyle(c);
	        
	        HSSFCell ce70 = ro.createCell(70);
			ce70.setCellValue(z.getData70());
			ce70.setCellStyle(c);
	        HSSFCell ce71 = ro.createCell(71);
	        ce71.setCellValue(z.getData71());
	        ce71.setCellStyle(c);
	        HSSFCell ce72 = ro.createCell(72);
	        ce72.setCellValue(z.getData72());
	        ce72.setCellStyle(c);
	        HSSFCell ce73 = ro.createCell(73);
	        ce73.setCellValue(z.getData73());
	        ce73.setCellStyle(c);
	        HSSFCell ce74 = ro.createCell(74);
	        ce74.setCellValue(z.getData74());
	        ce74.setCellStyle(c);
	        HSSFCell ce75 = ro.createCell(75);
	        ce75.setCellValue(z.getData75());
	        ce75.setCellStyle(c);
	        HSSFCell ce76 = ro.createCell(76);
	        ce76.setCellValue(z.getData76());
	        ce76.setCellStyle(c);
	        double d77;
	        if(d55 == 0.00)
	        {
	        	d77=0.00;
	        }
	        else
	        {
	        	d77 = d66/d55;
	        }
	        HSSFCell ce77 = ro.createCell(77);
	        ce77.setCellValue(new BigDecimal(d77).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce77.setCellStyle(c);
	        double d78;
	        if(z.getData56() == null || "".equals(z.getData56()) || "0".equals(z.getData56()))
	        {
	        	d78 = 0.00;
	        }
	        else
	        {
	        	d78 = Double.valueOf(z.getData67())/Double.valueOf(z.getData56());
	        }
	        HSSFCell ce78 = ro.createCell(78);
	        ce78.setCellValue(new BigDecimal(d78).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce78.setCellStyle(c);
	        double d79;
	        if(z.getData57() == null || "".equals(z.getData57()) || "0".equals(z.getData57()))
	        {
	        	d79 = 0.00;
	        }
	        else
	        {
	        	d79 = Double.valueOf(z.getData68())/Double.valueOf(z.getData57());
	        }
	        HSSFCell ce79 = ro.createCell(79);
	        ce79.setCellValue(new BigDecimal(d79).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce79.setCellStyle(c);
	        double d80;
	        if(z.getData58() == null || "".equals(z.getData58()) || "0".equals(z.getData58()))
	        {
	        	d80 = 0.00;
	        }
	        else
	        {
	        	d80 = Double.valueOf(z.getData69())/Double.valueOf(z.getData58());
	        }
	        HSSFCell ce80 = ro.createCell(80);
			ce80.setCellValue(new BigDecimal(d80).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
			ce80.setCellStyle(c);
			double d81;
	        if(z.getData59() == null || "".equals(z.getData59()) || "0".equals(z.getData59()))
	        {
	        	d81 = 0.00;
	        }
	        else
	        {
	        	d81 = Double.valueOf(z.getData70())/Double.valueOf(z.getData59());
	        }
	        HSSFCell ce81 = ro.createCell(81);
	        ce81.setCellValue(new BigDecimal(d81).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce81.setCellStyle(c);
	        double d82;
	        if(z.getData60() == null || "".equals(z.getData60()) || "0".equals(z.getData60()))
	        {
	        	d82 = 0.00;
	        }
	        else
	        {
	        	d82 = Double.valueOf(z.getData71())/Double.valueOf(z.getData60());
	        }
	        HSSFCell ce82 = ro.createCell(82);
	        ce82.setCellValue(new BigDecimal(d82).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce82.setCellStyle(c);
	        double d83;
	        if(z.getData61() == null || "".equals(z.getData61()) || "0".equals(z.getData61()))
	        {
	        	d83 = 0.00;
	        }
	        else
	        {
	        	d83 = Double.valueOf(z.getData72())/Double.valueOf(z.getData61());
	        }
	        HSSFCell ce83 = ro.createCell(83);
	        ce83.setCellValue(new BigDecimal(d83).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce83.setCellStyle(c);
	        double d84;
	        if(z.getData62() == null || "".equals(z.getData62()) || "0".equals(z.getData62()))
	        {
	        	d84 = 0.00;
	        }
	        else
	        {
	        	d84 = Double.valueOf(z.getData73())/Double.valueOf(z.getData62());
	        }
	        HSSFCell ce84 = ro.createCell(84);
	        ce84.setCellValue(new BigDecimal(d84).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce84.setCellStyle(c);
	        double d85;
	        if(z.getData63() == null || "".equals(z.getData63()) || "0".equals(z.getData63()))
	        {
	        	d85 = 0.00;
	        }
	        else
	        {
	        	d85 = Double.valueOf(z.getData74())/Double.valueOf(z.getData63());
	        }
	        HSSFCell ce85 = ro.createCell(85);
	        ce85.setCellValue(new BigDecimal(d85).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce85.setCellStyle(c);
	        double d86;
	        if(z.getData64() == null || "".equals(z.getData64()) || "0".equals(z.getData64()))
	        {
	        	d86 = 0.00;
	        }
	        else
	        {
	        	d86 = Double.valueOf(z.getData75())/Double.valueOf(z.getData64());
	        }
	        HSSFCell ce86 = ro.createCell(86);
	        ce86.setCellValue(new BigDecimal(d86).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce86.setCellStyle(c);
	        double d87;
	        if(z.getData65() == null || "".equals(z.getData65()) || "0".equals(z.getData65()))
	        {
	        	d87 = 0.00;
	        }
	        else
	        {
	        	d87 = Double.valueOf(z.getData76())/Double.valueOf(z.getData65());
	        }
	        HSSFCell ce87 = ro.createCell(87);
	        ce87.setCellValue(new BigDecimal(d87).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce87.setCellStyle(c);
	        HSSFCell ce88 = ro.createCell(88);
	        ce88.setCellValue(z.getData88());
	        ce88.setCellStyle(c);
	        HSSFCell ce89 = ro.createCell(89);
	        ce89.setCellValue(z.getData89());
	        ce89.setCellStyle(c);
	        
	        HSSFCell ce90 = ro.createCell(90);
			ce90.setCellValue(z.getData90());
			ce90.setCellStyle(c);
	        HSSFCell ce91 = ro.createCell(91);
	        ce91.setCellValue(z.getData91());
	        ce91.setCellStyle(c);
	        HSSFCell ce92 = ro.createCell(92);
	        ce92.setCellValue(z.getData92());
	        ce92.setCellStyle(c);
	        HSSFCell ce93 = ro.createCell(93);
	        ce93.setCellValue(z.getData93());
	        ce93.setCellStyle(c);
	        HSSFCell ce94 = ro.createCell(94);
	        ce94.setCellValue(z.getData94());
	        ce94.setCellStyle(c);
	        HSSFCell ce95 = ro.createCell(95);
	        ce95.setCellValue(z.getData95());
	        ce95.setCellStyle(c);
	        double d96;
	        if(z.getData88() == null || "".equals(z.getData88()) || "0".equals(z.getData88()))
	        {
	        	d96 = 0.00;
	        }
	        else
	        {
	        	d96 = Double.valueOf(z.getData92())/Double.valueOf(z.getData88());
	        }
	        HSSFCell ce96 = ro.createCell(96);
	        ce96.setCellValue(new BigDecimal(d96).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce96.setCellStyle(c);
	        double d97;
	        if(z.getData89() == null || "".equals(z.getData89()) || "0".equals(z.getData89()))
	        {
	        	d97 = 0.00;
	        }
	        else
	        {
	        	d97 = Double.valueOf(z.getData93())/Double.valueOf(z.getData89());
	        }
	        HSSFCell ce97 = ro.createCell(97);
	        ce97.setCellValue(new BigDecimal(d97).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce97.setCellStyle(c);
	        double d98;
	        if(z.getData90() == null || "".equals(z.getData90()) || "0".equals(z.getData90()))
	        {
	        	d98 = 0.00;
	        }
	        else
	        {
	        	d98 = Double.valueOf(z.getData94())/Double.valueOf(z.getData90());
	        }
	        HSSFCell ce98 = ro.createCell(98);
	        ce98.setCellValue(new BigDecimal(d98).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce8.setCellStyle(c);
	        double d99;
	        if(z.getData91() == null || "".equals(z.getData91()) || "0".equals(z.getData91()))
	        {
	        	d99 = 0.00;
	        }
	        else
	        {
	        	d99 = Double.valueOf(z.getData95())/Double.valueOf(z.getData91());
	        }
	        HSSFCell ce99 = ro.createCell(99);
	        ce99.setCellValue(new BigDecimal(d99).setScale(2,BigDecimal.ROUND_HALF_UP)+"");
	        ce99.setCellStyle(c);
	        
	        HSSFCell ce100 = ro.createCell(100);
			ce100.setCellValue(z.getData100());
			ce100.setCellStyle(c);
	        HSSFCell ce101 = ro.createCell(101);
	        ce101.setCellValue(z.getData101());
	        ce101.setCellStyle(c);
	        HSSFCell ce102 = ro.createCell(102);
	        ce102.setCellValue(z.getData102());
	        ce102.setCellStyle(c);
	        HSSFCell ce103 = ro.createCell(103);
	        ce103.setCellValue(z.getData103());
	        ce103.setCellStyle(c);
	        HSSFCell ce104 = ro.createCell(104);
	        ce104.setCellValue(z.getData104());
	        ce104.setCellStyle(c);
	        HSSFCell ce105 = ro.createCell(105);
	        ce105.setCellValue(z.getData105());
	        ce105.setCellStyle(c);
	        HSSFCell ce106 = ro.createCell(106);
	        ce106.setCellValue(z.getData106());
	        ce106.setCellStyle(c);
	        HSSFCell ce107 = ro.createCell(107);
	        ce107.setCellValue(z.getData107());
	        ce107.setCellStyle(c);
	        HSSFCell ce108 = ro.createCell(108);
	        ce108.setCellValue(z.getData108());
	        ce108.setCellStyle(c);
	        HSSFCell ce109 = ro.createCell(109);
	        ce109.setCellValue(z.getData109());
	        ce109.setCellStyle(c);
			
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

	public Zywsqk getZywsqk(){
		return this.zywsqk;
	}

	public void setZywsqk(Zywsqk zywsqk){
		this.zywsqk = zywsqk;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public String getQueryTjnfStart(){
		return this.queryTjnfStart;
	}

	public void setQueryTjnfStart(String queryTjnfStart){
		this.queryTjnfStart = queryTjnfStart;
	}

	public String getQueryTjnfEnd(){
		return this.queryTjnfEnd;
	}

	public void setQueryTjnfEnd(String queryTjnfEnd){
		this.queryTjnfEnd = queryTjnfEnd;
	}

	public List<Zywhglb> getFcList() {
		return fcList;
	}

	public void setFcList(List<Zywhglb> fcList) {
		this.fcList = fcList;
	}

	public List<Zywhglb> getHxList() {
		return hxList;
	}

	public void setHxList(List<Zywhglb> hxList) {
		this.hxList = hxList;
	}

	public List<Zywhglb> getWlList() {
		return wlList;
	}

	public void setWlList(List<Zywhglb> wlList) {
		this.wlList = wlList;
	}

	public List<Zywhglb> getSwList() {
		return swList;
	}

	public void setSwList(List<Zywhglb> swList) {
		this.swList = swList;
	}

	public String getFcjcrs() {
		return fcjcrs;
	}

	public void setFcjcrs(String fcjcrs) {
		this.fcjcrs = fcjcrs;
	}

	public String getFcjcds() {
		return fcjcds;
	}

	public void setFcjcds(String fcjcds) {
		this.fcjcds = fcjcds;
	}

	public String getFcdbds() {
		return fcdbds;
	}

	public void setFcdbds(String fcdbds) {
		this.fcdbds = fcdbds;
	}

	public String getHxjcrs() {
		return hxjcrs;
	}

	public void setHxjcrs(String hxjcrs) {
		this.hxjcrs = hxjcrs;
	}

	public String getHxjcds() {
		return hxjcds;
	}

	public void setHxjcds(String hxjcds) {
		this.hxjcds = hxjcds;
	}

	public String getHxdbds() {
		return hxdbds;
	}

	public void setHxdbds(String hxdbds) {
		this.hxdbds = hxdbds;
	}

	public String getWljcrs() {
		return wljcrs;
	}

	public void setWljcrs(String wljcrs) {
		this.wljcrs = wljcrs;
	}

	public String getWljcds() {
		return wljcds;
	}

	public void setWljcds(String wljcds) {
		this.wljcds = wljcds;
	}

	public String getWldbds() {
		return wldbds;
	}

	public void setWldbds(String wldbds) {
		this.wldbds = wldbds;
	}

	public String getSwjcrs() {
		return swjcrs;
	}

	public void setSwjcrs(String swjcrs) {
		this.swjcrs = swjcrs;
	}

	public String getSwjcds() {
		return swjcds;
	}

	public void setSwjcds(String swjcds) {
		this.swjcds = swjcds;
	}

	public String getSwdbds() {
		return swdbds;
	}

	public void setSwdbds(String swdbds) {
		this.swdbds = swdbds;
	}

	public String getFczywhmc() {
		return fczywhmc;
	}

	public void setFczywhmc(String fczywhmc) {
		this.fczywhmc = fczywhmc;
	}

	public String getFczywhid() {
		return fczywhid;
	}

	public void setFczywhid(String fczywhid) {
		this.fczywhid = fczywhid;
	}

	public String getHxzywhmc() {
		return hxzywhmc;
	}

	public void setHxzywhmc(String hxzywhmc) {
		this.hxzywhmc = hxzywhmc;
	}

	public String getHxzywhid() {
		return hxzywhid;
	}

	public void setHxzywhid(String hxzywhid) {
		this.hxzywhid = hxzywhid;
	}

	public String getWlzywhmc() {
		return wlzywhmc;
	}

	public void setWlzywhmc(String wlzywhmc) {
		this.wlzywhmc = wlzywhmc;
	}

	public String getWlzywhid() {
		return wlzywhid;
	}

	public void setWlzywhid(String wlzywhid) {
		this.wlzywhid = wlzywhid;
	}

	public String getSwzywhmc() {
		return swzywhmc;
	}

	public void setSwzywhmc(String swzywhmc) {
		this.swzywhmc = swzywhmc;
	}

	public String getSwzywhid() {
		return swzywhid;
	}

	public void setSwzywhid(String swzywhid) {
		this.swzywhid = swzywhid;
	}

	public String getDclx() {
		return dclx;
	}

	public void setDclx(String dclx) {
		this.dclx = dclx;
	}
}
