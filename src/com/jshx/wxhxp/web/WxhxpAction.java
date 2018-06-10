package com.jshx.wxhxp.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.utils.StringTools;
import com.jshx.wxhxp.entity.Wxhxp;
import com.jshx.wxhxp.service.WxhxpService;

public class WxhxpAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Wxhxp wxhxp = new Wxhxp();

	/**
	 * 业务类
	 */
	@Autowired
	private WxhxpService wxhxpService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != wxhxp){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != wxhxp.getWhpnum()) && (0 < wxhxp.getWhpnum().trim().length())){
				paraMap.put("whpnum", "%" + wxhxp.getWhpnum().trim() + "%");
			}

			if ((null != wxhxp.getWhpname()) && (0 < wxhxp.getWhpname().trim().length())){
				paraMap.put("whpname", "%" + wxhxp.getWhpname().trim() + "%");
			}

			if ((null != wxhxp.getBname()) && (0 < wxhxp.getBname().trim().length())){
				paraMap.put("bname", "%" + wxhxp.getBname().trim() + "%");
			}

			if ((null != wxhxp.getUnnum()) && (0 < wxhxp.getUnnum().trim().length())){
				paraMap.put("unnum", "%" + wxhxp.getUnnum().trim() + "%");
			}

		}
		
		pagination = wxhxpService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != wxhxp)&&(null != wxhxp.getId()))
			wxhxp = wxhxpService.getById(wxhxp.getId());
		
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
			wxhxp.setDeptId(this.getLoginUserDepartmentId());
			wxhxp.setDelFlag(0);
			wxhxpService.save(wxhxp);
		}else{
			wxhxpService.update(wxhxp);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			wxhxpService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public void saveExcel() throws Exception{
		if(!Filedata.isEmpty()){
			File file = Filedata.get(0);
	        Workbook wb = null;  
	        try {  
	            // 构造Workbook（工作薄）对象  
	            wb = Workbook.getWorkbook(file);  
	        // 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了  
	        Sheet sheet = wb.getSheet(0);  
	  
	            // 对每个工作表进行循环  
	                // 得到当前工作表的行数  
	                int rowNum = sheet.getRows();  
	                for (int j = 1; j < rowNum; j++) {
	                    // 得到当前行的所有单元格  
	                    Cell[] cells = sheet.getRow(j);  
	                    if (cells != null && cells.length >=2) {
	                    	if(cells[0].getContents()!=null&&!"".equals(cells[0].getContents())&& cells[1].getContents()!=null&&!"".equals(cells[1].getContents())){
	                    		 // 对每个单元格进行循环  
	                    		Wxhxp wxhxp = new Wxhxp();
	                    		wxhxp.setWhpnum(cells[0].getContents());
	                    		wxhxp.setWhpname(cells[1].getContents());
	                    		wxhxp.setBname(cells[2].getContents());
	                    		wxhxp.setUnnum(cells[3].getContents());
	                    		wxhxp.setDelFlag(0);
	                    		wxhxpService.save(wxhxp);
	                    	} 
                       	}
	                }
	        // 最后关闭资源，释放内存  
	        wb.close(); 
	        this.getResponse().getWriter().println("{\"result\":\"true\"}");
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	            this.getResponse().getWriter().println("{\"result\":\"false\"}");
	        }
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

	public Wxhxp getWxhxp(){
		return this.wxhxp;
	}

	public void setWxhxp(Wxhxp wxhxp){
		this.wxhxp = wxhxp;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}
       
    
}
