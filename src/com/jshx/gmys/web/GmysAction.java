package com.jshx.gmys.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.LobHelper;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.aqscjchzxzz.entity.AqscjchzxzzBean;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.gmys.entity.Gmys;
import com.jshx.gmys.entity.GmysBean;
import com.jshx.gmys.service.GmysService;

public class GmysAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Gmys gmys = new Gmys();

	/**
	 * 业务类
	 */
	@Autowired
	private GmysService gmysService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryMonthTimeStart;

	private Date queryMonthTimeEnd;
	
	List<GmysBean> gmyslist=new ArrayList<GmysBean>();

	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != gmys){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if (null != queryMonthTimeStart){
				paraMap.put("startMonthTime", queryMonthTimeStart);
			}

			if (null != queryMonthTimeEnd){
				paraMap.put("endMonthTime", queryMonthTimeEnd);
			}
			if ((null != gmys.getAreaName()) && (0 < gmys.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + gmys.getAreaName().trim() + "%");
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|monthTime|areaName|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination = gmysService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != gmys)&&(null != gmys.getId()))
			gmys = gmysService.getById(gmys.getId());
		
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
		FileInputStream in = null;
		try
		{
			//设置Blob字段
			setBlobField(in);
		}
		finally
		{
			if (null != in)
			{
				try
				{
					in.close();
				}
				catch (Exception ex)
				{
				}
			}
		}		
		if(gmys.getAreaName().equals("一中队")){
			gmys.setAreaId("1");
		}if(gmys.getAreaName().equals("二中队")){
			gmys.setAreaId("2");
		}if(gmys.getAreaName().equals("三中队")){
			gmys.setAreaId("3");
		}if(gmys.getAreaName().equals("娄葑街道")){
			gmys.setAreaId("4");
		}if(gmys.getAreaName().equals("斜塘街道")){
			gmys.setAreaId("5");
		}if(gmys.getAreaName().equals("唯亭街道")){
			gmys.setAreaId("6");
		}if(gmys.getAreaName().equals("胜浦街道")){
			gmys.setAreaId("7");
		}
		if ("add".equalsIgnoreCase(this.flag)){
			gmys.setDeptId(this.getLoginUserDepartmentId());
			gmys.setDelFlag(0);
			gmysService.save(gmys);
		}else{
			gmysService.update(gmys);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != gmys)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到gmys中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			gmysService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public void export()
	{
		try {
			Map<String, Object> paraMap = new HashMap<String, Object>();
			if(flag == null || "".equals(flag))
			{
				queryMonthTimeStart = (Date) getSessionAttribute("queryMonthTimeStart");
				queryMonthTimeEnd = (Date) getSessionAttribute("queryMonthTimeEnd");
			}
			if (null != queryMonthTimeStart){
				paraMap.put("startMonthTime", queryMonthTimeStart);
				setSessionAttribute("queryMonthTimeStart", queryMonthTimeStart);
			}

			if (null != queryMonthTimeEnd){
				paraMap.put("endMonthTime", queryMonthTimeEnd);
				setSessionAttribute("queryMonthTimeEnd", queryMonthTimeEnd);
			}
			try {
				gmyslist = gmysService.getGmysListByMap(paraMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GmysBean gmys = gmysService.getTotalGmysListByMap(paraMap);
			gmys.setData1("合计");
			gmyslist.add(gmys);
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=gmys.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "gmys.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	        int num = 3;
	        int j=1;
	        for(int i=0;i<gmyslist.size();i++)
	        {
	        	
	        	GmysBean gmysbean = gmyslist.get(i);
	        	HSSFRow row0 = sheet.createRow(num);
	        	 HSSFCellStyle css = wb.createCellStyle();
	        	 css.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	        	 css.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	        	 css.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	        	 css.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	        	
	        	HSSFCell cel0 = row0.createCell(0);
				cel0.setCellValue(j);
				cel0.setCellStyle(css);
				HSSFCell cel1 = row0.createCell(1);
				cel1.setCellValue(gmysbean.getData1());
				cel1.setCellStyle(css);
				HSSFCell cel2 = row0.createCell(2);
				cel2.setCellValue(gmysbean.getData2());
				cel2.setCellStyle(css);
				HSSFCell cel3 = row0.createCell(3);
				cel3.setCellValue(gmysbean.getData3());
				cel3.setCellStyle(css);
				HSSFCell cel4 = row0.createCell(4);
				cel4.setCellValue(gmysbean.getData4());
				cel4.setCellStyle(css);
				HSSFCell cel5 = row0.createCell(5);
				cel5.setCellValue(gmysbean.getData5());
				cel5.setCellStyle(css);
				HSSFCell cel6 = row0.createCell(6);
				cel6.setCellValue(gmysbean.getData6());
				cel6.setCellStyle(css);
				HSSFCell cel7 = row0.createCell(7);
				cel7.setCellValue(gmysbean.getData7());
				cel7.setCellStyle(css);
				HSSFCell cel8 = row0.createCell(8);
				cel8.setCellValue(gmysbean.getData8());
				cel8.setCellStyle(css);
				HSSFCell cel9 = row0.createCell(9);
				cel9.setCellValue(gmysbean.getData9());
				cel9.setCellStyle(css);
				HSSFCell cel10 = row0.createCell(10);
				cel10.setCellValue(gmysbean.getData10());
				cel10.setCellStyle(css);
				HSSFCell cel11 = row0.createCell(11);
				cel11.setCellValue(gmysbean.getData11());
				cel11.setCellStyle(css);
				HSSFCell cel12 = row0.createCell(12);
				cel12.setCellValue(gmysbean.getData12());
				cel12.setCellStyle(css);
				HSSFCell cel13 = row0.createCell(13);
				cel13.setCellValue(gmysbean.getData14());
				cel13.setCellStyle(css);
				HSSFCell cel14 = row0.createCell(14);
				cel14.setCellValue(gmysbean.getData13());
				cel14.setCellStyle(css);
				num ++;
				j++;
	        }
			
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
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

	public Gmys getGmys(){
		return this.gmys;
	}

	public void setGmys(Gmys gmys){
		this.gmys = gmys;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryMonthTimeStart(){
		return this.queryMonthTimeStart;
	}

	public void setQueryMonthTimeStart(Date queryMonthTimeStart){
		this.queryMonthTimeStart = queryMonthTimeStart;
	}

	public Date getQueryMonthTimeEnd(){
		return this.queryMonthTimeEnd;
	}

	public void setQueryMonthTimeEnd(Date queryMonthTimeEnd){
		this.queryMonthTimeEnd = queryMonthTimeEnd;
	}

	public List<GmysBean> getGmyslist() {
		return gmyslist;
	}

	public void setGmyslist(List<GmysBean> gmyslist) {
		this.gmyslist = gmyslist;
	}

}
