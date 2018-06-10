package com.jshx.whysjbqk.dao.impl;

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
import com.jshx.whysjbqk.entity.Whysjbqk;
import com.jshx.whysjbqk.entity.Whysjbqkglb;
import com.jshx.whysjbqk.dao.WhysjbqkDao;

@Component("whysjbqkDao")
public class WhysjbqkDaoImpl extends BaseDaoImpl implements WhysjbqkDao
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
		return this.findPageByHqlId("findWhysjbqkByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List findWhysjbqk(Map<String, Object> paraMap){
		return this.findListByHqlId("findWhysjbqkByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public Whysjbqk getById(String id)
	{
		return (Whysjbqk)this.getObjectById(Whysjbqk.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(Whysjbqk whysjbqk)
	{
		whysjbqk.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(whysjbqk);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(Whysjbqk whysjbqk)
	{
		this.saveOrUpdateObject(whysjbqk);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(Whysjbqk.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		Whysjbqk whysjbqk = (Whysjbqk)this.getObjectById(Whysjbqk.class, id);
		whysjbqk.setDelFlag(1);
		this.saveObject(whysjbqk);
	}

	@Override
	public void saveGlb(Whysjbqkglb model) {
		model.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(model);
	}

	@Override
	public List<Whysjbqkglb> getWhysjbqkglbList(Map map) {
		return sqlMapClientTemplate.queryForList("query_whysjbqkglb_list",map);
	}

	@Override
	public void delWhysjbqkglb(String id) {
		 try {
			sqlMapClientTemplate.delete("del_whysjbqkglb_byId",id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String getWhysjbqkIdByQyid(String id) {
		String mid="";
		 List<String> l = sqlMapClientTemplate.queryForList("query_whysjbqkglb_byqyId",id);
		if(l!=null&&!l.isEmpty()){
			mid = l.get(0);
		}
		return mid;
		
	}
}
