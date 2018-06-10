<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>视频通道管理</title>
   	<!-- jquery & easyui js -->
<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/windowOnMove.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

<!-- easyui css -->
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/easyui/themes/icon.css">

<!-- datePicker -->
<script src="${ctx}/webResources/My97DatePicker/WdatePicker.js" type="text/javascript"></script>

<!-- validform -->
<script type="text/javascript" src="${ctx}/webResources/js/Validform/Validform_v5.3.2.js"></script>


<script type="text/javascript">
     window.autoDatagridHeight= <%=(String) session.getAttribute("autoDatagridHeight")%>;
</script>
<!-- platform -->
<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/base.css"> 
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/animation1.css">
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/style/css/default/form.css">
<!-- 原有的表单验证工具，为了兼容旧的项目 -->
<script src="${ctx}/webResources/js/validator.js"></script>

<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />

<link rel="stylesheet" type="text/css" href="${ctx}/webResources/js/poshytip/tip-yellow/tip-yellow.css">
<script type="text/javascript" src="${ctx}/webResources/js/poshytip/jquery.poshytip.min.js"></script>
 <link rel="stylesheet" href="${ctx}/webResources/ZTreeStyle3/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${ctx}/webResources/ZTreeStyle3/js/jquery.ztree.core-3.5.js"></script>
	<style>
	.line {
		opacity: 1;
	}
	
	.layout_01_left {
		width: 300px;
	}
	
	.layout_01_right {
		left: 308px;
	}
	</style>
	<script>
        
       	function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_spgl();
        }
        function search_spgl(){
        	var queryParams = {
				"spgl.loginName": $("#loginName").val(),
				"spgl.guid": window.currentGuid,
 "queryLoginTimeStart" :$("#queryLoginTimeStart").val(),
 "queryLoginTimeEnd" :$("#queryLoginTimeEnd").val(),
 "spgl.videoName": $("#videoName").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        var zTree;
	
		function zTreeOnClick(event, treeId, treeNode) {
			window.currentGuid=treeNode.guid+","+ treeNode.name;
			search_spgl();
		};
		
		var setting = {
			data: {
				simpleData: {
					enable:true
				}
			},
			callback: {
				onClick: zTreeOnClick
			}
		};
	
	
		var str="${str}";
		var zNodes =eval("("+str+")");
        
        $(function(){
        	var t = $("#tree");
			t = $.fn.zTree.init(t, setting, zNodes);
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'视频浏览记录列表',
				url:'${ctx}/jsp/spgl/spglQuery.action',
				queryParams:{
					"spgl.loginName": $("#loginName").val(),
					"spgl.guid": window.currentGuid,
 "queryLoginTimeStart" :$("#queryLoginTimeStart").val(),
 "queryLoginTimeEnd" :$("#queryLoginTimeEnd").val(),
 "spgl.videoName": $("#videoName").val()
				},
				columns:[[
							{field:'videoName',title:'视频名称',width:100},
				          {field:'loginName',title:'查看人名称',width:100},
{field:'loginTime',title:'查看时间',width:100}
				        ]]
			}));
		});

        
    </script>
</head>
<body>
    <div class="page_content">
		<div class="layout_01_left">
			<div class="layout_overflow">
				<ul id="tree" class="ztree">
				</ul>
			</div>
			</div>
			<div class="layout_01_right">
			<div class="layout_overflow">
				<div class="inner6px submitdata">
					<div class="boxBmargin12 cell">
						<form name="myform" method="post">
							<table>
								<tr>
									<th width="15%">视频名称</th>
									<td width="35%"><input name="spgl.videoName" style="width: 50%" id="videoName" value="${spgl.videoName}" type="text"></td>
									<th width="15%">登录人名称</th>
									<td width="35%"><input name="spgl.loginName" style="width: 50%" id="loginName" value="${spgl.loginName}" type="text"></td>
								</tr>
								<tr>
									<th width="15%">登录时间</th>
									<td width="85%" colspan="3"><input name="queryLoginTimeStart" id="queryLoginTimeStart" value="<fmt:formatDate type='both' value='${queryLoginTimeStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'queryLoginTimeEnd\')}'})" >
									-<input name="queryLoginTimeEnd" id="queryLoginTimeEnd" value="<fmt:formatDate type='both' value='${queryLoginTimeEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'queryLoginTimeStart\')}'})" ></td>
								</tr>
								<tr>
									<td colspan="4" align="center">
										<a href="###" class="btn_01" onclick="search_spgl()" >查询<b></b></a>&nbsp;
										<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;				
									</td>
								</tr>
							</table>
						</form>
					</div>
					
					<div id="pagination"></div>
				</div>
			</div>
			</div>
		</div>
</body>
</html>
