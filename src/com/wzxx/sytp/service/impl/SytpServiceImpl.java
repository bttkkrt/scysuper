package com.wzxx.sytp.service.impl;

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
import com.wzxx.sytp.dao.SytpDao;
import com.wzxx.sytp.entity.Sytp;
import com.wzxx.sytp.service.SytpService;

@Service("sytpService")
public class SytpServiceImpl extends BaseServiceImpl implements SytpService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("sytpDao")
	private SytpDao sytpDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return sytpDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Sytp getById(String id)
	{
		return sytpDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param contentInformations 信息
	 */
	@Transactional
	public void save(Sytp tzgg)
	{
		sytpDao.save(tzgg);
	}

	/**
	 * 修改信息
	 * @param contentInformations 信息
	 */
	@Transactional
	public void update(Sytp tzgg)
	{
		sytpDao.update(tzgg);
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
		List objects=sytpDao.findSytp(paraMap);
		
		sytpDao.removeAll(objects);
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
			    	sytpDao.deleteWithFlag(id);
			}
		}
	}
	
	/**
	 * 获取未读公告
	 */
	public List<Sytp> findAllInfos(Map<String, Object> paraMap)
  	{
    	return sytpDao.findAllInfos(paraMap);
  	}
	
}
