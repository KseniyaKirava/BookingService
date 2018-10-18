<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>

    <form class="form-horizontal" method="post" command="do?command=Addrequest">

        <fieldset>

            <div class="row justify-content-left align-items-start">
                <div class="col-md-2">
                    <ul class="nav navbar-nav nav-pills nav-stacked">
                        <li class="active nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;"
                               href="do?command=Profile"><fmt:message key="message.editMyProfile"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Addrequest">
                                <fmt:message key="message.addrequest"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Request">
                                <fmt:message key="message.myRequest"/></a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Reservation">
                                <fmt:message key="message.myBills"/></a>
                        </li>

                    </ul>

                </div>


                <div class="col-md-10">

                    <div class="row">
                        <div class="col-md-6 text-left">
                            <div class="form-group">
                                <div class="col-md-auto" style="font-size: 10pt;">
                                    <legend><fmt:message key="message.addrequest"/></legend>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Text input-->
                    <div class="row justify-content-left align-items-start">

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="room_capacity"> <fmt:message key="message.roomcapacity"/></label>
                                <div class="col-md-auto">
                                    <input id="room_capacity" value="${room_capacity}" name="room_capacity"
                                           type="text" class="form-control input-md"
                                           required="">

                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="checkin_date"><fmt:message key="message.checkinDate"/></label>
                                <div class="col-md-auto">
                                    <input id="checkin_date" value="${checkin_date}" name="checkin_date" type="text" placeholder=""
                                           class="form-control input-md" required="">
                                    <span class="help-block">* 01.01.2018</span>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row justify-content-left align-items-start">
                        <!-- Password input-->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="checkout_date"><fmt:message key="message.checkoutDate"/></label>
                                <div class="col-md-auto">
                                    <input id="checkout_date" value="${checkout_date}" name="checkout_date" type="text"
                                           placeholder="" class="form-control input-md" required="">
                                    <span class="help-block">* 05.01.2018</span>
                                </div>
                            </div>
                        </div>


                        <!-- Text input-->

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="room_class"><fmt:message key="message.roomClass"/></label>
                                <div class="col-md-auto">
                                    <input id="room_class" value="${room_class}" name="room_class" type="text"
                                           placeholder="" class="form-control input-md" required="">
                                    <span class="help-block">normal / luxe / president</span>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Button (Double) -->
                    <div class="form-group">
                        <div class="col-md-auto text-right">
                            <button id="saveinfo" name="saveinfo" class="btn btn-primary" style="font-size: 10pt;">
                                <fmt:message key="message.send"/>
                            </button>
                        </div>
                    </div>
                </div>

            </div>
        </fieldset>
    </form>
</div>
</body>
</html>


