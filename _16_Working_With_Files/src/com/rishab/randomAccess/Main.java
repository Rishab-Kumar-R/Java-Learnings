package com.rishab.randomAccess;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();
    private static int recordsInFile = 0;

    static {
        try (RandomAccessFile raf = new RandomAccessFile("student.idx", "r")) {
            loadIndex(raf, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

//        BuildStudentData.build("student", true);
        try (RandomAccessFile raf = new RandomAccessFile("student.dat", "r")) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a Student Id or 0 to quit: ");

            while (scanner.hasNext()) {
                long studentId = Long.parseLong(scanner.nextLine());
                if (studentId < 1) {
                    break;
                }
                raf.seek(indexedIds.get(studentId));
                String targetedRecord = raf.readUTF();
                System.out.println(targetedRecord);
                System.out.print("Enter a Student Id or 0 to quit: ");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void loadIndex(RandomAccessFile raf, int indexPosition) {
        try {
            raf.seek(indexPosition);
            recordsInFile = raf.readInt();
            System.out.println(recordsInFile);

            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(raf.readLong(), raf.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
