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
			
			  	if($('#pass').val()==1)
		{
		     if($('#ifwhpqylx').val()==1)
			{
				var ches = document.getElementsByName("company.whpqylx");
				var ischecked = false;
				for(var i=0;i<ches.length;i++)
				{
					if(ches[i].checked)
					{
						ischecked = true;
						break;
					}
				}
				if(!ischecked)
				{
					$.messager.alert('提示',"危化品企业类型至少选择一项");
					return false;
				}
				}
		  if($('#ifzywhqylx').val()==1)
	         {
				var ches = document.getElementsByName("company.zywhqylx");
				var ischecked = false;
				for(var i=0;i<ches.length;i++)
				{
					if(ches[i].checked)
					{
						ischecked = true;
						break;
					}
				}
				if(!ischecked)
				{
					$.messager.alert('提示',"职业危害企业类型至少选择一项");
					return false;
				}
				}
				}
				document.myform1.action="companySaveMySelf.action";
				document.myform1.submit();
			}
		}
		
		$(document).ready(function(){
		if($('#pass').val()==1)
		{
		  if($('#ifwhpqylx').val()==1)
		  {
		      document.getElementById("file01").checked=true;
		        document.getElementById("file00").checked=false;
		   $('#ifwhpqylxid').css("display","block"); 
		  }else
		  {
		    document.getElementById("file01").checked=false;
		        document.getElementById("file00").checked=true;
		    $('#ifwhpqylxid').css("display","none");  
		  }
		
		  if($('#ifzywhqylx').val()==1)
		  {
		     document.getElementById("file11").checked=true;
		        document.getElementById("file10").checked=false;
		   $('#ifzywhqylxid').css("display","block"); 
		  }else
		  {
		      document.getElementById("file11").checked=false;
		        document.getElementById("file10").checked=true;
		    $('#ifzywhqylxid').css("display","none");  
		  }
		}
		})
		
		$(function(){       
            $(".filetype").change(function(){  
                 var val = $("input[name='file']:checked").val(); //获得选中的radio的值             
                     if(val=='1'){    
                 $('#ifwhpqylx').val(1);  
                    $('#ifwhpqylxid').css("display","block"); 
                       }else{   
                        $('#ifwhpqylx').val(0);   
                    $('#ifwhpqylxid').css("display","none");             
                      }                    });          }); 
          $(function(){       
            $(".filetype1").change(function(){  
                 var val = $("input[name='file1']:checked").val(); //获得选中的radio的值             
                     if(val=='1'){    
                 $('#ifzywhqylx').val(1);    
                    $('#ifzywhqylxid').css("display","block"); 
                       }else{   
                        $('#ifzywhqylx').val(0);  
                    $('#ifzywhqylxid').css("display","none");             
                      }                    });          }); 
	</script>
	
