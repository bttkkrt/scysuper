package com.jshx.gridManager.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.gridManager.dao.GridManagerDao;
import com.jshx.gridManager.entity.BaseBean;
import com.jshx.gridManager.entity.CompanyMapBean;
import com.jshx.gridManager.entity.GridManager;
import com.jshx.gridManager.entity.GridmanagerBean;

@Component("gridManagerDao")
public class GridManagerDaoImpl extends BaseDaoImpl implements GridManagerDao
{
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	@SuppressWarnings("unchecked")
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		try {
			String type =  (String)paraMap.get("type");
			//查询乡镇总数和企业数
			int start = page.getPageNumber();
			int limit = page.getPageSize();
			start = (start-1)*limit;
			List<BaseBean> lists  = new ArrayList<BaseBean>();
			int total = 0;
			if(type!=null&&"total".equals(type)){//查询所有乡镇
				lists = sqlMapClientTemplate.queryForList("get_towns",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_towns_count",paraMap);
				
			}else if(type!=null&&"fgld".equals(type)){//查询某个乡镇所有镇分管领导
				lists = sqlMapClientTemplate.queryForList("get_one_fgld",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_fgld_count",paraMap);
				
			}else if(type!=null&&"town".equals(type)){//查询某个乡镇所有镇安监办主任
				lists = sqlMapClientTemplate.queryForList("get_one_town",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_town_count",paraMap);
				
			}else if(type!=null&&"townDz".equals(type)){//查询某个镇的所有镇级中队队长
				//如果已经被选中 做个标示
				lists = sqlMapClientTemplate.queryForList("get_one_town_dzs",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_town_dzs_count",paraMap);
			}else if(type!=null&&"townUser".equals(type)){//查询某个镇的所有镇级安全责任人
				//如果已经被选中 做个标示
				lists = sqlMapClientTemplate.queryForList("get_one_town_users",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_town_users_count",paraMap);
			}else if(type!=null&&"countryUser".equals(type)){//查询某个镇的某个镇级安全责任人下所有村安全负责人
				//如果已经被选中 做个标示
				lists = sqlMapClientTemplate.queryForList("get_one_country_users",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_country_users_count",paraMap);
			}else if(type!=null&&"company".equals(type)){//查询有证企业
				//如果已经被选中 做个标示
				lists = sqlMapClientTemplate.queryForList("get_one_company_users",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_company_users_count",paraMap);
			}else if(type!=null&&"noCompany".equals(type)){//查询无证企业
				//如果已经被选中 做个标示
				lists = sqlMapClientTemplate.queryForList("get_one_no_company_users",paraMap,start,limit);
				total = (Integer)sqlMapClientTemplate.queryForObject("get_one_no_company_users_count",paraMap);
			}
			page.setList(lists);
			page.setTotalCount(total);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findGridManager(Map<String, Object> paraMap){
		return this.findListByHqlId("findGridManagerByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public GridManager getById(String id)
	{
		return (GridManager)this.getObjectById(GridManager.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(GridManager gridManager)
	{
		gridManager.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(gridManager);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(GridManager gridManager)
	{
		this.saveOrUpdateObject(gridManager);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(GridManager.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		GridManager gridManager = (GridManager)this.getObjectById(GridManager.class, id);
		gridManager.setDelFlag(1);
		this.saveObject(gridManager);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseBean> queryUsersByCode(Map map) {
		List<BaseBean> list = null;
		String flag = (String)map.get("flag");
		if(flag!=null&&!"3".equals(flag)&&!"4".equals(flag)){
			list = sqlMapClientTemplate.queryForList("get_town_persons_byCode",map);
		}else if("3".equals(flag)){//查询有证照企业的列表
			list = sqlMapClientTemplate.queryForList("get_companys_byCode",map);
		}else if("4".equals(flag)){//查询无证照企业的列表
			list = sqlMapClientTemplate.queryForList("get_no_companys_byCode",map);
		}
		
		return list;
	}

	@Override
	public void deleteBindUser(Map map) {
		sqlMapClientTemplate.delete("delete_bindUser_byUserId",map);
	}


	@Override
	public List<CompanyMapBean> getCompamyMapDotsByMap(Map map) {
		String type = (String)map.get("type");
		List<CompanyMapBean> beans = null;
		if("all".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_company_map_dots",map);
		}else if("fgld".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_fgld_company_map_dots",map);
		}else if("zr".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_zr_company_map_dots",map);
		}else if("towndz".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_towndz_company_map_dots",map);
		}else if("townaqy".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_townaqy_company_map_dots",map);
		}else if("countryaqy".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_countryaqy_company_map_dots",map);
		}else if("company".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_single_company_map_dots",map);
		}
		return beans; 
	}

	@Override
	public List<CompanyMapBean> getNoCompamyMapDotsByMap(Map map) {
		String type = (String)map.get("type");
		List<CompanyMapBean> beans = null;
		if("all".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_nocompany_map_dots",map);
		}else if("fgld".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_fgld_nocompany_map_dots",map);
		}else if("zr".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_zr_nocompany_map_dots",map);
		}else if("towndz".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_towndz_nocompany_map_dots",map);
		}else if("townaqy".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_townaqy_nocompany_map_dots",map);
		}else if("countryaqy".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_countryaqy_nocompany_map_dots",map);
		}else if("company".equals(type)){
			beans = sqlMapClientTemplate.queryForList("get_single_nocompany_map_dots",map);
		}
		return beans; 
	}

	@Override
	public List<GridmanagerBean> getCaqzzrListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getCaqzzrListByMap",map);
	}

	@Override
	public List<BaseBean> getDeptListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getDeptListByMap",map);
	}

	@Override
	public List<BaseBean> getZaqzzrListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZaqzzrListByMap",map);
	}

	@Override
	public List<BaseBean> getZzrListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZzrListByMap",map);
	}
	
	@Override
	public List<BaseBean> getZddzListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZddzListByMap",map);
	}

	@Override
	public Pagination findWzCompanyByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		try {
			int start = page.getPageNumber();
			int limit = page.getPageSize();
			start = (start-1)*limit;
			List<BaseBean> lists  = new ArrayList<BaseBean>();
			int total = 0;
			lists = sqlMapClientTemplate.queryForList("findWzCompanyByPage",paraMap,start,limit);
			total = (Integer)sqlMapClientTemplate.queryForObject("findWzCompanyByPageSize",paraMap);
			page.setList(lists);
			page.setTotalCount(total);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public Pagination findYzCompanyByPage(Pagination page,
			Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		try {
			//查询乡镇总数和企业数
			int start = page.getPageNumber();
			int limit = page.getPageSize();
			start = (start-1)*limit;
			List<BaseBean> lists  = new ArrayList<BaseBean>();
			int total = 0;
			lists = sqlMapClientTemplate.queryForList("findYzCompanyByPage",paraMap,start,limit);
			total = (Integer)sqlMapClientTemplate.queryForObject("findYzCompanyByPageSize",paraMap);
			page.setList(lists);
			page.setTotalCount(total);
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return page;
	}

	@Override
	public List<BaseBean> getFgldListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getFgldListByMap",map);
	}
}
