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
			<table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%" colspan="3">${wfwgcompany.companyname}</td>				
				</tr>
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%" colspan="3">
						${wfwgcompany.szzname}
					</td>
						<!--hanxc 2014/11/11 
					<th width="15%">所在村</th>
					<td width="35%">${wfwgcompany.szcname}
					</td>
						 -->
				</tr>
				<tr>
				 	<th width="15%">负责人</th>
					<td width="35%">${wfwgcompany.fzr}</td>
					<th width="15%">身份证号码</th>
					<td width="35%">${wfwgcompany.sfz}</td>
				</tr>
				<tr>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:hxlabel codeName="企业行业分类" itemValue="${wfwgcompany.hyfl}" /></td>
					<th width="15%">违规违法类别</th>
					<td width="35%"><cus:hxlabel codeName="违规违法类别" itemValue="${wfwgcompany.wgwflb}" /></td>
				</tr>
				<tr>
					<th width="15%">是否危化品企业类型</th>
					<td width="35%">			
					 	<s:if test="wfwgcompany.ifwhpqylx==1">
		 					<input type="radio">否
                  			<input type="radio" checked="checked">是
                  		</s:if>
                		<s:else>
                  			<input type="radio" checked="checked">否
                  			<input type="radio">是
                  		</s:else>
                    </td>
                    <td width="50%" colspan="2">
                    <cus:hxmulselectlabel codeName="危化品企业类型" itemValue="${wfwgcompany.whpqylx}" />
					</td>
				</tr>
				<tr>
					<th width="15%">是否为五小企业</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${wfwgcompany.ifwxqy}" /></td>
					<th width="15%">行政强制措施</th>
					<td width="35%"><cus:hxmulselectlabel codeName="行政强制措施" itemValue="${wfwgcompany.xzqzcs}" /></td>
				</tr>
				<tr>
					<th width="15%">立案日期</th>
					<td width="35%">
						${wfwgcompany.larq}
					</td>
					<th width="15%">结案日期</th>
					<td width="35%">
						${wfwgcompany.jarq}
					</td>
				</tr>
				<tr>
					<th width="15%">是否有营业执照</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${wfwgcompany.ifyyzz}" /></td>
					<th width="15%">是否属举报</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${wfwgcompany.ifjb}" /></td>
				</tr>
				<tr>
					<th width="15%">是否实施经济处罚</th>
					<td width="35%"><cus:hxlabel codeName="是或否" itemValue="${wfwgcompany.ifssjjcf}" /></td>
					<th width="15%">处罚金额(万元)</th>
					<td width="35%">${wfwgcompany.cfje}</td>
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
