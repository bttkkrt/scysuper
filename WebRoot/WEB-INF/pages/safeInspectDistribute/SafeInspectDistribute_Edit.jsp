<%@page language="java" pageEncoding="UTF-8"%>
<%@include file="/common/header.jsp"%>
<%@taglib prefix="cus" uri="/WEB-INF/tld/customized-tags.tld"%>
<%@taglib uri="http://java.fckeditor.net" prefix="FCK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title><s:if test="flag=='add'">新增</s:if><s:else>修改</s:else>记录</title>
	<%@include file="/common/jsLib.jsp"%>
	<script type="text/javascript" src="${ctx}/webResources/js/jquery.autocomplete.js"></script>
	<link rel="Stylesheet" href='<c:url value="/webResources/css/jquery.autocomplete.css"/>'/>
	<style type="text/css">
		#tableItem tr td{
			border:black 1px solid;
			text-align: center;
		}
		#tableItem tr th{
			border:black 1px solid;
			text-align: center;
			background-color: silver;
		}
	</style>
	<script>
	
		function validate(){
			if($('#formquarters').val()=="请选择"){
				$.messager.alert('警告','请选择检查岗位！');
				return false;
			}
			
			var $tr = $("#tableItem tr");
			var n = $tr.length;
			if(n==1){
				$.messager.alert('警告','请至少添加一项检查项！');
				return false;
			}
			
			var nodes = $('#personnel').tree('getChecked');
			if(nodes.length==0){
				$.messager.alert('警告','请选择检查人员！');
				return false;
			}
			return true;
		}
		function save(){
			var cycleFlag = $("#cycleFlag").val();
			if(cycleFlag==""){
				$("#cycleFlag").attr("dataType","Require").attr("msg","请选择检查周期");
			}
			
			/*
			if(startDate==""){ 
				$("#startDate").attr("dataType","Require").attr("msg","任务开始日期不能为空");
				$("#endDate").attr("dataType","Require").attr("msg","");
			}else{
				var endDate = $("#endDate").val();
				if(endDate==""){
					$("#endDate").attr("dataType","Require").attr("msg","任务结束日期不能为空");
				}
			}*/
			
			
			var nodes = $('#personnel').tree('getChecked');
			var personnels = '';
			for(var i=0; i<nodes.length; i++){
				if (personnels != '') 
					personnels += ',';
				personnels += nodes[i].id;
			}
			var companyFlag = $('#companyFlag').val();
			
			var quartersValue = $('#thisquarters').combotree('getValue');
			if($('#tempquarters').val()!=quartersValue){
			    	$('#formquarters').val(quartersValue);
			}
			
			if(Validator.Validate(document.myform1,3)&&validate()){
				document.myform1.action="safeInspectDistributeSave.action?safeInspectDistribute.personnel="+personnels+"&safeInspectDistribute.companyFlag="+companyFlag;
				document.myform1.submit();
			}
		}
		
		function addItem(){
			//var item = $("#inspectItem").combobox('getValue');
			var item = $("#inspectItem").val();
			var requirement = $("#inspectRequirement").val()
			if(item == ""){
				$.messager.alert('提示','巡检项不能为空！');
				return false;
			}
			if(requirement == ""){
				$.messager.alert('提示','巡检要求不能为空！');
				return false;
			}
			var valFlag = false;
			$("#tableItem tr").each(function(){
	     	 	var innerItem = $(this).find("td").eq(0).find("input").val();
	     	 	if(innerItem == item) 
	     	 		valFlag = true;
	     	});
			if(valFlag){
				$.messager.alert('提示','巡检项："'+item+'"已存在，不能重复添加！');
				return false;
			}
			var $tr = $("#tableItem tr");
			var n = $tr.length;
			var trId = "tr"+n;
			var tr = "<tr id='"+trId+"'>" +
					"<td  name='inspectItem.item' ><input name='insptItem' type='text' readonly style='text-align:center;border:0px;' value='"+item+"'/></td>" +
					"<td name='inspectItem.requirement'><input name='insptRequirement' type='text' readonly style='width: 400px;text-align:center;border:0px;' value='"+requirement+"'/></td>" +
					"<td><a href='#' onclick='delItem("+trId+")'>删除</a></td>" +
				"</tr>";
			$("#tableItem").append(tr);
		}
		function delItem(trId){
			 $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
						$(trId).remove();
			        }
			    });
			 
		}
		function delOldItem(trId,distId){
			 $.messager.confirm("删除","确定要删除吗?",function(result){
			        if(result){
		                $.ajax({
		                	url : "${ctx}/jsp/distributeItem/distributeItemDel.action",
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	ids : distId
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','删除时出错！');
		                    },
		                    success: function(data){
		                        if(data.result){
		                        	$.messager.alert('提示','删除成功！');
		                        	$(trId).remove();
		                        }else{
		                        	$.messager.alert('错误','删除时出错！');
		                        }
		                    }
		                });
			        }
			    });
			
		}
		
		/**
		function onInspectTypeChange(inspectType){
			var companyFlag = $('#companyFlag').val();
			var inspectItemItem = $('#inspectItem').combobox({
				valueField:'item',
				textField:'item',
				editable:false,
				url:'${ctx}/jsp/inspectItem/jsonList.action?inspectItem.inspectType='+inspectType+'&inspectItem.companyFlag='+companyFlag,  
				onChange:function(newValue, oldValue){
					$.ajax({
		                	url : '${ctx}/jsp/inspectItem/jsonList.action?inspectItem.inspectType='+inspectType+'&inspectItem.companyFlag='+companyFlag,
		                	type: 'post',
		                    dataType: 'json',
		                    async : false,
		                    data:{ 
		                    	'inspectItem.item':newValue
		                    },
		                    error: function(){
		                    	$.messager.alert('错误','获取巡检要求时出错！');
		                    },
		                    success: function(data){
		                       $("#inspectRequirement").val(data[0]['requirement']);
		                    }
		                });
				}
			});
		}
		*/
		
		function getDeptAndUser(deptCode,personnels){
             $('#personnel').tree({   
                 url: '${ctx}/jsp/safeInspectDistribute/findChildDeptAndUser.action?deptCode='+deptCode+'&personnels='+personnels, 
                 checkbox: true,  
                 onlyLeafCheck:true,
			     panelHeight:'auto',
                 onBeforeExpand:function(node,param){
                     //$('#personnel').tree('options').url = "${ctx}/member/admin/dept/findChildDept.action?selDept=" + node.id;
                 },
              	 onLoadSuccess:function(data){
              		
              	 }
             });   
	    }
		
		/*
		 * 新增时初始化
		 * @memberOf {TypeName} 
		 */
		function addInit(){
			/**
			var inspectTypeSelect = $("#inspectTypeTd").children("select");
			inspectTypeSelect.change(function(){
				onInspectTypeChange($(this).val());
			});
			onInspectTypeChange('1');//默认为日常安全检查
			*/
			$('#thisquarters').combotree({    
			    url: '${ctx}/jsp/safeInspectDistribute/findChildDept.action?',    
			    required: true,
			    panelHeight:'auto',
			    onClick:function(node){
                 	getDeptAndUser(node.id,"");
           		},  
				onBeforeExpand:function(node) {
				      $('#thisquarters').combotree("tree").tree("options").url = "${ctx}/jsp/safeInspectDistribute/findChildDept.action?selDept=" + node.id;
				},
				onSelect:function(node){
	    			$("#personnelFlag").css("display","");
				}
			});
		}
		 /*
		  * 更新时初始化
		  */
		 function updateInit(){
			//初始化检查类型
			var tempit = '${safeInspectDistribute.inspectType}';
			//onInspectTypeChange(tempit);
			//初始化检查岗位
			var quarters = '${safeInspectDistribute.quarters}';
			$.ajax({
			    url: '${ctx}/jsp/safeInspectDistribute/findDeptnameByDeptcode.action',
			    type: 'post',
			    dataType: 'json',
			    async : false,
			    data:{        
			    	"deptCode" : quarters
			    },
			    error: function(){
			        $.messager.alert('提示','获取一维代码错误！');
			    },
			    success: function(data){
			    	$('#tempquarters').val(data.deptName);
		        	$('#thisquarters').combotree('setValue',data.deptName);
		    	}
			});
			var cycleFlag = '${safeInspectDistribute.cycleFlag}';
			//初始化检查周期
			var cycleValue = '${safeInspectDistribute.cycleValue}';
			if("any" == cycleValue){
				$("#anyValue").attr("checked","checked");
			}else if("1" == cycleFlag){
	         	var cycleValueArray = cycleValue.split(",");
				for(i=0;i<cycleValueArray.length;i++){
					$("#weekFlag").find("input").each(function(d,e){
							var a = $.trim(cycleValueArray[i]);
							var b = $.trim($(this).val());
							if(a==b){
								$(this).attr('checked','true');
							}
					});
				}
			}else if("2" == cycleFlag){
	         	var cycleValueArray = cycleValue.split(",");
				for(i=0;i<cycleValueArray.length;i++){
					$("#monthFlag").find("input").each(function(d,e){
							var a = $.trim(cycleValueArray[i]);
							var b = $.trim($(this).val());
							if(a==b){
								$(this).attr('checked','true');
							}
					});
				}
			}
			showValue();
			//初始化巡检人员 
			$("#personnelFlag").css("display","");
			var personnels = '${safeInspectDistribute.personnel}';
			getDeptAndUser(quarters,personnels);
		}
		
		var options = {
            minChars: 0, //最少输入多少字符开始查询
            max: 500, //最多显示多少
            width: 180, //宽度
            matchContains: true,
            matchSubset:false,
            extraParams:{'inspectItem':function(){return $("#inspectItem").val();},'inspecttype':function(){return $("#inspectType").val();}}, //传递参数
            dataType: 'json',   //返回JSON格式
            parse: function(data) { 
           		var rows = [];   
         		for(var i=0; i<data.length; i++){ 
            		rows[rows.length] = { 
            		data:data[i], 
           			value:data[i].item,    
           	 		result:data[i].item 
	             };    
	            }   
      		 return rows;   
        },   
             formatItem: function(row, i, max) {//显示出来的项格式
                return row.item;
             },
             formatMatch: function(row){return row.item;}, 
             formatResult: function(row){return row.item; } 
        };
		
		$(function(){
			init();	
            $("#inspectItem").autocomplete("${ctx}/jsp/safeInspectDistribute/getSuggestion.action", options);
             $("#inspectItem").result(function(event, data, formatted) {//data 选中的行json对象. formatted是formatMatch返回的值.
            	$("#inspectRequirement").val(data.requirement);
            });
		});
		
		function init(){
			addInit();
			var flag = '${flag}';
			if(flag!='add'){
				updateInit();
			}
			var taskStatus = '${safeInspectDistribute.taskStatus}';
			if(taskStatus=='0'){
				$("#statusUp").attr('checked','checked');
				$("#statusDown").attr('checked','');
			}else{
				$("#statusUp").attr('checked','');
				$("#statusDown").attr('checked','checked');
			}
		}
		
		function clearNoNum(event,obj,type){ 
        //响应鼠标事件，允许左右方向键移动 
        event = window.event||event; 
        if(event.keyCode == 37 | event.keyCode == 39){ 
            return; 
        } 
        //先把非数字的都替换掉，除了数字和. 
        obj.value = obj.value.replace(/[^\d]/g,"");
        if(obj.value.substring(0,1) == "0")
        {
        	obj.value = obj.value.substring(1,obj.value.length);
        }
        
         var aqscData = document.getElementById("12_"+type);
		 		aqscData.value="0";
		 	for(var i=1;i<12;i++){//aqscData01.data_10
		 		var myid = i+"_"+type;
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
    function showValue(){
    	var anyValue = $("#anyValue").attr("checked");
    	var cycleFlag = $("#cycleFlag").val();
    	if(true == anyValue){
    		$("#weekFlag").css("display","none");
	    	$("#monthFlag").css("display","none");
    	}else{
	    	if("1" == cycleFlag){
	    		$("#weekFlag").css("display","");
	    		$("#monthFlag").css("display","none");
	    	}else if("2" == cycleFlag){
	    		$("#weekFlag").css("display","none");
	    		$("#monthFlag").css("display","");
	    	}else{
	    		$("#weekFlag").css("display","none");
	    		$("#monthFlag").css("display","none");
	    	}
    	}
    }
	</script>
	
</head>
<body style="overflow: auto;">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<div style="overflow-y: auto;width:972px;height:699px;" >
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="safeInspectDistribute.id" value="${safeInspectDistribute.id}">
		<input type="hidden" name="safeInspectDistribute.createTime" value="<fmt:formatDate type="both" value="${safeInspectDistribute.createTime}" />">
		<input type="hidden" name="safeInspectDistribute.updateTime" value="${safeInspectDistribute.updateTime}">
		<input type="hidden" name="safeInspectDistribute.createUserID" value="${safeInspectDistribute.createUserID}">
		<input type="hidden" name="safeInspectDistribute.updateUserID" value="${safeInspectDistribute.updateUserID}">
		<input type="hidden" name="safeInspectDistribute.deptId" value="${safeInspectDistribute.deptId}">
		<input type="hidden" name="safeInspectDistribute.delFlag" value="${safeInspectDistribute.delFlag}">
		
		<input type="hidden" id="companyFlag" name="companyFlag" value="${companyFlag}">
		<input type="hidden" id="formquarters" name="safeInspectDistribute.quarters" value="${safeInspectDistribute.quarters}">
		<input type="hidden" id="tempquarters" ">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr><th width="10%" colspan="4"  style="text-align: center;font-size: 15px;color: #2585D8;">安全检查任务基本信息</th></tr>	
				<tr>
					<th width="10%"><font style="color: red;">*</font>检查标题</th>
					<td width="35%"><input name="safeInspectDistribute.title" value="${safeInspectDistribute.title}" type="text" dataType="Require" msg="此项为必填" maxlength="25"   style="width:270px;" ></td>
					<th width="10%"><font style="color: red;">*</font>检查类型</th>
					<td width="35%" id="inspectTypeTd">
							<cus:SelectOneTag name="inspectType" property="safeInspectDistribute.inspectType"  defaultText='请选择' codeName="安全检测类型" value="1" dataType="Require" msg="此项为必选"   style="width:270px;"  /><!-- value="1":默认为日常安全检查 -->
					</td>
				</tr>
				<tr>
					<th width="10%">
						任务起止日期
					</th>
					<td width="35%">
						<input id="startDate" name="safeInspectDistribute.startDate"  
						value="<fmt:formatDate type='date' value='${safeInspectDistribute.startDate}' />" 
						type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
						-
						<input  id="endDate" name="safeInspectDistribute.endDate" 
						value="<fmt:formatDate type='date' value='${safeInspectDistribute.endDate}' />" 
						type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
					</td>
					<th width="10%"><font style="color: red;">*</font>检查次数</th>
					<td width="35%" colspan="3" >
						<input name="safeInspectDistribute.inspectNum" value="${safeInspectDistribute.inspectNum}" onKeyUp="clearNoNum(event,this)"  dataType="Require" msg="此项为必填" type="text" maxlength="2" style="width:270px;" >（请输入数字）
					</td>
				</tr>
				<tr>
					<th width="10%"><font style="color: red;">*</font>检查周期</th>
					<td width="35%">
						<select id="cycleFlag"  name="safeInspectDistribute.cycleFlag" onchange="showValue();">
							<option value="">请选择</option>
							<option value="2" <c:if test="${safeInspectDistribute.cycleFlag==2 }">selected="true"</c:if> >月</option>
							<option value="1" <c:if test="${safeInspectDistribute.cycleFlag==1 }">selected="true"</c:if> >周</option>
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input id="anyValue" name="anyValue" value="any" type="checkbox"  onchange="showValue();">&nbsp;任意（周期内任意时间上报）
					</td>
					<th width="10%" name="weekFlag">检查日期</th>
					<td width="40%" id="cycleValue"  name="weekFlag">
						<div id="weekFlag" style="display: none;">
							<input name="safeInspectDistribute.cycleValue" value="2" type="checkbox" >&nbsp;周一
							<input name="safeInspectDistribute.cycleValue" value="3" type="checkbox" >&nbsp;周二
							<input name="safeInspectDistribute.cycleValue" value="4" type="checkbox" >&nbsp;周三
							<input name="safeInspectDistribute.cycleValue" value="5" type="checkbox" >&nbsp;周四
							<input name="safeInspectDistribute.cycleValue" value="6" type="checkbox" >&nbsp;周五
							<input name="safeInspectDistribute.cycleValue" value="7" type="checkbox" >&nbsp;周六
							<input name="safeInspectDistribute.cycleValue" value="1" type="checkbox" >&nbsp;周日
						</div>
						<div id="monthFlag" style="display: none;">
							<input name="monthValue" value="01" type="checkbox" >&nbsp;1&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="02" type="checkbox" >&nbsp;2&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="03" type="checkbox" >&nbsp;3&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="04" type="checkbox" >&nbsp;4&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="05" type="checkbox" >&nbsp;5&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="06" type="checkbox" >&nbsp;6&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="07" type="checkbox" >&nbsp;7&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="08" type="checkbox" >&nbsp;8&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="09" type="checkbox" >&nbsp;9&nbsp;&nbsp;&nbsp;
							<input name="monthValue" value="10" type="checkbox" >&nbsp;10&nbsp;<br/>
							<input name="monthValue" value="11" type="checkbox" >&nbsp;11&nbsp;
							<input name="monthValue" value="12" type="checkbox" >&nbsp;12&nbsp;
							<input name="monthValue" value="13" type="checkbox" >&nbsp;13&nbsp;
							<input name="monthValue" value="14" type="checkbox" >&nbsp;14&nbsp;
							<input name="monthValue" value="15" type="checkbox" >&nbsp;15&nbsp;
							<input name="monthValue" value="16" type="checkbox" >&nbsp;16&nbsp;
							<input name="monthValue" value="17" type="checkbox" >&nbsp;17&nbsp;
							<input name="monthValue" value="18" type="checkbox" >&nbsp;18&nbsp;
							<input name="monthValue" value="19" type="checkbox" >&nbsp;19&nbsp;
							<input name="monthValue" value="20" type="checkbox" >&nbsp;20&nbsp;<br/>
							<input name="monthValue" value="21" type="checkbox" >&nbsp;21&nbsp;
							<input name="monthValue" value="22" type="checkbox" >&nbsp;22&nbsp;
							<input name="monthValue" value="23" type="checkbox" >&nbsp;23&nbsp;
							<input name="monthValue" value="24" type="checkbox" >&nbsp;24&nbsp;
							<input name="monthValue" value="25" type="checkbox" >&nbsp;25&nbsp;
							<input name="monthValue" value="26" type="checkbox" >&nbsp;26&nbsp;
							<input name="monthValue" value="27" type="checkbox" >&nbsp;27&nbsp;
							<input name="monthValue" value="28" type="checkbox" >&nbsp;28&nbsp;
							<input name="monthValue" value="29" type="checkbox" >&nbsp;29&nbsp;
							<input name="monthValue" value="30" type="checkbox" >&nbsp;30&nbsp;<br/>
							<input name="monthValue" value="31" type="checkbox" >&nbsp;31
						</div>
					</td>
				</tr>
				
				<tr>
					<th width="10%"><font style="color: red;">*</font>检查岗位</th>
					<td width="35%" id="quarters" >
						<input id="thisquarters" name="quarters" value="请选择" style="width:270px;height: 180px;overflow: auto;">
					</td>
					<th width="10%"><font style="color: red;">*</font>任务状态</th>
					<td width="35%">
						<input id="statusDown" name="safeInspectDistribute.taskStatus" value="4" type="radio"  >不启用&nbsp;&nbsp;<input id="statusUp" name="safeInspectDistribute.taskStatus" value="0" type="radio" >启用
					</td>
				</tr>
				<tr  id="personnelFlag"  style="display: none;">
					<th width="10%"><font style="color: red;">*</font>检查人员</th>
					<td width="35%" colspan="3" >
						<div style="width:200px;height: 150;overflow: auto;">
							<ul id="personnel" name="safeInspectDistribute.personnel" style="height: auto;overflow: auto;"></ul>
						</div>
						<!-- 
						<input id="personnel1" value="请选择岗位">
							<input name="safeInspectDistribute.personnel" value="${safeInspectDistribute.personnel}" type="text" dataType="Require" msg="此项为必填" maxlength="10"><font color="red">*</font> 
						-->
					</td>
				</tr>
				<tr><th width="10%" colspan="4"  style="text-align: center;font-size: 15px;color: #2585D8;">安全检查项</th></tr>
				<tr>
					<th width="10%">检查项</th>
					<td width="35%" colspan="3" >
						<input id="inspectItem" name="inspectItem" style="width:270px;" >
					</td>
				</tr>
				<tr>
					<th width="10%">检查要求</th>
					<td width="35%" colspan="3" >
						<textarea id="inspectRequirement" readonly rows="3" cols="100" name="inspectRequirement"  maxlength="500"></textarea>
						&nbsp;<a href="#" class="easyui-linkbutton" onclick="addItem()" iconCls="icon-save">添加一项</a>
					</td>
				</tr>
				<tr><td colspan="4" >&nbsp;</td></tr>
				<tr>
					<td colspan="4"  style="text-align: center;">
						<table id="tableItem" style="width: 650px; border: black 1px solid;margin:auto;">
							<tr >
								<th style="width: 150px;">检查项</th>
								<th style="width: 420px;">检查要求</th>
								<th style="width: 80px;">操作</th>
							</tr>
							<s:if test="flag!='add'">
								<c:forEach  var="dist" items="${distList}"  varStatus="status">
									<tr id="tr${status.index+'1024' }">
										<td>
											<input type='text' readonly style='text-align:center;border:0px;' value='${dist.item}'/>
										</td>
										<td>
											<input type='text' readonly style='width: 400px;text-align:center;border:0px;' value='${dist.requirement}'/>
										</td>
										<td><a href='#' onclick="delOldItem(tr${status.index+'1024' },'${dist.id}')">删除</a></td>
									</tr>
								</c:forEach>
							</s:if>
						</table>
					</td>
				</tr>
				<tr><td colspan="4" >&nbsp;</td></tr>
				<tr>
					<td colspan="4" height="100px" style="text-align: center;">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">提交</a>&nbsp;
						</s:if>
						<s:else>
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">更新</a>&nbsp;
						</s:else>						
						<a href="#" class="easyui-linkbutton" iconCls="icon-undo" onclick="document.myform1.reset();init();">重置</a>&nbsp;
						<a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="parent.close_win();">关闭</a>
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</form>
	</div>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
