package com.wzxx.aqwh.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.wzxx.aqwh.dao.SafCulDao;
import com.wzxx.aqwh.entity.SafCul;
import com.wzxx.aqwh.service.SafCulService;
import com.wzxx.tzgg.entity.Tzgg;

@Service("safCulService")
public class SafCulServiceImpl extends BaseServiceImpl implements SafCulService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("safCulDao")
	private SafCulDao safCulDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return safCulDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SafCul getById(String id)
	{
		return safCulDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SafCul safCul)
	{
		safCulDao.save(safCul);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SafCul safCul)
	{
		safCulDao.update(safCul);
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
		List objects=safCulDao.findSafCul(paraMap);
		
		safCulDao.removeAll(objects);
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
				    safCulDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取安全文化
	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	return safCulDao.findAllInfos(paraMap);
  	}
	
	/**
	 * 获取安全文化列表分页
	 */
	public List<SafCul> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		return safCulDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
