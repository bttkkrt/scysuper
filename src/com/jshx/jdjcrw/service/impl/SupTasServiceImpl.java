package com.jshx.jdjcrw.service.impl;

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
import com.jshx.jdjcrw.dao.SupTasDao;
import com.jshx.jdjcrw.entity.SupTas;
import com.jshx.jdjcrw.service.SupTasService;

@Service("supTasService")
public class SupTasServiceImpl extends BaseServiceImpl implements SupTasService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("supTasDao")
	private SupTasDao supTasDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return supTasDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupTas getById(String id)
	{
		return supTasDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SupTas supTas)
	{
		supTasDao.save(supTas);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SupTas supTas)
	{
		supTasDao.update(supTas);
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
		List objects=supTasDao.findSupTas(paraMap);
		
		supTasDao.removeAll(objects);
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
				    supTasDao.deleteWithFlag(id);
			}
		}
	}
	public List<Map<String,Object>> findCheckListByMap(Map<String, Object> paraMap){//获取检查机构的用户id和name
		return supTasDao.findCheckListByMap(paraMap);
	}
	
	public List<Map> getcheckList(Map map) {//获取检查项列表
		return supTasDao.getcheckList(map);
	}
	
}
