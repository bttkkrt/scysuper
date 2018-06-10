package com.jshx.module.mobile.version.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.entity.CodeValue;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.mobile.version.entity.Version;
import com.jshx.module.mobile.version.service.VersionService;

@SuppressWarnings("serial")
public class VersionAction extends BaseAction {

	private String ids;

	private Version version;

	@Autowired
	private VersionService versionService;
	@Autowired
	private CodeService codeService;

	// 修改新增标记，add为新增、mod为修改
	private String flag;

	private Pagination pagination;

	private File versionFile = null;
	
	private File ewmFile = null;

	private String versionNumber;

	private String versionPlatform;
	
	private String callback;

	public String initList() {
		return SUCCESS;
	}

	public String list() throws Exception {
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (pagination == null)
			pagination = new Pagination(this.getRequest());

		if (null != version) {
			// 设置查询条件，开发人员可以在此增加过滤条件
			if ((null != version.getVersionNumber()) && (0 < version.getVersionNumber().trim().length())) {
				paraMap.put("versionNumber", "%" + version.getVersionNumber().trim() + "%");
			}

			if ((null != version.getVersionPlatform()) && (0 < version.getVersionPlatform().trim().length())) {
				paraMap.put("versionPlatform", version.getVersionPlatform().trim());
			}

		}

		pagination = versionService.findByPage(pagination, paraMap);

		
		
		convObjectToJson(pagination, null);
		return null;
	}

	public String view() throws Exception {
		if ((null != version) && (null != version.getId()))
			version = versionService.getById(version.getId());

		return VIEW;
	}

	public String initEdit() throws Exception {
		view();
		return EDIT;
	}

