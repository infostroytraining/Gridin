package com.infostroy.introduction;

public class MathUtils {

	/**
	 * Returns the greatest common divider of given two numbers
	 * 
	 * @param firstNumber
	 *            - positive number
	 * @param secondNumber
	 *            - positive number
	 * @return greatest common divider of two numbers
	 */
	public int getGreatestCommonDivider(int firstNumber, int secondNumber) throws IllegalArgumentException {
		if (firstNumber < 0 || secondNumber < 0) {
			throw new IllegalArgumentException();
		}
		int firstNumberCopy = firstNumber;
		int secondNumberCopy = secondNumber;
		int residueOfDivision = 0;
		while (secondNumberCopy != 0) {
			residueOfDivision = firstNumberCopy % secondNumberCopy;
			firstNumberCopy = secondNumberCopy;
			secondNumberCopy = residueOfDivision;
		}
		return firstNumberCopy;
	}

	/**
	 * Returns sum of digits of the given number
	 * 
	 * @param number
	 *            - positive number
	 * @return the sum of digits of the given number
	 */
	public int getSumOfDigits(int number) throws IllegalArgumentException {
		if (number < 0) {
			throw new IllegalArgumentException();
		}
		int result = 0;
		int numberCopy = number;
		while (numberCopy != 0) {
			result += numberCopy % 10;
			numberCopy /= 10;
		}
		return result;
	}

	/**
	 * Checks if the given number is prime or not
	 * 
	 * @param number
	 *            - positive number(missing!!!)
	 * @return true - if number is prime, if not return false
	 */
	public boolean isPrime(int number) throws IllegalArgumentException {
		if (number < 0) {
			throw new IllegalArgumentException();
		}
		if (number < 2) {
			return false;
		}
		for (int i = 2; i * i <= number; ++i) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
	 * 
	 * @param n
	 *            - positive number
	 */
	public int getSumOfRow(int n) throws IllegalArgumentException {
		int result = 0;
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		for (int i = n; i > 0; --i) {
			if (i % 2 == 0) {
				result -= FastFactorial.factorial(i);
			} else {
				result += FastFactorial.factorial(i);
			}
		}
		return result;
	}

	/**
	 * Returns Fibonacci series of a specified length
	 * 
	 * @param length
	 *            - the length of the Fibonacci series
	 * @return array filled with Fibonacci series
	 */
	public int[] getFibonacciSeries(int length) throws IllegalArgumentException {
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		int fibonacci[] = new int[length];
		if (length >= 1) {
			fibonacci[0] = 1;
		}
		if (length > 2) {
			fibonacci[1] = 1;
			for (int i = 2; i < length; ++i) {
				fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
			}
		}
		return fibonacci;
	}

	/**
	 * Returns array with prime numbers
	 * 
	 * @param length
	 *            - the length of prime numbers series
	 * @return array filled with prime numbers
	 */
	public int[] getPrimeSeries(int length) throws IllegalArgumentException {
		if (length < 0) {
			throw new IllegalArgumentException();
		}
		int[] array = new int[length];
		int number = 2;
		for (int arrayPos = 0; arrayPos < length;) {
			if (isPrime(number)) {
				array[arrayPos] = number;
				++arrayPos;
			}
			++number;
		}
		return array;
	}
}
