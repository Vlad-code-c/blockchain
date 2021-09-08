package log;

import java.time.LocalTime;


public class Logger {



    public static void log(String e) {
        System.out.println(
                Colors.RED + "LOG: " +
                fillString(Thread.currentThread().getName(), 10) + "|   " +
                fillString(getTime(), 20) + "|   " +
                e + Colors.RESET);
    }


    private static String fillString(String str, int length) {
        if (str.length() >= length) return str;
        StringBuilder strBuilder = new StringBuilder(str);
        while (strBuilder.length() < length) {
            strBuilder.append(" ");
        }
        return strBuilder.toString();
    }

    private static String getTime() {
        return LocalTime.now().toString();
    }
}
