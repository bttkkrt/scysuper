package com.jshx.docReviewReceiver.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.service.impl.BaseServiceImpl;
import com.jshx.core.base.vo.Pagination;
import com.jshx.docReviewReceiver.dao.DocReviewReceiverDao;
import com.jshx.docReviewReceiver.entity.DocReviewReceiver;
import com.jshx.docReviewReceiver.service.DocReviewReceiverService;

@Service("docReviewReceiverService")
public class DocReviewReceiverServiceImpl extends BaseServiceImpl implements DocReviewReceiverService
{
	/**
	 * Dao类
	 */
	@Resource
	private DocReviewReceiverDao docReviewReceiverDao;

	/**
	 * 分页查询
	 * @param page 分页信息
	 * @param paraMap 查询条件信息
	 * @return 分页信息
	 */
	public Pagination findByPage(Pagination page, Map<String, Object> paraMap)
	{
		return docReviewReceiverDao.findByPage(page, paraMap);
	}

	/**
	 * 根据主键ID查询信息
	 * @param id 主键ID
	 * @return 主键ID对应的信息
	 */
	public DocReviewReceiver getById(String id)
	{
		return docReviewReceiverDao.getById(id);
	}

	/**
	 * 保存信息
	 * @param model 信息
	 */
	@Transactional
	public void save(DocReviewReceiver docReviewReceiver)
	{
		docReviewReceiverDao.save(docReviewReceiver);
	}

	/**
	 * 修改信息
	 * @param model 信息
	 */
	@Transactional
	public void update(DocReviewReceiver docReviewReceiver)
	{
		docReviewReceiverDao.update(docReviewReceiver);
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
		List objects=docReviewReceiverDao.findDocReviewReceiver(paraMap);
		
		docReviewReceiverDao.removeAll(objects);
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
				    docReviewReceiverDao.deleteWithFlag(id);
			}
		}
	}

	@Override
	public List<DocReviewReceiver> findDocReviewReceiver(Map<String, Object> paraMap) {
		 
		return docReviewReceiverDao.findDocReviewReceiver(paraMap);
	}
}
