<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!-- jquery & easyui js -->
<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/easyui/locale/easyui-lang-zh_CN.js"></script>

 <script src="${ctx}/webResources/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link href="${ctx}/webResources/qyzc/css/form.css" rel="stylesheet" type="text/css" />

<!-- platform -->
<link href="${ctx}/webResources/js/uploadify.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/webResources/js/swfobject.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/common.js"></script>

<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.mousewheel-3.0.4.pack.js"></script>
<script type="text/javascript" src="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/webResources/fancyboxs/jquery.fancybox-1.3.4.css" media="screen" />



<script>
		$(document).ready(function() {
		  	uploadPicOnly("uploadify","${entBaseInfo.linkId}","qyxx","cqtp","fileQueue");
		});
        function save()
        {
        	if(
        	checkInfo('registrationNumber')&&
        	checkInfo('enterpriseAddress')&&
        	checkInfo('factoryAddress')&&
			checkInfo('enterprisePossession')&&
			checkInfo('enterpriseZipcode')&&
			checkInfo('enterpriseNature')&&
			checkInfo('enterpriseScale')&&
			checkInfo('enterpriseType')&&
			checkInfo('enterpriseCategory')&&
			checkInfo('enterpriseNationnality')&&
			checkInfo('enterpriseLegalName')&&
			checkInfo('enterpriseLegalSex')&&
			checkInfo('enterpriseLegalAge')&&
			checkInfo('enterpriseLegalPhone')&&
			checkInfo('enterpriseLegalCardnum')&&
			checkInfo('enterpriseLegalEmail')&&
			checkInfo('enterpriseLegalZw')&&
			checkInfo('enterpriseFoundDate')&&
			checkInfo('enterpriseProductDate')&&
			checkInfo('enterpriseRegisterMoney')&&
			checkInfo('enterpriseInvestMoney')&&
			checkInfo('enterpriseFixedassetMoney')&&
			checkInfo('enterpriseFloorArea')&&
			checkInfo('enterpriseOfficeArea')&&
			checkInfo('enterpriseWorkshopArea')&&
			checkInfo('enterpriseWearhouseArea')&&
			checkInfo('enterprisWorkshopOwn')&&
			checkInfo('enterpriseManagerCount')&&
			checkInfo('enterpriseWorkerCount')&&
			checkInfo('enterpriseScope'))
			{
				var zlfs = document.getElementById('enterprisWorkshopOwn').value;
				if(zlfs == '1' || (zlfs == '2' && checkInfo('houseOwner') && checkInfo('ownerTel')))
				{
					document.myform1.action = "${ctx}/jsp/qyjbxx/entBaseInfoSaveRegister.action";
        			document.myform1.submit();
				}
			}
        }
        
		
		function showTitle(obj)
		{
			document.getElementById(obj+'Null').style.display = "none";
			document.getElementById(obj+'Succ').style.display = "none";
			if(obj == 'enterpriseName')
			{
				document.getElementById('enterpriseNameExit').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'registrationNumber')
			{
				document.getElementById('registrationNumberExit').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'enterpriseZipcode')
			{
				document.getElementById('enterpriseZipcodeExit').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'enterpriseLegalEmail')
			{
				document.getElementById('enterpriseLegalEmailExit').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'enterpriseType')
			{
				var ches = document.getElementsByName("entBaseInfo.enterpriseType");
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
					document.getElementById(obj+'Null').style.display = "";
				}
			}
			else
			{	
				document.getElementById(obj+'Title').style.display = "";
			}
		}
		function checkInfo(obj)
		{
			document.getElementById(obj).disabled = true;
			document.getElementById(obj+'Title').style.display = "none";
			document.getElementById(obj+'Null').style.display = "none";
			document.getElementById(obj+'Succ').style.display = "none";
			var ss = document.getElementById(obj).value;
			if(ss == null || ss == "")
			{
				document.getElementById(obj+'Null').style.display = "";
				document.getElementById(obj).disabled = false;
				return false;
			}
			else 
			{
				if(obj == 'enterpriseName')
				{
					document.getElementById('enterpriseNameExit').style.display = "none";
					$.ajax({
						url : "isUserExit.action",
						type: 'post',
						dataType: 'json',
						async : false,
						data:{ 
							"entBaseInfo.enterpriseName" : ss
						},
						error: function(){
							alert('验证企业名称时出错！');
						},
						success: function(data){
							if(data.result == 'true'){
								document.getElementById('enterpriseNameExit').style.display = "";
								document.getElementById(obj).disabled = false;
								return false;
							}else{
								document.getElementById('enterpriseNameSucc').style.display = "";
								document.getElementById(obj).disabled = false;
							}
						}
					});				
				}
				else if(obj == 'registrationNumber')
				{
					document.getElementById('registrationNumberExit').style.display = "none";
					$.ajax({
						url : "isUserExit.action",
						type: 'post',
						dataType: 'json',
						async : false,
						data:{ 
							"entBaseInfo.registrationNumber" : ss
						},
						error: function(){
							alert('验证工商注册号时出错！');
						},
						success: function(data){
							if(data.result == 'true'){
								document.getElementById('registrationNumberExit').style.display = "";
								document.getElementById(obj).disabled = false;
								return false;
							}else{
								document.getElementById('registrationNumberSucc').style.display = "";
								document.getElementById(obj).disabled = false;
							}
						}
					});				
				}
				else if(obj == 'enterpriseZipcode')
				{
					document.getElementById('enterpriseZipcodeExit').style.display = "none";
					var enterpriseZipcodeReq = /^\d{6}$/;
					if(!enterpriseZipcodeReq.test(ss))
					{	
						document.getElementById('enterpriseZipcodeExit').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{
						document.getElementById('enterpriseZipcodeSucc').style.display = "";
						document.getElementById(obj).disabled = false;
					}
				}
				else if(obj == 'enterpriseType')
				{
					var ches = document.getElementsByName("entBaseInfo.enterpriseType");
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
						document.getElementById(obj+'Null').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{
						document.getElementById('enterpriseTypeSucc').style.display = "";
						document.getElementById(obj).disabled = false;
					}
				}
				else if(obj == 'enterpriseLegalEmail')
				{
					document.getElementById('enterpriseLegalEmailExit').style.display = "none";
					var email =  /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
					if(!email.test(ss))
					{	
						document.getElementById('enterpriseLegalEmailExit').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{
						document.getElementById('enterpriseLegalEmailSucc').style.display = "";
						document.getElementById(obj).disabled = false;
					}
				}
				else
				{
					document.getElementById(obj+'Succ').style.display = "";
					document.getElementById(obj).disabled = false;
				}
				return true;
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
        
    function changeOwn(obj)
	{
		if(obj == 2)
		{
			document.getElementById('ownerdiv1').style.display = "";
			document.getElementById('ownerdiv2').style.display = "";
		}
		else
		{
			document.getElementById('ownerdiv1').style.display = "none";
			document.getElementById('ownerdiv2').style.display = "none";
		}
	}
</script>


</head>

<body >
<form id="myform1" name="myform1" method="post" enctype="multipart/form-data">
<input type="hidden" name="entBaseInfo.linkId" value="${entBaseInfo.linkId}">
<input type="hidden" name="entBaseInfo.ifCz" value="${entBaseInfo.ifCz}">
<input type="hidden" name="entBaseInfo.enterpriseCode" value="${entBaseInfo.enterpriseCode}">
<input type="hidden" name="entBaseInfo.loginId" value="${entBaseInfo.loginId}">
<input type="hidden" name="entBaseInfo.password" value="${entBaseInfo.password}">

<div class="topbar">
	<a href="#"><img src="${ctx}/webResources/qyzc/images/default/reg_logo.png"/></a>
    <div class="user">新会员注册</div>
</div>
<div class="af_add">
    <div class="padd20">
    <div class="addcontent">
        <div class="steps steps-3">
            <ul>
                <li><i>1</i>企业用户信息填写</li>
                <li class="active"><i>2</i>填写详细信息</li>
                <li><i>3</i>完成注册</li>
            </ul>	
        </div>
        <div class="steps_main">
            <div class="steps_01">
                <table width="80%"  border="0" cellpadding="0" cellspacing="0">
                	<tr>
                    	<th>企业名称：</th>
						<td>
							<input id="enterpriseName" name="entBaseInfo.enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" style="width:300px" maxlength="127" readonly="readonly">
						</td>
					</tr>
                   <tr>
						<th><span class="red">*</span>工商注册号：</th>
						<td>
							<input id="registrationNumber" name="entBaseInfo.registrationNumber" value="${entBaseInfo.registrationNumber}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('registrationNumber');" onblur="checkInfo('registrationNumber');">
							<div class="tips_01" id="registrationNumberTitle" style="display:none"><p>请填写工商注册号</p></div>
							<div class="tips_02" id="registrationNumberExit" style="display:none"><p>该工商注册号已被注册</p></div>
							<div class="tips_02" id="registrationNumberNull" style="display:none"><p>工商注册号不能为空</p></div>
                        	<div class="tips_03" id="registrationNumberSucc" style="display:none"><p>工商注册号输入正确</p></div>
						</td>
					</tr>
                    <tr>
                    	<th><span class="red">*</span>注册地址：</th>
						<td>
							<input id="enterpriseAddress" name="entBaseInfo.enterpriseAddress" value="${entBaseInfo.enterpriseAddress}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseAddress');" onblur="checkInfo('enterpriseAddress');">
							<div class="tips_01" id="enterpriseAddressTitle" style="display:none"><p>请填写注册地址</p></div>
							<div class="tips_02" id="enterpriseAddressNull" style="display:none"><p>注册地址不能为空</p></div>
                        	<div class="tips_03" id="enterpriseAddressSucc" style="display:none"><p>注册地址输入正确</p></div>
						</td>
					</tr>
					<tr>
                    	<th><span class="red">*</span>生产经营地址：</th>
						<td>
							<input id="factoryAddress" name="entBaseInfo.factoryAddress" value="${entBaseInfo.factoryAddress}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('factoryAddress');" onblur="checkInfo('factoryAddress');">
							<div class="tips_01" id="factoryAddressTitle" style="display:none"><p>请填写生产经营地址</p></div>
							<div class="tips_02" id="factoryAddressNull" style="display:none"><p>生产经营地址不能为空</p></div>
                        	<div class="tips_03" id="factoryAddressSucc" style="display:none"><p>生产经营地址输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>企业属地：</th>
						<td>
							<cus:SelectOneTag property="entBaseInfo.enterprisePossession" defaultText='请选择' codeName="企业属地" value="${entBaseInfo.enterprisePossession}" style="width:300px" maxlength="127" onfocus="showTitle('enterprisePossession');" onblur="checkInfo('enterprisePossession');"/>
							<div class="tips_01" id="enterprisePossessionTitle" style="display:none"><p>请选择企业属地</p></div>
							<div class="tips_02" id="enterprisePossessionNull" style="display:none"><p>企业属地不能为空</p></div>
                        	<div class="tips_03" id="enterprisePossessionSucc" style="display:none"><p>企业属地输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>邮政编码：</th>
						<td>
							<input id="enterpriseZipcode" name="entBaseInfo.enterpriseZipcode" value="${entBaseInfo.enterpriseZipcode}" type="text" style="width:300px" maxlength="6" onfocus="showTitle('enterpriseZipcode');" onblur="checkInfo('enterpriseZipcode');">
							<div class="tips_01" id="enterpriseZipcodeTitle" style="display:none"><p>请填写邮政编码</p></div>
							<div class="tips_02" id="enterpriseZipcodeExit" style="display:none"><p>邮政编码输入错误</p></div>
							<div class="tips_02" id="enterpriseZipcodeNull" style="display:none"><p>邮政编码不能为空</p></div>
                        	<div class="tips_03" id="enterpriseZipcodeSucc" style="display:none"><p>邮政编码输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>企业性质：</th>
						<td>
							<cus:SelectOneTag property="entBaseInfo.enterpriseNature" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseNature');" onblur="checkInfo('enterpriseNature');"/>
							<div class="tips_01" id="enterpriseNatureTitle" style="display:none"><p>请选择企业性质</p></div>
							<div class="tips_02" id="enterpriseNatureNull" style="display:none"><p>企业性质不能为空</p></div>
                        	<div class="tips_03" id="enterpriseNatureSucc" style="display:none"><p>企业性质输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>企业规模(<a href="${ctx}/qygm.docx" style="text-decoration:underline;color:red">查看定义</a>)：</th>
						<td>
							<cus:SelectOneTag property="entBaseInfo.enterpriseScale" defaultText='请选择' codeName="企业规模" value="${entBaseInfo.enterpriseScale}" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseScale');" onblur="checkInfo('enterpriseScale');"/>
							<div class="tips_01" id="enterpriseScaleTitle" style="display:none"><p>请选择企业规模</p></div>
							<div class="tips_02" id="enterpriseScaleNull" style="display:none"><p>企业规模不能为空</p></div>
                        	<div class="tips_03" id="enterpriseScaleSucc" style="display:none"><p>企业规模输入正确</p></div>
						</td>	
					</tr>
					<tr>
						<th><span class="red">*</span>企业分类：</th>
						<td>
							<cus:hxcheckbox property="entBaseInfo.enterpriseType" codeName="企业分类" value="${entBaseInfo.enterpriseType}" maxlength="127" onclick="showTitle('enterpriseType');checkInfo('enterpriseType');"/><font style='color:red'>（可多选）</font>
							<div class="tips_01" id="enterpriseTypeTitle" style="display:none"><p>请选择企业分类</p></div>
							<div class="tips_02" id="enterpriseTypeNull" style="display:none"><p>企业分类不能为空</p></div>
                        	<div class="tips_03" id="enterpriseTypeSucc" style="display:none"><p>企业分类输入正确</p></div>
						</td>	
					</tr>
					<tr>
						<th><span class="red">*</span>行业类别(<a href="${ctx}/hyfl.xlsx" style="text-decoration:underline;color:red">查看定义</a>)：</th>
						<td>
							<cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseCategory');" onblur="checkInfo('enterpriseCategory');"/>
							<div class="tips_01" id="enterpriseCategoryTitle" style="display:none"><p>请选择行业类别</p></div>
							<div class="tips_02" id="enterpriseCategoryNull" style="display:none"><p>行业类别不能为空</p></div>
                        	<div class="tips_03" id="enterpriseCategorySucc" style="display:none"><p>行业类别输入正确</p></div>	
						</td>	
					</tr>
					<tr>
						<th><span class="red">*</span>投资方国籍：</th>
						<td>
							<input id="enterpriseNationnality" name="entBaseInfo.enterpriseNationnality" value="${entBaseInfo.enterpriseNationnality}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseNationnality');" onblur="checkInfo('enterpriseNationnality');">
							<div class="tips_01" id="enterpriseNationnalityTitle" style="display:none"><p>请填写投资方国籍</p></div>
							<div class="tips_02" id="enterpriseNationnalityNull" style="display:none"><p>投资方国籍不能为空</p></div>
                        	<div class="tips_03" id="enterpriseNationnalitySucc" style="display:none"><p>投资方国籍输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表姓名：</th>
						<td>
							<input id="enterpriseLegalName" name="entBaseInfo.enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" style="width:300px" maxlength="127" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalName');" onblur="checkInfo('enterpriseLegalName');">
							<div class="tips_01" id="enterpriseLegalNameTitle" style="display:none"><p>请填写法人代表姓名</p></div>
							<div class="tips_02" id="enterpriseLegalNameNull" style="display:none"><p>法人代表姓名不能为空</p></div>
                        	<div class="tips_03" id="enterpriseLegalNameSucc" style="display:none"><p>法人代表姓名输入正确</p></div>	
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表性别：</th>
						<td>
							<cus:SelectOneTag property="entBaseInfo.enterpriseLegalSex" defaultText='请选择' codeName="性别" value="${entBaseInfo.enterpriseLegalSex}" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalSex');" onblur="checkInfo('enterpriseLegalSex');"/>
							<div class="tips_01" id="enterpriseLegalSexTitle" style="display:none"><p>请选择法人代表性别</p></div>
							<div class="tips_02" id="enterpriseLegalSexNull" style="display:none"><p>法人代表性别不能为空</p></div>
                        	<div class="tips_03" id="enterpriseLegalSexSucc" style="display:none"><p>法人代表性别输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表年龄：</th>
						<td>
							<input id="enterpriseLegalAge" name="entBaseInfo.enterpriseLegalAge" value="${entBaseInfo.enterpriseLegalAge}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalAge');" onblur="checkInfo('enterpriseLegalAge');" onKeyUp="clearNoNum(event,this)">
							<div class="tips_01" id="enterpriseLegalAgeTitle" style="display:none"><p>请填写法人代表年龄</p></div>
							<div class="tips_02" id="enterpriseLegalAgeNull" style="display:none"><p>法人代表年龄不能为空</p></div>
                        	<div class="tips_03" id="enterpriseLegalAgeSucc" style="display:none"><p>法人代表年龄输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表联系电话：</th>
						<td>
							<input id="enterpriseLegalPhone" name="entBaseInfo.enterpriseLegalPhone" value="${entBaseInfo.enterpriseLegalPhone}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalPhone');" onblur="checkInfo('enterpriseLegalPhone');" >
							<div class="tips_01" id="enterpriseLegalPhoneTitle" style="display:none"><p>请填写法人代表联系电话</p></div>
							<div class="tips_02" id="enterpriseLegalPhoneNull" style="display:none"><p>法人代表联系电话不能为空</p></div>
                        	<div class="tips_03" id="enterpriseLegalPhoneSucc" style="display:none"><p>法人代表联系电话输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表证件号码：</th>
						<td>
							<input id="enterpriseLegalCardnum" name="entBaseInfo.enterpriseLegalCardnum" value="${entBaseInfo.enterpriseLegalCardnum}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalCardnum');" onblur="checkInfo('enterpriseLegalCardnum');">
							<div class="tips_01" id="enterpriseLegalCardnumTitle" style="display:none"><p>请填写法人代表证件号码</p></div>
							<div class="tips_02" id="enterpriseLegalCardnumNull" style="display:none"><p>法人代表证件号码不能为空</p></div>
                        	<div class="tips_03" id="enterpriseLegalCardnumSucc" style="display:none"><p>法人代表证件号码输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表电子邮箱：</th>
						<td>
							<input id="enterpriseLegalEmail" name="entBaseInfo.enterpriseLegalEmail" value="${entBaseInfo.enterpriseLegalEmail}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalEmail');" onblur="checkInfo('enterpriseLegalEmail');">
							<div class="tips_01" id="enterpriseLegalEmailTitle" style="display:none"><p>请填写法人代表电子邮箱</p></div>
							<div class="tips_02" id="enterpriseLegalEmailNull" style="display:none"><p>法人代表电子邮箱不能为空</p></div>
							<div class="tips_02" id="enterpriseLegalEmailExit" style="display:none"><p>法人代表电子邮箱格式错误</p></div>
                        	<div class="tips_03" id="enterpriseLegalEmailSucc" style="display:none"><p>法人代表电子邮箱输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>法人代表职务：</th>
						<td>
							<input id="enterpriseLegalZw" name="entBaseInfo.enterpriseLegalZw" value="${entBaseInfo.enterpriseLegalZw}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseLegalZw');" onblur="checkInfo('enterpriseLegalZw');">
							<div class="tips_01" id="enterpriseLegalZwTitle" style="display:none"><p>请填写法人代表职务</p></div>
							<div class="tips_02" id="enterpriseLegalZwNull" style="display:none"><p>法人代表职务不能为空</p></div>
                        	<div class="tips_03" id="enterpriseLegalZwSucc" style="display:none"><p>法人代表职务输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>企业设立日期：</th>
						<td>
							<input id="enterpriseFoundDate" name="entBaseInfo.enterpriseFoundDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseFoundDate}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});showTitle('enterpriseFoundDate')" style="width:300px" maxlength="127" onblur="checkInfo('enterpriseFoundDate');">
							<div class="tips_01" id="enterpriseFoundDateTitle" style="display:none"><p>请填写企业设立日期</p></div>
							<div class="tips_02" id="enterpriseFoundDateNull" style="display:none"><p>企业设立日期不能为空</p></div>
                        	<div class="tips_03" id="enterpriseFoundDateSucc" style="display:none"><p>企业设立日期输入正确</p></div>	
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>企业投产日期：</th>
						<td>
							<input id="enterpriseProductDate" name="entBaseInfo.enterpriseProductDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseProductDate}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'});showTitle('enterpriseProductDate')" style="width:300px" maxlength="127" onblur="checkInfo('enterpriseProductDate');">
							<div class="tips_01" id="enterpriseProductDateTitle" style="display:none"><p>请填写企业投产日期</p></div>
							<div class="tips_02" id="enterpriseProductDateNull" style="display:none"><p>企业投产日期不能为空</p></div>
                        	<div class="tips_03" id="enterpriseProductDateSucc" style="display:none"><p>企业投产日期输入正确</p></div>	
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>注册资本：</th>
						<td>
							<input id="enterpriseRegisterMoney" name="entBaseInfo.enterpriseRegisterMoney" value="${entBaseInfo.enterpriseRegisterMoney}" type="text" style="width:200px" maxlength="127" onfocus="showTitle('enterpriseRegisterMoney');" onblur="checkInfo('enterpriseRegisterMoney');" onKeyUp="clearNoNum(event,this)">
							<cus:SelectOneTag property="entBaseInfo.enterpriseRegisterMoneyDw" codeName="货币种类" value="${entBaseInfo.enterpriseRegisterMoneyDw}"  maxlength="127" style="width:100px"/>
							<div class="tips_01" id="enterpriseRegisterMoneyTitle" style="display:none"><p>请填写注册资本</p></div>
							<div class="tips_02" id="enterpriseRegisterMoneyNull" style="display:none"><p>注册资本不能为空</p></div>
                        	<div class="tips_03" id="enterpriseRegisterMoneySucc" style="display:none"><p>注册资本输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>投资总额：</th>
						<td>
							<input id="enterpriseInvestMoney" name="entBaseInfo.enterpriseInvestMoney" value="${entBaseInfo.enterpriseInvestMoney}" type="text" style="width:200px" maxlength="127" onfocus="showTitle('enterpriseInvestMoney');" onblur="checkInfo('enterpriseInvestMoney');" onKeyUp="clearNoNum(event,this)">
							<cus:SelectOneTag property="entBaseInfo.enterpriseInvestMoneyDw" codeName="货币种类" value="${entBaseInfo.enterpriseInvestMoneyDw}"  maxlength="127" style="width:100px"/>
							<div class="tips_01" id="enterpriseInvestMoneyTitle" style="display:none"><p>请填写投资总额</p></div>
							<div class="tips_02" id="enterpriseInvestMoneyNull" style="display:none"><p>投资总额不能为空</p></div>
                        	<div class="tips_03" id="enterpriseInvestMoneySucc" style="display:none"><p>投资总额输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>固定资产总额：</th>
						<td>
							<input id="enterpriseFixedassetMoney" name="entBaseInfo.enterpriseFixedassetMoney" value="${entBaseInfo.enterpriseFixedassetMoney}" type="text" style="width:200px" maxlength="127" onfocus="showTitle('enterpriseFixedassetMoney');" onblur="checkInfo('enterpriseFixedassetMoney');" onKeyUp="clearNoNum(event,this)">
							<cus:SelectOneTag property="entBaseInfo.enterpriseFixedassetMoneyDw" codeName="货币种类" value="${entBaseInfo.enterpriseFixedassetMoneyDw}"  maxlength="127" style="width:100px"/>
							<div class="tips_01" id="enterpriseFixedassetMoneyTitle" style="display:none"><p>请填写固定资产总额</p></div>
							<div class="tips_02" id="enterpriseFixedassetMoneyNull" style="display:none"><p>固定资产总额不能为空</p></div>
                        	<div class="tips_03" id="enterpriseFixedassetMoneySucc" style="display:none"><p>固定资产总额输入正确</p></div>
                        </td>
					</tr>
					<tr>
						<th><span class="red">*</span>占地面积（m2）：</th>
						<td>
							<input id="enterpriseFloorArea" name="entBaseInfo.enterpriseFloorArea" value="${entBaseInfo.enterpriseFloorArea}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseFloorArea');" onblur="checkInfo('enterpriseFloorArea');" onKeyUp="clearNoNum(event,this)">
							<div class="tips_01" id="enterpriseFloorAreaTitle" style="display:none"><p>请填写占地面积</p></div>
							<div class="tips_02" id="enterpriseFloorAreaNull" style="display:none"><p>占地面积不能为空</p></div>
                        	<div class="tips_03" id="enterpriseFloorAreaSucc" style="display:none"><p>占地面积输入正确</p></div>
                        </td>
					</tr>
					<tr>
						<th><span class="red">*</span>办公楼建筑面积（m2）：</th>
						<td>
							<input id="enterpriseOfficeArea" name="entBaseInfo.enterpriseOfficeArea" value="${entBaseInfo.enterpriseOfficeArea}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseOfficeArea');" onblur="checkInfo('enterpriseOfficeArea');" onKeyUp="clearNoNum(event,this)">
							<div class="tips_01" id="enterpriseOfficeAreaTitle" style="display:none"><p>请填写办公楼建筑面积</p></div>
							<div class="tips_02" id="enterpriseOfficeAreaNull" style="display:none"><p>办公楼建筑面积不能为空</p></div>
                        	<div class="tips_03" id="enterpriseOfficeAreaSucc" style="display:none"><p>办公楼建筑面积输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>车间厂房建筑面积（m2）：</th>
						<td>
							<input id="enterpriseWorkshopArea" name="entBaseInfo.enterpriseWorkshopArea" value="${entBaseInfo.enterpriseWorkshopArea}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseWorkshopArea');" onblur="checkInfo('enterpriseWorkshopArea');" onKeyUp="clearNoNum(event,this)">
							<div class="tips_01" id="enterpriseWorkshopAreaTitle" style="display:none"><p>请填写车间厂房建筑面积</p></div>
							<div class="tips_02" id="enterpriseWorkshopAreaNull" style="display:none"><p>车间厂房建筑面积不能为空</p></div>
                        	<div class="tips_03" id="enterpriseWorkshopAreaSucc" style="display:none"><p>车间厂房建筑面积输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>仓库建筑面积（m2）：</th>
						<td>
							<input id="enterpriseWearhouseArea" name="entBaseInfo.enterpriseWearhouseArea" value="${entBaseInfo.enterpriseWearhouseArea}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseWearhouseArea');" onblur="checkInfo('enterpriseWearhouseArea');" onKeyUp="clearNoNum(event,this)">
							<div class="tips_01" id="enterpriseWearhouseAreaTitle" style="display:none"><p>请填写仓库建筑面积</p></div>
							<div class="tips_02" id="enterpriseWearhouseAreaNull" style="display:none"><p>仓库建筑面积不能为空</p></div>
                        	<div class="tips_03" id="enterpriseWearhouseAreaSucc" style="display:none"><p>仓库建筑面积输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>厂房归属：</th>
						<td>
							<cus:SelectOneTag property="entBaseInfo.enterprisWorkshopOwn" defaultText='请选择' codeName="厂房归属" value="${entBaseInfo.enterprisWorkshopOwn}" style="width:300px" maxlength="127" onfocus="showTitle('enterprisWorkshopOwn');" onblur="checkInfo('enterprisWorkshopOwn');" onchange="changeOwn(this.value)"/>
							<div class="tips_01" id="enterprisWorkshopOwnTitle" style="display:none"><p>请选择厂房归属</p></div>
							<div class="tips_02" id="enterprisWorkshopOwnNull" style="display:none"><p>厂房归属不能为空</p></div>
                        	<div class="tips_03" id="enterprisWorkshopOwnSucc" style="display:none"><p>厂房归属输入正确</p></div>
						</td>
					</tr>
					<tr id="ownerdiv1" style="display:none">
						<th><span class="red">*</span>出租方：</th>
						<td>
							<input id="houseOwner" name="entBaseInfo.houseOwner" value="${entBaseInfo.houseOwner}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('houseOwner');" onblur="checkInfo('houseOwner');">
							<div class="tips_01" id="houseOwnerTitle" style="display:none"><p>请输入出租方</p></div>
							<div class="tips_02" id="houseOwnerNull" style="display:none"><p>出租方不能为空</p></div>
                        	<div class="tips_03" id="houseOwnerSucc" style="display:none"><p>出租方输入正确</p></div>
						</td>
					</tr>
					<tr id="ownerdiv2" style="display:none">
						<th><span class="red">*</span>出租方联系方式：</th>
						<td>
							<input id="ownerTel" name="entBaseInfo.ownerTel" value="${entBaseInfo.ownerTel}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('ownerTel');" onblur="checkInfo('ownerTel');">
							<div class="tips_01" id="ownerTelTitle" style="display:none"><p>请输入出租方联系方式</p></div>
							<div class="tips_02" id="ownerTelNull" style="display:none"><p>出租方联系方式不能为空</p></div>
                        	<div class="tips_03" id="ownerTelSucc" style="display:none"><p>出租方联系方式输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>管理人员数：</th>
						<td>
							<input id="enterpriseManagerCount" name="entBaseInfo.enterpriseManagerCount" value="${entBaseInfo.enterpriseManagerCount}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseManagerCount');" onblur="checkInfo('enterpriseManagerCount');" onKeyUp="validate(event,this)">
							<div class="tips_01" id="enterpriseManagerCountTitle" style="display:none"><p>请填写管理人员数</p></div>
							<div class="tips_02" id="enterpriseManagerCountNull" style="display:none"><p>管理人员数不能为空</p></div>
                        	<div class="tips_03" id="enterpriseManagerCountSucc" style="display:none"><p>管理人员数输入正确</p></div>
						</td>
					</tr>
					<tr>	
						<th><span class="red">*</span>工人数：</th>
						<td>
							<input id="enterpriseWorkerCount" name="entBaseInfo.enterpriseWorkerCount" value="${entBaseInfo.enterpriseWorkerCount}" type="text" style="width:300px" maxlength="127" onfocus="showTitle('enterpriseWorkerCount');" onblur="checkInfo('enterpriseWorkerCount');" onKeyUp="validate(event,this)">
							<div class="tips_01" id="enterpriseWorkerCountTitle" style="display:none"><p>请填写工人数</p></div>
							<div class="tips_02" id="enterpriseWorkerCountNull" style="display:none"><p>工人数不能为空</p></div>
                        	<div class="tips_03" id="enterpriseWorkerCountSucc" style="display:none"><p>工人数输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>经营范围：</th>
						<td>
							<textarea id="enterpriseScope" name="entBaseInfo.enterpriseScope" style="width:50%;height:120px" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" onfocus="showTitle('enterpriseScope');" onblur="checkInfo('enterpriseScope');">${entBaseInfo.enterpriseScope}</textarea>
							<div class="tips_01" id="enterpriseScopeTitle" style="display:none"><p>请填写经营范围</p></div>
							<div class="tips_02" id="enterpriseScopeNull" style="display:none"><p>经营范围不能为空</p></div>
                        	<div class="tips_03" id="enterpriseScopeSucc" style="display:none"><p>经营范围输入正确</p></div>
						</td>
					</tr>
					<tr>
						<th rowspan="2"><span class="red">*</span>厂区平面图：</th>
						<td>
				   			<div id="fileQueue"/>
				    		<input type="file" name="uploadify" id="uploadify"/>
			    			<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
							<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
							<font style='color:red'>上传附件最大上限为50M</font>
				    	</td>
					</tr>
					<tr>
						<td>
							<table id="cqtp">
							</table>
						</td>
					</tr>
                    <tr>
                    	<th></th>
                        <td><a href="#" class="btn_next" onclick="save()">下一步</a></td>
                    </tr> 
                                       
                </table>
            </div>


            
        </div>
    </div>
    </div>
</div>

<!--底部-->
<div class="bottom">
    <span>Copyright ©2015</span>
    <span>苏州工业园安全生产监督管理局  版权所有.</span>
    <span>ALL RIGHTS RESERVED.</span>
    <p><span>苏ICP备08103496号-4</span>
    <span><a href="#">技术支持：南京拓构软件有限公司</a></span>
    </p>
</div>
</form>
</body>
</html>
