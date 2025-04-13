package com.copilotapp.factorial;

/**
 * Calculates the factorial of a give number using an iterative approach.
 *
 * @param number the non-negative integer for which the factorial is to be calculated
 * @return the factorial of the given number as a long
 * @throws IllegalArgumentException if the input number is negative
 */
public class Factorial {
   public static long calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Number must be non-negative.");
        }
        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }
        return factorial;
    }

    public static void main(String[] args) {
        int number = -5; // Example input
        try {
            long result = calculateFactorial(number);
            System.out.println("The factorial of " + number + " is: " + result);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
