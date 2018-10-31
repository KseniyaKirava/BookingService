<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <form class="form-horizontal" method="post" command="do?command=AddRequest">

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
                                        <fmt:message key="message.addrequest"/>
                                    </legend>
                                </div>
                            </div>
                        </div>
                    </div>


                    <!-- Text input-->
                    <div class="row justify-content-left align-items-start">

                        <div class="form-group">
                            <label class="col-md-12 control-label" style="color: red; font-size: 12pt;">
                                ${messageReservationNotFound}${incorrectData}
                            </label>
                        </div>
                    </div>

                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="roomCapacity" style="font-size: 11pt;">
                                    <fmt:message key="message.roomcapacity"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="roomCapacity" value="${roomCapacity}" name="roomCapacity"
                                           type="number" min="1" max="5" class="form-control input-md"
                                           required="" style="font-size: 11pt;"/>

                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="checkinDate" style="font-size: 11pt;">
                                    <fmt:message key="message.checkinDate"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="checkinDate" value="${checkinDate}" name="checkinDate" type="text"
                                           class="form-control input-md" required="" style="font-size: 11pt;"
                                           pattern="([0-9]{2})([\.])([0-9]{2})([\.])([0-9]{4})"/>

                                </div>
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


                    <div class="row justify-content-left align-items-start">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="checkoutDate" style="font-size: 11pt;">
                                    <fmt:message key="message.checkoutDate"/>
                                </label>
                                <div class="col-md-auto">
                                    <input id="checkoutDate" value="${checkoutDate}" name="checkoutDate" type="text"
                                           style="font-size: 11pt;" placeholder="" class="form-control input-md"
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


                        <!-- Text input-->

                        <div class="col-md-6">
                            <div class="form-group">
                                <label class="col-md-auto control-label" for="roomClass" style="font-size: 11pt;">
                                    <fmt:message key="message.roomClass"/>
                                </label>
                                <div class="col-md-auto">
                                    <select data-style="btn-primary" id="roomClass" name="roomClass" type="text"
                                            class="form-control input-md">
                                        <option selected value="standard" style="font-size: 11pt;">
                                            <fmt:message key="message.roomStandard"/>
                                        </option>
                                        <option value="luxe" style="font-size: 11pt;">
                                            <fmt:message key="message.roomLuxe"/>
                                        </option>
                                        <option value="president" style="font-size: 11pt;">
                                            <fmt:message key="message.roomPresident"/>
                                        </option>
                                    </select>
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


