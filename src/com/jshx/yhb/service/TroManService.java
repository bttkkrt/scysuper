package com.jshx.yhb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.yhb.entity.TjYhBean;
import com.jshx.yhb.entity.TroMan;

public interface TroManService extends BaseService
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
	public TroMan getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(TroMan model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(TroMan model);

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
	/**
	 * 保存隐患整改信息 
	 * lj
	 * 2015-11-07
	 * @param map
	 */
	public void saveRectInfo(Map map);
	/**
	 * 查询隐患整改信息列表
	 * lj
	 * 2015-11-07
	 * @param map
	 * @return
	 */

	public List<HashMap> queryRectInfosByMap(Map map);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTroMan(Map<String, Object> paraMap);
	
	/**
	 * 根据镇查询 企业隐患上报信息 
	 */
	public List<TjYhBean> getTjYhListFromQy(String sqlId,Map map);
	
	/**
	 * 针对企业上报隐患统计 
	 */
	public TjYhBean getTjYhDataFromQy(String sqlId,Map map);
	
	public Pagination findTjYhBeanByPage(Pagination page, Map<String, Object> paraMap);
	
	public List<EntBaseInfo> getYhsbqyByMap(Map map,int start,int limit);
	
	public int getYhsbqyTotalByMap(Map map);
	
	public List<EntBaseInfo> getYhwsbqyByMap(Map map,int start,int limit);
	
	public int getYhwsbqyTotalByMap(Map map);
}
