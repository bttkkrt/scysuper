<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>特种作业人员管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_speJobPer","添加特种作业人员","${ctx}/jsp/tzzyry/speJobPerInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_speJobPer","修改特种作业人员","${ctx}/jsp/tzzyry/speJobPerInitEdit.action?flag=mod&speJobPer.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_speJobPer","查看特种作业人员","${ctx}/jsp/tzzyry/speJobPerView.action?speJobPer.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_speJobPer();
        }
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "speJobPerDel.action",
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
		                        	search_speJobPer();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function importUser(){ 
        	createSimpleWindow("importUser","批量导入特种作业人员","${ctx}/jsp/tzzyry/initImportSpeJobPer.action", 350, 150);
        }
        function search_speJobPer(){
        	var queryParams = {
					"speJobPer.areaId": $("#areaId").val(),
"speJobPer.companyName": $("#companyName").val(),
"speJobPer.specialName": $("#specialName").val(),
"speJobPer.specialJobCradnum": $("#specialJobCradnum").val(),
"speJobPer.specialJobType": $("#specialJobType").val(),
 "querySpecialVerificationDateStart" :$("#querySpecialVerificationDateStart").val(),
 "querySpecialVerificationDateEnd" :$("#querySpecialVerificationDateEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
         var toolbar = [];
        var frozen=[];
        	if('${roleName}'==0){//判断登录角色
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
				},{
					text:'导入特种作业人员',
					iconCls:'icon-add',
					handler:importUser
				}];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'特种作业人员列表',
				url:'speJobPerQuery.action',
				queryParams:{
					"speJobPer.areaId": $("#areaId").val(),
"speJobPer.companyName": $("#companyName").val(),
"speJobPer.specialName": $("#specialName").val(),
"speJobPer.specialJobCradnum": $("#specialJobCradnum").val(),
"speJobPer.specialJobType": $("#specialJobType").val(),
 "querySpecialVerificationDateStart" :$("#querySpecialVerificationDateStart").val(),
 "querySpecialVerificationDateEnd" :$("#querySpecialVerificationDateEnd").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'specialName',title:'姓名',width:100},
{field:'specialJobType',title:'特种作业类型',width:100},
{field:'specialJobCradnum',title:'特种作业证号',width:100},
{field:'specialVerificationDate',title:'复审日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}				        ]],
				toolbar:toolbar
			})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
			<c:if test='${roleName!=0}'>
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="speJobPer.areaId" defaultText='请选择' codeName="企业属地" value="${speJobPer.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="speJobPer.companyName" style="width:50%;" id="companyName" value="${speJobPer.companyName}" type="text" maxlength="127"></td>
			</tr>
			</c:if>
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="speJobPer.specialName" style="width:50%;" id="specialName" value="${speJobPer.specialName}" type="text" maxlength="127"></td>
				<th width="15%">特种作业证号</th>
				<td width="35%"><input id="specialJobCradnum" style="width:50%;" name="speJobPer.specialJobCradnum" value="${speJobPer.specialJobCradnum}" type="text"  maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">特种作业类型</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="speJobPer.specialJobType" defaultText='请选择' codeName="特种作业类型" value="${speJobPer.specialJobType}"/></td>
				<th width="15%">复审日期</th>
				<td width="35%">
					<input name="querySpecialVerificationDateStart" id="querySpecialVerificationDateStart" value="<fmt:formatDate type='date' value='${querySpecialVerificationDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'querySpecialVerificationDateEnd\')}'})" >
					-<input name="querySpecialVerificationDateEnd" id="querySpecialVerificationDateEnd" value="<fmt:formatDate type='date' value='${querySpecialVerificationDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'querySpecialVerificationDateStart\')}'})" >
				</td>
			</tr>
			<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_speJobPer()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
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
