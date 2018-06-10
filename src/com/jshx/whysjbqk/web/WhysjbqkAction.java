/**
 * Class Name: WhysjbqkAction
 * Class Description：危害因素基本情况管理
 */
package com.jshx.whysjbqk.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.Department;
import com.jshx.module.admin.service.DeptService;
import com.jshx.whysjbqk.entity.Whysjbqk;
import com.jshx.whysjbqk.entity.Whysjbqkglb;
import com.jshx.whysjbqk.service.WhysjbqkService;

public class WhysjbqkAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Whysjbqk whysjbqk = new Whysjbqk();
	
	private List<Whysjbqkglb> qts = new ArrayList<Whysjbqkglb>();

	/**
	 * 业务类
	 */
	@Autowired
	private WhysjbqkService whysjbqkService;
	
	@Autowired
	private CompanyService companyService;
	@Autowired
	private DeptService deptService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag ="add";
	/**
	 * type
	 */
	private String type;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	/**
	 * 初始化列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String init()
	{
		try {
			String deptCode = this.getLoginUserDepartment().getDeptCode();
			if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
			{//企业
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				String id = whysjbqkService.getWhysjbqkIdByQyid(company.getId());//根据企业id获取危害因素id
				whysjbqk =  whysjbqkService.getById(id);
				if(whysjbqk!=null){
					flag = "update";
					Map map = new HashMap();
					map.put("whysjbqkid", whysjbqk.getId());
					qts = whysjbqkService.getWhysjbqkglbList(map);//获取其它因素列表
				}
				type = "edit";
			}
			else if(this.getLoginUserDepartment().getDeptName().contains(SysPropertiesUtil.getProperty("townstr")))
			{//中队
				type = "success";
			}
			else
			{
				type = "success";
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != whysjbqk){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != whysjbqk.getQymc()) && (0 < whysjbqk.getQymc().trim().length())){
				paraMap.put("qymc", "%" + whysjbqk.getQymc().trim() + "%");
			}
			if ((null != whysjbqk.getSzzid()) && (0 < whysjbqk.getSzzid().trim().length())){
				paraMap.put("szzid",  whysjbqk.getSzzid().trim() );
			}
			if ((null != whysjbqk.getSzc() )&& (0 < whysjbqk.getSzc().trim().length())){
				paraMap.put("szc",whysjbqk.getSzc().trim());
			}
		}
		
		pagination = whysjbqkService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看详细信息
	 */
	@SuppressWarnings("unchecked")
	public String view() throws Exception{//7C 4A 8D 09 CA 37 62 AF 61 E5 95 20 94 3D C2
		if((null != whysjbqk)&&(null != whysjbqk.getId())){
			whysjbqk = whysjbqkService.getById(whysjbqk.getId());
			Map map = new HashMap();
			map.put("whysjbqkid", whysjbqk.getId());
			qts = whysjbqkService.getWhysjbqkglbList(map);
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
		try {
			if ("add".equalsIgnoreCase(this.flag)){
				whysjbqk.setDeptId(this.getLoginUserDepartmentId());
				whysjbqk.setDelFlag(0);
				whysjbqk.setWhysmc(whysjbqk.getWhysmc());
				
				CompanyBackUp company = companyService.getCompanyByLoginId(this.getLoginUser().getLoginId());
				Department dept = deptService.findDeptByDeptCode(company.getDwdz1());
				whysjbqk.setSzzid(dept.getDeptCode());
				whysjbqk.setSzzname(dept.getDeptName());
				whysjbqk.setQyid(company.getId());
				whysjbqk.setQymc(company.getCompanyname());
				whysjbqk.setCreateUserID(this.getLoginUserId());
				whysjbqk.setCreateTime(new Date());
				whysjbqk.setQylx(company.getQylx());
				whysjbqk.setHyfl(company.getHyfl());
				whysjbqk.setQygm(company.getQygm());
				whysjbqk.setQyzclx(company.getQyzclx());
				whysjbqk.setSzc(company.getSzc());
				whysjbqk.setSzcname(company.getSzcname());
				whysjbqkService.save(whysjbqk);
				String tids = whysjbqk.getWhysid();
				String tnames = whysjbqk.getWhysmc();
				String qtnames = whysjbqk.getQtnames();//其它因素
				String qtids = whysjbqk.getQtids();//其它种类
				if(qtnames != null && !"".equals(qtnames))
				{
					String[] names = qtnames.split(",");
					String[] ids = qtids.split(",");
					for(int i=0;i<names.length;i++){
						Whysjbqkglb glb = new Whysjbqkglb();
						glb.setFl(ids[i].trim());
						glb.setQtwhys(names[i].trim());
						glb.setWhysjbqkid(whysjbqk.getId());   
						glb.setCode("QT"+i);
						whysjbqkService.saveGlb(glb);
						tids = tids+glb.getCode()+",";
						tnames = tnames +glb.getQtwhys()+",";
					}
				}
				whysjbqk.setWhysid(tids);
				whysjbqk.setWhysmc(tnames);
				String[] ss = tids.split(",");
				whysjbqk.setWhyssl(ss.length+"");
				whysjbqkService.update(whysjbqk);
			}else{
				whysjbqkService.delWhysjbqkglb(whysjbqk.getId());
				String tids = whysjbqk.getWhysid();
				String tnames = whysjbqk.getWhysmc();
				String qtnames = whysjbqk.getQtnames();//其它因素
				String qtids = whysjbqk.getQtids();//其它种类
				if(qtnames != null && !"".equals(qtnames))
				{
					String[] names = qtnames.split(",");
					String[] ids = qtids.split(",");
					for(int i=0;i<names.length;i++){
						Whysjbqkglb glb = new Whysjbqkglb();
						glb.setFl(ids[i].trim());
						glb.setQtwhys(names[i].trim());
						glb.setWhysjbqkid(whysjbqk.getId());   
						glb.setCode("QT"+i);
						whysjbqkService.saveGlb(glb);
						tids = tids+glb.getCode()+",";
						tnames = tnames +glb.getQtwhys()+",";
					}
				}
				String[] ss = tids.split(",");
				whysjbqk.setWhyssl(ss.length+"");
				whysjbqk.setWhysid(tids);
				whysjbqk.setWhysmc(tnames);
				whysjbqkService.update(whysjbqk);
			}
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			whysjbqkService.deleteWithFlag(ids);
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

	public Whysjbqk getWhysjbqk(){
		return this.whysjbqk;
	}

	public void setWhysjbqk(Whysjbqk whysjbqk){
		this.whysjbqk = whysjbqk;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<Whysjbqkglb> getQts() {
		return qts;
	}

	public void setQts(List<Whysjbqkglb> qts) {
		this.qts = qts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
       
    
}
