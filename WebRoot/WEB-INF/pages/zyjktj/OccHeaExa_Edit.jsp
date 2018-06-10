<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		var num = 1;
		function addNewWhp()
		{
			num=document.getElementsByName("occHeaExaList.occupationalDiseasName").length+1;
			var div= document.getElementById("more");
			var s = "<tr id='" + num + "'>";
				s += "<td style='text-align:center'>" + num + "</td>";
			s += "<td style='text-align:center'><input type='text' name='occHeaExaList.occupationalDiseasName'    onblur='setVal(this)' style='width:100px'/></td>";
			s += "<td style='text-align:center'><input type='text' name='occHeaExaList.preOccupationHealthNumber'    onblur='setVal(this)' style='width:100px'/></td>";
			s += "<td style='text-align:center'><input type='text' name='occHeaExaList.postOccupationalHealth'    onblur='setVal(this)' style='width:100px'/></td>";
			s += "<td style='text-align:center'><input type='text' name='occHeaExaList.postOccupationHealthNumber'    onblur='setVal(this)' style='width:100px'/></td>";
			s += "<td style='text-align:center'><input type='text' name='occHeaExaList.foundPostsNumber'    onblur='setVal(this)' style='width:100px'/></td>";
			s += "<td style='text-align:center'><input type='text' name='occHeaExaList.actualPositionNumber'    onblur='setVal(this)' style='width:100px'/></td>";
			s += "<td style='text-align:center'><input type='button' style='color:red' value='删除' onclick=delWhpZywsfgfzr('" + num + "')></td>";
			s += "</tr>";
			num = num +1;
			var a = div.innerHTML;
			a = a.replace("</TBODY>",s + "</TBODY>");
			a = a.replace("</tbody>",s + "</tbody>");
			div.innerHTML = a;
		}
		function delWhpZywsfgfzr(aid){
			$("tr").remove("tr[id="+aid+"]");
		}
		function delWhp(aid)
		{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "${ctx}/jsp/zyjktj/occHeaExaListDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : aid
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                           $("tr").remove("tr[id="+aid+"]");
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			
			
		}
		function setVal(obj)
		{
			var a = obj.value;
			obj.setAttribute("value", a);
		}
		
		function sub(){
			alert(document.myform1.action);
			document.myform1.submit();
		
		}
		
		function sub(){
			var ds=$("input[name='occChaInf.trainingDateStart']") ;
			var trainingDateStart="";
			if(ds.length>0){
				for(var i=0;i<ds.length;i++){
					if(i==ds.length-1){
						trainingDateStart+=ds[i].value;
					}else{
						trainingDateStart+=ds[i].value+",";
					}
				}
				$("#trainingDateStart").val(trainingDateStart);
			}
			var de=$("input[name='occChaInf.trainingDateEnd']") ;
			var trainingDateEnd="";
			if(de.length>0){
				for(var i=0;i<de.length;i++){
					if(i==de.length-1){
						trainingDateEnd+=de[i].value;
					}else{
						trainingDateEnd+=de[i].value+",";
					}
				}
				$("#trainingDateEnd").val(trainingDateEnd);
			}
			document.myform1.submit();
		
		}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
	<form name="myform1" method="post" enctype="multipart/form-data" action="occHeaExaSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occHeaExa.id" value="${occHeaExa.id}">
		<input type="hidden" name="occHeaExa.createTime" value="<fmt:formatDate type="both" value="${occHeaExa.createTime}" />">
		<input type="hidden" name="occHeaExa.updateTime" value="${occHeaExa.updateTime}">
		<input type="hidden" name="occHeaExa.createUserID" value="${occHeaExa.createUserID}">
		<input type="hidden" name="occHeaExa.updateUserID" value="${occHeaExa.updateUserID}">
		<input type="hidden" name="occHeaExa.deptId" value="${occHeaExa.deptId}">
		<input type="hidden" name="occHeaExa.delFlag" value="${occHeaExa.delFlag}">
		
			<table width="100%" border="0">
				<tr>
					<th width="15%">体检机构</th>
					<td width="35%"><input name="occHeaExa.medicalInstitutionName" style="width:60%" value="${occHeaExa.medicalInstitutionName}" type="text" maxlength="127" datatype="*1-127" errormsg='体检机构必须是1到127位字符！' nullmsg='体检机构不能为空！' sucmsg='体检机构填写正确！'><font style='color:red'>*</font></td>
				</tr>
				 
				 
				 <tr>
					<td colspan="4">
						<div id="more">
						<table>
							<tr>
						    	<td  style="text-align:center;width:5%">序号</td>
								<td  style="text-align:center;width:15%">职业病危害因素名称</td>
								<td  style="text-align:center;width:15%">岗前职业健康体检人数</td>
								<td  style="text-align:center;width:15%">岗中职业健康体检人数</td>
								<td  style="text-align:center;width:15%">离岗职业健康体检人数</td>
								<td  style="text-align:center;width:15%">体检发现应调岗离岗人数</td>
								<td  style="text-align:center;width:15%">实际调离岗位人数</td>
								<td  style="text-align:center;width:5%"><input type="button" value="添加" onclick="addNewWhp()"/></td>
							</tr>	
							<c:forEach var="occHeaExaList" items="${occHeaExaLists}"  varStatus="status">
								<tr style="text-align: center" id="${occHeaExaList.id}">
									<td style='text-align:center'>${status.index+1}</td>
									<td style='text-align:center'><input name="occHeaExaList.occupationalDiseasName" value="${occHeaExaList.occupationalDiseasName}" type="text" onblur="setVal(this)" style='width:100px'></td>
									<td style='text-align:center'><input name="occHeaExaList.preOccupationHealthNumber" value="${occHeaExaList.preOccupationHealthNumber}" type="text" onblur="setVal(this)" style='width:100px'></td>
									<td style='text-align:center'><input name="occHeaExaList.postOccupationalHealth" value="${occHeaExaList.postOccupationalHealth}" type="text" onblur="setVal(this)" style='width:100px'></td>
									<td style='text-align:center'><input name="occHeaExaList.postOccupationHealthNumber" value="${occHeaExaList.postOccupationHealthNumber}" type="text" onblur="setVal(this)" style='width:100px'></td>
									<td style='text-align:center'><input name="occHeaExaList.foundPostsNumber" value="${occHeaExaList.foundPostsNumber}" type="text" onblur="setVal(this)" style='width:100px'></td>
									<td style='text-align:center'><input name="occHeaExaList.actualPositionNumber" value="${occHeaExaList.actualPositionNumber}" type="text" onblur="setVal(this)" style='width:100px'></td>
									<td style='text-align:center'><input type="button" style="color:red" value="删除" onclick="delWhp('${occHeaExaList.id}')"></td>
									<td ><input name="occHeaExaListid" value="${occHeaExaList.id}" type="hidden" ></td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
				 
				 
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="btn_01" type="submit" >添加<b></b></a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="btn_01" type="submit" >更新<b></b></a>&nbsp;
						</s:else>						
						<a href="#" class="btn_01"  onclick="parent.close_win('win_occHeaExa');">关闭<b></b></a>
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
