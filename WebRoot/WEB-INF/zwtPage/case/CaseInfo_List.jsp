<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>案件管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        var flag = "${flag}";
        var loginUserId = "${loginUserId}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_caseInfo","添加案件","${ctx}/jsp/case/caseInfoInitEdit.action?flag=add&dt="+dt.getTime(),900,550);
        	//var id  = "win_caseInfo";
        	//var text = "添加案件-立案审批";
        	//var url = "/jsp/case/caseInfoInitEdit.action?flag=add&dt="+dt.getTime();
    		//window.parent.addTab(id,text,url);
        }
        function edit(row_Id,type){
        	var dt=new Date();
            createSimpleWindow("win_caseInfo","修改案件","${ctx}/jsp/case/caseInfoInitEdit.action?flag=mod&caseInfo.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,900,550);
        	//var id  = "win_caseInfo";
        	//var text = "修改案件-立案审批";
        	//var url = "/jsp/case/caseInfoInitEdit.action?flag=mod&caseInfo.id="+row_Id+"&dt="+dt.getTime();
        	//window.parent.addTab(id,text,url);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseInfo","查看案件","${ctx}/jsp/case/caseInfoView.action?caseInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	//var id  = "win_caseInfo";
        	//var text = "查看案件";
        	//var url = "/jsp/case/caseInfoView.action?caseInfo.id="+row_Id+"&dt="+dt.getTime();
        	//window.parent.addTab(id,text,url);
        }
        function editWs(row_Id,name)
        {
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","处理案件-"+name,"${ctx}/jsp/wsgl/queryWsType.action?instrumentsInfo.caseId=" + row_Id,950,550);
        }
        function shenhe(row_Id)
        {
        	var dt=new Date();
        	createSimpleWindow("win_caseInfo","审核案件-立案审批","${ctx}/jsp/case/caseInfoShenhe.action?caseInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	//var id  = "win_caseInfo";
        	//var text = "审核案件";
        	//var url = "/jsp/case/caseInfoShenhe.action?caseInfo.id="+row_Id+"&dt="+dt.getTime();
        	//window.parent.addTab(id,text,url);
        }
        
        function guidang(row_Id)
        {
        	var dt=new Date();
        	createSimpleWindow("win_caseInfo","案件归档","${ctx}/jsp/case/caseInfoGuidang.action?caseInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	//var id  = "win_caseInfo";
        	//var text = "案件归档";
        	//var url = "/jsp/case/caseInfoGuidang.action?caseInfo.id="+row_Id+"&dt="+dt.getTime();
        	//window.parent.addTab(id,text,url);
        }
        
        function uploadCl(row_Id,caseName)
        {
        	var id  = "win_caseCl";
        	var text = caseName + "-案件材料上传";
        	var url = "/jsp/case/initUploadFile.action?caseInfo.id="+row_Id;
        	window.parent.addTab(id,text,url);
        }
        
        function uploadZj(row_Id,caseName)
        {
        	var id  = "win_caseCl";
        	var text = caseName + "-证据上传";
        	var url = "/jsp/case/initUploadZjlb.action?caseInfo.id="+row_Id;
        	window.parent.addTab(id,text,url);
        }
        
        function wsList(row_Id,caseName)
        {
        	var id  = "win_instrumentsInfo";
        	var text = "查看" + caseName + "文书列表";
        	var url = "/jsp/wsgl/instrumentsInfoList.action?instrumentsInfo.caseId="+row_Id;
    		window.parent.addTab(id,text,url);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_caseInfo();
        }
        function del(){
       	 	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "caseInfoDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	search_caseInfo();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function checkPass(){
       	 	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项审核！');
			}else{
				var dt=new Date();
            	createSimpleWindow("win_caseInfo","审核案件","${ctx}/jsp/case/caseInfoShenheAll.action?flag=mod&ids="+ids+"&dt="+dt.getTime(),600,300);
			}
        }
        function search_caseInfo(){
        	var queryParams = {
				"caseInfo.areaId": $("#areaId").val(),
"caseInfo.companyName": $("#companyName").val(),
"caseInfo.caseId": $("#caseId").val(),
"caseInfo.caseSource": $("#caseSource").val(),
"caseInfo.caseStatus": $("#caseStatus").val(),
"caseInfo.caseName": $("#caseName").val(),
 "queryCaseTimeStart" :$("#queryCaseTimeStart").val(),
 "queryCaseTimeEnd" :$("#queryCaseTimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	if(flag.indexOf("A10") != -1 && flag.indexOf("A29") != -1)
        	{
        		toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				},{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}]
        	}
        	else if(flag.indexOf("A10") != -1)
        	{
        		toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				}];
        	}
        	else if(flag.indexOf("A29") != -1)
        	{
        		toolbar = [{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}];
        	}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'案件列表',
				url:'caseInfoQuery.action',
				queryParams:{
					"caseInfo.areaId": $("#areaId").val(),
"caseInfo.companyName": $("#companyName").val(),
"caseInfo.caseId": $("#caseId").val(),
"caseInfo.caseSource": $("#caseSource").val(),
"caseInfo.caseStatus": $("#caseStatus").val(),
"caseInfo.caseName": $("#caseName").val(),
 "queryCaseTimeStart" :$("#queryCaseTimeStart").val(),
 "queryCaseTimeEnd" :$("#queryCaseTimeEnd").val()
				},
				frozenColumns:[[
				    {field:'id',width:30,formatter:function(value,rec){
				    		var opt = ''; 
						    if((rec.caseStatus == 7 && rec.undertakerId == loginUserId)
						    || (flag.indexOf("A02") != -1 && rec.caseStatus == 1)
						    || (flag.indexOf("A09") != -1 && rec.caseStatus == 0)
						    || (flag.indexOf("A29") != -1)
						    ){//这里判断创建人是不是登录人
								opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:50},
{field:'companyName',title:'企业名称',width:150},
{field:'caseName',title:'案件名称',width:100},
{field:'caseTime',title:'案件时间',width:70,formatter:function(value,rec){return value.substring(0,10);}},
{field:'caseSource',title:'案件来源',width:50},
{field:'undertakerName',title:'承办人',width:70},
{field:'caseStatus',title:'案件状态',width:60,formatter:function(value,rec){
if(value == '0' || value == '1' || value == '7' || value == '8')
{
	return "待审核";
}
else if(value == '2')
{
	return "执行中";
}
else if(value == '3')
{
	return "已结案";
}
else if(value == '4')
{
	return "已归档";
}
else if(value == '5')
{
	return "审核不通过";
}
}},
{field:'op',title:'操作',width:250,formatter:function(value,rec){
var aa = "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=wsList('"+rec.id+"','"+rec.caseName+"')>文书<b></b></a>&nbsp;";
if(flag.indexOf("A10") != -1 && rec.caseStatus == '2')
{
	aa += "<a class='btn_04_mini' onclick=editWs('"+rec.id+"','"+rec.caseName+"')>处理<b></b></a>&nbsp;";
}
if(flag.indexOf("A10") != -1 && (rec.caseStatus == '2' || rec.caseStatus == '3'))
{
	aa += "<a class='btn_04_mini' onclick=uploadCl('"+rec.id+"','"+rec.caseName+"')>材料<b></b></a>&nbsp;<a class='btn_05_mini' onclick=uploadZj('"+rec.id+"','"+rec.caseName+"')>证据<b></b></a>&nbsp;";
}
if(rec.caseStatus == '5' && rec.createUserID == loginUserId)
{
	aa += "<a class='btn_04_mini' onclick=edit('"+rec.id+"','0')>修改<b></b></a>&nbsp;";
}
if((rec.caseStatus == '0' && flag.indexOf("A09") != -1) || (rec.caseStatus == '1' && flag.indexOf("A02") != -1) || (rec.caseStatus == '7' && rec.undertakerId == loginUserId) || (rec.caseStatus == '8' && flag.indexOf("A30") != -1))
{
	aa += "<a class='btn_04_mini' onclick=shenhe('"+rec.id+"')>审核<b></b></a>&nbsp;";
}
if(rec.caseStatus == '3' && rec.createUserID == loginUserId)
{
	aa += "<a class='btn_04_mini' onclick=guidang('"+rec.id+"')>归档<b></b></a>&nbsp;";
}
if(flag.indexOf("A01") != -1 || flag.indexOf("A29") != -1)
{
	aa += "<a class='btn_05_mini' onclick=edit('"+rec.id+"','1')>修改<b></b></a>&nbsp;";
}
return aa;

}}
				        ]],
				toolbar:toolbar
			}));
		});

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%">
					<cus:SelectOneTag property="caseInfo.areaId" style="width:50%" defaultText='请选择' codeName="企业属地" value="${caseInfo.areaId}" maxlength="127"/>
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="caseInfo.companyName" style="width:50%" id="companyName" value="${caseInfo.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">案件名称</th>
				<td width="35%"><input name="caseInfo.caseName" style="width:50%" id="caseName" value="${caseInfo.caseName}" type="text" maxlength="127"></td>
				<th width="15%">案件时间</th>
				<td width="35%"><input name="queryCaseTimeStart" id="queryCaseTimeStart" value="<fmt:formatDate type='date' value='${queryCaseTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryCaseTimeEnd\')}'})" >
					-<input name="queryCaseTimeEnd" id="queryCaseTimeEnd" value="<fmt:formatDate type='date' value='${queryCaseTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryCaseTimeStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">案件来源</th>
				<td width="35%">
					<cus:SelectOneTag property="caseInfo.caseSource" style="width:50%" defaultText='请选择' codeName="案件来源" value="${caseInfo.caseSource}" maxlength="127"/>
				</td>
				<th width="15%">案件状态</th>
				<td width="35%">
					<s:select id="caseStatus" name="caseInfo.caseStatus" cssStyle="width:50%" list='#{"":"--请选择---","0":"待审核","2":"执行中","3":"已结案","4":"已归档","5":"审核不通过"}' theme="simple"></s:select>
				</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_caseInfo()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;	
						<s:if test='flag.indexOf("A02") != -1 || flag.indexOf("A09") != -1 || flag.indexOf("A10") != -1 || flag.indexOf("A30") != -1'>
							<a href="###" class="btn_01" onclick="checkPass()" >审核<b></b></a>&nbsp;
						</s:if>	
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>
