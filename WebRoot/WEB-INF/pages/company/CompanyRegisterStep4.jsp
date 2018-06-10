<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业注册</title>
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/webResources/js/xmlhttp.js"></script>
		<%@include file="/common/jsLib.jsp"%>
		<script type="text/javascript">
		var browser="";
		var explorer = window.navigator.userAgent ;
		//ie 
		if (explorer.indexOf("MSIE") >= 0) {
		browser="ie";
		}
		//firefox 
		else if (explorer.indexOf("Firefox") >= 0) {
		browser="Firefox";
		}
		//Chrome
		else if(explorer.indexOf("Chrome") >= 0){
		browser="Chrome";
		}
		//Opera
		else if(explorer.indexOf("Opera") >= 0){
		browser="Opera";
		}
		//Safari
		else if(explorer.indexOf("Safari") >= 0){
		browser="Safari";
		}
		$(function(){
			$('body').delegate('.draw-list-paper','click',function(){
				var attachid = $(this).attr('attachid');
				window.location.href="${ctx}/jsp/attach/download.action?fileName="+attachid+"&browser="+browser;
			});

			//附加验证样式
			/* jdValidator({
				//这里是提供给使用者自己的验证方法（用于一些特别的元素）
				//返回true,验证通过， 返回false,则验证不通过
				beforeSubmit : function(){
					return true;
				}
			}); */
		});		
	</script>
		<script>
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

function showDiv(){
	var value = $("#diggingstype").children().val();
	if(value=="d001" || value=="d005"){
		$("#tffs").show();
		$("#ysfs").show();
		$("#tsfs").show();
		$("#ldxt").show();
		$("#ventilate").attr("class","jd-validator-droplist").attr("focusmsg","请选择通风方式").attr("nullmsg","请选择通风方式").attr("errormsg","请选择通风方式").attr("rightmsg","通风方式选择成功").attr("datatype","*");
		$("#transport").attr("class","jd-validator-droplist").attr("focusmsg","请选择运输方式").attr("nullmsg","请选择运输方式").attr("errormsg","请选择运输方式").attr("rightmsg","运输方式选择成功").attr("datatype","*");
		$("#raisetype").attr("class","jd-validator-droplist").attr("focusmsg","请选择提升方式").attr("nullmsg","请选择提升方式").attr("errormsg","请选择提升方式").attr("rightmsg","提升方式选择成功").attr("datatype","*");
		$("#sixsys").attr("class","jd-validator-droplist").attr("focusmsg","请输入六大系统情况").attr("nullmsg","请输入六大系统情况").attr("errormsg","请输入六大系统情况").attr("rightmsg","六大系统情况输入成功").attr("datatype","*");
	}else{
		$("#tffs").hide();
		$("#ysfs").hide();
		$("#tsfs").hide();
		$("#ldxt").hide();
	}
}

