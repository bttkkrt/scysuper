<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>字段管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<%@include file="/common/jsLib.jsp"%>
		<script>

		$(function(){
			var lastIndex = "";
			$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				toolbar:[{
					text:'新增',
					iconCls:'icon-add',
					handler:function(){
						editField(-1);
					}
				},'-',{
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				},'-',{
					text:'取消修改',
					iconCls:'icon-undo',
					handler:function(){
						$('#pagination').datagrid('rejectChanges');
					}
				},'-',{
					text:'保存修改',
					iconCls:'icon-save',
					handler:function(){
						update();
					}
				}],
				title:'${formTable.tableName}字段列表',
				queryParams:{
					tableId : "${tableId}"
				},
				url:'formFieldList.action',
				columns:[[
	                      {field:'id',checkbox:true},
				          {field:'fieldDisplayName',title:'显示名称',width:fixWidth(0.13)},
				          {field:'fieldName',title:'字段名',width:fixWidth(0.14)},
				          {field:'fieldType',title:'数据类型',width:fixWidth(0.1)},
				          {field:'fieldDisplayType',title:'显示方式',width:fixWidth(0.06)},
				          {field:'dispInGrid',title:'列表',width:fixWidth(0.06),editor:{type:'checkbox',options:{on:'1',off:'0'}},formatter:function(value,rec){
				        	  if(rec.dispInGrid==1)
				        		  return "是";
				        	  else
				        		  return "否";
				          }},
				          {field:'isQueryCondition',title:'查询',width:fixWidth(0.06),editor:{type:'checkbox',options:{on:'1',off:'0'}},formatter:function(value,rec){
				        	  if(rec.isQueryCondition==1)
				        		  return "是";
				        	  else
				        		  return "否";
				          }},
				          {field:'isSortField',title:'排序',width:fixWidth(0.06),editor:{type:'checkbox',options:{on:'1',off:'0'}},formatter:function(value,rec){
				        	  if(rec.isSortField==1)
				        		  return "是";
				        	  else
				        		  return "否";
				          }},
				          {field:'columnWidth',title:'列表宽度',width:fixWidth(0.08),editor:{
				        	  type :'text'
				          }},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
				        	  var html = "<a  class='btn_01_mini' onclick='javascript:editField(\""+rec.id+"\")'>修改<b></b></a>";
				        	  return html;
				          }}
				        ]],
				onBeforeLoad:function(){
					$(this).datagrid('rejectChanges');
				},
				onClickRow:function(rowIndex){
					if(rowIndex==0)
						return;
					if (lastIndex != rowIndex){
						$('#pagination').datagrid('endEdit', lastIndex);
						$('#pagination').datagrid('beginEdit', rowIndex);
					}
					lastIndex = rowIndex;
				}
			})); 
					
		});
		function reloadDate(){
			$('#pagination').datagrid('clearSelections');
			$("#pagination").datagrid('reload'); 
	    }
        
        function update(){
			var rows = $('#pagination').datagrid('getChanges');
			var result = "";
			for(var i = 0;i<rows.length;i++){
				var row = rows[i];
				$.ajax({
        		    url: 'formFieldListUp.action',
        		    type: 'post',
        		    dataType: 'json',
        		    async : false,
        		    data:{
        		    	"field.dispInGrid" : row.dispInGrid,
        		    	"field.isQueryCondition" : row.isQueryCondition,
        		    	"field.isSortField" : row.isSortField,
        		    	"field.id" : row.id,
        		    	"field.columnWidth" : row.columnWidth
        		    },
        		    success: function(data){
        		    	if(data.result){
        		    		result += row.fieldName+"修改成功<br>";
        		    	}else{
        		    		result += row.fieldName+"修改失败<br>";
        		    	}
        		    }
        		});
			}
			$.messager.alert("提示",result);
			$("#pagination").datagrid('reload'); 
        }
        function editField(id){
        	if(id==-1)
        		title = "添加字段";
        	else
        		title = "修改字段";
        	
        	createSimpleWindow("fieldWindow",title,"${ctx}/jsp/admin/form/formFieldGet.action?field.id="+id+"&tableId=<s:property value="tableId"/>", 700, 420);
        }
        function del(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				if(rows[i].fieldName=='ROW_ID'){
					$.messager.alert('提示','不能删除主键！');
					return;
				}
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "formFieldDelA.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : ids,
		                    	tableId : "${tableId}"
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	reloadDate();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
    </script>
	</head>

	<body>
	<div class="page_content">
	<div class="box_01 boxBmargin12 submitdata">
		<div class="inner12px">
			
		<div id="pagination" ></div>
		</div></div></div>
	</body>
</html>
