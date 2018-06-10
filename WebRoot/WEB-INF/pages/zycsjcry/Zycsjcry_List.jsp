<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>接触人员管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        var zycsid = "${zycsjcry.zycsid}";
        function addNew(){
        	window.open("${ctx}/jsp/zycsjcry/zycsjcryInitEdit.action?flag=add&zycsjcry.zycsid="+zycsid);
        }
        function edit(row_Id){
            window.open("${ctx}/jsp/zycsjcry/zycsjcryInitEdit.action?flag=mod&zycsjcry.id="+row_Id);
        }
        function view(row_Id){
            window.open("${ctx}/jsp/zycsjcry/zycsjcryView.action?zycsjcry.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zycsjcry();
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
		                	url : "zycsjcryDel.action?zycsjcry.zycsid="+zycsid,
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
		                        	search_zycsjcry();
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
        
        function search_zycsjcry(){
        	var queryParams = {
"zycsjcry.xm": $("#xm").val(),
"zycsjcry.sfz": $("#sfz").val(),
"zycsjcry.xb": $("#xb").val(),
"querySgsjStart": $("#querySgsjStart").val(),
"querySgsjEnd": $("#querySgsjEnd").val(),
"zycsjcry.zycsid": $("#zycsid").val(),
"zycsjcry.delFlag": $("#delFlag").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'接触人员列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zycsjcryQuery.action',
				queryParams:{
					"zycsjcry.xm": $("#xm").val(),
"zycsjcry.sfz": $("#sfz").val(),
"zycsjcry.xb": $("#xb").val(),
"querySgsjStart": $("#querySgsjStart").val(),
"querySgsjEnd": $("#querySgsjEnd").val(),
"zycsjcry.zycsid": $("#zycsid").val(),
"zycsjcry.delFlag": $("#delFlag").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'sfz',title:'身份证',width:fixWidth(0.2)},
{field:'xm',title:'姓名',width:fixWidth(0.2)},
{field:'xb',title:'性别',width:fixWidth(0.15),formatter:function(value,rec){
	var temp = '';
	$.ajax({
		url: '${ctx}/jsp/admin/code/findCodeValue.action',
		type: 'post',
		dataType: 'json',
		async : false,
		data:{      "codeValue.itemValue" : rec.xb,
					"codeValue.codeId" : "4ae6e5a340b8711b0140b89fe9840062"},
		error: function(){
			$.messager.alert('提示','获取一维代码错误！');
		},
		success: function(data){
			temp = data.itemText;
		}});
	return temp;
                          }},
{field:'sgsj',title:'上岗时间',width:fixWidth(0.2)},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
	                            /**var str="";
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
					        	 return str;
	                            **/
	                           
	                             if(flag == '1')
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}
	                            else
	                            {
	                            	return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
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
	<input type="hidden" id="zycsid" value="${zycsjcry.zycsid}" name="zycsjcry.zycsid"/>
	<input type="hidden" id="delFlag" name="zycsjcry.delFlag" value="0"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">身份证</th>
				<td width="35%"><input id="sfz" name="zycsjcry.sfz" value="${zycsjcry.sfz}" type="text" maxlength="255"></td>
				<th width="15%">性别</th>
				<td width="35%"><cus:SelectOneTag property="zycsjcry.xb" defaultText='请选择' codeName="性别" value="${zycsjcry.xb}" /></td>
			</tr>
			<tr>
				<th width="15%">姓名</th>
				<td width="35%"><input name="zycsjcry.xm" id="xm" value="${zycsjcry.xm}" type="text"></td>
				<th width="15%">上岗时间</th>
				<td width="35%"><input name="querySgsjStart" id="querySgsjStart" value="${querySgsjStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'querySgsjEnd\')}'})" >
					-<input name="querySgsjEnd" id="querySgsjEnd" value="${querySgsjEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'querySgsjStart\')}'})" ></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				<a href="###" class="easyui-linkbutton" onclick="search_zycsjcry()" iconCls="icon-search">查询</a>&nbsp;
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
