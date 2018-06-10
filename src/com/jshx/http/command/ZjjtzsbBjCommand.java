package com.jshx.http.command;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.duanpf.http.comm.BaseResponse;
import com.duanpf.http.comm.Command;
import com.duanpf.utils.Base64;
import com.jshx.core.utils.SpringContextHolder;
import com.jshx.http.bean.SummaryBean;
import com.jshx.photo.entity.PhotoPic;
import com.jshx.photo.service.SzwxPhotoService;
import com.jshx.zjjtzsb.entity.Zjjtzsb;
import com.jshx.zjjtzsb.service.ZjjtzsbService;

/**
 * 质监局特种设备审核
 * @author 陆婷 2014-1-2
 *
 */

public class ZjjtzsbBjCommand implements Command
{
	private ZjjtzsbService zjjtzsbService = (ZjjtzsbService) SpringContextHolder.getBean("zjjtzsbService");
	private SzwxPhotoService szwxPhotoService = (SzwxPhotoService) SpringContextHolder.getBean("szwxPhotoService");
	
	@SuppressWarnings("unchecked")
	@Override
	public BaseResponse execute(JSONObject json) {
		SummaryBean bd = new  SummaryBean();
		String id = json.getString("id");//编号
		String state = json.getString("state");//整改结果，2表示整改合格，4表示整改不合格
		String remark = json.getString("remark");//核实备注
		
		try{
			Zjjtzsb z = zjjtzsbService.getById(id);
			z.setState(state);
			z.setIsFirst("1");
			if("4".equals(state))
			{
				z.setRemark(remark);
				if(z.getQyid() != null && !"".equals(z.getQyid()))
				{
					z.setShbs("0");
				}
				else
				{
					z.setShbs("1");
				}
				z.setZgqk("");
				z.setZgwcsj("");
				Map map = new HashMap();
				map.put("taskProId",z.getLinkId());
				map.put("picType","zjjtzsb");
				List<PhotoPic> picList = szwxPhotoService.findPicPath(map);//获取整改后图片
				for(PhotoPic p:picList)
				{
					szwxPhotoService.deleteImageWithFlag(p.getId());
				}
			}
			else
			{
				z.setShbs("2");
			}
			zjjtzsbService.update(z);
			bd.setCode("0");
			bd.setMessage("审核成功");
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bd.setCode("1");
			bd.setMessage("审核失败");
		}
		return bd;
	}
	public static void main(String[] args){
		String s = "W3siYTEiOiIxMSIsImEyIjoiMTEiLCJhMyI6IjExIiwiYTQiOiIxMSIsImE1IjoiMTEiLCJhNiI6IjExIiwiYTciOiIxMSJ9LHsiYjEiOiIxMSIsImIyIjoiMTEiLCJiMyI6IjExIiwiYjQiOiIxMSIsImI1IjoiMTEiLCJiNiI6IjExIiwiYjciOiIxMSJ9LCLlkoznmoTlvojlpb3nmoQiXQ=="; 
		System.out.println(Base64.decode2Str(s));
	}
}
