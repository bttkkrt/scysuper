<%@page language="java" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
				<form name="myform1" method="post" enctype="multipart/form-data" action="versionSave.action">
					<s:token />
					<input type="hidden" name="flag" value="${flag}">
					<input type="hidden" name="version.id" value="${version.id}">
					<input type="hidden" name="version.createTime"
						value="<fmt:formatDate type="both" value="${version.createTime}" />">
					<input type="hidden" name="version.updateTime"
						value="${version.updateTime}">
					<input type="hidden" name="version.createUserID"
						value="${version.createUserID}">
					<input type="hidden" name="version.updateUserID"
						value="${version.updateUserID}">
					<input type="hidden" name="version.deptId"
						value="${version.deptId}">
					<input type="hidden" name="version.delFlag"
						value="${version.delFlag}">

					
						<table width="100%" border="0">
							<tr>
								<th width="25%">
									版本号
								</th>
								<td width="75%">
									<input name="version.versionNumber" class="form_text"
										value="${version.versionNumber}" type="text" datatype="s1-10"
										errormsg="昵称至少1个字符,最多10个字符！" style="width: 60%" >
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<th width="25%">
									版本平台
								</th>
								<td width="75%">
									<cus:SelectOneTag property="version.versionPlatform"
										defaultText='请选择' codeName="终端类型"
										value="${version.versionPlatform}" style="width: 60%" />
									<font color="red">*</font>
								</td>
							</tr>
							<tr>
								<th width="25%">
									更新内容
								</th>
								<td width="75%">
									<input name="version.content" value="${version.content}"
										class="form_text" type="text" maxlength="1000" style="width: 60%" >
								</td>
							</tr>
							<tr>
								<th width="25%">
									版本上传
								</th>
								<td width="75%">
									<input name="versionFile" type="file">
								</td>
							</tr>
							<tr>
								<th width="25%">
									二维码图片
								</th>
								<td width="75%">
									<input name="ewmFile" type="file">
								</td>
							</tr>
							<tr>
								<td colspan="4" height="100px" style="text-align:center">
									
										<s:if test="flag=='add'">
											<a href="#" class="btn_01" type="submit">添加<b></b> </a>
										</s:if>
										<s:else>
											<a href="#" class="btn_01" type="submit">更新<b></b> </a>
										</s:else>
								
										<a href="#" class="btn_01" onclick="parent.close_win('versionWindow');">关闭<b></b>
										</a>
								
								</td>
							</tr>
						</table>
					
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
