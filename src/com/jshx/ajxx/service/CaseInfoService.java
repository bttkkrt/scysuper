package com.jshx.ajxx.service;

import java.util.List;
import java.util.Map;

import com.jshx.ajxx.entity.CaseCl;
import com.jshx.ajxx.entity.CaseInfo;
import com.jshx.ajxx.entity.CaseZj;
import com.jshx.core.base.service.BaseService;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.User;

public interface CaseInfoService extends BaseService
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
	public CaseInfo getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(CaseInfo model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(CaseInfo model);

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
	 * 根据用户角色获取用户id
	 * @param map
	 * @return
	 * @author luting 2015-10-19
	 */
	public String getUserByRole(Map map);
	
	/**
	 * 获取案件列表
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public List<CaseInfo>  getCaseInfoListByUserAndType(Map map,int start,int limit);
	
	
	/**
	 * 获取案件列表条数
	 * @param map
	 * @return
	 * @author luting 2015-11-5
	 */ 
	public int  getCaseInfoListSizeByUserAndType(Map map );
	
	/**
	 * 获取案件编号
	 * @param map
	 * @return
	 * @author luting 2015-11-25
	 */ 
	public int getMaxGlhNumByMap(Map map);
	
	/**
	 * 获取承办人列表
	 * @author luting 2016-2-17
	 */
	public List<User> queryCaseUserList(Map map);
	
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findCaseClByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 保存案件材料信息
	 * @author luting 2016-2-17
	 */
	public void saveCaseCl(CaseCl caseCl);
	
	/**
	 * 查询案件材料信息
	 * @author luting 2016-2-17
	 */
	public List<CaseCl> getCaseClList(Map map);
	
	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void updateCaseCl(CaseCl model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteCaseCl(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteCaseClWithFlag(String ids);
	
	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CaseCl getCaseClById(String id);
	
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findCaseZjByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 保存案件材料信息
	 * @author luting 2016-2-17
	 */
	public void saveCaseZj(CaseZj caseZj);
	
	/**
	 * 查询案件材料信息
	 * @author luting 2016-2-17
	 */
	public List<CaseZj> getCaseZjList(Map map);
	
	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void updateCaseZj(CaseZj model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteCaseZj(String[] ids);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	public void deleteCaseZjWithFlag(String ids);
	

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CaseZj getCaseZjById(String id);
	
	
	public String getGlhNumListByMap(Map map);
	
}
