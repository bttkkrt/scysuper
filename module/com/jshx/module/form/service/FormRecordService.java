package com.jshx.module.form.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.ZIPUtil;
import com.jshx.module.form.dao.impl.FormFieldDao;
import com.jshx.module.form.dao.impl.FormTableDao;
import com.jshx.module.form.dbddl.IDateBaseDDL;
import com.jshx.module.form.entity.FormField;
import com.jshx.module.form.entity.FormTable;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author caron
 * 
 */
@Service("formRecordService")
public class FormRecordService  {

	
	
}
