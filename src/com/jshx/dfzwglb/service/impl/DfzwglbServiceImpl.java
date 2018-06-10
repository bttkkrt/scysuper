package com.jshx.dfzwglb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dfzwglb.dao.DfzwglbDao;
import com.jshx.dfzwglb.entity.Dfzwglb;
import com.jshx.dfzwglb.service.DfzwglbService;

@Service("dfzwglbService")
public class DfzwglbServiceImpl extends BaseServiceImpl implements DfzwglbService
{
	/**
	 * Dao类
	 */
	@Resource
	private DfzwglbDao dfzwglbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return dfzwglbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Dfzwglb getById(String id)
	{
		return dfzwglbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Dfzwglb dfzwglb)
	{
		dfzwglbDao.save(dfzwglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Dfzwglb dfzwglb)
	{
		dfzwglbDao.update(dfzwglb);
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
		List objects=dfzwglbDao.findDfzwglb(paraMap);
		
		dfzwglbDao.removeAll(objects);
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
				    dfzwglbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Dfzwglb> findDfzwglb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return dfzwglbDao.findDfzwglb(paraMap);
	}

	@Override
	public Dfzwglb getDfzwglbByMap(Map map) {
		// TODO Auto-generated method stub
		return dfzwglbDao.getDfzwglbByMap(map);
	}
}
