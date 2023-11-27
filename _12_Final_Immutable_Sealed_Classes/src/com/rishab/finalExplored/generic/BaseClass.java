package com.rishab.finalExplored.generic;

public class BaseClass {

    public final void recommendedMethod() {
        System.out.println("BaseClass.recommendedMethod()");
        optionalMethod();
        mandatoryMethod();
    }

    protected void optionalMethod() {
        System.out.println("BaseClass.optionalMethod()");
    }

    private void mandatoryMethod() {
        System.out.println("BaseClass.mandatoryMethod()");
    }

    public static void recommendedStatic() {
        System.out.println("BaseClass.recommendedStatic()");
        optionalStatic();
        mandatoryStatic();
    }

    protected static void optionalStatic() {
        System.out.println("BaseClass.optionalStatic()");
    }

    private static void mandatoryStatic() {
        System.out.println("BaseClass.mandatoryStatic()");
    }

}
