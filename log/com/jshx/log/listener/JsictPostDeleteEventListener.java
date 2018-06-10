package com.jshx.log.listener;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.log.entity.DataLog;

public class JsictPostDeleteEventListener implements PostDeleteEventListener {

	private static final long serialVersionUID = -2905479299011561255L;

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		if(EntitiesMonitor.checkEntity(event.getEntity().getClass().getCanonicalName())){
			Session session = event.getSession().getSessionFactory().openSession();
			
			Criteria criteria = session.createCriteria(DataLog.class);
			criteria.add(Restrictions.eq("opId", event.getEntity().toString()));
			criteria.setMaxResults(1);
			DataLog dataLog = (DataLog)criteria.uniqueResult();
			Date updateTime = new Date();
			long opDuration = updateTime.getTime() - dataLog.getCreateTime().getTime();
			dataLog.setOpDuration(opDuration);
			dataLog.setUpdateTime(updateTime);
			BaseModel entity = (BaseModel)event.getEntity();
			StringBuffer log = new StringBuffer("删除实体类");
			log.append(event.getEntity().getClass().getCanonicalName()).append("的实例");
			log.append(entity.getId());
			dataLog.setOpLog(log.toString());
			session.beginTransaction();
			session.update(dataLog);
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
	}

}
