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

            <br>

            <div class="row justify-content-left align-items-start">
                <div class="col-md-12">
                    <div class="form-group">
                        <div class="col-md-auto">
                            <legend style="font-size: 18px;">
                                <fmt:message key="message.signupPage"/>
                            </legend>
                        </div>
                    </div>
                </div>
            </div>


            <c:if test="${errorSignUpCommand != null || errorUsernameDuplicate != null}">
                <div class="form-group">
                    <div class="alert alert-danger" role="alert">
                        <label class="col-md-12 control-label" style="font-size: 16px;">
                                ${errorSignUpCommand}${errorUsernameDuplicate}
                        </label>
                    </div>
                </div>
            </c:if>


            <div class="row justify-content-left align-items-start">


                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="username" style="font-size: 16px;">
                            <fmt:message key="message.login"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="username" value="${username}" name="username" type="text"
                                   maxlength="15" pattern="[A-Za-z._-]{4,15}"
                                   style="font-size: 16px;"
                                   class="form-control input-md" required=""/>
                        </div>
                    </div>
                </div>


                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="email" style="font-size: 16px;">
                            <fmt:message key="message.email"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="email" value="${email}" name="email" type="email"
                                   minlength="6" maxlength="50" pattern="([\w\.\w]+)@(\w+\.)([a-z]{2,4})"
                                   style="font-size: 16px;" class="form-control input-md" required=""/>

                        </div>
                    </div>
                </div>
            </div>

            <!-- Password input-->
            <div class="row justify-content-left align-items-start">
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="password" style="font-size: 16px;">
                            <fmt:message key="message.password"/>
                            <button id="info" name="info" class="btn btn-link" data-toggle="tooltip"
                                    data-placement="top" title="<fmt:message key="message.passwordDescription"/>">
                                <img src="../image/info.png" height="14" border="0"/>
                            </button>
                        </label>
                        <div class="col-md-auto">
                            <input id="password" value="${password}" name="password" type="password"
                                   maxlength="15" pattern="[\w]{0,15}"
                                   style="font-size: 16px;"
                                   class="form-control input-md" required=""/>
                            <input onchange="if ($('#password').get(0).type=='password')
                                                            $('#password').get(0).type='text';
                                                     else $('#password').get(0).type='password';"
                                   name="check-box" type="checkbox" value="false">
                            <fmt:message key="message.showPassword"/>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="firstName" style="font-size: 16px;">
                            <fmt:message key="message.firstName"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="firstName" value="${firstName}" name="firstName" type="text"
                                   maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{2,15}"
                                   class="form-control input-md" required="" style="font-size: 16px;"/>

                        </div>
                    </div>
                </div>
            </div>

            <div class="row justify-content-left align-items-start">
                <!-- Text input-->
                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="lastName" style="font-size: 16px;">
                            <fmt:message key="message.lastName"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="lastName" value="${lastName}" name="lastName" type="text"
                                   maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{2,15}"
                                   class="form-control input-md" required="" style="font-size: 16px;"/>

                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="middleName" style="font-size: 16px;">
                            <fmt:message key="message.middleName"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="middleName" value="${middleName}" name="middleName" type="text"
                                   maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{0,15}" style="font-size: 16px;"
                                   class="form-control input-md"/>

                        </div>
                    </div>
                </div>
            </div>


            <!-- Button -->
            <div class="form-group">
                <div class="col-md-auto text-right">
                    <button id="singupButton" name="singupButton" class="btn btn-dark"
                            style="font-size: 14px;">
                        <fmt:message key="message.save"/>
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




