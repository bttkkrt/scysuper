package com.jshx.commonTrouble.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.commonTrouble.dao.CommoTroubleDao;
import com.jshx.commonTrouble.entity.CommoTrouble;
import com.jshx.commonTrouble.entity.DeptDataBean;
import com.jshx.commonTrouble.entity.KsjcBean;
import com.jshx.commonTrouble.entity.QyDataBean;
import com.jshx.commonTrouble.entity.XzzywhBean;
import com.jshx.commonTrouble.entity.ZfwsData;
import com.jshx.commonTrouble.service.CommoTroubleService;
import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.Department;

@Service("commoTroubleService")
public class CommoTroubleServiceImpl extends BaseServiceImpl implements CommoTroubleService
{
	/**
	 * Dao类
	 */
	@Resource
	private CommoTroubleDao commoTroubleDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return commoTroubleDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public CommoTrouble getById(String id)
	{
		return commoTroubleDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(CommoTrouble commoTrouble)
	{
		commoTroubleDao.save(commoTrouble);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(CommoTrouble commoTrouble)
	{
		commoTroubleDao.update(commoTrouble);
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
		List objects=commoTroubleDao.findCommoTrouble(paraMap);
		
		commoTroubleDao.removeAll(objects);
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
				    commoTroubleDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<String> getDeptListByMap(Map map) {
		return commoTroubleDao.getDeptListByMap(map);
	}

	@Override
	public List<String> getRidsByType(Map map) {
		return commoTroubleDao.getRidsByType(map);
	}

	@Override
	public List<DeptDataBean> getDeptDataList(Map map) {
		return commoTroubleDao.getDeptDataList(map);
	}

	@Override
	public DeptDataBean getDeptDataBean(Map map) {
		return commoTroubleDao.getDeptDataBean(map);
	}

	@Override
	public ZfwsData getZfwsData(Map map) {
		return commoTroubleDao.getZfwsData(map);
	}

	@Override
	public DeptDataBean getXzDataBean(Map map) {
		return commoTroubleDao.getXzDataBean(map);
	}

	@Override
	public XzzywhBean getXzzywhDataBean(Map map) {
		return commoTroubleDao.getXzzywhDataBean(map);
	}
	
	
	
	@Override
	public List<DeptDataBean> getXzDataList(Map map) {
		return commoTroubleDao.getXzDataList(map);
	}

	@Override
	public List<QyDataBean> getCommonTroubleQyListByMap(Map map, int start,
			int limit) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getCommonTroubleQyListByMap(map, start, limit);
	}

	@Override
	public int getCommonTroubleQyListSizeByMap(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getCommonTroubleQyListSizeByMap(map);
	}

	@Override
	public List<QyDataBean> getQyDataList(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getQyDataList(map);
	}

	@Override
	public List<Department> getAllDeptListByMap(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getAllDeptListByMap(map);
	}

	@Override
	public List<DeptDataBean> getXzDataListByKeshi(Map map) {
		return commoTroubleDao.getXzDataListByKeshi(map);
	}
	
	@Override
	public List<XzzywhBean> getXzzywhDataList(Map map) {
		return commoTroubleDao.getXzzywhDataList(map);
	}

	@Override
	public KsjcBean getKsjcBean(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getKsjcBean(map);
	}

	@Override
	public List<KsjcBean> getKsjcListList(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getKsjcListList(map);
	}

	@Override
	public KsjcBean getXzjcBean(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getXzjcBean(map);
	}

	@Override
	public List<KsjcBean> getXzjcListList(Map map) {
		// TODO Auto-generated method stub
		return commoTroubleDao.getXzjcListList(map);
	}

	@Override
	public List findTjcomm(Map<String, Object> map) {
		return commoTroubleDao.findTjcomm(map);
	}

	@Override
	public List findList(Map<String, Object> map) {
		return commoTroubleDao.findListByHqlId("findCommoTroubleByMap", map);
	}
	
	
}