function save(){
	debugger;
	var valiFlag = true;
	
	var ifwhpqylx=$("input[name='company.ifwhpqylx']:checked").val();
	var ifzywhqylx=$("input[name='company.ifzywhqylx']:checked").val();
	var ifyhbzjyqy=$("input[name='company.ifyhbzjyqy']:checked").val();
	var iffmksjyqy=$("input[name='company.iffmksjyqy']:checked").val();
	var iffmgmqylx=$("input[name='company.iffmgmqylx']:checked").val();
	
	 if(ifwhpqylx!=1  && ifyhbzjyqy!=1 && iffmksjyqy!=1 && iffmgmqylx!=1 )
	 { valiFlag = failedMsg("jglxResult","请至少选择一项企业所属监管类型");
	 }else{ successMsg("jglxResult","企业所属监管类型选择成功");
	 }
	//校验工贸企业类型
	 if(iffmgmqylx==1)
	{
		var ches = $("#iffmgmqylxid").children().children().children().children().children().children().children();
		var ischecked = false;
		for(var i=0;i<ches.length;i++)
		{
			if(ches[i].checked)
			{
				ischecked = true;
				break;
			}
		} 
		if(!ischecked){ valiFlag = failedMsg("iffmgmqylxResult","工贸企业类型至少选择一项");
		}else{ successMsg("iffmgmqylxResult","工贸企业类型选择成功");}
	}
	//校验危化品企业
	 if(ifwhpqylx==1)
	{
		var ches = $("#ifwhpqylxid").children().children().children().children().children().children().children();
		var ischecked = false;
		for(var i=0;i<ches.length;i++)
		{
			if(ches[i].checked)
			{
				ischecked = true;
				break;
			}
		}
		if(!ischecked){ valiFlag = failedMsg("ifwhpqylxResult","危化品企业类型至少选择一项");
		}else{ successMsg("ifwhpqylxResult","危化品企业类型选择成功");}
	}
	//校验职业危害企业类型
	if(ifzywhqylx==1)
    {
		var ches = $("#ifzywhqylxid").children().children().children().children().children().children().children();
		var ischecked = false;
		for(var i=0;i<ches.length;i++)
		{
			if(ches[i].checked)
			{
				ischecked = true;
				break;
			}
		}
		if(!ischecked){ valiFlag = failedMsg("ifzywhqylxResult","职业危害企业类型至少选择一项");
		}else{ successMsg("ifzywhqylxResult","职业危害企业类型选择成功");}
	}
	//校验烟花爆竹企业类型
	/* if(ifyhbzjyqy==1)
    {
		var ches = $("#ifyhbzjyqyid").children().children().children().children().children().children().children();
		var ischecked = false;
		for(var i=0;i<ches.length;i++)
		{
			if(ches[i].checked)
			{
				ischecked = true;
				break;
			}
		}
		if(!ischecked){ valiFlag = failedMsg("ifyhbzjyqyResult","烟花爆竹企业类型至少选择一项");
		}else{ successMsg("ifyhbzjyqyResult","烟花爆竹企业类型选择成功");}
	} */
	 //校验非煤矿山企业类型
	 if(iffmksjyqy==1)
	    {	//校验矿山类型
		 	var diggingstypeVal = $("#diggingstype").children().val();
		 	if(""==diggingstypeVal){
		 		valiFlag = failedMsg("diggingstypeResult","请选择矿山类型");
		 	}else{
				successMsg("diggingstypeResult","矿山类型选择成功");
		 	}
			//校验金属属性
		 	var metalVal = $("#metal").val();
		 	if(""==metalVal){
		 		valiFlag = failedMsg("metalResult","请选择金属属性");
		 	}else{
				successMsg("metalResult","金属属性选择成功");
		 	}
			
			if($("#diggingstype").children().val() == 'd001' || $("#diggingstype").children().val() == 'd005'){
				//校验通风方式
			 	var ventilateVal = $("#ventilate").val();
			 	if(""==ventilateVal){
			 		valiFlag = failedMsg("ventilateResult","请选择通风方式");
			 	}else{
					successMsg("ventilateResult","通风方式选择成功");
			 	}
			 	//校验运输方式
			 	var transportVal = $("#transport").val();
			 	if(""==transportVal){
			 		valiFlag = failedMsg("transportResult","请选择运输方式");
			 	}else{
					successMsg("transportResult","运输方式选择成功");
			 	}
			 	//校验提升方式
			 	var raisetypeVal = $("#raisetype").val();
			 	if(""==raisetypeVal){
			 		valiFlag = failedMsg("raisetypeResult","请选择提升方式");
			 	}else{
					successMsg("raisetypeResult","提升方式选择成功");
			 	}
			 	//校验六大系统情况
			 	var sixsysVal = $("#sixsys").val();
			 	if(""==sixsysVal){
			 		valiFlag = failedMsg("sixsysResult","请输入六大系统情况");
			 	}else{
					successMsg("sixsysResult","六大系统情况输入成功");
			 	}
			}else{
				delAllClass("ventilateResult");
				delAllClass("transportResult");
				delAllClass("raisetypeResult");
				delAllClass("sixsysResult");
			}
		}else{
			delAllClass("diggingstypeResult");
			delAllClass("metalResult");
			delAllClass("ventilateResult");
			delAllClass("transportResult");
			delAllClass("raisetypeResult");
			delAllClass("sixsysResult");
		}
	//校验企业类型
	var qylxVal = $("#qylx").val();
 	if(""==qylxVal){
 		valiFlag = failedMsg("qylxResult","请选择企业类型");
 	}else{
		successMsg("qylxResult","企业类型选择成功");
 	}
	//校验行业分类
	var hyflVal = $("#hyfl").val();
 	if(""==hyflVal){
 		valiFlag = failedMsg("hyflResult","请选择企业类型");
 	}else{
		successMsg("hyflResult","企业类型选择成功");
 	}
	//校验直属等级
	if($("input[name='company.ifzsqy']:checked").val()==1)
    { 
		if(""==$("#zsqytype").val()){
			valiFlag = failedMsg("zsqytypeResult","请选择直属等级");
		}else{ 
			successMsg("zsqytypeResult","直属等级选择成功");
		}
	}
	//校验是否设立安全管理机构
	if($("input[name='company.sfaqjg']:checked").val()==1)
	{ 
		if(""==$("#aqglr").val()){
			valiFlag = failedMsg("aqglrResult","请输入管理员人数");
		}else{successMsg("aqglrResult","管理员人数输入成功");}
	}
	//校验是否设立职业卫生管理机构
	if($("input[name='company.sfzywsjg']:checked").val()==1)
	{ 
		if(""==$("#zywsglry").val()){
			valiFlag = failedMsg("zywsglryResult","请输入管理员人数");
		}else{successMsg("zywsglryResult","管理员人数输入成功");}
	}
	if(valiFlag){
		document.myform1.action="companyRegister.action";
		document.myform1.submit();
	}
}

