<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 2020/2/28
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <link rel="stylesheet" href="${APP_PATH}/view/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${APP_PATH}/view/css/font-awesome.min.css">
    <link rel="stylesheet" href="${APP_PATH}/view/css/main.css">
    <link rel="stylesheet" href="${APP_PATH}/view/css/doc.min.css">
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="${APP_PATH}/web/user/index">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="padding-top:8px;">
                    <div class="btn-group">
                        <button type="button" class="btn btn-default btn-success dropdown-toggle"
                                data-toggle="dropdown">
                            <i class="glyphicon glyphicon-user"></i> ${loginUser.username} <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
                            <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                            <li class="divider"></li>
                            <li><a href="${APP_PATH}/web/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                        </ul>
                    </div>
                </li>
                <li style="margin-left:10px;padding-top:8px;">
                    <button type="button" class="btn btn-default btn-danger">
                        <span class="glyphicon glyphicon-question-sign"></span> 帮助
                    </button>
                </li>
            </ul>
            <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <div class="tree">
                <%@include file="/WEB-INF/jsp/common/menu.jsp" %>
            </div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">分配角色</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form id="form-assign-role" role="form" class="form-inline">
                        <input type="hidden" name="userId" value="${user.id}">
                        <div class="form-group">
                            <label for="un-assign-role-list">未分配角色列表</label><br>
                            <select id="un-assign-role-list" name="unAssignRoleIds" class="form-control" multiple size="10"
                                    style="width:200px;overflow-y:auto;">
                            </select>
                        </div>
                        <div class="form-group">
                            <ul>
                                <li id="assign-role-btn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                                <br>
                                <li id="un-assign-role-btn" class="btn btn-default glyphicon glyphicon-chevron-left"
                                    style="margin-top:20px;"></li>
                            </ul>
                        </div>
                        <div class="form-group" style="margin-left:40px;">
                            <label for="assign-role-list">已分配角色列表</label><br>
                            <select id="assign-role-list" name="assignRoleIds" class="form-control" multiple size="10" style="width:200px;overflow-y:auto;">
                            </select>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">帮助</h4>
            </div>
            <div class="modal-body">
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题1</h4>
                    <p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>测试标题2</h4>
                    <p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
                </div>
            </div>
            <!--
            <div class="modal-footer">
              <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
              <button type="button" class="btn btn-primary">Save changes</button>
            </div>
            -->
        </div>
    </div>
</div>
<script src="${APP_PATH}/view/jquery/jquery-2.1.1.min.js"></script>
<script src="${APP_PATH}/view/bootstrap/js/bootstrap.min.js"></script>
<script src="${APP_PATH}/view/script/docs.min.js"></script>
<script src="${APP_PATH}/view/layer/layer.js"></script>
<script src="${APP_PATH}/view/script/function.js"></script>
<script src="${APP_PATH}/view/script/url.js"></script>
<script type="text/javascript">
    const app_path = '${APP_PATH}';
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
        loadingRole();

        $('#assign-role-btn').click(function () {
           let opts= $('#un-assign-role-list :selected');
           if (opts.length===0){
               showMag('请选择需要分配的角色');
               return;
           }
            userAssignRole($('#assign-list'),opts);
        });

        $('#un-assign-role-btn').click(function () {
            let opts= $('#assign-role-list :selected');
            if (opts.length===0){
                showMag('请选择需要取消分配的角色');
                return;
            }
            userAssignRole($('#un-assign-role-list'),opts);
        });
    });

    function userAssignRole(obj,opts) {
        let json = $('#form-assign-role').serialize();
       jsonPost(app_path,url_user_assign_roles,json,function (result) {
           obj.append(opts);
           window.location.href = app_path+url_web_user_assign_role+'/'+${user.id};
       });
    }
    
    function loadingRole() {
        var json = {
            'userId':${user.id}
        };
        jsonPost(app_path,url_user_list,json,function (result) {
            let unAssignListHtml = '';
            let unAssignRoles = result.data.unAssignRoles;
            for (let index in unAssignRoles) {
                let role = unAssignRoles[index];
                unAssignListHtml += '<option value="' + role.id + '">' + role.roleName + '</option>';
            }
            $('#un-assign-role-list').html(unAssignListHtml);

            let assignListHtml = '';
            let assignRoles = result.data.assignRoles;
            for (let index in assignRoles) {
                let role = assignRoles[index];
                assignListHtml += '<option value="' + role.roleId + '">' + role.role.roleName + '</option>';
            }
            $('#assign-role-list').html(assignListHtml);
        });
    }
</script>
</body>
</html>

