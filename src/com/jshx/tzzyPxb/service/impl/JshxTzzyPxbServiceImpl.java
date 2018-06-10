package com.jshx.tzzyPxb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.tzzyPxb.dao.JshxTzzyPxbDao;
import com.jshx.tzzyPxb.entity.JshxTzzyPxb;
import com.jshx.tzzyPxb.service.JshxTzzyPxbService;

@Service("jshxTzzyPxbService")
public class JshxTzzyPxbServiceImpl extends BaseServiceImpl implements JshxTzzyPxbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxTzzyPxbDao jshxTzzyPxbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxTzzyPxbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxTzzyPxb getById(String id)
	{
		return jshxTzzyPxbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void save(JshxTzzyPxb jshxTzzyPxb)
	{
		jshxTzzyPxbDao.save(jshxTzzyPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void update(JshxTzzyPxb jshxTzzyPxb)
	{
		jshxTzzyPxbDao.update(jshxTzzyPxb);
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
		List objects=jshxTzzyPxbDao.findJshxTzzyPxb(paraMap);
		
		jshxTzzyPxbDao.removeAll(objects);
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
				    jshxTzzyPxbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<JshxTzzyPxb> getJshxTzzyPxbListByMap(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		return jshxTzzyPxbDao.getJshxTzzyPxbListByMap(map, start, limit);
	}

	@Override
	public int getJshxTzzyPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxTzzyPxbDao.getJshxTzzyPxbListSizeByMap(map);
	}
	@Transactional
	public void deleteByQyid(Map map) {
		// TODO Auto-generated method stub
		jshxTzzyPxbDao.deleteByQyid(map);
	}

	@Override
	public List<JshxTzzyPxb> findJshxTzzyPxb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return jshxTzzyPxbDao.findJshxTzzyPxb(paraMap);
	}

	@Transactional
	public void saveTzzyPxbs(List<JshxTzzyPxb> tzzyPxbs) {
		for(JshxTzzyPxb pb:tzzyPxbs){
			jshxTzzyPxbDao.save(pb);
		}	
	}
}
