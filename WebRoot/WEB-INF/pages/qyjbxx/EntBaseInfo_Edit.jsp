<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<!-- bootstrap -->
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${ctx}/bootstrapvalidator/vendor/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="${ctx}/bootstrapvalidator/dist/js/bootstrapValidator.js"></script>
<link rel="stylesheet" href="${ctx}/bootstrapvalidator/dist/css/bootstrapValidator.css"/>
<script type="text/javascript" src="${ctx}/bootstrapvalidator/dist/js/language/zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/webResources/js/citySelect/jquery.cityselect.js"></script>
<script src="${ctx}/webResources/layer/layer.js"></script>
<%-- <script src="${ctx}/webResources/layui/layui.js"></script>
<link href="${ctx}/webResources/layui/css/layui.css" rel="stylesheet" type="text/css" /> --%>
<!-- bootstrap-datetimepicker -->
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/bootstrap/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/bootstrap/js/bootstrap-datetimepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/bootstrapvalidator/vendor/bootstrap/css/bootstrap-datetimepicker.min.css">
<!-- fileinput.js -->
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/bootstrap/js/fileinput.js"></script>
<script type="text/javascript" src="${ctx}/bootstrapvalidator/vendor/bootstrap/js/zh.js"></script>
<link rel="stylesheet" href="${ctx}/bootstrapvalidator/vendor/bootstrap/css/fileinput.css">
<!-- viewer图片预览-->  
<script type="text/javascript" src="${ctx}/h-ui/lib/viewer/js/viewer-jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="${ctx}/h-ui/lib/viewer/css/viewer.min.css">

<link href="${ctx}/webResources/qyzc/css/form.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	select{
		border: 1px solid #ccc;
		    border-radius: 4px;
    	font-size: 14px;
    	height: 34px;
    	width: 100%;
	}
	label{
		    font-size: 14px;
		        color: #3c763d;
	}
	fieldset{
			    width: 96%;
			    margin: 0 auto;
			    border: 2px solid #17B3E1;
			    border-radius: 5px;
			    padding: 0 10;
		}
		legend{
		    margin-left: 50px;
		    line-height: 40px;
		    font-size: 18px;
		    font-style: italic;
		    color: black;
		    text-align: center;
		    font-weight: bold;
		}
