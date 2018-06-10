package com.jshx.module.form.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.core.utils.ZIPUtil;
import com.jshx.module.form.dao.impl.FormFieldDao;
import com.jshx.module.form.dao.impl.FormTableDao;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;

/**
 * 
 * @author caron
 * 
 */
@Service("formTableService")
public class FormTableService {

	String SQLKeyword = "数据库保留关键字";
	


	// @Autowired
	// private CodeValueManager codeValueManager;

	@Autowired
	private FormFieldDao formFieldDao;

	@Autowired
	private FormTableDao formTableDao;

	public FormTable get(String tableId) {
		FormTable formTable = (FormTable) formTableDao.getObjectById(
				FormTable.class, tableId.toString());
		return formTable;
	}

	@Transactional
	public void save(FormTable formTable) {
		formTableDao.saveObject(formTable);
	}
	
	
	@Transactional
	public void merge(FormTable formTable) {
		formTableDao.merge(formTable);
	}

	@Transactional
	public void delete(String tableId) {
		formTableDao.removeObjectById(FormTable.class, tableId.toString());
	}

	@Transactional
	public void saveOrUpdate(FormTable formTable) {
		formTableDao.saveOrUpdateObject(formTable);
	}

	
	
	
	
	public boolean isExist(String name) {
		try {
			// FormTable formTable =
			// (FormTable)formTableDao.getObjectById(FormTable.class,
			// tableId.toString());
			String physicalName = name;// formTable.getPhysicalName();
			if (physicalName != null) {
				return formTableDao.isExist(physicalName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * 取最大排序值
	 */
	public int getMaxSortSQ() {
		return formTableDao.getMaxSortSQ();
	}

	/**
	 * 列表更新表单的简单属性
	 * 
	 * @param tableId
	 * @param sortSQ
	 * @param pageType
	 * @param displayInMenu
	 */
	@Transactional
	public void updateStatusInfo(String[] tableId, Integer[] sortSQ,
			Integer[] pageType, Integer[] displayInMenu) {

		for (int i = 0; i < tableId.length; i++) {

			int thistableId = Integer.parseInt(tableId[i].split("----")[0]);
			int thistableIndex = Integer.parseInt(tableId[i].split("----")[1]);
			FormTable table = (FormTable) formTableDao.getObjectById(
					FormTable.class, tableId.toString());
			table.setSortSQ(sortSQ[thistableIndex]);
			formTableDao.saveObject(table);
		}
	}

	@Transactional
	public void createCopyTable(String tableName, String copyTableName) {

		try {
			formTableDao.createCopyTable(tableName, copyTableName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 根据条件分页查找表单
	 * 
	 * @param page
	 * @param paraMap
	 * @return
	 */
	public Pagination findTableByPage(Pagination page,
			Map<String, Object> paraMap) {
		return formTableDao.findTableByPage(page, paraMap);
	}

	/**
	 * 创建一个新表
	 * 
	 * @param TableName
	 * @return
	 */
	public boolean createTable(String TableName) throws Exception {
		return formTableDao.createTable(TableName);
	}

	public boolean dropTable(String tableName) throws Exception {
		return formTableDao.dropTable(tableName);
	}

	public boolean reCreateTable(String tableId) throws Exception {
		FormTable table = (FormTable) formTableDao.getObjectById(
				FormTable.class, tableId);
		String TableName = table.getPhysicalName();
		try {
			
			if (formTableDao.isExist(TableName)) {
				
					dropTable(TableName);
								
			}
			
		} catch (Exception e) {
			throw e;
		}
		//特别为mysql修改，由于mysql删除表后，schema中不能及时得到更新，导致误以为该表还是存在的，所以这里改用强制建表，建表前不判断存在
		formTableDao.createTableinForce(TableName);
			List<FormField> fFList = formFieldDao.getAllField(table.getId());
			if (fFList != null) {
				for (FormField formField : fFList) {
					//同上，改用强制建字段
					if(!formField.getFieldName().equals("ROW_ID"))
					formFieldDao.addColumninForce(TableName, formField);
				}
			}

		
		return true;
	}

	public Pagination getImportForm(Pagination tablePage,
			String modelphysicalName) {
		return formTableDao.getImportForm(tablePage, modelphysicalName);
	}

	public void downFilePath(String Path) {
		if (Path == null || Path.length() < 1) {
			return;
		}
		File srcdir = new File(Path);
		if (!srcdir.exists()) {
			System.out.println("Error while Finding Path:" + Path);
			return;
		}
		if (Path.endsWith("\\")) {
			Path = Path.substring(0, Path.length() - 1);

		}
		// 测试中文名
		String zipPathString = Path + ".zip";
		ZIPUtil.getInstance().createZipFile(Path, zipPathString);

		InputStream instream = null;
		try {

			String xmlFilePath = zipPathString;
			String xmlFileName = null;
			File xmlFile = null;

			if (xmlFilePath != null)
				xmlFileName = xmlFilePath.substring(xmlFilePath
						.lastIndexOf('\\') + 1, xmlFilePath.length());

			if (xmlFileName != null)
				xmlFile = new File(xmlFilePath);

			if ((xmlFile != null) && xmlFile.exists() && xmlFile.isFile()) {
				instream = new FileInputStream(xmlFile);

				Struts2Util.getResponse().reset();
				Struts2Util.getResponse().setContentType(
						"application/x-msdownload");
				Struts2Util.getResponse().setContentLength(
						(int) xmlFile.length());
				Struts2Util.getResponse().addHeader("content-disposition",
						"attachment; filename=\"" + xmlFileName + "\"");

				byte[] b = new byte[4096];
				int len;
				while ((len = instream.read(b)) > 0) {
					Struts2Util.getResponse().getOutputStream()
							.write(b, 0, len);
				}
				Struts2Util.getResponse().getOutputStream().flush();

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * 移动表单所在节点
	 */
	@Transactional
	public void move(String[] tableIDs, String categoryNum) {
		// System.out.println("move categoryNum = " + categoryNum);
		if ((null == tableIDs) || (0 == tableIDs.length)) {
			return;
		}
		StringBuffer hql = new StringBuffer(
				"delete FormInCategory where tableID = ");
		hql.append("'" + tableIDs[0] + "'");
		for (int i = 1; i < tableIDs.length; i++) {
			if(tableIDs[i]==null || tableIDs[i].trim().equals(""))
				continue;
			hql.append("or tableID = '").append(tableIDs[i] + "'");
		}
		// entityDao.getSession().createQuery(hql.toString()).executeUpdate();
		formTableDao.getSession().createQuery(hql.toString()).executeUpdate();
		// formTableDao.getSession().setFlushMode(FlushMode.AUTO)
		if ((null != categoryNum) && (false == "0".equals(categoryNum))
				&& (false == "00".equals(categoryNum))) {
			for (int i = 0; i < tableIDs.length; i++) {
				com.jshx.module.form.entity.FormInCategory formInCategory = new com.jshx.module.form.entity.FormInCategory();
				formInCategory.setTableID(tableIDs[i]);
				formInCategory.setCategoryNum(categoryNum);
				formTableDao.saveObject(formInCategory);
			}
		}
	}

	/**
	 * 
	 * 
	 * @param tableName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FormTable> queryFormTableByName(String tableName) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if (tableName != null)
			paraMap.put("tableName", "%" + tableName + "%");
		return formTableDao.findListByHqlId("queryFormTable", paraMap);
	}

	/**
	 * 检查传入的表名是否已经被平台管理，若未则传回“false”，若已管理，传递管理表中的id
	 * 
	 * @param phyName
	 * @return
	 */
	public String isTableExist(String phyName) {

		Object ft = formTableDao.getObjectByProperty(FormTable.class,
				"physicalName", phyName);
		if (ft == null) {
			return "false";
		} else {
			return ((FormTable) ft).getId();
		}

	}

	/**
	 * @throws UnsupportedEncodingException
	 * 
	 */
	public String copyAll2projectPath(String genPath)
			throws UnsupportedEncodingException {

		String projectPath = SysPropertiesUtil.getProperty("projectPath");
		if (projectPath == null) {
			return "请在system.properties文件中配置您的开发环境路径!";
		}

		projectPath = new String(projectPath.getBytes("ISO-8859-1"), "UTF-8");

		File srcfromFile;
		File pagefromFile;
		File srctoFile;
		File pagetoFile;
		try {
			//src目录拷贝
			srcfromFile = new File(genPath + "\\src");
			srctoFile = new File(projectPath + "\\src");
			//pages目录拷贝
			pagefromFile = new File(genPath + "\\pages");
			pagetoFile = new File(projectPath + "\\WebRoot\\WEB-INF\\pages");
		} catch (Exception e) {
			return "开发环境路径有误！请检查system.properties文件中的配置";
		}
		try {
			if (!copyDirectory(srcfromFile, srctoFile)) {
				return "开发环境src路径不存在！请检查配置";
			}

			if (!copyDirectory(pagefromFile, pagetoFile)) {
				return "开发环境page路径不存在！请检查配置";
			}
		} catch (Exception e) {
			return "拷贝失败，请检查权限问题";
		}
		return "代码已复制到开发环境目录！";

	}

	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");

	public boolean copyDirectory(File srcPath, File dstPath) throws Exception {
		if (srcPath.isDirectory()) {
			if (!dstPath.exists()) {
				dstPath.mkdir();
			}
			String files[] = srcPath.list();
			for (int i = 0; i < files.length; i++) {
				copyDirectory(new File(srcPath, files[i]), new File(dstPath,
						files[i]));
			}

		} else {
			if (!srcPath.exists()) {
				return false;
			} else {
				// 若目标文件存在，则在后缀后加入数字区分
				// 20120322 用时间可能会重名，使用UUID来区分
				if (dstPath.exists()) {
					dstPath = new File(dstPath.getAbsolutePath()
							+ UUID.randomUUID().toString());
				}
				InputStream in = new FileInputStream(srcPath);
				OutputStream out = new FileOutputStream(dstPath);// Transfer
																	// bytes
																	// from in
																	// to out
				byte[] buf = new byte[1024];
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				in.close();
				out.close();
			}
		}
		return true;
	}


}
