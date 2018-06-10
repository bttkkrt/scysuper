package com.jshx.core.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Caron
 *
 */
public class DecodeFilter implements Filter {
	
	private DesEncrypt des = new DesEncrypt();

    /**
     * Default constructor. 
     */
    public DecodeFilter() {
        
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Map<String,String[]> m = new HashMap<String,String[]>(request.getParameterMap());
		//解密
		for(Map.Entry<String, String[]> entry: m.entrySet()){
			String key = entry.getKey();
			String[] values = entry.getValue();
			int i = 0;
			for(String value: values){
				value = des.decrypt(value);
				values[i] = value;
			}
			i++;
			m.put(key, values);
		}
        request = new ParameterRequestWrapper((HttpServletRequest)request, m); 
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
