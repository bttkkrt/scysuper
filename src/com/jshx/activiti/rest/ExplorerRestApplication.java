/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-6-3           YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.rest;

import org.activiti.editor.rest.application.ModelerServicesInit;
import org.activiti.rest.api.DefaultResource;
import org.activiti.rest.application.ActivitiRestApplication;
import org.activiti.rest.filter.JsonpFilter;
import org.restlet.Restlet;
import org.restlet.routing.Router;

/**  
 * 注册REST路由
 * 
 * @author   YuWeitao
 * @version  创建时间：2013-6-3  10:44:01
 * 
 */
public class ExplorerRestApplication extends ActivitiRestApplication {

	public ExplorerRestApplication() {
		super();
	}
	/**
	* Creates a root Restlet that will receive all incoming calls.
	*/
	@Override
	public synchronized Restlet createInboundRoot() {
		Router router = new Router(getContext());
	    router.attachDefault(DefaultResource.class);
	    ModelerServicesInit.attachResources(router);
	    JsonpFilter jsonpFilter = new JsonpFilter(getContext());
	    jsonpFilter.setNext(router);
	    return jsonpFilter;
	}
}