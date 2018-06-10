<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript">
		var num = 1;
		function addNewWhp()
		{
			num=document.getElementsByName("occChaInf.jshxName").length+1;
			var div= document.getElementById("more");
			var s = "<tr id='" + num + "'>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:90px' type='text' name='occChaInf.jshxName' dataType='*1-127' errormsg='此项为必填'  onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:90px' type='text' name='occChaInf.duty'    onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:90px' type='text' name='occChaInf.telephone'    onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:90px' type='text' name='occChaInf.mobile'    onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'> <select  style='width:90px' name='occChaInf.degreeEducation'><option value ='1'>小学</option><option value ='2'>初中</option><option value ='3'>中专</option><option value ='4'>高中</option><option value ='5'>专科</option><option value='6'>本科</option><option value='7'>硕士研究生</option><option value='8'>博士研究生</option></select>  </td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:90px' type='text' name='occChaInf.professional'    onblur='setVal(this)'/></td>";
			
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:100px' type='text' name='occChaInf.trainingDateStart' class='Wdate'    onclick=WdatePicker({dateFmt:'"+'yyyy-MM-dd'+"'})  onblur='setVal(this)'/>-<input  style='width:100px' type='text' name='occChaInf.trainingDateEnd' class='Wdate'    onclick=WdatePicker({dateFmt:'"+'yyyy-MM-dd'+"'})  onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input  style='width:90px' type='text' name='occChaInf.trainingCertificatNumber'    onblur='setVal(this)'/></td>";
			s += "<td style='text-align:center;padding: 0 0 0 0;'><input type='button' style='text-align:center;color:red' value='删除' onclick=delWhpZywsfgfzr('" + num + "')></td>";
			s += "</tr>";
			num = num +1;
			var a = div.innerHTML;
			a = a.replace("</TBODY>",s + "</TBODY>");
			a = a.replace("</tbody>",s + "</tbody>");
			div.innerHTML = a;
		}
		function delWhpZywsfgfzr(aid){
			$("tr").remove("tr[id="+aid+"]");
		}
		function delWhp(aid)
		{
			    $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "${ctx}/jsp/zywsfgfzr/occChaInfDel.action",
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
		function setVal(obj)
		{
			var a = obj.value;
			obj.setAttribute("value", a);
		}
		
		
		function sub(){
			var names=$("input[name='occChaInf.jshxName']") ;
			for(var j=0;j<names.length;j++){
				if(names[j].value==null||names[j].value==''){
					alert("请填写职业卫生管理人员姓名");
					return false;
				}
			}
			var ds=$("input[name='occChaInf.trainingDateStart']") ;
			var trainingDateStart="";
			if(ds.length>0){
				for(var i=0;i<ds.length;i++){
					if(i==ds.length-1){
						trainingDateStart+=ds[i].value;
					}else{
						trainingDateStart+=ds[i].value+",";
					}
				}
				$("#trainingDateStart").val(trainingDateStart);
			}
			var de=$("input[name='occChaInf.trainingDateEnd']") ;
			var trainingDateEnd="";
			if(de.length>0){
				for(var i=0;i<de.length;i++){
					if(i==de.length-1){
						trainingDateEnd+=de[i].value;
					}else{
						trainingDateEnd+=de[i].value+",";
					}
				}
				$("#trainingDateEnd").val(trainingDateEnd);
			}
			document.myform1.submit();
		
		}
	</script>
</head>

<body validform="true" style="overflow: auto;">
   <div class="box_01 boxBmargin12 submitdata" style="overflow-y: auto;overflow-x: hidden;padding: 0;top: 0px;left: 0px;right: 0px;bottom: 0;position: absolute; ">
		<div class="inner6px" >
			<div class="cell" style="width: 100%;">
	<form name="myform1" method="post" enctype="multipart/form-data" action="occHeaInfoSave.action">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="occHeaInfo.id" value="${occHeaInfo.id}">
		<input type="hidden" name="occHeaInfo.createTime" value="<fmt:formatDate type="both" value="${occHeaInfo.createTime}" />">
		<input type="hidden" name="occHeaInfo.updateTime" value="${occHeaInfo.updateTime}">
		<input type="hidden" name="occHeaInfo.createUserID" value="${occHeaInfo.createUserID}">
		<input type="hidden" name="occHeaInfo.updateUserID" value="${occHeaInfo.updateUserID}">
		<input type="hidden" name="occHeaInfo.deptId" value="${occHeaInfo.deptId}">
		<input type="hidden" name="occHeaInfo.delFlag" value="${occHeaInfo.delFlag}">
		<input type="hidden" name="trainingDateStart" id="trainingDateStart" value="${trainingDateStart}">
		<input type="hidden" name="trainingDateEnd" id="trainingDateEnd" value="${trainingDateEnd}">
		
			<table width="100%" border="0">
				 <tr>
					<td colspan="4" style="text-align:center">基本信息</td>
				</tr>
				<tr>
					<th width="15%">女职工人数</th>
					<td width="35%"><input name="occHeaInfo.femaleWorkersNumber" style="width:60%"  value="${occHeaInfo.femaleWorkersNumber}" type="text" maxlength="127"></td>
					<th width="15%">接触职业病危害因素人数</th>
					<td width="35%"><input name="occHeaInfo.occupationalDiseasersNumber" style="width:60%"  value="${occHeaInfo.occupationalDiseasersNumber}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">企业职业病危害类别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHeaInfo.companyHazardCategory" defaultText='请选择' codeName="企业职业病危害类别" value="${occHeaInfo.companyHazardCategory}" /></td>
					<th width="15%">职业病危害行业类别</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHeaInfo.hazardIndustryCategory" defaultText='请选择' codeName="职业病危害行业类别" value="${occHeaInfo.hazardIndustryCategory}" /></td>
				</tr>
				<tr>
					<th width="15%">接触职业病危害因素女工人数</th>
					<td width="35%"><input name="occHeaInfo.femaleWorkersDiseasesNumber" style="width:60%"  value="${occHeaInfo.femaleWorkersDiseasesNumber}" type="text" maxlength="127"></td>
					<th width="15%">职业病危害岗位数</th>
					<td width="35%"><input name="occHeaInfo.occupationDiseasePosts" style="width:60%"  value="${occHeaInfo.occupationDiseasePosts}"  type="text" maxlength="127"></td>
				</tr>
				<tr >
					<td colspan="4" style="text-align:center">职业卫生分管负责人</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrName" style="width:60%" value="${occHeaInfo.zywsfgfzrName}" type="text" maxlength="127"></td>
					<th width="15%">职务</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrDuty" style="width:60%" value="${occHeaInfo.zywsfgfzrDuty}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrTelephone" style="width:60%" value="${occHeaInfo.zywsfgfzrTelephone}" type="text" maxlength="127"></td>
					<th width="15%">手机</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrMobile" style="width:60%" datatype="m" value="${occHeaInfo.zywsfgfzrMobile}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%"  property="occHeaInfo.zywsfgfzrEducation" defaultText='请选择' codeName="学历" value="${occHeaInfo.zywsfgfzrEducation}" /></td>
					<th width="15%">专业</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrProfession" style="width:60%" value="${occHeaInfo.zywsfgfzrProfession}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训日期</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrTrainingDateStart" id="zywsfgfzrTrainingDateStart" value="<fmt:formatDate pattern="yyyy-MM-dd"  type='both' value='${occHeaInfo.zywsfgfzrTrainingDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'zywsfgfzrTrainingDateEnd\')}'})">
						-<input name="occHeaInfo.zywsfgfzrTrainingDateEnd" id="zywsfgfzrTrainingDateEnd" value="<fmt:formatDate pattern="yyyy-MM-dd"  type='both' value='${occHeaInfo.zywsfgfzrTrainingDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'zywsfgfzrTrainingDateStart\')}'})">
					</td>
					<th width="15%">培训合格证号</th>
					<td width="35%"><input name="occHeaInfo.zywsfgfzrTrainingNo" style="width:60%" value="${occHeaInfo.zywsfgfzrTrainingNo}" type="text" maxlength="127"></td>
				</tr>
				
				<tr >
					<td colspan="4" style="text-align:center">职业卫生管理机构负责人</td>
				</tr>
				<tr>
					<th width="15%">姓名</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrName" style="width:60%" value="${occHeaInfo.zywsgljgfzrName}" type="text" maxlength="127"></td>
					<th width="15%">职务</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrDuty" style="width:60%" value="${occHeaInfo.zywsgljgfzrDuty}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">办公电话</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrTelephone" style="width:60%" value="${occHeaInfo.zywsgljgfzrTelephone}" type="text" maxlength="127"></td>
					<th width="15%">手机</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrMobile" style="width:60%" datatype="m" value="${occHeaInfo.zywsgljgfzrMobile}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">学历</th>
					<td width="35%"><cus:SelectOneTag style="width:60%" property="occHeaInfo.zywsgljgfzrEducation" defaultText='请选择' codeName="学历" value="${occHeaInfo.zywsgljgfzrEducation}" /></td>
					<th width="15%">专业</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrProfession" style="width:60%" value="${occHeaInfo.zywsgljgfzrProfession}" type="text" maxlength="127"></td>
				</tr>
				<tr>
					<th width="15%">培训日期</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrTrainingDateStart" id="zywsgljgfzrTrainingDateStart" value="<fmt:formatDate pattern="yyyy-MM-dd" type="date"  value='${occHeaInfo.zywsgljgfzrTrainingDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'zywsgljgfzrTrainingDateEnd\')}'})">
					-<input name="occHeaInfo.zywsgljgfzrTrainingDateEnd" id="zywsgljgfzrTrainingDateEnd" value="<fmt:formatDate pattern="yyyy-MM-dd"  type='both' value='${occHeaInfo.zywsgljgfzrTrainingDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'zywsgljgfzrTrainingDateStart\')}'})">
					</td>
					<th width="15%">培训合格证号</th>
					<td width="35%"><input name="occHeaInfo.zywsgljgfzrTrainingNo" style="width:60%" value="${occHeaInfo.zywsgljgfzrTrainingNo}" type="text" maxlength="127"></td>
				</tr>
				
				
				<tr>
			   	 <td  colspan="4" style="text-align:center">职业卫生管理人员</td>
			    </tr>
			    
			    
			    <tr>
					<td colspan="4">
						<div id="more">
						<table>
							<tr>
								<td  style="text-align:center;width:10%;padding: 0 0 0 0;">姓名<font style="color:red">*</font></td>
								<td  style="text-align:center;width:10%;padding: 0 0 0 0;">职务</td>
								<td  style="text-align:center;width:10%;padding: 0 0 0 0;">办公电话</td>
								<td  style="text-align:center;width:10%;padding: 0 0 0 0;">手机</td>
								<td  style="text-align:center;width:10%;padding: 0 0 0 0;">学历</td>
								<td  style="text-align:center;width:10%;padding: 0 0 0 0;">专业</td>
								<td  style="text-align:center;width:25%;padding: 0 0 0 0;">培训日期</td>
								<td  style="text-align:center;width:10;padding: 0 0 0 0;%">培训合格证号</td>
								<td  style="text-align:center;width:5%;padding: 0 0 0 0;"><input type="button" value="添加" onclick="addNewWhp()"/></td>
							</tr>	
							<c:forEach var="occChaInf" items="${occChaInfs}"  varStatus="status">
								<tr id="${occChaInf.id}">
									<td style="text-align:center;padding: 0 0 0 0;"><input style='width:90px' name="occChaInf.jshxName" value="${occChaInf.jshxName}" type="text" onblur="setVal(this)"></td>
									<td style="text-align:center;padding: 0 0 0 0;"><input style='width:90px' name="occChaInf.duty" value="${occChaInf.duty}" type="text" onblur="setVal(this)"></td>
									<td style="text-align:center;padding: 0 0 0 0;"><input  style='width:90px' name="occChaInf.telephone" value="${occChaInf.telephone}" type="text" onblur="setVal(this)"></td>
									<td style="text-align:center;padding: 0 0 0 0;"><input  style='width:90px' name="occChaInf.mobile" value="${occChaInf.mobile}" type="text" onblur="setVal(this)"></td>
									<td style="text-align:center;padding: 0 0 0 0;">  
											<select name="occChaInf.degreeEducation" style='width:90px' >
											<c:if test="${occChaInf.degreeEducation==1 }">
												<option value="1" selected="true">小学</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=1 }">
												<option value="1" >小学</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==2 }">
												<option value="2" selected="true">初中</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=2 }">
												<option value="2" >初中</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==3 }">
												<option value="3" selected="true">中专</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=3 }">
												<option value="3" >中专</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==4 }">
												<option value="4" selected="true">高中</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=4 }">
												<option value="4" >高中</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==5 }">
												<option value="5" selected="true">专科</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=5 }">
												<option value="5" >专科</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==6 }">
												<option value="6" selected="true">本科</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=6 }">
												<option value="6" >本科</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==7 }">
												<option value="7" selected="true">硕士研究生</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=7 }">
												<option value="7" >硕士研究生</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation==8 }">
												<option value="8" selected="true">博士研究生</option>
											</c:if>
											<c:if test="${occChaInf.degreeEducation!=8 }">
												<option value="8" >博士研究生</option>
											</c:if>
										</select>
									<td style="text-align:center;padding: 0 0 0 0;"><input  style='width:90px' name="occChaInf.professional" value="${occChaInf.professional}" type="text" onblur="setVal(this)"></td>
									<td style="text-align:center;padding: 0 0 0 0;"><input  style='width:100px'  name="occChaInf.trainingDateStart" value="<fmt:formatDate type='date' pattern="yyyy-MM-dd"  value='${occChaInf.trainingDateStart}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onblur="setVal(this)">
									-<input  style='width:100px'   name="occChaInf.trainingDateEnd" value="<fmt:formatDate type='date' pattern="yyyy-MM-dd"  value='${occChaInf.trainingDateEnd}' />" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" onblur="setVal(this)">
									</td>
									<td style="text-align:center;padding: 0 0 0 0;"><input  style='width:90px' name="occChaInf.trainingCertificatNumber" value="${occChaInf.trainingCertificatNumber}" type="text" onblur="setVal(this)"></td>
									<td style="text-align:center;padding: 0 0 0 0;"><input name="occChaInf.id" value="${occChaInf.id}" type="hidden" ><input type="button" style="color:red" value="删除" onclick="delWhp('${occChaInf.id}')"></td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</td>
				</tr>
			    
			    
			    
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<a href="#" class="btn_01" type="button" onclick="sub();" >更新<b></b></a>&nbsp;
					</td>
				</tr>
			</table>
	</form>
	</div></div></div>
</body>
</html>
