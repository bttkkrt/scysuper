<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业注册</title>
<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
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
//监测用户名是否存在
function checkUsername(){
	var username = document.getElementById("loginname");
	var viewobj = document.getElementById("checkResult");
	if(username.value==''||username.value.length<5)
	{
		$("#checkResult").removeClass("jd-validator-msg");
		$("#checkResult").removeClass("jd-validator-msg rightinput");
		$("#checkResult").removeClass("jd-validator-msg focus");
		$("#checkResult").addClass("jd-validator-msg errinput");	
		viewobj.innerHTML = "请按规则填写用户名";
		return false;
	}
	
	$("#checkResult").removeClass("jd-validator-msg");
	$("#checkResult").removeClass("jd-validator-msg rightinput");
	$("#checkResult").removeClass("jd-validator-msg errinput");
	$("#checkResult").addClass("jd-validator-msg focus");	
	viewobj.innerHTML = "正在检测中...";
	$.ajax({
		url : "isCompanyExsit.action?company.loginname="+ username.value,
	    type: 'post',
	    dataType: 'json',
	    async : false,
	    data:{ 
	    },
	    error: function(){
	    	$("#checkResult").removeClass("jd-validator-msg");
			$("#checkResult").removeClass("jd-validator-msg rightinput");
			$("#checkResult").removeClass("jd-validator-msg focus");
			$("#checkResult").addClass("jd-validator-msg errinput");	
	    	viewobj.innerHTML = "检测出错";
	    },
	    success: function(data){
	    	if(data.result == 'true'){
	        	 $("#checkResult").removeClass("jd-validator-msg");
				$("#checkResult").removeClass("jd-validator-msg rightinput");
				$("#checkResult").removeClass("jd-validator-msg focus");
				$("#checkResult").addClass("jd-validator-msg errinput");	
	        	viewobj.innerHTML = "该用户名已经存在";
	        }else{
	        	$("#checkResult").removeClass("jd-validator-msg");
				$("#checkResult").removeClass("jd-validator-msg errinput");
				$("#checkResult").removeClass("jd-validator-msg focus");
				$("#checkResult").addClass("jd-validator-msg rightinput");	
	            viewobj.innerHTML = "该用户名可以使用";
	        }
	   }
	});
}

