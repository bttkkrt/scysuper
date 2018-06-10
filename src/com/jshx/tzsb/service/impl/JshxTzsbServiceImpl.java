package com.jshx.tzsb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzsb.dao.JshxTzsbDao;
import com.jshx.tzsb.entity.JshxTzsb;
import com.jshx.tzsb.service.JshxTzsbService;

@Service("jshxTzsbService")
public class JshxTzsbServiceImpl extends BaseServiceImpl implements JshxTzsbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxTzsbDao jshxTzsbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxTzsbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxTzsb getById(String id)
	{
		return jshxTzsbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	@Transactional
	public void save(JshxTzsb jshxTzsb)
	{
		jshxTzsbDao.save(jshxTzsb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	@Transactional
	public void update(JshxTzsb jshxTzsb)
	{
		jshxTzsbDao.update(jshxTzsb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-19
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=jshxTzsbDao.findJshxTzsb(paraMap);
		
		jshxTzsbDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-19
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
				    jshxTzsbDao.deleteWithFlag(id);
			}
		}
	}
}
