package com.jshx.zxzzjh.service.impl;

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
import com.jshx.zxzzjh.dao.RecPlaDao;
import com.jshx.zxzzjh.entity.RecPla;
import com.jshx.zxzzjh.service.RecPlaService;

@Service("recPlaService")
public class RecPlaServiceImpl extends BaseServiceImpl implements RecPlaService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("recPlaDao")
	private RecPlaDao recPlaDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return recPlaDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public RecPla getById(String id)
	{
		return recPlaDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(RecPla recPla)
	{
		recPlaDao.save(recPla);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(RecPla recPla)
	{
		recPlaDao.update(recPla);
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
		List objects=recPlaDao.findRecPla(paraMap);
		
		recPlaDao.removeAll(objects);
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
				    recPlaDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findRecPla(Map<String, Object> paraMap){
		return recPlaDao.findRecPla(paraMap);
	}
}
