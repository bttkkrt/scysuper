<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>第二第三类非药品类易制毒化学品经营备案管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_preManRec","添加第二第三类非药品类易制毒化学品经营备案","${ctx}/jsp/premanrecord/preManRecInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_preManRec","修改第二第三类非药品类易制毒化学品经营备案","${ctx}/jsp/premanrecord/preManRecInitEdit.action?flag=mod&preManRec.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_preManRec","查看第二第三类非药品类易制毒化学品经营备案","${ctx}/jsp/premanrecord/preManRecView.action?preManRec.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_preManRec();
        }
       function del(){
        	var rows = document.getElementsByName('delBox');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
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
		                	url : "preManRecDel.action",
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
		                        	search_preManRec();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_preManRec(){
        	var queryParams = {
				"preManRec.areaId": $("#areaId").val(),
"preManRec.companyName": $("#companyName").val(),
"preManRec.receptName": $("#receptName").val(),
"preManRec.reviewName": $("#reviewName").val(),
"preManRec.fileNo": $("#fileNo").val(),
"preManRec.isComplete": $("#isComplete").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	 var roleName='';
	        var toolbar = [];
	        var frozen=[];
        	if("${operateRight}"=="add"){
		    	roleName='0';
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
				frozen=[[
					{field:'id',checkbox:true}
				]];	
			}else if("${operateRight}"=="qy"){
				document.getElementById('szqy').style.display='none';			                        	
			}else{
			}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'第二第三类非药品类易制毒化学品经营备案列表',
				url:'preManRecQuery.action',
				queryParams:{
					"preManRec.areaId": $("#areaId").val(),
"preManRec.companyName": $("#companyName").val(),
"preManRec.receptName": $("#receptName").val(),
"preManRec.reviewName": $("#reviewName").val(),
"preManRec.fileNo": $("#fileNo").val(),
"preManRec.isComplete": $("#isComplete").val()
				},
				//frozenColumns:frozen,
				columns:[[
{field:'id',width:25,formatter:function(value,rec){
	var box="<input type='checkbox' name='delBox' value='"+value+"'>";
	if(rec.createUserID=="${sessionScope['LOGIN_USER_ID']}"){
		return box;
	}else{
		return "";//return "<input type='checkbox' name='delBox' disabled value='"+value+"'>";
	}
	
}},
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'receptName',title:'材料接收人员',width:100},
{field:'reviewName',title:'材料审查人员',width:100},
{field:'fileNo',title:'档案编号',width:100},
{field:'isComplete',title:'申请材料是否齐全',width:100},
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
				<tr id="szqy" style="display:">
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="preManRec.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${preManRec.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="preManRec.companyName" id="companyName" style="width: 50%"  value="${preManRec.companyName}" type="text" maxlength="127"></td>
			</tr>
		<!-- 	<tr>
				<th width="15%">材料接收人员</th>
				<td width="35%"><input name="preManRec.receptName" id="receptName" style="width: 50%"  value="${preManRec.receptName}" type="text" maxlength="127"></td>
				<th width="15%">材料审查人员</th>
				<td width="35%"><input name="preManRec.reviewName" id="reviewName" style="width: 50%"  value="${preManRec.reviewName}" type="text" maxlength="127"></td>
			</tr>-->
			<tr>
				<th width="15%">档案编号</th>
				<td width="35%"><input name="preManRec.fileNo" id="fileNo" style="width: 50%"  value="${preManRec.fileNo}" type="text" maxlength="127"></td>
			<!-- 	<th width="15%">申请材料是否齐全</th>
				<td width="35%"><cus:SelectOneTag property="preManRec.isComplete" style="width: 50%"  defaultText='请选择' codeName="是或否" value="${preManRec.isComplete}" /></td>
			 --></tr> 
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_preManRec()" >查询<b></b></a>&nbsp;
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
