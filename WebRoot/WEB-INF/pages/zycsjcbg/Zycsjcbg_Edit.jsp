<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="zycsjcbgSave.action";
				document.myform1.submit();
			}
		}
		
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${zycsjcbg.linkid}&type="+obj);
        	}
		    function savepic(i,j){
        		window.location.href="${ctx}/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/safetysheet/safesheetImgDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
		    }
         function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qymcid").value="";
        }
        
        function queryQy()
		{
			var szzid = document.getElementById('deptId').value;
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid, '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qymcid').value = sonValue[0];
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('deptId').value = sonValue[2];
			}
		}
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zycsjcbg.id" value="${zycsjcbg.id}">
		<input type="hidden" name="zycsjcbg.createTime" value="<fmt:formatDate type="both" value="${zycsjcbg.createTime}" />">
		<input type="hidden" name="zycsjcbg.updateTime" value="${zycsjcbg.updateTime}">
		<input type="hidden" name="zycsjcbg.createUserID" value="${zycsjcbg.createUserID}">
		<input type="hidden" name="zycsjcbg.updateUserID" value="${zycsjcbg.updateUserID}">
		<input type="hidden" name="zycsjcbg.delFlag" value="${zycsjcbg.delFlag}">
		<input type="hidden" name="zycsjcbg.linkid" value="${zycsjcbg.linkid}">
		<input type="hidden" name="zycsjcbg.uploadtime" value="${zycsjcbg.uploadtime}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<s:if test="visable == 1">
					<tr>
				 		<th width="15%">所属地区</th>
						<td width="35%"><cus:SelectOneTag onchange="clearCompany()" style="width:200px;" property="zycsjcbg.deptId" defaultText='请选择' codeName="相城地址" value="${zycsjcbg.deptId}" /></td>
						<th width="15%">检查企业名称</th>
						<td width="35%">
							<input name="zycsjcbg.qymc" id="qymc" value="${zycsjcbg.qymc}" type="text" maxlength="255">
							<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>&nbsp;
							<input type="hidden" id="qymcid" name="zycsjcbg.comid" value="${zycsjcbg.comid}"/>
						</td>
					</tr>
				</s:if>
				<tr>
					<th width="15%">检测危害因素</th>
					<td width="35%"><input name="zycsjcbg.jcwz" value="${zycsjcbg.jcwz}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
					<th width="15%">检测点数</th>
					<td width="35%"><input name="zycsjcbg.jcds" value="${zycsjcbg.jcds}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">检测日期</th>
					<td width="35%"><input name="zycsjcbg.jcsj" value="${zycsjcbg.jcsj}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ><font style="color:red">*</font></td>
					<th width="15%">不合格点数</th>
					<td width="35%"><input name="zycsjcbg.bhgds" value="${zycsjcbg.bhgds}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">不合格点的危害因素名称</th>
					<td width="85%" colspan="3"><input name="zycsjcbg.bggName" value="${zycsjcbg.bggName}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" ><font style="color:red">*</font></td>
				</tr>
				<s:if test="visable == 0">
				<tr>
					<th width="15%">检测机构</th>
					<td width="35%">
						<s:select theme="simple" cssStyle="width:100px;" id="jcdwcode" emptyOption= "true" name="zycsjcbg.jcdwcode" list="%{deptList}" listKey="deptCode" listValue="deptName" value="{zycsjcbg.jcdwcode}"></s:select>
					</td>
				</tr>
				</s:if>
				<tr>
					<th width="15%">作业场所危害因素检测报告</th>
					<td colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('zycsjcbg')" title="点击上传文件">&nbsp;&nbsp;选择作业场所危害因素检测报告上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="zycsjcbg">
													<c:forEach var="item" items="${list}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																	<c:choose>
													    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
													    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
													   				&nbsp;&nbsp;&nbsp;<img src="/upload/file/${item.picName}"
																	border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:when> 
													    		<c:otherwise> 
													     			&nbsp;&nbsp;&nbsp;${item.fileName}
													    		</c:otherwise>
												  		 	</c:choose>
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
