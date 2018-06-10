/**
 * Class Name: ZdwxytjxxAction
 * Class Description：重大危险源信息管理
 */
package com.jshx.zdwxytjxx.web;

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
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.zdwxytjxx.entity.Zdwxytj;
import com.jshx.zdwxytjxx.entity.Zdwxytjxx;
import com.jshx.zdwxytjxx.service.ZdwxytjxxService;

public class ZdwxytjxxAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zdwxytjxx zdwxytjxx = new Zdwxytjxx();

	/**
	 * 业务类
	 */
	@Autowired
	private ZdwxytjxxService zdwxytjxxService;
	@Autowired
	private CompanyService companyService;
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
	private String createUserID;
	
	private Date queryTbrqStart;

	private Date queryTbrqEnd;
	
	private String type;

	private String tempDeptCode;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 初始化重大危险源列表
	 * author：陆婷
	 * 2013-10-31
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "1";
		}
		else
		{
			flag = "0";
		}
		createUserID = this.getLoginUserId();
		return SUCCESS;
	}
	
	/**
	 * 查询重大危险源列表
	 * author：陆婷
	 * 2013-10-31
	 */

	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zdwxytjxx){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zdwxytjxx.getSzzid()) && (0 < zdwxytjxx.getSzzid().trim().length())){
				paraMap.put("szzid", zdwxytjxx.getSzzid().trim());
			}

			if ((null != zdwxytjxx.getQymc()) && (0 < zdwxytjxx.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zdwxytjxx.getQymc().trim() + "%");
			}

			if (null != queryTbrqStart){
				paraMap.put("startTbrq", queryTbrqStart);
			}

			if (null != queryTbrqEnd){
				paraMap.put("endTbrq", queryTbrqEnd);
			}
			if ((null != zdwxytjxx.getSzc() )&& (0 < zdwxytjxx.getSzc().trim().length())){
				paraMap.put("szc",zdwxytjxx.getSzc().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		 
		pagination = zdwxytjxxService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看重大危险源详情
	 * author：陆婷
	 * 2013-10-31
	 */

	public String view() throws Exception{
		if((null != zdwxytjxx)&&(null != zdwxytjxx.getId()))
		{
			zdwxytjxx = zdwxytjxxService.getById(zdwxytjxx.getId());
		}
		else
		{
			zdwxytjxx.setTbr(this.getLoginUser().getDisplayName());
			zdwxytjxx.setTbrq(new Date());
		}
			
		
		return VIEW;
	}

	/**
	 * 初始化重大危险源修改信息
	 * author：陆婷
	 * 2013-10-31
	 */

	public String initEdit() throws Exception{
		view();
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			type = "1";
			zdwxytjxx.setSzzid(deptCode);
		}
		//hanxc 20150204 所在区域初始化
		int countyDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("countyDeptCodeLength"));
		int cityDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("cityDeptCodeLength"));
		int townDeptCodeLength = Integer.parseInt(SysPropertiesUtil.getProperty("townDeptCodeLength"));
	    if(deptCode.length() == countyDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, countyDeptCodeLength-3);
	    }else if(deptCode.length() == cityDeptCodeLength){
	    	tempDeptCode = "1";
	    }else if(deptCode.length() == townDeptCodeLength){
	    	tempDeptCode = deptCode.substring(0, townDeptCodeLength-6);
	    }
	    return EDIT;
	}

	/**
	 * 保存重大危险源信息
	 * author：陆婷
	 * 2013-10-31
	 */

	public String save() throws Exception{
		Map map = new HashMap();
		map.put("companyId", zdwxytjxx.getQyid());
		CompanyBackUp company = companyService.getCompanyBackupById(map);
		Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
		zdwxytjxx.setSzzid(dept.getDeptCode());
		zdwxytjxx.setSzzname(dept.getDeptName());
		zdwxytjxx.setQyid(company.getId());
		zdwxytjxx.setQymc(company.getCompanyname());
		zdwxytjxx.setQylx(company.getQylx());
		zdwxytjxx.setHyfl(company.getHyfl());
		zdwxytjxx.setQygm(company.getQygm());
		zdwxytjxx.setQyzclx(company.getQyzclx());
		zdwxytjxx.setIfwhpqylx(company.getIfwhpqylx());
		zdwxytjxx.setIfyhbzjyqy(company.getIfyhbzjyqy());
		zdwxytjxx.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山
		zdwxytjxx.setIfzywhqylx(company.getIfzywhqylx());
		Double zs = Double.valueOf(zdwxytjxx.getYjgs()) + Double.valueOf(zdwxytjxx.getEjgs())
		+Double.valueOf(zdwxytjxx.getSjgs()) + Double.valueOf(zdwxytjxx.getSijigs());
		zdwxytjxx.setZdwxyzs(zs+"");
		zdwxytjxx.setSzc(company.getSzc());
		zdwxytjxx.setSzcname(company.getSzcname());
		zdwxytjxx.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
		zdwxytjxx.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
		if ("add".equalsIgnoreCase(this.flag)){
			zdwxytjxx.setDeptId(this.getLoginUserDepartmentId());
			zdwxytjxx.setDelFlag(0);
			zdwxytjxxService.save(zdwxytjxx);
		}else{
			zdwxytjxxService.update(zdwxytjxx);
		}
		return RELOAD;
	}

	/**
	 * 删除重大危险源信息
	 * author：陆婷
	 * 2013-10-31
	 */

	public String delete() throws Exception{
	    try{
			zdwxytjxxService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 导出重大危险源信息
	 * author：陆婷
	 * 2013-11-5
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zdwxytjxx.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("重大危险源统计信息表");
		    sheet.setColumnWidth(0, 12000); 
	        sheet.setColumnWidth(1, 8500); 
	        sheet.setColumnWidth(2, 8500);
	        sheet.setColumnWidth(3, 8500);
	        sheet.setColumnWidth(4, 8500);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("相城区危险化学品重大危险源统计信息表");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 4)); 
	        
	        HSSFCellStyle cs = wb.createCellStyle();
		    cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    cs.setWrapText(true);
		    cs.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont font = wb.createFont();
	        font.setFontHeight((short) (15*15));
	        cs.setFont(font);
	        
	        HSSFRow r = sheet.createRow(1);
	        r.setHeight((short)(23*20));
	        HSSFCell cel0 = r.createCell(0);
	        cel0.setCellValue("企业名称");
	        cel0.setCellStyle(cs);
	        HSSFCell cel1 = r.createCell(1);
	        cel1.setCellValue("一级危险化学品重大危险源个数");
	        cel1.setCellStyle(cs);
	        HSSFCell cel2 = r.createCell(2);
	        cel2.setCellValue("二级危险化学品重大危险源个数");
	        cel2.setCellStyle(cs);
	        HSSFCell cel3 = r.createCell(3);
	        cel3.setCellValue("三级危险化学品重大危险源个数");
	        cel3.setCellStyle(cs);
	        HSSFCell cel4 = r.createCell(4);
	        cel4.setCellValue("四级危险化学品重大危险源个数");
	        cel4.setCellStyle(cs);
	        
	        
	        Map<String, Object> paraMap = new HashMap<String, Object>();
	        
	        if(flag == null || "".equals(flag))
    		{
        		String szzid = (String) getSessionAttribute("szzid");
        		String qymc = (String) getSessionAttribute("qymc");
        		paraMap.put("szzid", szzid);
        		paraMap.put("qymc", qymc);
        		queryTbrqStart = (Date) getSessionAttribute("queryTbrqStart");
        		queryTbrqEnd = (Date) getSessionAttribute("queryTbrqEnd");
    		}
	        if(null != zdwxytjxx){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != zdwxytjxx.getSzzid()) && (0 < zdwxytjxx.getSzzid().trim().length())){
					paraMap.put("szzid", zdwxytjxx.getSzzid().trim());
					setSessionAttribute("szzid",zdwxytjxx.getSzzid().trim());
				}

				if ((null != zdwxytjxx.getQymc()) && (0 < zdwxytjxx.getQymc().trim().length())){
					paraMap.put("qymc", "%" + zdwxytjxx.getQymc().trim() + "%");
					setSessionAttribute("qymc","%" + zdwxytjxx.getQymc().trim() + "%");
				}
			}
	        if (null != queryTbrqStart){
				paraMap.put("startTbrq", queryTbrqStart);
				setSessionAttribute("queryTbrqStart",queryTbrqStart);
			}

			if (null != queryTbrqEnd){
				paraMap.put("endTbrq", queryTbrqEnd);
	    		setSessionAttribute("queryTbrqEnd",queryTbrqEnd);
			}

			//hanxc 20141223 修改查询条件 start
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			String deptRole = this.getLoginUser().getDeptRole();
			if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
				paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
			}
			//hanxc 20141223 修改查询条件 end
	        List<Zdwxytjxx> list  = zdwxytjxxService.findZdwxytjxx(paraMap);
	        Zdwxytj zdwxytj = zdwxytjxxService.getZdwxytjByMap(paraMap);
	        int num = 2;
	        for(int i=0;i<list.size();i++)
			{
	        	Zdwxytjxx zdwxytjxx = list.get(i);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(zdwxytjxx.getQymc());
				ce0.setCellStyle(cs);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(zdwxytjxx.getYjgs());
		        ce1.setCellStyle(cs);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(zdwxytjxx.getEjgs());
		        ce2.setCellStyle(cs);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(zdwxytjxx.getSjgs());
		        ce3.setCellStyle(cs);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(zdwxytjxx.getSijigs());
		        ce4.setCellStyle(cs);
				num++;
			}
	        HSSFRow rr = sheet.createRow(num);
	        HSSFCell cell0 = rr.createCell(0);
	        cell0.setCellValue("合计");
	        cell0.setCellStyle(cs);
	        HSSFCell cell1 = rr.createCell(1);
	        cell1.setCellValue(zdwxytj.getYjtotal());
	        cell1.setCellStyle(cs);
	        HSSFCell cell2 = rr.createCell(2);
	        cell2.setCellValue(zdwxytj.getEjtotal());
	        cell2.setCellStyle(cs);
	        HSSFCell cell3 = rr.createCell(3);
	        cell3.setCellValue(zdwxytj.getSjtotal());
	        cell3.setCellStyle(cs);
	        HSSFCell cell4 = rr.createCell(4);
	        cell4.setCellValue(zdwxytj.getSijitotal());
	        cell4.setCellStyle(cs);
	        
	        HSSFRow rrr = sheet.createRow(num+1);
	        HSSFCell celll0 = rrr.createCell(0);
	        celll0.setCellValue("重大危险源总数");
	        celll0.setCellStyle(cs);
	        HSSFCell celll1 = rrr.createCell(1);
	        celll1.setCellValue(zdwxytj.getYjtotal());
	        celll1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(num+1, (short) 1, num+1, (short) 4));
	        
	        HSSFRow rrrr = sheet.createRow(num+2);
	        HSSFCell cellll0 = rrrr.createCell(0);
	        cellll0.setCellValue("重大危险源单位总数");
	        cellll0.setCellStyle(cs);
	        HSSFCell cellll1 = rrrr.createCell(1);
	        cellll1.setCellValue(zdwxytj.getQytotal());
	        cellll1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(num+2, (short) 1, num+2, (short) 4));
	        
	        
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

	public Zdwxytjxx getZdwxytjxx(){
		return this.zdwxytjxx;
	}

	public void setZdwxytjxx(Zdwxytjxx zdwxytjxx){
		this.zdwxytjxx = zdwxytjxx;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryTbrqStart(){
		return this.queryTbrqStart;
	}

	public void setQueryTbrqStart(Date queryTbrqStart){
		this.queryTbrqStart = queryTbrqStart;
	}

	public Date getQueryTbrqEnd(){
		return this.queryTbrqEnd;
	}

	public void setQueryTbrqEnd(Date queryTbrqEnd){
		this.queryTbrqEnd = queryTbrqEnd;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	public String getTempDeptCode() {
		return tempDeptCode;
	}

	public void setTempDeptCode(String tempDeptCode) {
		this.tempDeptCode = tempDeptCode;
	}

}
