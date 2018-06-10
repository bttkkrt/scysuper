package com.jshx.zjjtzsbs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zjjtzsbs.dao.ZjjtzsbsDao;
import com.jshx.zjjtzsbs.entity.Zjjtzsbs;
import com.jshx.zjjtzsbs.service.ZjjtzsbsService;

@Service("zjjtzsbsService")
public class ZjjtzsbsServiceImpl extends BaseServiceImpl implements ZjjtzsbsService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZjjtzsbsDao zjjtzsbsDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zjjtzsbsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zjjtzsbs getById(String id)
	{
		return zjjtzsbsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zjjtzsbs zjjtzsbs)
	{
		zjjtzsbsDao.save(zjjtzsbs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zjjtzsbs zjjtzsbs)
	{
		zjjtzsbsDao.update(zjjtzsbs);
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
		List objects=zjjtzsbsDao.findZjjtzsbs(paraMap);
		
		zjjtzsbsDao.removeAll(objects);
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
				    zjjtzsbsDao.deleteWithFlag(id);
			}
		}
	}
	
	@Transactional
	public void deleteAll() {
		// TODO Auto-generated method stub
		zjjtzsbsDao.deleteAll();
	}
}
