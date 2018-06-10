<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危险化学品生产或使用企业安全生产许可证管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheProLic","添加危险化学品生产或使用企业安全生产许可证","${ctx}/jsp/cheprolicense/cheProLicInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheProLic","修改危险化学品生产或使用企业安全生产许可证","${ctx}/jsp/cheprolicense/cheProLicInitEdit.action?flag=mod&cheProLic.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheProLic","查看危险化学品生产或使用企业安全生产许可证","${ctx}/jsp/cheprolicense/cheProLicView.action?cheProLic.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheProLic","审核危险化学品生产或使用企业安全生产许可证","${ctx}/jsp/cheprolicense/cheProLicCheck.action?flag=check&cheProLic.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheProLic();
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
		                	url : "cheProLicDel.action",
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
		                        	search_cheProLic();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheProLic(){
        	var queryParams = {
        	"cheProLic.areaId": $("#areaId").val(),
				"cheProLic.areaName": $("#areaName").val(),
"cheProLic.companyName": $("#companyName").val(),
"cheProLic.ratingAgenciesName": $("#ratingAgenciesName").val(),
"cheProLic.issuingAuthority": $("#issuingAuthority").val(),
"cheProLic.isComplete": $("#isComplete").val(),
 "queryReceptDateStart" :$("#queryReceptDateStart").val(),
 "queryReceptDateEnd" :$("#queryReceptDateEnd").val(),
"cheProLic.receptName": $("#receptName").val(),
"cheProLic.reviewName": $("#reviewName").val(),
"cheProLic.fileNo": $("#fileNo").val(),
 "queryIssuingDateStart" :$("#queryIssuingDateStart").val(),
 "cheProLic.auditState": $("#auditState").val(),
 "queryIssuingDateEnd" :$("#queryIssuingDateEnd").val()
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
				title:'危险化学品生产或使用企业安全生产许可证列表',
				url:'cheProLicQuery.action',
				queryParams:{
					"cheProLic.areaName": $("#areaName").val(),
"cheProLic.companyName": $("#companyName").val(),
"cheProLic.ratingAgenciesName": $("#ratingAgenciesName").val(),
"cheProLic.issuingAuthority": $("#issuingAuthority").val(),
"cheProLic.isComplete": $("#isComplete").val(),
 "queryReceptDateStart" :$("#queryReceptDateStart").val(),
 "queryReceptDateEnd" :$("#queryReceptDateEnd").val(),
"cheProLic.receptName": $("#receptName").val(),
"cheProLic.reviewName": $("#reviewName").val(),
"cheProLic.fileNo": $("#fileNo").val(),
 "queryIssuingDateStart" :$("#queryIssuingDateStart").val(),
 "queryIssuingDateEnd" :$("#queryIssuingDateEnd").val()
				},
				frozenColumns:[[
				   {field:'id',width:20,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if((rec.createUserID != "${sessionScope['LOGIN_USER_ID']}")||(rec.auditState=='待审核')||(rec.auditState=='审核通过')){
								opt = ''; 
						    } 
						    return opt ; 

				    }}
				]],
				columns:[[
				          {field:'areaName',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'ratingAgenciesName',title:'评价机构名称',width:100},
{field:'issuingAuthority',title:'发证机关',width:100},
{field:'receptDate',title:'材料接收日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'issuingDate',title:'发证日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'auditState',title:'审核状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=check('"+rec.id+"')>审核<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
		if(rec.auditState=='待审核'&&"true"=="${canCheck}"){
			return button1;
		}else if(rec.auditState=='审核未通过'&&(rec.createUserID == "${sessionScope['LOGIN_USER_ID']}")){
			return button3;
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
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="cheProLic.areaId" style="width: 50%" defaultText='请选择' codeName="企业属地" value="${cheProLic.areaId}"  /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cheProLic.companyName" style="width: 50%" id="companyName" value="${cheProLic.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">评价机构名称</th>
				<td width="35%"><input name="cheProLic.ratingAgenciesName" style="width: 50%" id="ratingAgenciesName" value="${cheProLic.ratingAgenciesName}" type="text" maxlength="127"></td>
				<th width="15%">发证机关</th>
				<td width="35%"><input name="cheProLic.issuingAuthority" style="width: 50%" id="issuingAuthority" value="${cheProLic.issuingAuthority}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">材料接收日期</th>
				<td width="35%"><input name="queryReceptDateStart" id="queryReceptDateStart" value="<fmt:formatDate type='both' value='${queryReceptDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryReceptDateEnd\')}'})" >
					-<input name="queryReceptDateEnd" id="queryReceptDateEnd" value="<fmt:formatDate type='both' value='${queryReceptDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryReceptDateStart\')}'})" ></td>
				<th width="15%">发证日期</th>
				<td width="35%"><input name="queryIssuingDateStart" id="queryIssuingDateStart" value="<fmt:formatDate type='both' value='${queryIssuingDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryIssuingDateEnd\')}'})" >
					-<input name="queryIssuingDateEnd" id="queryIssuingDateEnd" value="<fmt:formatDate type='both' value='${queryIssuingDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryIssuingDateStart\')}'})" ></td>
			</tr>
			<tr>

				<th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" name="cheProLic.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheProLic()" >查询<b></b></a>&nbsp;
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
