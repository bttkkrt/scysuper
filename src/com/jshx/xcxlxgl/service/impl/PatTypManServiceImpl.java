package com.jshx.xcxlxgl.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xcxlxgl.dao.PatTypManDao;
import com.jshx.xcxlxgl.entity.PatTypMan;
import com.jshx.xcxlxgl.service.PatTypManService;

@Service("patTypManService")
public class PatTypManServiceImpl extends BaseServiceImpl implements PatTypManService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("patTypManDao")
	private PatTypManDao patTypManDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return patTypManDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PatTypMan getById(String id)
	{
		return patTypManDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(PatTypMan patTypMan)
	{
		patTypManDao.save(patTypMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(PatTypMan patTypMan)
	{
		patTypManDao.update(patTypMan);
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
		List objects=patTypManDao.findPatTypMan(paraMap);
		
		patTypManDao.removeAll(objects);
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
				    patTypManDao.deleteWithFlag(id);
			}
		}
	}

	public List<String> getPatTypeIds(Map map) {
		return patTypManDao.getPatTypeIds(map);
	}
}
