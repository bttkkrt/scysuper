package com.jshx.log.listener;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateEventWiring {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JsictPreLoadEventListener jsictPreLoadEventListener;
	
	@Autowired
	private JsictPostLoadEventListener jsictPostLoadEventListener;
	
	@Autowired
	private JsictPreInsertEventListener jsictPreInsertEventListener;
	
	@Autowired
	private JsictPostInsertEventListener jsictPostInsertEventListener;
	
	@Autowired
	private JsictPreDeleteEventListener jsictPreDeleteEventListener;
	
	@Autowired
	private JsictPostDeleteEventListener jsictPostDeleteEventListener;
	
	@Autowired
	private JsictPreUpdateEventListener jsictPreUpdateEventListener;
	
	@Autowired
	private JsictPostUpdateEventListener jsictPostUpdateEventListener;
	
	@PostConstruct
    public void registerListeners() {
		EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory).getServiceRegistry().getService(
		        EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(jsictPostInsertEventListener);
		registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(jsictPreInsertEventListener);
		registry.getEventListenerGroup(EventType.PRE_LOAD).appendListener(jsictPreLoadEventListener);
		registry.getEventListenerGroup(EventType.POST_LOAD).appendListener(jsictPostLoadEventListener);
		registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(jsictPreUpdateEventListener);
		registry.getEventListenerGroup(EventType.POST_UPDATE).appendListener(jsictPostUpdateEventListener);
		registry.getEventListenerGroup(EventType.POST_DELETE).appendListener(jsictPostDeleteEventListener);
		registry.getEventListenerGroup(EventType.PRE_DELETE).appendListener(jsictPreDeleteEventListener);
	}
}
