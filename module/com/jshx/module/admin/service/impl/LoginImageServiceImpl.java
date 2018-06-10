package com.jshx.module.admin.service.impl;

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
import com.jshx.module.admin.dao.LoginImageDao;
import com.jshx.module.admin.entity.LoginImage;
import com.jshx.module.admin.service.LoginImageService;

@Service("loginImageService")
public class LoginImageServiceImpl extends BaseServiceImpl implements LoginImageService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("loginimageDao")
	private LoginImageDao loginImageDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return loginImageDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LoginImage getById(String id)
	{
		return loginImageDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(LoginImage loginImage)
	{
		loginImageDao.save(loginImage);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(LoginImage loginImage)
	{
		loginImageDao.update(loginImage);
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
		List objects=loginImageDao.findLoginImage(paraMap);
		
		loginImageDao.removeAll(objects);
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
			    	loginImageDao.deleteWithFlag(id);
			}
		}
	}
}
