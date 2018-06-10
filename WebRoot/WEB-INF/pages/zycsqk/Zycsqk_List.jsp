<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>作业场所情况</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        function addNew(){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zycsqk/zycsqkInitEdit.action?flag=add",900,600);
        	//window.open("${ctx}/jsp/zycsqk/zycsqkInitEdit.action?flag=add");
        }
        function edit(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zycsqk/zycsqkInitEdit.action?flag=mod&zycsqk.id="+row_Id,900,600);
        	//window.open("${ctx}/jsp/zycsqk/zycsqkInitEdit.action?flag=mod&zycsqk.id="+row_Id);
        }
        function view(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zycsqk/zycsqkView.action?zycsqk.id="+row_Id,900,600);
        	//window.open("${ctx}/jsp/zycsqk/zycsqkView.action?zycsqk.id="+row_Id);
        }
        function editJcry(row_Id,cjmc)
        {
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zycsjcry/zycsjcryList.action?zycsjcry.zycsid="+row_Id,900,600);
        	//parent.parent.frames["frm"].addTab("zycsjcry",cjmc+"-接触人员","${ctx}/jsp/zycsjcry/zycsjcryList.action?zycsjcry.zycsid="+row_Id);
        }
        
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zycsqk();
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
		                	url : "zycsqkDel.action",
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
		                        	search_zycsqk();
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
        
        function search_zycsqk(){
        	var queryParams = {
				"zycsqk.szzid": $("#szzid").val(),
"zycsqk.qymc": $("#qymc").val(),
"zycsqk.cjmc": $("#cjmc").val(),
"zycsqk.wccj": $("#wccj").val(),
"zycsqk.sbzt": $("#sbzt").val(),
"zycsqk.czfs": $("#czfs").val(),
"zycsqk.fhssmc": $("#fhssmc").val(),
"zycsqk.grfhyp": $("#grfhyp").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'作业场所情况',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zycsqkQuery.action',
				queryParams:{
					"zycsqk.szzid": $("#szzid").val(),
"zycsqk.qymc": $("#qymc").val(),
"zycsqk.cjmc": $("#cjmc").val(),
"zycsqk.wccj": $("#wccj").val(),
"zycsqk.sbzt": $("#sbzt").val(),
"zycsqk.czfs": $("#czfs").val(),
"zycsqk.fhssmc": $("#fhssmc").val(),
"zycsqk.grfhyp": $("#grfhyp").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
				          {field:'szzname',title:'所在镇',width:fixWidth(0.1)},
{field:'qymc',title:'企业名称',width:fixWidth(0.1)},
{field:'cjmc',title:'车间名称',width:fixWidth(0.1)},
{field:'wccj',title:'无尘车间',width:fixWidth(0.1)},
{field:'sbzt',title:'设备状态',width:fixWidth(0.1)},
{field:'czfs',title:'操作方式',width:fixWidth(0.1)},
{field:'fhssmc',title:'防护设施名称',width:fixWidth(0.1)},
{field:'grfhyp',title:'个人防护用品',width:fixWidth(0.1)},
				          {field:'op',title:'操作',width:fixWidth(0.2),formatter:function(value,rec){
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
					        		    str+= "<span style='color:red;cursor:hand' onclick=\"editJcry('"+rec.id+"','" + rec.cjmc +"')\">编辑接触人员</span>";
					        	 return str;
					        	 **/
	                            if(flag == '1')
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">修改</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"editJcry('"+rec.id+"','" + rec.cjmc +"')\">编辑接触人员</span>";
				          		}
	                            else
	                            {
	                            	return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"editJcry('"+rec.id+"','" + rec.cjmc +"')\">查看接触人员</span>";
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
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">企业名称</th>
				<td width="35%"><input name="zycsqk.qymc" id="qymc" value="${zycsqk.qymc}" type="text"></td>
				<th width="15%">车间名称</th>
				<td width="35%"><input name="zycsqk.cjmc" id="cjmc" value="${zycsqk.cjmc}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">无尘车间</th>
				<td width="35%"><s:select id="wccj" cssStyle="width:100px" listKey="key" listValue="value"  theme="simple" list="#{'':'','是':'是','否':'否'}" name="zycsqk.wccj" value="{zycsqk.wccj}"/></td>
				<th width="15%">设备状态</th>
				<td width="35%"><s:select id="sbzt" cssStyle="width:100px" listKey="key" listValue="value"  theme="simple" list="#{'':'','封闭':'封闭','半封闭':'半封闭','敞开':'敞开'}" name="zycsqk.sbzt" value="{zycsqk.sbzt}"/></td>
			</tr>
			<tr>
				<th width="15%">操作方式</th>
				<td width="35%"><s:select id="czfs" cssStyle="width:100px" listKey="key" listValue="value"  theme="simple" list="#{'':'','机械化':'机械化','半机械化':'半机械化','人工':'人工'}" name="zycsqk.czfs" value="{zycsqk.czfs}"/></td>
				<th width="15%">防护设施名称</th>
				<td width="35%"><s:select id="fhssmc" cssStyle="width:100px" listKey="key" listValue="value"  theme="simple" list="#{'':'','排风扇':'排风扇','通风罩':'通风罩'}" name="zycsqk.fhssmc" value="{zycsqk.fhssmc}"/></td>
			</tr>
			<tr>
				<th width="15%">个人防护用品</th>
				<td width="35%"><s:select id="grfhyp" cssStyle="width:100px" listKey="key" listValue="value"  theme="simple" list="#{'':'','头盔':'头盔','手套':'手套','其他':'其他'}" name="zycsqk.grfhyp" value="{zycsqk.grfhyp}"/></td>
				<s:if test="flag==3">
				 	<th width="15%">所属地区</th>
					<td width="35%">
					<cus:SelectOneTag property="county" defaultText='请选择' codeName="地址"  onchange="querySzz(this.value);"  style="width:150px;" />
					<select id="szzid" name="zycsqk.szzid" style="width:150px;" ><option value="">请选择</option></select>
						
					<!--hanxc 2014/11/11 		
					<cus:SelectOneTag property="zycsqk.szzid" defaultText='请选择' codeName="相城地址" value="${zycsqk.szzid}" /></td>
					 -->
				</s:if>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				 
				<a href="###" class="easyui-linkbutton" onclick="search_zycsqk()" iconCls="icon-search">查询</a>&nbsp;
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
