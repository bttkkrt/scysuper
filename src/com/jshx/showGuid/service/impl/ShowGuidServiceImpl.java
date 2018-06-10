package com.jshx.showGuid.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.showGuid.dao.ShowGuidDao;
import com.jshx.showGuid.entity.ShowGuid;
import com.jshx.showGuid.service.ShowGuidService;

@Service("showGuidService")
public class ShowGuidServiceImpl extends BaseServiceImpl implements ShowGuidService
{
	/**
	 * Dao类
	 */
	@Resource
	private ShowGuidDao showGuidDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return showGuidDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public ShowGuid getById(String id)
	{
		return showGuidDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(ShowGuid showGuid)
	{
		showGuidDao.save(showGuid);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(ShowGuid showGuid)
	{
		showGuidDao.update(showGuid);
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
		List objects=showGuidDao.findShowGuid(paraMap);
		
		showGuidDao.removeAll(objects);
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
			    	showGuidDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<String> findShowGuid(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return showGuidDao.findShowGuid(paraMap);
	}
}
