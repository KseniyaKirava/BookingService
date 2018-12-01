<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jspf" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jspf" %>

    <form class="form-horizontal" method="post" command="do?command=ComingSoon">

        <br>
        <br>
        <br>

        <div class="row justify-content-center align-items-start">
            <p style="color: black; height: 20px; font-weight: bold"><fmt:message key="message.comingSoon"/></p>
        </div>

        <br>

        <div class="row justify-content-center align-items-start">
            <img src="../image/comingsoon.png" alt="coming soon" width="250" border="0">
        </div>

        <br>
        <br>
        <br>

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




