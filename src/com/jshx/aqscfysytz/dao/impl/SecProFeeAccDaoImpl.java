package com.jshx.aqscfysytz.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.aqscfysytz.dao.SecProFeeAccDao;
import com.jshx.aqscfysytz.entity.SecProFeeAcc;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;

@Component("secProFeeAccDao")
public class SecProFeeAccDaoImpl extends BaseDaoImpl implements SecProFeeAccDao
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
//		return this.findPageByHqlId("findSecProFeeAccByMap", paraMap, page);
		int start = page.getPageNumber();//页数
		int limit = page.getPageSize();//每页条数
		start = (start-1) * limit;//开始条数
		
		List  CompanyListBean=this.sqlMapClientTemplate.queryForList("findSecProFeeAccListByMap",paraMap,start,limit);
		//查询总条数
		List list = this.sqlMapClientTemplate.queryForList("findSecProFeeAccListByMap",paraMap);
		page.setList(CompanyListBean);
		page.setTotalCount(list.size());
		return page;
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<SecProFeeAcc> findSecProFeeAcc(Map<String, Object> paraMap){
		return this.sqlMapClientTemplate.queryForList("findSecProFeeAccListByMap",paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public SecProFeeAcc getById(String id)
	{
		return (SecProFeeAcc)this.getObjectById(SecProFeeAcc.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(SecProFeeAcc secProFeeAcc)
	{
		secProFeeAcc.setId(null);
		this.saveOrUpdateObject(secProFeeAcc);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(SecProFeeAcc secProFeeAcc)
	{
		this.saveOrUpdateObject(secProFeeAcc);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(SecProFeeAcc.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		SecProFeeAcc secProFeeAcc = (SecProFeeAcc)this.getObjectById(SecProFeeAcc.class, id);
		secProFeeAcc.setDelFlag(1);
		this.saveObject(secProFeeAcc);
	}
}
