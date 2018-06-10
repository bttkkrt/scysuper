package com.jshx.jdjcjh.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.jdjcjh.dao.SupPlaDao;
import com.jshx.jdjcjh.entity.SupPla;
import com.jshx.jdjcjh.entity.SupPlaXccs;
import com.jshx.jdjcjh.service.SupPlaService;
import com.jshx.jdjcrw.entity.SupTasResult;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qywghjdgl.entity.ComGriMan;

@Service("supPlaService")
public class SupPlaServiceImpl extends BaseServiceImpl implements SupPlaService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("supPlaDao")
	private SupPlaDao supPlaDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return supPlaDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SupPla getById(String id)
	{
		return supPlaDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(SupPla supPla)
	{
		supPlaDao.save(supPla);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(SupPla supPla)
	{
		supPlaDao.update(supPla);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=supPlaDao.findSupPla(paraMap);
		
		supPlaDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 */
	@Transactional
	public void deleteWithFlag(String ids)
	{
	    String[] idArray = ids.split("\\|");
		if(null != idArray)
		{
			for(String id : idArray)
			{
			    if(id!=null && !id.trim().equals(""))
				    supPlaDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Map> getDeptsByMap(Map map) {
		return supPlaDao.getDeptsByMap(map);
	}

	@Override
	public List<Map> getPersonsByMap(Map map) {
		return supPlaDao.getPersonsByMap(map);
	}
	@Override
	public List<Map> getPlanNameByMap(Map map) {
		return supPlaDao.getPlanNameByMap(map);
	}
	@Override
	public List<Map> getXcxByMap(Map map) {
		return supPlaDao.getXcxByMap(map);
	}
	/**
	 * 获取检查企业id
	 */
	public String findCheckCompanyIds(Map map){
		return supPlaDao.findCheckCompanyIds(map);
	}
	/**
	 * 获取该计划任务的id
	 */
	public List<Map> findTask(Map map){
		return supPlaDao.findTask(map);
	}
	/**
	 * 查询该检查人员的网格id
	 */
	public List<String> findwglist(Map map){
		return supPlaDao.findwglist(map);
	}
	/**
	 * 查询所有企业
	 */
	public List<EntBaseInfo> findCompanyList(Map map){
		return supPlaDao.findCompanyList(map);
	}
	/**
	 * 查询周计划的企业
	 */
	public List<Map> findCompanyList2(Map map){
		return supPlaDao.findCompanyList2(map);
	}
	/**
	 * 查询所有网格的list
	 */
	public List<ComGriMan> findWglistAll(Map map){
		return supPlaDao.findWglistAll(map);
	}

	@Override
	public SupPlaXccs getXccs(Map map) {
		// TODO Auto-generated method stub
		return supPlaDao.getXccs(map);
	}

	@Transactional
	public void saveXccs(SupPlaXccs xccs) {
		// TODO Auto-generated method stub
		supPlaDao.saveXccs(xccs);
	}

	@Transactional
	public void updateXccs(SupPlaXccs xccs) {
		// TODO Auto-generated method stub
		supPlaDao.updateXccs(xccs);
	}
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findSupPla(Map<String, Object> paraMap){
		return supPlaDao.findSupPla(paraMap);
	}
	
	/**
	 * 保存巡查结果
	 * fq 2016-05-19
	 */
	@Transactional
	public void saveSupTasResult(SupTasResult supTasResult){
		supPlaDao.saveSupTasResult(supTasResult);
	}
	
	@Transactional
	public void updateSupTasResult(SupTasResult supTasResult){
		supPlaDao.updateSupTasResult(supTasResult);
	}
	
	public List<SupTasResult> findSupTasResultByMap(Map<String, Object> paraMap){
		return supPlaDao.findSupTasResultByMap(paraMap);
	}
}
