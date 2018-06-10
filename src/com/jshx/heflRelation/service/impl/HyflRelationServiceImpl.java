package com.jshx.heflRelation.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.heflRelation.dao.HyflRelationDao;
import com.jshx.heflRelation.entity.HyflRelation;
import com.jshx.heflRelation.service.HyflRelationService;

@Service("hyflRelationService")
public class HyflRelationServiceImpl extends BaseServiceImpl implements HyflRelationService
{
	/**
	 * Dao类
	 */
	@Resource
	private HyflRelationDao hyflRelationDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return hyflRelationDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public HyflRelation getById(String id)
	{
		return hyflRelationDao.getById(id);
	}
	
	/**
	 * 获得所有数据
	 * @param paraMap
	 * @return
	 */
	public List<HyflRelation> findAll(Map<String, Object> paraMap){
		return hyflRelationDao.findHyflRelation(paraMap);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(HyflRelation hyflRelation)
	{
		hyflRelationDao.save(hyflRelation);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(HyflRelation hyflRelation)
	{
		hyflRelationDao.update(hyflRelation);
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
		List objects=hyflRelationDao.findHyflRelation(paraMap);
		
		hyflRelationDao.removeAll(objects);
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
				    hyflRelationDao.deleteWithFlag(id);
			}
		}
	}
}
