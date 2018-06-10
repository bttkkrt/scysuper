package com.jshx.zcyhsb.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zcyhsb.dao.JshxZcyhsbDao;
import com.jshx.zcyhsb.entity.BzhBean;
import com.jshx.zcyhsb.entity.HyflDataBean;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.entity.TjYhBean;
import com.jshx.zcyhsb.entity.TypeBean;
import com.jshx.zcyhsb.entity.WhpBean;
import com.jshx.zcyhsb.entity.ZywhBean;
import com.jshx.zcyhsb.service.JshxZcyhsbService;

@Service("jshxZcyhsbService")
public class JshxZcyhsbServiceImpl extends BaseServiceImpl implements JshxZcyhsbService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxZcyhsbDao jshxZcyhsbDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxZcyhsbDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZcyhsb getById(String id)
	{
		return jshxZcyhsbDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(JshxZcyhsb jshxZcyhsb)
	{
		jshxZcyhsbDao.save(jshxZcyhsb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(JshxZcyhsb jshxZcyhsb)
	{
		jshxZcyhsbDao.update(jshxZcyhsb);
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
		List objects=jshxZcyhsbDao.findJshxZcyhsb(paraMap);
		
		jshxZcyhsbDao.removeAll(objects);
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
				    jshxZcyhsbDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<JshxZcyhsb> findJshxZcyhsb(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return jshxZcyhsbDao.findJshxZcyhsb(paraMap);
	}

	@Transactional
	public void deleteQyzcyhglbByMap(Map map) {
		// TODO Auto-generated method stub
		jshxZcyhsbDao.deleteQyzcyhglbByMap(map);
	}
	@Override
	public TjYhBean getTjYhDataFromQy(Map map) {
		return jshxZcyhsbDao.getTjYhDataFromQy(map);
	}
	
	@Override
	 public BzhBean getBzhDataFromQy(Map map){
		return jshxZcyhsbDao.getBzhDataFromQy(map);
	}
	
	
	@Override
	 public ZywhBean getZywhDataFromQy(Map map){
		return jshxZcyhsbDao.getZywhDataFromQy(map);
	}
	
	
	@Override
	 public WhpBean getWhpDataFromQy(Map map){
		return jshxZcyhsbDao.getWhpDataFromQy(map);
	}

	@Override
	public List<TypeBean> getTypeDataFromQy(Map map) {
		return jshxZcyhsbDao.getTypeDataFromQy(map);
	}

	@Override
	public List<TypeBean> getYhTypeDataFromQy(Map map) {
		return jshxZcyhsbDao.getYhTypeDataFromQy(map);
	}

	@Override
	public List<TypeBean> getZlTypeData(Map map) {
		return jshxZcyhsbDao.getZlTypeData(map);
	}

	@Override
	public List<TjYhBean> getTjYhListFromQy(Map map) {
		return jshxZcyhsbDao.getTjYhListFromQy(map);
	}
    
	@Override
	public List<BzhBean> getBzhListFromQy(Map map) {
		return jshxZcyhsbDao.getBzhListFromQy(map);
	}
	
	@Override
	public List<ZywhBean> getZywhListFromQy(Map map) {
		return jshxZcyhsbDao.getZywhListFromQy(map);
	}
	@Override
	public List<WhpBean> getWhpListFromQy(Map map) {
		return jshxZcyhsbDao.getWhpListFromQy(map);
	}
	
	@Override
	public List<HyflDataBean> getHyflDataList(Map map) {
		return jshxZcyhsbDao.getHyflDataList(map);
	}

	@Override
	public HyflDataBean getHyflDataBean(Map map) {
		return jshxZcyhsbDao.getHyflDataBean(map);
	}

	@Override
	public List findTjyhsb(Map<String, Object> paraMap) {
		return jshxZcyhsbDao.findTjyhsb(paraMap);
	}
}
