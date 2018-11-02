<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <form class="form-horizontal" method="post" command="do?command=SignUp">


        <fieldset>

            <div class="form-group">
                <label class="col-md-12 control-label" style="color: red; font-size: 12pt;">
                    ${errorSignUpCommand}${errorUsernameDuplicate}
                </label>
            </div>


            <!-- Form Name -->
            <legend style="font-size: 14pt;">    <fmt:message key="message.signupPage"/></legend>

            <div class="row justify-content-left align-items-start">


                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="username" style="font-size: 11pt;">
                            <fmt:message key="message.login"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="username" value="${username}" name="username" type="text" minlength="4"
                                   maxlength="15" pattern="[[A-Za-z._-]+]{4,15}" style="font-size: 11pt;"
                                   class="form-control input-md" required=""/>
                        </div>
                    </div>
                </div>


                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="email" style="font-size: 11pt;">
                            <fmt:message key="message.email"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="email" value="${email}" name="email" type="email" minlength="6"
                                   maxlength="50" pattern="([\w\.\w]+)@(\w+\.)([a-z]{2,4})"
                                   style="font-size: 11pt;" class="form-control input-md" required=""/>

                        </div>
                    </div>
                </div>
            </div>

            <!-- Password input-->
            <div class="row justify-content-left align-items-start">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="password" style="font-size: 11pt;">
                            <fmt:message key="message.password"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="password" value="${password}" name="password" type="password" minlength="5"
                                   maxlength="15"
                                   pattern="[\w]{5,15}" style="font-size: 11pt;"
                                   class="form-control input-md" required=""/>
                            <span class="help-block" style="font-size: 9pt; color: #949494;">
                                <fmt:message key="message.passwordDescription"/> (A-Za-z0-9)
                            </span>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="firstName" style="font-size: 11pt;">
                            <fmt:message key="message.firstName"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="firstName" value="${firstName}" name="firstName" type="text"
                                   minlength="2" maxlength="15" pattern="[[A-Za-zА-Яа-яЁё-]+]{2,15}"
                                   class="form-control input-md" required="" style="font-size: 11pt;"/>

                        </div>
                    </div>
                </div>
            </div>

            <div class="row justify-content-left align-items-start">
                <!-- Text input-->
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="lastName" style="font-size: 11pt;">
                            <fmt:message key="message.lastName"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="lastName" value="${lastName}" name="lastName" type="text"
                                   minlength="2" maxlength="15" pattern="[[A-Za-zА-Яа-яЁё-]+]{2,15}"
                                   class="form-control input-md" required="" style="font-size: 11pt;"/>

                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="middleName" style="font-size: 11pt;">
                            <fmt:message key="message.middleName"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="middleName" value="${middleName}" name="middleName" type="text" maxlength="15"
                                   pattern="[[A-Za-zА-Яа-яЁё.-]+]{0,15}" style="font-size: 11pt;"
                                   class="form-control input-md"/>

                        </div>
                    </div>
                </div>
            </div>


            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singupbutton"></label>
                <div class="col-md-4">
                    <button id="singupbutton" name="singupbutton" class="btn btn-primary"
                            style="font-size: 10pt;">
                        <fmt:message key="message.save"/>
                    </button>
                </div>
            </div>

        </fieldset>
    </form>


</div>
</body>
</html>




