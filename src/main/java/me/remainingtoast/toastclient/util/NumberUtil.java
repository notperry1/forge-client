package me.remainingtoast.toastclient.util;

public class NumberUtil {
    public static boolean isNumeric(String str) {
        try {
            int i = Integer.parseInt(str);
            System.out.println(i);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