</style>
	<%-- <%@include file="/common/jsLib.jsp"%> --%>
	<script>
	
		
	$(function(){
		//省地市级联初始化
    	$("#city").citySelect();
		if("${flag}"=="1"){
			layer.msg('操作结果', {
				  time: 2000, //2s后自动关闭
				  btnAlign: 'c', //按钮居中
				  shade: 0.8,
				  btn: ['操作成功']
				}); 
		}
		
		/* var obj = document.getElementById("enterprisWorkshopOwn").value;
		if(obj == 2)
		{
			$('.clsdiv').each(function(){
     			$(this).removeData("dataIgnore");
			});
		}
		else
		{
			$('.clsdiv').each(function(){
     			$(this).data("dataIgnore","dataIgnore");
			});
		} */
		
		$("#safePromise").change(function() { 
			if(!$(this).is(':checked')){
				$("#updateBtn").addClass("op_disable");
			}else{
				$("#updateBtn").removeClass("op_disable");
			}
		});
	});
	
	function showTips(){
		layer.tips('未完成三证合一的企业，请填写组织机构代码', '#unionCode');
	}
		
	function changeOwn(obj)
	{
		if(obj == 2)
		{
			document.getElementById('ownerdiv').style.display = "";
			$('.clsdiv').each(function(){
     			$(this).removeData("dataIgnore");
			});
		}
		else
		{
			document.getElementById('ownerdiv').style.display = "none";
			$('.clsdiv').each(function(){
     			$(this).data("dataIgnore","dataIgnore");
			});
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
    function save(){
    	if($("#updateBtn").hasClass("op_disable")){
    		layer.alert('请先勾选“同意安全生产承诺”按钮')
    		return false;
    	}
		$("#defaultForm").attr("action","entBaseInfoSave.action");
		$("#enterprisePossession").val($("#areaId").val());
		var areaname = $("#areaId").attr("areaname");
		$("#enterprisePossessionName").val(areaname);
		$("#defaultForm").submit();
		//判断是否通过校验
		/* if($("#myform").Validform().getStatus()=="normal"){
			return;
		} */
		//closeLayer();
	}
</script>
</head>

<body validform="true" style="overflow: auto;" >
<form id="defaultForm" method="post" enctype="multipart/form-data" style="padding-top: 20px;" class="form-horizontal" action="${ctx}/jsp/qyjbxx/entBaseInfoSave.action">
	<input type="hidden" name="flag" value="${flag}">
	<input type="hidden" name="entBaseInfo.id" value="${entBaseInfo.id}">
	<input type="hidden" name="entBaseInfo.createTime" value="<fmt:formatDate type="both" value="${entBaseInfo.createTime}" />">
	<input type="hidden" name="entBaseInfo.updateTime" value="${entBaseInfo.updateTime}">
	<input type="hidden" name="entBaseInfo.createUserID" value="${entBaseInfo.createUserID}">
	<input type="hidden" name="entBaseInfo.updateUserID" value="${entBaseInfo.updateUserID}">
	<input type="hidden" name="entBaseInfo.deptId" value="${entBaseInfo.deptId}">
	<input type="hidden" name="entBaseInfo.delFlag" value="${entBaseInfo.delFlag}">
	
	<input type="hidden" name="entBaseInfo.loginId" value="${entBaseInfo.loginId}">
	<input type="hidden" name="entBaseInfo.password" value="${entBaseInfo.password}">
	<input type="hidden" name="entBaseInfo.gridManageteamCode" value="${entBaseInfo.gridManageteamCode}">
	<input type="hidden" name="entBaseInfo.gridManageteamName" value="${entBaseInfo.gridManageteamName}">
	<input type="hidden" name="entBaseInfo.gridManageId" value="${entBaseInfo.gridManageId}">
	<input type="hidden" name="entBaseInfo.gridManageName" value="${entBaseInfo.gridManageName}">
	<input type="hidden" name="entBaseInfo.grid" value="${entBaseInfo.grid}">
	<input type="hidden" name="entBaseInfo.gridName" value="${entBaseInfo.gridName}">
	<input type="hidden" name="entBaseInfo.linkId" value="${entBaseInfo.linkId}">
	<input type="hidden" name="entBaseInfo.remark" value="${entBaseInfo.remark}">
	<input type="hidden" name="entBaseInfo.basePass" value="${entBaseInfo.basePass}">
	<input type="hidden" id="mapKey" name="comGriMan.mapKey" value="${comGriMan.mapKey}">
	<input type="hidden" name="entBaseInfo.ifCz" value="${entBaseInfo.ifCz}">
	<input type="hidden" id="enterprisePossession" name="entBaseInfo.enterprisePossession" value="${entBaseInfo.enterprisePossession}">
	<input type="hidden" id="enterprisePossessionName" name="entBaseInfo.enterprisePossessionName" value="${entBaseInfo.enterprisePossessionName}">
	<div class="col-lg-1 col-lg-offset-11" style="position: fixed;top: 0px;">
      <button type="submit" class="btn btn-primary">保存</button>
    </div>
    <fieldset>
		<legend>企业基础信息</legend>
  <div class="form-group">
    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>企业名称</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="enterpriseName"  value="${entBaseInfo.enterpriseName }"/>
    </div>
    <label class="col-lg-2 control-label" id="unionCode" onmouseover="showTips();"><span style='color:red;'>*</span>统一社会信用代码</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="enterpriseCode" value="${entBaseInfo.enterpriseCode }"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>所在地区</label>
    <div class="col-lg-10" id="city">
    	<input type="hidden" id="areaId" name="entBaseInfo.enterprisePossession" value="">
    	<input type="hidden" id="areaName" name="entBaseInfo.enterprisePossessionName" value="">
    	<input type="hidden" id="setAreaName" value="true">
        <select class="prov" disabled="disabled" style="width:19.6%" ></select> 
        <select class="city" disabled="disabled" hidden style="width:19.6%" ></select>
        <select class="dist" disabled="disabled" hidden style="width:19.6%" ></select>
        <select class="street" disabled="disabled" hidden style="width:19.6%" ></select>
    </div>
  </div>
    <div class="form-group">
    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>注册地址</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseAddress"  value="${entBaseInfo.enterpriseAddress }"/>
    </div>
    <label class="col-lg-2 control-label" ><span style='color:red;'>*</span>生产经营地址</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.factoryAddress " value="${entBaseInfo.factoryAddress }"/>
    </div>
  </div>
  
  <div class="form-group">
    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>企业成立时间</label>
    <div class="col-lg-3 input-group date" id='datetimepicker2' style="float: left;padding-left: 15px;padding-right: 15px;">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseFoundDate"  value="${entBaseInfo.enterpriseFoundDate }"/>
      <span class="input-group-addon">  
          <span class="glyphicon glyphicon-calendar"></span>  
      </span>
    </div>
    <label class="col-lg-2 control-label" >工商注册号</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.registrationNumber" value="${entBaseInfo.registrationNumber }"/>
    </div>
  </div>
  <div class="form-group">
  	 <label class="col-lg-2 control-label">直管部门级别</label>
    <div class="col-lg-3">
    	<cus:SelectOneTag property="entBaseInfo.departmentalLevel"  codeName="直管部门级别" value="${entBaseInfo.departmentalLevel}" defaultText='请选择'/>
    </div>
    <label class="col-lg-2 control-label" >邮政编码</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseZipcode" value="${entBaseInfo.enterpriseZipcode }"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>占地面积(㎡)</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseFloorArea"  value="${entBaseInfo.enterpriseFloorArea }"/>
    </div>
    <label class="col-lg-2 control-label">营业执照类型</label>
    <div class="col-lg-3">
    	<cus:SelectOneTag property="entBaseInfo.businessLicence"  codeName="营业执照类型" value="${entBaseInfo.businessLicence}" defaultText='请选择'/>
    </div>
  </div>
  <div class="form-group">
  	<label class="col-lg-2 control-label">行业门类(<a href="${ctx}/hyfl.xlsx" style="text-decoration:underline;color:red">查看定义</a>)</label>
    <div class="col-lg-3">
    	<cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}"  onchange="getHyxl(this.value)"/>
    </div>
    <label class="col-lg-2 control-label" ><span style='color:red;'>*</span>行业大类</label>
    <div class="col-lg-3">
      <s:select theme="simple" id="jjlx" name="entBaseInfo.jjlx" list="%{hylist}" listKey="id" listValue="name" ></s:select>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>分类监管</label>
    <div class="col-lg-3">
    	<cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="分类监管" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
    </div>
    <label class="col-lg-2 control-label" >安全监管等级</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="enterpriseCode" value="${entBaseInfo.enterpriseCode }"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label">经度(<a style="color: red;" href="http://api.map.baidu.com/lbsapi/getpoint/?qq-pf-to=pcqq.c2c" target="_blank">坐标拾取</a>)</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.latitude"  value="${entBaseInfo.latitude }"/>
    </div>
    <label class="col-lg-2 control-label" >纬度</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.longitude" value="${entBaseInfo.longitude }"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label">注册资金(万元)</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseRegisterMoney"  value="${entBaseInfo.enterpriseRegisterMoney }"/>
    </div>
    <label class="col-lg-2 control-label" >资产总额(万元)</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseFixedassetMoney" value="${entBaseInfo.enterpriseFixedassetMoney }"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label">员工总数</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseStaffCount"  value="${entBaseInfo.enterpriseStaffCount }"/>
    </div>
    <label class="col-lg-2 control-label" >工人数</label>
    <div class="col-lg-3">
      <input type="text" class="form-control" name="entBaseInfo.enterpriseWorkerCount" value="${entBaseInfo.enterpriseWorkerCount }"/>
    </div>
  </div>
  <div class="form-group">
    <label class="col-lg-2 control-label" >经营范围</label>
    <div class="col-lg-8">
    	<input type="text" class="form-control" name="entBaseInfo.enterpriseScope" value="${entBaseInfo.enterpriseScope }"/>
    </div>
  </div>
  <div class="form-group" style="    height: 290px;">
    <label class="col-lg-2 control-label">厂区图片</label>
    <div class="col-lg-5">
      <input type="file" id="fileQueue" name="Filedata"  multiple=true> 
    </div>
    <label class="col-lg-1 control-label"><button type="button" class="btn btn-info" id="uploadFile" onclick="getUploadFIle();">已上传附件</button></label>
  </div>
  <div class="form-group">
    <label class="col-lg-12 control-label" ></label>
  </div>
  </fieldset>
  <fieldset>
		<legend>相关人员信息</legend>
		    <div class="form-group">
		    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>法定代表人</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.enterpriseAddress"  value="${entBaseInfo.enterpriseAddress }"/>
		    </div>
		    <label class="col-lg-2 control-label" ><span style='color:red;'>*</span>法定代表人手机</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.factoryAddress " value="${entBaseInfo.factoryAddress }"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-lg-2 control-label"><span style='color:red;'>*</span>安全负责人</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.enterpriseAddress"  value="${entBaseInfo.enterpriseAddress }"/>
		    </div>
		    <label class="col-lg-2 control-label" ><span style='color:red;'>*</span>安全负责人手机</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.factoryAddress " value="${entBaseInfo.factoryAddress }"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-lg-2 control-label">企业联系人</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.enterpriseAddress"  value="${entBaseInfo.enterpriseAddress }"/>
		    </div>
		    <label class="col-lg-2 control-label" >企业联系人手机</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.factoryAddress " value="${entBaseInfo.factoryAddress }"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-lg-2 control-label">主要负责人</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.enterpriseAddress"  value="${entBaseInfo.enterpriseAddress }"/>
		    </div>
		    <label class="col-lg-2 control-label" >主要负责人手机</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.factoryAddress " value="${entBaseInfo.factoryAddress }"/>
		    </div>
		  </div>
		    
		  <div class="form-group">
		    <label class="col-lg-2 control-label">职业卫生管理人</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.enterpriseAddress"  value="${entBaseInfo.enterpriseAddress }"/>
		    </div>
		    <label class="col-lg-2 control-label" >职业卫生管理人手机</label>
		    <div class="col-lg-3">
		      <input type="text" class="form-control" name="entBaseInfo.factoryAddress " value="${entBaseInfo.factoryAddress }"/>
		    </div>
		  </div>
  </fieldset>
  <fieldset>
		<legend>安全生产情况</legend>
		<div class="form-group">
		    <label class="col-lg-2 control-label">是否有专门安全机构</label>
		    <div class="col-lg-3">
		    	<cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="是或否" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
		    </div>
		    <label class="col-lg-2 control-label" >是否有专职安全人员</label>
		    <div class="col-lg-3">
		      <cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="是或否" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-lg-2 control-label">是否有专门职业卫生机构</label>
		    <div class="col-lg-3">
		    	<cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="是或否" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
		    </div>
		    <label class="col-lg-2 control-label" >是否有专职职业卫生人员</label>
		    <div class="col-lg-3">
		      <cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="是或否" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label class="col-lg-2 control-label">是否存在重大危险源</label>
		    <div class="col-lg-3">
		    	<cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="是或否" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
		    </div>
		    <label class="col-lg-2 control-label" >标准化等级</label>
		    <div class="col-lg-3">
		      <cus:SelectOneTag property="entBaseInfo.enterpriseType"  codeName="标准化等级" value="${entBaseInfo.enterpriseType}" defaultText='请选择'/>
		    </div>
		  </div>
  </fieldset>
  <fieldset>
		<legend>审核记录</legend>
		<div class="form-group">
		    <label class="col-lg-2 control-label"></label>
		    <div class="col-lg-3">
		    	<c:forEach items="${checkRecords }" var="cr">
					<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
				</c:forEach>
		    </div>
		  </div>
  </fieldset>
  <!-- <div class="form-group">
    <label class="col-lg-3 control-label" id="captchaOperation"></label>
    <div class="col-lg-2">
      <input type="text" class="form-control" name="captcha" />
    </div>
  </div> -->
  <!-- <div class="form-group">
    <div class="col-lg-9 col-lg-offset-3">
      <button type="submit" class="btn btn-primary">保存</button>
      <button type="button" class="btn btn-info" id="resetBtn">重置</button>
    </div>
  </div> -->
</form>
   <%-- <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%">
				<form id="myform" name="myform1" method="post" enctype="multipart/form-data" >
					<s:token />
					<input type="hidden" name="flag" value="${flag}">
					<input type="hidden" name="entBaseInfo.id" value="${entBaseInfo.id}">
					<input type="hidden" name="entBaseInfo.createTime" value="<fmt:formatDate type="both" value="${entBaseInfo.createTime}" />">
					<input type="hidden" name="entBaseInfo.updateTime" value="${entBaseInfo.updateTime}">
					<input type="hidden" name="entBaseInfo.createUserID" value="${entBaseInfo.createUserID}">
					<input type="hidden" name="entBaseInfo.updateUserID" value="${entBaseInfo.updateUserID}">
					<input type="hidden" name="entBaseInfo.deptId" value="${entBaseInfo.deptId}">
					<input type="hidden" name="entBaseInfo.delFlag" value="${entBaseInfo.delFlag}">
					
					<input type="hidden" name="entBaseInfo.loginId" value="${entBaseInfo.loginId}">
					<input type="hidden" name="entBaseInfo.password" value="${entBaseInfo.password}">
					<input type="hidden" name="entBaseInfo.gridManageteamCode" value="${entBaseInfo.gridManageteamCode}">
					<input type="hidden" name="entBaseInfo.gridManageteamName" value="${entBaseInfo.gridManageteamName}">
					<input type="hidden" name="entBaseInfo.gridManageId" value="${entBaseInfo.gridManageId}">
					<input type="hidden" name="entBaseInfo.gridManageName" value="${entBaseInfo.gridManageName}">
					<input type="hidden" name="entBaseInfo.grid" value="${entBaseInfo.grid}">
					<input type="hidden" name="entBaseInfo.gridName" value="${entBaseInfo.gridName}">
					<input type="hidden" name="entBaseInfo.linkId" value="${entBaseInfo.linkId}">
					<input type="hidden" name="entBaseInfo.remark" value="${entBaseInfo.remark}">
					<input type="hidden" name="entBaseInfo.basePass" value="${entBaseInfo.basePass}">
					<input type="hidden" id="mapKey" name="comGriMan.mapKey" value="${comGriMan.mapKey}">
					<input type="hidden" name="entBaseInfo.ifCz" value="${entBaseInfo.ifCz}">
					<input type="hidden" id="enterprisePossession" name="entBaseInfo.enterprisePossession" value="${entBaseInfo.enterprisePossession}">
					<input type="hidden" id="enterprisePossessionName" name="entBaseInfo.enterprisePossessionName" value="${entBaseInfo.enterprisePossessionName}">
					
						<table width="100%" border="0">
							<tr>
								<th width="15%">企业名称</th>
								<td width="35%"><input name="entBaseInfo.enterpriseName" value="${entBaseInfo.enterpriseName}" type="text" datatype="*1-127" errormsg='企业名称必须是1到127位字符！' nullmsg='企业名称不能为空！' sucmsg='企业名称填写正确！'  maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
								<th width="15%">组织机构代码/统一社会信用编码</th>
								<td width="35%"><input name="entBaseInfo.enterpriseCode" value="${entBaseInfo.enterpriseCode}" type="text" datatype="*1-127" errormsg='组织机构代码必须是1到127位字符！'  sucmsg='组织机构代码填写正确！'  maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">工商注册号</th>
								<td width="35%"><input name="entBaseInfo.registrationNumber" value="${entBaseInfo.registrationNumber}" type="text" errormsg='工商注册号必须是1到127位字符！' sucmsg='工商注册号填写正确！'  maxlength="127" style="width:50%"></td>
								<th width="15%">企业注册类型</th>
								<td width="35%"><cus:SelectOneTag property="entBaseInfo.qyzclx" defaultText='请选择' codeName="企业注册类型" value="${entBaseInfo.qyzclx}" datatype="*1-127" errormsg='企业注册类型必须是1到127位字符！' nullmsg='企业注册类型不能为空！' sucmsg='企业注册类型填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">注册地址</th>
								<td width="85%"><input name="entBaseInfo.enterpriseAddress" value="${entBaseInfo.enterpriseAddress}" type="text" datatype="*1-127" errormsg='注册地址必须是1到127位字符！'  sucmsg='注册地址填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">生产经营地址</th>
								<td width="35%"><input name="entBaseInfo.factoryAddress" value="${entBaseInfo.factoryAddress}" type="text" datatype="*1-127" errormsg='生产经营地址必须是1到127位字符！'  sucmsg='生产经营地址填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">企业属地</th>
								<td width="35%">
									<div id="city">
										<input type="hidden" id="areaId" areaname="" value="">
										<input type="hidden" id="setAreaName" value="true">
				                        <select class="prov"></select> 
					                    <select class="city" disabled="disabled" hidden></select>
					                    <select class="dist" disabled="disabled" hidden></select>
					                    <select class="street" disabled="disabled" hidden></select>
				                    </div>
								</td>
								<th width="15%">企业性质</th>
								<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseNature" defaultText='请选择' codeName="企业性质" value="${entBaseInfo.enterpriseNature}" datatype="*1-127" errormsg='企业性质必须是1到127位字符！'  sucmsg='企业性质填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">企业分类</th>
								<td width="85%" colspan="3"><cus:hxcheckbox property="entBaseInfo.enterpriseType" codeName="企业分类" value="${entBaseInfo.enterpriseType}" datatype="*1-127" errormsg='企业分类必须是1到127位字符！'  sucmsg='企业分类填写正确！'  maxlength="127" ignore="ignore"/><font style='color:red'>*（可多选）</font></td>	
							</tr>
							<tr>
								<th width="15%">企业规模(<a href="${ctx}/qygm.docx" style="text-decoration:underline;color:red">查看定义</a>)</th>
								<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseScale" defaultText='请选择' codeName="企业规模" value="${entBaseInfo.enterpriseScale}" datatype="*1-127" errormsg='企业规模必须是1到127位字符！'  sucmsg='企业规模填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>	
								<th width="15%">行业类别(<a href="${ctx}/hyfl.xlsx" style="text-decoration:underline;color:red">查看定义</a>)</th>
								<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseCategory" defaultText='请选择' codeName="行业类别" value="${entBaseInfo.enterpriseCategory}" maxlength="127" style="width:25%" ignore="ignore" onchange="getHyxl(this.value)"/><s:select theme="simple" cssStyle="width:25%;" id="jjlx" name="entBaseInfo.jjlx" list="%{hylist}" listKey="id" listValue="name" datatype="*1-127" nullmsg='行业类别小类不能为空！' sucmsg='行业类别小类填写正确！'  ignore="ignore"></s:select><font style='color:red'>*</font></td>	
							</tr>
							<tr>
								<th width="15%">投资方国籍</th>
								<td width="35%"><input name="entBaseInfo.enterpriseNationnality" value="${entBaseInfo.enterpriseNationnality}" type="text" datatype="*1-127" errormsg='投资方国籍必须是1到127位字符！'  sucmsg='投资方国籍填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">法人代表姓名</th>
								<td width="35%"><input name="entBaseInfo.enterpriseLegalName" value="${entBaseInfo.enterpriseLegalName}" type="text" datatype="*1-127" errormsg='法人姓名必须是1到127位字符！' nullmsg='法人姓名不能为空！' sucmsg='法人姓名填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">法人代表性别</th>
								<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterpriseLegalSex" defaultText='请选择' codeName="性别" value="${entBaseInfo.enterpriseLegalSex}" datatype="*1-127" errormsg='法人性别必须是1到127位字符！' sucmsg='法人性别填写正确！'  maxlength="127" style="width:50%" ignore="ignore"/><font style='color:red'>*</font></td>
								<th width="15%">法人代表年龄</th>
								<td width="35%"><input name="entBaseInfo.enterpriseLegalAge" value="${entBaseInfo.enterpriseLegalAge}" type="text" datatype="n1-127" errormsg='法人年龄必须是1到127位字符！'  sucmsg='法人年龄填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">法人代表联系电话</th>
								<td width="35%"><input name="entBaseInfo.enterpriseLegalPhone" value="${entBaseInfo.enterpriseLegalPhone}" type="text" datatype="*1-127" errormsg='法人联系电话格式错误！' nullmsg='法人联系电话不能为空！' sucmsg='法人联系电话填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">法人代表证件号码</th>
								<td width="35%"><input name="entBaseInfo.enterpriseLegalCardnum" value="${entBaseInfo.enterpriseLegalCardnum}" type="text" datatype="*1-127" errormsg='法人证件号码必须是1到127位字符！'  sucmsg='法人证件号码填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">法人代表电子邮箱</th>
								<td width="35%"><input name="entBaseInfo.enterpriseLegalEmail" value="${entBaseInfo.enterpriseLegalEmail}" type="text" datatype="e" errormsg='法人电子邮箱格式错误！'  sucmsg='法人电子邮箱填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">法人代表职务</th>
								<td width="35%"><input name="entBaseInfo.enterpriseLegalZw" value="${entBaseInfo.enterpriseLegalZw}" type="text" datatype="*1-127" errormsg='法人职务必须是1到127位字符！'  sucmsg='法人职务填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">企业设立日期</th>
								<td width="35%"><input name="entBaseInfo.enterpriseFoundDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseFoundDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='企业设立日期必须是1到127位字符！'  sucmsg='企业设立日期填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">企业投产日期</th>
								<td width="35%"><input name="entBaseInfo.enterpriseProductDate" value="<fmt:formatDate type='date' value='${entBaseInfo.enterpriseProductDate}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='企业投产日期必须是1到127位字符！' sucmsg='企业投产日期填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">注册资本</th>
								<td width="35%">
									<input name="entBaseInfo.enterpriseRegisterMoney" value="${entBaseInfo.enterpriseRegisterMoney}" type="text" datatype="*1-127" errormsg='注册资本必须是1到127位字符！' sucmsg='注册资本填写正确！'  maxlength="127" style="width:30%" onKeyUp="clearNoNum(event,this)" ignore="ignore">
									<cus:SelectOneTag property="entBaseInfo.enterpriseRegisterMoneyDw" defaultText='请选择' codeName="货币种类" value="${entBaseInfo.enterpriseRegisterMoneyDw}" datatype="*1-127" errormsg='注册资本单位必须是1到127位字符！' nullmsg='注册资本单位不能为空！' sucmsg='注册资本单位填写正确！'  maxlength="127" style="width:20%"/>
									<font style='color:red'>*</font>
								</td>
								<th width="15%">投资总额</th>
								<td width="35%">
									<input name="entBaseInfo.enterpriseInvestMoney" value="${entBaseInfo.enterpriseInvestMoney}" type="text" datatype="*1-127" errormsg='投资总额必须是1到127位字符！'  sucmsg='投资总额填写正确！'  maxlength="127" style="width:30%" onKeyUp="clearNoNum(event,this)" ignore="ignore">
									<cus:SelectOneTag property="entBaseInfo.enterpriseInvestMoneyDw" defaultText='请选择' codeName="货币种类" value="${entBaseInfo.enterpriseInvestMoneyDw}" datatype="*1-127" errormsg='投资总额必须是1到127位字符！' nullmsg='投资总额单位不能为空！' sucmsg='投资总额单位填写正确！'  maxlength="127" style="width:20%"/>
									<font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%">固定资产总额</th>
								<td width="35%">
									<input name="entBaseInfo.enterpriseFixedassetMoney" value="${entBaseInfo.enterpriseFixedassetMoney}" type="text" datatype="*1-127" errormsg='固定资产总额必须是1到127位字符！'  sucmsg='固定资产总额填写正确！'  maxlength="127" style="width:30%" onKeyUp="clearNoNum(event,this)" ignore="ignore">
									<cus:SelectOneTag property="entBaseInfo.enterpriseFixedassetMoneyDw" defaultText='请选择' codeName="货币种类" value="${entBaseInfo.enterpriseFixedassetMoneyDw}" datatype="*1-127" errormsg='固定资产总额单位必须是1到127位字符！' nullmsg='固定资产总额单位不能为空！' sucmsg='固定资产总额单位填写正确！'  maxlength="127" style="width:20%"/>
									<font style='color:red'>*</font>
								</td>
								<th width="15%">占地面积（m2）</th>
								<td width="35%"><input name="entBaseInfo.enterpriseFloorArea" value="${entBaseInfo.enterpriseFloorArea}" type="text" datatype="*1-127" errormsg='占地面积必须是1到127位字符！'  sucmsg='占地面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">办公楼建筑面积（m2）</th>
								<td width="35%"><input name="entBaseInfo.enterpriseOfficeArea" value="${entBaseInfo.enterpriseOfficeArea}" type="text" datatype="*1-127" errormsg='办公楼面积必须是1到127位字符！'  sucmsg='办公楼面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">车间厂房建筑面积（m2）</th>
								<td width="35%"><input name="entBaseInfo.enterpriseWorkshopArea" value="${entBaseInfo.enterpriseWorkshopArea}" type="text" datatype="*1-127" errormsg='车间厂房面积必须是1到127位字符！'  sucmsg='车间厂房面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">仓库建筑面积（m2）</th>
								<td width="35%"><input name="entBaseInfo.enterpriseWearhouseArea" value="${entBaseInfo.enterpriseWearhouseArea}" type="text" datatype="*1-127" errormsg='仓库面积必须是1到127位字符！'  sucmsg='仓库面积填写正确！'  maxlength="127" style="width:50%" onKeyUp="clearNoNum(event,this)" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">厂房归属</th>
								<td width="35%"><cus:SelectOneTag property="entBaseInfo.enterprisWorkshopOwn" defaultText='请选择' codeName="厂房归属" value="${entBaseInfo.enterprisWorkshopOwn}" datatype="*1-127" errormsg='厂房归属必须是1到127位字符！'  sucmsg='厂房归属填写正确！'  maxlength="127" style="width:50%" onchange="changeOwn(this.value)" ignore="ignore"/><font style='color:red'>*</font></td>
							</tr>
							<tr id="ownerdiv" <s:if test="entBaseInfo.enterprisWorkshopOwn != 2">style="display:none"</s:if>>
								<th width="15%">出租方</th>
								<td width="35%"><input name="entBaseInfo.houseOwner" value="${entBaseInfo.houseOwner}" type="text" datatype="*1-127" errormsg='出租方必须是1到127位字符！'  sucmsg='出租方填写正确！'  maxlength="127" style="width:50%" class="clsdiv" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">出租方联系方式</th>
								<td width="35%"><input name="entBaseInfo.ownerTel" value="${entBaseInfo.ownerTel}" type="text" datatype="*1-127" errormsg='出租方联系方式必须是1到127位字符！'  sucmsg='出租方联系方式填写正确！'  maxlength="127" style="width:50%" class="clsdiv" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">管理人员数</th>
								<td width="35%"><input name="entBaseInfo.enterpriseManagerCount" value="${entBaseInfo.enterpriseManagerCount}" type="text" datatype="n1-127" errormsg='管理人员数必须是1到127位字符！'  sucmsg='管理人员数填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
								<th width="15%">工人数</th>
								<td width="35%"><input name="entBaseInfo.enterpriseWorkerCount" value="${entBaseInfo.enterpriseWorkerCount}" type="text" datatype="n1-127" errormsg='工人数必须是1到127位字符！' nullmsg='工人数不能为空！' sucmsg='工人数填写正确！'  maxlength="127" style="width:50%" ignore="ignore"><font style='color:red'>*</font></td>
							</tr>
							<tr>
								<th width="15%">经营范围</th>
								<td width="85%" colspan="3">
									<textarea id="entBaseInfo.enterpriseScope" name="entBaseInfo.enterpriseScope" style="width:80%;height:120px" datatype="*1-2000" errormsg='经营范围必须是1到2000位字符！' nullmsg='经营范围不能为空！' sucmsg='经营范围填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" ignore="ignore">${entBaseInfo.enterpriseScope}</textarea><font style='color:red'>*</font>
								</td>
							</tr>
							<tr>
								<th width="15%" rowspan="2">厂区平面图</th>
								<td width="85%" colspan="3">
							   		<div id="fileQueue"/>
							    	<input type="file" name="uploadify" id="uploadify"/>
						    		<a href="javascript:jQuery('#uploadify').uploadifyUpload()">开始上传</a>&nbsp;
									<a href="javascript:jQuery('#uploadify').uploadifyClearQueue()">取消所有上传</a>
									<font style='color:red'>上传附件最大上限为50M</font>
							    </td>
							</tr>
							<tr>
								<td width="85%" colspan="3" style="padding:0 0 0 0;">
									<div style="color:green;overflow:auto;height:175px;">
									<table id="cqtp">
										  <c:forEach var="item" items="${picList}">
											<tr id='${item.id}' style="text-align: center">
											   <td width="70%">
											   		<c:choose>
														<c:when test="${fn:endsWith(fn:toLowerCase(item.picName),'.jpg')
														||fn:endsWith(fn:toLowerCase(item.picName),'.bmp')
														||fn:endsWith(fn:toLowerCase(item.picName),'.png')
														||fn:endsWith(fn:toLowerCase(item.picName),'.jpeg')
														||fn:endsWith(fn:toLowerCase(item.picName),'.gif')}"> 
															<img src="${item.httpUrl}/upload/photo/${item.picName}"
															border='0' width='220' height='150'/><br>&nbsp;&nbsp;&nbsp;${item.fileName}
														</c:when> 
														<c:otherwise> 
															&nbsp;&nbsp;&nbsp;${item.fileName}
														</c:otherwise>
													</c:choose>
											   </td>
											   <td width="30%"><a href="javascript:downloadFile('${item.id}');">下载</a>&nbsp;&nbsp;
											   <a href="javascript:del('${item.id}');">删除</a></td>
											</tr>
										  </c:forEach>
									</table>
									</div>
								</td>
							</tr>
							<tr>
								<th width="15%">安全生产承诺</th>
								<td width="85%" colspan="3">
									<textarea style="width:80%;height:120px" >为使安全承诺制度在安全生产工作建立长效机制，确保全年安全稳定，根据《中华人民共和国安全生产法》、有关安全生产条例内容要求，我作为单位的法定代表人，对本单位安全生产工作负全面责任，并郑重承诺如下：
