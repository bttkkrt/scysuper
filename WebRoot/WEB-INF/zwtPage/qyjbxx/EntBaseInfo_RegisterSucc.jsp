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


</head>

<body >
<form id="myform1" method="post" enctype="multipart/form-data">
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
                <li><i>2</i>填写详细信息</li>
                <li class="active"><i>3</i>完成注册</li>
            </ul>	
        </div>
        <div class="steps_main">
            <div class="steps_03">
            	<img src="${ctx}/webResources/qyzc/images/default/icon_ok.png"/>
				<p>恭喜您，添加成功！</p>
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
