package com.jshx.photo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.photo.dao.SzwxPhotoDao;
import com.jshx.photo.entity.PhotoPic;
@Component(" szwxPhotoDao")
public class SzwxPhotoDaoImpl extends BaseDaoImpl  implements SzwxPhotoDao {
	
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	@SuppressWarnings("unchecked")
	public List<PhotoPic> findPicPath(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("findPicById", paraMap);
	}

	public void save(PhotoPic model) {
		// TODO Auto-generated method stub
		model.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(model);
	}

	@Override
	public List<String> getQyidsByMap(Map map) {
		// TODO Auto-generated method stub
		return this.sqlMapClientTemplate.queryForList("query_qyids_byMap",map);
	}

	@Override
	public List<String> getQyidsByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return this.sqlMapClientTemplate.queryForList("query_qyids_byLoginId",loginId);
	}

	@Override
	public void update(PhotoPic model) {
		this.saveOrUpdateObject(model);
	}

}
