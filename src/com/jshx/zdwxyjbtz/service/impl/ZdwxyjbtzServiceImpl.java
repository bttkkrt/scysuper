package com.jshx.zdwxyjbtz.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxyjbtz.dao.ZdwxyjbtzDao;
import com.jshx.zdwxyjbtz.entity.Zdwxyjbtz;
import com.jshx.zdwxyjbtz.service.ZdwxyjbtzService;

@Service("zdwxyjbtzService")
public class ZdwxyjbtzServiceImpl extends BaseServiceImpl implements ZdwxyjbtzService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZdwxyjbtzDao zdwxyjbtzDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zdwxyjbtzDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxyjbtz getById(String id)
	{
		return zdwxyjbtzDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zdwxyjbtz zdwxyjbtz)
	{
		zdwxyjbtzDao.save(zdwxyjbtz);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zdwxyjbtz zdwxyjbtz)
	{
		zdwxyjbtzDao.update(zdwxyjbtz);
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
		List objects=zdwxyjbtzDao.findZdwxyjbtz(paraMap);
		
		zdwxyjbtzDao.removeAll(objects);
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
				    zdwxyjbtzDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteWhpByMap(Map map) {
		// TODO Auto-generated method stub
		zdwxyjbtzDao.deleteWhpByMap(map);
	}
}
