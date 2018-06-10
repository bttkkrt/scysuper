<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>执法依据管理</title>
    <%@include file="/common/jsLib.jsp"%>
	<script>
        function view(){
        	var zfid = "";
        	var zfname = "";
        	var ids = document.getElementsByName("zfyjid");
        	var names = document.getElementsByName("zfyjname");
        	var tkxs = document.getElementsByName("zfyjtkx");
        	for(var i=0;i<ids.length;i++){
        		zfid += ids[i].value + ",";
        		zfname += names[i].value + tkxs[i].value + ",";
        	}
        	var flags = "${flag}".split(";");
        	window.opener.document.getElementById(flags[0]).value = zfid;
			window.opener.document.getElementById(flags[1]).value = zfname;
			window.close();
        }
        
        var num = 1;
        function choose(id,name,tkx)
        {
        	var ids = document.getElementsByName("zfyjid");
        	var sumnum = 0;
        	for(var i=0;i<ids.length;i++){
        		if(id == ids[i].value)
        		{
        			sumnum ++;
        		}
        	}
        	if(sumnum == 0)
        	{
        		var s = "<tr id='" + num + "'>";
				s += "<td style='text-align:center'><input type='hidden' value='" + name + "' name='zfyjname'>" + name + "</td>";
				s += "<td style='text-align:center'><input type='hidden' value='" + tkx + "' name='zfyjtkx'>" + tkx + "</td>";
				s += "<td style='text-align:center'><input type='hidden' value='" + id + "' name='zfyjid'><a href='###' class='btn_01_mini1' onclick=delZfyj('" + num + "')>删除</a></td>";
				s += "</tr>";
				$("#zfyj").append(s);
				num = num +1;
        	}
        	else
        	{
        		alert("该执法依据已被选择！");
        	}
        }
        
        function delZfyj(aid)
		{
			$("tr").remove("tr[id="+aid+"]");
		}
        
        
        function reloadDate(){
            $('#pagination').datagrid('clearSelections');
        	search_lawBasis();
        }
        function search_lawBasis(){
        	var queryParams = {
				"lawBasis.lawName": $("#lawName").val(),
"lawBasis.lawProvision": $("#lawProvision").val()
			};        	
        	$('#pagination').datagrid('options').queryParams = queryParams;
        	$('#pagination').datagrid('clearSelections');
        	$("#pagination").datagrid('load'); 
        }
        
        $(function(){
        	$('#pagination').datagrid($.extend(window.dg_cm_pp,{
				title:'执法依据列表',
				url:'lawBasisQuery.action',
				queryParams:{
					"lawBasis.lawName": $("#lawName").val(),
"lawBasis.lawProvision": $("#lawProvision").val()
				},
				columns:[[
				          {field:'lawName',title:'法律法规名称',width:100},
{field:'lawProvision',title:'法律法规条款项',width:100,formatter:function(value,rec){return "<span title='"+rec.lawContent+"'>" + value + "</span>";}},
{field:'op',title:'操作',width:100,formatter:function(value,rec){return "<a class='btn_02_mini' onclick=choose('"+rec.id+"','"+rec.lawName+"','"+rec.lawProvision+"') >选择<b></b></a>";}}
			]]
			}));
		});

        
    </script>
</head>
<body>
    <div class="page_content">
	
	    <div class="box_01 submitdata">
		<div class="inner12px">
			<div class="cell boxBmargin12">
			<table width="100%">
				<tr>
					
				<th width="15%">法律法规名称</th>
				<td width="35%"><input name="lawBasis.lawName" id="lawName" style="width: 50%" value="${lawBasis.lawName}" type="text" maxlength="127"></td>
				<th width="15%">法律法规条款项</th>
				<td width="35%"><input name="lawBasis.lawProvision" id="lawProvision" style="width: 50%" value="${lawBasis.lawProvision}" type="text" maxlength="127"></td>
			</tr>
				<tr>
					<td colspan="4" align="center">
						<a href="###" class="btn_01" onclick="search_lawBasis()" >查询<b></b></a>&nbsp;
						<a href="###" class="btn_01" onclick="clear_form(document.myform);" >清空<b></b></a>&nbsp;	
						<a href="###" class="btn_01" onclick="view()" >确认<b></b></a>&nbsp;			
					</td>
				</tr>
				<tr>
					<th width="15%">已选择</th>
					<td width="85%" colspan="3">
						<div style="color:green;overflow:auto;height:100px;">
						<table id="zfyj">
							<c:forEach var="lawBasis" items="${list}" varStatus="status">
								<tr id='${lawBasis.id}'>
									<td style='text-align:center'><input type='hidden' value='${lawBasis.lawName}' name='zfyjname'>${lawBasis.lawName}</td>
									<td style='text-align:center'><input type='hidden' value='${lawBasis.lawProvision}' name='zfyjtkx'>${lawBasis.lawProvision}</td>
									<td style='text-align:center'><input type='hidden' value='${lawBasis.id}' name='zfyjid'><a href='###' class='btn_01_mini1' onclick=delZfyj('${lawBasis.id}')>删除</a></td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="pagination" >
		</div>
		</div>
		</div>
	</div>
</body>
</html>
