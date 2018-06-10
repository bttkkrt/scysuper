/**
 * Class Name: ZdwxybasqAction
 * Class Description：危险化学品重大危险源备案申请
 */
package com.jshx.zdwxybasq.web;

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
import com.jshx.zdwxybasq.entity.Zdwxybasq;
import com.jshx.zdwxybasq.service.ZdwxybasqService;

public class ZdwxybasqAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Zdwxybasq zdwxybasq = new Zdwxybasq();

	/**
	 * 业务类
	 */
	@Autowired
	private ZdwxybasqService zdwxybasqService;
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
	 @Autowired
		private SzwxPhotoService szwxPhotoService;
	 /**
		 * @author gq
		 * @date 8yue 20
		 * @function 存放附件列表
		 */
		private List<PhotoPic> list = new ArrayList<PhotoPic>();
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
		    
		if(null != zdwxybasq){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != zdwxybasq.getQymc()) && (0 < zdwxybasq.getQymc().trim().length())){
				paraMap.put("qymc", "%" + zdwxybasq.getQymc().trim() + "%");
			}

			if ((null != zdwxybasq.getZdwxymc()) && (0 < zdwxybasq.getZdwxymc().trim().length())){
				paraMap.put("zdwxymc", "%" + zdwxybasq.getZdwxymc().trim() + "%");
			}

			if ((null != zdwxybasq.getTbrxm()) && (0 < zdwxybasq.getTbrxm().trim().length())){
				paraMap.put("tbrxm", "%" + zdwxybasq.getTbrxm().trim() + "%");
			}
			if ((null != zdwxybasq.getSzzid()) && (0 < zdwxybasq.getSzzid().trim().length())){
				paraMap.put("szzid",  zdwxybasq.getSzzid().trim() );
			}
			if ((null != zdwxybasq.getSzc() )&& (0 < zdwxybasq.getSzc().trim().length())){
				paraMap.put("szc",zdwxybasq.getSzc().trim());
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

		pagination = zdwxybasqService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != zdwxybasq)&&(null != zdwxybasq.getId()))
			zdwxybasq = zdwxybasqService.getById(zdwxybasq.getId());
		if(zdwxybasq.getLinkid()==null)
		{
			zdwxybasq.setLinkid(UUID.randomUUID().toString());
		}
		Map map = new HashMap();
		map.put("taskProId",zdwxybasq.getLinkid());
		map.put("picType","zdwxybasqb");
	    list = szwxPhotoService.findPicPath(map);//获取图片或附件列表
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		zdwxybasq.setQymc(company.getCompanyname());
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		zdwxybasq.setZdwxyjb(sdf.format(new Date()));
		CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
		if ("add".equalsIgnoreCase(this.flag)){
			Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
			zdwxybasq.setSzzid(dept.getDeptCode());
			zdwxybasq.setSzzname(dept.getDeptName());
			zdwxybasq.setQyid(company.getId());
			zdwxybasq.setQymc(company.getCompanyname());
			zdwxybasq.setDeptId(this.getLoginUserDepartmentId());
			zdwxybasq.setDelFlag(0);
			zdwxybasq.setCreateUserID(this.getLoginUserId());
			zdwxybasq.setCreateTime(new Date());
			zdwxybasq.setQylx(company.getQylx());
			zdwxybasq.setHyfl(company.getHyfl());
			zdwxybasq.setQygm(company.getQygm());
			zdwxybasq.setQyzclx(company.getQyzclx());
			zdwxybasq.setTbdwdz(company.getDwdz2());
			zdwxybasq.setYzbm(company.getYzbm());
			zdwxybasq.setIfwhpqylx(company.getIfwhpqylx());
			zdwxybasq.setIfyhbzjyqy(company.getIfyhbzjyqy());
			zdwxybasq.setIfzywhqylx(company.getIfzywhqylx());
			zdwxybasq.setIffmksjyqy(company.getIffmksjyqy());//heyc 20141210 设置非煤矿山标识
			zdwxybasq.setSzc(company.getSzc());
			zdwxybasq.setSzcname(company.getSzcname());
			zdwxybasq.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxybasq.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxybasqService.save(zdwxybasq);
		}else{
			zdwxybasq.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			zdwxybasq.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			zdwxybasqService.update(zdwxybasq);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			zdwxybasqService.deleteWithFlag(ids);
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

	public Zdwxybasq getZdwxybasq(){
		return this.zdwxybasq;
	}

	public void setZdwxybasq(Zdwxybasq zdwxybasq){
		this.zdwxybasq = zdwxybasq;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<PhotoPic> getList() {
		return list;
	}

	public void setList(List<PhotoPic> list) {
		this.list = list;
	}
       
    
}
