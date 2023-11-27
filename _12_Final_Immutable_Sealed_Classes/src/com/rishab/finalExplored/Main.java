package com.rishab.finalExplored;

import com.rishab.finalExplored.consumer.specific.ChildClass;
import com.rishab.finalExplored.external.util.Logger;
import com.rishab.finalExplored.generic.BaseClass;

public class Main {
    public static void main(String[] args) {

        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();
        BaseClass childReferredToAsBaseClass = new ChildClass();

        String lineSeparator = "—".repeat(50);

        parent.recommendedMethod();
        System.out.println(lineSeparator);
        child.recommendedMethod();
        System.out.println(lineSeparator);
        childReferredToAsBaseClass.recommendedMethod();

        System.out.println(lineSeparator);
        parent.recommendedStatic();
        System.out.println(lineSeparator);
        child.recommendedStatic();
        System.out.println(lineSeparator);
        childReferredToAsBaseClass.recommendedStatic();

        System.out.println(lineSeparator);
        BaseClass.recommendedStatic();
        ChildClass.recommendedStatic();

        System.out.println(lineSeparator);
        String xArgument = "This is argument x";
        StringBuilder zArgument = new StringBuilder("This is argument z");
        doXYZ(xArgument, 10, zArgument);
        System.out.println("xArgument = " + xArgument);
        System.out.println("zArgument = " + zArgument);

        System.out.println(lineSeparator);
        StringBuilder tracker = new StringBuilder("Start");
        Logger.logToConsole(tracker.toString()); // passing tracker.toString() instead of tracker will not clear the tracker
        tracker.append("End");
        Logger.logToConsole(tracker.toString()); // this is called defensive copying
        System.out.println("tracker = " + tracker);

    }

    private static void doXYZ(String x, int y, final StringBuilder z) {
        final String c = x + y;
        System.out.println("c = " + c);
        x = c;
        z.append(y); // This is allowed as z is final, because we are not changing the reference of z
        // z = new StringBuilder("This is a new StringBuilder"); // This is not allowed as z is final
    }
}
