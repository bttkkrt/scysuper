package com.jshx.yhb.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.yhb.dao.TroManDao;
import com.jshx.yhb.entity.TjYhBean;
import com.jshx.yhb.entity.TroMan;
import com.jshx.yhb.service.TroManService;

@Service("troManService")
public class TroManServiceImpl extends BaseServiceImpl implements TroManService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("troManDao")
	private TroManDao troManDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return troManDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public TroMan getById(String id)
	{
		return troManDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(TroMan troMan)
	{
		troManDao.save(troMan);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(TroMan troMan)
	{
		troManDao.update(troMan);
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
		List objects=troManDao.findTroMan(paraMap);
		
		troManDao.removeAll(objects);
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
				    troManDao.deleteWithFlag(id);
			}
		}
	}

	@Transactional
	public void saveRectInfo(Map map) {
		troManDao.saveRectInfo(map);
	}

	@Override
	public List<HashMap> queryRectInfosByMap(Map map) {
		return troManDao.queryRectInfosByMap(map);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findTroMan(Map<String, Object> paraMap){
		return troManDao.findTroMan(paraMap);
	}
	@Override
	public List<TjYhBean> getTjYhListFromQy(String sqlId,Map map) {
		return troManDao.getTjYhListFromQy(sqlId,map);
	}
	@Override
	public TjYhBean getTjYhDataFromQy(String sqlId,Map map) {
		return troManDao.getTjYhDataFromQy(sqlId,map);
	}
	
	public Pagination findTjYhBeanByPage(Pagination page, Map<String, Object> paraMap){
		return troManDao.findTjYhBeanByPage(page, paraMap);
	}

	@Override
	public List<EntBaseInfo> getYhsbqyByMap(Map map,int start,int limit) {
		// TODO Auto-generated method stub
		return troManDao.getYhsbqyByMap(map,start,limit);
	}

	@Override
	public int getYhsbqyTotalByMap(Map map) {
		// TODO Auto-generated method stub
		return troManDao.getYhsbqyTotalByMap(map);
	}

	@Override
	public List<EntBaseInfo> getYhwsbqyByMap(Map map,int start,int limit) {
		// TODO Auto-generated method stub
		return troManDao.getYhwsbqyByMap(map,start,limit);
	}

	@Override
	public int getYhwsbqyTotalByMap(Map map) {
		// TODO Auto-generated method stub
		return troManDao.getYhwsbqyTotalByMap(map);
	}
}
