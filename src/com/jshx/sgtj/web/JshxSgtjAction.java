/**
 * Class Name: JshxSgtjAction
 * Class Description：事故统计
 */
package com.jshx.sgtj.web;

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

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.http.util.StringTools;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.sgtj.entity.JshxSgtj;
import com.jshx.sgtj.service.JshxSgtjService;
import com.jshx.sgtjData.entity.SgtjData;
import com.jshx.sgtjData.service.SgtjDataService;

public class JshxSgtjAction extends BaseAction
{
	private String[] datas = 
		new String[]{"各类事故合计","其中：擦伤","烧伤","辐射","化学灼伤","触电","骨折","内出血","中毒","扭伤","肌肉拉伤","割伤","其他"};

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxSgtj jshxSgtj = new JshxSgtj();
	
	private SgtjData sgtjData = new SgtjData();

	private List<SgtjData> sgtjDatas = new ArrayList<SgtjData>();
	/**
	 * 业务类
	 */
	@Autowired
	private JshxSgtjService jshxSgtjService;
	@Autowired
	private SgtjDataService sgtjDataService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private String queryYfStart;

	private String queryYfEnd;
	
	/**
	 * 初始化列表
	 * @return
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
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxSgtj){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxSgtj.getQymc()) && (0 < jshxSgtj.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxSgtj.getQymc().trim() + "%");
			}
			if ((null != jshxSgtj.getSzzid()) && (0 < jshxSgtj.getSzzid().trim().length())){
				paraMap.put("szzid",  jshxSgtj.getSzzid().trim() );
			}
			if ((null != jshxSgtj.getSzc() )&& (0 < jshxSgtj.getSzc().trim().length())){
				paraMap.put("szc",jshxSgtj.getSzc().trim());
			}
		}
		if (null != queryYfStart && (0 < queryYfStart.trim().length())){
			paraMap.put("startYf", queryYfStart);
		}

		if (null != queryYfEnd && (0 < queryYfEnd.trim().length())){
			paraMap.put("endYf", queryYfEnd);
		}

		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = jshxSgtjService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if((null != jshxSgtj)&&(null != jshxSgtj.getId())){
			jshxSgtj = jshxSgtjService.getById(jshxSgtj.getId());
		     paraMap.put("linkId", jshxSgtj.getId());
		}else{
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			jshxSgtj.setTbr(this.getLoginUser().getDisplayName());
			jshxSgtj.setZgs(Long.toString(company.getCyry()));
			 paraMap.put("linkId", "linkId");
		}
		
		sgtjDatas = sgtjDataService.findSgtjData(paraMap);
		if(sgtjDatas==null){
			sgtjDatas = new ArrayList<SgtjData>();
			for(int i=0;i<14;i++){
				SgtjData sgtjData = new SgtjData();
				sgtjDatas.add(sgtjData);
			}
			
		}else if(sgtjDatas.size()<14){
			for(int i=(sgtjDatas.size());i<14;i++){
				SgtjData sgtjData = new SgtjData();
				sgtjDatas.add(sgtjData);
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
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			try {
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				jshxSgtj.setSzzid(dept.getDeptCode());
				jshxSgtj.setSzzname(dept.getDeptName());
				jshxSgtj.setQyid(company.getId());
				jshxSgtj.setQymc(company.getCompanyname());
				jshxSgtj.setDeptId(this.getLoginUserDepartmentId());
				jshxSgtj.setDelFlag(0);
				jshxSgtj.setCreateUserID(this.getLoginUserId());
				jshxSgtj.setCreateTime(new Date());
				jshxSgtj.setQylx(company.getQylx());
				jshxSgtj.setHyfl(company.getHyfl());
				jshxSgtj.setQygm(company.getQygm());
				jshxSgtj.setQyzclx(company.getQyzclx());

				jshxSgtj.setIfwhpqylx(company.getIfwhpqylx());
				jshxSgtj.setIfyhbzjyqy(company.getIfyhbzjyqy());
				jshxSgtj.setIfzywhqylx(company.getIfzywhqylx());
				jshxSgtj.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
				jshxSgtj.setSzc(company.getSzc());
				jshxSgtj.setSzcname(company.getSzcname());
			} catch (RuntimeException e) {
				e.printStackTrace();
			}//企业名称
			jshxSgtj.setDeptId(this.getLoginUserDepartmentId());
			jshxSgtj.setDelFlag(0);
			jshxSgtj.setTbr(this.getLoginUser().getDisplayName());
			jshxSgtj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxSgtj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxSgtjService.save(jshxSgtj);
			for(int i=0;i<14;i++){
				sgtjDataService.save(convertObjectData(jshxSgtj.getId(),i,this.flag));
			}
		}else{
			jshxSgtj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			jshxSgtj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			jshxSgtjService.update(jshxSgtj);
			for(int i=0;i<14;i++){
				sgtjDataService.update(convertObjectData(jshxSgtj.getId(),i,this.flag));
			}
		}
		return RELOAD;
	}
	/**
	 * 给指定对象赋值 李军 2013-08-19
	 */
	public  SgtjData convertObjectData(String linkId,int index,String flag){
		SgtjData aqsc = new SgtjData();
		aqsc.setLinkid(linkId);
		String sort="0";
		if(index+1<10){
			sort = "0"+(index+1);
		}else{
			sort=(index+1)+"";
		}
		aqsc.setSort(sort);
		aqsc.setDelFlag(0);
		if(!"add".equals(flag)){
			aqsc.setId(StringTools.getStrByIndex(sgtjData.getId(),index));
			aqsc.setCreateTime(sgtjData.getCreateTime());
		}
		aqsc.setData_1(StringTools.getStrByIndex(sgtjData.getData_1(),index));
		aqsc.setData_2(StringTools.getStrByIndex(sgtjData.getData_2(),index));
		aqsc.setData_3(StringTools.getStrByIndex(sgtjData.getData_3(),index));
		aqsc.setData_4(StringTools.getStrByIndex(sgtjData.getData_4(),index));
		aqsc.setData_5(StringTools.getStrByIndex(sgtjData.getData_5(),index));
		aqsc.setData_6(StringTools.getStrByIndex(sgtjData.getData_6(),index));
		aqsc.setData_7(StringTools.getStrByIndex(sgtjData.getData_7(),index));
		aqsc.setData_8(StringTools.getStrByIndex(sgtjData.getData_8(),index));
		aqsc.setData_9(StringTools.getStrByIndex(sgtjData.getData_9(),index));
		aqsc.setData_10(StringTools.getStrByIndex(sgtjData.getData_10(),index));
		aqsc.setData_11(StringTools.getStrByIndex(sgtjData.getData_11(),index));
		aqsc.setData_12(StringTools.getStrByIndex(sgtjData.getData_12(),index));
		aqsc.setData_13(StringTools.getStrByIndex(sgtjData.getData_13(),index));
		return aqsc;
	} 

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			jshxSgtjService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 导出事故统计信息
	 * author：陆婷
	 * 2013-11-12
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=sgtj.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("事故统计情况表");
			sheet.createFreezePane(2,4,3,4);
		    sheet.setColumnWidth(0, 3000); 
	        sheet.setColumnWidth(1, 10000); 
	        sheet.setColumnWidth(2, 5000);
	        sheet.setColumnWidth(3, 5000);
	        sheet.setColumnWidth(4, 5000);
	        sheet.setColumnWidth(5, 5000);
	        sheet.setColumnWidth(6, 5000);
	        sheet.setColumnWidth(7, 5000);
	        sheet.setColumnWidth(8, 5000);
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
	        sheet.setColumnWidth(19, 5000);
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
	        
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("工矿商贸企业安全生产事故统计情况表");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 40)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (16*16));
	        cs.setFont(font);
	        
	        HSSFRow r3 = sheet.createRow(1);
	        r3.setHeight((short)(25*20));
	        HSSFCell cc1111 = r3.createCell(0);
	        cc1111.setCellValue("年度");
	        cc1111.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 3, (short) 0)); 
	        HSSFCell ccl0 = r3.createCell(1);
	        ccl0.setCellValue("企业名称");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 3, (short) 1)); 
	        HSSFCell ccl1 = r3.createCell(2);
	        ccl1.setCellValue("损工事故起数");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 2, 1, (short) 9)); 
	        HSSFCell ccl2 = r3.createCell(10);
	        ccl2.setCellValue("伤害事故分级统计");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 10, 1, (short) 21)); 
	        HSSFCell ccl3 = r3.createCell(22);
	        ccl3.setCellValue("平均职工人数");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 22, 2, (short) 22)); 
	        HSSFCell ccl4 = r3.createCell(23);
	        ccl4.setCellValue("实际工作日");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 23, 2, (short) 23)); 
	        HSSFCell ccl5 = r3.createCell(24);
	        ccl5.setCellValue("实际总工时");
	        ccl5.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 24, 2, (short) 24)); 
	        HSSFCell ccl6 = r3.createCell(25);
	        ccl6.setCellValue("百万工时损工事故率");
	        ccl6.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 25, 1, (short) 26)); 
	        HSSFCell ccl7 = r3.createCell(27);
	        ccl7.setCellValue("20万工时损工事故率");
	        ccl7.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 27, 1, (short) 28)); 
	        HSSFCell ccl8 = r3.createCell(29);
	        ccl8.setCellValue("不同事故等级中导致人员受到伤害情况分类小计（单位：人）");
	        ccl8.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 29, 1, (short) 40)); 
	        
	        
	        HSSFRow r4 = sheet.createRow(2);
	        r4.setHeight((short)(25*20));
	        HSSFCell cc20 = r4.createCell(2);
	        cc20.setCellValue("非计划停止作业");
	        cc20.setCellStyle(cs);
	        HSSFCell cc21 = r4.createCell(3);
	        cc21.setCellValue("损失工时");
	        cc21.setCellStyle(cs);
	        HSSFCell cc22 = r4.createCell(4);
	        cc22.setCellValue("一般伤害事故");
	        cc22.setCellStyle(cs);
	        HSSFCell cc23 = r4.createCell(5);
	        cc23.setCellValue("损失工时");
	        cc23.setCellStyle(cs);
	        HSSFCell cc24 = r4.createCell(6);
	        cc24.setCellValue("工伤事故");
	        cc24.setCellStyle(cs);
	        HSSFCell cc25 = r4.createCell(7);
	        cc25.setCellValue("损失工时");
	        cc25.setCellStyle(cs);
	        HSSFCell cc26 = r4.createCell(8);
	        cc26.setCellValue("损工事故起数");
	        cc26.setCellStyle(cs);
	        HSSFCell cc27 = r4.createCell(9);
	        cc27.setCellValue("损失工时");
	        cc27.setCellStyle(cs);
	        
	        HSSFCell cc28 = r4.createCell(10);
	        cc28.setCellValue("未遂事故");
	        cc28.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 10, 2, (short) 11)); 
	        HSSFCell cc29 = r4.createCell(12);
	        cc29.setCellValue("非损工事故");
	        cc29.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 12, 2, (short) 13)); 
	        HSSFCell cc230 = r4.createCell(14);
	        cc230.setCellValue("一般损工事故");
	        cc230.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 14, 2, (short) 15)); 
	        HSSFCell cc231 = r4.createCell(16);
	        cc231.setCellValue("轻伤事故");
	        cc231.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 16, 2, (short) 17)); 
	        HSSFCell cc232 = r4.createCell(18);
	        cc232.setCellValue("重伤事故");
	        cc232.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 18, 2, (short) 19)); 
	        HSSFCell cc233 = r4.createCell(20);
	        cc233.setCellValue("死亡事故");
	        cc233.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 20, 2, (short) 21)); 
	        
	        
	        
	        HSSFCell cc234 = r4.createCell(25);
	        cc234.setCellValue("损工事故率");
	        cc234.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 25, 3, (short) 25)); 
	        HSSFCell cc235 = r4.createCell(26);
	        cc235.setCellValue("损工事故严重率");
	        cc235.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 26, 3, (short) 26)); 
	        HSSFCell cc236 = r4.createCell(27);
	        cc236.setCellValue("损工事故率");
	        cc236.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 27, 3, (short) 27)); 
	        HSSFCell cc237 = r4.createCell(28);
	        cc237.setCellValue("损工事故严重率");
	        cc237.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 28, 3, (short) 28)); 
	        HSSFCell cc238 = r4.createCell(29);
	        cc238.setCellValue("擦伤");
	        cc238.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 29, 3, (short) 29)); 
	        HSSFCell cc239 = r4.createCell(30);
	        cc239.setCellValue("烧伤");
	        cc239.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 30, 3, (short) 30)); 
	        
	        
	        HSSFCell cc240 = r4.createCell(31);
	        cc240.setCellValue("辐射");
	        cc240.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 31, 3, (short) 31)); 
	        HSSFCell cc241 = r4.createCell(32);
	        cc241.setCellValue("化学灼伤");
	        cc241.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 32, 3, (short) 32)); 
	        HSSFCell cc242 = r4.createCell(33);
	        cc242.setCellValue("触电");
	        cc242.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 33, 3, (short) 33)); 
	        HSSFCell cc243 = r4.createCell(34);
	        cc243.setCellValue("骨折");
	        cc243.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 34, 3, (short) 34)); 
	        HSSFCell cc244 = r4.createCell(35);
	        cc244.setCellValue("内出血");
	        cc244.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 35, 3, (short) 35)); 
	        HSSFCell cc245 = r4.createCell(36);
	        cc245.setCellValue("中毒");
	        cc245.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 36, 3, (short) 36)); 
	        HSSFCell cc246 = r4.createCell(37);
	        cc246.setCellValue("扭伤");
	        cc246.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 37, 3, (short) 37)); 
	        HSSFCell cc247 = r4.createCell(38);
	        cc247.setCellValue("肌肉拉伤");
	        cc247.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 38, 3, (short) 38)); 
	        HSSFCell cc248 = r4.createCell(39);
	        cc248.setCellValue("割伤");
	        cc248.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 39, 3, (short) 39)); 
	        HSSFCell cc249 = r4.createCell(40);
	        cc249.setCellValue("其他");
	        cc249.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(2, (short) 40, 3, (short) 40)); 
	        
	        
	        HSSFRow r5 = sheet.createRow(3);
	        r5.setHeight((short)(25*20));
	        HSSFCell cc30 = r5.createCell(2);
	        cc30.setCellValue("起数");
	        cc30.setCellStyle(cs);
	        HSSFCell cc31 = r5.createCell(3);
	        cc31.setCellValue("小时");
	        cc31.setCellStyle(cs);
	        HSSFCell cc32 = r5.createCell(4);
	        cc32.setCellValue("起数");
	        cc32.setCellStyle(cs);
	        HSSFCell cc33 = r5.createCell(5);
	        cc33.setCellValue("小时");
	        cc33.setCellStyle(cs);
	        HSSFCell cc34 = r5.createCell(6);
	        cc34.setCellValue("起数");
	        cc34.setCellStyle(cs);
	        HSSFCell cc35 = r5.createCell(7);
	        cc35.setCellValue("小时");
	        cc35.setCellStyle(cs);
	        HSSFCell cc36 = r5.createCell(8);
	        cc36.setCellValue("小计");
	        cc36.setCellStyle(cs);
	        HSSFCell cc37 = r5.createCell(9);
	        cc37.setCellValue("小计");
	        cc37.setCellStyle(cs);
	        
	        HSSFCell cc38 = r5.createCell(10);
	        cc38.setCellValue("起数");
	        cc38.setCellStyle(cs);
	        HSSFCell cc39 = r5.createCell(11);
	        cc39.setCellValue("小时");
	        cc39.setCellStyle(cs);
	        HSSFCell cc310 = r5.createCell(12);
	        cc310.setCellValue("起数");
	        cc310.setCellStyle(cs);
	        HSSFCell cc311 = r5.createCell(13);
	        cc311.setCellValue("小时");
	        cc311.setCellStyle(cs);
	        HSSFCell cc312 = r5.createCell(14);
	        cc312.setCellValue("起数");
	        cc312.setCellStyle(cs);
	        HSSFCell cc313 = r5.createCell(15);
	        cc313.setCellValue("小时");
	        cc313.setCellStyle(cs);
	        HSSFCell cc314 = r5.createCell(16);
	        cc314.setCellValue("起数");
	        cc314.setCellStyle(cs);
	        HSSFCell cc315 = r5.createCell(17);
	        cc315.setCellValue("小时");
	        cc315.setCellStyle(cs);
	        HSSFCell cc316 = r5.createCell(18);
	        cc316.setCellValue("起数");
	        cc316.setCellStyle(cs);
	        HSSFCell cc317 = r5.createCell(19);
	        cc317.setCellValue("小时");
	        cc317.setCellStyle(cs);
	        HSSFCell cc318 = r5.createCell(20);
	        cc318.setCellValue("起数");
	        cc318.setCellStyle(cs);
	        HSSFCell cc319 = r5.createCell(21);
	        cc319.setCellValue("小时");
	        cc319.setCellStyle(cs);
	        
	        
	        HSSFCell cc320 = r5.createCell(22);
	        cc320.setCellValue("人数");
	        cc320.setCellStyle(cs);
	        HSSFCell cc321 = r5.createCell(23);
	        cc321.setCellValue("小时");
	        cc321.setCellStyle(cs);
	        HSSFCell cc322 = r5.createCell(24);
	        cc322.setCellValue("小时");
	        cc322.setCellStyle(cs);
	        
	        
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
        		String qymc = (String) getSessionAttribute("qymc");
        		String szzid = (String) getSessionAttribute("szzid");
        		paraMap.put("qymc", qymc);
        		paraMap.put("szzid", szzid);
        		queryYfEnd = (String) getSessionAttribute("queryYfEnd");
        		queryYfEnd = (String) getSessionAttribute("queryYfEnd");
    		}
	        if(null != jshxSgtj){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != jshxSgtj.getQymc()) && (0 < jshxSgtj.getQymc().trim().length())){
					paraMap.put("qymc", "%" + jshxSgtj.getQymc().trim() + "%");
					setSessionAttribute("qymc", "%" + jshxSgtj.getQymc().trim() + "%");
				}
				if ((null != jshxSgtj.getSzzid()) && (0 < jshxSgtj.getSzzid().trim().length())){
					paraMap.put("szzid",  jshxSgtj.getSzzid().trim() );
					setSessionAttribute("szzid", jshxSgtj.getSzzid().trim());
				}
			}
			if (null != queryYfStart && (0 < queryYfStart.trim().length())){
				paraMap.put("startYf", queryYfStart);
				setSessionAttribute("queryYfStart", queryYfStart);
			}

			if (null != queryYfEnd && (0 < queryYfEnd.trim().length())){
				paraMap.put("endYf", queryYfEnd);
				setSessionAttribute("queryYfEnd", queryYfEnd);
			}

			//hanxc 20141223 修改查询条件 start
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			//hanxc 20141223 修改查询条件 end
			 
			List<JshxSgtj> list = jshxSgtjService.findJshxSgtj(paraMap);
			int num = 4;
			double data1 = 0;
			double data2 = 0;
			double data3 = 0;
			double data4 = 0;
			double data5 = 0;
			double data6 = 0;
			double data7 = 0;
			double data8 = 0;
			double data9 = 0;
			double data10 = 0;
			double data11 = 0;
			double data12 = 0;
			double data13 = 0;
			double data14 = 0;
			double data15 = 0;
			double data16 = 0;
			double data17 = 0;
			double data18 = 0;
			double data19 = 0;
			double data20 = 0;
			double data21 = 0;
			double data22 = 0;
			double data23 = 0;
			double data24 = 0;
			double data25 = 0;
			double data26 = 0;
			double data27 = 0;
			double data28 = 0;
			double data29 = 0;
			double data30 = 0;
			double data31 = 0;
			double data32 = 0;
			double data33 = 0;
			double data34 = 0;
			double data35 = 0;
			double data36 = 0;
			double data37 = 0;
			double data38 = 0;
			double data39 = 0;
			for(JshxSgtj jshxSgtj:list)
			{
				Map map = new HashMap();
				map.put("linkId", jshxSgtj.getId());
				sgtjDatas = sgtjDataService.findSgtjData(paraMap);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(jshxSgtj.getQynd());
				ce0.setCellStyle(c);
				HSSFCell ce1 = ro.createCell(1);
				ce1.setCellValue(jshxSgtj.getQymc());
				ce1.setCellStyle(c);
				HSSFCell ce2 = ro.createCell(2);
				ce2.setCellValue(jshxSgtj.getFjhqs());
				ce2.setCellStyle(c);
				data1 += Double.valueOf(jshxSgtj.getFjhqs());
				HSSFCell ce3 = ro.createCell(3);
				ce3.setCellValue(jshxSgtj.getFjhxs());
				ce3.setCellStyle(c);
				data2 += Double.valueOf(jshxSgtj.getFjhxs());
				HSSFCell ce4 = ro.createCell(4);
				ce4.setCellValue(jshxSgtj.getShsgqs());
				ce4.setCellStyle(c);
				data3 += Double.valueOf(jshxSgtj.getShsgqs());
				HSSFCell ce5 = ro.createCell(5);
				ce5.setCellValue(jshxSgtj.getShsgxs());
				ce5.setCellStyle(c);
				data4 += Double.valueOf(jshxSgtj.getShsgxs());
				HSSFCell ce6 = ro.createCell(6);
				ce6.setCellValue(jshxSgtj.getSgsgqs());
				ce6.setCellStyle(c);
				data5 += Double.valueOf(jshxSgtj.getSgsgqs());
				HSSFCell ce7 = ro.createCell(7);
				ce7.setCellValue(jshxSgtj.getSgsgxs());
				ce7.setCellStyle(c);
				data6 += Double.valueOf(jshxSgtj.getSgsgxs());
				HSSFCell ce8 = ro.createCell(8);
				ce8.setCellValue(jshxSgtj.getSgzqs());
				ce8.setCellStyle(c);
				data7 += Double.valueOf(jshxSgtj.getSgzqs());
				HSSFCell ce9 = ro.createCell(9);
				ce9.setCellValue(jshxSgtj.getSszgs());
				ce9.setCellStyle(c);
				data8 += Double.valueOf(jshxSgtj.getSszgs());
				HSSFCell ce10 = ro.createCell(10);
				ce10.setCellValue(sgtjDatas.get(0).getData_1());
				ce10.setCellStyle(c);
				data9 += Double.valueOf(sgtjDatas.get(0).getData_1());
				HSSFCell ce11 = ro.createCell(11);
				ce11.setCellValue(sgtjDatas.get(1).getData_1());
				ce11.setCellStyle(c);
				data10 += Double.valueOf(sgtjDatas.get(1).getData_1());
				HSSFCell ce12 = ro.createCell(12);
				ce12.setCellValue(sgtjDatas.get(2).getData_1());
				ce12.setCellStyle(c);
				data11 += Double.valueOf(sgtjDatas.get(2).getData_1());
				HSSFCell ce13 = ro.createCell(13);
				ce13.setCellValue(sgtjDatas.get(3).getData_1());
				ce13.setCellStyle(c);
				data12 += Double.valueOf(sgtjDatas.get(3).getData_1());
				HSSFCell ce14 = ro.createCell(14);
				ce14.setCellValue(sgtjDatas.get(4).getData_1());
				ce14.setCellStyle(c);
				data13 += Double.valueOf(sgtjDatas.get(4).getData_1());
				HSSFCell ce15 = ro.createCell(15);
				ce15.setCellValue(sgtjDatas.get(5).getData_1());
				ce15.setCellStyle(c);
				data14 += Double.valueOf(sgtjDatas.get(5).getData_1());
				HSSFCell ce16 = ro.createCell(16);
				ce16.setCellValue(sgtjDatas.get(6).getData_1());
				ce16.setCellStyle(c);
				data15 += Double.valueOf(sgtjDatas.get(6).getData_1());
				HSSFCell ce17 = ro.createCell(17);
				ce17.setCellValue(sgtjDatas.get(7).getData_1());
				ce17.setCellStyle(c);
				data16 += Double.valueOf(sgtjDatas.get(7).getData_1());
				HSSFCell ce18 = ro.createCell(18);
				ce18.setCellValue(sgtjDatas.get(8).getData_1());
				ce18.setCellStyle(c);
				data17 += Double.valueOf(sgtjDatas.get(8).getData_1());
				HSSFCell ce19 = ro.createCell(19);
				ce19.setCellValue(sgtjDatas.get(9).getData_1());
				ce19.setCellStyle(c);
				data18 += Double.valueOf(sgtjDatas.get(9).getData_1());
				HSSFCell ce20 = ro.createCell(20);
				ce20.setCellValue(sgtjDatas.get(10).getData_1());
				ce20.setCellStyle(c);
				data19 += Double.valueOf(sgtjDatas.get(10).getData_1());
				HSSFCell ce21 = ro.createCell(21);
				ce21.setCellValue(sgtjDatas.get(11).getData_1());
				ce21.setCellStyle(c);
				data20 += Double.valueOf(sgtjDatas.get(11).getData_1());
				HSSFCell ce22 = ro.createCell(22);
				ce22.setCellValue(jshxSgtj.getZgs());
				ce22.setCellStyle(c);
				data21 += Double.valueOf(jshxSgtj.getZgs());
				HSSFCell ce23 = ro.createCell(23);
				ce23.setCellValue(jshxSgtj.getSjgzr());
				ce23.setCellStyle(c);
				data22 += Double.valueOf(jshxSgtj.getSjgzr());
				HSSFCell ce24 = ro.createCell(24);
				ce24.setCellValue(jshxSgtj.getSjgs());
				ce24.setCellStyle(c);
				data23 += Double.valueOf(jshxSgtj.getSjgs());
				HSSFCell ce25 = ro.createCell(25);
				ce25.setCellValue(jshxSgtj.getSgl());
				ce25.setCellStyle(c);
				data24 += Double.valueOf(jshxSgtj.getSgl());
				HSSFCell ce26 = ro.createCell(26);
				ce26.setCellValue(jshxSgtj.getYzl());
				ce26.setCellStyle(c);
				data25 += Double.valueOf(jshxSgtj.getYzl());
				HSSFCell ce27 = ro.createCell(27);
				ce27.setCellValue(jshxSgtj.getSgl2());
				ce27.setCellStyle(c);
				data26 += Double.valueOf(jshxSgtj.getSgl2());
				HSSFCell ce28 = ro.createCell(28);
				ce28.setCellValue(jshxSgtj.getYzl2());
				ce28.setCellStyle(c);
				data27 += Double.valueOf(jshxSgtj.getYzl2());
				HSSFCell ce29 = ro.createCell(29);
				double cs1 = Double.valueOf(sgtjDatas.get(1).getData_2())+Double.valueOf(sgtjDatas.get(3).getData_2())
				+Double.valueOf(sgtjDatas.get(5).getData_2())+Double.valueOf(sgtjDatas.get(7).getData_2())+
				Double.valueOf(sgtjDatas.get(9).getData_2())+Double.valueOf(sgtjDatas.get(11).getData_2());
				ce29.setCellValue(cs1);
				ce29.setCellStyle(c);
				data28 += Double.valueOf(cs1);
				HSSFCell ce30 = ro.createCell(30);
				double cs2 = Double.valueOf(sgtjDatas.get(1).getData_3())+Double.valueOf(sgtjDatas.get(3).getData_3())
				+Double.valueOf(sgtjDatas.get(5).getData_3())+Double.valueOf(sgtjDatas.get(7).getData_3())+
				Double.valueOf(sgtjDatas.get(9).getData_3())+Double.valueOf(sgtjDatas.get(11).getData_3());
				ce30.setCellValue(cs2);
				ce30.setCellStyle(c);
				data29 += Double.valueOf(cs2);
				HSSFCell ce31 = ro.createCell(31);
				double cs3 = Double.valueOf(sgtjDatas.get(1).getData_4())+Double.valueOf(sgtjDatas.get(3).getData_4())
				+Double.valueOf(sgtjDatas.get(5).getData_4())+Double.valueOf(sgtjDatas.get(7).getData_4())+
				Double.valueOf(sgtjDatas.get(9).getData_4())+Double.valueOf(sgtjDatas.get(11).getData_4());
				ce31.setCellValue(cs3);
				ce31.setCellStyle(c);
				data30 += Double.valueOf(cs3);
				HSSFCell ce32 = ro.createCell(32);
				double cs4 = Double.valueOf(sgtjDatas.get(1).getData_5())+Double.valueOf(sgtjDatas.get(3).getData_5())
				+Double.valueOf(sgtjDatas.get(5).getData_5())+Double.valueOf(sgtjDatas.get(7).getData_5())+
				Double.valueOf(sgtjDatas.get(9).getData_5())+Double.valueOf(sgtjDatas.get(11).getData_5());
				ce32.setCellValue(cs4);
				ce32.setCellStyle(c);
				data31 += Double.valueOf(cs4);
				HSSFCell ce33 = ro.createCell(33);
				double cs5 = Double.valueOf(sgtjDatas.get(1).getData_6())+Double.valueOf(sgtjDatas.get(3).getData_6())
				+Double.valueOf(sgtjDatas.get(5).getData_6())+Double.valueOf(sgtjDatas.get(7).getData_6())+
				Double.valueOf(sgtjDatas.get(9).getData_6())+Double.valueOf(sgtjDatas.get(11).getData_6());
				ce33.setCellValue(cs5);
				ce33.setCellStyle(c);
				data32 += Double.valueOf(cs5);
				HSSFCell ce34 = ro.createCell(34);
				double cs6 = Double.valueOf(sgtjDatas.get(1).getData_7())+Double.valueOf(sgtjDatas.get(3).getData_7())
				+Double.valueOf(sgtjDatas.get(5).getData_7())+Double.valueOf(sgtjDatas.get(7).getData_7())+
				Double.valueOf(sgtjDatas.get(9).getData_7())+Double.valueOf(sgtjDatas.get(11).getData_7());
				ce34.setCellValue(cs6);
				ce34.setCellStyle(c);
				data33 += Double.valueOf(cs6);
				HSSFCell ce35 = ro.createCell(35);
				double cs7 = Double.valueOf(sgtjDatas.get(1).getData_8())+Double.valueOf(sgtjDatas.get(3).getData_8())
				+Double.valueOf(sgtjDatas.get(5).getData_8())+Double.valueOf(sgtjDatas.get(7).getData_8())+
				Double.valueOf(sgtjDatas.get(9).getData_8())+Double.valueOf(sgtjDatas.get(11).getData_8());
				ce35.setCellValue(cs7);
				ce35.setCellStyle(c);
				data34 += Double.valueOf(cs7);
				HSSFCell ce36 = ro.createCell(36);
				double cs8 = Double.valueOf(sgtjDatas.get(1).getData_9())+Double.valueOf(sgtjDatas.get(3).getData_9())
				+Double.valueOf(sgtjDatas.get(5).getData_9())+Double.valueOf(sgtjDatas.get(7).getData_9())+
				Double.valueOf(sgtjDatas.get(9).getData_9())+Double.valueOf(sgtjDatas.get(11).getData_9());
				ce36.setCellValue(cs8);
				ce36.setCellStyle(c);
				data35 += Double.valueOf(cs8);
				HSSFCell ce37 = ro.createCell(37);
				double cs9 = Double.valueOf(sgtjDatas.get(1).getData_10())+Double.valueOf(sgtjDatas.get(3).getData_10())
				+Double.valueOf(sgtjDatas.get(5).getData_10())+Double.valueOf(sgtjDatas.get(7).getData_10())+
				Double.valueOf(sgtjDatas.get(9).getData_10())+Double.valueOf(sgtjDatas.get(11).getData_10());
				ce37.setCellValue(cs9);
				ce37.setCellStyle(c);
				data36 += Double.valueOf(cs9);
				HSSFCell ce38 = ro.createCell(38);
				double cs10 = Double.valueOf(sgtjDatas.get(1).getData_11())+Double.valueOf(sgtjDatas.get(3).getData_11())
				+Double.valueOf(sgtjDatas.get(5).getData_11())+Double.valueOf(sgtjDatas.get(7).getData_11())+
				Double.valueOf(sgtjDatas.get(9).getData_11())+Double.valueOf(sgtjDatas.get(11).getData_11());
				ce38.setCellValue(cs10);
				ce38.setCellStyle(c);
				data37 += Double.valueOf(cs10);
				HSSFCell ce39 = ro.createCell(39);
				double cs11 = Double.valueOf(sgtjDatas.get(1).getData_12())+Double.valueOf(sgtjDatas.get(3).getData_12())
				+Double.valueOf(sgtjDatas.get(5).getData_12())+Double.valueOf(sgtjDatas.get(7).getData_12())+
				Double.valueOf(sgtjDatas.get(9).getData_12())+Double.valueOf(sgtjDatas.get(11).getData_12());
				ce39.setCellValue(cs11);
				ce39.setCellStyle(c);
				data38 += Double.valueOf(cs11);
				HSSFCell ce40 = ro.createCell(40);
				double cs12 = Double.valueOf(sgtjDatas.get(1).getData_13())+Double.valueOf(sgtjDatas.get(3).getData_13())
				+Double.valueOf(sgtjDatas.get(5).getData_13())+Double.valueOf(sgtjDatas.get(7).getData_13())+
				Double.valueOf(sgtjDatas.get(9).getData_13())+Double.valueOf(sgtjDatas.get(11).getData_13());
				ce40.setCellValue(cs12);
				ce40.setCellStyle(c);
				data39 += Double.valueOf(cs12);
		        num++;
			}
			HSSFRow ro = sheet.createRow(num);
			HSSFCell ce0 = ro.createCell(0);
			ce0.setCellValue("合计");
			ce0.setCellStyle(c);
			HSSFCell ce1 = ro.createCell(1);
			ce1.setCellValue(list.size());
			ce1.setCellStyle(c);
			HSSFCell ce2 = ro.createCell(2);
			ce2.setCellValue(data1);
			ce2.setCellStyle(c);
			HSSFCell ce3 = ro.createCell(3);
			ce3.setCellValue(data2);
			ce3.setCellStyle(c);
			HSSFCell ce4 = ro.createCell(4);
			ce4.setCellValue(data3);
			ce4.setCellStyle(c);
			HSSFCell ce5 = ro.createCell(5);
			ce5.setCellValue(data4);
			ce5.setCellStyle(c);
			HSSFCell ce6 = ro.createCell(6);
			ce6.setCellValue(data5);
			ce6.setCellStyle(c);
			HSSFCell ce7 = ro.createCell(7);
			ce7.setCellValue(data6);
			ce7.setCellStyle(c);
			HSSFCell ce8 = ro.createCell(8);
			ce8.setCellValue(data7);
			ce8.setCellStyle(c);
			HSSFCell ce9 = ro.createCell(9);
			ce9.setCellValue(data8);
			ce9.setCellStyle(c);
			HSSFCell ce10 = ro.createCell(10);
			ce10.setCellValue(data9);
			ce10.setCellStyle(c);
			HSSFCell ce11 = ro.createCell(11);
			ce11.setCellValue(data10);
			ce11.setCellStyle(c);
			HSSFCell ce12 = ro.createCell(12);
			ce12.setCellValue(data11);
			ce12.setCellStyle(c);
			HSSFCell ce13 = ro.createCell(13);
			ce13.setCellValue(data12);
			ce13.setCellStyle(c);
			HSSFCell ce14 = ro.createCell(14);
			ce14.setCellValue(data13);
			ce14.setCellStyle(c);
			HSSFCell ce15 = ro.createCell(15);
			ce15.setCellValue(data14);
			ce15.setCellStyle(c);
			HSSFCell ce16 = ro.createCell(16);
			ce16.setCellValue(data15);
			ce16.setCellStyle(c);
			HSSFCell ce17 = ro.createCell(17);
			ce17.setCellValue(data16);
			ce17.setCellStyle(c);
			HSSFCell ce18 = ro.createCell(18);
			ce18.setCellValue(data17);
			ce18.setCellStyle(c);
			HSSFCell ce19 = ro.createCell(19);
			ce19.setCellValue(data18);
			ce19.setCellStyle(c);
			HSSFCell ce20 = ro.createCell(20);
			ce20.setCellValue(data19);
			ce20.setCellStyle(c);
			HSSFCell ce21 = ro.createCell(21);
			ce21.setCellValue(data20);
			ce21.setCellStyle(c);
			HSSFCell ce22 = ro.createCell(22);
			ce22.setCellValue(data21);
			ce22.setCellStyle(c);
			HSSFCell ce23 = ro.createCell(23);
			ce23.setCellValue(data22);
			ce23.setCellStyle(c);
			HSSFCell ce24 = ro.createCell(24);
			ce24.setCellValue(data23);
			ce24.setCellStyle(c);
			HSSFCell ce25 = ro.createCell(25);
			ce25.setCellValue(data24);
			ce25.setCellStyle(c);
			HSSFCell ce26 = ro.createCell(26);
			ce26.setCellValue(data25);
			ce26.setCellStyle(c);
			HSSFCell ce27 = ro.createCell(27);
			ce27.setCellValue(data26);
			ce27.setCellStyle(c);
			HSSFCell ce28 = ro.createCell(28);
			ce28.setCellValue(data27);
			ce28.setCellStyle(c);
			HSSFCell ce29 = ro.createCell(29);
			ce29.setCellValue(data28);
			ce29.setCellStyle(c);
			HSSFCell ce30 = ro.createCell(30);
			ce30.setCellValue(data29);
			ce30.setCellStyle(c);
			HSSFCell ce31 = ro.createCell(31);
			ce31.setCellValue(data30);
			ce31.setCellStyle(c);
			HSSFCell ce32 = ro.createCell(32);
			ce32.setCellValue(data31);
			ce32.setCellStyle(c);
			HSSFCell ce33 = ro.createCell(33);
			ce33.setCellValue(data32);
			ce33.setCellStyle(c);
			HSSFCell ce34 = ro.createCell(34);
			ce34.setCellValue(data33);
			ce34.setCellStyle(c);
			HSSFCell ce35 = ro.createCell(35);
			ce35.setCellValue(data34);
			ce35.setCellStyle(c);
			HSSFCell ce36 = ro.createCell(36);
			ce36.setCellValue(data35);
			ce36.setCellStyle(c);
			HSSFCell ce37 = ro.createCell(37);
			ce37.setCellValue(data36);
			ce37.setCellStyle(c);
			HSSFCell ce38 = ro.createCell(38);
			ce38.setCellValue(data37);
			ce38.setCellStyle(c);
			HSSFCell ce39 = ro.createCell(39);
			ce39.setCellValue(data38);
			ce39.setCellStyle(c);
			HSSFCell ce40 = ro.createCell(40);
			ce40.setCellValue(data39);
			ce40.setCellStyle(c);
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

	public JshxSgtj getJshxSgtj(){
		return this.jshxSgtj;
	}

	public void setJshxSgtj(JshxSgtj jshxSgtj){
		this.jshxSgtj = jshxSgtj;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public SgtjData getSgtjData() {
		return sgtjData;
	}

	public void setSgtjData(SgtjData sgtjData) {
		this.sgtjData = sgtjData;
	}

	public String[] getDatas() {
		return datas;
	}

	public void setDatas(String[] datas) {
		this.datas = datas;
	}

	public List<SgtjData> getSgtjDatas() {
		return sgtjDatas;
	}

	public void setSgtjDatas(List<SgtjData> sgtjDatas) {
		this.sgtjDatas = sgtjDatas;
	}

	public String getQueryYfStart() {
		return queryYfStart;
	}

	public void setQueryYfStart(String queryYfStart) {
		this.queryYfStart = queryYfStart;
	}

	public String getQueryYfEnd() {
		return queryYfEnd;
	}

	public void setQueryYfEnd(String queryYfEnd) {
		this.queryYfEnd = queryYfEnd;
	}
       
    
}
