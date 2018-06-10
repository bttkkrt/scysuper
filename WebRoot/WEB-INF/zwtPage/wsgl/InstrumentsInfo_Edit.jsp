<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%><link href="${ctx}/webResources/zwt/css/sipac.css" rel="stylesheet">
	<script>
	var divvalue = "${instrumentsInfo.instrumentType}";
	$(function(){
		$('.clsdiv230').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv240').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		$('.clsdiv290').each(function(){
     		$(this).data("dataIgnore","dataIgnore");
		});
		
		if(divvalue == '23')
		{
			var type = "${spoPenDecCom.fineMethod}";
			if(type == '1')
			{
				$('.clsdiv230').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
		}
		else if(divvalue == '24')
		{
			var type = "${spoPenDecPer.fineMethod}";
			if(type == '1')
			{
				$('.clsdiv240').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
		}
		else if(divvalue == '29')
		{
			var type = "${posFinRat.postponeMethod}";
			if(type == '1')
			{
				$('.clsdiv290').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
		}
	});
	function setCheValue(obj)
	{
		if(obj == '1')
		{
			var sfz = document.getElementById('check1').checked?1:0;
			document.getElementById('sfz').value = sfz;
		}
		else if(obj == '2')
		{
			var yyzz = document.getElementById('check2').checked?1:0;
			document.getElementById('yyzz').value = yyzz;
		}
		else if(obj == '3')
		{
			var fddbzm = document.getElementById('check3').checked?1:0;
			document.getElementById('fddbzm').value = fddbzm;
		}
		else if(obj == '4')
		{
			var qt = document.getElementById('check4').checked?1:0;
			document.getElementById('qt').value = qt;
		}
	}
		
	var num = 100;
	function addNewWp()
	{
		var s = "<tr id='" + num + "'>";
		s += "<td style='text-align:center'><input name='samplingAssociate.evidenceName' type='text' onblur='setVal(this)' datatype='*1-127' errormsg='证据物品名称必须是1到127位字符！' nullmsg='证据物品名称不能为空！' sucmsg='证据物品名称填写正确！'  maxlength='127' class='clsdiv4'></td>";
		s += "<td style='text-align:center'><input name='samplingAssociate.specificationLot'  type='text' onblur='setVal(this)' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='samplingAssociate.samplingNum'  type='text' onblur='setVal(this)' maxlength='127'></td>";
		s += "<td style='text-align:center'><a href='###' class='btn_01_mini1' onclick=delWp('" + num + "','1')>删除</a><a href='###' class='btn_01_mini1' onclick=addNewWp()>添加</a></td>";
		s += "</tr>";
		$("#more").append(s);
		num = num +1;
	}
		
													
	var num1 = 200;
	function addNewZj()
	{
		var s = "<tr id='" + num1 + "'>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.evidenceName' type='text' onblur='setVal(this)' style='width:60px' datatype='*1-127' errormsg='证据名称必须是1到127位字符！' nullmsg='证据名称不能为空！' sucmsg='证据名称填写正确！'  maxlength='127' class='clsdiv5'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.specificationModel'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.originPlace'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.condition'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.company'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.price'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.inventoryNum'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='inventoryAssociate.remark'  type='text' onblur='setVal(this)' style='width:60px' maxlength='127'></td>";
		s += "<td style='text-align:center'><a href='###' class='btn_01_mini1' onclick=delWp('" + num1 + "','1')>删除</a><a href='###' class='btn_01_mini1' onclick=addNewZj()>添加</a></td>";
		s += "</tr>";
		$("#more1").append(s);
		num1 = num1 +1;
	}
												
	var num2 = 300;
	function addNewJdwp()
	{
		var s = "<tr id='" + num2 + "'>";
		s += "<td style='text-align:center'><input name='identifyItemAssociate.itemName' type='text' onblur='setVal(this)' datatype='*1-127' errormsg='物品名称必须是1到127位字符！' nullmsg='物品名称不能为空！' sucmsg='物品名称填写正确！'  maxlength='127' class='clsdiv14'></td>";
		s += "<td style='text-align:center'><input name='identifyItemAssociate.specificationModel'  type='text' onblur='setVal(this)' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='identifyItemAssociate.identifyNum'  type='text' onblur='setVal(this)' maxlength='127'></td>";
		s += "<td style='text-align:center'><input name='identifyItemAssociate.remark'  type='text' onblur='setVal(this)' maxlength='127'></td>";
		s += "<td style='text-align:center'><a href='###' class='btn_01_mini1' onclick=delWp('" + num2 + "','1')>删除</a><a href='###' class='btn_01_mini1' onclick=addNewJdwp()>添加</a></td>";
		s += "</tr>";
		$("#more2").append(s);
		num2 = num2 +1;
	}
	
	var num3 = 400;
	function addNewBl()
	{
		var s = "<tr id='aa" + num3 + "'>";
		s += "<td>问：<textarea name='inqRecRecord.askRecord' style='width:88%;height:60px' datatype='*1-2000' errormsg='问题必须是1到2000位字符！' nullmsg='问题不能为空！' sucmsg='问题填写正确！'  onKeyDown='if(this.value.length > 2000) this.value=this.value.substr(0,2000)' class='clsdiv2'></textarea><font style='color:red'>*</font>";
		s += "<td rowspan='2'><a href='###' class='btn_01_mini1' onclick=delBl('" + num3 + "','1')>删除</a><a href='###' class='btn_01_mini1' onclick=addNewBl()>添加</a></td>";
		s += "</tr>";
		s += "<tr id='bb" + num3 + "'>";
		s += "<td>答：<textarea name='inqRecRecord.recRecord' style='width:88%;height:60px' datatype='*1-2000' errormsg='回答必须是1到2000位字符！' nullmsg='回答不能为空！' sucmsg='回答填写正确！'  onKeyDown='if(this.value.length > 2000) this.value=this.value.substr(0,2000)' class='clsdiv2'></textarea><font style='color:red'>*</font>";
		s += "</tr>";
		$("#blMore").append(s);
		num3 = num3 +1;
	}
	
	var num4 = 501;
	function addNewBxr()
	{	
		var s = "<tr id='bxr" + num4 + "'>";
		s += "<td style='text-align:center'><input name='inquiryNotice.askPerson' type='text' onblur='setVal(this)' datatype='*1-127' errormsg='询问人员必须是1到127位字符！' nullmsg='询问人员不能为空！' sucmsg='询问人员填写正确！'  maxlength='127' class='clsdiv1'><font style='color:red'>*</font></td>";
		s += "<td style='text-align:center'><a href='###' class='btn_01_mini1' onclick=delBxr('" + num4 + "')>删除</a></td>";
		s += "</tr>";
		$("#bxrMore").append(s);
		num4 = num4 +1;
	}
	
	function delBxr(obj)
	{
		 $("tr").remove("tr[id=bxr"+obj+"]");
	}
	function delWp(aid,flag)
	{
		    $.messager.confirm("删除","确定要删除吗?",function(result){
		        if(result){
		        	if(flag == '0')
					{
						$.ajax({
	                	url : "${ctx}/jsp/cyzjglb/samplingAssociateDel.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	ids : aid
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','删除时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','删除成功！');
	                           $("tr").remove("tr[id="+aid+"]");
	                        }else{
	                        	$.messager.alert('错误','删除时出错！');
	                        }
	                    }
	                	});
					}
					else
					{
						$("tr").remove("tr[id="+aid+"]");
					}
		        }
		    });
		
		
	}
		
	function delZj(aid)
	{
		    $.messager.confirm("删除","确定要删除吗?",function(result){
		        if(result){
						$.ajax({
	                	url : "${ctx}/jsp/xxdjbczjqdglb/inventoryAssociateDel.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	ids : aid
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','删除时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','删除成功！');
	                           $("tr").remove("tr[id="+aid+"]");
	                        }else{
	                        	$.messager.alert('错误','删除时出错！');
	                        }
	                    }
	                	});
		        }
		    });
		
		
	}
	
	function delJdwp(aid)
	{
		    $.messager.confirm("删除","确定要删除吗?",function(result){
		        if(result){
						$.ajax({
	                	url : "${ctx}/jsp/jdwpglb/identifyItemAssociateDel.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	ids : aid
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','删除时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','删除成功！');
	                           $("tr").remove("tr[id="+aid+"]");
	                        }else{
	                        	$.messager.alert('错误','删除时出错！');
	                        }
	                    }
	                	});
		        }
		    });
		
		
	}
	
	function delBl(aid,flag)
	{
		    $.messager.confirm("删除","确定要删除吗?",function(result){
		        if(result){
		        	if(flag == '0')
					{
						$.ajax({
	                	url : "${ctx}/jsp/xwbl/inqRecRecordDel.action",
	                	type: 'post',
	                    dataType: 'json',
	                    async : false,
	                    data:{ 
	                    	ids : aid
	                    },
	                    error: function(){
	                    	$.messager.alert('错误','删除时出错！');
	                    },
	                    success: function(data){
	                        if(data.result){
	                        	$.messager.alert('提示','删除成功！');
	                            $("tr").remove("tr[id=aa"+aid+"]");
	                            $("tr").remove("tr[id=bb"+aid+"]");
	                        }else{
	                        	$.messager.alert('错误','删除时出错！');
	                        }
	                    }
	                	});
	                }
	                else
	                {
	                	$("tr").remove("tr[id=aa"+aid+"]");
	                    $("tr").remove("tr[id=bb"+aid+"]");
	                }
		        }
		    });
		
		
	}
		
	function setVal(obj)
	{
		var a = obj.value;
		obj.setAttribute("value", a);
	}
	
	function queryYj(obj)
	{
		var ids = document.getElementById('lawBasic'+obj).value;
		var flag = "lawBasic"+obj + ";" + "lawName" + obj;
		popupCenter("${ctx}/jsp/zfyj/lawBasisLists.action?ids="+ids+"&flag="+flag, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
	}
	
	function queryGd(obj)
	{
		var ids = document.getElementById('proId'+obj).value;
		var flag = "proId"+obj + ";" + "proName" + obj;
		popupCenter("${ctx}/jsp/zfyj/lawBasisLists.action?ids="+ids+"&flag="+flag, "setCompany", "800", "600", "no", "no", "no", "no", "no","no");
	}
	
	function changeFqfs(obj)
	{
		if(obj == '0')
		{
			document.getElementById('fkfs1').style.display = "none";
			document.getElementById('fkfs2').style.display = "none";
			$('.clsdiv290').each(function(){
     			$(this).data("dataIgnore","dataIgnore");
			});
		}
		else
		{
			document.getElementById('fkfs1').style.display = "";
			document.getElementById('fkfs2').style.display = "";
			$('.clsdiv290').each(function(){
     			$(this).removeData("dataIgnore");
			});
		}
	}
	
	function changeYhxx(obj,type)
	{
		if(obj == '0')
		{
			document.getElementById('yhxx1'+type).style.display = "none";
			if(type == '1')
			{
				$('.clsdiv230').each(function(){
     				$(this).data("dataIgnore","dataIgnore");
				});
			}
			else
			{
				$('.clsdiv240').each(function(){
     				$(this).data("dataIgnore","dataIgnore");
				});
			}
		}
		else
		{
			document.getElementById('yhxx1'+type).style.display = "";
			if(type == '1')
			{
				$('.clsdiv230').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
			else
			{
				$('.clsdiv240').each(function(){
     				$(this).removeData("dataIgnore");
				});
			}
		}
	}
	
	
	function checkInfo(obj,name)
    {
        var ches = document.getElementsByName(name);
		var ischecked = 0;
		var ids = "";
		for(var i=0;i<ches.length;i++)
		{
			if(ches[i].checked)
			{
				ids += ches[i].value + ",";
				ischecked ++;
			}
		}
		if(ischecked ==3)
		{
			alert("最多只能选择两个");
			obj.checked = false;
			ids = ids.replace(obj.value+",","");
		}
		lockUser(ids);
    }
    
    
    function selectUser()
	{
		popupCenter("${ctx}/jsp/wsgl/selectUsers.action", "setUser", "800", "600", "no", "no", "no", "no", "no","no");
	}
	
	function lockUser(obj)
	{	
		var time = document.getElementById("time").value;
		if(obj != "")
		{
			$.ajax({
	         url : "${ctx}/jsp/wsgl/lockUser.action?ids=" + obj + "&instrumentsInfo.caseId=${instrumentsInfo.caseId}&instrumentsInfo.time=" + time + "&instrumentsInfo.instrumentType=${instrumentsInfo.instrumentType}",
	         type: 'post',
	         dataType: 'json',
	         async : false,
	         data:{
			 },
	         error: function(){
	         },
	         success: function(data){
	         }
	     });
		}
	}
	
	function lockAndShowUser(obj,type)
	{	
		var time = document.getElementById("time").value;
		if(obj != "")
		{
			$.ajax({
	         url : "${ctx}/jsp/wsgl/lockAndShowUser.action?ids=" + obj + "&instrumentsInfo.caseId=${instrumentsInfo.caseId}&instrumentsInfo.time=" + time + "&instrumentsInfo.instrumentType=${instrumentsInfo.instrumentType}",
	         type: 'post',
	         dataType: 'json',
	         async : false,
	         data:{
			 },
	         error: function(){
	         },
	         success: function(data){
	         	document.getElementById('zfry' + type).innerHTML="、"+data.name;
	         	document.getElementById('zfzh' + type).innerHTML="、"+data.zfzh;
	         }
	     });
		}
	}
	
	function closeWindow()
	{
		var time = document.getElementById("time").value;
		$.ajax({
	         url : "${ctx}/jsp/wsgl/unlockUser.action?instrumentsInfo.caseId=${instrumentsInfo.caseId}&instrumentsInfo.time=" + time + "&instrumentsInfo.instrumentType=${instrumentsInfo.instrumentType}",
	         type: 'post',
	         dataType: 'json',
	         async : false,
	         data:{
			 },
	         error: function(){
	         },
	         success: function(data){
	         	if(data.result)
	         	{
	         		parent.close_win('win_instrumentsInfo');
	         	}
	         }
	     });
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
    
    
    function check()
    {
    	var type = "${instrumentsInfo.instrumentType}";
    	if(type == '1')
    	{
    		var ss = document.getElementsByName("inquiryNotice.askPerson");
    		if(ss.length == 0)
    		{
    			alert("至少需要填写一个被询问人！");
    			hideLoading();
    			return false;
    		}
    		else
    		{	
    			return true;
    		}
    	}
    	else if(type == '2')
    	{
    		var ss = document.getElementsByName("inqRecRecord.askRecord");
    		if(ss.length == 0)
    		{
    			alert("至少需要填写一条询问笔录！");
    			hideLoading();
    			return false;
    		}
    		else
    		{	
    			return true;
    		}
    	}
    	else if(type == '4')
    	{
    		var ss = document.getElementsByName("samplingAssociate.evidenceName");
    		if(ss.length == 0)
    		{
    			alert("至少需要填写一条抽样取证物品！");
    			hideLoading();
    			return false;
    		}
    		else
    		{	
    			return true;
    		}
    	}
    	else if(type == '5')
    	{
    		var ss = document.getElementsByName("inventoryAssociate.evidenceName");
    		if(ss.length == 0)
    		{
    			alert("至少需要填写一条证据！");
    			hideLoading();
    			return false;
    		}
    		else
    		{	
    			return true;
    		}
    	}
    	else if(type == '14')
    	{
    		var ss = document.getElementsByName("identifyItemAssociate.itemName");
    		if(ss.length == 0)
    		{
    			alert("至少需要填写一条鉴定物品！");
    			hideLoading();
    			return false;
    		}
    		else
    		{	
    			return true;
    		}
    	}
    	return true;
    }
    
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: auto;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px">
			<div class="cell" style="width: 100%;">
	<form class="checkform" id="myform1" name="myform1" method="post" enctype="multipart/form-data" action="instrumentsInfoSave.action" onsubmit="return check();">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="instrumentsInfo.id" value="${instrumentsInfo.id}">
		<input type="hidden" name="instrumentsInfo.createTime" value="<fmt:formatDate type="both" value="${instrumentsInfo.createTime}" />">
		<input type="hidden" name="instrumentsInfo.updateTime" value="${instrumentsInfo.updateTime}">
		<input type="hidden" name="instrumentsInfo.createUserID" value="${instrumentsInfo.createUserID}">
		<input type="hidden" name="instrumentsInfo.updateUserID" value="${instrumentsInfo.updateUserID}">
		<input type="hidden" name="instrumentsInfo.deptId" value="${instrumentsInfo.deptId}">
		<input type="hidden" name="instrumentsInfo.delFlag" value="${instrumentsInfo.delFlag}">
		<input type="hidden" name="instrumentsInfo.linkId" value="${instrumentsInfo.linkId}">
		<input type="hidden" name="instrumentsInfo.fileName" value="${instrumentsInfo.fileName}">
		<input type="hidden" name="instrumentsInfo.wsh" value="${instrumentsInfo.wsh}">
		<input type="hidden" name="instrumentsInfo.ajbz" value="${instrumentsInfo.ajbz}">
		<input type="hidden" name="instrumentsInfo.ajh" value="${instrumentsInfo.ajh}">
		<input type="hidden" name="instrumentsInfo.ajhNum" value="${instrumentsInfo.ajhNum}">
		<input type="hidden" name="instrumentsInfo.ifCheck" value="${instrumentsInfo.ifCheck}">
		<input type="hidden" name="instrumentsInfo.ifPrint" value="${instrumentsInfo.ifPrint}">
		<input id="caseId" type="hidden" name="instrumentsInfo.caseId" value="${instrumentsInfo.caseId}"/>
		<input type="hidden" name="instrumentsInfo.caseName" value="${instrumentsInfo.caseName}"/>
		<input type="hidden" name="instrumentsInfo.companyName" value="${instrumentsInfo.companyName}"/>
		<input type="hidden" id="instrumentType" name="instrumentsInfo.instrumentType" value="${instrumentsInfo.instrumentType}"/>
		<input type="hidden" name="instrumentsInfo.lastFile" value="${instrumentsInfo.lastFile}"/>
		<input type="hidden" name="inquiryNotice.id" value="${inquiryNotice.id}">
		<input type="hidden" name="inquiryRecord.id" value="${inquiryRecord.id}">
		<input type="hidden" name="inquestRecord.id" value="${inquestRecord.id}">
		<input type="hidden" name="samplingEvidence.id" value="${samplingEvidence.id}">
		<input type="hidden" name="preserveEvidence.id" value="${preserveEvidence.id}">
		<input type="hidden" name="noticeEvidence.id" value="${noticeEvidence.id}">
		<input type="hidden" name="inventoryCheck.id" value="${inventoryCheck.id}">
		<input type="hidden" name="inventoryDecision.id" value="${inventoryDecision.id}">
		<input type="hidden" name="siteCheckRecord.id" value="${siteCheckRecord.id}">
		<input type="hidden" name="liveActionDecision.id" value="${liveActionDecision.id}">
		<input type="hidden" name="orderDeadlineBook.id" value="${orderDeadlineBook.id}">
		<input type="hidden" name="reviewSubmission.id" value="${reviewSubmission.id}">
		<input type="hidden" name="enforenceDecision.id" value="${enforenceDecision.id}">
		<input type="hidden" name="identifyAttorney.id" value="${identifyAttorney.id}">
		<input type="hidden" name="penaltyNotice.id" value="${penaltyNotice.id}">
		<input type="hidden" name="partyStateNote.id" value="${partyStateNote.id}">
		<input type="hidden" name="hearingTell.id" value="${hearingTell.id}">
		<input type="hidden" name="hearingNotice.id" value="${hearingNotice.id}">
		<input type="hidden" name="hearingNote.id" value="${hearingNote.id}">
		<input type="hidden" name="hearingReport.id" value="${hearingReport.id}">
		<input type="hidden" name="caseProcessApproval.id" value="${caseProcessApproval.id}">
		<input type="hidden" name="penBraRec.id" value="${penBraRec.id}">
		<input type="hidden" name="spoPenDecCom.id" value="${spoPenDecCom.id}">
		<input type="hidden" name="spoPenDecPer.id" value="${spoPenDecPer.id}">
		<input type="hidden" name="penDecCom.id" value="${penDecCom.id}">
		<input type="hidden" name="penDecPer.id" value="${penDecPer.id}">
		<input type="hidden" name="posFinApp.id" value="${posFinApp.id}">
		<input type="hidden" name="posFinRat.id" value="${posFinRat.id}">
		<input type="hidden" name="enfApp.id" value="${enfApp.id}">
		<input type="hidden" name="closeApproval.id" value="${closeApproval.id}">
		<input type="hidden" name="caseRefer.id" value="${caseRefer.id}">
		<input type="hidden" name="caseTransfer.id" value="${caseTransfer.id}">
		<input type="hidden" name="dcbg.id" value="${dcbg.id}">
		<input type="hidden" name="type" value="${type}">
		
		<s:if test="type == 1">
		<input type="hidden" name="preserveEvidence.underTime" value="${preserveEvidence.underTime}">
		<input type="hidden" name="preserveEvidence.departPerson" value="${preserveEvidence.departPerson}">
		<input type="hidden" name="preserveEvidence.departComment" value="${preserveEvidence.departComment}">
		<input type="hidden" name="preserveEvidence.departTime" value="${preserveEvidence.departTime}">
		<input type="hidden" name="preserveEvidence.officePerson" value="${preserveEvidence.officePerson}">
		<input type="hidden" name="preserveEvidence.officeComment" value="${preserveEvidence.officeComment}">
		<input type="hidden" name="preserveEvidence.officeTime" value="${preserveEvidence.officeTime}">
		
		<input type="hidden" name="inventoryCheck.underTime" value="${inventoryCheck.underTime}">
		<input type="hidden" name="inventoryCheck.departPerson" value="${inventoryCheck.departPerson}">
		<input type="hidden" name="inventoryCheck.departComment" value="${inventoryCheck.departComment}">
		<input type="hidden" name="inventoryCheck.departTime" value="${inventoryCheck.departTime}">
		<input type="hidden" name="inventoryCheck.officePerson" value="${inventoryCheck.officePerson}">
		<input type="hidden" name="inventoryCheck.officeComment" value="${inventoryCheck.officeComment}">
		<input type="hidden" name="inventoryCheck.officeTime" value="${inventoryCheck.officeTime}">
		
		<input type="hidden" name="penaltyNotice.departPerson" value="${penaltyNotice.departPerson}">
		<input type="hidden" name="penaltyNotice.departComment" value="${penaltyNotice.departComment}">
		<input type="hidden" name="penaltyNotice.departTime" value="${penaltyNotice.departTime}">
		<input type="hidden" name="penaltyNotice.officePerson" value="${penaltyNotice.officePerson}">
		<input type="hidden" name="penaltyNotice.officeComment" value="${penaltyNotice.officeComment}">
		<input type="hidden" name="penaltyNotice.officeTime" value="${penaltyNotice.officeTime}">
		<input type="hidden" name="penaltyNotice.underTime" value="${penaltyNotice.underTime}">
		
		<input type="hidden" name="hearingTell.departPerson" value="${hearingTell.departPerson}">
		<input type="hidden" name="hearingTell.departComment" value="${hearingTell.departComment}">
		<input type="hidden" name="hearingTell.departTime" value="${hearingTell.departTime}">
		<input type="hidden" name="hearingTell.officePerson" value="${hearingTell.officePerson}">
		<input type="hidden" name="hearingTell.officeComment" value="${hearingTell.officeComment}">
		<input type="hidden" name="hearingTell.officeTime" value="${hearingTell.officeTime}">
		<input type="hidden" name="hearingTell.underTime" value="${hearingTell.underTime}">
		
		<input type="hidden" name="hearingReport.hearChairComment" value="${hearingReport.hearChairComment}">
		<input type="hidden" name="hearingReport.hearChairTime" value="${hearingReport.hearChairTime}">
		<input type="hidden" name="hearingReport.checkComment" value="${hearingReport.checkComment}">
		<input type="hidden" name="hearingReport.checkPerson" value="${hearingReport.checkPerson}">
		<input type="hidden" name="hearingReport.checkTime" value="${hearingReport.checkTime}">
		
		<input type="hidden" name="caseProcessApproval.underTime" value="${caseProcessApproval.underTime}">
		<input type="hidden" name="caseProcessApproval.checkPerson" value="${caseProcessApproval.checkPerson}">
		<input type="hidden" name="caseProcessApproval.checkComment" value="${caseProcessApproval.checkComment}">
		<input type="hidden" name="caseProcessApproval.checkTime" value="${caseProcessApproval.checkTime}">
		<input type="hidden" name="caseProcessApproval.approverPerson" value="${caseProcessApproval.approverPerson}">
		<input type="hidden" name="caseProcessApproval.approverComment" value="${caseProcessApproval.approverComment}">
		<input type="hidden" name="caseProcessApproval.approverTime" value="${caseProcessApproval.approverTime}">
		
		<input type="hidden" name="posFinApp.underTime" value="${posFinApp.underTime}">
		<input type="hidden" name="posFinApp.checkPerson" value="${posFinApp.checkPerson}">
		<input type="hidden" name="posFinApp.checkComment" value="${posFinApp.checkComment}">
		<input type="hidden" name="posFinApp.checkTime" value="${posFinApp.checkTime}">
		<input type="hidden" name="posFinApp.approver" value="${posFinApp.approver}">
		<input type="hidden" name="posFinApp.approverComment" value="${posFinApp.approverComment}">
		<input type="hidden" name="posFinApp.approverTime" value="${posFinApp.approverTime}">
		
		<input type="hidden" name="closeApproval.underTime" value="${closeApproval.underTime}">
		<input type="hidden" name="closeApproval.checkPerson" value="${closeApproval.checkPerson}">
		<input type="hidden" name="closeApproval.checkComment" value="${closeApproval.checkComment}">
		<input type="hidden" name="closeApproval.checkTime" value="${closeApproval.checkTime}">
		<input type="hidden" name="closeApproval.approver" value="${closeApproval.approver}">
		<input type="hidden" name="closeApproval.approverComment" value="${closeApproval.approverComment}">
		<input type="hidden" name="closeApproval.approverTime" value="${closeApproval.approverTime}">
		
		<input type="hidden" name="caseRefer.underTime" value="${caseRefer.underTime}">
		<input type="hidden" name="caseRefer.departPerson" value="${caseRefer.departPerson}">
		<input type="hidden" name="caseRefer.departComment" value="${caseRefer.departComment}">
		<input type="hidden" name="caseRefer.departTime" value="${caseRefer.departTime}">
		<input type="hidden" name="caseRefer.officePerson" value="${caseRefer.officePerson}">
		<input type="hidden" name="caseRefer.officeComment" value="${caseRefer.officeComment}">
		<input type="hidden" name="caseRefer.officeTime" value="${caseRefer.officeTime}">
		
		<input type="hidden" name="dcbg.underTime" value="${dcbg.underTime}">
		<input type="hidden" name="dcbg.checkPerson" value="${dcbg.checkPerson}">
		<input type="hidden" name="dcbg.checkComment" value="${dcbg.checkComment}">
		<input type="hidden" name="dcbg.checkTime" value="${dcbg.checkTime}">
		<input type="hidden" name="dcbg.approverPerson" value="${dcbg.approverPerson}">
		<input type="hidden" name="dcbg.approverComment" value="${dcbg.approverComment}">
		<input type="hidden" name="dcbg.approverTime" value="${dcbg.approverTime}">
		</s:if>
		
			<table width="100%" border="0">
				<s:if test="flag == 'mod'">
					<th width="15%">文书时间</th>
					<td width="85%" colspan="3"><input id="time" name="instrumentsInfo.time" value="<fmt:formatDate type='date' value='${instrumentsInfo.time}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" maxlength="127" style="width:50%"><font style='color:red'>*</font></td>
				</s:if>
				<s:else>
					<input type="hidden" id="time" name="instrumentsInfo.time" value="<fmt:formatDate type="date" value="${instrumentsInfo.time}" />">
				</s:else>
				<!-- 询问通知书  div1-->
				<s:if test="instrumentsInfo.instrumentType==1">
					<tr>
						<th width="15%">
							被询问人姓名
							<s:if test="flag == 'add'">
							<br/><a href="###" class="btn_01_mini1" onclick="addNewBxr()">添加</a>
							</s:if>
						</th>
						<td width="85%" colspan="3">
							<s:if test="flag == 'add'">
							<table id="bxrMore">
								<tr id="bxr500">
									<td style='text-align:center'>
										<input name="inquiryNotice.askPerson" value="${inquiryNotice.askPerson}" type="text" datatype="*1-127"  errormsg='询问人员必须是1到127位字符！' nullmsg='询问人员不能为空！' sucmsg='询问人员填写正确！'  maxlength="127" class="clsdiv1"><font style='color:red'>*</font>
									</td>
									<td style='text-align:center'>
										<a href="###" class="btn_01_mini1" onclick="delBxr('500')">删除</a>
									</td>
								</tr>
							</table>
							</s:if>
							<s:else>
								<input name="inquiryNotice.askPerson" value="${inquiryNotice.askPerson}" type="text" datatype="*1-127"  errormsg='询问人员必须是1到127位字符！' nullmsg='询问人员不能为空！' sucmsg='询问人员填写正确！'  maxlength="127" style="width:80%" class="clsdiv1"><font style='color:red'>*</font>
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="15%">询问时间</th>
						<td width="35%"><input name="inquiryNotice.inquiryTime" value="<fmt:formatDate type='both' value='${inquiryNotice.inquiryTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv1" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*1-127" errormsg='询问时间必须是1到127位字符！' nullmsg='询问时间不能为空！' sucmsg='询问时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
						<th width="15%">询问地点</th>
						<td width="35%"><input name="inquiryNotice.inquiryAddress" value="${inquiryNotice.inquiryAddress}" type="text" datatype="*1-127"  errormsg='询问地点必须是1到127位字符！' nullmsg='询问地点不能为空！' sucmsg='询问地点填写正确！'  maxlength="127" style="width:80%" class="clsdiv1"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">询问材料</th>
						<td width="85%" colspan="3">
							<input type="hidden" id="sfz" name="inquiryNotice.sfz" value="${inquiryNotice.sfz}"><input type="checkbox" id="check1" name="check1" <s:if test="inquiryNotice.sfz == 1">checked</s:if> onclick="setCheValue('1')"/>&nbsp;身份证&nbsp;
							<input type="hidden" id="yyzz" name="inquiryNotice.yyzz" value="${inquiryNotice.yyzz}"><input type="checkbox" id="check2" name="check2"  <s:if test="inquiryNotice.yyzz == 1">checked</s:if> onclick="setCheValue('2')"/>&nbsp;营业执照&nbsp;
							<input type="hidden" id="fddbzm" name="inquiryNotice.fddbzm" value="${inquiryNotice.fddbzm}"><input type="checkbox" id="check3" name="check3"  <s:if test="inquiryNotice.fddbzm == 1">checked</s:if> onclick="setCheValue('3')"/>&nbsp;法定代表人身份证明或者委托书&nbsp;
							<input type="hidden" id="qt" name="inquiryNotice.qt" value="${inquiryNotice.qt}"><input type="checkbox" id="check4" name="check4" <s:if test="inquiryNotice.qt == 1">checked</s:if> onclick="setCheValue('4')"/>&nbsp;<input name="inquiryNotice.docMaterial" value="${inquiryNotice.docMaterial}" type="text" maxlength="127">
						</td>
					</tr>
				</s:if>
				
				<!-- 询问笔录  div2 -->
				<s:if test="instrumentsInfo.instrumentType==2">
					<tr>
						<th width="15%">询问开始时间</th>
						<td width="35%"><input id="starttime1" name="inquiryRecord.inquiryPeriod"  value="<fmt:formatDate type='both' value='${inquiryRecord.inquiryPeriod}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime1\')}'})" datatype="*1-127" errormsg='询问开始时间必须是1到127位字符！' nullmsg='询问开始时间不能为空！' sucmsg='询问开始时间填写正确！'  maxlength="127"  style="width:60%"><font style='color:red'>*</font></td>
						<th width="15%">询问结束时间</th>
						<td width="35%"><input id="endtime1" name="inquiryRecord.endTime" value="<fmt:formatDate type='both' value='${inquiryRecord.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime1\')}'})" datatype="*1-127" errormsg='询问结束时间必须是1到127位字符！' nullmsg='询问结束时间不能为空！' sucmsg='询问结束时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">询问人</th>
						<td width="35%">
							<s:if test="type != 1">
							<cus:SelectOneTag property="inquiryRecord.inquiryPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}')" value="${inquiryRecord.inquiryPerson}" datatype="*1-127" errormsg='询问人必须是1到127位字符！' nullmsg='询问人不能为空！' sucmsg='询问人填写正确！'  maxlength="127" style="width:60%" class="clsdiv2" onchange="lockAndShowUser(this.value,'1');"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
							<cus:SelectOneTag property="inquiryRecord.inquiryPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${inquiryRecord.inquiryPerson}" datatype="*1-127" errormsg='询问人必须是1到127位字符！' nullmsg='询问人不能为空！' sucmsg='询问人填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"/><font style='color:red'>*</font>
							</s:else>
						</td>
						<th width="15%">记录人</th>
						<td width="35%">
							<input type="hidden"  name="inquiryRecord.recordPerson" value="${inquiryRecord.recordPerson}">
							<input type="hidden" name="inquiryRecord.recordPersonName" value="${inquiryRecord.recordPersonName}">
							<input type="hidden" name="inquiryRecord.recordPersonZh" value="${inquiryRecord.recordPersonZh}">
							${inquiryRecord.recordPersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">询问次数</th>
						<td width="35%"><input name="inquiryRecord.xwcs" value="${inquiryRecord.xwcs}" type="text" datatype="*1-127"  errormsg='询问次数必须是1到127位字符！' nullmsg='询问次数不能为空！' sucmsg='询问次数填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
						<th width="15%">询问地点</th>
						<td width="35%"><input name="inquiryRecord.inquiryAddress" value="${inquiryRecord.inquiryAddress}" type="text" datatype="*1-127"  errormsg='询问地点必须是1到127位字符！' nullmsg='询问地点不能为空！' sucmsg='询问地点填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">被询问人姓名</th>
						<td width="35%">
							<cus:SelectOneTag property="inquiryRecord.askedPerson" defaultText='请选择' codeSql="select t.ASK_PERSON id,t.ASK_PERSON name from INQUIRY_NOTICE t where t.delflag = 0 and t.RELATED_ID in (select ins.row_id from INSTRUMENTS_INFO ins where ins.delflag = 0 and ins.CASE_ID = '${instrumentsInfo.caseId}' and ins.IF_PRINT = 1)" value="${inquiryRecord.askedPerson}" datatype="*1-127" errormsg='被询问人姓名必须是1到127位字符！' nullmsg='被询问人姓名不能为空！' sucmsg='被询问人姓名填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"/><font style='color:red'>*</font>
						</td>
						<th width="15%">性别</th>
						<td width="35%"><cus:SelectOneTag property="inquiryRecord.sex" defaultText='请选择' codeName="性别" value="${inquiryRecord.sex}" datatype="*1-127" errormsg='性别必须是1到127位字符！' nullmsg='性别不能为空！' sucmsg='性别填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">年龄</th>
						<td width="35%"><input name="inquiryRecord.peopleAge" value="${inquiryRecord.peopleAge}" type="text" datatype="n1-3" errormsg='年龄必须是数字！' nullmsg='年龄不能为空！' sucmsg='年龄填写正确！'  maxlength="3" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
						<th width="15%">身份证号</th>
						<td width="35%"><input name="inquiryRecord.cardId" value="${inquiryRecord.cardId}" type="text" datatype="idcard" errormsg='身份证号格式错误！' nullmsg='身份证号不能为空！' sucmsg='身份证号填写正确！'  maxlength="18" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">工作单位</th>
						<td width="35%"><input name="inquiryRecord.companyName" value="${inquiryRecord.companyName}" type="text" datatype="*1-127" errormsg='工作单位必须是1到127位字符！' nullmsg='工作单位不能为空！' sucmsg='工作单位填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
						<th width="15%">职位</th>
						<td width="35%"><input name="inquiryRecord.position" value="${inquiryRecord.position}" type="text" datatype="*1-127" errormsg='职位必须是1到127位字符！' nullmsg='职位不能为空！' sucmsg='职位填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">住址</th>
						<td width="35%"><input name="inquiryRecord.address" value="${inquiryRecord.address}" type="text" datatype="*1-127" errormsg='住址必须是1到127位字符！' nullmsg='住址不能为空！' sucmsg='住址填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
						<th width="15%">电话</th>
						<td width="35%"><input name="inquiryRecord.tele" value="${inquiryRecord.tele}" type="text" datatype="*1-127" errormsg='电话必须是1到127位字符！' nullmsg='电话不能为空！' sucmsg='电话填写正确！'  maxlength="127" style="width:60%" class="clsdiv2"><font style='color:red'>*</font></td>
					</tr>
					<tr>	
						<th width="15%">在场人</th>
						<td width="85%" colspan="3"><input name="inquiryRecord.presentPeople" value="${inquiryRecord.presentPeople}" type="text"  maxlength="127" style="width:80%"></td>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
								<table id="blMore">
									<tr>
										<th style="text-align:center" width="90%">询问笔录<font style='color:red'>*</font></th>
										<td style="text-align:center" width="10%"><a href="###" class="btn_01_mini1" onclick="addNewBl()">添加</a></td>
									</tr>	
									<tr>
										<td>
											问：我们是苏州工业园区安全生产监督管理局的执法人员${inquiryRecord.recordPersonName}<span id="zfry1"></span>，证件号码为${inquiryRecord.recordPersonZh}<span id="zfzh1"></span>，这是我们的证件（出示证件）。我们依法就${instrumentsInfo.caseName}的有关问题向您了解情况，您有如实回答问题的义务，也有陈述、申辩和申请回避的权利。您听清楚了吗？
										</td>
									</tr>
									<tr>
										<td>
											答：听清楚了。我不申请回避。
										</td>
									</tr>
									<c:forEach var="inqRecRecord" items="${recordList}"  varStatus="status">
									<tr id="aa${inqRecRecord.id}">
										<input name="inqRecRecord.id" value="${inqRecRecord.id}" type="hidden" >
										<td>
											问：<textarea name="inqRecRecord.askRecord" style="width:88%;height:60px" datatype="*1-2000" errormsg='问题必须是1到2000位字符！' nullmsg='问题不能为空！' sucmsg='问题填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv2" onblur="setVal(this)">${inqRecRecord.askRecord}</textarea><font style='color:red'>*</font>
										</td>
										<td>
											<a href="###" class="btn_01_mini1" onclick="delBl('${inqRecRecord.id}','0')">删除</a>
										</td>
									</tr>
									<tr id="bb${inqRecRecord.id}">
										<td>
											答：<textarea name="inqRecRecord.recRecord" style="width:88%;height:60px" datatype="*1-2000" errormsg='回答必须是1到2000位字符！' nullmsg='回答不能为空！' sucmsg='回答填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv2" onblur="setVal(this)">${inqRecRecord.recRecord}</textarea><font style='color:red'>*</font>
										</td>
									</tr>
									</c:forEach>
								</table>
						</td>
					</tr>
				</s:if>
				
				<!-- 勘验笔录  div3 -->
				<s:if test="instrumentsInfo.instrumentType==3">
					<tr>
						<th width="15%">勘验开始时间</th>
						<td width="35%"><input id="starttime2" name="inquestRecord.startTime" value="<fmt:formatDate type='both' value='${inquestRecord.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv3" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime2\')}'})" datatype="*1-127" errormsg='勘验开始时间必须是1到127位字符！' nullmsg='勘验开始时间不能为空！' sucmsg='勘验开始时间填写正确！'  style="width:60%" maxlength="127"><font style='color:red'>*</font></td>
						<th width="15%">勘验结束时间</th>
						<td width="35%"><input id="endtime2" name="inquestRecord.endTime" value="<fmt:formatDate type='both' value='${inquestRecord.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv3" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime2\')}'})" datatype="*1-127" errormsg='勘验结束时间必须是1到127位字符！' nullmsg='勘验结束时间不能为空！' sucmsg='勘验结束时间填写正确！'  maxlength="127"  style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>	
						<th width="15%">勘验人</th>
						<td width="35%">
							<s:if test="type != 1">
								<cus:SelectOneTag property="inquestRecord.inquestPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}')" value="${inquestRecord.inquestPerson}" datatype="*1-127" errormsg='勘验人必须是1到127位字符！' nullmsg='勘验人不能为空！' sucmsg='勘验人填写正确！'  maxlength="127" style="width:60%" onchange="lockAndShowUser(this.value,'2');" class="clsdiv3"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
								<cus:SelectOneTag property="inquestRecord.inquestPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${inquestRecord.inquestPerson}" datatype="*1-127" errormsg='勘验人必须是1到127位字符！' nullmsg='勘验人不能为空！' sucmsg='勘验人填写正确！'  maxlength="127" style="width:60%" class="clsdiv3"/><font style='color:red'>*</font>
							</s:else>
						</td>
						<th width="15%">记录人</th>
						<td width="35%">
							<input type="hidden"  name="inquestRecord.recordPerson" value="${inquestRecord.recordPerson}">
							<input type="hidden" name="inquestRecord.recordPersonName" value="${inquestRecord.recordPersonName}">
							${inquestRecord.recordPersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">勘验场所</th>
						<td width="85%" colspan="3"><input name="inquestRecord.inquestAddress" value="${inquestRecord.inquestAddress}" type="text" datatype="*1-127" errormsg='勘验场所必须是1到127位字符！' nullmsg='勘验场所不能为空！' sucmsg='勘验场所填写正确！'  maxlength="127" style="width:80%" class="clsdiv3"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">天气情况</th>
						<td width="35%"><input name="inquestRecord.weatherCondition" value="${inquestRecord.weatherCondition}" type="text" datatype="*1-127" errormsg='天气情况必须是1到127位字符！' nullmsg='天气情况不能为空！' sucmsg='天气情况填写正确！'  maxlength="127" style="width:60%" class="clsdiv3"><font style='color:red'>*</font></td>
						<th width="15%">当事人1</th>
						<td width="35%"><input name="inquestRecord.party1" value="${inquestRecord.party1}" type="text" datatype="*1-127" errormsg='当事人1必须是1到127位字符！' nullmsg='当事人1不能为空！' sucmsg='当事人1填写正确！'  maxlength="127" style="width:60%" class="clsdiv3"><font style='color:red'>*</font></td>
					</tr>
						<th width="15%">当事人1单位职务</th>
						<td width="35%"><input name="inquestRecord.party1Company" value="${inquestRecord.party1Company}" type="text" datatype="*1-127" errormsg='当事人1单位职务必须是1到127位字符！' nullmsg='当事人1单位职务不能为空！' sucmsg='当事人1单位职务填写正确！'  maxlength="127" style="width:60%" class="clsdiv3"><font style='color:red'>*</font></td>
						<th width="15%">当事人1联系方式</th>
						<td width="35%"><input name="inquestRecord.party1Tel" value="${inquestRecord.party1Tel}" type="text" datatype="*1-127" errormsg='当事人1联系方式必须是1到127位字符！' nullmsg='当事人1联系方式不能为空！' sucmsg='当事人1联系方式填写正确！'  maxlength="127" style="width:60%" class="clsdiv3"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">当事人2</th>
						<td width="35%"><input name="inquestRecord.party2" value="${inquestRecord.party2}" type="text"  maxlength="127" style="width:60%"></td>
						<th width="15%">当事人2单位职务</th>
						<td width="35%"><input name="inquestRecord.party2Company" value="${inquestRecord.party2Company}" type="text"  maxlength="127"  style="width:60%"></td>
					</tr>
					<tr>
						<th width="15%">当事人2联系方式</th>
						<td width="35%"><input name="inquestRecord.party2Tel" value="${inquestRecord.party2Tel}" type="text"  maxlength="127" style="width:60%"></td>
						<th width="15%">被邀请人</th>
						<td width="35%"><input name="inquestRecord.invitee" value="${inquestRecord.invitee}" type="text" maxlength="127" style="width:60%"></td>
					</tr>
					<tr>
						<th width="15%">被邀请人单位职务</th>
						<td width="35%"><input name="inquestRecord.inviteeCompany" value="${inquestRecord.inviteeCompany}" type="text"  maxlength="127" style="width:60%"></td>
						<th width="15%">被邀请人联系方式</th>
						<td width="35%"><input name="inquestRecord.inviteeTel" value="${inquestRecord.inviteeTel}" type="text" maxlength="127" style="width:60%"></td>
					</tr>
					<tr>
						<th width="15%">勘验情况</th>
						<td width="85%" colspan="3">
							<textarea name="inquestRecord.inquestCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='勘验情况必须是1到2000位字符！' nullmsg='勘验情况不能为空！' sucmsg='勘验情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv3">${inquestRecord.inquestCondition}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 抽样取证凭证  div4 -->
				<s:if test="instrumentsInfo.instrumentType==4">
					<tr>
						<th width="15%">现场负责人</th>
						<td width="35%"><input name="samplingEvidence.chargePerson" value="${samplingEvidence.chargePerson}" type="text" datatype="*1-127" errormsg='现场负责人必须是1到127位字符！' nullmsg='现场负责人不能为空！' sucmsg='现场负责人填写正确！'  maxlength="127" style="width:60%" class="clsdiv4"><font style='color:red'>*</font></td>
						<th width="15%">抽样地点</th>
						<td width="35%"><input name="samplingEvidence.forensicAddress" value="${samplingEvidence.forensicAddress}" type="text" datatype="*1-127" errormsg='抽样地点必须是1到127位字符！' nullmsg='抽样地点不能为空！' sucmsg='抽样地点填写正确！'  maxlength="127" style="width:60%" class="clsdiv4"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">抽样取证开始时间</th>
						<td width="35%">
							<input id="starttime3" name="samplingEvidence.startTime"  value="<fmt:formatDate type='both' value='${samplingEvidence.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv4" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime3\')}'})" datatype="*1-127" errormsg='抽样取证开始时间必须是1到127位字符！' nullmsg='抽样取证开始时间不能为空！' sucmsg='抽样取证开始时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font>
						</td>
						<th width="15%">抽样取证结束时间</th>
						<td width="35%">
							<input id="endtime3" name="samplingEvidence.endTime"  value="<fmt:formatDate type='both' value='${samplingEvidence.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv4" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime3\')}'})" datatype="*1-127" errormsg='抽样取证结束时间必须是1到127位字符！' nullmsg='抽样取证结束时间不能为空！' sucmsg='抽样取证结束时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">执法人员1</th>
						<td width="35%">
							<input type="hidden" name="samplingEvidence.lawOfficerName1" value="${samplingEvidence.lawOfficerName1}">
							${samplingEvidence.lawOfficerName1}
						</td>
						<th width="15%">执法人员2</th>
						<td width="35%">
							<s:if test="type != 1">
								<cus:SelectOneTag property="samplingEvidence.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}')" value="${samplingEvidence.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv4" onchange="lockUser(this.value);"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
								<cus:SelectOneTag property="samplingEvidence.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${samplingEvidence.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv4"/><font style='color:red'>*</font>
							</s:else>
						</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align:center">抽样取证物品清单</th>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
								<table id="more">
									<tr>
										<td  style="text-align:center">证据物品名称<font style='color:red'>*</font></td>
										<td  style="text-align:center">规格及批号</td>
										<td  style="text-align:center">数量</td>
										<td  style="text-align:center"><a href="###" class="btn_01_mini1" onclick="addNewWp()">添加</a></td>
									</tr>	
									<c:forEach var="samplingAssociate" items="${cyqyglbList}"  varStatus="status">
									<tr style="text-align: center" id="${samplingAssociate.id}">
										<input name="samplingAssociate.id" value="${samplingAssociate.id}" type="hidden" >
										<td style='text-align:center'>
											<input name="samplingAssociate.evidenceName" value="${samplingAssociate.evidenceName}" type="text" onblur="setVal(this)" datatype="*1-127" errormsg='证据物品名称必须是1到127位字符！' nullmsg='证据物品名称不能为空！' sucmsg='证据物品名称填写正确！'  maxlength="127" class="clsdiv4">
										</td>
										<td style='text-align:center'>
											<input name="samplingAssociate.specificationLot" value="${samplingAssociate.specificationLot}" type="text" onblur="setVal(this)" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="samplingAssociate.samplingNum" value="${samplingAssociate.samplingNum}" type="text" onblur="setVal(this)"  maxlength="127">
										</td>
										<td style='text-align:center'>
											<a href="###" class="btn_01_mini1" onclick="delWp('${samplingAssociate.id}','0')">删除</a>
										</td>
									</tr>
									</c:forEach>
								</table>
						</td>
					</tr>
				</s:if>
				
				<!-- 先行登记保存证据审批表  div5 -->
				<s:if test="instrumentsInfo.instrumentType==5">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden" name="preserveEvidence.undertakerName1" value="${preserveEvidence.undertakerName1}">
							${preserveEvidence.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="preserveEvidence.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${preserveEvidence.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv5"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="preserveEvidence.undertakerComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='承办人意见必须是1到2000位字符！' nullmsg='承办人意见不能为空！' sucmsg='承办人意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv5">${preserveEvidence.undertakerComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">提请理由及依据</th>
						<td width="85%" colspan="3">
							<textarea name="preserveEvidence.reasonBasic" style="width:78%;height:120px" datatype="*1-2000" errormsg='提请理由及依据必须是1到2000位字符！' nullmsg='提请理由及依据不能为空！' sucmsg='提请理由及依据填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv5">${preserveEvidence.reasonBasic}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">保存方式</th>
						<td width="85%" colspan="3">
							<textarea name="preserveEvidence.preserveMethod" style="width:78%;height:120px" datatype="*1-2000" errormsg='保存方式必须是1到2000位字符！' nullmsg='保存方式不能为空！' sucmsg='保存方式填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv5">${preserveEvidence.preserveMethod}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align:center">证据清单</th>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
								<table id="more1">
									<tr>
										<td  style="text-align:center">证据名称<font style='color:red'>*</font></td>
										<td  style="text-align:center">规格型号</td>
										<td  style="text-align:center">产地</td>
										<td  style="text-align:center">成色（品级）</td>
										<td  style="text-align:center">单位</td>
										<td  style="text-align:center">价格</td>
										<td  style="text-align:center">数量</td>
										<td  style="text-align:center">备注</td>
										<td  style="text-align:center">
										<a href="###" class="btn_01_mini1" onclick="addNewZj()">添加</a>
										</td>
									</tr>	
									<c:forEach var="inventoryAssociate" items="${zzdjbczjglbList}"  varStatus="status">
									<tr style="text-align: center" id="${inventoryAssociate.id}">
										<input name="inventoryAssociate.id" value="${inventoryAssociate.id}" type="hidden" >
										<td style='text-align:center'>
											<input name="inventoryAssociate.evidenceName" value="${inventoryAssociate.evidenceName}" type="text" onblur="setVal(this)" style="width:60px" datatype="*1-127" errormsg='证据名称必须是1到127位字符！' nullmsg='证据名称不能为空！' sucmsg='证据名称填写正确！'  maxlength="127" class="clsdiv5">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.specificationModel" value="${inventoryAssociate.specificationModel}" type="text" onblur="setVal(this)" style="width:60px"  maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.originPlace" value="${inventoryAssociate.originPlace}" type="text" onblur="setVal(this)" style="width:60px" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.condition" value="${inventoryAssociate.condition}" type="text" onblur="setVal(this)" style="width:60px" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.company" value="${inventoryAssociate.company}" type="text" onblur="setVal(this)" style="width:60px" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.price" value="${inventoryAssociate.price}" type="text" onblur="setVal(this)" style="width:60px" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.inventoryNum" value="${inventoryAssociate.inventoryNum}" type="text" onblur="setVal(this)" style="width:60px" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="inventoryAssociate.remark" value="${inventoryAssociate.remark}" type="text" onblur="setVal(this)" style="width:60px" maxlength="127">
										</td>
										<td style='text-align:center'>
											<a href="###" class="btn_01_mini1" onclick="delZj('${inventoryAssociate.id}')">删除</a>
										</td>
									</tr>
									</c:forEach>
								</table>
						</td>
					</tr>
				</s:if>
				
				<!-- 先行登记保存证据通知书  div6 -->
				<s:if test="instrumentsInfo.instrumentType==6">
					<tr>
						<th width="15%">处理地点</th>
						<td width="35%"><input name="noticeEvidence.dealAddress" value="${noticeEvidence.dealAddress}" type="text" datatype="*1-127" errormsg='处理地点必须是1到127位字符！' nullmsg='处理地点不能为空！' sucmsg='处理地点填写正确！'  maxlength="127" style="width:60%" class="clsdiv6"><font style='color:red'>*</font></td>
						<th width="15%">处理时间</th>
						<td width="35%"><input name="noticeEvidence.dealTime" value="<fmt:formatDate type='both' value='${noticeEvidence.dealTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv6" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*1-127" errormsg='处理时间必须是1到127位字符！' nullmsg='处理时间不能为空！' sucmsg='处理时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden" name="noticeEvidence.undertakerName1" value="${noticeEvidence.undertakerName1}">
							${noticeEvidence.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="noticeEvidence.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${noticeEvidence.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv6"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">违法行为</th>
						<td width="85%" colspan="3">
							<textarea name="noticeEvidence.violation" style="width:78%;height:120px" datatype="*1-2000" errormsg='违法行为必须是1到2000位字符！' nullmsg='违法行为不能为空！' sucmsg='违法行为填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv6">${noticeEvidence.violation}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 先行登记保存证据处理审批表  div7 -->
				<s:if test="instrumentsInfo.instrumentType==7">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden" name="inventoryCheck.undertakerName1" value="${inventoryCheck.undertakerName1}">
							${inventoryCheck.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="inventoryCheck.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${inventoryCheck.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv7"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="inventoryCheck.undertakerComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='承办人意见必须是1到2000位字符！' nullmsg='承办人意见不能为空！' sucmsg='承办人意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv7">${inventoryCheck.undertakerComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">提请理由及依据</th>
						<td width="85%" colspan="3">
							<textarea name="inventoryCheck.reasonBasic" style="width:78%;height:120px" datatype="*1-2000" errormsg='提请理由及依据必须是1到2000位字符！' nullmsg='提请理由及依据不能为空！' sucmsg='提请理由及依据填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv7">${inventoryCheck.reasonBasic}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 先行登记保存证据处理决定书  div8 -->
				<s:if test="instrumentsInfo.instrumentType==8">
					<tr>
						<th width="15%">处理</th>
						<td width="85%" colspan="3">
							<textarea name="inventoryDecision.deal" style="width:78%;height:120px" datatype="*1-2000" errormsg='处理必须是1到2000位字符！' nullmsg='处理不能为空！' sucmsg='处理填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv8">${inventoryDecision.deal}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 现场检查记录  div9 -->
				<s:if test="instrumentsInfo.instrumentType==9">
					<tr>
						<th width="15%">检查开始时间</th>
						<td width="35%">
							<input id="starttime4" name="siteCheckRecord.startTime" value="<fmt:formatDate type='both' value='${siteCheckRecord.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv9" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime4\')}'})" datatype="*1-127" errormsg='检查开始时间必须是1到127位字符！' nullmsg='检查开始时间不能为空！' sucmsg='检查开始时间填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font>
						</td>
						<th width="15%">检查结束时间</th>
						<td width="35%">
							<input id="endtime4" name="siteCheckRecord.endTime" value="<fmt:formatDate type='both' value='${siteCheckRecord.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv9" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime4\')}'})" datatype="*1-127" errormsg='检查结束时间必须是1到127位字符！' nullmsg='检查结束时间不能为空！' sucmsg='检查结束时间填写正确！'  maxlength="127" style="width:60%"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">检查人员1</th>
						<td width="35%">
							<input type="hidden" name="siteCheckRecord.checkPersonName1" value="${siteCheckRecord.checkPersonName1}">
							${siteCheckRecord.checkPersonName1}
						</td>
						<th width="15%">检查人员2</th>
						<td width="35%">
							<s:if test="type != 1">
								<cus:SelectOneTag property="siteCheckRecord.checkPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}')" value="${siteCheckRecord.checkPerson}" datatype="*1-127" errormsg='检查人员必须是1到127位字符！' nullmsg='检查人员不能为空！' sucmsg='检查人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv9" onchange="lockUser(this.value);"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
								<cus:SelectOneTag property="siteCheckRecord.checkPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${siteCheckRecord.checkPerson}" datatype="*1-127" errormsg='检查人员必须是1到127位字符！' nullmsg='检查人员不能为空！' sucmsg='检查人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv9"/><font style='color:red'>*</font>
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="15%">检查场所</th>
						<td width="85%" colspan="3"><input name="siteCheckRecord.checkAddress" value="${siteCheckRecord.checkAddress}" type="text" datatype="*1-127" errormsg='检查场所必须是1到127位字符！' nullmsg='检查场所不能为空！' sucmsg='检查场所填写正确！'  maxlength="127" style="width:80%" class="clsdiv9"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">检查情况</th>
						<td width="85%" colspan="3">
							<textarea name="siteCheckRecord.checkCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='检查情况必须是1到2000位字符！' nullmsg='检查情况不能为空！' sucmsg='检查情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv9">${siteCheckRecord.checkCondition}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 现场处理措施决定书  div10 -->
				<s:if test="instrumentsInfo.instrumentType==10">
					<tr>
						<th width="15%">执法人员1</th>
						<td width="35%">
							<input type="hidden" name="liveActionDecision.lawOfficerName1" value="${liveActionDecision.lawOfficerName1}">
							${liveActionDecision.lawOfficerName1}
						</td>
						<th width="15%">执法人员2</th>
						<td width="35%">
							<cus:SelectOneTag property="liveActionDecision.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' " value="${liveActionDecision.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv10"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">违法违规行为</th>
						<td width="85%" colspan="3">
							<textarea name="liveActionDecision.illegalAccident" style="width:78%;height:120px" datatype="*1-2000" errormsg='违法违规行为必须是1到2000位字符！' nullmsg='违法违规行为不能为空！' sucmsg='违法违规行为填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv10">${liveActionDecision.illegalAccident}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">处理决定</th>
						<td width="85%" colspan="3">
							<textarea name="liveActionDecision.dealDecision" style="width:78%;height:120px" datatype="*1-2000" errormsg='处理决定必须是1到2000位字符！' nullmsg='处理决定不能为空！' sucmsg='处理决定填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv10">${liveActionDecision.dealDecision}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">执法依据</th>
						<td width="85%" colspan="3">
							<input id="lawName1" name="liveActionDecision.lawName" value="${liveActionDecision.lawName}" type="text" datatype="*1-2000" errormsg='执法依据必须是1到2000位字符！' nullmsg='执法依据不能为空！' sucmsg='执法依据填写正确！'   maxlength="2000" style="width:80%" class="clsdiv10" onclick="queryYj('1');"><font style='color:red'>*</font>
							<input type="hidden" id="lawBasic1" name="liveActionDecision.lawBasic" value="${liveActionDecision.lawBasic}"/>
						</td>
					</tr>
				</s:if>
				
				<!-- 责令限期整改指令书  div11 -->
				<s:if test="instrumentsInfo.instrumentType==11">
					<tr>
						<th width="15%">执法人员1</th>
						<td width="35%">
							<input type="hidden" name="orderDeadlineBook.lawOfficerName1" value="${orderDeadlineBook.lawOfficerName1}">
							${orderDeadlineBook.lawOfficerName1}
						</td>
						<th width="15%">执法人员2</th>
						<td width="35%">
							<cus:SelectOneTag property="orderDeadlineBook.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' " value="${orderDeadlineBook.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv11"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">问题</th>
						<td width="85%" colspan="3">
							<textarea name="orderDeadlineBook.problem" style="width:78%;height:120px" datatype="*1-2000" errormsg='问题必须是1到2000位字符！' nullmsg='问题不能为空！' sucmsg='问题填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv11">${orderDeadlineBook.problem}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">整改项</th>
						<td width="35%"><input name="orderDeadlineBook.changeItem" value="${orderDeadlineBook.changeItem}" type="text" datatype="*1-127" errormsg='整改项必须是1到127位字符！' nullmsg='整改项不能为空！' sucmsg='整改项填写正确！'  maxlength="127" style="width:60%" class="clsdiv11"><font style='color:red'>*</font></td>
						<th width="15%">整改期限</th>
						<td width="35%"><input name="orderDeadlineBook.startTime" value="<fmt:formatDate type='date' value='${orderDeadlineBook.startTime}' />" type="text" class="Wdate clsdiv11" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='整改期限必须是1到127位字符！' nullmsg='整改期限不能为空！' sucmsg='整改期限填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
				</s:if>
				
				<!-- 整改复查意见书  div12 -->
				<s:if test="instrumentsInfo.instrumentType==12">
					<tr>
						<th width="15%">执法人员1</th>
						<td width="35%">
							<input type="hidden" name="reviewSubmission.lawOfficerName1" value="${reviewSubmission.lawOfficerName1}">
							${reviewSubmission.lawOfficerName1}
						</td>
						<th width="15%">执法人员2</th>
						<td width="35%">
							<cus:SelectOneTag property="reviewSubmission.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' " value="${reviewSubmission.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv12"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">复查意见</th>
						<td width="85%" colspan="3">
							<textarea name="reviewSubmission.reviewComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='复查意见必须是1到2000位字符！' nullmsg='复查意见不能为空！' sucmsg='复查意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv12">${reviewSubmission.reviewComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 强制措施决定书  div13 -->
				<s:if test="instrumentsInfo.instrumentType==13">
					<tr>
						<th width="15%">问题</th>
						<td width="85%" colspan="3">
							<textarea name="enforenceDecision.problem" style="width:78%;height:120px" datatype="*1-2000" errormsg='问题必须是1到2000位字符！' nullmsg='问题不能为空！' sucmsg='问题填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv13">${enforenceDecision.problem}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">执法依据</th>
						<td width="85%" colspan="3">
							<input id="lawName2" name="enforenceDecision.lawName" value="${enforenceDecision.lawName}" type="text" datatype="*1-2000" errormsg='执法依据必须是1到2000位字符！' nullmsg='执法依据不能为空！' sucmsg='执法依据填写正确！'  maxlength="2000" style="width:80%" class="clsdiv13" onclick="queryYj('2');"> <font style='color:red'>*</font>
							<input type="hidden" id="lawBasic2" name="enforenceDecision.lawBasic" value="${enforenceDecision.lawBasic}"/>
						</td>
					</tr>
					<tr>
						<th width="15%">措施</th>
						<td width="85%" colspan="3">
							<textarea name="enforenceDecision.method" style="width:78%;height:120px" datatype="*1-2000"  errormsg='措施必须是1到2000位字符！' nullmsg='措施不能为空！' sucmsg='措施填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv13">${enforenceDecision.method}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 鉴定委托书  div14 -->
				<s:if test="instrumentsInfo.instrumentType==14">
					<tr>
						<th width="15%">鉴定机构名称</th>
						<td width="35%"><input name="identifyAttorney.jdjgName" value="${identifyAttorney.jdjgName}" type="text" maxlength="127" datatype="*1-127" errormsg='鉴定机构名称必须是1到127位字符！' nullmsg='鉴定机构名称不能为空！' sucmsg='鉴定机构名称填写正确！'  maxlength="127" style="width:60%" class="clsdiv14"><font style='color:red'>*</font></td>
						<th width="15%">提交时间</th>
						<td width="35%"><input name="identifyAttorney.submitTime" value="<fmt:formatDate type='date' value='${identifyAttorney.submitTime}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate clsdiv14" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='提交时间必须是1到127位字符！' nullmsg='提交时间不能为空！' sucmsg='提交时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">鉴定要求</th>
						<td width="85%" colspan="3">
							<textarea name="identifyAttorney.inentifyRequire" style="width:78%;height:120px" datatype="*1-2000" errormsg='鉴定要求必须是1到2000位字符！' nullmsg='鉴定要求不能为空！' sucmsg='鉴定要求填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv14">${identifyAttorney.inentifyRequire}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th colspan="4" style="text-align:center">鉴定物品清单</th>
					</tr>
					<tr>
						<td colspan="4" style="padding:0 0 0 0;">
								<table id="more2">
									<tr>
										<td  style="text-align:center">物品名称<font style='color:red'>*</font></td>
										<td  style="text-align:center">规格型号</td>
										<td  style="text-align:center">数量</td>
										<td  style="text-align:center">备注</td>
										<td  style="text-align:center">
										<a href="###" class="btn_01_mini1" onclick="addNewJdwp()">添加</a>
										</td>
									</tr>	
									<c:forEach var="identifyItemAssociate" items="${jdwpList}"  varStatus="status">
									<tr style="text-align: center" id="${identifyItemAssociate.id}">
										<input name="identifyItemAssociate.id" value="${identifyItemAssociate.id}" type="hidden" >
										<td style='text-align:center'>
											<input name="identifyItemAssociate.itemName" value="${identifyItemAssociate.itemName}" type="text" onblur="setVal(this)" datatype="*1-127" errormsg='物品名称必须是1到127位字符！' nullmsg='物品名称不能为空！' sucmsg='物品名称填写正确！'  maxlength="127" class="clsdiv14">
										</td>
										<td style='text-align:center'>
											<input name="identifyItemAssociate.specificationModel" value="${identifyItemAssociate.specificationModel}" type="text" onblur="setVal(this)" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="identifyItemAssociate.identifyNum" value="${identifyItemAssociate.identifyNum}" type="text" onblur="setVal(this)" maxlength="127">
										</td>
										<td style='text-align:center'>
											<input name="identifyItemAssociate.remark" value="${identifyItemAssociate.remark}" type="text" onblur="setVal(this)" maxlength="127">
										</td>
										<td style='text-align:center'>
											<a href="###" class="btn_01_mini1" onclick="delJdwp('${identifyItemAssociate.id}')">删除</a>
										</td>
									</tr>
									</c:forEach>
								</table>
						</td>
					</tr>
				</s:if>
				
				<!-- 行政处罚告知书  div15 -->
				<s:if test="instrumentsInfo.instrumentType==15">
					<tr>
						<td colspan="4">
							<textarea name="penaltyNotice.caseCondition" style="width:95%;height:100px" datatype="*1-2000" errormsg='案件基本情况必须是1到2000位字符！' nullmsg='案件基本情况不能为空！' sucmsg='案件基本情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv15">${penaltyNotice.caseCondition}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							经查，你（单位）<textarea name="penaltyNotice.wfxw" style="width:85%;height:100px" datatype="*1-2000" errormsg='违法行为必须是1到2000位字符！' nullmsg='违法行为不能为空！' sucmsg='违法行为填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv15">${penaltyNotice.wfxw}</textarea><font style='color:red'>*</font>。
						</td>
					</tr>
					<tr>
						<td colspan="4">
							以上事实违反了
							<input id="proName1" name="penaltyNotice.proName" value="${penaltyNotice.proName}" type="text" datatype="*1-2000" errormsg='违反规定必须是1到2000位字符！' nullmsg='违反规定不能为空！' sucmsg='违反规定填写正确！'  maxlength="2000" style="width:73%" class="clsdiv15" onclick="queryGd('1');"><font style='color:red'>*</font>
							的规定
							<input type="hidden" id="proId1" name="penaltyNotice.provision" value="${penaltyNotice.provision}"/>，
						</td>
					</tr>
					<tr>
						<td colspan="4">
							依据
							<input id="lawName3" name="penaltyNotice.lawName" value="${penaltyNotice.lawName}" type="text" datatype="*1-2000" errormsg='执法依据必须是1到2000位字符！' nullmsg='执法依据不能为空！' sucmsg='执法依据填写正确！'  maxlength="2000" style="width:80%" class="clsdiv15" onclick="queryYj('3');"><font style='color:red'>*</font>
							的规定
							<input type="hidden" id="lawBasic3" name="penaltyNotice.lawBasic" value="${penaltyNotice.lawBasic}"/>，
						</td>
					</tr>
					<tr>
						<td colspan="4">
							拟给予你（单位）
							<textarea name="penaltyNotice.adminPenality" style="width:78%;height:120px" datatype="*1-2000" errormsg='行政处罚必须是1到2000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv15">${penaltyNotice.adminPenality}</textarea><font style='color:red'>*</font>
							的行政处罚。
						</td>
					</tr>
					<s:if test="flag == 'add'">
					<tr>
						<th width="15%" style="color:red">是否生成听证告知书</th>
						<td width="35%">
							<cus:SelectOneTag style="width:78%;" property="penaltyNotice.iftzgz" defaultText='请选择' codeName="是或否" value="${penaltyNotice.iftzgz}" datatype="*1-127"  nullmsg='是否生成听证告知书不能为空！' sucmsg='是否生成听证告知书填写正确！' class="clsdiv15"/><font style='color:red'>*</font>
						</td>
					</tr>
					</s:if>
				</s:if>
				
				<!-- 当事人陈述申辩笔录  div16 -->
				<s:if test="instrumentsInfo.instrumentType==16">
					<tr>
						<th width="15%">陈述开始时间</th>
						<td width="35%"><input id="starttime5" name="partyStateNote.startTime"  value="<fmt:formatDate type='both' value='${partyStateNote.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime5\')}'})" datatype="*1-127" errormsg='陈述开始时间必须是1到127位字符！' nullmsg='陈述开始时间不能为空！' sucmsg='陈述开始时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
						<th width="15%">陈述结束时间</th>
						<td width="35%"><input id="endtime5" name="partyStateNote.endTime" value="<fmt:formatDate type='both' value='${partyStateNote.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv16" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime5\')}'})" datatype="*1-127" errormsg='陈述结束时间必须是1到127位字符！' nullmsg='陈述结束时间不能为空！' sucmsg='陈述结束时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">记录人</th>
						<td width="35%">
							<input type="hidden"  name="partyStateNote.recorder" value="${partyStateNote.recorder}">
							<input type="hidden" name="partyStateNote.recorderName" value="${partyStateNote.recorderName}">
							${partyStateNote.recorderName}
						</td>
						<th width="15%">承办人</th>
						<td width="35%">
							<s:if test="type != 1">
								<cus:SelectOneTag property="partyStateNote.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}')" value="${partyStateNote.undertaker}" datatype="*1-127" errormsg='承办人必须是1到127位字符！' nullmsg='承办人不能为空！' sucmsg='承办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv16" onchange="lockUser(this.value);"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
								<cus:SelectOneTag property="partyStateNote.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${partyStateNote.undertaker}" datatype="*1-127" errormsg='承办人必须是1到127位字符！' nullmsg='承办人不能为空！' sucmsg='承办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv16"/><font style='color:red'>*</font>
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="15%">陈述地点</th>
						<td width="85%" colspan="3"><input name="partyStateNote.stateAddress" value="${partyStateNote.stateAddress}" type="text" datatype="*1-127" errormsg='陈述地点必须是1到127位字符！' nullmsg='陈述地点不能为空！' sucmsg='陈述地点填写正确！'  maxlength="127" style="width:80%" class="clsdiv16"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">陈述申辩人</th>
						<td width="35%"><input name="partyStateNote.stateDefense" value="${partyStateNote.stateDefense}" type="text" datatype="*1-127" errormsg='陈述申辩人必须是1到127位字符！' nullmsg='陈述申辩人不能为空！' sucmsg='陈述申辩人填写正确！'  maxlength="127" style="width:60%" class="clsdiv16"><font style='color:red'>*</font></td>
						<th width="15%">性别</th>
						<td width="35%"><cus:SelectOneTag property="partyStateNote.sex" defaultText='请选择' codeName="性别" value="${partyStateNote.sex}" datatype="*1-127" errormsg='性别必须是1到127位字符！' nullmsg='性别不能为空！' sucmsg='性别填写正确！'  maxlength="127" style="width:60%" class="clsdiv16"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">职务</th>
						<td width="35%"><input name="partyStateNote.position" value="${partyStateNote.position}" type="text" datatype="*1-127" errormsg='职务必须是1到127位字符！' nullmsg='职务不能为空！' sucmsg='职务填写正确！'  maxlength="127" style="width:60%" class="clsdiv16"><font style='color:red'>*</font></td>
						<th width="15%">电话</th>
						<td width="35%"><input name="partyStateNote.tele" value="${partyStateNote.tele}" type="text" datatype="*1-127" errormsg='电话必须是1到127位字符！' nullmsg='电话不能为空！' sucmsg='电话填写正确！'  maxlength="127" style="width:60%" class="clsdiv16"><font style='color:red'>*</font></td>							
					</tr>
					<tr>
						<th width="15%">联系地址</th>
						<td width="35%"><input name="partyStateNote.address" value="${partyStateNote.address}" type="text" datatype="*1-127" errormsg='联系地址必须是1到127位字符！' nullmsg='联系地址不能为空！' sucmsg='联系地址填写正确！'  maxlength="127" style="width:60%" class="clsdiv16"><font style='color:red'>*</font></td>
						<th width="15%">邮编</th>
						<td width="35%"><input name="partyStateNote.zipCode" value="${partyStateNote.zipCode}" type="text" datatype="p" errormsg='邮编必须是6位字符！' nullmsg='邮编不能为空！' sucmsg='邮编填写正确！'  maxlength="6" style="width:60%" class="clsdiv16"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">陈述申辩记录</th>
						<td width="85%" colspan="3">
							<textarea name="partyStateNote.stateRecord" style="width:78%;height:120px" datatype="*1-2000" errormsg='陈述申辩记录必须是1到2000位字符！' nullmsg='陈述申辩记录不能为空！' sucmsg='陈述申辩记录填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv16">${partyStateNote.stateRecord}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 听证会通知书  div18 -->
				<s:if test="instrumentsInfo.instrumentType==18">
					<tr>
						<th width="15%">听证会时间</th>
						<td width="35%"><input name="hearingNotice.hearingTime" value="<fmt:formatDate type='both' value='${hearingNotice.hearingTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv18" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" datatype="*1-127" errormsg='听证会时间必须是1到127位字符！' nullmsg='听证会时间不能为空！' sucmsg='听证会时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
						<th width="15%">是否公开</th>
						<td width="35%"><cus:SelectOneTag property="hearingNotice.publicCondition" defaultText='请选择' codeName="是或否" value="${hearingNotice.publicCondition}" datatype="*1-127" errormsg='是否公开必须是1到127位字符！' nullmsg='是否公开不能为空！' sucmsg='是否公开填写正确！'  maxlength="127" style="width:60%" class="clsdiv18"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">听证地点</th>
						<td width="85%" colspan="3"><input name="hearingNotice.hearingAddress" value="${hearingNotice.hearingAddress}" type="text" datatype="*1-127" errormsg='听证地点必须是1到127位字符！' nullmsg='听证地点不能为空！' sucmsg='听证地点填写正确！'  maxlength="127"  style="width:80%" class="clsdiv18"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">听证主持人姓名</th>
						<td width="35%">
							<cus:SelectOneTag property="hearingNotice.hearingChairperson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${hearingNotice.hearingChairperson}" datatype="*1-127" errormsg='听证主持人姓名必须是1到127位字符！' nullmsg='听证主持人姓名不能为空！' sucmsg='听证主持人姓名填写正确！'  maxlength="127" style="width:60%" class="clsdiv18"/><font style='color:red'>*</font>
						</td>
						<th width="15%">书记员</th>
						<td width="35%">
							<input type="hidden"  name="hearingNotice.clerk" value="${hearingNotice.clerk}">
							<input type="hidden" name="hearingNotice.clerkName" value="${hearingNotice.clerkName}">
							${hearingNotice.clerkName}
						</td>
					</tr>
					<tr>
						<th width="15%">听证员</th>
						<td width="85%" colspan="3">
							<cus:hxcheckbox property="hearingNotice.hearingOfficer" codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${hearingNotice.hearingOfficer}" datatype="*1-127" errormsg='听证员必须是1到127位字符！' nullmsg='听证员不能为空！' sucmsg='听证员填写正确！'  maxlength="127" onclick="checkInfo(this,'hearingNotice.hearingOfficer');" class="clsdiv18"/><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 听证笔录  div19 -->
				<s:if test="instrumentsInfo.instrumentType==19">
					<tr>
						<th width="15%">听证开始时间</th>
						<td width="35%"><input id="starttime6" name="hearingNote.startTime"  value="<fmt:formatDate type='both' value='${hearingNote.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv19" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime6\')}'})" datatype="*1-127" errormsg='听证开始时间必须是1到127位字符！' nullmsg='听证开始时间不能为空！' sucmsg='听证开始时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
						<th width="15%">听证结束时间</th>
						<td width="35%"><input id="endtime6" name="hearingNote.endTime" value="<fmt:formatDate type='both' value='${hearingNote.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv19" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime6\')}'})" datatype="*1-127" errormsg='听证结束时间必须是1到127位字符！' nullmsg='听证结束时间不能为空！' sucmsg='听证结束时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">委托代理人1</th>
						<td width="35%"><input name="hearingNote.attorney1" value="${hearingNote.attorney1}" type="text" datatype="*1-127" errormsg='委托代理人1必须是1到127位字符！' nullmsg='委托代理人1不能为空！' sucmsg='委托代理人1填写正确！'  maxlength="127" style="width:60%" class="clsdiv19"><font style='color:red'>*</font></td>
						<th width="15%">委托代理人1性别</th>
						<td width="35%"><cus:SelectOneTag property="hearingNote.attorney1Sex" defaultText='请选择' codeName="性别" value="${hearingNote.attorney1Sex}" datatype="*1-127" errormsg='委托代理人1性别必须是1到127位字符！' nullmsg='委托代理人1性别不能为空！' sucmsg='委托代理人1性别填写正确！'  maxlength="127" style="width:60%" class="clsdiv19"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">委托代理人1工作单位</th>
						<td width="35%"><input name="hearingNote.attorney1Company" value="${hearingNote.attorney1Company}" type="text" datatype="*1-127" errormsg='委托代理人1工作单位必须是1到127位字符！' nullmsg='委托代理人1工作单位不能为空！' sucmsg='委托代理人1工作单位填写正确！'  maxlength="127" style="width:60%" class="clsdiv19"><font style='color:red'>*</font></td>
						<th width="15%">委托代理人1年龄</th>
						<td width="35%"><input name="hearingNote.attorney1Age" value="${hearingNote.attorney1Age}" type="text" datatype="*1-127" errormsg='委托代理人1年龄必须是1到127位字符！' nullmsg='委托代理人1年龄不能为空！' sucmsg='委托代理人1年龄填写正确！'  maxlength="127" style="width:60%" class="clsdiv19"><font style='color:red'>*</font></td>
						
					</tr>
					<tr>
						<th width="15%">委托代理人2</th>
						<td width="35%"><input name="hearingNote.attorney2" value="${hearingNote.attorney2}" type="text"   maxlength="127" style="width:60%" ></td>
						<th width="15%">委托代理人2性别</th>
						<td width="35%"><cus:SelectOneTag property="hearingNote.attorney2Sex" defaultText='请选择' codeName="性别" value="${hearingNote.attorney2Sex}"   maxlength="127" style="width:60%"/></td>
					</tr>
					<tr>
						<th width="15%">委托代理人2工作单位</th>
						<td width="35%"><input name="hearingNote.attorney2Company" value="${hearingNote.attorney2Company}" type="text"   maxlength="127" style="width:60%"></td>
						<th width="15%">委托代理人2年龄</th>
						<td width="35%"><input name="hearingNote.attorney2Age" value="${hearingNote.attorney2Age}" type="text"  maxlength="127" style="width:60%"></td>
						
					</tr>
					<tr>
						<th width="15%">调查人员</th>
						<td width="85%" colspan="3">
							<cus:hxcheckbox property="hearingNote.undertaker" codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%'" value="${hearingNote.undertaker}" datatype="*1-127" errormsg='调查人员必须是1到127位字符！' nullmsg='调查人员不能为空！' sucmsg='调查人员填写正确！'  maxlength="127" onclick="checkInfo(this,'hearingNote.undertaker');" class="clsdiv19"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">第三人</th>
						<td width="85%" colspan="3"><input name="hearingNote.thirdPerson" value="${hearingNote.thirdPerson}" type="text"  maxlength="2000" style="width:80%" ></td>
					</tr>
					<tr>
						<th width="15%">其他参与人员</th>
						<td width="85%" colspan="3"><input name="hearingNote.otherParticipants" value="${hearingNote.otherParticipants}" type="text"   maxlength="2000" style="width:80%"></td>
					</tr>
					<tr>	
						<th width="15%">听证记录</th>
						<td width="85%" colspan="3">
							<textarea name="hearingNote.hearingRecord" style="width:78%;height:120px" datatype="*1-2000" errormsg='听证记录必须是1到2000位字符！' nullmsg='听证记录不能为空！' sucmsg='听证记录填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv19">${hearingNote.hearingRecord}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 听证会报告书  div20 -->
				<s:if test="instrumentsInfo.instrumentType==20">
					<tr>
						<th width="15%">听证会基本情况摘要</th>
						<td width="85%" colspan="3">
							<textarea name="hearingReport.hearingSummary" style="width:78%;height:120px" datatype="*1-2000" errormsg='听证会基本情况摘要必须是1到2000位字符！' nullmsg='听证会基本情况摘要不能为空！' sucmsg='听证会基本情况摘要填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv20">${hearingReport.hearingSummary}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				
				<!-- 案件处理呈批表  div21 -->
				<s:if test="instrumentsInfo.instrumentType==21">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden"  name="caseProcessApproval.undertakerName1" value="${caseProcessApproval.undertakerName1}">
							${caseProcessApproval.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="caseProcessApproval.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${caseProcessApproval.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv21"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">违法事实及处罚依据</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.wfss" style="width:78%;height:120px" datatype="*1-5000" errormsg='违法事实及处罚依据必须是1到5000位字符！' nullmsg='违法事实及处罚依据不能为空！' sucmsg='违法事实及处罚依据填写正确！'  onKeyDown='if(this.value.length > 5000) this.value=this.value.substr(0,5000)' class="clsdiv7">${caseProcessApproval.wfss}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">当事人申辩意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.dsrsbyj" style="width:78%;height:120px" datatype="*1-2000" errormsg='当事人申辩意见必须是1到2000位字符！' nullmsg='当事人申辩意见不能为空！' sucmsg='当事人申辩意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv7">${caseProcessApproval.dsrsbyj}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseProcessApproval.undertakerComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='承办人意见必须是1到2000位字符！' nullmsg='承办人意见不能为空！' sucmsg='承办人意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv7">${caseProcessApproval.undertakerComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 行政处罚集体讨论记录  div22 -->
				<s:if test="instrumentsInfo.instrumentType==22">
					<tr>
						<th width="15%">讨论开始时间</th>
						<td width="35%"><input id="starttime7" name="penBraRec.startTime"  value="<fmt:formatDate type='both' value='${penBraRec.startTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv22" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endtime7\')}'})" datatype="*1-127" errormsg='讨论开始时间必须是1到127位字符！' nullmsg='讨论开始时间不能为空！' sucmsg='讨论开始时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
						<th width="15%">讨论结束时间</th>
						<td width="35%"><input id="endtime7" name="penBraRec.endTime" value="<fmt:formatDate type='both' value='${penBraRec.endTime}' pattern='yyyy-MM-dd HH:mm:ss'/>" type="text" class="Wdate clsdiv22" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'starttime7\')}'})" datatype="*1-127" errormsg='讨论结束时间必须是1到127位字符！' nullmsg='讨论结束时间不能为空！' sucmsg='讨论结束时间填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">讨论地点</th>
						<td width="35%"><input name="penBraRec.discussionAddress" value="${penBraRec.discussionAddress}" type="text" datatype="*1-127" errormsg='讨论地点必须是1到127位字符！' nullmsg='讨论地点不能为空！' sucmsg='讨论地点填写正确！'  maxlength="127" style="width:60%" class="clsdiv22"><font style='color:red'>*</font></td>
						<th width="15%">记录人</th>
						<td width="35%">
							<input type="hidden"  name="penBraRec.recordPerson" value="${penBraRec.recordPerson}">
							<input type="hidden" name="penBraRec.recordPersonName" value="${penBraRec.recordPersonName}">
							${penBraRec.recordPersonName}
						</td>
					</tr>
					<tr>
						<th width="15%">主持人</th>
						<td width="35%">
							<s:if test="type != 1">
								<cus:SelectOneTag property="penBraRec.chairperson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}') order by t.SORT_SQ" value="${penBraRec.chairperson}" datatype="*1-127" errormsg='主持人必须是1到127位字符！' nullmsg='主持人不能为空！' sucmsg='主持人填写正确！'  maxlength="127" style="width:60%" class="clsdiv22" onchange="lockUser(this.value);"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
								<cus:SelectOneTag property="penBraRec.chairperson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001%' and t.row_id != '${loginUserId}' order by t.SORT_SQ" value="${penBraRec.chairperson}" datatype="*1-127" errormsg='主持人必须是1到127位字符！' nullmsg='主持人不能为空！' sucmsg='主持人填写正确！'  maxlength="127" style="width:60%" class="clsdiv22"/><font style='color:red'>*</font>
							</s:else>
						</td>
						<th width="15%">汇报人</th>
						<td width="35%">
							<s:if test="type != 1">
								<cus:SelectOneTag property="penBraRec.reportPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001%' and t.row_id != '${loginUserId}' and t.row_id not in(select l.userId from lock_user l where l.lockTime = '${lockTime}') order by t.SORT_SQ" value="${penBraRec.reportPerson}" datatype="*1-127" errormsg='汇报人必须是1到127位字符！' nullmsg='汇报人不能为空！' sucmsg='汇报人填写正确！'  maxlength="127" style="width:60%" class="clsdiv22" onchange="lockUser(this.value);"/><font style='color:red'>*</font>
							</s:if>
							<s:else>
								<cus:SelectOneTag property="penBraRec.reportPerson" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001%' and t.row_id != '${loginUserId}' order by t.SORT_SQ" value="${penBraRec.reportPerson}" datatype="*1-127" errormsg='汇报人必须是1到127位字符！' nullmsg='汇报人不能为空！' sucmsg='汇报人填写正确！'  maxlength="127" style="width:60%" class="clsdiv22"/><font style='color:red'>*</font>
							</s:else>
						</td>
					</tr>
					<tr>
						<th width="15%">出席人员姓名及职务</th>
						<td width="85%" colspan="3">
							<textarea rows="5" style="width:78%;height:120px" readonly="readonly" name="penBraRec.attendName" id="attendName">${penBraRec.attendName}</textarea>
							<a href="###" class="btn_01_mini1" onclick="selectUser();">选择</a>
							<input type="hidden" id="attendId" name="penBraRec.attendId" value="${penBraRec.attendId}">
						</td>
					</tr>
					<tr>
						<th width="15%">讨论内容</th>
						<td width="85%" colspan="3">
							<textarea name="penBraRec.discussionContent" style="width:78%;height:120px" datatype="*1-2000" errormsg='讨论内容必须是1到2000位字符！' nullmsg='讨论内容不能为空！' sucmsg='讨论内容填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv22">${penBraRec.discussionContent}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">讨论记录</th>
						<td width="85%" colspan="3">
							<textarea name="penBraRec.discussionRecord" style="width:78%;height:120px" datatype="*1-2000" errormsg='讨论记录必须是1到2000位字符！' nullmsg='讨论记录不能为空！' sucmsg='讨论记录填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv22">${penBraRec.discussionRecord}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">结论性意见</th>
						<td width="85%" colspan="3">
							<textarea name="penBraRec.conclusionComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='结论性意见必须是1到2000位字符！' nullmsg='结论性意见不能为空！' sucmsg='结论性意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv22">${penBraRec.conclusionComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 行政（当场）处罚决定书（单位）  div23 -->
				<s:if test="instrumentsInfo.instrumentType==23">
					<tr>
						<th width="15%">执法人员1</th>
						<td width="35%">
							<input type="hidden" name="spoPenDecCom.lawOfficerName1" value="${spoPenDecCom.lawOfficerName1}">
							${spoPenDecCom.lawOfficerName1}
						</td>
						<th width="15%">执法人员2</th>
						<td width="35%">
							<cus:SelectOneTag property="spoPenDecCom.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}' " value="${spoPenDecCom.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv23"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxcheckbox property="spoPenDecCom.punishedSpecies"  codeName="处罚种类" value="${spoPenDecCom.punishedSpecies}" datatype="*1-127" errormsg='处罚种类必须是1到2000位字符！' nullmsg='处罚种类不能为空！' sucmsg='处罚种类填写正确！' class="clsdiv23"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">没收违法所得</th>
						<td width="35%"><input name="spoPenDecCom.illegalIncome" value="${spoPenDecCom.illegalIncome}" type="text" datatype="*1-127" errormsg='没收违法所得必须是数字！' nullmsg='没收违法所得不能为空！' sucmsg='没收违法所得填写正确！' maxlength="127"  style="width:60%" class="clsdiv23" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
						<th width="15%">罚款金额</th>
						<td width="35%"><input name="spoPenDecCom.fines" value="${spoPenDecCom.fines}" type="text" datatype="*1-127" errormsg='罚款金额必须是数字！' nullmsg='罚款金额不能为空！' sucmsg='罚款金额填写正确！' maxlength="127" style="width:60%" class="clsdiv23" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">罚款方式</th>
						<td width="35%">
							<s:select name="spoPenDecCom.fineMethod" list="#{'0':'当场缴纳','1':'缴纳至银行卡'}" theme="simple" datatype="*1-127" errormsg='罚款方式必须是1到127位字符！' nullmsg='罚款方式不能为空！' sucmsg='罚款方式填写正确！'  maxlength="127"  cssStyle="width:60%" class="clsdiv23"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="spoPenDecCom.adminPenalties" style="width:78%;height:120px" datatype="*1-2000" errormsg='行政处罚必须是1到2000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv23">${spoPenDecCom.adminPenalties}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 行政（当场）处罚决定书（个人）  div24 -->
				<s:if test="instrumentsInfo.instrumentType==24">
					<tr>
						<th width="15%">执法人员1</th>
						<td width="35%">
							<input type="hidden" name="spoPenDecPer.lawOfficerName1" value="${spoPenDecPer.lawOfficerName1}">
							${spoPenDecPer.lawOfficerName1}
						</td>
						<th width="15%">执法人员2</th>
						<td width="35%">	
							<cus:SelectOneTag property="spoPenDecPer.lawOfficer" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${spoPenDecPer.lawOfficer}" datatype="*1-127" errormsg='执法人员必须是1到127位字符！' nullmsg='执法人员不能为空！' sucmsg='执法人员填写正确！'  maxlength="127" style="width:60%" class="clsdiv24"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxcheckbox property="spoPenDecPer.punishedSpecies" codeName="处罚种类" value="${spoPenDecPer.punishedSpecies}" datatype="*1-127" errormsg='处罚种类必须是1到2000位字符！' nullmsg='处罚种类不能为空！' sucmsg='处罚种类填写正确！' class="clsdiv24"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">没收违法所得</th>
						<td width="35%"><input name="spoPenDecPer.illegalIncome" value="${spoPenDecPer.illegalIncome}" type="text" datatype="*1-127" errormsg='没收违法所得必须是数字！' nullmsg='没收违法所得不能为空！' sucmsg='没收违法所得填写正确！' maxlength="127" style="width:60%" class="clsdiv24" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
						<th width="15%">罚款金额</th>
						<td width="35%"><input name="spoPenDecPer.fines" value="${spoPenDecPer.fines}" type="text" datatype="*1-127" errormsg='罚款金额必须是数字！' nullmsg='罚款金额不能为空！' sucmsg='罚款金额填写正确！' maxlength="127" style="width:60%" class="clsdiv24" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">罚款方式</th>
						<td width="35%">
							<s:select name="spoPenDecPer.fineMethod" list="#{'0':'当场缴纳','1':'缴纳至银行卡'}" theme="simple" datatype="*1-127" errormsg='罚款方式必须是1到127位字符！' nullmsg='罚款方式不能为空！' sucmsg='罚款方式填写正确！'  maxlength="127" class="clsdiv24" cssStyle="width:60%"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="spoPenDecPer.adminPenalties" style="width:78%;height:120px" datatype="*1-2000" errormsg='行政处罚必须是1到2000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv24">${spoPenDecPer.adminPenalties}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 行政处罚决定书（单位）  div25 -->
				<s:if test="instrumentsInfo.instrumentType==25">
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxcheckbox  property="penDecCom.punishedSpecies" codeName="处罚种类" value="${penDecCom.punishedSpecies}" datatype="*1-127" errormsg='处罚种类必须是1到2000位字符！' nullmsg='处罚种类不能为空！' sucmsg='处罚种类填写正确！' class="clsdiv25"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">没收违法所得</th>
						<td width="35%"><input name="penDecCom.illegalIncome" value="${penDecCom.illegalIncome}" type="text" datatype="*1-127" errormsg='没收违法所得必须是数字！' nullmsg='没收违法所得不能为空！' sucmsg='没收违法所得填写正确！' maxlength="127" style="width:60%" class="clsdiv25" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
						<th width="15%">罚款金额</th>
						<td width="35%"><input name="penDecCom.fines" value="${penDecCom.fines}" type="text" datatype="*1-127" errormsg='罚款金额必须是数字！' nullmsg='罚款金额不能为空！' sucmsg='罚款金额填写正确！' maxlength="127" style="width:60%" class="clsdiv25" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">违法事实</th>
						<td width="85%" colspan="3">
							<textarea name="penDecCom.wfss" style="width:78%;height:120px" datatype="*1-5000" errormsg='违法事实必须是1到5000位字符！' nullmsg='违法事实不能为空！' sucmsg='违法事实填写正确！'  onKeyDown='if(this.value.length > 5000) this.value=this.value.substr(0,5000)' class="clsdiv25">${penDecCom.wfss}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="penDecCom.adminPenalties" style="width:78%;height:120px" datatype="*1-2000" errormsg='行政处罚必须是1到2000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv25">${penDecCom.adminPenalties}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 行政处罚决定书（个人）  div26 -->
				<s:if test="instrumentsInfo.instrumentType==26">
					<tr>
						<th width="15%">处罚种类</th>
						<td width="85%" colspan="3"><cus:hxcheckbox property="penDecPer.punishedSpecies" codeName="处罚种类" value="${penDecPer.punishedSpecies}" datatype="*1-127" errormsg='处罚种类必须是1到2000位字符！' nullmsg='处罚种类不能为空！' sucmsg='处罚种类填写正确！'  class="clsdiv26"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">没收违法所得</th>
						<td width="35%"><input name="penDecPer.illegalIncome" value="${penDecPer.illegalIncome}" type="text" datatype="*1-127" errormsg='没收违法所得必须是数字！' nullmsg='没收违法所得不能为空！' sucmsg='没收违法所得填写正确！' maxlength="127" style="width:60%" class="clsdiv26" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
						<th width="15%">罚款金额</th>
						<td width="35%"><input name="penDecPer.fines" value="${penDecPer.fines}" type="text" datatype="*1-127" errormsg='罚款金额必须是数字！' nullmsg='罚款金额不能为空！' sucmsg='罚款金额填写正确！' maxlength="127" style="width:60%" class="clsdiv26" onKeyUp="clearNoNum(event,this)"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">违法事实</th>
						<td width="85%" colspan="3">
							<textarea name="penDecPer.wfss" style="width:78%;height:120px" datatype="*1-5000" errormsg='行政处罚必须是1到5000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown='if(this.value.length > 5000) this.value=this.value.substr(0,5000)' class="clsdiv26">${penDecPer.wfss}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">行政处罚</th>
						<td width="85%" colspan="3">
							<textarea name="penDecPer.adminPenalties" style="width:78%;height:120px" datatype="*1-2000" errormsg='行政处罚必须是1到2000位字符！' nullmsg='行政处罚不能为空！' sucmsg='行政处罚填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv26">${penDecPer.adminPenalties}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 罚款催缴通知书  div27 -->
				<s:if test="instrumentsInfo.instrumentType==27">
				</s:if>
				
				<!-- 延期（分期）缴纳罚款审批表  div28 -->
				<s:if test="instrumentsInfo.instrumentType==28">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden"  name="posFinApp.undertakerName1" value="${posFinApp.undertakerName1}">
							${posFinApp.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="posFinApp.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${posFinApp.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv28"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="posFinApp.undertakerComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='承办人意见必须是1到2000位字符！' nullmsg='承办人意见不能为空！' sucmsg='承办人意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv28">${posFinApp.undertakerComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">理由</th>
						<td width="85%" colspan="3">
							<textarea name="posFinApp.reason" style="width:78%;height:120px" datatype="*1-2000" errormsg='理由必须是1到2000位字符！' nullmsg='理由不能为空！' sucmsg='理由填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv28">${posFinApp.reason}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 延期（分期）缴纳罚款批准书  div29 -->
				<s:if test="instrumentsInfo.instrumentType==29">
					<tr>
						<th width="15%">罚款(大写)</th>
						<td width="35%"><input name="posFinRat.fineUppercase" value="${posFinRat.fineUppercase}" type="text" datatype="*1-127" errormsg='罚款必须是1到127位字符！' nullmsg='罚款不能为空！' sucmsg='罚款填写正确！'  maxlength="127" style="width:60%" class="clsdiv29"><font style='color:red'>*</font></td>
						<th width="15%">延期方式</th>
						<td width="35%"><s:select name="posFinRat.postponeMethod" list="#{'0':'延期缴纳罚款','1':'分期缴纳罚款'}" theme="simple" onchange="changeFqfs(this.value)" datatype="*1-127" errormsg='延期方式必须是1到127位字符！' nullmsg='延期方式不能为空！' sucmsg='延期方式填写正确！'  maxlength="127" class="clsdiv29" cssStyle="width:60%"/><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">缴费期限</th>
						<td width="35%"><input name="posFinRat.repayPeriod" value="${posFinRat.repayPeriod}" type="text" class="Wdate clsdiv29" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*1-127" errormsg='缴费期限必须是1到127位字符！' nullmsg='缴费期限不能为空！' sucmsg='缴费期限填写正确！'  maxlength="127" style="width:60%"><font style='color:red'>*</font></td>
					</tr>
					<tr id="fkfs1" <s:if test="posFinRat.postponeMethod!=1">style="display:none"</s:if>>
						<th width="15%">缴费期</th>
						<td width="35%"><input name="posFinRat.stageLength" value="${posFinRat.stageLength}" type="text" datatype="*1-127" errormsg='缴费期必须是1到127位字符！' nullmsg='缴费期不能为空！' sucmsg='缴费期填写正确！'  maxlength="127" style="width:60%" class="clsdiv290"><font style='color:red'>*</font></td>
						<th width="15%">缴纳罚款(大写)</th>
						<td width="35%"><input name="posFinRat.pay" value="${posFinRat.pay}" type="text" datatype="*1-127" errormsg='缴纳罚款必须是1到127位字符！' nullmsg='缴纳罚款不能为空！' sucmsg='缴纳罚款填写正确！'  maxlength="127" style="width:60%" class="clsdiv290"><font style='color:red'>*</font></td>
					</tr>
					<tr id="fkfs2" <s:if test="posFinRat.postponeMethod!=1">style="display:none"</s:if>>
						<th width="15%">尚未缴纳罚款(大写)</th>
						<td width="35%"><input name="posFinRat.noPay" value="${posFinRat.noPay}" type="text" datatype="*1-127" errormsg='尚未缴纳罚款必须是1到127位字符！' nullmsg='尚未缴纳罚款不能为空！' sucmsg='尚未缴纳罚款填写正确！'  maxlength="127" style="width:60%" class="clsdiv290"><font style='color:red'>*</font></td>
					</tr>
				</s:if>
				
				<!-- 强制执行申请书  div30 -->
				<s:if test="instrumentsInfo.instrumentType==30">
					<tr>
						<th width="15%">法院名称</th>
						<td width="85%" colspan="3"><input name="enfApp.courtName" value="${enfApp.courtName}" type="text" datatype="*1-127" errormsg='法院名称必须是1到127位字符！' nullmsg='法院名称不能为空！' sucmsg='法院名称填写正确！'  maxlength="127" style="width:80%" class="clsdiv30">人民法院<font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">有关材料</th>
						<td width="85%" colspan="3">
							<textarea name="enfApp.ygcl" style="width:78%;height:120px" datatype="*1-2000" errormsg='有关材料必须是1到2000位字符！' nullmsg='有关材料不能为空！' sucmsg='有关材料填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv30">${enfApp.ygcl}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 结案审批表  div31 -->
				<s:if test="instrumentsInfo.instrumentType==31">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden"  name="closeApproval.undertakerName1" value="${closeApproval.undertakerName1}">
							${closeApproval.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="closeApproval.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${closeApproval.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv31"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">处理结果</th>
						<td width="85%" colspan="3">
							<textarea name="closeApproval.approvalResult" style="width:78%;height:120px" datatype="*1-2000" errormsg='处理结果必须是1到2000位字符！' nullmsg='处理结果不能为空！' sucmsg='处理结果填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv31">${closeApproval.approvalResult}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">执行情况</th>
						<td width="85%" colspan="3">
							<textarea name="closeApproval.executeCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='执行情况必须是1到2000位字符！' nullmsg='执行情况不能为空！' sucmsg='执行情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv31">${closeApproval.executeCondition}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 案件移送审批表 div32 -->
				<s:if test="instrumentsInfo.instrumentType==32">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden"  name="caseRefer.undertakerName1" value="${caseRefer.undertakerName1}">
							${caseRefer.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="caseRefer.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${caseRefer.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv32"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">承办人意见</th>
						<td width="85%" colspan="3">
							<textarea name="caseRefer.undertakerComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='承办人意见必须是1到2000位字符！' nullmsg='承办人意见不能为空！' sucmsg='承办人意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv32">${caseRefer.undertakerComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">受移送机关</th>
						<td width="35%"><input name="caseRefer.transferAuthority" value="${caseRefer.transferAuthority}" type="text" datatype="*1-127" errormsg='受移送机关必须是1到127位字符！' nullmsg='受移送机关不能为空！' sucmsg='受移送机关填写正确！'  maxlength="127" style="width:60%" class="clsdiv32"><font style='color:red'>*</font></td>
					</tr>
					<tr>
						<th width="15%">移送理由</th>
						<td width="85%" colspan="3">
							<textarea name="caseRefer.feedingGrounds" style="width:78%;height:120px" datatype="*1-2000" errormsg='移送理由必须是1到2000位字符！' nullmsg='移送理由不能为空！' sucmsg='移送理由填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv32">${caseRefer.feedingGrounds}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 案件移送书  div33 -->
				<s:if test="instrumentsInfo.instrumentType==33">
					<tr>
						<th width="15%">规定</th>
						<td width="85%" colspan="3">
							<input id="proName6" name="caseTransfer.proName" value="${caseTransfer.proName}" type="text" datatype="*1-2000" errormsg='规定必须是1到2000位字符！' nullmsg='规定不能为空！' sucmsg='规定填写正确！'  maxlength="2000" style="width:80%" class="clsdiv33" onclick="queryGd('6');"><font style='color:red'>*</font>
							<input type="hidden" id="proId6" name="caseTransfer.provision" value="${caseTransfer.provision}"/>
						</td>
					</tr>
					<tr>
						<th width="15%">有关材料</th>
						<td width="85%" colspan="3">
							<textarea name="caseTransfer.ygcl" style="width:78%;height:120px" datatype="*1-2000" errormsg='有关材料必须是1到2000位字符！' nullmsg='有关材料不能为空！' sucmsg='有关材料填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv33">${caseTransfer.ygcl}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				
				<!-- 调查报告  div34 -->
				<s:if test="instrumentsInfo.instrumentType==34">
					<tr>
						<th width="15%">承办人</th>
						<td width="35%">
							<input type="hidden"  name="dcbg.undertakerName1" value="${dcbg.undertakerName1}">
							${dcbg.undertakerName1}
						</td>
						<th width="15%">协办人</th>
						<td width="35%">
							<cus:SelectOneTag property="dcbg.undertaker" defaultText='请选择' codeSql="select t.row_id,t.display_name from users t where t.del_flag = 0 and t.dept_code like '002001004%' and t.row_id != '${loginUserId}'" value="${dcbg.undertaker}" datatype="*1-127" errormsg='协办人必须是1到127位字符！' nullmsg='协办人不能为空！' sucmsg='协办人填写正确！'  maxlength="127" style="width:60%" class="clsdiv34"/><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">案件基本情况</th>
						<td width="35%" colspan="3">
							<textarea name="dcbg.caseCondition" style="width:78%;height:120px" datatype="*1-2000" errormsg='案件基本情况必须是1到2000位字符！' nullmsg='案件基本情况不能为空！' sucmsg='案件基本情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)">${dcbg.caseCondition}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">调查取证情况</th>
						<td width="85%" colspan="3">
							<textarea name="dcbg.dcbgqk" style="width:78%;height:120px" datatype="*1-2000" errormsg='调查取证情况必须是1到2000位字符！' nullmsg='调查取证情况不能为空！' sucmsg='调查取证情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv34">${dcbg.dcbgqk}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">违法违规情况</th>
						<td width="85%" colspan="3">
							<textarea name="dcbg.wfwgxw" style="width:78%;height:120px" datatype="*1-2000" errormsg='违法违规情况必须是1到2000位字符！' nullmsg='违法违规情况不能为空！' sucmsg='违法违规情况填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv34">${dcbg.wfwgxw}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">拟办意见</th>
						<td width="85%" colspan="3">
							<textarea name="dcbg.undertakerComment" style="width:78%;height:120px" datatype="*1-2000" errormsg='拟办意见必须是1到2000位字符！' nullmsg='拟办意见不能为空！' sucmsg='拟办意见填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv34">${dcbg.undertakerComment}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				
				<!-- 特殊事项审批表  div35 -->
				<s:if test="instrumentsInfo.instrumentType==35">
					<tr>
						<th width="15%">事项标题</th>
						<td width="85%" colspan="3">
							<input name="specialItem.title" value="${specialItem.title}" type="text" datatype="*1-127" errormsg='事项标题必须是1到127位字符！' nullmsg='事项标题不能为空！' sucmsg='事项标题填写正确！'  maxlength="127" style="width:60%" class="clsdiv35"><font style='color:red'>*</font>
						</td>
					</tr>
					<tr>
						<th width="15%">事项理由</th>
						<td width="85%" colspan="3">
							<textarea name="specialItem.content" style="width:78%;height:120px" datatype="*1-2000" errormsg='事项理由必须是1到2000位字符！' nullmsg='事项理由不能为空！' sucmsg='事项理由填写正确！'  onKeyDown="if(this.value.length > 2000) this.value=this.value.substr(0,2000)" class="clsdiv35">${specialItem.content}</textarea><font style='color:red'>*</font>
						</td>
					</tr>
				</s:if>
				<s:if test="type != 1">
				<!-- 是否选择审核 -->
				<s:if test="ifcheck==2">
				<tr>
					<th width="15%">是否需法务审核</th>
					<td width="35%">
						<cus:SelectOneTag style="width:78%;" property="instrumentsInfo.needCheck" defaultText='请选择' codeName="是或否" value="${instrumentsInfo.needCheck}" datatype="*1-127"  nullmsg='是否需法务审核不能为空！' sucmsg='是否需法务审核填写正确！'/><font style='color:red'>*</font>
					</td>
				</tr>
				</s:if>
				<s:else>
					<input type="hidden" name="instrumentsInfo.needCheck" value="${ifcheck}">
				</s:else>
				</s:if>
				<s:else>
					<input type="hidden" name="instrumentsInfo.needCheck" value="${instrumentsInfo.needCheck}">
					<input type="hidden" name="instrumentsInfo.dzqmCheck" value="${instrumentsInfo.dzqmCheck}">
					<input type="hidden" name="instrumentsInfo.dzCheck" value="${instrumentsInfo.dzCheck}">
					<input type="hidden" name="instrumentsInfo.jzCheck" value="${instrumentsInfo.jzCheck}">
				</s:else>
				
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="submit"  >提交<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
