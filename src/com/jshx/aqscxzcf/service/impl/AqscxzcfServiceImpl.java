package com.jshx.aqscxzcf.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscxzcf.dao.AqscxzcfDao;
import com.jshx.aqscxzcf.entity.Aqscxzcf;
import com.jshx.aqscxzcf.service.AqscxzcfService;

@Service("aqscxzcfService")
public class AqscxzcfServiceImpl extends BaseServiceImpl implements AqscxzcfService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqscxzcfDao aqscxzcfDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscxzcfDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscxzcf getById(String id)
	{
		return aqscxzcfDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqscxzcf aqscxzcf)
	{
		aqscxzcfDao.save(aqscxzcf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqscxzcf aqscxzcf)
	{
		aqscxzcfDao.update(aqscxzcf);
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
		List objects=aqscxzcfDao.findAqscxzcf(paraMap);
		
		aqscxzcfDao.removeAll(objects);
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
				    aqscxzcfDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteAqscxzcfglbByMap(Map map) {
		// TODO Auto-generated method stub
		aqscxzcfDao.deleteAqscxzcfglbByMap(map);
	}

	@Override
	public List<String> getAqscxzcfIdsByMap(Map map) {
		// TODO Auto-generated method stub
		return aqscxzcfDao.getAqscxzcfIdsByMap(map);
	}

}
