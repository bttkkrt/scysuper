package com.jshx.zyjktj.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zyjktj.dao.OccHeaExaDao;
import com.jshx.zyjktj.entity.OccHeaExa;
import com.jshx.zyjktj.entity.OccHeaExaList;
import com.jshx.zyjktj.service.OccHeaExaService;

@Service("occHeaExaService")
public class OccHeaExaServiceImpl extends BaseServiceImpl implements OccHeaExaService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("occHeaExaDao")
	private OccHeaExaDao occHeaExaDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return occHeaExaDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccHeaExa getById(String id)
	{
		return occHeaExaDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(OccHeaExa occHeaExa)
	{
		occHeaExaDao.save(occHeaExa);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(OccHeaExa occHeaExa)
	{
		occHeaExaDao.update(occHeaExa);
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
		List objects=occHeaExaDao.findOccHeaExa(paraMap);
		
		occHeaExaDao.removeAll(objects);
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
				    occHeaExaDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findOccHeaExaLists(Map<String, Object> paraMap){
		return occHeaExaDao.findListByHqlId("findOccHeaExaListByMap", paraMap);
	}
	
	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void saveOccHeaExaList(OccHeaExaList occHeaExaList)
	{
		occHeaExaDao.saveOccHeaExaList(occHeaExaList);
	}
	
	
	
	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteOccHeaExaListWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    occHeaExaDao.deleteOccHeaExaListWithFlag(id);
			}
		}
	}
	
	
	
}
