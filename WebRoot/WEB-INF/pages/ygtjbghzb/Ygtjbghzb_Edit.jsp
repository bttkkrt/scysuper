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
				
				document.myform1.action="ygtjbghzbSave.action";
				document.myform1.submit();
			}
		}
		
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUpload.action?safetysheet.linkid=${ygtjbghzb.linkid}&type="+obj);
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
		    
		function validate(event,obj)
        {
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
           	 	return; 
        	} 
        	obj.value = obj.value.replace(/[^\d]/g,""); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
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
		<input type="hidden" name="ygtjbghzb.id" value="${ygtjbghzb.id}">
		<input type="hidden" name="ygtjbghzb.createTime" value="<fmt:formatDate type="both" value="${ygtjbghzb.createTime}" />">
		<input type="hidden" name="ygtjbghzb.updateTime" value="${ygtjbghzb.updateTime}">
		<input type="hidden" name="ygtjbghzb.createUserID" value="${ygtjbghzb.createUserID}">
		<input type="hidden" name="ygtjbghzb.updateUserID" value="${ygtjbghzb.updateUserID}">
		<input type="hidden" name="ygtjbghzb.delFlag" value="${ygtjbghzb.delFlag}">
		<input type="hidden" name="ygtjbghzb.linkid" value="${ygtjbghzb.linkid}">
		<input type="hidden" name="ygtjbghzb.uploadtime" value="${ygtjbghzb.uploadtime}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<s:if test="visable == 1">
					<tr>
				 		<th width="15%">所属地区</th>
						<td width="35%"><cus:SelectOneTag onchange="clearCompany()" style="width:200px;" property="ygtjbghzb.deptId" defaultText='请选择' codeName="相城地址" value="${ygtjbghzb.deptId}" /></td>
						<th width="15%">检查企业名称</th>
						<td width="35%">
							<input name="ygtjbghzb.qymc" id="qymc" value="${ygtjbghzb.qymc}" type="text" maxlength="255">
							<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>&nbsp;
							<input type="hidden" id="qymcid" name="ygtjbghzb.comid" value="${ygtjbghzb.comid}"/>
						</td>
				    </tr>
				</s:if>
				<tr>
					<th width="15%">体检人数</th>
					<td width="35%"><input  style="width:200px;" name="ygtjbghzb.zrs" value="${ygtjbghzb.zrs}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font style="color:red">*</font></td>
					<th width="15%">体检日期</th>
					<td width="35%"><input style="width:200px;" name="ygtjbghzb.tjsj" value="<s:property value="ygtjbghzb.tjsj"/>" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style="color:red">*</font></td>
				</tr>
				<tr>
				    <th width="15%">体检类型</th>
					<td width="35%">
						<cus:SelectOneTag style="width:200px;" property="ygtjbghzb.tjlx" defaultText='请选择' codeName="体检类型" value="${ygtjbghzb.tjlx}" />
					</td>
					<th width="15%">体检机构</th>
					<td width="35%">
						<cus:SelectOneTag style="width:200px;" property="ygtjbghzb.tjjg" defaultText='请选择' codeName="体检机构" value="${ygtjbghzb.tjjg}" />
					</td>
				</tr>
				<tr>
					<th width="15%">正常人数</th>
					<td width="35%"><input name="ygtjbghzb.zcrs" value="${ygtjbghzb.zcrs}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font style="color:red">*</font></td>
				    <th width="15%">职业相关异常人数</th>
					<td width="35%"><input name="ygtjbghzb.zybrs" value="${ygtjbghzb.zybrs}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">职业禁忌人数</th>
					<td width="35%"><input name="ygtjbghzb.zyjjrs" value="${ygtjbghzb.zyjjrs}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font style="color:red">*</font></td>
					<th width="15%">疑似职业病人人数</th>
					<td width="35%"><input name="ygtjbghzb.yszybrs" value="${ygtjbghzb.yszybrs}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255" onKeyUp="validate(event,this)"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">职业病健康体检报告</th>
					<td colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('ygtjbghzb')" title="点击上传文件">&nbsp;&nbsp;选择职业病健康体检报告上传</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="ygtjbghzb">
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
					<td colspan="4" height="100px" style="text-align:center">
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
