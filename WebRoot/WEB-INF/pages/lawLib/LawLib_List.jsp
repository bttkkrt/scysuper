<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>法律法规资源库管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_lawLib","添加法律法规资源库","${ctx}/jsp/lawLib/lawLibInitEdit.action?flag=add&dt="+dt.getTime(),1000,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_lawLib","修改法律法规资源库","${ctx}/jsp/lawLib/lawLibInitEdit.action?flag=mod&lawLib.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_lawLib","查看法律法规资源库","${ctx}/jsp/lawLib/lawLibView.action?lawLib.id="+row_Id+"&dt="+dt.getTime(),1000,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_lawLib();
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
		                	url : "lawLibDel.action",
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
		                        	search_lawLib();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_lawLib(){
        	var queryParams = {
				"lawLib.lawName": $("#lawName").val(),
"lawLib.state": $("#state").val(),
"lawLib.type": $("#type").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var frozen=[[
				    {field:'id',width:20,formatter:function(value,rec){
				    		if(("${roleName}"=="1"&&rec.state=="1")||("${roleName}"=="0"&&rec.state=="0")){
				    			 return '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
				    		}else{
				    			return '';
				    		}

				    }}
				]];
				
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'法律法规资源库列表',
				url:'lawLibQuery.action',
				queryParams:{
					"lawLib.lawName": $("#lawName").val(),
"lawLib.state": $("#state").val(),
"lawLib.type": $("#type").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'lawName',title:'法律法规名称',width:100},
				          {field:'type',title:'法律法规类型',width:100,formatter:function(value,rec){
				          	if(value == '1')
				          	{
				          		return "法律";
				          	}
				          	else if(value == '2')
				          	{
				          		return "法规";
				          	}
				          	else if(value == '3')
				          	{
				          		return "规章";
				          	}
				          	else if(value == '4')
				          	{
				          		return "规范性文件";
				          	}
				          	else
				          	{
				          		return "技术标准";
				          	}
							}},
//{field:'state',title:'状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	//安监 转入标准库
	if("${roleName}"=="1"&&rec.state=="0"){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=turn('"+rec.id+"')>转入标准库<b></b></a>";
	}
	//安监 修改
	if("${roleName}"=="1"&&rec.state=="1"){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}
	//企业 可修改
	if("${roleName}"=="0"&&rec.state=="0"){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}
	//企业 不可修改
	if("${roleName}"=="0"&&rec.state=="1"){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}
	
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

        
        function turn(id){
        	 $.messager.confirm("转入标准库","确定要转入标准库吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "lawLibTurn.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : id
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','转入标准库时出错1！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','转入标准库成功！');
		                        	search_lawLib();
		                        }else{
		                        	$.messager.alert('错误','转入标准库时出错2！');
		                        }
		                    }
		                });
			        }
			    });
        
        }
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
				<th width="15%">法律法规名称</th>
				<td width="35%"><input name="lawLib.lawName" id="lawName" value="${lawLib.lawName}" type="text" style="width:50%"></td>
				<th width="15%">法律法规类型</th>
				<td width="35%"><s:select id="type" name="lawLib.type" list="#{'':'请选择','1':'法律','2':'法规','3':'规章','4':'规范性文件','5':'技术标准'}"  theme="simple"  cssStyle="width:50%"/></td>
			</tr>
			<tr>
				<th width="15%">状态</th>
				<td width="35%">
					<select name="lawLib.state" id="state" style="width:50%">
						<option value="">请选择</option>
						<option value="0">待转入</option>
						<option value="1">标准库</option>
					</select>
				</td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_lawLib()" >查询<b></b></a>&nbsp;
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
