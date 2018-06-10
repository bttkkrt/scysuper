<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<style>  
  .inputText4 {  
    font-family:Arial,Helvetica,sans-serif;   
    background:none repeat scroll 0 0 #F5F7FD;   
    border:1px solid #AABAA9;   
    padding:5px 7px;   
    width:100px;   
    vertical-align:middle;   
    height:20px;   
    font-size:12px;   
    margin:0;   
    list-style:none outside none;   
    }
  </style>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="zybwhzlSave.action";
				document.myform1.submit();
			}
		}
		function clearNoNum(event,obj){ 
        //响应鼠标事件，允许左右方向键移动 
        event = window.event||event; 
        if(event.keyCode == 37 | event.keyCode == 39){ 
            return; 
        } 
        //先把非数字的都替换掉，除了数字和. 
        obj.value = obj.value.replace(/[^\d]/g,"");
        if(obj.value.substring(0,1) == "0")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
        	}
    } 
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zybwhzl.id" value="${zybwhzl.id}">
		<input type="hidden" name="zybwhzl.createTime" value="<fmt:formatDate type="both" value="${zybwhzl.createTime}" />">
		<input type="hidden" name="zybwhzl.updateTime" value="${zybwhzl.updateTime}">
		<input type="hidden" name="zybwhzl.createUserID" value="${zybwhzl.createUserID}">
		<input type="hidden" name="zybwhzl.updateUserID" value="${zybwhzl.updateUserID}">
		<input type="hidden" name="zybwhzl.deptId" value="${zybwhzl.deptId}">
		<input type="hidden" name="zybwhzl.delFlag" value="${zybwhzl.delFlag}">
		<input type="hidden" name="zybwhzl.szzname" value="${zybwhzl.szzname}">
		<input type="hidden" name="zybwhzl.qymc" value="${zybwhzl.qymc}">
		<input type="hidden" name="zybwhzl.szzid" value="${zybwhzl.szzid}">
		<input type="hidden" name="zybwhzl.qyid" value="${zybwhzl.qyid}">
		<input type="hidden" name="zybwhzl.qylx" value="${zybwhzl.qylx}">
		<input type="hidden" name="zybwhzl.hyfl" value="${zybwhzl.hyfl}">
		<input type="hidden" name="zybwhzl.qygm" value="${zybwhzl.qygm}">
		<input type="hidden" name="zybwhzl.qyzclx" value="${zybwhzl.qyzclx}">
		
		<input type="hidden" name="zybwhzl.ifwhpqylx" value="${zybwhzl.ifwhpqylx}">
		<input type="hidden" name="zybwhzl.ifzywhqylx" value="${zybwhzl.ifzywhqylx}">
		<input type="hidden" name="zybwhzl.ifyhbzjyqy" value="${zybwhzl.ifyhbzjyqy}">
		
		<input type="hidden" name="zybwhzl.szc" value="${zybwhzl.szc}">
		<input type="hidden" name="zybwhzl.szcname" value="${zybwhzl.szcname}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th  width="15%" style="text-align:center;">职业病危害因素种类</th>
					<th width="10%" style="text-align:center;">有或无</th>
					<th width="15%" style="text-align:center;">接触人数</th>
					<th width="15%" style="text-align:center;">接触职业病危害总人数</th>
					<th width="15%" style="text-align:center;">其中女工数</th>
				</tr>
				<tr>
					<th width="15%">粉尘类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.fclyw"  value="#{zybwhzl.fclyw}"/>
					</td>
					<td width="15%"><input   class="inputText4" name="zybwhzl.fcljcrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.fcljcrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.fclzrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.fclzrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.fclngs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.fclngs}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">化学物质类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.hxwzlyw"  value="#{zybwhzl.hxwzlyw}"/>
					</td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.hxwzljcrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.hxwzljcrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.hxwzlzrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.hxwzlzrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.hxwzlngs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.hxwzlngs}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					
					<th width="15%">物理因素类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.wlysyw"  value="#{zybwhzl.wlysyw}"/>
					</td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.wlysjcrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.wlysjcrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.wlyszrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.wlyszrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.wlysngs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.wlysngs}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">放射性物质类</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.fsxwzlyw"  value="#{zybwhzl.fsxwzlyw}"/>
					</td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.fsxwzljcrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.fsxwzljcrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.fsxwzlzrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.fsxwzlzrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.fsxwzlngs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.fsxwzlngs}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">其他</th>
					<td width="10%">
						<s:radio list="#{'1':'有','0':'无'}" theme="simple" name="zybwhzl.qtyw"  value="#{zybwhzl.qtyw}"/>
					</td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.qtjcrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.qtjcrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.qtzrs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.qtzrs}" type="text" maxlength="255"></td>
					<td width="15%"><input  class="inputText4" name="zybwhzl.qtngs" onKeyUp="clearNoNum(event,this)" value="${zybwhzl.qtngs}" type="text" maxlength="255"></td>
				</tr>
			</table>
			<table>
				<tr>
					<th width="15%">填报人</th>
					<td width="35%"><input  class="inputText4" name="zybwhzl.tbr" value="${zybwhzl.tbr}" type="text" maxlength="255"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input  class="inputText4" name="zybwhzl.lxdh" value="${zybwhzl.lxdh}" type="text" maxlength="255"></td>
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
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="window.close();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
