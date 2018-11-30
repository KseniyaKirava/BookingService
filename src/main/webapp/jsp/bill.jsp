<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>


    <form class="form-horizontal" method="post" command="do?command=Bill">
        <fieldset>
            <div class="row justify-content-left align-items-start">

                <div class="col-md-12">
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
                                        <th style="font-size:11pt;"><fmt:message key="message.totalCost"/>,
                                            <fmt:message key="message.currency"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td style="font-size:11pt;">${roomName}</td>
                                        <td style="font-size:11pt;">${roomNumber}</td>
                                        <td style="font-size:11pt;">${capacity}</td>


                                        <jsp:useBean id="checkIn" class="java.util.Date"/>
                                        <jsp:setProperty name="checkIn" property="time"
                                                         value="${checkIn}"/>
                                        <fmt:formatDate var="formattedCheckIn"
                                                        pattern="dd.MM.YYYY" value="${checkIn}"/>
                                        <td style="font-size:11pt;">${formattedCheckIn}</td>


                                        <jsp:useBean id="checkOut" class="java.util.Date"/>
                                        <jsp:setProperty name="checkOut" property="time"
                                                         value="${checkOut}"/>
                                        <fmt:formatDate var="formattedCheckOut"
                                                        pattern="dd.MM.YYYY" value="${checkOut}"/>
                                        <td style="font-size:11pt;">${formattedCheckOut}</td>

                                        <th scope="row" style="font-size:11pt;">${totalCost}</th>

                                    </tr>
                                    </tbody>
                                </table>

                            </form>
                        </div>
                    </div>


                    <div class="col-md-12 text-left">
                        <div class="form-group">
                            <div class="col-md-auto">
                                <button id="pay" name="pay" class="btn btn-white" style="font-size: 14px;">
                                    <fmt:message key="message.pay"/>
                                </button>
                                <button id="cancel" name="cancel" class="btn btn-dark" style="font-size: 14px;">
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


