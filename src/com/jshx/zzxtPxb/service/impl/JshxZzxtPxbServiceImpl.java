package com.jshx.zzxtPxb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zzxtPxb.dao.JshxZzxtPxbDao;
import com.jshx.zzxtPxb.entity.JshxZzxtPxb;
import com.jshx.zzxtPxb.service.JshxZzxtPxbService;

@Service("jshxZzxtPxbService")
public class JshxZzxtPxbServiceImpl extends BaseServiceImpl implements JshxZzxtPxbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxZzxtPxbDao jshxZzxtPxbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxZzxtPxbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-19
	 */
	public JshxZzxtPxb getById(String id)
	{
		return jshxZzxtPxbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	@Transactional
	public void save(JshxZzxtPxb jshxZzxtPxb)
	{
		jshxZzxtPxbDao.save(jshxZzxtPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-19
	 */
	@Transactional
	public void update(JshxZzxtPxb jshxZzxtPxb)
	{
		jshxZzxtPxbDao.update(jshxZzxtPxb);
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
		List objects=jshxZzxtPxbDao.findJshxZzxtPxb(paraMap);
		
		jshxZzxtPxbDao.removeAll(objects);
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
				    jshxZzxtPxbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<JshxZzxtPxb> getJshxZzxtPxbListByMap(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		return jshxZzxtPxbDao.getJshxZzxtPxbListByMap(map, start, limit);
	}

	@Override
	public int getJshxZzxtPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxZzxtPxbDao.getJshxZzxtPxbListSizeByMap(map);
	}

	@Override
	public List<JshxZzxtPxb> findJshxZzxtPxb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return jshxZzxtPxbDao.findJshxZzxtPxb(paraMap);
	}
}
