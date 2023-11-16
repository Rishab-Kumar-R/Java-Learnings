package com.rishab.genericsExtras.model;

public class StudentWithPercent extends Student {
    private double percentComplete;

    public StudentWithPercent() {
        percentComplete = random.nextDouble(0, 100.001);
    }

    @Override
    public String toString() {
        return "%s %8.1f%%".formatted(super.toString(), percentComplete);
    }

    public double getPercentComplete() {
        return percentComplete;
    }

    @Override
    public boolean matchFieldValue(String fieldName, String value) {
        if (fieldName.equalsIgnoreCase("percentComplete")) {
            return this.percentComplete <= Integer.parseInt(value);
        }
        return super.matchFieldValue(fieldName, value);
    }
}
