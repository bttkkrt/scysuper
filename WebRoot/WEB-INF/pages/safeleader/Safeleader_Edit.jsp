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
		
		if($("#basetype1").val()==""&&$("#basetype2").val()==""&&$("#basetype3").val()=="")
		{
		alert("请选择类别")
		return false
		}
		
			if(Validator.Validate(document.myform1,3)){
				document.myform1.action="safeleaderSave.action";
				document.myform1.submit();
			}
		}
		
		 //图片上传 高强 8月15  
        function uploadPhoto(obj){
        		window.open("${ctx}/jsp/safetysheet/safesheetUploadNone.action?safetysheet.linkid=${safeleader.linkid}&type="+obj);
        	}
		    function savepic(i,j){
        		window.location.href="${ctx}/jsp/safetysheet/safesheetDownFile.action?picName="+i+"&fileName="+j;
			}
			//删除附件
		function del(picName){
			$.ajax({
				url: "${ctx}/jsp/safetysheet/safesheetImgDel.action",
				data:{ picName : picName},
				type: "POST",
				success:function(){
					$("tr").remove("tr[id="+picName+"]");
				}
		    });
		    }
		    $().ready(function(){
		        changeType($("#leadgertype").val());
		    })
		    
		  function changeType(obj)  
		    {
		    for(var i=1;i<4;i++)
		    {
		    
		    if(i==obj)
		   $("#"+obj).css("display","block");
		    else
		   $("#"+i).css("display","none");
		    }
		    }
		    function download(type)
        {
        	location.href = "${ctx}/jsp/qysb/fileDownload.action?type=" + type;
        }
	</script>
	
</head>
<body>
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<form name="myform1" method="post" enctype="multipart/form-data">
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="safeleader.id" value="${safeleader.id}">
		<input type="hidden" name="safeleader.createTime" value="<fmt:formatDate type="both" value="${safeleader.createTime}" />">
		<input type="hidden" name="safeleader.updateTime" value="${safeleader.updateTime}">
		<input type="hidden" name="safeleader.createUserID" value="${safeleader.createUserID}">
		<input type="hidden" name="safeleader.updateUserID" value="${safeleader.updateUserID}">
		<input type="hidden" name="safeleader.deptId" value="${safeleader.deptId}">
		<input type="hidden" name="safeleader.delFlag" value="${safeleader.delFlag}">
		<input type="hidden" name="safeleader.linkid" value="${safeleader.linkid}">
		<input type="hidden" name="safeleader.basetype" id="basetype" value="${safeleader.basetype}">
		
		<input type="hidden" name="safeleader.deptName" value="${safeleader.deptName}">
		<input type="hidden" name="safeleader.comName"  value="${safeleader.comName}">
		<input type="hidden" name="safeleader.comId"  value="${safeleader.comId}">
		
			<input type="hidden" name="productchart.qylx" value="${safeleader.qylx}">
		<input type="hidden" name="safeleader.hyfl" value="${safeleader.hyfl}">
		<input type="hidden" name="safeleader.qygm" value="${safeleader.qygm}">
		<input type="hidden" name="safeleader.qyzclx" value="${safeleader.qyzclx}">
		
		<input type="hidden" name="safeleader.ifwhpqylx" value="${safeleader.ifwhpqylx}">
		<input type="hidden" name="safeleader.ifzywhqylx" value="${safeleader.ifzywhqylx}">
		<input type="hidden" name="safeleader.ifyhbzjyqy" value="${safeleader.ifyhbzjyqy}">
		
		<input type="hidden" name="safeleader.szc" value="${safeleader.szc}">
		<input type="hidden" name="safeleader.szcname" value="${safeleader.szcname}">
		<div class="submitdata">
			<table width="100%" border="0">
				<tr>
				<th width="15%">台账类型</th>
					<td width="35%"><cus:SelectOneTag property="safeleader.leadgertype"  defaultText='请选择' codeName="安全设施类别" value="${safeleader.leadgertype}" onchange="changeType(this.value)" dataType="Require" msg="此项为必选" /><font style="color:red">*</font></td>
						</tr>	<tr>
					<th width="15%">类别</th>
					<td width="35%" id="1" style="display:none"><cus:SelectOneTag property="safeleader.basetype1"   defaultText='请选择' codeName="预防事故设施" value="${safeleader.basetype}"  /><font style="color:red">*</font></td>
					<td width="35%" id="2" style="display:none"><cus:SelectOneTag property="safeleader.basetype2"   defaultText='请选择' codeName="控制事故设施台帐" value="${safeleader.basetype}"  /><font style="color:red">*</font></td>
					<td width="35%" id="3" style="display:none"><cus:SelectOneTag property="safeleader.basetype3"  defaultText='请选择' codeName="减少与消除事故影响设施台帐" value="${safeleader.basetype}"  /><font style="color:red">*</font></td>
				</tr>
				<tr>
					<th width="15%">文件上传</th>
					<td width="85%" colspan="3">
						<table width="100%">
							<tr>
								<td width="20%">
									<a style="color:blue;" href="javascript:uploadPhoto('aqsbdjtz')" title="点击上传文件">&nbsp;&nbsp;选择文件上传</a><br/>
									<a style="color:red;" href="javascript:download('aqss')" title="点击下载模板">&nbsp;&nbsp;下载模板</a>
								</td>
								<td width="70%">
									<div style="color:green;overflow:auto;height:160px;">
									   <div>
										  <table id="aqsbdjtz">
													<c:forEach var="item" items="${list}">
														<tr id='${item.id}' style="text-align: center">
															<td width="70%">
																${item.fileName}
															</td>
															<td width="30%">
																<a href="javascript:savepic('${item.id}','${item.fileName}');">下载</a>&nbsp;&nbsp;
																<a href="javascript:del('${item.id}');">删除</a>
															</td>
														</tr>
													</c:forEach>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
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
