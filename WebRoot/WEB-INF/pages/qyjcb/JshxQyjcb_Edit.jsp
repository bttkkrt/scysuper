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
    border:1px solid #AABAA9;   
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
		function save(){
			if(Validator.Validate(document.myform1,3)){
				
				document.myform1.action="jshxQyjcbSave.action";
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
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="jshxQyjcb.id" value="${jshxQyjcb.id}">
		<input type="hidden" name="jshxQyjcb.createTime" value="<fmt:formatDate type="both" value="${jshxQyjcb.createTime}" />">
		<input type="hidden" name="jshxQyjcb.updateTime" value="${jshxQyjcb.updateTime}">
		<input type="hidden" name="jshxQyjcb.createUserID" value="${jshxQyjcb.createUserID}">
		<input type="hidden" name="jshxQyjcb.updateUserID" value="${jshxQyjcb.updateUserID}">
		<input type="hidden" name="jshxQyjcb.deptId" value="${jshxQyjcb.deptId}">
		<input type="hidden" name="jshxQyjcb.delFlag" value="${jshxQyjcb.delFlag}">
		<input type="hidden" name="jshxQyjcb.tbsj" value="<fmt:formatDate type='both' value='${jshxQyjcb.tbsj}' />" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
		
		<input type="hidden" name="jshxQyjcb.szzname" value="${jshxQyjcb.szzname}">
		<input type="hidden" name="jshxQyjcb.qymc" value="${jshxQyjcb.qymc}">
		<input type="hidden" name="jshxQyjcb.szzid" value="${jshxQyjcb.szzid}">
		<input type="hidden" name="jshxQyjcb.qyid" value="${jshxQyjcb.qyid}">
		<input type="hidden" name="jshxQyjcb.qylx" value="${jshxQyjcb.qylx}">
		<input type="hidden" name="jshxQyjcb.hyfl" value="${jshxQyjcb.hyfl}">
		<input type="hidden" name="jshxQyjcb.qygm" value="${jshxQyjcb.qygm}">
		<input type="hidden" name="jshxQyjcb.qyzclx" value="${jshxQyjcb.qyzclx}">
		<input type="hidden" name="jshxQyjcb.ifwhpqylx" value="${jshxQyjcb.ifwhpqylx}">
		<input type="hidden" name="jshxQyjcb.ifzywhqylx" value="${jshxQyjcb.ifzywhqylx}">
		<input type="hidden" name="jshxQyjcb.ifyhbzjyqy" value="${jshxQyjcb.ifyhbzjyqy}">
		<input type="hidden" name="jshxQyjcb.szc" value="${jshxQyjcb.szc}">
		<input type="hidden" name="jshxQyjcb.szcname" value="${jshxQyjcb.szcname}">
		<div class="submitdata">
			<table width="100%" border="1">
				<tr>
					<th width="15%">填报月份</th>
					<td width="35%">
						<input class="inputText4" name="jshxQyjcb.jshxMonth" value="${jshxQyjcb.jshxMonth}" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" type="text" maxlength="255">
					</td>
					<th width="15%">企业负责人</th>
					<td width="35%"><input class="inputText4"  name="jshxQyjcb.manager" value="${jshxQyjcb.manager}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">填表人</th>
					<td width="35%"><input class="inputText4"  name="jshxQyjcb.tbr" value="${jshxQyjcb.tbr}" type="text" maxlength="255"></td>
					<th width="15%">联系电话</th>
					<td width="35%"><input class="inputText4"  name="jshxQyjcb.telephone" value="${jshxQyjcb.telephone}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" style="border:1px solid #AABAA9;">
								<tr>
									<th style="text-align:center;" rowspan="2">隐患类别</th>
									<th style="text-align:center;" rowspan="2">上月结转待整改隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;" rowspan="2">本月排查隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;" rowspan="2">本月整改隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;"rowspan="2">本月结转待整改隐患</th>
									<th style="text-align:center;">&nbsp;</th>
									<th style="text-align:center;" rowspan="2">本月投入整改资金</th>
									<th style="text-align:center;"></th>
									<th style="text-align:center;" rowspan="2">本年累计排查隐患</th>
									<th style="text-align:center;"></th>
									<th style="text-align:center;" rowspan="2">本年累计整改隐患</th>
									<th style="text-align:center;"></th>
									<th style="text-align:center;" rowspan="2">本年累计已投入整改资金</th>
									<th style="text-align:center;"></th>
								</tr>
								<tr>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
									<th style="text-align:center;" style="width:50px;">其中:重大隐患</th>
								</tr>
							<c:forEach items="${datas}" var="item" varStatus="status">
							      <tr> 
							      	<th style="width:170px;">${item}</th>
							      		<c:forEach   items="${qyjcbDatas}" var="data" varStatus="statu"> 
							     		<td style="width:5px;">
											<input type="hidden" name="qyjcbData.id" value="${data.id}">
											<input type="hidden" name="qyjcbData.sort" value="${data.sort}">
											<input type="hidden" name="qyjcbData.createTime" value="<fmt:formatDate type="both" value="${data.createTime}" />">
											<c:if test="${status.index eq 0 }">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}" onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;"
								     				 	 type="text" value="${data.data_1}">
								     			</c:if>
								     			<c:if test="${status.index eq 1}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_2}">
								     			</c:if>
								     			<c:if test="${status.index eq 2}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_3}">
								     			</c:if>
								     			<c:if test="${status.index eq 3}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_4}">
								     			</c:if>
								     			<c:if test="${status.index eq 4}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_5}">
								     			</c:if>
								     			<c:if test="${status.index eq 5}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_6}">
								     			</c:if>
								     			<c:if test="${status.index eq 6}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_7}">
								     			</c:if>
								     			<c:if test="${status.index eq 7}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_8}">
								     			</c:if>
								     			<c:if test="${status.index eq 8}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_9}">
								     			</c:if>
								     			<c:if test="${status.index eq 9}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_10}">
								     			</c:if>
								     			<c:if test="${status.index eq 10}">
								     				 <input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  onKeyUp="clearNoNum(event,this,${statu.index+1})" class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_11}">
								     			</c:if>
								     			<c:if test="${status.index eq 11}">
								     				<input name="qyjcbData.data_${status.index+1}" id="${status.index+1}_${statu.index+1}"  readonly class="inputText4" style="width:40px;" 
								     				  type="text" value="${data.data_12}">
								     			</c:if>	
										</td>
							      	</c:forEach> 
							      <tr> 
							</c:forEach>
							<tr>
								<td colspan="17">
									本月班组岗位自查<input name="jshxQyjcb.count_1" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_1}">次、
									车间自查<input  name="jshxQyjcb.count_2" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_2}">次、
									企业负责人检查<input  name="jshxQyjcb.count_3" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_3}">次；
									专家检查<input  name="jshxQyjcb.count_4" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_4}">次；
									安监部门检查<input  name="jshxQyjcb.count_5" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_5}">次。
									本月参加安全培训<input  name="jshxQyjcb.count_6" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_6}">人，
									其中法定培训<input  name="jshxQyjcb.count_7" class="inputText4" style="width:30px;" type="text" value="${jshxQyjcb.count_7}">人。
								</td>
							</tr>
							<tr>
								<td colspan="17">
									说明：本表统计包括企业自查、专家检查和部门检查发现的需要整改的安全生产隐患与问题。统计单位：隐患为“项”、资金为“万元”。
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
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
