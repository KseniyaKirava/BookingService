<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>

    <form class="form-horizontal" method="post" command="do?command=Profile">

        <fieldset>

            <div class="row justify-content-left align-items-start">
                <div class="col-md-2">
                    <ul class="nav navbar-nav nav-pills nav-stacked">

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">
                                <fmt:message key="message.users"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">
                                <fmt:message key="message.requests"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">
                                <fmt:message key="message.rooms"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">
                                <fmt:message key="message.reservations"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">
                                <fmt:message key="message.facilities"/></a>
                        </li>


                    </ul>

                </div>


                <div class="col-md-10">
                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend> <fmt:message key="message.users"/></legend>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${users}" var="users">
                        <div class="row">
                            <form class="form-inline user-${users.username}" action="do?command=Admin" method=POST>

                                <div class="form-group mb-2">
                                    <input id="username" class="form-control input-md" readonly name="username"
                                           value="${users.username}" title=""/>
                                </div>
                                <div class="form-group mb-2">
                                    <input id="email" class="form-control input-md" name="email"
                                           value="${users.email}" title=""/>
                                </div>
                                <div class="form-group mb-2">
                                    <input id="password" class="form-control input-md" name="password"
                                           value="${users.password}" title=""/>
                                </div>

                                <div class="form-group mb-2">
                                    <input id="first_name" class="form-control input-md" name="first_name"
                                           value="${users.first_name}" title=""/>
                                </div>

                                <div class="form-group mb-2">
                                    <input id="last_name" class="form-control input-md" name="last_name"
                                           value="${users.last_name}" title=""/>
                                </div>

                                <div class="form-group mb-2">
                                    <input id="middle_name" class="form-control input-md" name="middle_name"
                                           value="${users.middle_name}" title=""/>
                                </div>
                                <div class="form-group mb-2">
                                    <input id="residence_country" class="form-control input-md" name="enable"
                                           value="${users.enable}" title=""/>
                                </div>


                                <div class="form-group mb-1">
                                    <button id="Update" name="Update" class="btn btn-white">
                                        <fmt:message key="message.save"/>
                                    </button>
                                </div>

                                <div class="form-group mb-1">
                                    <button id="Delete" name="Delete" class="btn btn-primary">
                                        <fmt:message key="message.delete"/>
                                    </button>
                                </div>


                            </form>
                        </div>

                    </c:forEach>

                </div>

            </div>
        </fieldset>
    </form>
</div>
</body>
</html>


