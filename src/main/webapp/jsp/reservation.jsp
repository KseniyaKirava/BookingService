<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>


    <form class="form-horizontal" method="get" command="do?command=Reservation">
        <fieldset>
            <!-- Form Name -->


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
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Profile">
                                <fmt:message key="message.myBills"/></a>
                        </li>

                    </ul>

                </div>


                <div class="col-md-10">
                    <!-- Text input-->
                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-12">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend style="font-size: 14pt;">
                                        <fmt:message key="message.resultQuery"/>
                                    </legend>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-12">


                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-auto control-label" for="room_name"  style="font-size: 11pt;">
                                        <fmt:message key="message.roomName"/>
                                    </label>
                                    <div class="col-md-auto">
                                        <input id="room_name" value="${room_name}" name="room_name"
                                               type="text" class="form-control input-md"  style="font-size: 11pt;"
                                               required="" readonly/>

                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-auto control-label" for="room_number" style="font-size: 11pt;">
                                        <fmt:message key="message.roomNumber"/>
                                    </label>
                                    <div class="col-md-auto">
                                        <input id="room_number" value="${room_number}" name="room_number" type="text"
                                               placeholder="" style="font-size: 11pt;"
                                               class="form-control input-md" required="" readonly/>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <!-- Password input-->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-auto control-label" for="capacity" style="font-size: 11pt;">
                                        <fmt:message key="message.capacity"/>
                                    </label>
                                    <div class="col-md-auto">
                                        <input id="capacity" value="${capacity}" name="capacity" type="text"
                                               placeholder="" class="form-control input-md" required=""
                                               style="font-size: 11pt;" readonly/>
                                    </div>
                                </div>
                            </div>


                            <!-- Text input-->

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-auto control-label" for="check_in" style="font-size: 11pt;">
                                        <fmt:message key="message.checkinDate"/>
                                    </label>
                                    <div class="col-md-auto">
                                        <jsp:useBean id="checkIn" class="java.util.Date"/>
                                        <jsp:setProperty name="checkIn" property="time"
                                                         value="${check_in}"/>
                                        <fmt:formatDate var="formatted_check_in"
                                                        pattern="dd.MM.YYYY" value="${checkIn}"/>
                                        <input id="check_in" value="${formatted_check_in}" style="font-size: 11pt;"
                                               name="check_in" type="text" placeholder="" class="form-control input-md"
                                               required="" readonly/>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-12">
                            <!-- Text input-->
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-auto control-label" for="check_out" style="font-size: 11pt;">
                                        <fmt:message key="message.checkoutDate"/>
                                    </label>
                                    <div class="col-md-auto">
                                        <jsp:useBean id="checkOut" class="java.util.Date"/>
                                        <jsp:setProperty name="checkOut" property="time"
                                                         value="${check_out}"/>
                                        <fmt:formatDate var="formatted_check_out"
                                                        pattern="dd.MM.YYYY" value="${checkOut}"/>
                                        <input id="check_out" value="${formatted_check_out}" name="check_out" type="text"
                                               placeholder="" class="form-control input-md" required="" style="font-size: 11pt;"
                                               readonly/>

                                    </div>
                                </div>
                            </div>


                            <!-- Text input-->

                            <div class="col-md-6">
                                <div class="form-group">
                                    <label class="col-md-auto control-label" for="total_cost" style="font-size: 11pt;">
                                        <fmt:message key="message.totalCost"/>
                                    </label>
                                    <div class="col-md-auto">
                                        <input id="total_cost" value="${total_cost}" name="total_cost" type="text"
                                               placeholder="" class="form-control input-md" style="font-size: 11pt;"
                                               readonly/>

                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
        </fieldset>
    </form>
</div>
</body>
</html>


