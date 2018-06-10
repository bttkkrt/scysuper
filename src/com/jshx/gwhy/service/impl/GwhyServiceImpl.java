package com.jshx.gwhy.service.impl;

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
import com.jshx.gwhy.dao.GwhyDao;
import com.jshx.gwhy.entity.Gwhy;
import com.jshx.gwhy.entity.GwhyBean;
import com.jshx.gwhy.service.GwhyService;

@Service("gwhyService")
public class GwhyServiceImpl extends BaseServiceImpl implements GwhyService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("gwhyDao")
	private GwhyDao gwhyDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return gwhyDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gwhy getById(String id)
	{
		return gwhyDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Gwhy gwhy)
	{
		gwhyDao.save(gwhy);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Gwhy gwhy)
	{
		gwhyDao.update(gwhy);
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
		List objects=gwhyDao.findGwhy(paraMap);
		
		gwhyDao.removeAll(objects);
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
				    gwhyDao.deleteWithFlag(id);
			}
		}
	}
   public List<GwhyBean> getGwhyListByMap(Map map){
	   return gwhyDao.getGwhyListByMap(map);
   }
	
	public  GwhyBean getTotalGwhyListByMap(Map map){
		return gwhyDao.getTotalGwhyListByMap(map);
	}
}
