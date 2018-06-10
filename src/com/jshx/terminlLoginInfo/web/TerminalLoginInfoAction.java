/**
 * Class Name: TerminalLoginInfoAction
 * Class Description：终端登陆日志
 */
package com.jshx.terminlLoginInfo.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.StringUtil;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.LogonLog;
import com.jshx.module.admin.entity.User;
import com.jshx.terminlLoginInfo.entity.TerminalLoginInfo;
import com.jshx.terminlLoginInfo.service.TerminalLoginInfoService;

public class TerminalLoginInfoAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private TerminalLoginInfo terminalLoginInfo = new TerminalLoginInfo();

	/**
	 * 业务类
	 */
	@Autowired
	private TerminalLoginInfoService terminalLoginInfoService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
    /**
     * 添加日期范围查询 lj 2011-10-19
     */
	private Date beginDate;
	
	private Date endDate;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	public String initList(){
	    return SUCCESS;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public String list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != terminalLoginInfo){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != terminalLoginInfo.getUserName()) && (0 < terminalLoginInfo.getUserName().trim().length())){
				paraMap.put("userName", "%" + terminalLoginInfo.getUserName().trim() + "%");
			}
			if(null != beginDate && !"".equals(beginDate)){
				paraMap.put("beginDate",beginDate);
			}
			if(null != endDate && !"".equals(endDate)){
				paraMap.put("endDate",endDate);
			}
		}
		pagination = terminalLoginInfoService.findByPage(pagination, paraMap);
		
		//将查询结果转为json数据给datagrid展现
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
	    JSONArray json = JSONArray.fromObject(this.pagination.getListOfObject());
		data.append(json.toString());
		data.append("  \n").append("}");
		
		getResponse().getWriter().println(data);
		return null;
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != terminalLoginInfo)&&(null != terminalLoginInfo.getId()))
			terminalLoginInfo = terminalLoginInfoService.getById(terminalLoginInfo.getId());
		
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
		if ("add".equalsIgnoreCase(this.flag)){
			terminalLoginInfo.setDeptId(this.getLoginUserDepartmentId());
			terminalLoginInfo.setDelFlag(0);
			terminalLoginInfoService.save(terminalLoginInfo);
		}else{
			terminalLoginInfoService.update(terminalLoginInfo);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			terminalLoginInfoService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	/**
	 * 导出终端登录日志
	 * author：hanxc
	 * 2015-02-27
	 */
	public void export()
	{
		try {
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=zddlrz.xls");
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("终端登录日志");
		    sheet.setColumnWidth(0, 3000); 
	        sheet.setColumnWidth(1, 3500);
	        sheet.setColumnWidth(2, 8500);
	        
	        HSSFRow row = sheet.createRow(0);
	        row.setHeight((short)(28*20));
	        HSSFCell cell = row.createCell(0);
	        cell.setCellValue("终端登录日志");
	        HSSFCellStyle css = wb.createCellStyle();
		    css.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		    css.setWrapText(true);
		    css.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
	        HSSFFont fonts = wb.createFont();
	        fonts.setFontHeight((short) (20*20));
	        css.setFont(fonts);
	        cell.setCellStyle(css);
	        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 2)); 
	        
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
	        cel0.setCellValue("用户名");
	        cel0.setCellStyle(cs);
	        HSSFCell cel1 = r.createCell(1);
	        cel1.setCellValue("imsi号");
	        cel1.setCellStyle(cs);
	        HSSFCell cel2 = r.createCell(2);
	        cel2.setCellValue("登录日期");
	        cel2.setCellStyle(cs);
	        
	        Map<String, Object> paraMap = new HashMap<String, Object>();
			if(pagination==null)
			    pagination = new Pagination(this.getRequest());

	        pagination.setPageNumber(1);
	        pagination.setPageSize(1000000000);    
			if(null != terminalLoginInfo){
			    //设置查询条件，开发人员可以在此增加过滤条件
				if ((null != terminalLoginInfo.getUserName()) && (0 < terminalLoginInfo.getUserName().trim().length())){
					paraMap.put("userName", "%" + terminalLoginInfo.getUserName().trim() + "%");
				}
				if(null != beginDate && !"".equals(beginDate)){
					paraMap.put("beginDate",beginDate);
				}
				if(null != endDate && !"".equals(endDate)){
					paraMap.put("endDate",endDate);
				}
			}
			pagination = terminalLoginInfoService.findByPage(pagination, paraMap);
	        List<TerminalLoginInfo> list  = pagination.getListOfObject();
	        int num = 2;
	        for(int i=0;i<list.size();i++)
			{
	        	TerminalLoginInfo t = list.get(i);
				HSSFRow ro = sheet.createRow(num);
				HSSFCell ce0 = ro.createCell(0);
				ce0.setCellValue(t.getUserName());
				ce0.setCellStyle(cs);
		        HSSFCell ce1 = ro.createCell(1);
		        ce1.setCellValue(t.getImsi());
		        ce1.setCellStyle(cs);
		        HSSFCell ce2 = ro.createCell(2);
		        ce2.setCellValue(t.getCreateTime().toString());
		        ce2.setCellStyle(cs);
				num++;
			}
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (Exception e) {
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

	public TerminalLoginInfo getTerminalLoginInfo(){
		return this.terminalLoginInfo;
	}

	public void setTerminalLoginInfo(TerminalLoginInfo terminalLoginInfo){
		this.terminalLoginInfo = terminalLoginInfo;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
