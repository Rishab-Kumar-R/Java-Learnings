package com.rishab.randomAccess;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

record Employee(int employeeID, String firstName, String lastName, double salary) {
}

public class Challenge {
    private static final Map<Integer, Long> indexedIds = new HashMap<>();

    static {
        int recordsInFile = 0;

        try (RandomAccessFile raf = new RandomAccessFile("employees.dat", "r")) {
            recordsInFile = raf.readInt();
            System.out.println(recordsInFile + " records in file");

            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(raf.readInt(), raf.readLong());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try (RandomAccessFile raf = new RandomAccessFile("employees.dat", "rw")) {
            Scanner scanner = new Scanner(System.in);
            List<Integer> ids = new ArrayList<>(indexedIds.keySet());
            Collections.sort(ids);

            while (true) {
                System.out.println(ids);
                System.out.print("Enter an employee ID or 0 to quit: ");

                if (!scanner.hasNext()) break;

                int employeeId = Integer.parseInt(scanner.nextLine());
                if (employeeId < 1) break;
                if (!ids.contains(employeeId)) {
                    System.out.println("There is no employee with ID " + employeeId);
                    continue;
                }

                Employee employee = readRecord(raf, employeeId);
                System.out.print("Enter a new salary for " + employee.firstName() + ": ");
                try {
                    double salary = Double.parseDouble(scanner.nextLine());
                    raf.seek(indexedIds.get(employeeId) + 4);
                    raf.writeDouble(salary);
                    readRecord(raf, employeeId);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid salary");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Employee readRecord(RandomAccessFile raf, int employeeId) throws IOException {
        raf.seek(indexedIds.get(employeeId));

        int id = raf.readInt();
        double salary = raf.readDouble();
        String firstName = raf.readUTF();
        String lastName = raf.readUTF();

        Employee employee = new Employee(id, firstName, lastName, salary);
        System.out.println(employee);
        return employee;
    }
}
