package com.jshx.zywsgljbxx.service.impl;

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
import com.jshx.zywsgljbxx.dao.OccHeaInfoDao;
import com.jshx.zywsgljbxx.entity.OccHeaInfo;
import com.jshx.zywsgljbxx.service.OccHeaInfoService;

@Service("occHeaInfoService")
public class OccHeaInfoServiceImpl extends BaseServiceImpl implements OccHeaInfoService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("occHeaInfoDao")
	private OccHeaInfoDao occHeaInfoDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return occHeaInfoDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public OccHeaInfo getById(String id)
	{
		return occHeaInfoDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(OccHeaInfo occHeaInfo)
	{
		occHeaInfoDao.save(occHeaInfo);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(OccHeaInfo occHeaInfo)
	{
		occHeaInfoDao.update(occHeaInfo);
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
		List objects=occHeaInfoDao.findOccHeaInfo(paraMap);
		
		occHeaInfoDao.removeAll(objects);
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
				    occHeaInfoDao.deleteWithFlag(id);
			}
		}
	}
	
	public Object findObjectByMap(Class clazz,Map<String,Object> paraMap){
		return occHeaInfoDao.findObjectByFieldsMap(clazz, paraMap);
	}
	
	public List findListBy(Class clazz,String name,Object obj){
		return occHeaInfoDao.findListBy(clazz, name, obj);
	}
	
	public List findOccHeaInfo(Map<String, Object> paraMap){
		
		return occHeaInfoDao.findOccHeaInfo(paraMap);
	}
}
