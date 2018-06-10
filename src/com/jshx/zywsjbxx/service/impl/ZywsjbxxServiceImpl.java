package com.jshx.zywsjbxx.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zywsjbxx.dao.ZywsjbxxDao;
import com.jshx.zywsjbxx.entity.Zybwhqytj;
import com.jshx.zywsjbxx.entity.Zywsglry;
import com.jshx.zywsjbxx.entity.Zywsjbxx;
import com.jshx.zywsjbxx.service.ZywsjbxxService;
/**
 * @author 高强
 *  createtime 13年11月20
 *  desc 职业卫生基本信息service层
 *
 */
@Service("zywsjbxxService")
public class ZywsjbxxServiceImpl extends BaseServiceImpl implements ZywsjbxxService
{
	/**
	 * Dao类
	 */
	@Resource
	private ZywsjbxxDao zywsjbxxDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return zywsjbxxDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsjbxx getById(String id)
	{
		return zywsjbxxDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public String save(Zywsjbxx zywsjbxx)
	{
		zywsjbxxDao.save(zywsjbxx);
		return zywsjbxx.getId();
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(Zywsjbxx zywsjbxx)
	{
		zywsjbxxDao.update(zywsjbxx);
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
		List objects=zywsjbxxDao.findZywsjbxx(paraMap);
		
		zywsjbxxDao.removeAll(objects);
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
				    zywsjbxxDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<Zywsglry> getZywsglrysById(String id) {
		// TODO Auto-generated method stub
		return zywsjbxxDao.getZywsglrysById( id);
	}

	@Transactional
	public void saveZywsglry(Zywsglry zywsglry) {
		zywsjbxxDao.saveZywsglry( zywsglry);
		
	}

	@Override
	public Zywsjbxx getByCompanyId(String id) {
		// TODO Auto-generated method stub
		return zywsjbxxDao.getByCompanyId( id) ;
	}

	@Override
	public Zywsglry getZywsglryById(String id) {
		// TODO Auto-generated method stub
		return zywsjbxxDao.getZywsglryById(id) ;
	}

	@Transactional
	public void updateZywsglry(Zywsglry zywsglryOld) {
		// TODO Auto-generated method stub
		zywsjbxxDao.updateZywsglry( zywsglryOld);
	}

	@Transactional
	public void deleteZywsglryDel(String ids) {
		// TODO Auto-generated method stub
		zywsjbxxDao.deleteZywsglryDel( ids);
	}

	@Override
	public Zybwhqytj getZywhqytjByMap(Map map) {
		// TODO Auto-generated method stub
		return zywsjbxxDao.getZywhqytjByMap(map);
	}

	@Override
	public List<Zybwhqytj> getZywhqytjListByMap(Map map) {
		// TODO Auto-generated method stub
		return zywsjbxxDao.getZywhqytjListByMap(map);
	}
}
