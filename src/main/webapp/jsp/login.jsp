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

            <div class="form-group">
                <label class="col-md-12 control-label" style="color: red; font-size: 12pt;">
                    ${errorLoginCommand}
                </label>
            </div>

            <div class="form-group">

                <label class="col-md-4 control-label" for="username" style="font-size: 11pt;">
                    <fmt:message key="message.login"/>
                </label>
                <div class="col-md-4">
                    <input id="username" value="vasia" name="username" type="text" placeholder="login"
                           minlength="4" maxlength="15" pattern="[[A-Za-z._-]+]{4,15}"
                           class="form-control input-md" required="" style="font-size: 11pt;">
                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">
                    <fmt:message key="message.password"/>
                </label>
                <div class="col-md-4">
                    <input id="password" value="123456" name="password" type="password" placeholder="password"
                           minlength="5" maxlength="15" pattern="[\w\d]{5,15}"
                           class="form-control input-md" required="" style="font-size: 11pt;">
                    <span class="help-block" style="font-size: 9pt; color: #949494;">
                        <fmt:message key="message.passwordDescription"/>
                    </span>
                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="loginbutton"></label>
                <div class="col-md-4">
                    <button id="loginbutton" name="loginbutton" class="btn btn-primary" style="font-size: 10pt;">
                        <fmt:message key="message.submit"/>
                    </button>
                </div>
            </div>

        </fieldset>
    </form>

</div>
</body>
</html>

