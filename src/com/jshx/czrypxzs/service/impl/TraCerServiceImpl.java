package com.jshx.czrypxzs.service.impl;

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
import com.jshx.czrypxzs.dao.TraCerDao;
import com.jshx.czrypxzs.entity.TraCer;
import com.jshx.czrypxzs.service.TraCerService;

@Service("traCerService")
public class TraCerServiceImpl extends BaseServiceImpl implements TraCerService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("traCerDao")
	private TraCerDao traCerDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return traCerDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TraCer getById(String id)
	{
		return traCerDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(TraCer traCer)
	{
		traCerDao.save(traCer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(TraCer traCer)
	{
		traCerDao.update(traCer);
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
		List objects=traCerDao.findTraCer(paraMap);
		
		traCerDao.removeAll(objects);
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
				    traCerDao.deleteWithFlag(id);
			}
		}
	}
}
