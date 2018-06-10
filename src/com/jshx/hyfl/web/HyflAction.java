package com.jshx.hyfl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.hyfl.entity.Hyfl;
import com.jshx.hyfl.service.HyflService;
import com.jshx.module.admin.entity.Department;

public class HyflAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Hyfl hyfl = new Hyfl();

	/**
	 * 业务类
	 */
	@Autowired
	private HyflService hyflService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;

	private File hyflFile;
	private String logInfo;
	private String hyname;
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != hyfl){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != hyfl.getCode()) && (0 < hyfl.getCode().trim().length())){
				paraMap.put("code", "%" + hyfl.getCode().trim() + "%");
			}

			if ((null != hyfl.getHyname()) && (0 < hyfl.getHyname().trim().length())){
				paraMap.put("hyname", "%" + hyfl.getHyname().trim() + "%");
			}

			if ((null != hyfl.getHytext()) && (0 < hyfl.getHytext().trim().length())){
				paraMap.put("hytext", "%" + hyfl.getHytext().trim() + "%");
			}

		}
		
		pagination = hyflService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != hyfl)&&(null != hyfl.getId()))
			hyfl = hyflService.getById(hyfl.getId());
		
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
			hyfl.setDeptId(this.getLoginUserDepartmentId());
			hyfl.setDelFlag(0);
			hyflService.save(hyfl);
		}else{
			hyflService.update(hyfl);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			hyflService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	

	public String initImportHyfl()
	{
		return "success";
	}
	

	/**
	 * hanxc 20150212 太仓企业信息导入 补录信息
	 * @return
	 */
	public String importHyfl()
	{
		StringBuffer log = new StringBuffer("导入行业类型日志：<br>");
		HSSFWorkbook workbook = null;
		try{
			workbook = new HSSFWorkbook(new FileInputStream(this.hyflFile));
		}catch (IOException e){
			e.printStackTrace();
			log.append("导入失败：读取Excle文件出错，请检查!<br>");
			logInfo = "导入失败：读取Excle文件出错，请检查!";
			return "success";
		}
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFRow row = null;
		for (int i = 0; i <= sheet.getLastRowNum(); i++){
			try{
				row = sheet.getRow(i);
				Hyfl impHyfl = new Hyfl();
				impHyfl.setDelFlag(0);
				impHyfl.setCode(row.getCell(0) != null ? row.getCell(0).toString().trim() : "");
				impHyfl.setHyname(row.getCell(1) != null ? row.getCell(1).toString().trim() : "");
				impHyfl.setHytext(row.getCell(2) != null ? row.getCell(2).toString().trim() : "");
				hyflService.save(impHyfl);
			}catch (Exception e){
				e.printStackTrace();
				log.append("记录数").append(i).append("导入失败：未知的异常！<br>");
				logInfo = "导入失败：未知的异常！";
				continue;
			}
		}
		logInfo = "导入成功";
		return "success";
	}
	public void findCodeName(){
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != hyfl && null != hyfl.getCode() && !"".equals(hyfl.getCode().trim())){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != hyfl.getCode()) && (0 < hyfl.getCode().trim().length())){
				paraMap.put("code", "%" + hyfl.getCode().trim() + "%");
			}

			pagination = hyflService.findByPage(pagination, paraMap);
			List<Hyfl> list = pagination.getListOfObject(); 
			outputJson("id|hyname|", (Hyfl)list.get(0));
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

	public Hyfl getHyfl(){
		return this.hyfl;
	}

	public void setHyfl(Hyfl hyfl){
		this.hyfl = hyfl;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public File getHyflFile() {
		return hyflFile;
	}

	public void setHyflFile(File hyflFile) {
		this.hyflFile = hyflFile;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public String getHyname() {
		return hyname;
	}

	public void setHyname(String hyname) {
		this.hyname = hyname;
	}

    
}
