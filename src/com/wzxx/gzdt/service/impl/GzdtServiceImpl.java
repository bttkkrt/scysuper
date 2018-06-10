package com.wzxx.gzdt.service.impl;

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
import com.wzxx.gzdt.dao.GzdtDao;
import com.wzxx.gzdt.entity.Gzdt;
import com.wzxx.gzdt.service.GzdtService;

@Service("gzdtService")
public class GzdtServiceImpl extends BaseServiceImpl implements GzdtService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("gzdtDao")
	private GzdtDao gzdtDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return gzdtDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gzdt getById(String id)
	{
		return gzdtDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param contentInformations 信息
	 */
	@Transactional
	public void save(Gzdt tzgg)
	{
		gzdtDao.save(tzgg);
	}

	/**
	 * 修改信息
	 * @param contentInformations 信息
	 */
	@Transactional
	public void update(Gzdt tzgg)
	{
		gzdtDao.update(tzgg);
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
		List objects=gzdtDao.findGzdt(paraMap);
		
		gzdtDao.removeAll(objects);
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
			    	gzdtDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 获取未读公告
	 */
	public int findAllInfos(Map<String, Object> paraMap)
  	{
    	return gzdtDao.findAllInfos(paraMap);
  	}
	
	/**
	 * 获取未读公告列表分页
	 */
	public List<Gzdt> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize)
	{
		return gzdtDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
