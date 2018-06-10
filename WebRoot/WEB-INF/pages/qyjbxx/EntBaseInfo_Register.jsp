<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<!-- jquery & easyui js -->
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/bootstrapvalidator/vendor/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="${ctx}/bootstrapvalidator/dist/css/bootstrapValidator.css"/>
<script type="text/javascript" src="${ctx}/bootstrapvalidator/dist/js/language/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/citySelect/jquery.cityselect.js"></script>

<link href="${ctx}/webResources/qyzc/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{
		border: 1px solid #ccc;
		    border-radius: 4px;
    	font-size: 16px;
	}
	label{
		    font-size: 14px;
		        color: #3c763d;
	}
</style>


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
			else if(obj == 'enterpriseName')
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
					var enterpriseCodeReg = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
					var uniteSocialCodeReg = /^[a-zA-Z0-9]{18}$/;
					if(!enterpriseCodeReg.test(ss) && !uniteSocialCodeReg.test(ss))
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
				}else if(obj == 'enterpriseName')
				{
					document.getElementById('enterpriseNameSucc').style.display = "";
					document.getElementById(obj).disabled = false;
				}
				return true;
			}
		}
		
        
</script>


</head>

<body >
<!-- <form id="myform1" name="myform1" method="post" enctype="multipart/form-data"> -->
<div class="topbar">
	<%-- <a href="#"><img src="${ctx}/webResources/qyzc/images/default/reg_logo.png"/></a> --%>
    <div class="user">新会员注册</div>
</div>

<form id="defaultForm" method="post" class="form-horizontal" action="${ctx}/jsp/qyjbxx/entBaseInfoNextRegister.action">
  <div class="form-group">
    <label class="col-lg-3 control-label">组织机构代码/统一社会信用代码：</label>
    <div class="col-lg-5">
      <input type="text" class="form-control" name="enterpriseCode" value="${enterpriseCode }"/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">企业名称</label>
    <div class="col-lg-5">
      <input type="text" class="form-control" name="enterpriseName"  value="${enterpriseName }"/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">用户名</label>
    <div class="col-lg-5">
      <input type="text" class="form-control" name="loginId"  value="${loginId }"/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">密码</label>
    <div class="col-lg-5">
      <input type="password" class="form-control" name="password"  value="${password }"/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">确认密码</label>
    <div class="col-lg-5">
      <input type="password" class="form-control" name="confirmPassword"  value="${password }"/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">所在地区</label>
    <div class="col-lg-5" id="city">
    	<input type="hidden" id="areaId" name="entBaseInfo.enterprisePossession" value="">
    	<input type="hidden" id="areaName" name="entBaseInfo.enterprisePossessionName" value="">
    	<input type="hidden" id="localAreaName" value="true">
        <select class="prov" disabled="disabled"></select> 
        <select class="city" disabled="disabled" hidden></select>
        <select class="dist" disabled="disabled" hidden></select>
        <select class="street" disabled="disabled" hidden></select>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">直管部门级别</label>
    <div class="col-lg-5">
    	<cus:SelectOneTag property="entBaseInfo.departmentalLevel"  codeName="直管部门级别" value="${entBaseInfo.departmentalLevel}" defaultText='请选择'/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">营业执照类型</label>
    <div class="col-lg-5">
    	<cus:SelectOneTag property="entBaseInfo.businessLicence"  codeName="营业执照类型" value="${entBaseInfo.businessLicence}" defaultText='请选择'/>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <div class="form-group">
    <label class="col-lg-3 control-label">行业类别(<a href="${ctx}/hyfl.xlsx" style="text-decoration:underline;color:red">查看定义</a>)</label>
    <div class="col-lg-5">
    	<cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}" style="width:150px" maxlength="127"  onchange="getHyxl(this.value)"/>
		<s:select theme="simple" cssStyle="width:25%;" id="jjlx" name="entBaseInfo.jjlx" list="%{hylist}" listKey="id" listValue="name" style="width:150px" ></s:select>
    </div>
    <font class="col-lg-1 control-label" style='color:red;    text-align: left;'>*</font>
  </div>
  <!-- <div class="form-group">
    <label class="col-lg-3 control-label" id="captchaOperation"></label>
    <div class="col-lg-2">
      <input type="text" class="form-control" name="captcha" />
    </div>
  </div> -->
  <div class="form-group">
    <div class="col-lg-9 col-lg-offset-3">
      <button type="submit" class="btn btn-primary">注册</button>
      <button type="button" class="btn btn-info" id="resetBtn">重置</button>
    </div>
  </div>
</form>  

