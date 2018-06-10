package com.jshx.core.base.interceptor;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jshx.core.base.entity.BaseModel;
import com.jshx.core.utils.Constants;
import com.jshx.module.admin.entity.User;



/**
 * 自动监听保存、删除、修改方法，添加相应数据
 * 
 * @author john.zhang
 * 
 */
public class HibernateInterceptor extends EmptyInterceptor {
	
	private static final long serialVersionUID = 2504913633378515244L;
	
	protected final Logger logger = LoggerFactory.getLogger(HibernateInterceptor.class);;

	/**
	 * 保存entity时加入创建时间和创建者id
	 * 
	 * @param entity
	 * @param id
	 * @param state
	 * @param propertyNames
	 * @param types
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		try {
			// 保存数据记录时直接添加默认字段信息：BaseModel为XML配置的实体类；BaseEntity为注释配置的实体类
			if (entity instanceof BaseModel ) {	
				User user = null;
				if (ServletActionContext.getRequest() != null) {
					user=(User)ServletActionContext.getRequest().getSession().getAttribute(Constants.CURR_USER);
					
				} else {
					//PropertyUtils.setProperty(entity, "createUserID", "SYSTEM");
				}
				//2013-12-16 by chenmeng begin
				// 如果ServletActionContext报空指针，PropertyUtils.setProperty必须放在后面，否则执行两条语句
				for ( int i=0; i<propertyNames.length; i++ ) {
					 if("createUserID".equals(propertyNames[i]) && user!=null)
						 state[i] = user.getId();
					 else if("createTime".equals(propertyNames[i]))
						 state[i] = new Date();
				 }
			}
			//PropertyUtils.setProperty(entity, "createTime", new Date());
			 
		} catch (Exception e) {
			//logger.warn("DWR方式添加",e.fillInStackTrace());
			//e.printStackTrace();
		}
		
		return true;//super.onSave(entity, id, state, propertyNames, types);
		//2013-12-16 by chenmeng end
	}

	/**
	 * 更新entity时加入最近更新时间和最近更新者id
	 * 
	 * @param entity
	 * @param id
	 * @param currentState
	 * @param previousState
	 * @param propertyNames
	 * @param types
	 */
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		try {
			if (entity instanceof BaseModel  ) {
				User user = null;
				if (ServletActionContext.getRequest() != null) {
					user=(User)ServletActionContext.getRequest().getSession().getAttribute(Constants.CURR_USER);
					//PropertyUtils.setProperty(entity, "updateUserID", String.valueOf(user.getId()));
				} else {
					//PropertyUtils.setProperty(entity, "updateUserID", "SYSTEM");
				}
				// 同时更新currentState， 这样才能起到更新的效果
				for (int i = 0; i < propertyNames.length; i++) {
					if ("updateTime".equals(propertyNames[i])) {
						currentState[i] = new Date();
					}else if("updateUserID".equals(propertyNames[i])){
						currentState[i] = user.getId();
					}
				}
				//2013-12-16 by chenmeng begin
				// 如果ServletActionContext报空指针，PropertyUtils.setProperty必须放在后面，否则执行两条语句
				//PropertyUtils.setProperty(entity, "updateTime", new Date());
			}
		} catch (Exception e) {
			//logger.warn("DWR方式修改",e.fillInStackTrace());
			//e.printStackTrace();
		} 
		return true;//super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
		//2013-12-16 by chenmeng end
	}

}
