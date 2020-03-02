<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 2020/3/1
  Time: 16:28
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
    <link rel="stylesheet" href="${APP_PATH}/view/ztree/zTreeStyle.css">
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
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
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

            <div class="panel panel-default">
                <div class="panel-heading"><i class="glyphicon glyphicon-th-list"></i> 权限分配列表
                    <div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i
                            class="glyphicon glyphicon-question-sign"></i></div>
                </div>
                <div class="panel-body">
                    <button onclick="onDoAssign()" class="btn btn-success">分配许可</button>
                    <br><br>
                    <ul id="permission-tree" class="ztree"></ul>
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
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
                </div>
                <div class="bs-callout bs-callout-info">
                    <h4>没有默认类</h4>
                    <p>警告框没有默认类，只有基类和修饰类。默认的灰色警告框并没有多少意义。所以您要使用一种有意义的警告类。目前提供了成功、消息、警告或危险。</p>
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
<script src="${APP_PATH}/view/script/jquery.ztree.min.js"></script>
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

        var setting = {
            check: {
                enable: true
            },
            view: {
                selectedMulti: false,
                addDiyDom: function (treeId, treeNode) {
                    var icoObj = $("#" + treeNode.tId + "_ico"); // tId = permissionTree_1, $("#permissionTree_1_ico")
                    if (treeNode.icon) {
                        icoObj.removeClass("button ico_docu ico_open").addClass(treeNode.icon).css("background", "");
                    }
                },
            },
            async: {
                enable: true,
                url: app_path + url_permission_checked_list + '/' +${role.id},
                autoParam: ["id", "name=n", "level=lv"]
            },
            callback: {
                onClick: function (event, treeId, json) {

                }
            }
        };
        $.fn.zTree.init($("#permission-tree"), setting); //异步访问数据
    });

    function onDoAssign() {
        let treeObj = $.fn.zTree.getZTreeObj('permission-tree');
        let nodes = treeObj.getCheckedNodes(true); // 获取被选中的节点
        if (nodes.length === 0) {
            showMag("请选择需要分配的许可信息");
            return;
        }

        let json = 'roleId=${role.id}';
        $.each(nodes, function (index, node) {
            json += "&permissionIds=" + node.id;
        });
        console.log(json);
        jsonPost(app_path, url_role_assign_permissions, json, function (result) {
            showSuccessMag(result.msg);
            window.location.href = app_path + url_web_role_index;
        });
    }
</script>
</body>
</html>

