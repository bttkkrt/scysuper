<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>企业注册</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/js/xmlhttp.js"></script>
	<script>
	function onlyNum()
	{ 
		var keys=event.keyCode;
		if (!((keys>=48&&keys<=57)||(keys>=96&&keys<=105)||(keys==8)||(keys==46)||(keys==37)||(keys==39)||(keys==13)||(keys==229)||(keys==189)||(keys==109)))
			event.returnValue=false;
	} 
	function save(){
		
				if($('#ifwhpqylx').val()!=1 && 
				   $('#ifzywhqylx').val()!=1 && 
				   $('#ifyhbzjyqy').val()!=1 && 
				   $('#iffmksjyqy').val()!=1 && 
				   $('#iffmgmqylx').val()!=1 ){
						alert("请至少选择一项企业所属监管类型！");
		        		return false;
				}
				if($('#iffmgmqylx').val()==1)
				{
					var ches = $("#iffmgmqylxid").children().children().children().children();
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
						alert("非煤工贸企业类型至少选择一项");
						return false;
					}
				}
				if($('#ifwhpqylx').val()==1)
				{
					var ches = $("#ifwhpqylxid").children().children().children().children();
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
					var ches = $("#ifzywhqylxid").children().children().children().children();
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
				if($('#ifyhbzjyqy').val()==1)
			    {
					var ches = $("#ifyhbzjyqyid").children().children().children().children();
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
						alert("烟花爆竹企业类型至少选择一项");
						return false;
					}
				}
		if($('#iffmksjyqy').val()==1)
	    {
			$("#diggingstype").children().attr("dataType","Require").attr("msg","此项为必选");
			$("#metal").attr("dataType","Require").children().attr("msg","此项为必选");
			if($("#diggingstype").children().val() == 'd001' || $("#diggingstype").children().val() == 'd005'){
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
		var gddh = document.getElementById('gddh');
       	var reg=/(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/   ; 
       	if (reg.test(gddh.value)==false)
       	{
        	alert("请填写正确的固定电话号码。");
       	 	return false;
        }
		var cz = document.getElementById('cz');
        var reg=/(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}/   ; 
        if (reg.test(cz.value)==false)
        {
        	alert("请填写正确的传真号码。");
        	return false;
        }
        var radioCheck = document.getElementById('file21');
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
		}
		
		if(Validator.Validate(document.myform1,3)){
			document.myform1.action="companyRegister.action";
			document.myform1.submit();
		}
	}
	function pre(){
		document.myform1.action="companyRegisterUI.action";
		document.myform1.submit();
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
		if($('#ifurl').val()==1)
		{
		     document.getElementById("file21").checked=true;
		     document.getElementById("file20").checked=false;
		     $('#ifurlid').css("display","block"); 
		}else
		{
		     document.getElementById("file21").checked=false;
		     document.getElementById("file20").checked=true;
		     $('#ifurlid').css("display","none");  
		 }
		 
			if($('#ifyhbzjyqy').val()==1)
		{
		     document.getElementById("file31").checked=true;
		     document.getElementById("file30").checked=false;
		   	 $('#ifyhbzjyqyid').css("display","block"); 
		}else
		{
		     document.getElementById("file31").checked=false;
		     document.getElementById("file30").checked=true;
		     $('#ifyhbzjyqyid').css("display","none");  
	    } 
	    if($('#iffmksjyqy').val()==1)
		{
		     document.getElementById("file41").checked=true;
		     document.getElementById("file40").checked=false;
		   	 $('.iffmksjyqyid').css("display","block"); 
		}else
		{
		     document.getElementById("file41").checked=false;
		     document.getElementById("file40").checked=true;
		     $('.iffmksjyqyid').css("display","none");  
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
				$('.iffmksjyqyid').css("display","block"); 
			}else{   
				$('#iffmksjyqy').val(0);   
				$('.iffmksjyqyid').css("display","none");             
			}                    
		});          
	}); 
                       
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
	<style type="text/css">    
	body{    
       	background-image:url('../../webResources/themes/tree/images/default/registerUI.png');  
       	font-size : 12px; color : #000000; font-family : tahoma, 宋体, fantasy; text-align : center; margin : 0; 
 	}    

 
	table 
  	{      
  		line-height:   20px;   
  	} 
  	.changeStyle 
  	{
  		border:1px solid #000000;
  	}
  
   	.changeStyle 
   	{
   		star : expression(
			onmouseover=function(){this.style.border="1px solid #ff0000" },
			onmouseout=function(){this.style.border="1px solid #000000"}
		)
	}
