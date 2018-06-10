package com.jshx.gpqyyhzg.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gpqyyhzg.dao.GpqyyhzgDao;
import com.jshx.gpqyyhzg.entity.Gpqyyhzg;
import com.jshx.gpqyyhzg.service.GpqyyhzgService;

@Service("gpqyyhzgService")
public class GpqyyhzgServiceImpl extends BaseServiceImpl implements GpqyyhzgService
{
	/**
	 * Dao类
	 */
	@Resource
	private GpqyyhzgDao gpqyyhzgDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return gpqyyhzgDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Gpqyyhzg getById(String id)
	{
		return gpqyyhzgDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Gpqyyhzg gpqyyhzg)
	{
		gpqyyhzgDao.save(gpqyyhzg);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Gpqyyhzg gpqyyhzg)
	{
		gpqyyhzgDao.update(gpqyyhzg);
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
		List objects=gpqyyhzgDao.findGpqyyhzg(paraMap);
		
		gpqyyhzgDao.removeAll(objects);
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
				    gpqyyhzgDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public Gpqyyhzg getGpdbByMap(Map map) {
		// TODO Auto-generated method stub
		return gpqyyhzgDao.getGpdbByMap(map);
	}

	@Override
	public Gpqyyhzg findGpqyyhzgAllByMap(Map map) {
		// TODO Auto-generated method stub
		return gpqyyhzgDao.findGpqyyhzgAllByMap(map);
	}

	@Override
	public List<Gpqyyhzg> findGpqyyhzgAllListByMap(Map map) {
		// TODO Auto-generated method stub
		return gpqyyhzgDao.findGpqyyhzgAllListByMap(map);
	}

}
