<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform" method="post">
		<div class="submitdata">
			<table width="100%" border="0">
			    <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>基本信息</th>
			    </tr>
			    <tr>
					<th width="15%">所在镇</th>
					<td width="35%">${zywsjbxx.szzname}</td>
					<th width="15%">企业名称</th>
					<td width="35%">${zywsjbxx.qymc}</td>
				</tr>
			    <tr>
					<th width="15%">填报部门</th>
					<td width="35%">${zywsjbxx.tbbm}</td>
					<th width="15%">填报人</th>
					<td width="35%">${zywsjbxx.tbr}</td>
				</tr>
				<tr>
					<th width="15%">女职工人数</th>
					<td width="35%">${zywsjbxx.nzgs}</td>
					<th width="15%">接触职业病危害因素人数</th>
					<td width="35%">${zywsjbxx.jcrs}</td>
					</tr>
				<tr>
					<th width="15%">企业职业病危害类别</th>
					<td width="35%"><cus:hxlabel codeName="企业职业病危害类别" itemValue="${zywsjbxx.whlb}" /></td>
					<th width="15%">职业病危害行业类别</th>
					<td width="35%"><cus:hxlabel codeName="职业病危害行业类别" itemValue="${zywsjbxx.hylb}" /></td>
					</tr>
				<tr>
					<th width="15%">接触职业病危害因素女工人数</th>
					<td width="35%">${zywsjbxx.jcngrs}</td>
					<th width="15%">历年职业病人数</th>
					<td width="35%">${zywsjbxx.lnzgrs}</td>
					</tr>
				<tr>
					<th width="15%">是否为年度专项整治企业</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${zywsjbxx.sfzzqy}" /></td>
					<th width="15%">职业病危害岗位数</th>
					<td width="35%">${zywsjbxx.gws}</td>
					</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%">${zywsjbxx.lxdh}</td>
				</tr>
				 
				   <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>职业卫生分管负责人</th>
			    </tr>
				 
				 
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${zywsjbxx.fgxm}</td>
					<th width="15%">职务</th>
					<td width="35%">${zywsjbxx.fgzw}</td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%">${zywsjbxx.fgdh}</td>
					<th width="15%">手机</th>
					<td width="35%">${zywsjbxx.fgsj}</td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:hxlabel codeName="文化程度" itemValue="${zywsjbxx.fgwhcd}" /></td>
					<th width="15%">专业</th>
					<td width="35%">${zywsjbxx.fgzy}</td>
				</tr>
				
				   <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>职业卫生管理机构负责人</th>
			    </tr>
			    
			    
				<tr>
					<th width="15%">姓名</th>
					<td width="35%">${zywsjbxx.fzrxm}</td>
					<th width="15%">职务</th>
					<td width="35%">${zywsjbxx.fzrzw}</td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%">${zywsjbxx.fzrdh}</td>
					<th width="15%">手机</th>
					<td width="35%">${zywsjbxx.fzrsj}</td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:hxlabel codeName="文化程度" itemValue="${zywsjbxx.fzrwhcd}" /></td>
					<th width="15%">专业</th>
					<td width="35%">${zywsjbxx.fzrzy}</td>
				</tr>
				
				  <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>职业卫生管理人员</th>
			    </tr>
			    
			    
			    <tr>
					<td colspan="4">
						<div id="more">
						<table>
							<tr>
						    	<td  style="text-align:center">序号</td>
								<td  style="text-align:center">姓名</td>
								<td  style="text-align:center">职务</td>
								<td  style="text-align:center">办公电话</td>
								<td  style="text-align:center">手机</td>
								<td  style="text-align:center">文化程度</td>
								<td  style="text-align:center">专业</td>
							</tr>	
							<c:forEach var="zywsglry" items="${zywsglrys}"  varStatus="status">
								<tr style="text-align: center" id="${zywsglry.id}">
								
									<td style='text-align:center'>${status.index+1}</td>
									<td style='text-align:center'>${zywsglry.glrxm}</td>
									<td style='text-align:center'>${zywsglry.glrzw}</td>
									<td style='text-align:center'>${zywsglry.glrdh}</td>
									<td style='text-align:center'>${zywsglry.glrsj}</td>
									<td  style='text-align:center'><cus:hxlabel codeName="文化程度" itemValue="${zywsglry.slrwhcd}" /></td>
									<td style='text-align:center'>${zywsglry.slrzy}</td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
					    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		</div>
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
