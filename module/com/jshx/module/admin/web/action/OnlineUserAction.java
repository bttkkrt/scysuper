/**
 * 
 */
package com.jshx.module.admin.web.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.service.OnlineUserService;

/**
 * @author f_cheng
 *
 */
public class OnlineUserAction extends BaseAction {
	@Autowired() 
	@Qualifier("onlineUserService")
	private OnlineUserService onlineUserService;
	
	private Pagination pagination;
	/**
	 * 返回在线用户列表页面
	 */
	public String list(){
		return LIST;
	}
	/**
	 * 分页查询在线用户信息，返回查询结果的json数据:<br>
	 * {"total":1,"rows":[{"id":"","loginTime":{"time":1399623113000},"loginType":"","user":{"displayName":"","id":"","loginId":""}}]}
	 * @return
	 */
	public void onlineUserList(){
		pagination = new Pagination(super.getRequest());
		pagination = onlineUserService.findOnlineUserByPage(pagination);
		
		StringBuffer data = new StringBuffer("{\n");
		data.append("  \"total\":").append(pagination.getTotalCount()).append(",\n");
		data.append("  \"rows\":\n");
		
		final String colNames = new String("id|user|displayName|loginId|loginTime|time|loginType|");
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter(){
			public boolean apply(Object source, String name, Object value) { 
				if(colNames.indexOf(name+"|")!=-1)
					return false;
				else
					return true;
			}
		});
		JSONArray json = JSONArray.fromObject(pagination.getListOfObject(), config);
		data.append(json.toString());
		data.append("  \n").append("}");
		
		try{
			this.getResponse().getWriter().println(data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
