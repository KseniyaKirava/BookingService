<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="mytag" tagdir="/WEB-INF/tags" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <br>


    <div class="row justify-content-left align-items-start">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-auto">
                    <legend style="font-size: 18px;">
                        <fmt:message key="message.searchResults"/>
                    </legend>
                </div>
            </div>
        </div>
    </div>

    <c:if test="${reservationNotFound != null}">
        <div class="form-group">
            <div class="alert alert-danger" role="alert">
                <label class="col-md-12 control-label" style="font-size: 16px;">
                        ${reservationNotFound}
                </label>
            </div>
        </div>
    </c:if>

    <br>

    <div class="container">
        <c:forEach items="${rooms}" var="rooms">
            <form class="form-horizontal" method="post" command="do?command=Search">

                <div class="row justify-content-left align-items-start">
                    <div class="col-md-5">
                        <c:if test="${rooms.id == 1}">
                            <img src="../image/room/1.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 2}">
                            <img src="../image/room/2.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 3}">
                            <img src="../image/room/3.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 4}">
                            <img src="../image/room/4.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 5}">
                            <img src="../image/room/5.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 6}">
                            <img src="../image/room/6.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 7}">
                            <img src="../image/room/7.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                        <c:if test="${rooms.id == 8}">
                            <img src="../image/room/8.jpg" alt="room" width="450" border="0"/>
                        </c:if>
                    </div>

                    <div class="col-md-6">
                        <p style="font-weight: bold"><c:if test="${rooms.name == 'двухместный номер с террасой'}">
                            <fmt:message key="message.roomId1"/>
                        </c:if>
                            <c:if test="${rooms.name == 'двухместный номер с видом на океан'}">
                                <fmt:message key="message.roomId2"/>
                            </c:if>
                            <c:if test="${rooms.name == 'одноместный номер'}">
                                <fmt:message key="message.roomId3"/>
                            </c:if>
                            <c:if test="${rooms.name == 'двухместный номер с 2 кроватями'}">
                                <fmt:message key="message.roomId4"/>
                            </c:if>
                            <c:if test="${rooms.name == 'четырехместный номер'}">
                                <fmt:message key="message.roomId5"/>
                            </c:if>
                            <c:if test="${rooms.name == 'двухместный номер с диваном'}">
                                <fmt:message key="message.roomId6"/>
                            </c:if>
                            <c:if test="${rooms.name == 'одноместный номер'}">
                                <fmt:message key="message.roomId7"/>
                            </c:if>
                            <c:if test="${rooms.name == 'президентский номер с видом на океан'}">
                                <fmt:message key="message.roomId8"/>
                            </c:if>
                        </p>

                        <p><c:if test="${rooms.averageAssessment == 0.0}">
                            <fmt:message key="message.rating"/>: n/a
                            </c:if>
                            <c:if test="${rooms.averageAssessment != 0.0}">
                                <fmt:message key="message.rating"/>: ${rooms.averageAssessment}
                            </c:if>
                        </p>

                        <p>
                            <fmt:message key="message.roomCapacity"/>: ${rooms.capacity}
                        </p>

                        <p>
                            <fmt:message key="message.cost"/>: ${rooms.cost} <fmt:message
                                key="message.currency"/>
                        </p>

                        <br>
                        <br>
                        <br>
                        <br>
                        <button id="booking" name="booking" class="btn btn-dark">
                            <fmt:message key="message.book"/>
                        </button>

                    </div>

                    <input id="id" class="form-control" type="hidden" name="id"
                           value="${rooms.id}"/>
                    <input id="cost" class="form-control" type="hidden" name="cost"
                           value="${rooms.cost}"/>
                </div>

                <hr>
            </form>
        </c:forEach>
    </div>

    <br>

    <c:if test="${rooms != null}">

        <hr>

        <div class="row">
            <mytag:paginator count="${size}" step="${rowPerPage}" urlprefix="?command=Search&start="/>
        </div>
    </c:if>


    <br>

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


