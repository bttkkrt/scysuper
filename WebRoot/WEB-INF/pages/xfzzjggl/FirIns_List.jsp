<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>消防组织机构管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_firIns","添加消防组织机构","${ctx}/jsp/xfzzjggl/firInsInitEdit.action?flag=add&dt="+dt.getTime(),800,550);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_firIns","修改消防组织机构","${ctx}/jsp/xfzzjggl/firInsInitEdit.action?flag=mod&firIns.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_firIns","查看消防组织机构","${ctx}/jsp/xfzzjggl/firInsView.action?firIns.id="+row_Id+"&dt="+dt.getTime(),800,550);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_firIns();
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
		                	url : "firInsDel.action",
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
		                        	search_firIns();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_firIns(){
        	var queryParams = {
				"firIns.agencyNum": $("#agencyNum").val(),
"firIns.mobile": $("#mobile").val(),
"firIns.agencyName": $("#agencyName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
          var toolbar = [];
        	if('${roleName}'!='0'){//判断登录角色
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
				}];
				
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'消防组织机构列表',
				url:'firInsQuery.action',
				queryParams:{
					"firIns.agencyNum": $("#agencyNum").val(),
"firIns.mobile": $("#mobile").val(),
"firIns.agencyName": $("#agencyName").val()
				},
				frozenColumns:[[
				    {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != "${sessionScope['LOGIN_USER_ID']}"){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				          {field:'agencyNum',title:'机构编号',width:100},
{field:'mobile',title:'联系方式',width:100},
{field:'agencyName',title:'机构名称',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
if("${sessionScope['LOGIN_USER_ID']}"==rec.createUserID){
	return  button1;
}else{
	return button2;
}
}}				        ]],
						toolbar:toolbar
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
					
				<th width="15%">机构编号</th>
				<td width="35%"><input name="firIns.agencyNum" style="width: 50%" id="agencyNum" value="${firIns.agencyNum}" type="text" maxlength="127"></td>
				<th width="15%">联系方式</th>
				<td width="35%"><input name="firIns.mobile" style="width: 50%" id="mobile" value="${firIns.mobile}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">机构名称</th>
				<td width="35%"><input name="firIns.agencyName" style="width: 50%" id="agencyName" value="${firIns.agencyName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_firIns()" >查询<b></b></a>&nbsp;
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
