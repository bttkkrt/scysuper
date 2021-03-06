<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>村级安全员网格管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","添加网格",xc,yc,"400","350","${ctx}/jsp/gridManager/gridManagerInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 400) / 2;
    		var yc = (ah - 350) / 2;
            openparentWindow("newWindow","修改网格",xc,yc,"400","350","${ctx}/jsp/gridManager/gridManagerInitEdit.action?flag=mod&gridManager.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	
        }
        //绑定有证企业
        function bindCompany01(row_Id,name){
        
        	var id  = "town_list";
        	var text = "绑定到"+name+"的有证企业信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerCompanyUser.action?userId="+row_Id+"&deptCode=${deptCode}";
    		window.parent.addTab(id,text,url);
        }
        //绑定无证企业
        function bindCompany02(row_Id,name){
        
        	var id  = "town_list";
        	var text = "绑定到"+name+"的无证企业信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerNoCompanyUser.action?userId="+row_Id+"&deptCode=${deptCode}";
    		window.parent.addTab(id,text,url);
        }
         function mapView(row_Id,name){
        	var id  = "town_list_map";
        	var text = "查看"+name+"下地图信息";
        	var url = "${ctx}/jsp/gridManager/gridManagerCountryAQYMapView.action?deptCode="+row_Id;
    		window.parent.addTab(id,text,url);
        }
        
        function gxfw(userId)
        {
        	window.open("${ctx}/jsp/gridManager/gridManagerGxfw.action?user.id="+userId);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_gridManager();
        }
        function bindCountryUser(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项进行绑定！');
			}else{
			    $.messager.confirm("删除","确定要绑定吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "gridManagerBindUser.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                   		"userId":'${userId}',
		                   		"flag":'4',
		                    	ids : ids
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','绑定时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','绑定成功！');
		                        	search_gridManager();
		                        }else{
		                        	$.messager.alert('错误','绑定时出错！');
		                        }
		                    }
		                });
			        }
			    });
			}
        }
        
        function unbindCountryUser(){
        	var rows = $('#pagination').datagrid('getSelections');
        	var ids = "";
			for(var i=0;i<rows.length;i++){
				ids += rows[i].id+"|";
			}
			if(rows.length<1){
			    $.messager.alert('提示','至少选择一项进行解除绑定！');
			}else{
			    $.messager.confirm("删除","确定要解除绑定吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "gridManagerJcBindUser.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                   		"userId":'${userId}',
		                   		"flag":'4',
		                    	ids : ids
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','解除绑定时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','解除绑定成功！');
		                        	search_gridManager();
		                        }else{
		                        	$.messager.alert('错误','解除绑定时出错！');
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
        
        function search_gridManager(){
        	var queryParams = {
        			"userId":"${userId}",
					"gridManager.id": $("#userid").val(),
					"bind": $("#bind").val(),
					"deptCode": '${deptCode}',
					"szc":$("#szc").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
			$('#pagination').datagrid({
				title:'村级安全员列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'gridManagerBindCountryUserQuery.action',
				queryParams:{
				
        			"userId":"${userId}",
					"gridManager.id": $("#userid").val(),
					"bind": $("#bind").val(),
					"deptCode": '${deptCode}',
					"szc":$("#szc").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'name',title:'村级安全员',width:100},
				          {field:'szc',title:'下辖网格区域',width:100},
				          {field:'count',title:'有证照企业数',width:100},
				          {field:'wzCount',title:'无证照企业数',width:100},
						  {field:'mark',title:'已绑定责任人',width:200,formatter:function(value,rec){
				    		     var info = "";
						         if(rec.mark != 0){//这里判断是否是自己创建
						    	  info = "<font style='color:green;'>"+rec.info+"</font>";
						        } 
						     return info ; 
				    	  }},
				    	  {field:'gxfw',title:'管辖范围',width:200},
				          {field:'op',title:'操作',width:300,formatter:function(value,rec){
	                             var opt = "<span style='color:red;cursor:hand' onclick=\"bindCompany01('"+rec.id+"','"+rec.name+"')\">绑定有证企业</span>";
	                             opt = opt + "&nbsp;&nbsp;<span style='color:green;cursor:hand' onclick=\"bindCompany02('"+rec.id+"','"+rec.name+"')\">绑定无证企业</span>"+
	                             "&nbsp;&nbsp;<span style='color:blue;cursor:hand' onclick=\"mapView('"+rec.id+"','"+rec.name+"')\">地图查看</span>"+
	                             "&nbsp;&nbsp;<span style='color:black;cursor:hand' onclick=\"gxfw('"+rec.id+"')\">管辖范围</span>";
                          		return opt;
                          }}
				        ]],
				pagination:true,
				onLoadSuccess:tabOnloadSuccess,
				onLoadError:tabOnloadSuccess,
				rownumbers:true,
				pageList:[200,300],
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
				<th width="15%">村级安全员</th>
				<td width="35%">
					<s:select list="users" id="userid" listKey="id" listValue="name" theme="simple"
 						 headerKey="" headerValue=""></s:select>
				</td>
				<s:if test="bind != -1">
					<th width="15%">下辖网格区域</th>
					<td width="35%">
						<s:select theme="simple" id="szc" emptyOption= "true" list="%{deptlist}" listKey="deptCode" listValue="deptName"></s:select>
					</td>
				</tr>
				<tr>
					<th width="15%">绑定状态</th>
					<td width="35%">
						<s:select id="bind" theme="simple" list="#{1:'显示绑定给当前责任人',2:'显示所有'}"  listKey="key" listValue="value">
						</s:select>
					</td>
				</s:if>
				<s:else>
					<input type="hidden" id="bind" value="${bind}">
				</s:else>
			</tr>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
					<a href="###" class="easyui-linkbutton" onclick="search_gridManager()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;
					<s:if test="bind != -1">
						<a href="###" class="easyui-linkbutton" onclick="bindCountryUser();" iconCls="icon-add">绑定</a>&nbsp;
						<a href="###" class="easyui-linkbutton" onclick="unbindCountryUser();" iconCls="icon-add">解除绑定</a>&nbsp;	
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
