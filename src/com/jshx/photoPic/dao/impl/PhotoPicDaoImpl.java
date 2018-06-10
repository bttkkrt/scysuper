package com.jshx.photoPic.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.photoPic.entity.PhotoPic;
import com.jshx.photoPic.dao.PhotoPicDao;

@Component("photoPicDao")
public class PhotoPicDaoImpl extends BaseDaoImpl implements PhotoPicDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPhotoPicByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findPhotoPic(Map<String, Object> paraMap){
		return this.findListByHqlId("findPhotoPicByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PhotoPic getById(String id)
	{
		return (PhotoPic)this.getObjectById(PhotoPic.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PhotoPic photoPic)
	{
		photoPic.setId(null);
		this.saveOrUpdateObject(photoPic);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PhotoPic photoPic)
	{
		this.saveOrUpdateObject(photoPic);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PhotoPic.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PhotoPic photoPic = (PhotoPic)this.getObjectById(PhotoPic.class, id);
		photoPic.setDelFlag(1);
		this.saveObject(photoPic);
	}

	@Override
	public List<PhotoPic> findPicPath(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findPhotoPicByMap", paraMap);
	}
}
