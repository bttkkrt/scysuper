package com.jshx.httpData.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jshx.company.entity.CompanyBackUp;
import com.jshx.httpData.dao.HttpDataDao;
import com.jshx.httpData.service.HttpDataService;
@Service("httpDataService")
public class HttpDataServiceImpl implements HttpDataService {
	@Resource
	private HttpDataDao httpDataDao;

	@Override
	public List<Map> queryCompanysByMap(Map map, int s, int l) {
		return httpDataDao.queryCompanysByMap(map, s, l);
	}

	@Override
	public int queryCompanysCountByMap(Map map) {
		return httpDataDao.queryCompanysCountByMap(map);
	}

	@Override
	public List<Map> queryQyzhxxByMap(Map map, int s, int l) {
		return httpDataDao.queryQyzhxxByMap(map, s, l);
	}

	@Override
	public int queryQyzhxxCountByMap(Map map) {
		return httpDataDao.queryQyzhxxCountByMap(map);
	}

	@Override
	public List<Map> queryWgxxByMap(Map map, int s, int l) {
		return httpDataDao.queryWgxxByMap(map, s, l);
	}

	@Override
	public int queryWgxxCountByMap(Map map) {
		return httpDataDao.queryWgxxCountByMap(map);
	}
	
	@Override
	public List<Map> queryJzajbbmxxByMap(Map map, int s, int l) {
		return httpDataDao.queryJzajbbmxxByMap(map, s, l);
	}

	@Override
	public int queryJzajbbmxxCountByMap(Map map) {
		return httpDataDao.queryJzajbbmxxCountByMap(map);
	}

	@Override
	public List<Map> queryJzajbryxxByMap(Map map, int s, int l) {
		return httpDataDao.queryJzajbryxxByMap(map, s, l);
	}

	@Override
	public int queryJzajbryxxCountByMap(Map map) {
		return httpDataDao.queryJzajbryxxCountByMap(map);
	}

	@Override
	public List<Map> queryFwhqyshByMap(Map map, int s, int l) {
		return httpDataDao.queryFwhqyshByMap(map, s, l);
	}

	@Override
	public int queryFwhqyshCountByMap(Map map) {
		return httpDataDao.queryFwhqyshCountByMap(map);
	}

	@Override
	public List<Map> queryQygljgxxByMap(Map map, int s, int l) {
		return httpDataDao.queryQygljgxxByMap(map, s, l);
	}

	@Override
	public int queryQygljgxxCountByMap(Map map) {
		return httpDataDao.queryQygljgxxCountByMap(map);
	}

	@Override
	public void updateGuid(CompanyBackUp companyBackUp) {
		httpDataDao.updateGuid(companyBackUp);
	}

	@Override
	public List<Map> queryZcyhsbByMap(HashMap hashMap) {
		return httpDataDao.queryZcyhsbByMap(hashMap);
	}

	
}
