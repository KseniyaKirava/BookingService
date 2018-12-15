<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <form class="form-horizontal" method="post" command="do?command=Profile">

        <fieldset>

            <br>

            <div class="row justify-content-left align-items-start">
                <div class="col-md-12">

                    <div class="row">
                        <div class="col-md-6 text-left">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend style="font-size: 18px;">
                                        <fmt:message key="message.profile"/>
                                    </legend>
                                </div>
                            </div>
                        </div>


                        <div class="col-md-6 text-right">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <button data-toggle-id="delete-account" name="delete-account" type="button"
                                            data-toggle="modal" data-target="#ModalCenter"
                                            class="btn btn-white" style="font-size: 14px;">
                                        <fmt:message key="message.deleteAccount"/>
                                    </button>
                                    <button id="logout" name="logout" class="btn btn-dark" style="font-size: 14px;">
                                        <fmt:message key="message.logout"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <c:if test="${errorData != null}">
                        <div class="form-group">
                            <div class="alert alert-danger" role="alert">
                                <label class="col-md-12 control-label" style="font-size: 16px;">
                                        ${errorData}
                                </label>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${changeData != null}">
                        <div class="form-group">
                            <label class="col-md-12 control-label" style="color: red; font-size: 16px;">
                                    ${changeData}
                            </label>
                        </div>
                    </c:if>

                    <!-- Modal -->
                    <div class="modal fade" id="ModalCenter" tabindex="-1" role="dialog"
                         aria-labelledby="ModalCenterTitle" aria-hidden="true">
                        <div class="modal-dialog modal-dialog-centered" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="ModalLongTitle"><fmt:message
                                            key="message.deleteAccountHeader"/></h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <p><fmt:message key="message.areYouSure"/></p>
                                </div>
                                <div class="modal-footer">
                                    <button id="deleteAccount" name="deleteAccount"
                                            class="btn btn-white" style="font-size: 14px;">
                                        <fmt:message key="message.deleteAccount"/>
                                    </button>
                                    <button id="cancel-delete" name="cancel-delete" data-dismiss="modal"
                                            class="btn btn-dark" style="font-size: 14px;">
                                        <fmt:message key="message.cancel"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row justify-content-left align-items-start">

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="username" style="font-size: 16px;">
                                    <fmt:message key="message.login"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="username" value="${username}" name="username"
                                           style="font-size: 16px;" type="text" class="form-control input-md"
                                           required="" readonly/>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="email" style="font-size: 16px;">
                                    <fmt:message key="message.email"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="email" value="${email}" name="email" type="text" placeholder="e-mail"
                                           minlength="6" maxlength="50" pattern="([\w\.\w]+)@(\w+\.)([a-z]{2,4})"
                                           style="font-size: 16px;" class="form-control input-md" required=""/>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row justify-content-left align-items-start">
                        <!-- Password input-->
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
                                           maxlength="15" pattern="[\w]{0,15}" placeholder=""
                                           class="form-control input-md" style="font-size: 16px;"/>
                                    <input onchange="if ($('#password').get(0).type=='password')
                                                            $('#password').get(0).type='text';
                                                     else $('#password').get(0).type='password';"
                                           name="check-box" type="checkbox" value="false">
                                    <fmt:message key="message.showPassword"/>
                                </div>
                            </div>
                        </div>


                        <!-- Text input-->

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="firstName" style="font-size: 16px;">
                                    <fmt:message key="message.firstName"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="firstName" value="${firstName}" name="firstName" type="text"
                                           maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{2,15}"
                                           placeholder="first name" class="form-control input-md" required=""
                                           style="font-size: 16px;"/>

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
                                           placeholder="last name" class="form-control input-md" required=""
                                           style="font-size: 16px;"/>

                                </div>
                            </div>
                        </div>


                        <!-- Text input-->

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="middleName" style="font-size: 16px;">
                                    <fmt:message key="message.middleName"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="middleName" value="${middleName}" name="middleName" type="text"
                                           maxlength="15" pattern="[A-Za-zА-Яа-яЁё-]{0,15}"
                                           placeholder="" class="form-control input-md" style="font-size: 16px;"/>

                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Button (Double) -->
                    <div class="form-group">
                        <div class="col-md-auto text-right">
                            <button id="save" name="save" class="btn btn-dark" style="font-size: 14px;">
                                <fmt:message key="message.save"/>
                            </button>
                        </div>
                    </div>
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


