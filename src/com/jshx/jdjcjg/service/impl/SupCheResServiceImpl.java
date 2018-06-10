package com.jshx.jdjcjg.service.impl;

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
import com.jshx.jdjcjg.dao.SupCheResDao;
import com.jshx.jdjcjg.entity.SupCheRes;
import com.jshx.jdjcjg.service.SupCheResService;

@Service("supCheResService")
public class SupCheResServiceImpl extends BaseServiceImpl implements SupCheResService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("supCheResDao")
	private SupCheResDao supCheResDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return supCheResDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupCheRes getById(String id)
	{
		return supCheResDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SupCheRes supCheRes)
	{
		supCheResDao.save(supCheRes);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SupCheRes supCheRes)
	{
		supCheResDao.update(supCheRes);
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
		List objects=supCheResDao.findSupCheRes(paraMap);
		
		supCheResDao.removeAll(objects);
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
				    supCheResDao.deleteWithFlag(id);
			}
		}
	}
}