一、积极落实本单位安全生产工作主体责任，建立健全安全生产责任制和各项规章制度，并严格执行;按规定建立安全管理机构和配备安全管理人员。自觉接受上级部门的监督管理，把安全工作责任切实落实到部门和责任人以及每一个职工。
二、积极落实建立健全安全生产责任制度并严格执行。按规章建立安全生产管理组织，配备安全管理人员。按规定配备消防设施、设备、应急照明灯具，疏散通道畅通。
三、定期组织职工全面开展安全知识、消防知识的教育培训活动，提高从业人员的安全意识和安全业务技能。使从业人员做到“四懂四会”，能够果断、正确地处置各种事件。
四、对重大危险源和易发事故的重点部位实施有效检测、监控;落实重点部位、重点岗位应急措施，建立定期巡回检查制度;制定安全事故应急救援预案，并定期进行演练。
五、杜绝日常工作中违章指挥、违章作业、违反劳动纪律，盲目作业“三违”事件的发生。
六、如发生特、重大责任事故，立即停产整顿，并接受相应行政处罚和经济处罚及承担相应法律责任。</textarea><font style='color:red'>*</font>
									<input name="save" id="safePromise" type="checkbox"  checked>是否同意安全生产承诺
								</td>
								
							</tr>
							<s:if test="entBaseInfo.basePass == 2">
							<tr>
								<th width="15%">审核记录</th>
								<td width="96%" colspan="3">
									<c:forEach items="${checkRecords }" var="cr">
										<fmt:formatDate type="both" value="${cr.createTime}" />，${cr.checkUsername}${cr.checkResult}[${cr.checkRemark}]<br/>
									</c:forEach>
								
								</td>
							</tr>
							</s:if>
							<tr>
								<td colspan="4" height="100px" style="text-align:center">
									<a href="#" id="updateBtn"  class="btn_01" onclick="save()" >更新<b></b></a>&nbsp;
								</td>
							</tr>
						</table>
				</form>
			</div>
		</div>
	</div> --%>
