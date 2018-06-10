package com.jshx.module.admin.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.LogoffLog;


public interface LogoffLogDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	/**
	 * 分页查询
	 * @param pagination 分页信息
	 * @param logoffLog 登出日志信息
	 * @param queryLogoffDateStart 查询开始时间
	 * @param queryLogoffDateEnd 查询结束时间
	 * @return 分页信息
	 */
	public Pagination findLogByPage(Pagination pagination, LogoffLog logoffLog, Date queryLogoffDateStart, Date queryLogoffDateEnd);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findLogoffLog(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public LogoffLog getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(LogoffLog model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(LogoffLog model);

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
}
