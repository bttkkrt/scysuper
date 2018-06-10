package com.jshx.log.listener;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.event.internal.DefaultPreLoadEventListener;
import org.hibernate.event.spi.PreLoadEvent;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.utils.Constants;
import com.jshx.log.entity.DataLog;
import com.jshx.module.admin.entity.User;

public class JsictPreLoadEventListener extends DefaultPreLoadEventListener {

	private static final long serialVersionUID = 4829612125952344814L;
	
	@Override
	public void onPreLoad(PreLoadEvent event){
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
			BaseModel entity = (BaseModel)event.getEntity();
			StringBuffer log = new StringBuffer("读取实体类");
			log.append(event.getEntity().getClass().getCanonicalName()).append("的实例");
			log.append(entity.getId());
			dataLog.setRecordNum((long)1);
			dataLog.setOpLog(log.toString());
			dataLog.setOpType("001");
			dataLog.setOpId(event.getEntity().toString());
			Session session = event.getSession().getSessionFactory().openSession();
			dataLog.setId(null);
			session.beginTransaction();
			session.save(dataLog);
			session.flush();
			session.getTransaction().commit();
			
			session.close();
		}		
		super.onPreLoad(event);
	}
}
