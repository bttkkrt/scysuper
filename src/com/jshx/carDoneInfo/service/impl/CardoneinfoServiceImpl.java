/**
 * Class Name : CardoneinfoServiceImpl
 * Class Description：远程视频
 * Writer：陆婷
 * CreateTime：2013-12-10
 */
package com.jshx.carDoneInfo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.carDoneInfo.dao.CardoneinfoDao;
import com.jshx.carDoneInfo.entity.CarEquipment;
import com.jshx.carDoneInfo.entity.CarEquipmentState;
import com.jshx.carDoneInfo.service.CardoneinfoService;
import com.jshx.core.base.service.impl.BaseServiceImpl;

@Service("cardoneinfoService")
public class CardoneinfoServiceImpl extends BaseServiceImpl implements
		CardoneinfoService {
	/**
	 * Dao类
	 */
	@Resource
	private CardoneinfoDao cardoneinfoDao;

	/**
	 * Function Name: getEquipmentList Function Description：获取远程视频列表 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public List<CarEquipment> getEquipmentList(Map map) {
		// TODO Auto-generated method stub
		return cardoneinfoDao.getEquipmentList(map);
	}

	/**
	 * Function Name: saveEquipment Function Description：保存远程视频 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Transactional
	public void saveEquipment(CarEquipment carEquipment) {
		cardoneinfoDao.saveEquipment(carEquipment);
	}

	/**
	 * Function Name: deleteEquip Function Description：删除远程视频 Writer：陆婷
	 * CreateTime：2013-12-10
	 */

	@Transactional
	public void deleteEquip(Map map) {
		// TODO Auto-generated method stub
		cardoneinfoDao.deleteEquip(map);
	}

	/**
	 * Function Name: saveEquipmentState Function Description：保存密码修改状态 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Override
	public CarEquipmentState getEquipmentState(Map map) {
		// TODO Auto-generated method stub
		return cardoneinfoDao.getEquipmentState(map);
	}

	/**
	 * Function Name: CarEquipmentState Function Description：获取原始密码 Writer：陆婷
	 * CreateTime：2013-12-10
	 */
	@Transactional
	public void saveEquipmentState(CarEquipmentState carEquipmentState) {
		// TODO Auto-generated method stub
		cardoneinfoDao.saveEquipmentState(carEquipmentState);
	}

	@Override
	public int getEquipmentListSize(Map map) {
		// TODO Auto-generated method stub
		return cardoneinfoDao.getEquipmentListSize(map);
	}

	@Override
	public List<CarEquipment> getEquipmentLists(Map map) {
		// TODO Auto-generated method stub
		return cardoneinfoDao.getEquipmentLists(map);
	}
	
	public List<CarEquipment> getEquipmentListForShowname(Map map) {
		return cardoneinfoDao.getEquipmentListForShowname(map);
	}

}
