package com.jshx.whpaqglzd.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpaqglzd.dao.WhpaqglzdDao;
import com.jshx.whpaqglzd.entity.Whpaqglzd;
import com.jshx.whpaqglzd.service.WhpaqglzdService;

@Service("whpaqglzdService")
public class WhpaqglzdServiceImpl extends BaseServiceImpl implements WhpaqglzdService
{
	/**
	 * Dao类
	 */
	@Resource
	private WhpaqglzdDao whpaqglzdDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return whpaqglzdDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpaqglzd getById(String id)
	{
		return whpaqglzdDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Whpaqglzd whpaqglzd)
	{
		whpaqglzdDao.save(whpaqglzd);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Whpaqglzd whpaqglzd)
	{
		whpaqglzdDao.update(whpaqglzd);
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
		List objects=whpaqglzdDao.findWhpaqglzd(paraMap);
		
		whpaqglzdDao.removeAll(objects);
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
				    whpaqglzdDao.deleteWithFlag(id);
			}
		}
	}
}
