package com.jshx.module.admin.security;

import org.springframework.beans.factory.FactoryBean;

/**
 * 动态获取权限配置信息
 * 
 * @author Chenjian
 * @version 2013/05/29
 *
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean{
	
	private String filterChainDefinitions;
	
	public static final String PREMISSION_STRING="perms[\"{0}\"]";

	@Override
	public Object getObject() throws Exception {
		//TODO
        return filterChainDefinitions;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getObjectType() {
		return this.getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public void setFilterChainDefinitions(String filterChainDefinitions) {
		this.filterChainDefinitions = filterChainDefinitions;
	}
}
