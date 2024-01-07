package com.rishab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Review {
    public static void main(String[] args) {

        String phoneList = """
            (800) 555-1212
            (800)555-1212
            (800) 555 1212
            800-555-1212
            800 555-1212
            800 555 1212
            8005551212
            """;

        Pattern phonePattern = Pattern.compile("\\(*[0-9]{3}[)\\s-]*\\d{3}[\\s-]*\\p{Digit}{4}");
        Matcher phoneMatcher = phonePattern.matcher(phoneList);
        phoneMatcher.results()
            .forEach(matchResult -> System.out.println(matchResult.group()));

        String htmlSnippet = """
            <H1>My Heading</h1>
            <h2>Sub-heading</h2>
            <p>This is a paragraph about something.</p>
            <p style="text-align: center;">Another paragraph here.</p>
            <h3 id="summary">Summary</h3>
            <br/>
            <p>Testing</p>
            """;

        System.out.println("—".repeat(50));
        Pattern htmlPattern = Pattern.compile("<([a-z_0-9]+)[^>]*>([^\\v</>]*)(</\\1>)*",
            Pattern.CASE_INSENSITIVE);
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);
        htmlMatcher.results()
            .filter(matchResult -> matchResult.group(1).toLowerCase().startsWith("h"))
            .forEach(matchResult -> System.out.println("Full Tag: " + matchResult.group(0) +
                "\n\tType: " + matchResult.group(1) +
                "\n\tText: " + matchResult.group(2)));

    }
}
