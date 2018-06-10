package com.jshx.zjjtzsbs.web;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.utils.ExcelTools;
import com.jshx.zjjtzsbs.entity.Zjjtzsbs;
import com.jshx.zjjtzsbs.service.ZjjtzsbsService;

public class ZjjtzsbsAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zjjtzsbs zjjtzsbs = new Zjjtzsbs();

	/**
	 * 业务类
	 */
	@Autowired
	private ZjjtzsbsService zjjtzsbsService;

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
	
	private String companyId;
	
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;
	
	private String type;
	
	private String queryDjrqStart;
	private String queryDjrqEnd;
	private String queryCcrqStart;
	private String queryCcrqEnd;
	private String queryJyrqStart;
	private String queryJyrqEnd;
	private String queryXcjyrqStart;
	private String queryXcjyrqEnd;
	
	/**
	 * 初始化质监局特种设备作业人员
	 * 陆婷 
	 * 2013-12-27
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith("003"))
		{
			flag = "1";
		}
		else
		{
			flag = "0";
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
		if(null != zjjtzsbs){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zjjtzsbs.getQymc()) && (0 < zjjtzsbs.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zjjtzsbs.getQymc().trim() + "%");
			}
			if ((null != zjjtzsbs.getSzzname()) && (0 < zjjtzsbs.getSzzname().trim().length())){
				paraMap.put("szzname", zjjtzsbs.getSzzname().trim());
			}
			if ((null != zjjtzsbs.getZcdm()) && (0 < zjjtzsbs.getZcdm().trim().length())){
				paraMap.put("zcdm", "%" + zjjtzsbs.getZcdm().trim() + "%");
			}
			if ((null != zjjtzsbs.getSbdah()) && (0 < zjjtzsbs.getSbdah().trim().length())){
				paraMap.put("sbdah", "%" + zjjtzsbs.getSbdah().trim() + "%");
			}
			if ((null != zjjtzsbs.getCcbh()) && (0 < zjjtzsbs.getCcbh().trim().length())){
				paraMap.put("ccbh", "%" + zjjtzsbs.getCcbh().trim() + "%");
			}
		}
		if(null != queryDjrqStart)
		{
			paraMap.put("queryDjrqStart", queryDjrqStart);
		}
		
		if(null != queryDjrqEnd)
		{
			paraMap.put("queryDjrqEnd", queryDjrqEnd);
		}
		
		if(null != queryCcrqStart)
		{
			paraMap.put("queryCcrqStart", queryCcrqStart);
		}
		
		if(null != queryCcrqEnd)
		{
			paraMap.put("queryCcrqEnd", queryCcrqEnd);
		}
		
		if(null != queryJyrqStart)
		{
			paraMap.put("queryJyrqStart", queryJyrqStart);
		}
		
		if(null != queryJyrqEnd)
		{
			paraMap.put("queryJyrqEnd", queryJyrqEnd);
		}
		
		if(null != queryXcjyrqStart)
		{
			paraMap.put("queryXcjyrqStart", queryXcjyrqStart);
		}
		
		if(null != queryXcjyrqEnd)
		{
			paraMap.put("queryXcjyrqEnd", queryXcjyrqEnd);
		}
		
		pagination = zjjtzsbsService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zjjtzsbs)&&(null != zjjtzsbs.getId()))
		{
			zjjtzsbs = zjjtzsbsService.getById(zjjtzsbs.getId());
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
		if ("add".equalsIgnoreCase(this.flag)){
			zjjtzsbs.setDeptId(this.getLoginUserDepartmentId());
			zjjtzsbs.setDelFlag(0);
			zjjtzsbs.setCreateUserID(this.getLoginUserId());
			zjjtzsbs.setCreateTime(new Date());
			zjjtzsbsService.save(zjjtzsbs);
		}else{
			zjjtzsbsService.update(zjjtzsbs);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zjjtzsbsService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String deletes() throws Exception{
	    try{
			zjjtzsbsService.deleteAll();
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 文件导入 陆婷 2013-12-27
	 * @return
	 */
	public String upload() throws Exception{
		return SUCCESS;
	}
	/**
	 * 文件保存 陆婷 2013-12-27
	 * @return
	 */
	public void saveFile() throws Exception{
		try {
			if(Filedata!= null && !Filedata.isEmpty()){
				List<List<String>> lists = ExcelTools.parseExcel(Filedata.get(0),"2");
				for(List<String> l:lists){
						int len = l.size();
						if(len<16){
							for(int j=0;j<16-len;j++){
								l.add("");
							}
						}
					  int i=0;
					  if(l.get(2) != null && !"".equals(l.get(2)) && l.get(0) != null && !"".equals(l.get(0)))
					  {
						  Zjjtzsbs zjjtzsbs = new Zjjtzsbs();
						  zjjtzsbs.setZcdm(l.get(i++));
						  zjjtzsbs.setZcdjrq(l.get(i++));
						  zjjtzsbs.setSbdah(l.get(i++));
						  zjjtzsbs.setSblb(l.get(i++));
						  zjjtzsbs.setXsblb(l.get(i++));
						  zjjtzsbs.setQymc(l.get(i++));
						  zjjtzsbs.setSzzname(l.get(i++));
						  zjjtzsbs.setSzdd(l.get(i++));
						  zjjtzsbs.setSbzt(l.get(i++));
						  zjjtzsbs.setDwlxr(l.get(i++));
						  zjjtzsbs.setDh(l.get(i++));
						  zjjtzsbs.setDwdz(l.get(i++));
						  zjjtzsbs.setCcrq(l.get(i++));
						  zjjtzsbs.setCcbh(l.get(i++));
						  zjjtzsbs.setJyrq(l.get(i++));
						  zjjtzsbs.setXcjyrq(l.get(i++));
						  zjjtzsbs.setDelFlag(0);
						  zjjtzsbs.setCreateTime(new Date());
						  zjjtzsbs.setDeptId(this.getLoginUserDepartmentId());
						  zjjtzsbs.setCreateUserID(this.getLoginUserId());
						  zjjtzsbsService.save(zjjtzsbs);
					  }
				}
				
			}
			//在此处调用图片的保存
			this.getResponse().getWriter().write("1");
		} catch (Exception e) {
				e.printStackTrace();
				this.getResponse().getWriter().write("0");
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
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getPicName() {
		return picName;
	}
	public void setPicName(String picName) {
		this.picName = picName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Zjjtzsbs getZjjtzsbs() {
		return zjjtzsbs;
	}
	public void setZjjtzsbs(Zjjtzsbs zjjtzsbs) {
		this.zjjtzsbs = zjjtzsbs;
	}
	public String getQueryDjrqStart() {
		return queryDjrqStart;
	}
	public void setQueryDjrqStart(String queryDjrqStart) {
		this.queryDjrqStart = queryDjrqStart;
	}
	public String getQueryDjrqEnd() {
		return queryDjrqEnd;
	}
	public void setQueryDjrqEnd(String queryDjrqEnd) {
		this.queryDjrqEnd = queryDjrqEnd;
	}
	public String getQueryCcrqStart() {
		return queryCcrqStart;
	}
	public void setQueryCcrqStart(String queryCcrqStart) {
		this.queryCcrqStart = queryCcrqStart;
	}
	public String getQueryCcrqEnd() {
		return queryCcrqEnd;
	}
	public void setQueryCcrqEnd(String queryCcrqEnd) {
		this.queryCcrqEnd = queryCcrqEnd;
	}
	public String getQueryJyrqStart() {
		return queryJyrqStart;
	}
	public void setQueryJyrqStart(String queryJyrqStart) {
		this.queryJyrqStart = queryJyrqStart;
	}
	public String getQueryJyrqEnd() {
		return queryJyrqEnd;
	}
	public void setQueryJyrqEnd(String queryJyrqEnd) {
		this.queryJyrqEnd = queryJyrqEnd;
	}
	public String getQueryXcjyrqStart() {
		return queryXcjyrqStart;
	}
	public void setQueryXcjyrqStart(String queryXcjyrqStart) {
		this.queryXcjyrqStart = queryXcjyrqStart;
	}
	public String getQueryXcjyrqEnd() {
		return queryXcjyrqEnd;
	}
	public void setQueryXcjyrqEnd(String queryXcjyrqEnd) {
		this.queryXcjyrqEnd = queryXcjyrqEnd;
	}

}