function successMsg(resultId,msg){
	$("#"+resultId).removeClass("jd-validator-msg");
	$("#"+resultId).removeClass("jd-validator-msg errinput");
	$("#"+resultId).removeClass("jd-validator-msg focus");
	$("#"+resultId).addClass("jd-validator-msg rightinput");	
	document.getElementById(resultId).innerHTML = msg;
	//layer.msg(msg, {icon: 1});
}
function failedMsg(resultId,msg){
	$("#"+resultId).removeClass("jd-validator-msg");
	$("#"+resultId).removeClass("jd-validator-msg rightinput");
	$("#"+resultId).removeClass("jd-validator-msg focus");
	$("#"+resultId).addClass("jd-validator-msg errinput");
	document.getElementById(resultId).innerHTML = msg;
	layer.msg(msg, {icon: 0});
    return false;
}
function delAllClass(resultId){
	$("#"+resultId).removeClass("jd-validator-msg");
	$("#"+resultId).removeClass("jd-validator-msg errinput");
	$("#"+resultId).removeClass("jd-validator-msg focus");
	$("#"+resultId).removeClass("jd-validator-msg rightinput");	
	document.getElementById(resultId).innerHTML = "";
}

$(document).ready(function(){
	
	if($("input[name='company.iffmgmqylx']:checked").val()==1)
	{
		$("input[name='company.iffmgmqylx'][value='1']").attr("checked",true);
	   	$('#iffmgmqylxid').css("display",""); 
	}else
	{
		$("input[name='company.iffmgmqylx'][value='0']").attr("checked",true);
	    $('#iffmgmqylxid').css("display","none");  
	}
	
	$("input:radio[name='company.iffmgmqylx']").change(function(){
		if($("input[name='company.iffmgmqylx']:checked").val()==1)
		{
			$('#iffmgmqylxid').css("display",""); 
		}else{
			$('#iffmgmqylxid').css("display","none"); 
		}
	});
	
	
	if($("input[name='company.ifwhpqylx']:checked")==1)
	{
		$("input[name='company.ifwhpqylx'][value='1']").attr("checked",true);
	    $('#ifwhpqylxid').css("display",""); 
	}else
	{
		$("input[name='company.ifwhpqylx'][value='0']").attr("checked",true);
	    $('#ifwhpqylxid').css("display","none");  
	}
	
	$("input:radio[name='company.ifwhpqylx']").change(function(){
		if($("input[name='company.ifwhpqylx']:checked").val()==1)
		{
			$('#ifwhpqylxid').css("display",""); 
		}else{
			$('#ifwhpqylxid').css("display","none"); 
		}
	});
	
	if($("input[name='company.ifzywhqylx']:checked").val()==1)
	{
		$("input[name='company.ifzywhqylx'][value='1']").attr("checked",true);
	    $('#ifzywhqylxid').css("display",""); 
	}else
	{
		$("input[name='company.ifzywhqylx'][value='0']").attr("checked",true);
	    $('#ifzywhqylxid').css("display","none");  
	}
	
	$("input:radio[name='company.ifzywhqylx']").change(function(){
		if($("input[name='company.ifzywhqylx']:checked").val()==1)
		{
			$('#ifzywhqylxid').css("display",""); 
		}else{
			$('#ifzywhqylxid').css("display","none"); 
		}
	});
	
	if($("input[name='company.ifyhbzjyqy']:checked").val()==1)
	{
		$("input[name='company.ifyhbzjyqy'][value='1']").attr("checked",true);
	    $('#ifyhbzjyqyid').css("display",""); 
	}else
	{
		$("input[name='company.ifyhbzjyqy'][value='0']").attr("checked",true);
	    $('#ifyhbzjyqyid').css("display","none");  
	}
	
	$("input:radio[name='company.ifyhbzjyqy']").change(function(){
		if($("input[name='company.ifyhbzjyqy']:checked").val()==1)
		{
			$('#ifyhbzjyqyid').css("display",""); 
		}else{
			$('#ifyhbzjyqyid').css("display","none"); 
		}
	});
	
	if($("input[name='company.iffmksjyqy']:checked").val()==1)
	{
		$("input[name='company.iffmksjyqy'][value='1']").attr("checked",true);
	    $('.iffmksjyqyid').css("display",""); 
	}else
	{
		$("input[name='company.iffmksjyqy'][value='0']").attr("checked",true);
	    $('.iffmksjyqyid').css("display","none");  
	}
	/*
	if($("input[name='company.sfaqjg']:checked").val()==1)
	{
		$("input[name='company.sfaqjg'][value='1']").attr("checked",true);
	    $('#sfaqjgid').css("display",""); 
	}else
	{
		$("input[name='company.sfaqjg'][value='0']").attr("checked",true);
	    $('#sfaqjgid').css("display","none");  
	}
	$("input:radio[name='company.sfaqjg']").change(function(){
		if($("input[name='company.sfaqjg']:checked").val()==1)
		{
			$("input[name='company.sfaqjg'][value='1']").attr("checked",true);
			$('#sfaqjgid').css("display",""); 
		}else{
			$("input[name='company.sfaqjg'][value='0']").attr("checked",true);
			$('#sfaqjgid').css("display","none"); 
		}
	});
	
	if($("input[name='company.sfzywsjg']:checked").val()==1)
	{
	    $('#sfzywsjgid').css("display",""); 
	}else
	{
	    $('#sfzywsjgid').css("display","none");  
	}
	$("input:radio[name='company.sfzywsjg']").change(function(){
		if($("input[name='company.sfzywsjg']:checked").val()==1)
		{
			$("input[name='company.sfzywsjg'][value='1']").attr("checked",true);
			$('#sfzywsjgid').css("display",""); 
		}else{
			$("input[name='company.sfzywsjg'][value='0']").attr("checked",true);
			$('#sfzywsjgid').css("display","none"); 
		}
	});
	*/
	if($("input[name='company.ifzsqy']:checked").val()==1)
	{
		$("input[name='company.ifzsqy'][value='1']").attr("checked",true);
	    $('#ifzsqyid').css("display",""); 
	}else
	{
		$("input[name='company.ifzsqy'][value='0']").attr("checked",true);
	    $('#ifzsqyid').css("display","none");  
	}
	
	$("input:radio[name='company.ifzsqy']").change(function(){
		if($("input[name='company.ifzsqy']:checked").val()==1)
		{
			$("input[name='company.ifzsqy'][value='1']").attr("checked",true);
			$('#ifzsqyid').css("display","");
			$("#zsqytype").attr("class","jd-validator-droplist").attr("focusmsg","请选择直属等级").attr("nullmsg","请选择直属等级").attr("errormsg","请选择直属等级").attr("rightmsg","直属等级选择成功").attr("datatype","*");
		}else{
			$("input[name='company.ifzsqy'][value='0']").attr("checked",true);
			$('#ifzsqyid').css("display","none"); 
		}
	});
	
	$("input:radio[name='company.iffmksjyqy']").change(function(){
		if($("input[name='company.iffmksjyqy']:checked").val()==1)
		{
			$('.iffmksjyqyid').css("display","");
			$("#diggingstype").children().eq(0).attr("class","jd-validator-droplist").attr("focusmsg","请选择矿山类型").attr("nullmsg","请选择矿山类型").attr("errormsg","请选择矿山类型").attr("rightmsg","矿山类型选择成功").attr("datatype","*");
			$("#metal").attr("class","jd-validator-droplist").attr("focusmsg","请选择金属属性").attr("nullmsg","请选择金属属性").attr("errormsg","请选择金属属性").attr("rightmsg","金属属性选择成功").attr("datatype","*");
			showDiv();
		}else{
			$('.iffmksjyqyid').css("display","none"); 
		}
	});
})

