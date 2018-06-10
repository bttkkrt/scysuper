package com.jshx.aqsczfws.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqsczfws.dao.AqsczfwsDao;
import com.jshx.aqsczfws.entity.Aqsczfws;
import com.jshx.aqsczfws.service.AqsczfwsService;

@Service("aqsczfwsService")
public class AqsczfwsServiceImpl extends BaseServiceImpl implements AqsczfwsService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqsczfwsDao aqsczfwsDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqsczfwsDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqsczfws getById(String id)
	{
		return aqsczfwsDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqsczfws aqsczfws)
	{
		aqsczfwsDao.save(aqsczfws);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqsczfws aqsczfws)
	{
		aqsczfwsDao.update(aqsczfws);
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
		List objects=aqsczfwsDao.findAqsczfws(paraMap);
		
		aqsczfwsDao.removeAll(objects);
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
				    aqsczfwsDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteAqsczfwsglbByMap(Map map) {
		// TODO Auto-generated method stub
		aqsczfwsDao.deleteAqsczfwsglbByMap(map);
	}

	@Override
	public List<String> getAqsczfwsIdsByMap(Map map) {
		// TODO Auto-generated method stub
		return aqsczfwsDao.getAqsczfwsIdsByMap(map);
	}
}
