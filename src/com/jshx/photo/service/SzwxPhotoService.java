package com.jshx.photo.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.photo.entity.PhotoPic;

public interface SzwxPhotoService  extends BaseService{
	public List<PhotoPic> findPicPath(Map<String, Object> paraMap);
	public void save(PhotoPic pic);
	public void update(PhotoPic pic);
	public void deleteImageWithFlag(String picName);
	public PhotoPic getById(String id);
	/**
	 * 根据条件查询是否是危化科 、职业卫生健康科的企业 李军 2013-0824
	 */
	public List<String> getQyidsByMap(Map map);
	/**
	 * 根据企业登录名查询该企业的id lijun 2013-09-17
	 */
	public List<String> getQyidsByLoginId(String loginId);
}
