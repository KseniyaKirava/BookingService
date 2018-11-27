<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="http://bootstraptema.ru/snippets/form/2017/styles.css" rel="stylesheet">
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
<%--<script src="http://bootstraptema.ru/snippets/form/2017/jquery.payform.min.js"></script>--%>
<%--<script src="http://bootstraptema.ru/snippets/form/2017/script.js"></script>--%>

<html>
<%@ include file="../include/head.jspf" %>
<body>
<fmt:setBundle basename="message"/>
<form class="form-horizontal" method="post" command="do?command=Payment">
    <div class="container">
        <div class="row">

            <div class="creditCardForm">
                <div class="heading">
                    <h1 style="font-size: 12pt"><fmt:message key="message.summary"/>${totalCost}
                        <fmt:message key="message.currency"/></h1></div>
                <div class="payment">
                    <form>
                        <div class="form-group owner">
                            <label for="owner" style="font-size: 11pt"><fmt:message key="message.owner"/></label>
                            <input type="text" class="form-control" style="font-size: 11pt" id="owner"
                                   placeholder="IVAN IVANOV"/>
                        </div>
                        <div class="form-group CVV">
                            <label for="cvv" style="font-size: 11pt">CVV</label>
                            <input type="text" class="form-control" style="font-size: 11pt" id="cvv"
                                   placeholder="000"/>
                        </div>
                        <div class="form-group" id="card-number-field">
                            <label for="cardNumber" style="font-size: 11pt"><fmt:message
                                    key="message.cardNumber"/></label>
                            <input type="text" class="form-control" style="font-size: 11pt" id="cardNumber"
                                   placeholder="0000 0000 0000 0000"/>
                        </div>
                        <div class="form-group" id="expiration-date">
                            <label style="font-size: 11pt"><fmt:message key="message.expirationDate"/></label>
                            <select data-style="btn-primary">
                                <option value="01" style="font-size: 11pt">01</option>
                                <option value="02" style="font-size: 11pt">02</option>
                                <option value="03" style="font-size: 11pt">03</option>
                                <option value="04" style="font-size: 11pt">04</option>
                                <option value="05" style="font-size: 11pt">05</option>
                                <option value="06" style="font-size: 11pt">06</option>
                                <option value="07" style="font-size: 11pt">07</option>
                                <option value="08" style="font-size: 11pt">08</option>
                                <option value="09" style="font-size: 11pt">09</option>
                                <option value="10" style="font-size: 11pt">10</option>
                                <option value="11" style="font-size: 11pt">11</option>
                                <option value="12" style="font-size: 11pt">12</option>
                            </select>
                            <select data-style="btn-primary">
                                <option value="18" style="font-size: 11pt"> 2018</option>
                                <option value="19" style="font-size: 11pt"> 2019</option>
                                <option value="20" style="font-size: 11pt"> 2020</option>
                                <option value="21" style="font-size: 11pt"> 2021</option>
                                <option value="22" style="font-size: 11pt"> 2022</option>
                                <option value="23" style="font-size: 11pt"> 2023</option>
                            </select>
                        </div>
                        <div class="form-group" id="credit_cards">
                            <img src="http://bootstraptema.ru/snippets/form/2017/visa.jpg" id="visa">
                            <img src="http://bootstraptema.ru/snippets/form/2017/mastercard.jpg" id="mastercard">
                        </div>
                        <div class="form-group" id="pay-now">
                            <button type="submit" class="btn btn-primary" id="confirm-purchase"
                                    style="font-size: 11pt;">
                                <fmt:message key="message.confirm"/>
                            </button>
                            <button type="submit" class="btn btn-primary" id="cancel-purchase"
                                    style="font-size: 11pt;">
                                <fmt:message key="message.cancel"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </div>
</form>
</body>
</html>