<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="http://bootstraptema.ru/snippets/form/2017/styles.css" rel="stylesheet">

<html>
<%@ include file="../include/head.jspf" %>
<body>


<fmt:requestEncoding value="UTF-8"/>
<c:set var="lang" value="${not empty lang ? lang : pageContext.request.locale.toLanguageTag()}"
       scope="session"/>
<fmt:setLocale value="${lang}"/>
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
                            <label for="owner" style="font-size: 16px"><fmt:message key="message.owner"/></label>
                            <input type="text" class="form-control" style="font-size: 16px" id="owner"
                                   placeholder="IVAN IVANOV" value="IVAN IVANOV"/>
                        </div>
                        <div class="form-group CVV">
                            <label for="cvv" style="font-size: 16px">CVV</label>
                            <input type="text" class="form-control" style="font-size: 16px" id="cvv"
                                   placeholder="000" value="123"/>
                        </div>
                        <div class="form-group" id="card-number-field">
                            <label for="cardNumber" style="font-size: 16px"><fmt:message
                                    key="message.cardNumber"/></label>
                            <input type="text" class="form-control" style="font-size: 16px" id="cardNumber"
                                   placeholder="0000 0000 0000 0000" value="1589 6889 8987 8979"/>
                        </div>
                        <div class="form-group" id="expiration-date">
                            <label style="font-size: 16px"><fmt:message key="message.expirationDate"/></label>
                            <select data-style="btn-primary">
                                <option value="01" style="font-size: 16px">01</option>
                                <option value="02" style="font-size: 16px">02</option>
                                <option value="03" style="font-size: 16px">03</option>
                                <option value="04" style="font-size: 16px">04</option>
                                <option value="05" style="font-size: 16px">05</option>
                                <option value="06" style="font-size: 16px">06</option>
                                <option value="07" style="font-size: 16px">07</option>
                                <option value="08" style="font-size: 16px">08</option>
                                <option value="09" style="font-size: 16px">09</option>
                                <option value="10" style="font-size: 16px">10</option>
                                <option value="11" style="font-size: 16px">11</option>
                                <option value="12" style="font-size: 16px">12</option>
                            </select>
                            <select data-style="btn-primary">
                                <option value="18" style="font-size: 16px"> 2018</option>
                                <option value="19" style="font-size: 16px"> 2019</option>
                                <option value="20" style="font-size: 16px"> 2020</option>
                                <option value="21" style="font-size: 16px"> 2021</option>
                                <option value="22" style="font-size: 16px"> 2022</option>
                                <option value="23" style="font-size: 16px"> 2023</option>
                            </select>
                        </div>
                        <div class="form-group" id="credit_cards">
                            <img src="../image/payment/visa.jpg" id="visa">
                            <img src="../image/payment/mastercard.jpg" id="mastercard">
                        </div>
                        <div class="form-group" id="pay-now">
                            <button type="submit" class="btn btn-primary" id="confirm-purchase"
                                    style="font-size: 14px;"><fmt:message key="message.confirm"/>
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