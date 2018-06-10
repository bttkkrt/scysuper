package com.jshx.aqscgljl.service.impl;

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
import com.jshx.aqscgljl.dao.SecProManDao;
import com.jshx.aqscgljl.entity.SecProMan;
import com.jshx.aqscgljl.service.SecProManService;

@Service("secProManService")
public class SecProManServiceImpl extends BaseServiceImpl implements SecProManService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("secProManDao")
	private SecProManDao secProManDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return secProManDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProMan getById(String id)
	{
		return secProManDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SecProMan secProMan)
	{
		secProManDao.save(secProMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SecProMan secProMan)
	{
		secProManDao.update(secProMan);
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
		List objects=secProManDao.findSecProMan(paraMap);
		
		secProManDao.removeAll(objects);
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
				    secProManDao.deleteWithFlag(id);
			}
		}
	}
	/**
	 * 获取手机号
	 */
	public List<SecProMan> getTelephone(Map map){
		return secProManDao.getTelephone(map);
	}

	@Override
	public List<SecProMan> findSecProMan(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return secProManDao.findSecProMan(paraMap);
	}
	
}
