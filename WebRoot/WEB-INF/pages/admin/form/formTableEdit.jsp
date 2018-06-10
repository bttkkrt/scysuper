<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>编辑表单</title>
		<%@include file="/common/jsLib.jsp"%>
	</head>

	<body validform="true">
		<div class="page_dialog">
			<div class="inner6px">
				<div class="cell">

					<form id="myform" name="myform" method="post"
						action="formTableSave.action">
						<input type="hidden" name="model.id" value="${model.id}">
						<s:token />
						<table width="100%" align="center" border="0" cellpadding="2"
							cellspacing="0">
							<tr>
								<th width="15%" height="22px">
									显示名称：
								</th>
								<td width="35%" class="set_l" height="22px">
									<input class="form_text" id="tableName" name="model.tableName"
										type="text" datatype="s2-20"
										style="border: #A1BCA3 1px solid;" value="${model.tableName}" />
									<font style='color: red'>*</font>
								</td>
								<th width="15%" height="22px">
									存储表名：
								</th>
								<td width="35%" class="set_l" height="22px">
									<input class="form_text" id="physicalName"
										name="model.physicalName" type="text"
										<c:if test="${model!=null&&model.id!=null}">readonly="readonly"</c:if>
										datatype="/^[0-9a-zA-Z_]{3,30}$/"
										style="border: #A1BCA3 1px solid; width: 114px"
										value="${model.physicalName}"
										ajaxurl="tableIsExist.action?model.physicalName=${model.physicalName}&model.id=${model.id}" />
									<font style='color: red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%" height="22px">
									模块名称：
								</th>
								<td width="35%" class="set_l" height="22px">
									<input class="form_text" id="projectName"
										name="model.projectName" type="text"
										datatype="/^[0-9a-zA-Z_]{3,30}$/"
										style="border: #A1BCA3 1px solid;"
										value="${model.projectName}" />
									<font style='color: red'>*</font>
								</td>
								<th width="15%" height="22px">
									Java类包路径：
								</th>
								<td width="35%" class="set_l" height="22px">
									com.jshx.
									<input class="form_text" name="model.srcPackage" type="text"
										datatype="/^[0-9a-zA-Z_.]{3,200}$/"
										style="border: #A1BCA3 1px solid; width: 100px"
										value="${model.srcPackage}" />
								</td>
							</tr>
							<tr>
								<th width="15%" height="22px">
									表单页面宽度：
								</th>
								<td width="35%" height="22px">
									<input class="form_text" name="model.tableWidth" type="text"
										style="border: #A1BCA3 1px solid; width: 100px"
										value="${model.tableWidth}" />
								</td>
								<td colspan="2" width="50%" style="padding: 0 0 0 0">
									<table width="100%">
										<tr>
											<th width="40%" height="22px">
												属性标签宽度：
											</th>
											<td width="60%" height="22px">
												<input class="form_text" name="model.labelWidth" type="text"
													style="border: #A1BCA3 1px solid; width: 100px"
													value="${model.labelWidth}" />
											</td>
										</tr>
										<tr>
											<th width="40%" height="22px">
												属性内容宽度：
											</th>
											<td width="60%" height="22px">
												<input class="form_text" name="model.textWidth" type="text"
													style="border: #A1BCA3 1px solid; width: 100px"
													value="${model.textWidth}" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td align="center" colspan="4" height="22px">
									<a href="###" class="btn_01" type="submit">保存<b></b>
									</a>
								</td>
							</tr>
							<tr>
								<td class="set_c" colspan="4" height="22px">
									注：存储表名建议以下划线（"_"）隔开表名中的每个单词，如USER_DEPARTMENT
								</td>
							</tr>
						</table>

					</form>
					<c:if test="${model!=null&&model.id!=null}">

						<table width="100%">
							<tr>
								<th style="text-align: left">
									类似创建该表
								</th>
							</tr>
						</table>

						<form name="myFrm" id="myFrm" method="post"
							action="createCopyTable.action">
							<input type="hidden" name="model.id" value="${model.id}">
							<table width="100%" align="center" border="0" cellpadding="2"
								cellspacing="0">
								<tr>
									<th width="15%" height="22px">
										显示名称：
									</th>
									<td width="35%" height="22px" class="set_l">
										<input class="form_text" name="model.tableName" type="text"
											id="tableName_copy" datatype="s2-20"
											style="border: #A1BCA3 1px solid;" value="" />
										<font style="color: red">*</font>
									</td>
									<th width="15%" height="22px">
										存储表名：
									</th>
									<td width="35%" height="22px" class="set_l">
										<input class="form_text" id="physicalName_copy"
											name="model.physicalName" type="text"
											datatype="/^[0-9a-zA-Z_]{3,30}$/"
											style="border: #A1BCA3 1px solid; width: 114px" value=""
											ajaxurl="tableIsExist.action?model.physicalName=${model.physicalName}&model.id=${model.id}" />
										<font style="color: red">*</font>
									</td>
								</tr>
								<tr>
									<td align="center" colspan="4">
										<a href="###" class="btn_01" type="submit">类似创建<b></b>
										</a>
									</td>
								</tr>
							</table>
						</form>
					</c:if>
				</div>
			</div>
		</div>
	</body>
</html>