package com.rishab;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatching {
    public static void main(String[] args) {

        String sentence = "I like to learn Java is A.L.W.A.Y.S a contradiction.";
        boolean matched = Pattern.matches("[A-Z].*[.]", sentence);
        System.out.println(sentence + " : " + matched);

        Pattern firstPattern = Pattern.compile("[A-Z].*?[.]");
        Matcher matcher = firstPattern.matcher(sentence);
        System.out.println(sentence + " : " + matcher.matches());

        System.out.println("sentence.length: " + sentence.length());
        System.out.println("matcher.end(): " + matcher.end()); // end index of the match
        System.out.println("matcher.lookingAt(): " + matcher.lookingAt() + " : " + sentence); // checks if the pattern matches the beginning of the string
        System.out.println("matcher.end(): " + matcher.end());
        System.out.println("Matched on: " + sentence.substring(0, matcher.end()));

        matcher.reset();
        System.out.println("matcher.find(): " + matcher.find() + " : " + sentence); // checks if the pattern matches any part of the string (not just the beginning)
        System.out.println("matcher.end(): " + matcher.end());
        System.out.println("Matched on: " + sentence.substring(matcher.start(), matcher.end()));

        System.out.println("matcher.group(): " + matcher.group());

        String htmlSnippet = """
            <H1>My Heading</H1>
            <h2>Sub-Heading</h2>
            <p>This is a paragraph about something.</p>
            <p>Another paragraph here.</p>
            <h3>Summary</h3>
            """;

        Pattern htmlPattern = Pattern.compile("<[hH](?<level>\\d)>(.*)</[hH]\\d>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlSnippet);

        System.out.println("—".repeat(50));
        while (htmlMatcher.find()) {
            // System.out.println("Found match: " + htmlMatcher.group());
            // System.out.println("Found match 0: " + htmlMatcher.group(0)); // same as group()
            System.out.println(htmlMatcher.group("level") + " : " + htmlMatcher.group(2));
            System.out.println("index: " + htmlMatcher.start("level"));
        }

        System.out.println("—".repeat(50));
        htmlMatcher.reset();
        htmlMatcher.results() // returns a stream of matches
            .forEach(matchResult -> System.out.println(matchResult.group(1) + " : " + matchResult.group(2)));

        System.out.println("—".repeat(50));
        String tabbedText = """
            group1	group2	group3
            1	2	3
            a	b	c
            """;

        tabbedText.lines()
            .flatMap(line -> Pattern.compile("\\t").splitAsStream(line))
            .forEach(System.out::println);

        System.out.println("—".repeat(50));
        htmlMatcher.reset();
        String updatedSnippet = htmlMatcher.replaceFirst((matchResult) -> "<em>" + matchResult.group(2) + "</em>");
        System.out.println(updatedSnippet);
        System.out.println(htmlMatcher.start() + " : " + htmlMatcher.end());
        System.out.println(htmlMatcher.group(2));

        htmlMatcher.usePattern(Pattern.compile("<([hH]\\d)>(.*)</\\1>"));

        System.out.println("—".repeat(50));
        htmlMatcher.reset();
        System.out.println("Using back references:\n" + htmlMatcher.replaceFirst("<em>$2</em>"));

        System.out.println("—".repeat(50));
        String replacedHTML = htmlMatcher.replaceAll((matchResult) -> "<em>" + matchResult.group(2) + "</em>");
        System.out.println(replacedHTML);

        System.out.println("—".repeat(50));
        htmlMatcher.reset();
        StringBuilder sb = new StringBuilder();
        int index = 1;
        while (htmlMatcher.find()) {
            htmlMatcher.appendReplacement(sb,
                switch (htmlMatcher.group(1).toLowerCase()) {
                    case "h1" -> "<head>$2</head>";
                    case "h2" -> "<em>$2</em>";
                    default -> "<$1>" + index++ + ". $2</$1>";
                });
        }
        htmlMatcher.appendTail(sb);
        System.out.println(sb);

    }
}
