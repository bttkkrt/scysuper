package com.jshx.qyjbxx.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.entity.User;
import com.jshx.qyjbxx.entity.EntBaseInfo;


public interface EntBaseInfoDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<EntBaseInfo> findEntBaseInfo(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public EntBaseInfo getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(EntBaseInfo model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(EntBaseInfo model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id);
	
	/**
	 * 根据登录名、企业名称等条件查询企业信息
	 * @param map
	 * @author luting
	 * @return
	 */
	public EntBaseInfo findEntBaseInfoByMap(Map map);
	
	/**
	 * 获取企业列表
	 * @param map
	 * @return
	 * @author luting 2015-11-11
	 */ 
	public List<EntBaseInfo>  getEntBaseInfoListByUserAndType(Map map,int start,int limit);
	
	
	/**
	 * 获取企业列表条数
	 * @param map
	 * @return
	 * @author luting 2015-11-11
	 */ 
	public int  getEntBaseInfoListSizeByUserAndType(Map map);
	
	/**
	 * 更新企业用户信息
	 * @param map
	 * @return
	 * @author luting 2015-11-11
	 */
	public void updateUser(User user);
	
}
