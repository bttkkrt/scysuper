<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>企业注册</title>
		<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/webResources/js/xmlhttp.js"></script>
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
		function onlyNum()
		{ 
			var keys=event.keyCode;
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

function next(){
		document.myform1.action="companyRegisterNextUI.action?flag=4";
		document.myform1.submit();
}

function pre(){
	document.myform1.action="companyRegisterNextUI.action?flag=2";
	document.myform1.submit();
}
</script>
</head>
<body>
	<div class="ajtop">
		<div class="ajtopav">
			<div class="ajlogo">企业基本信息注册</div>
			<div class="user"></div>
		</div>
		<div class="content">
			<div class="wrap">
				<div class="steps">
					<div class="steps">
						<span>1.登录信息</span><b></b><span>2.基本信息</span><b class="i1"></b><span
							class="current">3.经营信息</span><b class="i2"></b><span>4.分类信息</span><b></b><span>5.注册完成</span>
					</div>
				</div>
				<div class="cell">
					<form id="myform1" name="myform1" method="post"
						class="jd-vali-form">
						<s:hidden name="company.loginname" />
						<s:hidden name="company.loginword" />
						<s:hidden name="company.lxr" />
						<s:hidden name="company.mobile" />
						<s:hidden name="company.lxfs" />
						<s:hidden name="company.fddbrlxhm" />
						<s:hidden name="company.gddh" />
						<s:hidden name="company.qyyx" />
						<s:hidden name="company.cz" />
						<s:hidden name="company.yzbm" />
						<s:hidden name="company.longitude" />
						<s:hidden name="company.latitude" />
						<s:hidden name="company.szc"/>
						<s:hidden name="company.szcname" />
						<s:hidden name="company.companyname" />
						<s:hidden name="company.fddbr" />
						<s:hidden name="company.county" />
						<s:hidden name="company.dwdz1" />
						<s:hidden name="company.dwdz2" />
						<s:hidden name="company.gszch" />
						<s:hidden name="company.zzjgdm" />
						<s:hidden name="company.qyclsj" />
						<s:hidden name="company.ifurl" />
						<s:hidden name="company.url" />
						<s:hidden name="company.qylx" />
						<s:hidden name="company.hyfl" />
						<s:hidden name="company.ifzsqy" />
						<s:hidden name="company.zsqytype" />
						<s:hidden name="company.ifwhpqylx" />
						<s:hidden name="company.companyType" />
						<s:hidden name="company.ifzywhqylx" />
						<s:hidden name="company.ifyhbzjyqy" />
						<s:hidden name="company.iffmksjyqy" />
						<s:hidden name="company.metal" />
						<s:hidden name="company.ventilate" />
						<s:hidden name="company.transport" />
						<s:hidden name="company.raisetype" />
						<s:hidden name="company.sixsys" />
						<s:hidden name="company.sfaqjg" />
						<s:hidden name="company.aqglr" />
						<s:hidden name="company.sfzywsjg" />
						<s:hidden name="company.zywsglry" />
						<s:hidden name="company.sfqzwsgly" />
						<s:hidden name="company.iffmgmqylx" />
						<s:hidden name="company.aqscxkzh"/>
						<s:hidden name="company.feature"/>
						<s:hidden name="company.tyshxydm"/>
						<s:hidden name="reloginword"/>
						<div class="bg_01">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="255" align="right">
									<span class="red">*</span>企业规模
								</td>
								<td>
									<cus:SelectOneTag style="width:188px; margin-right:8px;"
										property="company.qygm" defaultText='请选择' codeName="企业规模"
										value="${company.qygm}" class="jd-validator-droplist"
										focusmsg="请选择企业规模！" nullmsg="请选择企业规模！" errormsg='请选择企业规模！'
										rightmsg='企业规模输入正确！' datatype="*" />
									<div class="jd-validator-msg" style="margin-left: 5px;"></div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red">*</span>企业注册类型
								</td>
								<td>
									<cus:SelectOneTag property="company.qyzclx"
										style="width:188px; margin-right:8px;" defaultText='请选择'
										codeName="注册类型" value="${company.qyzclx}"
										class="jd-validator-droplist" focusmsg="请选择注册类型！"
										nullmsg="请选择注册类型！" errormsg='请选择注册类型！' rightmsg='注册类型输入正确！'
										datatype="*" />
									<div class="jd-validator-msg" style="margin-left: 5px;"></div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>注册资金（万元）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.zczj" class="jd-validator"
											style="width: 338px" value="${company.zczj}" type="text"
											maxlength="255" focusmsg="请输入注册资金！" nullmsg="请输入注册资金！"
											errormsg='请输入正确的金额！' rightmsg='注册资金输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>上年销售收入（万元）

								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.snxssr" class="jd-validator"
											style="width: 338px" value="${company.snxssr}" type="text"
											maxlength="255" focusmsg="请输入上年销售收入！" nullmsg="请输入上年销售收入！"
											errormsg='请输入正确的金额！' rightmsg='上年销售收入输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>上年上交税收（万元）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.snsjss" class="jd-validator"
											style="width: 338px" value="${company.snsjss}" type="text"
											maxlength="255" focusmsg="请输入上年上交税收！" nullmsg="请输入上年上交税收！"
											errormsg='请输入正确的金额！' rightmsg='上年上交税收输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>上年固定资产（万元）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.sngdzc" class="jd-validator"
											style="width: 338px" value="${company.sngdzc}" type="text"
											maxlength="255" focusmsg="请输入上年固定资产！" nullmsg="请输入上年固定资产！"
											errormsg='请输入正确的金额！' rightmsg='上年固定资产输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>上年安全投入（万元）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.snwqtr" class="jd-validator"
											style="width: 338px" value="${company.snwqtr}" type="text"
											maxlength="255" focusmsg="请输入上年固定资产！" nullmsg="请输入上年固定资产！"
											errormsg='请输入正确的金额！' rightmsg='上年固定资产输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>上年安全生产费用提取数（万元）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.snaqscf" class="jd-validator"
											style="width: 338px" value="${company.snaqscf}" type="text"
											maxlength="255" focusmsg="请输入上年安全生产费用提取数！"
											nullmsg="请输入上年安全生产费用提取数！" errormsg='请输入正确的金额！'
											rightmsg='上年安全生产费用提取数输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>占地面积（m2）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.zdmj" class="jd-validator"
											style="width: 338px" value="${company.zdmj}" type="text"
											focusmsg="请输入占地面积！" nullmsg="请输入占地面积！" errormsg='请输入正确的数字！'
											rightmsg='占地面积输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$" maxlength="255">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>建筑面积（m2）

								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.jzmj" class="jd-validator"
											style="width: 338px" value="${company.jzmj}" type="text"
											focusmsg="请输入占地面积！" nullmsg="请输入占地面积！" errormsg='请输入正确的数字！'
											rightmsg='占地面积输入正确！'
											datatype="^(([1-9]\d{0,9})|0)(\.\d{1,2})?$" maxlength="255">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>从业人员（人）
								</td>
								<td>
									<div class="jd-vali-linediv">
										<input name="company.cyry" class="jd-validator"
											style="width: 338px" onKeyDown="onlyNum()"
											value="${company.cyry}" type="text" focusmsg="请输入从业人员！"
											nullmsg="请输入从业人员！" errormsg='请输入正确的数字！' rightmsg='从业人员输入正确！'
											datatype="n" maxlength="255">
										<div class="jd-validator-msg" style="margin-left: 5px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red"></span>是否有员工宿舍
								</td>
								<td>
									<cus:hxradio property="company.sfyygss" codeName="是或否"
										value="0" />
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red">*</span>安全生产标准化达标级别
								</td>
								<td>
									<cus:SelectOneTag property="company.aqbzdbjb"
										codeName="标准化达标级别" defaultText='请选择'
										class="jd-validator-droplist" focusmsg="请选择安全生产标准化达标级别！"
										nullmsg="请选择安全生产标准化达标级别！" errormsg='请选择安全生产标准化达标级别！'
										rightmsg='安全生产标准化达标级别输入正确！' datatype="*"
										value="${company.aqbzdbjb}" />
									<div class="jd-validator-msg" style="margin-left: 5px;"></div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right">
									<span class="red">*</span>经营范围
								</td>
								<td>
									<div class="jd-vali-linediv">
										<textarea id="company.jyfw" class="jd-validator"
											focusmsg="请输入经营范围！" nullmsg="经营范围必填！" errormsg='经营范围必填！'
											rightmsg='经营范围输入正确！' datatype="*" name="company.jyfw"
											rows="5" style="width: 80%; height: 150px">${company.jyfw}</textarea>
										<div class="jd-validator-msg"
											style="width: 15%; float: left; margin-top: 50px;"></div>
									</div>
								</td>
							</tr>
							<tr>
								<td width="255" align="right"></td>
								<td>
									<div class="login_subfield_btnarea" style="float: left;">
										<a href="javaScript:void(0)" onclick="pre();" class="btn_next">上一步</a>
									</div>
									<div class="login_subfield_btnarea" style="float: left; margin-left: 30px;">
										<input id="submitform" style="display: none;" type="button"
											onclick="next();" />
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
				<span>版权所有&nbsp;&nbsp;南京拓构软件有限公司</span><span>COPYRIGHTS
					2014</span>
			</div>
		</div>
	</body>
</html>