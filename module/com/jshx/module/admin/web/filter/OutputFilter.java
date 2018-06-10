package com.jshx.module.admin.web.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jshx.core.utils.JSONUtil;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.service.CodeService;

/**
 * 输出记录集
 * 
 * @author Chenjian
 * 
 */
public class OutputFilter implements Filter {

	@SuppressWarnings("unused")
	private FilterConfig config;

	private CodeService codeService;

	public void destroy() {
		this.config = null;
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;
		
		String outputType = request.getParameter("outputType");
		if (outputType == null) {
			chain.doFilter(req, response);
		} else {
			String isAllData = request.getParameter("isAllData");
			if(isAllData!=null && isAllData.toLowerCase().equals("true")){
				request.setAttribute("rows", Integer.MAX_VALUE);
			}
			if (outputType.equals("excel")) {
				ResponseWrapper wrapper = new ResponseWrapper(response);
				chain.doFilter(req, wrapper);
				try {
					outputExcel(request, response, wrapper);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (outputType.equals("pdf")) {
				ResponseWrapper wrapper = new ResponseWrapper(response);
				chain.doFilter(req, wrapper);
				outputPdf(request, response, wrapper);
			} else
				chain.doFilter(req, response);
		}
	}

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Map<String, String>[] findCodeText(String codeIds) {
		if (codeIds != null && !codeIds.trim().equals("")) {
			String[] codeIdArr = codeIds.split(",");
			Map[] codeMaps = new Map[codeIdArr.length];
			int i = 0;
			for(String codeId : codeIdArr){
				Map<String, String> codeMap = new HashMap<String, String>();
				codeService = (CodeService) SpringContextHolder.getBean("codeService");
				List<CodeValue> valueList = codeService.findCodeValueByCode(codeId);
				if (valueList != null && valueList.size() > 0)
					for (CodeValue value : valueList) {
						codeMap.put(value.getItemValue(), value.getItemText());
					}
				codeMaps[i] = codeMap;
				i++;
			}
			return codeMaps;
		}else
			return null;
	}

	/**
	 * 输出PDF
	 * 
	 * @param request
	 * @param response
	 * @param wrapper
	 * @throws IOException
	 */
	private void outputPdf(HttpServletRequest request, 
			HttpServletResponse response, ResponseWrapper wrapper)
			throws IOException {
		String jsonData = wrapper.getResult();
		int pos1 = jsonData.indexOf("[");
		int pos2 = jsonData.lastIndexOf("]");
		jsonData = jsonData.substring(pos1, pos2 + 1);
		String fileName = request.getParameter("fileName");
		OutputStream os = response.getOutputStream();

		Map<String, String> cols = new LinkedHashMap<String, String>();
		String colNameStr = request.getParameter("colNames");
		String[] colNames = colNameStr.split(",");
		for (String colName : colNames) {
			String[] col = colName.split("\\|");
			//cols.put(new String(col[0].getBytes("ISO8859-1"), "UTF-8"), col[1]);
			cols.put(col[0], col[1]);
		}
		response.setHeader("Content-disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
		response.setContentType("application/pdf;charset=utf-8");

		try {
			Map<String, String>[] codeMap = findCodeText(request.getParameter("codeId"));
			String codeFields = request.getParameter("codeFields");
			String[] codeFieldArr = null;
			if(codeFields!=null && !codeFields.trim().equals(""))
				codeFieldArr = codeFields.split(",");
			JSONUtil.changeJSONtoPdf(jsonData, cols, os, codeMap, codeFieldArr);
		} catch (Exception e) {
			throw new IOException(e);
		}

		os.close();
	}

	/**
	 * 输出Excel
	 * 
	 * @param request
	 * @param response
	 * @param wrapper
	 */
	private void outputExcel(HttpServletRequest request,
			HttpServletResponse response, ResponseWrapper wrapper)
			throws IOException {
		String jsonData = wrapper.getResult();
		int pos1 = jsonData.indexOf("[");
		int pos2 = jsonData.lastIndexOf("]");
		jsonData = jsonData.substring(pos1, pos2 + 1);
		String fileName = request.getParameter("fileName");
		OutputStream os = response.getOutputStream();

		Map<String, String> cols = new LinkedHashMap<String, String>();
		// String colNameStr = new
		// String(request.getParameter("colNames").getBytes("GBK"),"ISO8859-1");
		String colNameStr = request.getParameter("colNames");
		String[] colNames = colNameStr.split(",");
		for (String colName : colNames) {
			String[] col = colName.split("\\|");
			//cols.put(new String(col[0].getBytes("ISO8859-1"), "UTF-8"), col[1]);
			cols.put(col[0], col[1]);
		}
		Map<String, String>[] codeMap = findCodeText(request.getParameter("codeId"));
		String codeFields = request.getParameter("codeFields");
		String[] codeFieldArr = null;
		if(codeFields!=null && !codeFields.trim().equals(""))
			codeFieldArr = codeFields.split(",");
		HSSFWorkbook wb = JSONUtil.changeJSONtoEXcel(jsonData, cols, codeMap, codeFieldArr);

		response.setHeader("Content-disposition", "attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
		response.setContentType("application/vnd.ms-excel;charset=utf-8");

		wb.write(os);
		os.close();
	}

}
