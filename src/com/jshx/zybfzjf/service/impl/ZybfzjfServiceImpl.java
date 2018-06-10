package com.jshx.zybfzjf.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zybfzjf.dao.ZybfzjfDao;
import com.jshx.zybfzjf.entity.Zybfzjf;
import com.jshx.zybfzjf.service.ZybfzjfService;

@Service("zybfzjfService")
public class ZybfzjfServiceImpl extends BaseServiceImpl implements ZybfzjfService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZybfzjfDao zybfzjfDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zybfzjfDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zybfzjf getById(String id)
	{
		return zybfzjfDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zybfzjf zybfzjf)
	{
		zybfzjfDao.save(zybfzjf);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zybfzjf zybfzjf)
	{
		zybfzjfDao.update(zybfzjf);
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
		List objects=zybfzjfDao.findZybfzjf(paraMap);
		
		zybfzjfDao.removeAll(objects);
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
				    zybfzjfDao.deleteWithFlag(id);
			}
		}
	}
}
