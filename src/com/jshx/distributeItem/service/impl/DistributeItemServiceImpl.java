package com.jshx.distributeItem.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.distributeItem.dao.DistributeItemDao;
import com.jshx.distributeItem.entity.DistributeItem;
import com.jshx.distributeItem.service.DistributeItemService;

@Service("distributeItemService")
public class DistributeItemServiceImpl extends BaseServiceImpl implements DistributeItemService
{
	/**
	 * Dao类
	 */
	@Resource
	private DistributeItemDao distributeItemDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return distributeItemDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DistributeItem getById(String id)
	{
		return distributeItemDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(DistributeItem distributeItem)
	{
		distributeItemDao.save(distributeItem);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(DistributeItem distributeItem)
	{
		distributeItemDao.update(distributeItem);
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
		List objects=distributeItemDao.findDistributeItem(paraMap);
		
		distributeItemDao.removeAll(objects);
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
				    distributeItemDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findDistributeItem(Map<String, Object> paraMap){
		return distributeItemDao.findDistributeItem(paraMap);
	}

	@Override
	public List<DistributeItem> findAllDistributeItem(Map<String, Object> paraMap)
	{
		return distributeItemDao.findAllDistributeItem(paraMap);
	}
}
