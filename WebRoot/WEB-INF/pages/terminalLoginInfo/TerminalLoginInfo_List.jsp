<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>终端登录信息管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	
	<script src="<c:url value='/webResources/js/docorder.js' />"></script>
	<script src="<c:url value='/webResources/My97DatePicker/WdatePicker.js' />"></script>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src='<c:url value="/webResources/js/easyui/jquery.easyui.min.js"/>'></script>
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/default/easyui.css"/>'> 
	<link rel="stylesheet" type="text/css" href='<c:url value="/webResources/js/easyui/themes/icon.css"/>'>
	<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>  
	<link rel="stylesheet" type="text/css" href="${ctx}/webResources/themes/${curr_user.cssId}/css/style.css">
	<script src="<c:url value='/webResources/js/common.js' />"></script>
	<script>
        
		function exportdata()
        {
        	document.myform.action = "${ctx}/jsp/terminalLoginInfo/terminalLoginInfoExport.action";
        	document.myform.submit();
        }
        function addNew(){
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 400) / 2;
            openparentWindow(parent.parent.$("#newWindow"),"添加终端登录信息",xc,yc,"800","400","${ctx}/jsp/terminalLoginInfo/terminalLoginInfoInitEdit.action?flag=add",true,true,true,false,true,parent.parent.$("#win"));
        	
        }
        function edit(row_Id){
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 400) / 2;
            openparentWindow(parent.parent.$("#newWindow"),"修改终端登录信息",xc,yc,"800","400","${ctx}/jsp/terminalLoginInfo/terminalLoginInfoInitEdit.action?flag=mod&terminalLoginInfo.id="+row_Id,true,true,true,false,true,parent.parent.$("#win"));
        	
        }
        function view(row_Id){
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 400) / 2;
            openparentWindow(parent.parent.$("#newWindow"),"查看终端登录信息",xc,yc,"800","400","${ctx}/jsp/terminalLoginInfo/terminalLoginInfoView.action?terminalLoginInfo.id="+row_Id,true,true,true,false,true,parent.parent.$("#win"));
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_terminalLoginInfo();
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
		                	url : "terminalLoginInfoDel.action",
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
		                        	search_terminalLoginInfo();
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
        
        function search_terminalLoginInfo(){
        	var queryParams = {
				"terminalLoginInfo.userName": $("#userName").val(),
				"beginDate" : $("#beginDate").val(),
				"endDate" : $("#endDate").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$("#pagination").datagrid('reload'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'终端登录信息列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'terminalLoginInfoQuery.action',
				queryParams:{
					"terminalLoginInfo.userName": $("#userName").val(),
					"beginDate" : $("#beginDate").val(),
					"endDate" : $("#endDate").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'userName',title:'用户名',width:fixWidth(0.3)},
				          {field:'imsi',title:'imsi号',width:fixWidth(0.3)},
				          {field:'createTime',title:'登录日期',width:fixWidth(0.3),formatter:function(value,rec){
					if(rec.createTime==null) {return;}
					var date = new Date(rec.createTime.time);
					var month = parseInt(date.getMonth()+1);
					return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
				}}
				        ]],
				pagination:true,
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

			var p = $('#pagination').datagrid('getPager');
			if(p){
				$(p).pagination({
					onBeforeRefresh:function(){
						search_terminalLoginInfo();
					},onChangePageSize:function(){
						search_terminalLoginInfo();
			        },onSelectPage:function(pageNumber,pageSize){
			        	search_terminalLoginInfo();
				    } 
				});
			}
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
		
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="10%">用户名</th>
				<td width="25%"><input name="terminalLoginInfo.userName" id="userName" value="${terminalLoginInfo.userName}" type="text"></td>
				<th width="10%" class="TB_01">登录日期</th>
				<td width="40%" class="TB_02">
					<input type="text" name="beginDate" id="beginDate"
							class="Wdate"
							style="width:150px;"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})"
							value="<fmt:formatDate type="date" value="${beginDate}" pattern="yyyy.MM.dd HH:mm:ss"/>">
					~
					<input type="text" name="endDate" id="endDate"
							class="Wdate" 
							style="width:150px;" 
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginDate\')}'})"
							value="<fmt:formatDate type="date" value="${endDate}" pattern="yyyy.MM.dd HH:mm:ss"/>">
				</td>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center;">
				<a href="###" class="easyui-linkbutton" onclick="search_terminalLoginInfo()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<!--  <a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;-->
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				<a href="###" class="easyui-linkbutton" onclick="exportdata();" iconCls="icon-print">导出</a>&nbsp;
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
<div id="newWindow" class="easyui-window" closed="true" style="width:300px;height:100px;">
    <iframe id="win" style="width:100%;height:100%;"></iframe>
</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
