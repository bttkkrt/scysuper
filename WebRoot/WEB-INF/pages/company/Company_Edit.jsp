<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%
		String companyid = request.getParameter("companyid");
	%>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	<%@include file="/common/jsLib.jsp"%>
	<style type="text/css">
	</style>
	<script type="text/javascript">
			var map;
			var overlays = [];
    		$(function(){
    			if("${updateFlag}"=="1"){
    				layer.alert('更新成功', {
  					  skin: 'layui-layer-molv' //样式类名
  					  ,closeBtn: 0
  					}, function(){
  						if (window.opener != null && !window.opener.closed) {
  				          window.opener.reloadDate();
  				        } 
  						window.opener=null;
  						window.open('','_self');
  						window.close();
  					});
    			}else if("${updateFlag}"=="2"){
    				layer.alert('保存失败');
    			}
    			
    			getHyflTwoLevel("${company.hyflOneLevel}");
				getHyflThreeLevel("${company.hyflTwoLevel}");
				getHyflFourLevel("${company.hyflThreeLevel}");
    			
    			/* // 百度地图API功能
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
    		drawingManager.addEventListener('overlaycomplete', overlaycomplete); */
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
			//clearAll();
			// 创建地址解析器实例
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(obj, function(point){
  				if (point) {
  					var marker = new BMap.Marker(point);
    				overlays.push(marker);
    				//map.centerAndZoom(point, 16);
    				//map.addOverlay(marker);
    				var longitude = marker.getPosition().lng;
					var latitude = marker.getPosition().lat;
					document.getElementById('longitude').value = longitude;
					document.getElementById('latitude').value = latitude;
					layer.alert("定位成功！");
  				}else{
  					layer.alert('该地址无法定位！请重新确认单位地址！否则将无法在地图上展示！')
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
	function onlyNum()
	{ 
		var keys=event.keyCode
		if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||(keys==8)||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)||(keys==189)||(keys==109)))
		event.returnValue=false;
	} 
	function save(){
		/* var longitude = document.getElementById('longitude').value;
		var latitude =  document.getElementById('latitude').value;
		if(longitude == null || longitude == "" || latitude == null || latitude == "")
		{
			alert("请在地图上标注企业位置");
            return false; 
		} */
		var fddbrlxhm = document.getElementById('fddbrlxhm');
		var reg=/(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/ ; 
		if(/^13\d{9}$/g.test(fddbrlxhm.value) == false &&
		/^15[0-35-9]\d{8}$/g.test(fddbrlxhm.value) == false &&  
		/^18[0-9]\d{8}$/g.test(fddbrlxhm.value) == false &&
		reg.test(fddbrlxhm.value) == false )
		{     
         	layer.alert("请填写正确的联系号码");
            return false;   
		} 
		/* var mobile = document.getElementById('mobile');
		if(/^13\d{9}$/g.test(mobile.value) == false &&
		/^15[0-35-9]\d{8}$/g.test(mobile.value)== false&&   
		/^18[0-9]\d{8}$/g.test(mobile.value) == false)
		{     
         	layer.alert("请填写正确的安全管理员手机号码");
            return false;   
		}  */
		/* var lxfs = document.getElementById('lxfs');
        if (reg.test(lxfs.value)==false)
        {
        	alert("请填写正确的安全管理员固话");
        	return false;
        } */
		/* var email =  /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		var objemail = document.getElementById('qyyx');
		if(objemail.value!="")
		{
			if(email.test(objemail.value)==false)
			{
				layer.alert("请输入正确的邮箱地址");
        		return false;
			}
		} */
		if($('#ifwhpqylx').val()!=1 && 
		   $('#ifyhbzjyqy').val()!=1 && 
		   $('#iffmksjyqy').val()!=1 && 
		   $('#iffmgmqylx').val()!=1 ){
				layer.alert("请至少选择一项企业所属监管类型！");
        		return false;
		}
		if($('#iffmgmqylx').val()==1)
		{
			var ches = $("#iffmgmqylxid").children();
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
				layer.alert("工贸企业类型至少选择一项");
				return false;
			}
		}
		if($('#ifwhpqylx').val()==1)
		{
			var ches = $("#ifwhpqylxid").children();
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
				layer.alert("危化品企业类型至少选择一项");
				return false;
			}
		}
		if($('#ifzywhqylx').val()==1)
	    {
			var ches = $("#ifzywhqylxid").children();
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
				layer.alert("职业危害企业类型至少选择一项");
				return false;
			}
		}
		/* if($('#ifyhbzjyqy').val()==1)
	    {
			var ches = $("#ifyhbzjyqyid").children();
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
				layer.alert("烟花爆竹企业类型至少选择一项");
				return false;
			}
		} */
		if($('#iffmksjyqy').val()==1)
	    {
			$("#diggingstype").children().attr("dataType","Require").attr("msg","此项为必选");
			$("#metal").attr("dataType","Require").attr("msg","此项为必选");
			if($("#diggingstype").children().val() == 'd001' || $("#diggingstype").children().val() == 'd005' ){
				$("#ventilate").attr("dataType","Require").attr("msg","此项为必选");
				$("#transport").attr("dataType","Require").attr("msg","此项为必选");
				$("#raisetype").attr("dataType","Require").attr("msg","此项为必选");
				$("#sixsys").attr("dataType","Require").attr("msg","此项为必填");
			}
			
			
		}else{
			$("#diggingstype").children().attr("dataType","").attr("msg","此项为必选");
			$("#metal").attr("dataType","").attr("msg","此项为必选");
			$("#ventilate").attr("dataType","").attr("msg","此项为必选");
			$("#transport").attr("dataType","").attr("msg","此项为必选");
			$("#raisetype").attr("dataType","").attr("msg","此项为必选");
			$("#sixsys").attr("dataType","").attr("msg","此项为必填");
		}
		
		
		/* var gddh = document.getElementById('gddh');
		if (reg.test(gddh.value)==false)
       	{
        	alert("请填写正确的固定电话号码。");
       	 	return false;
        } */
       /*  var radioCheck = document.getElementById('file21');
		if(radioCheck.checked)
		{
			var url = document.getElementById('url');
			if(url.value == "")
			{
				alert("选择有时必须填写网址信息");
				return false;
			}
			else
			{
				var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        		+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
        		+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        		+ "|" // 允许IP和DOMAIN（域名）
        		+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
        		+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
        		+ "[a-z]{2,6})" // first level domain- .com or .museum
       	 		+ "(:[0-9]{1,4})?" // 端口- :80
        		+ "((/?)|" // a slash isn't required if there is no file name
        		+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        		var re=new RegExp(strRegex);
        		if (re.test(url.value)){
        		}else{
              		alert("请填写正确的网址地址");
            		return false;
        		}
			}
		} */
		if($('#ifzsqy').val()==1)
	    {
			$("#zsqytype").attr("dataType","Require").attr("msg","此项为必选");
		}else{
			$("#zsqytype").attr("dataType","");
		}
		if(Validator.Validate(document.myform1,3)){
			//var szc = document.getElementById("szc");
			//var szcname = szc.options[szc.selectedIndex].text;
			//document.getElementById('szcname').value = szcname;
			layer.confirm('更新企业注册信息？', {
    					  btn: ['确定','取消'] //按钮
    					}, function(){
    						document.myform1.action="companySaveMySelf.action";
    						document.myform1.submit();
    					}, function(){
    					});
        	/* $.messager.confirm('更新企业注册信息', '更新企业注册信息将会重新提交安监局审核，审核前不影响继续使用。确定要更新企业注册信息？', function(result){
				if (result){
					document.myform1.action="companySaveMySelf.action";
					document.myform1.submit();
				}
			}); */
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
		if($('#iffmgmqylx').val()==1)
		{
			document.getElementById("file61").checked=true;
			document.getElementById("file60").checked=false;
		   	$('#iffmgmqylxid').css("display","block"); 
		}else
		{
			document.getElementById("file61").checked=false;
			document.getElementById("file60").checked=true;
		    $('#iffmgmqylxid').css("display","none");  
		}
		
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
		if($('#ifzywhqylx').val()==1)
		{
		   	document.getElementById("file11").checked=true;
		    document.getElementById("file10").checked=false;
			$('#ifzywhqylxid').css("display","block"); 
		}else
		{
		    document.getElementById("file11").checked=false;
		    document.getElementById("file10").checked=true;
		    $('#ifzywhqylxid').css("display","none");  
		}
		/* if($('#ifurl').val()==1)
		{
		    document.getElementById("file21").checked=true;
		    document.getElementById("file20").checked=false;
		    $('#ifurlid').css("display","block"); 
		}else
		{
		    document.getElementById("file21").checked=false;
		    document.getElementById("file20").checked=true;
		    $('#ifurlid').css("display","none");  
		} */
		/* if($('#ifyhbzjyqy').val()==1)
		{
			document.getElementById("file31").checked=true;
		 	document.getElementById("file30").checked=false;
			$('#ifyhbzjyqyid').css("display","block"); 
		}else
		{
			document.getElementById("file31").checked=false;
			document.getElementById("file30").checked=true;
			$('#ifyhbzjyqyid').css("display","none");  
	    }  */
	     if($('#iffmksjyqy').val()==1)
		{
		     document.getElementById("file41").checked=true;
		     document.getElementById("file40").checked=false;
		   	 $('.iffmksjyqyid').css("display","");
				showDiv();
		}else
		{ 
		     document.getElementById("file41").checked=false;
		     document.getElementById("file40").checked=true;
		     $('.iffmksjyqyid').css("display","none");  
	    } 
	      if($('#ifzsqy').val()==1)
		{
		     document.getElementById("file51").checked=true;
		     document.getElementById("file50").checked=false;
		   	 $('#ifzsqyid').css("display","block"); 
		}else
		{ 
		     document.getElementById("file51").checked=false;
		     document.getElementById("file50").checked=true;
		     $('#ifzsqyid').css("display","none");  
	    }
	})
		
	$(function(){       
    	$(".filetype").change(function(){  
        	var val = $("input[name='file6']:checked").val(); //获得选中的radio的值             
            if(val=='1'){    
            	$('#iffmgmqylx').val(1);  
            	$('#iffmgmqylxid').css("display","block"); 
            }else{   
                $('#iffmgmqylx').val(0);   
                $('#iffmgmqylxid').css("display","none");             
            }                    
        });          
	});
	$(function(){       
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
	$(function(){       
		$(".filetype1").change(function(){  
			var val = $("input[name='file1']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#ifzywhqylx').val(1);    
				$('#ifzywhqylxid').css("display","block"); 
			}else{   
				$('#ifzywhqylx').val(0);  
				$('#ifzywhqylxid').css("display","none");             
			}                    
		});         
	}); 
	$(function(){       
		$(".filetype2").change(function(){  
			var val = $("input[name='file2']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#ifurl').val(1);  
				$('#ifurlid').css("display","block"); 
			}else{   
				$('#ifurl').val(0);   
				$('#ifurlid').css("display","none");             
			}                   
		});          
	});
	$(function(){       
		$(".filetype3").change(function(){  
			var val = $("input[name='file3']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#ifyhbzjyqy').val(1);  
				$('#ifyhbzjyqyid').css("display","block"); 
			}else{   
				$('#ifyhbzjyqy').val(0);   
				$('#ifyhbzjyqyid').css("display","none");             
			}                    
		});          
	});     
    $(function(){       
		$(".filetype4").change(function(){  
			var val = $("input[name='file4']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#iffmksjyqy').val(1);  
				$('.iffmksjyqyid').css("display","");
				showDiv();
			}else{   
				$('#iffmksjyqy').val(0);   
				$('.iffmksjyqyid').css("display","none");             
			}                    
		});          
	});   
     $(function(){       
		$(".filetype5").change(function(){  
			var val = $("input[name='file5']:checked").val(); //获得选中的radio的值             
			if(val=='1'){    
				$('#ifzsqy').val(1);  
				$('#ifzsqyid').css("display","block"); 
			}else{   
				$('#ifzsqy').val(0);   
				$('#ifzsqyid').css("display","none");             
			}                    
		});          
	}); 
    function querySzc(obj)
    {
    	$.ajax({
				type:"POST",
				url:"${ctx}/jsp/company/companyQuerySzc.action?mode=ajaxJson&company.county="+obj,
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
					var option = $('<option></option>').text("").val(""); 
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
    
    $(function(){
    	querySzz($("#county").val());
    });

	$(function(){
		var value="";
		var ct = '${company.companyType}';
    	var nodes = ct.split(',');
		for(var i=0; i<nodes.length; i++){
			var temp = nodes[i].trim();
			if(temp.length==4 && temp.substring(0,1)=='d'){
				value = temp;
				$("#diggingstype").children().val(temp);
			}
		}
		if(value=="d001" || value=="d005"){
			$("#tffs").show();
    		$("#ysfs").show();
    		$("#tsfs").show();
			$("#ldxt").show();
			$("#ventilate").attr("dataType","Require").attr("msg","此项为必选");
			$("#transport").attr("dataType","Require").attr("msg","此项为必选");
			$("#raisetype").attr("dataType","Require").attr("msg","此项为必选");
			$("#sixsys").attr("dataType","Require").attr("msg","此项为必填");
		}else{
			$("#tffs").hide();
    		$("#ysfs").hide();
    		$("#tsfs").hide();
			$("#ldxt").hide();
			$("#ventilate").attr("dataType","").attr("msg","");
			$("#transport").attr("dataType","").attr("msg","");
			$("#raisetype").attr("dataType","").attr("msg","");
			$("#sixsys").attr("dataType","").attr("msg","");
		}
    });
    
    function showDiv(){ 
    	var value = $("#diggingstype").children().val();
		if(value=="d001" || value=="d005"){
			$("#tffs").show();
    		$("#ysfs").show();
    		$("#tsfs").show();
			$("#ldxt").show();
			$("#ventilate").attr("dataType","Require").attr("msg","此项为必选");
			$("#transport").attr("dataType","Require").attr("msg","此项为必选");
			$("#raisetype").attr("dataType","Require").attr("msg","此项为必选");
			$("#sixsys").attr("dataType","Require").attr("msg","此项为必填");
		}else{
			$("#tffs").hide();
    		$("#ysfs").hide();
    		$("#tsfs").hide();
			$("#ldxt").hide();
			$("#ventilate").attr("dataType","").attr("msg","");
			$("#transport").attr("dataType","").attr("msg","");
			$("#raisetype").attr("dataType","").attr("msg","");
			$("#sixsys").attr("dataType","").attr("msg","");
		}
    }
	</script>
	
</head>
<body style="overflow-y: scroll">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<s:form name="myform1" method="post" enctype="multipart/form-data" theme='simple'>
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="company.id" value="${company.id}">
		<input type="hidden" name="company.createTime" value="<fmt:formatDate type="both" value="${company.createTime}" />">
		<input type="hidden" name="company.updateTime" value="${company.updateTime}">
		<input type="hidden" name="company.createUserID" value="${company.createUserID}">
		<input type="hidden" name="company.updateUserID" value="${company.updateUserID}">
		<input type="hidden" name="company.deptId" value="${company.deptId}">
		<input type="hidden" name="company.delFlag" value="${company.delFlag}">
		<input type="hidden" name="company.loginname" value="${company.loginname}">
		<input type="hidden" name="company.loginword" value="${company.loginword}">
		<input type="hidden" name="company.iffmgmqylx" id="iffmgmqylx" value="${company.iffmgmqylx}">
		<input type="hidden" name="company.ifwhpqylx" id="ifwhpqylx" value="${company.ifwhpqylx}">
		<input type="hidden" name="company.ifzywhqylx" id="ifzywhqylx" value="${company.ifzywhqylx}">
		<input type="hidden" name="company.ifurl" id="ifurl" value="${company.ifurl}">
		<input type="hidden" name="company.ifyhbzjyqy" id="ifyhbzjyqy" value="${company.ifyhbzjyqy}">
		<input type="hidden" name="company.iffmksjyqy" id="iffmksjyqy" value="${company.iffmksjyqy}">
		<input type="hidden" name="company.ifzsqy" id="ifzsqy" value="${company.ifzsqy}">
		<input type="hidden" name="company.basePass"  value="${company.basePass}">
		<input type="hidden" id="szcname" name="company.szcname"  value="${company.szcname}">
		<input id="longitude" name="company.longitude" value="${company.longitude}" type="hidden">
		<input id="latitude" name="company.latitude" value="${company.latitude}"   type="hidden">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th colspan="4" style="text-align:center;color:red">注册信息</th>
				</tr>
				<tr>
					<th width="15%">单位名称<span style="color:red">*</span></th>
					<td width="35%"><input name="company.companyname" value="${company.companyname}" size= 50 type="text" dataType="Require" msg="此项为必填" maxlength="255"></td>				
					<th width="15%">社会信用代码<span style="color:red">*</span></th>
					<td width="35%"><input name="company.tyshxydm" value="${company.tyshxydm}" class="text" size= 50   dataType="Require" msg="此项为必填" type="text"  maxlength="255"></td>
				</tr>
				<tr>
				 	<th width="15%">法定代表人<span style="color:red">*</span></th>
					<td width="35%"><input name="company.fddbr" value="${company.fddbr}" size= 50 type="text" dataType="Require" msg="此项为必填" maxlength="255"></td>
					<th width="15%">法定代表人联系号码<span style="color:red">*</span></th>
					<td width="35%"><input id="fddbrlxhm" name="company.fddbrlxhm" value="${company.fddbrlxhm}" size=50 type="text" dataType="Require" msg="此项为必填" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">所属区域<span style="color:red">*</span></th>
					<td width="35%">
						<cus:SelectOneTag property="company.county" defaultText='请选择' codeName="地址" value="${company.county}" dataType="Require" msg="此项为必选" maxlength="255" onchange="querySzz(this.value);"/>
						<s:select theme="simple" cssStyle="width:100px;" id="szz" name="company.dwdz1" emptyOption="true" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require"  value="{company.dwdz1}" msg="此项为必选" ></s:select>
						<!--<s:select theme="simple" cssStyle="width:100px;"  headerKey=""  headerValue="请选择" id="szc" emptyOption= "true" name="company.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" value="{company.szc}"></s:select>-->
						<font color="red">*</font>
						
						<!-- hanxc 2014/11/8 
						<cus:SelectOneTag property="company.dwdz1" defaultText='请选择' codeName="相城地址" value="${company.dwdz1}" dataType="Require" msg="此项为必选" onchange="querySzc(this.value);"/>
						<s:select theme="simple" cssStyle="width:100px;" id="szc" name="company.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选" value="{company.szc}"></s:select>
						<font color="red">*</font>
						 -->
					</td>
					<th width="15%">详细地址<span style="color:red">*</span></th>
					<td width="35%">
						<input id="dwdz2" name="company.dwdz2" value="${company.dwdz2}" size=40 type="text" dataType="Require" msg="此项为必填" maxlength="255">
						<input type="button" name="check_username2" id="check_username2" value="定位" onClick="search_place(document.getElementById('dwdz2').value);" onMouseOver="this.style.cursor='hand';"/>
					</td>
				</tr>
				<!-- <tr>
					<td colspan="4" align="center">
						<div id="allmap" style="height:300px;"></div>
					</td>
				</tr> -->
				<tr>
					<th width="15%">是否省市直属企业<span style="color:red">*</span></th>
					<td width="35%">			
					 	<input type="radio" name="file5"  id="file50" value="0" class="filetype5">否
                     	<input type="radio" name="file5" id="file51"  value="1"  class="filetype5">是
                    </td>
                    <th width="15%">直属等级</th>
                    <td width="50%">
					 	<div id="ifzsqyid"><cus:SelectOneTag style="width:150px;" property="company.zsqytype" defaultText='请选择' codeName="直属等级" value="${company.zsqytype}" /><font style="color:red">*</font></div>
					</td>
				</tr>
				
				<tr>
						<th width="15%">行业分类<span style="color:red">*</span></th>
						<td width="35%"  colspan="3">
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
						</td>
					</tr>
				
				<tr>
					<th width="15%">企业特征</th>
					<td width="35%"><cus:SelectOneTag style="width:150px;" property="company.feature" defaultText='请选择' codeName="企业特征" value="${company.feature}" /></div>
					<th width="15%">企业类型</th>
					<td width="35%"><cus:SelectOneTag property="company.qylx" defaultText='请选择' codeName="企业类型" value="${company.qylx}" dataType="Require" msg="此项为必选" /></td>
					
					</tr>
				<tr>
					<th width="15%">安全管理员</th>
					<td width="35%"><input name="company.lxr"  size= 50  value="${company.lxr}" type="text" msg="此项为必填" maxlength="255"></td>
					<th width="15%">安全管理员手机号码</th>
					<td width="35%"><input id="mobile" name="company.mobile" value="${company.mobile}" size=50 type="text"  msg="此项为必填" maxlength="11"></td>
					<%-- <th width="15%">安全管理员固话</th>
					<td width="35%"><input id="lxfs" name="company.lxfs"  size= 50  value="${company.lxfs}" type="text" msg="此项必须填写" require="true" maxlength="255"><font style="color:red"></font></td>
				 --%></tr>
				<tr>
					<th width="15%">企业邮箱</th>
					<td width="35%"><input name="company.qyyx"  value="${company.qyyx}" size= 50 id="qyyx" type="text"  onKeyUp="value=value.replace(/^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi,'')"  msg="此项为必填"  maxlength="255"></td>
				<%-- 					<th width="15%">行业分类</th>
					<td width="35%"><cus:SelectOneTag property="company.hyfl" defaultText='请选择' codeName="企业行业分类" value="${company.hyfl}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
 --%>				</tr>
				<tr>
					<th colspan="4" style="text-align:center;color:red">企业所属监管类型（请至少选择一项）</th>
				</tr>
				
				<tr>
					<th width="15%">是否工贸企业</th>
					<td width="35%">			
					 	<input type="radio" name="file6"  id="file60" value="0" class="filetype">否
                     	<input type="radio" name="file6" id="file61"  value="1"  class="filetype">是
                    </td>
                    <td width="50%" colspan="2">
					 	<div id="iffmgmqylxid"><cus:hxcheckbox property="company.companyType" codeName="非煤工贸企业" value="${company.companyType}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></div>
					</td>
				</tr>
				<tr>
					<th width="15%">是否 危 化 品企业</th>
					<td width="35%">			
					 	<input type="radio" name="file"  id="file00" value="0" class="filetype">否
                     	<input type="radio" name="file" id="file01"  value="1"  class="filetype">是
                    </td>
                    <td width="50%" colspan="2">
					 	<div id="ifwhpqylxid"><cus:hxcheckbox property="company.companyType" codeName="危化品企业类型" value="${company.companyType}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></div>
					</td>
				</tr>
				<%-- <tr>
			    	<th width="15%" align="right">是否烟花爆竹企业</th>
					<td width="35%">
					 	<input type="radio" name="file3"  id="file30" value="0" class="filetype3">否
                     	<input type="radio" name="file3" id="file31"  checked="checked"  value="1"  class="filetype3">是<font style="color:red">*</font>
					</td>
					<td width="50%" colspan="2">
                     	<div id="ifyhbzjyqyid" ><cus:hxcheckbox property="company.companyType" codeName="烟花爆竹企业" value="${company.companyType}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></div>
					</td>
				</tr> --%>
				<tr>
					<th width="15%">是否非煤矿山企业</th>
					<td width="35%" colspan="3">
					 	<input type="radio" name="file4"  id="file40" value="0" class="filetype4">否
                     	<input type="radio" name="file4" id="file41"  checked="checked"  value="1"  class="filetype4">是
					</td>
				</tr>
				<tr class="iffmksjyqyid">
					<th width="15%">矿山类型</th>
					<td width="35%" id="diggingstype"><cus:SelectOneTag onchange="showDiv();" style="width:150px;"  property="company.companyType" defaultText='请选择' codeName="矿山类型" value="${company.companyType}" /><font color="red">*</font></td>
					<th width="15%">金属属性</th>
					<td width="35%"><cus:SelectOneTag style="width:150px;"  property="company.metal" defaultText='请选择' codeName="金属属性" value="${company.metal}" /><font color="red">*</font></td>
				</tr >
				<tr id="tffs"  class="iffmksjyqyid" >
					<th width="15%">通风方式</th>
					<td width="35%"><cus:SelectOneTag style="width:150px;"  property="company.ventilate" defaultText='请选择' codeName="通风方式" value="${company.ventilate}" /><font color="red">*</font></td>
					<th width="15%">运输方式</th>
					<td width="35%"><cus:SelectOneTag style="width:150px;"  property="company.transport" defaultText='请选择' codeName="运输方式" value="${company.transport}" /><font color="red">*</font></td>
				</tr>
				<tr id="tsfs"  class="iffmksjyqyid" >
					<th width="15%">提升方式</th>
					<td width="35%"><cus:SelectOneTag style="width:150px;"  property="company.raisetype" defaultText='请选择' codeName="提升方式" value="${company.raisetype}" /><font color="red">*</font></td>
					<th width="15%">六大系统情况</th>
					<td width="35%"><textarea id="sixsys" class="changeStyle" dataType="Require" msg="此项为必填" name="company.sixsys" rows="5" style="width: 50%">${company.sixsys}</textarea><font color="red">*</font></td>
				</tr>
				<tr >
				   <th width="100%" colspan="4" style="text-align:center;color:red">基本信息</th>
				</tr>
				
				<tr>
			    	<th width="15%">是否职业危害企业</th>
					<td width="35%">
					 	<input type="radio" name="file1"  id="file10" value="0" class="filetype1">否
                     	<input type="radio" name="file1" id="file11"  value="1"  class="filetype1">是
                    </td>
                    <td width="50%" colspan="2">
                     	<div id="ifzywhqylxid" ><cus:hxcheckbox property="company.companyType" codeName="职业危害企业类型" value="${company.companyType}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></div>
					</td>
				</tr>
				<tr>
					<th width="15%">企业规模</th>
					<td width="35%"><cus:SelectOneTag property="company.qygm" defaultText='请选择' codeName="企业规模" value="${company.qygm}"  msg="此项为必选" /><font style="color:red">*</font></td>
					<th width="15%">企业成立时间</th>
					<td width="35%"><input name="company.qyclsj" value="<fmt:formatDate type='date' value='${company.qyclsj}' />"  msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
				</tr>
				<tr>
					<th width="15%">企业注册类型</th>
					<td width="35%"><cus:SelectOneTag property="company.qyzclx" defaultText='请选择' codeName="注册类型" value="${company.qyzclx}" msg="此项为必选" /><font style="color:red"></font></td>
					<th width="15%">注册资金</th>
					<td width="35%"><input name="company.zczj" value="${company.zczj}"  onKeyUp="clearNoNum(event,this)" msg="此项为必选" type="text" maxlength="255">（万元）<font style="color:red"></font></td>
				</tr>
				
				<tr>
					<th width="15%">固定电话</th>
					<td width="35%"><input id="gddh" name="company.gddh" value="${company.gddh}"  msg="此项为必填" type="text" maxlength="255"><font style="color:red"></font></td>
					<th width="15%">传       真</th>
					<td width="35%"><input name="company.cz" value="${company.cz}"  msg="此项为必填" type="text" maxlength="255"><font style="color:red"></font></td>
				</tr>
				<tr>
					<th width="15%">邮政编码</th>
					<td width="35%"><input name="company.yzbm" value="${company.yzbm}" onKeyDown="onlyNum()"  onKeyUp="validate(event,obj)" type="text"  msg="此项为必填" checkregexp="\d{6}" maxlength="255"><font style="color:red"></font></td>
					<th width="15%">上年销售收入</th>
					<td width="35%"><input name="company.snxssr" value="${company.snxssr}" onKeyUp="clearNoNum(event,this)"   msg="此项为必填" type="text" maxlength="255">（万元）<font style="color:red"></font></td>
				</tr>
				<tr>
					<th width="15%">上年上交税收</th>
					<td width="35%"><input name="company.snsjss" value="${company.snsjss}"  onKeyUp="clearNoNum(event,this)"  msg="此项为必填" type="text" maxlength="255">（万元）<font style="color:red"></font></td>
					<th width="15%">上年固定资产</th>
					<td width="35%"><input name="company.sngdzc" value="${company.sngdzc}" onKeyUp="clearNoNum(event,this)"  msg="此项为必填" type="text" maxlength="255">（万元）<font style="color:red"></font></td>
				</tr>
				<tr>
					<th width="15%">上年安全投入</th>
					<td width="35%"><input name="company.snwqtr" value="${company.snwqtr}" onKeyUp="clearNoNum(event,this)"  msg="此项为必填" type="text" maxlength="255">（万元）<font style="color:red"></font></td>
					<th width="15%">上年安全生产费用提取数</th>
					<td width="35%"><input name="company.snaqscf" value="${company.snaqscf}"  onKeyUp="clearNoNum(event,this)"  msg="此项为必填" type="text" maxlength="255">（万元）<font style="color:red"></font></td>
				</tr>
				<tr>
					<th width="15%">是否设立安全管理机构</th>
					<td width="35%"><cus:hxradio property="company.sfaqjg" codeName="是或否" value="${company.sfaqjg}" dataType="Require" msg="此项为必选"/></td>
					<th width="15%">安全管理员</th>
					<td width="35%"><input name="company.aqglr" value="${company.aqglr}" onKeyDown="onlyNum()"  onKeyUp="validate(event,obj)" type="text" checkregexp="^\d*$" maxlength="255">（人）</td>
				</tr>
				<tr>
					<th width="15%">是否设立职业卫生管理机构</th>
					<td width="35%"><cus:hxradio property="company.sfzywsjg" codeName="是或否" value="${company.sfzywsjg}" dataType="Require" msg="此项为必选"/></td>
					<th width="15%">职业卫生管理人员</th>
					<td width="35%"><input name="company.zywsglry" value="${company.zywsglry}" onKeyDown="onlyNum()"  onKeyUp="validate(event,obj)" type="text"  checkregexp="^\d*$" maxlength="255">（人）</td>
				</tr>
				<tr>
					<th width="15%">是否为专职或兼职职业卫生管理员</th>
					<td width="35%"><cus:hxradio property="company.sfqzwsgly" codeName="是或否" value="${company.sfqzwsgly}" dataType="Require" msg="此项为必选"/></td>
					<th width="15%">占地面积</th>
					<td width="35%"><input name="company.zdmj" value="${company.zdmj}"  onKeyUp="clearNoNum(event,this)" type="text"  msg="此项为必填" require="false" maxlength="255">（m2）<font style="color:red"></font></td>
				</tr>
				<tr>
					<th width="15%">建筑面积</th>
					<td width="35%"><input name="company.jzmj" value="${company.jzmj}"  onKeyDown="onlyNum()" onKeyUp="clearNoNum(event,this)" type="text"  msg="此项为必填" require="false" maxlength="255">（m2）<font style="color:red"></font></td>
					<th width="15%">从业人员</th>
					<td width="35%"><input name="company.cyry" value="${company.cyry}" type="text" onKeyDown="onlyNum()" onKeyUp="validate(event,this)"  msg="此项为必填" require="false" checkregexp="^\d*$" maxlength="255">（人）<font style="color:red"></font></td>
				</tr>
				<tr>
					<th width="15%">是否有员工宿舍</th>
					<td width="35%"><cus:hxradio property="company.sfyygss" codeName="是或否" value="${company.sfyygss}" dataType="Require" msg="此项为必选"/></td>
					<th width="15%">安全生产标准化达标级别</th>
					<td width="35%"><cus:SelectOneTag property="company.aqbzdbjb" codeName="标准化达标级别" value="${company.aqbzdbjb}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="85%" colspan="3">
						<textarea id="company.jyfw" dataType="Require" msg="此项为必选"
							name="company.jyfw" rows="5"
							style="width: 80%">${company.jyfw}</textarea><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<th width="15%">网址</th>
					<!-- <td width="35%">
						<input type="radio" name="file2"  id="file20" value="0" class="filetype2">无
                     	<input type="radio" name="file2" id="file21" value="1"  class="filetype2">有<font style="color:red">*</font>
                     </td> -->
                    <td width="50%" colspan="2">
						<div id="ifurlid"><input name="company.url"  value="${company.url}"  id="url" type="text" style="width:80%"></div>
						</div>
					</td>
				</tr>
					<!--<th width="15%" style="color:red">基本信息审核状态</th>
					<td width="35%" style="color:red"><cus:hxlabel codeName="基本信息审核状态" itemValue="${company.basePass}" /></td>
					-->
					<%-- <c:if test="${ifzsqy==0}">
					<tr>
						<th width="15%" style="color:red"></th>
						<td width="100%" colspan="4">
							<font style="color:red">县级审核:</font>
							<c:if test="${xjshState==1}">
								&nbsp;&nbsp;&nbsp;通过
							</c:if>
							<c:if test="${xjshState==2}">
								&nbsp;&nbsp;&nbsp;未通过
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${xjBack}
						</td>
					</tr>
					</c:if> --%>
					<tr>
						<th width="15%" style="color:red">审核状态:</th>
						<td width="35%">
							<c:if test="${company.basePass==1}">
								&nbsp;&nbsp;&nbsp;通过
							</c:if>
							<c:if test="${company.basePass==2}">
								&nbsp;&nbsp;&nbsp;未通过
							</c:if>
							<c:if test="${company.basePass==0}">
								&nbsp;&nbsp;&nbsp;待审核
							</c:if>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<font style="color:red">备注:</font>&nbsp;&nbsp;&nbsp;${company.baseRemark}
						</td>
						<%-- <th width="15%" style="color:red">审核历史：</th>
						<c:forEach var="item" items="${reviewList}">
							<td width="35%">
								<c:if test="${item.state==1}"> 
									<span style="color:green;">${item.userName }</span>于${item.createTime }进行审核 ,审核结果:<span style="color:green;">通过</span>
								</c:if> 
								<c:if test="${item.state==2}"> 
									<span style="color:green;">${item.userName }</span>于${item.createTime }进行审核 ,审核结果:<span style="color:red;">不通过</span>
								</c:if>
							</td>
						</c:forEach> --%>
					</tr>
				
				<!-- 
				<s:if test="company.basePass==2" >
					<tr>
						<th width="15%">备注</th>
						<td width="65%" colspan=3>
							<textarea id="baseRemark" disabled
								name="company.baseRemark" rows="5"
								style="width: 80%">${company.baseRemark}</textarea>
						</td>
					</tr>
				</s:if>
				 -->
				 <p>&nbsp;</p>
				 <tr>
				 	<th width="15%" style="color:red">审核历史:</th>
					<td width="85%" colspan="3">
					 	<c:forEach var="item" items="${reviewList}">
							<c:if test="${item.state==1}"> 
								<p><span style="color:green;">${item.userName }</span>于${item.createTime }进行审核 ,审核结果:<span style="color:green;">通过</span></p>
							</c:if> 
							<c:if test="${item.state==2}"> 
								<p><span style="color:green;">${item.userName }</span>于${item.createTime }进行审核 ,审核结果:<span style="color:red;">不通过</span></p>
							</c:if>
						</c:forEach>
					</td>
				 </tr>
				<%--  <ul>
					<span style="color:red">审核历史</span>
					<c:forEach var="item" items="${reviewList}">
						<li width="35%">
							<c:if test="${item.state==1}"> 
								<span style="color:green;">${item.userName }</span>于${item.createTime }进行审核 ,审核结果:<span style="color:green;">通过</span>
							</c:if> 
							<c:if test="${item.state==2}"> 
								<span style="color:green;">${item.userName }</span>于${item.createTime }进行审核 ,审核结果:<span style="color:red;">不通过</span>
							</c:if>
						</li>
					</c:forEach>
				</ul> --%>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="company.basePass==0">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">上报</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">保存上报</a>&nbsp;
						</s:else>	
					</td>
				</tr>
			</table>
			
		</div>
	</s:form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
