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
				  var whysmc = $("#zybwhysjc_zybwhysid").find("option:selected").text();  
	               $("#zybwhysmc").val(whysmc);
		
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="zybwhysjcSave.action";
				document.myform1.submit();
			}
		}
		
		var num = 0;
		function addNewWhp()
		{
			     var whysmc = '';
			     var whysid = '';
				   var AllLabel=document.all.tags('label');
				   var r1 = document.getElementsByName("zybwhysjc.zybwhysid");
	                for (var i = 0; i < r1.length; i++)
	                {
	                        if (r1[i].checked)
	                        {
	                        	if(whysid ==''){
							          		 whysid = r1[i].value;
							          	}else{
							          		 whysid = whysid + ","+r1[i].value;
							          	}
					           for(var j=0;j<AllLabel.length; j++)
							      {
							          if(AllLabel[j].htmlFor == r1[i].id)
							          {
							          	if(whysmc ==''){
							          		 whysmc = AllLabel[j].innerText;
							          	}else{
							          		 whysmc = whysmc + ","+AllLabel[j].innerText;
							          	}
							          }
							      }
	                        }
	                }
		
			var ids = whysid.split(',');
			var mcs = whysmc.split(',');
			var sel = "<select onblur='setVal(this)'>";
			for(var i=0;i<ids.length;i++){
				var option = "<option value='"+ids[i]+"'>"+mcs[i]+"</option>";
				sel = sel+option;
			}
			sel = sel +"</select>";
		
			var div= document.getElementById("more");
			var s = "<tr id='" + num + "'>";
			s += "<td><input type='text' name='zybwhysjc.jcds' style='width:120px' onblur='setVal(this)'/></td>";
			s += "<td><input type='text' name='zybwhysjc.bhgds' style='width:120px' onblur='setVal(this)'/></td>";
			s += "<td>"+sel+"</td>";
			s += "<td><input type='text' name='zybwhysjc.jcjg' style='width:120px' onblur='setVal(this)'/></td>";
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
		                	url : "zybwhysjcDel.action",
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
		
		function validate(event,obj)
        {
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
           	 	return; 
        	} 
        	obj.value = obj.value.replace(/[^\d]/g,""); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
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
		<input type="hidden" name="zybwhysjc.id" value="${zybwhysjc.id}">
		<input type="hidden" name="zybwhysjc.cjid" value="${zybwhysjc.cjid}">
		<input type="hidden" name="zybwhysjc.createTime" value="<fmt:formatDate type="both" value="${zybwhysjc.createTime}" />">
		<input type="hidden" name="zybwhysjc.updateTime" value="${zybwhysjc.updateTime}">
		<input type="hidden" name="zybwhysjc.createUserID" value="${zybwhysjc.createUserID}">
		<input type="hidden" name="zybwhysjc.updateUserID" value="${zybwhysjc.updateUserID}">
		<input type="hidden" name="zybwhysjc.deptId" value="${zybwhysjc.deptId}">
		<input type="hidden" name="zybwhysjc.delFlag" value="${zybwhysjc.delFlag}">
		<input type="hidden" id="zybwhysmc" name="zybwhysjc.zybwhysmc" value="${zybwhysjc.zybwhysmc}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">危害因素</th>
					<td width="35%">
						<s:select theme="simple" list="datas" listKey="id" listValue="name" name="zybwhysjc.zybwhysid"></s:select>
					</td>
					<th width="15%">检测机构</th>
					<td width="35%">
						<input name="zybwhysjc.jcjg" value="${zybwhysjc.jcjg}" type="text" maxlength="255">
					</td>
				</tr>
				<tr>
					<th width="15%">检测点数</th>
					<td width="35%"><input name="zybwhysjc.jcds" value="${zybwhysjc.jcds}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">不合格点数</th>
					<td width="35%">
						<input name="zybwhysjc.bhgds" value="${zybwhysjc.bhgds}" type="text" maxlength="255" onKeyUp="validate(event,this)">
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset()">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