</head>
<body style="overflow-y: scroll">
<%@include file="/WEB-INF/template/content_title.jsp"%>
	<s:form name="myform1" method="post" enctype="multipart/form-data" theme='simple'>
		<s:token />
		<input type="hidden" name="flag" value="${flag}">
		<input type="hidden" name="company.id" value="${company.id}">
		<input type="hidden" name="company.createTime" value="<fmt:formatDate type="both" value="${company.createTime}" />">
		<input type="hidden" name="company.updateTime" value="${company.updateTime}">
		<input type="hidden" name="company.createUserID" value="${company.createUserID}">
		<input type="hidden" name="company.updateUserID" value="${company.updateUserID}">
		<input type="hidden" name="company.deptId" value="${company.deptId}">
		<input type="hidden" name="company.delFlag" value="${company.delFlag}">
		
			<input type="hidden" name="company.loginname" value="${company.loginname}">
		<input type="hidden" name="company.loginword" value="${company.loginword}">
		<input type="hidden" name="company.companyname"  value="${company.companyname}" >
		<input type="hidden" name="company.fddbr" value="${company.fddbr}">
		<input type="hidden" name="company.dwdz" value="${company.dwdz}">
			<input type="hidden" name="company.dwdz1" value="${company.dwdz1}">
				<input type="hidden" name="company.dwdz2" value="${company.dwdz2}">
		<input type="hidden" name="company.zzjgdm" value="${company.zzjgdm}">
		<input type="hidden" name="company.gszch" value="${company.gszch}">
		<input type="hidden" name="company.qylx" value="${company.qylx}">
		
		<input type="hidden" name="company.hyfl" value="${company.hyfl}">
		<input type="hidden" name="company.lxr" value="${company.lxr}">
		<input type="hidden" name="company.lxfs" value="${company.lxfs}">
			<input type="hidden" name="company.yzm" value="${company.yzm}">
		<input type="hidden" name="company.qyyx" value="${company.qyyx}">
		<input type="hidden" name="company.pass" id="pass" value="${company.pass}">
		<input type="hidden" name="company.ifwhpqylx" id="ifwhpqylx" value="${company.ifwhpqylx}">
		<input type="hidden" name="company.ifzywhqylx" id="ifzywhqylx" value="${company.ifzywhqylx}">
		
		
		
		
		
		<div class="submitdata">
			<table width="100%" border="0">
			<tr >
				   <th width="15%" colspan=4 style="text-align:center;color:red">注册信息</th>
				</tr>
					<tr>
					<th width="15%">单位名称</th>
					<td width="85%" colspan="3">${company.companyname}</td>
				</tr>
				<tr>
					<th width="15%">单位地址</th>
					<td width="85%" colspan="3">${company.dwdz}</td>	
				</tr>
				<tr>
					<th width="15%">法定代表人</th>
					<td width="35%">${company.fddbr}</td>
					<th width="15%">组织机构代码</th>
					<td width="35%">${company.zzjgdm}</td>
				</tr>
				<tr>
					<th width="15%">工商注册号</th>
					<td width="35%">${company.gszch}</td>
					<th width="15%">企业类型</th>
					<td width="35%"><cus:hxlabel codeName="企业类型" itemValue="${company.qylx}" /></td>
				</tr>
				<tr>
					<th width="15%">行业分类</th>
					<td width="35%"><cus:hxlabel codeName="行业分类" itemValue="${company.hyfl}" /></td>
					<th width="15%">安全管理员</th>
					<td width="35%">${company.lxr}</td>
				</tr>
				<tr>
					<th width="15%">联系方式</th>
					<td width="35%">${company.lxfs}</td>
					<th width="15%">企业邮箱</th>
					<td width="35%">${company.qyyx}</td>
					<!--
					<th width="15%">验证码</th>
					<td width="35%">${company.yzm}</td> -->
				</tr>
				<tr>
					<th width="15%" style="color:red">注册信息审核状态</th>
					<td width="35%" style="color:red"><cus:hxlabel codeName="审核状态" itemValue="${company.pass}" /></td>
				</tr>
					<s:if test="company.pass==2" >
					<tr>
				<th width="15%">备注</th>
				<td width="65%" colspan=3>
								<textarea id="company.remark" disabled
									name="company.remark" rows="5"
									style="width: 80%">${company.remark}</textarea>
							</td>
				
				</tr>
					
					
					
					</s:if>
				
				
				<s:if test="company.pass==1" >
				<tr >
				   <th width="15%" colspan=4 style="text-align:center;color:red">基本信息填报</th>
				</tr>
				<tr>
					<th width="15%">企业规模</th>
					<td width="35%"><cus:SelectOneTag property="company.qygm" defaultText='请选择' codeName="企业规模" value="${company.qygm}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">企业注册类型</th>
					<td width="35%"><cus:SelectOneTag property="company.qyzclx" defaultText='请选择' codeName="企业注册类型" value="${company.qyzclx}" dataType="Require" msg="此项为必选" /><font color="red">*</font></td>
					<th width="15%">注册资金（万元）</th>
					<td width="35%"><input name="company.zczj" value="${company.zczj}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">是否危化品企业类型</th>
					<td width="15%" colspan=3 style="width:100px">
					<div width="300px">
					 <input type="radio" name="file"  id="file00" value="0" class="filetype">否
                     <input type="radio" name="file" id="file01" checked="checked"  value="1"  class="filetype">是 
					<div id="ifwhpqylxid" style="float:right" ><cus:hxcheckbox property="company.whpqylx" codeName="危化品企业类型" value="${company.whpqylx}" /><font style="color:red">请至少选择一项</font></div>
					</div></td>
				</tr>
				<tr>
			    	<th width="15%">是否职业危害企业类型</th>
					<td width="35%" colspan=3 style="width:100px">
					 <input type="radio" name="file1"  id="file10" value="0" class="filetype1">否
                     <input type="radio" name="file1" id="file11"  checked="checked"  value="1"  class="filetype1">是
                    <div style="float:right"  id="ifzywhqylxid" ><cus:hxcheckbox property="company.zywhqylx" codeName="职业危害企业类型" value="${company.zywhqylx}" /><font style="color:red">请至少选择一项</font></div>
				</td>
				</tr>
				<tr>
					<th width="15%">企业成立时间</th>
					<td width="35%"><input name="company.qyclsj" value="<fmt:formatDate type='date' value='${company.qyclsj}' />" dataType="Require" msg="此项为必填" type="text" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"><font color="red">*</font></td>
				</tr>
				<tr>
					<th width="15%">固定电话</th>
					<td width="35%"><input name="company.gddh" value="${company.gddh}" type="text" maxlength="20"></td>
					<th width="15%">传       真</th>
					<td width="35%"><input name="company.cz" value="${company.cz}" type="text" maxlength="20"></td>
				</tr>
				<tr>
					<th width="15%">邮政编码</th>
					<td width="35%"><input name="company.yzbm" value="${company.yzbm}" type="text" dataType="Require" msg="此项为必填" checkregexp="\d{6}" maxlength="10"><font color="red">*</font></td>
					<th width="15%">上年销售收入（万元）</th>
					<td width="35%"><input name="company.snxssr" value="${company.snxssr}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">上年上交税收（万元）</th>
					<td width="35%"><input name="company.snsjss" value="${company.snsjss}" type="text" maxlength="255"></td>
					<th width="15%">上年固定资产 （万元）</th>
					<td width="35%"><input name="company.sngdzc" value="${company.sngdzc}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">上年安全投入</th>
					<td width="35%"><input name="company.snwqtr" value="${company.snwqtr}" type="text" maxlength="255"></td>
					<th width="15%">上年安全生产费用提取数（万元）</th>
					<td width="35%"><input name="company.snaqscf" value="${company.snaqscf}" type="text" maxlength="255"></td>
				</tr>
				<tr>
					<th width="15%">是否设立安全管理机构</th>
					<td width="35%"><cus:hxradio property="company.sfaqjg" codeName="是或否" value="${company.sfaqjg}" /></td>
					<th width="15%">安全管理员（人）</th>
					<td width="35%"><input name="company.aqglr" value="${company.aqglr}" type="text" dataType="Integer" msg="此项必须填写整数" require="false" checkregexp="^\d*$" maxlength="3"></td>
				</tr>
				<tr>
					<th width="15%">是否设立职业卫生管理机构</th>
					<td width="35%"><cus:hxradio property="company.sfzywsjg" codeName="是或否" value="${company.sfzywsjg}" /></td>
					<th width="15%">职业卫生管理人员（人）</th>
					<td width="35%"><input name="company.zywsglry" value="${company.zywsglry}" type="text" dataType="Integer" msg="此项必须填写整数" require="false" checkregexp="^\d*$" maxlength="3"></td>
				</tr>
				<tr>
					<th width="15%">是否为专职或兼职职业卫生管理员</th>
					<td width="35%"><cus:hxradio property="company.sfqzwsgly" codeName="是或否" value="${company.sfqzwsgly}" /></td>
					<th width="15%">占地面积（m2）</th>
					<td width="35%"><input name="company.zdmj" value="${company.zdmj}" type="text" dataType="Integer" msg="此项必须填写整数" require="false" checkregexp="^\d*$" maxlength="15"></td>
				</tr>
				<tr>
					<th width="15%">建筑面积（m2）</th>
					<td width="35%"><input name="company.jzmj" value="${company.jzmj}" type="text" dataType="Integer" msg="此项必须填写整数" require="false" checkregexp="^\d*$" maxlength="15"></td>
					<th width="15%">从业人员（人）</th>
					<td width="35%"><input name="company.cyry" value="${company.cyry}" type="text" dataType="Integer" msg="此项必须填写整数" require="false" checkregexp="^\d*$" maxlength="10"></td>
				</tr>
				<tr>
					<th width="15%">是否有员工宿舍</th>
					<td width="35%"><cus:hxradio property="company.sfyygss" codeName="是或否" value="${company.sfyygss}" /></td>
					<th width="15%">安全生产标准化达标级别</th>
				<td width="35%"><cus:SelectOneTag property="company.aqbzdbjb" codeName="标准化达标级别" value="${company.aqbzdbjb}"/></td>
				</tr>
				<tr>
					<th width="15%">经营范围</th>
						<td width="65%" colspan=3>
								<textarea id="company.jyfw"
									name="company.jyfw" rows="5"
									style="width: 80%">${company.jyfw}</textarea>
							</td>
				</tr>
					<tr>
					<th width="15%" style="color:red">基本信息审核状态</th>
					<td width="35%" style="color:red"><cus:hxlabel codeName="基本信息审核状态" itemValue="${company.basePass}" /></td>
						</tr>
				<s:if test="company.basePass==2" >
					<tr>
				<th width="15%">备注</th>
				<td width="65%" colspan=3>
								<textarea id="baseRemark" disabled
									name="company.baseRemark" rows="5"
									style="width: 80%">${company.baseRemark}</textarea>
							</td>
				
				</tr>
					
					
					
					</s:if>
				
				
				
				
				
				
				</s:if>
				<tr>
					<td colspan="4" height="100px" style="text-align:center">
						<s:if test="flag=='add'">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">上报</a>&nbsp;
						</s:if>
						<s:else>
						    <s:if test="company.pass!=1">
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">上报</a>&nbsp;
							</s:if>
							<s:else>
							
							    <s:if test="company.basePass==1">
							
							<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">修改</a>&nbsp;
								</s:if>
								<s:elseif test="company.basePass==0||company.basePass==2">
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">上报</a>&nbsp;
								</s:elseif>
								<s:else>
								<a href="#" class="easyui-linkbutton" onclick="save()" iconCls="icon-save">修改并上报</a>&nbsp;
								</s:else>	
							</s:else>	
						</s:else>						
					</td>
				</tr>
			</table>
		<div class="submitdata">
	</s:form>
<%@include file="/WEB-INF/template/pagefoot.jsp"%>
</body>
</html>