<%-- <div class="af_add">
    <div class="padd20">
    <div class="addcontent">
        <div class="steps steps-3">
            <ul>
                <li class="">企业用户信息填写</li>
            </ul>	
        </div>
        <div class="steps_main">
            <div class="steps_01">
                <table width="80%"  border="0" cellpadding="0" cellspacing="0">
                	<tr>
						<th><span class="red">*</span>组织机构代码/统一社会信用代码：</th>
						<td>
							<input id="enterpriseCode" name="entBaseInfo.enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" style="width:300px" onfocus="showTitle('enterpriseCode');" onblur="checkInfo('enterpriseCode');" maxlength="18">
							<div class="tips_01" id="enterpriseCodeTitle" style="display:none"><p>请填写组织机构代码/统一社会信用代码</p></div>
							<div class="tips_02" id="enterpriseCodeExit" style="display:none"><p>该组织机构代码/统一社会信用代码已被注册</p></div>
							<div class="tips_02" id="enterpriseCodeError" style="display:none"><p>组织机构代码/统一社会信用代码格式有误</p></div>
							<div class="tips_02" id="enterpriseCodeNull" style="display:none"><p>组织机构代码/统一社会信用代码不能为空</p></div>
                        	<div class="tips_03" id="enterpriseCodeSucc" style="display:none"><p>组织机构代码/统一社会信用代码输入正确</p></div>
						</td>
                    </tr>
                    <tr>
                        <th width="18%"><span class="red">*</span>企业名称：</th>
                        <td width="62%">
                        	<input id="enterpriseName" name="entBaseInfo.enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" style="width:300px" maxlength="20" onfocus="showTitle('enterpriseName');" onblur="checkInfo('enterpriseName');" >
                        	<div class="tips_01" id="enterpriseNameTitle" style="display:none"><p>请填写企业名称</p></div>
                        	<div class="tips_03" id="enterpriseNameSucc" style="display:none"><p>企业名称输入正确</p></div>
                        	<div class="tips_02" id="enterpriseNameNull" style="display:none"><p>企业名称不能为空</p></div>
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
</div> --%>

<!--底部-->
<div class="bottom">
    <span>Copyright ©2017</span>
    <span>智慧安监管理平台  版权所有.</span>
    <span>ALL RIGHTS RESERVED.</span>
    <p><span>苏ICP备08103496号-4</span>
    <span><a href="#">技术支持：南京拓构软件有限公司</a></span>
    </p>
</div>
<!-- </form> -->
<script type="text/javascript">
  $(document).ready(function() {
  // Generate a simple captcha
	  function randomNumber(min, max) {
	  	return Math.floor(Math.random() * (max - min + 1) + min);
	  };
	  $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));
	  $('#resetBtn').click(function() {
		$('#defaultForm').data('bootstrapValidator').resetForm(true);
	  });
	//省地市级联初始化
  	$("#city").citySelect();
	 
	  $('#defaultForm').bootstrapValidator({
		  
	  	message: 'This value is not valid',
		  fields: {
			  enterpriseCode: {
			      message: 'The username is not valid',
			      validators: {
				      notEmpty: {
				     	 message: '组织机构代码/统一社会信用代码不能为空'
				      },
				      stringLength: {
					      min: 10,
					      max: 18,
					      message: '组织机构代码为/统一社会信用代码长度不能低于10位或高于18位'
				      },
				      regexp: {
				        regexp: /^[a-zA-Z0-9]{18}$|^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/,
				        message: '组织机构代码（示例：12345678-9）/统一社会信用代码（G1234567891234567D）'
				      },
				      remote: {
		                    type: 'POST',
		                    url: 'isUserExit.action',
		                    message: '此代码已存在',
		                    delay: 2000

		                }
			       }
		    	},
		    	enterpriseName: {
			      message: 'The username is not valid',
			      validators: {
				      notEmpty: {
				     	 message: '企业名称不能为空'
				      },
				      stringLength: {
					      max: 50,
					      message: '企业名称不能超过50个字符'
				      }
			       }
		    	},
		    	loginId: {
			      validators: {
			        notEmpty: {
			          message: '用户名不能为空'
			        },
			        stringLength: {
					      max: 18,
					      message: '用户名长度不能高于18位'
				      },
				      regexp: {
				        regexp: /^[0-9A-Za-z_]{5,18}$/,
				        message: '用户名只支持数字、字母、下划线'
				      },
				      remote: {
		                    type: 'POST',
		                    url: 'isUserExit.action',
		                    message: '此代码已存在',
		                    delay: 2000

		                }
			      }
			    },
			    password: {
				    validators: {
				      	notEmpty: {
				          message: '密码不能为空'
				        },
				        different: {
				          field: 'loginId',
				          message: '密码不能与用户名相同'
				        }
			      	}
			    },
			    confirmPassword: {
			      validators: {
			        notEmpty: {
			       		message: '确认密码不能为空'
			        },
			        identical: {
				        field: 'password',
				        message: '两次输入的密码不一致'
			        },
			        different: {
				        field: 'loginId',
				        message: '密码不能与用户名相同'
			        }
			      }
			    },
			    captcha: {
			      validators: {
			        callback: {
			          message: '答案错误',
			          callback: function(value, validator) {
				          var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
				          return value == sum;
			          }
			        }
			      }
			    }
		  }
	  });
});
  
  function getHyxl(obj)
  {
  	$.ajax({
				type:"POST",
				url:"${ctx}/jsp/qyjbxx/getHylx.action?mode=ajaxJson&entBaseInfo.enterpriseCategory="+obj,
				success:function(json){
					json = eval('(' + json + ')');
					var selectContainer = $('#jjlx'); 
					selectContainer.empty();
					var option = document.createElement("OPTION");
      			selectContainer.append(option);//先追加
      			option.innerText = "请选择";
      			option.value = "";
	  				for(var i=0; i<json.length; i++){
						var option = document.createElement("OPTION");
      				selectContainer.append(option);//先追加
      				option.innerText = json[i].name;
      				option.value = json[i].id;
				 	}
				},
				dateType:"json"
			});
  }
</script>
</body>
</html>
