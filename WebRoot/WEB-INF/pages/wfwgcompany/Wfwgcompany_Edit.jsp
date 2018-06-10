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
			if($('#ifwhpqylx').val()==1)
			{
				var ches = document.getElementsByName("wfwgcompany.whpqylx");
				var ischecked = false;
				for(var i=0;i<ches.length;i++)
				{
					if(ches[i].checked)
					{
						ischecked = true;
						break;
					}
				}
				if(!ischecked)
				{
					alert("危化品企业类型至少选择一项");
					return false;
				}
			}
			if(Validator.Validate(document.myform1,3)){
				var county = document.getElementById("county");
				var countyName = county.options[county.selectedIndex].text;
				document.getElementById('countyName').value = countyName;
				var szzid = document.getElementById("szzid");
				var szzname = szzid.options[szzid.selectedIndex].text;
				document.getElementById('szzname').value = szzname;
				document.myform1.action="wfwgcompanySave.action";
				document.myform1.submit();
			}
		}
		
		
		function querySzc(obj)
    	{
    		$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
				success:function(json){
					json = eval('(' + json + ')');
					var selectContainer = $('#szc'); 
					selectContainer.empty();
					var option = $('<option></option>').text("").val(""); 
					selectContainer.append(option); 
	  				for(var i=0; i<json.length; i++){
						var option = $('<option></option>').text(json[i].name).val(json[i].id); 
						selectContainer.append(option); 
				 	}
				},
				dateType:"json"
			});
    	}   
    	
    	function onlyNum()
		{ 
			var keys=event.keyCode
			if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||(keys==8)||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)||(keys==189)||(keys==109)))
			event.returnValue=false;
		}      
		
		function clearNoNum(event,obj){ 
        	//响应鼠标事件，允许左右方向键移动 
        	event = window.event||event; 
        	if(event.keyCode == 37 | event.keyCode == 39){ 
        	    return; 
       	 	} 
        	//先把非数字的都替换掉，除了数字和. 
        	obj.value = obj.value.replace(/[^\d.]/g,""); 
        	//必须保证第一个为数字而不是. 
        	obj.value = obj.value.replace(/^\./g,""); 
        	//保证只有出现一个.而没有多个. 
       	 	obj.value = obj.value.replace(/\.{2,}/g,"."); 
        	//保证.只出现一次，而不能出现两次以上 
        	obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
        	if(obj.value.length >= 2 && obj.value.substring(0,1) == "0" && obj.value.substring(1,2) != ".")
        	{
        		obj.value = obj.value.substring(1,obj.value.length);
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
		
		
		
	$(document).ready(function(){
		if($('#ifwhpqylx').val()==1)
		{
			document.getElementById("file01").checked=true;
			document.getElementById("file00").checked=false;
		   	$('#ifwhpqylxid').css("display","block"); 
		}else
		{
			document.getElementById("file01").checked=false;
			document.getElementById("file00").checked=true;
		    $('#ifwhpqylxid').css("display","none");  
		}
	});
		
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
    	$(".filetype").change(function(){  
        	var val = $("input[name='file']:checked").val(); //获得选中的radio的值             
            if(val=='1'){    
            	$('#ifwhpqylx').val(1);  
            	$('#ifwhpqylxid').css("display","block"); 
            }else{   
                $('#ifwhpqylx').val(0);   
                $('#ifwhpqylxid').css("display","none");             
            }                    
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
	  					var option = "";
	  					var tempszz = "${wfwgcompany.szzid}";
				    	if(tempszz == json[i].id){
							option = $('<option></option>').text(json[i].name).val(json[i].id).attr("selected",true); 
				    	}else{
				    		option = $('<option></option>').text(json[i].name).val(json[i].id);
				    	}
						selectContainer.append(option); 
				 	}
				},
				dateType:"json"
			});
    }            
    $(function(){
    	querySzz($("#county").val());
    });
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="wfwgcompany.id" value="${wfwgcompany.id}">
		<input type="hidden" name="wfwgcompany.createTime" value="<fmt:formatDate type="both" value="${wfwgcompany.createTime}" />">
		<input type="hidden" name="wfwgcompany.updateTime" value="${wfwgcompany.updateTime}">
		<input type="hidden" name="wfwgcompany.createUserID" value="${wfwgcompany.createUserID}">
		<input type="hidden" name="wfwgcompany.updateUserID" value="${wfwgcompany.updateUserID}">
		<input type="hidden" name="wfwgcompany.deptId" value="${wfwgcompany.deptId}">
		<input type="hidden" name="wfwgcompany.delFlag" value="${wfwgcompany.delFlag}">
		<input type="hidden" name="wfwgcompany.ifwhpqylx" id="ifwhpqylx" value="${wfwgcompany.ifwhpqylx}">
		<input type="hidden" name="wfwgcompany.szcname" id="szcname" value="${wfwgcompany.szcname}">
		<input type="hidden" name="wfwgcompany.szzname" id="szzname" value="${wfwgcompany.szzname}">
		<input type="hidden" name="wfwgcompany.countyname" id="countyName" value="${wfwgcompany.countyName}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%" colspan="3"><input name="wfwgcompany.companyname" value="${wfwgcompany.companyname}"  type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>				
				</tr>
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%"  colspan="3">
						<cus:SelectOneTag  property="wfwgcompany.county" defaultText='请选择' codeName="地址" value="${wfwgcompany.county}" dataType="Require" msg="此项为必选" maxlength="255" onchange="querySzz(this.value);"  style="width:150px;" />
						<select id="szzid" name="wfwgcompany.szzid" dataType="Require" msg="此项为必选"  style="width:150px;" ><option value="">请选择</option></select>
						<font color="red">*</font>
						
						<!-- hanxc 2014/11/11 
						<cus:SelectOneTag property="wfwgcompany.szzid" defaultText='请选择' codeName="相城地址" value="${wfwgcompany.szzid}" dataType="Require" msg="此项为必选" onchange="querySzc(this.value);"/>
						<font color="red">*</font>
					</td>
					<th width="15%">所在村</th>
					<td width="35%">
						<s:select theme="simple" id="szc" name="wfwgcompany.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选" value="{wfwgcompany.szc}"></s:select><font color="red">*</font>
					</td>
						 -->
				</tr>
				<tr>
				 	<th width="15%">负责人</th>
					<td width="35%"><input name="wfwgcompany.fzr" value="${wfwgcompany.fzr}"  type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
					<th width="15%">身份证号码</th>
					<td width="35%"><input name="wfwgcompany.sfz" value="${wfwgcompany.sfz}"  msg="此项为必填" type="text" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:SelectOneTag property="wfwgcompany.hyfl" defaultText='请选择' codeName="企业行业分类" value="${wfwgcompany.hyfl}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
					<th width="15%">违规违法类别</th>
					<td width="35%"><cus:SelectOneTag property="wfwgcompany.wgwflb" defaultText='请选择' codeName="违规违法类别" value="${wfwgcompany.wgwflb}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">是否危化品企业类型</th>
					<td width="35%">			
					 	<input type="radio" name="file"  id="file00" value="0" class="filetype">否
                     	<input type="radio" name="file" id="file01"  value="1"  class="filetype">是 <font style="color:red">*</font>
                    </td>
                    <td width="50%" colspan="2">
					 	<div id="ifwhpqylxid"><cus:hxcheckbox property="wfwgcompany.whpqylx" codeName="危化品企业类型" value="${wfwgcompany.whpqylx}" dataType="Require" msg="此项为必选"/></div>
					</td>
				</tr>
				<tr>
					<th width="15%">是否为五小企业</th>
					<td width="35%"><cus:SelectOneTag property="wfwgcompany.ifwxqy" defaultText='请选择' codeName="是或否" value="${wfwgcompany.ifwxqy}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></td>
					<th width="15%">行政强制措施</th>
					<td width="35%"><cus:hxcheckbox property="wfwgcompany.xzqzcs" defaultText='请选择' codeName="行政强制措施" value="${wfwgcompany.xzqzcs}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">立案日期</th>
					<td width="35%">
						<input type="text" name="wfwgcompany.larq" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${wfwgcompany.larq}" dataType="Require" msg="此项为必填"/><font style="color:red">*</font>
					</td>
					<th width="15%">结案日期</th>
					<td width="35%">
						<input type="text" name="wfwgcompany.jarq" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${wfwgcompany.jarq}" dataType="Require" msg="此项为必填"/><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">是否有营业执照</th>
					<td width="35%"><cus:SelectOneTag property="wfwgcompany.ifyyzz" defaultText='请选择' codeName="是或否" value="${wfwgcompany.ifyyzz}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></td>
					<th width="15%">是否属举报</th>
					<td width="35%"><cus:SelectOneTag property="wfwgcompany.ifjb" defaultText='请选择' codeName="是或否" value="${wfwgcompany.ifjb}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">是否实施经济处罚</th>
					<td width="35%"><cus:SelectOneTag property="wfwgcompany.ifssjjcf" defaultText='请选择' codeName="是或否" value="${wfwgcompany.ifssjjcf}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></td>
					<th width="15%">处罚金额(万元)</th>
					<td width="35%"><input  msg="处罚金额不能为空" name="wfwgcompany.cfje" value="${wfwgcompany.cfje}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
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