	public String save() throws Exception {
		String destFilePath = "";

		if (versionFile != null) {
			final int BUFFER_SIZE = 16 * 1024;

			InputStream in = null;
			OutputStream out = null;
			
			InputStream in1 = null;
			OutputStream out1 = null;
			
			String fileType = "";
			try {
				if(version.getVersionPlatform()!=null&&version.getVersionPlatform().trim().length()>0){
					if(version.getVersionPlatform().equals("1"))
					{
						fileType = ".apk";
					}
					else
					{
						fileType = ".ipa";
					}
					String realFilePath = Struts2Util.getServletContext().getRealPath("/") + SysPropertiesUtil.getProperty("uploadVersion");
					File destFile = new File(realFilePath);
					if(!destFile.exists())
		        	{
		        		destFile.mkdir();
		        	}
		        	realFilePath += File.separator + "fsajj" + this.version.getVersionNumber() + fileType;
		        	File file = new File(realFilePath);
					in = new BufferedInputStream(new FileInputStream(versionFile), BUFFER_SIZE);
					out = new BufferedOutputStream(new FileOutputStream(file), BUFFER_SIZE);
		
					byte[] buffer = new byte[BUFFER_SIZE];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
		
					out.flush();
		
		        	destFilePath = "/apk/version/fsajj" + this.version.getVersionNumber() + fileType;
		        	
		        	//更新二维码图片
			  		StringBuffer destFName = new StringBuffer();
			  		String root = Struts2Util.getServletContext().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
			  		root = root.substring(0,root.indexOf("webapps")+8);
			  		root = root.replaceAll("\\\\", "/");
			  		root = root.replace("webapps","virtualdir/upload");
			  		destFName.append(root).append("wjxz/Android.png");
			  		File file1 = new File(destFName.toString());
					in1 = new BufferedInputStream(new FileInputStream(ewmFile), BUFFER_SIZE);
					out1 = new BufferedOutputStream(new FileOutputStream(file1), BUFFER_SIZE);
		
					byte[] buffer1 = new byte[BUFFER_SIZE];
					int len1 = 0;
					while ((len1 = in1.read(buffer1)) > 0) {
						out1.write(buffer1, 0, len1);
					}
		
					out1.flush();
			  		
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (in1 != null)
					in1.close();
				if (out1 != null)
					out1.close();
			} finally {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
				if (in1 != null)
					in1.close();
				if (out1 != null)
					out1.close();
			}
		}

		if ("add".equalsIgnoreCase(this.flag)) {
			version.setDeptId(this.getLoginUserDepartmentId());
			version.setDelFlag(0);
			version.setVersionDownload(destFilePath);
			versionService.save(version);
		} else {
			if (!"".equals(destFilePath))
				version.setVersionDownload(destFilePath);
				versionService.update(version);
		}
		return RELOAD;
	}
	
	public void download() {
		try {
			String filePath = new String();
			version = versionService.getById(version.getId());
			filePath = version.getVersionDownload();
			String path = Struts2Util.getServletContext().getRealPath("/");
			File fis = new File(path + "../../virtualdir" + filePath);
			if (fis.exists()) {
				InputStream in = new FileInputStream(fis);
				
				String browName=new String();
				browName = java.net.URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/") + 1), "UTF-8");
				String clientInfo = this.getRequest().getHeader("User-agent");
			    if(clientInfo != null && clientInfo.indexOf("MSIE") > 0 ){
				    if(clientInfo.indexOf("MSIE 6") > 0 || clientInfo.indexOf("MSIE 5") > 0){
				    	browName = new String(filePath.substring(filePath.lastIndexOf("/")+1).getBytes("GBK"),"ISO-8859-1");
				    }else{
				    	browName = java.net.URLEncoder.encode(filePath.substring(filePath.lastIndexOf("/")+1), "UTF-8");
				    }
			    }
                /*************************************/
				Struts2Util.getResponse().addHeader("Content-Disposition", "attachment;filename="+browName);
				OutputStream out = Struts2Util.getResponse().getOutputStream();
				try {
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) != -1) {
						out.write(buf, 0, len);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					in.close();
					out.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	public String delete() throws Exception {
		try {
			versionService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}

	public void checkUpdate() throws Exception {
		String result;
		
		if(versionNumber==null || "".equals(versionNumber)){
			result="error";
		}else if(versionPlatform==null || "".equals(versionPlatform)){
			result="error";
		}else{
			List<CodeValue> codeValues=codeService.getCodeValuesByCodeName("终端类型");
			String vpFlag="false";
			for(int i=0;i<codeValues.size();i++){
				if(versionPlatform.equals(codeValues.get(i).getItemValue())){
					vpFlag="true";
					break;
				}
			}
			if(vpFlag.equals("false")){
				result="error";
			}else{
				Version latestVersion=getLatestVersion(versionPlatform);
				if(latestVersion==null)
					result="false";
				else{
					int compare=compareVersionNumber(versionNumber,latestVersion.getVersionNumber());
					if(compare==-1)
						result="true";
					else
						result="false";
				}
				
			}
		}
		
		this.getResponse().getWriter().print(callback+"("+"{\"result\":"+result+"}"+")");
	}
	
	@SuppressWarnings("unchecked")
	public Version getLatestVersion(String versionPlatform){
		if(versionPlatform==null || "".equals(versionPlatform))
			return null;
		
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("versionPlatform", versionPlatform);
		List versions = versionService.findAllVersions(paraMap);
		
		if(versions==null || versions.size()==0)
			return null;
		else{
			int location=0;
			Version version=(Version)versions.get(0);
			String versionNumber=version.getVersionNumber();
			
			for(int i=1;i<versions.size();i++){
				version=(Version)versions.get(i);
				String compareNumber=version.getVersionNumber();
				int compare=compareVersionNumber(versionNumber,compareNumber);
				if(compare==-1){
					location=i;
					versionNumber=compareNumber;
				}
			}
			
			return (Version)versions.get(location);
		}
	}
	
	public void getLatestVersionUrl() throws IOException{
		Version version=getLatestVersion(versionPlatform);
		if(version!=null)
		    this.getResponse().getWriter().print(callback+"("+"{\"result\":\""+version.getVersionDownload()+"\"}"+")");
		else
			this.getResponse().getWriter().print(callback+"("+""+")");
	}
	
	public int compareVersionNumber(String versionNumber1, String versionNumber2){
		if(versionNumber1==null || "".equals(versionNumber1) || versionNumber2==null || "".equals(versionNumber2))
			return 2;
		String[] number1Arr=versionNumber1.split("\\.");
		String[] number2Arr=versionNumber2.split("\\.");
		int length,result;
		if(number1Arr.length<number2Arr.length){
			length=number1Arr.length;
			result=-1;
		}else if(number1Arr.length==number2Arr.length){
			length=number2Arr.length;
			result=0;
		}else{
			length=number2Arr.length;
			result=1;
		}
		for(int i=0;i<length;i++){
			if(Integer.parseInt(number1Arr[i])<Integer.parseInt(number2Arr[i]))
				return -1;
			if(Integer.parseInt(number1Arr[i])>Integer.parseInt(number2Arr[i]))
				return 1;
		}
		return result;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public File getVersionFile() {
		return versionFile;
	}

	public void setVersionFile(String versionFile) {
		if (versionFile != null && !"".equals(versionFile)) {
			try {
				this.versionFile = new File(versionFile);
			} catch (Exception e) {
				this.versionFile = null;
			}
		} else
			this.versionFile = null;
	}

	public String getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getVersionPlatform() {
		return versionPlatform;
	}

	public void setVersionPlatform(String versionPlatform) {
		this.versionPlatform = versionPlatform;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public void setVersionFile(File versionFile) {
		this.versionFile = versionFile;
	}

	public File getEwmFile() {
		return ewmFile;
	}

	public void setEwmFile(File ewmFile) {
		this.ewmFile = ewmFile;
	}
}
