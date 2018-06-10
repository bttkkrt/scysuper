package com.jshx.whpglb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.whpglb.dao.WhpglbDao;
import com.jshx.whpglb.entity.Whpglb;
import com.jshx.whpglb.service.WhpglbService;

@Service("whpglbService")
public class WhpglbServiceImpl extends BaseServiceImpl implements WhpglbService
{
	/**
	 * Dao类
	 */
	@Resource
	private WhpglbDao whpglbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return whpglbDao.findByPage(page, paraMap);
	}

	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<Whpglb> findWhpglb(Map<String, Object> paraMap)
	{
		return whpglbDao.findWhpglb(paraMap);
	}
	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpglb getById(String id)
	{
		return whpglbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Whpglb whpglb)
	{
		whpglbDao.save(whpglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Whpglb whpglb)
	{
		whpglbDao.update(whpglb);
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
		List objects=whpglbDao.findWhpglb(paraMap);
		
		whpglbDao.removeAll(objects);
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
				    whpglbDao.deleteWithFlag(id);
			}
		}
	}
}
