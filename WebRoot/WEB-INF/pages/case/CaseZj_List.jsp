<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>案件证据管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var loginUserId = "${loginUserId}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_caseZj","添加案件证据","${ctx}/jsp/case/uploadZjlbInitEdit.action?flag=add&caseZj.caseId=${caseZj.caseId}&dt="+dt.getTime(),900,550);
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseZj","修改案件证据","${ctx}/jsp/case/uploadZjlbInitEdit.action?flag=mod&caseZj.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseZj","查看案件证据","${ctx}/jsp/case/uploadZjlbView.action?caseZj.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_caseZj();
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
		                	url : "uploadZjlbDel.action",
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
		                        	search_caseZj();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function search_caseZj(){
        	var queryParams = {
"caseZj.caseId": '${caseZj.caseId}',
					"caseZj.zjContent": $("#zjContent").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'案件证据列表',
				url:'uploadZjlbList.action',
				queryParams:{
					"caseZj.caseId": '${caseZj.caseId}',
					"caseZj.zjContent": $("#zjContent").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'zjContent',title:'证据内容',width:100,formatter:function(value,rec){
	return value.substring(0,60);
}},
{field:'op',title:'操作',width:20,formatter:function(value,rec){
return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;";
}}
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
					<th width="15%">证据内容</th>
					<td width="35%">
						<input name="caseZj.zjContent" style="width:50%" id="zjContent" value="${caseZj.zjContent}" type="text" maxlength="127">
				    </td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_caseZj()" >查询<b></b></a>&nbsp;
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
