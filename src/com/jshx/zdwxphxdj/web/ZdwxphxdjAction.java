/**
 * Class Name: ZdwxphxdjAction
 * Class Description：危险化学品重大危险源核销登记
 */
package com.jshx.zdwxphxdj.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zdwxphxdj.entity.Zdwxphxdj;
import com.jshx.zdwxphxdj.service.ZdwxphxdjService;

public class ZdwxphxdjAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zdwxphxdj zdwxphxdj = new Zdwxphxdj();

	/**
	 * 业务类
	 */
	@Autowired
	private ZdwxphxdjService zdwxphxdjService;
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
	
	
	private Date queryDjrqStart;

	private Date queryDjrqEnd;
	@Autowired
	private SzwxPhotoService szwxPhotoService;
	 /**
	 * @author gq
	 * @date 8yue 20
	 * @function 存放附件列表
	 */
	private List<PhotoPic> list = new ArrayList<PhotoPic>();
	
	private Date createTimeStart;

	private Date createTimeEnd;
	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	/**
	 * 初始化安全附件情况列表
	 * author：陆婷
	 * 2013-08-19
	 */
	public String init()
	{
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
		{
			flag = "2";
		}
		else
		{
			flag = "3";
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
		    
		if(null != zdwxphxdj){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zdwxphxdj.getHxbh()) && (0 < zdwxphxdj.getHxbh().trim().length())){
				paraMap.put("hxbh", "%" + zdwxphxdj.getHxbh().trim() + "%");
			}

			if ((null != zdwxphxdj.getQymc()) && (0 < zdwxphxdj.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zdwxphxdj.getQymc().trim() + "%");
			}

			if ((null != zdwxphxdj.getZdwxymc()) && (0 < zdwxphxdj.getZdwxymc().trim().length())){
				paraMap.put("zdwxymc", "%" + zdwxphxdj.getZdwxymc().trim() + "%");
			}

			if ((null != zdwxphxdj.getZdwxybabh()) && (0 < zdwxphxdj.getZdwxybabh().trim().length())){
				paraMap.put("zdwxybabh", "%" + zdwxphxdj.getZdwxybabh().trim() + "%");
			}

			if (null != queryDjrqStart){
				paraMap.put("startDjrq", queryDjrqStart);
			}

			if (null != queryDjrqEnd){
				paraMap.put("endDjrq", queryDjrqEnd);
			}
			if ((null != zdwxphxdj.getSzzid()) && (0 < zdwxphxdj.getSzzid().trim().length())){
				paraMap.put("szzid",  zdwxphxdj.getSzzid().trim() );
			}
			if ((null != zdwxphxdj.getSzc() )&& (0 < zdwxphxdj.getSzc().trim().length())){
				paraMap.put("szc",zdwxphxdj.getSzc().trim());
			}
		}
		if (null != createTimeStart){
			paraMap.put("startCreateTime", createTimeStart);
		}

		if (null != createTimeEnd){
			paraMap.put("endCreateTime", createTimeEnd);
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		
		pagination = zdwxphxdjService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zdwxphxdj)&&(null != zdwxphxdj.getId()))
			zdwxphxdj = zdwxphxdjService.getById(zdwxphxdj.getId());
		if(zdwxphxdj.getLinkid()==null)
		{
			zdwxphxdj.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",zdwxphxdj.getLinkid());
		map.put("picType","zdwxyhxdjb");
	    list = szwxPhotoService.findPicPath(map);//获取图片或附件列表
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		zdwxphxdj.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		zdwxphxdj.setZdwxymc(sdf.format(new Date()));
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zdwxphxdj.setSzzid(dept.getDeptCode());
			zdwxphxdj.setSzzname(dept.getDeptName());
			zdwxphxdj.setQyid(company.getId());
			zdwxphxdj.setQymc(company.getCompanyname());
			zdwxphxdj.setDeptId(this.getLoginUserDepartmentId());
			zdwxphxdj.setDelFlag(0);
			zdwxphxdj.setCreateUserID(this.getLoginUserId());
			zdwxphxdj.setCreateTime(new Date());
			zdwxphxdj.setQylx(company.getQylx());
			zdwxphxdj.setHyfl(company.getHyfl());
			zdwxphxdj.setQygm(company.getQygm());
			zdwxphxdj.setQyzclx(company.getQyzclx());
			zdwxphxdj.setTbdwdz(company.getDwdz2());
			zdwxphxdj.setYzbm(company.getYzbm());
			zdwxphxdj.setIfwhpqylx(company.getIfwhpqylx());
			zdwxphxdj.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zdwxphxdj.setIfzywhqylx(company.getIfzywhqylx());
			zdwxphxdj.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			zdwxphxdj.setSzc(company.getSzc());
			zdwxphxdj.setSzcname(company.getSzcname());
			zdwxphxdj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxphxdj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxphxdjService.save(zdwxphxdj);
		}else{
			zdwxphxdj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxphxdj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxphxdjService.update(zdwxphxdj);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zdwxphxdjService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
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

	public Zdwxphxdj getZdwxphxdj(){
		return this.zdwxphxdj;
	}

	public void setZdwxphxdj(Zdwxphxdj zdwxphxdj){
		this.zdwxphxdj = zdwxphxdj;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryDjrqStart(){
		return this.queryDjrqStart;
	}

	public void setQueryDjrqStart(Date queryDjrqStart){
		this.queryDjrqStart = queryDjrqStart;
	}

	public Date getQueryDjrqEnd(){
		return this.queryDjrqEnd;
	}

	public void setQueryDjrqEnd(Date queryDjrqEnd){
		this.queryDjrqEnd = queryDjrqEnd;
	}

	public List<PhotoPic> getList() {
		return list;
	}

	public void setList(List<PhotoPic> list) {
		this.list = list;
	}

}
