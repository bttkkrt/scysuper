package com.jshx.sgtjData.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.sgtjData.dao.SgtjDataDao;
import com.jshx.sgtjData.entity.SgtjData;
import com.jshx.sgtjData.service.SgtjDataService;

@Service("sgtjDataService")
public class SgtjDataServiceImpl extends BaseServiceImpl implements SgtjDataService
{
	/**
	 * Dao类
	 */
	@Resource
	private SgtjDataDao sgtjDataDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return sgtjDataDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SgtjData getById(String id)
	{
		return sgtjDataDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SgtjData sgtjData)
	{
		sgtjDataDao.save(sgtjData);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SgtjData sgtjData)
	{
		sgtjDataDao.update(sgtjData);
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
		List objects=sgtjDataDao.findSgtjData(paraMap);
		
		sgtjDataDao.removeAll(objects);
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
				    sgtjDataDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List findSgtjData(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return sgtjDataDao.findSgtjData(paraMap);
	}
}
