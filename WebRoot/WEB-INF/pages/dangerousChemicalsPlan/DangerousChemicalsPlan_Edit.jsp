<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script src="/ajj/webResources/My97DatePicker/WdatePicker.js"></script>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="dangerousChemicalsPlanSave.action";
				document.myform1.submit();
			}
		}
		
		function queryQy()
		{
			var szzid = document.getElementById('szzid').value;
			var OpenWindow = window.showModalDialog("${ctx}/jsp/company/queryCompanyList.action?company.dwdz1="+szzid, '', 'dialogHeight=600px;dialogWidth=800px;scroll=no,resizable=no, status=no');
			if(typeof(OpenWindow) != 'undefined')
			{
				var sonValue = OpenWindow.split(";");
				document.getElementById('qyid').value = sonValue[0];
				document.getElementById('companyName').value = sonValue[1];
				//document.getElementById('szzid').value = sonValue[2];
			}
		}
		
		 //图片上传 lj 2013-08-15 
        function uploadPhoto(obj){
        		window.open("dangerousChemicalsPlanUpload.action?dangerousChemicalsPlan.linkId=${dangerousChemicalsPlan.linkId}&flag="+obj);
        	}
         function savepic(i,j){
        		window.location.href="dangerousChemicalsPlanSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "dangerousChemicalsPlanImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
			$(function(){
			var tempDeptCode = $("#tempDeptCode").val();
			var szzidhu = $("#szzidhu").val();
			if(tempDeptCode != "1"){
		    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+tempDeptCode,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("--请选择--").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	if(szzidhu==json[i].id){
						    	option = $('<option></option>').text(json[i].name).val(json[i].id).attr("selected","selected");
								selectContainer.append(option); 
					    	}else{
						    	option = $('<option></option>').text(json[i].name).val(json[i].id);
								selectContainer.append(option); 
					    	}
					 	}
					},
					dateType:"json"
				});			
			}
		});
        function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	option = $('<option></option>').text(json[i].name).val(json[i].id);
							selectContainer.append(option); 
					 	}
					},
					dateType:"json"
				});
	    }
	</script>
	
