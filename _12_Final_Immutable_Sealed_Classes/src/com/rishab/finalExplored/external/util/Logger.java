package com.rishab.finalExplored.external.util;

import java.time.LocalDateTime;

public class Logger {
    public static void logToConsole(CharSequence message) {
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("%1$tD %1$tT : %2$s%n", now, message);

        if (message instanceof StringBuilder sb) {
            sb.setLength(0);
        }
    }
}
