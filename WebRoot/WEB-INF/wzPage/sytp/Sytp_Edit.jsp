<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:if test="flag=='add'">新增</s:if> <s:else>修改</s:else>记录</title>
		<%@include file="/common/jsLib.jsp"%>
	<script>
		function save()
		{
			var strs = new Array(); //定义一数组
        	var pic1= $("#upload").val();
        	strs = pic1.split('.');
        	var suffix = strs [strs .length - 1];

        	if (suffix != 'jpg' && suffix != 'gif' && suffix != 'jpeg' && suffix != 'png' && suffix != 'bmp') {
           	 	alert("只能上传jpg、png、gif、bmp、jpeg格式文件！");
            	var obj = document.getElementById('upload');
            	obj.outerHTML = obj.outerHTML; //这样清空，在IE8下也能执行成功
        	}
        	else
        	{
        		document.myform1.submit();
        	}
		}
	</script>
	</head>
<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<form name="myform1" method="post" enctype="multipart/form-data" action="sytpSave.action">
				<s:token />
				<input type="hidden" name="flag" value="${flag}">
				<input type="hidden" name="sytp.id"
					value="${sytp.id}">
				<input type="hidden" name="sytp.createTime"
					value="<fmt:formatDate type="both" value="${sytp.createTime}" />">
				<input type="hidden" name="sytp.updateTime"
					value="${sytp.updateTime}">
				<input type="hidden" name="sytp.createUserID"
					value="${sytp.createUserID}">
				<input type="hidden" name="sytp.updateUserID"
					value="${sytp.updateUserID}">
				<input type="hidden" name="sytp.deptId"
					value="${sytp.deptId}">
				<input type="hidden" name="sytp.delFlag"
					value="${sytp.delFlag}">
				<div class="inner6px">
					<div class="cell">
						<table>
							<tr>
								<th width="15%">
									标题
								</th>
								<td colspan="3">
									<input name="sytp.infoTitle"  id="infoTitle"
										value="${sytp.infoTitle}" type="text" datatype="*1-127"
										nullmsg='请输入标题  ' sucmsg='标题填写正确！' maxlength="127" style="width:80%">
									<span class="red">*</span>
								</td>
							</tr>
						<tr>
							<th width="15%">图片上传</th>
							<td width="85%" colspan="3">
				    			<input type="file" name="upload" id="upload"  datatype="*"
										nullmsg='请选择图片  ' sucmsg='图片选择正确！'/>
										<br/>
										<span class="red">*只能上传jpg、png、gif、bmp、jpeg格式文件</span>
				    		</td>
						</tr>
					</table>
				</div>

				</div>
				<div class="inner6px">
					<div class="btn_area_setc">
						<s:if test="flag=='add'">
							<a href="###" class="btn_01" onclick="save()">保存<b></b> </a>
						</s:if>
						<s:else>
							<a href="###" class="btn_01" type="submit">更新<b></b> </a>
						</s:else>
						<a href="###" class="btn_01"
							onclick="parent.close_win('win_sytp');">关闭<b></b> </a>
					</div>
				</div>
			</form>
		</div>
	</body>
</html>
