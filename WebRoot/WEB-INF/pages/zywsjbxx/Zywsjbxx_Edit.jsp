<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="zywsjbxx.id==' '">新增</s:if><s:else>修改</s:else>信息</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="zywsjbxxSave.action";
				document.myform1.submit();
			}
		}
		
		
		var num = 1;
		function addNewWhp()
		{
			var div= document.getElementById("more");
			var s = "<tr id='" + num + "'>";
				s += "<td style='text-align:center'>" + num + "</td>";
			s += "<td style='text-align:center'><input type='text' name='zywsglry.glrxm' dataType='Require' msg='此项为必填' onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center'><input type='text' name='zywsglry.glrzw' dataType='Require' msg='此项为必填' onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center'><input type='text' name='zywsglry.glrdh' dataType='Require' msg='此项为必填' onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center'><input type='text' name='zywsglry.glrsj' dataType='Require' msg='此项为必填' onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center'> <select name='zywsglry.slrwhcd'><option value ='0'>初中</option><option value ='1'>高中</option><option value='2'>大学</option><option value='3'>硕士</option><option value='4'>博士</option></select>  </td>";
			s += "<td style='text-align:center'><input type='text' name='zywsglry.slrzy' dataType='Require' msg='此项为必填' onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center'><input type='button' style='color:red' value='删除' onclick=delWhp('" + num + "')></td>";
			s += "</tr>";
			num = num +1;
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
		                	url : "zywsglryDel.action",
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
		
		
		
		
	</script>
	
</head>
<body>
    <!-- add by 高强   11月21日  职业卫生 基本信息-->
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zywsjbxx.id" value="${zywsjbxx.id}">
		<input type="hidden" name="zywsjbxx.createTime" value="<fmt:formatDate type="both" value="${zywsjbxx.createTime}" />">
		<input type="hidden" name="zywsjbxx.updateTime" value="${zywsjbxx.updateTime}">
		<input type="hidden" name="zywsjbxx.createUserID" value="${zywsjbxx.createUserID}">
		<input type="hidden" name="zywsjbxx.updateUserID" value="${zywsjbxx.updateUserID}">
		<input type="hidden" name="zywsjbxx.deptId" value="${zywsjbxx.deptId}">
		<input type="hidden" name="zywsjbxx.delFlag" value="${zywsjbxx.delFlag}">
		
		<input type="hidden" name="zywsjbxx.szzid" value="${zywsjbxx.szzid}">
			<input type="hidden" name="zywsjbxx.szzname" value="${zywsjbxx.szzname}">
			<input type="hidden" name="zywsjbxx.qymc" value="${zywsjbxx.qymc}">
			<input type="hidden" name="zywsjbxx.qylx" value="${zywsjbxx.qylx}">
			<input type="hidden" name="zywsjbxx.hyfl" value="${zywsjbxx.hyfl}">
			<input type="hidden" name="zywsjbxx.qyzclx" value="${zywsjbxx.qyzclx}">
			<input type="hidden" name="zywsjbxx.qyid" value="${zywsjbxx.qyid}">
			
		<div class="submitdata">
			<table width="100%" border="0">
			    <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>基本信息</th>
			    </tr>
			    <tr>
					<th width="15%">填报部门</th>
					<td width="35%"><input name="zywsjbxx.tbbm" value="${zywsjbxx.tbbm}" dataType='Require' msg='此项为必填' type="text" maxlength="255"></td>
					<th width="15%">填报人</th>
					<td width="35%"><input name="zywsjbxx.tbr" value="${zywsjbxx.tbr}" type="text" maxlength="255" dataType='Require' msg='此项为必填'></td>
				</tr>
				<tr>
					<th width="15%">女职工人数</th>
					<td width="35%"><input name="zywsjbxx.nzgs" value="${zywsjbxx.nzgs}" dataType='Require' msg='此项为必填' type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					<th width="15%">接触职业病危害因素人数</th>
					<td width="35%"><input name="zywsjbxx.jcrs" value="${zywsjbxx.jcrs}" type="text" maxlength="255" readonly></td>
					</tr>
				<tr>
					<th width="15%">企业职业病危害类别</th>
					<td width="35%"><cus:SelectOneTag property="zywsjbxx.whlb" defaultText='请选择' codeName="企业职业病危害类别" value="${zywsjbxx.whlb}" /></td>
					<th width="15%">职业病危害行业类别</th>
					<td width="35%"><cus:SelectOneTag property="zywsjbxx.hylb" defaultText='请选择' codeName="职业病危害行业类别" value="${zywsjbxx.hylb}" /></td>
					</tr>
				<tr>
					<th width="15%">接触职业病危害因素女工人数</th>
					<td width="35%"><input name="zywsjbxx.jcngrs" value="${zywsjbxx.jcngrs}" type="text" maxlength="255" readonly></td>
					<th width="15%">历年职业病人数</th>
					<td width="35%"><input name="zywsjbxx.lnzgrs" value="${zywsjbxx.lnzgrs}" type="text" maxlength="255" onKeyUp="validate(event,this)"></td>
					</tr>
				<tr>
					<th width="15%">是否为年度专项整治企业</th>
					<td width="35%"><cus:SelectOneTag property="zywsjbxx.sfzzqy" defaultText='请选择' codeName="是或否" value="${zywsjbxx.sfzzqy}" /></td>
					<th width="15%">职业病危害岗位数</th>
					<td width="35%"><input name="zywsjbxx.gws" value="${zywsjbxx.gws}" type="text" maxlength="255" readonly></td>
					</tr>
				<tr>
					<th width="15%">联系电话</th>
					<td width="35%"><input name="zywsjbxx.lxdh" value="${zywsjbxx.lxdh}" type="text" maxlength="255"></td>
				</tr>
				 
				   <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>职业卫生分管负责人</th>
			    </tr>
				 
				 
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="zywsjbxx.fgxm" value="${zywsjbxx.fgxm}" type="text" maxlength="255"></td>
					<th width="15%">职务</th>
					<td width="35%"><input name="zywsjbxx.fgzw" value="${zywsjbxx.fgzw}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%"><input name="zywsjbxx.fgdh" value="${zywsjbxx.fgdh}" type="text" maxlength="255"></td>
					<th width="15%">手机</th>
					<td width="35%"><input name="zywsjbxx.fgsj" value="${zywsjbxx.fgsj}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:SelectOneTag property="zywsjbxx.fgwhcd" defaultText='请选择' codeName="文化程度" value="${zywsjbxx.fgwhcd}" /></td>
					<th width="15%">专业</th>
					<td width="35%"><input name="zywsjbxx.fgzy" value="${zywsjbxx.fgzy}" type="text" maxlength="255"></td>
				</tr>
				
				   <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>职业卫生管理机构负责人</th>
			    </tr>
			    
			    
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="zywsjbxx.fzrxm" value="${zywsjbxx.fzrxm}" type="text" maxlength="255"></td>
					<th width="15%">职务</th>
					<td width="35%"><input name="zywsjbxx.fzrzw" value="${zywsjbxx.fzrzw}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%"><input name="zywsjbxx.fzrdh" value="${zywsjbxx.fzrdh}" type="text" maxlength="255"></td>
					<th width="15%">手机</th>
					<td width="35%"><input name="zywsjbxx.fzrsj" value="${zywsjbxx.fzrsj}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">文化程度</th>
					<td width="35%"><cus:SelectOneTag property="zywsjbxx.fzrwhcd" defaultText='请选择' codeName="文化程度" value="${zywsjbxx.fzrwhcd}" /></td>
					<th width="15%">专业</th>
					<td width="35%"><input name="zywsjbxx.fzrzy" value="${zywsjbxx.fzrzy}" type="text" maxlength="255"></td>
				</tr>
				
				  <tr style="background-color:royalblue">
			    <th style="color:white;text-align:left;font-size :16;" colspan=4>职业卫生管理人员</th>
			    </tr>
			    
			    
			    <tr>
					<td colspan="4">
						<div id="more">
						<table>
							<tr>
						    	<td  style="text-align:center">序号</td>
								<td  style="text-align:center">姓名</td>
								<td  style="text-align:center">职务</td>
								<td  style="text-align:center">办公电话</td>
								<td  style="text-align:center">手机</td>
								<td  style="text-align:center">文化程度</td>
								<td  style="text-align:center">专业</td>
								<td  style="text-align:center"><input type="button" value="添加" onclick="addNewWhp()"/></td>
							</tr>	
							<c:forEach var="zywsglry" items="${zywsglrys}"  varStatus="status">
								<tr style="text-align: center" id="${zywsglry.id}">
								
									<td style='text-align:center'>${status.index+1}</td>
									<td style='text-align:center'><input name="zywsglry.glrxm" value="${zywsglry.glrxm}" type="text" onblur="setVal(this)"></td>
									<td style='text-align:center'><input name="zywsglry.glrzw" value="${zywsglry.glrzw}" type="text" onblur="setVal(this)"></td>
									<td style='text-align:center'><input name="zywsglry.glrdh" value="${zywsglry.glrdh}" type="text" onblur="setVal(this)"></td>
									<td style='text-align:center'><input name="zywsglry.glrsj" value="${zywsglry.glrsj}" type="text" onblur="setVal(this)"></td>
									<td  style='text-align:center'><cus:SelectOneTag property="zywsglry.slrwhcd" defaultText='请选择' codeName="文化程度" value="${zywsglry.slrwhcd}"/></td>
									<td style='text-align:center'><input name="zywsglry.slrzy" value="${zywsglry.slrzy}" type="text" onblur="setVal(this)"></td>
									<td style='text-align:center'><input type="button" style="color:red" value="删除" onclick="delWhp('${zywsglry.id}')"></td>
									<td ><input name="zywsglry.id" value="${zywsglry.id}" type="hidden" ></td>
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
							
						<s:if test="zywsjbxx.id !=''">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">添加</a>&nbsp;
						</s:else>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
