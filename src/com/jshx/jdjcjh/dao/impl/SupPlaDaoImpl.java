package com.jshx.jdjcjh.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdjcjh.dao.SupPlaDao;
import com.jshx.jdjcjh.entity.SupPla;
import com.jshx.jdjcjh.entity.SupPlaXccs;
import com.jshx.jdjcrw.entity.SupTasResult;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qywghjdgl.entity.ComGriMan;

@Component("supPlaDao")
public class SupPlaDaoImpl extends BaseDaoImpl implements SupPlaDao
{
	@Resource(name="sqlMapClientTemplate")
	SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findSupPlaByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSupPla(Map<String, Object> paraMap){
		return this.findListByHqlId("findSupPlaByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupPla getById(String id)
	{
		return (SupPla)this.getObjectById(SupPla.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SupPla supPla)
	{
		supPla.setId(null);
		this.saveOrUpdateObject(supPla);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SupPla supPla)
	{
		this.saveOrUpdateObject(supPla);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SupPla.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SupPla supPla = (SupPla)this.getObjectById(SupPla.class, id);
		supPla.setDelFlag(1);
		this.saveObject(supPla);
	}

	@Override
	public List<Map> getDeptsByMap(Map map) {
		return this.sqlMapClientTemplate.queryForList("query_dept_byMap_lj",map);
	}

	@Override
	public List<Map> getPersonsByMap(Map map) {
		return this.sqlMapClientTemplate.queryForList("query_person_byMap_lj",map);
	}
	@Override
	public List<Map> getPlanNameByMap(Map map) {
		return this.sqlMapClientTemplate.queryForList("query_planName_byMap",map);
	}
	@Override
	public List<Map> getXcxByMap(Map map) {
		return this.sqlMapClientTemplate.queryForList("query_xcx_byMap",map);
	}
	/**
	 * 获取检查企业id
	 */
	public String findCheckCompanyIds(Map map){
		String ids = (String)this.sqlMapClientTemplate.queryForObject("findCheckCompanyIds",map);
		return ids;
	}
	/**
	 * 获取该计划任务的id
	 */
	public List<Map> findTask(Map map) {
		return this.sqlMapClientTemplate.queryForList("findTask",map);
	}
	/**
	 * 查询该检查人员的网格id
	 */
	public List<String> findwglist(Map map){
		return this.sqlMapClientTemplate.queryForList("findwglist",map);
	}
	/**
	 * 查询所有企业
	 */
	public List<EntBaseInfo> findCompanyList(Map map){
		return this.sqlMapClientTemplate.queryForList("findCompanyList",map);
	}
	/**
	 * 查询周计划下的企业
	 */
	public List<Map> findCompanyList2(Map map){
		return this.sqlMapClientTemplate.queryForList("findCompanyList2",map);
	}
	/**
	 * 查询所有网格的list
	 */
	public List<ComGriMan> findWglistAll(Map map){
		return this.sqlMapClientTemplate.queryForList("findWglistAll",map);
	}

	@Override
	public SupPlaXccs getXccs(Map map) {
		// TODO Auto-generated method stub
		SupPlaXccs xccs = new SupPlaXccs();
		List<SupPlaXccs> list = this.findListByHqlId("getXccs", map);
		if(list.size() != 0)
		{
			xccs = list.get(0);
		}
		return xccs;
	}

	@Override
	public void saveXccs(SupPlaXccs xccs) {
		// TODO Auto-generated method stub
		xccs.setId(null);
		this.saveOrUpdateObject(xccs);
	}

	@Override
	public void updateXccs(SupPlaXccs xccs) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(xccs);
	}
	
	/**
	 * 保存巡查结果
	 * fq 2016-05-19
	 */
	public void saveSupTasResult(SupTasResult supTasResult){
		supTasResult.setId(null);
		this.saveOrUpdateObject(supTasResult);
	}
	
	public void updateSupTasResult(SupTasResult supTasResult){
		this.saveOrUpdateObject(supTasResult);
	}
	
	public List<SupTasResult> findSupTasResultByMap(Map<String, Object> paraMap){
		return this.sqlMapClientTemplate.queryForList("findSupTasResultByMap",paraMap);
	}
}
