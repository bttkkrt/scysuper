package com.jshx.aqscsgcc.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.aqscsgcc.dao.AqscsgccDao;
import com.jshx.aqscsgcc.entity.Aqscsgcc;
import com.jshx.aqscsgcc.service.AqscsgccService;

@Service("aqscsgccService")
public class AqscsgccServiceImpl extends BaseServiceImpl implements AqscsgccService
{
	/**
	 * Dao类
	 */
	@Resource
	private AqscsgccDao aqscsgccDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return aqscsgccDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Aqscsgcc getById(String id)
	{
		return aqscsgccDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Aqscsgcc aqscsgcc)
	{
		aqscsgccDao.save(aqscsgcc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Aqscsgcc aqscsgcc)
	{
		aqscsgccDao.update(aqscsgcc);
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
		List objects=aqscsgccDao.findAqscsgcc(paraMap);
		
		aqscsgccDao.removeAll(objects);
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
				    aqscsgccDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void deleteAqscsgccglbByMap(Map map) {
		// TODO Auto-generated method stub
		aqscsgccDao.deleteAqscsgccglbByMap(map);
	}

	@Override
	public List<String> getAqscsgccIdsByMap(Map map) {
		// TODO Auto-generated method stub
		return aqscsgccDao.getAqscsgccIdsByMap(map);
	}
}
