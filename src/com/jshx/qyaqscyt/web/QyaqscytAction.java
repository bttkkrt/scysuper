package com.jshx.qyaqscyt.web;

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

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.qyaqscyt.entity.Qyaqscyt;
import com.jshx.qyaqscyt.entity.QyaqscytBean;
import com.jshx.qyaqscyt.service.QyaqscytService;

public class QyaqscytAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Qyaqscyt qyaqscyt = new Qyaqscyt();

	/**
	 * 业务类
	 */
	@Autowired
	private QyaqscytService qyaqscytService;

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
	
	List<QyaqscytBean> qyaqlist=new ArrayList<QyaqscytBean>();

	
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
		    
		if(null != qyaqscyt){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != qyaqscyt.getAreaName()) && (0 < qyaqscyt.getAreaName().trim().length())){
				paraMap.put("areaName", "%" + qyaqscyt.getAreaName().trim() + "%");
			}

			if (null != queryMonthTimeStart){
				paraMap.put("startMonthTime", queryMonthTimeStart);
			}

			if (null != queryMonthTimeEnd){
				paraMap.put("endMonthTime", queryMonthTimeEnd);
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
		pagination = qyaqscytService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != qyaqscyt)&&(null != qyaqscyt.getId()))
			qyaqscyt = qyaqscytService.getById(qyaqscyt.getId());
		
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
	     
		if(qyaqscyt.getAreaName().equals("安监局")){
			qyaqscyt.setAreaId("1");
		}if(qyaqscyt.getAreaName().equals("综合二处")){
			qyaqscyt.setAreaId("2");
		}if(qyaqscyt.getAreaName().equals("监管三处")){
			qyaqscyt.setAreaId("3");
		}if(qyaqscyt.getAreaName().equals("监管四处")){
			qyaqscyt.setAreaId("4");
		}if(qyaqscyt.getAreaName().equals("安监大队")){
			qyaqscyt.setAreaId("5");
		}if(qyaqscyt.getAreaName().equals("第一中队")){
			qyaqscyt.setAreaId("6");
		}if(qyaqscyt.getAreaName().equals("第二中队")){
			qyaqscyt.setAreaId("7");
		}if(qyaqscyt.getAreaName().equals("第三中队")){
			qyaqscyt.setAreaId("8");
		}if(qyaqscyt.getAreaName().equals("娄葑街道")){
			qyaqscyt.setAreaId("9");
		}if(qyaqscyt.getAreaName().equals("斜塘街道")){
			qyaqscyt.setAreaId("10");
		}if(qyaqscyt.getAreaName().equals("唯亭街道")){
			qyaqscyt.setAreaId("110");
		}if(qyaqscyt.getAreaName().equals("胜浦街道")){
			qyaqscyt.setAreaId("12");
		}
		
		if ("add".equalsIgnoreCase(this.flag)){
			qyaqscyt.setDeptId(this.getLoginUserDepartmentId());
			qyaqscyt.setDelFlag(0);
			qyaqscytService.save(qyaqscyt);
		}else{
			qyaqscytService.update(qyaqscyt);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != qyaqscyt)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到qyaqscyt中去
				
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
			qyaqscytService.deleteWithFlag(ids);
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
			}

			
			if (null != queryMonthTimeStart && !"".equals(queryMonthTimeStart)){
				paraMap.put("startMonthTime", queryMonthTimeStart);
				setSessionAttribute("queryMonthTimeStart", queryMonthTimeStart);
			}

			try {
				qyaqlist = qyaqscytService.getQyaqscytListByMap(paraMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			QyaqscytBean qyaqscyt = qyaqscytService.getTotalQyaqscytListByMap(paraMap);
			qyaqscyt.setData1("合计");
			qyaqlist.add(qyaqscyt);
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=qyaq.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "qyaq.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	        int num = 2;
	        int j=1;
	        for(int i=0;i<qyaqlist.size();i++)
	        {
	        	
	        	QyaqscytBean qyaqbean = qyaqlist.get(i);
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
				cel1.setCellValue(qyaqbean.getData1());
				cel1.setCellStyle(css);
				HSSFCell cel2 = row0.createCell(2);
				cel2.setCellValue(qyaqbean.getData2());
				cel2.setCellStyle(css);
				HSSFCell cel3 = row0.createCell(3);
				cel3.setCellValue(qyaqbean.getData3());
				cel3.setCellStyle(css);
				HSSFCell cel4 = row0.createCell(4);
				cel4.setCellValue(qyaqbean.getData4());
				cel4.setCellStyle(css);
				HSSFCell cel5= row0.createCell(5);
				cel5.setCellValue(qyaqbean.getData5());
				cel5.setCellStyle(css);
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

	public Qyaqscyt getQyaqscyt(){
		return this.qyaqscyt;
	}

	public void setQyaqscyt(Qyaqscyt qyaqscyt){
		this.qyaqscyt = qyaqscyt;
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

	public List<QyaqscytBean> getQyaqlist() {
		return qyaqlist;
	}

	public void setQyaqlist(List<QyaqscytBean> qyaqlist) {
		this.qyaqlist = qyaqlist;
	}

}
