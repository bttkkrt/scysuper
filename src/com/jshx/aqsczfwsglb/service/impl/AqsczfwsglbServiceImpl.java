package com.jshx.aqsczfwsglb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqsczfwsglb.dao.AqsczfwsglbDao;
import com.jshx.aqsczfwsglb.entity.Aqsczfwsglb;
import com.jshx.aqsczfwsglb.service.AqsczfwsglbService;

@Service("aqsczfwsglbService")
public class AqsczfwsglbServiceImpl extends BaseServiceImpl implements AqsczfwsglbService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqsczfwsglbDao aqsczfwsglbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqsczfwsglbDao.findByPage(page, paraMap);
	}

	public List<Aqsczfwsglb> findAqsczfwsglb(Map<String, Object> paraMap)
	{
		return aqsczfwsglbDao.findAqsczfwsglb(paraMap);
	}
	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqsczfwsglb getById(String id)
	{
		return aqsczfwsglbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqsczfwsglb aqsczfwsglb)
	{
		aqsczfwsglbDao.save(aqsczfwsglb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqsczfwsglb aqsczfwsglb)
	{
		aqsczfwsglbDao.update(aqsczfwsglb);
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
		List objects=aqsczfwsglbDao.findAqsczfwsglb(paraMap);
		
		aqsczfwsglbDao.removeAll(objects);
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
				    aqsczfwsglbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public Aqsczfwsglb getAqsczfwsglbByMap(Map map) {
		// TODO Auto-generated method stub
		return aqsczfwsglbDao.getAqsczfwsglbByMap(map);
	}
}
