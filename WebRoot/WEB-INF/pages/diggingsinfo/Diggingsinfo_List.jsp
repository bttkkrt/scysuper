<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>DIGGINGSINFO管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var type= "${type}";
        function addNew(){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 500) / 2;
           // openparentWindow("newWindow","添加矿山信息",xc,yc,"800","500","${ctx}/jsp/diggingsinfo/diggingsinfoInitEdit.action?flag=add&dt="+dt.getTime(),true,true,true,false,true,"win");
        	window.open("${ctx}/jsp/diggingsinfo/diggingsinfoInitEdit.action?flag=add&dt="+dt.getTime());
        }
        function edit(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 500) / 2;
            //openparentWindow("newWindow","修改矿山信息",xc,yc,"800","500","${ctx}/jsp/diggingsinfo/diggingsinfoInitEdit.action?flag=mod&diggingsinfo.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	window.open("${ctx}/jsp/diggingsinfo/diggingsinfoInitEdit.action?flag=mod&diggingsinfo.id="+row_Id+"&dt="+dt.getTime());
        }
        function view(row_Id){
        	var dt=new Date();
            var ah = screen.availHeight - 30;
    		var aw = screen.availWidth - 10;
    		var xc = (aw - 800) / 2;
    		var yc = (ah - 500) / 2;
            //openparentWindow("newWindow","查看矿山信息",xc,yc,"800","500","${ctx}/jsp/diggingsinfo/diggingsinfoView.action?diggingsinfo.id="+row_Id+"&dt="+dt.getTime(),true,true,true,false,true,"win");
        	//window.open("${ctx}/jsp/diggingsinfo/diggingsinfoView.action?diggingsinfo.id="+row_Id+"&dt="+dt.getTime());
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/diggingsinfo/diggingsinfoView.action?diggingsinfo.id="+row_Id,900,600);
        }
        function shenhe(row_Id){
        	window.open("${ctx}/jsp/diggingsinfo/diggingsinfoShenhe.action?diggingsinfo.id="+row_Id+"&type="+type+"&isShenhe=1" );
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_diggingsinfo();
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
		                	url : "${ctx}/jsp/diggingsinfo/diggingsinfoDel.action",
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
		                        	search_diggingsinfo();
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
        
        function search_diggingsinfo(){
        	var queryParams = {
				"diggingsinfo.county": $("#county").val(),
"diggingsinfo.mainoreType": $("#mainoreType").val(),
"diggingsinfo.exploitType": $("#exploitType").val(),
 "queryProduceStartdateStart" :$("#queryProduceStartdateStart").val(),
 "queryProduceStartdateEnd" :$("#queryProduceStartdateEnd").val(),
"diggingsinfo.designServeAgelimit": $("#designServeAgelimit").val(),
"diggingsinfo.checkNo": $("#checkNo").val(),
"diggingsinfo.checkUnit": $("#checkUnit").val(),
"diggingsinfo.certificateSum": $("#certificateSum").val(),
"diggingsinfo.engineerSum": $("#engineerSum").val(),
 "queryExploitCertificateStartdateStart" :$("#queryExploitCertificateStartdateStart").val(),
 "queryExploitCertificateStartdateEnd" :$("#queryExploitCertificateStartdateEnd").val(),
 "queryExploitCertificateEnddateStart" :$("#queryExploitCertificateEnddateStart").val(),
 "queryExploitCertificateEnddateEnd" :$("#queryExploitCertificateEnddateEnd").val(),
"diggingsinfo.exploitCertificateNo": $("#exploitCertificateNo").val(),
"diggingsinfo.exploitCertificateUnit": $("#exploitCertificateUnit").val(),
 "querySafeCertificateStartdateStart" :$("#querySafeCertificateStartdateStart").val(),
 "querySafeCertificateStartdateEnd" :$("#querySafeCertificateStartdateEnd").val(),
 "querySafeCertificateEnddateStart" :$("#querySafeCertificateEnddateStart").val(),
 "querySafeCertificateEnddateEnd" :$("#querySafeCertificateEnddateEnd").val(),
"diggingsinfo.safeCertificateNo": $("#safeCertificateNo").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'矿山信息列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${ctx}/jsp/diggingsinfo/diggingsinfoQuery.action',
				queryParams:{
					"diggingsinfo.county": $("#county").val(),
"diggingsinfo.mainoreType": $("#mainoreType").val(),
"diggingsinfo.exploitType": $("#exploitType").val(),
 "queryProduceStartdateStart" :$("#queryProduceStartdateStart").val(),
 "queryProduceStartdateEnd" :$("#queryProduceStartdateEnd").val(),
"diggingsinfo.designServeAgelimit": $("#designServeAgelimit").val(),
"diggingsinfo.checkNo": $("#checkNo").val(),
"diggingsinfo.checkUnit": $("#checkUnit").val(),
"diggingsinfo.certificateSum": $("#certificateSum").val(),
"diggingsinfo.engineerSum": $("#engineerSum").val(),
 "queryExploitCertificateStartdateStart" :$("#queryExploitCertificateStartdateStart").val(),
 "queryExploitCertificateStartdateEnd" :$("#queryExploitCertificateStartdateEnd").val(),
 "queryExploitCertificateEnddateStart" :$("#queryExploitCertificateEnddateStart").val(),
 "queryExploitCertificateEnddateEnd" :$("#queryExploitCertificateEnddateEnd").val(),
"diggingsinfo.exploitCertificateNo": $("#exploitCertificateNo").val(),
"diggingsinfo.exploitCertificateUnit": $("#exploitCertificateUnit").val(),
 "querySafeCertificateStartdateStart" :$("#querySafeCertificateStartdateStart").val(),
 "querySafeCertificateStartdateEnd" :$("#querySafeCertificateStartdateEnd").val(),
 "querySafeCertificateEnddateStart" :$("#querySafeCertificateEnddateStart").val(),
 "querySafeCertificateEnddateEnd" :$("#querySafeCertificateEnddateEnd").val(),
"diggingsinfo.safeCertificateNo": $("#safeCertificateNo").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'county',title:'所属县区',width:fixWidth(0.1),align:'center'},
						{field:'mainoreType',title:'主矿种',width:fixWidth(0.2),align:'center'},
						{field:'exploitType',title:'开采方式',width:fixWidth(0.2),align:'center'},
						{field:'produceStartdate',title:'投产日期',width:fixWidth(0.13),align:'center',formatter:function(value,rec){
						if(rec.produceStartdate==null) return;
						var date = new Date(rec.produceStartdate.time);
						var month = parseInt(date.getMonth()+1);
						return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();}}
						,
						{field:'designServeAgelimit',title:'设计服务年限',width:fixWidth(0.13),align:'center'},
						{field:'state',title:'状态',width:fixWidth(0.1),formatter:function(value,rec){
								var str = "";
								$.ajax({
				       				url: '${ctx}/jsp/diggingsinfo/findState.action',
				        		    type: 'post',
				        		    dataType: 'json',
				        		    async : false,
				        		    data:{    
				        		    	"ids":rec.id
				        		    	},
				        		    error: function(){
				        		    },
				        		    success: function(data){
				        		    	if(data.state==2){
				        		    		str = "<span style='color:red'>未通过</span>";
				        		    	}else if(data.state==1 && rec.dutyFlag==1){
				        		    		str = "<span style='color:blue'>通过</span>";
				        		    	}else{
											str = "<span style='color:green'>待审核</span>";
				        		    	}
				        		    }});
									return str;
							
						                          }},
				          {field:'op',title:'操作',width:fixWidth(0.1),align:'center',formatter:function(value,rec){
	                                var tempPassFlag = "9";
									$.ajax({
					       				url: '${ctx}/jsp/diggingsinfo/findState.action',
					        		    type: 'post',
					        		    dataType: 'json',
					        		    async : false,
					        		    data:{    
					        		    	"ids":rec.id
					        		    	},
					        		    error: function(){
					        		    },
					        		    success: function(data){
					        		    	if(data.state==1){
					        		    		tempPassFlag="1";
					        		    	}else if(data.state==0 || data.state==2){
					        		    		tempPassFlag="2";
					        		    	}else{
					        		    		tempPassFlag="3";
						        		    }
					        		    }});
									var info = "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;"
									if(${deptflag}=='2'){
									
										info += "<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>&nbsp;";
									}
									if(${deptflag}=='1'&&tempPassFlag=='2'&&!(rec.state == '1'&&rec.dutyFlag=='1')){
									
										info += "<span style='color:red;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>&nbsp;";
									}
									return info;
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
				        		    if(rec.state == '0'){
					        		    $.ajax({
											url: '${ctx}/jsp/admin/role/buttons.action',
											type: 'post',
											dataType: 'json',
											async : false,
											data:{    
												"function":"",
												"num":"7", 
												"moduleId":"${currModuleCode}",
												"functionName":"审核" 
												},
											error: function(){
												$.messager.alert('提示','获取一维代码错误！');
											},
											success: function(data){
												if(""!=data.result){
												str+= "<span style='color:orange;cursor:hand' onclick=\"shenhe('"+rec.id+"')\">审核</span>";
												}
											}});
										}
					        	 return str;
					        	 **/
	                             // return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>";
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
				<th width="15%">所属县区</th>
				<td width="35%"><input name="diggingsinfo.county" id="county" value="${diggingsinfo.county}" type="text"></td>
				<th width="15%">主矿种</th>
				<td width="35%"><input name="diggingsinfo.mainoreType" id="mainoreType" value="${diggingsinfo.mainoreType}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">开采方式</th>
				<td width="35%"><input name="diggingsinfo.exploitType" id="exploitType" value="${diggingsinfo.exploitType}" type="text"></td>
				<th width="15%">投产日期</th>
				<td width="35%"><input name="queryProduceStartdateStart" id="queryProduceStartdateStart" value="<fmt:formatDate type='both' value='${queryProduceStartdateStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryProduceStartdateEnd\')}'})" >
					-<input name="queryProduceStartdateEnd" id="queryProduceStartdateEnd" value="<fmt:formatDate type='both' value='${queryProduceStartdateEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryProduceStartdateStart\')}'})" ></td>
			</tr>
			<tr>
				<th width="15%">设计服务年限</th>
				<td width="35%" colspan="3"><input name="diggingsinfo.designServeAgelimit" id="designServeAgelimit" value="${diggingsinfo.designServeAgelimit}" type="text"></td>
				
			</tr>
			
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align: center;">
				 
				<a href="###" class="easyui-linkbutton" onclick="search_diggingsinfo()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<c:if test="${deptflag==2}">
					<a href="###" class="easyui-linkbutton" onclick="addNew();" iconCls="icon-add">添加</a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="del();" iconCls="icon-remove">删除</a>
				</c:if>
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
