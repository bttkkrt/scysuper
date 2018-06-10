package com.jshx.whpjyxk.web;

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

import com.jshx.aqscwyhtj.entity.Aqscwyhtj;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.entity.User;
import com.jshx.module.form.service.AttachfileService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.whpjyxk.entity.Whpjyxk;
import com.jshx.whpjyxk.service.WhpjyxkService;

public class WhpjyxkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Whpjyxk whpjyxk = new Whpjyxk();

	/**
	 * 业务类
	 */
	@Autowired
	private WhpjyxkService whpjyxkService;

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
	
	
	private List<Whpjyxk> whpjyxklist=new ArrayList<Whpjyxk>();

	
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
		    
		if(null != whpjyxk){
		    //设置查询条件，开发人员可以在此增加过滤条件
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
		final String filter = "id|monthTime|";
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
		pagination = whpjyxkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != whpjyxk)&&(null != whpjyxk.getId()))
			whpjyxk = whpjyxkService.getById(whpjyxk.getId());
		
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
	
		if ("add".equalsIgnoreCase(this.flag)){
			whpjyxk.setDeptId(this.getLoginUserDepartmentId());
			whpjyxk.setDelFlag(0);
			whpjyxkService.save(whpjyxk);
		}else{
			whpjyxkService.update(whpjyxk);
		}
		
		return RELOAD;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != whpjyxk)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到whpjyxk中去
				
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
			whpjyxkService.deleteWithFlag(ids);
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
		
			if (null != queryMonthTimeStart){
				paraMap.put("startMonthTime", queryMonthTimeStart);
				setSessionAttribute("queryMonthTimeStart", queryMonthTimeStart);
			}
 
			
			try {
				whpjyxklist = whpjyxkService.getWhpjyxkListByMap(paraMap);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=whpjy.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "whpjy.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			HSSFSheet sheet = wb.getSheetAt(0);
	      
	       
	       
	        	if(whpjyxklist.size()!=0){
	        	 whpjyxk =whpjyxkService.getById( whpjyxklist.get(0).getId());
	        	}
	        	HSSFRow row0 = sheet.createRow(4);
	        	HSSFCellStyle css = wb.createCellStyle();
	        	 css.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
	        	 css.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	        	 css.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	        	 css.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	        	
	        	HSSFCell cel0 = row0.createCell(0);
				cel0.setCellValue("上月底持证数");
				cel0.setCellStyle(css);
				HSSFCell cel1 = row0.createCell(1);
				cel1.setCellValue(whpjyxk.getZxqsyd());
				cel1.setCellStyle(css);
				HSSFCell cel2 = row0.createCell(2);
				cel2.setCellValue(whpjyxk.getJdsyd());
				cel2.setCellStyle(css);
				HSSFCell cel3 = row0.createCell(3);
				cel3.setCellValue(whpjyxk.getZjsyd());
				cel3.setCellStyle(css);
				HSSFCell cel4 = row0.createCell(4);
				cel4.setCellValue(whpjyxk.getYqwccsyd());
				cel4.setCellStyle(css);
				HSSFCell cel5 = row0.createCell(5);
				cel5.setCellValue(whpjyxk.getYqlssyd());
				cel5.setCellStyle(css);
				HSSFCell cel6 = row0.createCell(6);
				cel6.setCellValue(whpjyxk.getYqsccsyd());
				cel6.setCellStyle(css);
				HSSFCell cel7 = row0.createCell(7);
				cel7.setCellValue(whpjyxk.getJyzsyd());
				cel7.setCellStyle(css);
				HSSFCell cel8 = row0.createCell(8);
				cel8.setCellValue(whpjyxk.getJdpsyd());
				cel8.setCellStyle(css);
				HSSFCell cel9 = row0.createCell(9);
				cel9.setCellValue(whpjyxk.getYzbsyd());
				cel9.setCellStyle(css);
				HSSFCell cel10 = row0.createCell(10);
				cel10.setCellValue(whpjyxk.getFzsyd());
				cel10.setCellStyle(css);
				HSSFCell cel11 = row0.createCell(11);
				cel11.setCellValue(whpjyxk.getCcsyd());
				cel11.setCellStyle(css);
				
				
				HSSFRow row1 = sheet.createRow(5);
	        	HSSFCell cel12 = row1.createCell(0);
				cel12.setCellValue("本月新领证数");
				cel12.setCellStyle(css);
				HSSFCell cel13 = row1.createCell(1);
				cel13.setCellValue(whpjyxk.getZxqbyxl());
				cel13.setCellStyle(css);
				HSSFCell cel14 = row1.createCell(2);
				cel14.setCellValue(whpjyxk.getJdbyxl());
				cel14.setCellStyle(css);
				HSSFCell cel15 = row1.createCell(3);
				cel15.setCellValue(whpjyxk.getZjbyxl());
				cel15.setCellStyle(css);
				HSSFCell cel16 = row1.createCell(4);
				cel16.setCellValue(whpjyxk.getYqwccbyxl());
				cel16.setCellStyle(css);
				HSSFCell cel17 = row1.createCell(5);
				cel17.setCellValue(whpjyxk.getYqlsbyxl());
				cel17.setCellStyle(css);
				HSSFCell cel18 = row1.createCell(6);
				cel18.setCellValue(whpjyxk.getYqsccbyxl());
				cel18.setCellStyle(css);
				HSSFCell cel19 = row1.createCell(7);
				cel19.setCellValue(whpjyxk.getJyzbyxl());
				cel19.setCellStyle(css);
				HSSFCell cel20 = row1.createCell(8);
				cel20.setCellValue(whpjyxk.getJdpbyxl());
				cel20.setCellStyle(css);
				HSSFCell cel21 = row1.createCell(9);
				cel21.setCellValue(whpjyxk.getYzbbyxl());
				cel21.setCellStyle(css);
				HSSFCell cel22 = row1.createCell(10);
				cel22.setCellValue(whpjyxk.getFzbyxl());
				cel22.setCellStyle(css);
				HSSFCell cel23 = row1.createCell(11);
				cel23.setCellValue(whpjyxk.getCcbyxl());
				cel23.setCellStyle(css);
				
				
				
				HSSFRow row2 = sheet.createRow(6);
	        	HSSFCell cel24 = row2.createCell(0);
				cel24.setCellValue("本月换领证数");
				cel24.setCellStyle(css);
				HSSFCell cel25 = row2.createCell(1);
				cel25.setCellValue(whpjyxk.getZxqbyhz());
				cel25.setCellStyle(css);
				HSSFCell cel26 = row2.createCell(2);
				cel26.setCellValue(whpjyxk.getJdbyhz());
				cel26.setCellStyle(css);
				HSSFCell cel27 = row2.createCell(3);
				cel27.setCellValue(whpjyxk.getZjbyhz());
				cel27.setCellStyle(css);
				HSSFCell cel28 = row2.createCell(4);
				cel28.setCellValue(whpjyxk.getYqwccbyhz());
				cel28.setCellStyle(css);
				HSSFCell cel29 = row2.createCell(5);
				cel29.setCellValue(whpjyxk.getYqlsbyhz());
				cel29.setCellStyle(css);
				HSSFCell cel30 = row2.createCell(6);
				cel30.setCellValue(whpjyxk.getYqsccbyhz());
				cel30.setCellStyle(css);
				HSSFCell cel31 = row2.createCell(7);
				cel31.setCellValue(whpjyxk.getJyzbyhz());
				cel31.setCellStyle(css);
				HSSFCell cel32 = row2.createCell(8);
				cel32.setCellValue(whpjyxk.getJdpbyhz());
				cel32.setCellStyle(css);
				HSSFCell cel33 = row2.createCell(9);
				cel33.setCellValue(whpjyxk.getYzbbyhz());
				cel33.setCellStyle(css);
				HSSFCell cel34 = row2.createCell(10);
				cel34.setCellValue(whpjyxk.getFzbyhz());
				cel34.setCellStyle(css);
				HSSFCell cel35 = row2.createCell(11);
				cel35.setCellValue(whpjyxk.getCcbyhz());
				cel35.setCellStyle(css);
				
				
				HSSFRow row3 = sheet.createRow(7);
	        	HSSFCell cel36 = row3.createCell(0);
				cel36.setCellValue("本月过期许可证数");
				cel36.setCellStyle(css);
				HSSFCell cel37 = row3.createCell(1);
				cel37.setCellValue(whpjyxk.getZxqbygq());
				cel37.setCellStyle(css);
				HSSFCell cel38 = row3.createCell(2);
				cel38.setCellValue(whpjyxk.getJdbygq());
				cel38.setCellStyle(css);
				HSSFCell cel39 = row3.createCell(3);
				cel39.setCellValue(whpjyxk.getZjbygq());
				cel39.setCellStyle(css);
				HSSFCell cel40 = row3.createCell(4);
				cel40.setCellValue(whpjyxk.getYqwccbygq());
				cel40.setCellStyle(css);
				HSSFCell cel41 = row3.createCell(5);
				cel41.setCellValue(whpjyxk.getYqlsbygq());
				cel41.setCellStyle(css);
				HSSFCell cel42 = row3.createCell(6);
				cel42.setCellValue(whpjyxk.getYqsccbygq());
				cel42.setCellStyle(css);
				HSSFCell cel43 = row3.createCell(7);
				cel43.setCellValue(whpjyxk.getJyzbygq());
				cel43.setCellStyle(css);
				HSSFCell cel44 = row3.createCell(8);
				cel44.setCellValue(whpjyxk.getJdpbygq());
				cel44.setCellStyle(css);
				HSSFCell cel45 = row3.createCell(9);
				cel45.setCellValue(whpjyxk.getYzbbygq());
				cel45.setCellStyle(css);
				HSSFCell cel46 = row3.createCell(10);
				cel46.setCellValue(whpjyxk.getFzbygq());
				cel46.setCellStyle(css);
				HSSFCell cel47 = row3.createCell(11);
				cel47.setCellValue(whpjyxk.getCcbygq());
				cel47.setCellStyle(css);
				
				
				HSSFRow row4 = sheet.createRow(8);
	        	HSSFCell cel48 = row4.createCell(0);
				cel48.setCellValue("其中：申报换证数");
				cel48.setCellStyle(css);
				HSSFCell cel49 = row4.createCell(1);
				cel49.setCellValue(whpjyxk.getZxqsbhz());
				cel49.setCellStyle(css);
				HSSFCell cel50 = row4.createCell(2);
				cel50.setCellValue(whpjyxk.getJdsbhz());
				cel50.setCellStyle(css);
				HSSFCell cel51 = row4.createCell(3);
				cel51.setCellValue(whpjyxk.getZjsbhz());
				cel51.setCellStyle(css);
				HSSFCell cel52 = row4.createCell(4);
				cel52.setCellValue(whpjyxk.getYqwccsbhz());
				cel52.setCellStyle(css);
				HSSFCell cel53 = row4.createCell(5);
				cel53.setCellValue(whpjyxk.getYqlssbhz());
				cel53.setCellStyle(css);
				HSSFCell cel54 = row4.createCell(6);
				cel54.setCellValue(whpjyxk.getYqsccsbhz());
				cel54.setCellStyle(css);
				HSSFCell cel55 = row4.createCell(7);
				cel55.setCellValue(whpjyxk.getJyzsbhz());
				cel55.setCellStyle(css);
				HSSFCell cel56 = row4.createCell(8);
				cel56.setCellValue(whpjyxk.getJdpsbhz());
				cel56.setCellStyle(css);
				HSSFCell cel57 = row4.createCell(9);
				cel57.setCellValue(whpjyxk.getYzbsbhz());
				cel57.setCellStyle(css);
				HSSFCell cel58 = row4.createCell(10);
				cel58.setCellValue(whpjyxk.getFzsbhz());
				cel58.setCellStyle(css);
				HSSFCell cel59 = row4.createCell(11);
				cel59.setCellValue(whpjyxk.getCcsbhz());
				cel59.setCellStyle(css);
				
				HSSFRow row5 = sheet.createRow(9);
	        	HSSFCell cel60 = row5.createCell(0);
				cel60.setCellValue("本月注销证数");
				cel60.setCellStyle(css);
				HSSFCell cel61 = row5.createCell(1);
				cel61.setCellValue(whpjyxk.getZxqbyzx());
				cel61.setCellStyle(css);
				HSSFCell cel62 = row5.createCell(2);
				cel62.setCellValue(whpjyxk.getJdbyzx());
				cel62.setCellStyle(css);
				HSSFCell cel63 = row5.createCell(3);
				cel63.setCellValue(whpjyxk.getZjbyzx());
				cel63.setCellStyle(css);
				HSSFCell cel64 = row5.createCell(4);
				cel64.setCellValue(whpjyxk.getYqwccbyzx());
				cel64.setCellStyle(css);
				HSSFCell cel65 = row5.createCell(5);
				cel65.setCellValue(whpjyxk.getYqlsbyzx());
				cel65.setCellStyle(css);
				HSSFCell cel66 = row5.createCell(6);
				cel66.setCellValue(whpjyxk.getYqsccbyzx());
				cel66.setCellStyle(css);
				HSSFCell cel67 = row5.createCell(7);
				cel67.setCellValue(whpjyxk.getJyzbyzx());
				cel67.setCellStyle(css);
				HSSFCell cel68 = row5.createCell(8);
				cel68.setCellValue(whpjyxk.getJdpbyzx());
				cel68.setCellStyle(css);
				HSSFCell cel69 = row5.createCell(9);
				cel69.setCellValue(whpjyxk.getYzbbyzx());
				cel69.setCellStyle(css);
				HSSFCell cel70 = row5.createCell(10);
				cel70.setCellValue(whpjyxk.getFzbyzx());
				cel70.setCellStyle(css);
				HSSFCell cel71 = row5.createCell(11);
				cel71.setCellValue(whpjyxk.getCcbyzx());
				cel71.setCellStyle(css);
				
				HSSFRow row6 = sheet.createRow(10);
	        	HSSFCell cel72 = row6.createCell(0);
				cel72.setCellValue("本月底持证数");
				cel72.setCellStyle(css);
				HSSFCell cel73 = row6.createCell(1);
				cel73.setCellValue(whpjyxk.getZxqbycz());
				cel73.setCellStyle(css);
				HSSFCell cel74 = row6.createCell(2);
				cel74.setCellValue(whpjyxk.getJdbycz());
				cel74.setCellStyle(css);
				HSSFCell cel75 = row6.createCell(3);
				cel75.setCellValue(whpjyxk.getZjbycz());
				cel75.setCellStyle(css);
				HSSFCell cel76 = row6.createCell(4);
				cel76.setCellValue(whpjyxk.getYqwccbycz());
				cel76.setCellStyle(css);
				HSSFCell cel77 = row6.createCell(5);
				cel77.setCellValue(whpjyxk.getYqlsbycz());
				cel77.setCellStyle(css);
				HSSFCell cel78 = row6.createCell(6);
				cel78.setCellValue(whpjyxk.getYqsccbycz());
				cel78.setCellStyle(css);
				HSSFCell cel79 = row6.createCell(7);
				cel79.setCellValue(whpjyxk.getJyzbycz());
				cel79.setCellStyle(css);
				HSSFCell cel80 = row6.createCell(8);
				cel80.setCellValue(whpjyxk.getJdpbycz());
				cel80.setCellStyle(css);
				HSSFCell cel81 = row6.createCell(9);
				cel81.setCellValue(whpjyxk.getYzbbycz());
				cel81.setCellStyle(css);
				HSSFCell cel82 = row6.createCell(10);
				cel82.setCellValue(whpjyxk.getFzbycz());
				cel82.setCellStyle(css);
				HSSFCell cel83 = row6.createCell(11);
				cel83.setCellValue(whpjyxk.getCcbycz());
				cel83.setCellStyle(css);
				
				
	     
			
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

	public Whpjyxk getWhpjyxk(){
		return this.whpjyxk;
	}

	public void setWhpjyxk(Whpjyxk whpjyxk){
		this.whpjyxk = whpjyxk;
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

}
