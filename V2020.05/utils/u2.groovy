package utils

import java.time.*

public class u2 {
    public static String sayHello(String txt) {
     
    return "DATE :: " + LocalDateTime.now().toLocalDate() + " | TIME :: " + LocalDateTime.now().toLocalTime() + " | MSG :: " + txt
    }
}