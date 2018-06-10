package com.jshx.dsrcssbbl.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dsrcssbbl.dao.PartyStateNoteDao;
import com.jshx.dsrcssbbl.entity.PartyStateNote;
import com.jshx.dsrcssbbl.service.PartyStateNoteService;

@Service("partyStateNoteService")
public class PartyStateNoteServiceImpl extends BaseServiceImpl implements PartyStateNoteService
{
	/**
	 * Dao类
	 */
	@Autowired() 
	@Qualifier("partyStateNoteDao")
	private PartyStateNoteDao partyStateNoteDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return partyStateNoteDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PartyStateNote getById(String id)
	{
		return partyStateNoteDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(PartyStateNote partyStateNote)
	{
		partyStateNoteDao.save(partyStateNote);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(PartyStateNote partyStateNote)
	{
		partyStateNoteDao.update(partyStateNote);
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
		List objects=partyStateNoteDao.findPartyStateNote(paraMap);
		
		partyStateNoteDao.removeAll(objects);
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
				    partyStateNoteDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<PartyStateNote> findPartyStateNote(Map<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return partyStateNoteDao.findPartyStateNote(paraMap);
	}
}
