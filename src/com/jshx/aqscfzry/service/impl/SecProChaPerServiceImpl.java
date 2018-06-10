package com.jshx.aqscfzry.service.impl;

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
import com.jshx.aqscfzry.dao.SecProChaPerDao;
import com.jshx.aqscfzry.entity.SecProChaPer;
import com.jshx.aqscfzry.service.SecProChaPerService;

@Service("secProChaPerService")
public class SecProChaPerServiceImpl extends BaseServiceImpl implements SecProChaPerService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("secProChaPerDao")
	private SecProChaPerDao secProChaPerDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return secProChaPerDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProChaPer getById(String id)
	{
		return secProChaPerDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SecProChaPer secProChaPer)
	{
		secProChaPerDao.save(secProChaPer);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SecProChaPer secProChaPer)
	{
		secProChaPerDao.update(secProChaPer);
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
		List objects=secProChaPerDao.findSecProChaPer(paraMap);
		
		secProChaPerDao.removeAll(objects);
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
				    secProChaPerDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取手机号
	 * 
	 */
	public List<SecProChaPer> getTelephone(Map map){
		return secProChaPerDao.getTelephone(map);
	}

	@Override
	public List<SecProChaPer> findSecProChaPer(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return secProChaPerDao.findSecProChaPer(paraMap);
	}
}
