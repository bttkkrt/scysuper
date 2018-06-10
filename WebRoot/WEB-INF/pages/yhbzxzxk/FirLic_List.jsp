<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>烟花爆竹行政许可管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_firLic","添加烟花爆竹行政许可","${ctx}/jsp/yhbzxzxk/firLicInitEdit.action?flag=add&dt="+dt.getTime(),1150,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_firLic","修改烟花爆竹行政许可","${ctx}/jsp/yhbzxzxk/firLicInitEdit.action?flag=mod&firLic.id="+row_Id+"&dt="+dt.getTime(),1150,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_firLic","查看烟花爆竹行政许可","${ctx}/jsp/yhbzxzxk/firLicView.action?firLic.id="+row_Id+"&dt="+dt.getTime(),1150,500);
        	
        }
         function check(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_firLic","审核烟花爆竹行政许可","${ctx}/jsp/yhbzxzxk/firLicCheck.action?flag=check&firLic.id="+row_Id+"&dt="+dt.getTime(),1150,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_firLic();
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
		                	url : "firLicDel.action",
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
		                        	search_firLic();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_firLic(){
        	var queryParams = {
				"firLic.areaId": $("#areaId").val(),
"firLic.companyName": $("#companyName").val(),
"firLic.itemNo": $("#itemNo").val(),
"firLic.checkPerson": $("#checkPerson").val(),
"firLic.receivePerson": $("#receivePerson").val(),
 "queryReceiveDateStart" :$("#queryReceiveDateStart").val(),
 "queryReceiveDateEnd" :$("#queryReceiveDateEnd").val(),
 "queryDealDateStart" :$("#queryDealDateStart").val(),
 "queryDealDateEnd" :$("#queryDealDateEnd").val(),
 "queryLicenseValidStart" :$("#queryLicenseValidStart").val(),
 "firLic.auditState": $("#auditState").val(),
 "queryLicenseValidEnd" :$("#queryLicenseValidEnd").val()
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
				title:'烟花爆竹行政许可列表',
				url:'firLicQuery.action',
				queryParams:{
					"firLic.areaId": $("#areaId").val(),
"firLic.companyName": $("#companyName").val(),
"firLic.itemNo": $("#itemNo").val(),
"firLic.checkPerson": $("#checkPerson").val(),
"firLic.receivePerson": $("#receivePerson").val(),
 "queryReceiveDateStart" :$("#queryReceiveDateStart").val(),
 "queryReceiveDateEnd" :$("#queryReceiveDateEnd").val(),
 "queryDealDateStart" :$("#queryDealDateStart").val(),
 "queryDealDateEnd" :$("#queryDealDateEnd").val(),
 "queryLicenseValidStart" :$("#queryLicenseValidStart").val(),
 "queryLicenseValidEnd" :$("#queryLicenseValidEnd").val()
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
{field:'itemNo',title:'档案号',width:100},
{field:'checkPerson',title:'材料审查人员',width:100},
{field:'receivePerson',title:'材料接收人员',width:100},
{field:'receiveDate',title:'材料接收日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'dealDate',title:'受理材料日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
{field:'licenseValid',title:'许可证有效起始日期',width:100,formatter:function(value,rec){return value.substring(0,10);}},
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
				<td width="35%"><cus:SelectOneTag style="width: 50%" property="firLic.areaId" defaultText='请选择' codeName="企业属地" value="${firLic.areaId}" /></td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="firLic.companyName" style="width: 50%" id="companyName" value="${firLic.companyName}" type="text" maxlength="127"></td>
			</tr></s:if>
			<tr>
				<th width="15%">档案号</th>
				<td width="35%"><input name="firLic.itemNo" style="width: 50%" id="itemNo" value="${firLic.itemNo}" type="text" maxlength="127"></td>
				<th width="15%">材料审查人员</th>
				<td width="35%"><input name="firLic.checkPerson" style="width: 50%" id="checkPerson" value="${firLic.checkPerson}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				
				<th width="15%">审核状态</th>
			 	<td width="35%">
			 		<s:select id="auditState" name="firLic.auditState" cssStyle="width:50%;"  list="#{'':'请选择','待审核':'待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
			 	</td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_firLic()" >查询<b></b></a>&nbsp;
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
