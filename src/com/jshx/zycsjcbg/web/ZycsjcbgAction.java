/**
 * Class Name: ZycsjcbgAction
 * Class Description：作业场所检测报告
 */
package com.jshx.zycsjcbg.web;

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

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zycsjcbg.entity.Zycsjcbg;
import com.jshx.zycsjcbg.service.ZycsjcbgService;

public class ZycsjcbgAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zycsjcbg zycsjcbg = new Zycsjcbg();

	/**
	 * 业务类
	 */
	@Autowired
	private ZycsjcbgService zycsjcbgService;

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
	
	private List<Department> deptList = new ArrayList<Department>();
	
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
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			visable = "0";
		}
		else if(deptCode.startsWith("004"))//检测机构
		{
			visable = "1";
		}
		else
		{
			visable = "2";
		}
		deptList = deptService.findDeptByParentDeptCode("004");
		return SUCCESS;
	}
	
	public String initlists() throws Exception{
		deptList = deptService.findDeptByParentDeptCode("004");
		return SUCCESS;
	}
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zycsjcbg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zycsjcbg.getDeptId()) && (0 < zycsjcbg.getDeptId().trim().length())){
				paraMap.put("deptCodes", zycsjcbg.getDeptId().trim());
			}
			if ((null != zycsjcbg.getQymc()) && (0 < zycsjcbg.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zycsjcbg.getQymc().trim() + "%");
			}
			if ((null != zycsjcbg.getJcdwcode()) && (0 < zycsjcbg.getJcdwcode().trim().length())){
				paraMap.put("jcdwcode", zycsjcbg.getJcdwcode().trim());
			}
		}
		if (null != createTimeStart && !"".equals(createTimeStart)){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd && !"".equals(createTimeEnd)){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = zycsjcbgService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}
	
	public void lists() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zycsjcbg){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zycsjcbg.getDeptId()) && (0 < zycsjcbg.getDeptId().trim().length())){
				paraMap.put("deptCodes", zycsjcbg.getDeptId().trim());
			}
			if ((null != zycsjcbg.getQymc()) && (0 < zycsjcbg.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zycsjcbg.getQymc().trim() + "%");
			}
			if ((null != zycsjcbg.getJcdwcode()) && (0 < zycsjcbg.getJcdwcode().trim().length())){
				paraMap.put("jcdwcode", zycsjcbg.getJcdwcode().trim());
			}
		}
		if (null != createTimeStart && !"".equals(createTimeStart)){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd && !"".equals(createTimeEnd)){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		pagination = zycsjcbgService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zycsjcbg)&&(null != zycsjcbg.getId()))
			zycsjcbg = zycsjcbgService.getById(zycsjcbg.getId());
		if(zycsjcbg.getLinkid()==null)
		{
			zycsjcbg.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",zycsjcbg.getLinkid());
		map.put("picType","zycsjcbg");
	    list = szwxPhotoService.findPicPath(map);//获取执法文书材料
	    
	    String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			deptList = deptService.findDeptByParentDeptCode("004");
			visable = "0";
		}
		else if(deptCode.startsWith("004"))
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
		company=companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			zycsjcbg.setSzz(deptService.findDeptByDeptCode(company.getDwdz1()).getDeptName());
			zycsjcbg.setDeptId(company.getDwdz1());
			zycsjcbg.setComid(company.getId());
			zycsjcbg.setQymc(company.getCompanyname());
				//企业类型 qylx 行业分类 hyfl 企业规模 qygm  企业注册类型  qyzclx
			zycsjcbg.setQylx(company.getQylx());
			zycsjcbg.setHyfl(company.getHyfl());
			zycsjcbg.setQygm(company.getQygm());
			zycsjcbg.setQyzclx(company.getQyzclx());
			zycsjcbg.setIfwhpqylx(company.getIfwhpqylx());
			zycsjcbg.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zycsjcbg.setIfzywhqylx(company.getIfzywhqylx());
			zycsjcbg.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
			zycsjcbg.setSzc(company.getSzc());
			zycsjcbg.setSzcname(company.getSzcname());
			if(zycsjcbg.getJcdwcode() != null && !"".equals(zycsjcbg.getJcdwcode()))
			{
				zycsjcbg.setJcdwname(deptService.findDeptByDeptCode(zycsjcbg.getJcdwcode()).getDeptName());
			}
		}
		else if(deptCode.startsWith("004"))
		{
			if(zycsjcbg.getComid() != null && !"".equals(zycsjcbg.getComid()))
			{
				Map map = new HashMap();
				map.put("companyId", zycsjcbg.getComid());
				CompanyBackUp company = companyService.getCompanyBackupById(map);
				if(!zycsjcbg.getQymc().equals(company.getCompanyname()))
				{
					zycsjcbg.setComid(null);
					Department dept = deptService.findDeptByDeptCode(zycsjcbg.getDeptId());
					zycsjcbg.setDeptId(dept.getDeptCode());
					zycsjcbg.setSzz(dept.getDeptName());
				}
				else
				{
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					zycsjcbg.setDeptId(dept.getDeptCode());
					zycsjcbg.setSzz(dept.getDeptName());
					zycsjcbg.setComid(company.getId());
					zycsjcbg.setQymc(company.getCompanyname());
					zycsjcbg.setQylx(company.getQylx());
					zycsjcbg.setHyfl(company.getHyfl());
					zycsjcbg.setQygm(company.getQygm());
					zycsjcbg.setQyzclx(company.getQyzclx());
					zycsjcbg.setIfwhpqylx(company.getIfwhpqylx());
					zycsjcbg.setIfyhbzjyqy(company.getIfyhbzjyqy());
					zycsjcbg.setIfzywhqylx(company.getIfzywhqylx());
					zycsjcbg.setSzc(company.getSzc());
					zycsjcbg.setSzcname(company.getSzcname());
				}
			}
			else if(zycsjcbg.getDeptId() != null && !"".equals(zycsjcbg.getDeptId()))
			{
				Department dept = deptService.findDeptByDeptCode(zycsjcbg.getDeptId());
				zycsjcbg.setDeptId(dept.getDeptCode());
				zycsjcbg.setSzz(dept.getDeptName());
			}
			zycsjcbg.setJcdwcode(deptCode);
			zycsjcbg.setJcdwname(this.getLoginUserDepartment().getDeptName());
		}
		if ("add".equalsIgnoreCase(this.flag)){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			zycsjcbg.setUploadtime(sdf.format(new Date()));
			zycsjcbg.setDelFlag(0);
			zycsjcbg.setCreateUserID(this.getLoginUserId());
			zycsjcbg.setCreateTime(new Date());
			zycsjcbg.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zycsjcbg.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zycsjcbgService.save(zycsjcbg);
		}else{
			zycsjcbg.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zycsjcbg.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zycsjcbgService.update(zycsjcbg);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zycsjcbgService.deleteWithFlag(ids);
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
			getResponse().addHeader("Content-Disposition", "attachment;filename=zycsjcbg.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("作业场所危害因素检测报告");
		    sheet.setColumnWidth(0, 6000); 
	        sheet.setColumnWidth(1, 10000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 10000);
	        sheet.setColumnWidth(4, 5000); 
	        sheet.setColumnWidth(5, 10000); 
	        sheet.setColumnWidth(6, 6000);
	        sheet.setColumnWidth(7, 5000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("作业场所危害因素检测报告");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 7)); 
	        
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
	        ccl0.setCellValue("所在镇");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("企业名称");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(2);
	        ccl3.setCellValue("检测日期");
	        ccl3.setCellStyle(cs);
	        HSSFCell ccl4 = r3.createCell(3);
	        ccl4.setCellValue("检测危害因素");
	        ccl4.setCellStyle(cs);
	        HSSFCell ccl5 = r3.createCell(4);
	        ccl5.setCellValue("检测点数");
	        ccl5.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(5);
	        ccl2.setCellValue("不合格点的危险因素名称");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl6 = r3.createCell(6);
	        ccl6.setCellValue("不合格点数");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r3.createCell(7);
	        ccl7.setCellValue("检测机构");
	        ccl7.setCellStyle(cs);
	        
	        
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
	        	zycsjcbg = (Zycsjcbg) getSessionAttribute("zycsjcbg");
	        	createTimeStart = (String) getSessionAttribute("createTimeStart");
	        	createTimeEnd = (String) getSessionAttribute("createTimeEnd");
    		}
	        if(null != zycsjcbg){
	        	setSessionAttribute("zycsjcbg", zycsjcbg);
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != zycsjcbg.getDeptId()) && (0 < zycsjcbg.getDeptId().trim().length())){
					paraMap.put("deptCodes", zycsjcbg.getDeptId().trim());
				}
				if ((null != zycsjcbg.getQymc()) && (0 < zycsjcbg.getQymc().trim().length())){
					paraMap.put("qymc", "%" + zycsjcbg.getQymc().trim() + "%");
				}
				if ((null != zycsjcbg.getJcdwcode()) && (0 < zycsjcbg.getJcdwcode().trim().length())){
					paraMap.put("jcdwcode", zycsjcbg.getJcdwcode().trim());
				}
			}
			if (null != createTimeStart && !"".equals(createTimeStart)){
				paraMap.put("startCreateTime", createTimeStart);
				setSessionAttribute("createTimeStart", createTimeStart);
			}

			if (null != createTimeEnd && !"".equals(createTimeEnd)){
				paraMap.put("endCreateTime", createTimeEnd);
				setSessionAttribute("createTimeEnd", createTimeEnd);
			}
			//hanxc 20141223 修改查询条件 start
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			//hanxc 20141223 修改查询条件 end
 
			List<Zycsjcbg> list = zycsjcbgService.findZycsjcbg(paraMap);
			
			int num = 2;
			for(Zycsjcbg zycsjcbg:list)
			{
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(zycsjcbg.getSzz());
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(zycsjcbg.getQymc());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(zycsjcbg.getJcsj());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(zycsjcbg.getJcwz());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(zycsjcbg.getJcds());
		        ce4.setCellStyle(c);
        		HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(zycsjcbg.getBggName());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(zycsjcbg.getBhgds());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(zycsjcbg.getJcdwname());
		        ce7.setCellStyle(c);
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

	public Zycsjcbg getZycsjcbg(){
		return this.zycsjcbg;
	}

	public void setZycsjcbg(Zycsjcbg zycsjcbg){
		this.zycsjcbg = zycsjcbg;
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


	public List<Department> getDeptList() {
		return deptList;
	}


	public void setDeptList(List<Department> deptList) {
		this.deptList = deptList;
	}
     
    
}
