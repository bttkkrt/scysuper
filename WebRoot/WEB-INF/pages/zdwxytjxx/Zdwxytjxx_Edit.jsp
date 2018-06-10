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
				
				document.myform1.action="zdwxytjxxSave.action";
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
				document.getElementById('qymc').value = sonValue[1];
				document.getElementById('szzid').value = sonValue[2];
			}
		}
		function clearCompany(){
        	document.getElementById("qymc").value="";
        	document.getElementById("qyid").value="";
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
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zdwxytjxx.id" value="${zdwxytjxx.id}">
		<input type="hidden" name="zdwxytjxx.createTime" value="<fmt:formatDate type="both" value="${zdwxytjxx.createTime}" />">
		<input type="hidden" name="zdwxytjxx.updateTime" value="${zdwxytjxx.updateTime}">
		<input type="hidden" name="zdwxytjxx.createUserID" value="${zdwxytjxx.createUserID}">
		<input type="hidden" name="zdwxytjxx.updateUserID" value="${zdwxytjxx.updateUserID}">
		<input type="hidden" name="zdwxytjxx.deptId" value="${zdwxytjxx.deptId}">
		<input type="hidden" name="zdwxytjxx.delFlag" value="${zdwxytjxx.delFlag}">
		<input type="hidden" id="tempDeptCode" name="tempDeptCode" value="${tempDeptCode}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%">
						<c:if test="${tempDeptCode != 1}">
							<select id="szzid" name="zdwxytjxx.szzid" style="width:135px;" onchange="clearCompany()" ><option value="">请选择</option></select><font style="color:red">*</font>
						</c:if>
						<c:if test="${tempDeptCode == 1}">
							<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
							<select id="szzid" name="zdwxytjxx.szzid" style="width:150px;" ><option value="">请选择</option></select>
							<font color="red">*</font>
						</c:if>
						<!-- 
						<s:if test="type == 1">
							<cus:SelectOneTag property="zdwxytjxx.szzid" defaultText='请选择' codeName="相城地址" value="${zdwxytjxx.szzid}" disable="true"/>
						</s:if>
						<s:else>
							<cus:SelectOneTag property="zdwxytjxx.szzid" defaultText='请选择' codeName="相城地址" value="${zdwxytjxx.szzid}" onchange="clearCompany()"/>
						</s:else> -->
					</td>
					<th width="15%">企业名称</th>
					<td width="35%">
					<input id="qymc" name="zdwxytjxx.qymc" type="text" maxlength="255" dataType="Require" msg="此项为必填" value="${zdwxytjxx.qymc}" readonly="readonly"><font style="color:red">*</font>
					<input name="zdwxytjxx.qyid" value="${zdwxytjxx.qyid}"  type="hidden" id="qyid" type="text" maxlength="255">
					<a href="#" class="easyui-linkbutton" onclick="javascript:queryQy()" iconCls="icon-save">选择</a>
					</td>
				</tr>
				<tr>
					<th width="15%">一级危险化学品重大危险源个数</th>
					<td width="35%"><input name="zdwxytjxx.yjgs" value="${zdwxytjxx.yjgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">二级危险化学品重大危险源个数</th>
					<td width="35%"><input name="zdwxytjxx.ejgs" value="${zdwxytjxx.ejgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">三级危险化学品重大危险源个数</th>
					<td width="35%"><input name="zdwxytjxx.sjgs" value="${zdwxytjxx.sjgs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">四级危险化学品重大危险源个数</th>
					<td width="35%"><input name="zdwxytjxx.sijigs" value="${zdwxytjxx.sijigs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">填报人</th>
					<td width="35%"><input name="zdwxytjxx.tbr" value="${zdwxytjxx.tbr}" type="text" maxlength="255"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="zdwxytjxx.lxdh" value="${zdwxytjxx.lxdh}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">填表日期</th>
					<td width="35%"><input name="zdwxytjxx.tbrq" value="<fmt:formatDate type='date' value='${zdwxytjxx.tbrq}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
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
