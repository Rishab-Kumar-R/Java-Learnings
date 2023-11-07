package com.rishab;

public class WhileLoop {
    public static void main(String[] args) {

        // for (int i = 1; i <= 5; i++) {
        //     System.out.println(i);
        // }

        // while loop
        int j = 1;
        while (j <= 5) {
            System.out.println(j);
            j++;
        }

        // do-while loop
        boolean isReady = false;
        j = 1;
        do {
            if (j > 5) {
                break;
            }
            System.out.println(j);
            j++;
            isReady = (j > 0);
        } while (isReady);

        System.out.println();

        int number = 0;
        while (number < 50) {
            number += 5;
            if (number % 25 == 0) {
                continue;
            }
            System.out.print(number + "_");
        }

        System.out.println();

        // Challenge
        number = 4;
        int finishNumber = 20;
        int evenCount = 0, oddCount = 0;
        while (number <= finishNumber && evenCount < 5) {
            number++;
            if (!isEvenNumber(number)) {
                oddCount++;
                continue;
            }
            System.out.println("Even number " + number);
            evenCount++;
        }
        System.out.println("Total even numbers found: " + evenCount);
        System.out.println("Total odd numbers found: " + oddCount);

        System.out.println();

        System.out.println("Sum of digits in number 125 is " + sumDigits(125));
        System.out.println("Sum of digits in number 1000 is " + sumDigits(1000));

        System.out.println();

        System.out.println("Is 12321 a palindrome? " + isPalindrome(12321));
        System.out.println("Is 12345 a palindrome? " + isPalindrome(12345));
        System.out.println("Is 123321 a palindrome? " + isPalindrome(123321));

        System.out.println();

        System.out.println("Sum of first and last digits of 252 is " + sumFirstAndLastDigit(252));
        System.out.println("Sum of first and last digits of 257 is " + sumFirstAndLastDigit(257));
        System.out.println("Sum of first and last digits of 0 is " + sumFirstAndLastDigit(0));

        System.out.println();

        System.out.println("Sum of even digits in 123456789 is " + getEvenDigitSum(123456789));
        System.out.println("Sum of even digits in 252 is " + getEvenDigitSum(252));
        System.out.println("Sum of even digits in -22 is " + getEvenDigitSum(-22));

        System.out.println();

        System.out.println("Does 12 and 23 have a shared digit? " + hasSharedDigit(12, 23));
        System.out.println("Does 9 and 99 have a shared digit? " + hasSharedDigit(9, 99));
        System.out.println("Does 15 and 55 have a shared digit? " + hasSharedDigit(15, 55));
        System.out.println("Does 12 and 13 have a shared digit? " + hasSharedDigit(12, 13));

        System.out.println();

        System.out.println("Does 12, 23, and 34 have the same last digit? " + hasSameLastDigit(12, 23, 34));
        System.out.println("Does 9, 99, and 999 have the same last digit? " + hasSameLastDigit(9, 99, 999));
        System.out.println("Does 15, 55, and 555 have the same last digit? " + hasSameLastDigit(15, 55, 555));
        System.out.println("Does 12, 13, and 14 have the same last digit? " + hasSameLastDigit(12, 13, 14));

        System.out.println();

        printFactors(6);
        printFactors(32);
        printFactors(10);
        printFactors(-1);

        System.out.println();

        System.out.println("Greatest common divisor of 25 and 15 is " + getGreatestCommonDivisor(25, 15));
        System.out.println("Greatest common divisor of 12 and 30 is " + getGreatestCommonDivisor(12, 30));
        System.out.println("Greatest common divisor of 9 and 18 is " + getGreatestCommonDivisor(9, 18));
        System.out.println("Greatest common divisor of 81 and 153 is " + getGreatestCommonDivisor(81, 153));

        System.out.println();

        System.out.println("Is 6 a perfect number? " + isPerfectNumber(6));
        System.out.println("Is 28 a perfect number? " + isPerfectNumber(28));
        System.out.println("Is 5 a perfect number? " + isPerfectNumber(5));
        System.out.println("Is -1 a perfect number? " + isPerfectNumber(-1));

        System.out.println();

        numberToWords(123);
        System.out.println();
        numberToWords(1010);
        System.out.println();
        numberToWords(1000);
        System.out.println();
        numberToWords(-12);

        System.out.println();

        System.out.println("Can we pack 1 big flour bag, 0 small flour bags, and 4 kilos of goal? " + canPack(1, 0, 4));
        System.out.println("Can we pack 1 big flour bag, 0 small flour bags, and 5 kilos of goal? " + canPack(1, 0, 5));
        System.out.println("Can we pack 0 big flour bag, 5 small flour bags, and 4 kilos of goal? " + canPack(0, 5, 4));
        System.out.println("Can we pack 2 big flour bag, 2 small flour bags, and 11 kilos of goal? " + canPack(2, 2, 11));
        System.out.println("Can we pack -3 big flour bag, 2 small flour bags, and 12 kilos of goal? " + canPack(-3, 2, 12));

        System.out.println();

        System.out.println("Largest prime factor of 21 is " + getLargestPrime(21));
        System.out.println("Largest prime factor of 217 is " + getLargestPrime(217));
        System.out.println("Largest prime factor of 0 is " + getLargestPrime(0));
        System.out.println("Largest prime factor of 45 is " + getLargestPrime(45));

        System.out.println();

        printDiagonalStar(5);
        System.out.println();
        printDiagonalStar(8);
        System.out.println();
        printDiagonalStar(10);
    }

