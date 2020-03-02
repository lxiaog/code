<%--
  Created by IntelliJ IDEA.
  User: xiao
  Date: 2020/3/1
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul style="padding-left:0px;" class="list-group">
    <c:forEach items="${loginUser.permissions[0].children}" var="permission">
        <c:if test="${empty permission.children}">
            <li class="list-group-item tree-closed">
                <a href="${APP_PATH}${permission.url}">
                    <i class="${permission.icon}"></i> ${permission.name}</a>
            </li>
        </c:if>
        <c:if test="${not empty permission.children}">
            <li class="list-group-item">
                        <span>
                            <i class="${permission.icon}"></i> ${permission.name}
                            <span class="badge" style="float:right">${permission.children.size()}</span>
                        </span>
                <ul style="margin-top:10px;">
                    <c:forEach items="${permission.children}" var="item">
                        <li style="height:30px;">
                            <a href="${APP_PATH}${item.url}"><i class="${item.icon}"></i> ${item.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:if>
    </c:forEach>
</ul>
