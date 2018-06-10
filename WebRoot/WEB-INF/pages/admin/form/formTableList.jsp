<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>工作表单管理</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/h-ui/js/H-ui.js"></script> 
	<script type="text/javascript" src="${ctx}/h-ui/lib/lazyload/1.9.3/jquery.lazyload.js"></script>   
	<script type="text/javascript" src="${ctx}/h-ui/lib/select2-4.0.2/js/select2.min.js"></script>
	<script type="text/javascript" src="${ctx}/h-ui/lib/icheck/jquery.icheck.min.js"></script>
	<script type="text/javascript" src="${ctx}/h-ui/js/H-ui.admin.js"></script> 
	<script>
		$(function(){
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						editTable(-1);
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:del
				},'-',{
					text:'重建数据表',
					iconCls:'icon-save',
					handler:recreatetable
				},'-',{
					text:'生成代码',
					iconCls:'icon-save',
					handler:genCodeALL
				}],
				title:'表单列表',
				url:'formTableList.action',
				columns:[[
	                      {field:'id',checkbox:true,width:0.05},
				          {field:'tableName',title:'表单名称',width:0.2},
				          {field:'physicalName',title:'数据表名称',width:0.3},
				          {field:'sortSQ',title:'同级排序',width:0.15},
				          {field:'op',title:'操作',width:0.3,formatter:function(value,rec){
				        	  return "<a href='javascript:void(0)' class='btn_01_mini' onclick='Hui_admin_tab(this)' data-title='字段设置' _href='${ctx}/jsp/admin/form/formFieldInitList.action?tableId="+rec.id+"'>字段设置<b></b></a>"
									+"<a class='btn_01_mini' onclick=\"editTable('"+rec.id+"')\">属性修改<b></b></a>";
									//+"<button class='btn' onclick=\"viewRecord('"+rec.id+"')\">查看记录</button>";
				          }}
				        ]]
			}));
		});
		
		function reloadDate(){
			var queryParams = {
					categoryNum : "${categoryNum}",
					"model.tableName" : $("#tableName").val(),
					"model.physicalName" : $("#physicalName").val()
				}
			$('#pagination').datagrid('clearSelections');
			$('#pagination').datagrid('options').queryParams = queryParams;
			$("#pagination").datagrid('reload'); 
	    }
		
	    
		function fieldList(id){
			parent.addTab("editformTableField","字段设置","/jsp/admin/form/formFieldInitList.action?tableId="+id)
	 	}       
        function editTable(obj){
        	if(obj==-1){
        		title = "新建表单";
        		createSimpleWindow("formWindow",title,"${ctx}/jsp/admin/form/formTableGet.action?model.id="+obj, 700, 250);
        	}     		
        	else{
        		title = "修改表单";
            	createSimpleWindow("formWindow",title,"${ctx}/jsp/admin/form/formTableGet.action?model.id="+obj, 700, 350);
        	}
        }
        function editField(obj){
        	window.location.href="formFieldList.action?tableId="+obj;
        }
        function viewRecord(obj){
        	window.location.href="viewList.action?tableId="+obj;
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
			        	$.messager.confirm("提示","是否要删除物理表?<br>”确定“表示删除；”取消“不删除",function(result){
			        		var flag = 0;
			        		if(result){
			        			flag = 1;
			                }
			                $.ajax({
			                	url : "formTableDel.action?flag="+flag,
			                	type: 'post',
			                    dataType: 'json',
			                    async : false,
			                    data:{ 
			                    	tableIds : ids
			                    },
			                    error: function(){
			                    	$.messager.alert('错误','删除时出错！');
			                    },
			                    success: function(data){
			                        if(data.result){
			                        	$.messager.alert('提示','删除成功！');
			                        	reloadDate();
			                        }else{
			                        	$.messager.alert('错误','删除时出错！！');
			                        }
			                    }
			                });
			        	});		                
			        }
			    });
			}
        }
        function recreatetable()
        {
        	var rows = $('#pagination').datagrid('getSelections');
        	
			if(rows.length!=1){
			    $.messager.alert('提示','只能选择一张表进行重建！');
			}else{
				$.messager.confirm("重建表","重建数据表会丢失数据表中所有的记录，确定需要重建吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "reCreateTable.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	tableIds : rows[0].id
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','重建表时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','重建成功！');
		                        	reloadDate();
		                        }else{
		                        	$.messager.alert('错误','重建时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }

        function genCodeALL(){
        	var rows = $('#pagination').datagrid('getSelections');
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一张表生成代码！');
			}else{
				var ids = "";
				for(var i=0;i<rows.length;i++){
					ids += rows[i].id+"|";
				}
				createSimpleWindow("geneWindow","生成代码","${ctx}/jsp/admin/form/genALLCode.action?thistableIds="+ids, 400, 150);
			}
        }
        
        function import_table(){
        	location.href = "${ctx}/jsp/admin/form/importFormInit.action";
        }
    </script>
</head>

<body>
    <div class="page_content">
		<div class="box_01 submitdata">
			<div class="inner12px">
				<form name="myform" method="post">
				<div class="cell boxBmargin12">
					<table>
						<tr>
							<th>表单名称</th>
							<td><input class="form_text" id="tableName"/></td>
							<th>物理表名</th>
							<td><input class="form_text" id="physicalName"/></td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<div class="btn_area_setc">
									<a href="###" class="btn_01" onclick="reloadDate()">查询<b></b></a> 
									<a href="###" class="btn_01" onclick="import_table()">导入表单<b></b></a>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div id="pagination"></div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
