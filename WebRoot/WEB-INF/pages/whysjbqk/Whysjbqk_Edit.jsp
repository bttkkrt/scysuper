<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				  var whysid = '';
				  var whysmc = '';
				   var r1 = document.getElementsByName("whysjbqk.fc");
	                for (var i = 0; i < r1.length; i++)
	                {
	                        if (r1[i].checked)
	                        {
	                        		whysid = whysid+r1[i].value+",";
	                        		var v = r1[i].nextSibling.nodeValue;
	                        		whysmc = whysmc+v.substr(0,v.length-1)+",";
	                        }
	                }
	                  var r2 = document.getElementsByName("whysjbqk.hxdw");
	                for (var i = 0; i < r2.length; i++)
	                {
	                        if (r2[i].checked)
	                        {
	                        		whysid = whysid+r2[i].value+",";
	                        		var v = r2[i].nextSibling.nodeValue;
	                        		whysmc = whysmc+v.substr(0,v.length-1)+",";
	                        }
	                }
	                  var r3 = document.getElementsByName("whysjbqk.wlys");
	                for (var i = 0; i < r3.length; i++)
	                {
	                        if (r3[i].checked)
	                        {
	                        		whysid = whysid+r3[i].value+",";
	                        		var v = r3[i].nextSibling.nodeValue;
	                        		whysmc = whysmc+v.substr(0,v.length-1)+",";
	                        }
	                }
	                  var r4 = document.getElementsByName("whysjbqk.swxys");
	                for (var i = 0; i < r4.length; i++)
	                {
	                        if (r4[i].checked)
	                        {
	                        		whysid = whysid+r4[i].value+",";
	                        		var v = r4[i].nextSibling.nodeValue;
	                        		whysmc = whysmc+v.substr(0,v.length-1)+",";
	                        } 
	                }
	                $("#whysid").val(whysid);
	                $("#whysmc").val(whysmc);
	                
		        
		         var r3 = document.getElementsByName("whysjbqk.wlys");
		        
				document.myform1.action="whysjbqkSave.action";
				document.myform1.submit();
			}
		}
		var num = 0;
		function addNewWhp()
		{
			var div= document.getElementById("more");
			var sel="<select name='whysjbqk.qtids' style='width:180px'>"+
			"<option value='粉尘'>粉尘</option><option value='物理因素'>物理因素</option>"+
			"<option value='化学毒物'>化学毒物</option><option value='生物性因素'>生物性因素</option></select>";
			var s = "<tr id='" + num + "'>";
			s += "<td>"+sel+"</td>";
			s += "<td><input type='text' name='whysjbqk.qtnames' style='width:180px' onblur='setVal(this)'/></td>";
			s += "<td><input type='button' value='删除' onclick=delWhp('" + num + "')></td>";
			s += "</tr>";
			num = num + 1;
			var a = div.innerHTML;
			a = a.replace("</TBODY>",s + "</TBODY>");
			a = a.replace("</tbody>",s + "</tbody>");
			div.innerHTML = a;
		} 
		 
		function delWhp(aid)
		{
			$.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "whysjbqkDel.action",
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="whysjbqk.id" value="${whysjbqk.id}">
		<input type="hidden" name="whysjbqk.createTime" value="<fmt:formatDate type="both" value="${whysjbqk.createTime}" />">
		<input type="hidden" name="whysjbqk.updateTime" value="${whysjbqk.updateTime}">
		<input type="hidden" name="whysjbqk.createUserID" value="${whysjbqk.createUserID}">
		<input type="hidden" name="whysjbqk.updateUserID" value="${whysjbqk.updateUserID}">
		<input type="hidden" name="whysjbqk.deptId" value="${whysjbqk.deptId}">
		<input type="hidden" name="whysjbqk.delFlag" value="${whysjbqk.delFlag}">
		<input type="hidden" id="whysid" name="whysjbqk.whysid" value="${whysjbqk.whysid}">
		<input type="hidden" id="whysmc" name="whysjbqk.whysmc" value="${whysjbqk.whysmc}">
		
		<input type="hidden" name="whysjbqk.szzname" value="${whysjbqk.szzname}">
		<input type="hidden" name="whysjbqk.qymc" value="${whysjbqk.qymc}">
		<input type="hidden" name="whysjbqk.szzid" value="${whysjbqk.szzid}">
		<input type="hidden" name="whysjbqk.qyid" value="${whysjbqk.qyid}">
		<input type="hidden" name="whysjbqk.qylx" value="${whysjbqk.qylx}">
		<input type="hidden" name="whysjbqk.hyfl" value="${whysjbqk.hyfl}">
		<input type="hidden" name="whysjbqk.qygm" value="${whysjbqk.qygm}">
		<input type="hidden" name="whysjbqk.qyzclx" value="${whysjbqk.qyzclx}">
		<input type="hidden" name="whysjbqk.szc" value="${whysjbqk.szc}">
		<input type="hidden" name="whysjbqk.szcname" value="${whysjbqk.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">粉尘</th>
					<td colspan="3"><cus:hxcheckbox property="whysjbqk.fc" codeName="粉尘类" value="${whysjbqk.fc}" /></td>
				</tr>
				<tr>
					<th width="15%">化学毒物</th>
					<td colspan="3"><cus:hxcheckbox property="whysjbqk.hxdw" codeName="化学毒物类" value="${whysjbqk.hxdw}" /></td>
				</tr>
				<tr>
					<th width="15%">物理因素</th>
					<td colspan="3"><cus:hxcheckbox property="whysjbqk.wlys" codeName="物理因素类" value="${whysjbqk.wlys}" /></td>
				</tr>
				<tr>
					<th width="15%">生物性因素</th>
					<td colspan="3"><cus:hxcheckbox property="whysjbqk.swxys" codeName="生物因素类" value="${whysjbqk.swxys}" /></td>
				</tr>
				<tr>
					<th width="15%">新增其它分类</th>
					<td width="85%" colspan="3">
						<div id="more">
						<table>
							<tr>
								<th  style="text-align:center">分类</th>
								<th style="text-align:center">其它危害因素</th>
								<th  style="text-align:center"><input type="button" value="添加" onclick="addNewWhp()"/></th>
							</tr>	
							<c:forEach var="glb" items="${qts}">
								<tr style="text-align: center" id="${glb.id}">
									<td>
									
										<select name='whysjbqk.qtids' style='width:180px'>
											<c:if test="${glb.fl eq '粉尘'}">
												<option value='粉尘' selected>粉尘</option>
												<option value='化学毒物' >化学毒物</option>
												<option value='物理因素' >物理因素</option>
												<option value='生物性因素'>生物性因素</option>
											</c:if>
											<c:if test="${glb.fl eq '化学毒物'}">
												<option value='粉尘'>粉尘</option>
												<option value='化学毒物' selected>化学毒物</option>
												<option value='物理因素' >物理因素</option>
												<option value='生物性因素'>生物性因素</option>
											</c:if>
											<c:if test="${glb.fl eq '物理因素'}">
												<option value='粉尘'>粉尘</option>
												<option value='化学毒物' >化学毒物</option>
												<option value='物理因素' selected >物理因素</option>
												<option value='生物性因素'>生物性因素</option>
											</c:if>
											<c:if test="${glb.fl eq '生物性因素'}">
												<option value='粉尘'>粉尘</option>
												<option value='化学毒物' >化学毒物</option>
												<option value='物理因素' >物理因素</option>
												<option value='生物性因素' selected>生物性因素</option>
											</c:if>
											
										</select>
									</td>
									<td><input name="whysjbqk.qtnames" value="${glb.qtwhys}" type="text" style="width:180px" onblur="setVal(this)"></td>
									<td><input type="button" value="删除" onclick="delWhp('${glb.id}')"></td>
								</tr>
							</c:forEach>
						</table> 
						</div>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px"  style="text-align:center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
