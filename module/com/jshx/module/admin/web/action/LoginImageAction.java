package com.jshx.module.admin.web.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.module.admin.entity.LoginImage;
import com.jshx.module.admin.service.LoginImageService;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.Struts2Util;

public class LoginImageAction extends BaseAction{
	
	private static final long serialVersionUID = 4071737192011698126L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private LoginImage loginImage = new LoginImage();

	/**
	 * 业务类
	 */
	@Autowired
	private LoginImageService loginImageService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private File file;
	
	private String fileFileName;
	
	
	
	/**
	 * 分页查询登录页面背景，返回json数据:<br>
	 * {"total":1,"rows":[{"id":"","imageName":"","imageUrl":"","isUsing":0}]}
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != loginImage){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != loginImage.getImageName()) && (0 < loginImage.getImageName().trim().length())){
				paraMap.put("imageName", "%" + loginImage.getImageName().trim() + "%");
			}

			if (null != loginImage.getIsUsing()){
				paraMap.put("isUsing", loginImage.getIsUsing());
			}

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|imageName|imageUrl|isUsing|";
		if (filter != null && filter.length() > 1) {
			config.setJsonPropertyFilter(new PropertyFilter() {
				public boolean apply(Object source, String name, Object value) {
					if (filter.indexOf(name + "|") != -1)
						return false;
					else
						return true;
				}
			});
		}
		pagination = loginImageService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != loginImage)&&(null != loginImage.getId()))
			loginImage = loginImageService.getById(loginImage.getId());
		
		return VIEW;
	}

	/**
	 * 初始化修改信息
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 * 保存信息（包括新增和修改）
	 */
	public String save() throws Exception{
		String orignalFileName = this.fileFileName;
		String uploadFileName = null;
		Long nowTime = new Date().getTime();
		uploadFileName = String.valueOf(nowTime)+"."+orignalFileName;
		
		String uploadFilePath = Struts2Util.getServletContext().
			getRealPath("/webResources/loginImageUploadFiles/")+"\\"+uploadFileName;
		
		loginImage.setImageUrl("/webResources/loginImageUploadFiles/"+uploadFileName);
		
		File loginImageFile = new File(uploadFilePath);
		
		try{
			FileUtils.copyFile(file, loginImageFile);
			
			if ("add".equalsIgnoreCase(this.flag)){
				loginImage.setDeptId(this.getLoginUserDepartmentId());
				loginImage.setDelFlag(0);
				loginImageService.save(loginImage);
			}else{
				loginImageService.update(loginImage);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return RELOAD;
	}

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			loginImageService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	/**
	 * 启用图片
	 * @throws IOException 
	 */
	public void loginImageOn() throws IOException{
		try {
			this.loginImage = loginImageService.getById(this.loginImage.getId());
			if(null != loginImage){
				loginImage.setIsUsing(new Long(1));
				loginImageService.update(loginImage);
				this.getResponse().getWriter().println("{\"result\":true}");
			}
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
		}
	}
	
	/**
	 * 停止使用图片
	 * @throws IOException 
	 */
	public void loginImageOff() throws IOException{
		try {
			this.loginImage = loginImageService.getById(this.loginImage.getId());
			if(null != loginImage){
				loginImage.setIsUsing(new Long(0));
				loginImageService.update(loginImage);
				this.getResponse().getWriter().println("{\"result\":true}");
			}
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"result\":false}");
		}		
	}
	
	/**
	 * login.jsp使用，读取
	 * @throws Exception 
	 */
	public void getloginImageURL() throws Exception{
		try {
			if(pagination==null)
			    pagination = new Pagination(this.getRequest());
			Map<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("isUsing", new Long(1));
			pagination = loginImageService.findByPage(pagination, paraMap);	
			
			if(pagination.getListOfObject().size()>0){
				LoginImage loginImage = (LoginImage) pagination.getListOfObject().get(0);
				this.getResponse().getWriter().println("{\"status\":\"y\",\"info\":\""+loginImage.getImageUrl()+"\"}");	
			}else{
				this.getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"没有开启使用的图片！\"}");	
			}
		} catch (Exception e) {
			this.getResponse().getWriter().println("{\"status\":\"n\",\"info\":\"获取失败！\"}");		
		}

		
	}
	
	public String getIds(){
		return ids;
	}

	public void setIds(String ids){
		this.ids = ids;
	}

	public Pagination getPagination(){
		return pagination;
	}

	public void setPagination(Pagination pagination){
		this.pagination = pagination;
	}

	public LoginImage getLoginImage() {
		return loginImage;
	}

	public void setLoginImage(LoginImage loginImage) {
		this.loginImage = loginImage;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
