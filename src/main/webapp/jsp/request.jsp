<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>


    <form class="form-horizontal" method="get" command="do?command=Request">
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

                    <div class="row">
                        <div class="col-md-6 text-left">
                            <div class="form-group">
                                <div class="col-md-auto" style="font-size: 10pt;">
                                    <legend><fmt:message key="message.myRequest"/></legend>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-12">

                            <form class="form-inline" method="get">
                                <c:forEach items="${requests}" var="row">
                                    <hr/>
                                    <div class="row justify-content-left align-items-start">
                                        <div class="col-md-12">

                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" for="id">ID</label>
                                                <div class="col-md-auto">
                                                    <input id="id" style="font-size:10pt;" readonly name="id"
                                                           class="form-control input-md" value="${row.id}"
                                                           title=""/>
                                                </div>
                                            </div>


                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" style="font-size:10pt;"
                                                       for="room_capacity">  <fmt:message key="message.capacity"/></label>
                                                <div class="col-md-auto">
                                                    <input id="room_capacity" style="font-size:10pt;" readonly
                                                           name="room_capacity" class="form-control input-md"
                                                           value="${row.room_capacity}" title=""/>
                                                </div>
                                            </div>

                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" for="checkin_date">  <fmt:message key="message.checkinDate"/></label>
                                                <div class="col-md-auto">
                                                    <jsp:useBean id="checkInDate" class="java.util.Date"/>
                                                    <jsp:setProperty name="checkInDate" property="time"
                                                                     value="${row.checkin_date}"/>
                                                    <fmt:formatDate var="formattedCheckInDate"
                                                                    pattern="dd.MM.YYYY" value="${checkInDate}"/>
                                                    <input id="checkin_date" style="font-size:10pt;" readonly
                                                           name="checkin_date" class="form-control input-md"
                                                           value="${formattedCheckInDate}" title=""/>
                                                </div>
                                            </div>

                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" for="checkout_date">  <fmt:message key="message.checkoutDate"/></label>
                                                <div class="col-md-auto">
                                                    <jsp:useBean id="checkOutDate" class="java.util.Date"/>
                                                    <jsp:setProperty name="checkOutDate" property="time"
                                                                     value="${row.checkout_date}"/>
                                                    <fmt:formatDate var="formattedCheckOutDate"
                                                                    pattern="dd.MM.YYYY" value="${checkOutDate}"/>
                                                    <input id="checkout_date" style="font-size:10pt;" readonly
                                                           name="checkout_date" class="form-control input-md"
                                                           title="" value="${formattedCheckOutDate}"/>
                                                </div>
                                            </div>


                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" for="room_class">  <fmt:message key="message.roomClass"/></label>
                                                <div class="col-md-auto">
                                                    <input id="room_class" style="font-size:10pt;" readonly
                                                           name="room_class" class="form-control input-md"
                                                           value="${row.room_class}" title=""/>
                                                </div>
                                            </div>

                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" for="enabled"></label>
                                                <div class="col-md-auto">
                                                    <input id="enabled" style="font-size:10pt;" readonly type="hidden"
                                                           name="enabled" class="form-control input-md"
                                                           value="${row.enabled}" title=""/>
                                                </div>
                                            </div>

                                            <div class="form-group mb-2">
                                                <label class="col-md-auto control-label" for="users_username"></label>
                                                <div class="col-md-auto">
                                                    <input id="users_username" style="font-size:10pt;" readonly
                                                           name="users_username" type="hidden"
                                                           class="form-control input-md"
                                                           value="${row.users_username}" title=""/>
                                                </div>
                                            </div>

                                        </div>

                                    </div>
                                </c:forEach>
                            </form>

                        </div>
                    </div>

                </div>
            </div>


        </fieldset>
    </form>
</div>
</body>
</html>


