package com.rishab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherChallenge {
    public static void main(String[] args) {

        String emailText = """
            john.boy@valid.com
            john.boy@invalid
            jane.doe-smith@valid.co.uk
            jane_Doe1976@valid.co.uk
            bob-1964@valid.net
            bob!@invalid.com
            elaine@valid-test.com.au
            elaineinvalid1983@.com
            david@valid.io
            david@invalid..com
            """;

        Pattern partialPattern = Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
        Matcher emailMatcher = partialPattern.matcher(emailText);

        emailMatcher.results()
            .forEach(matchResult -> System.out.printf("[username: %s, domain: %s]%n",
                matchResult.group(1), matchResult.group(2)));

        System.out.println("—".repeat(50));
        Pattern emailPattern = Pattern.compile("([\\w.-]+)@(([\\w-]+\\.)+[\\w-]{2,})");
        String[] emailSamples = emailText.lines().toArray(String[]::new);
        for (String email : emailSamples) {
            Matcher eMatcher = emailPattern.matcher(email);
            boolean matches = eMatcher.matches();
            System.out.print(email + " is " + (matches ? "VALID" : "INVALID"));

            if (matches) {
                System.out.printf(" [username: %s, domain: %s]%n", eMatcher.group(1), eMatcher.group(2));
            } else {
                System.out.println();
            }
        }

    }
}
