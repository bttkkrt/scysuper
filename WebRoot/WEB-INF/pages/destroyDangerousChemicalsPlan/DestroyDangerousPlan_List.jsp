<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>危险化学品重大危险源核销告知书管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
         var flag = "${flag}";
        var createUserID = "${createUserID}";
        function addNew(){
        	window.open("${ctx}/jsp/destroyDangerousChemicalsPlan/destroyDangerousPlanInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/destroyDangerousChemicalsPlan/destroyDangerousPlanInitEdit.action?flag=mod&destroyDangerousPlan.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/destroyDangerousChemicalsPlan/destroyDangerousPlanView.action?destroyDangerousPlan.id="+row_Id);
        }
        function printOut(row_Id)
        {
        	window.open("${ctx}/jsp/destroyDangerousChemicalsPlan/destroyDangerousPlanPrint.action?destroyDangerousPlan.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_destroyDangerousPlan();
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
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项删除！');
			}else{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "destroyDangerousPlanDel.action",
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
		                        	search_destroyDangerousPlan();
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
        
        function search_destroyDangerousPlan(){
        	var queryParams = {
        		"destroyDangerousPlan.szzid": $("#szzid").val(),
					"destroyDangerousPlan.companyName": $("#companyName").val(),
"destroyDangerousPlan.planId": $("#planId").val(),
"destroyDangerousPlan.hxJg": $("#hxJg").val(),
"queryHxStart" :$("#queryHxStart").val(),
 "queryHxEnd" :$("#queryHxEnd").val(),
 "queryTbrqStart" :$("#queryTbrqStart").val(),
 "queryTbrqEnd" :$("#queryTbrqEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'危险化学品重大危险源核销告知书列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'destroyDangerousPlanQuery.action',
				queryParams:{
				"destroyDangerousPlan.szzid": $("#szzid").val(),
					"destroyDangerousPlan.companyName": $("#companyName").val(),
"destroyDangerousPlan.planId": $("#planId").val(),
"destroyDangerousPlan.hxJg": $("#hxJg").val(),
"queryHxStart" :$("#queryHxStart").val(),
 "queryHxEnd" :$("#queryHxEnd").val(),
 "queryTbrqStart" :$("#queryTbrqStart").val(),
 "queryTbrqEnd" :$("#queryTbrqEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				   {field:'id',width:30,formatter:function(value,rec){
				    		var opt = '<input type="checkbox" name="xxx" value='+rec.id+'>'; 
						    if(rec.createUserID != createUserID){//这里判断是否是自己创建
								opt = ''; 
						    } 
						    return opt ; 
				    }}
				]],
				columns:[[
					{field:'szzname',title:'所在镇',width:fixWidth(0.1)},
				          {field:'companyName',title:'单位名称',width:fixWidth(0.2)},
				          {field:'planId',title:'原备案编号',width:fixWidth(0.1)},
{field:'hxJg',title:'核销机关',width:fixWidth(0.2)},
{field:'hxDate',title:'核销日期',width:fixWidth(0.1)},
{field:'sendTime',title:'回执时间',width:fixWidth(0.1)},
				          {field:'op',title:'操作',width:fixWidth(0.1),formatter:function(value,rec){
	                           /**
	                           var str="";
					        	  $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"8", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"查看" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;";
					        		    	}
					        		    }});
					        	  $.ajax({
					        		    url: '${ctx}/jsp/admin/role/buttons.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"function":"",
					        		    	"num":"3", 
					        		    	"moduleId":"${currModuleCode}",
					        		    	"functionName":"修改" 
					        		    	},
					        		    error: function(){
					        		        $.messager.alert('提示','获取一维代码错误！');
					        		    },
					        		    success: function(data){
					        		    	if(""!=data.result){
					        		        str+= "<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
					        		    	}
					        		    }});
					        		    str+= "<span style='color:red;cursor:hand' onclick=\"printOut('"+rec.id+"')\">打印</span> ";
					        	 return str;
	                           **/
	                             if(rec.createUserID == createUserID)
				          		{
				          			 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"printOut('"+rec.id+"')\">打印</span>";
				          		}
				          		else
				          		{
				          			 return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"printOut('"+rec.id+"')\">打印</span>";
				          		}
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
				<th width="15%">单位名称</th>
				<td width="35%"><input name="destroyDangerousPlan.companyName" id="companyName" value="${destroyDangerousPlan.companyName}" type="text"></td>
				<th width="15%">核销机关</th>
				<td width="35%"><input name="destroyDangerousPlan.hxJg" id="hxJg" value="${destroyDangerousPlan.hxJg}" type="text"></td>
			</tr>
			<tr>
			    <th width="15%">核销日期</th>
				<td width="35%"><input name="queryHxStart" id="queryHxStart" value="${queryHxStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryHxEnd\')}'})" >
					-<input name="queryHxEnd" id="queryHxEnd" value="${queryHxEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryHxStart\')}'})" ></td>
				<th width="15%">回执时间</th>
				<td width="35%"><input name="queryTbrqStart" id="queryTbrqStart" value="${queryTbrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryTbrqEnd\')}'})" >
					-<input name="queryTbrqEnd" id="queryTbrqEnd" value="${queryTbrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryTbrqStart\')}'})" ></td>
				
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_destroyDangerousPlan()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="flag == 1">
				<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</s:if>
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
