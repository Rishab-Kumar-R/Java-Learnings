package com.rishab.finalExplored.consumer.specific;

import com.rishab.finalExplored.generic.BaseClass;

public class ChildClass extends BaseClass {
    @Override
    protected void optionalMethod() {
        System.out.println("ChildClass.optionalMethod()");
        super.optionalMethod();
    }

    // @Override
    // public void recommendedMethod() {
    //     System.out.println("ChildClass.recommendedMethod()");
    //     optionalMethod();
    // }

    // @Override // This is not allowed on static methods
    // This is called method hiding, as the method in the parent class is still there, but is hidden from the child class
    // If in base class the method is final, then method hiding is not allowed
    public static void recommendedStatic() {
        System.out.println("ChildClass.recommendedStatic()");
        optionalStatic();
        // mandatoryStatic();
    }
}
