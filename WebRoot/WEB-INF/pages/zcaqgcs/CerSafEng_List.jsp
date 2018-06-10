<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>注册安全工程师管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cerSafEng","添加注册安全工程师","${ctx}/jsp/zcaqgcs/cerSafEngInitEdit.action?flag=add&dt="+dt.getTime(),800,400);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cerSafEng","修改注册安全工程师","${ctx}/jsp/zcaqgcs/cerSafEngInitEdit.action?flag=mod&cerSafEng.id="+row_Id+"&dt="+dt.getTime(),800,400);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cerSafEng","查看注册安全工程师","${ctx}/jsp/zcaqgcs/cerSafEngView.action?cerSafEng.id="+row_Id+"&dt="+dt.getTime(),800,400);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cerSafEng();
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
		                	url : "cerSafEngDel.action",
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
		                        	search_cerSafEng();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cerSafEng(){
        	var queryParams = {
				"cerSafEng.areaId": $("#areaId").val(),
"cerSafEng.areaName": $("#areaName").val(),
"cerSafEng.companyName": $("#companyName").val(),
"cerSafEng.saftyName": $("#saftyName").val(),
"cerSafEng.idCard": $("#idCard").val(),
"cerSafEng.certificateNo": $("#certificateNo").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        var toolbar = [];
        	if('${roleName}'=='1'){//判断登录角色
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
				title:'注册安全工程师列表',
				url:'cerSafEngQuery.action',
				queryParams:{
					"cerSafEng.areaId": $("#areaId").val(),
"cerSafEng.areaName": $("#areaName").val(),
"cerSafEng.companyName": $("#companyName").val(),
"cerSafEng.saftyName": $("#saftyName").val(),
"cerSafEng.idCard": $("#idCard").val(),
"cerSafEng.certificateNo": $("#certificateNo").val()
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
					{field:'areaName',title:'所在区域',width:100},
				          {field:'companyName',title:'企业名称',width:100},
{field:'saftyName',title:'姓名',width:100},
{field:'idCard',title:'身份证',width:100},
{field:'certificateNo',title:'合格证号',width:100},
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
				<s:if test='roleName!="1"'>
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="cerSafEng.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${cerSafEng.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cerSafEng.companyName" style="width: 50%" id="companyName" value="${cerSafEng.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">合格证号</th>
				<td width="35%"><input name="cerSafEng.certificateNo" style="width: 50%" id="certificateNo" value="${cerSafEng.certificateNo}" type="text" maxlength="127"></td>
				<th width="15%">姓名</th>
				<td width="35%"><input name="cerSafEng.saftyName" style="width: 50%" id="saftyName" value="${cerSafEng.saftyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">身份证</th>
				<td width="35%"><input name="cerSafEng.idCard" style="width: 50%" id="idCard" value="${cerSafEng.idCard}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cerSafEng()" >查询<b></b></a>&nbsp;
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