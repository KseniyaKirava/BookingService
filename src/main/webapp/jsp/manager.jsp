<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>


    <fieldset>

        <br>

        <div class="row justify-content-left align-items-start">
            <div class="col-md-12">

                <div class="row">
                    <div class="col-md-6 text-left">
                        <div class="form-group">
                            <div class="col-md-auto">
                                <legend style="font-size: 18px;">
                                    <fmt:message key="message.managerReservations"/>
                                </legend>
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
                <c:if test="${tooEarly != null}">
                    <div class="form-group">
                        <div class="alert alert-info" role="alert">
                            <label class="col-md-12 control-label" style="font-size: 16px;">
                                    ${tooEarly}
                            </label>
                        </div>
                    </div>
                </c:if>


                <div class="row justify-content-left align-items-start">
                    <div class="col-md-12">

                        <table class="table">
                            <thead class="thead-default">
                            <tr>
                                <th style="font-size:14px;">ID</th>
                                <th style="font-size:14px;"><fmt:message key="message.firstName"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.lastName"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.checkinDateTable"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.checkoutDateTable"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.roomClass"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.roomName"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.roomNumber"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.roomCapacity"/></th>
                                <th style="font-size:14px;"><fmt:message key="message.totalCost"/>,
                                    <fmt:message key="message.currency"/></th>
                                <th></th>

                            </tr>
                            </thead>
                            <c:forEach items="${reservations}" var="row">
                                <form class="form-inline" action="do?command=Reserve" method=POST>
                                    <tbody>
                                    <tr>
                                        <input id="reservationId" name="reservationId" type="hidden"
                                               value="${row.reservationId}"
                                               class="form-control input-md" style="font-size: 14px;">

                                        <th scope="row" style="font-size:14px;">${row.reservationId}</th>

                                        <jsp:useBean id="reservationDate" class="java.util.Date"/>
                                        <jsp:setProperty name="reservationDate" property="time"
                                                         value="${row.reservationDate}"/>
                                        <td style="font-size:14px;">
                                            <fmt:formatDate pattern="dd.MM.YYYY" value="${reservationDate}"/>
                                        </td>

                                        <jsp:useBean id="checkinDate" class="java.util.Date"/>
                                        <jsp:setProperty name="checkinDate" property="time"
                                                         value="${row.checkinDate}"/>
                                        <td style="font-size:14px;">
                                            <fmt:formatDate pattern="dd.MM.YYYY" value="${checkinDate}"/>
                                        </td>

                                        <jsp:useBean id="checkoutDate" class="java.util.Date"/>
                                        <jsp:setProperty name="checkoutDate" property="time"
                                                         value="${row.checkoutDate}"/>
                                        <td style="font-size:14px;">
                                            <fmt:formatDate pattern="dd.MM.YYYY" value="${checkoutDate}"/>
                                        </td>

                                        <input id="checkoutDate" name="checkoutDate" type="hidden"
                                               value="${row.checkoutDate}"
                                               class="form-control input-md" style="font-size: 14px;">

                                        <td style="font-size:14px;">
                                            <c:if test="${row.roomName == 'двухместный номер с террасой'}">
                                                <fmt:message key="message.roomId1"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'двухместный номер с видом на океан'}">
                                                <fmt:message key="message.roomId2"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'одноместный номер'}">
                                                <fmt:message key="message.roomId3"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'двухместный номер с 2 кроватями'}">
                                                <fmt:message key="message.roomId4"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'четырехместный номер'}">
                                                <fmt:message key="message.roomId5"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'двухместный номер с диваном'}">
                                                <fmt:message key="message.roomId6"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'одноместный номер'}">
                                                <fmt:message key="message.roomId7"/>
                                            </c:if>
                                            <c:if test="${row.roomName == 'президентский номер с видом на океан'}">
                                                <fmt:message key="message.roomId8"/>
                                            </c:if>
                                        </td>

                                        <td style="font-size:14px;">${row.roomNumber}</td>
                                        <td style="font-size:14px;">${row.roomCapacity}</td>

                                        <td style="font-size:14px;">
                                            <c:if test="${row.roomClassName == 'люкс'}">
                                                <fmt:message key="message.roomLuxe"/>
                                            </c:if>
                                            <c:if test="${row.roomClassName == 'стандартный'}">
                                                <fmt:message key="message.roomStandard"/>
                                            </c:if>
                                            <c:if test="${row.roomClassName == 'президентский'}">
                                                <fmt:message key="message.roomPresident"/>
                                            </c:if>
                                        </td>

                                        <td style="font-size:14px;">${row.totalCost}</td>

                                        <td style="font-size:14px;">
                                            <c:if test="${row.assessment == 0}">
                                                <input id="assessment" name="assessment" type="number"
                                                       value="" min="1" max="5"
                                                       class="form-control input-md" style="font-size: 14px;">
                                            </c:if>
                                            <c:if test="${row.assessment != 0}">
                                                <input id="assessment" name="assessment" type="number"
                                                       value="${row.assessment}" min="1" max="5"
                                                       class="form-control input-md" style="font-size: 14px;">
                                            </c:if>

                                        </td>

                                        <td style="font-size:14px;">
                                            <button id="rate" name="rate" class="btn btn-dark" style="font-size: 14px;">
                                                <fmt:message key="message.rate"/>
                                            </button>
                                        </td>

                                    </tr>

                                    </tbody>
                                </form>
                            </c:forEach>

                        </table>



                        <c:if test="${reservations != null}">

                            <hr>

                            <div class="row">
                                <mytag:paginator count="${size}" step="${rowPerPage}"
                                                 urlprefix="?command=Reserve&start="/>
                            </div>
                        </c:if>

                    </div>
                </div>

            </div>
        </div>
    </fieldset>
    <%--</form>--%>
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


