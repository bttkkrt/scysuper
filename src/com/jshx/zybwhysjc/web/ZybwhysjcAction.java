/**
 * Class Name: ZybwhysjcAction
 * Class Description：职业病危害因素检测
 */
package com.jshx.zybwhysjc.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.jshx.company.service.CompanyService;
import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.utils.Struts2Util;
import com.jshx.core.utils.SysPropertiesUtil;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.whysjbqk.service.WhysjbqkService;
import com.jshx.zybwhysjc.entity.DataBean;
import com.jshx.zybwhysjc.entity.Zybwhysjc;
import com.jshx.zybwhysjc.service.ZybwhysjcService;
import com.jshx.zycsqk.entity.Zycsqk;
import com.jshx.zycsqk.service.ZycsqkService;

public class ZybwhysjcAction extends BaseAction
{
	private String ids;

	private Zybwhysjc zybwhysjc = new Zybwhysjc();
	
	private  List<DataBean> datas = new ArrayList<DataBean>();
	
	private  List<DataBean> checks = new ArrayList<DataBean>();
	
	private List<Zybwhysjc> qts = new ArrayList<Zybwhysjc>();
	
	private List<PhotoPic> picList = new ArrayList<PhotoPic>();

	@Autowired
	private SzwxPhotoService szwxPhotoService;
	@Autowired
	private ZybwhysjcService zybwhysjcService;
	@Autowired
	private WhysjbqkService whysjbqkService;
	@Autowired
	private ZycsqkService zycsqkService;
	
	@Autowired
	private CompanyService companyService;

	/**
	 * 标识位
	 */
	private String flag;
	
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	
	/**
	 * 图片地址
	 */
    private String picName;
    /**
     * 原文件名称
     */
    private String fileName;

	/**
	 * 分页
	 */
	private Pagination pagination;
	
	public String init(){
		String deptCode = this.getLoginUserDepartment().getDeptCode();
		if(deptCode.startsWith(SysPropertiesUtil.getProperty("qiyeDeptCode")))
		{
			flag = "1";
		}
		else
		{
			flag = "2";
		}
		return SUCCESS;
	}
	
