<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>REVIEW_LOG管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","添加REVIEW_LOG",xc,yc,"400","350","${ctx}/jsp/reviewLog/reviewLogInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","修改REVIEW_LOG",xc,yc,"400","350","${ctx}/jsp/reviewLog/reviewLogInitEdit.action?flag=mod&reviewLog.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","查看REVIEW_LOG",xc,yc,"400","350","${ctx}/jsp/reviewLog/reviewLogView.action?reviewLog.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_reviewLog();
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
		                	url : "reviewLogDel.action",
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
		                        	search_reviewLog();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        //清除查询表单中的搜索条件
        function clear_form(ff){
            var elements = ff.elements;
            for(i=0;i<elements.length;i++){
                var element = elements[i];
                if(element.type=="text"){
                    element.value = "";
                }else if(element.type=="radio" || element.type=="checkbox"){
                	element.checked = false;
                }else if(element.options!=null){
                	element.options[0].selected  = true;
                }
            }
        }
        
        function search_reviewLog(){
        	var queryParams = {
				"reviewLog.itemId": $("#itemId").val(),
"reviewLog.itemType": $("#itemType").val(),
"reviewLog.state": $("#state").val(),
"reviewLog.dutyFlag": $("#dutyFlag").val(),
"reviewLog.userId": $("#userId").val(),
"reviewLog.userName": $("#userName").val(),
"reviewLog.userDeptCode": $("#userDeptCode").val(),
 "queryStartTimeStart" :$("#queryStartTimeStart").val(),
 "queryStartTimeEnd" :$("#queryStartTimeEnd").val(),
 "queryEndTimeStart" :$("#queryEndTimeStart").val(),
 "queryEndTimeEnd" :$("#queryEndTimeEnd").val(),
"reviewLog.record": $("#record").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'REVIEW_LOG列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'reviewLogQuery.action',
				queryParams:{
					"reviewLog.itemId": $("#itemId").val(),
"reviewLog.itemType": $("#itemType").val(),
"reviewLog.state": $("#state").val(),
"reviewLog.dutyFlag": $("#dutyFlag").val(),
"reviewLog.userId": $("#userId").val(),
"reviewLog.userName": $("#userName").val(),
"reviewLog.userDeptCode": $("#userDeptCode").val(),
 "queryStartTimeStart" :$("#queryStartTimeStart").val(),
 "queryStartTimeEnd" :$("#queryStartTimeEnd").val(),
 "queryEndTimeStart" :$("#queryEndTimeStart").val(),
 "queryEndTimeEnd" :$("#queryEndTimeEnd").val(),
"reviewLog.record": $("#record").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'itemId',title:'审核项目id',width:100},
{field:'itemType',title:'审核项目类型',width:100},
{field:'state',title:'审核状态',width:100},
{field:'dutyFlag',title:'审核岗位标记',width:100},
{field:'userId',title:'审核人Id',width:100},
{field:'userName',title:'审核人名称',width:100},
{field:'userDeptCode',title:'审核人部门编码',width:100},
{field:'startTime',title:'审核开始时间',width:200,formatter:function(value,rec){
if(rec.startTime==null) return;
var date = new Date(rec.startTime.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();}}
,
{field:'endTime',title:'审核结束时间',width:200,formatter:function(value,rec){
if(rec.endTime==null) return;
var date = new Date(rec.endTime.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();}}
,
{field:'record',title:'审核记录',width:100},

				          {field:'op',title:'操作',width:100,formatter:function(value,rec){
	                             return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
                          }}
				        ]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[10,20,30],
				onHeaderContextMenu: function(e, field){
					e.preventDefault();
					if (!$('#tmenu').length){
						createColumnMenu();
					}
					$('#tmenu').menu('show', {
						left:e.pageX,
						top:e.pageY
					});
				}
			});
		});

        var titles = new Array();
        function createColumnMenu(){
			var tmenu = $('<div id="tmenu" style="width:150px;"></div>').appendTo('body');
			var fields = $('#pagination').datagrid('getColumnFields');
			
			for(var i=0; i<fields.length; i++){
				var option = $('#pagination').datagrid('getColumnOption',fields[i]);
				var obj = {};
				obj.title = option.title;
				obj.field = fields[i];
				titles[i] = obj;
			}			
			for(var i=0; i<titles.length; i++){
				$('<div iconCls="icon-ok"/>').html(titles[i].title).appendTo(tmenu);
			}
			tmenu.menu({
				onClick: function(item){
					if (item.iconCls=='icon-ok'){
						var field;
						for(var i=0; i<titles.length; i++){
							if(titles[i].title==item.text){
								field = titles[i].field;
							}
						}
						$('#pagination').datagrid('hideColumn', field);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-empty'
						});
					} else {
						var field;
						for(var i=0; i<titles.length; i++){
							if(titles[i].title==item.text){
								field = titles[i].field;
							}
						}
						$('#pagination').datagrid('showColumn', field);
						tmenu.menu('setIcon', {
							target: item.target,
							iconCls: 'icon-ok'
						});
					}
				}
			});
		}
		$(window).resize(function(){
            $('#pagination').datagrid('resize',{
            	width: document.body.clientWidth-20
            });
        });
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">审核项目id</th>
				<td width="35%"><input name="reviewLog.itemId" id="itemId" value="${reviewLog.itemId}" type="text"></td>
				<th width="15%">审核项目类型</th>
				<td width="35%"><input name="reviewLog.itemType" id="itemType" value="${reviewLog.itemType}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">审核状态</th>
				<td width="35%"><input name="reviewLog.state" id="state" value="${reviewLog.state}" type="text"></td>
				<th width="15%">审核岗位标记</th>
				<td width="35%"><input name="reviewLog.dutyFlag" id="dutyFlag" value="${reviewLog.dutyFlag}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">审核人Id</th>
				<td width="35%"><input name="reviewLog.userId" id="userId" value="${reviewLog.userId}" type="text"></td>
				<th width="15%">审核人名称</th>
				<td width="35%"><input name="reviewLog.userName" id="userName" value="${reviewLog.userName}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">审核人部门编码</th>
				<td width="35%"><input name="reviewLog.userDeptCode" id="userDeptCode" value="${reviewLog.userDeptCode}" type="text"></td>
				<th width="15%">审核开始时间</th>
				<td width="35%"><input name="queryStartTimeStart" id="queryStartTimeStart" value="<fmt:formatDate type='both' value='${queryStartTimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryStartTimeEnd\')}'})" >
					-<input name="queryStartTimeEnd" id="queryStartTimeEnd" value="<fmt:formatDate type='both' value='${queryStartTimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryStartTimeStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">审核结束时间</th>
				<td width="35%"><input name="queryEndTimeStart" id="queryEndTimeStart" value="<fmt:formatDate type='both' value='${queryEndTimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryEndTimeEnd\')}'})" >
					-<input name="queryEndTimeEnd" id="queryEndTimeEnd" value="<fmt:formatDate type='both' value='${queryEndTimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryEndTimeStart\')}'})" ></td>
				<th width="15%">审核记录</th>
				<td width="35%"><input name="reviewLog.record" id="record" value="${reviewLog.record}" type="text"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<a href="###" class="easyui-linkbutton" onclick="search_reviewLog()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</td>
			</tr>
		</table>
	</div>
		<table cellspacing="0" cellpadding="0" width="100%" border="0">
			<tr>
				<td>
				<div id="pagination" style="background:#efefef;border:1px solid #ccc;">
				
				</div>
				</td>
			</tr>
		</table>
</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
