<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/view/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/view/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/view/css/login.css">
    <style>

    </style>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" href="${APP_PATH}/web/index" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <form id="form-login" action="${APP_PATH}/web/main" method="post" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-user"></i> 用户登录</h2>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="username" name="username" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" id="password" name="password" placeholder="请输入登录密码"
                   style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control">
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="${APP_PATH}/web/reg">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()"> 登录</a>
    </form>
</div>
<script src="${APP_PATH}/view/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/view/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/view/layer/layer.js"></script>
<script src="${APP_PATH}/view/script/function.js"></script>
<script src="${APP_PATH}/view/script/url.js"></script>
<script>


    function dologin() {
        const app_path = '${APP_PATH}';
        const username = $("#username").val();
        if (usernameReg(username)) return;

        const password = $("#password").val();
        if (passwordReg(password)) return;
        const data = {
            "username": username,
            "password": password
        };
        jsonPost(app_path, url_user_login, data, function (result) {
            window.location.href = app_path + url_web_main;
        });
    }
</script>
</body>

</html>