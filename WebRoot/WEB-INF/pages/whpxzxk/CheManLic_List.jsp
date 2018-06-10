<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>危险化学品经营企业的行政许可管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_cheManLic","添加危险化学品经营企业的行政许可","${ctx}/jsp/whpxzxk/cheManLicInitEdit.action?flag=add&dt="+dt.getTime(),1100,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheManLic","修改危险化学品经营企业的行政许可","${ctx}/jsp/whpxzxk/cheManLicInitEdit.action?flag=mod&cheManLic.id="+row_Id+"&dt="+dt.getTime(),1100,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheManLic","查看危险化学品经营企业的行政许可","${ctx}/jsp/whpxzxk/cheManLicView.action?cheManLic.id="+row_Id+"&dt="+dt.getTime(),1100,500);
        	
        }
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_cheManLic","审核危险化学品经营企业的行政许可","${ctx}/jsp/whpxzxk/cheManLicCheck.action?flag=check&cheManLic.id="+row_Id+"&dt="+dt.getTime(),1100,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_cheManLic();
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
		                	url : "cheManLicDel.action",
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
		                        	search_cheManLic();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_cheManLic(){
        	var queryParams = {
				"cheManLic.areaId": $("#areaId").val(),
"cheManLic.companyName": $("#companyName").val(),
"cheManLic.ratingAgencies": $("#ratingAgencies").val(),
 "queryReceiveDateStart" :$("#queryReceiveDateStart").val(),
 "queryReceiveDateEnd" :$("#queryReceiveDateEnd").val(),
 "queryDealDateStart" :$("#queryDealDateStart").val(),
 "queryDealDateEnd" :$("#queryDealDateEnd").val(),
 "queryLicenseValidStart" :$("#queryLicenseValidStart").val(),
 "queryLicenseValidEnd" :$("#queryLicenseValidEnd").val(),
"cheManLic.receivePerson": $("#receivePerson").val(),
"cheManLic.auditState": $("#auditState").val(),
"cheManLic.checkPerson": $("#checkPerson").val()
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
				title:'危险化学品经营企业的行政许可列表',
				url:'cheManLicQuery.action',
				queryParams:{
					"cheManLic.areaId": $("#areaId").val(),
"cheManLic.companyName": $("#companyName").val(),
"cheManLic.ratingAgencies": $("#ratingAgencies").val(),
 "queryReceiveDateStart" :$("#queryReceiveDateStart").val(),
 "queryReceiveDateEnd" :$("#queryReceiveDateEnd").val(),
 "queryDealDateStart" :$("#queryDealDateStart").val(),
 "queryDealDateEnd" :$("#queryDealDateEnd").val(),
 "queryLicenseValidStart" :$("#queryLicenseValidStart").val(),
 "queryLicenseValidEnd" :$("#queryLicenseValidEnd").val(),
"cheManLic.receivePerson": $("#receivePerson").val(),
"cheManLic.checkPerson": $("#checkPerson").val()
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
{field:'ratingAgencies',title:'评价机构',width:100},
{field:'receiveDate',title:'材料接收日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'dealDate',title:'受理材料日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'licenseValid',title:'许可证有效期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'receivePerson',title:'材料接收人员',width:100},
{field:'checkPerson',title:'材料审查人员',width:100},
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
				<s:if test='roleName!="1"'>
				<tr>
					
				<th width="15%">所在区域</th>
				<td width="35%"><cus:SelectOneTag property="cheManLic.areaId" style="width: 50%"  defaultText='请选择' codeName="企业属地" value="${cheManLic.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="cheManLic.companyName" style="width: 50%" id="companyName" value="${cheManLic.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">材料接收人员</th>
				<td width="35%"><input name="cheManLic.receivePerson" style="width: 50%" id="receivePerson" value="${cheManLic.receivePerson}" type="text" maxlength="127"></td>
				<th width="15%">材料审查人员</th>
				<td width="35%"><input name="cheManLic.checkPerson" style="width: 50%" id="checkPerson" value="${cheManLic.checkPerson}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">评价机构</th>
				<td width="35%"><input name="cheManLic.ratingAgencies" style="width: 50%" id="ratingAgencies" value="${cheManLic.ratingAgencies}" type="text" maxlength="127"></td>
				<th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" name="cheManLic.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_cheManLic()" >查询<b></b></a>&nbsp;
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
