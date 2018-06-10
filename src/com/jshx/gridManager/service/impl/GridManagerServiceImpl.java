package com.jshx.gridManager.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.company.dao.CompanyDao;
import com.jshx.company.entity.CompanyBackUp;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gridManager.dao.GridManagerDao;
import com.jshx.gridManager.entity.BaseBean;
import com.jshx.gridManager.entity.CompanyMapBean;
import com.jshx.gridManager.entity.GridManager;
import com.jshx.gridManager.entity.GridmanagerBean;
import com.jshx.gridManager.service.GridManagerService;
import com.jshx.module.admin.dao.UserDAO;
import com.jshx.module.admin.entity.User;

@Service("gridManagerService")
public class GridManagerServiceImpl extends BaseServiceImpl implements GridManagerService
{
	/**
	 * Dao类
	 */
	@Resource
	private GridManagerDao gridManagerDao;
	 @Resource
	 private UserDAO userDAO;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return gridManagerDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public GridManager getById(String id)
	{
		return gridManagerDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(GridManager gridManager)
	{
		gridManagerDao.save(gridManager);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(GridManager gridManager)
	{
		gridManagerDao.update(gridManager);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=gridManagerDao.findGridManager(paraMap);
		
		gridManagerDao.removeAll(objects);
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
				    gridManagerDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<BaseBean> queryUsersByCode(Map map) {
		return gridManagerDao.queryUsersByCode(map);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void bindUser(String ids, String id,String userId,String flag) {
		   String upName = "";
		   User u = userDAO.findUserById(id);
		   if(u!=null){
			   upName = u.getDisplayName();
		   }
		  String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				for(String mid : idArray)
				{
					GridManager gm = null;
				    if(mid!=null && !mid.trim().equals("")){
				    	  	gm = new GridManager();
					    	gm.setDownUserid(mid);
					    	gm.setUpUserid(id);
					    	gm.setUpUsername(upName);
					    	gm.setCreateUserID(userId);
					    	gm.setGridType(flag);
					    	gridManagerDao.save(gm);
				    }
				}
			}
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public void jcbindUser(String ids, String id,String userId,String flag) {
		  String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				Map map = new HashMap();
				for(String mid : idArray)
				{
				    if(mid!=null && !mid.trim().equals("")){
				    	map.put("upId", id);
				    	map.put("downId",mid);
				    	map.put("gridType", flag);
				    	gridManagerDao.deleteBindUser(map);
				    }
				}
			}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public void bindCompanyUser(String ids, String userId, String loginUserId) {
		   String upName = "";
		   User u = userDAO.findUserById(userId);
		   if(u!=null){
			   upName = u.getDisplayName();
		   }
		  String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				GridManager gm = null;
				for(String mid : idArray)
				{
				    if(mid!=null && !mid.trim().equals("")){
				    	gm = new GridManager();
				    	gm.setDownUserid(mid);
				    	gm.setUpUserid(userId);
				    	gm.setUpUsername(upName);
				    	gm.setCreateUserID(loginUserId);
				    	gm.setGridType("5");
				    	gridManagerDao.save(gm);
				    }
				}
			}
	}

	
	@SuppressWarnings("unchecked")
	@Transactional
	public void jcbindCompanyUser(String ids, String userId, String loginUserId) {
		  String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				Map map = new HashMap();
				for(String mid : idArray)
				{
				    if(mid!=null && !mid.trim().equals("")){
				    	map.put("upId", userId);
				    	map.put("downId",mid);
				    	map.put("gridType", "5");
				    	gridManagerDao.deleteBindUser(map);
				    }
				}
			}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void bindNoCompanyUser(String ids, String userId, String loginUserId) {
		   String upName = "";
		   User u = userDAO.findUserById(userId);
		   if(u!=null){
			   upName = u.getDisplayName();
		   }
		  String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				GridManager gm = null;
				for(String mid : idArray)
				{
				    if(mid!=null && !mid.trim().equals("")){
				    	gm = new GridManager();
				    	gm.setDownUserid(mid);
				    	gm.setUpUserid(userId);
				    	gm.setUpUsername(upName);
				    	gm.setCreateUserID(loginUserId);
				    	gm.setGridType("6");
				    	gridManagerDao.save(gm);
				    }
				}
			}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void jcbindNoCompanyUser(String ids, String userId, String loginUserId) {
		  String[] idArray = ids.split("\\|");
			if(null != idArray)
			{
				Map map = new HashMap();
				for(String mid : idArray)
				{
				    if(mid!=null && !mid.trim().equals("")){
				    	map.put("upId", userId);
				    	map.put("downId",mid);
				    	map.put("gridType", "6");
				    	gridManagerDao.deleteBindUser(map);
				    }
				}
			}
	}

	@Override
	public List<CompanyMapBean> getCompamyMapDotsByMap(Map map) {
		return gridManagerDao.getCompamyMapDotsByMap(map);
	}

	@Override
	public List<CompanyMapBean> getNoCompamyMapDotsByMap(Map map) {
		return gridManagerDao.getNoCompamyMapDotsByMap(map);
	}

	@Override
	public List<GridmanagerBean> getCaqzzrListByMap(Map map) {
		// TODO Auto-generated method stub
		return gridManagerDao.getCaqzzrListByMap(map);
	}

	@Override
	public List<BaseBean> getDeptListByMap(Map map) {
		// TODO Auto-generated method stub
		return gridManagerDao.getDeptListByMap(map);
	}

	@Override
	public List<BaseBean> getZaqzzrListByMap(Map map) {
		// TODO Auto-generated method stub
		return gridManagerDao.getZaqzzrListByMap(map);
	}

	@Override
	public List<BaseBean> getZzrListByMap(Map map) {
		// TODO Auto-generated method stub
		return gridManagerDao.getZzrListByMap(map);
	}

	@Override
	public Pagination findWzCompanyByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return gridManagerDao.findWzCompanyByPage(page, paraMap);
	}

	@Override
	public Pagination findYzCompanyByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return gridManagerDao.findYzCompanyByPage(page, paraMap);
	}

	@Override
	public List<BaseBean> getZddzListByMap(Map map) {
		// TODO Auto-generated method stub
		return gridManagerDao.getZddzListByMap(map);
	}

	@Override
	public List<BaseBean> getFgldListByMap(Map map) {
		// TODO Auto-generated method stub
		return gridManagerDao.getFgldListByMap(map);
	}
}
