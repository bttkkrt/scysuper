package com.jshx.zzbw.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zzbw.dao.JshxZzbwDao;
import com.jshx.zzbw.entity.JshxZzbw;
import com.jshx.zzbw.service.JshxZzbwService;

@Service("jshxZzbwService")
public class JshxZzbwServiceImpl extends BaseServiceImpl implements JshxZzbwService
{
	/**
	 * Dao类
	 */
	@Resource
	private JshxZzbwDao jshxZzbwDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return jshxZzbwDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 * author：陆婷
	 * 2013-08-20
	 */
	public JshxZzbw getById(String id)
	{
		return jshxZzbwDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	@Transactional
	public void save(JshxZzbw jshxZzbw)
	{
		jshxZzbwDao.save(jshxZzbw);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 * author：陆婷
	 * 2013-08-20
	 */
	@Transactional
	public void update(JshxZzbw jshxZzbw)
	{
		jshxZzbwDao.update(jshxZzbw);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID列表
	* author：陆婷
	 * 2013-08-20
	 */
	@Transactional
	public void delete(String[] ids)
	{
		List list=Arrays.asList(ids);
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ids", list);
		List objects=jshxZzbwDao.findJshxZzbw(paraMap);
		
		jshxZzbwDao.removeAll(objects);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID列表
	 * author：陆婷
	 * 2013-08-20
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
				    jshxZzbwDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<JshxZzbw> findJshxZzbw(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return jshxZzbwDao.findJshxZzbw(paraMap);
	}
}
