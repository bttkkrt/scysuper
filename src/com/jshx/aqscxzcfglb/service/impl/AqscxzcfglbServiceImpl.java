package com.jshx.aqscxzcfglb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscxzcfglb.dao.AqscxzcfglbDao;
import com.jshx.aqscxzcfglb.entity.Aqscxzcfglb;
import com.jshx.aqscxzcfglb.service.AqscxzcfglbService;

@Service("aqscxzcfglbService")
public class AqscxzcfglbServiceImpl extends BaseServiceImpl implements AqscxzcfglbService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqscxzcfglbDao aqscxzcfglbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscxzcfglbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscxzcfglb getById(String id)
	{
		return aqscxzcfglbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqscxzcfglb aqscxzcfglb)
	{
		aqscxzcfglbDao.save(aqscxzcfglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqscxzcfglb aqscxzcfglb)
	{
		aqscxzcfglbDao.update(aqscxzcfglb);
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
		List objects=aqscxzcfglbDao.findAqscxzcfglb(paraMap);
		
		aqscxzcfglbDao.removeAll(objects);
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
				    aqscxzcfglbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Aqscxzcfglb> findAqscxzcfglb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return aqscxzcfglbDao.findAqscxzcfglb(paraMap);
	}

	@Override
	public Aqscxzcfglb getAqscxzcfglbByMap(Map map) {
		// TODO Auto-generated method stub
		return aqscxzcfglbDao.getAqscxzcfglbByMap(map);
	}
}
