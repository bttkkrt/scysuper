package com.jshx.ssjjc.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.jshx.core.base.action.BaseAction;
import com.jshx.core.base.vo.Pagination;
import com.jshx.core.json.CodeJsonValueProcessor;
import com.jshx.core.json.DateJsonValueProcessor;
import com.jshx.http.service.HttpService;
import com.jshx.jdjcjh.service.SupPlaService;
import com.jshx.jdjcrw.entity.SupTas;
import com.jshx.jdjcrw.service.SupTasService;
import com.jshx.module.admin.entity.User;
import com.jshx.module.admin.service.CodeService;
import com.jshx.module.admin.service.UserService;
import com.jshx.qyjbxx.entity.EntBaseInfo;
import com.jshx.qyjbxx.service.EntBaseInfoService;
import com.jshx.ssjjc.entity.Ssjjc;
import com.jshx.ssjjc.entity.SsjjcBean;
import com.jshx.ssjjc.entity.SsjjcCompany;
import com.jshx.ssjjc.entity.SsjjcGz;
import com.jshx.ssjjc.service.SsjjcService;
import com.jshx.xcxgl.service.PatManService;
import com.jshx.xcxlxgl.entity.PatTypMan;
import com.jshx.xcxlxgl.service.PatTypManService;

public class SsjjcAction extends BaseAction
{

	/**
	 * 主键ID列表，用于接收页面提交的多条主键ID信息
	 */
	private String ids;

	/**
	 * 实体类
	 */
	private Ssjjc ssjjc = new Ssjjc();
	
	private SsjjcBean ssjjcBean = new SsjjcBean();

	/**
	 * 业务类
	 */
	@Autowired
	private SsjjcService ssjjcService;
	@Autowired
	private SupTasService supTasService;
	@Autowired
	private PatTypManService patTypManService;
	@Autowired
	private SupPlaService supPlaService;

	/**
	 * 修改新增标记，add为新增、mod为修改
	 */
	private String flag;

	/**
	 * 分页信息
	 */
	private Pagination pagination;
	
	private List<EntBaseInfo> qylist1 = new ArrayList<EntBaseInfo>();
	
	private List<SsjjcCompany> ssjjcqylist = new ArrayList<SsjjcCompany>();
	
	
	private List<User> rylist1 = new ArrayList<User>();
	
	private List<User> rylist2 = new ArrayList<User>();
	
	private SsjjcGz ssjjcGz = new SsjjcGz();
	
	private List<SsjjcBean> sjList = new ArrayList<SsjjcBean>();
	
	@Autowired()
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	private CodeService codeService;
	@Autowired
	private EntBaseInfoService entBaseInfoService;
	@Autowired
	private HttpService httpService;
	
	@Autowired
	private UserService userService;
	
	private String width;
	
	
	/**
	 * 执行查询的方法，返回json数据
	 */
	public void list() throws Exception{
		Map<String, Object> paraMap = new HashMap<String, Object>();
		if(pagination==null)
		    pagination = new Pagination(this.getRequest());
		    
		if(null != ssjjc){
		    //设置查询条件，开发人员可以在此增加过滤条件

		}
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor());  
		Map<String, String> codeMap = new HashMap<String, String>();
		//此处添加需要转换的一维代码，key是一维代码在数据对象中的属性名，value是一维代码的codeId
		config.registerJsonValueProcessor(String.class,new CodeJsonValueProcessor(codeMap)); 
		final String filter = "id|jcsj|jcbl|createTime|ifrw|";
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
		pagination = ssjjcService.findByPage(pagination, paraMap);
		
