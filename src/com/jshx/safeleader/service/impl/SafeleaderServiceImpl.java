package com.jshx.safeleader.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.safeleader.dao.SafeleaderDao;
import com.jshx.safeleader.entity.Safeleader;
import com.jshx.safeleader.service.SafeleaderService;

@Service("safeleaderService")
public class SafeleaderServiceImpl extends BaseServiceImpl implements SafeleaderService
{
	/**
	 * Dao类
	 */
	@Resource
	private SafeleaderDao safeleaderDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return safeleaderDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Safeleader getById(String id)
	{
		return safeleaderDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Safeleader safeleader)
	{
		safeleaderDao.save(safeleader);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Safeleader safeleader)
	{
		safeleaderDao.update(safeleader);
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
		List objects=safeleaderDao.findSafeleader(paraMap);
		
		safeleaderDao.removeAll(objects);
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
				    safeleaderDao.deleteWithFlag(id);
			}
		}
	}
}