function pre(){
		document.myform1.action="companyRegisterNextUI.action?flag=3";
		document.myform1.submit();
}
$(document).ready(function(){
	$("#aqglr").attr("class","jd-validator-droplist").attr("focusmsg","请输入管理员人数").attr("nullmsg","请输入管理员人数").attr("errormsg","请输入管理员人数").attr("rightmsg","管理员人数输入成功").attr("datatype","*");
	$("#zywsglry").attr("class","jd-validator-droplist").attr("focusmsg","请输入管理员人数").attr("nullmsg","请输入管理员人数").attr("errormsg","请输入管理员人数").attr("rightmsg","管理员人数输入成功").attr("datatype","*");
	var message = '${message}';
	if(message=="该用户已注册"){
		layer.msg('该用户已注册,请修改用户名!', {icon: 0});
	}
	//getHyflTwoLevel("${company.hyflOneLevel}");
	//getHyflThreeLevel("${company.hyflTwoLevel}");
	//getHyflFourLevel("${company.hyflThreeLevel}");
})
 function getHyflTwoLevel(obj){
	  	$.ajax({
			type:"POST",
			url:"${ctx}/jsp/company/hyflTwoLevel.action?code="+obj,
			dateType:"json",
			success:function(json){
				json = eval('(' + json + ')');
				var selectContainer = $('#hyflTwoLevel'); 
				selectContainer.empty();
				var option = $('<option></option>').text("行业二级分类").val(""); 
				selectContainer.append(option); 
 				var temphyflTwoLevel = "${company.hyflTwoLevel}";
 				for(var i=0; i<json.length; i++){
 					var option = "";
			    	if(temphyflTwoLevel == json[i].code){
						option = $('<option></option>').text(json[i].name).val(json[i].code).attr("selected",true); 
			    	}else{
			    		option = $('<option></option>').text(json[i].name).val(json[i].code);
			    	}
					selectContainer.append(option); 
		 		}
		 		$('#hyflThreeLevel').empty();
		 		$('#hyflThreeLevel').append('<option>行业三级分类</option>');
		 		$('#hyflFourLevel').empty();
		 		$('#hyflFourLevel').append('<option>行业四级分类</option>');
		 		 $("#hyflTwoLevel").change();
			}
		});
  	}
  	
	function getHyflThreeLevel(obj){
	  	$.ajax({
			type:"POST",
			url:"${ctx}/jsp/company/hyflThreeLevel.action?code="+obj,
			dateType:"json",
			success:function(json){
				json = eval('(' + json + ')');
				var selectContainer = $('#hyflThreeLevel'); 
				selectContainer.empty();
				var option = $('<option></option>').text("行业三级分类").val(""); 
				selectContainer.append(option); 
 				var temphyflThreeLevel = "${company.hyflThreeLevel}";
 				for(var i=0; i<json.length; i++){
 					var option = "";
			    	if(temphyflThreeLevel == json[i].code){
						option = $('<option></option>').text(json[i].name).val(json[i].code).attr("selected",true); 
			    	}else{
			    		option = $('<option></option>').text(json[i].name).val(json[i].code);
			    	}
					selectContainer.append(option);
			 	}
			 	$('#hyflFourLevel').empty();
		 		$('#hyflFourLevel').append('<option>行业四级分类</option>');
		    	 $("#hyflThreeLevel").change();
			}
		});
  	}
  	
	function getHyflFourLevel(obj){
	  	$.ajax({
			type:"POST",
			url:"${ctx}/jsp/company/hyflFourLevel.action?code="+obj,
			dateType:"json",
			success:function(json){
				json = eval('(' + json + ')');
				var selectContainer = $('#hyflFourLevel'); 
				selectContainer.empty();
				var option = $('<option></option>').text("行业四级分类").val(""); 
				selectContainer.append(option); 
	 			var temphyflFourLevel = "${company.hyflFourLevel}";
 				for(var i=0; i<json.length; i++){
 					var option = "";
			    	if(temphyflFourLevel == json[i].code){
						option = $('<option></option>').text(json[i].name).val(json[i].code).attr("selected",true); 
			    	}else{
			    		option = $('<option></option>').text(json[i].name).val(json[i].code);
			    	}
					selectContainer.append(option); 
			 	}
			}
		});
	}
	function onlyNum()
	{ 
		var keys=event.keyCode;
		if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||(keys==8)||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)||(keys==189)||(keys==109)))
			event.returnValue=false;
	}
