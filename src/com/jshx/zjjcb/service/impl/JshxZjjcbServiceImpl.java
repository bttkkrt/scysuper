package com.jshx.zjjcb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zjjcb.dao.JshxZjjcbDao;
import com.jshx.zjjcb.entity.JshxZjjcb;
import com.jshx.zjjcb.service.JshxZjjcbService;

@Service("jshxZjjcbService")
public class JshxZjjcbServiceImpl extends BaseServiceImpl implements JshxZjjcbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxZjjcbDao jshxZjjcbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxZjjcbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZjjcb getById(String id)
	{
		return jshxZjjcbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(JshxZjjcb jshxZjjcb)
	{
		jshxZjjcbDao.save(jshxZjjcb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(JshxZjjcb jshxZjjcb)
	{
		jshxZjjcbDao.update(jshxZjjcb);
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
		List objects=jshxZjjcbDao.findJshxZjjcb(paraMap);
		
		jshxZjjcbDao.removeAll(objects);
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
				    jshxZjjcbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public JshxZjjcb getJshxZjjcbByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxZjjcbDao.getJshxZjjcbByMap(map);
	}

	@Override
	public List<JshxZjjcb> getCompanyListByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxZjjcbDao.getCompanyListByMap(map);
	}
}
