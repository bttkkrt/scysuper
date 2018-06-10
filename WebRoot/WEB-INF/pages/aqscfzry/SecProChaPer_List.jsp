<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>安全生产管理人员管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_secProChaPer","添加安全生产管理人员","${ctx}/jsp/aqscfzry/secProChaPerInitEdit.action?flag=add&dt="+dt.getTime(),800,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProChaPer","修改安全生产管理人员","${ctx}/jsp/aqscfzry/secProChaPerInitEdit.action?flag=mod&secProChaPer.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_secProChaPer","查看安全生产管理人员","${ctx}/jsp/aqscfzry/secProChaPerView.action?secProChaPer.id="+row_Id+"&dt="+dt.getTime(),800,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_secProChaPer();
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
		                	url : "secProChaPerDel.action",
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
		                        	search_secProChaPer();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_secProChaPer(){
        	var queryParams = {
				"secProChaPer.areaId": $("#areaId").val(),
"secProChaPer.chargeSpecializedNum": $("#chargeSpecializedNum").val(),
"secProChaPer.chargeName": $("#chargeName").val(),
"secProChaPer.companyName": $("#companyName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
         var toolbar = [];
       	 var frozen=[];
        	if('${roleName}'==0){//判断登录角色
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
				},{
				    text:'导入安全生产负责人管理',
					iconCls:'icon-add',
					handler:importUser
				
				}
				];
				frozen=[[
				    {field:'id',checkbox:true}
				]];
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'安全生产管理人员列表',
				url:'secProChaPerQuery.action',
				queryParams:{
					"secProChaPer.areaId": $("#areaId").val(),
"secProChaPer.chargeSpecializedNum": $("#chargeSpecializedNum").val(),
"secProChaPer.chargeName": $("#chargeName").val(),
"secProChaPer.companyName": $("#companyName").val()
				},
				frozenColumns:frozen,
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'chargeName',title:'姓名',width:100},
{field:'chargeSpecializedNum',title:'安全生产管理员资格证号',width:100},
{field:'chargePhone',title:'联系方式',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	if('${roleName}'=='0'){
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	}else{
		return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	}

}}				        ]],
				toolbar:toolbar
			})); 
			if('${roleName}'=='0'){//判断登录角色
        	$('#pagination').datagrid('hideColumn', 'areaId');
        	$('#pagination').datagrid('hideColumn', 'companyName');
        	}
		});
function importUser(){ 
        	createSimpleWindow("importUser","批量安全生产人员","${ctx}/jsp/aqscfzry/initImportSecProChaPer.action", 350, 200);
        }
        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
			<c:if test='${roleName!=0}'>
				<tr>
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag style="width:50%;" property="secProChaPer.areaId" defaultText='请选择' codeName="企业属地" value="${secProChaPer.areaId}"  maxlength="127"/></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="secProChaPer.companyName" style="width:50%;" id="companyName" value="${secProChaPer.companyName}" type="text" maxlength="127"></td>
				
			</tr>
			</c:if>
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="secProChaPer.chargeName" style="width:50%;" id="chargeName" value="${secProChaPer.chargeName}" type="text" maxlength="127"></td>
				<th width="15%">安全生产管理员资格证号</th>
				<td width="35%"><input name="secProChaPer.chargeSpecializedNum" style="width:50%;" id="chargeSpecializedNum" value="${secProChaPer.chargeSpecializedNum}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_secProChaPer()" >查询<b></b></a>&nbsp;
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
