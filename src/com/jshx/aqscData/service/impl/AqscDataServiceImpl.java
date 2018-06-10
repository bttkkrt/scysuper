package com.jshx.aqscData.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscData.dao.AqscDataDao;
import com.jshx.aqscData.entity.AqscData;
import com.jshx.aqscData.service.AqscDataService;

@Service("aqscDataService")
public class AqscDataServiceImpl extends BaseServiceImpl implements AqscDataService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqscDataDao aqscDataDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscDataDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public AqscData getById(String id)
	{
		return aqscDataDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(AqscData aqscData)
	{
		aqscDataDao.save(aqscData);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(AqscData aqscData)
	{
		aqscDataDao.update(aqscData);
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
		List objects=aqscDataDao.findAqscData(paraMap);
		
		aqscDataDao.removeAll(objects);
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
				    aqscDataDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public AqscData getAqscDataByMap(Map map) {
		// TODO Auto-generated method stub
		return aqscDataDao.getAqscDataByMap(map);
	}
}
