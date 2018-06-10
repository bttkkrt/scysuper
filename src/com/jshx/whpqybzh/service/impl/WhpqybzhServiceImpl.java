package com.jshx.whpqybzh.service.impl;

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
import com.jshx.whpqybzh.dao.WhpqybzhDao;
import com.jshx.whpqybzh.entity.Whpqybzh;
import com.jshx.whpqybzh.entity.WhpqybzhBean;
import com.jshx.whpqybzh.service.WhpqybzhService;

@Service("whpqybzhService")
public class WhpqybzhServiceImpl extends BaseServiceImpl implements WhpqybzhService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("whpqybzhDao")
	private WhpqybzhDao whpqybzhDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return whpqybzhDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whpqybzh getById(String id)
	{
		return whpqybzhDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Whpqybzh whpqybzh)
	{
		whpqybzhDao.save(whpqybzh);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Whpqybzh whpqybzh)
	{
		whpqybzhDao.update(whpqybzh);
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
		List objects=whpqybzhDao.findWhpqybzh(paraMap);
		
		whpqybzhDao.removeAll(objects);
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
				    whpqybzhDao.deleteWithFlag(id);
			}
		}
	}
	
	public List<WhpqybzhBean> getWhpqybzhListByMap(Map map){
		return whpqybzhDao.getWhpqybzhListByMap(map);
	}
	
	public WhpqybzhBean getTotalLiWhpqybzhstByMap(Map map){
		return whpqybzhDao.getTotalLiWhpqybzhstByMap(map);
	}
}
