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
				document.myform1.action="jshxSgtjSave.action";
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
    
     function validateNum(event,obj)
        {
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
        
        function changezqs()
        {
        	var sgzqs = 0;
        	var fjhqs = document.getElementById('fjhqs').value;
			var shsgqs = document.getElementById('shsgqs').value;
			var sgsgqs = document.getElementById('sgsgqs').value;
        	sgzqs = accAdd(sgzqs,fjhqs);
        	sgzqs = accAdd(sgzqs,shsgqs);
        	sgzqs = accAdd(sgzqs,sgzqs);
        	document.getElementById('sgzqs').value = sgzqs;
        	var sjgs;
        	var zgs = document.getElementById('zgs').value;
			var sjgzr = document.getElementById('sjgzr').value;
			sjgs = accMul(zgs,sjgzr);
			sjgs = accMul(sjgs,8);
        	var sgl1;
        	var sgl2;
        	if(sjgs != null && sjgs != "" && sjgs != "0")
        	{
        		var sgl = accDiv(sgzqs,sjgs);
        		sgl1 = accMul(sgl,1000000).toFixed(2);
        		sgl2 = accMul(sgl,200000).toFixed(2);
        		document.getElementById('sgl').value = sgl1;
        		document.getElementById('sgl2').value = sgl2;
        	}
        	else
        	{
        		document.getElementById('sgl').value = "0";
        		document.getElementById('sgl2').value = "0";
        	}
        }
        
        function changesgl()
        {
        	var sszgs = 0;
			var fjhxs = document.getElementById('fjhxs').value;
			var shsgxs = document.getElementById('shsgxs').value;
			var sgsgxs = document.getElementById('sgsgxs').value;
        	sszgs = accAdd(sszgs,fjhxs);
        	sszgs = accAdd(sszgs,shsgxs);
        	sszgs = accAdd(sszgs,sgsgxs);
        	document.getElementById('sszgs').value = sszgs;
        	var sjgs;
        	var zgs = document.getElementById('zgs').value;
			var sjgzr = document.getElementById('sjgzr').value;
			sjgs = accMul(zgs,sjgzr);
			sjgs = accMul(sjgs,8);
        	var yzl1;
        	var yzl2;
        	if(sjgs != null && sjgs != "" && sjgs != "0")
        	{
        		var yzl = accDiv(sszgs,sjgs);
        		yzl1 = accMul(yzl,1000000).toFixed(2);
        		yzl2 = accMul(yzl,200000).toFixed(2);
        		document.getElementById('yzl').value = yzl1;
        		document.getElementById('yzl2').value = yzl2;
        	}
        	else
        	{
        		document.getElementById('yzl').value = "0";
        		document.getElementById('yzl2').value = "0";
        	}
        }
        
        
        function changegs()
        {
        	var sjgs;
        	var zgs = document.getElementById('zgs').value;
			var sjgzr = document.getElementById('sjgzr').value;
			sjgs = accMul(zgs,sjgzr);
			sjgs = accMul(sjgs,8);
			document.getElementById('sjgs').value = sjgs;
			var sgzqs = 0;
        	var fjhqs = document.getElementById('fjhqs').value;
			var shsgqs = document.getElementById('shsgqs').value;
			var sgsgqs = document.getElementById('sgsgqs').value;
        	sgzqs = accAdd(sgzqs,fjhqs);
        	sgzqs = accAdd(sgzqs,shsgqs);
        	sgzqs = accAdd(sgzqs,sgzqs);
        	var sszgs = 0;
			var fjhxs = document.getElementById('fjhxs').value;
			var shsgxs = document.getElementById('shsgxs').value;
			var sgsgxs = document.getElementById('sgsgxs').value;
        	sszgs = accAdd(sszgs,fjhxs);
        	sszgs = accAdd(sszgs,shsgxs);
        	sszgs = accAdd(sszgs,sgsgxs);
        	var sgl1;
        	var yzl1;
        	var sgl2;
        	var yzl2;
        	if(sjgs != null && sjgs != "" && sjgs != "0")
        	{
        		var sgl = accDiv(sgzqs,sjgs);
        		sgl1 = accMul(sgl,1000000).toFixed(2);
        		sgl2 = accMul(sgl,200000).toFixed(2);
        		document.getElementById('sgl').value = sgl1;
        		document.getElementById('sgl2').value = sgl2;
        		var yzl = accDiv(sszgs,sjgs);
        		yzl1 = accMul(yzl,1000000).toFixed(2);
        		yzl2 = accMul(yzl,200000).toFixed(2);
        		document.getElementById('yzl').value = yzl1;
        		document.getElementById('yzl2').value = yzl2;
        	}
        	else
        	{
        		document.getElementById('sgl').value = "0";
        		document.getElementById('sgl2').value = "0";
        		document.getElementById('yzl').value = "0";
        		document.getElementById('yzl2').value = "0";
        	}
        }
        
        function changetotal(obj)
        {
        	var total = 0;
        	for(var i=2;i<14;i++)
        	{
        		var a = document.getElementById('data_'+obj + '_' + i).value;
        		total = accAdd(total,a);
        	}
        	document.getElementById('data_'+obj + '_1').value = total;
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
    	
    	//乘法
		function accMul(arg1,arg2)
		{
			var m=0,s1=arg1.toString(),s2=arg2.toString();
			try{m+=s1.split(".")[1].length}catch(e){}
			try{m+=s2.split(".")[1].length}catch(e){}
			return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
		}
		
		//除法
		function accDiv(arg1, arg2) {
         var t1 = 0, t2 = 0, r1, r2;
         try { t1 = arg1.toString().split(".")[1].length } catch (e) { }
         try { t2 = arg2.toString().split(".")[1].length } catch (e) { }
         with (Math) {
             r1 = Number(arg1.toString().replace(".", ""))
             r2 = Number(arg2.toString().replace(".", ""))
             return (r1 / r2) * pow(10, t2 - t1);
         }
     }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxSgtj.id" value="${jshxSgtj.id}">
		<input type="hidden" name="jshxSgtj.createTime" value="<fmt:formatDate type="both" value="${jshxSgtj.createTime}" />">
		<input type="hidden" name="jshxSgtj.updateTime" value="${jshxSgtj.updateTime}">
		<input type="hidden" name="jshxSgtj.createUserID" value="${jshxSgtj.createUserID}">
		<input type="hidden" name="jshxSgtj.updateUserID" value="${jshxSgtj.updateUserID}">
		<input type="hidden" name="jshxSgtj.deptId" value="${jshxSgtj.deptId}">
		<input type="hidden" name="jshxSgtj.delFlag" value="${jshxSgtj.delFlag}">
		
		<input type="hidden" name="jshxSgtj.szzname" value="${jshxSgtj.szzname}">
		<input type="hidden" name="jshxSgtj.qymc" value="${jshxSgtj.qymc}">
		<input type="hidden" name="jshxSgtj.szzid" value="${jshxSgtj.szzid}">
		<input type="hidden" name="jshxSgtj.qyid" value="${jshxSgtj.qyid}">
		<input type="hidden" name="jshxSgtj.qylx" value="${jshxSgtj.qylx}">
		<input type="hidden" name="jshxSgtj.hyfl" value="${jshxSgtj.hyfl}">
		<input type="hidden" name="jshxSgtj.qygm" value="${jshxSgtj.qygm}">
		<input type="hidden" name="jshxSgtj.qyzclx" value="${jshxSgtj.qyzclx}">
		
		<input type="hidden" name="jshxSgtj.ifwhpqylx" value="${jshxSgtj.ifwhpqylx}">
		<input type="hidden" name="jshxSgtj.ifzywhqylx" value="${jshxSgtj.ifzywhqylx}">
		<input type="hidden" name="jshxSgtj.ifyhbzjyqy" value="${jshxSgtj.ifyhbzjyqy}">
		
		<input type="hidden" id="sgzqs" name="jshxSgtj.sgzqs" value="${jshxSgtj.sgzqs}">
		<input type="hidden" id="sszgs" name="jshxSgtj.sszgs" value="${jshxSgtj.sszgs}">
		<input type="hidden" name="jshxSgtj.szc" value="${jshxSgtj.szc}">
		<input type="hidden" name="jshxSgtj.szcname" value="${jshxSgtj.szcname}">
		
		<div class="submitdata" >
			<table width="100%" border="0">
				<tr>
					<th width="15%">年度</th>
					<td width="35%">
						<input class="inputText4" name="jshxSgtj.qynd" value="${jshxSgtj.qynd}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy'})" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font color="red">*</font>
					</td>
					<th width="15%">填表人</th>
					<td width="35%"><input  class="inputText4" name="jshxSgtj.tbr" value="${jshxSgtj.tbr}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">企业负责人</th>
					<td width="35%"><input  class="inputText4" name="jshxSgtj.qyfzr" value="${jshxSgtj.qyfzr}" type="text" maxlength="255"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input  class="inputText4" name="jshxSgtj.telephone" value="${jshxSgtj.telephone}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<center>
								<tr>
									<th style="text-align:center;width:10%;">损工事故起数</th>
									<th style="text-align:center;width:10%;" colspan="2">非计划停止作业</th>
									<td style="text-align:center;width:5%;"><input id="fjhqs" class="inputText4" style="width:100%;" name="jshxSgtj.fjhqs" value="${jshxSgtj.fjhqs}" type="text" maxlength="255" onkeyup="clearNoNum(event,this)" onblur="changezqs();"></td>
									<th style="text-align:center;width:10%;" colspan="2">损失工时</th>
									<td style="text-align:center;width:5%;"><input id="fjhxs" class="inputText4" style="width:100%;" name="jshxSgtj.fjhxs" value="${jshxSgtj.fjhxs}" type="text" maxlength="255" onkeyup="validateNum(event,this)" onblur="changesgl();"></td>
									<th style="text-align:center;width:10%;" colspan="2">一般伤害事故</th>
									<td style="text-align:center;width:5%;"><input id="shsgqs" class="inputText4" style="width:100%;" name="jshxSgtj.shsgqs" value="${jshxSgtj.shsgqs}" type="text" maxlength="255" onkeyup="clearNoNum(event,this)" onblur="changezqs();"></td>
									<th style="text-align:center;width:10%;" colspan="2">损失工时</th>
									<td style="text-align:center;width:5%;"><input id="shsgxs" class="inputText4" style="width:100%;" name="jshxSgtj.shsgxs" value="${jshxSgtj.shsgxs}" type="text" maxlength="255" onkeyup="validateNum(event,this)" onblur="changesgl();"></td>
									<th style="text-align:center;width:10%;">工伤事故</th>
									<td style="text-align:center;width:5%;"><input id="sgsgqs" class="inputText4" style="width:100%;" name="jshxSgtj.sgsgqs" value="${jshxSgtj.sgsgqs}" type="text" maxlength="255" onkeyup="clearNoNum(event,this)" onblur="changezqs();"></td>
									<th style="text-align:center;width:10%;">损失工时</th>
									<td style="text-align:center;width:5%;"><input id="sgsgxs" class="inputText4" style="width:100%;" name="jshxSgtj.sgsgxs" value="${jshxSgtj.sgsgxs}" type="text" maxlength="255" onkeyup="validateNum(event,this)" onblur="changesgl();"></td>
								</tr>
								<tr>
									<th style="text-align:center;width:10%;" rowspan="2">伤害事故分级</th>
									<th style="text-align:center;width:10%;" colspan="2">伤害未遂事故</th>
									<th style="text-align:center;width:10%;" colspan="2">非损工事故</th>
									<th style="text-align:center;width:10%;" colspan="2">一般损工事故</th>
									<th style="text-align:center;width:10%;" colspan="2">轻伤事故</th>
									<th style="text-align:center;width:10%;" colspan="2">重伤事故</th>
									<th style="text-align:center;width:10%;" colspan="2">死亡事故</th>
									<th style="text-align:center;width:15%;" rowspan="2" colspan="2">主要人身伤害部位（按受伤部位分类或致病器官填写）</th>
									<th style="text-align:center;width:15%;" rowspan="2" colspan="2">发生伤害事故主要作业位置</th>
								</tr>
								
								<tr>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
									<th style="text-align:center;width:5%;">起数</th>
									<th style="text-align:center;width:5%;">人数</th>
								</tr>
							</center>
							<c:forEach items="${datas}" var="item" varStatus="status">
							      <tr> 
							      	<th style="width:10%;">${item}</th>
							      	<c:forEach  items="${sgtjDatas}" var="data" varStatus="statu"> 
											<input type="hidden" name="sgtjData.id" value="${data.id}">
											<input type="hidden" name="sgtjData.sort" value="${data.sort}">
											<input type="hidden" name="sgtjData.createTime" value="<fmt:formatDate type="both" value="${data.createTime}" />">
							     			<c:if test="${statu.index ne 13 and statu.index ne 12}">
							     				<td style="width:5%;">
								     			<c:if test="${status.index eq 0 }">
								     				 <input id="data_${statu.index}_1" name="sgtjData.data_${status.index+1}" style="width:100%;"
								     				 	 type="text" value="${data.data_1}" readonly>
								     			</c:if>
								     			<c:if test="${status.index eq 1}">
								     				 <input id="data_${statu.index}_2" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_2}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 2}">
								     				 <input id="data_${statu.index}_3" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_3}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 3}">
								     				 <input id="data_${statu.index}_4" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_4}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 4}">
								     				 <input id="data_${statu.index}_5" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_5}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 5}">
								     				 <input id="data_${statu.index}_6" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_6}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 6}">
								     				 <input id="data_${statu.index}_7" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_7}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 7}">
								     				 <input id="data_${statu.index}_8" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_8}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 8}">
								     				 <input id="data_${statu.index}_9" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_9}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 9}">
								     				 <input id="data_${statu.index}_10" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_10}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 10}">
								     				 <input id="data_${statu.index}_11" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_11}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 11}">
								     				 <input id="data_${statu.index}_12" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_12}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			<c:if test="${status.index eq 12}">
								     				  <input id="data_${statu.index}_13" name="sgtjData.data_${status.index+1}" onkeyup="clearNoNum(event,this)" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_13}" onblur="changetotal('${statu.index}');">
								     			</c:if>
								     			</td>
							     			</c:if>
							     			<c:if test="${statu.index eq  13 or statu.index eq 12}">
							     				<td style="width:15%;" colspan="2">
								     			<c:if test="${status.index eq 0 }">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				 	 type="text" value="${data.data_1}">
								     			</c:if>
								     			<c:if test="${status.index eq 1}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_2}">
								     			</c:if>
								     			<c:if test="${status.index eq 2}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;"  
								     				  type="text" value="${data.data_3}">
								     			</c:if>
								     			<c:if test="${status.index eq 3}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_4}">
								     			</c:if>
								     			<c:if test="${status.index eq 4}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_5}">
								     			</c:if>
								     			<c:if test="${status.index eq 5}">
								     				<input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_6}">
								     			</c:if>
								     			<c:if test="${status.index eq 6}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_7}">
								     			</c:if>
								     			<c:if test="${status.index eq 7}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_8}">
								     			</c:if>
								     			<c:if test="${status.index eq 8}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_9}">
								     			</c:if>
								     			<c:if test="${status.index eq 9}">
								     				 <input name="sgtjData.data_${status.index+1}"class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_10}">
								     			</c:if>
								     			<c:if test="${status.index eq 10}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_11}">
								     			</c:if>
								     			<c:if test="${status.index eq 11}">
								     				 <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_12}">
								     			</c:if>
								     			<c:if test="${status.index eq 12}">
								     				  <input name="sgtjData.data_${status.index+1}" class="inputText4" style="width:100%;" 
								     				  type="text" value="${data.data_13}">
								     			</c:if>
								     			</td>
								     		</c:if>
							     	 </c:forEach> 
							      <tr> 
							</c:forEach> 
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">平均职工人数</th>
					<td width="35%"><input id="zgs" class="inputText4" name="jshxSgtj.zgs" value="${jshxSgtj.zgs}" type="text" maxlength="255" onkeyup="clearNoNum(event,this)" dataType="Require" msg="此项为必填" onblur="changegs();"><font color="red">*</font></td>
					<th width="15%">实际工作日</th>
					<td width="35%"><input id="sjgzr" class="inputText4" name="jshxSgtj.sjgzr" value="${jshxSgtj.sjgzr}" type="text" maxlength="255" onkeyup="validateNum(event,this)" dataType="Require" msg="此项为必填" onblur="changegs();"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">百万工时损工事故率</th>
					<td width="35%"><input id="sgl" name="jshxSgtj.sgl" value="${jshxSgtj.sgl}" type="text" maxlength="255" readonly></td>
					<th width="15%">百万工时损工严重率</th>
					<td width="35%"><input id="yzl"  name="jshxSgtj.yzl" value="${jshxSgtj.yzl}" type="text" maxlength="255" readonly></td>
				</tr>
				<tr>
					<th width="15%">20万工时损工事故率</th>
					<td width="35%"><input id="sgl2" name="jshxSgtj.sgl2" value="${jshxSgtj.sgl2}" type="text" maxlength="255" readonly></td>
					<th width="15%">20万工时损工严重率</th>
					<td width="35%"><input id="yzl2"  name="jshxSgtj.yzl2" value="${jshxSgtj.yzl2}" type="text" maxlength="255" readonly></td>
				</tr>
				<tr>
					<th width="15%">实际总工时</th>
					<td width="35%"><input id="sjgs" name="jshxSgtj.sjgs" value="${jshxSgtj.sjgs}" type="text" maxlength="255" readonly></td>
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
