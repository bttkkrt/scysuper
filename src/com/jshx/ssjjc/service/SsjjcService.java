package com.jshx.ssjjc.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.ssjjc.entity.Ssjjc;
import com.jshx.ssjjc.entity.SsjjcBean;
import com.jshx.ssjjc.entity.SsjjcGz;

public interface SsjjcService extends BaseService
{

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Ssjjc getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Ssjjc model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Ssjjc model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void delete(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteWithFlag(String ids);
	
	public List<SsjjcBean> findSsjjcBean(Map<String, Object> paraMap);
	
	public List<EntBaseInfo> getQyListByLxAndWg(Map map);
	
	public List<User> getRyListByLdOrZd(Map map);
	
	public void saveSsjjcGz(SsjjcGz ssjjcGz);
	
	public SsjjcGz getSsjjcGzByMap(Map map);
	
	public void updateSsjjcGz(SsjjcGz ssjjcGz);
	
	public void saveSsjjcBean(SsjjcBean ssjjcBean);
	
	public SsjjcBean getSsjjcBeanById(String id);
}
