<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>监督检查结果表管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_supCheRes","添加监督检查结果表","${ctx}/jsp/jdjcjg/supCheResInitEdit.action?flag=add&dt="+dt.getTime(),700,450);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supCheRes","修改监督检查结果表","${ctx}/jsp/jdjcjg/supCheResInitEdit.action?flag=mod&supCheRes.id="+row_Id+"&dt="+dt.getTime(),700,450);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supCheRes","查看监督检查结果表","${ctx}/jsp/jdjcjg/supCheResView.action?supCheRes.id="+row_Id+"&dt="+dt.getTime(),700,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_supCheRes();
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
		                	url : "supCheResDel.action",
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
		                        	search_supCheRes();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_supCheRes(){
        	var queryParams = {
				"supCheRes.areaId": $("#areaId").val(),
"supCheRes.companyName": $("#companyName").val(),
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"supCheRes.checkUsername": $("#checkUsername").val(),
"supCheRes.checkInstrumentNum": $("#checkInstrumentNum").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'监督检查结果表列表',
				url:'supCheResQuery.action',
				queryParams:{
					"supCheRes.areaId": $("#areaId").val(),
"supCheRes.companyName": $("#companyName").val(),
 "queryCheckTimeStart" :$("#queryCheckTimeStart").val(),
 "queryCheckTimeEnd" :$("#queryCheckTimeEnd").val(),
"supCheRes.checkUsername": $("#checkUsername").val(),
"supCheRes.checkInstrumentNum": $("#checkInstrumentNum").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'checkTime',title:'检查时间',width:100},
{field:'checkUsername',title:'检查人员姓名',width:100},
{field:'checkInstrumentNum',title:'检查文书号',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}
				        ]],
				toolbar:[{
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
				<td width="35%"><cus:SelectOneTag property="supCheRes.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${supCheRes.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="supCheRes.companyName" id="companyName" style="width: 50%"  value="${supCheRes.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryCheckTimeStart" id="queryCheckTimeStart" value="<fmt:formatDate type='both' value='${queryCheckTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryCheckTimeEnd\')}'})" >
					-<input name="queryCheckTimeEnd" id="queryCheckTimeEnd" value="<fmt:formatDate type='both' value='${queryCheckTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryCheckTimeStart\')}'})" ></td>
				<th width="15%">检查人员姓名</th>
				<td width="35%"><input name="supCheRes.checkUsername" style="width: 50%"  id="checkUsername" value="${supCheRes.checkUsername}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">检查文书号</th>
				<td width="35%"><input name="supCheRes.checkInstrumentNum" style="width: 50%"  id="checkInstrumentNum" value="${supCheRes.checkInstrumentNum}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_supCheRes()" >查询<b></b></a>&nbsp;
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
