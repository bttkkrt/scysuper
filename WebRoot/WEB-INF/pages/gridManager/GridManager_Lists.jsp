<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>乡镇街道列表</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function showYzcompany(row_Id,name)
        {
        	var id  = "town_list";
        	var text = "查看"+name+"有证照企业信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerYzcompany.action?userId="+row_Id;
    		window.parent.addTab(id,text,url);
        }
        
        function showWzcompany(row_Id,name)
        {
        	var id  = "town_list";
        	var text = "查看"+name+"无证照企业信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerWzcompany.action?userId="+row_Id;
    		window.parent.addTab(id,text,url);
        }
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="11%" style="text-align:center">分管领导</th>
				<th width="11%" style="text-align:center">安监办主任</th>
				<th width="11%" style="text-align:center">中队中队长</th>
				<th width="11%" style="text-align:center">中队网格监管员</th>
				<th width="11%" style="text-align:center">下辖网格区域</th>
				<th width="11%" style="text-align:center">村级网格协管员</th>
				<th width="16%" style="text-align:center">管辖范围</th>
				<th width="9%" style="text-align:center">有证企业数</th>
				<th width="9%" style="text-align:center">无证企业数</th>
			</tr>
			<s:iterator value="%{gridlist}" var="fgldbean"> 
				<tr>
					<td width="11%" style="text-align:center"><s:property value="#fgldbean.name"/></td>
					<td width="89%" colspan="8" style="text-align:center">
						<table width="100%">
							<s:iterator value="%{#fgldbean.list}" var="zzrbean"> 
								<tr>
									<td width="11%" style="text-align:center"><s:property value="#zzrbean.name"/></td>
									<td width="78%" colspan="7" style="text-align:center">
										<table width="100%">
											<s:iterator value="%{#zzrbean.list}" var="zddzbean"> 
												<tr>
													<td width="11%" style="text-align:center"><s:property value="#zddzbean.name"/></td>
													<td width="67%" colspan="6" style="text-align:center">
														<table width="100%">
															<s:iterator value="%{#zddzbean.list}" var="zaqzrrbean"> 
																<tr>
																	<td width="11%" style="text-align:center"><s:property value="#zaqzrrbean.name"/></td>
																	<td width="56%" colspan="5" style="text-align:center">
																		<table width="100%">
																			<s:iterator value="%{#zaqzrrbean.list}" var="caqzrrbean"> 
																				<tr>
																					<td width="11%" style="text-align:center"> <s:property value="#caqzrrbean.szc"/></td>
																					<td width="11%" style="text-align:center"> <s:property value="#caqzrrbean.name"/></td>
																					<td width="16%" style="text-align:center"> <s:property value="#caqzrrbean.gxfw"/></td>
																					<td width="9%" style="text-align:center"><span style="color:red;cursor:hand" onclick="showYzcompany('<s:property value="#caqzrrbean.id"/>','<s:property value="#caqzrrbean.name"/>')"><s:property value="#caqzrrbean.yznum"/></span></td>
																					<td width="9%" style="text-align:center"><span style="color:red;cursor:hand" onclick="showWzcompany('<s:property value="#caqzrrbean.id"/>','<s:property value="#caqzrrbean.name"/>')"><s:property value="#caqzrrbean.wznum"/></span></td>
																				</tr>
																			</s:iterator>
																		</table>
																	</td>
																</tr>
															</s:iterator>
														</table>
													</td>
												</tr>
											</s:iterator>
									</table>
								</td>
							</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
		</s:iterator>
	</div>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
