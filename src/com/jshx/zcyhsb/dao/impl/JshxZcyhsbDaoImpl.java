package com.jshx.zcyhsb.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;
import com.jshx.qyzcyhglb.entity.Qyzcyhglb;
import com.jshx.zcyhsb.dao.JshxZcyhsbDao;
import com.jshx.zcyhsb.entity.BzhBean;
import com.jshx.zcyhsb.entity.HyflDataBean;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.entity.TjYhBean;
import com.jshx.zcyhsb.entity.TypeBean;
import com.jshx.zcyhsb.entity.WhpBean;
import com.jshx.zcyhsb.entity.ZywhBean;

@Component("jshxZcyhsbDao")
public class JshxZcyhsbDaoImpl extends BaseDaoImpl implements JshxZcyhsbDao
{
	/**
	 * 新加用于ibatis查询 李军 2013-11-1
	 */
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
		return this.findPageByHqlId("findJshxZcyhsbByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<JshxZcyhsb> findJshxZcyhsb(Map<String, Object> paraMap){
		return this.findListByHqlId("findJshxZcyhsbByMap", paraMap);
	}
	@Override
	public List   findTjyhsb(Map<String, Object> paraMap){
		return sqlMapClientTemplate.queryForList("tjyhsb", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZcyhsb getById(String id)
	{
		return (JshxZcyhsb)this.getObjectById(JshxZcyhsb.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxZcyhsb jshxZcyhsb)
	{
		jshxZcyhsb.setId(null);
		//此处处理Blob对象（Blob对象不能直接insert）
		this.saveOrUpdateObject(jshxZcyhsb);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxZcyhsb jshxZcyhsb)
	{
		this.saveOrUpdateObject(jshxZcyhsb);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(JshxZcyhsb.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		JshxZcyhsb jshxZcyhsb = (JshxZcyhsb)this.getObjectById(JshxZcyhsb.class, id);
		jshxZcyhsb.setDelFlag(1);
		this.saveObject(jshxZcyhsb);
	}

	@Override
	public void deleteQyzcyhglbByMap(Map map) {
		// TODO Auto-generated method stub
		sqlMapClientTemplate.update("deleteQyzcyhglbByMap",map);
//		String id = map.get("yhid").toString();
//		this.removeObjectById(Qyzcyhglb.class, id);
	}
	
	@Override
	public TjYhBean getTjYhDataFromQy(Map map) {
		return (TjYhBean)sqlMapClientTemplate.queryForObject("query_tjYh_data",map);
	}

	@Override
	public BzhBean getBzhDataFromQy(Map map){
		return (BzhBean)sqlMapClientTemplate.queryForObject("query_bzh_data",map);
	}
	
	@Override
	public ZywhBean getZywhDataFromQy(Map map){
		return (ZywhBean)sqlMapClientTemplate.queryForObject("query_zywh_data",map);
	}
	@Override
	public WhpBean getWhpDataFromQy(Map map){
		return (WhpBean)sqlMapClientTemplate.queryForObject("query_whp_data",map);
	}
	
	@Override
	public List<TypeBean> getTypeDataFromQy(Map map) {
		return sqlMapClientTemplate.queryForList("query_type_data",map);
	}

	@Override
	public List<TypeBean> getYhTypeDataFromQy(Map map) {
		return sqlMapClientTemplate.queryForList("query_yhType_data",map);
	}

	@Override
	public List<TypeBean> getZlTypeData(Map map) {
		return sqlMapClientTemplate.queryForList("query_zlType_data",map);
	}

	@Override
	public List<TjYhBean> getTjYhListFromQy(Map map) {
		return sqlMapClientTemplate.queryForList("query_tjyh_list_data",map);
	}

	@Override
	public List<BzhBean> getBzhListFromQy(Map map) {
		return sqlMapClientTemplate.queryForList("query_bzh_list_data",map);
	}
	
	@Override
	public List<ZywhBean> getZywhListFromQy(Map map) {
		return sqlMapClientTemplate.queryForList("query_zywh_list_data",map);
	}
	@Override
	public List<WhpBean> getWhpListFromQy(Map map) {
		return sqlMapClientTemplate.queryForList("query_whp_list_data",map);
	}
	
	
	
	
	
	@Override
	public List<HyflDataBean> getHyflDataList(Map map) {
		return sqlMapClientTemplate.queryForList("query_hyfl_list_data",map);
	}

	@Override
	public HyflDataBean getHyflDataBean(Map map) {
		return (HyflDataBean)sqlMapClientTemplate.queryForObject("query_hyfl_bean_data",map);
	}
}
