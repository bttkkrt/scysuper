package com.wzxx.csgl.service.impl;

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
import com.wzxx.csgl.dao.CityManDao;
import com.wzxx.csgl.entity.CityMan;
import com.wzxx.csgl.service.CityManService;

@Service("cityManService")
public class CityManServiceImpl extends BaseServiceImpl implements CityManService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("cityManDao")
	private CityManDao cityManDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return cityManDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CityMan getById(String id)
	{
		return cityManDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CityMan cityMan)
	{
		cityManDao.save(cityMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CityMan cityMan)
	{
		cityManDao.update(cityMan);
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
		List objects=cityManDao.findCityMan(paraMap);
		
		cityManDao.removeAll(objects);
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
				    cityManDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取城市管理
	 */
	public int findAllInfos(Map<String, Object> paraMap){
		return cityManDao.findAllInfos(paraMap);
	}
	
	/**
	 * 获取城市管理列表分页
	 */
	public List<CityMan> findAllInfo(Map<String, Object> paraMap,int totalPageNum,int pageSize){
		return cityManDao.findAllInfo(paraMap, totalPageNum, pageSize);
	}
}
