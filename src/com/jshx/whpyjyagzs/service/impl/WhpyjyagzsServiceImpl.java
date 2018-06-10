package com.jshx.whpyjyagzs.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpyjyagzs.dao.WhpyjyagzsDao;
import com.jshx.whpyjyagzs.entity.Whpyjyagzs;
import com.jshx.whpyjyagzs.service.WhpyjyagzsService;

@Service("whpyjyagzsService")
public class WhpyjyagzsServiceImpl extends BaseServiceImpl implements WhpyjyagzsService
{
	/**
	 * Dao类
	 */
	@Resource
	private WhpyjyagzsDao whpyjyagzsDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return whpyjyagzsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpyjyagzs getById(String id)
	{
		return whpyjyagzsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Whpyjyagzs whpyjyagzs)
	{
		whpyjyagzsDao.save(whpyjyagzs);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Whpyjyagzs whpyjyagzs)
	{
		whpyjyagzsDao.update(whpyjyagzs);
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
		List objects=whpyjyagzsDao.findWhpyjyagzs(paraMap);
		
		whpyjyagzsDao.removeAll(objects);
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
				    whpyjyagzsDao.deleteWithFlag(id);
			}
		}
	}
}
