<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <%--<form class="form-horizontal" method="post" command="do?command=Admin">--%>

    <fieldset>

        <br>

        <div class="row justify-content-left align-items-start">
            <div class="col-md-12">
                <div class="row">
                    <div class="col-md-6 text-left">
                        <div class="form-group">
                            <div class="col-md-auto">
                                <legend style="font-size: 18px;">
                                    <fmt:message key="message.users"/>
                                </legend>
                            </div>
                        </div>
                    </div>
                </div>

                <%--<c:if test="${wordUser != null && currentUser != null && (isDisabled != null || isUpdated != null)}">--%>
                    <%--<div class="form-group">--%>
                        <%--<div class="alert alert-success" role="alert">--%>
                            <%--<label class="col-md-12 control-label" style="font-size: 16px;">--%>
                                    <%--${wordUser}${currentUser}${isDisabled}${isUpdated}--%>
                            <%--</label>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</c:if>--%>


                <c:if test="${errorData != null}">
                    <div class="form-group">
                        <div class="alert alert-danger" role="alert">
                            <label class="col-md-12 control-label" style="font-size: 16px;">
                                    ${errorData}
                            </label>
                        </div>
                    </div>
                </c:if>

                <div class="container">
                    <!--HEADER-->
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="row">
                                <div class="col-lg-4 text-left" style="font-size: 16px; font-weight: bold">
                                    <fmt:message key="message.login"/>
                                </div>
                                <div class="col-lg-5 text-left" style="font-size: 16px; font-weight: bold">
                                    <fmt:message key="message.email"/>
                                </div>
                                <div class="col-lg-3 text-left" style="font-size: 16px; font-weight: bold">
                                    <fmt:message key="message.password"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-5">
                            <div class="row">
                                <div class="col-lg-4 text-left" style="font-size: 16px; font-weight: bold">
                                    <fmt:message key="message.firstName"/>
                                </div>
                                <div class="col-lg-4 text-left" style="font-size: 16px; font-weight: bold">
                                    <fmt:message key="message.lastName"/>
                                </div>
                                <div class="col-lg-4 text-left" style="font-size: 16px; font-weight: bold">
                                    <fmt:message key="message.middleName"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-1">
                            <div class="row">
                                <div class="col-lg-12 text-center"></div>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${users}" var="users">
                        <form class="user-${users.username}" action="do?command=Admin" method=POST>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <input id="username" class="form-control" readonly name="username"
                                                   value="${users.username}" title="" style="font-size: 15px;"/>
                                        </div>
                                        <div class="col-lg-5">
                                            <input id="email" class="form-control" name="email" maxlength="50"
                                                   pattern="([\w\.\w]+)@(\w+\.)([a-z]{2,4})" minlength="6"
                                                   value="${users.email}" title="" required=""
                                                   style="font-size: 15px;"/>
                                        </div>
                                        <div class="col-lg-3">
                                            <input id="password" class="form-control" name="password"
                                                   maxlength="15" pattern="[\w]{0,15}" placeholder=""
                                                   value="${users.password}" type="password"
                                                   style="font-size: 15px;"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-5">
                                    <div class="row">
                                        <div class="col-lg-4">
                                            <input id="firstName" class="form-control " name="firstName"
                                                   maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{2,15}"
                                                   value="${users.firstName}" title="" required=""
                                                   style="font-size: 15px;"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <input id="lastName" class="form-control" name="lastName"
                                                   maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{2,15}"
                                                   value="${users.lastName}" title="" required=""
                                                   style="font-size: 15px;"/>
                                        </div>
                                        <div class="col-lg-4">
                                            <input id="middleName" class="form-control" name="middleName"
                                                   maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{0,15}"
                                                   value="${users.middleName}" title="" style="font-size: 15px;"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-1">
                                    <div class="row ">
                                        <div class="col-lg-6">
                                            <button id="Update" name="Update" class="btn btn-link">
                                                <img src="../image/admin/save.png" height="22" border="0" align="bottom"/>
                                            </button>
                                        </div>
                                        <div class="col-lg-6">
                                            <button id="Delete" name="Delete" class="btn btn-link">
                                                <img src="../image/admin/bin.png" height="23" border="0" align="bottom"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>

                <c:if test="${users != null}">

                    <hr>

                    <div class="row">
                        <mytag:paginator count="${size}" step="${rowPerPage}" urlprefix="?command=Admin&start="/>
                    </div>
                </c:if>
            </div>

        </div>
    </fieldset>
    <%--</form>--%>
</div>
</body>
<footer>
    <div class="container">
        <div class="row justify-content-left align-items-start">
            <div class="col-md-12">
                <%@ include file="../include/footer.jspf" %>
            </div>
        </div>
    </div>
</footer>
</html>


