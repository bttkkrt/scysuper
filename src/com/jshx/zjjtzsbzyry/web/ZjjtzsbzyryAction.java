package com.jshx.zjjtzsbzyry.web;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.utils.ExcelTools;
import com.jshx.utils.StringTools;
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;
import com.jshx.zjjtzsbzyry.service.ZjjtzsbzyryService;

public class ZjjtzsbzyryAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zjjtzsbzyry zjjtzsbzyry = new Zjjtzsbzyry();

	/**
	 * 业务类
	 */
	@Autowired
	private ZjjtzsbzyryService zjjtzsbzyryService;

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
		if(deptCode.startsWith("003"))//质监局
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
		    
		if(null != zjjtzsbzyry){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zjjtzsbzyry.getMc()) && (0 < zjjtzsbzyry.getMc().trim().length())){
				paraMap.put("mc", "%" + zjjtzsbzyry.getMc().trim() + "%");
			}
			
			if ((null != zjjtzsbzyry.getSzzname()) && (0 < zjjtzsbzyry.getSzzname().trim().length())){
				paraMap.put("szzname", zjjtzsbzyry.getSzzname().trim());
			}
			
			if ((null != zjjtzsbzyry.getXl()) && (0 < zjjtzsbzyry.getXl().trim().length())){
				paraMap.put("xl", zjjtzsbzyry.getXl().trim());
			}

			if ((null != zjjtzsbzyry.getSfz()) && (0 < zjjtzsbzyry.getSfz().trim().length())){
				paraMap.put("sfz", "%" + zjjtzsbzyry.getSfz().trim() + "%");
			}

			if ((null != zjjtzsbzyry.getXm()) && (0 < zjjtzsbzyry.getXm().trim().length())){
				paraMap.put("xm", zjjtzsbzyry.getXm().trim());
			}

			if ((null != zjjtzsbzyry.getPydw()) && (0 < zjjtzsbzyry.getPydw().trim().length())){
				paraMap.put("pydw", "%" + zjjtzsbzyry.getPydw().trim() + "%");
			}
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
		pagination = zjjtzsbzyryService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zjjtzsbzyry)&&(null != zjjtzsbzyry.getId()))
			zjjtzsbzyry = zjjtzsbzyryService.getById(zjjtzsbzyry.getId());
		
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
			zjjtzsbzyry.setDeptId(this.getLoginUserDepartmentId());
			zjjtzsbzyry.setDelFlag(0);
			zjjtzsbzyryService.save(zjjtzsbzyry);
		}else{
			zjjtzsbzyryService.update(zjjtzsbzyry);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zjjtzsbzyryService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	
	public String deletes() throws Exception{
	    try{
			zjjtzsbzyryService.deleteAll();
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
				List<List<String>> lists = ExcelTools.parseExcel(Filedata.get(0),"1");
				for(List<String> l:lists){
						int len = l.size();
						if(len<12){
							for(int j=0;j<12-len;j++){
								l.add("");
							}
						}
					  int i=0;
					  if(l.get(0) != null && !"".equals(l.get(0)))
					  {
						  Zjjtzsbzyry zjjtzsbzyry = new Zjjtzsbzyry();
						  zjjtzsbzyry.setMc(l.get(i++));
						  zjjtzsbzyry.setSex(l.get(i++));
						  zjjtzsbzyry.setSzzname(l.get(i++));
						  zjjtzsbzyry.setXl(l.get(i++));
						  zjjtzsbzyry.setSfz(l.get(i++));
						  zjjtzsbzyry.setLxdh(l.get(i++));
						  zjjtzsbzyry.setXm(StringTools.getXmIndexFromName(l.get(i++)));
						  zjjtzsbzyry.setPydw(l.get(i++));
						  zjjtzsbzyry.setDwdz(l.get(i++));
						  zjjtzsbzyry.setDwlxdh(l.get(i++));
						  zjjtzsbzyry.setPzrq(l.get(i++));
						  zjjtzsbzyry.setYxrq(l.get(i++));
						  zjjtzsbzyry.setDelFlag(0);
						  zjjtzsbzyry.setCreateTime(new Date());
						  zjjtzsbzyry.setDeptId(this.getLoginUserDepartmentId());
						  zjjtzsbzyry.setCreateUserID(this.getLoginUserId());
						  zjjtzsbzyryService.save(zjjtzsbzyry);
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

	public Zjjtzsbzyry getZjjtzsbzyry(){
		return this.zjjtzsbzyry;
	}

	public void setZjjtzsbzyry(Zjjtzsbzyry zjjtzsbzyry){
		this.zjjtzsbzyry = zjjtzsbzyry;
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
