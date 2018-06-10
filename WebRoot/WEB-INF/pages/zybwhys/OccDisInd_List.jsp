<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>职业病危害因素管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var occDisId=$("#occDisId").val();
        	var dt=new Date();
            createSimpleWindow("win_occDisInd","添加职业病危害因素","${ctx}/jsp/zybwhys/occDisIndInitEdit.action?flag=add&occDis.id="+occDisId+"&dt="+dt.getTime(),900,250);
        	
        }
        function edit(row_Id){
       	 	var occDisId=$("#occDisId").val();
        	var dt=new Date();
            createSimpleWindow("win_occDisInd","修改职业病危害因素","${ctx}/jsp/zybwhys/occDisIndInitEdit.action?flag=mod&occDis.id="+occDisId+"&occDisInd.id="+row_Id+"&dt="+dt.getTime(),900,250);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_occDisInd","查看职业病危害因素","${ctx}/jsp/zybwhys/occDisIndView.action?occDisInd.id="+row_Id+"&dt="+dt.getTime(),900,250);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_occDisInd();
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
		                	url : "occDisIndDel.action",
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
		                        	search_occDisInd();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_occDisInd(){
        	var queryParams = {
				"occDisInd.occupationalDiseaseName": $("#occupationalDiseaseName").val(),
				"occDis.id": $("#occDisId").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'职业病危害因素列表',
				url:'occDisIndQuery.action',
				queryParams:{
					"occDisInd.occupationalDiseaseName": $("#occupationalDiseaseName").val(),
					"occDis.id": $("#occDisId").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'occupationalDiseaseName',title:'职业病危害因素名称',width:100},
{field:'fieldConcentration',title:'现场浓度',width:100},
{field:'contactNumber',title:'接触人数（可重复）',width:100},
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
	function sub(){
		document.myform1.submit();
	}
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
		
		<div class="box_01 boxBmargin12 submitdata">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="../zybwhysfbqk/occDisSave.action">
		<s:token />
		<input type="hidden" name="flag" value="update">
		<input type="hidden" name="occDis.id" value="${occDis.id}" id="occDisId">
		<input type="hidden" name="occDis.createTime" value="<fmt:formatDate type="both" value="${occDis.createTime}" />">
		<input type="hidden" name="occDis.updateTime" value="${occDis.updateTime}">
		<input type="hidden" name="occDis.createUserID" value="${occDis.createUserID}">
		<input type="hidden" name="occDis.updateUserID" value="${occDis.updateUserID}">
		<input type="hidden" name="occDis.deptId" value="${occDis.deptId}">
		<input type="hidden" name="occDis.delFlag" value="${occDis.delFlag}">
		<input type="hidden" name="occDis.proId" value="${occHazBas.id}">
		<input type="hidden" name="occHazBas.id" value="${occHazBas.id}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">作业场所名称</th>
					<td width="35%">${occDis.workPlace}</td>
					<th width="15%">接触人数</th>
					<td width="35%">${occDis.contactNum}</td>
				</tr>
			</table>
	</form>
	</div></div></div>
		
			<div class="cell boxBmargin12" style="display:none">
			<table width="100%">
				<tr>
					
				<th width="15%">职业病危害因素名称</th>
				<td width="35%"><input name="occDisInd.occupationalDiseaseName" style="width: 50%" id="occupationalDiseaseName" value="${occDisInd.occupationalDiseaseName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_occDisInd()" >查询<b></b></a>&nbsp;
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
