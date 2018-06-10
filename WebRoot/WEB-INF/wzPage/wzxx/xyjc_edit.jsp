<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>苏州工业园区综合行政执法局</title>
<link href="${ctx}/webResources/wzCss/css/index.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/webResources/js/jquery-1.8.3.min.js"></script>
<script>
function checkInfo(obj)
{
	var value = document.getElementById(obj).value;
	if(value == "")
	{
		document.getElementById(obj+'Null').style.display = "";
		return false;
	}
	else
	{
		document.getElementById(obj+'Null').style.display = "none";
		return true;
	}
}

function save()
{
	if(
	checkInfo('mail') && checkInfo('infoTitle') && checkInfo('infoContent')
	)
	{
		$.ajax({
			cache: false,
			type: "POST",
			dataType: 'json',
			url:"xyjcSave.action", //把表单数据发送到entBaseInfoSaveRegister.action
			data:$('#myform1').serialize(), //要发送的是ajaxFrm表单中的数据
			async: false,
			error: function(request) {
				alert("提交失败");
			},
			success: function(data) {
 				if(data.result){
 					alert("提交成功!");
					location.href = "${ctx}/wzxx/index.action"
        		}else{
        			alert("提交失败");
        		}
			}
		});
	}
}

</script>
</head>

<body>
<div class="wrapper">
<!--header-->
	<div class="header">
    	<div class="logo fleft"><a href="#"><img src="${ctx}/webResources/wzCss/images/logo.png"/></a></div>
        <div class="erweima fright"><img src="${ctx}/webResources/wzCss/images/Android.png"/><a href="#">安卓</a></div>
        <div class="myclear"></div>
    </div>
<!--/header end-->

<!--nav-->
    <div id="navBg">
        <div class="nav">
             <ul>
                <li><a class="navnow" href="${ctx}/wzxx/index.action" >首页</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=6">组织机构</a></li>
                <li><a href="${ctx}/wzxx/gzdtList.action?pageNum=1">工作动态</a></li>
                <li><a href="${ctx}/wzxx/newsList.action">通知公告</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=7">专项整治</a></li>
                <li><a href="${ctx}/wzxx/aqwhList.action">安全文化</a></li>
                <li><a href="${ctx}/wzxx/flfgList.action">法律规章</a></li>
                <li><a href="${ctx}/wzxx/awhgzList.action?wzInfoManage.infoType=2">政务信息</a></li>
                <li><a href="${ctx}/wzxx/lxwmList.action">联系我们</a></li>        
          </ul>
        </div>	
    </div>
<!--/nav end-->	
<form id="myform1" name="myform1" method="post" enctype="multipart/form-data">
<div class="main">
	<div class="location"><p>您现在的位置：<a href="${ctx}/wzxx/index.action">首页</a>&gt;<a href="#">建言献策</a></p></div>
    <div class="newscon">
    	<div class="tips"><span>提示：*</span>为必填项</div>
        <table border="0" cellpadding="0" cellspacing="0">
            <tr>
                <th width="18%">姓名：</th>
                <td width="62%"><input name="xyjc.name" type="text" style="width:400px"/></td>
            </tr> 
            <tr>
                <th>性别：</th>
                <td><cus:hxradio property="xyjc.sex" codeName="性别" value="${xyjc.sex}" /></td>
            </tr> 
            <tr>
                <th>联系地址：</th>
                <td><input name="xyjc.address" type="text" style="width:400px"/></td>
            </tr> 
            <tr>
                <th>联系电话：</th>
                <td><input name="xyjc.mobile" type="text" style="width:400px"/></td>
            </tr> 
            <tr>
                <th><span class="red">*</span>电子邮箱：</th>
                <td>
                	<input name="xyjc.mail" id="mail" type="text" style="width:400px" maxlength="127" onblur="checkInfo('mail');"/>
                	<div class="tips_02" id="mailNull" style="display:none"><p>电子邮箱不能为空</p></div>
                </td>
            </tr> 
            <tr>
                <th><span class="red">*</span>建言标题：</th>
                <td>
                	<input name="xyjc.infoTitle" id="infoTitle" type="text" maxlength="127" style="width:400px" onblur="checkInfo('infoTitle');"/>
                	<div class="tips_02" id="infoTitleNull" style="display:none"><p>建言标题不能为空</p></div>	
                </td>
            </tr> 
            <tr>
                <th><span class="red">*</span>建言内容：</th>
                <td>
                	<textarea id="infoContent" name="xyjc.infoContent" onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" onblur="checkInfo('infoContent');"></textarea>
                	<div class="tips_02" id="infoContentNull" style="display:none"><p>建言内容不能为空</p></div>	
                </td>
            </tr>             
            <tr>
                <th></th>
                <td><a href="#" class="btn_next" onclick="save()">提交问题</a></td>
            </tr> 
                               
        </table>
  	</div>                            
</div>
</form>

<!--/bottom-->
<div class="bottom">
    <span>Copyright ©2015</span>
    <span>苏州工业园区综合行政执法局 版权所有.</span>
    <span>ALL RIGHTS RESERVED.</span>
    <p><span>苏ICP备09024432</span><span><a href="#">技术支持：南京拓构软件有限公司</a></span></p>
</div>
<!--/bottom end-->

	
</div>

</body>
</html>


