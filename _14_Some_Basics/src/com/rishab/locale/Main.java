package com.rishab.locale;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Default Locale: " + Locale.getDefault());
        Locale.setDefault(Locale.US);
        System.out.println("Default Locale: " + Locale.getDefault());

        Locale en = new Locale("en");
        Locale enIN = new Locale("en", "IN");
        Locale enCA = new Locale.Builder().setLanguage("en").setRegion("CA").build();
        Locale enZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        for (Locale locale : List.of(Locale.getDefault(), Locale.US, en, enIN, enCA, Locale.UK, enZ)) {
            System.out.println(locale.getDisplayName() + " = " + LocalDateTime.now().format(dtf.withLocale(locale)));
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        LocalDate mayFifth = LocalDate.of(2020, 5, 5);
        System.out.println("—".repeat(50));
        for(Locale locale : List.of(
            Locale.CANADA, Locale.CANADA_FRENCH, Locale.FRANCE, Locale.GERMANY, Locale.TAIWAN, Locale.JAPAN, Locale.ITALY)) {
            System.out.println(
                locale.getDisplayName() + " : " + locale.getDisplayName(locale) + " = " +
                    mayFifth.format(dateTimeFormatter.withLocale(locale)));
            System.out.print(String.format(locale, "\t%1$tA, %1$tB %1$te, %1$tY %n", mayFifth));

            NumberFormat decimal = NumberFormat.getNumberInstance(locale);
            decimal.setMaximumFractionDigits(6);
            System.out.println(decimal.format(123456789.123456));

            NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
            Currency localCurrency = Currency.getInstance(locale);
            System.out.println(currency.format(555.555) + " [" + localCurrency.getCurrencyCode() + "]" +
                localCurrency.getDisplayName(locale) + "/" + localCurrency.getDisplayName());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the loan amount: ");
        scanner.useLocale(Locale.ITALY);
        BigDecimal myLoan = scanner.nextBigDecimal();
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ITALY);
        System.out.println("My Loan: " + numberFormat.format(myLoan));

    }
}
