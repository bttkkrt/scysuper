/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * Jan 25, 2011        john.zhang          create
 * ---------------------------------------------------------------
 */

package com.jshx.module.admin.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.PersonalPortalDao;
import com.jshx.module.admin.dao.PortalDao;
import com.jshx.module.admin.dao.PortalRightDao;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.dao.UserRoleDao;
import com.jshx.module.admin.entity.PasonnelPortal;
import com.jshx.module.admin.entity.Portal;
import com.jshx.module.admin.entity.PortalRight;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.entity.UserRole;
import com.jshx.module.admin.service.PortalService;

/**
 * @author john.zhang
 * @version 创建时间：Jan 25, 2011 2:51:41 PM 类说明
 */
@Service("portalService")
public class PortalServiceImpl implements PortalService {
	@Autowired() 
	@Qualifier("portalDao")
	private PortalDao portalDao;
	
	@Autowired() 
	@Qualifier("portalRightDao")
	private PortalRightDao portalRightDao;
	
	@Autowired() 
	@Qualifier("personalPortalDao")
	private PersonalPortalDao personalPortalDao;
	
	@Autowired() 
	@Qualifier("userDAOIpml")
	private UserDAO userDAO;
	
	@Autowired() 
	@Qualifier("userRoleDao")
	private UserRoleDao userRoleDao;

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#delPasonnelPortal(com.jshx.module.admin.entity.PasonnelPortal)
	 */

	@Transactional(propagation=Propagation.NESTED) 
	public void delPasonnelPortal(String id) {
		personalPortalDao.removeObjectById(PasonnelPortal.class, id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#deletePortal(java.lang.String)
	 */

	@Transactional(propagation=Propagation.NESTED) 
	public void deletePortal(String id) {
		portalDao.removeObjectById(Portal.class, id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#findMyPortal(java.lang.String)
	 */
	@SuppressWarnings("unchecked")

	public PasonnelPortal findMyPortal(String userCode) {
		List list = personalPortalDao.findMyPortal(userCode);
		if(list!=null && list.size()>0)
			return (PasonnelPortal)list.get(0);
		else
			return null;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#findPasonnelPortalById(java.lang.String)
	 */

	public PasonnelPortal findPasonnelPortalById(String id) {
		return (PasonnelPortal)personalPortalDao.getObjectById(PasonnelPortal.class, id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#findPortalByUser(java.lang.String)
	 */

	public List<Portal> findPortalByUser(String userId) {
		User user = userDAO.findUserById(userId);
		return portalDao.findPortalByUser(user);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#getAllPortals()
	 */

	public List<Portal> getAllPortals() {
		return portalDao.findAllPotals();
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#getPortalById(java.lang.String)
	 */

	public Portal getPortalById(String id) {
		return (Portal)portalDao.getObjectById(Portal.class, id);
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#savePasonnelPortal(com.jshx.module.admin.entity.PasonnelPortal)
	 */

	@Transactional(propagation=Propagation.NESTED) 
	public PasonnelPortal savePasonnelPortal(PasonnelPortal myPortal) {
		PasonnelPortal portal = this.findMyPortal(myPortal.getUserCode());
		if(portal==null)
			this.personalPortalDao.saveBaseModelObject(myPortal);
		else{
			portal.setLeft(myPortal.getLeft());
			portal.setRight(myPortal.getRight());
			personalPortalDao.updateBaseModelObject(portal);
			myPortal = portal;
		}
		return myPortal;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#savePortal(com.jshx.module.admin.entity.Portal, java.lang.String[])
	 */

	@Transactional(propagation=Propagation.NESTED) 
	public Portal savePortal(Portal portal, String[] roleIds) {
		portalDao.saveOrUpdateBaseModelObject(portal);
		portalRightDao.delRightByPortal(portal);
		List<PortalRight> rightList = new ArrayList<PortalRight>();
		if(roleIds!=null){
			for(String roleId:roleIds){
				PortalRight right = new PortalRight();
				UserRole role = userRoleDao.findUserRoleById(roleId);
				right.setPortal(portal);
				right.setRole(role);
				portalRightDao.saveBaseModelObject(right);
				rightList.add(right);
			}
		}
		portal.setRightList(rightList);
		return portal;
	}

	/* (non-Javadoc)
	 * @see com.jshx.module.admin.service.PortalService#findPortalByPage(com.jshx.core.base.vo.Pagination)
	 */

	public Pagination findPortalByPage(Pagination page) {
		Map<String,Object> paraMap = new HashMap<String, Object>();
		return portalDao.findPageByHqlId("findPortalByUser", paraMap, page);
	}
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
			    	portalDao.deleteWithFlag(id);
			}
		}
	}
	

}
