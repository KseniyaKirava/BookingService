<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <form class="form-horizontal" method="post" command="do?command=Admin">

        <fieldset>

            <div class="row justify-content-left align-items-start">
                <div class="col-md-2">
                    <ul class="nav navbar-nav nav-pills nav-stacked">

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">
                                <fmt:message key="message.users"/></a>
                        </li>

                        <%--<li class="nav-item">--%>
                            <%--<a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">--%>
                                <%--<fmt:message key="message.requests"/></a>--%>
                        <%--</li>--%>

                        <%--<li class="nav-item">--%>
                            <%--<a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Admin">--%>
                                <%--<fmt:message key="message.reservations"/></a>--%>
                        <%--</li>--%>


                    </ul>

                </div>


                <div class="col-md-10">
                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend>
                                        <fmt:message key="message.users"/>
                                    </legend>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${users}" var="users">
                        <div class="row">
                            <form class="form-inline user-${users.username}" action="do?command=Admin" method=POST>

                                <div class="form-group mb-2">
                                    <input id="username" class="form-control input-md" readonly name="username"
                                           minlength="4" maxlength="15" pattern="[[A-Za-z._-]+]{4,15}"
                                           value="${users.username}" title="" style="font-size: 11pt;"/>
                                </div>
                                <div class="form-group mb-2">
                                    <input id="email" class="form-control input-md" name="email" maxlength="50"
                                           pattern="([\w\.\w]+)@(\w+\.)([a-z]{2,4})" minlength="6"
                                           value="${users.email}" title="" required="" style="font-size: 11pt;"/>
                                </div>
                                <div class="form-group mb-2">
                                    <input id="password" class="form-control input-md" name="password"
                                           minlength="5" maxlength="200" pattern="[\w]{5,200}"
                                           value="${users.password}" type="password" required="" style="font-size: 11pt;"/>
                                </div>

                                <div class="form-group mb-2">
                                    <input id="firstName" class="form-control input-md" name="firstName"
                                           minlength="2" maxlength="15" pattern="[[A-Za-zА-Яа-яЁё-]+]{2,15}"
                                           value="${users.firstName}" title="" required="" style="font-size: 11pt;"/>
                                </div>

                                <div class="form-group mb-2">
                                    <input id="lastName" class="form-control input-md" name="lastName"
                                           minlength="2" maxlength="15" pattern="[[A-Za-zА-Яа-яЁё-]+]{2,15}"
                                           value="${users.lastName}" title="" required="" style="font-size: 11pt;"/>
                                </div>

                                <div class="form-group mb-2">
                                    <input id="middleName" class="form-control input-md" name="middleName"
                                           maxlength="15" pattern="[[A-Za-zА-Яа-яЁё.-]+]{0,15}"
                                           value="${users.middleName}" title="" style="font-size: 11pt;"/>
                                </div>
                                <div class="form-group mb-2">
                                    <input id="residence_country" class="form-control input-md" name="enabled"
                                           value="${users.enabled}" title="" required="" style="font-size: 11pt;"/>
                                </div>


                                <div class="form-group mb-1">
                                    <button id="Update" name="Update" class="btn btn-white" style="font-size: 10pt;">
                                        <fmt:message key="message.save"/>
                                    </button>
                                </div>

                                <div class="form-group mb-1">
                                    <button id="Delete" name="Delete" class="btn btn-primary" style="font-size: 10pt;">
                                        <fmt:message key="message.delete"/>
                                    </button>
                                </div>

                            </form>
                        </div>


                    </c:forEach>

                    <hr>
                    <div class="row">
                        <mytag:paginator count="${size}" step="5" urlprefix="?command=Admin&start="/>
                    </div>

                </div>

            </div>
        </fieldset>
    </form>
</div>
</body>
</html>