	/**
	 * 列表查询
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != zybwhysjc){
			if ((null != zybwhysjc.getCjmc()) && (0 < zybwhysjc.getCjmc().trim().length())){
				paraMap.put("cjmc", "%" + zybwhysjc.getCjmc().trim() + "%");
			}
			if ((null != zybwhysjc.getCjid()) && (0 < zybwhysjc.getCjid().trim().length())){
				paraMap.put("rowId", zybwhysjc.getCjid().trim());
			}
		}
		
		pagination = zybwhysjcService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination,null);
	}

	/**
	 * 查看
	 */
	public String view() throws Exception{
		if((null != zybwhysjc)&&(null != zybwhysjc.getId()))
			zybwhysjc = zybwhysjcService.getById(zybwhysjc.getId());
		try {
			if((null != zybwhysjc)&&(null != zybwhysjc.getCjid())){
					System.out.println("zybwhysjc.getCjid():"+zybwhysjc.getCjid());
				Zycsqk whysjbqk =  zycsqkService.getById(zybwhysjc.getCjid());
				String mid =  whysjbqk.getZywhysmcid();
				String mname =  whysjbqk.getZywhysmc();
				if(mid!=null&&mname!=null){
					String[] ids = mid.replaceAll(" ", "").split(",");
					String[] names = mname.replaceAll(" ", "").split(",");
					for(int i=0;i<ids.length;i++){
						DataBean data = new DataBean();
						data.setId(ids[i]);
						data.setName(names[i]);
						datas.add(data);
					}
				}
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return VIEW;
	}

	/**
	 * 初始化修改
	 */
	public String initEdit() throws Exception{
		view();
	    return EDIT;
	}

	/**
	 *保存信息
	 */
	public String save() throws Exception{
		if((null != zybwhysjc)&&(null != zybwhysjc.getCjid())){
			System.out.println("zybwhysjc.getCjid():"+zybwhysjc.getCjid());
			Zycsqk whysjbqk =  zycsqkService.getById(zybwhysjc.getCjid());
			zybwhysjc.setCjmc(whysjbqk.getCjmc());
			zybwhysjc.setWccj(whysjbqk.getWccj());
			zybwhysjc.setGw(whysjbqk.getGw());
		}
		zybwhysjc.setTbry(this.getLoginUser().getDisplayName());
		if ("add".equalsIgnoreCase(this.flag)){
			zybwhysjc.setDeptId(this.getLoginUserDepartmentId());
			zybwhysjc.setDelFlag(0);
			zybwhysjcService.save(zybwhysjc);
		}else{
			zybwhysjcService.update(zybwhysjc);
		}
		return RELOAD;
	}

	/**
	 * 删除
	 */
	public String delete() throws Exception{
	    try{
			zybwhysjcService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 添加扫描件 李军 2013-11-27
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String smj(){
		Map map = new HashMap();
		map.put("taskProId",zybwhysjc.getCjid());
		map.put("picType","zybwhysjc");
	    picList = szwxPhotoService.findPicPath(map);//获取岗位图片
		return "smj";
	}
	
	/**
	 * 图片保存 lj  2013-07-18
	 * @return
	 */
	public void savePhoto(){
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
			destFName.append(root).append("file/zybwhysjc/");
			outdir = new File(destFName.toString());
			if(Filedata!= null && !Filedata.isEmpty()){//获取附件文件 进行判断是否存在
			    imgName =getDatedFName(FiledataFileName.get(0));
				outfile = new File(destFName+imgName);
				InputStream stream  = new FileInputStream(Filedata.get(0));
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
			PhotoPic taskPic = new PhotoPic();
			taskPic.setCreateTime(new Date());
			taskPic.setPicName("zybwhysjc/"+imgName);
			taskPic.setTaskProId(zybwhysjc.getCjid());
			taskPic.setTaskRemark("");
			taskPic.setDelFlag(0);
			taskPic.setPicType("zybwhysjc");
			taskPic.setFileName(FiledataFileName.get(0));//保存原文件的名称 李军 2013-07-19
			szwxPhotoService.save(taskPic);//在此处调用图片的保存
			JSONObject jn = new JSONObject();
			jn.put("picName", "zybwhysjc/"+imgName);
			jn.put("pid", taskPic.getId());
			this.getResponse().getWriter().write(jn.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	/**
	 *  自定义上传附件的名称 以时间格式来处理
	 * @param fname
	 * @return
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
	 * 删除信息图片
	 */
	public String deleteImage() throws Exception{
	    try{
	    	szwxPhotoService.deleteImageWithFlag(picName);
			this.getResponse().getWriter().println("{\"result\":\"true\"}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":\"false\"}");
		}
		return null;
	}
	/**
	 * 图片 文字 另存为功能 
	 *  李军 2013-08-15
	 */
	public void saveFile()
  	{
  		try
  		{//根据附件id获取附件对象
			PhotoPic photoPic = szwxPhotoService.getById(picName);
  			String filePath = new String();
  			filePath = "virtualdir/upload/file/";
  			String path = Struts2Util.getServletContext().getRealPath("/");
  			path = path.substring(0,path.indexOf("webapps"));
  			File fis = new File(path + filePath + photoPic.getPicName());
  			System.out.print("图片的地址是："+path + filePath + picName);
  			if (fis.exists()) {
  				InputStream in = new FileInputStream(fis);

	        String browName = new String();
	        browName = URLEncoder.encode(photoPic.getFileName(), "UTF-8");
	        String clientInfo = getRequest().getHeader("User-agent");
	        if ((clientInfo != null) && (clientInfo.indexOf("MSIE") > 0)) {
	          if ((clientInfo.indexOf("MSIE 6") > 0) || (clientInfo.indexOf("MSIE 5") > 0))
	            browName = new String((photoPic.getFileName()).getBytes("GBK"), "ISO-8859-1");
	        }

        Struts2Util.getResponse()
          .addHeader(
          "Content-Disposition", 
          "attachment;filename=" + 
          browName);
        OutputStream out = Struts2Util.getResponse().getOutputStream();
        try {
          byte[] buf = new byte[1024];
          int len;
          while ((len = in.read(buf)) != -1)
          {
            out.write(buf, 0, len);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          in.close();
          out.close();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
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

	public Zybwhysjc getZybwhysjc(){
		return this.zybwhysjc;
	}

	public void setZybwhysjc(Zybwhysjc zybwhysjc){
		this.zybwhysjc = zybwhysjc;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<DataBean> getDatas() {
		return datas;
	}

	public void setDatas(List<DataBean> datas) {
		this.datas = datas;
	}

	public List<DataBean> getChecks() {
		return checks;
	}

	public void setChecks(List<DataBean> checks) {
		this.checks = checks;
	}

	public List<Zybwhysjc> getQts() {
		return qts;
	}

	public void setQts(List<Zybwhysjc> qts) {
		this.qts = qts;
	}

	public List<File> getFiledata() {
		return Filedata;
	}

	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}

	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}

	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<PhotoPic> getPicList() {
		return picList;
	}

	public void setPicList(List<PhotoPic> picList) {
		this.picList = picList;
	}
       
    
}
