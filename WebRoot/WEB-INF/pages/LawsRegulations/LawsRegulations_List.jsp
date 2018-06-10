<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>法律法规管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function addNew(){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/LawsRegulations/lawsRegulationsInitEdit.action?flag=add",900,600);
        	/* var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 500) / 2;
            openparentWindow("newWindow","添加法律法规",xc,yc,"800","500","${ctx}/jsp/LawsRegulations/lawsRegulationsInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	 */
        }
        function edit(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/LawsRegulations/lawsRegulationsInitEdit.action?flag=mod&lawsRegulations.id="+row_Id,900,600);
        	/* var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 500) / 2;
            openparentWindow("newWindow","修改法律法规",xc,yc,"800","500","${ctx}/jsp/LawsRegulations/lawsRegulationsInitEdit.action?flag=mod&lawsRegulations.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	 */
        }
        function view(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/LawsRegulations/lawsRegulationsView.action?flag=view&lawsRegulations.id="+row_Id,900,600);
        	/* var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 500) / 2;
            openparentWindow("newWindow","查看法律法规",xc,yc,"800","500","${ctx}/jsp/LawsRegulations/lawsRegulationsView.action?flag=view&lawsRegulations.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	 */
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_lawsRegulations();
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
		                	url : "lawsRegulationsDel.action",
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
		                        	search_lawsRegulations();
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
        
        function search_lawsRegulations(){
        	var queryParams = {
				"lawsRegulations.title": $("#title").val(),
 "queryFbtimeStart" :$("#queryFbtimeStart").val(),
 "queryFbtimeEnd" :$("#queryFbtimeEnd").val(),
 "querySxtimeStart" :$("#querySxtimeStart").val(),
 "querySxtimeEnd" :$("#querySxtimeEnd").val(),
"lawsRegulations.fabr": $("#fabr").val(),
"lawsRegulations.fgstatus": $("#fgstatus").val(),
"lawsRegulations.regutionsid": $("#regutionsid").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'法律法规列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'lawsRegulationsQuery.action',
				queryParams:{
					"lawsRegulations.title": $("#title").val(),
 "queryFbtimeStart" :$("#queryFbtimeStart").val(),
 "queryFbtimeEnd" :$("#queryFbtimeEnd").val(),
 "querySxtimeStart" :$("#querySxtimeStart").val(),
 "querySxtimeEnd" :$("#querySxtimeEnd").val(),
"lawsRegulations.fabr": $("#fabr").val(),
"lawsRegulations.fgstatus": $("#fgstatus").val(),
"lawsRegulations.regutionsid": $("#regutionsid").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				        {field:'title',title:'标题',width:100},
						{field:'fbtime',title:'发布时间',width:200,formatter:function(value,rec){
						if(rec.fbtime==null) return;
						var date = new Date(rec.fbtime.time);
						var month = parseInt(date.getMonth()+1);
						return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();}}
						,
						{field:'sxtime',title:'生效时间',width:200,formatter:function(value,rec){
						if(rec.sxtime==null) return;
						var date = new Date(rec.sxtime.time);
						var month = parseInt(date.getMonth()+1);
						return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();}}
						,
						{field:'fabr',title:'发布人',width:100},
						{field:'fgstatus',title:'状态',width:100,formatter:function(value,rec){
			  						var temp = '';
								    if("1" == rec.fgstatus){
								    	temp = "启用";
								    }else{
								    	temp = "停用";
								    }
								    return temp;
					    		}},
						{field:'regutionsid',title:'法规级别',width:100,formatter:function(value,rec){
			  						var temp = '';
								    $.ajax({
									    url: '${ctx}/jsp/RegulationsLevel/findName.action',
									    type: 'post',
									    dataType: 'json',
									    async : false,
									    data:{        
									    	"regulationsLevel.uplevelId" : rec.regutionsid
									    },
									    error: function(){
									        $.messager.alert('提示','获取法规级别错误！');
									    },
									    success: function(data){
									    	if(data.result != false){
									        	temp = data.result;
									    	}
								    	}
									});
								    return temp;
					    		}},
	
				          {field:'op',title:'操作',width:100,formatter:function(value,rec){
								var value = "";
					    		if(${flag}=='1'){
				          			 value += "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
				          		}else{
				          			 value += "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>";
				          		}
					    		return value;
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
				<th width="15%">标题</th>
				<td width="35%"><input style="width:70%"  name="lawsRegulations.title" id="title" value="${lawsRegulations.title}" type="text"></td>
				<th width="15%">发布人</th>
				<td width="35%"><input style="width:70%"  name="lawsRegulations.fabr" id="fabr" value="${lawsRegulations.fabr}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">生效时间</th>
				<td width="35%"><input name="querySxtimeStart" id="querySxtimeStart" value="<fmt:formatDate type='both' value='${querySxtimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'querySxtimeEnd\')}'})" >
					&nbsp;-&nbsp;&nbsp;<input name="querySxtimeEnd" id="querySxtimeEnd" value="<fmt:formatDate type='both' value='${querySxtimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'querySxtimeStart\')}'})" ></td>
				<th width="15%">发布时间</th>
				<td width="35%"><input name="queryFbtimeStart" id="queryFbtimeStart" value="<fmt:formatDate type='both' value='${queryFbtimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryFbtimeEnd\')}'})" >
					&nbsp;- &nbsp;<input name="queryFbtimeEnd" id="queryFbtimeEnd" value="<fmt:formatDate type='both' value='${queryFbtimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryFbtimeStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">状态</th>
				<td width="35%">
					<cus:SelectOneTag style="width:70%"  property="lawsRegulations.fgstatus"  codeName="法规状态" defaultText="--请选择--"
									value="${lawsRegulations.fgstatus}" datatype="*1-8" errormsg='此项为必填' maxlength="9" />
				</td>
				<th width="15%">法规级别</th>
				<td width="35%">
					<input style="width:70%"  name="lawsRegulations.regutionsid" id="regutionsid" value="${lawsRegulations.regutionsid}" type="text">
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
				<center>
					<a href="###" class="easyui-linkbutton" onclick="search_lawsRegulations()" iconCls="icon-search">查询</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
					<s:if test="flag == 1">
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
					</s:if>
				</center>
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
