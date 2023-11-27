package com.rishab.constructorsProject;

import java.time.LocalDate;

public enum Generation {
    GEN_ALPHA {
        {
            System.out.println("Gen Alpha instance initializer block");
        }
    },
    GEN_Z(1997, 2012),
    MILLENNIAL(1981, 1996),
    GEN_X(1965, 1980),
    BABY_BOOMER(1946, 1964),
    SILENT_GENERATION(1928, 1945),
    GREATEST_GENERATION(1910, 1927);

    private final int startYear;
    private final int endYear;

    Generation() {
        this(2013, LocalDate.now().getYear());
    }

    Generation(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
        System.out.println(this);
    }

    @Override
    public String toString() {
        return this.name() + " (" + startYear + ", " + endYear + ")";
    }
}
