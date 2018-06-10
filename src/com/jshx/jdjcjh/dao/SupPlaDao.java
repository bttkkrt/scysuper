package com.jshx.jdjcjh.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdjcjh.entity.SupPla;
import com.jshx.jdjcjh.entity.SupPlaXccs;
import com.jshx.jdjcrw.entity.SupTasResult;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qywghjdgl.entity.ComGriMan;


public interface SupPlaDao extends BaseDao
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
	public List findSupPla(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupPla getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SupPla model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SupPla model);

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
	 * 获取部门列表信息
	 * @param map
	 * @return
	 */
	public List<Map> getDeptsByMap(Map map);
	/**
	 * 获取中队人员信息
	 * @param map
	 * @return
	 */
	public List<Map> getPersonsByMap(Map map);
	/**
	 * 获取计划名称
	 * @param map
	 * @return
	 */
	public List<Map> getPlanNameByMap(Map map);
	/**
	 * 获取检查项
	 * @param map
	 * @return
	 */
	public List<Map> getXcxByMap(Map map);
	/**
	 * 获取检查企业id
	 */
	public String findCheckCompanyIds(Map map);
	
	/**
	 * 获取该计划任务的id
	 */
	public List<Map> findTask(Map map);
	
	/**
	 * 查询该检查人员的网格id
	 */
	public List<String>findwglist(Map map);
	/**
	 * 查询所有企业
	 */
	public List<EntBaseInfo> findCompanyList(Map map);
	/**
	 * 查询周计划下的企业
	 */
	public List<Map> findCompanyList2(Map map);
	/**
	 * 查询所有网格的list
	 */
	public List<ComGriMan> findWglistAll(Map map);
	/**
	 * 保存巡查次数
	 */
	public void saveXccs(SupPlaXccs xccs);
	
	/**
	 * 更新巡查次数
	 */
	public void updateXccs(SupPlaXccs xccs);
	
	/**
	 * 查询巡查次数
	 */
	public SupPlaXccs getXccs(Map map);
	/**
	 * 保存巡查结果
	 * fq 2016-05-19
	 */
	public void saveSupTasResult(SupTasResult supTasResult);
	
	public void updateSupTasResult(SupTasResult supTasResult);
	
	public List<SupTasResult> findSupTasResultByMap(Map<String, Object> paraMap);
}
