package com.jshx.photo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.photo.dao.SzwxPhotoDao;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
@Service("szwxPhotoService")
public class SzwxPhotoServiceImpl implements SzwxPhotoService {
	/**
	 * Dao类
	 */
	@Resource
	private SzwxPhotoDao szwxPhotoDao;
	
	public SzwxPhotoDao getSzwxPhotoDao() {
		return szwxPhotoDao;
	}

	public void setSzwxPhotoDao(SzwxPhotoDao szwxPhotoDao) {
		this.szwxPhotoDao = szwxPhotoDao;
	}

	@Override
	public List<PhotoPic> findPicPath(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return szwxPhotoDao.findPicPath(paraMap);
	}

	@Transactional
	public void save(PhotoPic pic) {
		// TODO Auto-generated method stub
		szwxPhotoDao.save(pic);
	}

	@Transactional
	public void deleteImageWithFlag(String picName) {
		// TODO Auto-generated method stub
		 String[] idArray = picName.split("\\|");
			if(null != idArray)
			{
				for(String id : idArray)
				{
				    if(id!=null && !id.trim().equals(""))
				    	szwxPhotoDao.removeObject(szwxPhotoDao.getObjectById(PhotoPic.class, id));
				}
			}
		}

	@Override
	public PhotoPic getById(String id) {
		// TODO Auto-generated method stub
		return (PhotoPic) szwxPhotoDao.getObjectById(PhotoPic.class, id);
	}

	@Override
	public List<String> getQyidsByMap(Map map) {
		// TODO Auto-generated method stub
		return szwxPhotoDao.getQyidsByMap(map);
	}

	@Override
	public List<String> getQyidsByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return szwxPhotoDao.getQyidsByLoginId(loginId);
	}

	@Override
	@Transactional
	public void update(PhotoPic pic) {
		szwxPhotoDao.update(pic);
	}
}
