<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script>
		function save(){
			if(Validator.Validate(document.myform1,3)){
				changeXfNum('0');
				changeXfNum('1');
				changeNum('1','1');
				changeNum('1','2');
				changeNum('1','3');
				changeNum('2','1');
				changeNum('2','2');
				changeNum('2','3');
				changeNum('3','1');
				changeNum('3','2');
				changeNum('3','3');
				changeNum('4','1');
				changeNum('4','2');
				changeNum('4','3');
				document.myform1.action="zywsqkSave.action";
				document.myform1.submit();
			}
		}
		
		function changeXfNum(type)
		{
			if(type == '0')
			{
				var total = 0;
				var xfcf = document.getElementById("xfcf").value;
				total = accAdd(total,xfcf);
				var xfzd = document.getElementById("xfzd").value;
				total = accAdd(total,xfzd);
				var xfsz = document.getElementById("xfsz").value;
				total = accAdd(total,xfsz);
				var xfpfb = document.getElementById("xfpfb").value;
				total = accAdd(total,xfpfb);
				document.getElementById("xfhj").value = total;
			}
			else
			{
				var total = 0;
				var ljcf = document.getElementById("ljcf").value;
				total = accAdd(total,ljcf);
				var ljzd = document.getElementById("ljzd").value;
				total = accAdd(total,ljzd);
				var ljsl = document.getElementById("ljsl").value;
				total = accAdd(total,ljsl);
				var ljpfb = document.getElementById("ljpfb").value;
				total = accAdd(total,ljpfb);
				document.getElementById("ljhj").value = total;
			}
		}
		
		function changeNum(obj,type)
		{
			if(obj == '1')
			{
				if(type == '1')
				{
					var p = document.getElementsByName("fcjcrs");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('fcjcrshj').value = total;
				}
				else if(type == '2')
				{
					var p = document.getElementsByName("fcjcds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('fcjcdshj').value = total;
				}
				else
				{
					var p = document.getElementsByName("fcdbds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('fcdbdshj').value = total;
				}
			}
			else if(obj == '2')
			{
				if(type == '1')
				{
					var p = document.getElementsByName("hxjcrs");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('hxjcrshj').value = total;
				}
				else if(type == '2')
				{
					var p = document.getElementsByName("hxjcds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('hxjcdshj').value = total;
				}
				else
				{
					var p = document.getElementsByName("hxdbds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('hxdbdshj').value = total;
				}
			}
			else if(obj == '3')
			{
				if(type == '1')
				{
					var p = document.getElementsByName("wljcrs");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('wljcrshj').value = total;
				}
				else if(type == '2')
				{
					var p = document.getElementsByName("wljcds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('wljcdshj').value = total;
				}
				else
				{
					var p = document.getElementsByName("wldbds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('wldbdshj').value = total;
				}
			}
			else
			{
				if(type == '1')
				{
					var p = document.getElementsByName("swjcrs");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('swjcrshj').value = total;
				}
				else if(type == '2')
				{
					var p = document.getElementsByName("swjcds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('swjcdshj').value = total;
				}
				else
				{
					var p = document.getElementsByName("swdbds");
					var total = 0;
					for(var i=0;i<p.length;i++)
					{
						var a = p[i].value;			
						if(a != null && a != "" && a != "0" && a != 0)
						{					
							total = accAdd(total,a);
						}
					}
					document.getElementById('swdbdshj').value = total;
				}
			}
		}
		
		//加法
		function accAdd(arg1, arg2) {
        	var r1, r2, m, c;
        	try { r1 = arg1.toString().split(".")[1].length } catch (e) { r1 = 0 }
        	try { r2 = arg2.toString().split(".")[1].length } catch (e) { r2 = 0 }
        	c = Math.abs(r1 - r2);
        	m = Math.pow(10, Math.max(r1, r2))
       	 	if (c > 0) {
           	 	var cm = Math.pow(10, c);
            	if (r1 > r2) {
                	arg1 = Number(arg1.toString().replace(".", ""));
                	arg2 = Number(arg2.toString().replace(".", "")) * cm;
            	}
            	else {
                	arg1 = Number(arg1.toString().replace(".", "")) * cm;
                	arg2 = Number(arg2.toString().replace(".", ""));
            	}
        	}
        	else {
            	arg1 = Number(arg1.toString().replace(".", ""));
            	arg2 = Number(arg2.toString().replace(".", ""));
        	}
        	return (arg1 + arg2) / m
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="zywsqk.id" value="${zywsqk.id}">
		<input type="hidden" name="zywsqk.createTime" value="<fmt:formatDate type="both" value="${zywsqk.createTime}" />">
		<input type="hidden" name="zywsqk.updateTime" value="${zywsqk.updateTime}">
		<input type="hidden" name="zywsqk.createUserID" value="${zywsqk.createUserID}">
		<input type="hidden" name="zywsqk.updateUserID" value="${zywsqk.updateUserID}">
		<input type="hidden" name="zywsqk.deptId" value="${zywsqk.deptId}">
		<input type="hidden" name="zywsqk.delFlag" value="${zywsqk.delFlag}">
		<input type="hidden" name="zywsqk.szzname" value="${zywsqk.szzname}">
		<input type="hidden" name="zywsqk.qymc" value="${zywsqk.qymc}">
		<input type="hidden" name="zywsqk.szzid" value="${zywsqk.szzid}">
		<input type="hidden" name="zywsqk.qyid" value="${zywsqk.qyid}">
		<input type="hidden" name="zywsqk.qylx" value="${zywsqk.qylx}">
		<input type="hidden" name="zywsqk.hyfl" value="${zywsqk.hyfl}">
		<input type="hidden" name="zywsqk.qygm" value="${zywsqk.qygm}">
		<input type="hidden" name="zywsqk.qyzclx" value="${zywsqk.qyzclx}">
		<input type="hidden" name="zywsqk.zzjgdm" value="${zywsqk.zzjgdm}">
		<input type="hidden" name="zywsqk.fddbr" value="${zywsqk.fddbr}">
		<input type="hidden" name="zywsqk.fddbrlxdh" value="${zywsqk.fddbrlxdh}">
		<input type="hidden" name="zywsqk.szc" value="${zywsqk.szc}">
		<input type="hidden" name="zywsqk.szcname" value="${zywsqk.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
					<th width="15%">注册地址</th>
					<td width="35%" colspan="2"><input name="zywsqk.zcdz" value="${zywsqk.zcdz}" type="text" style="width:90%"></td>
					<th width="15%">工作场所地址</th>
					<td width="35%" colspan="2"><input name="zywsqk.gzcsdz" value="${zywsqk.gzcsdz}" type="text" style="width:90%"></td>
				</tr>
				<tr>
					<th width="15%">联系人</th>
					<td width="20%"><input name="zywsqk.lxr" value="${zywsqk.lxr}" type="text" style="width:90%"></td>
					<th width="15%">联系电话</th>
					<td width="15%"><input name="zywsqk.lxrlxdh" value="${zywsqk.lxrlxdh}" type="text" style="width:90%"></td>
					<th width="15%">统计年份</th>
					<td width="20%"><input name="zywsqk.tjnf" value="${zywsqk.tjnf}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy'})" type="text" style="width:90%"></td>
				</tr>
				<tr>
					<th width="15%">从业人员数(人)</th>
					<td width="20%"><input name="zywsqk.cyrys" value="${zywsqk.cyrys}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">接触职业病危害因素人数(人)</th>
					<td width="15%"><input name="zywsqk.jcrs" value="${zywsqk.jcrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">合同告知职业病危害人数(人)</th>
					<td width="20%"><input name="zywsqk.htrs" value="${zywsqk.htrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">建立职业健康监护档案人数(人)</th>
					<td width="20%"><input name="zywsqk.jdrs" value="${zywsqk.jdrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">职业病危害作业岗位数(个)</th>
					<td width="15%"><input name="zywsqk.zygws" value="${zywsqk.zygws}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">设置警示标识岗位数(个)</th>
					<td width="20%"><input name="zywsqk.szgws" value="${zywsqk.szgws}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">应职业卫生培训人数(人)</th>
					<td width="20%"><input name="zywsqk.yzrs" value="${zywsqk.yzrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">实际职业卫生培训人数(人)</th>
					<td width="15%"><input name="zywsqk.sjrs" value="${zywsqk.sjrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">专职职业卫生管理人数(人)</th>
					<td width="20%"><input name="zywsqk.zzrs" value="${zywsqk.zzrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">兼职职业卫生管理人数(人)</th>
					<td width="20%"><input name="zywsqk.jzrs" value="${zywsqk.jzrs}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">应职业病危害预评价项目数(个)</th>
					<td width="15%"><input name="zywsqk.yzxms" value="${zywsqk.yzxms}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">实际职业病危害预评价项目数(个)</th>
					<td width="20%"><input name="zywsqk.sjxms" value="${zywsqk.sjxms}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
				</tr>
				<tr>
					<th width="15%">应职业病危害控制效果评价项目数(个)</th>
					<td width="20%"><input name="zywsqk.yzpjxms" value="${zywsqk.yzpjxms}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">实际职业病危害控制效果评价项目数(个)</th>
					<td width="15%"><input name="zywsqk.sjpjxms" value="${zywsqk.sjpjxms}" type="text" style="width:90%" onKeyUp="validate(event,this)"></td>
					<th width="15%">职业病危害申报</th>
					<td width="20%"><cus:SelectOneTag property="zywsqk.zybwhsb" defaultText='请选择' codeName="职业病危害申报" value="${zywsqk.zybwhsb}" style="width:90%"/></td>
				</tr>
				<tr>
					<th width="15%" rowspan="2">主要负责人职业卫生培训</th>
					<td width="20%" rowspan="2"><cus:SelectOneTag property="zywsqk.zyfzrzywspx" defaultText='请选择' codeName="主要负责人职业卫生培训" value="${zywsqk.zyfzrzywspx}" style="width:90%"/></td>
					<th width="32%" colspan="2" style="text-align:center">应职业健康检查人数(人)</th>
					<th width="33%" colspan="2" style="text-align:center">实际职业健康检查人数(人)</th>
				</tr>
				<tr>
					<td width="65%" colspan="4">
						<table width="100%">
							<tr>
								<td width="16%">岗前</th>
								<td width="16%">在岗</th>
								<td width="18%">离岗</th>
								<td width="16%">岗前</th>
								<td width="16%">在岗</th>
								<td width="18%">离岗</th>
							</tr>
							<tr>
								<td width="16%"><input name="zywsqk.gqyzjcrs" value="${zywsqk.gqyzjcrs}" type="text" style="width:100%" onKeyUp="validate(event,this)"></td>
								<td width="16%"><input name="zywsqk.zgyzjcrs" value="${zywsqk.zgyzjcrs}" type="text" style="width:100%" onKeyUp="validate(event,this)"></td>
								<td width="18%"><input name="zywsqk.lgyzjcrs" value="${zywsqk.lgyzjcrs}" type="text" style="width:100%" onKeyUp="validate(event,this)"></td>
								<td width="16%"><input name="zywsqk.gqsjjcrs" value="${zywsqk.gqsjjcrs}" type="text" style="width:100%" onKeyUp="validate(event,this)"></td>
								<td width="16%"><input name="zywsqk.zgsjjcrs" value="${zywsqk.zgsjjcrs}" type="text" style="width:100%" onKeyUp="validate(event,this)"></td>
								<td width="18%"><input name="zywsqk.lgsjjcrs" value="${zywsqk.lgsjjcrs}" type="text" style="width:100%" onKeyUp="validate(event,this)"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th width="50%" colspan="3" style="text-align:center">新发职业病病例数(人)</th>
					<th width="50%" colspan="3" style="text-align:center">累计职业病病例数(人)</th>
				</tr>
				<tr>
					<td width="50%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">合计</th>
								<td width="20%">尘肺</th>
								<td width="20%">职业中毒</th>
								<td width="20%">噪声聋</th>
								<td width="20%">职业性皮肤病</th>
							</tr>
							<tr>
								<td width="20%"><input id="xfhj" name="zywsqk.xfhj" value="${zywsqk.xfhj}" type="text"  style="width:100%" readonly="readonly"></td>
								<td width="20%"><input id="xfcf" name="zywsqk.xfcf" value="${zywsqk.xfcf}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('0')"></td>
								<td width="20%"><input id="xfzd" name="zywsqk.xfzd" value="${zywsqk.xfzd}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('0')"></td>
								<td width="20%"><input id="xfsz" name="zywsqk.xfsz" value="${zywsqk.xfsz}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('0')"></td>
								<td width="20%"><input id="xfpfb" name="zywsqk.xfpfb" value="${zywsqk.xfpfb}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('0')"></td>
							</tr>
						</table>
					</td>
					<td width="50%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">合计</th>
								<td width="20%">尘肺</th>
								<td width="20%">职业中毒</th>
								<td width="20%">噪声聋</th>
								<td width="20%">职业性皮肤病</th>
							</tr>
							<tr>
								<td width="20%"><input id="ljhj" name="zywsqk.ljhj" value="${zywsqk.ljhj}" type="text" style="width:100%" readonly="readonly"></td>
								<td width="20%"><input id="ljcf" name="zywsqk.ljcf" value="${zywsqk.ljcf}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('1')"></td>
								<td width="20%"><input id="ljzd" name="zywsqk.ljzd" value="${zywsqk.ljzd}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('1')"></td>
								<td width="20%"><input id="ljsl" name="zywsqk.ljsl" value="${zywsqk.ljsl}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('1')"></td>
								<td width="20%"><input id="ljpfb" name="zywsqk.ljpfb" value="${zywsqk.ljpfb}" type="text" style="width:100%" onKeyUp="validate(event,this);" onblur="changeXfNum('1')"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="6">
						<table width="100%">
							<tr>
								<td width="40%" colspan="2" style="text-align:center">职业病危害因素</td>
								<td width="20%" style="text-align:center">接触人数(人)</td>
								<td width="20%" style="text-align:center">检测点数（个)</td>
								<td width="20%" style="text-align:center">达标点数(个)</td>						
							</tr>
							<tr>
								<td width="20%">1.粉尘(小计)</td>
								<td width="20%">FC</td>
								<td width="20%"><input id="fcjcrshj" name="zywsqk.fcjcrshj" value="${zywsqk.fcjcrshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td width="20%"><input id="fcjcdshj" name="zywsqk.fcjcdshj" value="${zywsqk.fcjcdshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td width="20%"><input id="fcdbdshj" name="zywsqk.fcdbdshj" value="${zywsqk.fcdbdshj}" type="text" readonly="readonly" style="width:100%"></td>						
							</tr>
							<c:forEach var="zywhglb" items="${fcList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}<input type="hidden" name="fczywhmc" value="${zywhglb.zywhmc}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}<input type="hidden" name="fczywhid" value="${zywhglb.zywhid}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="fcjcrs" value="${zywhglb.jcrs}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('1','1')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="fcjcds" value="${zywhglb.jcds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('1','2')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="fcdbds" value="${zywhglb.dbds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('1','3')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								</tr>
							</c:forEach>
							<tr>
								<td>2.化学毒物(小计)</td>
								<td>HX</td>
								<td><input id="hxjcrshj" name="zywsqk.hxjcrshj" value="${zywsqk.hxjcrshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td><input id="hxjcdshj" name="zywsqk.hxjcdshj" value="${zywsqk.hxjcdshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td><input id="hxdbdshj" name="zywsqk.hxdbdshj" value="${zywsqk.hxdbdshj}" type="text" readonly="readonly" style="width:100%"></td>						
							</tr>
							<c:forEach var="zywhglb" items="${hxList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}<input type="hidden" name="hxzywhmc" value="${zywhglb.zywhmc}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}<input type="hidden" name="hxzywhid" value="${zywhglb.zywhid}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="hxjcrs" value="${zywhglb.jcrs}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('2','1')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="hxjcds" value="${zywhglb.jcds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('2','2')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="hxdbds" value="${zywhglb.dbds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('2','3')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								</tr>
							</c:forEach>
							<tr>
								<td>3.物理因素(小计)</td>
								<td>WL</td>
								<td><input id="wljcrshj" name="zywsqk.wljcrshj" value="${zywsqk.wljcrshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td><input id="wljcdshj" name="zywsqk.wljcdshj" value="${zywsqk.wljcdshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td><input id="wldbdshj" name="zywsqk.wldbdshj" value="${zywsqk.wldbdshj}" type="text" readonly="readonly" style="width:100%"></td>						
							</tr>
							<c:forEach var="zywhglb" items="${wlList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}<input type="hidden" name="wlzywhmc" value="${zywhglb.zywhmc}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}<input type="hidden" name="wlzywhid" value="${zywhglb.zywhid}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="wljcrs" value="${zywhglb.jcrs}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('3','1')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="wljcds" value="${zywhglb.jcds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('3','2')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="wldbds" value="${zywhglb.dbds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('3','3')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								</tr>
							</c:forEach>
							<tr>
								<td>4.生物因素(小计)</td>
								<td>SW</td>
								<td><input id="swjcrshj" name="zywsqk.swjcrshj" value="${zywsqk.swjcrshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td><input id="swjcdshj" name="zywsqk.swjcdshj" value="${zywsqk.swjcdshj}" type="text" readonly="readonly" style="width:100%"></td>
								<td><input id="swdbdshj" name="zywsqk.swdbdshj" value="${zywsqk.swdbdshj}" type="text" readonly="readonly" style="width:100%"></td>						
							</tr>
							<c:forEach var="zywhglb" items="${swList}">
								<tr>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhmc}<input type="hidden" name="swzywhmc" value="${zywhglb.zywhmc}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>>${zywhglb.zywhid}<input type="hidden" name="swzywhid" value="${zywhglb.zywhid}"></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="swjcrs" value="${zywhglb.jcrs}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('4','1')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="swjcds" value="${zywhglb.jcds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('4','2')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								   <td <c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>><input name="swdbds" value="${zywhglb.dbds}" type="text" onKeyUp="validate(event,this);" onblur="changeNum('4','3')" style="width:100%;<c:if test="${status.count%2==0}">style="background-color:#EEE"</c:if>"/></td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
				<tr>
					<th width="15%">负责人</th>
					<td width="20%"><input name="zywsqk.fzr" value="${zywsqk.fzr}" type="text" style="width:90%"></td>
					<th width="15%">填表人</th>
					<td width="15%"><input name="zywsqk.tbr" value="${zywsqk.tbr}" type="text" style="width:90%"></td>
					<th width="15%">联系电话</th>
					<td width="20%"><input name="zywsqk.tbrlxdh" value="${zywsqk.tbrlxdh}" type="text" style="width:90%"></td>
				</tr>
				<tr>
					<td colspan="6" height="100px" style="text-align:center">
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
		<div class="submitdata">
	</form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
