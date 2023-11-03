package com.rishab;

public class Operators {
	public static void main(String[] args) {

		/*
		 * Operators are special symbols that perform specific operations on one, two, or three operands, and then return a result
		 *
		 * Operands are the variables or values on which the operator operates
		 *
		 * Unary operators: operators that take a single operand/argument and perform an operation
		 *
		 * Binary operators: operators that take two operands/arguments and perform an operation
		 *
		 * Ternary operators: operators that take three operands/arguments and perform an operation
		 */

		// Unary operators
		int result = 1;
		System.out.println("result: " + result); // 1
		result--;
		System.out.println("result: " + result); // 0
		result++;
		System.out.println("result: " + result); // 1
		result = -result;
		System.out.println("result: " + result); // -1
		boolean success = false;
		System.out.println("success: " + success); // false
		System.out.println("success: " + !success); // true

		System.out.println();

		// Binary operators
		int a = 1;
		int b = 3;
		int c = a + b;
		System.out.println("c: " + c); // 4
		c = a - b;
		System.out.println("c: " + c); // -2
		c = a * b;
		System.out.println("c: " + c); // 3
		c = b / a;
		System.out.println("c: " + c); // 3
		c = b % a;
		System.out.println("c: " + c); // 0

		System.out.println();

		// Assignment operators
		int d = 1;
		System.out.println("d: " + d); // 1
		d += 2;
		System.out.println("d: " + d); // 3
		d -= 1;
		System.out.println("d: " + d); // 2
		d *= 2;
		System.out.println("d: " + d); // 4

        System.out.println();

        // Comparison operators
        int e = 1;
        int f = 2;
        System.out.println("e == f: " + (e == f)); // false
        System.out.println("e != f: " + (e != f)); // true
        System.out.println("e > f: " + (e > f)); // false
        System.out.println("e < f: " + (e < f)); // true
        System.out.println("e >= f: " + (e >= f)); // false
        System.out.println("e <= f: " + (e <= f)); // true

        System.out.println();

        // Logical operators
        boolean g = true;
        boolean h = false;
        System.out.println("g && h: " + (g && h)); // false
        System.out.println("g || h: " + (g || h)); // true
        System.out.println("!(g && h): " + !(g && h)); // true

        System.out.println();

        // Bitwise operators
        int i = 1;
        int j = 2;
        System.out.println("i & j: " + (i & j)); // 0
        System.out.println("i | j: " + (i | j)); // 3
        System.out.println("i ^ j: " + (i ^ j)); // 3
        System.out.println("~i: " + (~i)); // -2
        System.out.println("i << 2: " + (i << 2)); // 4
        System.out.println("i >> 2: " + (i >> 2)); // 0
        System.out.println("i >>> 2: " + (i >>> 2)); // 0

        System.out.println();

        // Ternary operator
        int k = 1;
        int l = 2;
        int m = (k > l) ? k : l;
        System.out.println("m: " + m); // 2

        System.out.println();

        // instanceof operator
        String n = "Hello";
        System.out.println("n instanceof String: " + (n instanceof String)); // true

        System.out.println();

        // Operator precedence
        /*
         * determines the grouping of terms in an expression. This affects how an expression is evaluated
         * Certain operators have higher precedence than others
         * 
         * The following table lists the operators in order of precedence, from highest to lowest
         * 
         * |--------------------|-------------------------------|-------------------|
         * | Category           | Operator(s)					| Associativity     |
         * |--------------------|-------------------------------|-------------------|
         * | Parentheses	    | () [] .						| Left to right     |
         * | Postfix		    | expr++ expr--				    | Left to right     |
         * | Unary		        | ++expr --expr +expr -expr	    | Right to left     |
         * | Unary      	    | ~ !							| Right to left     |
         * | Multiplicative     | * / %						    | Left to right     |
         * | Additive   	    | + -							| Left to right     |
         * | Shift		        | << >> >>>					    | Left to right     |
         * | Relational	        | < > <= >= instanceof		    | Left to right     |
         * | Equality		    | == !=						    | Left to right     |
         * | Bitwise AND	    | &							    | Left to right     |
         * | Bitwise XOR	    | ^							    | Left to right     |
         * | Bitwise OR	        | |							    | Left to right     |
         * | Logical AND	    | &&							| Left to right     |
         * | Logical OR	        | ||							| Left to right     |
         * | Ternary		    | ? :							| Right to left     |
         * | Assignment	        | = += -= *= /= %= &= ^= |=	    | Right to left     |
         * |--------------------|-------------------------------|-------------------|
         */

        double myFirstValue = 20.00d;
        double mySecondValue = 80.00d;
        double myTotalValue = myFirstValue + mySecondValue * 100.00d; // operator precedence in action
        System.out.println("myTotalValue: " + myTotalValue); // 8020.0
        double myRemainder = myTotalValue % 40.00d;
        System.out.println("myRemainder: " + myRemainder); // 20.0
        boolean isNoRemainder = (myRemainder == 0) ? true : false;
        System.out.println("isNoRemainder: " + isNoRemainder); // false
        if (!isNoRemainder) {
            System.out.println("Got some remainder");
        } else {
            System.out.println("No remainder");
        }
        
	}
}
