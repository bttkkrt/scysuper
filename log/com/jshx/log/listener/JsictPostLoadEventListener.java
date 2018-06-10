package com.jshx.log.listener;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.internal.DefaultPostLoadEventListener;
import org.hibernate.event.spi.PostLoadEvent;

import com.jshx.log.entity.DataLog;

public class JsictPostLoadEventListener extends DefaultPostLoadEventListener {

	private static final long serialVersionUID = 7128224935063879704L;
	
	@Override
	public void onPostLoad(PostLoadEvent event){
		if(EntitiesMonitor.checkEntity(event.getEntity().getClass().getCanonicalName())){
			Session session = event.getSession().getSessionFactory().openSession();
			
			Criteria criteria = session.createCriteria(DataLog.class);
			criteria.add(Restrictions.eq("opId", event.getEntity().toString()));
			criteria.setMaxResults(1);
			DataLog dataLog = (DataLog)criteria.uniqueResult();
			if(dataLog!=null){
				Date updateTime = new Date();
				long opDuration = updateTime.getTime() - dataLog.getCreateTime().getTime();
				dataLog.setOpDuration(opDuration);
				dataLog.setUpdateTime(updateTime);
				session.beginTransaction();
				session.update(dataLog);
				session.getTransaction().commit();
				session.flush();
				session.close();
			}
		}
		super.onPostLoad(event);
	}

}
