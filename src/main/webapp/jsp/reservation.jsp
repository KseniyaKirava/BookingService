<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.htm" %>
<body>
<div class="container">
    <%@ include file="../include/menu.htm" %>


    <form class="form-horizontal" method="post" command="do?command=Reservation">
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
                            <form class="form-inline" method="get">

                                <table class="table">
                                    <thead class="thead-default">
                                    <tr>
                                        <th style="font-size:11pt;"><fmt:message key="message.roomName"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.roomNumber"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.capacity"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.checkinDate"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.checkoutDate"/></th>
                                        <th style="font-size:11pt;"><fmt:message key="message.totalCost"/>, <fmt:message key="message.currency"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td style="font-size:11pt;">${room_name}</td>
                                        <td style="font-size:11pt;">${room_number}</td>
                                        <td style="font-size:11pt;">${capacity}</td>


                                        <jsp:useBean id="checkIn" class="java.util.Date"/>
                                        <jsp:setProperty name="checkIn" property="time"
                                                         value="${check_in}"/>
                                        <fmt:formatDate var="formatted_check_in"
                                                        pattern="dd.MM.YYYY" value="${checkIn}"/>
                                        <td style="font-size:11pt;">${formatted_check_in}</td>


                                        <jsp:useBean id="checkOut" class="java.util.Date"/>
                                        <jsp:setProperty name="checkOut" property="time"
                                                         value="${check_out}"/>
                                        <fmt:formatDate var="formatted_check_out"
                                                        pattern="dd.MM.YYYY" value="${checkOut}"/>
                                        <td style="font-size:11pt;">${formatted_check_out}</td>

                                        <th scope="row" style="font-size:11pt;">${total_cost}</th>

                                    </tr>
                                    </tbody>
                                </table>

                            </form>
                        </div>
                    </div>


                    <div class="col-md-12 text-left">
                        <div class="form-group">
                            <div class="col-md-auto">
                                <button id="pay" name="pay" class="btn btn-white" style="font-size: 10pt;">
                                    <fmt:message key="message.pay"/>
                                </button>
                                <button id="cancel" name="cancel" class="btn btn-primary" style="font-size: 10pt;">
                                    <fmt:message key="message.cancel"/>
                                </button>
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


