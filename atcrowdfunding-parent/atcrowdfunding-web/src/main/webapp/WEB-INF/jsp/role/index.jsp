<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 2020/2/25
  Time: 16:14
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
    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a>
            </div>
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
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a>
                            </li>
                            <li>
                                <a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="${APP_PATH}/web/logout"><i class="glyphicon glyphicon-off"></i> 退出系统</a>
                            </li>
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
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input id="query-role-text" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button id="query-role-btn" type="button" class="btn btn-warning">
                            <i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button onclick="onBatchDeleteRole()" type="button" class="btn btn-danger"
                            style="float:right;margin-left:10px;">
                        <i class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" class="btn btn-primary" style="float:right;"
                            onclick="onAddRole()">
                        <i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <form id="form-role-table">
                            <table class="table  table-bordered">
                                <thead>
                                <tr>
                                    <th width="30">#</th>
                                    <th width="30"><input id="role-all-check-box" type="checkbox"></th>
                                    <th>角色名称</th>
                                    <th>创建时间</th>
                                    <th>更新时间</th>
                                    <th width="100">操作</th>
                                </tr>
                                </thead>
                                <tbody id="table-role">

                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="8" align="center">
                                        <ul id="page-role" class="pagination">

                                        </ul>
                                    </td>
                                </tr>

                                </tfoot>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
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
    var pageNum = 1;
    const pageSize = 10;
    var roleName = '';
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
        queryRolePage(pageNum, pageSize, roleName);
        $("#query-role-btn").click(function () {
            roleName = $("#query-role-text").val();
            queryRolePage(pageNum, pageSize, roleName);
        });

        $('#role-all-check-box').click(function () {
            const allFlag = this.checked;
            $('#table-role :checkbox').each(function () {
                this.checked = allFlag;
            })
        });
    });


    function queryRolePage(pageNum, pageSize, roleName) {
        var data = {
            "pageNum": pageNum,
            "pageSize": pageSize,
            "roleName": roleName
        };
        jsonPost(app_path, url_role_page, data, function (result) {
            var tableHtml = '';
            var pageHtml = '';
            var rolePage = result.data;
            var roleList = rolePage.list;
            $.each(roleList, function (index, role) {
                tableHtml += '<tr>';
                tableHtml += '<td>' + (index + 1) + '</td>';
                tableHtml += '<td><input onclick="onRoleCheckBox(' + role.id + ')" type="checkbox" name="roleIds" value="' + role.id + '"></td>';
                tableHtml += '<td>' + role.roleName + '</td>';
                tableHtml += '<td>' + role.createDate + '</td>';
                tableHtml += '<td>' + role.updateDate + '</td>';
                tableHtml += '<td>';
                tableHtml += '<button type="button" onclick="onAssignPermissions(' + role.id + ')" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>';
                tableHtml += '<button type="button" onclick="onEditRole(' + role.id + ')" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>';
                tableHtml += '<button type="button" onclick="onDeleteRole(' + role.id + ',\'' + role.roleName + '\')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>';
                tableHtml += '</td>';
                tableHtml += '</tr>';
            });
            //如果是第一页上一页禁止点击
            if (rolePage.isFirstPage === 'true') {
                pageHtml += '<li class="disabled"><a href="#">上一页</a></li>';
            } else {
                pageHtml += '<li><a href="#" onclick="queryRolePage(' + (rolePage.prePage) + ',' + pageSize + ',' + roleName + ')">上一页</a></li>';
            }
            for (var currentPage = 1; currentPage <= rolePage.pages; currentPage++) {
                if (pageNum === currentPage) {
                    pageHtml += '<li class="active"><a href="#">' + currentPage + '</a></li>';
                } else {
                    pageHtml += '<li><a href="#" onclick="queryRolePage(' + currentPage + ',' + pageSize + ',' + roleName + ')">' + currentPage + '</a></li>';
                }
            }
            //如果是最后一页
            if (rolePage.isLastPage === 'true') {
                pageHtml += '<li class="disabled"><a href="#">下一页</a></li>';
            } else {
                pageHtml += '<li ><a href="#" onclick="queryRolePage(' + rolePage.nextPage + ',' + pageSize + ',' + roleName + ')">下一页</a></li>';
            }

            $("#table-role").html(tableHtml);
            $("#page-role").html(pageHtml);
        });
    }

    function onAssignPermissions(id) {
        window.location.href = app_path + url_web_role_assign_permission + '/' + id;
    }

    function onAddRole() {
        window.location.href = app_path + url_web_role_add;
    }

    function onEditRole(id) {
        window.location.href = app_path + url_web_role_edit + '/' + id;
    }

    function onDeleteRole(id, roleName) {
        console.log(id + "=" + roleName);
        layer.confirm('删除角色信息【' + roleName + '】，是否继续', {icon: 3, title: '删除提示'}, function (cindex) {
            layer.close(cindex);
            var json = {
                'id': id
            };
            jsonPost(app_path, url_role_delete, json, function (result) {
                showMag(result.msg);
                queryRolePage(pageNum, pageSize, '');
            });
        }, function (cindex) {
            layer.close(cindex);
        });
    }

    function onRoleCheckBox(id) {
        var checkBoxCount = 0;
        const boxs = $('#table-role :checkbox');
        boxs.each(function () {
            if (this.checked) {
                checkBoxCount++;
            }
        });
        const allBox = $('#role-all-check-box');
        allBox[0].checked = checkBoxCount === boxs.length;
    }

    function onBatchDeleteRole() {
        const boxs = $('#table-role :checkbox');
        var count = 0;
        boxs.each(function () {
            if (this.checked) {
                count++;
            }
        });
        if (count <= 0) {
            showMag('请选择需要删除的数据');
        } else {
            layer.confirm('是否删除已选择的数据', {icon: 3, title: '删除提示'}, function (cindex) {
                layer.close(cindex);
                const json = $('#form-role-table').serialize();
                jsonPost(app_path, url_role_batch_delete, json, function (result) {
                    showMag(result.msg);
                    queryRolePage(pageNum, pageSize, '');
                });
            }, function (cindex) {
                layer.close(cindex);
            });
        }
    }
</script>
</body>

</html>