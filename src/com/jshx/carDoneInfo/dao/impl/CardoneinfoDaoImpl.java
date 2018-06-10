/**
 * Class Name : CardoneinfoDaoImpl
 * Class Description：远程视频
 * Writer：陆婷
 * CreateTime：2013-12-10
 */
package com.jshx.carDoneInfo.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import com.jshx.carDoneInfo.dao.CardoneinfoDao;
import com.jshx.carDoneInfo.entity.CarEquipment;
import com.jshx.carDoneInfo.entity.CarEquipmentState;
import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.ibatis.IctSqlMapClientDaoSupport;

@Component("cardoneinfoDao")
public class CardoneinfoDaoImpl extends BaseDaoImpl implements CardoneinfoDao {
	@Autowired
	@Qualifier("sqlMapClientTemplate")
	private SqlMapClientTemplate sqlMapClientTemplate;

	/**
	 * Function Name: getEquipmentList Function Description：获取远程视频列表 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public List<CarEquipment> getEquipmentList(Map map) {
		// TODO Auto-generated method stub
		return this.findListByHqlId("getCarEquipmentByName", map);
	}

	/**
	 * Function Name: saveEquipment Function Description：保存远程视频 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public void saveEquipment(CarEquipment carEquipment) {
		// TODO Auto-generated method stub
		carEquipment.setId(null);
		this.saveOrUpdateObject(carEquipment);
	}

	/**
	 * Function Name: deleteEquip Function Description：删除远程视频 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public void deleteEquip(Map map) {
		// TODO Auto-generated method stub
		List<CarEquipment> list = this.findListByHqlId("getCarEquipmentByName",
				map);
		for (CarEquipment c : list) {
			String id = c.getId();
			this.removeObjectById(CarEquipment.class, id);
		}

	}

	/**
	 * Function Name: saveEquipmentState Function Description：保存密码修改状态 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public CarEquipmentState getEquipmentState(Map map) {
		// TODO Auto-generated method stub
		CarEquipmentState a = null;
		List<CarEquipmentState> list = this.findListByHqlId(
				"getCarEquipmentStateByMap", map);
		if (list.size() != 0) {
			a = list.get(0);
		}
		return a;
	}

	/**
	 * Function Name: CarEquipmentState Function Description：获取原始密码 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public void saveEquipmentState(CarEquipmentState carEquipmentState) {
		// TODO Auto-generated method stub
		this.saveOrUpdateObject(carEquipmentState);
	}

	@Override
	public int getEquipmentListSize(Map map) {
		// TODO Auto-generated method stub
		return (Integer) sqlMapClientTemplate.queryForObject(
				"getEquipmentListSize", map);
	}

	@Override
	public List<CarEquipment> getEquipmentLists(Map map) {
		// TODO Auto-generated method stub
		return sqlMapClientTemplate.queryForList("getEquipmentLists", map,
				Integer.valueOf(map.get("start").toString()),
				Integer.valueOf(map.get("limit").toString()));
	}

	/**
	 * Function Name: getEquipmentList Function Description：获取远程视频列表 Writer：费谦
	 * CreateTime：2015-1-13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CarEquipment> getEquipmentListForShowname(Map map) {
		List<CarEquipment> carEquipments = sqlMapClientTemplate.queryForList("getEquipmentListsForShowname", map);
		return carEquipments;
	}
}
