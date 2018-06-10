<%@page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="/common/header.jsp"%>
<%@ taglib prefix="ww" uri="/struts-tags"%>
<c:set var="curr_path" value="添加字段" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>添加字段</title>
<script type='text/javascript' src='<ww:url value="/dwr/engine.js"/>'></script>
<script type='text/javascript' src='<ww:url value="/dwr/util.js"/>'></script>
<script type='text/javascript'
	src='<ww:url value="/dwr/interface/FormFieldManager.js"/>'></script>
<script type='text/javascript'
	src='<ww:url value="/dwr/interface/CodeService.js"/>'></script>
<%@include file="/common/jsLib.jsp"%>
<script type="text/javascript"
	src="${ctx}/webResources/js/jquery/jquery.form.js"></script>
<script type="text/javascript">
        var isShow = false;
		function init() {
			var name = document.getElementById("field.fieldType").value;
			displayNumber(name, '<ww:property value="field.fieldDisplayType"/>');
			if(document.getElementById("field.dataSourceType")){
				var dataSourceType = document.getElementById("field.dataSourceType").value;
				getCodeValue(dataSourceType,'<ww:property value="field.fieldDefaultValue"/>');
			}
			
			$("#trRxpSam").hide();
			dataSourceSelect('<ww:property value="field.dataSource"/>');
		}		
		function displayNumber(value, obj){
		    getXSW(value);
			FormFieldManager.getColumnType(value, function(data) {
				dwr.util.removeAllOptions('field.fieldDisplayType');
				dwr.util.addOptions('field.fieldDisplayType', data);
				if(obj==""||obj=="-1")
				{
				document.getElementById("field.fieldDisplayType").options[0].selected = true;
				
				
				}
				else
				{
				DWRUtil.setValue('field.fieldDisplayType', obj);
				}
				 displayFields();			
			});
		}
		function getCodeValue(codeId,obj){
			if(codeId!='-1'){
				DWREngine.setAsync(false);   
				CodeService.findCodeValueByCode(codeId,function(data){
			        dwr.util.removeAllOptions("field.fieldDefaultValue");      	
			        dwr.util.addOptions("field.fieldDefaultValue",{'-1':"-默认值-"});
			        dwr.util.addOptions("field.fieldDefaultValue",data,"id","itemText");
					if(obj==''){obj="-1";}
					    DWRUtil.setValue("field.fieldDefaultValue",obj);
			    });
			    DWREngine.setAsync(true); 
		    }
		}	
		function displayFields() {
			var value = document.getElementById("field.fieldDisplayType").value;
			if(value!="DropdownList"&&value!="Radio"&&value!="CheckBox"){
	 			document.getElementById("dsType").style.display = "none";
	 		}
	 		else{
	  			document.getElementById("dsType").style.display = "block";
	 		}
		}
		function dataSourceSelect(value) {
			if (value == 'Blank'||value == '') {
				document.getElementById("dateDisName").innerHTML = "无类型";
				document.getElementById("divSqlValue").style.display = "none";
				document.getElementById("divCodes").style.display = "none";
			} else if (value == 'Other') {
				document.getElementById("dateDisName").innerHTML = "取值sql";
				document.getElementById("divSqlValue").style.display = "block";
				document.getElementById("divCodes").style.display = "none";
			} else {
				document.getElementById("dateDisName").innerHTML = "数据源代码";
				document.getElementById("divSqlValue").style.display = "none";
				document.getElementById("divCodes").style.display = "block";
			}
		}
		function getXSW(name){
			if(name=="number"||name=="decimal"){
				document.getElementById("xsw").style.display = "block";
			}else{
				document.getElementById("xsw").style.display = "none";
			}
		}
		function changeHidStat(){
			if(isShow){
				isShow = false;
				$("#trRxpSam").hide();
				$("#btnSample").html("举例<b></b>");
			}else{
				isShow = true;
				$("#trRxpSam").show();
				$("#btnSample").html("隐藏<b></b>");
			}
		}
		function testPatrn_check(){ 
			var patrnStr = document.getElementById("validCheckFormula").value;	
			if(patrnStr == "")
				return;
			var str = document.getElementById("testRep").value;
			var reg = new RegExp(patrnStr);
			var rsobj = reg.test(str);
			if(rsobj==false)
	        	alert(str+" 不符合正则表达式： "+patrnStr);
	        else
				alert(str+" 适应正则表达式： "+patrnStr);
		} 
		//ajax提交formFieldSave.action后的回调方法
		function formAjaxCallback(data){
			if(data.result)
				$.messager.alert('提示', data.message);
			else
				$.messager.alert('提示', data.message);
			reloadData("fieldWindow");
		}	
		</script>
