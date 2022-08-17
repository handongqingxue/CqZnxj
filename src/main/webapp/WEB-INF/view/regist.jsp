<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新用户注册</title>
<%@include file="inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
$(function(){
	initFirstDeptCBB();
	initSecondDeptCBB();
	setTimeout(function(){
		setComboLocation();
	},"1000");
});

function setComboLocation(){
	$(".combo").eq(0).css("margin-top","8px");
	$(".combo").eq(1).css("margin-top","8px");
}

function initFirstDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择一级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			firstDeptCBB=$("#firstDept_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onSelect:function(){
					loadSecondDeptCBBData();
				}
			});
		}
	,"json");
}

function initSecondDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	secondDeptCBB=$("#secondDept_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function loadSecondDeptCBBData(){
	var parentId=firstDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:parentId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			secondDeptCBB.combobox("loadData",data);
		}
	,"json");
}

//验证一级部门
function checkFirstDeptId(){
	var deptId=firstDeptCBB.combobox("getValue");
	if(deptId==null||deptId==""){
	  	alert("请选择一级部门");
	  	return false;
	}
	else
		return true;
}

//验证二级部门
function checkSecondDeptId(){
	var deptId=secondDeptCBB.combobox("getValue");
	if(deptId==null||deptId==""){
	  	alert("请选择二级部门");
	  	return false;
	}
	else
		return true;
}
</script>
<style>
.register_content {
	width: 53%;
	margin: auto;
	background-color: #fff;
	border-radius: 3px;
}

body .beg-login-bg {
	position: inherit;
	overflow: auto;
}

.beg-login-bg {
	background-color: #393D49;
}

.register_title {
	padding: 10px 0 20px 0;
}

.register {
	margin-top: 100px;
}

.register-form {
	padding: 20px;
	border-radius: 5px;
	width: 90%;
	max-width: 1080px;
	margin: auto;
}

.title {
	font-size: 21px;
	font-weight: 600;
	text-align: center;
}

.layui-form-item>label {
	font-size: 14px;
	width: 90px;
	font-weight: 600;
}

.layui-form-item>.layui-input-block {
	margin-left: 120px;
}

.layui-form {
	width: 90%
}
</style>
</head>
<body class="beg-login-bg">
	<div class="register">
		<div class="register_content register-form">
			<div class="register_title">
				<h2 class="title">智能巡检系统注册</h2>
			</div>
			<form class="layui-form" method="post">
				<input type="hidden" id="deptId" name="deptId"/>
				<div class="layui-form-item">
					<label class="layui-form-label">用户名</label>
					<div class="layui-input-inline">
						<input type="text" name="userName" required lay-verify="required"
							placeholder="请输入用户名" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">该用户名将作为登录账号使用</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="password" id="password"required
							lay-verify="required" placeholder="请输入密码" autocomplete="off"
							class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请确保两次输入密码一致</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">确认密码:</label>
					<div class="layui-input-inline">
						<input type="password" name="password1" id="password1" required
							lay-verify="required|same_password" placeholder="请再次输入密码" autocomplete="off"
							class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux">请确保两次输入密码一致</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">手机:</label>
					<div class="layui-input-block">
						<input type="text" name="phone" required
							lay-verify="required|phone" placeholder="请输入手机号"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">一级部门:</label>
					<div class="layui-input-block">
						<input id="firstDept_cbb"/>
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">二级部门:</label>
					<div class="layui-input-block">
						<input id="secondDept_cbb"/>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
				<div style="height: 1px;width: 100%;background-color: #00f;margin-top: -88px;"></div>
	</div>
	<script type="text/javascript" src="<%=basePath %>resource/js/MD5.js"></script>
	<script type="text/javascript">
	var baseUrl="${pageContext.request.contextPath}"
		layui.use('form', function() {
			var form = layui.form;

			//监听提交
			form.on('submit(formDemo)', function(data) {
				if(checkFirstDeptId()){
					if(checkSecondDeptId()){
						var url=baseUrl+"/main/regist";
						var deptId=secondDeptCBB.combobox("getValue");
						$("#deptId").val(deptId);
						data.field.password=MD5(data.field.password).toUpperCase();
						$.post(url,data.field,function(result){
							console.log(result)
							if(result.status==1){
								window.location.href=baseUrl+result.url
							}
							else{
								alert(result.msg)
							}
						},"json")
						console.log("===111===")
					}
				}
				return false
			});
			form.verify({
	            //验证密码是否一样
	            same_password: function (value) {
	                var pass1 = $("#password").val();
	                if (value !== pass1) {
	                    return '两次输入密码不一致';
	                }
	            }
	        });
		})
	</script>
</body>
</html>