<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		var type= "${type}";
        function showDiv(obj)
        {
        	var qyid = "${companyBackUp.id}";
			var companyid = "${companyBackUp.companyId}";
        	if(obj == '8')
        	{
        		//企业详情
        		parent.parent.frames["frm"].addTab("companydetail","企业详情","${ctx}/jsp/company/companyView.action?company.id="+companyid+"&type="+type+"&isShenhe=0");
        	}
        	else if(obj == '0')
        	{
        		//自查隐患
        		parent.parent.frames["frm"].addTab("M0621","企业自查隐患上报","${ctx}/jsp/zcyhsb/jshxZcyhsbList.action?isshow=1&jshxZcyhsb.qyid=" +qyid );
        	}
        	else if(obj == '1')
        	{
        		//主要负责人培训
        		parent.parent.frames["frm"].addTab("M060407","安全、职业卫生培训","${ctx}/jsp/zazPxb/jshxZazPxbList.action?isshow=1&jshxZazPxb.qyid="+qyid);
        	}
        	else if(obj == '2')
        	{
        		//特种作业人员培训
        		parent.parent.frames["frm"].addTab("M060408","特种作业人员培训","${ctx}/jsp/tzzyPxb/jshxTzzyPxbList.action?isshow=1&jshxTzzyPxb.qyid="+qyid);
        	}
        	else if(obj == '3')
        	{
        		//新入厂员工培训
        		parent.parent.frames["frm"].addTab("M060409","新入厂员工培训","${ctx}/jsp/xrcPxb/jshxXrcPxbList.action?isshow=1&jshxXrcPxb.qyid="+qyid);
        	}
        	else if(obj == '4')
        	{
        		//再转岗员工培训
        		parent.parent.frames["frm"].addTab("M060410","再培训及转岗下岗脱岗培训","${ctx}/jsp/zzxtPxb/jshxZzxtPxbList.action?isshow=1&jshxZzxtPxb.qyid="+qyid);
        	}
        	else if(obj == '5')
        	{
        		//行政许可
        		parent.parent.frames["frm"].addTab("M0605","行政许可","${ctx}/jsp/xkzsb/jshxXkzsbList.action?isshow=1&jshxXkzsb.qyid="+qyid);
        	}
        	else if(obj == '6')
        	{
        		//厂区图片
        		parent.parent.frames["frm"].addTab("M0607","厂区图片","${ctx}/jsp/factorypicture/factorypictureList.action?isshow=1&factorypicture.comId="+companyid);
        	}
        	else
        	{
        		//关键装置部位
        		parent.parent.frames["frm"].addTab("M0609","关键装置和重点部位","${ctx}/jsp/zzbw/jshxZzbwList.action?isshow=1&jshxZzbw.qyid="+qyid);
        	}
        }
	</script>
</head>
<body >
	<div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; background-color:#EEE; font-family:楷体" onclick="showDiv('8')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#EEE'">
		基本信息
	</div>
    
    <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; font-family:楷体" onclick="showDiv('0')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#FFF'">
		企业自查隐患情况
	</div>
    
    <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; background-color:#EEE; font-family:楷体" onclick="showDiv('1')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#EEE'">
		主要负责人、安全管理员安全培训和职业卫生培训情况
	</div>
    
    <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; font-family:楷体" onclick="showDiv('2')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#FFF'">
		特种作业人员培训情况
	</div>
			
    <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; background-color:#EEE; font-family:楷体" onclick="showDiv('3')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#EEE'">
		新入厂员工培训情况
	</div>	
				
	 <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; font-family:楷体" onclick="showDiv('4')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#FFF'">
		员工再培训及转岗、下岗、脱岗培训情况
	</div>		
    
     <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; background-color:#EEE; font-family:楷体" onclick="showDiv('5')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#EEE'">
		行政许可信息
	</div>		
	
     <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; font-family:楷体" onclick="showDiv('6')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#FFF'">
		厂区图片
	</div>		
    
    
    <div style="width：80%;cursor: pointer;height:45px; text-align:center;color:blue; font-size:20px; line-height:45px; background-color:#EEE; font-family:楷体" onclick="showDiv('7')" onMouseOver="this.style.backgroundColor = '#CCC'" onMouseOut="this.style.backgroundColor = '#EEE'">
		关键装置部位
	</div>			
</body>
</html>
