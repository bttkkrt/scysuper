package com.jshx.zywsqk.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zywsqk.dao.ZywsqkDao;
import com.jshx.zywsqk.entity.Zywsqk;
import com.jshx.zywsqk.entity.ZywsqkAll;

@Component("zywsqkDao")
public class ZywsqkDaoImpl extends BaseDaoImpl implements ZywsqkDao
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
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findZywsqkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZywsqk(Map<String, Object> paraMap){
		return this.findListByHqlId("findZywsqkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsqk getById(String id)
	{
		return (Zywsqk)this.getObjectById(Zywsqk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsqk zywsqk)
	{
		zywsqk.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zywsqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsqk zywsqk)
	{
		this.saveOrUpdateObject(zywsqk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zywsqk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zywsqk zywsqk = (Zywsqk)this.getObjectById(Zywsqk.class, id);
		zywsqk.setDelFlag(1);
		this.saveObject(zywsqk);
	}

	@Override
	public void deleteZywhglbByMap(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.delete("deleteZywhglbByMap",map);
	}

	@Override
	public List<ZywsqkAll> getZywsqkAllListByMap(Map map) {
		// TODO Auto-generated method stub
		List<ZywsqkAll> list = new ArrayList<ZywsqkAll>();
		String dclx = (String) map.get("dclx");
		if("0".equals(dclx))
		{
			map.put("codeId", "4028e56c4014f290014014f981510003");
		}
		else if("1".equals(dclx))
		{
			map.put("codeId", "402880484076bce30140a04236590a02");
		}
		else if("2".equals(dclx))
		{
			map.put("codeId", "4028e56c3ff0d189013ff0feee650023");
		}
		else if("3".equals(dclx))
		{
			map.put("codeId", "402880484076bce30140a04025e509f7");
		}
		return sqlMapClientTemplate.queryForList("getZywsqkAllListByMap",map);
	}

	@Override
	public ZywsqkAll getZywsqkAllByMap(Map map) {
		// TODO Auto-generated method stub
		return (ZywsqkAll) sqlMapClientTemplate.queryForObject("getZywsqkAllByMap",map);
	}
	
}
