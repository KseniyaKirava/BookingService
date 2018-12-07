<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>


    <form class="form-horizontal" method="post" command="do?command=Login">
        <fieldset>

            <br>

            <c:if test="${errorLoginCommand != null}">
                <div class="form-group">
                    <div class="alert alert-danger" role="alert">
                        <label class="col-md-12 control-label" style="font-size: 16px;">
                                ${errorLoginCommand}
                        </label>
                    </div>
                </div>
            </c:if>

            <div class="form-group">

                <label class="col-md-4 control-label" for="username" style="font-size: 16px;">
                    <fmt:message key="message.login"/>
                </label>
                <div class="col-md-4">
                    <input id="username" value="vasia" name="username" type="text" placeholder="login"
                           maxlength="15" pattern="[A-Za-z._-]{4,15}"
                           class="form-control input-md" required="" style="font-size: 16px;">
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">
                    <fmt:message key="message.password"/>
                </label>
                <div class="col-md-4">
                    <input id="password" value="123456" name="password" type="password" placeholder="password"
                           maxlength="15" pattern="[\w]{0,15}"
                           class="form-control input-md" required="" style="font-size: 16px;">
                    <input onchange="if ($('#password').get(0).type=='password')
                                                            $('#password').get(0).type='text';
                                                     else $('#password').get(0).type='password';"
                           name="check-box" type="checkbox" value="false" style="font-size: 12px;">
                    <fmt:message key="message.showPassword"/>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="loginButton"></label>
                <div class="col-md-4">
                    <button id="loginButton" name="loginButton" class="btn btn-dark" style="font-size: 14px;">
                        <fmt:message key="message.submit"/>
                    </button>
                </div>
            </div>

        </fieldset>
    </form>

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

