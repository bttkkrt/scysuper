/**
 * Copyright 2013 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2013-7-4           YuWeitao          create
 * ---------------------------------------------------------------
 */
package com.jshx.activiti.listener;

import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;

/**  
 * TaskListener测试类
 * 
 * @author   YuWeitao
 * @version  创建时间：2013-7-4 下午16:18:02 
 * 
 */
public class TestTaskListener implements TaskListener {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask delegateTask) {
		// TODO Auto-generated method stub
		System.out.println("++++++++++++++++++++++++++++lllllllistner+++");
		System.out.println(delegateTask.getName());
		System.out.println(delegateTask.getAssignee());
		Set<IdentityLink> candidates = delegateTask.getCandidates();
		for(IdentityLink identityLink : candidates){
			System.out.println("--------"+identityLink.getGroupId()+"----"+identityLink.getUserId());
		}
	}

}
