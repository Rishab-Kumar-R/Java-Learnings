package com.rishab.genericsExtras.model;

import java.util.Comparator;

public class StudentWithPercentComparator implements Comparator<StudentWithPercent> {
    @Override
    public int compare(StudentWithPercent o1, StudentWithPercent o2) {
        return (int) (o1.getPercentComplete() - o2.getPercentComplete());
    }
}