</script>
	</head>
	<body>
		<div class="ajtop">
			<div class="ajtopav">
				<div class="ajlogo">
					企业基本信息注册
				</div>
				<div class="user"></div>
			</div>
		</div>
		<div class="content">
			<div class="wrap">
				<div class="steps">
					<div class="steps">
						<span>1.登录信息</span><b></b><span>2.基本信息</span><b></b><span>3.经营信息</span><b
							class="i1"></b><span class="current">4.分类信息</span><b class="i2"></b><span>5.注册完成</span>
					</div>
				</div>
				<div class="cell">
					<form id="myform1" name="myform1" method="post"
						class="jd-vali-form">
						<s:hidden name="company.loginname" />
						<s:hidden name="company.loginword" />
						<s:hidden name="company.lxr" />
						<s:hidden name="company.mobile" />
						<s:hidden name="company.lxfs" />
						<s:hidden name="company.fddbr" />
						<s:hidden name="company.fddbrlxhm" />
						<s:hidden name="company.gddh" />
						<s:hidden name="company.qyyx" />
						<s:hidden name="company.cz" />
						<s:hidden name="company.yzbm" />
						<s:hidden name="company.longitude" />
						<s:hidden name="company.latitude" />
						<s:hidden name="company.szc" />
						<s:hidden name="company.szcname" />
						<s:hidden name="company.companyname" />
						<s:hidden name="company.county" />
						<s:hidden name="company.dwdz1" />
						<s:hidden name="company.dwdz2" />
						<s:hidden name="company.gszch" />
						<s:hidden name="company.zzjgdm" />
						<s:hidden name="company.qyclsj" />
						<s:hidden name="company.ifurl" />
						<s:hidden name="company.url" />
						<s:hidden name="company.qygm" />
						<s:hidden name="company.qyzclx" />
						<s:hidden name="company.zczj" />
						<s:hidden name="company.snxssr" />
						<s:hidden name="company.snsjss" />
						<s:hidden name="company.sngdzc" />
						<s:hidden name="company.snwqtr" />
						<s:hidden name="company.snaqscf" />
						<s:hidden name="company.zdmj" />
						<s:hidden name="company.jzmj" />
						<s:hidden name="company.cyry" />
						<s:hidden name="company.sfyygss" />
						<s:hidden name="company.aqbzdbjb" />
						<s:hidden name="company.jyfw" />
						<s:hidden name="company.aqscxkzh" />
						<s:hidden name="company.tyshxydm"/>
						<s:hidden name="reloginword" />
						<div class="bg_01">
							<div class="jd-vali-linediv">
								<b>企业所属监管类型</b>
								<div id="jglxResult" class="jd-validator-msg"
									style="margin-left: 250px;">
									&nbsp;
								</div>
							</div>

							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否工贸企业
									</td>
									<td colspan="3">
										<input name="company.iffmgmqylx" type="radio" value="0" />
										否
										<input name="company.iffmgmqylx" type="radio" value="1" />
										是
									</td>
								</tr>
								<tr id="iffmgmqylxid">
									<td width="240"></td>
									<td>
										<table width="100%">
											<tr>
												<td colspan="4">
													<p style="float: left;">
														<cus:hxcheckbox property="company.companyType"
															codeName="非煤工贸企业" value="${company.companyType}"
															dataType="Require" msg="此项为必选" focusmsg="请输入经营范围！"
															nullmsg="经营范围必填！" errormsg='经营范围必填！' rightmsg='经营范围输入正确！'
															datatype="*" />
													</p>
													<div id="iffmgmqylxResult" class="jd-validator-msg"
														style="margin-left: 5px; width: 150px;"></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否危化品企业类型
									</td>
									<td>
										<input name="company.ifwhpqylx" type="radio" value="0" />
										否
										<input name="company.ifwhpqylx" type="radio" value="1" />
										是
									</td>
								</tr>
								<tr id="ifwhpqylxid">
									<td width="240"></td>
									<td>
										<table width="100%">
											<tr>
												<td colspan="4">
													<p style="float: left;">
														<cus:hxcheckbox property="company.companyType"
															codeName="危化品企业类型" value="${company.companyType}"
															dataType="Require" msg="此项为必选" />
													</p>
													<div id="ifwhpqylxResult" class="jd-validator-msg"
														style="margin-left: 5px; width: 164px;"></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否烟花爆竹经营企业
									</td>
									<td>
										<input name="company.ifyhbzjyqy" type="radio" value="0" />
										否
										<input name="company.ifyhbzjyqy" type="radio" value="1" />
										是
									</td>
								</tr>
								<%-- <tr id="ifyhbzjyqyid">
									<td width="240"></td>
									<td>
										<table width="100%">
											<tr>
												<td colspan="4">
													<p style="float: left;">
														<cus:hxcheckbox property="company.companyType"
															codeName="烟花爆竹企业" value="${company.companyType}"
															dataType="Require" msg="此项为必选" />
													</p>
													<div id="ifyhbzjyqyResult" class="jd-validator-msg"
														style="margin-left: 5px; width: 174px;"></div>
												</td>
											</tr>
										</table>
									</td>
								</tr> --%>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否非煤矿山企业
									</td>
									<td>
										<input name="company.iffmksjyqy" type="radio" value="0" />
										否
										<input name="company.iffmksjyqy" type="radio" value="1" />
										是
									</td>
								</tr>
								<tr class="iffmksjyqyid">
									<td width="240" align="right">
										矿山类型
										<span class="red">*</span>
									</td>
									<td id="diggingstype">
										<cus:SelectOneTag onchange="showDiv();"
											style="width:188px; margin-right:8px; float:left;"
											property="company.companyType" defaultText='请选择'
											codeName="矿山类型" value="${company.companyType}" />
										<div id="diggingstypeResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr class="iffmksjyqyid">
									<td width="240" align="right">
										金属属性
										<span class="red">*</span>
									</td>
									<td>
										<cus:SelectOneTag
											style="width:188px; margin-right:8px; float:left;"
											property="company.metal" defaultText='请选择' codeName="金属属性"
											value="${company.metal}" />
										<div id="metalResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr class="iffmksjyqyid" id="tffs">
									<td width="240" align="right">
										通风方式
										<span class="red">*</span>
									</td>
									<td>
										<cus:SelectOneTag
											style="width:188px; margin-right:8px; float:left;"
											property="company.ventilate" defaultText='请选择'
											codeName="通风方式" value="${company.ventilate}" />
										<div id="ventilateResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr class="iffmksjyqyid" id="ysfs">
									<td width="240" align="right">
										运输方式
										<span class="red">*</span>
									</td>
									<td>
										<cus:SelectOneTag
											style="width:188px; margin-right:8px; float:left;"
											property="company.transport" defaultText='请选择'
											codeName="运输方式" value="${company.transport}" />
										<div id="transportResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr class="iffmksjyqyid" id="tsfs">
									<td width="240" align="right">
										提升方式
										<span class="red">*</span>
									</td>
									<td>
										<cus:SelectOneTag
											style="width:188px; margin-right:8px; float:left;"
											property="company.raisetype" defaultText='请选择'
											codeName="提升方式" value="${company.raisetype}" />
										<div id="raisetypeResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr class="iffmksjyqyid" id="ldxt">
									<td width="240" align="right">
										六大系统情况
										<span class="red">*</span>
									</td>
									<td>
										<textarea id="sixsys" dataType="Require" msg="此项为必填"
											name="company.sixsys" rows="5"
											style="width: 50%; float: left;">${company.sixsys}</textarea>
										<div id="sixsysResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 120px;""></div>
									</td>
								</tr>
							</table>
						</div>
						<div class="bg_02">
							<b>其它类型信息</b>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否职业危害企业类型
									</td>
									<td>
										<input name="company.ifzywhqylx" type="radio" value="0" />
										否
										<input name="company.ifzywhqylx" type="radio" value="1" />
										是
									</td>
								</tr>
								<tr id="ifzywhqylxid">
									<td width="240"></td>
									<td>
										<table width="100%">
											<tr>
												<td colspan="4" style="float: left;">
													<p style="float: left;">
														<cus:hxcheckbox property="company.companyType"
															codeName="职业危害企业类型" value="${company.companyType}"
															dataType="Require" msg="此项为必选" />
													</p>
													<div id="ifzywhqylxResult" class="jd-validator-msg"
														style="margin-left: 5px; width: 174px;"></div>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>企业类型
									</td>
									<td>
										<cus:SelectOneTag
											style="width:188px; margin-right:8px; float:left;"
											property="company.qylx" defaultText='请选择' codeName="企业类型"
											value="${company.qylx}" class="jd-validator-droplist"
											focusmsg="请选择企业类型！" nullmsg="请选择企业类型！" errormsg='请选择企业类型！'
											rightmsg='企业类型输入正确！' datatype="*" />
										<div id="qylxResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>行业分类
									</td>
									<td>
										<cus:SelectOneTag property="company.hyflOneLevel" defaultText='行业一级分类' codeName="经济行业分类" 
											value="${company.hyflOneLevel}"  style="width: 130px;" 
											onchange="getHyflTwoLevel(this.value);"/>
										<select id="hyflTwoLevel" name="company.hyflTwoLevel"  onchange="getHyflThreeLevel(this.value);" 
											style="width: 145px;" >
											<option>请先选择行业一级分类</option>
										</select>
										<select id="hyflThreeLevel" name="company.hyflThreeLevel"  onchange="getHyflFourLevel(this.value);" 
											style="width: 145px;"  >
											<option>请先选择行业二级分类</option>
										</select>
										<select id="hyflFourLevel" name="company.hyflFourLevel" style="width: 145px;">
											<option>请先选择行业三级分类</option>
										</select>
										<%-- <cus:SelectOneTag
											style="width:188px; margin-right:8px; float:left;"
											property="company.hyfl" defaultText='请选择' codeName="企业行业分类"
											value="${company.hyfl}" class="jd-validator-droplist"
											focusmsg="请选择行业分类！" nullmsg="请选择行业分类！" errormsg='请选择行业分类！'
											rightmsg='行业分类输入正确！' datatype="*" /> --%>
										<div id="hyflResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否省市直属企业
									</td>
									<td>
										<input name="company.ifzsqy" type="radio" value="0" />
										否
										<input name="company.ifzsqy" type="radio" value="1" />
										是
									</td>
								</tr>
								<tr id="ifzsqyid">
									<td width="240" align="right">
										<span class="red">*</span>直属等级
									</td>
									<td>
										<cus:SelectOneTag style="width:150px; float:left;"
											property="company.zsqytype" defaultText='请选择' codeName="直属等级"
											value="${company.zsqytype}" />
										<div id="zsqytypeResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										企业特征
									</td>
									<td>
										<cus:SelectOneTag style="width:150px; float:left;"
											property="company.feature" defaultText='请选择' codeName="企业特征"
											value="${company.feature}" />
										<div id="zsqytypeResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 100px;"></div>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否设立安全管理机构
									</td>
									<td>
										<cus:hxradio property="company.sfaqjg" codeName="是或否"
											value="0" dataType="Require" msg="此项为必选" />
									</td>
								</tr>
								<tr id="sfaqjgid">
									<td width="240" align="right">
										<span class="red">*</span>安全管理员人数
									</td>
									<td>
										<input id="aqglr" name="company.aqglr" class="form_text"
											style="width: 338px; float: left;" onKeyDown="onlyNum()"
											onKeyUp="validate(event,this)" value="${company.aqglr}"
											type="text" checkregexp="^\d*$" maxlength="255">
										<p style="float: left;">
											（人）
										</p>
										<div id="aqglrResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 120px;"></div>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否设立职业卫生管理机构
									</td>
									<td>
										<cus:hxradio property="company.sfzywsjg" codeName="是或否"
											value="0" dataType="Require" msg="此项为必选" />
									</td>
								</tr>
								<tr id="sfzywsjgid">
									<td width="240" align="right">
										<span class="red">*</span>安全管理员人数
									</td>
									<td>
										<input id="zywsglry" name="company.zywsglry" class="form_text"
											style="width: 338px; float: left;" onKeyDown="onlyNum()"
											onKeyUp="validate(event,this)" value="${company.zywsglry}"
											checkregexp="^\d*$" maxlength="255">
										<p style="float: left;">
											（人）
										</p>
										<div id="zywsglryResult" class="jd-validator-msg"
											style="margin-left: 5px; width: 120px;"></div>
									</td>
								</tr>
								<tr>
									<td width="240" align="right">
										<span class="red">*</span>是否专职或兼职职业卫生管理员
									</td>
									<td>
										<cus:hxradio property="company.sfqzwsgly" codeName="是或否"
											value="0" dataType="Require" msg="此项为必选" />
									</td>
								</tr>
								<tr>
									<td width="240" align="right"></td>
									<td>
										<div class="login_subfield_btnarea" style="float: left;">
											<a href="javaScript:void(0)" onclick="pre();"
												class="btn_next">上一步</a>
										</div>
										<div class="login_subfield_btnarea"
											style="float: left; margin-left: 30px;">
											<a href="javaScript:void(0)" onclick="save();"
												class="btn_next">注册</a>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="footer">
			<div class="footer_l"></div>
			<div class="footer_r">
				<span>版权所有&nbsp;&nbsp;南京拓构软件有限公司</span><span>COPYRIGHTS
					2014</span>
			</div>
		</div>
	</body>
</html>