package com.jshx.core.utils;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 读取system.properties的工具类
 * 
 * @author Chenjian
 *
 */
public class SysPropertiesUtil {
	private static SysPropertiesUtil oInstance = new SysPropertiesUtil();
	private static Properties oProperties;
	protected final Log logger = LogFactory.getLog(this.getClass());

	private SysPropertiesUtil() {
	}
	protected void loadProperties() {
		try {
			oProperties = new Properties();

			ClassLoader oClassLoader = Thread.currentThread()
					.getContextClassLoader();

			if (oClassLoader == null) {
				oClassLoader = oInstance.getClass().getClassLoader();
			}

			InputStream is = oClassLoader
					.getResourceAsStream("system.properties");

			if (is != null) {
				oProperties.load(is);
				is.close();
			} else {
				logger.error("SysPropertiesUtil can not load property files!");
			}

		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
	}

	/**  
     * 根据key读取属性值
     * @param key 取得其值的键
     * @return 属性值  
     */  
	public static String getProperty(String key) {
		if (oProperties == null) {
			oInstance.loadProperties();
		}
		return oProperties.getProperty(key);
	}

	/**  
     * 根据属性名返回整形的属性值
     */ 
	public static int getInt(String sPropertyName, int iDefaultValue) {
		try {
			String sProperty = getProperty(sPropertyName);
			return Integer.parseInt(sProperty);
		} catch (Exception e) {
			return iDefaultValue;
		}
	}

	/**  
     * 根据属性名返回字符串型属性值
     */
	public static String getString(String sPropertyName, String sDefaultValue) {
		try {
			return getProperty(sPropertyName);
		} catch (Exception e) {
			return sDefaultValue;
		}
	}

	/**
	 * 根据key得到属性键值Map
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static HashMap getProperties(String keyGroup) {
		HashMap hashmap = new HashMap();
		if (oProperties == null) {
			oInstance.loadProperties();
		}
		Enumeration enumeration = oProperties.keys();
		while (enumeration.hasMoreElements()) {
			String tempKey = (String) enumeration.nextElement();
			if (tempKey.startsWith(keyGroup)) {
				hashmap.put(tempKey, oProperties.get(tempKey));
			}
		}
		return hashmap;
	}

	/**  
     * 根据属性名返回布尔型的属性值
     */
	public static boolean getBoolean(String key, boolean bDefaultValue) {
		try {
			String s = getProperty(key);
			if (s != null) {
				return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("t");
			} else {
				return bDefaultValue;
			}
		} catch (Exception e) {
			return bDefaultValue;
		}
	}

//	@SuppressWarnings("unused")
//	public static void main(String[] args) {
//		getProperties("isExceptionLog");
//	}
}
