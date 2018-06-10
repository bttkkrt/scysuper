package com.jshx.module.form.service;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.module.form.dao.impl.AttachfileDao;
import com.jshx.module.form.entity.Attachfiles;



@Service("attachfileManager")
public class AttachfileService extends BaseServiceImpl {


	@Autowired
	private AttachfileDao  attachfileDao;


	@Transactional
	public void save(Attachfiles attachFile) {
		//Blob blob = attachFile.getContent();
		
		//attachfileDao.saveBaseModelObject(attachFile);
		//attachfileDao.flush();
		//attachfileDao.refresh(attachFile, LockMode.UPGRADE);
		//attachFile.setContent(blob);
		attachfileDao.saveBaseModelObject(attachFile);
	}

	@Transactional
	public void del(String rowguid)
	{
	    Attachfiles attachfiles = (Attachfiles)attachfileDao.getObjectByProperty(Attachfiles.class,"rowguid",rowguid);
	    attachfileDao.removeObject(attachfiles);
	}
	
	@Transactional
	public void delbyId(String id)
	{
	    Attachfiles attachfiles = (Attachfiles)attachfileDao.getObjectById(Attachfiles.class, id);
	    attachfileDao.removeObject(attachfiles);
	}
	
	

	public List<Attachfiles> getAttFilesByGuID(String StrGuid){
		String hql = "from Attachfiles a where a.formRowGuid='"+StrGuid+"'";

		return (List<Attachfiles>)attachfileDao.findListByHql(hql);
		//entityDao.find(hql, StrGuid);
	}


	public Attachfiles getAttFileByRowguid(String rowguid)
	{
		String hql="from Attachfiles a where a.rowguid='"+rowguid+"'";
		List<Attachfiles> list=(List<Attachfiles>)attachfileDao.findListByHql(hql);
		if(list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}

}
