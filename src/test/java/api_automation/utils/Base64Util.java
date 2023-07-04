package api_automation.utils;

import java.util.Base64;

import org.junit.Test;

public class Base64Util {

    /**
     * @param String password
     * @return String
     * @author Elshan R
     * @Description This methods encrypts password
     */

    public static String encryptedPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());

    }

    /**
     * @param String password
     * @return String
     * @author Elshan R
     * @Description This methods decodes encrypted password
     */
    public static String decryptedPassword(String password) {

        return new String(Base64.getDecoder().decode(password));
    }

    /**
     * @param String password
     * @return String
     * @author Elshan R
     * @Description This methods decodes encrypted URL
     */
    public static String encryptedURL(String url) {
        return Base64.getUrlEncoder().encodeToString(url.getBytes());

    }

    /**
     * @param String password
     * @return String
     * @author Elshan R
     * @Description This methods encrypts URL
     */
    public static String decryptedURL(String password) {
        return new String(Base64.getUrlDecoder().decode(password));
    }






    /*
     * Following code is for to test the methods above
     *
     * To run the code uncomment @Test
     *
     */


    //	@Test
    public void test1() {
        String a = encryptedPassword("YollAcademy");
        System.out.println(a);
    }

    //	@Test
    public void test2() {
        String a = decryptedPassword("WW9sbEFjYWRlbXk=");
        System.out.println(a);
    }

    //	@Test
    public void test3() {
        String a = encryptedURL("https://34.235.0.4/api/tokenauth/authenticate");
        System.out.println(a);
    }

    //	@Test
    public void test4() {
        String a = decryptedURL("aHR0cHM6Ly8zNC4yMzUuMC40L2FwaS90b2tlbmF1dGgvYXV0aGVudGljYXRl");
        System.out.println(a);
    }

}