</style>
</head>
<body>
    <div style="margin-top:20px;margin-left:10px;">
	<s:form name="myform1" method="post" enctype="multipart/form-data" theme='simple'>
	<s:token />
		<div>
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
			<input type="hidden" name="company.companyname"  value="${company.companyname}" >
			<input type="hidden" name="company.fddbr" value="${company.fddbr}">
			<input type="hidden" name="company.dwdz" value="${company.dwdz}">
			<input type="hidden" name="company.dwdz1" value="${company.dwdz1}">
			<input type="hidden" name="company.dwdz2" value="${company.dwdz2}">
			<input type="hidden" name="company.zzjgdm" value="${company.zzjgdm}">
			<input type="hidden" name="company.gszch" value="${company.gszch}">
			<input type="hidden" name="company.qylx" value="${company.qylx}">
			<input type="hidden" name="company.hyfl" value="${company.hyfl}">
			<input type="hidden" name="company.lxr" value="${company.lxr}">
			<input type="hidden" name="company.lxfs" value="${company.lxfs}">
			<input type="hidden" name="company.yzm" value="${company.yzm}">
			<input type="hidden" name="company.qyyx" value="${company.qyyx}">
			<input type="hidden" name="company.iffmgmqylx" id="iffmgmqylx" value="${company.iffmgmqylx}">
			<input type="hidden" name="company.ifwhpqylx" id="ifwhpqylx" value="${company.ifwhpqylx}">
			<input type="hidden" name="company.ifzywhqylx" id="ifzywhqylx" value="${company.ifzywhqylx}">
			<input type="hidden" name="company.ifyhbzjyqy" id="ifyhbzjyqy" value="${company.ifyhbzjyqy}">
			<input type="hidden" name="company.iffmksjyqy" id="iffmksjyqy" value="${company.iffmksjyqy}">
			<input type="hidden" name="company.fddbrlxhm" value="${company.fddbrlxhm}">
			<input type="hidden" name="company.mobile" value="${company.mobile}">
			<input type="hidden" name="company.ifurl" id="ifurl" value="${company.ifurl}">
			<input type="hidden" name="company.szc"  value="${company.szc}">
			<input type="hidden" name="company.szcname"  value="${company.szcname}">
			<input type="hidden" name="company.county" value="${company.county}">
			<input id="longitude" name="company.longitude" value="${company.longitude}" type="hidden">
			<input id="latitude" name="company.latitude" value="${company.latitude}"   type="hidden">
			
			<input type="hidden" name="company.ifzsqy" id="ifzsqy" value="${company.ifzsqy}">
			<input type="hidden" name="company.zsqytype" id="zsqytype" value="${company.zsqytype}">
			
			<table width="100%" border="0" bgcolor="#F9F9F9">
				<tr >
				   	<td colspan="4" style="text-align:center;font-size:20px;font-weight:bold;">企业所属监管类型</td>
				</tr>
				<tr>
					<td width="35%" align="right">是否非煤工贸企业:</td>
					<td colspan="3">
					 	<input type="radio" name="file6"  id="file60" value="0" class="filetype">否
                     	<input type="radio" name="file6" id="file61" checked="checked"  value="1"  class="filetype">是 <font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" id="iffmgmqylxid">
							<td colspan="4">
								<cus:hxcheckbox property="company.companyType" codeName="非煤工贸企业" value="${company.companyType}" dataType="Require" msg="此项为必选"/>
							</td>
						</table>
					</td>
				</tr>
				<tr>
					<td width="35%" align="right">是否危化品企业:</td>
					<td colspan="3">
					 	<input type="radio" name="file"  id="file00" value="0" class="filetype">否
                     	<input type="radio" name="file" id="file01" checked="checked"  value="1"  class="filetype">是 <font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" id="ifwhpqylxid">
							<td colspan="4">
								<cus:hxcheckbox property="company.companyType" codeName="危化品企业类型" value="${company.companyType}" dataType="Require" msg="此项为必选"/>
							</td>
						</table>
					</td>
				</tr>
				<tr>
			    	<td width="35%" align="right">是否职业危害企业:</td>
					<td colspan="3">
					 	<input type="radio" name="file1"  id="file10" value="0" class="filetype1">否
                     	<input type="radio" name="file1" id="file11"  checked="checked"  value="1"  class="filetype1">是<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" id="ifzywhqylxid">
							<td colspan="4">
								<cus:hxcheckbox property="company.companyType" codeName="职业危害企业类型" value="${company.companyType}" dataType="Require" msg="此项为必选"/>
							</td>
						</table>
					</td>
				</tr>
				<tr>
			    	<td width="35%" align="right">是否烟花爆竹企业:</td>
					<td colspan="3">
					 	<input type="radio" name="file3"  id="file30" value="0" class="filetype3">否
                     	<input type="radio" name="file3" id="file31"  checked="checked"  value="1"  class="filetype3">是<font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="4">
						<table width="100%" id="ifyhbzjyqyid">
							<td colspan="4">
								<cus:hxcheckbox property="company.companyType" codeName="烟花爆竹企业" value="${company.companyType}" dataType="Require" msg="此项为必选"/>
							</td>
						</table>
					</td>
				</tr>
				
				<tr>
			    	<td width="35%" align="right">是否非煤矿山企业:</td>
					<td colspan="3">
					 	<input type="radio" name="file4"  id="file40" value="0" class="filetype4">否
                     	<input type="radio" name="file4" id="file41"  checked="checked"  value="1"  class="filetype4">是<font color="red">*</font>
					</td>
				</tr>
					<tr class="iffmksjyqyid">
						<td width="35%" align="right">矿山类型：</td>
						<td colspan="3" id="diggingstype">
							<cus:SelectOneTag onchange="showDiv();" style="width:150px;" property="company.companyType" defaultText='请选择' codeName="矿山类型" value="${company.companyType}" /><font color="red">*</font>
						</td>
					</tr>
					<tr class="iffmksjyqyid">
						<td width="35%" align="right">金属属性：</td>
						<td colspan="3">
							<cus:SelectOneTag style="width:150px;" property="company.metal" defaultText='请选择' codeName="金属属性" value="${company.metal}" /><font color="red">*</font>
						</td>
					</tr>
					<tr class="iffmksjyqyid" id="tffs">
						<td width="35%" align="right">通风方式：</td>
						<td colspan="3" >
							<cus:SelectOneTag style="width:150px;" property="company.ventilate" defaultText='请选择' codeName="通风方式" value="${company.ventilate}" /><font color="red">*</font>
						</td>
					</tr>
					<tr class="iffmksjyqyid" id="ysfs">
						<td width="35%" align="right">运输方式：</td>
						<td colspan="3" >
							<cus:SelectOneTag style="width:150px;" property="company.transport" defaultText='请选择' codeName="运输方式" value="${company.transport}" /><font color="red">*</font>
						</td>
					</tr>
					<tr class="iffmksjyqyid" id="tsfs">
						<td width="35%" align="right">提升方式：</td>
						<td colspan="3" >
							<cus:SelectOneTag style="width:150px;" property="company.raisetype" defaultText='请选择' codeName="提升方式" value="${company.raisetype}" /><font color="red">*</font>
						</td>
					</tr>
					<tr class="iffmksjyqyid" id="ldxt">
						<td width="35%" align="right">六大系统情况：</td>
						<td colspan="3" >
							<textarea id="sixsys" class="changeStyle" dataType="Require" msg="此项为必填" name="company.sixsys" rows="5" style="width: 50%">${company.sixsys}</textarea>
						</td>
					</tr>
				<tr >
				   	<td colspan="4" style="text-align:center;font-size:20px;font-weight:bold;">其他信息完善</td>
				</tr>
				<tr>
					<td width="35%" align="right">企业规模:</td>
					<td colspan="3"><cus:SelectOneTag property="company.qygm" defaultText='请选择' codeName="企业规模" value="${company.qygm}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">企业注册类型:</td>
					<td colspan="3"><cus:SelectOneTag property="company.qyzclx" defaultText='请选择' codeName="注册类型" value="${company.qyzclx}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
					</tr>
				<tr>
					<td width="35%" align="right">注册资金:</td>
					<td colspan="3"><input name="company.zczj"  class="changeStyle" value="${company.zczj}" onKeyUp="clearNoNum(event,this)"  size=30 type="text" maxlength="255" dataType="Require" msg="此项为必填"> （万元）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">企业成立时间:</td>
					<td colspan="3"><input name="company.qyclsj" value="<fmt:formatDate type='date' value='${company.qyclsj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">固定电话:</td>
					<td colspan="3"><input name="company.gddh" class="changeStyle" id='gddh' size=30 value="${company.gddh}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font color="red">*</font>(格式：024-1234567)</td>
				</tr>
				<tr>
					<td width="35%" align="right">传       真:</td>
					<td colspan="3"><input name="company.cz"  class="changeStyle" id='cz' size=30 value="${company.cz}" type="text" maxlength="255" dataType="Require" msg="此项为必填"><font color="red">*</font>(格式：024-1234567)</td>
				</tr>
				<tr>
					<td width="35%" align="right">邮政编码:</td>
					<td colspan="3"><input name="company.yzbm" class="changeStyle" onKeyDown="onlyNum()"  onKeyUp="clearNoNum(event,this)"  value="${company.yzbm}" size=30 type="text" dataType="Require" msg="此项为必填" checkregexp="\d{6}" maxlength="255"><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">上年销售收入:</td>
					<td colspan="3"><input name="company.snxssr" class="changeStyle" value="${company.snxssr}" onKeyUp="clearNoNum(event,this)" size=30 type="text" maxlength="255" dataType="Require" msg="此项为必填">（万元）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">上年上交税收:</td>
					<td colspan="3"><input name="company.snsjss" class="changeStyle" value="${company.snsjss}"size=30 onKeyUp="clearNoNum(event,this)" type="text" maxlength="255" dataType="Require" msg="此项为必填">（万元）<font color="red">*</font></td>
					</tr>
				<tr>
					<td width="35%" align="right">上年固定资产:</td>
					<td colspan="3"><input name="company.sngdzc" class="changeStyle" value="${company.sngdzc}"size=30 onKeyUp="clearNoNum(event,this)" type="text" maxlength="255" dataType="Require" msg="此项为必填">（万元）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">上年安全投入:</td>
					<td colspan="3"><input name="company.snwqtr" class="changeStyle" value="${company.snwqtr}"size=30 onKeyUp="clearNoNum(event,this)" type="text" maxlength="255" dataType="Require" msg="此项为必填">（万元）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">上年安全生产费用提取数:</td>
					<td colspan="3"><input name="company.snaqscf" class="changeStyle" value="${company.snaqscf}"size=30 onKeyUp="clearNoNum(event,this)" type="text" maxlength="255" dataType="Require" msg="此项为必填">（万元）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">是否设立安全管理机构:</td>
					<td colspan="3"><cus:hxradio property="company.sfaqjg" codeName="是或否" value="0" dataType="Require" msg="此项为必选"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">安全管理员:</td>
					<td colspan="3"><input name="company.aqglr" class="changeStyle" onKeyDown="onlyNum()" onKeyUp="validate(event,this)" value="${company.aqglr}" size=30 type="text" dataType="Require" msg="此项为必填" checkregexp="^\d*$" maxlength="255">（人）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">是否设立职业卫生管理机构:</td>
					<td colspan="3"><cus:hxradio property="company.sfzywsjg" codeName="是或否" value="0" dataType="Require" msg="此项为必选"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">职业卫生管理人员:</td>
					<td colspan="3"><input name="company.zywsglry" class="changeStyle" onKeyDown="onlyNum()" onKeyUp="validate(event,this)" value="${company.zywsglry}" size=30 type="text" dataType="Require" msg="此项为必填" checkregexp="^\d*$" maxlength="255">（人）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">是否为专职或兼职职业卫生管理员:</td>
					<td colspan="3"><cus:hxradio property="company.sfqzwsgly" codeName="是或否" value="0" dataType="Require" msg="此项为必选"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">占地面积:</td>
					<td colspan="3"><input name="company.zdmj" class="changeStyle"  value="${company.zdmj}"size=30 onKeyUp="clearNoNum(event,this)" type="text" dataType="Require" msg="此项为必填" checkregexp="^\d*$" maxlength="255">（m2）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">建筑面积:</td>
					<td colspan="3"><input name="company.jzmj" class="changeStyle" value="${company.jzmj}"size=30 onKeyUp="clearNoNum(event,this)" type="text" dataType="Require" msg="此项为必填" checkregexp="^\d*$" maxlength="255">（m2）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">从业人员:</td>
					<td colspan="3"><input name="company.cyry" class="changeStyle" onKeyDown="onlyNum()" onKeyUp="validate(event,this)" value="${company.cyry}"size=30 type="text" dataType="Require" msg="此项为必填" checkregexp="^\d*$" maxlength="255">（人）<font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">是否有员工宿舍:</td>
					<td colspan="3"><cus:hxradio property="company.sfyygss" codeName="是或否" value="0" dataType="Require" msg="此项为必选"/><font color="red">*</font></td>
				</tr>
				<tr>
					<td width="35%" align="right">安全生产标准化达标级别:</td>
					<td colspan="3"><cus:SelectOneTag property="company.aqbzdbjb" codeName="标准化达标级别"  defaultText='请选择' dataType="Require" msg="此项为必选"  value="${company.aqbzdbjb}"/><font color="red">*</font></td>
				</tr>
				<tr>
			    	<td width="35%" align="right">企业网址:</td>
					<td colspan="3">
						<input type="radio" name="file2"  id="file20" value="0" class="filetype2">无
                     	<input type="radio" name="file2" id="file21" value="1"  class="filetype2">有<font color="red">*</font>
					</td>
				</tr>
				<tr  id="ifurlid">
			    	<td width="35%" align="right">网址为：</td>
					<td colspan="3">
						<input name="company.url" value="${company.url}" onKeyUp="value=value.replace(/^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi,'')" class="changeStyle" id="url" type="text" style="width:80%"><font color="red">*</font>
					</td>
				</tr>
				<tr>
					<td width="35%" align="right">经营范围:</td>
					<td colspan="3">
						<textarea id="company.jyfw" class="changeStyle" dataType="Require" msg="此项为必填"
							name="company.jyfw" rows="5"
						style="width: 80%">${company.jyfw}</textarea><font color="red">*</font>
					</td>
				</tr>
			</table>
	  	</div>
		<div  style="margin:10px;margin-left:140px;">
			<a href="#" ><img style="border:none;" src='<c:url value="/webResources/themes/tree/images/default/pre.png"/>' onClick="pre();"   />
			</a>
			<a href="#" ><img style="border:none;" src='<c:url value="/webResources/themes/tree/images/default/reg.png"/>' onClick="save();" onmouseover="this.src='<c:url value="/webResources/themes/tree/images/default/reg2.png"/>'" 
				onmouseout="this.src='<c:url value="/webResources/themes/tree/images/default/reg.png"/>'"  />
			</a><!--
			<a href="#"  ><img style="border:none;" src='<c:url value="/webResources/themes/tree/images/default/can1.png"/>' onClick="history.go(-1);" onmouseover="this.src='<c:url value="/webResources/themes/tree/images/default/can2.png"/>'" 
				onmouseout="this.src='<c:url value="/webResources/themes/tree/images/default/can1.png"/>'"  />
			</a> -->
				
		</div>
	</s:form>
</div>
</body>
</html>
