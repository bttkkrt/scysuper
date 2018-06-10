/**
 * Class Name: ZdwxybadjAction
 * Class Description：危险化学品重大危险源备案登记
 */
package com.jshx.zdwxybadj.web;

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
import com.jshx.zdwxybadj.entity.Zdwxybadj;
import com.jshx.zdwxybadj.service.ZdwxybadjService;

public class ZdwxybadjAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zdwxybadj zdwxybadj = new Zdwxybadj();

	/**
	 * 业务类
	 */
	@Autowired
	private ZdwxybadjService zdwxybadjService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
	 @Autowired
		private SzwxPhotoService szwxPhotoService;
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
		 * @author gq
		 * @date 8yue 20
		 * @function 存放附件列表
		 */
		private List<PhotoPic> list = new ArrayList<PhotoPic>();
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	private Date queryYxqStart;

	private Date queryYxqEnd;

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
		    
		if(null != zdwxybadj){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zdwxybadj.getBabh()) && (0 < zdwxybadj.getBabh().trim().length())){
				paraMap.put("babh", "%" + zdwxybadj.getBabh().trim() + "%");
			}

			if (null != queryYxqStart){
				paraMap.put("startYxq", queryYxqStart);
			}

			if (null != queryYxqEnd){
				paraMap.put("endYxq", queryYxqEnd);
			}
			if ((null != zdwxybadj.getQymc()) && (0 < zdwxybadj.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zdwxybadj.getQymc().trim() + "%");
			}

			if ((null != zdwxybadj.getZdwxymc()) && (0 < zdwxybadj.getZdwxymc().trim().length())){
				paraMap.put("zdwxymc", "%" + zdwxybadj.getZdwxymc().trim() + "%");
			}

			if ((null != zdwxybadj.getTbrxm()) && (0 < zdwxybadj.getTbrxm().trim().length())){
				paraMap.put("tbrxm", "%" + zdwxybadj.getTbrxm().trim() + "%");
			}
			if ((null != zdwxybadj.getSzzid()) && (0 < zdwxybadj.getSzzid().trim().length())){
				paraMap.put("szzid",  zdwxybadj.getSzzid().trim() );
			}
			if ((null != zdwxybadj.getSzc() )&& (0 < zdwxybadj.getSzc().trim().length())){
				paraMap.put("szc",zdwxybadj.getSzc().trim());
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
		
		pagination = zdwxybadjService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zdwxybadj)&&(null != zdwxybadj.getId()))
			zdwxybadj = zdwxybadjService.getById(zdwxybadj.getId());
		if(zdwxybadj.getLinkid()==null)
		{
			zdwxybadj.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",zdwxybadj.getLinkid());
		map.put("picType","zdwxybadjb");
	    list = szwxPhotoService.findPicPath(map);//获取图片或附件列表
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		zdwxybadj.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		zdwxybadj.setZdwxymc(sdf.format(new Date()));
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zdwxybadj.setSzzid(dept.getDeptCode());
			zdwxybadj.setSzzname(dept.getDeptName());
			zdwxybadj.setQyid(company.getId());
			zdwxybadj.setQymc(company.getCompanyname());
			zdwxybadj.setDeptId(this.getLoginUserDepartmentId());
			zdwxybadj.setDelFlag(0);
			zdwxybadj.setCreateUserID(this.getLoginUserId());
			zdwxybadj.setCreateTime(new Date());
			zdwxybadj.setQylx(company.getQylx());
			zdwxybadj.setHyfl(company.getHyfl());
			zdwxybadj.setQygm(company.getQygm());
			zdwxybadj.setQyzclx(company.getQyzclx());
			zdwxybadj.setTbdwdz(company.getDwdz2());
			zdwxybadj.setYzbm(company.getYzbm());
			zdwxybadj.setIfwhpqylx(company.getIfwhpqylx());
			zdwxybadj.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zdwxybadj.setIfzywhqylx(company.getIfzywhqylx());
			zdwxybadj.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			zdwxybadj.setSzc(company.getSzc());
			zdwxybadj.setSzcname(company.getSzcname());
			zdwxybadj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxybadj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxybadjService.save(zdwxybadj);
		}else{
			zdwxybadj.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxybadj.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxybadjService.update(zdwxybadj);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zdwxybadjService.deleteWithFlag(ids);
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

	public Zdwxybadj getZdwxybadj(){
		return this.zdwxybadj;
	}

	public void setZdwxybadj(Zdwxybadj zdwxybadj){
		this.zdwxybadj = zdwxybadj;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
	public Date getQueryYxqStart(){
		return this.queryYxqStart;
	}

	public void setQueryYxqStart(Date queryYxqStart){
		this.queryYxqStart = queryYxqStart;
	}

	public Date getQueryYxqEnd(){
		return this.queryYxqEnd;
	}

	public void setQueryYxqEnd(Date queryYxqEnd){
		this.queryYxqEnd = queryYxqEnd;
	}
	public List<PhotoPic> getList() {
		return list;
	}
	public void setList(List<PhotoPic> list) {
		this.list = list;
	}

}