</body>
<script type="text/javascript">
	$(document).ready(function() {
	  //uploadPicOnly("uploadify","${entBaseInfo.linkId}","qyxx","cqtp","fileQueue");
		$("#datetimepicker2").datetimepicker({
			 format: "yyyy-mm-dd",
			 autoclose: true,
			 todayBtn: true,
			 todayHighlight: true,
			 showMeridian: true,
			 pickerPosition: "bottom-left",
			 language: 'zh-CN',//中文，需要引用zh-CN.js包
			 startView: 2,//月视图
			 minView: 2//日期时间选择器所能够提供的最精确的时间选择视图
			 }); 
		/* $("#fileUpload").fileinput({
	        showUpload: true,
	        language:'zh',
	        uploadAsync:true,
	        dropZoneEnabled:false,
	        uploadUrl:'${ctx}/jsp/qyjbxx/entBaseInfoPicSave.action',
	        maxFileCount: 1,
	        maxImageWidth: 600,
	        resizeImage: false,
	        showRemove :false, //显示移除按钮
	        //showCaption: false,
	        //showPreview: false,
	        browseClass: "btn btn-primary btn-lg",
	        //allowedPreviewTypes: ['image'],
            //allowedFileTypes: ['image'],
	        allowedFileExtensions : ['jpg', 'png'],
	        previewFileIcon: ""
	    }).on("filebatchselected", function(event, files) {
	            $(this).fileinput("upload");
	        })
	        .on("fileuploaded", function(event, data) {
	        if(data.response)
	        {
	            alert('处理成功');
	        }
	    }).on('filebatchuploaderror', function(event, data, msg) {
            console.log(data.id);
            console.log(data.index);
            console.log(data.file);
            console.log(data.reader);
            console.log(data.files);
            // get message
            alert(msg);
         }); */
	});
	
	function getUploadFIle(){
		
		var url ='${ctx}/jsp/qyjbxx/getUploadFile.action?entBaseInfo.linkId=${entBaseInfo.linkId}';
		$.getJSON(url, function(json){
			  layer.photos({
			    photos: json
			    ,shift: 5 //0-6的选择，指定弹出图片动画类型，默认随机
			  });
			});
	}
	
	var url ='${ctx}/jsp/qyjbxx/entBaseInfoPicSave.action?entBaseInfoId=${entBaseInfoId}';
	//初始化fileinput控件（第一次初始化）
	function initFileInput(ctrlName, uploadUrl) {    
		
	    var control = $('#' + ctrlName); 

	    control.fileinput({
	        language: 'zh', //设置语言
	        uploadUrl: uploadUrl, //上传的地址
	       //allowedFileExtensions : ["jpg", "JPG", "jpeg", "JPEG", "png", "PNG"],//接收的文件后缀
	        showUpload: true, //是否显示上传按钮
	        maxFileCount: 20,
	        enctype: 'multipart/form-data',
	        showCaption: false,//是否显示标题
	        browseClass: "btn btn-primary", //按钮样式             
	        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>", 
	    });
	}

	
	initFileInput("fileQueue",url);

	$("#fileQueue").on('fileuploaded', function(event, data, previewId, index) {
		addmore(data.response);
		var filepreviews = $("#"+previewId).parent();
		$("#"+previewId).remove();
		 
		if(filepreviews.children().size()<=0){
			$("#fileQueue").fileinput('destroy');
			initFileInput("fileQueue",url);
			$("#fileQueue").fileinput('enable');
			close();
		}
		layer.msg("上传成功");
	});
	$("#fileQueue").on('fileerror', function(event, data, msg) {
		top.layer.open({
			title:"错误",
			icon:2,
			content:"文件:" + data.files[0].name + "上传失败"
		});
		});	
	function addmore(data){
		var result = data.result;
		if(result ==0){
			layer.msg("上传失败");
			return;
		}
		var filename = data.filename;
		var id = data.id;
		var docurl = data.docurl;
		var content = $("#more");
		var index = $("#more .file-preview-frame").size();
		if(filename.indexOf('.jpg')!=-1||filename.indexOf('.bmp')!=-1||filename.indexOf('.png')!=-1||filename.indexOf('.jpeg')!=-1||filename.indexOf('.gif')!=-1){
			var $filepreviewframe = $('<div class="file-preview-frame" id="' + id + '"></div>');
			var $img = $('<img src="'+docurl+'" class="file-preview-image" title="'+filename+'" alt="'+filename+'" style="width:auto;height:160px;">');
			var $filethumbnailfooter = $('<div class="file-thumbnail-footer"></div>');
			//var $formControls =$('<div class="formControls" title="'+filename+'"><input type="text"   name="picList['+index+'].taskRemark"class="taskRemark input-text" placeholder="请写点描述..." onKeyUp="textarealength(this,50)" maxlength="50" style="width: 160px;"><input type="hidden" value="'+id+'" name="picList['+index+'].id"><p class="textarea-numberbar" style="right: 8px;display: none"><em class="textarea-length">0</em>/50</p></div>');
			var $fileactions=$('<div class="file-actions" style="visibility: hidden;"></div>');
			var $filefooterbuttons=$('<div class="file-footer-buttons" style="margin-top: -50px;margin-right: 10px;"></div>');
			var $down = $("<button type='button' class='kv-file-down btn btn-xs btn-default' title='下载文件' onclick=\"down('"+id+"');\"><i class='glyphicon glyphicon-download text-info'></i></button>&nbsp;");
			var $del =$("<button type='button' class='kv-file-remove btn btn-xs btn-default' title='删除文件' onclick=\"del('"+id+"');\"><i class='glyphicon glyphicon-trash text-danger'></i></button>");
			
			$filefooterbuttons.append($down);
			$filefooterbuttons.append($del);
			$fileactions.append($filefooterbuttons);
			//$filethumbnailfooter.append($formControls);
			$filepreviewframe.append($img);
			$filepreviewframe.append($fileactions);
			$filepreviewframe.append($filethumbnailfooter);
			content.append($filepreviewframe);
		}else{
			var $filepreviewframe = $('<div class="file-preview-frame"   data-fileindex="0" title="'+filename+'" style="width:160px;height:160px;"></div>');
			var $filepreviewotherframe=$('<div class="file-preview-other-frame"><div class="file-preview-other"><span class="file-icon-4x"><i class="glyphicon glyphicon-file"></i></span></div></div>');
			var $filethumbnailfooter =$('<div class="file-thumbnail-footer"></div>');
			//var $formControls =$('<div class="file-footer-caption" title="'+filename+'"><input type="text"   name="picList['+index+'].taskRemark"class="taskRemark"><input type="hidden" value="'+id+'" name="picList['+index+'].id"></div>');
			var $fileactions=$('<div class="file-actions" style="visibility: hidden;"></div>');
			var $filefooterbuttons=$('<div class="file-footer-buttons" style="margin-top: -50px;margin-right: 10px;"></div>');
			var $down = $("<button type='button' class='kv-file-down btn btn-xs btn-default' title='下载文件' onclick=\"down('"+id+"');\"><i class='glyphicon glyphicon-download text-info'></i></button>&nbsp;");
			var $del =$("<button type='button' class='kv-file-remove btn btn-xs btn-default' title='删除文件' onclick=\"del('"+id+"');\"><i class='glyphicon glyphicon-trash text-danger'></i></button>");
			
			$filefooterbuttons.append($down);
			$filefooterbuttons.append($del);
			$fileactions.append($filefooterbuttons);
			//$filethumbnailfooter.append($formControls);	
			$filepreviewframe.append($filepreviewotherframe);
			$filepreviewframe.append($fileactions);
			$filepreviewframe.append($filethumbnailfooter);
			content.append($filepreviewframe);
		}
        $(".file-preview-frame").mouseover(function(){ 
            $(this).find(".file-actions").css("visibility","visible");
           }); 
       $(".file-preview-frame").mouseout(function(){ 
           $(this).find(".file-actions").css("visibility","hidden");
          }); 
	}
	
	//删除附件
	function del(self,aid){
		$.ajax({url: "${ctx}/jsp/information/deleteInfoAttach.action"
			,data:{attachId:aid}
			,type: "POST",
			success:function(){
				$(self).parent().parent().parent().parent().fadeOut(300);
			}
	    });
    }
	function down(attachId){
		open("download.action?attachId="+attachId);
	}
    $(".file-preview-frame").mouseover(function(){ 
        $(this).find(".file-actions").css("visibility","visible");
       }); 
   $(".file-preview-frame").mouseout(function(){ 
       $(this).find(".file-actions").css("visibility","hidden");
      });
	
</script>
</html>
