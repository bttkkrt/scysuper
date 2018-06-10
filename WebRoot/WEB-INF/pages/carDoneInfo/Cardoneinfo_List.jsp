<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>视频通道管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
       function turnTotime(guid,name){
        	var id  = "win_CarEquipment";
        	var text = name + "-录像查看";
        	var url = "/jsp/carDoneInfo/turnTotime.action?carEquipment.guid="+guid;
        	window.parent.addTab(id,text,url);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_carEquipment();
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
		                	url : "carEquipmentDel.action",
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
		                        	search_carEquipment();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_carEquipment(){
        	var queryParams = {
				"carEquipment.companyname": $("#companyname").val(),
"carEquipment.detailName": $("#detailName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'视频通道列表',
				url:'carEquipmentQuery.action',
				queryParams:{
					"carEquipment.companyname": $("#companyname").val(),
"carEquipment.detailName": $("#detailName").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'companyname',title:'企业名称',width:100},
{field:'detailName',title:'通道名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=turnTotime('"+rec.guid+"','" + rec.detailName + "') >查看<b></b></a>";}}
				        ]],
				toolbar:[]
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
					
				<th width="15%">企业名称</th>
				<td width="35%"><input name="carEquipment.companyname" id="companyname" value="${carEquipment.companyname}" type="text"></td>
				<th width="15%">通道名称</th>
				<td width="35%"><input name="carEquipment.detailName" id="detailName" value="${carEquipment.detailName}" type="text"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_carEquipment()" >查询<b></b></a>&nbsp;
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
