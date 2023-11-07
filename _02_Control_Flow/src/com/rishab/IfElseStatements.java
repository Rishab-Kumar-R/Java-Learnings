package com.rishab;

public class IfElseStatements {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        if (a > b) {
            System.out.println("a is greater than b");
        } else if (a < b) {
            System.out.println("a is less than b");
        } else {
            System.out.println("a is equal to b");
        }

        int topScore = 80;
        int secondTopScore = 60;
        if (topScore > secondTopScore && topScore < 100) {
            System.out.println("Greater than second top score and less than 100");
        }

        if ((topScore > 90) || (secondTopScore <= 90)) {
            System.out.println("Either or both of the conditions are true");
        }

        String expression = (topScore > 90) ? "Greater than 90" : "Less than or equal to 90";
        System.out.println(expression);

        boolean gameOver = true;
        int score = 800;
        int levelCompleted = 5;
        int bonus = 100;

        // if (score < 5000 && score > 1000) {
        //     System.out.println("Your score was less than 5000 but greater than 1000");
        // } else if (score < 1000) {
        //     System.out.println("Your score was less than 1000");
        // } else {
        //     System.out.println("Got here");
        // }

        int finalScore = score;
        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            System.out.println("Your final score was: " + finalScore);
        } else {
            System.out.println("Game is not over");
        }

        // here, we just repeated the same code as above, but with different values
        // this is not a good practice, as it is not DRY (Don't Repeat Yourself)
        // instead, we can use a method to do this
        score = 10000;
        levelCompleted = 8;
        bonus = 200;
        finalScore = score;
        if (gameOver) {
            finalScore += (levelCompleted * bonus);
            System.out.println("Your final score was: " + finalScore);
        } else {
            System.out.println("Game is not over");
        }

    }
}
