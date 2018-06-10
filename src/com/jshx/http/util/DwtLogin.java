package com.jshx.http.util;

import java.util.HashMap;
import java.util.Map;

import com.jshx.core.utils.SpringContextHolder;
import com.jshx.qyjbxx.service.EntBaseInfoService;

import net.risesoft.plat.auth.Authenticator;
import net.risesoft.plat.auth.Identity;
import net.risesoft.plat.auth.exception.AuthenticateException;
import net.risesoft.plat.bean.User;

public class DwtLogin {
	public Map login(String flag,String loginId,String password)
	{
		Map map = new HashMap();
		// 实例化验证类对象
		Authenticator au = new Authenticator();
		try {
			/**
			 * Identity id为验证通过后返回的用户实例对象，其中包含用户相关信息。
			 * 如果验证失败的话则会抛出异常,通过异常对象能得到验证失败的原因
			 */

			Identity id = au.authenticate(flag, loginId, password);
			User user = id.getUser();//获取用户信息对象
			map.put("guid", user.getGuid());
			map.put("displayName", user.getDisplayName());
			map.put("duty", user.getProperty("title"));
			map.put("mobile", user.getProperty("mobile"));
			map.put("tel", user.getProperty("telephonenumber"));
			map.put("email", user.getProperty("mail"));
			map.put("code", "0");

		} catch (AuthenticateException e) {
			map.put("code", "1");
			map.put("message", e.getMessage());
			return map;
		}
		return map;
	}
}
