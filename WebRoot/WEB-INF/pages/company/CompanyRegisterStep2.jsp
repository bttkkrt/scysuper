<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业注册</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/xmlhttp.js"></script>
<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
<%@include file="/common/jsLib.jsp"%>
<style type="">
	.secTd{width:100%}
	input{width:90%}
</style>
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

			/* //附加验证样式
			jdValidator({
				//这里是提供给使用者自己的验证方法（用于一些特别的元素）
				//返回true,验证通过， 返回false,则验证不通过
				beforeSubmit : function(){
					return true;
				}
			}); */
		});		
	</script>
<script> 
var map;
var overlays = [];
$(function(){
	// 百度地图API功能
	/* map = new BMap.Map("allmap");
	map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	var longitude ="${company.longitude}";
	var latitude = "${company.latitude}";
	if(longitude != null && longitude != "")
	{
		var point = new BMap.Point(longitude, latitude);
		map.centerAndZoom(point, 16);
		var marker = new BMap.Marker(point);  // 创建标注
		map.addOverlay(marker);              // 将标注添加到地图中
		overlays.push(marker);
	}
	else
	{
		load("抚顺市");
	}
	
	//实例化鼠标绘制工具
	var drawingManager = new BMapLib.DrawingManager(map, {
		isOpen: true, //是否开启绘制模式
		enableDrawingTool: true, //是否显示工具栏
		drawingToolOptions: {
			anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
			offset: new BMap.Size(5, 5), //偏离值
			scale: 0.8 ,//工具栏缩放比例
			drawingModes:[
				BMAP_DRAWING_MARKER
				]
		}
	});
	//添加鼠标绘制工具监听事件，用于获取绘制结果
	drawingManager.addEventListener('overlaycomplete', overlaycomplete); */
	
	/* $(".filetype2").change(function(){
		var val = $("input[name='company.ifurl']:checked").val(); //获得选中的radio的值             
		if(val=='1'){    
			$('#ifurl').val(1);  
				$('#ifurlid').css("display","block"); 
		}else{   
			$('#ifurl').val(0);   
			$('#ifurlid').css("display","none");             
		}                    
	}); */
});

/* //回调获得覆盖物信息
var overlaycomplete = function(e){
		clearAll();
	overlays.push(e.overlay);
		var longitude = e.overlay.getPosition().lng;
	var latitude = e.overlay.getPosition().lat;
	document.getElementById('longitude').value = longitude;
	document.getElementById('latitude').value = latitude;
}; */

/* function clearAll()
{
	for(var i = 0; i < overlays.length; i++){
		 	map.removeOverlay(overlays[i]);
	}
} */

