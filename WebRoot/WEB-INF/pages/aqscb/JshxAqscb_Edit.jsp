<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<style>  
  .inputText4 {  
    font-family:Arial,Helvetica,sans-serif;   
    background:none repeat scroll 0 0 #F5F7FD;   
    border:1px solid #B8BFE9;   
    padding:5px 7px;   
    width:100px;   
    vertical-align:middle;   
    height:20px;   
    font-size:12px;   
    margin:0;   
    list-style:none outside none;   
    }   
  </style>
	<script>
	var preNum ="0.0";
	var month = "${month}";
	var beforeMonth = "${beforeMonth}";
		function save(){
			if(Validator.Validate(document.myform1,3)){
				var tb_month = $("#tb_month").val();
				if(month<tb_month&&beforeMonth!=''&&beforeMonth!=null){
					$.messager.alert('警告','填报的月份有误，请确认上月的数据已填报！');
					return;
				}
				for(var type=1;type<3;type++)
				{
					var aqscData = document.getElementById("aqscData0"+type);
					aqscData.value="0";
					for(var i=1;i<22;i++){
		 				var myid = "aqscData0"+type+".data_"+i;
		 				var myValue = document.getElementById(myid).value;
		 				if(myValue == ''||myValue == '0'){
		 					aqscData.value = parseFloat(aqscData.value)+(parseFloat("0"));
		 				}else{
		 				aqscData.value = parseFloat(aqscData.value)+(parseFloat(myValue));
		 				}
		 			}
			 		var newData = aqscData.value;
		 			aqscData.value =  Math.round(newData*100)/100;
				}
				document.myform1.action="jshxAqscbSave.action";
				document.myform1.submit();
			}
		}
		 function clearNoNum(event,obj,type){ 
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
    function changtaotal(type)
    {
    	var aqscData = document.getElementById("aqscData0"+type);
		aqscData.value="0";
		for(var i=1;i<22;i++){
		 	var myid = "aqscData0"+type+".data_"+i;
		 	var myValue = document.getElementById(myid).value;
		 	if(myValue == ''||myValue == '0'){
		 		aqscData.value = parseFloat(aqscData.value)+(parseFloat("0"));
		 	}else{
		 		aqscData.value = parseFloat(aqscData.value)+(parseFloat(myValue));
		 	}
		 }
		 var newData = aqscData.value;
		 aqscData.value =  Math.round(newData*100)/100;
    }
    function getPreNum(obj){
    	if(obj.value != ''){
    		preNum = obj.value;
    	}
    }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxAqscb.id" value="${jshxAqscb.id}">
		<input type="hidden" name="jshxAqscb.createTime" value="<fmt:formatDate type="both" value="${jshxAqscb.createTime}" />">
		<input type="hidden" name="jshxAqscb.updateTime" value="${jshxAqscb.updateTime}">
		<input type="hidden" name="jshxAqscb.createUserID" value="${jshxAqscb.createUserID}">
		<input type="hidden" name="jshxAqscb.updateUserID" value="${jshxAqscb.updateUserID}">
		<input type="hidden" name="jshxAqscb.deptId" value="${jshxAqscb.deptId}">
		<input type="hidden" name="jshxAqscb.delFlag" value="${jshxAqscb.delFlag}">
		<input type="hidden" name="jshxAqscb.tbsj" value="<fmt:formatDate type='both' value='${jshxAqscb.tbsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		<input type="hidden" name="aqscData.id" value="${aqscData01.id}" type="text" maxlength="255">
		<input  type="hidden" name="aqscData.id" value="${aqscData02.id}" type="text" maxlength="255">
		<input  type="hidden" name="aqscData.id" value="${aqscData03.id}" type="text" maxlength="255">
		
		<input type="hidden" name="jshxAqscb.szzname" value="${jshxAqscb.szzname}">
		<input type="hidden" name="jshxAqscb.qymc" value="${jshxAqscb.qymc}">
		<input type="hidden" name="jshxAqscb.szzid" value="${jshxAqscb.szzid}">
		<input type="hidden" name="jshxAqscb.qyid" value="${jshxAqscb.qyid}">
		<input type="hidden" name="jshxAqscb.qylx" value="${jshxAqscb.qylx}">
		<input type="hidden" name="jshxAqscb.hyfl" value="${jshxAqscb.hyfl}">
		<input type="hidden" name="jshxAqscb.qygm" value="${jshxAqscb.qygm}">
		<input type="hidden" name="jshxAqscb.qyzclx" value="${jshxAqscb.qyzclx}">
		
		<input type="hidden" name="jshxAqscb.ifwhpqylx" value="${jshxAqscb.ifwhpqylx}">
		<input type="hidden" name="jshxAqscb.ifzywhqylx" value="${jshxAqscb.ifzywhqylx}">
		<input type="hidden" name="jshxAqscb.ifyhbzjyqy" value="${jshxAqscb.ifyhbzjyqy}">
		<input type="hidden" name="jshxAqscb.szc" value="${jshxAqscb.szc}">
		<input type="hidden" name="jshxAqscb.szcname" value="${jshxAqscb.szcname}">
		
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">填报月份</th>
					<td width="35%" colspan="3">
						<input id="tb_month" class="inputText4" name="jshxAqscb.jshxYear" value="${jshxAqscb.jshxYear}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" type="text" maxlength="255">
						<br><font style="color:green;">友情提示：如果上个月没有上报数据，则本月的数据不可以上报，最后一次上报数据的月份是：</font><font style="color:red;">${beforeMonth}</font>	
					</td>
				</tr>
				<tr>
					<th width="15%">填报人</th>
					<td width="35%"><input class="inputText4" name="jshxAqscb.tbr" value="${jshxAqscb.tbr}" type="text" maxlength="255"dataType="Require" msg="此项为必填"><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">企业负责人</th>
					<td width="35%"><input class="inputText4" name="jshxAqscb.qyfzr" value="${jshxAqscb.qyfzr}" type="text" maxlength="255"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input class="inputText4" name="jshxAqscb.telephone" value="${jshxAqscb.telephone}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" border="1">
							<tr>
								<th  width="5%" style="text-align:center;">序号</th>
								<th  width="8%" style="text-align:center;">类别</th>
								<th  width="20%" style="text-align:center;">项目</th>
								<th  width="8%" style="text-align:center;">本月实际支出数额（万元）</th>
								<th  width="8%" style="text-align:center;">上月实际支出数额（万元）</th>
								<th  width="20%" style="text-align:center;">备注</th>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="5">1</th>
								<th  width="5%" style="text-align:center;" rowspan="5">安全设施</th>
								<th  width="20%" style="text-align:center;">消防设施配备及维护费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_1" id="aqscData01.data_1" value="${aqscData01.data_1}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_1"  id="aqscData02.data_1"  value="${aqscData02.data_1}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_1" value="${aqscData03.data_1}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">自控联锁和监控报警装置安装及维护费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_2" id="aqscData01.data_2" value="${aqscData01.data_2}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_2"  id="aqscData02.data_2" value="${aqscData02.data_2}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_2" value="${aqscData03.data_2}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">防雷接地设施安装及维护费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_3" id="aqscData01.data_3" value="${aqscData01.data_3}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_3" id="aqscData02.data_3" value="${aqscData02.data_3}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_3" value="${aqscData03.data_3}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">特种设备安全维护和检测费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_4" id="aqscData01.data_4" value="${aqscData01.data_4}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input  class="inputText4" name="aqscData.data_4" id="aqscData02.data_4" value="${aqscData02.data_4}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_4" value="${aqscData03.data_4}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全防护装置安装及维护费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_5" id="aqscData01.data_5" value="${aqscData01.data_5}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_5" id="aqscData02.data_5" value="${aqscData02.data_5}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_5" value="${aqscData03.data_5}" type="text" maxlength="255"></th>
							</tr>
							
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="4">2</th>
								<th  width="5%" style="text-align:center;" rowspan="4">安全活动</th>
								<th  width="20%" style="text-align:center;">安全培训和宣传教育活动经费</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_6" id="aqscData01.data_6" value="${aqscData01.data_6}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_6" id="aqscData02.data_6" value="${aqscData02.data_6}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_6" value="${aqscData03.data_6}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全生产综合奖励经费</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_7" id="aqscData01.data_7" value="${aqscData01.data_7}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_7" id="aqscData02.data_7" value="${aqscData02.data_7}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_7" value="${aqscData03.data_7}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全隐患排查整改工作奖励经费</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_8" id="aqscData01.data_8" value="${aqscData01.data_8}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_8" id="aqscData02.data_8" value="${aqscData02.data_8}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_8" value="${aqscData03.data_8}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">应急救援演练经费</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_9" id="aqscData01.data_9" value="${aqscData01.data_9}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_9" id="aqscData02.data_9" value="${aqscData02.data_9}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_9" value="${aqscData03.data_9}" type="text" maxlength="255" ></th>
							</tr>
							
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="6">3</th>
								<th  width="5%" style="text-align:center;" rowspan="6">安全保障</th>
								<th  width="20%" style="text-align:center;">劳动防护用品购置费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_10" id="aqscData01.data_10" value="${aqscData01.data_10}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_10" id="aqscData02.data_10" value="${aqscData02.data_10}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_10" value="${aqscData03.data_10}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">应急救援装备物资购置与储备经费</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_11" id="aqscData01.data_11" value="${aqscData01.data_11}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_11" id="aqscData02.data_11" value="${aqscData02.data_11}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_11" value="${aqscData03.data_11}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">员工职业卫生体检费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_12" id="aqscData01.data_12" value="${aqscData01.data_12}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_12" id="aqscData02.data_12" value="${aqscData02.data_12}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_12" value="${aqscData03.data_12}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">作业场所安全检测费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_13" id="aqscData01.data_13" value="${aqscData01.data_13}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_13" id="aqscData02.data_13" value="${aqscData02.data_13}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_13" value="${aqscData03.data_13}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">安全生产评价与评估费用</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_14" id="aqscData01.data_14" value="${aqscData01.data_14}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_14" id="aqscData02.data_14" value="${aqscData02.data_14}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_14" value="${aqscData03.data_14}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">专家检查咨询活动经费</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_15" id="aqscData01.data_15" value="${aqscData01.data_15}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_15" id="aqscData02.data_15" value="${aqscData02.data_15}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_15" value="${aqscData03.data_15}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;" rowspan="4">4</th>
								<th  width="5%" style="text-align:center;" rowspan="4">安全保险</th>
								<th  width="20%" style="text-align:center;">安全生产责任保险支出</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_16" id="aqscData01.data_16" value="${aqscData01.data_16}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_16" id="aqscData02.data_16" value="${aqscData02.data_16}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_16" value="${aqscData03.data_16}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">社保工伤保险支出</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_17" id="aqscData01.data_17" value="${aqscData01.data_17}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_17" id="aqscData02.data_17" value="${aqscData02.data_17}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_17" value="${aqscData03.data_17}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">员工人身意外伤害保险支出</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_18" id="aqscData01.data_18" value="${aqscData01.data_18}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_18" id="aqscData02.data_18" value="${aqscData02.data_18}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_18" value="${aqscData03.data_18}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="20%" style="text-align:center;">企业公众责任险支出</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_19" id="aqscData01.data_19" value="${aqscData01.data_19}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_19" id="aqscData02.data_19" value="${aqscData02.data_19}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_19" value="${aqscData03.data_19}" type="text" maxlength="255"></th>
							</tr>
							
							<tr>
								<th  width="5%" style="text-align:center;">5</th>
								<th  width="5%" style="text-align:center;">安全治理</th>
								<th  width="20%" style="text-align:center;">用于隐患整改的改造投资</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_20" id="aqscData01.data_20" value="${aqscData01.data_20}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_20" id="aqscData02.data_20" value="${aqscData02.data_20}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_20" value="${aqscData03.data_20}" type="text" maxlength="255"></th>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;">6</th>
								<th  width="5%" style="text-align:center;">其他安全费用</th>
								<th  width="20%" style="text-align:center;"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_21" id="aqscData01.data_21" value="${aqscData01.data_21}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'1')" onblur="changtaotal('1');" onKeyDown="getPreNum(this)"></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_21" id="aqscData02.data_21" value="${aqscData02.data_21}" type="text" maxlength="255" onKeyUp="clearNoNum(event,this,'2')" onblur="changtaotal('2');" onKeyDown="getPreNum(this)"></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_21" value="${aqscData03.data_21}" type="text" maxlength="255" ></th>
							</tr>
							<tr>
								<th  width="5%" style="text-align:center;"></th>
								<th  width="5%" style="text-align:center;" colspan="2">合    计</th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_22" id="aqscData01" value="${aqscData01.data_22}" type="text" maxlength="255" readonly ></th>
								<th  width="8%" style="text-align:center;"><input class="inputText4" name="aqscData.data_22" id="aqscData02" value="${aqscData02.data_22}" type="text" maxlength="255" readonly></th>
								<th  width="20%" style="text-align:center;"><input class="inputText4" name="aqscData.data_22" id="aqscData03"  value="${aqscData03.data_22}" type="text" maxlength="255"></th>
							</tr>
							<tr>
							<td colspan="6" height="100px"  style="text-align:center;">
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
						</td>
				</tr>
				
			</table>
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
