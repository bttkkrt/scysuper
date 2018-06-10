/**
 * Class Name: DailyinspectionAction
 * Class Description：日常巡检
 */
package com.jshx.dailyInspection.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.common.util.Condition;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.dailyInspection.entity.Dailyinspection;
import com.jshx.dailyInspection.service.DailyinspectionService;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;

public class DailyinspectionAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Dailyinspection dailyinspection = new Dailyinspection();

	/**
	 * 业务类
	 */
	@Autowired
	private DailyinspectionService dailyinspectionService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;
    /** @author gq
	 * @date 8yue 20 
     *用于区分监管部门0和更高级别1
     */
    private String visable;
	/**
	 * 分页信息
	 */
	private Pagination pagination;
	 private CompanyBackUp company=null;
	 /**
		 * @author gq
		 * @date 8yue 20
		 * @function 存放附件列表
		 */
		private List<PhotoPic> list = new ArrayList<PhotoPic>();
		 @Autowired
			private SzwxPhotoService szwxPhotoService;
	 /**
		 * 企业业务类
		 */
		@Autowired
		private CompanyService companyService;
		/**
		 * @author gq
		 * @date 8yue 20
		 * @function 初始化安全生产页面
		 * @return 上传附件页面
		 */
		public String initlist() throws Exception{
		
			if(this.getLoginUser().getDept().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))//监管部门以下的角色
			{
			    this.visable="0";
			}
			else 
				 this.visable="1";
			return SUCCESS;
		}
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		
		if(null != dailyinspection){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != dailyinspection.getComname()) && (0 < dailyinspection.getComname().trim().length())){
				paraMap.put("comname", "%" + dailyinspection.getComname().trim() + "%");
			}

			if ((null != dailyinspection.getDeptId()) && (0 < dailyinspection.getDeptId().trim().length())){
				paraMap.put("deptCodes", dailyinspection.getDeptId().trim());
			}
			if ((null != dailyinspection.getSzc() )&& (0 < dailyinspection.getSzc().trim().length())){
				paraMap.put("szc",dailyinspection.getSzc().trim());
			}
		}
		//hanxc 20141223 修改查询条件 start
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		String deptRole = this.getLoginUser().getDeptRole();
		if(!SysPropertiesUtil.getProperty("adminDeptRole").equals(deptRole)){//企业人员或有审核任务部门人员登录，添加查询条件过滤
			paraMap = Condition.getContiton(paraMap, deptRole, deptCode, companyService,this.getLoginUser().getLoginId(), "");
		}
		//hanxc 20141223 修改查询条件 end
		pagination = dailyinspectionService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != dailyinspection)&&(null != dailyinspection.getId()))
			dailyinspection = dailyinspectionService.getById(dailyinspection.getId());
		Map map = new HashMap();
		map.put("taskProId",dailyinspection.getLinkid());
	    list = szwxPhotoService.findPicPath(map);//获取图片或附件列表
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
			dailyinspection.setDeptId(this.getLoginUserDepartmentId());
			dailyinspection.setDelFlag(0);
			dailyinspection.setCreateTime(new Date());
			dailyinspection.setCreateUserID(this.getLoginUserId());
			dailyinspection.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			//dailyinspection.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			//dailyinspectionService.save(dailyinspection);
		}else{
			//dailyinspection.setCompanyType(company.getCompanyType());//hanxc 20141223 企业所属监管类型
			//dailyinspection.setIfzsqy(company.getIfzsqy());//hanxc 20141223 是否直属企业
			dailyinspectionService.update(dailyinspection);
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			dailyinspectionService.deleteWithFlag(ids);
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

	public Dailyinspection getDailyinspection(){
		return this.dailyinspection;
	}

	public void setDailyinspection(Dailyinspection dailyinspection){
		this.dailyinspection = dailyinspection;
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
	public String getVisable() {
		return visable;
	}
	public void setVisable(String visable) {
		this.visable = visable;
	}
       
    
}
