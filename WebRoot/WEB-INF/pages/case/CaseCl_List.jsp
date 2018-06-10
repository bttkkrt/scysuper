<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>案件材料管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var loginUserId = "${loginUserId}";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_caseCl","添加案件材料","${ctx}/jsp/case/uploadFileInitEdit.action?flag=add&caseCl.caseId=${caseCl.caseId}&dt="+dt.getTime(),900,550);
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseCl","修改案件材料","${ctx}/jsp/case/uploadFileInitEdit.action?flag=mod&caseCl.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_caseCl","查看案件材料","${ctx}/jsp/case/uploadFileView.action?caseCl.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_caseCl();
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
		                	url : "uploadFileDel.action",
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
		                        	search_caseCl();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function search_caseCl(){
        	var queryParams = {
"caseCl.caseId": '${caseCl.caseId}',
"caseCl.zjType": $("#zjType").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'案件材料列表',
				url:'uploadFileList.action',
				queryParams:{
					"caseCl.caseId": '${caseCl.caseId}',
"caseCl.zjType": $("#zjType").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'zjType',title:'材料类型',width:100,formatter:function(value,rec){
if(value == '1')
{
	return "现场照片";
}
else if(value == '2')
{
	return "罚没款收据回执";
}
}},
{field:'op',title:'操作',width:200,formatter:function(value,rec){
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
					<th width="15%">材料类型</th>
					<td width="35%">
						<s:select id="zjType" name="caseCl.zjType" list="#{'':'请选择','1':'现场照片','2':'罚没款收据回执'}"  theme="simple" cssStyle="width:50%"/>
				    </td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_caseCl()" >查询<b></b></a>&nbsp;
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