</head>
<body onload="init()" validform="true" style="overflow:visible">
	<div class="page_dialog">
		<div class="inner6px" >
			<div class="cell" >
				<form name="myform" method="post" id="myform" ajax="true"
					action="formFieldSave.action">
					<input type="hidden" name="tableId"
						value="<ww:property value="tableId"/>"> <input
						type="hidden" name="field.id"
						value="<ww:property value="field.id"/>">

					<table cellspacing="0" cellpadding="0" width="100%" align="center"
						border="0" style="word-break: break-all;">
						<tr>
							<th width="100">显示名</th>
							<td><input id="fieldDisplayName" class="form_text"
								name="field.fieldDisplayName" type="text" datatype="s2-30"
								style="border: #A1BCA3 1px solid;"
								value="<ww:property value="field.fieldDisplayName"/>" /> <span
								style="color: Red">*</span></td>
							<th width="100">创建名称</th>
							<td <ww:if test="field.id!=null">disabled</ww:if>><input class="form_text"
								id="fieldName" name="field.fieldName" type="text"
								datatype="/^[0-9a-zA-Z_]{3,30}$/"
								style="border: #A1BCA3 1px solid;"
								value="<ww:property value="field.fieldName"/>"
								ajaxurl="checkFieldName.action?field.fieldName=${field.fieldName}&field.id=${field.id}&tableId=${tableId}" />
								<span style="color: Red;">*</span></td>
						</tr>
						<tr>
							<th>字段类型</th>
							<td <ww:if test="field.id!=null">disabled</ww:if>><select
								id="field.fieldType" name="field.fieldType"
								onchange="displayNumber(this.value,'-1')">
									<option value='varchar'
										<ww:if test="field.fieldType=='varchar'">selected</ww:if>>字符串</option>
									<option value='date'
										<ww:if test="field.fieldType=='date'">selected</ww:if>>时间日期</option>
									<option value='int'
										<ww:if test="field.fieldType=='int'">selected</ww:if>>整数类型</option>
									<option value='number'
										<ww:if test="field.fieldType=='number'">selected</ww:if>>浮点数</option>
									<option value='blob'
										<ww:if test="field.fieldType=='blob'">selected</ww:if>>二进制流</option>
									<option value='clob'
										<ww:if test="field.fieldType=='clob'">selected</ww:if>>文本</option>
							</select></td>
							<th>字段长度</th>
							<td <ww:if test="field.id!=null">disabled</ww:if>>
								<table>
									<tr>
										<td><input id="fieldLength" name="field.fieldLength" class="form_text"
											type="text" value="<ww:property value="field.fieldLength"/>"
											datatype="n1-20"
											style="width: 70px; border: #A1BCA3 1px solid;" /></td>
										<td id="xsw">小数位数 <input name="field.decimalLength" class="form_text"
											type="text"
											value="<ww:property value="field.decimalLength"/>"
											style="width: 40px;" /></td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<th>显示方式</th>
							<td colspan="3"><select name="field.fieldDisplayType"
								id="field.fieldDisplayType" onchange="displayFields()">

							</select></td>
						</tr>
						<tr id="dsType">
							<th>数据源类型：</th>
							<td>
								<table id="ctl00_ContentPlaceHolder1_rdoDataSourceType"
									border="0">
									<tr>
										<td><input type="radio" name="field.dataSource" class="form_text"
											value="Codes"
											onclick="javascript:dataSourceSelect(this.value)"
											<ww:if test="field.dataSource=='Codes'">checked</ww:if> /> <label>
												代码表 </label></td>
										<td><input type="radio" name="field.dataSource" class="form_text"
											value="Other"
											<ww:if test="field.dataSource=='Other'">checked</ww:if>
											onclick="javascript:dataSourceSelect(this.value)" /> <label>
												其他 </label></td>
										<td><input type="radio" name="field.dataSource" class="form_text"
											value="Blank"
											<ww:if test="field==null||field.dataSource==null||field.dataSource=='Blank'">checked</ww:if>
											onclick="javascript:dataSourceSelect(this.value)" /> <label>
												无 </label></td>
									</tr>
								</table></td>
							<th><label id="dateDisName"> 无类型 </label></th>
							<td>
								<div id="divCodes" style="display: none">
									<select name="field.dataSourceType"
										onchange="getCodeValue(this.value,'-1')">
										<option value="-1"></option>
										<ww:iterator value="codes">
											<option value="<ww:property value="id"/>"
												<ww:if test="field.dataSourceType==id">selected</ww:if>>
												<ww:property value="codeName" />
											</option>
										</ww:iterator>
									</select> <select id="fieldDefaultValue" name="field.fieldDefaultValue">
										<option value="-1">-默认值-</option>
									</select>
								</div>
								<div id="divSqlValue" style="display: none">
									<input name="field.defaultValue" type="text" class="form_text"
										value="<ww:property value="field.defaultValue"/>"
										style="width: 90%;" />
								</div>
								<div id="divBlank" style="display: block"></div></td>
						</tr>
						<tr>
							<th>自动取值</th>
							<td><select name="field.valueUrl">
									<option value="无"
										<ww:if test="field.valueUrl!=null&&field.valueUrl.equals('无')">selected</ww:if>>
										无</option>
									<option value="操作人员姓名"
										<ww:if test="field.valueUrl!=null&&field.valueUrl.equals('操作人员姓名')">selected</ww:if>>
										操作人员姓名</option>
									<option value="操作人员部门"
										<ww:if test="field.valueUrl!=null&&field.valueUrl.equals('操作人员部门')">selected</ww:if>>
										操作人员部门</option>
									<option value="当前日期"
										<ww:if test="field.valueUrl!=null&&field.valueUrl.equals('当前日期')">selected</ww:if>>
										当前日期</option>
							</select></td>
							<th>显示顺序</th>
							<td><input name="field.sortSQ" type="text" class="form_text"
								value="<ww:property value="field.sortSQ"/>"
								id="ctl00_ContentPlaceHolder1_txtSortSQInGrid" datatype="n1-5"
								style="border: #A1BCA3 1px solid;" /></td>
						</tr>
						<tr>
							<th>查询条件</th>
							<td style="padding:0">
								<table id="ctl00_ContentPlaceHolder1_rdoQuery" border="0">
									<tr>
										<td><input id="ctl00_ContentPlaceHolder1_rdoQuery_0"
											type="radio" name="field.isQueryCondition"
											<ww:if test="field.isQueryCondition==1">checked</ww:if>
											value="1" /> <label> 是 </label></td>
										<td><input id="ctl00_ContentPlaceHolder1_rdoQuery_1"
											type="radio" name="field.isQueryCondition"
											<ww:if test="field==null||field.isQueryCondition==0">checked</ww:if>
											value="0" /> <label> 否 </label></td>
									</tr>
								</table></td>
							<th>列表显示</th>
							<td style="padding:0">
								<table id="ctl00_ContentPlaceHolder1_rdoInGrid" border="0">
									<tr>
										<td><input type="radio" name="field.dispInGrid"
											<ww:if test="field.dispInGrid==1">checked</ww:if> value="1" />
											<label for="ctl00_ContentPlaceHolder1_rdoInGrid_0"> 是
										</label></td>
										<td><input id="ctl00_ContentPlaceHolder1_rdoInGrid_1"
											type="radio" name="field.dispInGrid"
											<ww:if test="field==null||field.dispInGrid==0">checked</ww:if>
											value="0" /> <label
											for="ctl00_ContentPlaceHolder1_rdoInGrid_1"> 否 </label></td>
									</tr>
								</table></td>

						</tr>
						<tr>
							<th>排序字段</th>
							<td style="padding:0">
								<table id="" border="0">
									<tr>
										<td><input id=""
											<ww:if test="field.isSortField==1">checked</ww:if>
											type="radio" name="field.isSortField" value="1" /> <label
											for="ctl00_ContentPlaceHolder1_rdoSort_0"> 是 </label></td>
										<td><input id="ctl00_ContentPlaceHolder1_rdoSort_1"
											type="radio" name="field.isSortField" value="0"
											<ww:if test="field==null||field.isSortField==0">checked</ww:if> />
											<label for="ctl00_ContentPlaceHolder1_rdoSort_1"> 否 </label>
										</td>
									</tr>
								</table></td>
							<th>排序方向</th>
							<td style="padding:0">
								<table id="" border="0" style="width: 100%;">
									<tr>
										<td><input id="" type="radio" name="field.sortDirection"
											value="asc"
											<ww:if test="field==null||field.sortDirection=='asc'">checked</ww:if> />
											<label for="ctl00_ContentPlaceHolder1_rdoOrderDirection_0">
												升序 </label></td>
										<td><input id="" type="radio"
											<ww:if test="field.sortDirection=='desc'">checked</ww:if>
											name="field.sortDirection" value="desc" /> <label
											for="ctl00_ContentPlaceHolder1_rdoOrderDirection_1">
												降序 </label></td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<th>必填项</th>
							<td style="padding:0">
								<table id="ctl00_ContentPlaceHolder1_rdoMustFill" border="0">
									<tr>
										<td><input id="" type="radio" name="field.mustFill"
											<ww:if test="field.mustFill==1">checked</ww:if> value="1" />
											<label for="ctl00_ContentPlaceHolder1_rdoMustFill_0">
												是 </label></td>
										<td><input id="ctl00_ContentPlaceHolder1_rdoMustFill_1"
											type="radio" name="field.mustFill"
											<ww:if test="field==null||field.mustFill==0">checked</ww:if>
											value="0" /> <label
											for="ctl00_ContentPlaceHolder1_rdoMustFill_1"> 否 </label></td>
									</tr>
								</table></td>

							<th>单独成一行</th>
							<td style="padding:0">
								<table id="" border="0">
									<tr>
										<td><input id="ctl00_ContentPlaceHolder1_rdbMultiRows_1"
											type="radio" name="field.gridMultiRows"
											<ww:if test="field.gridMultiRows==0">checked</ww:if>
											value="0" /> <label
											for="ctl00_ContentPlaceHolder1_rdbMultiRows_1"> 是 </label></td>
										<td><input id="" type="radio" name="field.gridMultiRows"
											<ww:if test="field==null||field.gridMultiRows==1">checked</ww:if>
											value="1" /> <label
											for="ctl00_ContentPlaceHolder1_rdbMultiRows_0"> 否 </label></td>
									</tr>
								</table></td>
						</tr>
						<tr>
							<th>详细信息显示</th>
							<td colspan="3" style="padding:0">
								<table id="ctl00_ContentPlaceHolder1_rdoInDetail" border="0">
									<tr>
										<td><input type="radio" name="field.dispInDetail"
											<ww:if test="field==null||field.dispInDetail==1">checked</ww:if>
											value="1" checked="true" /> <label
											for="ctl00_ContentPlaceHolder1_rdoInDetail_0"> 是 </label></td>
										<td><input id="ctl00_ContentPlaceHolder1_rdoInDetail_1"
											type="radio" name="field.dispInDetail"
											<ww:if test="field.dispInDetail==0">checked</ww:if> value="0" />
											<label for="ctl00_ContentPlaceHolder1_rdoInDetail_1">
												否 </label></td>

									</tr>
								</table></td>
						</tr>
						<tr>
							<th>有效性检查正则表达式</th>
							<td colspan="3"><input id="validCheckFormula" class="form_text" 
								name="field.validCheckFormula" style="width: 400px" type="text"
								value="<ww:property value="field.validCheckFormula" />">
								<a id="btnSample" class="btn_01_mini"
								onclick="changeHidStat()" >举例<b></b></a></td>
						</tr>
						<tr id="trRxpSam" >
						    
							<th >正则表达式举例：</th><td colspan="3"> 全部都必须数字：^\d*$
								<br /> 电子邮件：\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*
								<br /> 电话号码：(\(\d{3}\)|\d{3}-)?\d{8} <br /> 身份证号码：^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$
								<br /> 邮政编码：\d{6} <br /> <br />
								测试正则表达式：&nbsp; <input type="text" id="testRep" name="testRep"/>
								<a class="btn_01_mini"  onclick="testPatrn_check()" >测试<b></b></a>
							</td>
							 
						</tr>
						<tr>
							<td align="center" colspan="4"><a href="#"
								class="btn_01" type="submit">保存<b></b></a>
								 <a
								href="#" class="btn_01" 
								onclick="parent.close_win('fieldWindow')" iconCls="icon-back">关闭<b></b></a>
							</td>
						</tr>
						<tr>
							<td align="left" colspan="4">
								注：创建名称建议以下划线（"_"）隔开字段名中的每个单词，如USER_NAME</td>
						</tr>
					</table>

				</form>
			</div>
		</div>
	</div>
</body>
</html>
