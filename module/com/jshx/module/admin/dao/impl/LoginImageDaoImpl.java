package com.jshx.module.admin.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.dao.LoginImageDao;
import com.jshx.module.admin.entity.LoginImage;

@Component("loginimageDao")
public class LoginImageDaoImpl extends BaseDaoImpl implements LoginImageDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findLoginImageByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLoginImage(Map<String, Object> paraMap){
		return this.findListByHqlId("findLoginImageByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LoginImage getById(String id)
	{
		return (LoginImage)this.getObjectById(LoginImage.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LoginImage loginimage)
	{
		loginimage.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(loginimage);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LoginImage loginImage)
	{
		this.saveOrUpdateObject(loginImage);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(LoginImage.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		LoginImage loginImage = (LoginImage)this.getObjectById(LoginImage.class, id);
		loginImage.setDelFlag(1);
		this.saveObject(loginImage);
	}
}
