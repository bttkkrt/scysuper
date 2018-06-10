<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>企业注册</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/js/xmlhttp.js"></script>
	<script type="text/javascript">
			var map;
			var overlays = [];
    		$(function(){
    			// 百度地图API功能
				map = new BMap.Map("allmap");
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
    		drawingManager.addEventListener('overlaycomplete', overlaycomplete);
    		});
		
			//回调获得覆盖物信息
    		var overlaycomplete = function(e){
    	 		clearAll();
    			overlays.push(e.overlay);
      			var longitude = e.overlay.getPosition().lng;
				var latitude = e.overlay.getPosition().lat;
				document.getElementById('longitude').value = longitude;
				document.getElementById('latitude').value = latitude;
    		};
    	function clearAll()
    	{
    		for(var i = 0; i < overlays.length; i++){
           	 	map.removeOverlay(overlays[i]);
        	}
    	}
    	function myFun(result){
   	 		var cityName = result.name;
    		map.centerAndZoom(cityName,12);
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
	    
	</script>
	<script>
	function next(){
     	var username = document.getElementById("loginname");
		if(username==''||username.value.length<5)
		{
			alert("请按规则填写用户名");
			return false;
		}
		checkUsername();
		if(document.getElementById("checkResult").innerHTML == '该用户名已经存在')
		{
			return false;
		}
		var loginword = document.getElementById("loginword");
		if(loginword==''||loginword.value.length<5)
		{
			alert("密码长度过短！");
			return false;
		}
		var form=document.forms[0];
		if(form.loginword.value!=form.reloginword.value){
			document.getElementById('HackBox').innerHTML='两次输入的密码不正确';
			document.getElementById('HackBox').style.display="";
			document.getElementById('reloginword').focus();
			return false;
		}
		var fddbrlxhm = document.getElementById('fddbrlxhm');
		var reg= /(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/ ; 
		if(/^13\d{9}$/g.test(fddbrlxhm.value) == false&& 
		/^15[0-35-9]\d{8}$/g.test(fddbrlxhm.value) == false&& 
		/^18[0-9]\d{8}$/g.test(fddbrlxhm.value) == false &&
		reg.test(fddbrlxhm.value) == false )
		{  
         	alert("请填写正确的联系号码");
            return false;   
		} 
		var longitude = document.getElementById('longitude').value;
		var latitude =  document.getElementById('latitude').value;
		if(longitude == null || longitude == "" || latitude == null || latitude == "")
		{
			alert("请在地图上标注企业位置");
            return false; 
		}
		checkUsername1();
		if(document.getElementById("checkResult1").innerHTML == '该工商注册号已经存在')
		{
			return false;
		}
		var mobile = document.getElementById('mobile');
		if(/^13\d{9}$/g.test(mobile.value) == false &&
		/^15[0-35-9]\d{8}$/g.test(mobile.value) == false &&   
		/^18[0-9]\d{8}$/g.test(mobile.value) == false )
		{     
         	alert("请填写正确的安全管理员手机号码");
            return false;   
		} 
		var lxfs = document.getElementById('lxfs');
        if (reg.test(lxfs.value)==false)
        {
        	alert("请填写正确的安全管理员固话");
        	return false;
        }
		var email =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var objemail = document.getElementById('qyyx');
		if(objemail.value!="")
		{
			if(email.test(objemail.value)==false)
			{
				alert("请输入正确的邮箱地址");
        		return false;
			}
		}
		if($('#ifzsqy').val()==1)
	    {
			$("#zsqytype").attr("dataType","Require").attr("msg","此项为必选");
		}else{
			$("#zsqytype").attr("dataType","");
		}
		if(Validator.Validate(document.myform1,3)){
			var szc = document.getElementById("szc");
			var szcname = szc.options[szc.selectedIndex].text;
			document.getElementById('szcname').value = szcname;
			
			document.myform1.action="companyRegisterNextUI.action";
			document.myform1.submit();
		}
	}
	function checkUsername(){
		var username = document.getElementById("loginname");
		if(username.value==''||username.value.length<5)
		{
		alert("请按规则填写用户名");
		return false;
		}
		
		var viewobj = document.getElementById("checkResult");
		viewobj.innerHTML = "正在检测中...";
		$.ajax({
			url : "isCompanyExsit.action?company.loginname="+ username.value,
		    type: 'post',
		    dataType: 'json',
		    async : false,
		    data:{ 
		    },
		    error: function(){
		    	viewobj.innerHTML = "检测出错";
		    },
		    success: function(data){
		    	if(data.result == 'true'){
		        	viewobj.innerHTML = "该用户名已经存在";
		        }else{
		            viewobj.innerHTML = "该用户名可以使用";
		        }
		   }
		});
	}
	
	function checkUsername1(){
		var gszch = document.getElementById("gszch");
		if(gszch.value=='')
		{
		alert("请先输入工商注册号");
		return false;
		}
		
		var viewobj = document.getElementById("checkResult1");
		viewobj.innerHTML = "正在检测中...";
		$.ajax({
			url : "isCompanyExsit.action?company.gszch="+ gszch.value,
		    type: 'post',
		    dataType: 'json',
		    async : false,
		    data:{ 
		    },
		    error: function(){
		    	viewobj.innerHTML = "检测出错";
		    },
		    success: function(data){
		    	if(data.result == 'true'){
		        	viewobj.innerHTML = "该工商注册号已经存在";
		        }else{
		            viewobj.innerHTML = "该工商注册号可以使用";
		        }
		   }
		});
	}
		
	function checkForm(){
	    var form=document.forms[0];
		if(form.loginword.value!=form.reloginword.value){
			document.getElementById('HackBox').innerHTML='两次输入的密码不正确';
			document.getElementById('HackBox').style.display="";
			document.getElementById('reloginword').focus();
		}else
		{
		document.getElementById('HackBox').style.display='none';
		}
	}
	function onlyNum()
	{ 
		var keys=event.keyCode;
		if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||(keys==8)||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)||(keys==189)||(keys==109)))
			event.returnValue=false;
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
    
    $(function(){       
		$(".filetype1").change(function(){  
			var val = $("input[name='file1']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#ifzsqy').val(1);    
				$('#ifzsqyid').css("display","block"); 
			}else{   
				$('#ifzsqy').val(0);  
				$('#ifzsqyid').css("display","none");             
			}                    
		});          
	}); 
    
    $(document).ready(function(){
		if($('#ifzsqy').val()==1)
		{
			document.getElementById("file01").checked=true;
		    document.getElementById("file00").checked=false;
		    $('#ifzsqyid').css("display","block"); 
		}else
		{
		    document.getElementById("file01").checked=false;
		    document.getElementById("file00").checked=true;
		    $('#ifzsqyid').css("display","none");  
		}
	})
	</script>
	<style type="text/css">    
 	body{    
		background-image:url('../../webResources/themes/tree/images/default/registerUI.png');  
		font-size : 12px; color : #000000; font-family : tahoma, 宋体, fantasy; text-align : center; margin : 0; 
	}    

 
	table 
  	{      
  		line-height:   20px;   
  	} 
  	input 
  	{
  		border:1px solid #000000;
  	}
	input 
	{
		star : expression(
			onmouseover=function(){this.style.border="1px solid #ff0000" },
			onmouseout=function(){this.style.border="1px solid #000000"}
		)
	}