		convObjectToJson(pagination, config);
	}

	/**
	 * 查看详细信息
	 */
	public String view() throws Exception{
		if((null != ssjjc)&&(null != ssjjc.getId()))
		{
			ssjjc = ssjjcService.getById(ssjjc.getId());
			Map map = new HashMap();
			map.put("cqid", ssjjc.getId());
			sjList = ssjjcService.findSsjjcBean(map);
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
		Map map = new HashMap();
		map.put("jcbl", 100);
		ssjjcGz = ssjjcService.getSsjjcGzByMap(null);
		String cydy = ssjjcGz.getRyids();
		String cyld = ssjjcGz.getLdids();
		if(cydy != null && !"".equals(cydy))
		{
			map.put("ryids", "('" + cydy.replaceAll(" ", "").replaceAll(",", "','") + "')");
		}
		else
		{
			map.put("ryids", "('-1')");
		}
		if(cyld != null && !"".equals(cyld))
		{
			map.put("ldids", "('" + cyld.replaceAll(" ", "").replaceAll(",", "','") + "')");
		}
		else
		{
			map.put("ldids", "('-1')");
		}
		if(ssjjc != null)
		{
			List<String> qylxlist = new ArrayList<String>();
			if(ssjjc.getQylx() != null && !"".equals(ssjjc.getQylx()))
			{
				String[] ss = ssjjc.getQylx().trim().split(",");
				for(String s:ss)
				{
					qylxlist.add("%"+s.trim()+"%");
				}
				map.put("qylx", qylxlist);
			}
			if(ssjjc.getSflddd() != null)
			{
				if(ssjjc.getSflddd().equals("1"))//领导不带队
				{
					map.put("deptCode", "ldbm");
					rylist1 = ssjjcService.getRyListByLdOrZd(map);
				}
				else
				{
					map.put("deptCode", "zd");
					rylist1 = ssjjcService.getRyListByLdOrZd(map);
				}
			}
			if(ssjjc.getJcbl() != null && !"".equals(ssjjc.getJcbl()))
			{
				map.put("jcbl", ssjjc.getJcbl().trim());
			}
		}
		
		map.put("deptCode", "zd");
		rylist2 = ssjjcService.getRyListByLdOrZd(map);
		
		for(int i=0;i<Integer.parseInt(ssjjc.getZrs());i++)
		{
			SsjjcCompany sjjjcCompany = new SsjjcCompany();
			sjjjcCompany.setQylist(ssjjcService.getQyListByLxAndWg(map));
			ssjjcqylist.add(sjjjcCompany);
		}
		
		int num = 200*Integer.parseInt(ssjjc.getZrs());
		width=num+"px";
		ssjjc.setDeptId(this.getLoginUserDepartmentId());
		ssjjc.setDelFlag(0);
		
		setSessionAttribute("ssjjc", ssjjc);
		return EDIT;
	}

	/**
	 * 将File对象转换为Blob对象，并设置到实体类中
	 * 如果没有File对象，可删除此方法，并一并删除save方法中调用此方法的代码
	 */
	private void setBlobField(FileInputStream in)
	{
		if (null != ssjjc)
		{
			try
			{
				//此处将File对象转换成blob对象，并设置到ssjjc中去
				
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }

	/**
	 * 删除信息
	 */
	public String delete() throws Exception{
	    try{
			ssjjcService.deleteWithFlag(ids);
			this.getResponse().getWriter().println("{\"result\":true}");
		}catch(Exception e){
			this.getResponse().getWriter().println("{\"result\":false}");
		}
		return null;
	}
	
	public void ssjjcCreate()
	{
		
		try {
			ssjjc = (Ssjjc) getSessionAttribute("ssjjc");
			ssjjcService.save(ssjjc);
			
			getResponse().setContentType("octets/stream");
			getResponse().addHeader("Content-Disposition", "attachment;filename=ssjjc.xls");
			String root = this.getRequest().getRealPath("/"); 
			root = root.replaceAll("\\\\", "/");
			InputStream is= new FileInputStream(root + "ssjjc.xls");
			HSSFWorkbook wb = new HSSFWorkbook(is);
			
			
			ssjjcGz = ssjjcService.getSsjjcGzByMap(null);
			String ifWg = ssjjcGz.getIfWg();
			String cydy = ssjjcGz.getRyids();
			String cyld = ssjjcGz.getLdids();
			Map map = new HashMap();
			map.put("jcbl", 100);
			if(cydy != null && !"".equals(cydy))
			{
				map.put("ryids", "('" + cydy.replaceAll(" ", "").replaceAll(",", "','") + "')");
			}
			else
			{
				map.put("ryids", "('-1')");
			}
			if(cyld != null && !"".equals(cyld))
			{
				map.put("ldids", "('" + cyld.replaceAll(" ", "").replaceAll(",", "','") + "')");
			}
			else
			{
				map.put("ldids", "('-1')");
			}
			if(ssjjc != null)
			{
				List<String> qylxlist = new ArrayList<String>();
				if(ssjjc.getQylx() != null && !"".equals(ssjjc.getQylx()))
				{
					String[] ss = ssjjc.getQylx().trim().split(",");
					for(String s:ss)
					{
						qylxlist.add("%"+s.trim()+"%");
					}
					map.put("qylx", qylxlist);
				}
				if(ssjjc.getJcbl() != null && !"".equals(ssjjc.getJcbl()))
				{
					map.put("jcbl", ssjjc.getJcbl().trim());
				}
			}
			
			
			if(ifWg != null && ifWg.equals("0"))//不照网格
			{
				if(ssjjc.getSflddd() != null)
				{
					if(ssjjc.getSflddd().equals("1"))//领导带队
					{
						map.put("deptCode", "ldbm");
					}
					else
					{
						map.put("deptCode", "zd");
						
					}
				}
				rylist1 = ssjjcService.getRyListByLdOrZd(map);
				map.put("deptCode", "zd");
				rylist2 = ssjjcService.getRyListByLdOrZd(map);
				qylist1 = ssjjcService.getQyListByLxAndWg(map);
				
				createExcel(ssjjc, qylist1, rylist1, rylist2, wb);
			}
			else //按照网格
			{
				String[] delist = new String[]{"002001004001","002001004002","002001004003","002001005001","002001005002","002001005003","002001005004"};
				for(int k=0;k<delist.length;k++)
				{
					map.put("deptcode", delist[k]);
					qylist1 = ssjjcService.getQyListByLxAndWg(map);
					if(ssjjc.getSflddd() != null)
					{
						if(ssjjc.getSflddd().equals("1"))//领导带队
						{
							map.put("deptcode", "");
							map.put("deptCode", "ldbm");
						}
						else
						{
							map.put("deptCode", "zd");
							
						}
					}
					rylist1 = ssjjcService.getRyListByLdOrZd(map);
					map.put("deptCode", "zd");
					rylist2 = ssjjcService.getRyListByLdOrZd(map);
					
					createExcel(ssjjc, qylist1, rylist1, rylist2, wb);
				}
			}
			
			wb.write(getResponse().getOutputStream());
			System.out.println("excel导出成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createExcel(Ssjjc ssjjc,List<EntBaseInfo> qylist1,List<User> rylist1,List<User> rylist2,HSSFWorkbook wb)
	{
		HSSFSheet sheet = wb.getSheetAt(0);
		int num = 2;
		int zrs = Integer.parseInt(ssjjc.getZrs());
		int total=qylist1.size();
		int zs = total/zrs;
		
		if(rylist1.size() != 0 && rylist2.size() != 0)
		{
			int zdz1 = (zs%rylist1.size()) !=0?((zs/rylist1.size())+1):(zs/rylist1.size());
			int zdz2 = (zs%rylist2.size()) !=0?((zs/rylist2.size())+1):(zs/rylist2.size());
			String syry1 = "";
			String syry2 = "";
			for(int i=0;i<qylist1.size();i=i+zrs)
			{
				if(i+zrs <= qylist1.size())
				{
					HSSFRow row0 = sheet.createRow(num);
		        	HSSFCellStyle css = wb.createCellStyle();
		        	css.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
		        	css.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
		        	css.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
		        	css.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
		        	
		        	HSSFCell cel0 = row0.createCell(0);
					cel0.setCellValue(num-1);
					cel0.setCellStyle(css);
					HSSFCell cel1 = row0.createCell(1);
					cel1.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(ssjjc.getJcsj()));
					cel1.setCellStyle(css);
					
					if(rylist2.size() == 1)
					{
						rylist1.remove(rylist2.get(0));
					}
					if(rylist1.size() != 0)
					{
						String ry = "";
						String ryid = "";
						int radom1 = (int)(Math.random()*(rylist1.size()));
						User user1 = rylist1.get(radom1);
						String mc1 = user1.getDisplayName();
						syry1 += mc1 + ",";
						if(countStr(syry1,mc1) == zdz1)
						{
							rylist1.remove(radom1);
						}
						while(true)
						{
							int radom2 = (int)(Math.random()*(rylist2.size()));
							User user2 = rylist2.get(radom2);
							String mc2 = user2.getDisplayName();
							if(!mc1.equals(mc2))
							{
								ry = mc1 + "," + mc2;
								ryid = user1.getId() + "," + user2.getId();
								syry2 += mc2 + ",";
								if(countStr(syry2,mc2) == zdz2)
								{
									rylist2.remove(radom2);
								}
								break;
							}
						}
						
						HSSFCell cel2 = row0.createCell(2);
						cel2.setCellValue(ry);
						cel2.setCellStyle(css);
						
						
						
						String qymc = "";
						String qyid = "";
						for(int j=i;j<i+zrs;j++)
						{
							if(j<qylist1.size())
							{
								EntBaseInfo ent = qylist1.get(j);
								qymc += ent.getEnterpriseName()+",";
								qyid += ent.getId() +",";
							}
						}
						if(qymc.length() != 0)
						{
							qymc = qymc.substring(0,qymc.length()-1);
							qyid = qyid.substring(0,qyid.length()-1);
						}
						HSSFCell cel3 = row0.createCell(3);
						cel3.setCellValue(qymc);
						cel3.setCellStyle(css);
						
						SsjjcBean bean = new SsjjcBean();
						bean.setCqid(ssjjc.getId());
						bean.setDelFlag(0);
						bean.setCreateTime(new Date());
						bean.setJcsj(new SimpleDateFormat("yyyy-MM-dd").format(ssjjc.getJcsj()));
						bean.setJcry(ry);
						bean.setJcryid(ryid);
						bean.setJcqy(qymc);
						bean.setJcqyid(qyid);
						ssjjcService.saveSsjjcBean(bean);
						
						num++;
					}
				}
			}
		}
	}
	
	public static int countStr(String str1, String str2) {   
        int counter=0;  
        if (str1.indexOf(str2) == -1) {    
            return 0;  
        }   
            while(str1.indexOf(str2)!=-1){  
                counter++;  
                str1=str1.substring(str1.indexOf(str2)+str2.length());  
            }  
            return counter;    
    }    
	
	public String initGzEdit()
	{
		ssjjcGz = ssjjcService.getSsjjcGzByMap(null);
		return EDIT;
	}
	
	public String saveGz()
	{
		if(ssjjcGz != null)
		{
			ssjjcGz.setDelFlag(0);
			if(ssjjcGz.getId() != null && !"".equals(ssjjcGz.getId()))
			{
				ssjjcService.updateSsjjcGz(ssjjcGz);
			}
			else
			{
				ssjjcService.saveSsjjcGz(ssjjcGz);
			}
		}
		return RELOAD;
	}
	
	public String initTaskEdit() throws Exception
	{
		view();
		return EDIT;
	}
	
	public String saveTask() throws Exception
	{
		if(ssjjcBean != null && ssjjcBean.getId() != null)
		{
			ssjjc = ssjjcService.getById(ssjjc.getId());
			Date jcsj = ssjjc.getJcsj();
			Map maps=new HashMap();
			maps.put("planId", ssjjc.getId());
			maps.put("state", "未完成");
			List<Map> tasklist=supPlaService.findTask(maps);//查找该计划生成的任务
			String taskIds="";
			for(Map tas:tasklist){
				String id=tas.get("id").toString();
				taskIds+=id+"|";
			}
			supTasService.deleteWithFlag(taskIds);//更新的时候删除原本计划的任务
			String[] beanids = ssjjcBean.getId().replaceAll(" ", "").split(",");
			String[] jclxs = ssjjcBean.getJcxlx().replaceAll(" ", "").split(",");
			int ssnum = 1;
			for(int i=0;i<beanids.length;i++)
			{
				SsjjcBean bean = ssjjcService.getSsjjcBeanById(beanids[i].trim());
				String[] qyids = bean.getJcqyid().split(",");
				String[] qymcs = bean.getJcqy().split(",");
				String[] ryids = bean.getJcryid().split(",");
				String[] rymcs = bean.getJcry().split(",");
				String jcxlx = jclxs.length>i?jclxs[i].substring(0,jclxs[i].length()-1).replaceAll(";", ","):"";
				if(!"".equals(jcxlx))
				{
					for(int j=0;j<qyids.length;j++)
					{
						User user = userService.findUserById(ryids[1].trim());
						SupTas supTas = new SupTas();
						supTas.setTaskName(bean.getJcsj().replaceAll("-", "")+"双随机检查临时任务");
						Date d = new Date();
						SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
						EntBaseInfo entBaseInfo = entBaseInfoService.getById(qyids[j].trim());
						supTas.setAreaId(entBaseInfo.getGrid());
						supTas.setAreaName(entBaseInfo.getGridName());
						supTas.setTaskType("临时任务");
						supTas.setTaskState("未完成");
						supTas.setCheckDeptId(user.getDeptCode());
						supTas.setTaskNum(supTas.getTaskName()+sdf2.format(d)+ssnum);
						supTas.setTaskName(supTas.getTaskName());
						supTas.setCheckItemId(jcxlx.trim());
						String name="";
				            for(String citId:supTas.getCheckItemId().split(",")){
				            	PatTypMan patTypMan = new PatTypMan();
					            patTypMan=patTypManService.getById(citId.trim());
					            name+=patTypMan.getPatrolTypeName()+",";
				            }
				            if(name.endsWith(",")){
				            	name=name.substring(0, name.length()-1);
				            }
						supTas.setCheckItemName(name);
						supTas.setCheckUserid(ryids[0].trim());
						supTas.setCheckUsername(rymcs[0].trim());
						supTas.setXbUserId(ryids[1].trim());
						supTas.setXbUserName(rymcs[1].trim());
						supTas.setCompanyId(qyids[j].trim());
						supTas.setCompanyName(qymcs[j].trim());
						supTas.setStime(jcsj);
						supTas.setFtime(jcsj);
						supTas.setDelFlag(0);
						supTas.setPlanId(bean.getCqid());
						supTasService.save(supTas);
						ssnum++;
					}
				}
			}
			ssjjc.setIfrw("1");
			ssjjcService.update(ssjjc);
		}
		return RELOAD;
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

	public Ssjjc getSsjjc(){
		return this.ssjjc;
	}

	public void setSsjjc(Ssjjc ssjjc){
		this.ssjjc = ssjjc;
	}

	public String getFlag(){
		return flag;
	}

    public void setFlag(String flag){
        this.flag = flag;
    }

	public List<EntBaseInfo> getQylist1() {
		return qylist1;
	}

	public void setQylist1(List<EntBaseInfo> qylist1) {
		this.qylist1 = qylist1;
	}

	public List<User> getRylist1() {
		return rylist1;
	}

	public void setRylist1(List<User> rylist1) {
		this.rylist1 = rylist1;
	}

	public List<User> getRylist2() {
		return rylist2;
	}

	public void setRylist2(List<User> rylist2) {
		this.rylist2 = rylist2;
	}

	public SsjjcGz getSsjjcGz() {
		return ssjjcGz;
	}

	public void setSsjjcGz(SsjjcGz ssjjcGz) {
		this.ssjjcGz = ssjjcGz;
	}

	public List<SsjjcBean> getSjList() {
		return sjList;
	}

	public void setSjList(List<SsjjcBean> sjList) {
		this.sjList = sjList;
	}

	public List<SsjjcCompany> getSsjjcqylist() {
		return ssjjcqylist;
	}

	public void setSsjjcqylist(List<SsjjcCompany> ssjjcqylist) {
		this.ssjjcqylist = ssjjcqylist;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public SsjjcBean getSsjjcBean() {
		return ssjjcBean;
	}

	public void setSjjjcBean(SsjjcBean ssjjcBean) {
		this.ssjjcBean = ssjjcBean;
	}
}
