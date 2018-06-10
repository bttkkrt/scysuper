package com.jshx.zazPxb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zazPxb.dao.JshxZazPxbDao;
import com.jshx.zazPxb.entity.JshxPx;
import com.jshx.zazPxb.entity.JshxZazPxb;
import com.jshx.zazPxb.service.JshxZazPxbService;

@Service("jshxZazPxbService")
public class JshxZazPxbServiceImpl extends BaseServiceImpl implements JshxZazPxbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxZazPxbDao jshxZazPxbDao;

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
		return jshxZazPxbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-17
	 */
	public JshxZazPxb getById(String id)
	{
		return jshxZazPxbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void save(JshxZazPxb jshxZazPxb)
	{
		jshxZazPxbDao.save(jshxZazPxb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-17
	 */
	@Transactional
	public void update(JshxZazPxb jshxZazPxb)
	{
		jshxZazPxbDao.update(jshxZazPxb);
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
		List objects=jshxZazPxbDao.findJshxZazPxb(paraMap);
		
		jshxZazPxbDao.removeAll(objects);
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
				    jshxZazPxbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<JshxZazPxb> getJshxZazPxbListByMap(Map map, int start, int limit) {
		// TODO Auto-generated method stub
		return jshxZazPxbDao.getJshxZazPxbListByMap(map, start, limit);
	}

	@Override
	public int getJshxZazPxbListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxZazPxbDao.getJshxZazPxbListSizeByMap(map);
	}

	@Transactional
	public void deleteByQyid(Map map) {
		// TODO Auto-generated method stub
		jshxZazPxbDao.deleteByQyid(map);
	}

	@Override
	public List<JshxZazPxb> findJshxZazPxb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return jshxZazPxbDao.findJshxZazPxb(paraMap);
	}

	@Transactional
	public void saveZazPxbs(List<JshxZazPxb> pxbs) {
		for(JshxZazPxb jzp:pxbs){
			jshxZazPxbDao.save(jzp);
		}
		
	}

	@Override
	public List<JshxPx> getJshxPxListByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxZazPxbDao.getJshxPxListByMap(map);
	}

	@Override
	public JshxPx getTotalJshxPxListByMap(Map map) {
		// TODO Auto-generated method stub
		return jshxZazPxbDao.getTotalJshxPxListByMap(map);
	}
}