</style>
</head>
  <body >
    <div  style="margin-top:20px;" >
	<form name="myform1" method="post" enctype="multipart/form-data">
		<div style="height:100%;overflow:auto">
			<input type="hidden" name="company.qygm" value="${company.qygm}">
			<input type="hidden" name="company.qyzclx" value="${company.qyzclx}">
			<input type="hidden" name="company.zczj" value="${company.zczj}">
			<input type="hidden" name="company.whpqylx" value="${company.whpqylx}">
			<input type="hidden" name="company.iffmgmqylx" id="iffmgmqylx" value="${company.iffmgmqylx}">
			<input type="hidden" name="company.ifwhpqylx" id="ifwhpqylx" value="${company.ifwhpqylx}">
			<input type="hidden" name="company.ifzywhqylx" id="ifzywhqylx" value="${company.ifzywhqylx}">
			<input type="hidden" name="company.ifyhbzjyqy" id="ifyhbzjyqy" value="${company.ifyhbzjyqy}">
			<input type="hidden" name="company.iffmksjyqy" id="iffmksjyqy" value="${company.iffmksjyqy}">
			<input type="hidden" name="company.ifzsqy" id="ifzsqy" value="${company.ifzsqy}">
			<input type="hidden" name="company.yhbzjyqy"  value="${company.yhbzjyqy}">
			<input type="hidden" name="company.zywhqylx" value="${company.zywhqylx}">
			<input type="hidden" name="company.frdm" value="${company.frdm}">
			<input type="hidden" name="company.qyclsj" value="<fmt:formatDate type="both" value="${company.qyclsj}" />">
			<input type="hidden" name="company.gddh" value="${company.gddh}">
			<input type="hidden" name="company.cz" value="${company.cz}">
			<input type="hidden" name="company.yzbm"  value="${company.yzbm}" >
			<input type="hidden" name="company.snxssr" value="${company.snxssr}">
		
			<input type="hidden" name="company.snsjss" value="${company.snsjss}">
			<input type="hidden" name="company.sngdzc" value="${company.sngdzc}">
			<input type="hidden" name="company.snwqtr" value="${company.snwqtr}">
			<input type="hidden" name="company.snaqscf" value="${company.snaqscf}">
			<input type="hidden" name="company.sfaqjg" value="${company.sfaqjg}">
			<input type="hidden" name="company.aqglr" value="${company.aqglr}">
		
			<input type="hidden" name="company.sfzywsjg" value="${company.sfzywsjg}">
			<input type="hidden" name="company.zywsglry" value="${company.zywsglry}">
			<input type="hidden" name="company.sfqzwsgly" value="${company.sfqzwsgly}">
			<input type="hidden" name="company.zdmj" value="${company.zdmj}">
			<input type="hidden" name="company.jzmj" value="${company.jzmj}">
	
			<input type="hidden" name="company.cyry" value="${company.cyry}">
			<input type="hidden" name="company.sfyygss" value="${company.sfyygss}">
			<input type="hidden" name="company.aqbzdbjb" value="${company.aqbzdbjb}">
			<input type="hidden" name="company.jyfw" value="${company.jyfw}">
			<input type="hidden" name="company.ifurl" value="${company.ifurl}">
			<input type="hidden" name="company.url"  value="${company.url}">
			<input type="hidden" id="szcname" name="company.szcname"  value="${company.szcname}">
			
			<input id="longitude" name="company.longitude" value="${company.longitude}" type="hidden">
			<input id="latitude" name="company.latitude" value="${company.latitude}"   type="hidden">
			
			<table width="100%" border="0"  bgcolor="#F9F9F9">
			     <tr>
					<td colspan='3' style="text-align:center;font-size:20px;font-weight:bold;">企业基本信息注册</td>
				</tr>
				<tr></tr>
				<tr>
					<td width="22%" align="right">用户名：</td>
					<td colspan="2">
						<input name="company.loginname" id='loginname' size=30 dataType="Require" msg="此项为必填" value="${company.loginname}" onKeyUp="value=value.replace(/[\W]/g,'')"  type="text" maxlength="20"><font color="red">*</font>
						<input type="button" name="check_username" id="check_username" value="检查用户名是否可用" onClick="checkUsername()" onMouseOver="this.style.cursor='hand';"/>
				  		<div id="checkResult" style="color:red"></div>					
				  	</td>
			  	</tr>
				<tr>
					<td colspan='3' align="center"><div id="username_info" style="color:#000000">5-20个字符(包括小写字母、数字、下划线)，一旦注册成功会员名不能修改。</div></td>
				</tr>
				<tr>
					<td width="22%" align="right">密码：</td>
					<td colspan="2"><input name="company.loginword" id="loginword" size=30 dataType="Require" msg="密码为必填项" value="${company.loginword}" type="password" minlength="5" maxlength="15" ><font color="red">*</font>(5-15个字符)</td>
			  	</tr>
				<tr>
					<td width="22%" align="right">确认密码：</td>
					<td colspan="2"><input name="reloginword"  type="password" size=30 onkeyup ="checkForm()" maxlength="15" value="${company.loginword}"><font color="red">*</font>(再输一次密码)
				  		<div style="color:#ff4500" id="HackBox"></div>					
				  	</td>
				</tr>
				<tr>
					<td width="22%" align="right">单位名称：</td>
					<td colspan="2"><input name="company.companyname" value="${company.companyname}" type="text" size=60 dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font>(请填写公司全称，否则将不能通过审核。)</td>
			  	</tr>
				<tr>
					<td width="22%" align="right">法定代表人：</td>
					<td colspan="2"><input name="company.fddbr" value="${company.fddbr}" size=30 type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="22%" align="right">联系号码：</td>
					<td colspan="2"><input id="fddbrlxhm" name="company.fddbrlxhm" value="${company.fddbrlxhm}" dataType="Require" msg="此项为必填" size=30 type="text" maxlength="255"><font >法定代表人手机号码或固定电话(格式：18941316001 或024-1234567)</font></td>
				</tr>
				<tr>
					<td width="22%" align="right">所属区域：</td>
					<td colspan="2" >
					
					
						<cus:SelectOneTag property="company.county" defaultText='请选择' codeName="地址" value="${company.county}" dataType="Require" msg="此项为必选" maxlength="255" onchange="querySzc(this.value);"/>
						
						<s:select theme="simple" cssStyle="width:100px;" id="szc" name="company.dwdz1" emptyOption="true" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选"></s:select>
						<font color="red">*</font>
						
						<!-- hanxc 2014/11/8  company.county
						<s:select theme="simple" cssStyle="width:100px;" id="szc" name="company.szc" emptyOption="true" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选"></s:select>
						<font color="red">*</font>
						 -->
					</td>
				</tr>
				<tr>
				 	<td width="22%" align="right">详细地址：</td>
				    <td colspan="2">
						<input id="dwdz2" name="company.dwdz2" size=30 value="${company.dwdz2}" size=28 type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font>
						<input type="button" name="check_username2" id="check_username2" value="定位" onClick="search_place(document.getElementById('dwdz2').value);" onMouseOver="this.style.cursor='hand';"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<div id="allmap" style="height:300px;width:70%"></div>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right">工商注册号：</td>
					<td colspan="2"><input id="gszch" name="company.gszch" size=30 value="${company.gszch}" onKeyUp="validate(event,obj);"  type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font>
					<input type="button" name="check_username1" id="check_username1" value="检查工商注册号是否存在" onClick="checkUsername1()" onMouseOver="this.style.cursor='hand';"/>
				  	<div id="checkResult1" style="color:red"></div>	
				  </td>
			  	</tr>
				<tr>
					<td width="22%" align="right">企业类型：</td>
					<td colspan="2"><cus:SelectOneTag property="company.qylx"  defaultText='请选择' codeName="企业类型" value="${company.qylx}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
			    </tr>
				<tr>
					<td width="22%" align="right">行业分类：</td>
					<td colspan="2"><cus:SelectOneTag property="company.hyfl"  defaultText='请选择' codeName="企业行业分类" value="${company.hyfl}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
	          	</tr>
	          	<tr>
			    	<td width="22%" align="right">是否直属企业：</td>
					<td colspan="2">
					 	<input type="radio" style="border: 0px;" name="file1"  id="file00" value="0" class="filetype1">否
                     	<input type="radio" name="file1" id="file01"  checked="checked"  value="1"  class="filetype1">是<font color="red">*</font>
					</td>
				</tr>
	          	<tr  id="ifzsqyid">
	          		<td width="22%" align="right">直属等级：</td>
					<td width="100%" colspan="2">
						<cus:SelectOneTag style="width:150px;" property="company.zsqytype" defaultText='请选择' codeName="直属等级" value="${company.zsqytype}" /><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td width="22%" align="right">安全管理员：</td>
					<td colspan="2"><input name="company.lxr" value="${company.lxr}" size=30 type="text" dataType="Require" msg="此项为必填" maxlength="255"><font color="red">*</font></td>
			    </tr>
			    <tr>
					<td width="22%" align="right">安全管理员手机号码：</td>
					<td colspan="2"><input name="company.mobile" value="${company.mobile}" size=30 type="text" dataType="Require"  onKeyDown="onlyNum()" onKeyUp = "validate(event,obj)" id="mobile"  msg="此项为必填" maxlength="11"><font color="red">*</font>(格式：18941316001)</td>
			    </tr>
				<tr>
					<td width="22%" align="right">安全管理员固话：</td>
					<td colspan="2"><input name="company.lxfs" value="${company.lxfs}" size=30 id='lxfs' type="text"  id="lxfs" dataType="Require" msg="此项为必填" require="true" maxlength="255"><font color="red">*</font>(格式：024-1234567)</td>
	          	</tr>
				<tr>
					<td width="22%" align="right">组织机构代码：</td>
					<td colspan="2"><input name="company.zzjgdm" value="${company.zzjgdm}" size=30 onKeyUp="validate(event,obj)" type="text"  maxlength="255"></td>
			    </tr>
				<tr>
					<td width="22%" align="right">企业邮箱：</td>
					<td colspan="2"><input name="company.qyyx" value="${company.qyyx}" onKeyUp="value=value.replace(/^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi,'')" dataType="Require" msg="此项为必填" size=30 id='qyyx' type="text"  maxlength="255"><font color="red">*</font>(格式：123@126.com)</td>
			    </tr>
			</table>
	  	</div>
	
	  	<div  style="margin:10px;margin-left:60px;">
			<a href="#" ><img style="border:none;" src='<c:url value="/webResources/themes/tree/images/default/next.png"/>' onClick="next();" onmouseover="this.src='<c:url value="/webResources/themes/tree/images/default/next.png"/>'" 
			onmouseout="this.src='<c:url value="/webResources/themes/tree/images/default/next.png"/>'"  />
			</a>
		</div>
	</form>
</div>
</body>
</html>