</head>
<body>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" id="linkId" name="dangerousChemicalsPlan.linkId" value="${dangerousChemicalsPlan.linkId}"/>
		<input type="hidden" name="dangerousChemicalsPlan.id" value="${dangerousChemicalsPlan.id}">
		<input type="hidden" name="dangerousChemicalsPlan.createTime" value="<fmt:formatDate type="both" value="${dangerousChemicalsPlan.createTime}" />">
		<input type="hidden" name="dangerousChemicalsPlan.updateTime" value="${dangerousChemicalsPlan.updateTime}">
		<input type="hidden" name="dangerousChemicalsPlan.createUserID" value="${dangerousChemicalsPlan.createUserID}">
		<input type="hidden" name="dangerousChemicalsPlan.updateUserID" value="${dangerousChemicalsPlan.updateUserID}">
		<input type="hidden" name="dangerousChemicalsPlan.deptId" value="${dangerousChemicalsPlan.deptId}">
		<input type="hidden" name="dangerousChemicalsPlan.delFlag" value="${dangerousChemicalsPlan.delFlag}">
		<input name="dangerousChemicalsPlan.qyid" value="${dangerousChemicalsPlan.qyid}"  type="hidden" id="qyid">
		<input type="hidden" name="dangerousChemicalsPlan.szc" value="${dangerousChemicalsPlan.szc}">
		<input type="hidden" name="dangerousChemicalsPlan.szcname" value="${dangerousChemicalsPlan.szcname}">
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		<div style="position: absolute;width: 80%; left: 10%">
			<table width="100%">
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 42.0pt;">
						抚顺市安全生产监督管理局
					</td>
				</tr>
				<tr>
					<td>
						<hr style="height:5px;border:none;border-top:5px double;"/>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;font-family: 宋体;font-size: 22.0pt">
						危险化学品重大危险源通知书
					</td>	
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="gpdb.szzid" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><font style="color:red">*</font>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="gpdb.szzid" style="width:150px;" ><option value="">请选择</option></select>
							<font color="red">*</font>
						</c:if>
						<!--<cus:SelectOneTag property="mainPersonReceipt.szzid" defaultText='请选择' codeName="相城地址" value="${mainPersonReceipt.szzid}" dataType="Require" msg="此项为必选"/>-->
						<input id="companyName" name="dangerousChemicalsPlan.companyName" value="${dangerousChemicalsPlan.companyName}" type="text" dataType="Require" msg="此项为必填" maxlength="255" style="font-family:仿宋_GB2312;font-size: 16px "><a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>:
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						<textarea  name="dangerousChemicalsPlan.userName" rows="5" style="width: 100%">${dangerousChemicalsPlan.userName}</textarea>
					</td>
				</tr>
				<tr>
					<td height="50px">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
						联系人：<input name="dangerousChemicalsPlan.contact" value="${dangerousChemicalsPlan.contact}" type="text" dataType="Require" msg="此项为必填" maxlength="255">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系电话：<input name="dangerousChemicalsPlan.tel" value="${dangerousChemicalsPlan.tel}" type="text" dataType="Require" msg="此项为必填" maxlength="255">
					</td>
				</tr>
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					危险源级别：<s:select id="dangerLevel" listKey="key" listValue="value"  theme="simple" list="#{'':'','一级':'一级','二级':'二级','三级':'三级','四级':'四级'}" name="dangerousChemicalsPlan.dangerLevel" value="{dangerousChemicalsPlan.dangerLevel}"/>
					</td>
				</tr>
				<tr>
				    <td style="text-align: left;font-family:仿宋_GB2312;font-size: 16.0pt">
					危险化学品单位类型:<cus:hxcheckbox property="dangerousChemicalsPlan.dangerType" codeName="危险化学品单位类型" value="${dangerousChemicalsPlan.dangerType}" /></td>	
				</tr>	
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						备案日期：<input name="dangerousChemicalsPlan.planDate" value="${dangerousChemicalsPlan.planDate}" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>	
				<tr>
					<td style="text-align: left;font-family:仿宋_GB2312;font-size:  16.0pt">
						备案编号：<input name="dangerousChemicalsPlan.planId" value="${dangerousChemicalsPlan.planId}" type="text" dataType="Require" msg="此项为必填" maxlength="255">
					</td>
				</tr>
				
				<tr>
					<td>
						<a style="color:blue;" href="javascript:uploadPhoto('dangerousChemicalsPlan1')" title="点击上传附件">危险化学品重大危险源基本特征表</a>
					</td>
				</tr>
				<tr>
					<td>
						<div style="color:green;overflow:auto;height:160px;">
							<div>
								<table id="dangerousChemicalsPlan1">
									<c:forEach var="item" items="${picList01}">
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
				<tr>
					<td>
						<a style="color:blue;" href="javascript:uploadPhoto('dangerousChemicalsPlan2')" title="点击上传附件">危险化学品重大危险源备案申请表</a>
					</td>
				</tr>
				<tr>
					<td>
						<div style="color:green;overflow:auto;height:160px;">
							<div>
								<table id="dangerousChemicalsPlan2">
									<c:forEach var="item" items="${picList02}">
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
											<td width="25%">
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
				<tr>
					<td>
						<a style="color:blue;" href="javascript:uploadPhoto('dangerousChemicalsPlan3')" title="点击上传附件">危险化学品重大危险源备案登记表</a>
					</td>
				</tr>
				<tr>
					<td>
						<div style="color:green;overflow:auto;height:160px;">
							<div>
								<table id="dangerousChemicalsPlan3">
									<c:forEach var="item" items="${picList03}">
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
											<td width="25%">
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
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						（承办机构盖章）
					</td>
				</tr>
				<tr>
					<td style="text-align: right;font-family:仿宋_GB2312;font-size: 16.0pt">
						<input name="dangerousChemicalsPlan.sendTime" value="${dangerousChemicalsPlan.sendTime}" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
				</tr>
				<tr>
					<td height="100px" style="text-align:center">
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
		</div>
	</form>
</body>
</html>
