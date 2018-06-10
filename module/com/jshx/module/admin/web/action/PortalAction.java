package com.jshx.module.admin.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Constants;
import com.jshx.core.utils.Struts2Util;
import com.jshx.module.admin.entity.PasonnelPortal;
import com.jshx.module.admin.entity.Portal;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.PortalService;
import com.jshx.module.admin.service.UserRoleService;

public class PortalAction extends BaseAction {
	private static final String RELOAD = "reload";
	private static final String LEFT_PORTAL = "left_portal";
	
	private static final String RIGHT_PORTAL = "right_portal";

	private static final long serialVersionUID = 1L;
	
	@Autowired() 
	@Qualifier("portalService")
	private PortalService portalService;
	
	@Autowired() 
	@Qualifier("userRoleService")
	private UserRoleService userRoleService;
	
	private List<Portal> portalList;
	
	private List<Portal> leftPortalList;
	
	private List<Portal> rightPortalList;
	
	private String ids;
	
	private String[] roleIds;
	
	private Portal portal;
	
	private PasonnelPortal pasonnelPortal;
	
	private Pagination pagination;
	
	private List<UserRole> roleList;
		
	public String listPortal(){
		return SUCCESS;
	}
	
	public void listPortalAjax(){
		pagination = new Pagination(super.getRequest());
		pagination = portalService.findPortalByPage(pagination);
		
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(
				",\n");
		data.append("  \"rows\":\n");

		final String colNames = new String(
				"id|title|url|isPublic|rightList|");
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (colNames.indexOf(name + "|") != -1)
					return false;
				else
					return true;
			}
		});
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject(),
				config);
		data.append(json.toString());
		data.append("  \n").append("}");

		try {
			this.getResponse().getWriter().println(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String editPortal(){
		roleList = userRoleService.getAll();
		if(portal!=null)
			portal = portalService.getPortalById(portal.getId());
		return SUCCESS;
	}
	
	/*
	 * 保存门户布局
	 */
	public String savePortal(){
		portalService.savePortal(portal, roleIds);
		return SUCCESS;
	}

	public String getPortalSettingList(){
		User user = (User)Struts2Util.getSession().getAttribute(Constants.CURR_USER);
		portalList = portalService.findPortalByUser(user.getId());
	    pasonnelPortal = portalService.findMyPortal(user.getId());
		if(pasonnelPortal!=null){
			leftPortalList = new ArrayList<Portal>();
			rightPortalList = new ArrayList<Portal>();
			String left = pasonnelPortal.getLeft();
			String right = pasonnelPortal.getRight();
			if(left!=null && !left.trim().equals("")){
				String[] ids = left.split(",");
				for(String id:ids){
					if(id!=null && !id.trim().equals("")){
						Portal p = portalService.getPortalById(id);
						leftPortalList.add(p);
					}
				}
			}
			if(right!=null && !right.trim().equals("")){
				String[] ids = right.split(",");
				for(String id:ids){
					if(id!=null && !id.trim().equals("")){
						Portal p = portalService.getPortalById(id);
						rightPortalList.add(p);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public String savePersonnelPortal()
	{	
		portalService.savePasonnelPortal(pasonnelPortal);
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String getMyPortal(){
		
		leftPortalList = (List<Portal>)Struts2Util.getSession().getAttribute(LEFT_PORTAL);
		rightPortalList = (List<Portal>)Struts2Util.getSession().getAttribute(RIGHT_PORTAL);
		
		if(leftPortalList==null && rightPortalList==null){
			leftPortalList = new ArrayList<Portal>();
			rightPortalList = new ArrayList<Portal>();
			
			User user = (User)Struts2Util.getSession().getAttribute(Constants.CURR_USER);
			pasonnelPortal = portalService.findMyPortal(user.getId());
			
			if(pasonnelPortal!=null)
			{
				String left = pasonnelPortal.getLeft();
				String right = pasonnelPortal.getRight();
				
				if(left!=null && !left.trim().equals("")){
					String[] ids = left.split(",");
					for(String id:ids){
						if(id!=null && !id.trim().equals("")){
							Portal p = portalService.getPortalById(id);
							leftPortalList.add(p);
						}
					}
				}
				if(right!=null && !right.trim().equals("")){
					String[] ids = right.split(",");
					for(String id:ids){
						if(id!=null && !id.trim().equals("")){
							Portal p = portalService.getPortalById(id);
							rightPortalList.add(p);
						}
					}
				}
				if((right==null || right.trim().equals("")) &&(left==null || left.trim().equals(""))){
					portalList = portalService.findPortalByUser(user.getId());
					int i = 0;
					for(Portal p : portalList){
						if(i%2==0)
							leftPortalList.add(p);
						else
							rightPortalList.add(p);
						i++;
					}
				}
			}
			else{
				portalList = portalService.findPortalByUser(user.getId());
				int i = 0;
				for(Portal p : portalList){
					if(i%2==0)
						leftPortalList.add(p);
					else
						rightPortalList.add(p);
					i++;
				}
			}
			
		}

		return SUCCESS;
	}
	
	public String delPortal() throws Exception{
		try {
			portalService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}

	public List<Portal> getPortalList() {
		return portalList;
	}

	public void setPortalList(List<Portal> portalList) {
		this.portalList = portalList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Portal getPortal() {
		return portal;
	}

	public void setPortal(Portal portal) {
		this.portal = portal;
	}

	public List<Portal> getLeftPortalList() {
		return leftPortalList;
	}

	public void setLeftPortalList(List<Portal> leftPortalList) {
		this.leftPortalList = leftPortalList;
	}

	public List<Portal> getRightPortalList() {
		return rightPortalList;
	}

	public void setRightPortalList(List<Portal> rightPortalList) {
		this.rightPortalList = rightPortalList;
	}
	/**
	 * @return the pasonnelPortal
	 */
	public PasonnelPortal getPasonnelPortal() {
		return pasonnelPortal;
	}
	/**
	 * @param pasonnelPortal the pasonnelPortal to set
	 */
	public void setPasonnelPortal(PasonnelPortal pasonnelPortal) {
		this.pasonnelPortal = pasonnelPortal;
	}
	/**
	 * @return the roleIds
	 */
	public String[] getRoleIds() {
		return roleIds;
	}
	/**
	 * @param roleIds the roleIds to set
	 */
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	/**
	 * @return the pagination
	 */
	public Pagination getPagination() {
		return pagination;
	}

	/**
	 * @param pagination the pagination to set
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	/**
	 * @return the roleList
	 */
	public List<UserRole> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}

}