//监测2次密码是否相同
function checkForm(){
    var form=document.forms[0];
	if(form.loginword.value!=form.reloginword.value){
		$("#HackBox").removeClass("jd-validator-msg");
		$("#HackBox").removeClass("jd-validator-msg rightinput");
		$("#HackBox").removeClass("jd-validator-msg focus");
		$("#HackBox").addClass("jd-validator-msg errinput");	
		document.getElementById('HackBox').innerHTML='两次输入的密码不正确';
	}else
	{
		$("#HackBox").removeClass("jd-validator-msg errinput");
		$("#HackBox").removeClass("jd-validator-msg focus");
		$("#HackBox").addClass("jd-validator-msg rightinput");	
		document.getElementById('HackBox').innerHTML='密码输入正确';
		
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

function next(){
	checkUsername();
	if(document.getElementById("checkResult").innerHTML == '该用户名可以使用')
	{
		document.myform1.action="companyRegisterNextUI.action?flag=2";
		document.myform1.submit();
	}else{
		return false;
	}
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
		<div class="wrap" >
			<div class="steps">
				<span class="current">1.登录信息</span><b class="i2"></b><span>2.基本信息</span><b></b><span>3.经营信息</span><b></b><span>4.分类信息</span><b></b><span>5.注册完成</span>
			</div>
			<div class="cell" >
				<form id="myform1" name="myform1" method="post" class="jd-vali-form">
				<s:hidden name="company.longitude"/>
				<s:hidden name="company.latitude"/>
				<s:hidden name="company.szc"/>
				<s:hidden name="company.szcname"/>
				<s:hidden name="company.companyname"/>
				<s:hidden name="company.county"/>
				<s:hidden name="company.dwdz1"/>
				<s:hidden name="company.dwdz2"/>
				<s:hidden name="company.gszch"/>
				<s:hidden name="company.zzjgdm"/>
				<s:hidden name="company.qyclsj"/>
				<s:hidden name="company.ifurl"/>
				<s:hidden name="company.url"/>
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
				<s:hidden name="company.aqscxkzh"/>
				<s:hidden name="company.feature"/>
				<div class="bg_01">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" style="text-align: center;">
					<tr>
						<td width="20%" align="right"><span class="red">*</span>用户名</td>
						<td width="80%">
							<div class="jd-vali-linediv">
							<input class="jd-validator" type="text" id="loginname" name="company.loginname" value="${company.loginname}" style="width:338px;"  focusmsg="5-20个字符(包括英文（区分大小写）、数字、下划线)！"  nullmsg="请填写用户名！" errormsg='用户名只能5-20个字符(包括英文（区分大小写）、数字、下划线)!' rightmsg='用户名输入正确！' datatype="[_a-zA-Z0-9][_a-zA-Z0-9]{4,20}" dataType="Require" minlength="5"maxlength="20"/>
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td style="text-align: center;">
						<div class="btn_area_bg" style="float: left;width:338px;">
									<a href="javaScript:void(0)" onClick="checkUsername()" class="btn_01">检查用户名是否可用<b></b></a>
							</div>
							<div class="jd-validator-msg" id="checkResult" align="left" ></div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red">*</span>密码</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.loginword" id="loginword"  class="jd-validator" style="width: 338px" focusmsg="5-15个字符!"  nullmsg="请填写密码！" errormsg='密码只能5-15个字符!'  rightmsg='密码输入正确！' datatype="*5-20" value="${company.loginword}" type="password" minlength="5" maxlength="15" >
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red">*</span>确认密码</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="reloginword" id="reloginword" value="${reloginword}"  class="jd-validator" style="width: 338px" focusmsg="5-15个字符!"  nullmsg="请填写密码！" errormsg='密码只能5-15个字符!' rightmsg='密码输入正确！'  datatype="*5-20"  type="password" minlength="5" maxlength="15"  onkeyup ="checkForm()">
							<div class="jd-validator-msg" id="HackBox"> </div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right"><span class="red">*</span>法定代表人</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.fddbr" value="${company.fddbr}" class="jd-validator" style="width: 338px" type="text" focusmsg="请填写法定代表人"  nullmsg="此项为必填！" errormsg='' rightmsg='法定代表人输入正确！' datatype="*" maxlength="255">
							<div class="jd-validator-msg" ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red">*</span>法人联系号码</td>
						<td>
							<div class="jd-vali-linediv">
							<input id="fddbrlxhm" name="company.fddbrlxhm" value="${company.fddbrlxhm}"  class="jd-validator" style="width: 338px" type="text" maxlength="255" focusmsg="法定代表人手机号码或固定电话(格式：18941316001 或024-1234567)"  nullmsg="此项为必填！" errormsg='联系号码格式不正确！'   rightmsg='联系号码输入正确！' datatype="^(\d{3,4}\-?)?\d{7,11}$">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red"></span>安全管理员</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.lxr" value="${company.lxr}" class="jd-validator" style="width: 338px" type="text"  focusmsg="请填写安全管理员！"  nullmsg="此项为必填！" errormsg=''  rightmsg='安全管理员输入正确！' datatype="*" maxlength="255" >
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red"></span>安全管理员手机号码</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.mobile" value="${company.mobile}"  class="jd-validator" style="width: 338px" type="text" focusmsg="请填写安全管理员手机号码！"  nullmsg="此项为必填！" errormsg='手机号码必须是数字！'  rightmsg='安全管理员手机号码输入正确！' datatype="n"   maxlength="11">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right"><span class="red"></span>安全管理员固话</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.lxfs" value="${company.lxfs}" class="jd-validator" style="width: 338px" id='lxfs' type="text"  id="lxfs" focusmsg="请填写安全管理员固定电话(格式：024-1234567)"  nullmsg="此项为必填！" errormsg='电话格式不正确！' rightmsg='安全管理员固定电话输入正确！'  datatype="\d{2,3}-\d{7,8}" maxlength="255">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					
					<tr>
						<td align="right"><span class="red"></span>固定电话</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.gddh"  class="jd-validator" style="width: 338px" value="${company.gddh}" type="text" maxlength="255"  focusmsg="请填写固定电话(格式：024-1234567)"  nullmsg="此项为必填！" errormsg='固定电话格式不规范！'  rightmsg='固定电话输入正确！' datatype="\d{2,3}-\d{7,8}">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red"></span>企业邮箱</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.qyyx" value="${company.qyyx}" onKeyUp="value=value.replace(/^([\u4E00-\u9FA5]|[\uFE30-\uFFA0])*$/gi,'')"  class="jd-validator" style="width: 338px" id='qyyx' type="text"  maxlength="255" focusmsg="请填写企业邮箱(格式：123@126.com)"  nullmsg="此项为必填！" errormsg='邮箱格式不正确！'  rightmsg='企业邮箱输入正确！'  datatype="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td align="right"><span class="red"></span>传真</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.cz" id='cz'  class="jd-validator" style="width: 338px" value="${company.cz}" type="text" maxlength="255"  focusmsg="请填写传真(格式：024-1234567)"  nullmsg="此项为必填！" errormsg='传真号码格式不正确！'  rightmsg='传真输入正确！' datatype="\d{2,3}-\d{7,8}">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"><span class="red"></span>邮政编码</td>
						<td>
							<div class="jd-vali-linediv">
							<input name="company.yzbm" class="jd-validator" style="width: 338px" value="${company.yzbm}" type="text"  focusmsg="请填写邮政编码"  nullmsg="此项为必填！" errormsg='邮政编码不规范' rightmsg='邮政编码输入正确！'   datatype="[1-9]\d{5}(?!\d)"maxlength="255">
							<div class="jd-validator-msg"  ></div>
							</div>
						</td>
					</tr>
					<tr>
						<td  align="right"></td>
						<td>
							<div class="login_subfield_btnarea"  style="float: left; margin-left: 155px;">
								<input id="submitform" style="display:none;" type="button" onclick="next();"/>
								<a href="javaScript:void(0)"  class="btn_next checkformdata" onclick="next();">下一步</a>
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