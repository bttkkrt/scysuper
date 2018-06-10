package com.jshx.dsrcssbbl.dao.impl;

import java.util.List;
import java.sql.Blob;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import com.jshx.core.base.dao.impl.BaseDaoImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.dsrcssbbl.entity.PartyStateNote;
import com.jshx.dsrcssbbl.dao.PartyStateNoteDao;

@Component("partyStateNoteDao")
public class PartyStateNoteDaoImpl extends BaseDaoImpl implements PartyStateNoteDao
{
	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return this.findPageByHqlId("findPartyStateNoteByMap", paraMap, page);
	}
	
	/**
	 * 查询所有记录
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public List<PartyStateNote> findPartyStateNote(Map<String, Object> paraMap){
		return this.findListByHqlId("findPartyStateNoteByMap", paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public PartyStateNote getById(String id)
	{
		return (PartyStateNote)this.getObjectById(PartyStateNote.class, id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	public void save(PartyStateNote partyStateNote)
	{
		partyStateNote.setId(null);
		this.saveOrUpdateObject(partyStateNote);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	public void update(PartyStateNote partyStateNote)
	{
		this.saveOrUpdateObject(partyStateNote);
	}

	/**
	 * 物理删除信息
	 * @param ids 主键ID
	 */
	public void delete(String id)
	{
		this.removeObjectById(PartyStateNote.class, id);
	}

	/**
	 * 逻辑删除信息
	 * @param ids 主键ID
	 */
	public void deleteWithFlag(String id)
	{
		PartyStateNote partyStateNote = (PartyStateNote)this.getObjectById(PartyStateNote.class, id);
		partyStateNote.setDelFlag(1);
		this.saveObject(partyStateNote);
	}
}