    private static boolean isEvenNumber(int number) {
        return (number % 2 == 0);
    }

    private static int sumDigits(int number) {
        if (number < 0) {
            return -1;
        }
        int sum = 0;
        int digit;
        while (number > 0) {
            digit = number % 10;
            sum += digit;
            number /= 10;
        }
        return sum;
    }

    private static boolean isPalindrome(int number) {
        int originalNumber = number;
        int reverse = 0;

        while (number != 0) {
            int lastDigit = number % 10;
            reverse = (reverse * 10) + lastDigit;
            number /= 10;
        }
        return originalNumber == reverse;
    }

    private static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1;
        }
        int firstDigit = 0;
        int lastDigit = number % 10;

        while (number > 0) {
            if (number < 10) {
                firstDigit = number;
            }
            number /= 10;
        }
        return firstDigit + lastDigit;
    }

    private static int getEvenDigitSum(int number) {
        if (number < 0) {
            return -1;
        }

        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            if (digit % 2 == 0) {
                sum += digit;
            }
            number /= 10;
        }
        return sum;
    }

    private static boolean hasSharedDigit(int num1, int num2) {
        if (num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99) {
            return false;
        }
        int digit1 = num1 % 10;
        int digit2 = num2 % 10;

        int num1WithoutLastDigit = num1 / 10;
        int num2WithoutLastDigit = num2 / 10;

        return (digit1 == digit2 || digit1 == num2WithoutLastDigit || digit2 == num1WithoutLastDigit || num1WithoutLastDigit == num2WithoutLastDigit);
    }

    private static boolean hasSameLastDigit(int num1, int num2, int num3) {
        if (!isValid(num1) || !isValid(num2) || !isValid(num3)) {
            return false;
        }
        int digit1 = num1 % 10;
        int digit2 = num2 % 10;
        int digit3 = num3 % 10;

        return (digit1 == digit2) || (digit2 == digit3) || (digit1 == digit3);
    }

    private static boolean isValid(int number) {
        return (number >= 10 && number <= 1000);
    }

    private static void printFactors(int number) {
        if (number < 1) {
            System.out.println("Invalid Value");
        } else {
            for (int i = 1; i <= number; i++) {
                if (number % i == 0) {
                    System.out.println("Factors of " + number + ": " + i);
                }
            }
        }
    }

    private static int getGreatestCommonDivisor(int first, int second) {
        if (first < 10 || second < 10) {
            return -1;
        }

        while (first != second) {
            if (first > second) {
                first -= second;
            } else {
                second -= first;
            }
        }
        return first;
    }

    private static boolean isPerfectNumber(int number) {
        if (number < 1) {
            return false;
        }

        int sum = 0;
        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }
        return sum == number;
    }

    private static void numberToWords(int number) {
        if (number < 0) {
            System.out.println("Invalid value");
        } else if (number == 0) {
            System.out.print("Zero ");
        } else {
            int reversedNumber = reverse(number);
            int originalDigitCount = getDigitCount(number);
            int reversedDigitCount = getDigitCount(reversedNumber);

            while (reversedNumber > 0) {
                int digit = reversedNumber % 10;
                switch (digit) {
                    case 0 -> System.out.print("Zero ");
                    case 1 -> System.out.print("One ");
                    case 2 -> System.out.print("Two ");
                    case 3 -> System.out.print("Three ");
                    case 4 -> System.out.print("Four ");
                    case 5 -> System.out.print("Five ");
                    case 6 -> System.out.print("Six ");
                    case 7 -> System.out.print("Seven ");
                    case 8 -> System.out.print("Eight ");
                    case 9 -> System.out.print("Nine ");
                    default -> System.out.println("Invalid digit");
                }
                reversedNumber /= 10;
            }

            if (originalDigitCount > reversedDigitCount) {
                int difference = originalDigitCount - reversedDigitCount;
                for (int i = 0; i < difference; i++) {
                    System.out.print("Zero ");
                }
            }
        }
    }

    private static int reverse(int number) {
        int reverse = 0;
        while (number != 0) {
            int lastDigit = number % 10;
            reverse = (reverse * 10) + lastDigit;
            number /= 10;
        }
        return reverse;
    }

    private static int getDigitCount(int number) {
        if (number < 0) {
            return -1;
        }

        if (number == 0) {
            return 1;
        }

        int count = 0;
        while (number > 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    private static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }
        int bigCountWeight = bigCount * 5;

        if (bigCountWeight + smallCount < goal) {
            return false;
        } else return smallCount >= goal % 5;
    }

    private static int getLargestPrime(int number) {
        if (number <= 1) {
            return -1;
        }

        int largestPrime = -1;
        for (int i = 2; i <= number; i++) {
            while (number % i == 0) {
                largestPrime = i;
                number /= i;
            }
        }
        return largestPrime;
    }

    private static void printDiagonalStar(int number) {
        if (number < 5) {
            System.out.println("Invalid value");
        } else {
            for (int row = 1; row <= number; row++) {
                for (int col = 1; col <= number; col++) {
                    if (row == 1 || row == number || col == 1 || col == number || row == col || col == (number - row + 1)) {
                        System.out.print("*");
                    } else {
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
        }
    }
}
