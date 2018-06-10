package com.jshx.zywsjbxx.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.zywsjbxx.entity.Zybwhqytj;
import com.jshx.zywsjbxx.entity.Zywsglry;
import com.jshx.zywsjbxx.entity.Zywsjbxx;
import com.jshx.zywsjbxx.dao.ZywsjbxxDao;
/**
 * @author 高强
 *  createtime 13年11月20
 *  desc 职业卫生基本信息service层
 *
 */
@Component("zywsjbxxDao")
public class ZywsjbxxDaoImpl extends BaseDaoImpl implements ZywsjbxxDao
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
		return this.findPageByHqlId("findZywsjbxxByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findZywsjbxx(Map<String, Object> paraMap){
		return this.findListByHqlId("findZywsjbxxByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Zywsjbxx getById(String id)
	{
		return (Zywsjbxx)this.getObjectById(Zywsjbxx.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Zywsjbxx zywsjbxx)
	{
		zywsjbxx.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(zywsjbxx);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Zywsjbxx zywsjbxx)
	{
		this.saveOrUpdateObject(zywsjbxx);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Zywsjbxx.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Zywsjbxx zywsjbxx = (Zywsjbxx)this.getObjectById(Zywsjbxx.class, id);
		zywsjbxx.setDelFlag(1);
		this.saveObject(zywsjbxx);
	}

	@Override
	public List<Zywsglry> getZywsglrysById(String id) {
		// TODO Auto-generated method stub
		return this.findListByHql("from Zywsglry z where z.delFlag=0 and z.zywsjbxxid='"+id+"'");
	}

	@Override
	public void saveZywsglry(Zywsglry zywsglry) {
		zywsglry.setId(null);
           this.saveOrUpdateObject(zywsglry);		
	}

	@Override
	public Zywsjbxx getByCompanyId(String id) {
		// TODO Auto-generated method stub
	List<Zywsjbxx>	list= this.findListByHql("from Zywsjbxx z where z.delFlag=0 and z.qyid='"+id+"'");
		 if(list.size()>0)
		 return list.get(0);
		 else
			 return null;
	}

	@Override
	public Zywsglry getZywsglryById(String id) {
		// TODO Auto-generated method stub
		return (Zywsglry) this.getObjectById(Zywsglry.class, id);
	}

	@Override
	public void updateZywsglry(Zywsglry zywsglryOld) {
		// TODO Auto-generated method stub
		this.updateObject(zywsglryOld);
	}

	@Override
	public void deleteZywsglryDel(String ids) {
		// TODO Auto-generated method stub
		Zywsglry ywsglry = (Zywsglry)this.getObjectById(Zywsglry.class, ids);
		ywsglry.setDelFlag(1);
		this.saveObject(ywsglry);
	}

	@Override
	public Zybwhqytj getZywhqytjByMap(Map map) {
		// TODO Auto-generated method stub
		return (Zybwhqytj) sqlMapClientTemplate.queryForObject("getZywhqytjByMap",map);
	}

	@Override
	public List<Zybwhqytj> getZywhqytjListByMap(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getZywhqytjListByMap",map);
	}
}
