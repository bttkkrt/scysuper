package com.jshx.zjjtzsbzyry.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zjjtzsbzyry.dao.ZjjtzsbzyryDao;
import com.jshx.zjjtzsbzyry.entity.Zjjtzsbzyry;
import com.jshx.zjjtzsbzyry.service.ZjjtzsbzyryService;

@Service("zjjtzsbzyryService")
public class ZjjtzsbzyryServiceImpl extends BaseServiceImpl implements ZjjtzsbzyryService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZjjtzsbzyryDao zjjtzsbzyryDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zjjtzsbzyryDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zjjtzsbzyry getById(String id)
	{
		return zjjtzsbzyryDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zjjtzsbzyry zjjtzsbzyry)
	{
		zjjtzsbzyryDao.save(zjjtzsbzyry);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zjjtzsbzyry zjjtzsbzyry)
	{
		zjjtzsbzyryDao.update(zjjtzsbzyry);
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
		List objects=zjjtzsbzyryDao.findZjjtzsbzyry(paraMap);
		
		zjjtzsbzyryDao.removeAll(objects);
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
				    zjjtzsbzyryDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteAll() {
		// TODO Auto-generated method stub
		zjjtzsbzyryDao.deleteAll();
	}
}
