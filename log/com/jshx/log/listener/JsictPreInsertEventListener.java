package com.jshx.log.listener;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import com.jshx.core.utils.Constants;
import com.jshx.log.entity.DataLog;
import com.jshx.module.admin.entity.User;

public class JsictPreInsertEventListener implements PreInsertEventListener {

	private static final long serialVersionUID = -7972472689784596126L;

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		if(EntitiesMonitor.checkEntity(event.getEntity().getClass().getCanonicalName())){
			DataLog dataLog = new DataLog();
			try{
				if (ServletActionContext.getRequest() != null) {
					User user = (User)ServletActionContext.getRequest().getSession().getAttribute(Constants.CURR_USER);
					dataLog.setCreateUserID(user.getId());
				}
			}catch(Exception e){
				
			}
			dataLog.setCreateTime(new Date());
			dataLog.setEntityName(event.getEntity().getClass().getCanonicalName());
			dataLog.setDelFlag(0);
			dataLog.setRecordNum((long)1);
			dataLog.setOpType("002");
			dataLog.setOpId(event.getEntity().toString());
			Session session = event.getSession().getSessionFactory().openSession();
			session.beginTransaction();
			session.save(dataLog);
			session.getTransaction().commit();
			session.flush();
			session.close();
		}
		return false;
	}
	
	

}
