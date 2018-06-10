<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE html>
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<style type="text/css">
		.layui-upload-img{width: 192px;
		    height: 192px;
		    margin: 0 10px 10px 0;}
	</style>
	<script>
		var yhdl1 = "${qyzcyhglb1.yhdl}";
		var yhzl1 = "${qyzcyhglb1.yhzl}";
		var yhdl2 = "${qyzcyhglb2.yhdl}";
		var yhzl2 = "${qyzcyhglb2.yhzl}";
		var yhdl3 = "${qyzcyhglb3.yhdl}";
		var yhzl3 = "${qyzcyhglb3.yhzl}";
		var yhdl4 = "${qyzcyhglb4.yhdl}";
		var yhzl4 = "${qyzcyhglb4.yhzl}";
		var yhdl5 = "${qyzcyhglb5.yhdl}";
		var yhzl5 = "${qyzcyhglb5.yhzl}";
		
		var jb = "${jshxZcyhsb.jb}";
		var mqzt = "${jshxZcyhsb.mqzt}";
		var a = new Array(' ','资质证照','安全生产管理机构及人员','安全生产责任制',
				'安全生产管理制度','安全操作规程','教育培训','安全生产管理档案','安全生产投入',
				'应急管理','特征设备基础管理','职业卫生基础管理','相关方基础管理','其他基础管理');//14
		var b = new Array(' ','特种设备现场管理','生产设备设施及工艺','场所环境',
				'从业人员操作行为','消防安全','用电安全','职业卫生现场安全','有限空间现场安全',
				'辅助动力系统','相关方现场管理','其他现场管理');//12
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
   				layer.alert('更新失败');
   			}
		});
		function initLB(){
			var title11 = document.getElementById('yhdl1');   
			for(var i=0;i<title11.options.length;i++){
			   if(title11.options[i].value == yhdl1){   
			    title11.options[i].selected = true;   
			    break;   
			    }   
			}
			changeZL(yhdl1,'1');
			var title12 = document.getElementById('yhdl2');   
			for(var i=0;i<title12.options.length;i++){
			   if(title12.options[i].value == yhdl2){   
			    title12.options[i].selected = true;   
			    break;   
			    }   
			}
			changeZL(yhdl2,'2');
			var title13 = document.getElementById('yhdl3');   
			for(var i=0;i<title13.options.length;i++){
			   if(title13.options[i].value == yhdl3){   
			    title13.options[i].selected = true;   
			    break;   
			    }   
			}
			changeZL(yhdl3,'3');
			var title14 = document.getElementById('yhdl4');   
			for(var i=0;i<title14.options.length;i++){
			   if(title14.options[i].value == yhdl4){   
			    title14.options[i].selected = true;   
			    break;   
			    }   
			}
			changeZL(yhdl4,'4');
			var title15 = document.getElementById('yhdl5');   
			for(var i=0;i<title15.options.length;i++){
			   if(title15.options[i].value == yhdl5){   
			    title15.options[i].selected = true;   
			    break;   
			    }   
			}
			changeZL(yhdl5,'5');
			var title21 = document.getElementById('yhzl1');   
			for(var i=0;i<title21.options.length;i++){
			   if(title21.options[i].value == yhzl1){   
			    title21.options[i].selected = true;   
			    break;   
			    }   
			} 
			var title22 = document.getElementById('yhzl2');   
			for(var i=0;i<title22.options.length;i++){
			   if(title22.options[i].value == yhzl2){   
			    title22.options[i].selected = true;   
			    break;   
			    }   
			} 
			var title23 = document.getElementById('yhzl3');   
			for(var i=0;i<title23.options.length;i++){
			   if(title23.options[i].value == yhzl3){   
			    title23.options[i].selected = true;   
			    break;   
			    }   
			} 
			var title24 = document.getElementById('yhzl4');   
			for(var i=0;i<title24.options.length;i++){
			   if(title24.options[i].value == yhzl4){   
			    title24.options[i].selected = true;   
			    break;   
			    }   
			} 
			var title25 = document.getElementById('yhzl5');   
			for(var i=0;i<title25.options.length;i++){
			   if(title25.options[i].value == yhzl5){   
			    title25.options[i].selected = true;   
			    break;   
			    }   
			} 
			var title2 = document.getElementById('zgzt');   
			for(var i=0;i<title2.options.length;i++){
			   if(title2.options[i].value == mqzt){   
			    title2.options[i].selected = true;   
			    break;   
			    }   
			}
			changeZT(mqzt);
			var title3 = document.getElementById('jb');   
			for(var i=0;i<title3.options.length;i++){
			   if(title3.options[i].value == jb){   
			    title3.options[i].selected = true;   
			    break;   
			    }   
			}
			var yhsl = "${jshxZcyhsb.yhsl}";
			if(yhsl != 0)
			{
				for(var i=1;i<parseInt(yhsl)+1;i++)
        		{
        			document.getElementById("more"+i).style.display="";
        		}
			}
			else
			{
				document.getElementById("more0").style.display="";
				$(".hasyinhuan").attr("style","display:none;");
			}
		}
		function save(){
			if(Validator.Validate(document.myform1,3)){

				var yhsl = $("#yhsl").val();
				if(yhsl == null || yhsl == 'null' || yhsl == '')
				{
					layer.alert('请选择隐患数量');
					return;
				}
				else 
				{
					for(var i=1;i<parseInt(yhsl)+1;i++)
					{
						var yhdl = document.getElementById("yhdl"+i).value;
						var yhzl = document.getElementById("yhzl"+i).value;
						if(yhdl == '0'|| yhdl == 'null'||yhdl == null){
							layer.alert('请选择隐患'+i+'隐患大类');
							return;
						}
						if(yhzl == '0'|| yhzl == 'null'||yhzl == null|| yhzl == '1_0'||yhzl == '2_0'){
							layer.alert('请选择隐患' + i + '隐患中类');
							return;
						}
					}
				}
				var zgzt = $("#zgzt").val();
				var fcr = $("#fcr").val();
				var fcsj = $("#fcsj").val();
				var fcysqk = $("#fcysqk").val();
				if(zgzt=='0'|| zgzt == 'null'||zgzt == null){
					if(fcr == ''|| fcr == 'null'||fcr == null){
						layer.alert('请输入复查人');
						return;
					}
					if(fcsj == ''|| fcsj == 'null'||fcsj == null){
						layer.alert('请选择复查时间');
						return;
					}
					if(fcysqk == ''|| fcysqk == 'null'||fcysqk == null){
						layer.alert('请输入复查验收情况');
						return;
					}
				}
				document.myform1.action="jshxZcyhsbSave.action";
				document.myform1.submit();
			}
		}
		function changeZL(obj,type){
				var selectContainer = $('#yhzl'+type); 
				 selectContainer.empty();
				 if(obj == '0'){
				 	for(var i=0; i<1; i++){
							var option = $('<option></option>').text("").val("0"); 
							selectContainer.append(option); 
						 }
				 }else if(obj == '1'){
				 	for(var i=0; i<14; i++){
							var option = $('<option></option>').text(a[i]).val("1_"+i);
							
							selectContainer.append(option); 
			 			}
				 }else if(obj == '2'){
				 	for(var i=0; i<12; i++){
						var option = $('<option></option>').text(b[i]).val("2_"+i); 
						selectContainer.append(option); 
			 		}
				 }
		}
		function changeZT(obj){
		
			if(obj == '1'){
				//document.getElementById("zt_0").style.display="none";
				document.getElementById("zt_1").style.display="none";
			}else{
				document.getElementById("zt_0").style.display="";
				document.getElementById("zt_1").style.display="";
			}
		}
		 //图片上传 lj 2013-08-31
        function uploadPhoto(obj){
        	window.open("jshxZcyhsbUpload.action?jshxZcyhsb.linkId=${jshxZcyhsb.linkId}&flag="+obj);
        	}
         function savepic(i,j){
        		window.location.href="jshxZcyhsbSaveFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "jshxZcyhsbImageDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
        }
        
        function showDiv(obj)
        {	
        	if(obj == '')
        	{
        		layer.msg('请选择隐患数量', {icon: 0});
				return;
        	}
        	else 
        	{
        		if(obj == 0)
        		{
        			document.getElementById("more0").style.display="";
        			$(".hasyinhuan").attr("style","display:none;");
        		}
        		else
        		{
        			document.getElementById("more0").style.display="none";
        			for(var i=1;i<parseInt(obj)+1;i++)
        			{
        				//document.getElementById("more"+i).style.display="";
        				$("#more"+i).attr("style","display:block;");
        			}
        			$(".hasyinhuan").attr("style","display:table-row;");
        		}
        		for(var i=parseInt(obj)+1;i<6;i++)
        		{
        			document.getElementById("more"+i).style.display="none";
        		}
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
        layui.use('upload', function(){
			  var $ = layui.jquery
			  ,upload = layui.upload;
		        var linkId = $("#linkId").val();
		        
			  
			//多图片上传
			  upload.render({
			    elem: '#test1_1'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo1_1').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			
			//多图片上传
			  upload.render({
			    elem: '#test1_2'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo1_2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			//多图片上传
			  upload.render({
			    elem: '#test2_1'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo2_1').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			
			//多图片上传
			  upload.render({
			    elem: '#test2_2'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo2_2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			//多图片上传
			  upload.render({
			    elem: '#test3_1'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo3_1').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			
			//多图片上传
			  upload.render({
			    elem: '#test3_2'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo3_2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			//多图片上传
			  upload.render({
			    elem: '#test4_1'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo4_1').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			
			//多图片上传
			  upload.render({
			    elem: '#test4_2'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo4_2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			//多图片上传
			  upload.render({
			    elem: '#test5_1'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo5_1').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			
			//多图片上传
			  upload.render({
			    elem: '#test5_2'
			    ,url: '${ctx}/jsp/zcyhsb/jshxZcyhsbSavePhoto.action'
			    ,multiple: true
			    ,data:{linkid: linkId}
			    ,before: function(obj){
			      //预读本地文件示例，不支持ie8
			      obj.preview(function(index, file, result){
			        $('#demo5_2').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
			      });
			    }
			    ,done: function(res){
			      //上传完毕
			    }
			  });
			
		});
        
        layui.use('flow', function(){
        	  var flow = layui.flow;
        	//按屏加载图片
        	  flow.lazyimg({
        	    elem: '#LAY_demo11 img'
        	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
        	  });
        	  flow.lazyimg({
          	    elem: '#LAY_demo21 img'
          	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
          	  });
        	  flow.lazyimg({
          	    elem: '#LAY_demo31 img'
          	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
          	  });
          	  flow.lazyimg({
            	    elem: '#LAY_demo41 img'
            	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
            	  });
          	  flow.lazyimg({
          	    elem: '#LAY_demo51 img'
          	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
          	  });
          	  flow.lazyimg({
            	    elem: '#LAY_demo12 img'
            	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
            	  });
          	  flow.lazyimg({
          	    elem: '#LAY_demo22 img'
          	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
          	  });
          	  flow.lazyimg({
            	    elem: '#LAY_demo32 img'
            	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
            	  });
          	  flow.lazyimg({
          	    elem: '#LAY_demo42 img'
          	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
          	  });
          	  flow.lazyimg({
            	    elem: '#LAY_demo52 img'
            	    ,scrollElem: '#LAY_demo3' //一般不用设置，此处只是演示需要。
            	  });
        });
	</script>
	
</head>
<body onload="initLB();">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" id="type" name="type" value="${type}">
		<input type="hidden" id="linkId" name="jshxZcyhsb.linkId" value="${jshxZcyhsb.linkId}"/>
		<input type="hidden" name="jshxZcyhsb.id" value="${jshxZcyhsb.id}">
		<input type="hidden" name="jshxZcyhsb.createTime" value="<fmt:formatDate type="both" value="${jshxZcyhsb.createTime}" />">
		<input type="hidden" name="jshxZcyhsb.updateTime" value="${jshxZcyhsb.updateTime}">
		<input type="hidden" name="jshxZcyhsb.createUserID" value="${jshxZcyhsb.createUserID}">
		<input type="hidden" name="jshxZcyhsb.updateUserID" value="${jshxZcyhsb.updateUserID}">
		<input type="hidden" name="jshxZcyhsb.deptId" value="${jshxZcyhsb.deptId}">
		<input type="hidden" name="jshxZcyhsb.delFlag" value="${jshxZcyhsb.delFlag}">
		
		<input type="hidden" name="jshxZcyhsb.szzname" value="${jshxZcyhsb.szzname}">
		<input type="hidden" name="jshxZcyhsb.qymc" value="${jshxZcyhsb.qymc}">
		<input type="hidden" name="jshxZcyhsb.szzid" value="${jshxZcyhsb.szzid}">
		<input type="hidden" name="jshxZcyhsb.qyid" value="${jshxZcyhsb.qyid}">
		<input type="hidden" name="jshxZcyhsb.qylx" value="${jshxZcyhsb.qylx}">
		<input type="hidden" name="jshxZcyhsb.hyfl" value="${jshxZcyhsb.hyfl}">
		<input type="hidden" name="jshxZcyhsb.qygm" value="${jshxZcyhsb.qygm}">
		<input type="hidden" name="jshxZcyhsb.qyzclx" value="${jshxZcyhsb.qyzclx}">
		
		<input type="hidden" name="jshxZcyhsb.ifwhpqylx" value="${jshxZcyhsb.ifwhpqylx}">
		<input type="hidden" name="jshxZcyhsb.ifzywhqylx" value="${jshxZcyhsb.ifzywhqylx}">
		<input type="hidden" name="jshxZcyhsb.ifyhbzjyqy" value="${jshxZcyhsb.ifyhbzjyqy}">
		<input type="hidden" name="jshxZcyhsb.szc" value="${jshxZcyhsb.szc}">
		<input type="hidden" name="jshxZcyhsb.szcname" value="${jshxZcyhsb.szcname}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">所属企业</th>
					<td width="35%">${jshxZcyhsb.qymc}</td>
					<%-- <th width="15%">检查类别</th>
					<td width="35%">
						<s:radio id="jclb"  list="#{'0':'企业自查','1':'专家检查','2':'企业互评互查'}"  theme="simple" name="jshxZcyhsb.jclb" value="#{jshxZcyhsb.jclb}"/>
					</td> --%>
				</tr>
				<tr>
					<th width="15%">检查时间</th>
					<td width="35%"><input name="jshxZcyhsb.jcsj" dataType="Require" 
						msg="检查时间不能为空" value="<fmt:formatDate type='date' value='${jshxZcyhsb.jcsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style="color:red">*</font></td>
					<th width="15%">检查人员</th>
					<td width="35%"><input name="jshxZcyhsb.jcry" dataType="Require" 
						msg="检查人员不能为空" value="${jshxZcyhsb.jcry}" type="text" maxlength="255"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">隐患数量</th>
					<td width="35%"><cus:SelectOneTag property="jshxZcyhsb.yhsl" defaultText='请选择' codeName="隐患数量" value="${jshxZcyhsb.yhsl}" onchange="showDiv(this.value)"/></td>
					<td colspan="2">
						<div id="more0" class="submitdata" style="display:none">
							<table>
								<tr>
									<td><s:select id="yy" listKey="key" listValue="value"  theme="simple" list="#{'0':'本月全厂停产','1':'本月未查到安全隐患'}" name="jshxZcyhsb.yy" value="{jshxZcyhsb.yy}"/></td>
								</tr>
							</table>
						<div>
					</td>
				</tr>
				<tr>
					<td colspan="12">
						<div id="more1" class="submitdata" style="display:none">
							<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
							  <legend>隐患一</legend>
							</fieldset>
							<table style="width: 100%;">
								<tr>
									<th width="15%">隐患一被检查部位</th>
									<td width="35%"><input id="jcbw1" name="qyzcyhglb1.jcbw" value="${qyzcyhglb1.jcbw}" type="text" maxlength="255"></td>
									<th width="15%">隐患一整改完成时间</th>
									<td width="35%"><input id="yhzgwcsj1" name="qyzcyhglb1.yhzgwcsj" value="${qyzcyhglb1.yhzgwcsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
								</tr>
								<tr>
									<th width="15%">隐患一隐患大类</th>
									<td width="35%">
										<s:select id="yhdl1" listKey="key" listValue="value"  theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb1.yhdl" value="#{qyzcyhglb1.yhdl}" onchange="changeZL(this.value,'1');"/>
										<font style="color:red">*</font>
									</td>
									<th width="15%">隐患一隐患中类</th>
									<td width="35%">
										<select id="yhzl1" name="qyzcyhglb1.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
										<font style="color:red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患一整改责任部门</th>
									<td width="35%"><input name="qyzcyhglb1.zgzrbm" value="${qyzcyhglb1.zgzrbm}" type="text" maxlength="255"></td>
									<th width="15%">隐患一整改责任人</th>
									<td width="35%"><input name="qyzcyhglb1.zgzrr" value="${qyzcyhglb1.zgzrr}" type="text" maxlength="255"></td>
								</tr>
								<tr>
									<th width="15%">隐患一整改前图片</th>
									<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test1_1"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zgqtp1'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo1_1"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo11">
													<c:forEach var="item" items="${picList11}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
												<!-- <center><a style="color:blue;" href="javascript:uploadPhoto('zgqtp1')" title="点击上传附件">选择整改前图片</a></center> -->
											</td>
										</tr>
										<%-- <tr>
											<td>
												<div style="color:green;overflow:auto;height:178px;">
									  				 <div>
										  				<table id="zgqtp1">
															<c:forEach var="item" items="${picList11}">
																<tr id='${item.id}' style="text-align: center">
																	<td width="70%">
																		<c:choose>
																    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    			&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
																				border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
																    		</c:when> 
																    		<c:otherwise> 
																     			&nbsp;&nbsp;&nbsp;${item.fileName}
																    		</c:otherwise>
															  		 	</c:choose>
																	</td>
																	<td width="30%">
																		<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																		<a href="javascript:del('${item.id}');">删除</a>
																	</td>
																</tr>
															</c:forEach>
														</table>
													</div>
												</div>
											</td>
										</tr> --%>
									</table>
								</td>
							</tr>
							
							
							
							<tr>
								<th width="15%">隐患一整改后图片</th>
								<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test1_2"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zghtp1'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo1_2"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo21">
													<c:forEach var="item" items="${picList21}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
												<!-- <center><a style="color:blue;" href="javascript:uploadPhoto('zghtp1')" title="点击上传附件">选择整改后图片</a></center> -->
											</td>
										</tr>
										<%-- <tr>
											<td>
												<div style="color:green;overflow:auto;height:178px;">
									   				<div>
										 				 <table id="zghtp1">
															<c:forEach var="item" items="${picList12}">
																<tr id='${item.id}' style="text-align: center">
																	<td width="70%">
																		<c:choose>
																    		<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.png')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
																    			||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
																    			&nbsp;&nbsp;&nbsp;<img src="/upload/photo/${item.picName}"
																				border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
																    		</c:when> 
																    		<c:otherwise> 
																    			 &nbsp;&nbsp;&nbsp;${item.fileName}
																    		</c:otherwise>
															   			</c:choose>
																	</td>
																	<td width="30%">
																		<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																		<a href="javascript:del('${item.id}');">删除</a>
																	</td>
																</tr>
															</c:forEach>
														</table>
													</div>
												</div>
											</td>
										</tr> --%>
									</table>
								</td>
							</tr>
						</table>
					</div>
					
					<div id="more2" class="submitdata" style="display:none">
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
							  <legend>隐患二</legend>
							</fieldset>
							<table  style="width: 100%;">
								<tr>
									<th width="15%">隐患二被检查部位</th>
									<td width="35%"><input id="jcbw2" name="qyzcyhglb2.jcbw" value="${qyzcyhglb2.jcbw}" type="text" maxlength="255"></td>
									<th width="15%">隐患二整改完成时间</th>
									<td width="35%"><input id="yhzgwcsj2" name="qyzcyhglb2.yhzgwcsj" value="${qyzcyhglb2.yhzgwcsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
								</tr>
								<tr>
									<th width="15%">隐患二隐患大类</th>
									<td width="35%">
										<s:select id="yhdl2" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb2.yhdl" value="#{qyzcyhglb2.yhdl}" onchange="changeZL(this.value,'2');"/>
										<font style="color:red">*</font>
									</td>
									<th width="15%">隐患二隐患中类</th>
									<td width="35%">
										<select id="yhzl2" name="qyzcyhglb2.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
										<font style="color:red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患二整改责任部门</th>
									<td width="35%"><input  name="qyzcyhglb2.zgzrbm" value="${qyzcyhglb2.zgzrbm}" type="text" maxlength="255"></td>
									<th width="15%">隐患二整改责任人</th>
									<td width="35%"><input  name="qyzcyhglb2.zgzrr" value="${qyzcyhglb2.zgzrr}" type="text" maxlength="255"></td>
								</tr>
								<tr>
									<th width="15%">隐患二整改前图片</th>
									<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test2_1"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zgqtp2'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo2_1"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo21">
													<c:forEach var="item" items="${picList21}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th width="15%">隐患二整改后图片</th>
								<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test2_2"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zghtp2'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo2_2"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo22">
													<c:forEach var="item" items="${picList22}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					
					<div id="more3" class="submitdata" style="display:none">
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
							  <legend>隐患三</legend>
							</fieldset>
							<table style="width: 100%;">
								<tr>
									<th width="15%">隐患三被检查部位</th>
									<td width="35%"><input id="jcbw3" name="qyzcyhglb3.jcbw" value="${qyzcyhglb3.jcbw}" type="text" maxlength="255"></td>
									<th width="15%">隐患三整改完成时间</th>
									<td width="35%"><input id="yhzgwcsj3" name="qyzcyhglb3.yhzgwcsj" value="${qyzcyhglb3.yhzgwcsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
								</tr>
								<tr>
									<th width="15%">隐患三隐患大类</th>
									<td width="35%">
										<s:select id="yhdl3" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb3.yhdl" value="#{qyzcyhglb3.yhdl}" onchange="changeZL(this.value,'3');"/>
										<font style="color:red">*</font>
									</td>
									<th width="15%">隐患三隐患中类</th>
									<td width="35%">
										<select id="yhzl3" name="qyzcyhglb3.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
										<font style="color:red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患三整改责任部门</th>
									<td width="35%"><input name="qyzcyhglb3.zgzrbm" value="${qyzcyhglb3.zgzrbm}" type="text" maxlength="255"></td>
									<th width="15%">隐患三整改责任人</th>
									<td width="35%"><input name="qyzcyhglb3.zgzrr" value="${qyzcyhglb3.zgzrr}" type="text" maxlength="255"></td>
								</tr>
								<tr>
									<th width="15%">隐患三整改前图片</th>
									<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test3_1"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zgqtp3'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo3_1"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo31">
													<c:forEach var="item" items="${picList31}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th width="15%">隐患三整改后图片</th>
								<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
											<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test3_2"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zghtp3'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo3_2"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo32">
													<c:forEach var="item" items="${picList32}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					
					
					
					<div id="more4" class="submitdata" style="display:none">
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
							  <legend>隐患四</legend>
							</fieldset>
							<table style="width: 100%;">
								<tr>
									<th width="15%">隐患四被检查部位</th>
									<td width="35%"><input id="jcbw4" name="qyzcyhglb4.jcbw" value="${qyzcyhglb4.jcbw}" type="text" maxlength="255"></td>
									<th width="15%">隐患四整改完成时间</th>
									<td width="35%"><input id="yhzgwcsj4" name="qyzcyhglb4.yhzgwcsj" value="${qyzcyhglb4.yhzgwcsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
								</tr>
								<tr>
									<th width="15%">隐患四隐患大类</th>
									<td width="35%">
										<s:select id="yhdl4" listKey="key" listValue="value" theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb4.yhdl" value="#{qyzcyhglb4.yhdl}" onchange="changeZL(this.value,'4');"/>
										<font style="color:red">*</font>
									</td>
									<th width="15%">隐患四隐患中类</th>
									<td width="35%">
										<select id="yhzl4" name="qyzcyhglb4.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
										<font style="color:red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患四整改责任部门</th>
									<td width="35%"><input  name="qyzcyhglb4.zgzrbm" value="${qyzcyhglb4.zgzrbm}" type="text" maxlength="255"></td>
									<th width="15%">隐患四整改责任人</th>
									<td width="35%"><input  name="qyzcyhglb4.zgzrr" value="${qyzcyhglb4.zgzrr}" type="text" maxlength="255"></td>
								</tr>
								<tr>
									<th width="15%">隐患四整改前图片</th>
									<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
											<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test4_1"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zgqtp4'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo4_1"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo41">
													<c:forEach var="item" items="${picList41}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th width="15%">隐患四整改后图片</th>
								<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test4_2"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zghtp4'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo4_2"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo42">
													<c:forEach var="item" items="${picList42}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					
					<div id="more5" class="submitdata" style="display:none">
					<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
							  <legend>隐患五</legend>
							</fieldset>
							<table style="width: 100%;">
								<tr>
									<th width="15%">隐患五被检查部位</th>
									<td width="35%"><input id="jcbw5" name="qyzcyhglb5.jcbw" value="${qyzcyhglb5.jcbw}"  type="text" maxlength="255"></td>
									<th width="15%">隐患五整改完成时间</th>
									<td width="35%"><input id="yhzgwcsj5" name="qyzcyhglb5.yhzgwcsj" value="${qyzcyhglb5.yhzgwcsj}" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
								</tr>
								<tr>
									<th width="15%">隐患五隐患大类</th>
									<td width="35%">
										<s:select id="yhdl5" listKey="key" listValue="value"  theme="simple" list="#{'0':' ','1':'基础管理','2':'现场管理'}" name="qyzcyhglb5.yhdl" value="#{qyzcyhglb5.yhdl}" onchange="changeZL(this.value,'5');"/>
										<font style="color:red">*</font>
									</td>
									<th width="15%">隐患五隐患中类</th>
									<td width="35%">
										<select id="yhzl5" name="qyzcyhglb5.yhzl">
											<option  value="0">&nbsp;&nbsp;&nbsp;</option>
										</select>
										<font style="color:red">*</font>
									</td>
								</tr>
								<tr>
									<th width="15%">隐患五整改责任部门</th>
									<td width="35%"><input  name="qyzcyhglb5.zgzrbm" value="${qyzcyhglb5.zgzrbm}" type="text" maxlength="255"></td>
									<th width="15%">隐患五整改责任人</th>
									<td width="35%"><input name="qyzcyhglb5.zgzrr" value="${qyzcyhglb5.zgzrr}" type="text" maxlength="255" ></td>
								</tr>
								<tr>
									<th width="15%">隐患五整改前图片</th>
									<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
											<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test5_1"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zgqtp5'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo5_1"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo51">
													<c:forEach var="item" items="${picList51}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<th width="15%">隐患五整改后图片</th>
								<td width="85%" colspan="3">
									<table width="100%">
										<tr>
											<td>
												<div class="layui-upload">
												    <button type="button" class="layui-btn" id="test5_2"   lay-data="{url: '/fsajj/jsp/zcyhsb/jshxZcyhsbSavePhoto.action?type=zghtp5'}">选择整改前图片</button>
												    <blockquote class="layui-elem-quote layui-quote-nm" style="margin-top: 10px;">
													   预览图：
													   <div class="layui-upload-list" id="demo5_2"></div>
													</blockquote>
												</div> 
												<div class="site-demo-flow" id="LAY_demo52">
													<c:forEach var="item" items="${picList52}">
														<img src="/upload/photo/${item.fileName}" border='0' width='200' height='150'/>
													</c:forEach>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
				</td>
				</tr>
				<tr class="hasyinhuan">
					<th width="15%">计划完成时间</th>
					<td width="35%"><input  
						msg="计划完成时间不能为空" name="jshxZcyhsb.jhwcsj" value="<fmt:formatDate type='date' value='${jshxZcyhsb.jhwcsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font style="color:red">*</font></td>
					
					<th width="15%">复查时间</th>
							<td width="35%">
								<input id="fcsj" name="jshxZcyhsb.fcsj"  value='${jshxZcyhsb.fcsj}' type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
							</td>
				</tr>
				<tr class="hasyinhuan">
					<td width="100%" colspan="4">
						<table width="100%" id="zt_0">
							<th width="15%">目前状态</th>
							<td width="35%">
								<s:select id="zgzt"  theme="simple" list="#{'1':'整改中','0':'整改完成'}" name="jshxZcyhsb.mqzt" value="#{jshxZcyhsb.mqzt}" onchange="changeZT(this.value);"></s:select>
							</td>
							<th width="15%">复查人</th>
							<td width="35%"><input id="fcr"  name="jshxZcyhsb.fcr" value="${jshxZcyhsb.fcr}" type="text" maxlength="255"></td>
						</table>
					</td>
				</tr>
				<tr class="hasyinhuan">
					<th width="15%">级别</th>
					<td width="35%">
						<s:select id="jb" theme="simple" list="#{'0':'一般隐患','1':'一级隐患','2':'二级隐患','3':'三级隐患'}" name="jshxZcyhsb.jb" value="#{jshxZcyhsb.jb}"></s:select>
					</td>
					<th width="15%">整改投入资金(万元)</th>
					<td width="35%"><input 
						msg="整改投入资金不能为空" name="jshxZcyhsb.zgtrzj" value="${jshxZcyhsb.zgtrzj}" type="text" maxlength="255" onKeyUp="validateNum(event,this)"></td>
				</tr>
				<tr class="hasyinhuan">
					<th width="15%">存在问题和隐患</th>
					<td colspan="3"><textarea 
						msg="存在问题和隐患不能为空" name="jshxZcyhsb.wtyh" style="width:100%;height:100px">${jshxZcyhsb.wtyh}</textarea><font style="color:red">*</font></td>
				</tr>
				<tr class="hasyinhuan">
					<th width="15%">具体情况及整改措施方案</th>
					<td colspan="3"><textarea 
						msg="具体情况及整改措施方案不能为空"  name="jshxZcyhsb.csfa" style="width:100%;height:100px">${jshxZcyhsb.csfa}</textarea><font style="color:red">*</font></td>
				</tr>
				<tr class="hasyinhuan">
					<td width="100%" colspan="4">
						<table width="100%" id="zt_1">
							<th width="15%">复查验收情况</th>
							<td colspan="3"><textarea id="fcysqk" name="jshxZcyhsb.fcysqk" style="width:100%;height:100px">${jshxZcyhsb.fcysqk}</textarea></td>
						</table>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td colspan="4" height="100px"   style="text-align:center;">
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
