package by.htp.kirova.task2.service.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TodayTag extends TagSupport {
    private String format;

    public void setFormat(String writeFormat) {
        format = writeFormat;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            Date today = new Date();
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            JspWriter out = pageContext.getOut();
            out.write(dateFormatter.format(today));

        } catch (IOException e) {
            throw new JspException("Error: " + e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
