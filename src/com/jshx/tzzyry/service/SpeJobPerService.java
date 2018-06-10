package com.jshx.tzzyry.service;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;
import com.jshx.tzzyry.entity.SpeJobPer;

public interface SpeJobPerService extends BaseService
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
	public SpeJobPer getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SpeJobPer model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SpeJobPer model);

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
	 * 批量保存用户
	 * @param speJobPerList
	 */
	public List<String> saveList(List<SpeJobPer> speJobPerList) throws Exception;
	
	/**
	 * 判断特种作业证号是否注册过
	 * 
	 * @param loginId
	 * @return
	 * @throws BaseDaoException
	 * @return Boolean
	 * @throws
	 */
	public Boolean isRegSpeJobPer(String loginId);
	
	public List<SpeJobPer> findSpeJobPer(Map<String, Object> paraMap);
}
