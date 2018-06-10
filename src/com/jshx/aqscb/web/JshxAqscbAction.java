/**
 * Class Name: JshxAqglbAction
 * Class Description：安全生产经费
 */
package com.jshx.aqscb.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.jshx.aqscData.entity.AqscData;
import com.jshx.aqscData.service.AqscDataService;
import com.jshx.aqscb.entity.JshxAqscb;
import com.jshx.aqscb.service.JshxAqscbService;
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

public class JshxAqscbAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private JshxAqscb jshxAqscb = new JshxAqscb();
	
	private AqscData aqscData = new AqscData();
	
	private AqscData aqscData01 = new AqscData();
	private AqscData aqscData02 = new AqscData();
	private AqscData aqscData03 = new AqscData();

	/**
	 * 业务类
	 */
	@Autowired
	private JshxAqscbService jshxAqscbService;
	@Autowired
	private AqscDataService aqscDataService;//保存关联数据service
	@Autowired
	private CompanyService companyService;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private DeptService deptService;
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
	 * 最近上报月份
	 */
	private String beforeMonth;

	/**
	 * 本次需上报的月份
	 */
	private String month;
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
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
	 * 根据查询条件执行安全生产管理机构文件列表查询的方法，返回json格式数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != jshxAqscb){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != jshxAqscb.getQymc()) && (0 < jshxAqscb.getQymc().trim().length())){
				paraMap.put("qymc", "%" + jshxAqscb.getQymc().trim() + "%");
			}
			if ((null != jshxAqscb.getQyfzr()) && (0 < jshxAqscb.getQyfzr().trim().length())){
				paraMap.put("qyfzr", "%" + jshxAqscb.getQyfzr().trim() + "%");
			}

			if ((null != jshxAqscb.getTbr()) && (0 < jshxAqscb.getTbr().trim().length())){
				paraMap.put("tbr", "%" + jshxAqscb.getTbr().trim() + "%");
			}

			if ((null != jshxAqscb.getSzzid()) && (0 < jshxAqscb.getSzzid().trim().length())){
				paraMap.put("deptCode",  jshxAqscb.getSzzid().trim() );
			}
			if ((null != jshxAqscb.getSzc() )&& (0 < jshxAqscb.getSzc().trim().length())){
				paraMap.put("szc",jshxAqscb.getSzc().trim());
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
		 
		pagination = jshxAqscbService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看某条安全生产管理机构文件详细信息
	 */
	public String view() throws Exception{
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("createUserID", this.getLoginUserId());
			beforeMonth = jshxAqscbService.getLaterMonth(map);//获取企业最近一次上报的记录
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			if(beforeMonth!=null&&beforeMonth.length()==7){
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(sdf.parse(beforeMonth));
				cal1.add(Calendar.MONTH, 1);
				Date d = cal1.getTime();
				month = sdf.format(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//根据月份加1得到本次需要上报的月份
		if((null != jshxAqscb)&&(null != jshxAqscb.getId())){
			jshxAqscb = jshxAqscbService.getById(jshxAqscb.getId());
			Map<String, Object> paraMap = new HashMap<String, Object>();
	
			if(pagination==null)
			    pagination = new Pagination(this.getRequest());
			    
			paraMap.put("linkId", jshxAqscb.getId());
			
			pagination = aqscDataService.findByPage(pagination, paraMap);
			List<AqscData> datas = pagination.getListOfObject();
			if(datas!=null&&!datas.isEmpty()){
				for(AqscData data:datas){
					if(data!=null){
						if("1".equals(data.getSort())){
							aqscData01 = data;
						}else if("2".equals(data.getSort())){
							aqscData02 = data;
						}else if("3".equals(data.getSort())){
							aqscData03 = data;
						}
					}
				}
			}
		}else if((null != jshxAqscb)&&(month!=null)&&(month.length()==7)){
			jshxAqscb.setJshxYear(month);
			jshxAqscb.setTbr(this.getLoginUser().getDisplayName());
		}
		return VIEW;
	}

	/**
	 * 初始化安全生产管理机构文件某条修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存安全生产管理机构文件信息（包括新增和修改）
	 */
	public String save() throws Exception{
		try {
			CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
			if ("add".equalsIgnoreCase(this.flag)){
				try {
					Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
					jshxAqscb.setSzzid(dept.getDeptCode());
					jshxAqscb.setSzzname(dept.getDeptName());
					jshxAqscb.setQyid(company.getId());
					jshxAqscb.setQymc(company.getCompanyname());
					jshxAqscb.setDeptId(this.getLoginUserDepartmentId());
					jshxAqscb.setDelFlag(0);
					jshxAqscb.setCreateUserID(this.getLoginUserId());
					jshxAqscb.setCreateTime(new Date());
					jshxAqscb.setQylx(company.getQylx());
					jshxAqscb.setHyfl(company.getHyfl());
					jshxAqscb.setQygm(company.getQygm());
					jshxAqscb.setQyzclx(company.getQyzclx());
					jshxAqscb.setDeptId(this.getLoginUserDepartmentId());
					jshxAqscb.setDelFlag(0);

					jshxAqscb.setIfwhpqylx(company.getIfwhpqylx());
					jshxAqscb.setIfyhbzjyqy(company.getIfyhbzjyqy());
					jshxAqscb.setIfzywhqylx(company.getIfzywhqylx());
					jshxAqscb.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
					jshxAqscb.setSzc(company.getSzc());
					jshxAqscb.setSzcname(company.getSzcname());
				} catch (RuntimeException e) {
					e.printStackTrace();
				}//企业名称
				jshxAqscb.setTbr(this.getLoginUser().getDisplayName());//填报人
				jshxAqscb.setTbsj(new Date());//填报时间
				jshxAqscb.setDeptId(this.getLoginUserDepartmentId());
				jshxAqscb.setDelFlag(0);
				jshxAqscb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				jshxAqscb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				jshxAqscbService.save(jshxAqscb);
				aqscDataService.save(convertObjectData(jshxAqscb.getId(),0,this.flag));
				aqscDataService.save(convertObjectData(jshxAqscb.getId(),1,this.flag));
				aqscDataService.save(convertObjectData(jshxAqscb.getId(),2,this.flag));
			}else{
				jshxAqscb.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
				jshxAqscb.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
				jshxAqscbService.update(jshxAqscb);
				aqscDataService.update(convertObjectData(jshxAqscb.getId(),0,null));
				aqscDataService.update(convertObjectData(jshxAqscb.getId(),1,null));
				aqscDataService.update(convertObjectData(jshxAqscb.getId(),2,null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return RELOAD;
	}
	/**
	 * 给指定对象赋值 李军 2013-08-19
	 */
	public  AqscData convertObjectData(String linkId,int index,String flag){
		AqscData aqsc = new AqscData();
		aqsc.setLinkid(linkId);
		aqsc.setSort((index+1)+"");
		aqsc.setDelFlag(0);
		if(!"add".equals(flag)){
			aqsc.setId(StringTools.getStrByIndex(aqscData.getId(),index));
		}
		aqsc.setData_1(StringTools.getStrByIndex(aqscData.getData_1(),index));
		aqsc.setData_2(StringTools.getStrByIndex(aqscData.getData_2(),index));
		aqsc.setData_3(StringTools.getStrByIndex(aqscData.getData_3(),index));
		aqsc.setData_4(StringTools.getStrByIndex(aqscData.getData_4(),index));
		aqsc.setData_5(StringTools.getStrByIndex(aqscData.getData_5(),index));
		aqsc.setData_6(StringTools.getStrByIndex(aqscData.getData_6(),index));
		aqsc.setData_7(StringTools.getStrByIndex(aqscData.getData_7(),index));
		aqsc.setData_8(StringTools.getStrByIndex(aqscData.getData_8(),index));
		aqsc.setData_9(StringTools.getStrByIndex(aqscData.getData_9(),index));
		aqsc.setData_10(StringTools.getStrByIndex(aqscData.getData_10(),index));
		aqsc.setData_11(StringTools.getStrByIndex(aqscData.getData_11(),index));
		aqsc.setData_12(StringTools.getStrByIndex(aqscData.getData_12(),index));
		aqsc.setData_13(StringTools.getStrByIndex(aqscData.getData_13(),index));
		aqsc.setData_14(StringTools.getStrByIndex(aqscData.getData_14(),index));
		aqsc.setData_15(StringTools.getStrByIndex(aqscData.getData_15(),index));
		aqsc.setData_16(StringTools.getStrByIndex(aqscData.getData_16(),index));
		aqsc.setData_17(StringTools.getStrByIndex(aqscData.getData_17(),index));
		aqsc.setData_18(StringTools.getStrByIndex(aqscData.getData_18(),index));
		aqsc.setData_19(StringTools.getStrByIndex(aqscData.getData_19(),index));
		aqsc.setData_20(StringTools.getStrByIndex(aqscData.getData_20(),index));
		aqsc.setData_21(StringTools.getStrByIndex(aqscData.getData_21(),index));
		aqsc.setData_22(StringTools.getStrByIndex(aqscData.getData_22(),index));
		return aqsc;
	} 
	/**
	 * 根据id删除安全生产管理机构文件信息
	 */
	public String delete() throws Exception{
	    try{
			jshxAqscbService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 导出安全生产经费信息
	 * author：陆婷
	 * 2013-11-8
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=aqscjf.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("安全生产经费投入情况统计");
		    sheet.setColumnWidth(0, 10000); 
	        sheet.setColumnWidth(1, 15000); 
	        sheet.setColumnWidth(2, 10000);
	        sheet.setColumnWidth(3, 10000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("安全生产经费投入情况统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 3)); 
	        
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
	        ccl0.setCellValue("类别");
	        ccl0.setCellStyle(cs);
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("项目");
	        ccl1.setCellStyle(cs);
	        HSSFCell ccl2 = r3.createCell(2);
	        ccl2.setCellValue("本年实际支出数额（万元）");
	        ccl2.setCellStyle(cs);
	        HSSFCell ccl3 = r3.createCell(3);
	        ccl3.setCellValue("上年实际支出数额（万元）");
	        ccl3.setCellStyle(cs);
	        
	        
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
        		String qyfzr = (String) getSessionAttribute("qyfzr");
        		String tbr = (String) getSessionAttribute("tbr");
        		String szzid = (String) getSessionAttribute("szzid");
        		paraMap.put("qymc", qymc);
        		paraMap.put("qyfzr", qyfzr);
        		paraMap.put("tbr", tbr);
        		paraMap.put("szzid", szzid);
        		queryYfStart = (String) getSessionAttribute("queryYfStart");
        		queryYfEnd = (String) getSessionAttribute("queryYfEnd");
    		}
	        if(null != jshxAqscb){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != jshxAqscb.getQymc()) && (0 < jshxAqscb.getQymc().trim().length())){
					paraMap.put("qymc", "%" + jshxAqscb.getQymc().trim() + "%");
					setSessionAttribute("qymc", "%" + jshxAqscb.getQymc().trim() + "%");
				}
				if ((null != jshxAqscb.getQyfzr()) && (0 < jshxAqscb.getQyfzr().trim().length())){
					paraMap.put("qyfzr", "%" + jshxAqscb.getQyfzr().trim() + "%");
					setSessionAttribute("qyfzr", "%" + jshxAqscb.getQyfzr().trim() + "%");
				}

				if ((null != jshxAqscb.getTbr()) && (0 < jshxAqscb.getTbr().trim().length())){
					paraMap.put("tbr", "%" + jshxAqscb.getTbr().trim() + "%");
					setSessionAttribute("tbr", "%" + jshxAqscb.getTbr().trim() + "%");
				}

				if ((null != jshxAqscb.getSzzid()) && (0 < jshxAqscb.getSzzid().trim().length())){
					paraMap.put("szzid",  jshxAqscb.getSzzid().trim() );
					setSessionAttribute("szzid",  jshxAqscb.getSzzid().trim() );
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
			 
			List<String> proid = jshxAqscbService.getAqscIdsByMap(paraMap);
			Map map = new HashMap(); 
			map.put("proids", proid);
			map.put("sort", "1");
			AqscData aqscData01 = aqscDataService.getAqscDataByMap(map);
			map.put("sort", "2");
			AqscData aqscData02 = aqscDataService.getAqscDataByMap(map);
			HSSFRow ro1 = sheet.createRow(2);
			HSSFCell ce10 = ro1.createCell(0);
			ce10.setCellValue("安全设施");
			ce10.setCellStyle(c);
			sheet.addMergedRegion(new Region(2, (short) 0, 6, (short) 0)); 
	        HSSFCell ce11 = ro1.createCell(1);
	        ce11.setCellValue("消防设施配备及维护费用");
	        ce11.setCellStyle(c);
	        HSSFCell ce12 = ro1.createCell(2);
			ce12.setCellValue(aqscData01.getData_1());
			ce12.setCellStyle(c);
	        HSSFCell ce13 = ro1.createCell(3);
	        ce13.setCellValue(aqscData02.getData_1());
	        ce13.setCellStyle(c);
	        
	        HSSFRow ro2 = sheet.createRow(3);
	        HSSFCell ce21 = ro2.createCell(1);
	        ce21.setCellValue("自控联锁和监控报警装置安装及维护费用");
	        ce21.setCellStyle(c);
	        HSSFCell ce22 = ro2.createCell(2);
			ce22.setCellValue(aqscData01.getData_2());
			ce22.setCellStyle(c);
	        HSSFCell ce23 = ro2.createCell(3);
	        ce23.setCellValue(aqscData02.getData_2());
	        ce23.setCellStyle(c);
	        
	        
	        HSSFRow ro3 = sheet.createRow(4);
	        HSSFCell ce31 = ro3.createCell(1);
	        ce31.setCellValue("防雷接地设施安装及维护费用");
	        ce31.setCellStyle(c);
	        HSSFCell ce32 = ro3.createCell(2);
			ce32.setCellValue(aqscData01.getData_3());
			ce32.setCellStyle(c);
	        HSSFCell ce33 = ro3.createCell(3);
	        ce33.setCellValue(aqscData02.getData_3());
	        ce33.setCellStyle(c);
	        
	        HSSFRow ro4 = sheet.createRow(5);
	        HSSFCell ce41 = ro4.createCell(1);
	        ce41.setCellValue("特种设备安全维护和检测费用");
	        ce41.setCellStyle(c);
	        HSSFCell ce42 = ro4.createCell(2);
			ce42.setCellValue(aqscData01.getData_4());
			ce42.setCellStyle(c);
	        HSSFCell ce43 = ro4.createCell(3);
	        ce43.setCellValue(aqscData02.getData_4());
	        ce43.setCellStyle(c);
	        
	        HSSFRow ro5 = sheet.createRow(6);
	        HSSFCell ce51 = ro5.createCell(1);
	        ce51.setCellValue("安全防护装置安装及维护费用");
	        ce51.setCellStyle(c);
	        HSSFCell ce52 = ro5.createCell(2);
			ce52.setCellValue(aqscData01.getData_5());
			ce52.setCellStyle(c);
	        HSSFCell ce53 = ro5.createCell(3);
	        ce53.setCellValue(aqscData02.getData_5());
	        ce53.setCellStyle(c);
	        
			HSSFRow ro6 = sheet.createRow(7);
			HSSFCell ce60 = ro6.createCell(0);
			ce60.setCellValue("安全活动");
			ce60.setCellStyle(c);
			sheet.addMergedRegion(new Region(7, (short) 0, 10, (short) 0)); 
	        HSSFCell ce61 = ro6.createCell(1);
	        ce61.setCellValue("安全培训和宣传教育活动经费");
	        ce61.setCellStyle(c);
	        HSSFCell ce62 = ro6.createCell(2);
			ce62.setCellValue(aqscData01.getData_6());
			ce62.setCellStyle(c);
	        HSSFCell ce63 = ro6.createCell(3);
	        ce63.setCellValue(aqscData02.getData_6());
	        ce63.setCellStyle(c);
	        
	        HSSFRow ro7 = sheet.createRow(8);
	        HSSFCell ce71 = ro7.createCell(1);
	        ce71.setCellValue("安全生产综合奖励经费");
	        ce71.setCellStyle(c);
	        HSSFCell ce72 = ro7.createCell(2);
			ce72.setCellValue(aqscData01.getData_7());
			ce72.setCellStyle(c);
	        HSSFCell ce73 = ro7.createCell(3);
	        ce73.setCellValue(aqscData02.getData_7());
	        ce73.setCellStyle(c);
	        
	        HSSFRow ro8 = sheet.createRow(9);
	        HSSFCell ce81 = ro8.createCell(1);
	        ce81.setCellValue("安全隐患排查整改工作奖励经费");
	        ce81.setCellStyle(c);
	        HSSFCell ce82 = ro8.createCell(2);
			ce82.setCellValue(aqscData01.getData_8());
			ce82.setCellStyle(c);
	        HSSFCell ce83 = ro8.createCell(3);
	        ce83.setCellValue(aqscData02.getData_8());
	        ce83.setCellStyle(c);
	        
	        HSSFRow ro9 = sheet.createRow(10);
	        HSSFCell ce91 = ro9.createCell(1);
	        ce91.setCellValue("应急救援演练经费");
	        ce91.setCellStyle(c);
	        HSSFCell ce92 = ro9.createCell(2);
			ce92.setCellValue(aqscData01.getData_9());
			ce92.setCellStyle(c);
	        HSSFCell ce93 = ro9.createCell(3);
	        ce93.setCellValue(aqscData02.getData_9());
	        ce93.setCellStyle(c);

			HSSFRow ro10 = sheet.createRow(11);
			HSSFCell ce100 = ro10.createCell(0);
			ce100.setCellValue("安全保障");
			ce100.setCellStyle(c);
			sheet.addMergedRegion(new Region(11, (short) 0, 16, (short) 0)); 
	        HSSFCell ce101 = ro10.createCell(1);
	        ce101.setCellValue("劳动防护用品购置费用");
	        ce101.setCellStyle(c);
	        HSSFCell ce102 = ro10.createCell(2);
			ce102.setCellValue(aqscData01.getData_10());
			ce102.setCellStyle(c);
	        HSSFCell ce103 = ro10.createCell(3);
	        ce103.setCellValue(aqscData02.getData_10());
	        ce103.setCellStyle(c);
	        
	        HSSFRow ro11 = sheet.createRow(12);
	        HSSFCell ce111 = ro11.createCell(1);
	        ce111.setCellValue("应急救援装备物资购置与储备经费");
	        ce111.setCellStyle(c);
	        HSSFCell ce112 = ro11.createCell(2);
			ce112.setCellValue(aqscData01.getData_11());
			ce112.setCellStyle(c);
	        HSSFCell ce113 = ro11.createCell(3);
	        ce113.setCellValue(aqscData02.getData_11());
	        ce113.setCellStyle(c);
	        
	        
	        HSSFRow ro12 = sheet.createRow(13);
	        HSSFCell ce121 = ro12.createCell(1);
	        ce121.setCellValue("员工职业卫生体检费用");
	        ce121.setCellStyle(c);
	        HSSFCell ce122 = ro12.createCell(2);
			ce122.setCellValue(aqscData01.getData_12());
			ce122.setCellStyle(c);
	        HSSFCell ce123 = ro12.createCell(3);
	        ce123.setCellValue(aqscData02.getData_12());
	        ce123.setCellStyle(c);
	        
	        HSSFRow ro13 = sheet.createRow(14);
	        HSSFCell ce131 = ro13.createCell(1);
	        ce131.setCellValue("作业场所安全检测费用");
	        ce131.setCellStyle(c);
	        HSSFCell ce132 = ro13.createCell(2);
			ce132.setCellValue(aqscData01.getData_13());
			ce132.setCellStyle(c);
	        HSSFCell ce133 = ro13.createCell(3);
	        ce133.setCellValue(aqscData02.getData_13());
	        ce133.setCellStyle(c);
	        
	        HSSFRow ro14 = sheet.createRow(15);
	        HSSFCell ce141 = ro14.createCell(1);
	        ce141.setCellValue("安全生产评价与评估费用");
	        ce141.setCellStyle(c);
	        HSSFCell ce142 = ro14.createCell(2);
			ce142.setCellValue(aqscData01.getData_14());
			ce142.setCellStyle(c);
	        HSSFCell ce143 = ro14.createCell(3);
	        ce143.setCellValue(aqscData02.getData_14());
	        ce143.setCellStyle(c);
	        
	        HSSFRow ro15 = sheet.createRow(16);
	        HSSFCell ce151 = ro15.createCell(1);
	        ce151.setCellValue("专家检查咨询活动经费");
	        ce151.setCellStyle(c);
	        HSSFCell ce152 = ro15.createCell(2);
			ce152.setCellValue(aqscData01.getData_15());
			ce152.setCellStyle(c);
	        HSSFCell ce153 = ro15.createCell(3);
	        ce153.setCellValue(aqscData02.getData_15());
	        ce153.setCellStyle(c);
	        
			HSSFRow ro16 = sheet.createRow(17);
			HSSFCell ce160 = ro16.createCell(0);
			ce160.setCellValue("安全保险");
			ce160.setCellStyle(c);
			sheet.addMergedRegion(new Region(17, (short) 0, 20, (short) 0)); 
	        HSSFCell ce161 = ro16.createCell(1);
	        ce161.setCellValue("安全生产责任保险支出");
	        ce161.setCellStyle(c);
	        HSSFCell ce162 = ro16.createCell(2);
			ce162.setCellValue(aqscData01.getData_16());
			ce162.setCellStyle(c);
	        HSSFCell ce163 = ro16.createCell(3);
	        ce163.setCellValue(aqscData02.getData_16());
	        ce163.setCellStyle(c);
	        
	        HSSFRow ro17 = sheet.createRow(18);
	        HSSFCell ce171 = ro17.createCell(1);
	        ce171.setCellValue("社保工伤保险支出");
	        ce171.setCellStyle(c);
	        HSSFCell ce172 = ro17.createCell(2);
			ce172.setCellValue(aqscData01.getData_17());
			ce172.setCellStyle(c);
	        HSSFCell ce173 = ro17.createCell(3);
	        ce173.setCellValue(aqscData02.getData_17());
	        ce173.setCellStyle(c);
	        
	        HSSFRow ro18 = sheet.createRow(19);
	        HSSFCell ce181 = ro18.createCell(1);
	        ce181.setCellValue("员工人身意外伤害保险支出");
	        ce181.setCellStyle(c);
	        HSSFCell ce182 = ro18.createCell(2);
			ce182.setCellValue(aqscData01.getData_18());
			ce182.setCellStyle(c);
	        HSSFCell ce183 = ro18.createCell(3);
	        ce183.setCellValue(aqscData02.getData_18());
	        ce183.setCellStyle(c);
	        
	        HSSFRow ro19 = sheet.createRow(20);
	        HSSFCell ce191 = ro19.createCell(1);
	        ce191.setCellValue("企业公众责任险支出");
	        ce191.setCellStyle(c);
	        HSSFCell ce192 = ro19.createCell(2);
			ce192.setCellValue(aqscData01.getData_19());
			ce192.setCellStyle(c);
	        HSSFCell ce193 = ro19.createCell(3);
	        ce193.setCellValue(aqscData02.getData_19());
	        ce193.setCellStyle(c);
	        
	        HSSFRow ro20 = sheet.createRow(21);
			HSSFCell ce200 = ro20.createCell(0);
			ce200.setCellValue("安全治理");
			ce200.setCellStyle(c);
	        HSSFCell ce201 = ro20.createCell(1);
	        ce201.setCellValue("用于隐患整改的改造投资");
	        ce201.setCellStyle(c);
	        HSSFCell ce202 = ro20.createCell(2);
			ce202.setCellValue(aqscData01.getData_20());
			ce202.setCellStyle(c);
	        HSSFCell ce203 = ro20.createCell(3);
	        ce203.setCellValue(aqscData02.getData_20());
	        ce203.setCellStyle(c);
	        
	        HSSFRow ro21 = sheet.createRow(22);
	        HSSFCell ce210 = ro21.createCell(0);
			ce210.setCellValue("其他安全费用");
			ce210.setCellStyle(c);
	        HSSFCell ce212 = ro21.createCell(2);
			ce212.setCellValue(aqscData01.getData_21());
			ce212.setCellStyle(c);
	        HSSFCell ce213 = ro21.createCell(3);
	        ce213.setCellValue(aqscData02.getData_21());
	        ce213.setCellStyle(c);
	        
	        HSSFRow ro22 = sheet.createRow(23);
	        HSSFCell ce221 = ro22.createCell(0);
	        ce221.setCellValue("合计");
	        ce221.setCellStyle(c);
	        sheet.addMergedRegion(new Region(23, (short) 0, 23, (short) 1)); 
	        HSSFCell ce222 = ro22.createCell(2);
			ce222.setCellValue(aqscData01.getData_22());
			ce222.setCellStyle(c);
	        HSSFCell ce223 = ro22.createCell(3);
	        ce223.setCellValue(aqscData02.getData_22());
	        ce223.setCellStyle(c);
	        
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

	public JshxAqscb getJshxAqscb(){
		return this.jshxAqscb;
	}

	public void setJshxAqscb(JshxAqscb jshxAqscb){
		this.jshxAqscb = jshxAqscb;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
	public AqscData getAqscData() {
		return aqscData;
	}

	public void setAqscData(AqscData aqscData) {
		this.aqscData = aqscData;
	}

	public AqscData getAqscData01() {
		return aqscData01;
	}

	public void setAqscData01(AqscData aqscData01) {
		this.aqscData01 = aqscData01;
	}

	public AqscData getAqscData02() {
		return aqscData02;
	}

	public void setAqscData02(AqscData aqscData02) {
		this.aqscData02 = aqscData02;
	}

	public AqscData getAqscData03() {
		return aqscData03;
	}

	public void setAqscData03(AqscData aqscData03) {
		this.aqscData03 = aqscData03;
	}
	public String getBeforeMonth() {
		return beforeMonth;
	}
	public void setBeforeMonth(String beforeMonth) {
		this.beforeMonth = beforeMonth;
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
