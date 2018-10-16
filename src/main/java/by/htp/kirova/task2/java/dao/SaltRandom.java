package by.htp.kirova.task2.java.dao;

import java.util.Random;

/**
 * Class containing encryption methods.
 *
 * @author Kseniya Kirava
 * @since Sept 24, 2018
 */
public class SaltRandom {

    /**
     * Salt-generating password encryption method.
     *
     * @return java.lang.String unique salt for password.
     */
    public static String getSaltString() {
        final String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random random = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (random.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
}
