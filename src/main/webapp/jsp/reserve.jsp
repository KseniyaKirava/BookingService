<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>


    <form class="form-horizontal" method="get" command="do?command=Request">
        <fieldset>



            <div class="row justify-content-left align-items-start">
                <div class="col-md-12">

                    <div class="row">
                        <div class="col-md-6 text-left">
                            <div class="form-group">
                                <div class="col-md-auto">
                                    <legend style="font-size: 18px;">
                                        <fmt:message key="message.myBills"/>
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
                                        <th style="font-size:16px;">ID</th>
                                        <th style="font-size:16px;"><fmt:message key="message.reservationDateTable"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.checkinDateTable"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.checkoutDateTable"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.roomName"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.roomNumber"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.capacity"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.roomClassTable"/></th>
                                        <th style="font-size:16px;"><fmt:message key="message.totalCost"/>,
                                            <fmt:message key="message.currency"/></th>

                                    </tr>
                                    </thead>
                                    <c:forEach items="${reservations}" var="row">
                                        <tbody>
                                        <tr>
                                            <th scope="row" style="font-size:16px;">${row[0]}</th>

                                            <jsp:useBean id="reservationDate" class="java.util.Date"/>
                                            <jsp:setProperty name="reservationDate" property="time"
                                                             value="${row[1]}"/>
                                            <fmt:formatDate var="formattedReservationDate"
                                                            pattern="dd.MM.YYYY" value="${reservationDate}"/>
                                            <td style="font-size:16px;">${formattedReservationDate}</td>

                                            <jsp:useBean id="checkInDate" class="java.util.Date"/>
                                            <jsp:setProperty name="checkInDate" property="time"
                                                             value="${row[2]}"/>
                                            <fmt:formatDate var="formattedCheckInDate"
                                                            pattern="dd.MM.YYYY" value="${checkInDate}"/>
                                            <td style="font-size:16px;">${formattedCheckInDate}</td>

                                            <jsp:useBean id="checkOutDate" class="java.util.Date"/>
                                            <jsp:setProperty name="checkOutDate" property="time"
                                                             value="${row[3]}"/>
                                            <fmt:formatDate var="formattedCheckOutDate"
                                                            pattern="dd.MM.YYYY" value="${checkOutDate}"/>
                                            <td style="font-size:16px;">${formattedCheckOutDate}</td>

                                            <td style="font-size:16px;">${row[4]}</td>
                                            <td style="font-size:16px;">${row[5]}</td>
                                            <td style="font-size:16px;">${row[6]}</td>
                                            <td style="font-size:16px;">${row[7]}</td>
                                            <td style="font-size:16px;">${row[8]}</td>

                                        </tr>
                                        </tbody>
                                    </c:forEach>

                                </table>

                                <hr>
                                <div class="row">
                                    <mytag:paginator count="${size}" step="10" urlprefix="?command=Reserve&start="/>
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


