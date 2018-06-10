package com.jshx.log.listener;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.utils.ReflectionUtil;
import com.jshx.log.entity.DataLog;

public class JsictPostUpdateEventListener implements PostUpdateEventListener {

	private static final long serialVersionUID = 547789885321158858L;

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
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
			
			Object delFlag = ReflectionUtil.getFieldValue(entity, "delFlag");
			if(delFlag!=null){
				Integer flag = (Integer)delFlag;
				if(flag==0){
					StringBuffer log = new StringBuffer("修改实体类");
					log.append(event.getEntity().getClass().getCanonicalName()).append("的实例");
					log.append(entity.getId());
					dataLog.setOpLog(log.toString());
				}else if(flag==1){
					dataLog.setOpType("006");
					StringBuffer log = new StringBuffer("逻辑删除实体类");
					log.append(event.getEntity().getClass().getCanonicalName()).append("的实例");
					log.append(entity.getId());
					dataLog.setOpLog(log.toString());
				}
			}else{
				StringBuffer log = new StringBuffer("修改实体类");
				log.append(event.getEntity().getClass().getCanonicalName()).append("的实例");
				log.append(entity.getId());
				dataLog.setOpLog(log.toString());
			}
			
			
			session.beginTransaction();
			session.update(dataLog);
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
	}

}