function myFun(result){
	var cityName = result.name;
map.centerAndZoom(cityName,12);
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
					var option = $('<option></option>').text("请选择").val(""); 
					selectContainer.append(option); 
	  				var tempszc = "${company.szc}";
	  				for(var i=0; i<json.length; i++){
	  					var option = "";
				    	if(tempszc == json[i].id){
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
	function querySzz(obj)
    { 
    	$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county="+obj,
				success:function(json){
					if(json==""){
						return false;
					}
					json = eval('(' + json + ')');
					var selectContainer = $('#szz'); 
					selectContainer.empty();
					var option = $('<option></option>').text("请选择").val(""); 
					selectContainer.append(option); 
	  				for(var i=0; i<json.length; i++){
	  					var option = "";
	  					var tempdwdz1 = "${company.dwdz1}";
				    	if(tempdwdz1 == json[i].id){
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
function search_place(obj)
{
	document.getElementById('longitude').value = "";
	document.getElementById('latitude').value = "";
	clearAll();
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(obj, function(point){
			if (point) {
				var marker = new BMap.Marker(point);
			overlays.push(marker);
			map.centerAndZoom(point, 16);
			map.addOverlay(marker);
			var longitude = marker.getPosition().lng;
			var latitude = marker.getPosition().lat;
			document.getElementById('longitude').value = longitude;
			document.getElementById('latitude').value = latitude;
			}
	}, "抚顺市");
}

function load(obj)
{
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
	// 将地址解析结果显示在地图上,并调整地图视野
	myGeo.getPoint(obj, function(point){
			if (point) {
			map.centerAndZoom(point, 16);
			}	
			else
			{
				var myCity = new BMap.LocalCity();
			myCity.get(myFun);
			}
	}, "抚顺市");
}

function checkUsername1(){
	var gszch = document.getElementById("gszch");
	if(gszch.value=='')
	{
		$("#checkResult1").removeClass("jd-validator-msg");
		$("#checkResult1").removeClass("jd-validator-msg rightinput");
		$("#checkResult1").removeClass("jd-validator-msg focus");
		$("#checkResult1").addClass("jd-validator-msg errinput");	
		document.getElementById("checkResult1").innerHTML = "请先输入工商注册号";
		return false;
	}
	
	$("#checkResult1").removeClass("jd-validator-msg");
	$("#checkResult1").removeClass("jd-validator-msg rightinput");
	$("#checkResult1").removeClass("jd-validator-msg errinput");
	$("#checkResult1").addClass("jd-validator-msg focus");	
	document.getElementById("checkResult1").innerHTML = "正在检测中...";
	$.ajax({
		url : "isCompanyExsit.action?company.gszch="+ gszch.value,
	    type: 'post',
	    dataType: 'json',
	    async : false,
	    data:{ 
	    },
	    error: function(){
	    	$("#checkResult1").removeClass("jd-validator-msg");
			$("#checkResult1").removeClass("jd-validator-msg rightinput");
			$("#checkResult1").removeClass("jd-validator-msg focus");
			$("#checkResult1").addClass("jd-validator-msg errinput");	
	    	document.getElementById("checkResult1").innerHTML = "检测出错";
	    },
	    success: function(data){
	    	if(data.result == 'true'){
	    		$("#checkResult1").removeClass("jd-validator-msg");
				$("#checkResult1").removeClass("jd-validator-msg rightinput");
				$("#checkResult1").removeClass("jd-validator-msg focus");
				$("#checkResult1").addClass("jd-validator-msg errinput");	
	        	document.getElementById("checkResult1").innerHTML = "该工商注册号已经存在";
	        }else{
	        	$("#checkResult1").removeClass("jd-validator-msg");
				$("#checkResult1").removeClass("jd-validator-msg errinput");
				$("#checkResult1").removeClass("jd-validator-msg focus");
				$("#checkResult1").addClass("jd-validator-msg rightinput");	
	            document.getElementById("checkResult1").innerHTML = "该工商注册号可以使用";
	        }
	   }
	});
}

function checkMap()
{
	var longitude = document.getElementById('longitude').value;
	var latitude =  document.getElementById('latitude').value;
	if(longitude == null || longitude == "" || latitude == null || latitude == "")
	{
		document.getElementById("dwResult").innerHTML = "测试!";
		$("#dwResult").removeClass("jd-validator-msg");
		$("#dwResult").removeClass("jd-validator-msg rightinput");
		$("#dwResult").removeClass("jd-validator-msg focus");
		$("#dwResult").addClass("jd-validator-msg errinput");	
		document.getElementById("dwResult").innerHTML = "请在地图上标注企业位置!";
	}
	//checkUsername1();
	//if(document.getElementById("checkResult1").innerHTML == '该工商注册号已经存在')
	//{
	//	return false;
	//}
	else
	{
		$("#dwResult").removeClass("jd-validator-msg");
		$("#dwResult").removeClass("jd-validator-msg errinput");
		$("#dwResult").removeClass("jd-validator-msg focus");
		$("#dwResult").addClass("jd-validator-msg rightinput");	
		document.getElementById("dwResult").innerHTML = "在地图上已成功定位!";
	}
}

function next(){
	//checkMap();
	/* if(document.getElementById("dwResult").innerHTML == '请在地图上标注企业位置!')
	{
		return false;
	} */
	
	/* var radioCheck = document.getElementById('file21');
	if(radioCheck.checked)
	{
		var url = document.getElementById('url');
		if(url.value == "")
		{
			$("#urlResult").removeClass("jd-validator-msg");
			$("#urlResult").removeClass("jd-validator-msg rightinput");
			$("#urlResult").removeClass("jd-validator-msg focus");
			$("#urlResult").addClass("jd-validator-msg errinput");
			document.getElementById("urlResult").innerHTML = '选择有时必须填写网址信息';
			return false;
		}
		else
		{
			var strRegex = "[a-zA-z]+://[^\s]*";
    		var re=new RegExp(strRegex);
    		if (re.test(url.value))
    		{
	    		$("#urlResult").removeClass("jd-validator-msg");
				$("#urlResult").removeClass("jd-validator-msg focus");
				$("#urlResult").removeClass("jd-validator-msg errinput");
				$("#urlResult").addClass("jd-validator-msg rightinput");
				document.getElementById("urlResult").innerHTML = '网址输入正确！';
    		}
    		else{
    			$("#urlResult").removeClass("jd-validator-msg");
				$("#urlResult").removeClass("jd-validator-msg rightinput");
				$("#urlResult").removeClass("jd-validator-msg focus");
				$("#urlResult").addClass("jd-validator-msg errinput");
				document.getElementById("urlResult").innerHTML = '请填写正确的网址地址（格式：http://www.baidu.com）！';
        		return false;
    		}
		}
	} */
	
		//var szc = document.getElementById("szc");
		//var szcname = szc.options[szc.selectedIndex].text;
		//document.getElementById('szcname').value = szcname;
		
		document.myform1.action="companyRegisterNextUI.action?flag=3";
		document.myform1.submit();
}

$(document).ready(function(){
	/* if($("input[name='company.ifurl']:checked").val()==1)
	{
		$("input[name='company.ifurl'][value='1']").attr("checked",true);
	   	$('#ifurlid').css("display","block"); 
	}else
	{
		$("input[name='company.ifurl'][value='0']").attr("checked",true);
	    $('#ifurlid').css("display","none");  
	}
	
	$("input:radio[name='company.ifurl']").change(function(){
		if($("input[name='company.ifurl']:checked").val()==1)
		{
			$('#ifurlid').css("display","block"); 
		}else{
			$('#ifurlid').css("display","none"); 
		}
	});
	
	var ifurl='${company.ifurl}';
	if(ifurl==1)
	{
		$("input[name='company.ifurl'][value='1']").attr("checked",true);
		$('#ifurlid').css("display","block"); 
	} */
	
	var county='${company.dwdz1}';
	if(null!=county&&county!='')
	{
		$.ajax({
			type:"POST",
			url:"${ctx}/jsp/company/companyQuerySzz.action?mode=ajaxJson&company.county=${company.county}",
			success:function(json){
				json = eval('(' + json + ')');
				var selectContainer = $('#szz'); 
				selectContainer.empty();
				var option = $('<option></option>').text("").val(""); 
				selectContainer.append(option); 
				var option ;
  				for(var i=0; i<json.length; i++){
  					if(json[i].id==county){
  						option = $("<option></option>").text(json[i].name).val(json[i].id).attr('selected','selected'); 
  					}else{
						option = $('<option></option>').text(json[i].name).val(json[i].id); 
  					}
					selectContainer.append(option); 
			 	}
			},
			dateType:"json"
		});
	}
})

function pre(){
		document.myform1.action="companyRegisterNextUI.action?flag=1";
		document.myform1.submit();
	}
	function setDwdz1(obj){
		console.log(obj);
		$("#dwdz1").val();
	}
</script>
</head>
<body>
	<div class="ajtop">
		<div class="ajtopav">
			<div class="ajlogo">企业基本信息注册</div>
			<div class="user"></div>
		</div>
	</div>
	<div class="content">
		<div class="wrap">
			<div class="steps">
				<div class="steps"><span>1.登录信息</span><b class="i1"></b><span class="current">2.基本信息</span><b class="i2"></b><span>3.经营信息</span><b></b><span>4.分类信息</span><b></b><span>5.注册完成</span></div>
			</div>
			<div class="cell">
				<form id="myform1" name="myform1" method="post" class="jd-vali-form">
				<s:hidden name="company.loginname"/>
				<s:hidden name="company.loginword"/>
				<s:hidden name="company.lxr"/>
				<s:hidden name="company.mobile"/>
				<s:hidden name="company.lxfs"/>
				<s:hidden name="company.fddbrlxhm"/>
				<s:hidden name="company.gddh"/>
				<s:hidden name="company.qyyx"/>
				<s:hidden name="company.cz"/>
				<s:hidden name="company.yzbm"/>
				<s:hidden name="company.fddbr"/>
				<s:hidden id="longitude" name="company.longitude"/>
				<s:hidden id="latitude" name="company.latitude"/>
				<s:hidden id="szcname" name="company.szcname"/>
				<s:hidden name="company.qygm"/>
				<s:hidden name="company.qyzclx"/>
				<s:hidden name="company.zczj"/>
				<s:hidden name="company.snxssr"/>
				<s:hidden name="company.snsjss"/>
				<s:hidden name="company.sngdzc"/>
				<s:hidden name="company.snwqtr"/>
				<s:hidden name="company.snaqscf"/>
				<s:hidden name="company.zdmj"/>
				<s:hidden name="company.jzmj"/>
				<s:hidden name="company.cyry"/>
				<s:hidden name="company.sfyygss"/>
				<s:hidden name="company.aqbzdbjb"/>
				<s:hidden name="company.jyfw"/>
				<s:hidden name="company.qylx"/>
				<s:hidden name="company.hyfl"/>
				<s:hidden name="company.ifzsqy"/>
				<s:hidden name="company.zsqytype"/>
				<s:hidden name="company.ifwhpqylx"/>
				<s:hidden name="company.companyType"/>
				<s:hidden name="company.ifzywhqylx"/>
				<s:hidden name="company.ifyhbzjyqy"/>
				<s:hidden name="company.iffmksjyqy"/>
				<s:hidden name="company.metal"/>
				<s:hidden name="company.ventilate"/>
				<s:hidden name="company.transport"/>
				<s:hidden name="company.raisetype"/>
				<s:hidden name="company.sixsys"/>
				<s:hidden name="company.sfaqjg"/>
				<s:hidden name="company.aqglr"/>
				<s:hidden name="company.sfzywsjg"/>
				<s:hidden name="company.zywsglry"/>
				<s:hidden name="company.sfqzwsgly"/>
				<s:hidden name="company.iffmgmqylx"/>
				<s:hidden name="company.feature"/>
				<s:hidden name="company.dwdz1"/>
				<s:hidden name="reloginword"/>
				<div class="bg_01">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th width="25%"><span class="red">*</span>单位名称</th>
						<td width="75%">
							<div class="jd-vali-linediv">
							<input name="company.companyname" dataType="Require" value="${company.companyname}" type="text"class="jd-validator" style="" focusmsg="请填写公司全称，否则将不能通过审核！"  nullmsg="请填写单位名称！" errormsg=''  rightmsg='单位名称输入正确！' datatype="*"  maxlength="255">
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<th width="25%"><span class="red">*</span>所属区域</th>
						<td width="75%">
							<input id="dwdz1" name="company.dwdz1" value="${company.dwdz1}" type="hidden"/>
							<div class="jd-vali-linediv">
							<cus:SelectOneTag property="company.county" defaultText='请选择' style="    width: 45%;" codeName="地址" value="${company.county}" dataType="Require" msg="此项为必选" maxlength="255" onchange="querySzz(this.value);"/>
							<s:select theme="simple" cssStyle="    width: 45%;" id="szz" name="company.dwdz1" emptyOption="true" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require"  value="{company.dwdz1}" msg="此项为必选" onchange="setDwdz1(this.value);"></s:select>
						<!--
							<cus:SelectOneTag property="company.county" defaultText='请选择' codeName="地址" value="${company.county}" style="width:138px;float:left;" onchange="querySzz(this.value);"/>
							<s:select theme="simple" cssStyle="width:200px;" id="szz" name="company.dwdz1" emptyOption="true" list="%{deptlist}" listKey="deptCode" listValue="deptName"   onchange="querySzc(this.value);" headerKey=""  headerValue="请选择" cssClass="jd-validator-droplist" focusmsg="请选择所属区域！"  nullmsg="请选择所属区域！"  rightmsg="所属区域输入正确！" datatype="*"></s:select>
							
							<s:select theme="simple" cssStyle="width:338px;" id="szc" emptyOption= "true" name="company.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" value="{company.szc}" headerKey=""  headerValue="请选择" cssClass="jd-validator-droplist" focusmsg="请选择所属区域！"  nullmsg="请选择所属区域！"  rightmsg="所属区域输入正确！" datatype="*"></s:select>
							-->
							<div class="jd-validator-msg" style="margin-left: 5px;" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<th width="25%"><span class="red">*</span>详细地址</th>
						<td width="75%">
							<div class="jd-vali-linediv">
							<input id="dwdz2" name="company.dwdz2" dataType="Require" value="${company.dwdz2}" focusmsg="请填写详细地址！"  nullmsg="请填写详细地址！" errormsg=''  rightmsg='详细地址输入正确！' datatype="*"   type="text" class="jd-validator" style=""/>
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<!-- <tr>
						<td>
						</td>
						<td>
							<div class="btn_area_bg"  style="float: left;">
								<a href="javaScript:void(0);" onClick="search_place(document.getElementById('dwdz2').value);" class="btn_01">定位<b></b></a>
							</div>
							<div id="dwResult" class="jd-validator-msg"></div>	
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<div id="allmap" style="height:300px;width:70%"></div>
						</td>
					</tr> -->
					<!-- hanxc 20142426 抚顺版本工商注册号可能重复，需加安全生产许可证号
					<tr>
						<th width="25%"><span class="red">*</span>工商注册号</th>
						<td>
							<div style="float: left;width: 43%">
							<input id="gszch" name="company.gszch" class="form_text" style="" value="${company.gszch}" onKeyUp="validate(event,obj);"  type="text" maxlength="255">
							<div class="jd-vali-linediv">
							<input id="gszch" name="company.gszch" class="jd-validator" style=""  focusmsg="请填写工商注册号！"  nullmsg="请填写工商注册号！" errormsg=''  rightmsg='工商注册号输入正确！' datatype="*"  value="${company.gszch}" onKeyUp="validate(event,obj);"  type="text" maxlength="255">
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
							<div class="btn_area_bg" style="float: left;">
									<a href="javaScript:void(0)" onClick="checkUsername1();" class="btn_01">检查工商注册号是否存在<b></b></a>
							</div>
							<div id="checkResult1" class="jd-validator-msg"></div>	
						</td>
					</tr>
					
					 <tr>
						<th width="25%"><span class="red">*</span>工商注册号</th>
						<td width="75%">
							<input id="gszch" name="company.gszch" class="form_text" style="" value="${company.gszch}" onKeyUp="validate(event,obj);" dataType="Require" msg="此项为必填"  type="text" maxlength="255">
						</td>
					</tr>
					<tr>
						<th width="25%">安全生产许可证号</th>
						<td width="75%">
							<input name="company.aqscxkzh" value="${company.aqscxkzh}" class="form_text" style="" onKeyUp="validate(event,obj)" type="text"  maxlength="255">
						</td>
					</tr> -->
					<tr>
						<th width="25%"><span class="red">*</span>社会信用代码</th>
						<td width="75%">
							<input name="company.tyshxydm" dataType="Require" value="${company.tyshxydm}" class="form_text" style="" onKeyUp="validate(event,obj)" type="text"  maxlength="255">
						</td>
					</tr>
					<tr>
						<th width="25%"><span class="red"></span>企业成立时间</th>
						<td width="75%">
							<div class="jd-vali-linediv">
							<input name="company.qyclsj" value="<fmt:formatDate type='date' value='${company.qyclsj}' />" focusmsg="请填写企业成立时间！"  nullmsg="请填写企业成立时间！" errormsg=''  rightmsg='企业成立时间输入正确！' datatype="*" type="text" style="width:338px" class="form_data Wdate  jd-validator" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<!-- <tr>
						<th width="25%"><span class="red">*</span>是否有企业网址</th>
						<td width="75%">
							<input name="company.ifurl"  class="filetype2" id="file20" value="0" type="radio" />&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" class="filetype2" name="company.ifurl" id="file21" value="1"/>&nbsp;是
						</td>
					</tr> -->
					<tr id="ifurlid">
						<th width="25%">企业网址</th>
						<td width="75%">
							<input name="company.url" value="${company.url}"   class="form_text" style="" id="url" type="text">
							<div class="jd-validator-msg"  id="urlResult" ></div>
						</td>
					</tr>
					<tr>
						<th width="25%"></th>
						<td width="75%">
							<div class="login_subfield_btnarea" style="float: left;">
								<a href="javaScript:void(0)" onclick="pre();" class="btn_next">上一步</a>
							</div>
							<div class="login_subfield_btnarea" style="float: left; margin-left: 30px;">
								<input id="submitform" style="display:none;" type="button" onclick="next();"/>
								<a href="javaScript:void(0)" class="btn_next checkformdata" onclick="next();">下一步</a>
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
			<span>版权所有&nbsp;&nbsp;南京拓构软件有限公司</span><span>COPYRIGHTS 2014</span>
		</div>
	</div>
</body>
</html>