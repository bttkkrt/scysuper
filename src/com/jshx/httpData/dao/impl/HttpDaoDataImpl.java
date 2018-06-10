package com.jshx.httpData.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.httpData.dao.HttpDataDao;
@Component("httpDataDao")
public class HttpDaoDataImpl extends BaseDaoImpl implements HttpDataDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate") 
	private SqlMapClientTemplate sqlMapClientTemplate;
	
	@Override
	public void updateGuid(CompanyBackUp companyBackUp){
		this.saveOrUpdateObject(companyBackUp);
//		this.sqlMapClientTemplate.update("updateGUIDcompanyBackup", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> queryCompanysByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_companys_httpData");
	}

	@Override
	public int queryCompanysCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_companys_count_httpData");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map> queryQyzhxxByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_Qyzhxx_httpData",map,s,l);
	}

	@Override
	public int queryQyzhxxCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_Qyzhxx_count_httpData");
	}

	@Override
	public List<Map> queryWgxxByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_Wgxx_httpData",map,s,l);
	}

	@Override
	public int queryWgxxCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_Wgxx_count_httpData");
	}
	
	@Override
	public List<Map> queryJzajbbmxxByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_Jzajbbmxx_httpData",map,s,l);
	}

	@Override
	public int queryJzajbbmxxCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_Jzajbbmxx_count_httpData");
	}

	@Override
	public List<Map> queryJzajbryxxByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_Jzajbryxx_httpData",map,s,l);
	}

	@Override
	public int queryJzajbryxxCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_Jzajbryxx_count_httpData");
	}

	@Override
	public List<Map> queryFwhqyshByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_Fwhqysh_httpData",map,s,l);
	}

	@Override
	public int queryFwhqyshCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_Fwhqysh_count_httpData");
	}

	@Override
	public List<Map> queryQygljgxxByMap(Map map, int s, int l) {
		return this.sqlMapClientTemplate.queryForList("query_qygljgxx_httpData",map,s,l);
	}

	@Override
	public int queryQygljgxxCountByMap(Map map) {
		return (Integer)this.sqlMapClientTemplate.queryForObject("query_qygljgxx_count_httpData");
	}

	@Override
	public List<Map> queryZcyhsbByMap(HashMap hashMap) {
		// TODO Auto-generated method stub
		return this.sqlMapClientTemplate.queryForList("query_zcyhsb_httpData");
	}

	
}
