<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>职业健康监护管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        var flag = "${flag}";
        function edit(row_Id){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zycsjcry/zycsjcryInitEdits.action?flag=mod&zycsjcry.id="+row_Id,900,600);
        	//window.open("${ctx}/jsp/zycsjcry/zycsjcryInitEdits.action?flag=mod&zycsjcry.id="+row_Id);
        }
         function view(row_Id){
        	 createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zycsjcry/zycsjcryViews.action?zycsjcry.id="+row_Id,900,600);
            //window.open("${ctx}/jsp/zycsjcry/zycsjcryViews.action?zycsjcry.id="+row_Id);
        }
        function returnToList()
        {
        	location.href = "${ctx}/jsp/zycsjcry/zycsjcryListss.action";
        }
        function editYc(row_Id,xm){
        	createSimpleWindow("win_agencyInfo","","${ctx}/jsp/zyjkjhyc/zyjkjhycList.action?zyjkjhyc.proid="+row_Id,900,600);
        	//parent.parent.frames["frm"].addTab("zyjkjh",xm+"-职业相关异常追踪","${ctx}/jsp/zyjkjhyc/zyjkjhycList.action?zyjkjhyc.proid="+row_Id);
        }
        function viewFile()
        {
        	var a = document.getElementById('ss').style.display;
        	if(a == 'none')
        	{
        		document.getElementById('ss').style.display = "";
        		document.getElementById('qq').innerHTML = "隐藏健康体检汇总表扫描件";
        	}
        	else
        	{
        		document.getElementById('ss').style.display = "none";
        		document.getElementById('qq').innerHTML = "查看健康体检汇总表扫描件";
        	}
        }
        
        function upload()
        {
        	window.open("${ctx}/jsp/zycsjcry/zycsjcryUpload.action");
        }
        
        function savepic(i){
        		window.location.href="${ctx}/jsp/zycsjcry/zycsjcrySaveFile.action?picName="+i;
			}
			//删除附件
		function del(picName){
			$.messager.confirm('删除健康体检汇总表扫描件', '确定要删除健康体检汇总表扫描件？', function(result){
				if (result){
					$.ajax({
						url: "${ctx}/jsp/zycsjcry/zycsjcryImageDel.action",
						data:{ picName : picName},
						type: "POST",
						success:function(){
							$("tr").remove("tr[id="+picName+"]");
						}
				    });
				}
			});
        }
        function close_win(){
        	$("#newWindow").window("close");
        }
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_zycsjcry();
        }
        
         function dels(){
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
		                	url : "zycsjcryDels.action",
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
"zycsjcry.tjlx": $("#tjlx").val(),
"zycsjcry.tjjguo": $("#tjjguo").val(),
"queryTjrqStart": $("#queryTjrqStart").val(),
"queryTjrqEnd": $("#queryTjrqEnd").val(),
"zycsjcry.qyid": $("#qyid").val(),
"zycsjcry.delFlags": $("#delFlags").val()

			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	
			$('#pagination').datagrid({
				title:'职业健康监护列表',
				iconCls:'icon-save',
				nowrap: false,
				striped: true,
				collapsible:true,
				url:'zycsjcryQuery.action',
				queryParams:{
					"zycsjcry.xm": $("#xm").val(),
"zycsjcry.sfz": $("#sfz").val(),
"zycsjcry.tjlx": $("#tjlx").val(),
"zycsjcry.tjjguo": $("#tjjguo").val(),
"queryTjrqStart": $("#queryTjrqStart").val(),
"queryTjrqEnd": $("#queryTjrqEnd").val(),
"zycsjcry.qyid": $("#qyid").val(),
"zycsjcry.delFlags": $("#delFlags").val()
				},
				idField:'id',
				remoteSort: false,
				frozenColumns:[[
				    {field:'id',checkbox:true}
				]],
				columns:[[
{field:'tjlx',title:'体检类型',width:fixWidth(0.1)},
{field:'sfz',title:'身份证',width:fixWidth(0.15)},
{field:'xm',title:'姓名',width:fixWidth(0.1)},
{field:'tjrq',title:'体检时间',width:fixWidth(0.15)},
{field:'tjjg',title:'体检机构',width:fixWidth(0.2)},
{field:'tjjguo',title:'体检结果',width:fixWidth(0.1)},
				          {field:'op',title:'操作',width:fixWidth(0.15),formatter:function(value,rec){
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
					        		    str+= "<span style='color:red;cursor:hand' onclick=\"editYc('"+rec.id+"','" + rec.xm + "')\">异常情况追踪</span>";
					        	 return str;
	                            **/
	                            if(flag == '1')
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"edit('"+rec.id+"')\">编辑</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"editYc('"+rec.id+"','" + rec.xm + "')\">异常情况追踪</span>";
				          		}
				          		else
				          		{
				          			return "<span style='color:red;cursor:hand' onclick=\"view('"+rec.id+"')\">查看</span>&nbsp;<span style='color:red;cursor:hand' onclick=\"editYc('"+rec.id+"','" + rec.xm + "')\">异常情况追踪</span>";
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
	<input type="hidden" id="qyid" value="${zycsjcry.qyid}" name="zycsjcry.qyid"/>
	<input type="hidden" id="delFlags" name="zycsjcry.delFlags" value="0"/>
	<div class="submitdata">
		<table width="100%">
			<tr>
				<th width="15%">身份证</th>
				<td width="35%"><input id="sfz" name="zycsjcry.sfz" value="${zycsjcry.sfz}" type="text" maxlength="255"></td>
				<th width="15%">姓名</th>
				<td width="35%"><input name="zycsjcry.xm" id="xm" value="${zycsjcry.xm}" type="text"></td>
			</tr>
			<tr>
				<th width="15%">体检类型</th>
				<td width="35%"><s:select cssStyle="width:100px" id="tjlx" listKey="key" listValue="value"  theme="simple" list="#{'':'','岗前':'岗前','在岗':'在岗','离岗':'离岗','应急':'应急'}" name="zycsjcry.tjlx" value="{zycsjcry.tjlx}"/></td>
				<th width="15%">体检结果</th>
				<td width="35%"><s:select cssStyle="width:100px" id="tjjguo" listKey="key" listValue="value"  theme="simple" list="#{'':'','正常':'正常','职业相关异常':'职业相关异常','职业禁忌':'职业禁忌','疑似职业病人':'疑似职业病人'}" name="zycsjcry.tjjguo" value="{zycsjcry.tjjguo}"/></td>
			</tr>
			<tr>
				<th width="15%">体检日期</th>
				<td width="35%"><input name="queryTjrqStart" id="queryTjrqStart" value="${queryTjrqStart}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'queryTjrqEnd\')}'})" >
					-<input name="queryTjrqEnd" id="queryTjrqEnd" value="${queryTjrqEnd}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'queryTjrqStart\')}'})" ></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td colspan="4" style="text-align:center">
				
				<a href="###" class="easyui-linkbutton" onclick="search_zycsjcry()" iconCls="icon-search">查询</a>&nbsp;
				<a href="###" class="easyui-linkbutton" onclick="clear_form(document.myform);" iconCls="icon-undo">清空</a>&nbsp;				
				<s:if test = "flag == 2">
					<a href="###" class="easyui-linkbutton" onclick="returnToList();" iconCls="icon-add">返回</a>&nbsp;
					<!--<a href="###" class="easyui-linkbutton" onclick="viewFile(this);" iconCls="icon-add"><span id="qq">查看健康体检汇总表扫描件</span></a>&nbsp;-->
				</s:if>
				<s:else>
					<a href="###" class="easyui-linkbutton" onclick="dels();" iconCls="icon-remove">删除</a>
					<a href="###" class="easyui-linkbutton" onclick="viewFile();" iconCls="icon-add"><span id="qq">查看健康体检汇总表扫描件</span></a>&nbsp;
					<a href="###" class="easyui-linkbutton" onclick="upload();" iconCls="icon-add">上传健康体检汇总表扫描件</a>&nbsp;
				</s:else>
				</td>
			</tr>
	
	</table>
	</div>
	
	<div class="submitdata" id="ss" style="color:green;overflow:auto;height:160px;display:none">
			<table id="zyjkjh">
				<c:forEach var="item" items="${picList}">
					<tr id='${item.id}' style="text-align: center">
						<td width="70%">
							<c:choose>
								<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
									||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
									||fn:endsWith(fn:toLowerCase(item.picName),'.png')
									||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
									||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
									&nbsp;&nbsp;&nbsp;
									<a href="/upload/file/${item.picName}" rel="example_group">
									<img src="/upload/file/${item.picName}"
									border='0' width='220' height='150'/>
									</a>
								</c:when> 
								<c:otherwise> 
									&nbsp;&nbsp;&nbsp;${item.fileName}
								</c:otherwise>
							</c:choose>
						</td>
						<td width="30%">
							<a href="javascript:savepic('${item.id}');">下载</a>&nbsp;&nbsp;
							<a href="javascript:del('${item.id}');">删除</a>
						</td>
					</tr>
				</c:forEach>
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
