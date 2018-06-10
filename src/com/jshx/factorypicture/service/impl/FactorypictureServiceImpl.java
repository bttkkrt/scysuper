package com.jshx.factorypicture.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.factorypicture.dao.FactorypictureDao;
import com.jshx.factorypicture.entity.Factorypicture;
import com.jshx.factorypicture.service.FactorypictureService;

@Service("factorypictureService")
public class FactorypictureServiceImpl extends BaseServiceImpl implements FactorypictureService
{
	/**
	 * Dao类
	 */
	@Resource
	private FactorypictureDao factorypictureDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return factorypictureDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Factorypicture getById(String id)
	{
		return factorypictureDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Factorypicture factorypicture)
	{
		factorypictureDao.save(factorypicture);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Factorypicture factorypicture)
	{
		factorypictureDao.update(factorypicture);
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
		List objects=factorypictureDao.findFactorypicture(paraMap);
		
		factorypictureDao.removeAll(objects);
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
				    factorypictureDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Factorypicture> findFactorypicture(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return factorypictureDao.findFactorypicture(paraMap);
	}
}
