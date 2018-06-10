package com.wzxx.sytp.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.exception.BasalException;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.module.admin.service.UserService;
import com.wzxx.sytp.entity.Sytp;
import com.wzxx.sytp.service.SytpService;

public class SytpAction extends BaseAction
{
	private static final long serialVersionUID = 3967636502860318998L;

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;
	
	private String message;

	/**
	 * 实体类
	 */
	private Sytp sytp = new Sytp();
	
	/**
	 * 业务类
	 */
	@Autowired
	private SytpService sytpService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<File> upload;
	private List<String> uploadFileName;
	private List<String> uploadContentType;
	
	/**
	 * 执行查询的方法，返回json数据<br>
	 * json包含的属性："infoTitle|infoType|topFlag|expireFlag|delFlag|user|user.displayName|dept|dept.deptName|publicDate|time|id|"
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != sytp){
		    //设置查询条件，开发人员可以在此增加过滤条件
			if ((null != sytp.getInfoTitle()) && (0 < sytp.getInfoTitle().trim().length())){
				paraMap.put("infoTitle", "%" + sytp.getInfoTitle().trim() + "%");
			}

		}
		
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "infoTitle|id|";
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
		
		pagination = sytpService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
		
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != sytp)&&(null != sytp.getId()))
		{
			sytp = sytpService.getById(sytp.getId());
		}
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
		String name = uploadFileName.get(0);
		name = name.toLowerCase();
		if(name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif")  || name.endsWith(".bmp") || name.endsWith(".tiff")  || name.endsWith(".jpeg")  || name.endsWith(".tga"))
		{
			try {
				FileOutputStream fos = null;
				BufferedInputStream bis = null;
				File outfile = null;
				File outdir = null;
				byte[] bs = new byte[1024];
				String imgName="";
				StringBuffer destFName = new StringBuffer();
				String root = this.getRequest().getRealPath("/"); // 系统根目录F:\tomcat06\webapps\ajj\
				root = root.substring(0,root.indexOf("webapps")+8);
				root = root.replaceAll("\\\\", "/");
				root = root.replace("webapps","virtualdir/upload");
				destFName.append(root).append("wztp/");
				outdir = new File(destFName.toString());
				if(upload!= null && !upload.isEmpty()){//获取附件文件 进行判断是否存在
				    imgName =getDatedFName(uploadFileName.get(0));
					outfile = new File(destFName+imgName);
					InputStream stream  = new FileInputStream(upload.get(0));
					bis = new BufferedInputStream(stream);
					if (!outdir.exists())
						outdir.mkdirs();
					if (!outfile.exists())
						outfile.createNewFile();

					fos = new FileOutputStream(outfile);
					int i;
					while ((i = bis.read(bs)) != -1) {
						fos.write(bs, 0, i);
					}
				}
				String url = SysPropertiesUtil.getProperty("httpurl");
				sytp.setFilePath(url+"/upload/wztp/"+imgName);
				if ("add".equalsIgnoreCase(this.flag)){
					Date d = new Date();
					String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d);
					sytp.setDeptId(this.getLoginUserDepartment().getId());
					sytp.setDelFlag("0");
					sytp.setCreateTime(d);
					sytp.setCreateUserID(getLoginUser().getId());
					sytpService.save(sytp);
				}else{
					//更新公告
				    sytpService.update(sytp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message  = "上传失败！";
			}
		}
		else
		{
			message  = "文件格式错误！";
		}
		return RELOAD;
	}
	
	/**
	 *  自定义上传附件的名称 以时间格式来处理
	 * @param fname
	 * @author luting 2015-10-13
	 */
	public String getDatedFName(String fname) {
		StringBuffer result = new StringBuffer();

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String dateSfx = df.format(new Date());

		int idx = fname.lastIndexOf('.');
		if (idx != -1) {
			// result.append(fname.substring(0, idx));
			result.append(dateSfx);
			result.append(fname.substring(idx));
		} else {
			result.append(fname);
			result.append(dateSfx);
		}

		return  result.toString();
	}

	/**
	 * 删除信息,返回json{result:true/false}
	 */
	public String delete() throws Exception{
	    try{
			sytpService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 获取文件后缀字符串
	 */
	public String getFileSuffix(String fileName) {
		String filesuffix = null;
		StringTokenizer fx = new StringTokenizer(fileName, ". ");
		while (fx.hasMoreTokens()) {
			filesuffix = fx.nextToken();
		}
		return filesuffix;
	}
	
  	public boolean compare(String s1,String s2){
  		boolean a=false;
  		if(!s1.equals("")){
  		String ids1[]=s1.split(",");//S1表示部门ids拼接字符串，S2表示传入的字符串
  		for(int i=0;i<ids1.length;i++){
  			if(s2.contains(ids1[i])){
  				a=true;
  			}else{
  				a=false;
  				break;
  			}
  		}
  		}
  		return a;
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

	public Sytp getSytp(){
		return this.sytp;
	}

	public void setSytp(Sytp sytp){
		this.sytp = sytp;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<File> getUpload() {
		return upload;
	}

	public void setUpload(List<File> upload) {
		this.upload = upload;
	}

	public List<String> getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<String> getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
