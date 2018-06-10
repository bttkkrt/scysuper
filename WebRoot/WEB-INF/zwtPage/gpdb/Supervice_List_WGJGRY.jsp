<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>挂牌督办管理</title>
    <%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_supervice","添加挂牌督办","${ctx}/jsp/gpdb/superviceInitEdit.action?flag=add&dt="+dt.getTime(),900,500);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","修改挂牌督办","${ctx}/jsp/gpdb/superviceInitEdit.action?flag=mod&supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
         function report(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","上报整改信息","${ctx}/jsp/gpdb/superviceInitEdit.action?flag=mod&supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_supervice","查看挂牌督办","${ctx}/jsp/gpdb/superviceView.action?supervice.id="+row_Id+"&dt="+dt.getTime(),900,500);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_supervice();
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
		                	url : "superviceDel.action",
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
		                        	search_supervice();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_supervice(){
        	var queryParams = {
				"supervice.areaId": $("#areaId").val(),
"supervice.companyName": $("#companyName").val(),
"supervice.dangerName": $("#dangerName").val(),
"supervice.dangerSort": $("#dangerSort").val(),
"supervice.dangerLevel": $("#dangerLevel").val(),
"supervice.rectificationState": $("#rectificationState").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'挂牌督办列表',
				url:'superviceQuery.action',
				queryParams:{
					"supervice.areaId": $("#areaId").val(),
"supervice.companyName": $("#companyName").val(),
"supervice.dangerName": $("#dangerName").val(),
"supervice.dangerSort": $("#dangerSort").val(),
"supervice.dangerLevel": $("#dangerLevel").val(),
"supervice.rectificationState": $("#rectificationState").val()
				},
				columns:[[
				          {field:'areaId',title:'所在区域',width:100},
{field:'companyName',title:'企业名称',width:100},
{field:'dangerName',title:'隐患名称',width:100},
{field:'dangerSort',title:'隐患类别',width:100},
{field:'dangerLevel',title:'隐患级别',width:100},
{field:'rectificationLevel',title:'整改级别',width:100},
{field:'rectificationState',title:'整改状态',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){
	var button1="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";
	var button2="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=report('"+rec.id+"')>整改上报<b></b></a>";
	var button3="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>";
	var button4="<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=report('"+rec.id+"')>整改上报<b></b></a>";
	if(rec.rectificationState=="待整改"){
		return button2;
	}else if(rec.rectificationState=="已整改待审核"){
		return button3;
	}else if(rec.rectificationState=="审核未通过"){
		return button4;
	}else{
		return button3;
	}
}}
				        ]] 
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
				<td width="35%">
					<cus:SelectOneTag style="width:50%"  property="supervice.areaId" defaultText='请选择' codeName="企业属地" value="${supervice.areaId}" />
				</td>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="supervice.companyName" style="width:50%" id="companyName" value="${supervice.companyName}" type="text" maxlength="127"></td>
			</tr>
			<tr>
				<th width="15%">隐患名称</th>
				<td width="35%"><input name="supervice.dangerName" style="width:50%" id="dangerName" value="${supervice.dangerName}" type="text" maxlength="127"></td>
				<th width="15%">隐患类别</th>
				<td width="35%"><cus:SelectOneTag style="width:50%"   property="supervice.dangerSort" defaultText='请选择' codeName="隐患类别" value="${supervice.dangerSort}" /></td>
			</tr>
			<tr>
				<th width="15%">隐患级别</th>
				<td width="35%"><cus:SelectOneTag  style="width:50%"  property="supervice.dangerLevel" defaultText='请选择' codeName="隐患级别" value="${supervice.dangerLevel}" /></td>
				<th width="15%">整改状态</th>
				<td width="35%">
					<s:select id="rectificationState" name="supervice.rectificationState" cssStyle="width:50%;"  list="#{'':'请选择','待整改':'待整改','已整改待审核':'已整改待审核','审核通过':'审核通过','审核未通过':'审核未通过'}" theme="simple"/>
				</td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_supervice()" >查询<b></b></a>&nbsp;
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
