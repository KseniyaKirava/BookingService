<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>

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
                            <a class="nav-link" style="font-size: 12pt; color: #949494;" href="do?command=Reserve">
                                <fmt:message key="message.myBills"/></a>
                        </li>

                    </ul>

                </div>


                <div class="col-md-10">

                    <div class="row">
                        <div class="col-md-6 text-left">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend style="font-size: 14pt;">
                                        <fmt:message key="message.myRequest"/>
                                    </legend>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-12">
                            <form class="form-inline" method="get">

                                <table class="table">
                                    <thead class="thead-default">
                                    <tr>
                                        <th style="font-size:11pt;">ID</th>
                                        <th style="font-size:11pt;"><fmt:message key="message.capacity"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.checkinDateTable"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.checkoutDateTable"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.roomClassTable"/></th>
                                    </tr>
                                    </thead>
                                    <c:forEach items="${requests}" var="row">
                                        <tbody>
                                        <tr>
                                            <th scope="row" style="font-size:11pt;">${row.id}</th>
                                            <td style="font-size:11pt;">${row.room_capacity}</td>

                                            <jsp:useBean id="checkInDate" class="java.util.Date"/>
                                            <jsp:setProperty name="checkInDate" property="time"
                                                             value="${row.checkin_date}"/>
                                            <fmt:formatDate var="formattedCheckInDate"
                                                            pattern="dd.MM.YYYY" value="${checkInDate}"/>
                                            <td style="font-size:11pt;">${formattedCheckInDate}</td>

                                            <jsp:useBean id="checkOutDate" class="java.util.Date"/>
                                            <jsp:setProperty name="checkOutDate" property="time"
                                                             value="${row.checkout_date}"/>
                                            <fmt:formatDate var="formattedCheckOutDate"
                                                            pattern="dd.MM.YYYY" value="${checkOutDate}"/>
                                            <td style="font-size:11pt;">${formattedCheckOutDate}</td>

                                            <td style="font-size:11pt;">${row.room_class}</td>
                                            <td style="font-size:11pt;" hidden>${row.enabled}</td>
                                            <td style="font-size:11pt;" hidden>${row.users_username}</td>

                                        </tr>
                                        </tbody>
                                    </c:forEach>

                                </table>

                                <hr>
                                <div class="row">
                                        <mytag:paginator count="${size}" step="10" urlprefix="?command=Request&start="/>
                                </div>
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


