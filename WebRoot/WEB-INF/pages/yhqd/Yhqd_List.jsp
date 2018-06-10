<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>YHQD管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
    		window.open("${ctx}/jsp/yhqd/yhqdInitEdit.action?flag=add&dt="+dt.getTime() );
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
    		window.open("${ctx}/jsp/yhqd/yhqdInitEdit.action?flag=mod&yhqd.id="+row_Id+"&dt="+dt.getTime() );
        	
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
    		window.open("${ctx}/jsp/yhqd/yhqdView.action?yhqd.id="+row_Id+"&dt="+dt.getTime() );
        	
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_yhqd();
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
		                	url : "yhqdDel.action",
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
		                        	search_yhqd();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        function endProcess(id){
			$.messager.confirm('隐患处理', "确认中止流程", function(result){
				if (result){
					$.ajax({
		            	url : "${ctx}/jsp/checkBasic/endProcess.action",
		            	type: 'post',
		                dataType: 'json',
		                async : false,
		                data: {
		                	"yhqd.id" : id,
		                },
		                error: function(){
		                	$.messager.alert('错误','发送中止请求出错！');
		                },
		                success: function(data){
		                    if(data.result){
		                    	$.messager.alert('提示','中止完成！','info',function(){
		                    		search_yhqd();
		                    	});
		                    }else{
		                    	$.messager.alert('错误','中止时出错！');
		                    }
		                }
		            });					
				}
			});		
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
        
        function search_yhqd(){
        	var queryParams = {
        			"yhqd.basic.company.companyname": $("#qymc").val(), 
        			"yhqd.yhContent": $("#yhContent").val(), 
 "queryChecktimeStart" :$("#queryChecktimeStart").val(),
 "queryChecktimeEnd" :$("#queryChecktimeEnd").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'YHQD列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'yhqdQuery.action',
				queryParams:{
				  	"yhqd.basic.company.companyname": $("#qymc").val(), 
				  	"yhqd.yhContent": $("#yhContent").val(), 
 "queryChecktimeStart" :$("#queryChecktimeStart").val(),
 "queryChecktimeEnd" :$("#queryChecktimeEnd").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'basic.company.companyname',title:'被检查单位名称',width:300,formatter:function(value,rec){
				        	  return rec.basic.company.companyname;
				          }
				        	  },
				          {field:'yhContent',title:'隐患内容',width:450,formatter:function(value,rec){
				        	  return rec.yhContent;
				          }
				        	  },
{field:'checktime',title:'检查时间',width:100,formatter:function(value,rec){
if(rec.checktime==null) return;
var date = new Date(rec.checktime.time);
var month = parseInt(date.getMonth()+1);
return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  ' ;}}
,

				          {field:'op',title:'操作',width:100,formatter:function(value,rec){
				          		var option = "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
				          		if(rec.basic.createUserID=="${LOGIN_USER_ID}"||rec.createUserID=="${LOGIN_USER_ID}"){
					          		if(rec.ended == '0'){
			                              option += "&nbsp;<span style='color:red;cursor:hand' onclick=\"endProcess('"+rec.id+"')\">中止</span>";
		                        	  }else{
		                        		   option += "&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>&nbsp;"; 
					          		}
	                        	}
				          		return option;
				          	}
				          },
                          {field:'ended',title:'状态',width:fixWidth(0.06),formatter:function(value,rec){
                        	  if(rec.ended == '1'){
                        		  return "<span style='color:green;'>已办结</span>"; 
                        	  }else if(rec.ended == '-1'){
 	                             return "<span style='color:red;'>已取消</span>";
                         	  }else{
 	                             return "<span style='color:red;'>未办结</span>";
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
				<th width="15%">被检查单位名称</th>
				<td width="35%"><input name="yhqd.basic.company.companyname" id="qymc" value="${yhqd.basic.company.companyname}" type="text"></td>
				<th width="15%">检查时间</th>
				<td width="35%"><input name="queryChecktimeStart" id="queryChecktimeStart" value="<fmt:formatDate type='both' value='${queryChecktimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryChecktimeEnd\')}'})" >
					-<input name="queryChecktimeEnd" id="queryChecktimeEnd" value="<fmt:formatDate type='both' value='${queryChecktimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryChecktimeStart\')}'})" ></td>
			</tr>
			<tr>
			 
			<th width="15%">隐患内容</th>
				<td width="35%"><input name="yhqd.yhContent" id="yhContent" value="${yhqd.yhContent}" type="text"></td>
				
			</tr>
			<tr>
				<td colspan="4" align="center">
				<a href="###" class="easyui-linkbutton" onclick="search_yhqd()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<!-- <a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp; -->
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
