package com.jshx.ssjjc.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.ssjjc.dao.SsjjcDao;
import com.jshx.ssjjc.entity.Ssjjc;
import com.jshx.ssjjc.entity.SsjjcBean;
import com.jshx.ssjjc.entity.SsjjcGz;
import com.jshx.ssjjc.service.SsjjcService;

@Service("ssjjcService")
public class SsjjcServiceImpl extends BaseServiceImpl implements SsjjcService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("ssjjcDao")
	private SsjjcDao ssjjcDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return ssjjcDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Ssjjc getById(String id)
	{
		return ssjjcDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(Ssjjc ssjjc)
	{
		ssjjcDao.save(ssjjc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Ssjjc ssjjc)
	{
		ssjjcDao.update(ssjjc);
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
		List objects=ssjjcDao.findSsjjc(paraMap);
		
		ssjjcDao.removeAll(objects);
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
				    ssjjcDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<SsjjcBean> findSsjjcBean(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return ssjjcDao.findSsjjcBean(paraMap);
	}

	@Override
	public List<EntBaseInfo> getQyListByLxAndWg(Map map) {
		// TODO Auto-generated method stub
		return ssjjcDao.getQyListByLxAndWg(map);
	}

	@Override
	public List<User> getRyListByLdOrZd(Map map) {
		// TODO Auto-generated method stub
		return ssjjcDao.getRyListByLdOrZd(map);
	}

	@Override
	public SsjjcGz getSsjjcGzByMap(Map map) {
		// TODO Auto-generated method stub
		return ssjjcDao.getSsjjcGzByMap(map);
	}

	@Transactional
	public void saveSsjjcGz(SsjjcGz ssjjcGz) {
		// TODO Auto-generated method stub
		ssjjcDao.saveSsjjcGz(ssjjcGz);
	}

	@Transactional
	public void updateSsjjcGz(SsjjcGz ssjjcGz) {
		// TODO Auto-generated method stub
		ssjjcDao.updateSsjjcGz(ssjjcGz);
	}

	@Transactional
	public void saveSsjjcBean(SsjjcBean ssjjcBean) {
		// TODO Auto-generated method stub
		ssjjcDao.saveSsjjcBean(ssjjcBean);
	}

	@Override
	public SsjjcBean getSsjjcBeanById(String id) {
		// TODO Auto-generated method stub
		return ssjjcDao.getSsjjcBeanById(id);
	}
}
