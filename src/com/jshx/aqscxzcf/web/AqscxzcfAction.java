/**
 * Class Name: AqscxzcfAction
 * Class Description：安全生产行政处罚
 */
package com.jshx.aqscxzcf.web;

import java.text.SimpleDateFormat;
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

import com.jshx.aqscfk.entity.Aqscfk;
import com.jshx.aqscxzcf.entity.Aqscxzcf;
import com.jshx.aqscxzcf.service.AqscxzcfService;
import com.jshx.aqscxzcfglb.entity.Aqscxzcfglb;
import com.jshx.aqscxzcfglb.service.AqscxzcfglbService;
import com.jshx.aqsczfwsglb.entity.Aqsczfwsglb;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;

public class AqscxzcfAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Aqscxzcf aqscxzcf = new Aqscxzcf();
	
	private Aqscxzcfglb aqscxzcfglb = new Aqscxzcfglb();

	/**
	 * 业务类
	 */
	@Autowired
	private AqscxzcfService aqscxzcfService;
	@Autowired
	private AqscxzcfglbService aqscxzcfglbService;

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
	
	private Date queryTbrqStart;

	private Date queryTbrqEnd;
	private String createUserID;

	private String[] aqscxzcftype = {"危化品企业","烟花爆竹经营企业","冶金企业","有色金属企业","其他企业"};
	
	private List<Aqscxzcfglb> xzcflist = new ArrayList<Aqscxzcfglb>();
	
	/**
	 * 初始化安全生产行政处罚列表
	 * author：陆婷
	 * 2013-11-1
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(!deptCode.equals("002") && !deptCode.startsWith("001"))
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
	 * 查询安全生产行政处罚列表
	 * author：陆婷
	 * 2013-11-1
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != aqscxzcf){
		    //设置查询条件，开发人员可以在此增加过滤条件

			if ((null != aqscxzcf.getDeptname()) && (0 < aqscxzcf.getDeptname().trim().length())){
				paraMap.put("deptname", "%" + aqscxzcf.getDeptname().trim() + "%");
			}
			
		}
		if (null != queryYfStart && (0 < queryYfStart.trim().length())){
			paraMap.put("startYf", queryYfStart);
		}

		if (null != queryYfEnd && (0 < queryYfStart.trim().length())){
			paraMap.put("endYf", queryYfEnd);
		}
		if (null != queryTbrqStart){
			paraMap.put("startTbrq", queryTbrqStart);
		}

		if (null != queryTbrqEnd){
			paraMap.put("endTbrq", queryTbrqEnd);
		}
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(!deptCode.equals("002") && !deptCode.startsWith("001"))
		{
			paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
		}
		pagination = aqscxzcfService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看安全生产行政处罚详情
	 * author：陆婷
	 * 2013-11-1
	 */
	public String view() throws Exception{
		if((null != aqscxzcf)&&(null != aqscxzcf.getId()))
		{
			aqscxzcf = aqscxzcfService.getById(aqscxzcf.getId());
			Map map = new HashMap();
			map.put("proid", aqscxzcf.getId());
			xzcflist = aqscxzcfglbService.findAqscxzcfglb(map);
		}
		else
		{
			aqscxzcf.setDeptname(this.getLoginUserDepartment().getDeptName());
			for(String s:aqscxzcftype)
			{
				Aqscxzcfglb aqscxzcfglb = new Aqscxzcfglb();
				aqscxzcfglb.setLinktype(s);
				xzcflist.add(aqscxzcfglb);
			}
		}
		
		return VIEW;
	}

	/**
	 * 初始化安全生产行政处罚修改信息
	 * author：陆婷
	 * 2013-11-1
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存安全生产行政处罚信息
	 * author：陆婷
	 * 2013-11-1
	 */
	public String save() throws Exception{
		if ("add".equalsIgnoreCase(this.flag)){
			aqscxzcf.setDeptId(this.getLoginUserDepartmentId());
			aqscxzcf.setDelFlag(0);
			aqscxzcf.setCreateTime(new Date());
			aqscxzcf.setCreateUserID(this.getLoginUserId());
			aqscxzcf.setDeptname(this.getLoginUserDepartment().getDeptName());
			aqscxzcfService.save(aqscxzcf);
		}else{
			aqscxzcfService.update(aqscxzcf);
			Map map = new HashMap();
			map.put("proid", aqscxzcf.getId());
			aqscxzcfService.deleteAqscxzcfglbByMap(map);
		}
		String[] linktype =  aqscxzcfglb.getLinktype().replaceAll(" ", "").split(",");
		String[] xzcfcs =  aqscxzcfglb.getXzcfcs().replaceAll(" ", "").split(",");
		String[] scjydwcs =  aqscxzcfglb.getScjydwcs().replaceAll(" ", "").split(",");
		String[] zyfzrcs =  aqscxzcfglb.getZyfzrcs().replaceAll(" ", "").split(",");
		String[] zltcgs =  aqscxzcfglb.getZltcgs().replaceAll(" ", "").split(",");
		String[] tqgbgs =  aqscxzcfglb.getTqgbgs().replaceAll(" ", "").split(",");
		String[] sjgbgs =  aqscxzcfglb.getSjgbgs().replaceAll(" ", "").split(",");
		String[] zkgs =  aqscxzcfglb.getZkgs().replaceAll(" ", "").split(",");
		String[] dxgs =  aqscxzcfglb.getDxgs().replaceAll(" ", "").split(",");
		for(int i=0;i<linktype.length;i++)
		{
			Aqscxzcfglb aqscxzcfglb = new Aqscxzcfglb();
			aqscxzcfglb.setCreateTime(new Date());
			aqscxzcfglb.setCreateUserID(this.getLoginUserId());
			aqscxzcfglb.setDelFlag(0);
			aqscxzcfglb.setDeptId(this.getLoginUserDepartmentId());
			aqscxzcfglb.setLinktype(linktype[i]);
			aqscxzcfglb.setXzcfcs(NullToNum(xzcfcs,i));
			aqscxzcfglb.setScjydwcs(NullToNum(scjydwcs,i));
			aqscxzcfglb.setZyfzrcs(NullToNum(zyfzrcs,i));
			aqscxzcfglb.setZltcgs(NullToNum(zltcgs,i));
			aqscxzcfglb.setTqgbgs(NullToNum(tqgbgs,i));
			aqscxzcfglb.setSjgbgs(NullToNum(sjgbgs,i));
			aqscxzcfglb.setZkgs(NullToNum(zkgs,i));
			aqscxzcfglb.setDxgs(NullToNum(dxgs,i));
			aqscxzcfglb.setProid(aqscxzcf.getId());
			aqscxzcfglbService.save(aqscxzcfglb);
		}
		return RELOAD;
	}

	public String NullToNum(String[] s,int i)
	{
		String ss = "";
		if(i >= s.length || s[i] == null || "".equals(s[i]))
		{
			ss = "0";
		}
		else
		{
			ss = s[i];
		}
		return ss;
	}
	/**
	 * 删除安全生产行政处罚信息
	 * author：陆婷
	 * 2013-11-1
	 */
	public String delete() throws Exception{
	    try{
			aqscxzcfService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	/**
	 * 导出安全生产行政处罚
	 * author：陆婷
	 * 2013-12-19
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=aqscxzcf.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("安全生产行政处罚情况统计");
		    sheet.setColumnWidth(0, 8000); 
	        sheet.setColumnWidth(1, 6000); 
	        sheet.setColumnWidth(2, 8000);
	        sheet.setColumnWidth(3, 8000);
	        sheet.setColumnWidth(4, 8000); 
	        sheet.setColumnWidth(5, 8000); 
	        sheet.setColumnWidth(6, 8000);
	        sheet.setColumnWidth(7, 8000);
	        sheet.setColumnWidth(8, 8000);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("安全生产行政处罚情况统计");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 8)); 
	        
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
	        ccl0.setCellValue("项目内容");
	        ccl0.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 0, 2, (short) 0)); 
	        HSSFCell ccl1 = r3.createCell(1);
	        ccl1.setCellValue("行政处罚");
	        ccl1.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 1, 1, (short) 3)); 
	        HSSFCell ccl2 = r3.createCell(4);
	        ccl2.setCellValue("责令停产停业整顿生产经营单位（个）");
	        ccl2.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 4, 2, (short) 4)); 
	        HSSFCell ccl3 = r3.createCell(5);
	        ccl3.setCellValue("提请关闭生产经营单位");
	        ccl3.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 5, 1, (short) 6)); 
	        HSSFCell ccl4 = r3.createCell(7);
	        ccl4.setCellValue("暂扣安全生产许可证企业");
	        ccl4.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 7, 2, (short) 7)); 
	        HSSFCell ccl5 = r3.createCell(8);
	        ccl5.setCellValue("吊销安全生产许可证企业");
	        ccl5.setCellStyle(cs);
	        sheet.addMergedRegion(new Region(1, (short) 8, 2, (short) 8)); 
	        
	        HSSFRow r4 = sheet.createRow(2);
	        HSSFCell ccl6 = r4.createCell(1);
	        ccl6.setCellValue("次数");
	        ccl6.setCellStyle(cs);
	        HSSFCell ccl7 = r4.createCell(2);
	        ccl7.setCellValue("其中：对生产经营单位");
	        ccl7.setCellStyle(cs);
	        HSSFCell ccl8 = r4.createCell(3);
	        ccl8.setCellValue("其中：对生产经营单位主要负责人");
	        ccl8.setCellStyle(cs);
	        HSSFCell ccl9 = r4.createCell(5);
	        ccl9.setCellValue("个数");
	        ccl9.setCellStyle(cs);
	        HSSFCell ccl10 = r4.createCell(6);
	        ccl10.setCellValue("其中：实际关闭生产经营单位");
	        ccl10.setCellStyle(cs);
	        
	        
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
        		String deptname = (String) getSessionAttribute("deptname");
        		paraMap.put("deptname", deptname);
        		queryYfStart = (String) getSessionAttribute("queryYfStart");
        		queryYfEnd = (String) getSessionAttribute("queryYfEnd");
        		queryTbrqStart = (Date) getSessionAttribute("queryTbrqStart");
        		queryTbrqEnd = (Date) getSessionAttribute("queryTbrqEnd");
    		}
	        if(null != aqscxzcf){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != aqscxzcf.getDeptname()) && (0 < aqscxzcf.getDeptname().trim().length())){
					paraMap.put("deptname", "%" + aqscxzcf.getDeptname().trim() + "%");
					setSessionAttribute("deptname", "%" + aqscxzcf.getDeptname().trim() + "%");
				}
			}
			if (null != queryYfStart && (0 < queryYfStart.trim().length())){
				paraMap.put("startYf", queryYfStart);
				setSessionAttribute("queryYfStart", queryYfStart);
			}

			if (null != queryYfEnd && (0 < queryYfStart.trim().length())){
				paraMap.put("endYf", queryYfEnd);
				setSessionAttribute("queryYfEnd", queryYfEnd);
			}
			if (null != queryTbrqStart){
				paraMap.put("startTbrq", queryTbrqStart);
				setSessionAttribute("queryTbrqStart", queryTbrqStart);
			}

			if (null != queryTbrqEnd){
				paraMap.put("endTbrq", queryTbrqEnd);
				setSessionAttribute("queryTbrqEnd", queryTbrqEnd);
			}
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(!deptCode.equals("002") && !deptCode.startsWith("001"))
			{
				paraMap.put("deptCodes", this.getLoginUserDepartment().getChildDeptIds());
			}
	        
			List<String> proid = aqscxzcfService.getAqscxzcfIdsByMap(paraMap);
			
			int num = 3;
			for(int i=0;i<aqscxzcftype.length;i++)
			{
				String linktype = aqscxzcftype[i];
				Map map = new HashMap();
				map.put("proids", proid);
				map.put("linktype", linktype);
				Aqscxzcfglb aqscxzcfglb = aqscxzcfglbService.getAqscxzcfglbByMap(map);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(linktype);
				ce0.setCellStyle(c);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(aqscxzcfglb.getXzcfcs());
		        ce1.setCellStyle(c);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(aqscxzcfglb.getScjydwcs());
		        ce2.setCellStyle(c);
		        HSSFCell ce3 = ro.createCell(3);
		        ce3.setCellValue(aqscxzcfglb.getZyfzrcs());
		        ce3.setCellStyle(c);
		        HSSFCell ce4 = ro.createCell(4);
		        ce4.setCellValue(aqscxzcfglb.getZltcgs());
		        ce4.setCellStyle(c);
		        HSSFCell ce5 = ro.createCell(5);
		        ce5.setCellValue(aqscxzcfglb.getTqgbgs());
		        ce5.setCellStyle(c);
		        HSSFCell ce6 = ro.createCell(6);
		        ce6.setCellValue(aqscxzcfglb.getSjgbgs());
		        ce6.setCellStyle(c);
		        HSSFCell ce7 = ro.createCell(7);
		        ce7.setCellValue(aqscxzcfglb.getZkgs());
		        ce7.setCellStyle(c);
		        HSSFCell ce8 = ro.createCell(8);
		        ce8.setCellValue(aqscxzcfglb.getDxgs());
		        ce8.setCellStyle(c);
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

	public Aqscxzcf getAqscxzcf(){
		return this.aqscxzcf;
	}

	public void setAqscxzcf(Aqscxzcf aqscxzcf){
		this.aqscxzcf = aqscxzcf;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
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
	public Aqscxzcfglb getAqscxzcfglb() {
		return aqscxzcfglb;
	}

	public void setAqscxzcfglb(Aqscxzcfglb aqscxzcfglb) {
		this.aqscxzcfglb = aqscxzcfglb;
	}

	public Date getQueryTbrqStart() {
		return queryTbrqStart;
	}

	public void setQueryTbrqStart(Date queryTbrqStart) {
		this.queryTbrqStart = queryTbrqStart;
	}

	public Date getQueryTbrqEnd() {
		return queryTbrqEnd;
	}

	public void setQueryTbrqEnd(Date queryTbrqEnd) {
		this.queryTbrqEnd = queryTbrqEnd;
	}

	public String getCreateUserID() {
		return createUserID;
	}

	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}
	public List<Aqscxzcfglb> getXzcflist() {
		return xzcflist;
	}
	public void setXzcflist(List<Aqscxzcfglb> xzcflist) {
		this.xzcflist = xzcflist;
	}

}
