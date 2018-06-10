<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>文书管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        var flag = "${flag}";
        var loginUserId = "${loginUserId}";
        var userid = ",${loginUserId},";
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","添加文书","${ctx}/jsp/wsgl/instrumentsInfoInitEdit.action?flag=add&dt="+dt.getTime(),900,550);
        	
        }
        function edit(row_Id,type){
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","修改文书","${ctx}/jsp/wsgl/instrumentsInfoInitEdit.action?flag=mod&instrumentsInfo.id="+row_Id+"&dt="+dt.getTime()+"&type="+type,900,550);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","查看文书","${ctx}/jsp/wsgl/instrumentsInfoView.action?instrumentsInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        	
        }
        function downloadWs(row_Id)
        {
        	document.myform.action = "${ctx}/jsp/wsgl/instrumentsInfoExport.action?instrumentsInfo.id="+row_Id;
        	document.myform.submit();
        }
        function shenhe(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","审核文书","${ctx}/jsp/wsgl/instrumentsInfoShenhe.action?instrumentsInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        
        function returnPic(row_Id)
        {
        	var dt=new Date();
            createSimpleWindow("win_instrumentsInfo","回执","${ctx}/jsp/wsgl/instrumentsInfoPic.action?instrumentsInfo.id="+row_Id+"&dt="+dt.getTime(),900,550);
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_instrumentsInfo();
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
		                	url : "instrumentsInfoDel.action",
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
		                        	search_instrumentsInfo();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function checkPass()
        {
        	var rows = document.getElementsByName("xxx");
        	var ids = "";
        	var len = rows.length;
			for(var i=0;i<len;i++){
				if(rows[i].checked){
					ids += rows[i].value+"|";
				}
			}
			if(ids == ""){
			    $.messager.alert('提示','至少选择一项审核！');
			}else{
				var dt=new Date();
            	createSimpleWindow("win_instrumentsInfo","审核文书","${ctx}/jsp/wsgl/instrumentsInfoShenheAll.action?flag=mod&ids="+ids+"&dt="+dt.getTime()+"&type="+flag,600,300);
			}
        }
        function search_instrumentsInfo(){
        	var queryParams = {
"instrumentsInfo.instrumentType": $("#instrumentType").val(),
"instrumentsInfo.companyName": $("#companyName").val(),
"instrumentsInfo.caseId": "${instrumentsInfo.caseId}",
"instrumentsInfo.ifCheck": $("#ifCheck").val(),
"instrumentsInfo.instrumentName": $("#instrumentName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	var toolbar = [];
        	if(flag.indexOf("A10") != -1 && flag.indexOf("A29") != -1 && "${instrumentsInfo.caseId}" == "")
        	{
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
				}]
        	}
        	else if(flag.indexOf("A10") != -1 && "${instrumentsInfo.caseId}" == "")
        	{
        		toolbar = [{
					id:'btnadd',
					text:'添加',
					iconCls:'icon-add',
					handler:function(){
						addNew();
					}
				}];
        	}
        	else if(flag.indexOf("A29") != -1)
        	{
        		toolbar = [{
					id:'btncut',
					text:'删除',
					iconCls:'icon-remove',
					handler:function(){
						del();
					}
				}];
        	}
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'文书管理列表',
				url:'instrumentsInfoQuery.action',
				queryParams:{
"instrumentsInfo.instrumentType": $("#instrumentType").val(),
"instrumentsInfo.companyName": $("#companyName").val(),
"instrumentsInfo.caseId": "${instrumentsInfo.caseId}",
"instrumentsInfo.ifCheck": $("#ifCheck").val(),
"instrumentsInfo.instrumentName": $("#instrumentName").val()
				},
				frozenColumns:[[
				    {field:'id',width:30,formatter:function(value,rec){
				    		var opt = ''; 
						    if((rec.ifCheck == 3 && rec.needCheckUser.indexOf(userid) != -1)
						    || (flag.indexOf("A02") != -1 && rec.ifCheck == 2)
						    || (flag.indexOf("A09") != -1 && rec.ifCheck == 1)
						    || (flag.indexOf("A30") != -1 && rec.ifCheck == 8)
						    || (flag.indexOf("A29") != -1)
						    ){//这里判断创建人是不是登录人
								opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    }
						    return opt ; 
				    }}
				]],
				columns:[[
				{field:'companyName',title:'企业名称',width:100},
{field:'instrumentName',title:'文书名称',width:100},
{field:'ifCheck',title:'文书状态',width:100,formatter:function(value,rec){
if(value == '1' || value == '2' || value == '3' || value == '8')
{
	return "待审核";
}
else if(value == '4')
{
	return "审核不通过";
}
else
{
	return "已生成";
}
}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
var aa = "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;";
if(rec.createUserID == loginUserId &&  rec.ifCheck == '4')
{
	aa += "<a class='btn_03_mini' onclick=edit('"+rec.id+"','0')>修改<b></b></a>&nbsp;";
}
if((flag.indexOf("A01") != -1 || flag.indexOf("A29") != -1) && rec.instrumentType != '100' && rec.instrumentType != '102')
{
	aa += "<a class='btn_03_mini' onclick=edit('"+rec.id+"','1')>修改<b></b></a>&nbsp;";
}
if((rec.ifCheck == '1' && flag.indexOf("A09") != -1) || (rec.ifCheck == '2' && flag.indexOf("A02") != -1) || (rec.ifCheck == '3' && rec.needCheckUser.indexOf(userid) != -1) || (rec.ifCheck == '8' && flag.indexOf("A30") != -1))
{
	aa += "<a class='btn_03_mini' onclick=shenhe('"+rec.id+"')>审核<b></b></a>&nbsp;";
}
if((rec.ifCheck == '0' || rec.ifCheck == '5' || rec.ifCheck == '6' || rec.ifCheck == '7') && rec.ifPrint == '1')
{
	aa += "<a class='btn_04_mini' onclick=downloadWs('"+rec.id+"')>下载<b></b></a>&nbsp;";
}
if((rec.ifCheck == '0' || rec.ifCheck == '5' || rec.ifCheck == '6') && rec.ifPrint == '1')
{
	aa += "<a class='btn_05_mini' onclick=returnPic('"+rec.id+"')>回执<b></b></a>";
}
return aa;

}}
				        ]],
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
			<form name="myform" method="post">
			<table width="100%">
				<tr>
					<th width="15%">企业名称</th>
					<td width="35%"><input name="instrumentsInfo.companyName" style="width:50%" id="companyName" value="${instrumentsInfo.companyName}" type="text" maxlength="127"></td>
					<th width="15%">文书名称</th>
					<td width="35%"><input name="instrumentsInfo.instrumentName" style="width:50%" id="instrumentName" value="${instrumentsInfo.instrumentName}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">文书类型</th>
					<td width="35%"><cus:SelectOneTag property="instrumentsInfo.instrumentType" style="width:50%" defaultText='请选择' codeName="文书类型" value="${instrumentsInfo.instrumentType}" maxlength="127"/></td>
					<th width="15%">文书状态</th>
					<td width="35%">
						<s:select id="ifCheck" name="instrumentsInfo.ifCheck" cssStyle="width:50%"  list='#{"":"--请选择---","1":"待审核","2":"已生成","4":"审核不通过"}' theme="simple"></s:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_instrumentsInfo()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;		
						<s:if test="flag.indexOf('A02') != -1 || flag.indexOf('A09') != -1 || flag.indexOf('B00') != -1 || flag.indexOf('A30') != -1">
							<a href="###" class="btn_01" onclick="checkPass()" >审核<b></b></a>&nbsp;
						</s:if>		
					</td>
				</tr>
			</table>
			</form>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>
