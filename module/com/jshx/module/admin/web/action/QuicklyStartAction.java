/**
 * Copyright 2011 hongxin 
 * Change Revision
 * ---------------------------------------------------------------
 * Date               Author            Remarks
 * 2011-3-14        Chenjian          create
 * ---------------------------------------------------------------
 */
package com.jshx.module.admin.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.module.admin.entity.Module;
import com.jshx.module.admin.entity.QuicklyStart;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.ModuleService;
import com.jshx.module.admin.service.QuicklyStartService;

/**
 * @author Chenjian
 * @version 创建时间：2011-3-14 下午02:07:43 类说明
 */
public class QuicklyStartAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired() 
	@Qualifier("quicklyStartService")
	private QuicklyStartService quicklyStartService;

	@Autowired() 
	@Qualifier("moduleService")
	private ModuleService moduleService;

	private String moduleIds;

	private List<QuicklyStart> startList;

	private String selModule;

	public String getSelModule() {
		return selModule;
	}

	public void setSelModule(String selModule) {
		this.selModule = selModule;
	}

	/**
	 * @return the moduleIds
	 */
	public String getModuleIds() {
		return moduleIds;
	}

	/**
	 * @param moduleIds
	 *            the moduleIds to set
	 */
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	/**
	 * @return the startList
	 */
	public List<QuicklyStart> getStartList() {
		return startList;
	}

	/**
	 * @param startList
	 *            the startList to set
	 */
	public void setStartList(List<QuicklyStart> startList) {
		this.startList = startList;
	}
	/**
	 * 返回快捷菜单设置页面
	 */
	public String listQuicklyStart() {
		User user = this.getLoginUser();
		startList = quicklyStartService.findQuicklyStart(user.getId());
		return LIST;
	}
	/**
	 * 保存快捷菜单设置
	 */
	public String saveQuicklyStart() {
		if (moduleIds != null) {
			String[] moduleIdArr = moduleIds.split(",");
			User user = this.getLoginUser();
			quicklyStartService.saveQuicklyStart(user.getId(), moduleIdArr);
		}
		return SUCCESS;
	}
	/**
	 * 快捷菜单树的节点查询，封装成树的节点信息返回：<br>
	 * 初始化时：[{"id":"","text":"","state":"","children":[{"id":"","text":"","state":""}]}]<br>
	 * 非初始化时：[{"id":"","text":"","state":""}]
	 */
	public void findModuleTreeForQuicklyStart() {
		if (null == selModule) {// 初始化时
			List<Map<String, Object>> treeData = new ArrayList<Map<String, Object>>();
			Map<String, Object> root = new HashMap<String, Object>();
			root.put("id", "M");
			root.put("text", "所有模块");
			root.put("iconCls", "icon-treeNodeParent");
			List<Module> moduleList = moduleService
					.findChildModulesByModuleCode(selModule);
			selModule = "M";
			List<Map<String, Object>> items = getChildrenForQuicklyStart(selModule);
			root.put("children", items);
			treeData.add(root);
			writerJSONArray(treeData);
		} else {
			Module module = moduleService.findModuleById(selModule);
			List<Map<String, Object>> items = getChildrenForQuicklyStart(module
					.getModuleCode());
			writerJSONArray(items);
		}

	}

	private List<Map<String, Object>> getChildrenForQuicklyStart(
			String modulecode) {
		User user = this.getLoginUser();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("userId", user.getId());
		paraMap.put("moduleCode", modulecode.trim() + "%");
		Integer length = modulecode.length() + 2;

		paraMap.put("length", length);
		List<Module> moduleList;
		if (user.getIsSuperAdmin() != null && user.getIsSuperAdmin()) {
			moduleList = moduleService.findModuleForAdmin(paraMap);
		} else {
			moduleList = moduleService.findModuleForLeft(paraMap);
		}

		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (Module m : moduleList) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", m.getId());
			item.put("text", m.getModuleName());
			if (moduleService.findChildModulesByModuleCode(m.getModuleCode())
					.size() > 0) {
				item.put("iconCls", "icon-treeNodeParent");
				item.put("children", getChildrenForQuicklyStart(m
						.getModuleCode()));
			} else {
				item.put("iconCls", "icon-module");
				Map<String, Object> paraMap2 = new HashMap<String, Object>();
				paraMap2.put("user", user);
				paraMap2.put("module", m);
				List list = moduleService.findQuicklyStart(paraMap2);
				if (list != null && list.size() > 0
						&& Integer.valueOf(list.get(0).toString()) > 0)
					item.put("checked", true);
				else
					item.put("checked", false);
			}
			
			items.add(item);
		}
		return items;
	}

}
