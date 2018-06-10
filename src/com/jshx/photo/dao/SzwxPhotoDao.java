package com.jshx.photo.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.photo.entity.PhotoPic;


public interface SzwxPhotoDao extends BaseDao
{

	public List<PhotoPic> findPicPath(Map<String, Object> paraMap);
	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PhotoPic model);
	public void update(PhotoPic model);
	/**
	 * 根据条件查询是否是危化科 、职业卫生健康科的企业 李军 2013-0824
	 */
	public List<String> getQyidsByMap(Map map);
	/**
	 * 根据企业登录名查询该企业的id lijun 2013-09-17
	 */
	public List<String> getQyidsByLoginId(String loginId);

}
