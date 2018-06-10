<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=XA9GjGiTQiDS54abo0ga2iEd"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
			var map;
			var overlays = [];
    		$(function(){
    			if("${flag}"=="1"){
    				layer.msg('添加成功');
    			}
    			// 百度地图API功能
				map = new BMap.Map("allmap");
				map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
				map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    			var longitude ="${wzcompany.longitude}";
    			var latitude = "${wzcompany.latitude}";
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
		function save(){
			if($('#ifwhpqylx').val()==1)
			{
				var ches = document.getElementsByName("wzcompany.whpqylx");
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
			if($('#ifzywhqylx').val()==1)
	   	 	{
				var ches = document.getElementsByName("wzcompany.zywhqylx");
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
					alert("职业危害企业类型至少选择一项");
					return false;
				}
			}
			var longitude = document.getElementById('longitude').value;
			var latitude =  document.getElementById('latitude').value;
			if(longitude == null || longitude == "" || latitude == null || latitude == "")
			{
				alert("请在地图上标注企业位置");
            	return false; 
			}
			if(Validator.Validate(document.myform1,3)){
				var county = document.getElementById("county");
				var countyName = county.options[county.selectedIndex].text;
				document.getElementById('countyName').value = countyName;
				var szzid = document.getElementById("szzid");
				var szzname = szzid.options[szzid.selectedIndex].text;
				document.getElementById('szzname').value = szzname;
				document.myform1.action="wzcompanySave.action";
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
	  					var tempszz = "${wzcompany.szzid}";//需要修改
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
		<input type="hidden" name="wzcompany.id" value="${wzcompany.id}">
		<input type="hidden" name="wzcompany.createTime" value="<fmt:formatDate type="both" value="${wzcompany.createTime}" />">
		<input type="hidden" name="wzcompany.updateTime" value="${wzcompany.updateTime}">
		<input type="hidden" name="wzcompany.createUserID" value="${wzcompany.createUserID}">
		<input type="hidden" name="wzcompany.updateUserID" value="${wzcompany.updateUserID}">
		<input type="hidden" name="wzcompany.deptId" value="${wzcompany.deptId}">
		<input type="hidden" name="wzcompany.delFlag" value="${wzcompany.delFlag}">
		<input type="hidden" name="wzcompany.ifwhpqylx" id="ifwhpqylx" value="${wzcompany.ifwhpqylx}">
		<input type="hidden" name="wzcompany.ifzywhqylx" id="ifzywhqylx" value="${wzcompany.ifzywhqylx}">
		<input type="hidden" name="wzcompany.szcname" id="szcname" value="${wzcompany.szcname}">
		<input type="hidden" name="wzcompany.szzname" id="szzname" value="${wzcompany.szzname}">
		<input type="hidden" name="wzcompany.countyName" id="countyName" value="${wzcompany.countyName}">
		<input id="longitude" name="wzcompany.longitude" value="${wzcompany.longitude}" type="hidden">
		<input id="latitude" name="wzcompany.latitude" value="${wzcompany.latitude}"   type="hidden">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">企业名称</th>
					<td width="85%" colspan="3"><input name="wzcompany.companyname" value="${wzcompany.companyname}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>				
				</tr>
				<tr>
					<th width="15%">所在镇</th>
					<td width="35%"  colspan="3">
						<cus:SelectOneTag property="wzcompany.county" defaultText='请选择' codeName="地址" value="${wzcompany.county}" dataType="Require" msg="此项为必选" maxlength="255" onchange="querySzz(this.value);"/>
						<select id="szzid" name="wzcompany.szzid" style="width:100px;" dataType="Require" msg="此项为必选"><option value="">请选择</option></select>
						<font color="red">*</font>
						
						<!-- hanxc 2014/11/11 
						<cus:SelectOneTag property="wzcompany.szzid" defaultText='请选择' codeName="相城地址" value="${wzcompany.szzid}" dataType="Require" msg="此项为必选" onchange="querySzc(this.value);"/>
						<font color="red">*</font>
						 -->
					</td>
					<!-- 
					<th width="15%">所在村</th>
					<td width="35%">
						<s:select theme="simple"  id="szc" name="wzcompany.szc" list="%{deptlist}" listKey="deptCode" listValue="deptName" dataType="Require" msg="此项为必选" value="{wzcompany.szc}"></s:select><font color="red">*</font>
					</td>
					 -->
				</tr>
				<tr>
				 	<th width="15%">负责人</th>
					<td width="35%"><input name="wzcompany.fzr" value="${wzcompany.fzr}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
					<th width="15%">手机</th>
					<td width="35%"><input id="mobile" name="wzcompany.mobile" value="${wzcompany.mobile}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">传       真</th>
					<td width="35%"><input name="wzcompany.cz" value="${wzcompany.cz}" type="text" maxlength="255"></td>
					<th width="15%">身份证号码</th>
					<td width="35%"><input name="wzcompany.sfz" value="${wzcompany.sfz}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:SelectOneTag property="wzcompany.hyfl" defaultText='请选择' codeName="企业行业分类" value="${wzcompany.hyfl}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
					<th width="15%">是否为五小企业</th>
					<td width="35%"><cus:SelectOneTag property="wzcompany.ifwxqy" defaultText='请选择' codeName="是或否" value="${wzcompany.ifwxqy}" dataType="Require" msg="此项为必选"/><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">是否危化品企业类型</th>
					<td width="35%">			
					 	<input type="radio" name="file"  id="file00" value="0" class="filetype">否
                     	<input type="radio" name="file" id="file01"  value="1"  class="filetype">是 <font style="color:red">*</font>
                    </td>
                    <td width="50%" colspan="2">
					 	<div id="ifwhpqylxid"><cus:hxcheckbox property="wzcompany.whpqylx" codeName="危化品企业类型" value="${wzcompany.whpqylx}" dataType="Require" msg="此项为必选"/></div>
					</td>
				</tr>
				<tr>
			    	<th width="15%">是否职业危害企业类型</th>
					<td width="35%">
					 	<input type="radio" name="file1"  id="file10" value="0" class="filetype1">否
                     	<input type="radio" name="file1" id="file11"  value="1"  class="filetype1">是<font style="color:red">*</font>
                    </td>
                    <td width="50%" colspan="2">
                     	<div id="ifzywhqylxid" ><cus:hxcheckbox property="wzcompany.zywhqylx" codeName="职业危害企业类型" value="${wzcompany.zywhqylx}" dataType="Require" msg="此项为必选"/></div>
					</td>
				</tr>
				<tr>
					<th width="15%">员工数</th>
					<td width="35%"><input name="wzcompany.ygs" value="${wzcompany.ygs}" type="text" onKeyDown="onlyNum()" onKeyUp="validate(event,this)" dataType="Require" msg="此项为必填" maxlength="255">（人）<font style="color:red">*</font></td>
					<th width="15%">年销售收入</th>
					<td width="35%"><input name="wzcompany.nxssr" value="${wzcompany.nxssr}" onKeyDown="onlyNum()" onKeyUp="clearNoNum(event,this)"  dataType="Require" msg="此项为必填" type="text" maxlength="255">（万元）<font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">占地面积</th>
					<td width="35%"><input name="wzcompany.zdmj" value="${wzcompany.zdmj}"  onKeyDown="onlyNum()" onKeyUp="clearNoNum(event,this)" type="text" dataType="Require" msg="此项为必填" maxlength="255">（m2）<font style="color:red">*</font></td>
					<th width="15%">建筑面积</th>
					<td width="35%"><input name="wzcompany.jzmj" value="${wzcompany.jzmj}"  onKeyDown="onlyNum()" onKeyUp="clearNoNum(event,this)" type="text" dataType="Require" msg="此项为必填" maxlength="255">（m2）<font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">经营场所性质</th>
					<td width="35%"><cus:SelectOneTag property="wzcompany.jycsxz" defaultText='请选择' codeName="经营场所性质" value="${wzcompany.jycsxz}" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
				 	<th width="15%">房东姓名</th>
					<td width="35%"><input name="wzcompany.fdxm" value="${wzcompany.fdxm}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">房东手机</th>
					<td width="35%"><input id="fdsjh" name="wzcompany.fdsjh" value="${wzcompany.fdsjh}" type="text" dataType="Require" msg="此项为必填" maxlength="255"><font style="color:red">*</font></td>
					<th width="15%">房东身份证号码</th>
					<td width="35%"><input name="wzcompany.fdsfz" value="${wzcompany.fdsfz}" dataType="Require" msg="此项为必填" type="text" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
					<td width="85%" colspan="3">
						<textarea id="wzcompany.jyfw" dataType="Require" msg="此项为必填" name="wzcompany.jyfw" rows="5" style="width: 80%">${wzcompany.jyfw}</textarea><font style="color:red">*</font>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<div id="allmap" style="height:300px;"></div>
					</td>
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
