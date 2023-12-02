package com.rishab.resourceBundle;

import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {

        for (Locale l : List.of(Locale.US, Locale.CANADA_FRENCH, Locale.CANADA)) {
            ResourceBundle bundle = ResourceBundle.getBundle("BasicText", l);
            // System.out.println(bundle.getClass().getName());
            // System.out.println(bundle.getBaseBundleName());
            // System.out.println(bundle.keySet());

            String message = "%s %s!%n".formatted(bundle.getString("hello"), bundle.getString("world"));

            ResourceBundle ui = ResourceBundle.getBundle("UIComponents", l);

            JOptionPane.showOptionDialog(null, message, ui.getString("first.title"),
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new Object[]{bundle.getString("yes"), bundle.getString("no")}, null);
        }

    }
}
