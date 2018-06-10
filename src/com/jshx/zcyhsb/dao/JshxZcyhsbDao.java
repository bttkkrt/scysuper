package com.jshx.zcyhsb.dao;

import java.util.List;
import java.util.Map;

import com.jshx.core.base.dao.BaseDao;
import com.jshx.core.base.vo.Pagination;
import com.jshx.zcyhsb.entity.BzhBean;
import com.jshx.zcyhsb.entity.HyflDataBean;
import com.jshx.zcyhsb.entity.JshxZcyhsb;
import com.jshx.zcyhsb.entity.TjYhBean;
import com.jshx.zcyhsb.entity.TypeBean;
import com.jshx.zcyhsb.entity.WhpBean;
import com.jshx.zcyhsb.entity.ZywhBean;


public interface JshxZcyhsbDao extends BaseDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap);
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<JshxZcyhsb> findJshxZcyhsb(Map<String, Object> paraMap);

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public JshxZcyhsb getById(String id);

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(JshxZcyhsb model);

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(JshxZcyhsb model);

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id);

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id);
	
	public void deleteQyzcyhglbByMap(Map map);
	/**
	 * 针对企业上报隐患统计 2013-11-1 李军
	 */
	public TjYhBean getTjYhDataFromQy(Map map);
	/**
	 * 针对安全标准化达标自查自纠统计
	 */
	public BzhBean getBzhDataFromQy(Map map);
	
	/**
	 * 针对职业危害自查自纠统计
	 */
	public ZywhBean getZywhDataFromQy(Map map);
	
	/**
	 * 针对危险化学品企业自查自纠统计
	 */
	public WhpBean getWhpDataFromQy(Map map);
	
	
	
	
	
	/**
	 * 针对检查类别的统计 2013-11-4 李军
	 */
	public List<TypeBean> getTypeDataFromQy(Map map);
	/**
	 * 针对隐患类别的统计 2013-11-5 李军
	 */
	public List<TypeBean> getYhTypeDataFromQy(Map map);
	/**
	 * 针对隐患类别中类数据的查询 2013-11-6 李军
	 */
	public List<TypeBean> getZlTypeData(Map map);
	/**
	 * 根据镇查询 企业隐患上报信息 2013-11-11 李军
	 */
	public List<TjYhBean> getTjYhListFromQy(Map map);
	
	/**
	 * 根据镇查询 安全标准化达标企业上报信息 
	 */
	public List<BzhBean> getBzhListFromQy(Map map);
	
	/**
	 * 根据镇查询 职业危害企业上报信息 
	 */
	public List<ZywhBean> getZywhListFromQy(Map map);
	
	
	/**
	 * 根据镇查询 危险化学品企业上报信息 
	 */
	public List<WhpBean> getWhpListFromQy(Map map);
	
	/**
	 * 根据行业分类查询企业自查隐患信息 2013-11-11 李军
	 */
	public List<HyflDataBean> getHyflDataList(Map map);
	/**
	 * 根据行业分类查询企业自查隐患信息 2013-11-11 李军
	 */
	public HyflDataBean getHyflDataBean(Map map);

	/**
	 * @param paraMap
	 * @return
	 */
	public List findTjyhsb(Map<String, Object> paraMap);
}
