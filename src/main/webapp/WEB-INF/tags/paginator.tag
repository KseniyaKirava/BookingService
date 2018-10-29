<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="message"/>

<%@ attribute name="count" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="step" required="true" rtexprvalue="true" type="java.lang.Integer" %>
<%@ attribute name="urlprefix" required="true" rtexprvalue="true" type="java.lang.String" %>


<div class="form-group">
    <div class="col-md-auto text-right">
        <button class="btn btn-light">
            <%
                for (int i = 0; i <= (count - 1) / step; i++) {
                    out.println(String.format("\t&nbsp<a href='%s%d' style=\"font-size: 10pt; color: black; font-weight: bold\"> %d </a>", urlprefix, i * step, i + 1));
                }
            %>
        </button>
    </div>
</div>
