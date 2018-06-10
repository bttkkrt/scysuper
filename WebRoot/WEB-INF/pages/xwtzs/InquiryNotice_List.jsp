<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>询问通知书管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            createSimpleWindow("win_inquiryNotice","添加询问通知书","${ctx}/jsp/xwtzs/inquiryNoticeInitEdit.action?flag=add&dt="+dt.getTime(),700,300);
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_inquiryNotice","修改询问通知书","${ctx}/jsp/xwtzs/inquiryNoticeInitEdit.action?flag=mod&inquiryNotice.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function view(row_Id){
        	var dt=new Date();
            createSimpleWindow("win_inquiryNotice","查看询问通知书","${ctx}/jsp/xwtzs/inquiryNoticeView.action?inquiryNotice.id="+row_Id+"&dt="+dt.getTime(),700,300);
        	
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_inquiryNotice();
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
		                	url : "inquiryNoticeDel.action",
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
		                        	search_inquiryNotice();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function search_inquiryNotice(){
        	var queryParams = {
				 "queryInquiryTimeStart" :$("#queryInquiryTimeStart").val(),
 "queryInquiryTimeEnd" :$("#queryInquiryTimeEnd").val(),
"inquiryNotice.contact": $("#contact").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'询问通知书列表',
				url:'inquiryNoticeQuery.action',
				queryParams:{
					 "queryInquiryTimeStart" :$("#queryInquiryTimeStart").val(),
 "queryInquiryTimeEnd" :$("#queryInquiryTimeEnd").val(),
"inquiryNotice.contact": $("#contact").val()
				},
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'inquiryTime',title:'询问时间',width:100},
{field:'contact',title:'联系人',width:100},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=view('"+rec.id+"') >查看<b></b></a>&nbsp;<a class='btn_03_mini' onclick=edit('"+rec.id+"')>修改<b></b></a>";}}
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

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
					
				<th width="15%">询问时间</th>
				<td width="35%"><input name="queryInquiryTimeStart" id="queryInquiryTimeStart" value="<fmt:formatDate type='both' value='${queryInquiryTimeStart}' />" type="text" class="Wdateonclick="WdatePicker(r({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryInquiryTimeEnd\')}'})" >
					-<input name="queryInquiryTimeEnd" id="queryInquiryTimeEnd" value="<fmt:formatDate type='both' value='${queryInquiryTimeEnd}' />" type="text" class="Wdatonclick="WdatePicker(er({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryInquiryTimeStart\')}'})" ></td>
				<th width="15%">联系人</th>
				<td width="35%"><input name="inquiryNotice.contact" id="contact" value="${inquiryNotice.contact}" type="text"></td>
			</tr>
			<tr>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_inquiryNotice()" >查询<b></b></a>&nbsp;
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
