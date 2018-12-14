<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <form class="form-horizontal" method="post" command="do?command=Main">

        <fieldset>
            <br>

            <c:if test="${errorSearchCommand != null}">
                <div class="form-group">
                    <div class="alert alert-danger" role="alert">
                        <label class="col-md-12 control-label" style="font-size: 16px;">
                                ${errorSearchCommand}
                        </label>
                    </div>
                </div>
            </c:if>


            <div class="row justify-content-left align-items-end">

                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="roomCapacity" style="font-size: 16px;">
                            <fmt:message key="message.roomCapacity"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="roomCapacity" value="${roomCapacity}" name="roomCapacity"
                                   type="number" min="1" max="5" class="form-control input-md"
                                   required="" placeholder="2" style="font-size: 16px;"/>

                        </div>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="checkinDate" style="font-size: 16px;">
                            <fmt:message key="message.checkinDate"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="checkinDate" value="${checkinDate}" name="checkinDate" type="text"
                                   class="form-control input-md" required="" style="font-size: 16px;"
                                   pattern="([0-9]{2})([\.])([0-9]{2})([\.])([0-9]{4})"/>

                        </div>
                    </div>
                </div>


                <script>
                    $(function () {
                        $("#checkinDate").daterangepicker({
                            singleDatePicker: true,
                            minDate: new Date(),
                            locale: {
                                format: 'DD.MM.YYYY'
                            }
                        });
                    });
                </script>


                <div class="col-md-2">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="checkoutDate" style="font-size: 16px;">
                            <fmt:message key="message.checkoutDate"/>
                        </label>
                        <div class="col-md-auto">
                            <input id="checkoutDate" value="${checkoutDate}" name="checkoutDate" type="text"
                                   style="font-size: 16px;" placeholder="" class="form-control input-md"
                                   required="" pattern="([0-9]{2})([\.])([0-9]{2})([\.])([0-9]{4})"/>
                        </div>
                    </div>
                </div>

                <script>
                    $(function () {
                        $("#checkoutDate").daterangepicker({
                            singleDatePicker: true,
                            minDate: new Date(),
                            locale: {
                                format: 'DD.MM.YYYY'
                            }
                        });
                    });
                </script>


                <div class="col-md-3">
                    <div class="form-group">
                        <label class="col-md-auto control-label" for="roomClassId" style="font-size: 16px;">
                            <fmt:message key="message.roomClass"/>
                        </label>
                        <div class="col-md-auto">
                            <select data-style="btn-dark" id="roomClassId" name="roomClassId" type="text"
                                    class="form-control input-md">
                                <option selected value="2" style="font-size: 16px;">
                                    <fmt:message key="message.roomStandard"/>
                                </option>
                                <option value="1" style="font-size: 16px;">
                                    <fmt:message key="message.roomLuxe"/>
                                </option>
                                <option value="3" style="font-size: 16px;">
                                    <fmt:message key="message.roomPresident"/>
                                </option>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="col-md-1">
                    <div class="form-group">
                        <div class="col-md-auto text-right">
                            <button id="search" name="search" class="btn btn-dark" style="font-size: 14px;">
                                <fmt:message key="message.search"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>

            <br>

        </fieldset>
    </form>


    <div class="row justify-content-left align-items-start">
        <div class="col-md-12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="carousel-item active">
                        <img src="../image/slider/k1.jpg" alt="First slide">
                    </div>
                    <div class="carousel-item">
                        <img src="../image/slider/k2.jpg" alt="Second slide">
                    </div>
                    <div class="carousel-item">
                        <img src="../image/slider/k3.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="left carousel-control" href="#carousel-example-generic" role="button"
                   data-slide="prev">
                    <span class="icon-prev" aria-hidden="true"></span>
                    <span class="sr-only"><fmt:message key="message.previous"/></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button"
                   data-slide="next">
                    <span class="icon-next" aria-hidden="true"></span>
                    <span class="sr-only"><fmt:message key="message.next"/></span>
                </a>
            </div>
        </div>
    </div>


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


