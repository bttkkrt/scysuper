package com.jshx.zdwxytjxx.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zdwxytjxx.dao.ZdwxytjxxDao;
import com.jshx.zdwxytjxx.entity.Zdwxytj;
import com.jshx.zdwxytjxx.entity.Zdwxytjxx;
import com.jshx.zdwxytjxx.service.ZdwxytjxxService;

@Service("zdwxytjxxService")
public class ZdwxytjxxServiceImpl extends BaseServiceImpl implements ZdwxytjxxService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZdwxytjxxDao zdwxytjxxDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zdwxytjxxDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zdwxytjxx getById(String id)
	{
		return zdwxytjxxDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Zdwxytjxx zdwxytjxx)
	{
		zdwxytjxxDao.save(zdwxytjxx);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zdwxytjxx zdwxytjxx)
	{
		zdwxytjxxDao.update(zdwxytjxx);
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
		List objects=zdwxytjxxDao.findZdwxytjxx(paraMap);
		
		zdwxytjxxDao.removeAll(objects);
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
				    zdwxytjxxDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Zdwxytjxx> findZdwxytjxx(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return zdwxytjxxDao.findZdwxytjxx(paraMap);
	}

	@Override
	public Zdwxytj getZdwxytjByMap(Map map) {
		// TODO Auto-generated method stub
		return zdwxytjxxDao.getZdwxytjByMap(map);
	}
}
