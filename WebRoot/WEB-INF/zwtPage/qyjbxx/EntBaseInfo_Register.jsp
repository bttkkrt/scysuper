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
        function chanageDiv(){
        	if(
        		checkInfo('enterpriseCode')&&
        		checkInfo('loginId')&&
        		checkInfo('password')&&
        		checkInfo('repassword')
        	)
        	{
        		document.myform1.action = "${ctx}/jsp/qyjbxx/entBaseInfoNextRegister.action";
        		document.myform1.submit();
        	}
        }
        
		function showTitle(obj)
		{
			document.getElementById(obj+'Null').style.display = "none";
			document.getElementById(obj+'Succ').style.display = "none";
			if(obj == 'loginId')
			{
				document.getElementById('loginIdExit').style.display = "none";
				document.getElementById('loginIdError').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'password')
			{
				document.getElementById('passwordError').style.display = "none";
				document.getElementById('passwordEasy').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'repassword')
			{
				document.getElementById('repasswordExit').style.display = "none";
				document.getElementById(obj+'Title').style.display = "";
			}
			else if(obj == 'enterpriseCode')
			{
				document.getElementById('enterpriseCodeExit').style.display = "none";
				document.getElementById('enterpriseCodeError').style.display = "none";
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
				if(obj == 'loginId')
				{
					document.getElementById('loginIdExit').style.display = "none";
					document.getElementById('loginIdError').style.display = "none";
					var loginReg = /^[0-9A-Za-z_]{5,20}$/;
					if(!loginReg.test(ss))
					{
						document.getElementById('loginIdError').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{
						$.ajax({
							url : "isUserExit.action",
							type: 'post',
							dataType: 'json',
							async : false,
							data:{ 
								"entBaseInfo.loginId" : ss
							},
							error: function(){
								alert('验证用户名时出错！');
							},
							success: function(data){
								if(data.result == 'true'){
									document.getElementById('loginIdExit').style.display = "";
									document.getElementById(obj).disabled = false;
									return false;
								}else{
									document.getElementById('loginIdSucc').style.display = "";
									document.getElementById(obj).disabled = false;
								}
							}
						});				
					}
				}
				else if(obj == 'password')
				{
					document.getElementById('passwordError').style.display = "none";
					document.getElementById('passwordEasy').style.display = "none";
					if(ss.length < 5)
					{
						document.getElementById('passwordError').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{
						var ls = 0;
 						if(ss.match(/([a-z])+/)){
     						ls++;
  						}
 						if(ss.match(/([0-9])+/)){
      			 			ls++;  
 						}
 						if(ss.match(/([A-Z])+/)){
        					ls++;
  						}
  						if(ss.match(/[^a-zA-Z0-9]+/)){
        					ls++;
    					}
    					if(ls < 3)
    					{
    						document.getElementById('passwordEasy').style.display = "";
							document.getElementById(obj).disabled = false;
							return false;
    					}
    					else
    					{
    						document.getElementById('passwordSucc').style.display = "";
							document.getElementById(obj).disabled = false;
    					}
					}
 				}
				else if(obj == 'repassword')
				{
					document.getElementById('repasswordExit').style.display = "none";
					var pa = document.getElementById('password').value;
					if(pa != ss)
					{
						document.getElementById('repasswordExit').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{	
						document.getElementById('repasswordSucc').style.display = "";
						document.getElementById(obj).disabled = false;
					}
				}
				else if(obj == 'enterpriseCode')
				{
					document.getElementById('enterpriseCodeExit').style.display = "none";
					document.getElementById('enterpriseCodeError').style.display = "none";
					var enterpriseCodeReg = /^[0-9A-Z]{9}$/;
					if(!enterpriseCodeReg.test(ss))
					{
						document.getElementById('enterpriseCodeError').style.display = "";
						document.getElementById(obj).disabled = false;
						return false;
					}
					else
					{
						$.ajax({
							url : "isUserExit.action",
							type: 'post',
							dataType: 'json',
							async : false,
							data:{ 
								"entBaseInfo.enterpriseCode" : ss
							},
							error: function(){
								alert('验证组织机构代码时出错！');
							},
							success: function(data){
								if(data.result == '0'){
									document.getElementById('enterpriseCodeSucc').style.display = "";
									document.getElementById(obj).disabled = false;
								}else if(data.result == '1'){
									document.getElementById('enterpriseCodeSucc').style.display = "";
									document.getElementById(obj).disabled = false;
								}else if(data.result == '2'){
									document.getElementById('enterpriseCodeError').style.display = "";
									document.getElementById(obj).disabled = false;
									return false;
								}else if(data.result == '3'){
									document.getElementById('enterpriseCodeExit').style.display = "";
									document.getElementById(obj).disabled = false;
									return false;
								}
							}
						});	
					}		
				}
				return true;
			}
		}
		
        
</script>


</head>

<body >
<form id="myform1" name="myform1" method="post" enctype="multipart/form-data">
<div class="topbar">
	<a href="#"><img src="${ctx}/webResources/qyzc/images/default/reg_logo.png"/></a>
    <div class="user">新会员注册</div>
</div>

<div class="af_add">
    <div class="padd20">
    <div class="addcontent">
        <div class="steps steps-3">
            <ul>
                <li class="active"><i>1</i>企业用户信息填写</li>
                <li><i>2</i>填写详细信息</li>
                <li><i>3</i>完成注册</li>
            </ul>	
        </div>
        <div class="steps_main">
            <div class="steps_01">
                <table width="80%"  border="0" cellpadding="0" cellspacing="0">
                	<tr>
						<th><span class="red">*</span>组织机构代码：</th>
						<td>
							<input id="enterpriseCode" name="entBaseInfo.enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" style="width:300px" onfocus="showTitle('enterpriseCode');" onblur="checkInfo('enterpriseCode');" maxlength="9">
							<div class="tips_01" id="enterpriseCodeTitle" style="display:none"><p>请填写组织机构代码</p></div>
							<div class="tips_02" id="enterpriseCodeExit" style="display:none"><p>该组织机构代码已被注册</p></div>
							<div class="tips_02" id="enterpriseCodeError" style="display:none"><p>组织机构代码填写有误，只保留数字和字母，去除-等特殊符号</p></div>
							<div class="tips_02" id="enterpriseCodeNull" style="display:none"><p>组织机构代码不能为空</p></div>
                        	<div class="tips_03" id="enterpriseCodeSucc" style="display:none"><p>组织机构代码输入正确</p></div>
						</td>
                    </tr>
                    <tr>
                        <th width="18%"><span class="red">*</span>用户名：</th>
                        <td width="62%">
                        	<input id="loginId" name="entBaseInfo.loginId" value="${entBaseInfo.loginId}" type="text" style="width:300px" maxlength="20" onfocus="showTitle('loginId');" onblur="checkInfo('loginId');" onKeyUp="value=value.replace(/[\W]/g,'')">
                        	<div class="tips_01" id="loginIdTitle" style="display:none"><p>请填写用户名(5-20个字符)，一旦注册成功用户名不能修改</p></div>
                        	<div class="tips_02" id="loginIdExit" style="display:none"><p>该用户名已被注册</p></div>
                        	<div class="tips_03" id="loginIdSucc" style="display:none"><p>用户名输入正确</p></div>
                        	<div class="tips_02" id="loginIdError" style="display:none"><p>用户名填写错误，请重新输入</p></div>
                        	<div class="tips_02" id="loginIdNull" style="display:none"><p>用户名不能为空</p></div>
                        </td>
                    </tr> 
                    <tr>
                        <th><span class="red">*</span>密码：</th>
                        <td>
                        	<input id="password" name="entBaseInfo.password" value="${entBaseInfo.password}" type="password" style="width:300px" maxlength="15" onfocus="showTitle('password');" onblur="checkInfo('password');">
							<div class="tips_01" id="passwordTitle" style="display:none"><p>请填写密码(5-15个字符)</p></div>
                        	<div class="tips_02" id="passwordError" style="display:none"><p>密码长度过短</p></div>
                        	<div class="tips_03" id="passwordSucc" style="display:none"><p>密码输入正确</p></div>
                        	<div class="tips_02" id="passwordNull" style="display:none"><p>密码不能为空</p></div>
                        	<div class="tips_02" id="passwordEasy" style="display:none"><p>密码必须是大写字母，小写字母，数字，特殊字符中任意三个组合</p></div>
						</td>
                    </tr>
                    <tr>
                        <th><span class="red">*</span>确认密码：</th>
                        <td>
                        	<input id="repassword" type="password"  maxlength="15" value="${company.loginword}" style="width:300px" onfocus="showTitle('repassword');" onblur="checkInfo('repassword');">
                        	<div class="tips_01" id="repasswordTitle" style="display:none"><p>请再次填写密码</p></div>
                        	<div class="tips_02" id="repasswordExit" style="display:none"><p>两次输入的密码不一致</p></div>
                        	<div class="tips_03" id="repasswordSucc" style="display:none"><p>密码输入正确</p></div>
                        	<div class="tips_02" id="repasswordNull" style="display:none"><p>密码不能为空</p></div>
                        </td>
                    </tr>  
                    <tr>
                        <th></th>
                        <td><a href="#" class="btn_next" onclick="chanageDiv()">下一步</a></td>
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
