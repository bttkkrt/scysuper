package com.jshx.xrcPxb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.xrcPxb.dao.JshxXrcPxbDao;
import com.jshx.xrcPxb.entity.JshxXrcPxb;
import com.jshx.xrcPxb.service.JshxXrcPxbService;

@Service("jshxXrcPxbService")
public class JshxXrcPxbServiceImpl extends BaseServiceImpl implements JshxXrcPxbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxXrcPxbDao jshxXrcPxbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 * author：陆婷
	 * 2013-08-17
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxXrcPxbDao.findByPage(page, paraMap);
	}

	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public List<JshxXrcPxb> findJshxXrcPxb(Map<String, Object> paraMap)
	{
		return jshxXrcPxbDao.findJshxXrcPxb(paraMap);
	}
	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxXrcPxb getById(String id)
	{
		return jshxXrcPxbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void save(JshxXrcPxb jshxXrcPxb)
	{
		jshxXrcPxbDao.save(jshxXrcPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void update(JshxXrcPxb jshxXrcPxb)
	{
		jshxXrcPxbDao.update(jshxXrcPxb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=jshxXrcPxbDao.findJshxXrcPxb(paraMap);
		
		jshxXrcPxbDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-17
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
				    jshxXrcPxbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<JshxXrcPxb> getJshxXrcPxbListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		return jshxXrcPxbDao.getJshxXrcPxbListByMap(map, start, limit);
	}

	@Override
	public int getJshxXrcPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxXrcPxbDao.getJshxXrcPxbListSizeByMap(map);
	}
}
