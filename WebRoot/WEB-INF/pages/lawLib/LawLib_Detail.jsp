<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>查看</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="${ctx}/webResources/js/pdfobject.js"></script>
	<script>
  		window.onload = function (){
  			if('${lawLib.url}' != "")
  			{
  				var myPDF = new PDFObject({ url: "${lawLib.url}" }).embed("pdf");
  			}
  	};
	</script>
	<style>
		TD {
			FONT-SIZE: 9pt;
			FONT-FAMILY: "宋体", "宋体";
		}
</style>
</head>
<body style="overflow: auto;">
	<div class="page_dialog" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
	<table border="0" cellpadding="0" style="border-collapse: collapse" width="100%">
					<tr>
						<td>&nbsp;
						</td>
					</tr>	
					<tr>
						<td>
							<table border="1" cellpadding="0" style="border-collapse: collapse" width="100%" height="100%" bordercolor="#003366">
							<tr>
								<td height="25" width="20%" align="center">法律法规名称：</td>
								<td height="25" width="80%" colspan="3">${lawLib.lawName}</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">法律法规类型：</td>
								<td height="25" width="80%" colspan="3">
									<s:if test="lawLib.type == 1">
										法律
									</s:if>
									<s:elseif test="lawLib.type == 2">
										法规
									</s:elseif>
									<s:elseif test="lawLib.type == 3">
										规章
									</s:elseif>
									<s:elseif test="lawLib.type == 4">
										规范性文件
									</s:elseif>
									<s:else>
										技术标准
									</s:else>
								</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">索 引 号：</td>
								<td height="25" width="80%" colspan="3">${lawLib.syh}</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">发布机构：</td>
								<td height="25" width="80%" colspan="3">${lawLib.fbjg}</td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">发布日期：</td>
								<td height="25" width="30%"><fmt:formatDate type="date" value="${lawLib.createDay}"/></td>
								<td height="25" width="20%" align="center">实施日期：</td>
								<td height="25" width="30%"><fmt:formatDate type="date" value="${lawLib.pubDay}"/></td>
							</tr>
							<tr>
								<td height="25" width="20%" align="center">内容概述：</td>
								<td height="25" width="80%" colspan="3">${lawLib.gs}</td>
							</tr>
							</table>						
						</td>
					</tr>
					<tr>
						<td>&nbsp;
						</td>
					</tr>	
					<tr>
						<td valign="top">
							<div id="pdf" style="height:300px;text-align:center;font-size:14px">
								<s:if test="lawLib.url != null && lawLib.url != ''">
								您的浏览器不支持在线阅读，请点击<a href="${lawLib.url}" target="_blank" style="color:red">此处</a>下载查看。
								</s:if>
							</div>
						</td>
					</tr>
				</table>
			</div>
</body>
</html>
