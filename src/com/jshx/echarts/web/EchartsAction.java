package com.jshx.echarts.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.echarts.entity.Echarts;
import com.jshx.echarts.service.EchartsService;
import com.jshx.echarts.utils.EchartsUtils;
import com.jshx.yhb.entity.TjYhBean;

public class EchartsAction extends BaseAction
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7720997271555062636L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Echarts echarts = new Echarts();

	/**
	 * 业务类
	 */
	@Autowired
	private EchartsService echartsService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("rawtypes")
	public void getSbqyCharts(){
        JSONObject jo = new JSONObject();
        try{
        	List<TjYhBean> list = echartsService.getTjYhListFromQy("query_tjyh_list_data", new HashMap());  //获取图表信息
        	jo = EchartsUtils.createData(list);
            this.getResponse().getWriter().print(jo.toString());
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
    }
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != echarts){
		    //设置查询条件，开发人员可以在此增加过滤条件
		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination = echartsService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != echarts)&&(null != echarts.getId()))
			echarts = echartsService.getById(echarts.getId());
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public Echarts getEcharts(){
		return this.echarts;
	}

	public void setEcharts(Echarts echarts){
		this.echarts = echarts;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }
       
    
}
