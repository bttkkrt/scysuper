<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>上报审核材料</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
	     var type= "${type}";
         var flag = "${flag}";
        function addNew(){
        	window.open("${ctx}/jsp/sbshcl/jshxSbshclInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	window.open("${ctx}/jsp/sbshcl/jshxSbshclInitEdit.action?flag=mod&jshxSbshcl.id="+row_Id);
        }
        function view(row_Id){
        	window.open("${ctx}/jsp/sbshcl/jshxSbshclView.action?jshxSbshcl.id="+row_Id);
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function shenhe(row_Id){
        	window.open("${ctx}/jsp/sbshcl/jshxSbshclShenhe.action?jshxSbshcl.id="+row_Id+"&type="+type+"&isShenhe=1");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_jshxSbshcl();
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
		                	url : "jshxSbshclDel.action",
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
		                        	search_jshxSbshcl();
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
        
        function search_jshxSbshcl(){
        	var queryParams = {
	        	"jshxSbshcl.szzid": $("#szzid").val(),
				"jshxSbshcl.qymc": $("#qymc").val(),
				"jshxSbshcl.jshxTitle": $("#jshxTitle").val(),
				"createTimeStart" :$("#createTimeStart").val(),
				"createTimeEnd" :$("#createTimeEnd").val(),
				"jshxSbshcl.qyid": $("#qyid").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'上报审查材料列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'${ctx}/jsp/sbshcl/jshxSbshclQuery.action',
				queryParams:{
				"jshxSbshcl.szzid": $("#szzid").val(),
					"jshxSbshcl.qymc": $("#qymc").val(),
					"jshxSbshcl.jshxTitle": $("#jshxTitle").val(),
				 	"createTimeStart" :$("#createTimeStart").val(),
				 	"createTimeEnd" :$("#createTimeEnd").val(),
				 	"jshxSbshcl.qyid": $("#qyid").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
						{field:'szzname',title:'所属地区',width:fixWidth(0.2)},
						{field:'qymc',title:'企业名称',width:fixWidth(0.2)},
				        {field:'jshxTitle',title:'标题',width:fixWidth(0.2)},
						{field:'createTime',title:'上传时间',width:fixWidth(0.2),formatter:function(value,rec){
				          	if(rec.createTime==null) {return;}
							var date = new Date(rec.createTime.time);
							var month = parseInt(date.getMonth()+1);
							return date.getFullYear()+'年'+month+'月'+date.getDate()+'日  '+date.getHours()+':'+date.getMinutes()+':'+date.getSeconds();
				          }},
				        {field:'state',title:'状态',width:fixWidth(0.1),formatter:function(value,rec){
								var str = "";
								$.ajax({
				       				url: '${ctx}/jsp/sbshcl/findState.action',
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
				        {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
	                            var tempPassFlag = "9";
								$.ajax({
				       				url: '${ctx}/jsp/sbshcl/findState.action',
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
		function querySzz(obj)
	    {
	    	$.ajax({
					type:"POST",
					url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
					success:function(json){
						json = eval('(' + json + ')');
						var selectContainer = $('#szzid'); 
						selectContainer.empty();
						var option = $('<option></option>').text("").val(""); 
						selectContainer.append(option); 
		  				for(var i=0; i<json.length; i++){
					    	option = $('<option></option>').text(json[i].name).val(json[i].id);
							selectContainer.append(option); 
					 	}
					},
					dateType:"json"
				});
	    }         
    </script>
</head>

<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>

<form name="myform" method="post">
	<input type="hidden" value="${jshxSbshcl.qyid}" id="qyid"/>
	<div class="submitdata">
		<table width="100%">
			
			<tr>
				<th width="15%">标题</th>
				<td width="35%"><input name="jshxSbshcl.jshxTitle" id="jshxTitle" value="${jshxSbshcl.jshxTitle}" type="text"> </td>
				<th width="15%">文件上传时间</th>
				<td width="35%"><input name="createTimeStart" id="createTimeStart" value="<fmt:formatDate type='both' value='${createTimeStart}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'createTimeEnd\')}'})" >
					-<input name="createTimeEnd" id="createTimeEnd" value="<fmt:formatDate type='both' value='${createTimeEnd}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'createTimeStart\')}'})" ></td>
			</tr>
			<s:if test = "isshow != 1">
			<tr>
				<s:if test="flag==3">
					<th width="15%">所属地区</th>
					<td width="35%">
						<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"/>
						<select id="szzid" name="jshxSbshcl.szzid" style="width:100px;" ><option value="">请选择</option></select>
						<!--hanxc 2014/11/8 
						<cus:SelectOneTag property="jshxSbshcl.szzid" defaultText='请选择' codeName="相城地址" value="${jshxSbshcl.szzid}" />
						
						 -->
					</td>
				</s:if>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="jshxSbshcl.qymc" id="qymc" value="${jshxSbshcl.qymc}" type="text"></td>
			</tr>
			</s:if>
			<tr>
				<td colspan="4" align="center" style="text-align:center;">
				 
				<a href="###" class="easyui-linkbutton" onclick="search_jshxSbshcl()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test="deptflag == 2">
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
