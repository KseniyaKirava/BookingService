package by.htp.kirova.task2.controller;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Message manager for messages.
 *
 * @author Kirava Kseniya
 * @since Oct 12, 2018
 */
public class MessageManager {

    private static final String resourceName = "message";

    /**
     * The session attribute language constant.
     */
    private final static String LANG = "lang";

    public static ResourceBundle getSessionResourceBundle(HttpSession session) {
        String lang = session.getAttribute(LANG).toString();
        Locale locale = Locale.forLanguageTag(lang);
        return ResourceBundle.getBundle(resourceName, locale);
    }

    public static String getMessageInSessionLanguage(HttpSession session, String messageKey) {
        ResourceBundle resourceBundle = getSessionResourceBundle(session);
        return resourceBundle.getString(messageKey);
    }

//    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
//
//    private MessageManager(Locale locale) {
//        resourceBundle = ResourceBundle.getBundle("message", locale);
//    }
//
//    public String getString(String key) {
//        return resourceBundle.getString(key);
//    }
}



