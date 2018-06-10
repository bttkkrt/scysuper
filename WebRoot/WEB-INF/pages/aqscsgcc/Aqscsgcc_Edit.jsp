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
	  $(function(){ 
		  if("${flag}"=="1"){
				layer.alert('更新成功', {
					  skin: 'layui-layer-molv' //样式类名
					  ,closeBtn: 0
					}, function(){
						window.opener=null;
						window.open('','_self');
						window.close();
					});
			}else if("${flag}"=="2"){
				layer.alert('更新失败');
			}
	  });
	
		function save(){
			if(Validator.Validate(document.myform1,3)){
				for(var i=1;i<22;i++)
				{
					changeTotal(i);
				}
				document.myform1.action="aqscsgccSave.action";
				document.myform1.submit();
			}
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
        
        function changeTotal(obj)
        {
        	var wxptotal = 0;
        	var yhbztotal = 0;
			var yjtotal = 0;
			var yousetotal = 0;
			var qttotal = 0;
			var total1 = 0;
			var total2 = 0;
			var total3 = 0;
			var total = 0;
			if(obj < 17)
			{
				var b = parseInt(obj/4);
				var s = parseInt(obj%4);
				if(s == 0)
				{
					b = b-1;
				}
				var c = 1+b*4;
				var d = c + 1;
				var e = c + 2;
				var f = c + 3;
				if(c <9)
				{
					c = '0' + c;
					d = '0' + d;
					e = '0' + e;
					f = '0' + f;
				}
				else if(c == 9)
				{
					c = '0' + c;
					d = '' + d;
					e = '' + e;
					f = '' + f;
				}
				else
				{
					c = '' + c;
					d = '' + d;
					e = '' + e;
					f = '' + f;
				}
				var wxp1 = document.getElementById('wxp' + d).value;
        		var wxp2 = document.getElementById('wxp' + e).value;
        		var wxp3 = document.getElementById('wxp' + f).value;
        		wxptotal = accAdd(wxptotal,wxp1);
        		wxptotal = accAdd(wxptotal,wxp2);
        		wxptotal = accAdd(wxptotal,wxp3);
        		total1 = accAdd(total1,wxp1);
        		total2 = accAdd(total2,wxp2);
        		total3 = accAdd(total3,wxp3);
        		total = accAdd(total,wxp1);
        		total = accAdd(total,wxp2);
        		total = accAdd(total,wxp3);
        		var yhbz1 = document.getElementById('yhbz'+d).value;
        		var yhbz2 = document.getElementById('yhbz'+e).value;
        		var yhbz3 = document.getElementById('yhbz'+f).value;
        		yhbztotal = accAdd(yhbztotal,yhbz1);
        		yhbztotal = accAdd(yhbztotal,yhbz2);
        		yhbztotal = accAdd(yhbztotal,yhbz3);
        		total1 = accAdd(total1,yhbz1);
        		total2 = accAdd(total2,yhbz2);
        		total3 = accAdd(total3,yhbz3);
        		total = accAdd(total,yhbz1);
        		total = accAdd(total,yhbz2);
        		total = accAdd(total,yhbz3);
        		var yj1 = document.getElementById('yj'+d).value;
        		var yj2 = document.getElementById('yj'+e).value;
        		var yj3 = document.getElementById('yj'+f).value;
        		yjtotal = accAdd(yjtotal,yj1);
        		yjtotal = accAdd(yjtotal,yj2);
        		yjtotal = accAdd(yjtotal,yj3);
        		total1 = accAdd(total1,yj1);
        		total2 = accAdd(total2,yj2);
        		total3 = accAdd(total3,yj3);
        		total = accAdd(total,yj1);
        		total = accAdd(total,yj2);
        		total = accAdd(total,yj3);
        		var youse1 = document.getElementById('youse'+d).value;
        		var youse2 = document.getElementById('youse'+e).value;
        		var youse3 = document.getElementById('youse'+f).value;
        		yousetotal = accAdd(yousetotal,youse1);
        		yousetotal = accAdd(yousetotal,youse2);
        		yousetotal = accAdd(yousetotal,youse3);
        		total1 = accAdd(total1,youse1);
        		total2 = accAdd(total2,youse2);
        		total3 = accAdd(total3,youse3);
        		total = accAdd(total,youse1);
        		total = accAdd(total,youse2);
        		total = accAdd(total,youse3);
        		var qt1 = document.getElementById('qt'+d).value;
        		var qt2 = document.getElementById('qt'+e).value;
        		var qt3 = document.getElementById('qt'+f).value;
        		qttotal = accAdd(qttotal,qt1);
        		qttotal = accAdd(qttotal,qt2);
        		qttotal = accAdd(qttotal,qt3);
        		total1 = accAdd(total1,qt1);
        		total2 = accAdd(total2,qt2);
        		total3 = accAdd(total3,qt3);
        		total = accAdd(total,qt1);
        		total = accAdd(total,qt2);
        		total = accAdd(total,qt3);
        		document.getElementById('wxp'+c).value = wxptotal;
        		document.getElementById('yhbz'+c).value = yhbztotal;
        		document.getElementById('yj'+c).value = yjtotal;
        		document.getElementById('youse'+c).value = yousetotal;
        		document.getElementById('qt'+c).value = qttotal;
        		document.getElementById('hj'+c).value = total;
        		document.getElementById('hj'+d).value = total1;
        		document.getElementById('hj'+e).value = total2;
        		document.getElementById('hj'+f).value = total3;
			}
			else
			{
				var b = parseInt(obj/3);
				var c = b*3-1;
				var d = c + 1;
				var e = c + 2;
				var wxp1 = document.getElementById('wxp'+d).value;
        		var wxp2 = document.getElementById('wxp'+e).value;
        		total1 = accAdd(total1,wxp1);
        		total2 = accAdd(total2,wxp2);
        		total = accAdd(total,wxp1);
        		total = accAdd(total,wxp2);
        		var yhbz1 = document.getElementById('yhbz'+d).value;
        		var yhbz2 = document.getElementById('yhbz'+e).value;
        		total1 = accAdd(total1,yhbz1);
        		total2 = accAdd(total2,yhbz2);
        		total = accAdd(total,yhbz1);
        		total = accAdd(total,yhbz2);
        		var yj2 = document.getElementById('yj'+e).value;
        		total2 = accAdd(total2,yj2);
        		total = accAdd(total,yj2);
        		var youse2 = document.getElementById('youse'+e).value;
        		total2 = accAdd(total2,youse2);
        		total = accAdd(total,youse2);
        		var qt2 = document.getElementById('qt'+e).value;
        		total2 = accAdd(total2,qt2);
        		total = accAdd(total,qt2);
        		document.getElementById('hj'+c).value = total;
        		document.getElementById('hj'+d).value = total1;
        		document.getElementById('hj'+e).value = total2;
			}
        }
        
        //加法
		function accAdd(arg1, arg2) {
        	var r1, r2, m, c;
        	try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
        	try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
        	c = Math.abs(r1 - r2);
        	m = Math.pow(10, Math.max(r1, r2))
       	 	if (c > 0) {
           	 	var cm = Math.pow(10, c);
            	if (r1 > r2) {
                	arg1 = Number(arg1.toString().replace(".", ""));
                	arg2 = Number(arg2.toString().replace(".", "")) * cm;
            	}
            	else {
                	arg1 = Number(arg1.toString().replace(".", "")) * cm;
                	arg2 = Number(arg2.toString().replace(".", ""));
            	}
        	}
        	else {
            	arg1 = Number(arg1.toString().replace(".", ""));
            	arg2 = Number(arg2.toString().replace(".", ""));
        	}
        	return (arg1 + arg2) / m
    	}	
    	
	</script>
	<style>
	th
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:center;
		padding:0 2px 0 0;
	}
	td
	{
		border:1px solid #d5dbdc;
		height:24px;
		text-align:left;
		padding:0 0 0 2px;
		background:#fff;
		text-align:center;
	}
	tr
	{
		border:1px solid #d5dbdc;
		height:24px
	}
	</style>
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="aqscsgcc.id" value="${aqscsgcc.id}">
		<input type="hidden" name="aqscsgcc.createTime" value="<fmt:formatDate type="both" value="${aqscsgcc.createTime}" />">
		<input type="hidden" name="aqscsgcc.updateTime" value="${aqscsgcc.updateTime}">
		<input type="hidden" name="aqscsgcc.createUserID" value="${aqscsgcc.createUserID}">
		<input type="hidden" name="aqscsgcc.updateUserID" value="${aqscsgcc.updateUserID}">
		<input type="hidden" name="aqscsgcc.deptId" value="${aqscsgcc.deptId}">
		<input type="hidden" name="aqscsgcc.delFlag" value="${aqscsgcc.delFlag}">
		
		<div style="clear:both;background:#f5f8f9;border:1px solid #e8eff2;margin:6px 0 0 0">
			<table width="100%" border="0" style="border-collapse:collapse;border:1px solid #d5dbdc;width:100%">
				<tr>
					<th width="16%">月份</th>
					<td width="24%" colspan="2"><input name="aqscsgcc.yf" value="${aqscsgcc.yf}" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="16%">项目内容</th>
					<th width="12%">计量单位</th>
					<th width="12%">合计</th>
					<th width="12%">危险化学品</th>
					<th width="12%">烟花爆竹</th>
					<th width="12%">冶金</th>
					<th width="12%">有色</th>
					<th width="12%">其它</th>
				</tr>
				<s:iterator id="aqscsgccglb" value="%{list}" status="sta">
					<s:if test="%{#aqscsgccglb.linktype == '17' || #aqscsgccglb.linktype == '20' || #aqscsgccglb.linktype == '23'}">
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/><input type="hidden" name="aqscsgccglb.xmrr" value="<s:property value='#aqscsgccglb.xmrr'/>"></th>
							<th><s:property value="#aqscsgccglb.jldw"/><input type="hidden" name="aqscsgccglb.jldw" value="<s:property value='#aqscsgccglb.jldw'/>"><input type="hidden" name="aqscsgccglb.linktype" value="<s:property value='#aqscsgccglb.linktype'/>"></th>
							<td><input id="hj<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.hj" value="<s:property value='#aqscsgccglb.hj'/>" type="text" style="width:100%;background-color:#EEE" readonly="readonly"/></td>
							<th>乡科级</th>
							<th colspan="2">其它</th>
							<td colspan="2">
								<input type="hidden" name="aqscsgccglb.wxp" value="">
								<input type="hidden" name="aqscsgccglb.yhbz" value="">
								<input type="hidden" name="aqscsgccglb.yj" value="">
								<input type="hidden" name="aqscsgccglb.youse" value="">
								<input type="hidden" name="aqscsgccglb.qt" value="">
							</td>
						</tr>
					</s:if>
					<s:elseif test="%{#aqscsgccglb.linktype == '18' || #aqscsgccglb.linktype == '21' || #aqscsgccglb.linktype == '24'}">
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/><input type="hidden" name="aqscsgccglb.xmrr" value="<s:property value='#aqscsgccglb.xmrr'/>"></th>
							<th><s:property value="#aqscsgccglb.jldw"/><input type="hidden" name="aqscsgccglb.jldw" value="<s:property value='#aqscsgccglb.jldw'/>"><input type="hidden" name="aqscsgccglb.linktype" value="<s:property value='#aqscsgccglb.linktype'/>"></th>
							<td><input id="hj<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.hj" value="<s:property value='#aqscsgccglb.hj'/>" type="text" style="width:100%;background-color:#f5f8f9" readonly="readonly"/></td>
							<td><input id="wxp<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.wxp" value="<s:property value='#aqscsgccglb.wxp'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"/></td>
							<td colspan="2"><input id="yhbz<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.yhbz" value="<s:property value='#aqscsgccglb.yhbz'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"/></td>
							<td colspan="2">
								<input type="hidden" name="aqscsgccglb.yj" value="">
								<input type="hidden" name="aqscsgccglb.youse" value="">
								<input type="hidden" name="aqscsgccglb.qt" value="">
							</td>
						</tr>
					</s:elseif>
					<s:elseif test="%{#aqscsgccglb.linktype == '19' || #aqscsgccglb.linktype == '22' || #aqscsgccglb.linktype == '25'}">
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/><input type="hidden" name="aqscsgccglb.xmrr" value="<s:property value='#aqscsgccglb.xmrr'/>"></th>
							<th><s:property value="#aqscsgccglb.jldw"/><input type="hidden" name="aqscsgccglb.jldw" value="<s:property value='#aqscsgccglb.jldw'/>"><input type="hidden" name="aqscsgccglb.linktype" value="<s:property value='#aqscsgccglb.linktype'/>"></th>
							<td><input id="hj<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.hj" value="<s:property value='#aqscsgccglb.hj'/>" type="text" style="width:100%;background-color:#f5f8f9" readonly="readonly"/></td>
							<td><input id="wxp<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.wxp" value="<s:property value='#aqscsgccglb.wxp'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"/></td>
							<td><input id="yhbz<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.yhbz" value="<s:property value='#aqscsgccglb.yhbz'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"/></td>
							<td><input id="yj<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.yj" value="<s:property value='#aqscsgccglb.yj'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"/></td>
							<td><input id="youse<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.youse" value="<s:property value='#aqscsgccglb.youse'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"></td>
							<td><input id="qt<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.qt" value="<s:property value='#aqscsgccglb.qt'/>" type="text" style="width:100%;" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"/></td>
						</tr>
					</s:elseif>
					<s:else>
						<tr>
							<th><s:property value="#aqscsgccglb.xmrr"/><input type="hidden" name="aqscsgccglb.xmrr" value="<s:property value='#aqscsgccglb.xmrr'/>"></th>
							<th><s:property value="#aqscsgccglb.jldw"/><input type="hidden" name="aqscsgccglb.jldw" value="<s:property value='#aqscsgccglb.jldw'/>"><input type="hidden" name="aqscsgccglb.linktype" value="<s:property value='#aqscsgccglb.linktype'/>"></th>
							<td><input id="hj<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.hj" value="<s:property value='#aqscsgccglb.hj'/>" type="text" style="width:100%;background-color:#f5f8f9" readonly="readonly"/></td>
							<td><input id="wxp<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.wxp" value="<s:property value='#aqscsgccglb.wxp'/>" type="text" style="width:100%;<s:if test="#sta.index%4 != 0">" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"</s:if> <s:else>background-color:#f5f8f9" readonly="readonly"</s:else>/></td>
							<td><input id="yhbz<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.yhbz" value="<s:property value='#aqscsgccglb.yhbz'/>" type="text" style="width:100%;<s:if test="#sta.index%4 != 0">" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"</s:if> <s:else>background-color:#f5f8f9" readonly="readonly"</s:else>/></td>
							<td><input id="yj<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.yj" value="<s:property value='#aqscsgccglb.yj'/>" type="text" style="width:100%;<s:if test="#sta.index%4 != 0">" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"</s:if> <s:else>background-color:#f5f8f9" readonly="readonly"</s:else>/></td>
							<td><input id="youse<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.youse" value="<s:property value='#aqscsgccglb.youse'/>" type="text" style="width:100%;<s:if test="#sta.index%4 != 0">" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"</s:if> <s:else>background-color:#f5f8f9" readonly="readonly"</s:else>/></td>
							<td><input id="qt<s:property value='#aqscsgccglb.linktype'/>" name="aqscsgccglb.qt" value="<s:property value='#aqscsgccglb.qt'/>" type="text" style="width:100%;<s:if test="#sta.index%4 != 0">" onKeyUp="validate(event,this);" onblur="changeTotal('<s:property value="#aqscsgccglb.linktype"/>')"</s:if> <s:else>background-color:#f5f8f9" readonly="readonly"</s:else>/></td>
						</tr>
					</s:else>
				</s:iterator>
				<tr>
					<th width="16%">负责人</th>
					<td width="24%" colspan="2"><input name="aqscsgcc.fzr" value="${aqscsgcc.fzr}" type="text" maxlength="255"></td>
					<th width="12%">填表人</th>
					<td width="24%" colspan="2"><input name="aqscsgcc.tbr" value="${aqscsgcc.tbr}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="8" height="100px" style="text-align:center">
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
