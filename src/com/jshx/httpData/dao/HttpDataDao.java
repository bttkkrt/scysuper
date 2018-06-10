package com.jshx.httpData.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jshx.company.entity.CompanyBackUp;

public interface HttpDataDao {
	public void updateGuid(CompanyBackUp companyBackUp);
	public List<Map> queryCompanysByMap(Map map,int s,int l);
	public int queryCompanysCountByMap(Map map);
	
	public List<Map> queryQyzhxxByMap(Map map,int s,int l);
	public int queryQyzhxxCountByMap(Map map);
	
	public List<Map> queryWgxxByMap(Map map,int s,int l);
	public int queryWgxxCountByMap(Map map);
	
	public List<Map> queryJzajbbmxxByMap(Map map,int s,int l);
	public int queryJzajbbmxxCountByMap(Map map);
	
	public List<Map> queryJzajbryxxByMap(Map map,int s,int l);
	public int queryJzajbryxxCountByMap(Map map);
	
	public List<Map> queryFwhqyshByMap(Map map,int s,int l);
	public int queryFwhqyshCountByMap(Map map);
	
	public List<Map> queryQygljgxxByMap(Map map,int s,int l);
	public int queryQygljgxxCountByMap(Map map);
	public List<Map> queryZcyhsbByMap(HashMap hashMap);
}
