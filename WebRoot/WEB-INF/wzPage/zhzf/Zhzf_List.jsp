<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>综合执法管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_zhzf","添加综合执法","${ctx}/jsp/zhzf/zhzfInitEdit.action?flag=add&dt="+dt.getTime(),800,450);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_zhzf","修改综合执法","${ctx}/jsp/zhzf/zhzfInitEdit.action?flag=mod&zhzf.id="+row_Id+"&dt="+dt.getTime(),800,450);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_zhzf","查看综合执法","${ctx}/jsp/zhzf/zhzfView.action?zhzf.id="+row_Id+"&dt="+dt.getTime(),800,450);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zhzf();
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
		                	url : "zhzfDel.action",
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
		                        	search_zhzf();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_zhzf(){
        	var queryParams = {
				 "queryPublicDateStart" :$("#queryPublicDateStart").val(),
 "queryPublicDateEnd" :$("#queryPublicDateEnd").val(),
"zhzf.infoTitle": $("#infoTitle").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        function move(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_wzInfoManage","移动综合执法信息","${ctx}/jsp/wzInfoManage/wzInfoManageMove.action?flag=move&type=4&ids="+row_Id+"&dt="+dt.getTime(),500,150);
        	
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'综合执法列表',
				url:'zhzfQuery.action',
				queryParams:{
					 "queryPublicDateStart" :$("#queryPublicDateStart").val(),
 "queryPublicDateEnd" :$("#queryPublicDateEnd").val(),
"zhzf.infoTitle": $("#infoTitle").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          
{field:'infoTitle',title:'标题',width:100},
{field:'publicDate',title:'发布时间',width:100,formatter:function(value,rec){
						return value.substring(0,10);					
					}},
{field:'clickTime',title:'点击量',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>&nbsp;<a class='btn_04_mini' onclick=move('"+rec.id+"')>移动<b></b></a>";}}
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
					
				<th width="15%">发布时间</th>
				<td width="35%"><input name="queryPublicDateStart" id="queryPublicDateStart" value="<fmt:formatDate type='both' value='${queryPublicDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryPublicDateEnd\')}'})" >
					-<input name="queryPublicDateEnd" id="queryPublicDateEnd" value="<fmt:formatDate type='both' value='${queryPublicDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryPublicDateStart\')}'})" ></td>
				<th width="15%">标题</th>
				<td width="35%"><input name="zhzf.infoTitle" id="infoTitle" value="${zhzf.infoTitle}" type="text" style="width:50%"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_zhzf()" >查询<b></b></a>&nbsp;
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
